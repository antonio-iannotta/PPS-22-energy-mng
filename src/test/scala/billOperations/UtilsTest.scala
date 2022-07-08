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

  fillBillList(billListTest, "antonio", "privato", "milano", "lombardia")
  fillBillList(billListTest, "andrea", "private", "bergamo", "lombardia")
  fillBillList(billListTest,"marco","private","roma","lazio")
  fillBillList(billListTest,"carlo","private","milano","lombardia")


  var testMap: mutable.LinkedHashMap[Int, Double] = mutable.LinkedHashMap()

  test("averageTest1") {
    
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
    
    assert(average(testMap) == 65)
    
  }

  test("averageTest2") {
    
    testMap = mutable.LinkedHashMap()
    
    assert(testMap.isEmpty)
    
  }


  test("predictionResult test") {

    testMap = mutable.LinkedHashMap()
    testMap(2000) = 10.0

    assert(predictionResult(2001, testMap, 10.0, 10.0, "water") == "Your usage and cost for water is not supposed to change for 2001")
    assert(predictionResult(2003, testMap, 10.0, 10.0, "water").nonEmpty)

    testMap(2001) = 15.0
    testMap(2002) = 15.0

    assert(predictionResult(2003, testMap, 10.0, 10.0, "water") == "Your usage and cost for water is not supposed to change for 2003")
    assert(predictionResult(2004, testMap, 10.0, 10.0, "water") == "Your usage and cost for water is not supposed to change for 2004")
    assert(predictionResult(2005, testMap, 10.0, 10.0, "water") == "Your usage and cost for water is not supposed to change for 2005")
    assert(predictionResult(2006, testMap, 10.0, 10.0, "water").nonEmpty)

  }

  test("individualMapInitialization test1") {
    
    individualMapInitialization(testMap, "antonio", "water", billListTest)
    assert(testMap.nonEmpty)
    
    testMap = mutable.LinkedHashMap()
     
  }

  test("individualMapInitialization test2") {
    
    individualMapInitialization(testMap,"giacomo","water",billListTest)
    
    assert(testMap.isEmpty)
    
  }

  test("mapInitializationByLocation() test1") {
    
    testMap = mutable.LinkedHashMap()
    
    mapInitializationByLocation(testMap,"private","heat","city","milano", billListTest)
    assert(testMap.nonEmpty)
    
  }

  test("mapInitializationByLocation() test2") {
    
    testMap = mutable.LinkedHashMap()
    
    mapInitializationByLocation(testMap, "private", "heat", "region", "lazio", billListTest)
    assert(testMap.nonEmpty)
  }

  test("mapInitializationByLocation() when company is not present") {
    
    testMap = mutable.LinkedHashMap()
    
    mapInitializationByLocation(testMap, "company", "heat", "region", "lombardia", billListTest)
    assert(testMap.isEmpty)
    
  }

  test("fillIndividualUsageCostMap success scenario") {
    
    testMap = mutable.LinkedHashMap()
    
    individualMapInitialization(testMap,"antonio","electricity", billListTest)
    fillIndividualUsageCostMap(testMap,"usage","antonio","electricity", billListTest)
    assert(testMap.nonEmpty)
    
    testMap = mutable.LinkedHashMap()
    
    individualMapInitialization(testMap,"antonio","electricity", billListTest)
    fillIndividualUsageCostMap(testMap, "cost", "antonio", "electricity", billListTest)
    assert(testMap.nonEmpty)
    
  }

  test("fillIndividualUsageCost fail scenario with wrong userID 1") {
    
    testMap = mutable.LinkedHashMap()
    
    fillIndividualUsageCostMap(testMap, "usage", "marco", "electricity", billListTest)
    assert(testMap.isEmpty)
    
  }

  test("fillIndividualUsageCost fail scenario with wrong userID 2") {
    
    testMap = mutable.LinkedHashMap()
    
    individualMapInitialization(testMap,"marco","electricity", billListTest)
    fillIndividualUsageCostMap(testMap, "cost", "giacomo", "electricity", billListTest)
    assert(testMap.values.head.isNaN)
    
  }

  test("fillIndividualUsageCost fail with wrong usageOrCost selection") {
    
    testMap = mutable.LinkedHashMap()
    
    individualMapInitialization(testMap,"marco","electricity", billListTest)
    fillIndividualUsageCostMap(testMap, "utenza", "giacomo", "electricity", billListTest)
    assert(testMap.values.sum == 0.0)
    
  }

  test("fillIndividualUsageCost fail with wrong usageType selection") {
    
    testMap = mutable.LinkedHashMap()
    
    individualMapInitialization(testMap,"antonio","electricity", billListTest)
    fillIndividualUsageCostMap(testMap, "usage", "antonio", "gas", billListTest)
    assert(testMap.values.head.isNaN)
    
  }

  test("fillUsageCostMapByLocation success scenario") {
    
    testMap = mutable.LinkedHashMap()
    
    mapInitializationByLocation(testMap, "private", "electricity", "city", "milano", billListTest)
    fillUsageCostMapByLocation(testMap, "electricity", "usage", "private", "city", "milano", billListTest)
    assert(testMap.nonEmpty)
    
    testMap = mutable.LinkedHashMap()
    
    mapInitializationByLocation(testMap, "private", "electricity", "region", "lazio", billListTest)
    fillUsageCostMapByLocation(testMap, "electricity", "cost", "private", "region", "lazio", billListTest)
    assert(testMap.nonEmpty)
    
  }

  test("fillUsageCostMapByLocation fail scenario") {
    
    //Caso in cui il tipo di utente è una azienda. Questo test fallirà dal momento in cui non c'è nessuna bolletta relativa ad un'azienda.
    testMap = mutable.LinkedHashMap()
    
    mapInitializationByLocation(testMap, "company", "electricity", "city", "milano", billListTest)
    fillUsageCostMapByLocation(testMap, "electricity", "usage", "company", "city", "milano", billListTest)
    assert(testMap.isEmpty)

    //Caso in cui il tipo di utenza inserita non esiste
    testMap = mutable.LinkedHashMap()
    
    mapInitializationByLocation(testMap, "company", "gas", "city", "milano", billListTest)
    fillUsageCostMapByLocation(testMap, "gas", "usage", "company", "city", "milano", billListTest)
    assert(testMap.isEmpty)

    //Caso in cui non c'è alcuna bolletta relativa ad una specifica città
    testMap = mutable.LinkedHashMap()
    
    mapInitializationByLocation(testMap, "company", "electricity", "city", "bologna", billListTest)
    fillUsageCostMapByLocation(testMap, "electricity", "usage", "company", "city", "bologna", billListTest)
    assert(testMap.isEmpty)

    //Caso in cui non c'è alcuna bolletta relativa ad una certa regione
    testMap = mutable.LinkedHashMap()
    
    mapInitializationByLocation(testMap, "company", "electricity", "region", "veneto", billListTest)
    fillUsageCostMapByLocation(testMap, "electricity", "usage", "company", "region", "veneto", billListTest)
    assert(testMap.isEmpty)
    
  }

  test("getBillsByUserIDAndUsageType success scenario") {
    
    assert(getBillsByUserIDAndUsageType("antonio", "electricity", billListTest).nonEmpty)
    assert(getBillsByUserIDAndUsageType("antonio", "heat", billListTest).nonEmpty)
    assert(getBillsByUserIDAndUsageType("antonio", "water", billListTest).nonEmpty)
    assert(getBillsByUserIDAndUsageType("andrea", "electricity", billListTest).nonEmpty)
    assert(getBillsByUserIDAndUsageType("andrea", "water", billListTest).nonEmpty)
    assert(getBillsByUserIDAndUsageType("andrea", "heat", billListTest).nonEmpty)
    assert(getBillsByUserIDAndUsageType("carlo", "water", billListTest).nonEmpty)
    assert(getBillsByUserIDAndUsageType("carlo", "heat", billListTest).nonEmpty)
    assert(getBillsByUserIDAndUsageType("carlo", "electricity", billListTest).nonEmpty)
    assert(getBillsByUserIDAndUsageType("marco", "water", billListTest).nonEmpty)
    assert(getBillsByUserIDAndUsageType("marco", "heat", billListTest).nonEmpty)
    assert(getBillsByUserIDAndUsageType("marco", "electricity", billListTest).nonEmpty)
    
  }

  test("getBillsByUserIDAndUsageType fail scenario") {
    
    //Caso in cui lo UserID sia errato
    assert(getBillsByUserIDAndUsageType("antonio1", "water", billListTest).isEmpty)
    
    //Caso in cui il tipo di utenza non sia corretto
    assert(getBillsByUserIDAndUsageType("antonio", "gas", billListTest).isEmpty)
    
  }

  test("getBillsByCityOrRegion success scenario") {
    
    //Caso in cui la città inserita sia presente nella lista delle bollette
    assert(getBillsByCityOrRegion("private", "electricity", "city", "milano", billListTest).nonEmpty)
    
    // Caso in cui la regione inserita sia presente nella lista delle bollette
    assert(getBillsByCityOrRegion("private", "electricity", "region", "lombardia", billListTest).nonEmpty)
    
  }

  test("getBillsByCityOrRegion fail scenario") {
    
    //Caso in cui la città non sia presente nella lista delle bollette
    assert(getBillsByCityOrRegion("private", "electricity", "city", "bologna", billListTest).isEmpty)
    
    //Caso in cui la regione non sia presente nella lista delle bollette
    assert(getBillsByCityOrRegion("private", "electricity", "region", "liguria", billListTest).isEmpty)
    
    //Caso in cui la tipologia di utente inserita non sia corretta
    assert(getBillsByCityOrRegion("azienda", "electricity", "city", "milano", billListTest).isEmpty)
    
    //Caso in cui la tipologia di utenza inserita non sia una tra quelle valide
    assert(getBillsByCityOrRegion("private", "gas", "city", "milano", billListTest).isEmpty)
    
  }
  
  test("getMonthlyAverageUsageOrCost success scenario") {
    
    billListTest = ListBuffer(Bill("1","antonio","private","electricity",10,20,1,2020,"milano","lombardia"),
      Bill("2","marco","private","electricity",20,30,1,2020,"milano","lombardia"))

    assert(getMonthlyAverageUsageOrCost("private","electricity","city","milano",billListTest,"usage",2020,1) == 15)
    assert(getMonthlyAverageUsageOrCost("private","electricity","city","milano",billListTest,"cost",2020,1) == 25)
    
  }


  private def fillBillList(billList: ListBuffer[Bill], userID: String, userType: String, city: String, region: String): Unit =

    for month <- Range(1,13) do
      for year <- Range(2000, 2005) do
        for usageType <- List("water", "heat", "electricity") do
          billList += Bill(LocalDateTime.now().toString, userID, userType, usageType, Random.between(100.0, 200.0), Random.between(100.0, 200.0), month, year, city, region)



