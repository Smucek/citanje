package pocetak

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source
import scala.util.{Failure, Success}

object FSeqRead {

  def readFromFile(fileNumber: Int): Seq[News] = {
    val sourceFile = Source.fromFile("Zadaca2File" + fileNumber.toString + ".txt")
    val fileLines: Seq[String] = sourceFile.getLines().toSeq

    val fileLinesSplit: Seq[Seq[String]] = fileLines.map { com =>
      com.split("\\|").map(znak => znak.trim)
    }
    val news: Seq[News] = fileLinesSplit.map { com =>
      new News(com(0), com(1), com(2), com(3), com(4))
    }
    news
  }

    def main(args: Array[String]): Unit = {
      val startSeqRead = System.currentTimeMillis()
      for (i <- 1 to 10) {
        readFromFile(i)
      }
      val timeSeq = (s"Sequentional read of 10 txt files read in ${System.currentTimeMillis() - startSeqRead} ms")
      println(timeSeq)

      val startF = System.currentTimeMillis()
      val seq: Seq[Int] = 1 to 10
      val futures: Seq[Future[Seq[News]]] = seq.map { broj =>
        Future {
          readFromFile(broj)
        }
      }
      val res: Future[Seq[Seq[News]]] = Future.sequence(futures)

      res.onComplete {
        case Success(r) => println(s"Future read of ${r.size} txt files read in ${System.currentTimeMillis() - startF} ms")
        case Failure(ex) => println(s"ex: ${ex.getMessage}")
      }
      Thread.sleep(3000)
    }
  }