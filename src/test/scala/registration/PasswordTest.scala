package dataLayer.registration

import dataLayer.registration.MD5.md5HashPassword
import sun.security.provider.MD5
import org.scalatest.funsuite.AnyFunSuite

class PasswordTest extends AnyFunSuite:

  test("pierino are hashed with success") {
    assert(md5HashPassword("pierino") == "f817231a2ff2e1a01964d278b8dc8feb")
  }

  test(" pierino not are hashed with success") {
    assert(md5HashPassword("pierino") != "f917231a2ff2e1a01964d278b8dc8feb")
  }

  test("foo, bar, foobar are hashed with success") {
    assert(md5HashPassword("foo") == "acbd18db4cc2f85cedef654fccc4a4d8")
    assert(md5HashPassword("bar") == "37b51d194a7513e45b56f6524f2d51f2")
    assert(md5HashPassword("foobar") == "3858f62230ac3c915f300c664312c63f")
  }

  test(" deme3io are hashed with success") {
    assert(md5HashPassword("deme3io") == "1e901ebb1e1bea53d5f2765524d88f55")
  }