package mongoDriver

import org.mongodb.scala._
import org.mongodb.scala.bson.BsonString
import user.User
import bill.Bill
import collection.mutable._

import scala.collection.mutable._
import mongoDriver.Helpers._

import java.time.{LocalDate, LocalDateTime}
import scala.collection.mutable

object MongoDB:

  def mongoDBConnection(): MongoDatabase =
    MongoClient("mongodb://localhost:27017").getDatabase("energymanagement")

  def retrieveUsers(): ListBuffer[User] =
    var userList: ListBuffer[User] = ListBuffer()
    MongoDB.mongoDBConnection().getCollection("users")
      .find().results()
      .foreach(user => userList += createUser(user.foldLeft[ListBuffer[String]](ListBuffer())(_ += _._2.asString().getValue)))
    userList

  def retrieveUsages(): ListBuffer[Bill] =

    var billList: ListBuffer[Bill] = ListBuffer()
    MongoDB.mongoDBConnection().getCollection("usages")
      .find().results()
      .foreach(usage => billList += createBill(usage.foldLeft[ListBuffer[String]](ListBuffer())(_ += _._2.asString().getValue)))
    billList

  private def createBill(usages: ListBuffer[String]): Bill =
    val billID: String = LocalDateTime.now().toString
    val userID: String = usages(0)
    val userType: String = usages(1)
    val city: String = usages(2)
    val region: String = usages(3)
    val usageType: String = usages(4)
    val usage = usages(5).toDouble
    val cost = usages(6).toDouble
    val month = usages(7).toInt
    val year = usages(8).toInt

    Bill(billID,userID,userType,usageType,usage,cost,month,year,city,region)

  def addUser(user: User): String =
    val document = Document(composeUser(user))
    mongoDBConnection().getCollection("users").insertOne(document).results()

  private def createUser(users: ListBuffer[String]): User =
    val userID: String = users(0)
    val password: String = users(1)
    val userType: String = users(2)
    val region: String = users(3)
    val city: String = users(4)

    User(userID,password,region,city,userType)

  private def composeUser(user: User): LinkedHashMap[String, BsonString] =
    val userMap: LinkedHashMap[String, BsonString] = LinkedHashMap()
    userMap("userID") = BsonString.apply(user.getUserID)
    userMap("password") = BsonString.apply(user.getPassword)
    userMap("region") = BsonString.apply(user.getRegion)
    userMap("city") = BsonString.apply(user.getCity)
    userMap("userType") = BsonString.apply(user.getUserType)

    userMap