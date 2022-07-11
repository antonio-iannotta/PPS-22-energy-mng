package usageGenerator


import org.scalatest.funsuite.AnyFunSuite
import dataLayer.usageGenerator.UsageGenerator
import dataLayer.bill.Bill
import scala.collection.mutable.*


class UsageGeneratorTest extends AnyFunSuite :

  test("Check if month initializes at the following month based on existing bills") {
    val userRandomBill = ListBuffer[Bill]()
    var month = 1
    var year = 1970

    userRandomBill += Bill("234","AndreaCatani","private","water",332.25,82.6,
      2,1970,"Cesena","Emilia-Romagna")
    userRandomBill += Bill("235","AndreaCatani","private","water",244.15,109.6,
      3,1970,"Cesena","Emilia-Romagna")
    userRandomBill += Bill("236","AndreaCatani","private","water",134.65,29.6,
      4,1970,"Cesena","Emilia-Romagna")
    userRandomBill += Bill("237","AndreaCatani","private","water",166.57,62.1,
      5,1970,"Cesena","Emilia-Romagna")

    userRandomBill.nonEmpty match
      case false =>
        month = 1
        year = 1970
      case _ =>
        month = userRandomBill.last.month + 1
        year = userRandomBill.last.year

    assert(month == 6 && year == 1970)
  }

  test("Check if month initializes at 1 and year initializes at 1970 if no Bills exist") {
    val userRandomBill = ListBuffer[Bill]()
    var month = 1
    var year = 1970

    userRandomBill.nonEmpty match
      case false =>
        month = 1
        year = 1970
      case _ =>
        month = userRandomBill.last.month
        month match
          case 12 =>
            month = 1
            year = userRandomBill.last.year + 1
          case _ =>
            month += 1

    assert(month == 1 && year == 1970)
  }

  test("Check if month and year start over again when last month in Bills is 12") {
    val userRandomBill = ListBuffer[Bill]()
    var month = 1
    var year = 1970

    userRandomBill += Bill("234","AndreaCatani","private","water",332.25,82.6,
      9,1970,"Cesena","Emilia-Romagna")
    userRandomBill += Bill("235","AndreaCatani","private","water",244.15,109.6,
      10,1970,"Cesena","Emilia-Romagna")
    userRandomBill += Bill("236","AndreaCatani","private","water",134.65,29.6,
      11,1970,"Cesena","Emilia-Romagna")
    userRandomBill += Bill("237","AndreaCatani","private","water",166.57,62.1,
      12, 1970,"Cesena","Emilia-Romagna")

    userRandomBill.nonEmpty match
      case false =>
        month = 1
        year = 1970
      case _ =>
        month = userRandomBill.last.month
        month match
          case 12 =>
            month = 1
            year = userRandomBill.last.year + 1
          case _ =>
            month += 1

    assert(month == 1 && year == 1971)
  }