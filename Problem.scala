import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser._
import game.domain.Game
// The next line is marked as not used by IntelliJ but
// the Response derived decoder needs it for decoding its
// nested case class Game.
import game.domain.GameCodec._

object Problem extends App {

  case class Response(game: Game)

  // If we don't import import `GameCodec._`, the next line will throw:
  // `could not find Lazy implicit value of type
  // io.circe.generic.decoding.DerivedDecoder[UnusedImplicitsDemo.Response]`
  implicit val responseDecoder: Decoder[Response] =
    deriveDecoder[Response]

  val gameRawJson =
    """
      |{ "game": { "id": 1, "the_user": { "id": 2, "name": "Sam" } } }
      |""".stripMargin
  val decoded = decode[Response](gameRawJson)

  println(decoded)
}
