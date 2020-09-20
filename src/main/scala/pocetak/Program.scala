package pocetak

object Program extends SekvencijalnoCitanje {

  def main(args: Array[String]) = {

    val start = System.currentTimeMillis()
    sekvencijalnoCitanje()
    println(s"Sekvencijalno citanje 10 txt fajlova ocitano u ${System.currentTimeMillis() - start} ms")
  }
}
