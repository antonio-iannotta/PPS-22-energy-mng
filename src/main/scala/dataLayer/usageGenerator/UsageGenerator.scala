package dataLayer.usageGenerator

import dataLayer.mongoDriver.MongoDB
import dataLayer.mongoDriver.Helpers._
import org.mongodb.scala.bson.{BsonString, Document}
import scala.collection.mutable.*
import scala.language.postfixOps
import scala.util.Random

class UsageGenerator extends Thread:

  /**
   * Metodo per generare dati in maniera randomica per ogni tipo utilizzo e per ogni utente ed effettuare il caricamento degli stessi sul DB
   * @Return Unit
   */
  override def run(): Unit =
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

      println("Generated data uploaded.")
      Thread.sleep(10000)

  /**
   * Metodo per inizializzare mese e anno per determinare le date degli "usages" che verranno creati e caricati sul DB
   * @param monthOrYear
   * @return
   * Caso in cui monthOrYear sia uguale a "month": 1 se non ci sono "usages" nel db, numero intero corrispondente al mese seguente se elementi "usages" già presenti sul DB
   * Caso in cui monthOrYear sia uguale a "year": 1970 se non ci sono "usages" nel db, numero intero corrispondente all'anno seguente se elementi "usages" già presenti sul DB
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