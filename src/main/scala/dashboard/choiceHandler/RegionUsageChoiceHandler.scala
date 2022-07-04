package dashboard.choiceHandler

import user.User

object RegionUsageChoiceHandler extends ChoiceHandler:
  override def choiceHandler(user: User): Unit =
    println("1) Regione di default")
    println("2) Regione a scelta")
    val scelta = scala.io.StdIn.readInt()
        scelta match
               case 1 => println("Inserire il consumo d'interesse")
                         var usage = scala.io.StdIn.readLine()
                         user.getUsageByRegion(usageType = usage)

               case 2 => println("Inserire la regione")
                         val regioneSelected = scala.io.StdIn.readLine()
                         println("Inserire il consumo d'interesse")
                         var usage = scala.io.StdIn.readLine()
                         user.getUsageByRegion(regioneSelected,usageType = usage)