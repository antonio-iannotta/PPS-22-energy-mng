package dataLayer.login

import dataLayer.mongoDriver.MongoDB._
import dataLayer.registration.MD5
import dataLayer.user.User

object Login:
  def signIN(userID: String, password: String): Option[User] =
    LoginChecker(userID, password).checkFields(userID, password) match
      case "OK" =>
        Option.apply(retrieveUsers().filter(user => user.userID == userID && user.password == MD5.md5HashPassword(password)).head)
      case _ =>
        Option.empty[User]