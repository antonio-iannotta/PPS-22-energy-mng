package registration

import mongoDriver.MongoDB._
import mongoDriver.Helpers._
import usageGenerator.UsageGenerator._
import registration.MD5.md5HashPassword
import user.User

import java.math.BigInteger
import java.security.MessageDigest
import registration.RegistrationChecker

object Registration extends App:
  def signUP(userID: String, password: String, userType: Int, region: String, city: String): String =
    var checkerResponse: String = ""
    var registration = ""
    var userTypeString = ""
    val registrationChecker = RegistrationChecker(userID, password, userType, region, city)

    checkerResponse = registrationChecker.checkFields(userID, password, userType, region, city)
    if checkerResponse == "OK" then
      //val users = retrieveUsers("users")
      //if !users.exists(user => user.getUserID() == userID) then
      userType match
        case 0 => userTypeString = "private"
        case _ => userTypeString = "company"

      addUser(User(userID, MD5.md5HashPassword(password), userTypeString, region, city))
      registration = "L'utente è stato inserito correttamente"
    else
      registration = checkerResponse

    registration

  println(signUP("andreacato","password",0,"Lombardia","Milano"))
