package applicationLogicLayer.billOperations

import scala.collection.mutable.LinkedHashMap
import bill.Bill
import billBuilder.BillBuilder
import collection.mutable.ListBuffer

import scala.util.Random

object BillOperations:
  /*
  Il seguente metodo ritorna il costo o il consumo mensile per una certa utenza relativamente ad uno specifico utente
  */
  def getIndividualCostOrUsage(userID: String, usageType: String, costOrUsage: String): String =
    val billList: ListBuffer[Bill] = BillBuilder.build()
    getBillsByUserIDAndUsageType(userID, usageType, billList)
      .foldLeft[String]("")(_ + composeUsageOrCostInformation(_ , costOrUsage.toLowerCase))

  /*
  Il seguente metodo ritorna il costo o il consumo mensile per una certa utenza relativamente ad una certa località geografica
  */
  def getUsageOrCostByLocation(userType: String, usageType: String, cityRegion: String, cityOrRegion: String, usageOrCost: String): LinkedHashMap[Int, Double] =
    val billList: ListBuffer[Bill] = BillBuilder.build()
    monthlyUsageOrCost(userType,usageType,cityOrRegion,cityRegion,usageOrCost, billList)

  /*
  Il seguente metodo effettua le previsioni per un singolo utente con riferimento ad un certo anno e con riferimento ad una certa utenza
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

    var percentageUsageVariation = percentageVariation(annualUsage)
    var percentageCostVariation = percentageVariation(annualCost)

    predictionResult(year,annualUsage, percentageUsageVariation, percentageCostVariation, usageType)

  /*
  Il seguente metodo effettua le previsioni relativamente ad una certa tipologia di consumi per un certo anno per una specifica località geografica
  */
  def makePredictionByLocation(userType: String, usageType: String, year: Int, cityOrRegion: String, cityRegion: String): String =
    val billList: ListBuffer[Bill] = BillBuilder.build()
    val annualUsage: LinkedHashMap[Int, Double] = LinkedHashMap()
    val annualCost: LinkedHashMap[Int, Double] = LinkedHashMap()

    initializationMapByLocation(annualUsage, userType, usageType, cityOrRegion, cityRegion, billList)
    initializationMapByLocation(annualCost, userType, usageType, cityOrRegion, cityRegion, billList)

    fillUsageCostMapByLocation(annualUsage,usageType,"usage",userType, cityOrRegion, cityRegion, billList)
    fillUsageCostMapByLocation(annualCost, usageType, "cost", userType, cityOrRegion, cityRegion, billList)

    annualUsage.toSeq.sortBy(_._1)
    annualCost.toSeq.sortBy(_._1)

    val percentageUsageVariation = annualUsage.values.foldLeft[Double](0.0)(_ + _) / annualUsage.keys.size
    val percentageCostVariation = annualCost.values.foldLeft[Double](0.0)(_ + _) / annualCost.keys.size

    predictionResult(year,annualUsage, percentageUsageVariation, percentageCostVariation, usageType)



  /*
  Il seguente metodo privato ritorna il risultato della previsione richiesta
  */
  //DA RIGUARDARE
  private def predictionResult(year: Int, map: LinkedHashMap[Int, Double], percentageUsageVariation: Double, percentageCostVariation: Double, usageType: String): String =
    year - map.keys.size match
      case duration if duration <= map.keys.size =>
        "Your usage and cost for " + usageType + " is not supposed to change for " + year
      case duration if duration > map.keys.size =>
        s"Year: ${year}\nPredicted usage variation: ${percentageUsageVariation + Random.nextDouble()}\nPredicted cost variation: ${percentageCostVariation + Random.nextDouble()}"

  /*
  Il seguente metodo privato ritorna la variazione percentuale di costi o consumi su base annuale.
  La variazione è calcolata sulla mappa anno-consumi/costi che viene fornita in input
  */
  private def percentageVariation(map: LinkedHashMap[Int, Double]): Double =
    map.values.foldLeft(0.0)(_ + _) / map.keys.size


  /*
  Il seguente metodo inizializza la mappa anno-consumi/costi passata come argomento, filtrando per singolo utente, di modo da poter essere utilizzata nelle previsioni
  */
  private def individualMapInitialization(map: LinkedHashMap[Int, Double], userID: String, usageType: String, billList: ListBuffer[Bill]): Unit =
    billList.filter(bill => bill.userID == userID && bill.usageType == usageType).foreach(bill => map(bill.year) = 0.0)

  /*
  Il seguente metodo inizializza la mappa anno-consumi/costi passata come argomento filtrando per la tipologia di utente, la tipologia di consumo e la località geografica
  */
  private def initializationMapByLocation(map: LinkedHashMap[Int, Double], userType: String, usageType: String, cityOrRegion: String, cityRegion: String, billList: ListBuffer[Bill]): Unit =
    cityOrRegion match
      case "city" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.city == cityRegion)
          .foreach(bill => map(bill.year) = 0.0)
      case "region" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.region == cityRegion)
          .foreach(bill => map(bill.year) = 0.0)


  /*
  Il seguente metodo riempie la mappa anno-consumi/costi relativa ai costi e ai consumi individuali annuali con la media dei consumi/costi per ogni anno
  */
  private def fillIndividualUsageCostMap(map: LinkedHashMap[Int, Double], usageOrCost: String, userID: String, usageType: String, billList: ListBuffer[Bill]): Unit =
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
            billList.filter(bill => bill.userID == userID && bill.year == mapYear && bill.usageType == usageType).foldLeft(0.0)(_ + _.usage) /
              billList.count(bill => bill.userID == userID && bill.year == mapYear && bill.usageType == usageType)
        )

  /*
  Il seguente metodo riempie la mappa anno-consumi/costi relativa ai costi e consumi per una specifica utenza per una specifica località geografica con la media dei consumi/costi per ogni anno
  */
  private def fillUsageCostMapByLocation(map: LinkedHashMap[Int, Double], usageType: String, usageOrCost: String, userType: String, cityOrRegion: String, cityRegion: String, billList: ListBuffer[Bill]): Unit =
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


  /*
  Questo metodo privato ritorna le bollette relative allo specifico utente per la specifica utenza
  */
  private def getBillsByUserIDAndUsageType(userID: String, usageType: String, billList: ListBuffer[Bill]): ListBuffer[Bill] =
    billList.filter(bill => bill.userID == userID && bill.usageType == usageType)


  /*
  Questo metodo ritorna le bollette relative ad una specifica tipologia di utenti in una certa località geografica con riferimento ad una specifica utenza
  */
  private def getBillsByCityOrRegion(userType: String, usageType: String, cityOrRegion: String, cityRegion: String, billList: ListBuffer[Bill]): ListBuffer[Bill] =
    cityOrRegion match
      case "city" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.city == cityRegion)
      case "region" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.region == cityRegion)



  /*
  Questo metodo opera un raggruppamento per mesi riportando una mappa anno-consumi/costi in cui i consumi/costi sono la somma di tutti i valori relativi ad una certa località geografica
  */
  private def monthlyUsageOrCost(userType: String, usageType: String, cityOrRegion: String, cityRegion: String, usageOrCost: String, billList: ListBuffer[Bill]): LinkedHashMap[Int, Double] =
    val monthlyUsageOrCost: LinkedHashMap[Int, Double] = LinkedHashMap()
    usageOrCost match
      case "usage" =>
        for i <- Range(1,13) do
          val monthlyUsageSum = getBillsByCityOrRegion(userType, usageType, cityOrRegion, cityRegion, billList)
            .filter(bill => bill.month == i).foldLeft(0.0)(_ + _.usage) /
            getBillsByCityOrRegion(userType, usageType, cityOrRegion, cityRegion, billList)
              .count(bill => bill.month == i)
          monthlyUsageSum match
            case sum if sum.isNaN => monthlyUsageOrCost(i) = 0.0
            case _ => monthlyUsageOrCost(i) = monthlyUsageSum

      case "cost" =>
        for i <- Range(1,13) do
          val monthlyUsageSum = getBillsByCityOrRegion(userType, usageType, cityOrRegion, cityRegion, billList)
            .filter(bill => bill.month == i).foldLeft(0.0)(_ + _.cost) /
            getBillsByCityOrRegion(userType, usageType, cityOrRegion, cityRegion, billList)
              .count(bill => bill.month == i)
          monthlyUsageSum match
            case sum if sum.isNaN => monthlyUsageOrCost(i) = 0.0
            case _ => monthlyUsageOrCost(i) = monthlyUsageSum

    monthlyUsageOrCost


  /*
  Questo metodo privato ritorna semplicemente i risultati dell'analisi su consumi/costi per una certa bolletta
  */
  private def composeUsageOrCostInformation(bill: Bill, requestedInformation: String): String =
    requestedInformation match
      case "cost" =>
         "cost: " + bill.cost + "\nmonth: " + bill.month + "\nyear: " + bill.year+"\n"
      case "usage" =>
          "usage: " + bill.usage + "\nmonth: " + bill.month + "\nyear: " + bill.year+"\n"
