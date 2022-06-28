package login

object LoginErrorCodeHandler:
  def errorCodeHandler(errorCode: String): String =
    errorCode match
      case "USERID_IS_BLANK" => "USERID inserito e' vuoto"
      case "PASSWORD_IS_BLANK" => "PASSWORD inserito e' vuoto"
      case _ => "OK"