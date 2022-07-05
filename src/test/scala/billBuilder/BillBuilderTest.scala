package businessLogicLayer.billBuilder

import billBuilder.BillBuilder
import org.scalatest.funsuite.AnyFunSuite

class BillBuilderTest extends AnyFunSuite:
  val billListTest = BillBuilder.build()

  test("List is longer than 1") {
    assert(billListTest.length > 1)
  }

  test("At least one element in list has city == 'Cesena'") {
    assert(billListTest.exists(bill => bill.city == "Cesena"))
  }

  test("At least one element in list has usageType == 'acqua'") {
    assert(!billListTest.exists(bill => bill.usageType != "acqua" &&
      bill.usageType != "luce" &&
      bill.usageType != "gas"))
  }

  test("Element has as userType 'private' or 'company' only") {
    assert(!billListTest.exists(bill => bill.userType != "private" &&
      bill.userType != "company"))
  }

  test("At least one element in list has usage > 0") {
    assert(!billListTest.filter(bill => bill.usage > 0).isEmpty)
  }

  test("List is not empty") {
    assert(!billListTest.isEmpty)
  }

  test("First billID is String") {
    assert(!billListTest.filter(bill => bill.billID.isInstanceOf[String]).isEmpty)
  }

  test("First userID is String") {
    assert(!billListTest.filter(bill => bill.userID.isInstanceOf[String]).isEmpty)
  }

  test("First userType is String") {
    assert(!billListTest.filter(bill => bill.userType.isInstanceOf[String]).isEmpty)
  }

  test("First usageType is String") {
    assert(!billListTest.filter(bill => bill.usageType.isInstanceOf[String]).isEmpty)
  }

  test("First usage is Double") {
    assert(!billListTest.filter(bill => bill.usage.isInstanceOf[Double]).isEmpty)
  }

  test("First cost is Double") {
    assert(!billListTest.filter(bill => bill.cost.isInstanceOf[Double]).isEmpty)
  }

  test("First month is Int") {
    assert(!billListTest.filter(bill => bill.month.isInstanceOf[Int]).isEmpty)
  }

  test("First year is Int") {
    assert(!billListTest.filter(bill => bill.year.isInstanceOf[Int]).isEmpty)
  }

  test("First city is String") {
    assert(!billListTest.filter(bill => bill.city.isInstanceOf[String]).isEmpty)
  }

  test("First region is String") {
    assert(!billListTest.filter(bill => bill.region.isInstanceOf[String]).isEmpty)
  }