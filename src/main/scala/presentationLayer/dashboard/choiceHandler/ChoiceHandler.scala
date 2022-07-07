package presentationLayer.dashboard.choiceHandler

import dataLayer.user.User
import scala.collection.mutable
import scala.collection.mutable.*

object ChoiceHandler:
  def cityRegionChoiceHandler(user: User, usageOrCost: String, cityOrRegion: String): LinkedHashMap[Int, Double] =

    var usage: String = ""

    cityOrRegion match
      case "region" =>
        println("1) Regione relativa all'utente")
        println("2) Regione a scelta")
      case _ =>
        println("1) Città relativa all'utente")
        println("2) Città a scelta")

    val choice = scala.io.StdIn.readInt()
    choice match
      case 1 =>
        printUsage()
        val usageChoice = scala.io.StdIn.readInt()
        usage = usageChoiceMatch(usageChoice)

        println("Inserire l'anno d'interesse:")
        val year = scala.io.StdIn.readInt()

        cityOrRegion match
          case "region" =>
            user.getUsageOrcostByRegionOrcity(user.region, usage, cityOrRegion, usageOrCost, year)
          case _ =>
            user.getUsageOrcostByRegionOrcity(user.city, usage, cityOrRegion, usageOrCost, year)

      case 2 =>

        cityOrRegion match
          case "region" => println("Inserire la regione d'interesse:")
          case _ => println("Inserire la città d'interesse:")

        val cityOrRegionSelected = scala.io.StdIn.readLine()

        printUsage()
        val usageChoice = scala.io.StdIn.readInt()
        usage = usageChoiceMatch(usageChoice)

        println("Inserire l'anno d'interesse:")
        val year = scala.io.StdIn.readInt()

        user.getUsageOrcostByRegionOrcity(cityOrRegionSelected, usage, cityOrRegion, usageOrCost, year)

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

  def printUsage() =
    println("Inserire il consumo d'interesse")
    println("1) per visualizzare consumi luce")
    println("2) per visualizzare consumi gas")
    println("3) per visualizzare consumi acqua")

  def usageChoiceMatch(usage: Int) : String =
    usage match
      case 1 => "electricity"
      case 2 => "heat"
      case 3 => "water"
      case _ => "Input non valido"
