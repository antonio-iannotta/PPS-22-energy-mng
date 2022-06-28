package mongoDriver

import org.mongodb.scala._

object MongoDB:
  def mongoDBConnection(): MongoDatabase =
    MongoClient("mongodb://localhost:27017").getDatabase("energymanagement")

