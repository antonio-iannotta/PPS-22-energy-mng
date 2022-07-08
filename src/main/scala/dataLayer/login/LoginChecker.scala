package dataLayer.login

import dataLayer.errorCodeHandler.LoginErrorCodeHandler
import dataLayer.user.User

import scala.collection.mutable.LinkedHashMap

class LoginChecker(userID: String, password: String):
  /**
   * The following function returns a string which contain an Error code if the field check go wrong or an OK
   * message if the field check go right
   * @param userID is the userID target
   * @param password is the user password
   * @return
   */
  def checkFields(userID: String, password: String): String =
    val userCheck = LoginErrorCodeHandler.errorCodeHandler(checkUserID(userID))
    val passwordCheck = LoginErrorCodeHandler.errorCodeHandler(checkPassword(password))
    val checkResponseList = List(userCheck, passwordCheck).filter(str => str != "OK")

    if checkResponseList.isEmpty then "OK"
    else checkResponseList.head

  /**
   * The following function returns a string which contain an Error code if the user check go wrong or an OK
   * message if the user check go right
   * @param userID is the userID target
   * @return
   */
  private def checkUserID(userID: String): String =
    userID match
      case id if id.isBlank => "LOGIN_USERID_1"
      case _ => "OK"

  /**
   * The following function returns a string which contain an Error code if the password check go wrong or an OK
   * message if the user password go right
   * @param password is the user password
   * @return
   */
  private def checkPassword(password: String): String =
    password match
      case userPassword if userPassword.isBlank => "LOGIN_PASSWORD_1"
      case _ => "OK"
