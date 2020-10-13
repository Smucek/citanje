package pocetak

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import pocetak.FileReader.ReadFromFile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object FileReaderActor {
  def main(args: Array[String]): Unit = {


    val as = ActorSystem("file-reader")
    implicit val timeout = new Timeout(2.seconds)

    val reader = as.actorOf(Props(new FileReader))

    val startActorRead = System.currentTimeMillis()
    val fileNumbers: Seq[Int] = 0 to 10
    val futures = fileNumbers.map { number => {
      (reader ? ReadFromFile(number)).asInstanceOf[Future[Seq[News]]]
      }
    }

    val result = Future.sequence(futures).recover { case ex: Exception => "Cant read files"}
//    Await.result(result,1.seconds)


    result.onComplete {
          case Success(r) => println(s"Actor read of ${r.size} txt files read in ${System.currentTimeMillis() - startActorRead} ms")
          case Failure(exception) => println(s"File does not exists ${exception.getMessage}")
        }
      }
}
