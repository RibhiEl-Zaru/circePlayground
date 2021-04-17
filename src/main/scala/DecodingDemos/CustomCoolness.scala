package DecodingDemos

import DecodingDemos.Utils.CustomCoolnessCaseClasses
import DecodingDemos.Utils.CustomCoolnessCaseClasses.AlteredValues
import io.circe.generic.semiauto.deriveEncoder
import io.circe.{Decoder, Encoder, HCursor}
import io.circe.syntax.EncoderOps


object CustomCoolness extends App {

  println("Whats great about custom decoders is that you can manipulate/massage data before creating your case class.\n")
  println("Take a look at decodeAndDouble in CustomCaseClasses class. Basically takes an expected json and duplicates it \n")


  println("Now, we take an AlteredValues case class and make a json out of it\n")
  val json = AlteredValues("test", 2).asJson

  println(json)

  println("Then we use our decodeAndDouble decoder to double the values by using circe\n")

  val doubledDecodingRes = CustomCoolnessCaseClasses.decodeAndDouble.decodeJson(json)

  println("\nLook!")
  println(doubledDecodingRes)


  println("Anyways, the world is our oyster, say we want to make a nihilist decoder? You got it")

  val nihilistDecodingRes = CustomCoolnessCaseClasses.nihilistDecoder.decodeJson(json)


  println("\nLook! Or maybe dont it doesnt matter anyways. Life is meaningless fyi\n")
  println(nihilistDecodingRes)


}


