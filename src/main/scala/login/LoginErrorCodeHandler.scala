package login

import `trait`.ErrorCodeHandler

object LoginErrorCodeHandler extends ErrorCodeHandler:
  def errorCodeHandler(errorCode: String): String =
    errorCode match
      case "USERID_IS_BLANK" => "Il campo USERID inserito e' vuoto"
      case "PASSWORD_IS_BLANK" => "Il campo PASSWORD inserito e' vuoto"
      case _ => "OK"