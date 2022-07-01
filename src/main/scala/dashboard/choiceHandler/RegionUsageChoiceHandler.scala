package dashboard.choiceHandler

import user.User

object RegionUsageChoiceHandler extends ChoiceHandler:
  override def choiceHandler(user: User): Unit =
    println("1) Regione di default")
    println("2) Regione a scelta")
    val scelta = scala.io.StdIn.readInt()
        scelta match
               case 1 => println("Inserire l'anno d'interesse")
                         var year = scala.io.StdIn.readInt()
                         user.getUsageByRegion(user.getRegion,user.getUserType)

               case 2 => println("Inserire la regione")
                         val regione = scala.io.StdIn.readLine()
                         println("Inserire l'anno d'interesse")
                         var year = scala.io.StdIn.readInt()
                         user.getUsageByRegion(regione,user.getUserType)