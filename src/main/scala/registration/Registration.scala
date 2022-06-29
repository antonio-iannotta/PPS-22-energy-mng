package registration

import mongoDriver.MongoDB._
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
      val users = retrieveUsers("users")
      if !users.exists(user => user.getUserID() == userID) then
        val user = User(userID, password, userType, region, city)
        addUser(user)
    else
        registration = "Registration failed"

    registration


