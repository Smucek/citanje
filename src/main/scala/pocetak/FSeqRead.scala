package pocetak

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source
import scala.util.{Failure, Success}

object FSeqRead {

  def readFromFile(fileNumber: Int) {
    val sourceFile = Source.fromFile("Zadaca2File" + fileNumber.toString + ".txt")
    val fileLines: Seq[String] = sourceFile.getLines().toSeq

    val fileLinesSplit: Seq[Seq[String]] = fileLines.map { komanda =>
      komanda.split("\\|").map(znak => znak.trim)
    }
    fileLinesSplit.foreach { komanda =>
      new News(komanda(0), komanda(1), komanda(2), komanda(3), komanda(4))
    }
//println(fileLinesSplit)
  }

    def main(args: Array[String]): Unit = {
      val startP = System.currentTimeMillis()
      for (i <- 1 to 10) {
        readFromFile(i)
      }
      val a = (s"Paralelno citanje 10 txt fajlova ocitano u ${System.currentTimeMillis() - startP} ms")
      println(a)

      val seq: Seq[Int] = 1 to 10
      val futurei: Seq[Future[Unit]] = seq.map { broj =>
        Future {
          readFromFile(broj)
        }
      }
      val rezultat: Future[Seq[Unit]] = Future.sequence(futurei)

      rezultat.onComplete {
        case Success(r) => r
        case Failure(ex) => println(s"greska: ${ex.getMessage}")
      }
      val startF = System.currentTimeMillis()
      Thread.sleep(3000)
      val b = (s"Future citanje 10 txt fajlova ocitano u ${System.currentTimeMillis() - startF-3000} ms")
      println(b)
    }
  }