package presentationLayer.dashboard.choiceHandler

import user.User

trait ChoiceHandler:
  def choiceHandler(user:User) : Any
