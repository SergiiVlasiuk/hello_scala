package stream_queue

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Keep, Sink, Source}
import akka.stream.{ActorMaterializer, OverflowStrategy, QueueOfferResult}

import scala.concurrent.duration._

object QueueSenderActor {

  def main(args: Array[String]): Unit = {
    implicit val actorSystem: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val ec = actorSystem.dispatcher

    val bufferSize = 10
    val elementsToProcess = 2

    val queue = Source
      .queue[Int](bufferSize, OverflowStrategy.backpressure)
      .throttle(elementsToProcess, 3.second)
      .map(x => x * x)
      .toMat(Sink.foreach(x => println(s"completed $x")))(Keep.left)
      .run()

    val source = Source(1 to 10)

    source
      .mapAsync(1)(x => {
        queue.offer(x).map {
          case QueueOfferResult.Enqueued => println(s"enqueued $x")
          case QueueOfferResult.Dropped  => println(s"dropped $x")
          case QueueOfferResult.Failure(ex) =>
            println(s"Offer failed ${ex.getMessage}")
          case QueueOfferResult.QueueClosed => println("Source Queue closed")
        }
      })
      .runWith(Sink.ignore)
  }
}
