import MD5.md5HashPassword
import org.scalatest.funsuite.AnyFunSuite

class RegistrationTest extends AnyFunSuite:

  test("a and b are hashed with success") {
    assert(md5HashPassword("a") == "0cc175b9c0f1b6a831c399e269772661")
    assert(md5HashPassword("b") == "92eb5ffee6ae2fec3ad71c777531578f")
  }

  test("foo, bar, foobar are hashed with success") {
    assert(md5HashPassword("foo") == "acbd18db4cc2f85cedef654fccc4a4d8")
    assert(md5HashPassword("bar") == "37b51d194a7513e45b56f6524f2d51f2")
    assert(md5HashPassword("foobar") == "3858f62230ac3c915f300c664312c63f")
  }

  test(" a and b not are hashed with success") {
    assert(md5HashPassword("a") != "1cc175b9c0f1b6a831c399e269772661")
    assert(md5HashPassword("b") != "02eb5ffee6ae2fec3ad71c777531578f")
  }