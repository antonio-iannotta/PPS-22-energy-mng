package login

import user.User

object Login:
  def singIN(userID: String, password: String): String =
    var checkerResponse: String = ""
    val loginChecker = LoginChecker(userID, password)


    checkerResponse = loginChecker.checkFields(userID, password)
    if checkerResponse == "OK" then
      "OK"//connessione db da fare
    else checkerResponse