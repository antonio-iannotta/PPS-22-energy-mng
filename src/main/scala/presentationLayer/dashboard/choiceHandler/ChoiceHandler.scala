package presentationLayer.dashboard.choiceHandler

import dataLayer.user.User
import scala.collection.mutable
import scala.collection.mutable.*

object ChoiceHandler:
  override def choiceHandler(user: User, stampa: Int): LinkedHashMap[Int, Double] =

    stampa match
      case 1 => stampaClassica
        val scelta = scala.io.StdIn.readInt()

        scelta match
          case 1 => user.getCost("electricity")
          case 2 => user.getCost("heat")
          case 3 => user.getCost("water")

      case 2 => stampaClassica
        val scelta = scala.io.StdIn.readInt()

        scelta match
          case 1 => user.getUsage("electricity")
          case 2 => user.getUsage("heat")
          case 3 => user.getUsage("water")

      case 3 || 4 => println("1) Città di default")
        println("2) Città a scelta")
        val scelta = scala.io.StdIn.readLine()
        scelta match
          case "1" => scelta = 3
          case "2" => scelta = 4

      case 5 || 6 => println("1) Regione di default")
        println("2) Regione a scelta")
        val scelta = scala.io.StdIn.readLine()
        scelta match
          case "1" => scelta = 5
          case "2" => scelta = 6

    scelta match
      case 3 || 5 =>
        stampaClassica
        val usage = scala.io.StdIn.readLine()

        usage match
          case "1" => scelta match
            case 3 => user.getCostByCity(usageType = "electricity")
            case 5 => user.getCostByRegion(usageType = "electricity")
          case "2" => scelta match
            case 3 => user.getCostByCity(usageType = "heat")
            case 5 => user.getCostByRegion(usageType = "heat")
          case "3" => scelta match
            case 3 => user.getCostByCity(usageType = "water")
            case 5 => user.getCostByRegion(usageType = "water")
          case _ =>
            println("L'input inserito non è valido.")
            mutable.LinkedHashMap[Int, Double]()

      case 4 || 6 => scelta match
        case 4 => println("Inserire la città")
          val citySelected = scala.io.StdIn.readLine()
        case 6 => println("Inserire la regione")
          val regionSelected = scala.io.StdIn.readLine()

          stampaClassica
          val usage = scala.io.StdIn.readLine()
          usage match
            case "1" => scelta match
              case 4 => user.getCostByCity(citySelected, usageType = "electricity")
              case 6 => user.getCostByRegion(regionSelected, usageType = "electricity")
            case "2" => scelta match
              case 4 => user.getCostByCity(citySelected, usageType = "heat")
              case 6 => user.getCostByRegion(regionSelected, usageType = "heat")
            case "3" => scelta match
              case 4 => user.getCostByCity(citySelected, usageType = "water")
              case 6 => user.getCostByRegion(regionSelected, usageType = "water")
            case _ =>
              println("L'input inserito non è valido.")
              mutable.LinkedHashMap[Int, Double]()

    case _ =>
    println("L'input inserito non è valido.")
    mutable.LinkedHashMap[Int, Double]()

    def stampaClassica =
      println("Inserire il consumo d'interesse:")
      println("1) per visualizzare consumi luce")
      println("2) per visualizzare consumi gas")
      println("3) per visualizzare consumi acqua")

