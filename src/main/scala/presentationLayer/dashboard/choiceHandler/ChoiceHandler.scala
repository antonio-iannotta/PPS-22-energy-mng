package presentationLayer.dashboard.choiceHandler

import dataLayer.user.User
import presentationLayer.dashboard.printHelper.PrintHelper

import scala.collection.mutable
import scala.collection.mutable.*

object ChoiceHandler:

  /**
   * The following function returns a LinkedHashMap[Int,Double] by calling the user.getUsageOrCostByRegionOrCity(...) function
   * @param user is the variable which contains the user
   * @param usageOrCost is the variable which indicates which one of the usage or cost we wanna view
   * @param locationType is the variable which indicates the location type ex (city or region)
   * @return
   */
  def cityRegionChoiceHandler(user: User, usageOrCost: String, locationType: String): LinkedHashMap[Int, Double] =

    var usage: String = ""
    val printHelper = new PrintHelper

    locationType match
      case "region" =>
        println("1) Regione relativa all'utente")
        println("2) Regione a scelta")
      case _ =>
        println("1) Città relativa all'utente")
        println("2) Città a scelta")

    val choice = scala.io.StdIn.readInt()
    choice match
      case 1 =>

        usage =  printHelper.usageMenuPrint()

        println("Inserire l'anno d'interesse:")
        val year = scala.io.StdIn.readInt()

        locationType match
          case "region" =>
            user.getUsageOrCostByRegionOrCity(user.region, usage, locationType, usageOrCost, year)
          case _ =>
            user.getUsageOrCostByRegionOrCity(user.city, usage, locationType, usageOrCost, year)

      case 2 =>

        locationType match
          case "region" => println("Inserire la regione d'interesse:")
          case _ => println("Inserire la città d'interesse:")

        val cityOrRegionSelected = scala.io.StdIn.readLine()
        usage = printHelper.usageMenuPrint()

        println("Inserire l'anno d'interesse:")
        val year = scala.io.StdIn.readInt()

        user.getUsageOrCostByRegionOrCity(cityOrRegionSelected, usage, locationType, usageOrCost, year)


  /**
   * The following function returns a String which contains the usage or the cost for a specific usage ex(heat,water,electricity)
   * @param user is the variable which contains the user
   * @param usageOrCost is the variable which indicates which one of the usage or cost we wanna view
   * @return
   */
  def individualChoiceHandler(user: User, usageOrCost: String): String =

    var usageString = ""
    usageOrCost match
      case "cost" => usageString = "costi"
      case "usage" => usageString = "utilizzi"

    println("1) per visualizzare " + usageString + " luce")
    println("2) per visualizzare "+ usageString + " gas")
    println("3) per visualizzare " + usageString + " acqua")
    val choice = scala.io.StdIn.readInt()

    choice match
      case 1 => user.getUsageOrCost(usageOrCost, "electricity")
      case 2 => user.getUsageOrCost(usageOrCost,"heat")
      case 3 => user.getUsageOrCost(usageOrCost, "water")