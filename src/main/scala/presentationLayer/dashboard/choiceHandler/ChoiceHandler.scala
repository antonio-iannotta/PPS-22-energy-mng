package presentationLayer.dashboard.choiceHandler

import dataLayer.user.User
import scala.collection.mutable
import scala.collection.mutable.*

object ChoiceHandler:
  def choiceHandler(user: User, usageOrCost: String, cityOrRegion: String): LinkedHashMap[Int, Double] =

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
        println("Inserire il consumo d'interesse:")
        println("1) per visualizzare consumi luce")
        println("2) per visualizzare consumi gas")
        println("3) per visualizzare consumi acqua")
        val usageChoice = scala.io.StdIn.readInt()

        usageChoice match
          case 1 => usage = "electricity"
          case 2 => usage ="heat"
          case 3 => usage ="water"

        println("Inserire l'anno d'interesse:")
        val year = scala.io.StdIn.readInt()

        user.getUsageOrcostByRegionOrcity(user.region, usage, cityOrRegion, usageOrCost, year)

      case 2 =>
        println("Inserire la regione d'interesse:")
        val selectedRegion = scala.io.StdIn.readLine()

        println("Inserire il consumo d'interesse")
        println("1) per visualizzare consumi luce")
        println("2) per visualizzare consumi gas")
        println("3) per visualizzare consumi acqua")
        val usageChoice = scala.io.StdIn.readInt()

        usageChoice match
          case 1 => usage = "electricity"
          case 2 => usage = "heat"
          case 3 => usage = "water"

        println("Inserire l'anno d'interesse:")
        val year = scala.io.StdIn.readInt()

        user.getUsageOrcostByRegionOrcity(selectedRegion, usage, cityOrRegion, usageOrCost, year)
