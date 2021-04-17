package DecodingDemos.Utils

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder, HCursor}

import scala.util.Try

object FriendlyDecodersCaseClasses {

  case class StringPayload(schrodingersInt: String)
  object StringPayload{
    implicit val encodeStringPayload: Encoder[StringPayload] = deriveEncoder[StringPayload]
    implicit val decodeStringPayload: Decoder[StringPayload] = deriveDecoder[StringPayload]
  }

  case class IntPayload(schrodingersInt: Integer)
  object IntPayload{
    implicit val encodeIntPayload: Encoder[IntPayload] = deriveEncoder[IntPayload]
    implicit val decodeIntPayload: Decoder[IntPayload] = deriveDecoder[IntPayload]
  }



  case class DesiredPayload(schrodingersInt: Integer)
  object DesiredPayload{
    implicit val encodeDesiredPayload: Encoder[DesiredPayload] = deriveEncoder[DesiredPayload]

    /**
     * This decoder takes a json object with { "schrodingersInt" : "2" }
     * OR { "schrodingersInt" : 2 } and returns DesiredPayload(2)
     */
    val decodeDesiredPayload: Decoder[DesiredPayload] = (c: HCursor) => {

      val downfield = c.downField("schrodingersInt")
      val downfieldAsStr = downfield.as[String]
      downfieldAsStr match {
        case Left(_) => for {
          z <- downfield.as[Int]
        } yield DesiredPayload(z)
        case Right(_) =>
          for {
            str <- downfieldAsStr
          } yield DesiredPayload(Integer.valueOf(str))

      }
    }
  }

}
