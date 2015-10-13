package codes.bytes.scalaz_workshop.validation

import codes.bytes.scalaz_workshop.Address
import org.scalatest.{MustMatchers, FlatSpec}
import org.typelevel.scalatest.ValidationMatchers

class FormValidationSpec extends FlatSpec with MustMatchers with ValidationMatchers {
  behavior of "A New User Form Validator"

  it should "Pass validation on a valid user" in {
    val validatedUser = NewUserForm.validate(validUserForm)
    validatedUser mustBe success
    validatedUser must beSuccess(validUserUser)
  }

  it should "Fail on a user with a bad email address" in {
    val validatedUser = NewUserForm.validate(
      validUserForm.copy(email = "OvalOffice@TheWhiteHouse")
    )
    validatedUser mustBe failure
    validatedUser must haveFailure(InvalidEmail)
  }

  it should "Fail on a user with a bad phone number" in {
    val validatedUser = NewUserForm.validate(
      validUserForm.copy(phoneNumber = "IM-PRESIDENT!")
    )
    validatedUser mustBe failure
    validatedUser must haveFailure(InvalidPhone)
  }

  it should "Fail on a user with a bad state" in {
    val validatedUser = NewUserForm.validate(
      validUserForm.copy(address = validAddress.copy(
          state = "The District Of Columbia"
        )
      )
    )
    validatedUser mustBe failure
    validatedUser must haveFailure(InvalidState)
  }

  it should "Fail on a user with a bad zip code" in {
    val validatedUser = NewUserForm.validate(
      validUserForm.copy(address = validAddress.copy(
          zipCode = "WHITE-HOUSE"
        )
      )
    )
    validatedUser mustBe failure
    validatedUser must haveFailure(InvalidZip)
  }

  it should "Fail on a user with bad name data" in {
    val validatedUser = NewUserForm.validate(
      validUserForm.copy(
        familyName = "",
        middleName = None,
        givenName = "         "
      )
    )
    validatedUser mustBe failure
    validatedUser must haveFailure(EmptyGivenName)
    validatedUser must haveFailure(EmptyFamilyName)
  }

  it should "Fail on a bunch of the above bad properties combined" in {
    val validatedUser = NewUserForm.validate(
      validUserForm.copy(
        familyName = "",
        middleName = None,
        givenName = "         ",
        address = validAddress.copy(
          zipCode = "WHITE-HOUSE",
          state = "The District Of Columbia"
        )
      )
    )
    validatedUser mustBe failure
    validatedUser must haveFailure(EmptyGivenName)
    validatedUser must haveFailure(EmptyFamilyName)
    validatedUser must haveFailure(InvalidZip)
    validatedUser must haveFailure(InvalidState)
  }

  lazy val validUserForm = NewUserForm(
    "Mr.",
    None,
    "President",
    validAddress,
    "(202) 456-1111",
    "president@whitehouse.gov"
  )

  lazy val validUserUser = NewUserForm.newUserFromForm(validUserForm)

  lazy val validAddress = Address(
    "1600 Pennsylvania Ave NW",
    None,
    "Washington",
    "DC",
    "20500"
  )

}

// vim: set ts=2 sw=2 sts=2 et: