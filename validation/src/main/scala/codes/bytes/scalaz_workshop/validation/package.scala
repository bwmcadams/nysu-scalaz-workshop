package codes.bytes.scalaz_workshop

package object validation {

  /**
   * Yes, validating email with Regex is unlikely to be correct.
   * But for a workshop, easy enough. Regex swiped from Play framework
   */
  private val emailRegex = """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r

  def isValidEmail(email: String): Boolean = emailRegex.findFirstMatchIn(email).isDefined

  private val phoneRegex = """^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$""".r

  def isValidPhone(phone: String): Boolean = phoneRegex.findFirstMatchIn(phone).isDefined

  private val validStateRegex = """^((A[LKSZR])|(C[AOT])|(D[EC])|(F[ML])|(G[AU])|(HI)|(I[DLNA])|(K[SY])|(LA)|(M[EHDAINSOT])|(N[EVHJMYCD])|(MP)|(O[HKR])|(P[WAR])|(RI)|(S[CD])|(T[NX])|(UT)|(V[TIA])|(W[AVIY]))$""".r

  def isValidState(state: String): Boolean = validStateRegex.findFirstMatchIn(state).isDefined

  private val validZipCodeRegex = """^[0-9]{5}(?:-[0-9]{4})?$""".r

  def isValidZipCode(zip: String): Boolean = validZipCodeRegex.findFirstMatchIn(zip).isDefined


  case class ValidationError(message: String)

  val InvalidEmail     = ValidationError("Invalid Email Address")
  val InvalidPhone     = ValidationError("Invalid Phone Number")
  val InvalidState     = ValidationError("Invalid State in Address")
  val InvalidZip       = ValidationError("Invalid Zip Code")
  val EmptyGivenName   = ValidationError("Given Name Must Not Be Empty")
  val EmptyFamilyName  = ValidationError("Family Name Must Not Be Empty")
  val EmptyMiddleName  = ValidationError("Middle Name Be 'None', not Empty String")

}
