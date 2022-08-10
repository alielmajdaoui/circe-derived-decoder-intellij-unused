import io.circe.{Decoder, HCursor}
import io.circe.parser._
import game.domain.Game
import game.domain.GameCodec._

object Solution1 extends App {

  case class Response(game: Game)

  // Don't use derived decoder for Response but explicitly decode it
  implicit val responseExplicitDecoder: Decoder[Response] =
    (c: HCursor) => c.get[Game]("game").map(Response)
  // OR
  // (c: HCursor) => c.as[Game].map(Response)
  // if you don't want `game` field in the json

  val gameRawJson =
    """
      |{ "game": {
      |      "id": 1, "the_user": { "id": 2, "name": "Sam" }
      |} } 
      |""".stripMargin
  val decoded = decode[Response](gameRawJson)

  println(decoded)
}
