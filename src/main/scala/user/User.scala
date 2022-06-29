package user

import billOperations.BillOperations

class User (val userID: String, val password: String, val region: String,val city: String,val userType: String):
  
  def getUserID(): String = userID
  
  def getRegion(): String = region
  
  def getUserType(): String = userType
  
  def getCity(): String = city
  
  def getPassword(): String = password

  def printUser(): Unit =
    println("UserID: " + getUserID() + "\nRegion: " + getRegion() + "\nCity: " + getCity() +"\nUserType: " + getUserType())


  def getCost(usageType: String): Unit =
    println(BillOperations.getIndividualCostOrUsage(getUserID(),usageType,"cost"))

  def getUsage(usageType: String): Unit =
    println(BillOperations.getIndividualCostOrUsage(getUserID(),usageType,"usage"))

  def getCostByCity(city: String = getCity(), usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(getUserType(),usageType,city,"city","cost"))

  def getUsageByCity(city: String = getCity(), usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(getUserType(),usageType,city,"city","usage"))

  def getCostByRegion(region: String = getRegion(), usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(getUserType(),usageType,region,"region","cost"))

  def getUsageByRegion(region: String = getRegion(), usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(getUserType(),usageType,region,"region","usage"))
    
  def makeIndividualPrediction(usageType: String, year: Int): Unit =
    println(BillOperations.makeIndividualPrediction(getUserID(), usageType, year))
    
  def makePredictionByCity(usageType: String, year: Int, city: String = getCity()): Unit =
    println(BillOperations.makePredictionByLocation(getUserType(), usageType, year,"city", city))
    
  def makePredictionByRegion(usageType: String, year: Int, region: String = getRegion()): Unit =
    println(BillOperations.makePredictionByLocation(getUserType(), usageType, year,"region", region))


