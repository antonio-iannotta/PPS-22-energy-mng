package dashboard.choiceHandler

import user.User

object UserUsageChoiceHandler extends ChoiceHandler {
  override def choiceHandler(user: User): Unit =
    println("1) per visualizzare consumi luce")
    println("2) per visualizzare consumi gas")
    println("3) per visualizzare consumi acqua")
    val scelta = scala.io.StdIn.readInt()

    scelta match
      case 1 => user.getUsage("electricity")
      case 2 => user.getUsage("heat")
      case 3 => user.getUsage("water")
}
