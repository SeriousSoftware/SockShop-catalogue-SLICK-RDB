package sock

import java.util.UUID

import slick.lifted.{Tag => SlickTag}

import scala.concurrent.ExecutionContext.Implicits.global

/** A Picture contains an ID and a URL pointing to an image file */
case class SockTag(sockId: UUID, tagId: Int)

/** PictureComponent provides database definitions for Picture objects */
trait SockTagComponent {
  this: DriverComponent with SockComponent with TagComponent=>

  import driver.api._

  val sockTags = TableQuery[SockTags]

  def insert(sockTag: SockTag): DBIO[SockTag] = (sockTags += sockTag).map(_ => sockTag)

  class SockTags(tag: SlickTag) extends Table[SockTag](tag, "SOCK_TAG") {
    def * = (sockId, tagId) <> (SockTag.tupled, SockTag.unapply)
    def tagId = column[Int]("TAG_ID")
    def sockId = column[UUID]("SOCK_ID", O.Length(38))

    def pk = primaryKey("PK_SOCK_TAG", (tagId, sockId))
    def socksFK =
      foreignKey("FK_SOCK", sockId, socks)(_.id, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
    def tabsFK =
      foreignKey("FK_TAG", tagId, tags)(_.tagId, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Cascade)
  }

}
