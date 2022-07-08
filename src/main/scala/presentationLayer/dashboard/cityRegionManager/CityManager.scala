package presentationLayer.dashboard.cityRegionManager

import dataLayer.user.User
import presentationLayer.dashboard.printHelper.PrintHelper

object CityManager extends CityRegionManager :
  override def manager(user: User, city: String, usage:String): Unit =
    println("Inserire l'anno d'interesse")
    val printHelper = new PrintHelper
    try
      val year = scala.io.StdIn.readInt()
      user.makePredictionByCity(printHelper.usageMenuPrint(), year, city)
    catch
      case _: NumberFormatException => println("Input non valido.")