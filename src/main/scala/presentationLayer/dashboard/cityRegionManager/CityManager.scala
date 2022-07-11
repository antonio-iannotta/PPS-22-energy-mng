package presentationLayer.dashboard.cityRegionManager

import dataLayer.user.User
import presentationLayer.dashboard.printHelper.PrintHelper

object CityManager extends CityRegionManager :

  val printHelper = new PrintHelper

  override def manager(user: User, city: String, usage:String): Unit =
    println("Inserire l'anno d'interesse")
    try
      val year = scala.io.StdIn.readInt()
      user.makePredictionByCity(printHelper.usageMenuPrint(), year, city)
    catch
      case _: NumberFormatException => println("Input non valido.")

  override def comparison(printHelper: PrintHelper,user: User): Unit =
    var costOrUsage = ""
    val usage = printHelper.usageMenuPrint()
    println("1) Per visualizzare il costo")
    println("2) Per visualizzare l'utilizzo")
    val choice = scala.io.StdIn.readInt()
    choice match
      case 1 => costOrUsage = "cost"
      case 2 => costOrUsage = "usage"
      val (city1,city2,year) = printHelper.comparisonCity()
        costOrUsage match
          case "cost" =>
            print("------------------- " + city1.capitalize + " ------------------------------\n")
            user.getUsageOrCostByRegionOrCity(city1,usage,"city",costOrUsage,year).foreach(monthAndCost => println(printHelper.formatter(monthAndCost._1) + monthAndCost._2.toString + "Є"))
            print("------------------- " + city2.capitalize + " ------------------------------\n")
            user.getUsageOrCostByRegionOrCity(city2,usage,"city",costOrUsage,year).foreach(monthAndCost => println(printHelper.formatter(monthAndCost._1) + monthAndCost._2.toString + "Є"))
          case "usage" =>
            print("------------------- " + city1.capitalize + " ------------------------------\n")
            user.getUsageOrCostByRegionOrCity(city1,usage,"city",costOrUsage,year).foreach(monthAndUsage => println(printHelper.formatter(monthAndUsage._1) + printHelper.usageFormatter(usage, monthAndUsage._2)))
            print("------------------- " + city2.capitalize + " ------------------------------\n")
            user.getUsageOrCostByRegionOrCity(city2,usage,"city",costOrUsage,year).foreach(monthAndUsage => println(printHelper.formatter(monthAndUsage._1) + printHelper.usageFormatter(usage, monthAndUsage._2)))
          case _ => println("ERROR!")
