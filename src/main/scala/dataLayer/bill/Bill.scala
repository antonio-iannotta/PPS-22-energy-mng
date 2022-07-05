package dataLayer.bill

class Bill(private val _billID: String, private val _userID: String, private val _userType: String,
           private val _usageType: String, private val _usage: Double, private val _cost: Double,
           private val _month: Int, private val _year: Int, private val _city: String, private val _region: String):

  def userID: String = _userID

  def region: String = _region

  def userType: String = _userType

  def billID: String = _billID

  def city: String = _city

  def usageType: String = _usageType

  def usage: Double = _usage

  def cost: Double = _cost

  def month: Int = _month

  def year: Int = _year