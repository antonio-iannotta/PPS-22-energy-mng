package registration

import mongoDriver.{MongoDB, Helpers}
import registration.MD5.md5HashPassword
import user.User

import java.math.BigInteger
import java.security.MessageDigest
import registration.{MD5, RegistrationChecker}

object Registration:
  def signUP(userID: String, password: String, userType: Int, region: String, city: String): String =
    RegistrationChecker(userID, password, userType, region, city).checkFields(userID, password, userType, region, city) match
      case "OK" =>
        userType match
          case 0 =>
            MongoDB.addUser(User(userID, MD5.md5HashPassword(password), "private", region, city))
          case 1 =>
            MongoDB.addUser(User(userID, MD5.md5HashPassword(password), "company", region, city))

      case _ =>
        RegistrationChecker(userID, password, userType, region, city).checkFields(userID, password, userType, region, city)

