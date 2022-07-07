package dataLayer.mongoDriver

import org.mongodb.scala.bson.{BsonObjectId, BsonString}
import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ListBuffer
import dataLayer.bill.Bill
import dataLayer.user.User
import dataLayer.mongoDriver.MongoDB._

class MongoDBTest extends AnyFunSuite:

  test("createBill success scenario") {

    var usageFields: ListBuffer[Any] = ListBuffer()
    usageFields += BsonObjectId.apply()
    usageFields += BsonString.apply("userID")
    usageFields += BsonString.apply("private")
    usageFields += BsonString.apply("milano")
    usageFields += BsonString.apply("lombardia")
    usageFields += BsonString.apply("water")
    usageFields += BsonString.apply("20.0")
    usageFields += BsonString.apply("30.0")
    usageFields += BsonString.apply("1")
    usageFields += BsonString.apply("1970")

    val bill: Bill = createBill(usageFields)
    assert(bill.isInstanceOf[Bill])
    assert(bill.userID == "userID")
    assert(bill.usage == 20.0)
    assert(bill.userType == "private")
    assert(bill.region == "lombardia")
    assert(bill.city == "milano")
    assert(bill.month == 1)
    assert(bill.year == 1970)
    assert(bill.usageType == "water")
    assert(bill.cost == 30.0)
  }

  test("createBill fail scenario") {

    var usageFields: ListBuffer[Any] = ListBuffer()
    usageFields += BsonObjectId.apply()
    usageFields += BsonObjectId.apply()
    usageFields += BsonString.apply("private")
    usageFields += BsonString.apply("milano")
    usageFields += BsonString.apply("lombardia")
    usageFields += BsonString.apply("water")
    usageFields += BsonString.apply("20.0")
    usageFields += BsonString.apply("30.0")
    usageFields += BsonString.apply("1")
    usageFields += BsonString.apply("1970")

    assertThrows[ClassCastException](createBill(usageFields))
  }

  test("createUser success scenario") {

    var userFields: ListBuffer[Any] = ListBuffer()
    userFields += BsonObjectId.apply()
    userFields += BsonString.apply("userID")
    userFields += BsonString.apply("password")
    userFields += BsonString.apply("private")
    userFields += BsonString.apply("lombardia")
    userFields += BsonString.apply("milano")

    val user: User = createUser(userFields)
    assert(user.isInstanceOf[User])
    assert(user.userID == "userID")
    assert(user.password == "password")
    assert(user.userType == "private")
    assert(user.region == "lombardia")
    assert(user.city == "milano")

  }

  test("createUser fail scenario") {

    var userFields: ListBuffer[Any] = ListBuffer()
    userFields += BsonObjectId.apply()
    userFields += BsonObjectId.apply()
    userFields += BsonString.apply("password")
    userFields += BsonString.apply("private")
    userFields += BsonString.apply("lombardia")
    userFields += BsonString.apply("milano")


    assertThrows[ClassCastException](createUser(userFields))

  }
