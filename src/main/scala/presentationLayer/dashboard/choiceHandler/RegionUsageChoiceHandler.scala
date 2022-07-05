package dashboard.choiceHandler

import user.User

import scala.collection.mutable
import scala.collection.mutable.*

object RegionUsageChoiceHandler extends ChoiceHandler:
  override def choiceHandler(user: User): LinkedHashMap[Int, Double] =
    println("1) Regione di default")
    println("2) Regione a scelta")
    val scelta = scala.io.StdIn.readLine()
    scelta match
      case "1" =>
        println("Inserire il consumo d'interesse:")
        println("1) per visualizzare consumi luce")
        println("2) per visualizzare consumi gas")
        println("3) per visualizzare consumi acqua")

        val usage = scala.io.StdIn.readLine()

        usage match
          case "1" => user.getUsageByRegion(usageType = "electricity")
          case "2" => user.getUsageByRegion(usageType = "heat")
          case "3" => user.getUsageByRegion(usageType = "water")
          case _ =>
            println("L'input inserito non è valido.")
            mutable.LinkedHashMap[Int, Double]()


      case "2" =>
        println("Inserire la regione")
        val regionSelected = scala.io.StdIn.readLine()
        println("Inserire il consumo d'interesse:")
        println("1) per visualizzare consumi luce")
        println("2) per visualizzare consumi gas")
        println("3) per visualizzare consumi acqua")
        val usage = scala.io.StdIn.readLine()

        usage match
          case "1" => user.getUsageByRegion(regionSelected, usageType = "electricity")
          case "2" => user.getUsageByRegion(regionSelected, usageType = "heat")
          case "3" => user.getUsageByRegion(regionSelected, usageType = "water")
          case _ =>
            println("L'input inserito non è valido.")
            mutable.LinkedHashMap[Int, Double]()

      case _ =>
        println("L'input inserito non è valido.")
        mutable.LinkedHashMap[Int, Double]()