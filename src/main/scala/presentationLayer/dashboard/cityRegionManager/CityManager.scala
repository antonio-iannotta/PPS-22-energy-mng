package presentationLayer.dashboard.cityRegionManager

import dataLayer.user.User
import presentationLayer.dashboard.printHelper.PrintHelper

object CityManager extends CityRegionManager :
  override def manager(user: User, city: String, usage:String): Unit =
    println("Inserire l'anno d'interesse")
  try
    val year = scala.io.StdIn.readInt()
    user.makePredictionByCity(PrintHelper.usageMenuPrint(), year, city)
  catch
    case _: NumberFormatException => println("Input non valido.")