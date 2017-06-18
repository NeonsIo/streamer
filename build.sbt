import Dependencies._

resolvers += "Maven snapshots" at "https://repository.apache.org/content/repositories/snapshots"

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
      scalaGuice,
      scalaUri,
      flinkScala,
      flinkStreaming,
      flinkClients,
      flinkKafka,
      flinkElasticsearch,
      json4sNative,
      deviceDetectorLib,
      maxMindGeoIp2,
      "org.apache.maven.plugins" % "maven-shade-plugin" % "3.0.0",
      "org.slf4j" % "slf4j-simple" % "1.7.24",
      "org.apache.logging.log4j" % "log4j-api" % "2.8.1",
      "org.apache.logging.log4j" % "log4j-core" % "2.8.1"
    ),
    mainClass in Compile := Some("io.neons.streamer.Application")
  )

