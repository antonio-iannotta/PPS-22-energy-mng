package user

import billOperations.BillOperations

class User (private val _userID: String, private val _password: String, private val _region: String, private val _city: String, private val _userType: String):

  def userID: String = _userID

  def region: String = _region

  def userType: String = _userType

  def city: String = _city

  def password: String = _password

  def printUser(): Unit =
    println("UserID: " + userID + "\nRegion: " + region + "\nCity: " + city +"\nUserType: " + userType)


  def getCost(usageType: String): Unit =
    println(BillOperations.getIndividualCostOrUsage(userID,usageType,"cost"))

  def getUsage(usageType: String): Unit =
    println(BillOperations.getIndividualCostOrUsage(userID,usageType,"usage"))

  def getCostByCity(city: String = city, usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(userType,usageType,city,"city","cost"))

  def getUsageByCity(city: String = city, usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(userType,usageType,city,"city","usage"))

  def getCostByRegion(region: String = region, usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(userType,usageType,region,"region","cost"))

  def getUsageByRegion(region: String = region, usageType: String): Unit =
    println(BillOperations.getUsageOrCostByLocation(userType,usageType,region,"region","usage"))

  def makeIndividualPrediction(usageType: String, year: Int): Unit =
    println(BillOperations.makeIndividualPrediction(userID, usageType, year))

  def makePredictionByCity(usageType: String, year: Int, city: String = city): Unit =
    println(BillOperations.makePredictionByLocation(userType, usageType, year,"city", city))

  def makePredictionByRegion(usageType: String, year: Int, region: String = region): Unit =
    println(BillOperations.makePredictionByLocation(userType, usageType, year,"region", region))