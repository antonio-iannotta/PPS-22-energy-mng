package registration

import mongoDriver.MongoDB
import mongoDriver.Helpers._
import usageGenerator.UsageGenerator._
import registration.MD5.md5HashPassword

import java.math.BigInteger
import java.security.MessageDigest
import registration.RegistrationChecker

object Registration:
  def signUP(userID: String, password: String, userType: Int, region: String, city: String): String =
    var checkerResponse: String = ""
    var registration = ""
    val registrationChecker = RegistrationChecker(userID, password, userType, region, city)

    checkerResponse = registrationChecker.checkFields(userID, password, userType, region, city)
    if checkerResponse == "OK" then
      val users = retrieveUsers()
      if users.filter(user => user.getUserID() == userID).size == 0 then
        registration = "Registration succeeded"
    else
        registration = "Registration failed"

    registration


