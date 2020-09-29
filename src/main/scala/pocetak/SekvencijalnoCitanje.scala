package pocetak

import scala.io.Source

trait SekvencijalnoCitanje {

  def sekvencijalnoCitanje() {
    for (i <- 1 until 11) {
      val izvorniFajlovi = Source.fromFile("Zadaca2File" + i.toString + ".txt")
      val linijeFajla: Seq[String] = izvorniFajlovi.getLines().toSeq

      val vijesti: Seq[News] = linijeFajla.map { komanda =>
        val dijelovi: Seq[String] = komanda.split("\\|")

        new News(id = dijelovi(0), simbol = dijelovi(1), naslov = dijelovi(2),
          datum = dijelovi(3), stranica = dijelovi(4))

        }
      }
    }
}
