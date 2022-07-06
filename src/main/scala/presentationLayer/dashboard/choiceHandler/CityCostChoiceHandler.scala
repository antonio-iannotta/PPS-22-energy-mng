package presentationLayer.dashboard.choiceHandler

import dataLayer.user.User
import java.util
import scala.collection.mutable._

object CityCostChoiceHandler extends ChoiceHandler:
  override def choiceHandler(user: User): LinkedHashMap[Int, Double]=
    println("1) Città di default")
    println("2) Città a scelta")
    val scelta = scala.io.StdIn.readInt()
        scelta match
               case 1 => println("Inserire il consumo d'interesse")
                         var usage = scala.io.StdIn.readLine()
                         user.getCostByCity(usageType = usage)

               case 2 => println("Inserire città")
                         var citySelected = scala.io.StdIn.readLine()
                         println("Inserire il consumo d'interesse")
                         var usage = scala.io.StdIn.readLine()
                         user.getCostByCity(city = citySelected,usageType = usage)