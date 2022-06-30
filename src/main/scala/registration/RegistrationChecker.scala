package registration

import errorCodeHandler.RegistrationErrorCodeHandler
import mongoDriver.MongoDB.retrieveUsers

import scala.collection.mutable.LinkedHashMap

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

  def checkFields(userID: String, password: String, userType: Int, region: String, city: String): String =

    val userCheck = RegistrationErrorCodeHandler.errorCodeHandler(checkUserID(userID))
    val passwordCheck = RegistrationErrorCodeHandler.errorCodeHandler(checkPassword(password))
    val userTypeCheck = RegistrationErrorCodeHandler.errorCodeHandler(checkUserType(userType))
    val cityCheck = RegistrationErrorCodeHandler.errorCodeHandler(checkCityRegionMatch(city, region))
    val checkResponseList = List(userCheck, passwordCheck, userTypeCheck, cityCheck).filter(str => str != "OK")

    if checkResponseList.isEmpty then "OK"
    else checkResponseList.head

  private def checkUserID(userID: String): String =
    userID match
      case id if id == "" => "REGISTRATION_USERID_1"
      case id if id.length > 20 => "REGISTRATION_USERID_2"
      case id if id.length < 6 => "REGISTRATION_USERID_3"
      case id if checkDuplicatedUserID(id) => "REGISTRATION_USERID_4"
      case _ => "OK"

  private def checkPassword(password: String): String =
    password match
      case pwd if pwd == "" => "REGISTRATION_PASSWORD_1"
      case pwd if pwd.length > 20 => "REGISTRATION_PASSWORD_2"
      case pwd if pwd.length < 6 => "REGISTRATION_PASSWORD_3"
      case _ => "OK"

  private def checkUserType(userType: Int): String =
    if 0 to 1 contains userType then "OK"
    else "REGISTRATION_USERTYPE_1"


  private def checkCityRegionMatch(city: String, region: String): String =
    if regionCityMap(region.toLowerCase.capitalize).contains(city.toLowerCase.capitalize) then "OK"
    else "REGISTRATION_CITY_1"
  
  private def checkDuplicatedUserID(userID: String): Boolean =
    val users = retrieveUsers()
    users.exists(user => user.getUserID() == userID)
      