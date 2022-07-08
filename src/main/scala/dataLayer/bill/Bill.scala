package dataLayer.bill

class Bill(private val _billID: String, private val _userID: String, private val _userType: String,
           private val _usageType: String, private val _usage: Double, private val _cost: Double,
           private val _month: Int, private val _year: Int, private val _city: String, private val _region: String):

  /**
   * returns the userID
   * @return
   */
  def userID: String = _userID

  /**
   * returns the region
   * @return
   */
  def region: String = _region


  /**
   * returns the user type
   * @return
   */
  def userType: String = _userType

  /**
   * returns the billID
   * @return
   */
  def billID: String = _billID

  /**
   * returns the city 
   * @return
   */
  def city: String = _city


  /**
   * returns the usage type
   * @return
   */
  def usageType: String = _usageType

  /**
   * returns the usage related to a certain usage type
   * @return
   */
  def usage: Double = _usage

  /**
   * return the cost related to a certain usage
   * @return
   */
  def cost: Double = _cost

  /**
   * returns the month
   * @return
   */
  def month: Int = _month

  /**
   * returns the year
   * @return
   */
  def year: Int = _year