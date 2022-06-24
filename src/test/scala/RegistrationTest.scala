import org.scalatest.funsuite.AnyFunSuite

class RegistrationTest extends AnyFunSuite:

    test("Napoli is not in Lombardia") {
      assert(registration.signUP("Demetrio","pippo","private","Napoli","Lombardia") == "error!")
    }
    test("Milano is in Lombardia") {
      assert(registration.signUP("Demetrio","pippo","private","Milano","Lombardia") == "success!")
    }