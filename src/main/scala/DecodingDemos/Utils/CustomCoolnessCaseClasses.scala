package DecodingDemos.Utils

import io.circe.generic.semiauto.deriveEncoder
import io.circe.{Decoder, Encoder, HCursor}

object CustomCoolnessCaseClasses {

  case class AlteredValues(foo: String, bar: Int)


  implicit val encodeAlteredValue: Encoder[AlteredValues] = deriveEncoder[AlteredValues]

  /**
   * This decoder takes a json object with { "foo" : "string", "bar" : 2 } and returns a case class with the
   * string duplicated and the integer doubled.
   */
  val decodeAndDouble: Decoder[AlteredValues] = (c: HCursor) => for {
    foo <- c.downField("foo").as[String]
    bar <- c.downField("bar").as[Int]
  } yield AlteredValues(foo + foo, bar * 2)

  /**
   * This returns a fixed and nihilist AlteredValue case class.
   */
  val nihilistDecoder: Decoder[AlteredValues] = Decoder.const(AlteredValues("\"Everything in the world displeases me: but, above all, my displeasure in everything displeases me", 666))


}
