package main

import login._
import registration._
import user._

object Main extends App:
  println("Salve, cosa vuoi fare? 1)Registration o 2)Login")
  var selection = scala.io.StdIn.readInt()

  selection match
    case 1 => callRegistration()
    case 2 => callLogin()
    case _ => println("Operazione non valida")


  def callRegistration(): String =
    println("Inserire userID, password, userType, region e city")
    println("userID: ")
    val userID = scala.io.StdIn.readLine()
    println("password: ")
    val password = scala.io.StdIn.readLine()
    println("user type: ")
    val user_type = scala.io.StdIn.readInt()
    println("region: ")
    val region = scala.io.StdIn.readLine()
    println("city: ")
    val city =scala.io.StdIn.readLine()

    Registration.signUP(userID, password, user_type, region, city)

  def callLogin(): Option[User] =
    println("Inserire userID, password")
    println("userID: ")
    val userID = scala.io.StdIn.readLine()
    println("password: ")
    val password = scala.io.StdIn.readLine()
    val user = Login.signIN(userID, password)

    user match
      case user if user.isEmpty =>
        println("errore!")
        None
      case _ => user
