
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

  override def comparison(printHelper: PrintHelper, user: User): Unit =
    var costOrUsage = ""
    val usage = printHelper.usageMenuPrint()
    println("1) Per visualizzare il costo")
    println("2) Per visualizzare l'utilizzo")
    val choice = scala.io.StdIn.readInt()
    choice match
      case 1 => costOrUsage = "cost"
      case 2 => costOrUsage = "usage"
    println("Inserire regione 1")
    val region1 = scala.io.StdIn.readLine()
    println("Inserire regione 2")
    val region2 = scala.io.StdIn.readLine()
    println("Inserire l'anno di riferimento")
    val year = scala.io.StdIn.readInt()
    costOrUsage match
      case "cost" =>
        print("------------------- " + region1.capitalize + " ------------------------------\n")
        user.getUsageOrCostByRegionOrCity(region1,usage,"region",costOrUsage,year).foreach(monthAndCost => println(printHelper.formatter(monthAndCost._1) + monthAndCost._2.toString + "Є"))
        print("------------------- " + region2.capitalize + " ------------------------------\n")
        user.getUsageOrCostByRegionOrCity(region2,usage,"region",costOrUsage,year).foreach(monthAndCost => println(printHelper.formatter(monthAndCost._1) + monthAndCost._2.toString + "Є"))
      case "usage" =>
        print("------------------- " + region1.capitalize + " ------------------------------\n")
        user.getUsageOrCostByRegionOrCity(region1,usage,"region",costOrUsage,year).foreach(monthAndUsage => println(printHelper.formatter(monthAndUsage._1) + usageFormatter(usage, monthAndUsage._2)))
        print("------------------- " + region2.capitalize + " ------------------------------\n")
        user.getUsageOrCostByRegionOrCity(region2,usage,"region",costOrUsage,year).foreach(monthAndUsage => println(printHelper.formatter(monthAndUsage._1) + usageFormatter(usage, monthAndUsage._2)))
      case _ => println("ERROR!")


  def usageFormatter(usageType: String, usage: Double): String =

    usageType match

      case "water" =>
        usage + " Lmc"

      case "heat" =>
        usage + " Smc"

      case "electricity" =>
        usage + " Kw/h"