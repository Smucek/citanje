package pocetak

import scala.io.Source

trait SekvencijalnoCitanje {

  def sekvencijalnoCitanje() {
    for (i <- 1 until 11) {
      val izvorniFajlovi = Source.fromFile("Zadaca2File" + i.toString + ".txt")
      val linijeFajla: Seq[String] = izvorniFajlovi.getLines().toSeq

      linijeFajla.foreach { linija =>
        println(linija)
      }

//      for (linija <- linijeFajla) println(linija.trim)
//      println("Broj procitanih fajlova je " + i)
    }
  }
}
