package billOperations

import scala.collection.mutable.LinkedHashMap
import bill.Bill
import billBuilder.BillBuilder
import collection.mutable.ListBuffer

import scala.util.Random

object BillOperations:
  val billList: ListBuffer[Bill] = BillBuilder.build()
  /*
  Il seguente metodo ritorna il costo o il consumo mensile per una certa utenza relativamente ad uno specifico utente
  */
  def getIndividualCostOrUsage(userID: String, usageType: String, costOrUsage: String): String =
    var individualInformation = ""
    val userIdBills = getBillsByUserIDAndUsageType(userID, usageType)
    for (bill <- userIdBills) do
      individualInformation += composeUsageOrCostInformation(bill, costOrUsage.toLowerCase())

    individualInformation

  /*
  Il seguente metodo ritorna il costo o il consumo mensile per una certa utenza relativamente ad una certa località geografica
  */
  def getUsageOrCostByLocation(userType: String, usageType: String, cityRegion: String, cityOrRegion: String, usageOrCost: String): String =
    monthlyUsageOrCost(userType,usageType,cityOrRegion,cityRegion,usageOrCost).toString()

  /*
  Il seguente metodo effettua le previsioni per un singolo utente con riferimento ad un certo anno e con riferimento ad una certa utenza
  */
  def makeIndividualPrediction(userID: String, usageType: String, year: Int): String =
    var result = ""
    val annualUsage: LinkedHashMap[Int, Double] = LinkedHashMap()
    val annualCost: LinkedHashMap[Int, Double] = LinkedHashMap()

    individualMapInitialization(annualUsage, userID, usageType)
    individualMapInitialization(annualCost, userID, usageType)

    fillIndividualUsageCostMap(annualUsage, "usage", userID, usageType)
    fillIndividualUsageCostMap(annualCost, "cost", userID, usageType)

    annualUsage.toSeq.sortBy(_._1)
    annualCost.toSeq.sortBy(_._1)

    var percentageUsageVariation = percentageVariation(annualUsage)
    var percentageCostVariation = percentageVariation(annualCost)

    predictionResult(year,annualUsage, percentageUsageVariation, percentageCostVariation, usageType)

  /*
  Il seguente metodo effettua le previsioni relativamente ad una certa tipologia di consumi per un certo anno per una specifica località geografica
  */
  def makePredictionByLocation(userType: String, usageType: String, year: Int, cityOrRegion: String, cityRegion: String): String =

    var result = ""
    val annualUsage: LinkedHashMap[Int, Double] = LinkedHashMap()
    val annualCost: LinkedHashMap[Int, Double] = LinkedHashMap()

    initializationMapByLocation(annualUsage, userType, usageType, cityOrRegion, cityRegion)
    initializationMapByLocation(annualCost, userType, usageType, cityOrRegion, cityRegion)

    fillUsageCostMapByLocation(annualUsage,usageType,"usage",userType, cityOrRegion, cityRegion)
    fillUsageCostMapByLocation(annualCost, usageType, "cost", userType, cityOrRegion, cityRegion)

    annualUsage.toSeq.sortBy(_._1)
    annualCost.toSeq.sortBy(_._1)

    var percentageUsageVariation = annualUsage.values.foldLeft[Double](0.0)(_ + _) / annualUsage.keys.size
    var percentageCostVariation = annualCost.values.foldLeft[Double](0.0)(_ + _) / annualCost.keys.size

    predictionResult(year,annualUsage, percentageUsageVariation, percentageCostVariation, usageType)



  /*
  Il seguente metodo privato ritorna il risultato della previsione richiesta
  */
  private def predictionResult(year: Int, map: LinkedHashMap[Int, Double], percentageUsageVariation: Double, percentageCostVariation: Double, usageType: String): String =
    var result = ""
    var costVariation = percentageCostVariation
    var usageVariation = percentageUsageVariation
    val lengthYearPrediction = year - map.keys.head
    if (lengthYearPrediction <= map.keys.size) then
      result = "Your usage and cost for " + usageType + " is not supposed to change for " + year
    else
      for i <- Range(0,lengthYearPrediction - map.keys.size) do
        costVariation = costVariation + Random.nextDouble()/100
        usageVariation = usageVariation + Random.nextDouble()/100
        result = s"Year: ${year}\nPredicted usage variation: ${usageVariation}\nPredicted cost variation: ${costVariation}"
    result



  /*
  Il seguente metodo privato ritorna la variazione percentuale di costi o consumi su base annuale.
  La variazione è calcolata sulla mappa anno-consumi/costi che viene fornita in input
  */
  private def percentageVariation(map: LinkedHashMap[Int, Double]): Double =
    map.values.foldLeft(0.0)(_ + _) / map.keys.size


  /*
  Il seguente metodo inizializza la mappa anno-consumi/costi passata come argomento, filtrando per singolo utente, di modo da poter essere utilizzata nelle previsioni
  */
  private def individualMapInitialization(map: LinkedHashMap[Int, Double], userID: String, usageType: String): Unit =
    billList.filter(bill => bill.getUserID() == userID && bill.getUsageType() == usageType).foreach(bill => map(bill.getYear()) = 0.0)

  /*
  Il seguente metodo inizializza la mappa anno-consumi/costi passata come argomento filtrando per la tipologia di utente, la tipologia di consumo e la località geografica
  */
  private def initializationMapByLocation(map: LinkedHashMap[Int, Double], userType: String, usageType: String, cityOrRegion: String, cityRegion: String): Unit =
    cityOrRegion match
      case "city" =>
        billList.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getCity() == cityRegion)
          .foreach(bill => map(bill.getYear()) = 0.0)
      case "region" =>
        billList.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getRegion() == cityRegion)
          .foreach(bill => map(bill.getYear()) = 0.0)


  /*
  Il seguente metodo riempie la mappa anno-consumi/costi relativa ai costi e ai consumi individuali annuali con la media dei consumi/costi per ogni anno
  */
  private def fillIndividualUsageCostMap(map: LinkedHashMap[Int, Double], usageOrCost: String, userID: String, usageType: String): Unit =
    usageOrCost match
      case "usage" =>
        map.keys.foreach(
          mapYear => map(mapYear) =
            billList.filter(bill => bill.getUserID() == userID && bill.getYear() == mapYear && bill.getUsageType() == usageType).foldLeft(0.0)(_ + _.getUsage()) /
              billList.count(bill => bill.getUserID() == userID && bill.getYear() == mapYear && bill.getUsageType() == usageType)
        )
      case "cost" =>
        map.keys.foreach(
          mapYear => map(mapYear) =
            billList.filter(bill => bill.getUserID() == userID && bill.getYear() == mapYear && bill.getUsageType() == usageType).foldLeft(0.0)(_ + _.getUsage()) /
              billList.count(bill => bill.getUserID() == userID && bill.getYear() == mapYear && bill.getUsageType() == usageType)
        )

  /*
  Il seguente metodo riempie la mappa anno-consumi/costi relativa ai costi e consumi per una specifica utenza per una specifica località geografica con la media dei consumi/costi per ogni anno
  */
  private def fillUsageCostMapByLocation(map: LinkedHashMap[Int, Double], usageType: String, usageOrCost: String, userType: String, cityOrRegion: String, cityRegion: String): Unit =
    cityOrRegion match
      case "city" =>
        usageOrCost match
          case "usage" =>
            map.keys.foreach(
              mapYear => map(mapYear) =
                billList.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getYear() == mapYear && bill.getCity() == cityRegion).foldLeft(0.0)(_ + _.getUsage()) /
                  billList.count(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getYear() == mapYear && bill.getCity() == cityRegion)
            )
          case "cost" =>
            map.keys.foreach(
              mapYear => map(mapYear) =
                billList.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getYear() == mapYear && bill.getCity() == cityRegion).foldLeft(0.0)(_ + _.getCost()) /
                  billList.count(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getYear() == mapYear && bill.getCity() == cityRegion)
            )
      case "region" =>
        usageOrCost match
          case "usage" =>
            map.keys.foreach(
              mapYear => map(mapYear) =
                billList.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getYear() == mapYear && bill.getRegion() == cityRegion).foldLeft(0.0)(_ + _.getUsage()) /
                  billList.count(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getYear() == mapYear && bill.getRegion() == cityRegion)
            )
          case "cost" =>
            map.keys.foreach(
              mapYear => map(mapYear) =
                billList.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getYear() == mapYear && bill.getRegion() == cityRegion).foldLeft(0.0)(_ + _.getCost()) /
                  billList.count(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getYear() == mapYear && bill.getRegion() == cityRegion)
            )


  /*
  Questo metodo privato ritorna le bollette relative allo specifico utente per la specifica utenza
  */
  private def getBillsByUserIDAndUsageType(userID: String, usageType: String): ListBuffer[Bill] =
    billList.filter(bill => bill.getUserID() == userID && bill.getUsageType() == usageType)



  /*
  Questo metodo ritorna le bollette relative ad una specifica tipologia di utenti in una certa località geografica con riferimento ad una specifica utenza
  */
  private def getBillsByCityOrRegion(userType: String, usageType: String, cityOrRegion: String, cityRegion: String): ListBuffer[Bill] =
    var bills: ListBuffer[Bill] = ListBuffer()
    cityOrRegion match
      case "city" =>
        bills = billList.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getCity() == cityRegion)
      case "region" =>
        bills = billList.filter(bill => bill.getUserType() == userType && bill.getUsageType() == usageType && bill.getRegion() == cityRegion)

    bills



  /*
  Questo metodo opera un raggruppamento per mesi riportando una mappa anno-consumi/costi in cui i consumi/costi sono la somma di tutti i valori relativi ad una certa località geografica
  */
  private def monthlyUsageOrCost(userType: String, usageType: String, cityOrRegion: String, cityRegion: String, usageOrCost: String): LinkedHashMap[Int, Double] =
    val monthlyUsageOrCost: LinkedHashMap[Int, Double] = LinkedHashMap()
    val cityOrRegionBills = getBillsByCityOrRegion(userType, usageType, cityOrRegion, cityRegion)
    usageOrCost match
      case "usage" =>
        for i <- Range(1,13) do
          val monthlyUsageSum = cityOrRegionBills.filter(bill => bill.getMonth() == i).foldLeft(0.0)(_ + _.getUsage()) /
            cityOrRegionBills.filter(bill => bill.getMonth() == i).size
          if (!monthlyUsageSum.isNaN) then
            monthlyUsageOrCost(i) = monthlyUsageSum
          else
            monthlyUsageOrCost(i) = 0.0

      case "cost" =>
        for i <- Range(1,13) do
          val monthlyCostSum = cityOrRegionBills.filter(bill => bill.getMonth() == i).foldLeft(0.0)(_ + _.getCost()) /
            cityOrRegionBills.count(bill => bill.getMonth() == i)
          if (!monthlyCostSum.isNaN) then
            monthlyUsageOrCost(i) = monthlyCostSum
          else
            monthlyUsageOrCost(i) = 0.0

    monthlyUsageOrCost


  /*
  Questo metodo privato ritorna semplicemente i risultati dell'analisi su consumi/costi per una certa bolletta
  */
  private def composeUsageOrCostInformation(bill: Bill, requestedInformation: String): String =
    var result = ""
    requestedInformation match
      case "cost" =>
        result = "cost: " + bill.getCost() + "\nmonth: " + bill.getMonth() + "\nyear: " + bill.getYear()+"\n"
      case "usage" =>
        result = "usage: " + bill.getUsage() + "\nmonth: " + bill.getMonth() + "\nyear: " + bill.getYear()+"\n"

    result
