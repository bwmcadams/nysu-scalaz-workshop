package codes.bytes.scalaz_workshop.validation


object NewUserForm {
  import scalaz._
  import Scalaz._
  import java.util.concurrent.atomic.AtomicLong

  private val userIdCounter = new AtomicLong(0)

  // TODO: Students, fill me in
  def validate: ValidationNel[String, NewUser] = ???

  def fromForm(form: NewUserForm): NewUser = {
    NewUser(
      UserID(userIdCounter.getAndIncrement()),
      form.givenName,
      form.middleName,
      form.familyName,
      form.address,
      form.phoneNumber,
      form.email
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

case class Address(
  line1: String,
  line2: Option[String],
  city: String,
  state: String,
  zipCode: Int // validate that it's 5 digits, or 5 followed by a dash and 4 more for ZIP+4
)

case class NewUser(
  userID: UserID,
  givenName: String,
  middleName: Option[String],
  familyName: String,
  address: Address,
  phoneNumber: String,
  email: String
)

case class UserID(private val underlying: Long) extends AnyVal

// vim: set ts=2 sw=2 sts=2 et: