package DecodingDemos

import DecodingDemos.Utils.CustomCaseClasses.AlteredValues
import DecodingDemos.Utils.NestedCaseClass.{NestedNestedNestedNestedString, NestedNestedNestedString, NestedNestedString, NestedString}
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.EncoderOps


object NestedCustomClasses extends App {

  println("One pain point all circe users go through is when you have to decode a custom case class within a custom case class within a custom case class within a custom case class within a custom case class within a custom case class within a custom case class.\n")

  println("\n Basically, the rule is you need to have an implicit de/encoder for each case class within the case class you are trying to de/encode.\n")

  println("\n The reason why this works for generic classes is CUZ of circe implicits. so this has always been around, but is hidden from ya\n")


  println("\n Any who, the below examples show how the structure of Nested Case Class in Utils works. Delete any decoder/encoder and the following lines will be borked.\n")


  val nestedJson = NestedNestedNestedNestedString(NestedNestedNestedString(NestedNestedString(NestedString("wow")))).asJson

  println(nestedJson)


  println("Now we decode the json \n")

  val res = NestedNestedNestedNestedString.decodeNestedNestedNestedNestedString.decodeJson(nestedJson)

  println(res)

  println("\nBooyah. Dont forget to delete a decoder/encoder to break this.")




}
