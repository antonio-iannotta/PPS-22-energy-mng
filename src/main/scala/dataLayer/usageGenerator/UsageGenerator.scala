package dataLayer.usageGenerator

import dataLayer.mongoDriver.MongoDB
import dataLayer.mongoDriver.Helpers._
import org.mongodb.scala.bson.{BsonString, Document}
import scala.collection.mutable.*
import scala.language.postfixOps
import scala.util.Random

object UsageGenerator extends App:

  def generation(): Unit =
    var month = getActualMonthOrYear("month")
    var year = getActualMonthOrYear("year")
    val usagesCollection = MongoDB.mongoDBConnection().getCollection("usages")
    val usageTypes: List[String] = List("water", "heat", "electricity")

    while true do
      MongoDB.retrieveUsers().foreach(user => usageTypes.foreach(
        usageType => usagesCollection.insertOne(Document(MongoDB.composeUsageMap(user,usageType,month,year))).results()
      ))

      month match
        case 12 =>
          month = 1
          year += 1
        case _ =>
          month += 1

      Thread.sleep(10000)

  /**
   * Metodo inizializare mese e anno per determinare le date
   * degli "usages" che verranno creati
   * @param monthOrYear
   * @return mese 1 / anno 1970 se non ci sono "usages", ultimo mese/anno se ci sono "usages"
   */
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


  generation()