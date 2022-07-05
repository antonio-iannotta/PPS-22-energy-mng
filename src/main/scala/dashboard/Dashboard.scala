package dashboard

import dashboard.choiceHandler.{CityCostChoiceHandler, CityUsageChoiceHandler, RegionCostChoiceHandler, RegionUsageChoiceHandler, UserCostChoiceHandler, UserUsageChoiceHandler}
import user.User

case class Dashboard(private val user: User):

  def view(): Unit =

    var exit = true

    while(exit)
      println("Selezionare voce desiderata:")
      println("1) visualizza costi utente")
      println("2) visualizza utilizzi utente")
      println("3) visualizza costi città")
      println("4) visualizza utilizzi città")
      println("5) visualizza costi regione")
      println("6) visualizza utilizzi regione")
      println("7) previsione individuale")
      println("8) previsione città")
      println("9) previsione regione")
      println("10) exit")

      var selection = scala.io.StdIn.readInt()

      selection match
        case 1 => println(UserCostChoiceHandler.choiceHandler(user))
        case 2 => println(UserUsageChoiceHandler.choiceHandler(user))
        case 3 => CityCostChoiceHandler.choiceHandler(user).foreach(monthAndCost => println(formatter(monthAndCost._1) + monthAndCost._2.toString))
        case 4 => CityUsageChoiceHandler.choiceHandler(user).foreach(monthAndUsage => println(formatter(monthAndUsage._1) + monthAndUsage._2.toString))
        case 5 => RegionCostChoiceHandler.choiceHandler(user).foreach(monthAndCost => println(formatter(monthAndCost._1) + monthAndCost._2.toString))
        case 6 => RegionUsageChoiceHandler.choiceHandler(user).foreach(monthAndUsage => println(formatter(monthAndUsage._1) + monthAndUsage._2.toString))

        case 7 => println("Inserire l'anno d'interesse")
          var year = scala.io.StdIn.readInt()
          user.makeIndividualPrediction(user.userType,year)

        case 8 => println("Inserire l'anno d'interesse")
          var year = scala.io.StdIn.readInt()
          user.makePredictionByCity(user.userType,year)

        case 9 => println("Inserire l'anno d'interesse")
          var year = scala.io.StdIn.readInt()
          user.makePredictionByRegion(user.userType,year)

        case 10 => exit = false

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