package login

import mongoDriver.MongoDB._
import registration.MD5
import user.User

object Login:
  def signIN(userID: String, password: String): Option[User] =
    var user: Option[User] = Option.empty
    var checkerResponse: String = ""
    val loginChecker = LoginChecker(userID, password)

    checkerResponse = loginChecker.checkFields(userID, password)
    if checkerResponse == "OK" then
      val userList = retrieveUsers()
      user = Option.apply(userList.filter(user => user.getUserID == userID && user.getPassword == MD5.md5HashPassword(password)).head)

    user