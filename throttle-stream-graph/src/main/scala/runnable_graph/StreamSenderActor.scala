package runnable_graph

import akka.NotUsed
import akka.actor.SupervisorStrategy.Restart
import akka.actor.{
  Actor,
  ActorLogging,
  ActorRef,
  ActorSystem,
  OneForOneStrategy,
  Props,
  SupervisorStrategy
}
import akka.stream.scaladsl.{
  FileIO,
  Flow,
  Framing,
  GraphDSL,
  RunnableGraph,
  Sink
}
import akka.stream.{ActorMaterializer, ClosedShape, ThrottleMode}
import akka.util.ByteString

import scala.concurrent.duration.Duration
import scala.language.postfixOps

class StreamSenderActor(target: ActorRef) extends Actor with ActorLogging {
  override def receive: Receive = {
    case StreamBook(filename) =>
      val materializer = ActorMaterializer.create(context) // Materializing and running a stream always requires a Materializer to be in implicit scope.
      val sink: Sink[Any, NotUsed] = Sink.actorRef(target, NotUsed)
      import java.nio.file.Paths
      val sourceLines = FileIO.fromPath(Paths.get(filename))

      val lines: Flow[ByteString, String, NotUsed] = Framing
        .delimiter(
          ByteString(System.lineSeparator),
          10000,
          allowTruncation = true
        )
        .map(bs => bs.utf8String)

      val throttledLines: Flow[ByteString, StreamLine, NotUsed] = lines.throttle(1, Duration(2, "seconds"), 1, ThrottleMode.shaping)
        .to(sink)

      RunnableGraph
        .fromGraph(GraphDSL.create() {
          implicit builder: GraphDSL.Builder[NotUsed] =>
            import akka.stream.scaladsl.GraphDSL.Implicits._
            sourceLines ~> throttledLines ~> sink
//            sourceLines ~> sink
            ClosedShape
        })
        .run()(materializer)
  }

  override def supervisorStrategy: SupervisorStrategy =
    OneForOneStrategy(10, Duration(60, "seconds")) {
      case _: Exception => Restart
    }
}
class StreamReceiverActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case StreamLine(line) => log.info(s"line: $line")
    case x                => log info s"all: $x"
  }
}

case class StreamBook(fileName: String)
case class StreamLine(line: String)

object StreamSenderActor {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("Thottler-Messages")
    val target = system.actorOf(Props[StreamReceiverActor], "receiver")
    val sender =
      system.actorOf(Props(classOf[StreamSenderActor], target), "sender")

    sender ! StreamBook("throttle-stream-graph/src/main/resources/log.txt")

    Thread sleep 4000
    system terminate
  }
}
