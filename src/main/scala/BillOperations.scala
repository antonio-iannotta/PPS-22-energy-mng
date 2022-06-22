import scala.collection.mutable.Map

object BillOperations:
  var list = List(
    UserBill("234","AntonioIannotta","private","water",234.65,89.6,
      2,2022,"Cesena","Emilia-Romagna"),

    UserBill("235","AndreaCatani","company","heat",856.65,290.6,
      2,2022,"Bologna","Emilia-Romagna"),
    UserBill("236","CarloDiRaddo","private","electric",100.93,120.0,
      5,2021,"Roma","Lazio"),
    UserBill("237","DemetrioAndriani","company","heat",324.56,96.8,
      7,2022,"Milano","Lombardia"),
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
      result = "usage: " + bill.getCost() +
        "-month: " + bill.getMonth() +
        "-year: " + bill.getYear() + "\n"

    result

  def getUsageByLocation(userType: String, usageType: String, location: String, locationType: String): String =
    var result = ""
    val monthlyUsage: Map[Int, Double] = Map()
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
    val monthlyUsage: Map[Int, Double] = Map()
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
    "makeIndividualPrediction"

  def makePredictionByLocation(userID: String, userType: String, usageType: String, year: Int, locationType: String, location: String): String =
    "makePredictionByLocation"
