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



  var yearUsageCostMap: mutable.LinkedHashMap[Int, Double] = mutable.LinkedHashMap()

  test("variation should work") {
    yearUsageCostMap(1) = 10.0
    yearUsageCostMap(2) = 20.0
    yearUsageCostMap(3) = 30.0
    yearUsageCostMap(4) = 40.0
    yearUsageCostMap(5) = 50.0
    yearUsageCostMap(6) = 60.0
    yearUsageCostMap(7) = 70.0
    yearUsageCostMap(8) = 80.0
    yearUsageCostMap(9) = 90.0
    yearUsageCostMap(10) = 100.0
    yearUsageCostMap(11) = 110.0
    yearUsageCostMap(12) = 120.0
    assert(variation(yearUsageCostMap) == 65)
  }

  test("variation should not work") {
    assertThrows(variation(yearUsageCostMap))
  }