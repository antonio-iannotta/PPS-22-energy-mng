package dashboard.choiceHandler

import user.User

object CityUsageChoiceHandler extends ChoiceHandler:
  override def choiceHandler(user: User): Unit =
    println("1) Città di default")
    println("2) Città a scelta")
    val scelta = scala.io.StdIn.readInt()
        scelta match
               case 1 => println("Inserire l'anno d'interesse")
                         var year = scala.io.StdIn.readInt()
                         user.getUsageByCity(user.userType)

                case 2 => println("Inserire città")
                          val citySelected = scala.io.StdIn.readLine()
                          println("Inserire l'anno d'interesse")
                          var year = scala.io.StdIn.readInt()
                          user.getUsageByCity(city = citySelected,user.userType)