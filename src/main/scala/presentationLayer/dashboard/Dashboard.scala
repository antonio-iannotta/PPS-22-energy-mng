
package presentationLayer.dashboard
import dataLayer.user.User
import presentationLayer.dashboard.choiceHandler.ChoiceHandler


case class Dashboard(private val user: User):

  def view(): Unit =

    var exit = true

    while (exit)
      println("\nBenvenuto " + user.userID + "! Seleziona l'operazione desiderata:")
      PrintHelper.printMenu()
      val selection = scala.io.StdIn.readLine()

      selection match
        case "1" => println(ChoiceHandler.individualChoiceHandler(user, "cost"))
        case "2" => println(ChoiceHandler.individualChoiceHandler(user, "usage"))
        case "3" => ChoiceHandler.cityRegionChoiceHandler(user, "cost", "city").foreach(monthAndCost => println(PrintHelper.formatter(monthAndCost._1) + monthAndCost._2.toString))
        case "4" => ChoiceHandler.cityRegionChoiceHandler(user, "usage", "city").foreach(monthAndUsage => println(PrintHelper.formatter(monthAndUsage._1) + monthAndUsage._2.toString))
        case "5" => ChoiceHandler.cityRegionChoiceHandler(user, "cost", "region").foreach(monthAndCost => println(PrintHelper.formatter(monthAndCost._1) + monthAndCost._2.toString))
        case "6" => ChoiceHandler.cityRegionChoiceHandler(user, "usage", "region").foreach(monthAndUsage => println(PrintHelper.formatter(monthAndUsage._1) + monthAndUsage._2.toString))

        case "7" =>
          println("Inserire l'anno d'interesse:")
          try
            val year = scala.io.StdIn.readInt()
            user.makeIndividualPrediction(PrintHelper.usageMenuPrint(), year)
          catch
            case _: NumberFormatException => println("Input non valido.")

        case "8" =>
          println("1) Previsione città corrente utente")
          println("2) Previsione città a scelta")
          val choice = scala.io.StdIn.readInt()
          choice match
            case 1 =>
              CityManager(user,user.city,PrintHelper.usageMenuPrint())
            case 2 =>
              println("Inserire la città di cui si desidera visualizzare la previsione")
              val city = scala.io.StdIn.readLine()
              CityManager(user,city,PrintHelper.usageMenuPrint())

        case "9" =>
          println("1) Previsione regione corrente utente")
          println("2) Previsione regionea scelta")
          val choice = scala.io.StdIn.readInt()
          choice match
            case 1 =>
              RegionManager(user,user.region,PrintHelper.usageMenuPrint())
            case 2 =>
              println("Inserire la regione di cui si desidera visualizzare la previsione")
              val region = scala.io.StdIn.readLine()
              RegionManager(user,region,PrintHelper.usageMenuPrint())

        case "10" => exit = false
        case _ => println("Scelta non valida!")