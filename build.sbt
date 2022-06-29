
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.2"


libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.12"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % "test"
libraryDependencies += "io.circe" % "circe-literal_3" % "0.14.2"
libraryDependencies += "org.mongodb.scala" % "mongo-scala-bson_2.13" % "4.6.0"
libraryDependencies += "org.mongodb.scala" % "mongo-scala-driver_2.13" % "4.6.0"
libraryDependencies += "com.typesafe.scala-logging" % "scala-logging-slf4j_2.10" % "2.1.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.11"


lazy val root = (project in file("."))
  .settings(
    name := "PPS-22-energy-mng"
  )
