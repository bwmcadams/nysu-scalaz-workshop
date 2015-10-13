package codes.bytes.scalaz_workshop

import java.util.concurrent.atomic.AtomicLong

case class Address(
                    line1: String,
                    line2: Option[String],
                    city: String,
                    state: String,
                    zipCode: String
                    )

case class NewUser(
                    userID: UserID = UserID(NewUser.nextID) ,
                    givenName: String,
                    middleName: Option[String],
                    familyName: String,
                    address: Address,
                    phoneNumber: String,
                    email: String
                    )

object NewUser {
  private val userIdCounter = new AtomicLong(0)
  def nextID = userIdCounter.getAndIncrement()
}

case class UserID(private val underlying: Long) extends AnyVal


// vim: set ts=2 sw=2 sts=2 et: