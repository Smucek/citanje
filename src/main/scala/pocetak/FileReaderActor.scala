package pocetak

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import pocetak.FileReader.ReadFromFile

import scala.concurrent.duration._

object FileReaderActor {
  def main(args: Array[String]): Unit = {


    val as = ActorSystem("file-reader")
    implicit val timeout = new Timeout(2.seconds)

    val reader = as.actorOf(Props(new FileReader))

//    val fN: Seq[Int] = 1 to 10

    val startActorRead = System.currentTimeMillis()

    reader ! ReadFromFile(1)
    reader ! ReadFromFile(2)
    reader ! ReadFromFile(3)
    reader ! ReadFromFile(4)
    reader ! ReadFromFile(5)
    reader ! ReadFromFile(6)
    reader ! ReadFromFile(7)
    reader ! ReadFromFile(8)
    reader ! ReadFromFile(9)
    reader ! ReadFromFile(10)

    val timeActor = (s"Actor read of 10 txt files read in ${System.currentTimeMillis() - startActorRead} ms")
    println(timeActor)


    //    result.onComplete {
//          case Success(r) => println("Read success")
//          case Failure(exception) => println(s"Can not read file ${exception.getMessage}")
//
//        }
      }
}
