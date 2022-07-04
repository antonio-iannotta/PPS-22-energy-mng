package dashboard

import dashboard.choiceHandler.{CityChoiceHandler, CityCostChoiceHandler, CityUsageChoiceHandler, RegionCostChoiceHandler, RegionUsageChoiceHandler}
import user.User

case class Dashboard(private val user: User):
  
  def view(choise: String): Unit =

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
              case 1 => user.getCost(user.getUserType)
              case 2 => user.getUsage(user.getUserType)
              case 3 => CityCostChoiceHandler.choiceHandler(user)
              case 4 => CityUsageChoiceHandler.choiceHandler(user)
              case 5 => RegionCostChoiceHandler.choiceHandler(user)
              case 6 => RegionUsageChoiceHandler.choiceHandler(user)

              case 7 => println("Inserire l'anno d'interesse")
                        var year = scala.io.StdIn.readInt()
                        user.makeIndividualPrediction(user.getUserType,year)

              case 8 => println("Inserire l'anno d'interesse")
                        var year = scala.io.StdIn.readInt()
                        user.makePredictionByCity(user.getUserType,year)

              case 9 => println("Inserire l'anno d'interesse")
                        var year = scala.io.StdIn.readInt()
                        user.makePredictionByRegion(user.getUserType,year)

              case 10 => exit = false

              case _ => println("Scelta non valida!")