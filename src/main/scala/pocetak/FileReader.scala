package pocetak

import akka.actor.Actor
import akka.pattern.ask
import akka.util.Timeout
import pocetak.FileReader.ReadFromFile

import scala.concurrent.duration._
import scala.io.Source

object FileReader {

  case class ReadFromFile(numberFile: Int)
}

class FileReader extends Actor {
  override def receive: Receive = {
    case ReadFromFile(numberFile) => {
      implicit val timeout = new Timeout(2.seconds)
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
      sender ? readFromFile
    }
    }
}
