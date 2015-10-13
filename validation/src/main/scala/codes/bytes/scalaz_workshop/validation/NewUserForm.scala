package codes.bytes.scalaz_workshop.validation

import codes.bytes.scalaz_workshop.{UserID, NewUser, Address}


object NewUserForm {
  import scalaz._
  import Scalaz._
  import java.util.concurrent.atomic.AtomicLong


  // TODO: Students, fill me in
  def validate(submission: NewUserForm): ValidationNel[ValidationError, NewUser] = ???

  def newUserFromForm(form: NewUserForm): NewUser = {
    NewUser(
      givenName = form.givenName,
      middleName = form.middleName,
      familyName = form.familyName,
      address = form.address,
      phoneNumber = form.phoneNumber,
      email = form.email
    )
  }

}

case class NewUserForm(
  givenName: String,
  middleName: Option[String],
  familyName: String,
  address: Address,
  phoneNumber: String,
  email: String
)

// vim: set ts=2 sw=2 sts=2 et: