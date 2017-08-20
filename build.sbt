import Dependencies._

enablePlugins(DockerPlugin)

val getDeviceDetectorData = taskKey[Unit]("download device detector db")
val getGeoIpData = taskKey[Unit]("download geo ip data")

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "neons",
      scalaVersion := "2.11.7",
      version      := "0.0.8"
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
    mainClass in Compile := Some("io.neons.streamer.application.flink.Application"),

    getDeviceDetectorData := {
      println("Start downloading device detector data")
      IO.download(
        new URL("https://github.com/51Degrees/Device-Detection/raw/master/data/51Degrees-LiteV3.2.dat"),
        new File("src/main/resources/51Degrees.dat")
      )
    },

    getGeoIpData := {
      println("Start downloading geo ip data")
      IO.download(
        new URL("https://github.com/maxmind/MaxMind-DB-Reader-dotnet/raw/master/MaxMind.Db.Benchmark/GeoLite2-City.mmdb"),
        new File("src/main/resources/GeoLite2-City.mmdb")
      )
    }

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
