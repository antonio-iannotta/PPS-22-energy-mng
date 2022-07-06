package applicationLogicLayer.billOperations


import dataLayer.bill.Bill

import scala.collection.mutable.{LinkedHashMap, ListBuffer}
import scala.util.Random

object Utils:

  /*
  Il seguente metodo privato ritorna il risultato della previsione richiesta
  */
  def predictionResult(year: Int, yearMap: LinkedHashMap[Int, Double], usageVariation: Double, costVariation: Double, usageType: String): String =
    year - yearMap.keys.head match
      case duration if duration <= yearMap.keys.size =>
        "Your usage and cost for " + usageType + " is not supposed to change for " + year
      case duration if duration > yearMap.keys.size =>
        s"Year: ${year}\nPredicted usage variation: ${usageVariation + Random.between(-1.0, 1.0) * duration}\nPredicted cost variation: ${costVariation + Random.between(-1.0, 1.0) * duration}"


  def variation(map: LinkedHashMap[Int, Double]): Double =
    map.values.foldLeft(0.0)(_ + _) / map.keys.size


  def individualMapInitialization(map: LinkedHashMap[Int, Double], userID: String, usageType: String, billList: ListBuffer[Bill]): Unit =
    billList.filter(bill => bill.userID == userID && bill.usageType == usageType).foreach(bill => map(bill.year) = 0.0)

  def initializationMapByLocation(map: LinkedHashMap[Int, Double], userType: String, usageType: String, cityOrRegion: String, cityRegion: String, billList: ListBuffer[Bill]): Unit =
    cityOrRegion match
      case "city" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.city == cityRegion)
          .foreach(bill => map(bill.year) = 0.0)
      case "region" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.region == cityRegion)
          .foreach(bill => map(bill.year) = 0.0)

  def fillIndividualUsageCostMap(map: LinkedHashMap[Int, Double], usageOrCost: String, userID: String, usageType: String, billList: ListBuffer[Bill]): Unit =
    usageOrCost match
      case "usage" =>
        map.keys.foreach(
          mapYear => map(mapYear) =
            billList.filter(bill => bill.userID == userID && bill.year == mapYear && bill.usageType == usageType).foldLeft(0.0)(_ + _.usage) /
              billList.count(bill => bill.userID == userID && bill.year == mapYear && bill.usageType == usageType)
        )
      case "cost" =>
        map.keys.foreach(
          mapYear => map(mapYear) =
            billList.filter(bill => bill.userID == userID && bill.year == mapYear && bill.usageType == usageType).foldLeft(0.0)(_ + _.cost) /
              billList.count(bill => bill.userID == userID && bill.year == mapYear && bill.usageType == usageType)
        )

  def fillUsageCostMapByLocation(map: LinkedHashMap[Int, Double], usageType: String, usageOrCost: String, userType: String, cityOrRegion: String, cityRegion: String, billList: ListBuffer[Bill]): Unit =
    cityOrRegion match
      case "city" =>
        usageOrCost match
          case "usage" =>
            map.keys.foreach(
              mapYear => map(mapYear) =
                billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.city == cityRegion).foldLeft(0.0)(_ + _.usage) /
                  billList.count(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.city == cityRegion)
            )
          case "cost" =>
            map.keys.foreach(
              mapYear => map(mapYear) =
                billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.city == cityRegion).foldLeft(0.0)(_ + _.cost) /
                  billList.count(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.city == cityRegion)
            )
      case "region" =>
        usageOrCost match
          case "usage" =>
            map.keys.foreach(
              mapYear => map(mapYear) =
                billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.region == cityRegion).foldLeft(0.0)(_ + _.usage) /
                  billList.count(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.region == cityRegion)
            )
          case "cost" =>
            map.keys.foreach(
              mapYear => map(mapYear) =
                billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.region == cityRegion).foldLeft(0.0)(_ + _.cost) /
                  billList.count(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.region == cityRegion)
            )


  def getBillsByUserIDAndUsageType(userID: String, usageType: String, billList: ListBuffer[Bill]): ListBuffer[Bill] =
    billList.filter(bill => bill.userID == userID && bill.usageType == usageType)


  def getBillsByCityOrRegion(userType: String, usageType: String, cityOrRegion: String, cityRegion: String, billList: ListBuffer[Bill]): ListBuffer[Bill] =
    cityOrRegion match
      case "city" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.city == cityRegion)
      case "region" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.region == cityRegion)

  def monthlyUsageOrCost(userType: String, usageType: String, cityOrRegion: String, cityRegion: String, usageOrCost: String, billList: ListBuffer[Bill], year: Int): LinkedHashMap[Int, Double] =
    val monthlyUsageOrCost: LinkedHashMap[Int, Double] = LinkedHashMap()
    usageOrCost match
      case "usage" =>
        for month <- Range(1,13) do
          val monthlyAverageUsage = getMonthlyAverageUsageOrCost(userType, usageType, cityOrRegion, cityRegion, billList, usageOrCost, year, month)
          monthlyAverageUsage match
            case sum if sum.isNaN => monthlyUsageOrCost(month) = 0.0
            case _ => monthlyUsageOrCost(month) = monthlyAverageUsage

      case "cost" =>
        for month <- Range(1,13) do
          val monthlyAverageCost = getMonthlyAverageUsageOrCost(userType, usageType, cityOrRegion, cityRegion, billList, usageOrCost, year, month)
          monthlyAverageCost match
            case sum if sum.isNaN => monthlyUsageOrCost(month) = 0.0
            case _ => monthlyUsageOrCost(month) = monthlyAverageCost

    monthlyUsageOrCost


  def getMonthlyAverageUsageOrCost(userType: String, usageType: String, cityOrRegion: String, cityRegion: String, billList: ListBuffer[Bill], usageOrCost: String, year: Int, month: Int): Double =
    usageOrCost match
      case "usage" =>
        getBillsByCityOrRegion(userType, usageType, cityOrRegion, cityRegion, billList).filter(bill => bill.month == month && bill.year == year).foldLeft(0.0)(_ + _.usage) /
          getBillsByCityOrRegion(userType, usageType, cityOrRegion, cityRegion, billList).count(bill => bill.month == month && bill.year == year)
      case "cost" =>
        getBillsByCityOrRegion(userType, usageType, cityOrRegion, cityRegion, billList).filter(bill => bill.month == month && bill.year == year).foldLeft(0.0)(_ + _.cost) /
          getBillsByCityOrRegion(userType, usageType, cityOrRegion, cityRegion, billList).count(bill => bill.month == month && bill.year == year)


  def composeUsageOrCostInformation(bill: Bill, requestedInformation: String): String =
    requestedInformation match
      case "cost" =>
        "cost: " + bill.cost + "\nmonth: " + bill.month + "\nyear: " + bill.year+"\n"
      case "usage" =>
        "usage: " + bill.usage + "\nmonth: " + bill.month + "\nyear: " + bill.year+"\n"