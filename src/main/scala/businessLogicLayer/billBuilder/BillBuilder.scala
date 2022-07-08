package businessLogicLayer.billBuilder

import scala.collection.mutable.ListBuffer
import dataLayer.bill.Bill
import dataLayer.mongoDriver.MongoDB

object BillBuilder:
  /**
   * Metodo che ritorna un ListBuffer di oggetti di tipo "Bill"
   * @return
   */
  def build(): ListBuffer[Bill] =
    MongoDB.retrieveDataFromCollection().asInstanceOf[ListBuffer[Bill]]
