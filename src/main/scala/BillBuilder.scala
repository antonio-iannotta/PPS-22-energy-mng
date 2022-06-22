import scala.collection.mutable.ListBuffer

object BillBuilder:
  def build(): ListBuffer[UserBill] =
    var userBillList = ListBuffer[UserBill]()
    val billListTest = List(UserBill("id_we9q80", "00001", "privato", "acqua", 56, 32.31, 02, 2022, "Cesena", "Emilia-Romagna"),
                            UserBill("id_we4q82", "00002", "privato", "luce", 44, 22.91, 02, 2022, "Cesena", "Emilia-Romagna"),
                            UserBill("id_we3q83", "00003", "azienda", "luce", 53, 52.31, 03, 2022, "Bologna", "Emilia-Romagna"),
                            UserBill("id_we8q50", "00004", "privato", "gas", 75, 36.16, 03, 2022, "Milano", "Lombardia"))

    billListTest.foreach(bill => userBillList += bill)
    userBillList

