package sock

import scala.concurrent.ExecutionContext.Implicits.global
import java.util.UUID

case class Sock(id: UUID
                , name: String
                , description: String
                , price: Int
                , count: Int
                , imageURL1: Option[String] = None
                , imageURL2: Option[String] = None
               )

/** UserComponent provides database definitions for User objects */
trait SockComponent {
  this: DriverComponent with SockTagComponent =>
  import driver.api._

  val socks = TableQuery[Socks]

  def insert(sock: Sock): DBIO[Sock] = for {
    _ <- socks.map(s => (s.id, s.name, s.description, s.price, s.count, s.imageURL1, s.imageURL2)) +=
      (sock.id, sock.name, sock.description, sock.price, sock.count, sock.imageURL1, sock.imageURL2)
  } yield sock

  class Socks(tag: slick.lifted.Tag) extends Table[(UUID, String, String, Int, Int, Option[String], Option[String])](tag, "SOCKS") {
    def * = (id, name, description, price, count, imageURL1, imageURL2)
    def id = column[UUID]("SOCK_ID", O.PrimaryKey, O.Length(38))
    def name = column[String]("NAME", O.Length(20))
    def description = column[String]("DESCRIPTION", O.Length(240))
    def price = column[Int]("PRICE")
    def count = column[Int]("COUNT")
    def imageURL1 = column[Option[String]]("IMAGE_URL1", O.Length(240))
    def imageURL2 = column[Option[String]]("IMAGE_URL2", O.Length(240))

  }

}
