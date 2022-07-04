package dashboard.choiceHandler
import user.User

object CityCostChoiceHandler extends ChoiceHandler:
  override def choiceHandler(user: User): Unit =
    println("1) Città di default")
    println("2) Città a scelta")
    val scelta = scala.io.StdIn.readInt()
        scelta match
               case 1 => println("Inserire l'anno d'interesse")
                         var year = scala.io.StdIn.readInt()
                         user.getCostByCity(user.getUserType)

               case 2 => println("Inserire città")
                         val citySelected = scala.io.StdIn.readLine()
                         println("Inserire l'anno d'interesse")
                         var year = scala.io.StdIn.readInt()
                         user.getCostByCity(city = citySelected,user.getUserType)