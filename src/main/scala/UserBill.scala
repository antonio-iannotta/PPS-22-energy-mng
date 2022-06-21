class UserBill(private val billID: String, private val userID: String, private val userType: String,
           private val usageType: String, private val usage: Double, private val cost: Double,
           private val month: Int, private val year: Int, private val city: String, private val region: String):

  def getUserID(): String =
    userID

  def getRegion(): String =
    region

  def getUserType(): String =
    userType

  def getBillID(): String =
    billID

  def getCity(): String =
    city

  def getUsageType(): String =
    usageType

  def getUsage(): Double =
    usage

  def getCost(): Double =
    cost

  def getMonth(): Int =
    month

  def getYear(): Int =
    year

