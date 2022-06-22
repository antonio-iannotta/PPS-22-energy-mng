package Login

import User.User

object Login {

  val x = List(new User("1","lazio","roma","privato"))

  def singIn(userID:String, password:String): User = {
    return new User("1","lazio","roma","privato")
  }
}

