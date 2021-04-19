package DecodingDemos.Utils

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder, HCursor}

import scala.util.Try

object FriendlyDecodersCaseClasses {

  case class StringIntPayload(schrodingersInt: String)
  object StringIntPayload{
    implicit val encodeStringPayload: Encoder[StringIntPayload] = deriveEncoder[StringIntPayload]
    implicit val decodeStringPayload: Decoder[StringIntPayload] = deriveDecoder[StringIntPayload]

    implicit val validatedDecodeStringPayload: Decoder[StringIntPayload] = deriveDecoder[StringIntPayload].validate(
      cursor => {
        val x = for {
          x <- cursor.get[String]("schrodingersInt")
        } yield Try.apply(Integer.parseInt(x))
        x.fold(decodingFailure => List(decodingFailure.message), successfulParse => {
          successfulParse.fold(error => {
            List("Failed to parse integer value " + error.getMessage)
          }, success => {
            List()
          })
        })
      }
    )
  }

  case class IntPayload(schrodingersInt: Integer)
  object IntPayload{
    implicit val encodeIntPayload: Encoder[IntPayload] = deriveEncoder[IntPayload]
    implicit val decodeIntPayload: Decoder[IntPayload] = deriveDecoder[IntPayload]
  }



  case class DesiredPayload(schrodingersInt: Integer)
  object DesiredPayload{

    def apply(intPayload: IntPayload): DesiredPayload ={
      DesiredPayload(intPayload.schrodingersInt)
    }

    def apply(stringPayload: StringIntPayload): DesiredPayload ={
      DesiredPayload(Integer.parseInt(stringPayload.schrodingersInt))
    }


    implicit val encodeDesiredPayload: Encoder[DesiredPayload] = deriveEncoder[DesiredPayload]

    /**
     * This decoder takes a json object with { "schrodingersInt" : "2" }
     * OR { "schrodingersInt" : 2 } and returns DesiredPayload(2)
     */
    val decodeDesiredPayload: Decoder[DesiredPayload] = (c: HCursor) => {

      val downfield = c.downField("schrodingersInt")
      val downfieldAsStr = downfield.as[String]
      downfieldAsStr match {
        case Left(_) =>
          IntPayload.decodeIntPayload.decodeJson(c.value).map(DesiredPayload(_))
        case Right(_) =>
          StringIntPayload.decodeStringPayload.decodeJson(c.value).map(DesiredPayload(_))
      }
    }

    val validatedDecodeDesiredPayload: Decoder[DesiredPayload]  = (c: HCursor) => {

      val downfield = c.downField("schrodingersInt")
      val downfieldAsStr = downfield.as[String]
      downfieldAsStr match {
        case Left(_) =>
          IntPayload.decodeIntPayload.decodeJson(c.value).map(DesiredPayload(_))
        case Right(_) =>
          StringIntPayload.validatedDecodeStringPayload.decodeJson(c.value).map(DesiredPayload(_))
      }
    }
  }

}
