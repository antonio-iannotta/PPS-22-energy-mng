package dataLayer.registration

import dataLayer.errorCodeHandler.RegistrationErrorCodeHandler
import dataLayer.mongoDriver.MongoDB.{retrieveDataFromCollection, retrieveUsers}
import scala.collection.mutable.{LinkedHashMap, ListBuffer}
import dataLayer.user.User

class RegistrationChecker(private val userID: String, private val password: String, private val userType: Int, private val region: String, private val city: String):

  val regionCityMap: LinkedHashMap[String, List[String]] = LinkedHashMap()
  regionCityMap("Abruzzo") = Regions.AbruzzoCities
  regionCityMap("Basilicata") = Regions.BasilicataCities
  regionCityMap("Calabria") = Regions.CalabriaCities
  regionCityMap("Campania") = Regions.CampaniaCities
  regionCityMap("EmiliaRomagna") = Regions.EmiliaRomagnaCities
  regionCityMap("FVG") = Regions.FVGCities
  regionCityMap("Lazio") = Regions.LazioCities
  regionCityMap("Liguria") = Regions.LiguriaCities
  regionCityMap("Lombardia") = Regions.LombardiaCities
  regionCityMap("Marche") = Regions.MarcheCities
  regionCityMap("Molise") = Regions.MoliseCities
  regionCityMap("Piemonte") = Regions.PiemonteCities
  regionCityMap("Puglia") = Regions.PugliaCities
  regionCityMap("Sardegna") = Regions.SardegnaCities
  regionCityMap("Sicilia") = Regions.SiciliaCities
  regionCityMap("Toscana") = Regions.ToscanaCities
  regionCityMap("TAA") = Regions.TAACities
  regionCityMap("Umbria") = Regions.UmbriaCities
  regionCityMap("VAC") = Regions.VACities
  regionCityMap("Veneto") = Regions.VenetoCities

  /**
   * The following method checks the various fields of the registration
   * @return
   */
  def checkFields(): String =

    val userCheck = RegistrationErrorCodeHandler.errorCodeHandler(checkUserID(userID))
    val passwordCheck = RegistrationErrorCodeHandler.errorCodeHandler(checkPassword(password))
    val userTypeCheck = RegistrationErrorCodeHandler.errorCodeHandler(checkUserType(userType))
    val cityCheck = RegistrationErrorCodeHandler.errorCodeHandler(checkCityRegionMatch(city, region))
    val checkResponseList = List(userCheck, passwordCheck, userTypeCheck, cityCheck).filter(str => str != "OK")

    checkResponseList match
      case response if response.isEmpty => "OK"
      case _ => checkResponseList.head

  /**
   * The following method checks the userID
   * @param userID
   * @return
   */
  private def checkUserID(userID: String): String =
    userID match
      case id if id == "" => "REGISTRATION_USERID_1"
      case id if id.length > 20 => "REGISTRATION_USERID_2"
      case id if id.length < 6 => "REGISTRATION_USERID_3"
      case id if checkDuplicatedUserID(id) => "REGISTRATION_USERID_4"
      case _ => "OK"

  /**
   * The following method checks the password
   * @param password
   * @return
   */
  private def checkPassword(password: String): String =
    password match
      case pwd if pwd == "" => "REGISTRATION_PASSWORD_1"
      case pwd if pwd.length > 20 => "REGISTRATION_PASSWORD_2"
      case pwd if pwd.length < 6 => "REGISTRATION_PASSWORD_3"
      case _ => "OK"

  /**
   * The following method checks the userType 
   * @param userType
   * @return
   */
  private def checkUserType(userType: Int): String =
    if 0 to 1 contains userType then "OK"
    else "REGISTRATION_USERTYPE_1"

  /**
   * The following method checks the match between the cities and their regions
   * @param city
   * @param region
   * @return
   */
  private def checkCityRegionMatch(city: String, region: String): String =
    if regionCityMap.contains(city.toLowerCase.capitalize)then
      if regionCityMap(region.toLowerCase.capitalize).contains(city.toLowerCase.capitalize) then "OK"
      else "REGISTRATION_CITY_1"
    else "REGISTRATION_CITY_2"

  /**
   * The following method checks whether the userID has already been used or not
   * @param userID
   * @return
   */
  private def checkDuplicatedUserID(userID: String): Boolean =
    val users = retrieveDataFromCollection("users").asInstanceOf[ListBuffer[User]]    
    users.exists(user => user.userID == userID)