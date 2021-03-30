package DecodingDemos

import io.circe.Json
import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.parse
import io.circe.syntax._

object TheProblemWithOptions extends App {

  case class OptionList(badList: List[Option[String]])

  val optionListDecoder = deriveDecoder[OptionList]

  val listWithUnforcedType = List("2", 1, "3", 4)

  val badJson = """{"badList" : [1, "2", 3, "4"]}""".stripMargin
  val goodJson = Map("badList" -> List("1", "2", "3", "4"))


  println("Problem 1 with Options: Derive Decoder does not work smart with them.")
  println("If objects don't decode to the type T nested in Option[T], then watch out. For example...")

  val json = parse(badJson).getOrElse(Json.Null)

  val badDecodedList = optionListDecoder.decodeJson(json)
  println(badDecodedList)
  println("\n\nSee? On Failure to decode a string, instead of defaulting to None, we get a decoding error.")
  println("So when paired with DeriveDecoder, having an option in a List for circe to decode is a waste of time")

  println("\n\n However we're still only able to decode the good list, but the Option wrapper is basically useless")

  val wellDecodedList = optionListDecoder.decodeJson(goodJson.asJson)

  println(wellDecodedList)


  println("\n\nAlright... well is there any way to use an Option well?")


  println("Yes! You can use it to decode values that don't necessarily need to exist in the json very well. Looksie")

  case class OptionField(badField: Option[List[String]])

  val optionFieldDecoder = deriveDecoder[OptionField]


  val optionDecodedList = optionFieldDecoder.decodeJson(json)

  println(optionDecodedList)

  println("See! The field is None, which is good because there was no mapping field")


  println("\n\n Anywho, the lesson of this is that options can be good, but don't put them in lists!")


}
