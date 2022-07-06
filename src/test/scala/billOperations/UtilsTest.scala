package applicationLogicLayer.billOperations

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.ListBuffer
import dataLayer.bill.Bill

import java.time.LocalDateTime
import scala.collection.mutable
import scala.util.Random
import Utils._

class UtilsTest extends AnyFunSuite:

  var billListTest: ListBuffer[Bill] = ListBuffer()

  for month <- Range(1,13) do
     for year <- Range(2000,2005) do
       for usageType <- List("water", "heat", "electricity") do
          billListTest += Bill(LocalDateTime.now().toString, "antonio", "private", usageType, Random.between(100.0, 500.0), Random.between(100.0, 500.0), month, year, "milano", "lombardia")


  for month <- Range(1,13) do
    for year <- Range(2000,2005) do
      for usageType <- List("water", "heat", "electricity") do
        billListTest += Bill(LocalDateTime.now().toString, "demetrio", "private", usageType, Random.between(100.0, 500.0), Random.between(100.0, 500.0), month, year, "brescia", "lombardia")

  for month <- Range(1,13) do
    for year <- Range(2000,2005) do
      for usageType <- List("water", "heat", "electricity") do
        billListTest += Bill(LocalDateTime.now().toString, "carlo", "private", usageType, Random.between(100.0, 500.0), Random.between(100.0, 500.0), month, year, "roma", "lazio")


  for month <- Range(1,13) do
    for year <- Range(2000,2005) do
      for usageType <- List("water", "heat", "electricity") do
        billListTest += Bill(LocalDateTime.now().toString, "antonio", "private", usageType, Random.between(100.0, 500.0), Random.between(100.0, 500.0), month, year, "latina", "lazio")



  var testMap: mutable.LinkedHashMap[Int, Double] = mutable.LinkedHashMap()

  test("variationTest1") {
    testMap(2000) = 10.0
    testMap(2001) = 20.0
    testMap(2002) = 30.0
    testMap(2003) = 40.0
    testMap(2004) = 50.0
    testMap(2005) = 60.0
    testMap(2006) = 70.0
    testMap(2007) = 80.0
    testMap(2008) = 90.0
    testMap(2009) = 100.0
    testMap(2010) = 110.0
    testMap(2011) = 120.0
    assert(variation(testMap) == 65)
  }

  test("variationTest2") {
    testMap = mutable.LinkedHashMap()
    assert(testMap.isEmpty)
  }


  test("predictionResult test") {
    testMap(2000) = 10.0
    testMap(2000) = 20.0
    testMap(2000) = 30.0
    testMap(2000) = 40.0
    testMap(2000) = 50.0
    testMap(2000) = 60.0
    testMap(2000) = 70.0
    testMap(2000) = 80.0
    testMap(2000) = 90.0
    testMap(2000) = 100.0
    testMap(2000) = 110.0
    testMap(2000) = 120.0

    assert(predictionResult(2001, testMap, 10.0, 10.0, "water") == "Your usage and cost for water is not supposed to change for 2001")
    assert(!predictionResult(2003, testMap, 10.0, 10.0, "water").isEmpty)
    println(predictionResult(2003, testMap, 10.0, 10.0, "water"))
  }

  test("individualMapInitialization test1") {
    individualMapInitialization(testMap, "antonio", "water", billListTest)
    assert(!testMap.isEmpty)
    testMap = mutable.LinkedHashMap()
  }

  test("individualMapInitialization test2") {
    individualMapInitialization(testMap,"marco","water",billListTest)
    assert(testMap.isEmpty)
  }

  test("initializationMapByLocation test1") {
    testMap = mutable.LinkedHashMap()
    initializationMapByLocation(testMap,"private","heat","city","milano", billListTest)
    assert(!testMap.isEmpty)
  }

  test("initializationMapByLocation test2") {
    testMap = mutable.LinkedHashMap()
    initializationMapByLocation(testMap, "private", "heat", "region", "lazio", billListTest)
    assert(!testMap.isEmpty)
  }

  test("initializationMapByLocation when company is not present") {
    testMap = mutable.LinkedHashMap()
    initializationMapByLocation(testMap, "company", "heat", "region", "lombardia", billListTest)
    assert(testMap.isEmpty)
  }

  test("fillIndividualUsageCostMap success scenario") {
    testMap = mutable.LinkedHashMap()
    individualMapInitialization(testMap,"antonio","electricity", billListTest)
    fillIndividualUsageCostMap(testMap,"usage","antonio","electricity", billListTest)
    assert(!testMap.isEmpty)
    testMap = mutable.LinkedHashMap()
    individualMapInitialization(testMap,"antonio","electricity", billListTest)
    fillIndividualUsageCostMap(testMap, "cost", "antonio", "electricity", billListTest)
    assert(!testMap.isEmpty)
  }

  test("fillIndividualUsageCost fail scenario with wrong userID 1") {
    testMap = mutable.LinkedHashMap()
    fillIndividualUsageCostMap(testMap, "usage", "marco", "electricity", billListTest)
    assert(testMap.isEmpty)
  }

  test("fillIndividualUsageCost fail scenario with wrong userID 2") {
    testMap = mutable.LinkedHashMap()
    individualMapInitialization(testMap,"marco","electricity", billListTest)
    fillIndividualUsageCostMap(testMap, "cost", "marco", "electricity", billListTest)
    assert(testMap.isEmpty)
  }

  test("fillIndividualUsageCost fail with wrong usageOrCost selection") {
    testMap = mutable.LinkedHashMap()
    individualMapInitialization(testMap,"marco","electricity", billListTest)
    fillIndividualUsageCostMap(testMap, "utenza", "marco", "electricity", billListTest)
    assert(testMap.isEmpty)
  }

  test("fillIndividualUsageCost fail with wrong usageType selection") {
    testMap = mutable.LinkedHashMap()
    individualMapInitialization(testMap,"antonio","electricity", billListTest)
    fillIndividualUsageCostMap(testMap, "usage", "antonio", "gas", billListTest)
    assert(testMap.values.head.isNaN)
  }






