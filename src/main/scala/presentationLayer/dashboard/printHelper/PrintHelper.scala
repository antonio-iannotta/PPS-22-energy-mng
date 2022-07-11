package presentationLayer.dashboard.printHelper

class PrintHelper:

  def printMenu():Unit =
    println("1) Visualizza costi utente")
    println("2) Visualizza utilizzi utente")
    println("3) Visualizza costi città")
    println("4) Visualizza utilizzi città")
    println("5) Visualizza costi regione")
    println("6) Visualizza utilizzi regione")
    println("7) Previsione individuale")
    println("8) Previsione città")
    println("9) Previsione regione")
    println("10) Confronto tra regione")
    println("11) Confronto tra città" )
    println("12) Logout e torna al menu iniziale")

  def printUsage() : Unit =
    println("Inserire il consumo d'interesse")
    println("1) per visualizzare consumi luce")
    println("2) per visualizzare consumi gas")
    println("3) per visualizzare consumi acqua")

  def usageMenuPrint(): String =
    printUsage()
    val usageChoice = scala.io.StdIn.readInt()

    usageChoice match
      case 1 => "electricity"
      case 2 => "heat"
      case 3 => "water"
      case _ => "Input non valido"

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

  def comparisonCity(): (String, String, Int) =
    println("Inserire città 1")
    val city1 = scala.io.StdIn.readLine()
    println("Inserire città 2")
    val city2 = scala.io.StdIn.readLine()
    println("Inserire l'anno di riferimento")
    val year = scala.io.StdIn.readInt()
    (city1, city2 ,year)

  def comparisonRegion(): (String, String, Int) =
    println("Inserire regione 1")
    val region1 = scala.io.StdIn.readLine()
    println("Inserire regione 2")
    val region2 = scala.io.StdIn.readLine()
    println("Inserire l'anno di riferimento")
    val year = scala.io.StdIn.readInt()
    (region1,region2,year)


  def usageFormatter(usageType: String, usage: Double): String =

    usageType match

      case "water" =>
        usage + " Lmc"

      case "heat" =>
        usage + " Smc"

      case "electricity" =>
        usage + " Kw/h"