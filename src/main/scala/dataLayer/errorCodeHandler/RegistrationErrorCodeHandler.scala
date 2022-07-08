package dataLayer.errorCodeHandler 

object RegistrationErrorCodeHandler extends ErrorCodeHandler :
  /**
   * the following method match to every possible error present in the RegistrationChecker a well defined message
   * @param errorCode
   * @return
   */
  def errorCodeHandler(errorCode: String): String =
    errorCode match
      case "REGISTRATION_USERID_1" => "the field USERID inserted is blank "
      case "REGISTRATION_USERID_2" => "the field USERID inserted is longer than 20 characters "
      case "REGISTRATION_USERID_3" => "the field USERID inserted is less than 6 characters "
      case "REGISTRATION_USERID_4" => "the field USERID inserted is already been used "
      case "REGISTRATION_PASSWORD_1" => "the field PASSWORD inserted is blank "
      case "REGISTRATION_PASSWORD_2" => "the field PASSWORD inserted is longer than 20 characters "
      case "REGISTRATION_PASSWORD_3" => "the field PASSWORD inserted is less than 6 characters "
      case "REGISTRATION_USERTYPE_1" => "the field USERTYPE inserted is not private or company "
      case "REGISTRATION_REGION_1" => "the field REGION inserted is blank "
      case "REGISTRATION_CITY_1" => "the CITY is not present in the region "
      case "REGISTRATION_CITY_2" => "the REGION is not present among the possible regions "
      case _ => "OK"