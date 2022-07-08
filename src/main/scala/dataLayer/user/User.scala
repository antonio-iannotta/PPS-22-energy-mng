package dataLayer.user

import applicationLogicLayer.billOperations.BillOperations

import scala.collection.mutable._

class User(private val _userID: String, private val _password: String, private val _userType: String, private val _region: String, private val _city: String):

  def userID: String = _userID

  def region: String = _region

  def userType: String = _userType

  def city: String = _city

  def password: String = _password

  def printUser(): Unit =
    println("UserID: " + userID + "\nRegion: " + region + "\nCity: " + city + "\nUserType: " + userType)


  def getUsageOrCost(usageOrCost: String, usageType: String) =
    BillOperations.getIndividualCostOrUsage(userID, usageType, usageOrCost)

  def makeIndividualPrediction(usageType: String, year: Int): Unit =
    println(BillOperations.makeIndividualPrediction(userID, usageType, year))

  def makePredictionByCity(usageType: String, year: Int, city: String = city): Unit =
    println(BillOperations.makePredictionByLocation(userType, usageType, year, "city", city))

  def makePredictionByRegion(usageType: String, year: Int, region: String = region): Unit =
    println(BillOperations.makePredictionByLocation(userType, usageType, year, "region", region))

  def getUsageOrCostByRegionOrCity(location: String, usageType: String, locationType: String, usageOrCost: String, year: Int): LinkedHashMap[Int, Double] =
    BillOperations.getUsageOrCostByLocation(userType, usageType, location, locationType, usageOrCost, year)
