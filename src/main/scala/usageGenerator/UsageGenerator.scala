package usageGenerator

import io.circe.syntax
import io.circe.syntax.*
import mongoDriver.MongoDB
import org.bson.json.JsonReader
import org.mongodb.scala.{Document, FindObservable, MongoClient, MongoCollection, Observer}
import user.User

import scala.util.Random

object UsageGenerator extends App:

  def generation(): Unit =
    var month = 1
    var year = 1970
    val client: MongoClient = MongoDB.mongoDBConnection("mongodb://localhost:27017")
    val database = client.getDatabase("energymanagement")
    val usagesCollection = database.getCollection("usages")

    usagesCollection.find()

    while true do
      val userListFromDatabase: List[User] = retrieveUsers()
      val usages: List[String] = List("water", "heat", "electricity")
      for user <- userListFromDatabase do
        for usageType <- usages do
          val userUsage = composeUsageString(user,usageType)
          userUsage.asJson

      month += 1
      if (month == 13) then
        month = 1
        year += 1
      Thread.sleep(10000)


  private def composeUsageString(user: User, usageType: String): String =
    val userUsage = "userID: " + user.getUserID() + "\nuserType: " + user.getUserType()
                    + "\ncity: " + user.getCity() + "\nregion: " + user.getRegion()
                    + "\nusageType: " + usageType +"\nusage: " + (Random.nextDouble()/100)
                    + "\ncost: " + (Random.nextDouble()/100)

    userUsage

  private def retrieveUsers(): List[User] =
    val client: MongoClient = MongoDB.mongoDBConnection("mongodb://localhost:27017")
    val database = client.getDatabase("energymanagement")
    val usersCollection = database.getCollection("users")


    //LAVORARE QUI, RIUSCIRE A LEGGERE DATI DA FILE
    val observable = usersCollection.find().printHeadResult()
    /*observable.subscribe( new Observer[Document] {
      def onNext(result: Document): Unit = println(result)
      def onError(e: Throwable): Unit = println("Failed" + e.getMessage)
      def onComplete(): Unit = println("Completed")
    })*/

    val userList: List[User] =
      List(User("1","lombardia","milano","private"),
        User("2","lombardia","bergamo","company"),
        User("3","lombardia","cologno","private"),
        User("4","lombardia","brescia","company"))

    userList


  retrieveUsers()


