package errorCodeHandler

object LoginErrorCodeHandler extends ErrorCodeHandler :
  def errorCodeHandler(errorCode: String): String =
    errorCode match
      case "LOGIN_USERID_1" => "Il campo USERID inserito e' vuoto"
      case "LOGIN_PASSWORD_1" => "Il campo PASSWORD inserito e' vuoto"
      case _ => "OK"
