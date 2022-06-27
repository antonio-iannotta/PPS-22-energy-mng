package user

import org.scalatest.funsuite.AnyFunSuite

class UserTest extends AnyFunSuite:

  test("All fields are correct") {
    val UserClassTest = new User("codiceFiscale", "regione", "città", "privato")
    assert(UserClassTest.getUserID() == "codiceFiscale")
    assert(UserClassTest.getRegion() == "regione")
    assert(UserClassTest.getCity() == "città")
    assert(UserClassTest.getUserType() == "privato")
  }

  test("All fields are empty"){
    val UserClassTest = new User("", "", "", "")
    assert(UserClassTest.getUserID() != "codiceFiscale")
    assert(UserClassTest.getRegion() != "regione")
    assert(UserClassTest.getCity() != "città")
    assert(UserClassTest.getUserType() != "privato")
  }

  test("All fields are null"){
    val UserClassTest = new User(null, null, null, null)
    assert(UserClassTest.getUserID() == null)
    assert(UserClassTest.getRegion() == null)
    assert(UserClassTest.getCity() == null)
    assert(UserClassTest.getUserType() == null)
  }