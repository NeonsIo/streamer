import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"
  lazy val guice = "com.google.inject" % "guice" % "4.1.0"
  lazy val scalaGuice = "net.codingwell" %% "scala-guice" % "4.1.0"
  lazy val flinkScala = "org.apache.flink" %% "flink-scala" % "1.2.0"
  lazy val flinkClients = "org.apache.flink" %% "flink-clients" % "1.2.0"
  lazy val flinkStreaming = "org.apache.flink" %% "flink-streaming-scala" % "1.2.0"
  lazy val flinkKafka = "org.apache.flink" % "flink-connector-kafka-0.10_2.10" % "1.2.0"
  lazy val json4sNative = "org.json4s" %% "json4s-native" % "3.5.0"
  lazy val cassandra = "org.apache.flink" % "flink-connector-cassandra_2.11" % "1.2.0"
  lazy val scalaUri = "com.netaporter" %% "scala-uri" % "0.4.16"
}

