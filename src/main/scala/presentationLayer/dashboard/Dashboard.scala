package presentationLayer.dashboard

import dashboard.choiceHandler.{CityCostChoiceHandler, CityUsageChoiceHandler, RegionCostChoiceHandler, RegionUsageChoiceHandler, UserCostChoiceHandler, UserUsageChoiceHandler}
import user.User

case class Dashboard(private val user: User):

  def view(): Unit =

    var exit = true

    while(exit)
      println("\nBenvenuto " + user.userID + "! Seleziona l'operazione desiderata:")
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

      val selection = scala.io.StdIn.readLine()

      selection match
        case "1" => println(UserCostChoiceHandler.choiceHandler(user))
        case "2" => println(UserUsageChoiceHandler.choiceHandler(user))
        case "3" => CityCostChoiceHandler.choiceHandler(user).foreach(monthAndCost => println(formatter(monthAndCost._1) + monthAndCost._2.toString))
        case "4" => CityUsageChoiceHandler.choiceHandler(user).foreach(monthAndUsage => println(formatter(monthAndUsage._1) + monthAndUsage._2.toString))
        case "5" => RegionCostChoiceHandler.choiceHandler(user).foreach(monthAndCost => println(formatter(monthAndCost._1) + monthAndCost._2.toString))
        case "6" => RegionUsageChoiceHandler.choiceHandler(user).foreach(monthAndUsage => println(formatter(monthAndUsage._1) + monthAndUsage._2.toString))
        case "7" => println("Inserire l'anno d'interesse:")
          try
            val year = scala.io.StdIn.readInt()
            user.makeIndividualPrediction(user.userType,year)
          catch
            case _ : NumberFormatException => println("Input non valido.")
        case "8" => println("Inserire l'anno d'interesse")
          try
            val year = scala.io.StdIn.readInt()
            user.makePredictionByCity(user.userType,year)
          catch
            case _ : NumberFormatException => println("Input non valido.")
        case "9" => println("Inserire l'anno d'interesse")
          try
            val year = scala.io.StdIn.readInt()
            user.makePredictionByRegion(user.userType,year)
          catch
            case _ : NumberFormatException => println("Input non valido.")
        case "10" => exit = false
        case _ => println("Scelta non valida!")

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