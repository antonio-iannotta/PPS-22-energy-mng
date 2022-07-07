package presentationLayer.dashboard.cityRegionManager

import dataLayer.user.User

object RegionManager extends CityRegionManager:
  override def manager(user: User, region: String, usage:String): Unit =
    println("Inserire l'anno d'interesse")
    try
      val year = scala.io.StdIn.readInt()
      user.makePredictionByRegion(usageMenuPrint(), year, region)
    catch
      case _: NumberFormatException => println("Input non valido.")
