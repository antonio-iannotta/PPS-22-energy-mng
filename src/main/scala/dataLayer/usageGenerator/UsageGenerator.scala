package dataLayer.usageGenerator

import dataLayer.mongoDriver.MongoDB
import dataLayer.mongoDriver.Helpers._
import org.mongodb.scala.bson.{BsonString, Document}
import scala.collection.mutable.*
import scala.language.postfixOps
import dataLayer.user.User
import scala.util.Random

class UsageGenerator extends Thread:

  /**
   * Method to generate random data for Bills to fill the database. It generates data for each usage type and each user and uploads it to the database
   * @Return Unit
   */
  override def run(): Unit =
    var month = getActualMonthOrYear("month")
    var year = getActualMonthOrYear("year")
    val usagesCollection = MongoDB.mongoDBConnection().getCollection("usages")
    val usageTypes: List[String] = List("water", "heat", "electricity")

    while true do
      MongoDB.retrieveDataFromCollection("users").foreach(user => usageTypes.foreach(
        usageType => usagesCollection.insertOne(Document(MongoDB.composeUsageMap(user.asInstanceOf[User],usageType,month,year))).results()
      ))

      month match
        case 12 =>
          month = 1
          year += 1
        case _ =>
          month += 1

      Thread.sleep(10000)

  /**
   * Mtheod to initialize month and year used to determine the dates of "usages" that will be generated randomically and uploaded into the database
   * @param monthOrYear String that specifies wether it has to initialize the month or the year
   * @return Int based on the String given in input:
   * if monthOrYear = "month" returns 1 if there are no usages in the database; the following month as Int otherwise
   * if monthOrYear = "year" returns 1970 if there are no usages in the database; the following year as Int otherwise
   */
  private def getActualMonthOrYear(monthOrYear: String): Int =
    val usagesCollection = MongoDB.mongoDBConnection().getCollection("usages")

    monthOrYear match
      case month if month.equalsIgnoreCase("month") =>
        usagesCollection.countDocuments().results().last match
          case 0 =>  1
          case _ => usagesCollection.find().results().last("month").asString().getValue.toInt

      case year if year.equalsIgnoreCase("year") =>
        usagesCollection.countDocuments().results().last match
          case 0 => 1970
          case _ => usagesCollection.find().results().last("year").asString().getValue.toInt
