import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.neons",
      scalaVersion := "2.11.7",
      version      := "0.0.1"
    )),
    name := "Streamer",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      guice,
      scalaGuice,
      flinkScala,
      flinkStreaming,
      flinkClients,
      flinkKafka,
      json4sNative,
      cassandra,
      scalaUri,
      flinkTable
    ),
    mainClass in Compile := Some("io.neons.streamer.Application")
  )
