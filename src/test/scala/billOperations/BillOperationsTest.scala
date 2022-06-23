package billOperations

import org.scalatest.funsuite.AnyFunSuite

class BillOperationsTest extends AnyFunSuite:

  test("Usage should be present") {
    val result = BillOperations.getIndividualCost("AntonioIannotta","water")
    assert(!result.isEmpty)
    println(result)
  }

  test("Usage for user is not present!") {
    val result = BillOperations.getIndividualCost("AntonioIannotta", "heat")
    assert(result.isEmpty)
  }

  test("Usage for a certain location is present!") {
    val result = BillOperations.getUsageByLocation("private","water","Emilia-Romagna","region")
    assert(!result.isEmpty)
    println(result)
  }

  test("Usage is not supposed to change!") {
    val result = BillOperations.makeIndividualPrediction("AntonioIannotta","water",2024)
    assert(result == "Your usage for water is not supposed to change for 2024")
    println(result)
  }