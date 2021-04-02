package DecodingDemos.Utils

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

object NestedCaseClass {

  case class NestedString(x: String)
  case class NestedNestedString(nested: NestedString)
  case class NestedNestedNestedString(nested: NestedNestedString)
  case class NestedNestedNestedNestedString(nested: NestedNestedNestedString)

  object NestedString {
    implicit val encodeNestedString: Encoder[NestedString] = deriveEncoder[NestedString]
    implicit val decodeNestedString: Decoder[NestedString] = deriveDecoder[NestedString]
  }

  object NestedNestedString {
    implicit val encodeNestedNestedString: Encoder[NestedNestedString] = deriveEncoder[NestedNestedString]
    implicit val decodeNestedNestedString: Decoder[NestedNestedString] = deriveDecoder[NestedNestedString]
  }

  object NestedNestedNestedString {
    implicit val encodeNestedNestedString: Encoder[NestedNestedNestedString] = deriveEncoder[NestedNestedNestedString]
    implicit val decodeNestedNestedString: Decoder[NestedNestedNestedString] = deriveDecoder[NestedNestedNestedString]
  }

  object NestedNestedNestedNestedString {
    implicit val encodeNestedNestedNestedNestedString: Encoder[NestedNestedNestedNestedString] = deriveEncoder[NestedNestedNestedNestedString]
    implicit val decodeNestedNestedNestedNestedString: Decoder[NestedNestedNestedNestedString] = deriveDecoder[NestedNestedNestedNestedString]
  }
}
