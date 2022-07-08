package dataLayer.registration

import java.math.BigInteger
import java.security.MessageDigest

object MD5:
  /**
   * The following method generates the hash code of the incoming password
   * @param password
   * @return
   */
  def md5HashPassword(password: String): String =
    val md = MessageDigest.getInstance("MD5")
    val digest: Array[Byte] = md.digest(password.getBytes)
    val bigInt = BigInteger(1, digest)
    val hashedPassword = bigInt.toString(16).trim

    hashedPassword
