package registration

object RegistrationErrorCodeHandler:
  def errorCodeHandler(errorCode: String): String =
    errorCode match
      case "REGISTRATION_USERID_1" => "USERID inserito e' vuoto"
      case "REGISTRATION_USERID_2" => "USERID inserito e' miniore di 6 caratteri"
      case "REGISTRATION_USERID_3" => "USERID inserito e' superiore a 20 caratteri"
      case "REGISTRATION_PASSWORD_1" => "PASSWORD inserito e' vuoto"
      case "REGISTRATION_PASSWORD_2" => "PASSWORD inserito e' miniore di 6 caratteri"
      case "REGISTRATION_PASSWORD_3" => "PASSWORD inserito e' superiore a 20 caratteri"
      case "REGISTRATION_USERTYPE_1" => "USERTYPE inserito non è privato o azienda"
      case "REGISTRATION_REGION_1" => "REGION inserita e' vuoto"
      case "REGISTRATION_CITY_1" => "CITY non presente nella regione"
      case _ => "OK"
