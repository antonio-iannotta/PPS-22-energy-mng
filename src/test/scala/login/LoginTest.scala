package login

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable.LinkedHashMap

class LoginTest extends AnyFunSuite:

  val userMap: LinkedHashMap[String, String] = LinkedHashMap("User1" -> "Password1", "User2" -> "Password2")

  test("User1 is not blank") {
    val user = Login.signIN("User1","Password1")
    assert(user.get.userID == "User1" && user.get.getPassword == "Password1")
  }

  test("User is blank") {
    val user = Login.signIN(" ","Password1")
    assert(user.get.userID == "")
  }

  test("Password is not blank") {
    val user = Login.signIN("User1","Password1")
    assert(user.get.password == "Password1")
  }

  test("Password is blank"){
    val user = Login.signIN("User1","")
    assert(user.get.password == "")
  }