package usageGenerator

import org.mongodb.scala._
import io.circe.syntax.*
import user.User
import scala.util.Random

object UsageGenerator:

  def generation(): Unit =
    var month = 1
    var year = 1970

    while true do
      val userListFromDatabase: List[User] = retrieveUsers()
      val usages: List[String] = List("water", "heat", "electricity")
      for user <- userListFromDatabase do
        for usageType <- usages do
          val userUsage = composeUsageString(user,usageType)
          userUsage.asJson //trasforma una stringa in json
          //invia dati db

      month += 1
      if (month == 13) then
        month = 1
        year += 1
      Thread.sleep(10000)


  private def composeUsageString(user: User, usageType: String): String =
    /*val userUsage = "userID: " + user.getUserID() + "\nuserType: " + user.getUserType()
                    + "\ncity: " + user.getCity() + "\nregion: " + user.getRegion()
                    + "\nusageType: " + usageType +"\nusage: " + (Random.nextDouble()/100)
                    + "\ncost: " + (Random.nextDouble()/100)

    userUsage*/
    val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")

  private def retrieveUsers(): List[User] =
    val userList: List[User] =
      List(User("1","lombardia","milano","private"),
        User("2","lombardia","bergamo","company"),
        User("3","lombardia","cologno","private"),
        User("4","lombardia","brescia","company"))

    userList



