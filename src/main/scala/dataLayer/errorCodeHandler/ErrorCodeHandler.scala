package dataLayer.errorCodeHandler

trait ErrorCodeHandler:
  def errorCodeHandler(errorCode: String): String
