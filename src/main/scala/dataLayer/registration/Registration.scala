package dataLayer.registration

import dataLayer.mongoDriver.{MongoDB, Helpers}
import dataLayer.registration.{MD5, RegistrationChecker}
import dataLayer.user.User
import java.math.BigInteger
import java.security.MessageDigest


object Registration:
  /**
   * Il seguente metodo permette di svolgere l’operazione di registrazione
   * @param userID
   * @param password
   * @param userType
   * @param region
   * @param city
   * @return
   */
  def signUP(userID: String, password: String, userType: Int, region: String, city: String): String =
    val responseMessage = RegistrationChecker(userID, password, userType, region, city).checkFields()
    responseMessage match
      case "OK" =>
        userType match
          case 0 =>
            MongoDB.addUser(User(userID, MD5.md5HashPassword(password), "private", region.toLowerCase(), city.toLowerCase()))
          case 1 =>
            MongoDB.addUser(User(userID, MD5.md5HashPassword(password), "company", region.toLowerCase(), city.toLowerCase()))
        responseMessage
      case _ =>
        responseMessage
