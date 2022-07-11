package presentationLayer.dashboard.cityRegionManager

import dataLayer.user.User
import presentationLayer.dashboard.printHelper.PrintHelper

trait CityRegionManager:
  def manager(user:User, locationType:String, usage:String): Unit
  def comparison(printHelper: PrintHelper,user: User): Unit