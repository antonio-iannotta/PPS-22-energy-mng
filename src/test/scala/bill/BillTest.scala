package dataLayer.bill

import org.scalatest.funsuite.AnyFunSuite
import bill.Bill

class BillTest extends AnyFunSuite:
  val testBill = Bill("234","AntonioIannotta","private","water",234.65,89.6,
    2,2022,"Cesena","Emilia-Romagna")

  test("BillID must be equals to 234") {
    assert(testBill.billID == "234")
  }

  test("UserID must be AntonioIannotta") {
    assert(testBill.userID == "AntonioIannotta")
  }

  test("userType must be private") {
    assert(testBill.userType == "private")
  }

  test("usageType must be water") {
    assert(testBill.usageType == "water")
  }

  test("Usage must be 234.65") {
    assert(testBill.usage == 234.65)
  }

  test("Cost must be 89.6") {
    assert(testBill.cost == 89.6)
  }

  test("Month must be February") {
    assert(testBill.month == 2)
  }

  test("Year must be 2022") {
    assert(testBill.year == 2022)
  }

  test("City must be Cesena") {
    assert(testBill.city == "Cesena")
  }

  test("Region must be Emilia-Romagna") {
    assert(testBill.region == "Emilia-Romagna")
  }
