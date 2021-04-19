package DecodingDemos.Utils

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

object ValidatedCaseClasses {

  case class Name(name: String)
  object Name {
    implicit val encodeNamePayload: Encoder[Name] = deriveEncoder[Name]
    implicit val decodeNamePayload: Decoder[Name] = deriveDecoder[Name]
  }

  case class NameStartsWithA(name: String)
  object NameStartsWithA {
    implicit val encodeNameStartsWithAPayload: Encoder[NameStartsWithA] = deriveEncoder[NameStartsWithA] // TODO encode so that it starts with only A.
    implicit val decodeNameStartsWithAPayload: Decoder[NameStartsWithA] = deriveDecoder[NameStartsWithA].validate(c => {
      val potentialName = for {
        x <- c.get[String]("name")
      } yield x

      potentialName.fold(decodingFailure => List(decodingFailure.message),
        successfulParse => {
          if(successfulParse.head.toLower != 'a')
            List("Name doesnt start with A, sorry bud.")
          else
            List()
        })
    })
  }

  case class Number(number: Int)
  object Number {
    implicit val encodeNumberPayload: Encoder[Number] = deriveEncoder[Number]
    implicit val decodeNumberPayload: Decoder[Number] = deriveDecoder[Number]
  }

  case class PositiveNumber(number: Int)
  object PositiveNumber {
    implicit val encodePositiveNumber: Encoder[PositiveNumber] = deriveEncoder[PositiveNumber] // TODO encode so that it must be positive
    implicit val decodePositiveNumber: Decoder[PositiveNumber] = deriveDecoder[PositiveNumber].validate(c => {
      val potentialNumber = for {
        x <- c.get[Int]("number")
      } yield x

      potentialNumber.fold(decodingFailure => List(decodingFailure.message),
        successfulParse => {
          if (successfulParse <= 0)
            List("Number isn't positive sorry bud.")
          else
            List()
        })
    })
  }
}
