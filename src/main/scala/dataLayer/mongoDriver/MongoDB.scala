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
      .foreach(user => userList += createUser(user.foldLeft[ListBuffer[Any]](ListBuffer())(_ += _._2.asInstanceOf[Any])))
    userList

  def retrieveUsages(): ListBuffer[Bill] =

    var billList: ListBuffer[Bill] = ListBuffer()
    MongoDB.mongoDBConnection().getCollection("usages")
      .find().results()
      .foreach(usage => billList += createBill(usage.foldLeft[ListBuffer[Any]](ListBuffer())(_ += _._2.asInstanceOf[Any])))
    billList

  private def createBill(usages: ListBuffer[Any]): Bill =
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

  def addUser(user: User): String =
    val document = Document(composeUser(user))
    mongoDBConnection().getCollection("users").insertOne(document).results()

  private def createUser(users: ListBuffer[Any]): User =

    val userID: String = users(1).asInstanceOf[BsonString].asString().getValue
    val password: String = users(2).asInstanceOf[BsonString].asString().getValue
    val userType: String = users(3).asInstanceOf[BsonString].asString().getValue
    val region: String = users(4).asInstanceOf[BsonString].asString().getValue
    val city: String = users(5).asInstanceOf[BsonString].asString().getValue

    User(userID,password,userType,region,city)

  private def composeUser(user: User): LinkedHashMap[String, BsonString] =
    val userMap: LinkedHashMap[String, BsonString] = LinkedHashMap()
    userMap("userID") = BsonString.apply(user.userID)
    userMap("password") = BsonString.apply(user.password)
    userMap("userType") = BsonString.apply(user.userType)
    userMap("region") = BsonString.apply(user.region)
    userMap("city") = BsonString.apply(user.city)

    userMap