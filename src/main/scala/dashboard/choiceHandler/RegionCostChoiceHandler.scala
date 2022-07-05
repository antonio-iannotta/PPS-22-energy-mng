package dashboard.choiceHandler
import user.User

import scala.collection.mutable._

object RegionCostChoiceHandler extends ChoiceHandler:
  override def choiceHandler(user: User): LinkedHashMap[Int, Double] =
    println("1) Regione di default")
    println("2) Regione a scelta")
    val scelta = scala.io.StdIn.readInt()
    scelta match
      case 1 => println("Inserire il consumo d'interesse")
        var usage = scala.io.StdIn.readLine()
        user.getCostByRegion(usageType = usage)

      case 2 => println("Inserire la regione")
        var regioneSelected = scala.io.StdIn.readLine()
        println("Inserire il consumo d'interesse")
        var usage = scala.io.StdIn.readLine()
        user.getCostByRegion(region = regioneSelected, usageType = usage)