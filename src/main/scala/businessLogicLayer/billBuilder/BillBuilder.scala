package businessLogicLayer.billBuilder

import scala.collection.mutable.ListBuffer
import dataLayer.bill.Bill
import dataLayer.mongoDriver.MongoDB

object BillBuilder:
  /**
   * This method retrieves data from database and returns a ListBuffer of object type "Bill"
   * @return
   */
  def build(): ListBuffer[Bill] =
    MongoDB.retrieveDataFromCollection("usages").asInstanceOf[ListBuffer[Bill]]
