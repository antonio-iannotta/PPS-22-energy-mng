
package presentationLayer.dashboard
import dataLayer.user.User
import presentationLayer.dashboard.choiceHandler.ChoiceHandler
import presentationLayer.dashboard.printHelper.PrintHelper
import presentationLayer.dashboard.cityRegionManager.RegionManager
import presentationLayer.dashboard.cityRegionManager.CityManager


/**
 * The following class is a kind of interface for the user. Thanks to this class the user can navigate in to the System
 * @param user is the variable which contains the user
 * @return
 */

case class Dashboard(private val user: User):

  def view(): Unit =

    val printHelper = new PrintHelper
    val cityManager = CityManager
    val regionManager = RegionManager
    var costOrUsage = ""
    var exit = true

    while (exit)
      println("\nBenvenuto " + user.userID + "! Seleziona l'operazione desiderata:")
      printHelper.printMenu()
      val selection = scala.io.StdIn.readLine()


      selection match
        case "1" => println(ChoiceHandler.individualChoiceHandler(user, "cost"))
        case "2" => println(ChoiceHandler.individualChoiceHandler(user, "usage"))
        case "3" => ChoiceHandler.cityRegionChoiceHandler(user, "cost", "city").foreach(monthAndCost => println(printHelper.formatter(monthAndCost._1) + monthAndCost._2.toString + "Є"))
        case "4" => ChoiceHandler.cityRegionChoiceHandler(user, "usage", "city").foreach(monthAndUsage => println(printHelper.formatter(monthAndUsage._1) + monthAndUsage._2.toString))
        case "5" => ChoiceHandler.cityRegionChoiceHandler(user, "cost", "region").foreach(monthAndCost => println(printHelper.formatter(monthAndCost._1) + monthAndCost._2.toString + "Є"))
        case "6" => ChoiceHandler.cityRegionChoiceHandler(user, "usage", "region").foreach(monthAndUsage => println(printHelper.formatter(monthAndUsage._1) + monthAndUsage._2.toString))

        case "7" =>
          println("Inserire l'anno d'interesse:")
          try
            val year = scala.io.StdIn.readInt()
            user.makeIndividualPrediction(printHelper.usageMenuPrint(), year)
          catch
            case _: NumberFormatException => println("Input non valido.")

        case "8" =>
          println("1) Previsione città corrente utente")
          println("2) Previsione città a scelta")
          val choice = scala.io.StdIn.readInt()
          choice match
            case 1 =>
              println(cityManager.manager(user,user.city,printHelper.usageMenuPrint()))
            case 2 =>
              println("Inserire la città di cui si desidera visualizzare la previsione")
              val city = scala.io.StdIn.readLine()
              println(cityManager.manager(user,city,printHelper.usageMenuPrint()))

        case "9" =>
          println("1) Previsione regione corrente utente")
          println("2) Previsione regionea scelta")
          val choice = scala.io.StdIn.readInt()
          choice match
            case 1 =>
              println(regionManager.manager(user,user.region,printHelper.usageMenuPrint()))
            case 2 =>
              println("Inserire la regione di cui si desidera visualizzare la previsione")
              val region = scala.io.StdIn.readLine()
              println(regionManager.manager(user,region,printHelper.usageMenuPrint()))

        case "10" => regionManager.comparison(printHelper, user)
        case "11" => cityManager.comparison(printHelper, user)
        case "12" => exit = false
        case _ => println("Scelta non valida!")