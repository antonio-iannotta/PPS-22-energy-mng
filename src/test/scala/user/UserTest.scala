package user

import org.scalatest.funsuite.AnyFunSuite

class UserTest extends AnyFunSuite {

  val UserClassTest_1 = new User("codiceFiscale", "regione", "città", "privato")

  assert(UserClassTest_1.getUserID() == "codiceFiscale")
  assert(UserClassTest_1.getRegion() == "regione")
  assert(UserClassTest_1.getCity() == "città")
  assert(UserClassTest_1.getUserType() == "privato")
}
class UserTest_CheckValues2 extends AnyFunSuite{

  val UserClassTest_2 = new User("", "", "", "")

  assert(UserClassTest_2.getUserID() != "codiceFiscale")
  assert(UserClassTest_2.getRegion() != "regione")
  assert(UserClassTest_2.getCity() != "città")
  assert(UserClassTest_2.getUserType() != "privato")
}
class UserTest_CheckValues3 extends AnyFunSuite{

  val UserClassTest_3 = new User(null, null, null, null)

  assert(UserClassTest_3.getUserID() == null)
  assert(UserClassTest_3.getRegion() == null)
  assert(UserClassTest_3.getCity() == null)
  assert(UserClassTest_3.getUserType() == null)
}