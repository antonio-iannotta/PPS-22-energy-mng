package dashboard.choiceHandler

import user.User

object UserCostChoiceHandler extends ChoiceHandler {
  override def choiceHandler(user: User): String =
    println("1) per visualizzare costi luce")
    println("2) per visualizzare costi gas")
    println("3) per visualizzare costi acqua")
    val scelta = scala.io.StdIn.readInt()

    scelta match
      case 1 => user.getCost("electricity")
      case 2 => user.getCost("heat")
      case 3 => user.getCost("water")
}
