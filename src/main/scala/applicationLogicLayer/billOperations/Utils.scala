package applicationLogicLayer.billOperations


import dataLayer.bill.Bill

import scala.collection.mutable
import scala.collection.mutable.{LinkedHashMap, ListBuffer}
import scala.util.Random

object Utils:

  /**
   * Il seguente metodo prende ritorna il risultato della previsione relativa allo specifico anno e ad una specifica utenza sottoforma di stringa.
   * @param year
   * @param yearMap
   * @param usageVariation
   * @param costVariation
   * @param usageType
   * @return
   */
  def predictionResult(year: Int, yearMap: LinkedHashMap[Int, Double], averageUsage: Double, averageCost: Double, usageType: String): String =
    println("STAMPA QUI")
    println(yearMap)
    year - yearMap.keys.head match
      case duration if duration <= yearMap.keys.size =>
        "Your usage and cost for " + usageType + " is not supposed to change for " + year
      case duration if duration > yearMap.keys.size =>
        s"Year: ${year}\nPredicted usage variation: ${averageUsage - (averageUsage - Random.between(-1.0, 1.0) * duration)}\nPredicted cost variation: ${averageCost - (averageCost - Random.between(-1.0, 1.0) * duration)}"


  /**
   * Il seguente metodo ritorna la media dei valori calcolati su una certa mappa [Int, Double].
   * @param map
   * @return
   */
  def average(map: LinkedHashMap[Int, Double]): Double =
    map.values.foldLeft(0.0)(_ + _) / map.keys.size


  /**
   * Il seguente metodo inizializza una mappa [Int, Double] con valori 0.0 sulla base delle bollette presenti nel sistema che hanno un certo userID ed una certa tipologia di consumo
   * @param individualMap
   * @param userID
   * @param usageType
   * @param billList
   */
  def individualMapInitialization(individualMap: LinkedHashMap[Int, Double], userID: String, usageType: String, billList: ListBuffer[Bill]): Unit =
    billList.filter(bill => bill.userID == userID && bill.usageType == usageType).foreach(bill => individualMap(bill.year) = 0.0)

  /**
   * Il seguente metodo inizializza una mappa [Int, Double] con valori 0.0 sulla base di una specifica città o regione inserita, di una specifica tipologia di utente e di una specifica tipologia di consumo
   * @param mapByLocation
   * @param userType
   * @param usageType
   * @param locationType
   * @param location
   * @param billList
   */
  def initializationMapByLocation(mapByLocation: LinkedHashMap[Int, Double], userType: String, usageType: String, locationType: String, location: String, billList: ListBuffer[Bill]): Unit =
    locationType match
      case "city" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.city == location)
          .foreach(bill => mapByLocation(bill.year) = 0.0)
      case "region" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.region == location)
          .foreach(bill => mapByLocation(bill.year) = 0.0)

  /**
   * Il seguente metodo riempie una mappa [Int, Double] con la media dei consumi o dei costi annuali per ogni anno memorizzato all'interno del sistema relativi ad uno specifico utente e ad ima specifica tipologia di consumi
   * @param individualMap
   * @param usageOrCost
   * @param userID
   * @param usageType
   * @param billList
   */
  def fillIndividualUsageCostMap(individualMap: LinkedHashMap[Int, Double], usageOrCost: String, userID: String, usageType: String, billList: ListBuffer[Bill]): Unit =
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
      case _ => println("Error!")

  /**
   * Il seguente metodo riempie una mappa [Int, Double] con la media dei consumi o dei costi annuali per ogni anno memorizzato all'interno del sistema relativi ad una specifica tipologia di utente, di consumi, e una specifica
   * città o regione
   * @param usageCostMap
   * @param usageType
   * @param usageOrCost
   * @param userType
   * @param locationType
   * @param location
   * @param billList
   */
  def fillUsageCostMapByLocation(usageCostMap: LinkedHashMap[Int, Double], usageType: String, usageOrCost: String, userType: String, locationType: String, location: String, billList: ListBuffer[Bill]): Unit =
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
   *Il seguente metodo ritorna una lista di bollette associata ad un certo userID per una certa tipologia di consumi
   * @param userID
   * @param usageType
   * @param billList
   * @return
   */
  def getBillsByUserIDAndUsageType(userID: String, usageType: String, billList: ListBuffer[Bill]): ListBuffer[Bill] =
    billList.filter(bill => bill.userID == userID && bill.usageType == usageType)


  /**
   * Il seguente metodo ritorna una lista di bollette associata ad una specifica città o regione, ad una specifica tipologia di utente e ad una specifica tipologia di consumi
   * @param userType
   * @param usageType
   * @param locationType
   * @param location
   * @param billList
   * @return
   */
  def getBillsByCityOrRegion(userType: String, usageType: String, locationType: String, location: String, billList: ListBuffer[Bill]): ListBuffer[Bill] =
    locationType match
      case "city" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.city == location)
      case "region" =>
        billList.filter(bill => bill.userType == userType && bill.usageType == usageType && bill.region == location)

  /**
   * Il seguente metodo ritorna una mappa [Int, Double] in cui ad ogni mese è associata la media dei consumi o dei costi relativi ad una certa tipologia di utente, una certa tipologia di consumi e una certa città o regione.
   * @param userType
   * @param usageType
   * @param locationType
   * @param location
   * @param usageOrCost
   * @param billList
   * @param year
   * @return
   */
  def monthlyUsageOrCost(userType: String, usageType: String, locationType: String, location: String, usageOrCost: String, billList: ListBuffer[Bill], year: Int): LinkedHashMap[Int, Double] =
    val monthlyUsageOrCost: LinkedHashMap[Int, Double] = LinkedHashMap()
    usageOrCost match
      case "usage" =>
        for month <- Range(1,13) do
          val monthlyAverageUsage = getMonthlyAverageUsageOrCost(userType, usageType, locationType, location, billList, usageOrCost, year, month)
          monthlyAverageUsage match
            case sum if sum.isNaN => monthlyUsageOrCost(month) = 0.0
            case _ => monthlyUsageOrCost(month) = monthlyAverageUsage

      case "cost" =>
        for month <- Range(1,13) do
          val monthlyAverageCost = getMonthlyAverageUsageOrCost(userType, usageType, locationType, location, billList, usageOrCost, year, month)
          monthlyAverageCost match
            case sum if sum.isNaN => monthlyUsageOrCost(month) = 0.0
            case _ => monthlyUsageOrCost(month) = monthlyAverageCost

    monthlyUsageOrCost


  /**
   * Il seguente metodo ritorna la media mensile di consumi o costi relativi ad un certo anno, ad una certa tipologia di utente, ad una certa città o regione e ad un certo anno
   * @param userType
   * @param usageType
   * @param locationType
   * @param location
   * @param billList
   * @param usageOrCost
   * @param year
   * @param month
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
   * Il seguente metodo compone e restituisce sottoforma di stringa le informazioni relative al costo o al consumo associati ad una certa bolletta.
   * @param bill
   * @param requestedInformation
   * @return
   */
  def composeUsageOrCostInformation(bill: Bill, requestedInformation: String): String =
    requestedInformation match
      case "cost" =>
        "cost: " + bill.cost + "\nmonth: " + bill.month + "\nyear: " + bill.year+"\n"
      case "usage" =>
        "usage: " + bill.usage + "\nmonth: " + bill.month + "\nyear: " + bill.year+"\n"