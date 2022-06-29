package login

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.LinkedHashMap

class LoginTest extends AnyFunSuite:

  val userMap: LinkedHashMap[String, String] = LinkedHashMap("User1" -> "Password1", "User2" -> "Password2")

  test("User1 is not blank") {
    assert(Login.singIN("User1","Password1") == "OK")
  }

  test("User is blank") {
    assert(Login.singIN(" ","Password1") != "OK")
  }

  test("Password is not blank") {
    assert(Login.singIN("UserMock","Password1") == "OK")
  }

  test("Password is blank"){
    assert(Login.singIN("UserMock","") != "OK")
  }