package presentationLayer.dashboard.choiceHandler

import dataLayer.user.User

trait ChoiceHandler:
  def choiceHandler(user:User) : Any
