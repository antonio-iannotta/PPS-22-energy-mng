package dataLayer.registration

import dataLayer.registration.Registration
import org.scalatest.funsuite.AnyFunSuite

class RegistrationTest extends AnyFunSuite :


  test("userID is ok") {
    assert(dataLayer.registration.Registration.signUP("MarcoAntonio", "deme3io", 1, "Lombardia", "Milano") == "OK")
  }

  test("userID is blank") {
    assert(dataLayer.registration.Registration.signUP("", "pierino", 1, "Lombardia", "Milano") == "the field USERID inserted is blank")
  }

  test("userID is less than 6") {
    assert(dataLayer.registration.Registration.signUP("Marco", "pierino", 1, "Lombardia", "Milano") != "OK")
  }

  test("userId is more than 20") {
    assert(dataLayer.registration.Registration.signUP("Massimiliano Maria Antonio", "pierino", 1, "Lombardia", "Milano") != "OK")
  }

  test("password is ok") {
    assert(dataLayer.registration.Registration.signUP("Alberto3", "pierino", 1, "Lombardia", "Milano") == "OK")
  }

  test("password is blank") {
    assert(dataLayer.registration.Registration.signUP("Claudio1", "", 1, "Lombardia", "Milano") == "the field PASSWORD inserted is blank")
  }

  test("password is less than 6") {
    assert(dataLayer.registration.Registration.signUP("Giancarlo2", "piero", 1, "Lombardia", "Milano") == "the field PASSWORD inserted is less than 6 characters")
  }

  test("password is more than 20") {
    assert(dataLayer.registration.Registration.signUP("Francesco", "internazionale milano football club", 1, "Lombardia", "Milano") == "the field PASSWORD inserted is longer than 20 characters")
  }

  test("userType is private(0)") {
    assert(dataLayer.registration.Registration.signUP("Margherita", "pierino", 0, "Lombardia", "Milano") == "OK")
  }

  test("userType is company(1)") {
    assert(dataLayer.registration.Registration.signUP("Roberto", "pierino", 1, "Lombardia", "Milano") == "OK")
  }

  test("userType is not private(0)") {
    assert(dataLayer.registration.Registration.signUP("Alberto1", "pierino", 2, "Lombardia", "Milano") == "the field USERTYPE inserted is not private or company")
  }

  test("userType is not company(1)") {
    assert(dataLayer.registration.Registration.signUP("Massimo5", "pierino", 2, "Lombardia", "Milano") == "the field USERTYPE inserted is not private or company")
  }

  test("Napoli is not in Lombardia") {
    assert(dataLayer.registration.Registration.signUP("Demetrio3", "pierino", 0, "Lombardia", "Napoli") == "the CITY is not present in the region")
  }

  test("Milano is in Lombardia") {
    assert(dataLayer.registration.Registration.signUP("Maurizio", "pierino", 1, "Lombardia", "Milano") == "OK")
  }
  test("region is ok") {
    assert(dataLayer.registration.Registration.signUP("Carletto2", "pierino", 1, "Lombardia", "Monza") == "OK")
  }





