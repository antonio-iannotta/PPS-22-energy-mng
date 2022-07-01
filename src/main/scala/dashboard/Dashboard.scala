package dashboard

import user.User

case class Dashboard(private val user: User):
  
  def view(choise: String): Unit =
    println("ciao")
