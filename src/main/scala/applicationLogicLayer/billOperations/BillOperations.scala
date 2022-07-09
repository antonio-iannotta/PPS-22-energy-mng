package applicationLogicLayer.billOperations

import scala.collection.mutable.LinkedHashMap
import dataLayer.bill.Bill
import businessLogicLayer.billBuilder.BillBuilder

import collection.mutable.ListBuffer
import scala.util.Random
import Utils._

import scala.collection.mutable

object BillOperations:


  /**
   * The following function returns the cost or usage related to a certain userID and a certain user type
   * @param userID is the userID target for the search of stored bills
   * @param usageType is the usageType target for the search of stored bills
   * @param costOrUsage is the variable that states if the required information is the cost or usage
   * @return
   */
  def getIndividualCostOrUsage(userID: String, usageType: String, costOrUsage: String): String =
    val billList: ListBuffer[Bill] = BillBuilder.build()
    getBillsByUserIDAndUsageType(userID, usageType, billList)
      .foldLeft[String]("")(_ + composeUsageOrCostInformation(_ , costOrUsage.toLowerCase))

  
  
  

  /**
   * This function returns the average monthly cost for a certain user type related to a certain usage type and a certain location
   * @param userType is the user type target for the search of stored bills
   * @param usageType is the usage type target for the search of stored bills
   * @param location is the location target for the search of stored bills
   * @param locationType is the location type (city or region)
   * @param usageOrCost is the variable that states if computation must be computed on usage or cost
   * @param year is the target year
   * @return
   */
  def getUsageOrCostByLocation(userType: String, usageType: String, location: String, locationType: String, usageOrCost: String, year: Int): mutable.LinkedHashMap[Int, Double] =
    val billList: ListBuffer[Bill] = BillBuilder.build()
    monthlyUsageOrCost(userType,usageType,locationType,location,usageOrCost, billList, year)

  
  
  

  /**
   * The following function returns the prediction for an individual user, for a certain usage type and related to a certain year
   * @param userID is the userID target
   * @param usageType is the usage
   * @param year is the reference year for the prediction
   * @return
   */
  def makeIndividualPrediction(userID: String, usageType: String, year: Int): String =
    val billList: ListBuffer[Bill] = BillBuilder.build()
    val annualUsage: mutable.LinkedHashMap[Int, Double] = mutable.LinkedHashMap()
    val annualCost: mutable.LinkedHashMap[Int, Double] = mutable.LinkedHashMap()

    individualMapInitialization(annualUsage, userID, usageType, billList)
    individualMapInitialization(annualCost, userID, usageType, billList)

    fillIndividualUsageCostMap(annualUsage, "usage", userID, usageType, billList)
    fillIndividualUsageCostMap(annualCost, "cost", userID, usageType, billList)

    annualUsage.toSeq.sortBy(_._1)
    annualCost.toSeq.sortBy(_._1)

    val averageUsage = average(annualUsage)
    val averageCost = average(annualCost)

    predictionResult(year,annualUsage, averageUsage, averageCost, usageType)

  
  
  

  /**
   * The following function returns the prediction for a certain location related to a certain usage type and a certain user type
   * @param userType is the user type target
   * @param usageType is the usage type target
   * @param year is the year for which the prediction is desired
   * @param locationType is the location type (city or region)
   * @param location is the location target
   * @return
   */
  def makePredictionByLocation(userType: String, usageType: String, year: Int, locationType: String, location: String): String =
    val billList: ListBuffer[Bill] = BillBuilder.build()
    val annualUsage: mutable.LinkedHashMap[Int, Double] = mutable.LinkedHashMap()
    val annualCost: mutable.LinkedHashMap[Int, Double] = mutable.LinkedHashMap()

    mapInitializationByLocation(annualUsage, userType, usageType, locationType, location, billList)
    mapInitializationByLocation(annualCost, userType, usageType, locationType, location, billList)

    fillUsageCostMapByLocation(annualUsage,usageType,"usage",userType, locationType, location, billList)
    fillUsageCostMapByLocation(annualCost, usageType, "cost", userType, locationType, location, billList)

    annualUsage.toSeq.sortBy(_._1)
    annualCost.toSeq.sortBy(_._1)

    val averageUsage = average(annualUsage)
    val averageCost = average(annualCost)

    predictionResult(year,annualUsage, averageUsage, averageCost, usageType)


