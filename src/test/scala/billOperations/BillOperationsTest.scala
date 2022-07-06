package applicationLogicLayer.billOperations

import org.scalatest.funsuite.AnyFunSuite

class BillOperationsTest extends AnyFunSuite:

  test("Usage should be present") {
    val result = BillOperations.getIndividualCostOrUsage("AntonioIannotta","water", "usage")
    assert(!result.isEmpty)
    println(result)
  }

  test("Usage for user is not present!") {
    val result = BillOperations.getIndividualCostOrUsage("AntonioIannotta", "heat", "cost")
    assert(result.isEmpty)
  }

  test("Usage for a certain location is present!") {
    val result = BillOperations.getUsageOrCostByLocation("private","water","Emilia-Romagna","region", "usage")
    assert(!result.isEmpty)
    println(result)
  }
  
  test("Usage for a certain location is not present!") {
    assert(BillOperations.getUsageOrCostByLocation("private","water","Lombardia","region", "usage").isEmpty)
  }

  test("Usage and cost are not supposed to change!") {
    val result = BillOperations.makeIndividualPrediction("AntonioIannotta","water",2024)
    assert(result == "Your usage and cost for water is not supposed to change for 2024")
    println(result)
  }

  test("Usage and cost are supposed to change!") {
    val result = BillOperations.makeIndividualPrediction("AntonioIannotta","water",2026)
    assert(result != "Your usage and cost for water is not supposed to change for 2024")
    println(result)
  }
  
  