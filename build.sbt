import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.neons",
      scalaVersion := "2.12.1",
      version      := "0.0.1"
    )),
    name := "Streamer",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      guice,
      scalaGuice,
      akkaStream,
      akkaStreamTest
    ),
    mainClass in Compile := Some("io.neons.streamer.Application")
  )
