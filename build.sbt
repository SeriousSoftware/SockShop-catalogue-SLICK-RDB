name := "slick-multidb"

version := "1.0"

scalaVersion := "2.11.8"

scalacOptions += "-deprecation"

mainClass in Compile := Some("sock.MultiDBCakeExample")

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "3.2.0-M1",
  "org.slf4j" % "slf4j-nop" % "1.7.21",
  "com.h2database" % "h2" % "1.4.193",
  "org.xerial" % "sqlite-jdbc" % "3.15.1"
)

fork in run := true
