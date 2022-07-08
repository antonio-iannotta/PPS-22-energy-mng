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


  /**
   * The following function returns the cost or usage related to a certain userID and a certain user type
   * @param usageOrCost is the variable that contains the string which specify if we want see usage or cost
   * @param usageType is the variable thta contains the string which specify which usage we want visualize
   * @return
   */
  def getUsageOrCost(usageOrCost: String, usageType: String) =
    BillOperations.getIndividualCostOrUsage(userID, usageType, usageOrCost)


  /**
   * The following function returns the prediction releted to a certain userID and a certain user type and a certain year
   * @param usageType is the variable thta contains the string which specify which usage we want visualize
   * @param year is the variable that specify the year of the prediction
   * @return
   */
  def makeIndividualPrediction(usageType: String, year: Int): Unit =
    println(BillOperations.makeIndividualPrediction(userID, usageType, year))


  /**
   * The following function returns the prediction releted to a certain city and a certain user type and a certain year
   * @param usageType is the variable thta contains the string which specify which usage we want visualize
   * @param city is the variable which contain the name of the city
   * @param year is the variable that specify the year of the prediction
   * @return
   */
  def makePredictionByCity(usageType: String, year: Int, city: String = city): Unit =
    println(BillOperations.makePredictionByLocation(userType, usageType, year, "city", city))


  /**
   * The following function returns the prediction releted to a certain city and a certain user type and a certain year
   * @param usageType is the variable thta contains the string which specify which usage we want visualize
   * @param region is the variable which contain the name of the region
   * @param year is the variable that specify the year of the prediction
   * @return
   */
  def makePredictionByRegion(usageType: String, year: Int, region: String = region): Unit =
    println(BillOperations.makePredictionByLocation(userType, usageType, year, "region", region))


  /**
   * The following function returns a LinkedHashMap[Int, Double] which contians the cost or the usage releted to a certain city or to a certain region and to a cetain year
   * @param location is the variable whihc indicates the location ex(name of the city or name of the region)
   * @param usageType is the variable thta contains the string which specify which usage we want visualize
   * @param locationType is the variable which contain the kind of location ex(city or region)
   * @param usageOrCost is the variable which indicates if we want to get the usages or the cost related
   * @param year is the variable that specify the year of the prediction
   * @return
   */
  def getUsageOrCostByRegionOrCity(location: String, usageType: String, locationType: String, usageOrCost: String, year: Int): LinkedHashMap[Int, Double] =
    BillOperations.getUsageOrCostByLocation(userType, usageType, location, locationType, usageOrCost, year)
