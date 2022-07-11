package applicationLogicLayer.billOperations


import dataLayer.bill.Bill

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random

object Utils:

  /**
   * The following function returns the string related to a certain prediction
   * @param year is the target year for the prediction
   * @param yearMap is the map of the average usage/cost for every year stored into the database
   * @param averageUsage is the average usage of every year stored into the database, related to a certain usage type
   * @param averageCost is the average cost of every year stored into the database, related to a certain usage type
   * @param usageType is the target usage type for the prediction
   * @return
   */
  def predictionResult(year: Int, yearMap: mutable.LinkedHashMap[Int, Double], averageUsage: Double, averageCost: Double, usageType: String): String =
    year - yearMap.keys.last match

      case duration if duration <= yearMap.keys.size =>
        "Your usage and cost for " + usageType + " is not supposed to change for " + year

      case duration if duration > yearMap.keys.size =>
        s"Year: $year\nPredicted usage variation: ${BigDecimal(averageUsage - (averageUsage - Random.between(-1.0, 1.0) * duration)).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble}\nPredicted cost variation: ${BigDecimal(averageCost - (averageCost - Random.between(-1.0, 1.0) * duration)).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble}"





  /**
   * The following function returns the average for every values stored into a [Int, Double] map
   * @param map is a general map indexed by Int and with Double as values
   * @return
   */
  def average(map: mutable.LinkedHashMap[Int, Double]): Double =
    map.values.foldLeft(0.0)(_ + _) / map.keys.size





  /**
   * The following function initializes a map with 0.0 for every year stored into the database related to a certain userID and a certain usage type
   * @param individualMap is the map to be initialized with value 0.0 for every year of stored bills related to a specific userID and a specific usage type
   * @param userID is the userID target for the search of stored bills
   * @param usageType is the usage type target for the search of stored bills
   * @param billList is the list of bills stored into the database
   */
  def individualMapInitialization(individualMap: mutable.LinkedHashMap[Int, Double], userID: String, usageType: String, billList: mutable.ListBuffer[Bill]): Unit =
    billList.filter(bill => bill.userID == userID && bill.usageType == usageType).foreach(bill => individualMap(bill.year) = 0.0)





  /**
   * The following function initializes a map with 0.0 for every year stored into the database related to a certain user type, a certain usage type, a certain location type (region or city) and a certain location
   * @param mapByLocation is the map to be initialized with value 0.0 for every year of stored filtered bills
   * @param userType is the target user type for the search of stored bills
   * @param usageType is the target usage type for the search of stored bills
   * @param locationType is the target location type for the search of stored bills
   * @param location is the target location for the search of stored bills
   * @param billList is the list of bills stored into the database
   */
  def mapInitializationByLocation(mapByLocation: mutable.LinkedHashMap[Int, Double], userType: String, usageType: String, locationType: String, location: String, billList: mutable.ListBuffer[Bill]): Unit =

    locationType match

      case "city" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.city == location)
          .foreach(bill => mapByLocation(bill.year) = 0.0)

      case "region" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.region == location)
          .foreach(bill => mapByLocation(bill.year) = 0.0)





  /**
   * The following function fills a map with the average of usage or cost for a certain usage type, a certain userID for every year stored into the database
   * @param individualMap is the map to be filled with average values
   * @param usageOrCost is the variable that indicates if the average must be calculated on the usages or the costs
   * @param userID is the userID target for the search of stored bills
   * @param usageType is the usage type target for the search of stored bills
   * @param billList is the list of bills stored into the database
   */
  def fillIndividualUsageCostMap(individualMap: mutable.LinkedHashMap[Int, Double], usageOrCost: String, userID: String, usageType: String, billList: mutable.ListBuffer[Bill]): Unit =

    usageOrCost match

      case "usage" =>
        individualMap.keys.foreach(
          mapYear => individualMap(mapYear) =
            billList.filter(bill => bill.userID == userID && bill.year == mapYear && bill.usageType == usageType).foldLeft(0.0)(_ + _.usage) /
              billList.count(bill => bill.userID == userID && bill.year == mapYear && bill.usageType == usageType)
        )

      case "cost" =>
        individualMap.keys.foreach(
          mapYear => individualMap(mapYear) =
            billList.filter(bill => bill.userID == userID && bill.year == mapYear && bill.usageType == usageType).foldLeft(0.0)(_ + _.cost) /
              billList.count(bill => bill.userID == userID && bill.year == mapYear && bill.usageType == usageType)
        )

      case _ => individualMap.empty





  /**
   * The following function fills a map with the average value of usage or cost for a certain user type, a certain usage type, a certain location type (region or city) and a certain location for every year stored into the
   * database
   * @param usageCostMap is the map to be filled
   * @param usageType is the usage type target for the search of stored bills
   * @param usageOrCost is the parameter that states if the average must be computed on usages or costs
   * @param userType is the user type target for the search of stored bills
   * @param locationType is the location type target for the search of stored bills
   * @param location is the location target for the search of stored bills
   * @param billList is the list of bills stored into the database
   */
  def fillUsageCostMapByLocation(usageCostMap: mutable.LinkedHashMap[Int, Double], usageType: String, usageOrCost: String, userType: String, locationType: String, location: String, billList: mutable.ListBuffer[Bill]): Unit =

    locationType match

      case "city" =>

        usageOrCost match

          case "usage" =>
            usageCostMap.keys.foreach(
              mapYear => usageCostMap(mapYear) =
                billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.city == location).foldLeft(0.0)(_ + _.usage) /
                  billList.count(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.city == location)
            )

          case "cost" =>
            usageCostMap.keys.foreach(
              mapYear => usageCostMap(mapYear) =
                billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.city == location).foldLeft(0.0)(_ + _.cost) /
                  billList.count(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.city == location)
            )

      case "region" =>

        usageOrCost match

          case "usage" =>
            usageCostMap.keys.foreach(
              mapYear => usageCostMap(mapYear) =
                billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.region == location).foldLeft(0.0)(_ + _.usage) /
                  billList.count(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.region == location)
            )

          case "cost" =>
            usageCostMap.keys.foreach(
              mapYear => usageCostMap(mapYear) =
                billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.region == location).foldLeft(0.0)(_ + _.cost) /
                  billList.count(bill => bill.userType == userType && bill.usageType == usageType && bill.year == mapYear && bill.region == location)
            )





  /**
   *The following function returns the bills related to a certain user ID for a certain usage type
   * @param userID is the userID target for the search of stored bills
   * @param usageType is the usage type for the search of stored bills
   * @param billList is the list of bills stored into the database
   * @return
   */
  def getBillsByUserIDAndUsageType(userID: String, usageType: String, billList: mutable.ListBuffer[Bill]): mutable.ListBuffer[Bill] =
    billList.filter(bill => bill.userID == userID && bill.usageType == usageType)





  /**
   * The following function returns the bills related to a certain user type, for a certain usage type, related to a certain location
   * @param userType is the user type target for the search of stored bills
   * @param usageType is the usage type target for the search of stored bills
   * @param locationType is the location type (city or region)
   * @param location is the location target for the search of stored bills
   * @param billList is the list of bills stored into the database
   * @return
   */
  def getBillsByCityOrRegion(userType: String, usageType: String, locationType: String, location: String, billList: mutable.ListBuffer[Bill]): mutable.ListBuffer[Bill] =

    locationType match

      case "city" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.city == location)

      case "region" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.region == location)





  /**
   * The following function returns the average usage or cost for every month of a certain year stored into the database, related to a certain user type and a certain usage type and location
   * @param userType is the user type target for the search of stored bills
   * @param usageType is the usage type target for the search of stored bills
   * @param locationType is the location type (city or region)
   * @param location is the location target for the search of stored bills
   * @param usageOrCost is the variable that states if the average must be computed on usages or costs
   * @param billList is the list of bills stored into the database
   * @param year is the target year
   * @return
   */
  def monthlyUsageOrCost(userType: String, usageType: String, locationType: String, location: String, usageOrCost: String, billList: mutable.ListBuffer[Bill], year: Int): mutable.LinkedHashMap[Int, Double] =

    val monthlyUsageOrCost: mutable.LinkedHashMap[Int, Double] = mutable.LinkedHashMap()

    usageOrCost match

      case "usage" =>
        for month <- 1 to 12 do
          val monthlyAverageUsage = getMonthlyAverageUsageOrCost(userType, usageType, locationType, location, billList, usageOrCost, year, month)
          monthlyAverageUsage match
            case sum if sum.isNaN => monthlyUsageOrCost(month) = 0.0
            case _ => monthlyUsageOrCost(month) = monthlyAverageUsage

      case "cost" =>
        for month <- 1 to 12 do
          val monthlyAverageCost = getMonthlyAverageUsageOrCost(userType, usageType, locationType, location, billList, usageOrCost, year, month)
          monthlyAverageCost match
            case sum if sum.isNaN => monthlyUsageOrCost(month) = 0.0
            case _ => monthlyUsageOrCost(month) = monthlyAverageCost

    monthlyUsageOrCost





  /**
   * The following function returns the average usage or cost for a specific month, a specific location, a specific usage type and a specific user type
   * @param userType is the user type target for the search of stored bills into the database
   * @param usageType is the usage type target for the search of stored bills stored into the database
   * @param locationType is the location type (city or region)
   * @param location is the location target for the search of stored bills stored into the database
   * @param billList is the list of bills stored into the database
   * @param usageOrCost is the variable that states if the average must be computed on usages or costs
   * @param year is the target year
   * @param month is the target month
   * @return
   */
  def getMonthlyAverageUsageOrCost(userType: String, usageType: String, locationType: String, location: String, billList: ListBuffer[Bill], usageOrCost: String, year: Int, month: Int): Double =

    usageOrCost match

      case "usage" =>
        getBillsByCityOrRegion(userType, usageType, locationType, location, billList).filter(bill => bill.month == month && bill.year == year).foldLeft(0.0)(_ + _.usage) /
          getBillsByCityOrRegion(userType, usageType, locationType, location, billList).count(bill => bill.month == month && bill.year == year)

      case "cost" =>
        getBillsByCityOrRegion(userType, usageType, locationType, location, billList).filter(bill => bill.month == month && bill.year == year).foldLeft(0.0)(_ + _.cost) /
          getBillsByCityOrRegion(userType, usageType, locationType, location, billList).count(bill => bill.month == month && bill.year == year)





  /**
   * The following function returns the information related to cost or usages for a certain bill
   * @param bill is the bill for which information ar desired
   * @param usageOrCost is the variable that states if the information must be returned for usage or cost
   * @return
   */
  def composeUsageOrCostInformation(bill: Bill, usageOrCost: String): String =

    usageOrCost match

      case "cost" =>
        "Cost: " + bill.cost + "€" + " - Usage type: " + bill.usageType + " - Month: " + bill.month + " - Year: " + bill.year + "\n"

      case "usage" =>
        "Usage: " + usageFormatter(bill.usageType, bill.usage) + " - Usage type: " + bill.usageType +  " - Month: " + bill.month + " - Year: " + bill.year + "\n"


  def usageFormatter(usageType: String, usage: Double): String =

    usageType match

      case "water" =>
        usage + "Lmc"

      case "heat" =>
        usage + "Smc"

      case "electricity" =>
        usage + "Kw/h"
