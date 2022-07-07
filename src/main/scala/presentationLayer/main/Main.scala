package presentationLayer.main

import presentationLayer.dashboard.Dashboard
import dataLayer.login.Login
import dataLayer.user.User
import dataLayer.registration.Registration
import dataLayer.usageGenerator.UsageGenerator

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
        val generator = new UsageGenerator

        println("Vuoi avviare la generazione dei dati automatica?")
        println("1) Si")
        println("2) No")

        val generatorSelection = scala.io.StdIn.readInt()
        if generatorSelection == 1 then
          generator.start()

        Dashboard(user.get).view()

        if generatorSelection == 1 then
          try
            generator.interrupt()
          catch
            case _ : Exception => println("Il generatore è stato interrotto.")

      case "3" =>
        exit = false
        println("Arrivederci, grazie per aver utilizzato Energy Management.")
      case _ =>
        println("Input errato, inserisci una possibile scelta.")

  /**
   * Il seguente metodo si occupa di prendere i dati inseriti dall'utente relativi alla Registration e successivamente di chiamare la Registration
   * @return
   */
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

  /**
   * Il seguente metodo si occupa di prendere i dati inseriti dall'utente relativi al Login e successivamente di chiamare il Login
   * @return
   */
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