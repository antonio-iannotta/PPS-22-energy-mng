package registration

import org.scalatest.funsuite.AnyFunSuite

class RegistrationTest extends AnyFunSuite:

    test("Napoli is not in Lombardia") {
      assert(registration.Registration.signUP("Demetrio","pippo",0,"Lombardia","Napoli") == "error!")
    }
    test("Milano is in Lombardia") {
      assert(registration.Registration.signUP("Demetrio","pippo",1,"Lombardia","Milano") == "success!")
    }