package user

import org.scalatest.funsuite.AnyFunSuite

class UserTest extends AnyFunSuite:

  test("All fields are correct") {
    val UserClassTest = User("codiceFiscale", "123456", "regione", "città", "privato")
    assert(UserClassTest.getUserID() == "codiceFiscale")
    assert(UserClassTest.getPassword() == "123456")
    assert(UserClassTest.getRegion() == "regione")
    assert(UserClassTest.getCity() == "città")
    assert(UserClassTest.getUserType() == "privato")
  }

  test("All fields are empty"){
    val UserClassTest = User("","", "", "", "")
    assert(UserClassTest.getUserID() != "codiceFiscale")
    assert(UserClassTest.getPassword() != "123456")
    assert(UserClassTest.getRegion() != "regione")
    assert(UserClassTest.getCity() != "città")
    assert(UserClassTest.getUserType() != "privato")
  }

  test("All fields are null"){
    val UserClassTest = User(null, null,null, null, null)
    assert(UserClassTest.getUserID() == null)
    assert(UserClassTest.getPassword() == null)
    assert(UserClassTest.getRegion() == null)
    assert(UserClassTest.getCity() == null)
    assert(UserClassTest.getUserType() == null)
  }