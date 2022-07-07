package dataLayer.bill

class Bill(private val _billID: String, private val _userID: String, private val _userType: String,
           private val _usageType: String, private val _usage: Double, private val _cost: Double,
           private val _month: Int, private val _year: Int, private val _city: String, private val _region: String):

  /**
   * metodo che ritorna lo userID
   * @return
   */
  def userID: String = _userID

  /**
   * metodo che ritorna la regione a cui la bolletta fa riferimento
   * @return
   */
  def region: String = _region


  /**
   * metodo che ritorna la tipologia di utente
   * @return
   */
  def userType: String = _userType

  /**
   * metodo che ritorna l'ID della bolletta
   * @return
   */
  def billID: String = _billID

  /**
   * metodo che ritorna la città a cui la bolletta fa riferimento
   * @return
   */
  def city: String = _city


  /**
   * metodo che ritorna la tipologia di consumo a cui la bolletta fa riferimento
   * @return
   */
  def usageType: String = _usageType

  /**
   * metodo che ritorna il consumo effettivo per la specifica tipologia di consumo
   * @return
   */
  def usage: Double = _usage

  /**
   * metodo che ritorna il costo relativo al consumo per una specifica tipologia di utenza
   * @return
   */
  def cost: Double = _cost

  /**
   * metodo che ritorna il mese a cui fa riferimento la bolletta
   * @return
   */
  def month: Int = _month

  /**
   * mese che ritorna l'anno a cui fa riferimento la bolletta
   * @return
   */
  def year: Int = _year