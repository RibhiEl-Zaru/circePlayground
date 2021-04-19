package DecodingDemos

import DecodingDemos.Utils.FriendlyDecodersCaseClasses
import DecodingDemos.Utils.FriendlyDecodersCaseClasses.{IntPayload, StringIntPayload}
import io.circe.syntax.EncoderOps

import scala.util.Try

object FriendlyDecoders extends App {

  println("Say you have someone very unreliable sending you requests and you simply can't agree on request Schema.")
  println("But, you NEED something of a certain type but are afraid of confrontation and need to react to their inconsistent data types")
  println("Well... circe lets you be friendly and avoid this confrontation no sweat (although in the real world don't do this just settle on a contract please.")


  println("Look here, we can send an int as a String OR an Integer, and correctly parse to our Desired Payload using Circe's smart decoding")

  val stringJson = StringIntPayload("2").asJson

  println(stringJson)
  println("That was the string json\n")

  val stringDecodedRes = FriendlyDecodersCaseClasses.DesiredPayload.decodeDesiredPayload.decodeJson(stringJson)

  println("\n And this is what we decoded!")
  println(stringDecodedRes)

  val integerJson = IntPayload(2).asJson

  println(integerJson)
  println("\n\nThat was the int json")

  val intDecodeRes = FriendlyDecodersCaseClasses.DesiredPayload.decodeDesiredPayload.decodeJson(integerJson)

  println("And this is what we decoded!")
  println(intDecodeRes)

  println("However... if the string is NOT an Integer it does not break safely with the out of box deriven decoders...")

  val badStringJson = StringIntPayload("two").asJson

  val badStringDecodedRes = Try(FriendlyDecodersCaseClasses.DesiredPayload.decodeDesiredPayload.decodeJson(badStringJson))

  println("This is the bad json")
  println(badStringJson)

  println("And this is the result")
  println(badStringDecodedRes)

  println("If that was not wrapped in a try... we would have thrown an exception which is obviously bad.")

  println("However! There is a solution, and that solution comes from validation, which is discussed in the ValidatedDecoders file woohoo!")

}
