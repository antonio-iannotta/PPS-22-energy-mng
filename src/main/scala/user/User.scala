import billOperations.BillOperations

class User (private val _userID: String, private val _password: String, private val _region: String, private val _city: String, private val _userType: String):

  def getUserID: String = _userID

  def getRegion: String = _region

  def getUserType: String = _userType

  def getCity: String = _city

  def getPassword: String = _password

  def printUser(): Unit =
    println("UserID: " + getUserID + "\nRegion: " + getRegion + "\nCity: " + getCity +"\nUserType: " + getUserType)


  def getCost(usageType: String): Unit =
    println(BillOperations.getIndividualCostOrUsage(getUserID,usageType,"cost"))

  def getUsage(usageType: String): Unit =
    println(BillOperations.getIndividualCostOrUsage(getUserID,usageType,"usage"))

  def getCostByCity(city: String = getCity, usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(getUserType,usageType,city,"city","cost"))

  def getUsageByCity(city: String = getCity, usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(getUserType,usageType,city,"city","usage"))

  def getCostByRegion(region: String = getRegion, usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(getUserType,usageType,region,"region","cost"))

  def getUsageByRegion(region: String = getRegion, usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(getUserType,usageType,region,"region","usage"))

  def makeIndividualPrediction(usageType: String, year: Int): Unit =
    println(BillOperations.makeIndividualPrediction(getUserID, usageType, year))

  def makePredictionByCity(usageType: String, year: Int, city: String = getCity): Unit =
    println(BillOperations.makePredictionByLocation(getUserType, usageType, year,"city", city))

  def makePredictionByRegion(usageType: String, year: Int, region: String = getRegion): Unit =
    println(BillOperations.makePredictionByLocation(getUserType, usageType, year,"region", region))

