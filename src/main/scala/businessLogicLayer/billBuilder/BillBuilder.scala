package businessLogicLayer.billBuilder

import scala.collection.mutable.ListBuffer
import dataLayer.bill.Bill
import dataLayer.mongoDriver.MongoDB

object BillBuilder:
  def build(): ListBuffer[Bill] =
    MongoDB.retrieveUsages()
