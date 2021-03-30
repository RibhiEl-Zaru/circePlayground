package DecodingDemos

import io.circe.{Decoder, HCursor}

object CustomCoolness extends App {

  class Thing(val foo: String, val bar: Int)


  implicit val decodeFoo: Decoder[Thing] = new Decoder[Thing] {
    final def apply(c: HCursor): Decoder.Result[Thing] = {
      val z = c.downField("foo").as[String]


      for {
        foo <- c.downField("foo").as[String]
        bar <- c.downField("bar").as[Int]
      } yield {
        new Thing(foo, bar)
      }
    }
  }

}
