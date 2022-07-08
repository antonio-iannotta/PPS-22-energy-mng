package presentationLayer.dashboard.printHelper

class PrintHelper:

  def printMenu() =
    println("1) Visualizza costi utente")
    println("2) Visualizza utilizzi utente")
    println("3) Visualizza costi città")
    println("4) Visualizza utilizzi città")
    println("5) Visualizza costi regione")
    println("6) Visualizza utilizzi regione")
    println("7) Previsione individuale")
    println("8) Previsione città")
    println("9) Previsione regione")
    println("10) Logout e torna al menu iniziale")

  def usageMenuPrint(): String =
    println("Inserire il consumo d'interesse")
    println("1) per visualizzare consumi luce")
    println("2) per visualizzare consumi gas")
    println("3) per visualizzare consumi acqua")
    val usageChoice = scala.io.StdIn.readInt()

    usageChoice match
      case 1 => "electricity"
      case 2 => "heat"
      case 3 => "water"

  def formatter(month: Int): String =
    month match
      case 1 => "Gennaio: "
      case 2 => "Febbraio: "
      case 3 => "Marzo: "
      case 4 => "Aprile: "
      case 5 => "Maggio: "
      case 6 => "Giugno: "
      case 7 => "Luglio: "
      case 8 => "Agosto: "
      case 9 => "Settembre: "
      case 10 => "Ottobre: "
      case 11 => "Novembre: "
      case 12 => "Dicembre: "