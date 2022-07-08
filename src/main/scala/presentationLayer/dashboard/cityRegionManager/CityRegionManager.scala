package presentationLayer.dashboard.cityRegionManager

import dataLayer.user.User

trait CityRegionManager:
  def manager(user:User, locationType:String, usage:String): Unit