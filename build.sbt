import Dependencies._

enablePlugins(DockerPlugin)

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "neons",
      scalaVersion := "2.11.7",
      version      := "0.0.5"
    )),
    name := "streamer",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      scalaMock,
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
    mainClass in Compile := Some("io.neons.streamer.application.flink.Application")
  )

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

dockerfile in docker := {
  val artifact: File = assembly.value
  val artifactTargetPath = s"/app/${artifact.name}"

  new Dockerfile {
    from("flink")
    add(artifact, artifactTargetPath)
  }
}

imageNames in docker := Seq(
  ImageName(
    namespace = Some(organization.value),
    repository = name.value,
    tag = Some(version.value)
  )
)

buildOptions in docker := BuildOptions(
  cache = false,
  removeIntermediateContainers = BuildOptions.Remove.Always,
  pullBaseImage = BuildOptions.Pull.Always
)
