package user

class User (val userID: String, val password: String, val region: String,val city: String,val userType: String):

  /*------Metodi Getter----------*/
  def getUserID(): String = userID
  def getRegion(): String = region
  def getUserType(): String = userType
  def getCity(): String = city
  def getPassword(): String = password
  /*----------------------------*/

  def printUser(): Unit =
    println("UserID: " + getUserID() + "\nRegion: " + getRegion() + "\nCity: " + getCity() +"\nUserType: " + getUserType())

