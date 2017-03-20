import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"
  lazy val guice = "com.google.inject" % "guice" % "4.1.0"
  lazy val scalaGuice = "net.codingwell" %% "scala-guice" % "4.1.0"
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.4.17"
  lazy val akkaStreamTest = "com.typesafe.akka" %% "akka-stream-testkit" % "2.4.17"
}

