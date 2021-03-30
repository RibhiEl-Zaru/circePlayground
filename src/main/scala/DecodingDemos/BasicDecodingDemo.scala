package DecodingDemos

import io.circe.Decoder.Result

object BasicDecodingDemo extends App {
  import io.circe.parser.decode
  import io.circe.syntax._

  val intsJson = List(1, 2, 3).asJson

  println("Heres a quick lesson of the power of decoding")

  val goodEncoding: Result[List[Int]] = intsJson.as[List[Int]]

  println("Decode it to the RIGHT type, and u get the right thing. Look here")

  println(goodEncoding)


  val badEncoding: Result[List[String]] = intsJson.as[List[String]]

  println("\nDecode it to the WRONG type, and things go Left. Look!")
  println(badEncoding)

  println("\n\n So basically, you can take a fork in the road based on circe encoding.\n" +
    "If things are able to encode into an object, you can proceed as usual.\n" +
    "But! If things go wrong, its easy to respond.")


  println("\n\n Oh! Almost forgot to mention. If u ever need to handle raw JSON, circe abstracts that real quick with the decode method wow.")

  val decodeRightFromStr = decode[List[Int]]("[1, 2, 3]")

  println(decodeRightFromStr)


  println("Ugh one LAST thing. The reason why we haven't used any custom case-classes is cuz that requires some custom decoders/encoders.\n" +
    "And I talk about that in the next file! DecodingCustomClasses. wow what a treat")


}
