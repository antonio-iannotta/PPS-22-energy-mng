package presentationLayer.dashboard.choiceHandler

import dataLayer.user.User
import scala.collection.mutable
import scala.collection.mutable.*

object ChoiceHandler:
  override def choiceHandler(user: User, printOpt: Int): LinkedHashMap[Int, Double] =
    var choice: Int = 0
    var citySelected: String = ""
    var regionSelected: String = ""

    printOpt match
      case 1 => classicPrint()
        choice = scala.io.StdIn.readInt()

        choice match
          case 1 => user.getCost("electricity")
          case 2 => user.getCost("heat")
          case 3 => user.getCost("water")

      case 2 => classicPrint()
        choice = scala.io.StdIn.readInt()

        choice match
          case 1 => user.getUsage("electricity")
          case 2 => user.getUsage("heat")
          case 3 => user.getUsage("water")

      case 3 | 4 =>
        println("1) Città di default")
        println("2) Città a scelta")
        val choice_str = scala.io.StdIn.readLine()
        choice_str match
          case "1" => choice = 3
          case "2" => choice = 4

      case 5 | 6 =>
        println("1) Regione di default")
        println("2) Regione a scelta")
        val choice_str = scala.io.StdIn.readLine()
        choice_str match
          case "1" => choice = 5
          case "2" => choice = 6

      case _ =>
        println("L'input inserito non è valido.")
        mutable.LinkedHashMap[Int, Double]()

    choice match
      case 3 | 5 =>
        classicPrint()
        val usage = scala.io.StdIn.readLine()

        usage match
          case "1" => choice match
            case 3 => user.getCostByCity(usageType = "electricity")
            case 5 => user.getCostByRegion(usageType = "electricity")
          case "2" => choice match
            case 3 => user.getCostByCity(usageType = "heat")
            case 5 => user.getCostByRegion(usageType = "heat")
          case "3" => choice match
            case 3 => user.getCostByCity(usageType = "water")
            case 5 => user.getCostByRegion(usageType = "water")
          case _ =>
            println("L'input inserito non è valido.")
            mutable.LinkedHashMap[Int, Double]()

      case 4 | 6 => choice match
        case 4 => println("Inserire la città")
          citySelected = scala.io.StdIn.readLine()
        case 6 => println("Inserire la regione")
          regionSelected = scala.io.StdIn.readLine()

          classicPrint()
          println("Selezionare anno")
          val year = scala.io.StdIn.readInt()
          val usage = scala.io.StdIn.readLine()
          usage match
            case "1" => choice match
              case 4 => user.getUsageByCity(citySelected, usageType = "electricity",year)
              case 6 => user.getUsageByRegion(regionSelected, usageType = "electricity",year)
            case "2" => choice match
              case 4 => user.getUsageByCity(citySelected, usageType = "heat",year)
              case 6 => user.getUsageByRegion(regionSelected, usageType = "heat",year)
            case "3" => choice match
              case 4 => user.getUsageByCity(citySelected, usageType = "water",year)
              case 6 => user.getUsageByRegion(regionSelected, usageType = "water",year)
            case _ =>
              println("L'input inserito non è valido.")
              mutable.LinkedHashMap[Int, Double]()


    def classicPrint(): Unit =
      println("Inserire il consumo d'interesse:")
      println("1) per visualizzare consumi luce")
      println("2) per visualizzare consumi gas")
      println("3) per visualizzare consumi acqua")