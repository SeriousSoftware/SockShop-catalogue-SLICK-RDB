package sock

import slick.jdbc.H2Profile
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object MultiDBCakeExample extends App {
  def run(dal: DAL, db: Database): Future[Unit] = {
    import dal.driver.api._

    println("Running test against " + dal.driver)

    // Monadic composition
    val dbio = for {
      _ <- dal.create
      _ <- DBIO.seq(Util.socksList.map(row => dal.insert(row)): _*)
      _ <- DBIO.seq(Util.tagsList.map(row => dal.insert(row)): _*)
      _ <- DBIO.seq(Util.sockTagsList.map(row => dal.insert(row)): _*)

      socks_ <- dal.socks.size.result
      tags_ <- dal.tags.size.result
      sockTags_ <- dal.sockTags.size.result
      _ = println("- All socksList: " + socks_ + " " + tags_ + " " + sockTags_)

      _ = dal.sockTags.schema.create.statements.foreach(println)

      rec <- (for {
        s <- dal.socks
        st <- dal.sockTags
        t <- dal.tags
        if s.id === st.sockId && st.tagId === t.tagId
      } yield (s.name, t.name)).sortBy(_._1).result

      _ = rec.foreach(println(_))

    } yield ()
    db.run(dbio.withPinnedSession)
  }

  try {
    val fut = {
      val h2db = Database.forConfig("h2")
      run(new DAL(H2Profile), h2db).andThen { case _ => h2db.close }
    } /*.flatMap { _ =>
      val sqlitedb = Database.forConfig("sqlite")
      run(new DAL(SQLiteProfile), sqlitedb).andThen { case _ => sqlitedb.close }
    }*/

    Await.result(fut, atMost = Duration.Inf)
  } finally Util.unloadDrivers()

}
