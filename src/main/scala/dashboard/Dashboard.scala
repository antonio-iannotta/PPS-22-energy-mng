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
                case 3 =>
                  val linkedHashMap = CityCostChoiceHandler.choiceHandler(user)
                  //si lavora linked... e si torna una print decente
                case 4 => CityUsageChoiceHandler.choiceHandler(user)
                case 5 => RegionCostChoiceHandler.choiceHandler(user)
                case 6 => RegionUsageChoiceHandler.choiceHandler(user)

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