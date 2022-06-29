package registration

import `trait`.ErrorCodeHandler

object RegistrationErrorCodeHandler extends ErrorCodeHandler:
  def errorCodeHandler(errorCode: String): String =
    errorCode match
      case "REGISTRATION_USERID_1" => "Il campo USERID inserito e' vuoto"
      case "REGISTRATION_USERID_2" => "Il campo USERID inserito e' superiore a 20 caratteri"
      case "REGISTRATION_USERID_3" => "Il campo USERID inserito e' minore di 6 caratteri"
      case "REGISTRATION_PASSWORD_1" => "Il campo PASSWORD inserito e' vuoto"
      case "REGISTRATION_PASSWORD_2" => "Il campo PASSWORD inserito e' minore di 6 caratteri"
      case "REGISTRATION_PASSWORD_3" => "Il campo PASSWORD inserito e' superiore a 20 caratteri"
      case "REGISTRATION_USERTYPE_1" => "Il campo USERTYPE inserito non è privato o azienda"
      case "REGISTRATION_REGION_1" => "Il campo REGION inserito e' vuoto"
      case "REGISTRATION_CITY_1" => "Il campo CITY non presente nella regione"
      case _ => "OK"
