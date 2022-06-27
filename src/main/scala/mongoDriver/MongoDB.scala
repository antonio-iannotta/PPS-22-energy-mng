package mongoDriver

import org.mongodb.scala._

object MongoDB:
  def mongoDBConnection(uri: String): MongoClient =
    MongoClient(uri)

