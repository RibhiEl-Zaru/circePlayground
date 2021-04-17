package DecodingDemos

import DecodingDemos.Utils.FriendlyDecodersCaseClasses
import DecodingDemos.Utils.FriendlyDecodersCaseClasses.{IntPayload, StringPayload}
import io.circe.syntax.EncoderOps

object FriendlyDecoders extends App {

  println("Say you have someone very unreliable sending you requests and you simply can't agree on request Schema.")
  println("But, you NEED something of a certain type but are afraid of confrontation and need to react to their inconsistent data types")
  println("Well... circe lets you be friendly and avoid this confrontation no sweat (although in the real world don't do this just settle on a contract please.")


  println("Look here, we can send an int as a String OR an Integer, and correctly parse to our Desired Payload using Circe's smart decoding")

  val stringJson = StringPayload("2").asJson

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

  // TODO make this break safely.
  println("However... if the string is NOT an Integer it does not break safely... yet")

  val badStringJson = StringPayload("two").asJson

  val badStringDecodedRes = FriendlyDecodersCaseClasses.DesiredPayload.decodeDesiredPayload.decodeJson(badStringJson)

  println("This is the bad json")
  println(badStringJson)

  println("And this is the result")
  println(badStringDecodedRes)

}
