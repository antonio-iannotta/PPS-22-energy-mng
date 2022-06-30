package login

import mongoDriver.MongoDB._
import registration.MD5
import user.User

object Login extends App:
  def signIN(userID: String, password: String): User =
    var user: User = null
    var checkerResponse: String = ""
    var resultSignInOperation = ""
    val loginChecker = LoginChecker(userID, password)
    
    checkerResponse = loginChecker.checkFields(userID, password)
    if checkerResponse == "OK" then
      val userList = retrieveUsers()
      user = userList.filter(user => user.getUserID() == userID && user.getPassword() == MD5.md5HashPassword(password)).head

    user
    
  signIN("andreacato", "password")