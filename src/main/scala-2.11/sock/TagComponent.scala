package sock

import scala.concurrent.ExecutionContext.Implicits.global

case class Tag(tagId: Int, name: String)


/** PictureComponent provides database definitions for Picture objects */
trait TagComponent {
  this: DriverComponent =>

  import driver.api._

  val tags = TableQuery[Tags]

  def insert(tag: sock.Tag): DBIO[sock.Tag] = (tags += tag).map(_ => tag)

  class Tags(tag: slick.lifted.Tag) extends Table[sock.Tag](tag, "TAG") {
    def * = (tagId, name) <> (sock.Tag.tupled, sock.Tag.unapply)
    def name = column[String]("NAME", O.Length(24))
    def tagId = column[Int]("TAG_ID", O.PrimaryKey)
    def unique = index("idx_name", name, unique = true)
  }

}
