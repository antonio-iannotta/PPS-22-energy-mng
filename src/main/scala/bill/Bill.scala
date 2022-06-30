package bill

class Bill(private val _billID: String, private val _userID: String, private val _userType: String,
           private val _usageType: String, private val _usage: Double, private val _cost: Double,
           private val _month: Int, private val _year: Int, private val _city: String, private val _region: String):

  def getUserID: String = _userID

  def getRegion: String = _region

  def getUserType: String = _userType

  def getBillID: String = _billID

  def getCity: String = _city

  def getUsageType: String = _usageType

  def getUsage: Double = _usage

  def getCost: Double = _cost

  def getMonth: Int = _month

  def getYear: Int = _year

