package sock

import slick.jdbc.JdbcProfile

/** The Data Access Layer contains all components and a driver */
class DAL(val driver: JdbcProfile)
      extends SockComponent with SockTagComponent with TagComponent with DriverComponent {
  import driver.api._

  def create = (socks.schema ++ sockTags.schema ++ tags.schema).create
}
