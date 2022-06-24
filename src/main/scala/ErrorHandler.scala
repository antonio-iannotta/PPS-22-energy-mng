object ErrorHandler:

  def registrationHandler(errorCode: String): String =
    errorCode match
      case "REGISTRATION_USERID_1" => "USERID_1"
      case "REGISTRATION_USERID_2" => "USERID_2"
      case "REGISTRATION_USERID_3" => "USERID_3"
      case "REGISTRATION_PASSWORD_1" => "PASSWORD_1"
      case "REGISTRATION_PASSWORD_2" => "PASSWORD_2"
      case "REGISTRATION_PASSWORD_3" => "PASSWORD_3"
      case "REGISTRATION_USERTYPE_1" => "USERTYPE_1"
      case "REGISTRATION_REGION_1" => "REGION_1"
      case "REGISTRATION_REGION_2" => "REGION_2"
      case "REGISTRATION_CITY_1" => "CITY_1"
      case _ => "OK"
