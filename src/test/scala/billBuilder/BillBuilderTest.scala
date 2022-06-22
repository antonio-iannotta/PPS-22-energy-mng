package billBuilder

import billBuilder.BillBuilder
import org.scalatest.funsuite.AnyFunSuite

class BillBuilderTest extends AnyFunSuite:
  val billListTest = BillBuilder.build()

  test("List is longer than 1") {
    assert(billListTest.length > 1)
  }

  test("At least one element in list has city == 'Cesena'") {
    assert(billListTest.exists(bill => bill.getCity() == "Cesena"))
  }

  test("At least one element in list has usageType == 'acqua'") {
    assert(!billListTest.exists(bill => bill.getUsageType() != "acqua" &&
      bill.getUsageType() != "luce" &&
      bill.getUsageType() != "gas"))
  }

  test("Element has as userType 'privato' or 'azienda' only") {
    assert(!billListTest.exists(bill => bill.getUserType() != "privato" &&
      bill.getUserType() != "azienda"))
  }

  test("At least one element in list has usage > 0") {
    assert(!billListTest.filter(bill => bill.getUsage() > 0).isEmpty)
  }

  test("List is not empty") {
    assert(!billListTest.isEmpty)
  }

  test("First billID is String") {
    assert(!billListTest.filter(bill => bill.getBillID().isInstanceOf[String]).isEmpty)
  }

  test("First userID is String") {
    assert(!billListTest.filter(bill => bill.getUserID().isInstanceOf[String]).isEmpty)
  }

  test("First userType is String") {
    assert(!billListTest.filter(bill => bill.getUserType().isInstanceOf[String]).isEmpty)
  }

  test("First usageType is String") {
    assert(!billListTest.filter(bill => bill.getUsageType().isInstanceOf[String]).isEmpty)
  }

  test("First usage is Double") {
    assert(!billListTest.filter(bill => bill.getUsage().isInstanceOf[Double]).isEmpty)
  }

  test("First cost is Double") {
    assert(!billListTest.filter(bill => bill.getCost().isInstanceOf[Double]).isEmpty)
  }

  test("First month is Int") {
    assert(!billListTest.filter(bill => bill.getMonth().isInstanceOf[Int]).isEmpty)
  }

  test("First year is Int") {
    assert(!billListTest.filter(bill => bill.getYear().isInstanceOf[Int]).isEmpty)
  }

  test("First city is String") {
    assert(!billListTest.filter(bill => bill.getCity().isInstanceOf[String]).isEmpty)
  }

  test("First region is String") {
    assert(!billListTest.filter(bill => bill.getRegion().isInstanceOf[String]).isEmpty)
  }