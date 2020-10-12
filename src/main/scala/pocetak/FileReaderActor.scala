package pocetak

import akka.actor.Status.{Failure, Success}
import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import pocetak.FileReader.ReadFromFile
import akka.pattern.ask
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

object FileReaderActor {
  def main(args: Array[String]): Unit = {


    val as = ActorSystem("file-reader")
    implicit val timeout = new Timeout(2.seconds)

    val reader = as.actorOf(Props(new FileReader))


    val startActorRead = System.currentTimeMillis()

    val fN: Seq[Int] = 1 to 10


    //    reader ? ReadFromFile(1)

    val futures: Seq[Future[Any]] = fN.map { number => {

        reader ? ReadFromFile(number)
      }
    }
    val res = Future.sequence(futures)

        res.onComplete {
          case Success(r) => println(s"Future read of ${r} txt files read in ${System.currentTimeMillis() - startActorRead} ms")
          case Failure(exception) => println(s"Can not read file ${exception.getMessage}")

        }
      }
}
