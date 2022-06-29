package registration

import mongoDriver.MongoDB._
import mongoDriver.Helpers._
import usageGenerator.UsageGenerator._
import registration.MD5.md5HashPassword
import user.User

import java.math.BigInteger
import java.security.MessageDigest
import registration.RegistrationChecker

object Registration:
  def signUP(userID: String, password: String, userType: Int, region: String, city: String): String =
    var checkerResponse: String = ""
    var registration = ""
    var userTypeString = ""
    val registrationChecker = RegistrationChecker(userID, password, userType, region, city)

    checkerResponse = registrationChecker.checkFields(userID, password, userType, region, city)
    if checkerResponse == "OK" then
      val users = retrieveUsers("users")
      if !users.exists(user => user.getUserID() == userID) then
        if userType == 0 then
          userTypeString = "private"
        else
          userTypeString = "company"

        val user = User(userID, password, userTypeString, region, city)
        addUser(user)
    else
        registration = "Registration failed"

    registration


