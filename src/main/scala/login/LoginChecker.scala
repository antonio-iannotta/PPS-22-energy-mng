package login

import user.User
import scala.collection.mutable.LinkedHashMap

class LoginChecker(userID: String, password: String):

  def checkFields(userID: String, password: String): String =
    val userCheck = LoginErrorCodeHandler.errorCodeHandler(checkUserID(userID))
    val passwordCheck = LoginErrorCodeHandler.errorCodeHandler(checkPassword(password))

    val checkResponseList = List(userCheck, passwordCheck).filter(str => str != "OK")

    if checkResponseList.isEmpty then "OK"
    else checkResponseList.head

  private def checkUserID(userID: String): String =
    userID match
      case id if id.isBlank => "USERID_IS_BLANK"
      case _ => "OK"

  private def checkPassword(password: String): String =
    password match
      case userPassword if userPassword.isBlank => "PASSWORD_IS_BLANK"
      case _ => "OK"