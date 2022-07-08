package dataLayer.mongoDriver

import org.mongodb.scala.*
import org.mongodb.scala.bson.BsonString

import java.time.{LocalDate, LocalDateTime}
import dataLayer.bill.Bill
import dataLayer.user.User

import scala.collection.mutable.{LinkedHashMap, ListBuffer}
import scala.util.Random
import dataLayer.mongoDriver.Helpers.*

import scala.collection.mutable

object MongoDB:

  /**
   * The following function returns an instance of MongoDatabase
   * @return
   */
  def mongoDBConnection(): MongoDatabase =
    MongoClient("mongodb://localhost:27017").getDatabase("energymanagement")





  /**
   * The following function returns the data stored in a certain collections provided as argument, organized as ListBuffer
   * @param collection is the name of collection of interest
   * @return
   */
  def retrieveDataFromCollection(collection: String): ListBuffer[AnyRef] =
    val retrievedData: ListBuffer[AnyRef] = ListBuffer()
    collection match
      case "users" =>
        MongoDB.mongoDBConnection().getCollection(collection)
          .find().results()
          .foreach(user => retrievedData += createUser(user.foldLeft[ListBuffer[AnyRef]](ListBuffer())(_ += _._2.asInstanceOf[AnyRef])))
      case "usages" =>
        MongoDB.mongoDBConnection().getCollection(collection)
          .find().results()
          .foreach(user => retrievedData += createBill(user.foldLeft[ListBuffer[AnyRef]](ListBuffer())(_ += _._2.asInstanceOf[AnyRef])))

    retrievedData





  /**
   * The following function create a Bill starting from a list of usages retrieved from the database
   * @param usages is the list of information retrieved from the collection "usages"
   * @return
   */
  def createBill(usages: ListBuffer[AnyRef]): Bill =

    val billID: String = LocalDateTime.now().toString
    val userID: String = usages(1).asInstanceOf[BsonString].asString().getValue
    val userType: String = usages(2).asInstanceOf[BsonString].asString().getValue
    val city: String = usages(3).asInstanceOf[BsonString].asString().getValue
    val region: String = usages(4).asInstanceOf[BsonString].asString().getValue
    val usageType: String = usages(5).asInstanceOf[BsonString].asString().getValue
    val usage = usages(6).asInstanceOf[BsonString].asString().getValue.toDouble
    val cost = usages(7).asInstanceOf[BsonString].asString().getValue.toDouble
    val month = usages(8).asInstanceOf[BsonString].asString().getValue.toInt
    val year = usages(9).asInstanceOf[BsonString].asString().getValue.toInt

    Bill(billID,userID,userType,usageType,usage,cost,month,year,city,region)





  /**
   * The following function adds a user to the collection "users"
   * @param user is the user to be added
   * @return
   */
  def addUser(user: User): String =

    val document = Document(composeUserMap(user))
    mongoDBConnection().getCollection("users").insertOne(document).results()





  /**
   * The following function create a User starting from a list of users retrieved from the database
   * @param users is the list of information retrieved from the collection "users"
   * @return
   */
  def createUser(users: ListBuffer[AnyRef]): User =

    val userID: String = users(1).asInstanceOf[BsonString].asString().getValue
    val password: String = users(2).asInstanceOf[BsonString].asString().getValue
    val userType: String = users(3).asInstanceOf[BsonString].asString().getValue
    val region: String = users(4).asInstanceOf[BsonString].asString().getValue
    val city: String = users(5).asInstanceOf[BsonString].asString().getValue

    User(userID,password,userType,region,city)





  /**
   * The following function returns a map used to compose a document to be add a user to collection "users"
   * @param user is the user used to create the map
   * @return
   */
  def composeUserMap(user: User): mutable.LinkedHashMap[String, BsonString] =

    val userMap: mutable.LinkedHashMap[String, BsonString] = mutable.LinkedHashMap()

    userMap("userID") = BsonString.apply(user.userID)
    userMap("password") = BsonString.apply(user.password)
    userMap("userType") = BsonString.apply(user.userType)
    userMap("region") = BsonString.apply(user.region)
    userMap("city") = BsonString.apply(user.city)

    userMap





  /**
   * The following function returns a map used to compose a document to be add a Bill to collection "usages"
   * @param user is the user needed to retrieve user information
   * @param usageType is the usage type
   * @param month is the month of the bill
   * @param year is the year of the bill
   * @return
   */
  def composeUsageMap(user: User, usageType: String, month: Int, year: Int): mutable.LinkedHashMap[String, BsonString] =

    val usageMap: mutable.LinkedHashMap[String, BsonString] = mutable.LinkedHashMap()

    usageMap("userID") = BsonString.apply(user.userID)
    usageMap("userType") = BsonString.apply(user.userType)
    usageMap("city") = BsonString.apply(user.city)
    usageMap("region") = BsonString.apply(user.region)
    usageMap("usageType") = BsonString.apply(usageType)
    usageMap("usage") = BsonString.apply(Random.between(100.0,500.0).toString)
    usageMap("cost") = BsonString.apply(Random.between(100.0,500.0).toString)
    usageMap("month") = BsonString.apply(month.toString)
    usageMap("year") = BsonString.apply(year.toString)

    usageMap