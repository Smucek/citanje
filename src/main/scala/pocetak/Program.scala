package pocetak

import scala.io.Source

object Program {

  def main(args: Array[String]) = {

    //      val start = System.currentTimeMillis()
    for (i <- 1 until 11) {
      Source.fromFile(("Zadaca2File" + i.toString + ".txt"))
      println("Broj procitanih fajlova je " + i)
    }

  }
}
