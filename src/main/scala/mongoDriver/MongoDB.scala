package mongoDriver

import org.mongodb.scala.*
import org.mongodb.scala.bson.BsonString
import user.User

import scala.collection.mutable._
import mongoDriver.Helpers.*

import scala.collection.mutable

object MongoDB:

  def mongoDBConnection(): MongoDatabase =
    MongoClient("mongodb://localhost:27017").getDatabase("energymanagement")

  def retrieveUsers(collectionName: String): ListBuffer[User] =
    val usersCollection = MongoDB.mongoDBConnection().getCollection(collectionName)
    val usersList: ListBuffer[User] = ListBuffer()
    val registeredUsers = usersCollection.find().results()

    for user <- registeredUsers do
      var userFields: ListBuffer[String] = ListBuffer()
      for userField <- user do
        if userField._2.isInstanceOf[BsonString] then
          userFields += userField._2.asString().getValue

      usersList += createUser(userFields)

    usersList

  def addUser(user: User): String =
    val document = Document(composeUser(user))
    mongoDBConnection().getCollection("users").insertOne(document).results()


  private def createUser(users: ListBuffer[String]): User =
    val userID: String = users(0)
    val password: String = users(1)
    val region: String = users(2)
    val city: String = users(3)
    val userType: String = users(4)

    User(userID,password,region,city,userType)



  private def composeUser(user: User): LinkedHashMap[String, BsonString] =
    val userMap: LinkedHashMap[String, BsonString] = LinkedHashMap()
    userMap("userID") = BsonString.apply(user.getUserID())
    userMap("password") = BsonString.apply(user.getPassword())
    userMap("region") = BsonString.apply(user.getRegion())
    userMap("city") = BsonString.apply(user.getCity())
    userMap("userType") = BsonString.apply(user.getUserType())

    userMap



