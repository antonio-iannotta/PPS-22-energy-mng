package registration

import registration.MD5.md5HashPassword
import java.math.BigInteger
import java.security.MessageDigest
import registration.RegistrationChecker

object Registration:
  def signUP(userID: String, password: String, userType: Int, region: String, city: String): String =
    var checkerResponse: String = ""
    val registrationChecker = RegistrationChecker(userID, password, userType, region, city)

    checkerResponse = registrationChecker.checkFields(userID, password, userType, region, city)
    if checkerResponse == "OK" then
      "OK"//connessione db da fare
    else checkerResponse



