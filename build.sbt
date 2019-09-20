import Dependencies._

ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

val reactiveMongo = "org.reactivemongo" %% "reactivemongo" % "0.18.6"

lazy val root = (project in file("."))
  .settings(
    name := "hello_scala",
    libraryDependencies += scalaTest % Test
  )
  .aggregate(`introduction`, `reactivemongo-getstarted`)

lazy val `introduction` = (project in file("introduction"))
  .settings(libraryDependencies ++= {
    Seq(scalaTest % Test)
  })
lazy val `reactivemongo-getstarted` = (project in file("reactivemongo-getstarted"))
  .settings(libraryDependencies ++= {
    Seq(reactiveMongo)
  })


// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
