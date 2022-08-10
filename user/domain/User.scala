package user.domain

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

case class User(id: Int, name: String)

object UserCodec {

  implicit val userDecoder: Decoder[User] =
    deriveDecoder[User]

}
