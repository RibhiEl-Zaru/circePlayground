package DecodingDemos

import DecodingDemos.Utils.{FriendlyDecodersCaseClasses, ValidatedCaseClasses}
import DecodingDemos.Utils.FriendlyDecodersCaseClasses.StringIntPayload
import DecodingDemos.Utils.ValidatedCaseClasses.{Name, Number}
import io.circe.syntax.EncoderOps


object ValidatedDecoders extends App {

  println("Sometimes you don't want to just accept any old value when you decode. " +
    "You want them to suit your definition of valid even if the type matches that in your case class.\n" +
    " Simply deriving a decoder does not do this for ya, and you need to use circe's validate methods.")

  println("\nThe StringIntPayload is a good example of this, as it would accept any string, however not just any String is an Int")
  println("This broke the DesiredPayload decoder... tragically.")
  println("However! We can fix it! Lookie here:")

  val badStringJson = StringIntPayload("two").asJson

  val badStringDecodedRes = FriendlyDecodersCaseClasses.DesiredPayload.validatedDecodeDesiredPayload.decodeJson(badStringJson)

  println("This is the bad json")
  println(badStringJson)

  println("And this is the result")
  println(badStringDecodedRes)

  println("See! It did not throw an exception and instead returned a good old Decoding Failure. Allowing us to truly parse any valid Int or String json I'm so happy.")


  println("\n\nThis applies to any other stuff. Say you want only Ints within a range? Or Strings that start with A? Validation does that trick for you")

  val goodName = Name("Alfonso").asJson
  val badName = Name("Bobby").asJson

  println("Look at these names")
  println(goodName)
  println(badName)

  println("Only one will survive.")

  val goodNameDecode = ValidatedCaseClasses.NameStartsWithA.decodeNameStartsWithAPayload.decodeJson(goodName)
  val badNameDecode = ValidatedCaseClasses.NameStartsWithA.decodeNameStartsWithAPayload.decodeJson(badName)

  println(goodNameDecode)
  println(badNameDecode)



  val goodNumber = Number(20).asJson
  val badNumber = Number(-20).asJson

  println("Look at these numbers")
  println(goodNumber)
  println(badNumber)

  println("Only one will survive.")

  val goodNumberDecode = ValidatedCaseClasses.PositiveNumber.decodePositiveNumber.decodeJson(goodNumber)
  val badNumberDecode = ValidatedCaseClasses.PositiveNumber.decodePositiveNumber.decodeJson(badNumber)

  println(goodNumberDecode)
  println(badNumberDecode)


}
