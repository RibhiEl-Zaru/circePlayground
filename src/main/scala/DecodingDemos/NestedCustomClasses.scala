package DecodingDemos

import DecodingDemos.Utils.CustomCaseClasses.AlteredValues
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.EncoderOps


object NestedCustomClasses extends App {

  println("One pain point all circe users go through is when you have to decode a custom case class within a custom case class within a custom case class within a custom case class within a custom case class within a custom case class within a custom case class.\n")

  println("\n This is how you do it.")

  case class NestedString(x: String)
  case class NestedNestedString(nested: NestedString)
  case class NestedNestedNestedString(nested: NestedNestedString)
  case class NestedNestedNestedNestedString(nested: NestedNestedNestedString)

  object NestedString {
    implicit val encodeNestedString: Encoder[NestedString] = deriveEncoder[NestedString]
    implicit val decodeNestedString: Decoder[NestedString] = deriveDecoder[NestedString]
  }

}
