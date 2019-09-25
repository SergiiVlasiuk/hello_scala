package stream_actor

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
import akka.stream.scaladsl.{Sink, Source}
import akka.stream.{ActorMaterializer, OverflowStrategy, ThrottleMode}

import scala.concurrent.duration.Duration
import scala.language.postfixOps

class StreamSenderActor(target: ActorRef) extends Actor with ActorLogging {
  override def receive: Receive = {
    case StreamBook(filename) =>
      val materializer = ActorMaterializer.create(context) // Materializing and running a stream always requires a Materializer to be in implicit scope.
      val sink: ActorRef = Source
        .actorRef(1000, OverflowStrategy.dropNew) // If the buffer is full when a new element arrives, drops the new element.
        .throttle(1, Duration(1, "seconds"), 1, ThrottleMode.shaping) // throttle - to slow down the stream to 1 element per second.
        .to(Sink.actorRef(target, NotUsed))
        .run()(materializer)

      import scala.io.Source.fromFile // doesn't work
      val lines = fromFile(filename).getLines
      lines.foreach(
        line => {
          println(line)
          sink ! StreamLine(line)
        })
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

    sender ! StreamBook("throttle-stream-actor/src/main/resources/log.txt")

    Thread sleep 4000
    system terminate
  }
}
