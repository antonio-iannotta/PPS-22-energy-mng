package dataLayer.mongoDriver

import org.mongodb.scala.*
import org.mongodb.scala.bson.BsonString


import java.time.{LocalDate, LocalDateTime}
import dataLayer.bill.{Bill}
import dataLayer.user.{User}
import scala.collection.mutable.{LinkedHashMap, ListBuffer}
import scala.util.Random
import dataLayer.mongoDriver.Helpers._

object MongoDB:

  /**
   * Il seguente metodo ritorna il database 
   * @return
   */
  def mongoDBConnection(): MongoDatabase =
    MongoClient("mongodb://localhost:27017").getDatabase("energymanagement")

  /**
   * Il seguente metodo recupera gli utenti dal DB effettuando una chiamata sulla collezione "users"
   * @return
   */
  def retrieveUsers(): ListBuffer[User] =
    var userList: ListBuffer[User] = ListBuffer()
    MongoDB.mongoDBConnection().getCollection("users")
      .find().results()
      .foreach(user => userList += createUser(user.foldLeft[ListBuffer[Any]](ListBuffer())(_ += _._2.asInstanceOf[Any])))
    userList

  /**
   * Il seguente metodo recupera i consumi dal DB effettuando una chiamata sulla collezione "usages"
   * @return
   */
  def retrieveUsages(): ListBuffer[Bill] =
    var billList: ListBuffer[Bill] = ListBuffer()
    MongoDB.mongoDBConnection().getCollection("usages")
      .find().results()
      .foreach(usage => billList += createBill(usage.foldLeft[ListBuffer[Any]](ListBuffer())(_ += _._2.asInstanceOf[Any])))
    billList

  /**
   * Il seguente metodo restituisce una bolletta creata a partire dai campi dei singoli consumi che sono stati recuperati dal database
   * @param usages
   * @return
   */
  def createBill(usages: ListBuffer[Any]): Bill =
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
   * Il seguente metodo aggiunge un utente al database
   * @param user
   * @return
   */
  def addUser(user: User): String =
    val document = Document(composeUserMap(user))
    mongoDBConnection().getCollection("users").insertOne(document).results()


  /**
   * Il seguente metodo restituisce una bolletta creata a partire dia campi dei singoli utenti creati dal database
   * @param users
   * @return
   */
  def createUser(users: ListBuffer[Any]): User =

    val userID: String = users(1).asInstanceOf[BsonString].asString().getValue
    val password: String = users(2).asInstanceOf[BsonString].asString().getValue
    val userType: String = users(3).asInstanceOf[BsonString].asString().getValue
    val region: String = users(4).asInstanceOf[BsonString].asString().getValue
    val city: String = users(5).asInstanceOf[BsonString].asString().getValue

    User(userID,password,userType,region,city)


  /**
   * Il seguente metodo si occupa di comporre una mappa [String, BsonString] che servirà come input per il Document da memorizzare nel database degli utenti
   * @param user
   * @return
   */
  def composeUserMap(user: User): LinkedHashMap[String, BsonString] =
    val userMap: LinkedHashMap[String, BsonString] = LinkedHashMap()
    userMap("userID") = BsonString.apply(user.userID)
    userMap("password") = BsonString.apply(user.password)
    userMap("userType") = BsonString.apply(user.userType)
    userMap("region") = BsonString.apply(user.region)
    userMap("city") = BsonString.apply(user.city)

    userMap

  /**
   * Il seguente metodo si occupa di comporre una mappa [String, BsonString] che servirà come input per il Document da memorizzare nel database dei consumi
   * @param user
   * @param usageType
   * @param month
   * @param year
   * @return
   */
  def composeUsageMap(user: User, usageType: String, month: Int, year: Int): LinkedHashMap[String, BsonString] =
    val usageMap: LinkedHashMap[String, BsonString] = LinkedHashMap()

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