import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.3"
  lazy val scalaMock = "org.mockito" % "mockito-all" % "1.10.19"
  lazy val scalaGuice = "net.codingwell" %% "scala-guice" % "4.1.0"
  lazy val flinkScala = "org.apache.flink" %% "flink-scala" % "1.3.1"
  lazy val flinkClients = "org.apache.flink" %% "flink-clients" % "1.3.1"
  lazy val flinkStreaming = "org.apache.flink" %% "flink-streaming-scala" % "1.3.1"
  lazy val flinkKafka = "org.apache.flink" % "flink-connector-kafka-0.10_2.10" % "1.3.1"
  lazy val flinkElasticsearch = "org.apache.flink" % "flink-connector-elasticsearch5_2.10" % "1.3.1"
  lazy val json4sNative = "org.json4s" %% "json4s-native" % "3.5.0"
  lazy val scalaUri = "com.netaporter" %% "scala-uri" % "0.4.16"
  lazy val deviceDetectorLib = "com.51degrees" % "51Degrees.detection.core" % "3.2.1.9-beta"
  lazy val maxMindGeoIp2 = "com.maxmind.geoip2" % "geoip2" % "2.8.1"
  lazy val flinkTable = "org.apache.flink" % "flink-table_2.10" % "1.3.1"
  lazy val cassandra = "org.apache.flink" % "flink-connector-cassandra_2.10" % "1.3.1"

}
