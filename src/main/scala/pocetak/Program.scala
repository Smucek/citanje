package pocetak

import scala.concurrent.Future
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Program extends SekvencijalnoCitanje {

  def futureCitanje(): Future[Unit] = Future {
    sekvencijalnoCitanje()
}

  def main(args: Array[String]) = {


    futureCitanje().onComplete {
      case Success(value) => futureCitanje()
      case Failure(exception) => println(s"Greska + ${exception.getMessage}")
    }
    val startP = System.currentTimeMillis()
    Thread.sleep(3000)
    val a = (s"Paralelno citanje 10 txt fajlova ocitano u ${System.currentTimeMillis() - startP - 3000} ms")

    val start = System.currentTimeMillis()
    sekvencijalnoCitanje()
    println(s"Sekvencijalno citanje 10 txt fajlova ocitano u ${System.currentTimeMillis() - start} ms")
    println(a)
  }
}
