package billOperations

import scala.collection.mutable.LinkedHashMap
import bill.Bill

import scala.util.Random

object BillOperations:
  var list = List(
    Bill("234", "AntonioIannotta", "private", "water", 234.65, 89.6,
      2, 2022, "Cesena", "Emilia-Romagna"),

    Bill("235", "AndreaCatani", "private", "water", 856.65, 290.6,
      2, 2022, "Bologna", "Emilia-Romagna"),
    Bill("236", "CarloDiRaddo", "private", "electric", 100.93, 120.0,
      5, 2021, "Roma", "Lazio"),
    Bill("237", "DemetrioAndriani", "company", "heat", 324.56, 96.8,
      7, 2020, "Milano", "Lombardia"),
    Bill("238","AntonioIannotta","private","water",102,68.9,2,2021,"Cesena","Emilia-Romagna")
  )


  def getIndividualUsage(userID: String, usageType: String): String =
    var result = ""
    val userIdBills = list.filter(bill => bill.getUserID() == userID && bill.getUsageType() == usageType)
    for (bill <- userIdBills) do
      result = "usage: " + bill.getUsage() +
        "-month: " + bill.getMonth() +
        "-year: " + bill.getYear() + "\n"

    result


  def getIndividualCost(userID: String, usageType: String): String =
    var result = ""
    val userIdBills = list.filter(bill => bill.getUserID() == userID && bill.getUsageType() == usageType)
    for (bill <- userIdBills) do
      result = "usage: " + bill.getCost() + "\n" +
        "month: " + bill.getMonth() + "\n" +
        "year: " + bill.getYear()

    result

  def getUsageByLocation(userType: String, usageType: String, location: String, locationType: String): String =
    var result = ""
    val monthlyUsage: LinkedHashMap[Int, Double] = LinkedHashMap()
    locationType match
      case "city" =>
        val locationBills = list.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getCity() == location)
        for i <- Range(1, 12) do
          val monthlyUsageSum = locationBills.filter(bill => bill.getMonth() == i).foldLeft(0.0)(_ + _.getUsage())
          monthlyUsage(i) = monthlyUsageSum
      case "region" =>
        val locationBills = list.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getRegion() == location)
        for i <- Range(1, 12) do
          val monthlyUsageSum = locationBills.filter(bill => bill.getMonth() == i).foldLeft(0.0)(_ + _.getUsage())
          monthlyUsage(i) = monthlyUsageSum

    result = monthlyUsage.toString()
    result


  def getCostByLocation(userType: String, usageType: String, location: String, locationType: String): String =
    var result = ""
    val monthlyUsage: LinkedHashMap[Int, Double] = LinkedHashMap()
    locationType match
      case "city" =>
        val locationBills = list.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getCity() == location)
        for i <- Range(1, 12) do
          val monthlyUsageSum = locationBills.filter(bill => bill.getMonth() == i).foldLeft(0.0)(_ + _.getCost())
          monthlyUsage(i) = monthlyUsageSum
      case "region" =>
        val locationBills = list.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getRegion() == location)
        for i <- Range(1, 12) do
          val monthlyUsageSum = locationBills.filter(bill => bill.getMonth() == i).foldLeft(0.0)(_ + _.getCost())
          monthlyUsage(i) = monthlyUsageSum

    result = monthlyUsage.toString()
    result


  def makeIndividualPrediction(userID: String, usageType: String, year: Int): String =
    var result = ""
    val annualUsage: LinkedHashMap[Int, Double] = LinkedHashMap()
    val annualCost: LinkedHashMap[Int, Double] = LinkedHashMap()

    list.filter(bill => bill.getUserID() == userID).foreach(bill => annualUsage(bill.getYear()) = 0.0)
    list.filter(bill => bill.getUserID() == userID).foreach(bill => annualCost(bill.getYear()) = 0.0)

    annualUsage.keys.foreach(
      mapYear => annualUsage(mapYear) =
        list.filter(bill => bill.getUserID() == userID && bill.getYear() == mapYear && bill.getUsageType() == usageType).foldLeft(0.0)(_ + _.getUsage()) /
          list.count(bill => bill.getUserID() == userID && bill.getYear() == mapYear && bill.getUsageType() == usageType)
    )

    annualCost.keys.foreach(
      mapYear => annualCost(mapYear) =
        list.filter(bill => bill.getUserID() == userID && bill.getYear() == mapYear && bill.getUsageType() == usageType).foldLeft(0.0)(_ + _.getCost()) /
          list.count(bill => bill.getUserID() == userID && bill.getYear() == mapYear && bill.getUsageType() == usageType)
    )
    annualUsage.toSeq.sortBy(_._1)
    annualCost.toSeq.sortBy(_._1)

    var percentageUsageVariation = annualUsage.values.foldLeft[Double](0.0)(_ + _) / annualUsage.keys.size
    var percentageCostVariation = annualCost.values.foldLeft[Double](0.0)(_ + _) / annualCost.keys.size

    val lengthYearPrediction = year - annualUsage.keys.head
    if (lengthYearPrediction <= annualUsage.keys.size) then
      result = "Your usage for " + usageType + " is not supposed to change for " + year
    else
      println(annualUsage)
      for i <- Range(0,lengthYearPrediction - annualUsage.keys.size) do
        percentageCostVariation = percentageUsageVariation + Random.nextDouble()/100
        percentageUsageVariation = percentageUsageVariation + Random.nextDouble()/100
        result = s"Year: ${year}\nPredicted usage variation: ${percentageUsageVariation}\nPredicted cost variation: ${percentageCostVariation}"

    result




  def makePredictionByLocation(userID: String, userType: String, usageType: String, year: Int, locationType: String, location: String): String =
    "makePredictionByLocation"
