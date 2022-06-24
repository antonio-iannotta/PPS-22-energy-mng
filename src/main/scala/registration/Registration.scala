package registration

import java.math.BigInteger
import java.security.MessageDigest

object Registration:
  def signUP(userID: String, password: String, userType: Int, region: String, city: String): String =
    ""

  def passwordHash(password: String): String =
    val md = MessageDigest.getInstance("MD5")
    val digest: Array[Byte] = md.digest(password.getBytes)
    val bigInt = new BigInteger(1, digest)
    val hashedPassword = bigInt.toString(16).trim

    hashedPassword