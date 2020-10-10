package pocetak

import akka.actor.Actor
import pocetak.FileReader.ReadFromFile

import scala.io.Source

object FileReader {

  case class ReadFromFile(numberFile: Int) {

  }
}

class FileReader extends Actor {
//  val fileRange: Seq[Int] = 1 to 10




  override def receive: Receive = {
    case ReadFromFile(numberFile) => {
      def readFromFile: Seq[News] = {
        val sourceFile = Source.fromFile("Zadaca2File" + numberFile.toString + ".txt")
        val fileLines: Seq[String] = sourceFile.getLines().toSeq

        val fileLinesSplit: Seq[Seq[String]] = fileLines.map { com =>
          com.split("\\|").map(znak => znak.trim)
        }
        val news: Seq[News] = fileLinesSplit.map { com =>
          new News(com(0), com(1), com(2), com(3), com(4))
        }
        news
      }
      sender ! readFromFile
    }
    }
}
