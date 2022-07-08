package dataLayer.login

import dataLayer.mongoDriver.MongoDB._
import dataLayer.registration.MD5
import dataLayer.user.User
import scala.collection.mutable.{LinkedHashMap, ListBuffer}


object Login:
  /**
 * The following function returns an Option[User] which can be fulled with an user or if the login doesn't succed
   * it return an empty option
 * @param userID is the userID target
 * @param password is the user's password
 * @return
 */
  def signIN(userID: String, password: String): Option[User] =
    LoginChecker(userID, password).checkFields(userID, password) match
      case "OK" =>
        Option.apply(retrieveDataFromCollection("users").asInstanceOf[ListBuffer[User]].filter(user => user.userID == userID && user.password == MD5.md5HashPassword(password)).head)
      case _ =>
        Option.empty[User]