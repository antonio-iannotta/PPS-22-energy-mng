import org.scalatest.funsuite.AnyFunSuite

object UserBillTest:
  class User extends AnyFunSuite:
    val testBill = UserBill("234","AntonioIannotta","private","water",234.65,89.6,
    2,2022,"Cesena","Emilia-Romagna")

    test("BillID should be equals to 234") {
      assert(testBill.getBillID() == "234")
    }
