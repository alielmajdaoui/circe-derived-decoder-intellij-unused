package game.domain

import io.circe.{Decoder, HCursor}
import io.circe.generic.semiauto.deriveDecoder
import user.domain.User
import user.domain.UserCodec._

case class Game(id: Int, user: User)

object GameCodec {

  val gameDecoder: Decoder[Game] =
    (c: HCursor) =>
      for {
        gameId <- c.downField("id").as[Int]
        user   <- c.downField("the_user").as[User]
      } yield Game(gameId, user)

  implicit val allGameDecoders: Decoder[Game] =
    deriveDecoder[Game] or gameDecoder
}
