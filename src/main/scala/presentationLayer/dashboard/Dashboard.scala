package presentationLayer.dashboard

import presentationLayer.dashboard.cityRegionManager
import presentationLayer.dashboard.choiceHandler.ChoiceHandler
import presentationLayer.dashboard.printHelper.PrintHelper
import dataLayer.user.User


case class Dashboard(private val user: User):

  def view(): Unit =

    var exit = true

    while (exit)
      println("\nBenvenuto " + user.userID + "! Seleziona l'operazione desiderata:")
      PrintHelper.printMenu()
      val selection = scala.io.StdIn.readLine()

      selection match
        case "1" => println(ChoiceHandler.individualChoiceHandler(user, "cost"))
        case "2" => println(ChoiceHandler.individualChoiceHandler(user, "usage"))
        case "3" => ChoiceHandler.cityRegionChoiceHandler(user, "cost", "city").foreach(monthAndCost => println(PrintHelper.formatter(monthAndCost._1) + monthAndCost._2.toString))
        case "4" => ChoiceHandler.cityRegionChoiceHandler(user, "usage", "city").foreach(monthAndUsage => println(PrintHelper.formatter(monthAndUsage._1) + monthAndUsage._2.toString))
        case "5" => ChoiceHandler.cityRegionChoiceHandler(user, "cost", "region").foreach(monthAndCost => println(PrintHelper.formatter(monthAndCost._1) + monthAndCost._2.toString))
        case "6" => ChoiceHandler.cityRegionChoiceHandler(user, "usage", "region").foreach(monthAndUsage => println(PrintHelper.formatter(monthAndUsage._1) + monthAndUsage._2.toString))

        case "7" =>
          println("Inserire l'anno d'interesse:")
          try
            val year = scala.io.StdIn.readInt()
            user.makeIndividualPrediction(PrintHelper.usageMenuPrint(), year)
          catch
            case _: NumberFormatException => println("Input non valido.")

        case "8" =>
          println("1) Previsione città corrente utente")
          println("2) Previsione città a scelta")
          val choice = scala.io.StdIn.readInt()
          choice match
            case 1 =>
              cityManager(user,user.city,PrintHelper.usageMenuPrint())
            case 2 =>
              println("Inserire la città di cui si desidera visualizzare la previsione")
              val city = scala.io.StdIn.readLine()
              cityManager(user,city,PrintHelper.usageMenuPrint())

        case "9" =>
          println("1) Previsione regione corrente utente")
          println("2) Previsione regionea scelta")
          val choice = scala.io.StdIn.readInt()
          choice match
            case 1 =>
              regionManager(user,user.region,PrintHelper.usageMenuPrint())
            case 2 =>
              println("Inserire la regione di cui si desidera visualizzare la previsione")
              val region = scala.io.StdIn.readLine()
              regionManager(user,region,PrintHelper.usageMenuPrint())

        case "10" => exit = false
        case _ => println("Scelta non valida!")

  /*
  private def formatter(month: Int): String =
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
*/
  /*
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
*/
  /*
  private def cityManager(city: String): Unit =
    println("Inserire l'anno d'interesse")
    try
      val year = scala.io.StdIn.readInt()
      user.makePredictionByCity(usageMenuPrint(), year, city)
    catch
      case _: NumberFormatException => println("Input non valido.")

  private def regionManager(region: String): Unit =
    println("Inserire l'anno d'interesse")
    try
      val year = scala.io.StdIn.readInt()
      user.makePredictionByRegion(usageMenuPrint(), year, region)
    catch
      case _: NumberFormatException => println("Input non valido.")
*/

  /*
  private def printMenu() =
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
*/