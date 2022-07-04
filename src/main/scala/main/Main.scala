package main

import login.Login
import user.User
import registration.Registration

object Main extends App:

  var selection = 0
  while !(1 to 2 contains selection) do
    println("Salve, che operazione vuoi effettuare? Digita: \n 1) Registrazione \n 2) Login")
    try
      selection = scala.io.StdIn.readInt()
      selection match
        case 1 =>
          val responseMessage = callRegistration()
          responseMessage match
            case "OK" => println("Registrazione avvenuta con successo!")
            case _ => println(responseMessage)

        case 2 =>
          callLogin()
    catch
      case _ : NumberFormatException => println("Input non valido.")

  def callRegistration(): String =
    println("Registrazione: inserire userID, password, userType, region e city")
    println("userID: ")
    val userID = scala.io.StdIn.readLine()
    println("password: ")
    val password = scala.io.StdIn.readLine()
    println("user type: ")
    val userType = scala.io.StdIn.readInt()
    println("region: ")
    val region = scala.io.StdIn.readLine()
    println("city: ")
    val city = scala.io.StdIn.readLine()

    Registration.signUP(userID, password, userType, region, city)

  def callLogin(): Option[User] =
    println("Login: inserire userID, password")
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