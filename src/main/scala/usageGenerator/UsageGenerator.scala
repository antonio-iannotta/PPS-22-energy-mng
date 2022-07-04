import mongoDriver.Helpers._
import mongoDriver.MongoDB._
import user.User
import org.mongodb.scala.bson.BsonString

import scala.collection.mutable.*
import scala.language.postfixOps
import scala.util.Random

object UsageGenerator:

  def generation(): Unit =
    var month = getActualMonthOrYear("month")
    var year = getActualMonthOrYear("year")
    val usagesCollection = MongoDB.mongoDBConnection().getCollection("usages")
    val usageTypes: List[String] = List("water", "heat", "electricity")

    while true do
      retrieveUsers().foreach(user => usageTypes.foreach(
        usageType => usagesCollection.insertOne(Document(composeUsageMap(user,usageType,month,year))).results()
      ))

      month match
        case 12 =>
          month = 1
          year += 1
        case _ =>
          month += 1

      Thread.sleep(100000)

  private def getActualMonthOrYear(monthOrYear: String): Int =
    val usagesCollection = MongoDB.mongoDBConnection().getCollection("usages")

    monthOrYear match
      case month if month.toString.equalsIgnoreCase("month") =>
        usagesCollection.countDocuments().results().last match
          case 0 =>  1
          case _ => usagesCollection.find().results().last("month").asString().getValue.toInt

      case year if year.toString.equalsIgnoreCase("year") =>
        usagesCollection.countDocuments().results().last match
          case 0 => 1970
          case _ => usagesCollection.find().results().last("year").asString().getValue.toInt

  private def composeUsageMap(user: User, usageType: String, month: Int, year: Int): LinkedHashMap[String, BsonString] =
    val userUsage: LinkedHashMap[String, BsonString] = LinkedHashMap()

    userUsage("userID") = BsonString.apply(user.userID)
    userUsage("userType") = BsonString.apply(user.userType)
    userUsage("city") = BsonString.apply(user.city)
    userUsage("region") = BsonString.apply(user.region)
    userUsage("usageType") = BsonString.apply(usageType)
    userUsage("usage") = BsonString.apply((Random.nextDouble()/100).toString)
    userUsage("cost") = BsonString.apply((Random.nextDouble()/100).toString)
    userUsage("month") = BsonString.apply(month.toString)
    userUsage("year") = BsonString.apply(year.toString)

    userUsage