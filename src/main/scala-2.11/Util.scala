import java.sql.DriverManager
import java.util.UUID

import sock.{Sock, SockTag, Tag}

import scala.collection.JavaConverters._

package object Util {
  def tagsList = Seq(Tag(1, "brown"), Tag(2, "geek"), Tag(3, "formal"), Tag(4, "blue"), Tag(5, "skin"),
    Tag(6, "red"), Tag(7, "action"), Tag(8, "sport"), Tag(9, "black"), Tag(10, "magic"), Tag(11, "green"))

  def socksList = Seq(
    Sock(UUID.fromString("a0a4f044-b040-410d-8ead-4de0446aec7e"), "Nerd leg", "For all those leg lovers out there. A perfect example of a swivel chair trained calf. Meticulously trained on a diet of sitting and Pina Coladas. Phwarr...", 799, 115, Some("/catalogue/images/bit_of_leg_1.jpeg"), Some("/catalogue/images/bit_of_leg_2.jpeg")),
    Sock(UUID.fromString("808a2de1-1aaa-4c25-a9b9-6612e8f29a38"), "Crossed", "A mature sock, crossed, with an air of nonchalance.", 1732, 738, Some("/catalogue/images/cross_1.jpeg"), Some("/catalogue/images/cross_2.jpeg")),
    Sock(UUID.fromString("510a0d7e-8e83-4193-b483-e27e09ddc34d"), "SuperSport XL", "Ready for action. Engineers: be ready to smash that next bug! Be ready, with these super-action-sport-masterpieces. This particular engineer was chased away from the office with a stick.", 1500, 820, Some("/catalogue/images/puma_1.jpeg"), Some("/catalogue/images/puma_2.jpeg")),
    Sock(UUID.fromString("03fef6ac-1896-4ce8-bd69-b798f85c6e0b"), "Holy", "Socks fit for a Messiah. You too can experience walking in water with these special edition beauties. Each hole is lovingly proggled to leave smooth edges. The only sock approved by a higher power.", 9999, 1, Some("/catalogue/images/holy_1.jpeg"), Some("/catalogue/images/holy_2.jpeg")),
    Sock(UUID.fromString("d3588630-ad8e-49df-bbd7-3167f7efb246"), "YouTube.sock", "We were not paid to sell this sock. It's just a bit geeky.", 1099, 801, Some("/catalogue/images/youtube_1.jpeg"), Some("/catalogue/images/youtube_2.jpeg")),
    Sock(UUID.fromString("819e1fbf-8b7e-4f6d-811f-693534916a8b"), "Figueroa", "enim officia aliqua excepteur esse deserunt quis aliquip nostrud anim", 14, 808, Some("/catalogue/images/WAT.jpg"), Some("/catalogue/images/WAT2.jpg")),
    Sock(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"), "Classic", "Keep it simple.", 12, 127, Some("/catalogue/images/classic.jpg"), Some("/catalogue/images/classic2.jpg")),
    Sock(UUID.fromString("3395a43e-2d88-40de-b95f-e00e1502085b"), "Colourful", "proident occaecat irure et excepteur labore minim nisi amet irure", 18, 438, Some("/catalogue/images/colourful_socks.jpg"), Some("/catalogue/images/colourful_socks.jpg")),
    Sock(UUID.fromString("837ab141-399e-4c1f-9abc-bace40296bac"), "Cat socksList", "consequat amet cupidatat minim laborum tempor elit ex consequat in", 15, 175, Some("/catalogue/images/catsocks.jpg"), Some("/catalogue/images/catsocks2.jpg"))
  )

  def sockTagsList = Seq(
    SockTag(UUID.fromString("a0a4f044-b040-410d-8ead-4de0446aec7e"), 4)
    , SockTag(UUID.fromString("a0a4f044-b040-410d-8ead-4de0446aec7e"), 5)
    , SockTag(UUID.fromString("808a2de1-1aaa-4c25-a9b9-6612e8f29a38"), 4)
    , SockTag(UUID.fromString("808a2de1-1aaa-4c25-a9b9-6612e8f29a38"), 6)
    , SockTag(UUID.fromString("808a2de1-1aaa-4c25-a9b9-6612e8f29a38"), 7)

    , SockTag(UUID.fromString("808a2de1-1aaa-4c25-a9b9-6612e8f29a38"), 3)
    , SockTag(UUID.fromString("510a0d7e-8e83-4193-b483-e27e09ddc34d"), 8)
    , SockTag(UUID.fromString("510a0d7e-8e83-4193-b483-e27e09ddc34d"), 9)
    , SockTag(UUID.fromString("510a0d7e-8e83-4193-b483-e27e09ddc34d"), 3)
    , SockTag(UUID.fromString("03fef6ac-1896-4ce8-bd69-b798f85c6e0b"), 10)

    , SockTag(UUID.fromString("03fef6ac-1896-4ce8-bd69-b798f85c6e0b"), 7)
    , SockTag(UUID.fromString("d3588630-ad8e-49df-bbd7-3167f7efb246"), 2)
    , SockTag(UUID.fromString("d3588630-ad8e-49df-bbd7-3167f7efb246"), 3)
    , SockTag(UUID.fromString("819e1fbf-8b7e-4f6d-811f-693534916a8b"), 3)
    , SockTag(UUID.fromString("819e1fbf-8b7e-4f6d-811f-693534916a8b"), 11)

    , SockTag(UUID.fromString("819e1fbf-8b7e-4f6d-811f-693534916a8b"), 4)
    , SockTag(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"), 1)
    , SockTag(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"), 11)
    , SockTag(UUID.fromString("3395a43e-2d88-40de-b95f-e00e1502085b"), 1)
    , SockTag(UUID.fromString("3395a43e-2d88-40de-b95f-e00e1502085b"), 4)

    , SockTag(UUID.fromString("837ab141-399e-4c1f-9abc-bace40296bac"), 1)
    , SockTag(UUID.fromString("837ab141-399e-4c1f-9abc-bace40296bac"), 11)
    , SockTag(UUID.fromString("837ab141-399e-4c1f-9abc-bace40296bac"), 3))

  /** A helper function to unload all JDBC drivers so we don't leak memory */
  def unloadDrivers() {
    DriverManager.getDrivers.asScala.foreach { d =>
      DriverManager.deregisterDriver(d)
    }
  }
}
