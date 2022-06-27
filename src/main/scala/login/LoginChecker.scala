package login

import errorCodeHandler.ErrorCodeHandler
import user.User

import scala.collection.mutable.LinkedHashMap

class LoginChecker(userID: String, password: String) extends ErrorCodeHandler:

  def checkFields(userID: String, password: String): String =
    val userCheck = errorCodeHandler(checkUserID(userID))
    val passwordCheck = errorCodeHandler(checkPassword(password))

    val checkResponseList = List(userCheck, passwordCheck).filter(str => str != "OK")

    if checkResponseList.isEmpty then "OK"
    else checkResponseList.head

  private def checkUserID(userID: String): String =
    userID match
      case login_unsuccessful if userID.isEmpty => "USERID_IS_BLANK"
      case _ => "OK"

  private def checkPassword(password: String): String =
    password match
      case login_unsuccessful if password.isEmpty => "PASSWORD_IS_BLANK"
      case _ => "OK"