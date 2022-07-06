package dataLayer.registration

import dataLayer.registration.Registration
import org.scalatest.funsuite.AnyFunSuite

class RegistrationTest extends AnyFunSuite:

    test("Napoli is not in Lombardia") {
      assert(dataLayer.registration.Registration.signUP("Demetrio","pierino",0,"Lombardia","Napoli") != "OK")
    }

    test("Milano is in Lombardia") {
      assert(dataLayer.registration.Registration.signUP("Demetrio","pierino",1,"Lombardia","Milano") == "OK")
    }

    test("userId is ok") {
      assert(dataLayer.registration.Registration.signUP("Demetrio","pierino",1,"Lombardia","Milano") == "OK")
    }

    test("userId is blank") {
      assert(dataLayer.registration.Registration.signUP("","pierino",1,"Lombardia","Milano") != "OK")
    }

    test("userId is less than 6") {
      assert(dataLayer.registration.Registration.signUP("Marco","pierino",1,"Lombardia","Milano") != "OK")
    }

    test("userId is more than 20") {
      assert(dataLayer.registration.Registration.signUP("Massimiliano Maria Antonio ","pierino",1,"Lombardia","Milano") != "OK")
    }

    test("password is ok") {
      assert(dataLayer.registration.Registration.signUP("Demetrio","pierino",1,"Lombardia","Milano") == "OK")
    }

    test("password is blank") {
    assert(dataLayer.registration.Registration.signUP("Demetrio","",1,"Lombardia","Milano") != "OK")
    }

    test("password is less than 6") {
      assert(dataLayer.registration.Registration.signUP("Demetrio","piero",1,"Lombardia","Milano") != "OK")
    }

    test("password is more than 20") {
      assert(dataLayer.registration.Registration.signUP("Demetrio","internazionale milano football club",1,"Lombardia","Milano") != "OK")
    }

    test("userType is private(0)") {
      assert(dataLayer.registration.Registration.signUP("Demetrio","pierino",0,"Lombardia","Milano") == "OK")
    }

    test("userType is company(1)") {
      assert(dataLayer.registration.Registration.signUP("Demetrio","pierino",1,"Lombardia","Milano") == "OK")
    }

    test("userType is not private(0)") {
      assert(dataLayer.registration.Registration.signUP("Demetrio","pierino",2,"Lombardia","Milano") != "OK")
    }

    test("userType is not company(1)") {
      assert(dataLayer.registration.Registration.signUP("Demetrio","pierino",2,"Lombardia","Milano") != "OK")
    }

    test("region is ok") {
      assert(dataLayer.registration.Registration.signUP("Demetrio","pierino",1,"Lombardia","Monza") == "OK")
    }





