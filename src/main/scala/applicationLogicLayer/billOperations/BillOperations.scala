package applicationLogicLayer.billOperations

import scala.collection.mutable.LinkedHashMap
import dataLayer.bill.Bill
import businessLogicLayer.billBuilder.BillBuilder
import collection.mutable.ListBuffer
import scala.util.Random
import Utils._

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
   * Il seguente metodo ritorna una mappa [Int, Double] a cui ogni mese è associato il costo medio di tipologie di consumi o costi relativi ad un certo utente, una certa utenza, una certa città o regione e un certo anno
   * @param userType
   * @param usageType
   * @param location
   * @param locationType
   * @param usageOrCost
   * @param year
   * @return
   */
  def getUsageOrCostByLocation(userType: String, usageType: String, location: String, locationType: String, usageOrCost: String, year: Int): LinkedHashMap[Int, Double] =
    val billList: ListBuffer[Bill] = BillBuilder.build()
    monthlyUsageOrCost(userType,usageType,locationType,location,usageOrCost, billList, year)

  
  
  

  /**
   * Il seguente metodo esegue le previsioni basate su un singolo utente relative ad un certo anno ed una certa utenza
   * @param userID
   * @param usageType
   * @param year
   * @return
   */
  def makeIndividualPrediction(userID: String, usageType: String, year: Int): String =
    val billList: ListBuffer[Bill] = BillBuilder.build()
    val annualUsage: LinkedHashMap[Int, Double] = LinkedHashMap()
    val annualCost: LinkedHashMap[Int, Double] = LinkedHashMap()

    individualMapInitialization(annualUsage, userID, usageType, billList)
    individualMapInitialization(annualCost, userID, usageType, billList)

    fillIndividualUsageCostMap(annualUsage, "usage", userID, usageType, billList)
    fillIndividualUsageCostMap(annualCost, "cost", userID, usageType, billList)

    annualUsage.toSeq.sortBy(_._1)
    annualCost.toSeq.sortBy(_._1)

    var averageUsage = average(annualUsage)
    var averageCost = average(annualCost)

    predictionResult(year,annualUsage, averageUsage, averageCost, usageType)

  
  
  

  /**
   * Il seguente metodo ritorna le previsioni associate ad una certa tipologia di consumo, ad una tipologia di utente, ad una certa città o regione e ad un certo anno.
   * @param userType
   * @param usageType
   * @param year
   * @param locationType
   * @param location
   * @return
   */
  def makePredictionByLocation(userType: String, usageType: String, year: Int, locationType: String, location: String): String =
    val billList: ListBuffer[Bill] = BillBuilder.build()
    val annualUsage: LinkedHashMap[Int, Double] = LinkedHashMap()
    val annualCost: LinkedHashMap[Int, Double] = LinkedHashMap()

    mapInitializationByLocation(annualUsage, userType, usageType, locationType, location, billList)
    mapInitializationByLocation(annualCost, userType, usageType, locationType, location, billList)

    fillUsageCostMapByLocation(annualUsage,usageType,"usage",userType, locationType, location, billList)
    fillUsageCostMapByLocation(annualCost, usageType, "cost", userType, locationType, location, billList)

    annualUsage.toSeq.sortBy(_._1)
    annualCost.toSeq.sortBy(_._1)

    val averageUsage = average(annualUsage)
    val averageCost = average(annualCost)

    predictionResult(year,annualUsage, averageUsage, averageCost, usageType)


