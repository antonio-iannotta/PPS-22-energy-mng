package user

class User (val userID: String,val region: String,val city: String,val userType: String){

  /*------Metodi Getter----------*/
  def getUserID(): String ={
    return userID
  }
  def getRegion(): String ={
    return region
  }
  def getUserType(): String ={
    return userType
  }
  def getCity(): String ={
    return city
  }
  /*----------------------------*/

  def getUsage(): Unit ={

  }
}