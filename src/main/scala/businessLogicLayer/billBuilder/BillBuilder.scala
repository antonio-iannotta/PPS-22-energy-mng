package businessLogicLayer.billBuilder

import scala.collection.mutable.ListBuffer
import bill.Bill
import mongoDriver.MongoDB

object BillBuilder:
  def build(): ListBuffer[Bill] =
    MongoDB.retrieveUsages()
