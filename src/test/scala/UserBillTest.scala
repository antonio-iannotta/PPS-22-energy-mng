import org.scalatest.funsuite.AnyFunSuite

class UserBillTest extends AnyFunSuite:
  val testBill = UserBill("234","AntonioIannotta","private","water",234.65,89.6,
    2,2022,"Cesena","Emilia-Romagna")

  test("BillID must be equals to 234") {
    assert(testBill.getBillID() == "234")
  }

  test("UserID must be AntonioIannotta") {
    assert(testBill.getUserID() == "AntonioIannotta")
  }

  test("userType must be private") {
    assert(testBill.getUserType() == "private")
  }

  test("usageType must be water") {
    assert(testBill.getUsageType() == "water")
  }

  test("Usage must be 234.65") {
    assert(testBill.getUsage() == 234.65)
  }

  test("Cost must be 89.6") {
    assert(testBill.getCost() == 89.6)
  }

  test("Month must be February") {
    assert(testBill.getMonth() == 2)
  }

  test("Year must be 2022") {
    assert(testBill.getYear() == 2022)
  }

  test("City must be Cesena") {
    assert(testBill.getCity() == "Cesena")
  }

  test("Region must be Emilia-Romagna") {
    assert(testBill.getRegion() == "Emilia-Romagna")
  }
