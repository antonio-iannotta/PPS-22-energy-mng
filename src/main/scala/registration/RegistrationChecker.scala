package registration

import registration.ErrorCodeHandler
import com.sun.tools.classfile.Module_attribute.ProvidesEntry
import registration.Regions
import sun.security.util.Password
import scala.collection.mutable.LinkedHashMap

class RegistrationChecker(private val userID: String, private val password: String, private val userType: Int, private val region: String, private val city: String):

  val regionCityMap: LinkedHashMap[String, List[String]] = LinkedHashMap()
  regionCityMap("Abruzzo") = AbruzzoCities
  regionCityMap("Basilicata") = BasilicataCities
  regionCityMap("Calabria") = CalabriaCities
  regionCityMap("Campania") = CampaniaCities
  regionCityMap("EmiliaRomagna") = EmiliaRomagnaCities
  regionCityMap("FVG") = FVGCities
  regionCityMap("Lazio") = LazioCities
  regionCityMap("Liguria") = LiguriaCities
  regionCityMap("Lombardia") = LombardiaCities
  regionCityMap("Marche") = MarcheCities
  regionCityMap("Molise") = MoliseCities
  regionCityMap("Piemonte") = PiemonteCities
  regionCityMap("Puglia") = PugliaCities
  regionCityMap("Sardegna") = SardegnaCities
  regionCityMap("Sicilia") = SiciliaCities
  regionCityMap("Toscana") = ToscanaCities
  regionCityMap("TAA") = TAACities
  regionCityMap("Umbria") = UmbriaCities
  regionCityMap("VAC") = VACities
  regionCityMap("Veneto") = VenetoCities

  def checkFields(userID: String, password: String, userType: Int, region: String, city: String) =

    val userCheck = ErrorCodeHandler.registrationHandler(checkUserID(userID))
    val passwordCheck = ErrorCodeHandler.registrationHandler(checkPassword(password))
    val userTypeCheck = ErrorCodeHandler.registrationHandler(checkUserType(userType))
    val regionCheck = ErrorCodeHandler.registrationHandler(checkRegion(region))
    val cityCheck = ErrorCodeHandler.registrationHandler(checkCity(city, region))
    val checkResponseList = List(userCheck, passwordCheck, userTypeCheck, regionCheck, cityCheck).filter(str => str != "OK")

    if checkResponseList.isEmpty then "OK"
    else checkResponseList.head

  private def checkUserID(userID: String): String =
    userID match
      case id if id.isBlank => "REGISTRATION_USERID_1"
      case id if id.length > 20 => "REGISTRATION_USERID_2"
      case id if id.length < 6 => "REGISTRATION_USERID_3"
      case _ => "OK"

  private def checkPassword(password: String): String =
    password match
      case pwd if pwd.isBlank => "REGISTRATION_PASSWORD_1"
      case pwd if pwd.length > 20 => "REGISTRATION_PASSWORD_2"
      case pwd if pwd.length < 6 => "REGISTRATION_PASSWORD_3"
      case _ => "OK"

  private def checkUserType(userType: Int): String =
    if 0 to 1 contains userType then "OK"
    else "REGISTRATION_USERTYPE_1"

  private def checkRegion(region: String): String =
    region match
      case region if region.isBlank => "REGISTRATION_REGION_1"
      case region if region.length > 25 => "REGISTRATION_REGION_2"
      case _ => "OK"

  private def checkCity(city: String, region: String): String =
    if regionCityMap(region).contains(city) then "OK"
    else "REGISTRATION_CITY_1"