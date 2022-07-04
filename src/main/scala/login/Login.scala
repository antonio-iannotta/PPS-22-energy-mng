package login

import mongoDriver.MongoDB._
import registration.MD5
import user.User

object Login:
  def signIN(userID: String, password: String): Option[User] =
    LoginChecker(userID, password).checkFields(userID, password) match
      case "OK" =>
        Option.apply(retrieveUsers().filter(user => user.getUserID == userID && user.getPassword == MD5.md5HashPassword(password)).head)
      case _ =>
        Option.empty[User]