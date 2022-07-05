package dataLayer.user

import org.scalatest.funsuite.AnyFunSuite

class UserTest extends AnyFunSuite:

  test("All fields are correct") {
    val UserClassTest = User("codiceFiscale", "123456", "regione", "città", "privato")
    assert(UserClassTest.userID == "codiceFiscale")
    assert(UserClassTest.password == "123456")
    assert(UserClassTest.region == "regione")
    assert(UserClassTest.city == "città")
    assert(UserClassTest.userType == "privato")
  }

  test("All fields are empty"){
    val UserClassTest = User("","", "", "", "")
    assert(UserClassTest.userID != "codiceFiscale")
    assert(UserClassTest.password != "123456")
    assert(UserClassTest.region != "regione")
    assert(UserClassTest.city != "città")
    assert(UserClassTest.userType != "privato")
  }

  test("All fields are null"){
    val UserClassTest = User(null, null,null, null, null)
    assert(UserClassTest.userID == null)
    assert(UserClassTest.password == null)
    assert(UserClassTest.region == null)
    assert(UserClassTest.city == null)
    assert(UserClassTest.userType == null)
  }