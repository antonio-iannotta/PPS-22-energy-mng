package presentationLayer.main

import dashboard.Dashboard
import login.Login
import user.User
import registration.Registration

object Main extends App :

  var exit = true
  while exit do
    println("Salve, che operazione vuoi effettuare? Digita: \n 1) Registrazione \n 2) Login \n 3) Esci")
    val selection = scala.io.StdIn.readLine()
    selection match
      case "1" =>
        val responseMessage = callRegistration()
        responseMessage match
          case "OK" => println("Registrazione avvenuta con successo!")
          case _ => println(responseMessage)

      case "2" =>
        val user = callLogin()
        Dashboard(user.get).view()
      case "3" =>
        exit = false
        println("Arrivederci, grazie per aver utilizzato Energy Management.")
      case _ =>
        println("Input errato, inserisci una possibile scelta.")

  def callRegistration(): String =
    println("Registrazione: inserire userID, password, userType, region e city")
    println("userID: ")
    val userID = scala.io.StdIn.readLine()
    println("password: ")
    val password = scala.io.StdIn.readLine()
    println("user type: \n 0) Privato \n 1) Azienda ")

    var repeat = true
    var userType = ""
    while repeat do
      userType = scala.io.StdIn.readLine()
      userType match
        case "0" =>
          repeat = false
        case "1" =>
          repeat = false
        case _ =>
          println("Input errato, digita: \n 0) Privato \n 1) Azienda")

    println("region: ")
    val region = scala.io.StdIn.readLine()
    println("city: ")
    val city = scala.io.StdIn.readLine()

    Registration.signUP(userID, password, userType.toInt, region, city)

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