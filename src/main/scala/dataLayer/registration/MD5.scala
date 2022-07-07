package dataLayer.registration

import java.math.BigInteger
import java.security.MessageDigest

object MD5:
  /**
   * il seguente metodo svolge l'hash code della password in ingresso
   * @param password
   * @return
   */
  def md5HashPassword(password: String): String =
    val md = MessageDigest.getInstance("MD5")
    val digest: Array[Byte] = md.digest(password.getBytes)
    val bigInt = BigInteger(1, digest)
    val hashedPassword = bigInt.toString(16).trim

    hashedPassword
