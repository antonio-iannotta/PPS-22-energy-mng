
package presentationLayer.dashboard.cityRegionManager

import dataLayer.user.User
import presentationLayer.dashboard.printHelper.PrintHelper

object RegionManager extends CityRegionManager:
  override def manager(user: User, region: String, usage:String): Unit =
    println("Inserire l'anno d'interesse")
    val printHelper = new PrintHelper
    try
      val year = scala.io.StdIn.readInt()
      user.makePredictionByRegion(printHelper.usageMenuPrint(), year, region)
    catch
      case _: NumberFormatException => println("Input non valido.")