import Dependencies._

ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val akkaVersion = "2.5.25"

val reactiveMongo = "org.reactivemongo" %% "reactivemongo" % "0.18.6"
val slf4jApi = "org.slf4j" % "slf4j-api" % "1.7.28"
val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion

val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test

lazy val root = (project in file("."))
  .settings(
    name := "hello_scala",
    libraryDependencies += scalaTest % Test
  )
  .aggregate(`introduction`, `ninety-nine-scala-problems`, `reactivemongo-getstarted`, `throttle-stream-actor`, `throttle-stream-graph`)

lazy val `introduction` = (project in file("introduction"))
  .settings(libraryDependencies ++= {
    Seq(scalaTest % Test)
  })
lazy val `reactivemongo-getstarted` = (project in file("reactivemongo-getstarted"))
  .settings(libraryDependencies ++= {
    Seq(reactiveMongo, slf4jApi)
  })
lazy val `throttle-stream-actor` = (project in file("throttle-stream-actor"))
  .settings(libraryDependencies ++= {
    Seq(akkaActor, akkaTestKit, scalaTest, akkaStream)
  })
lazy val `throttle-stream-graph` = (project in file("throttle-stream-graph"))
  .settings(libraryDependencies ++= {
    Seq(akkaActor, akkaTestKit, scalaTest, akkaStream)
  })

lazy val `ninety-nine-scala-problems` = (project in file("ninety-nine-scala-problems"))
  .settings(libraryDependencies ++= {
    Seq(scalaTest % Test)
  })

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
