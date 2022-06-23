ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "sttp-tapir-akka-server",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.0.1",
      "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % "1.0.1",
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % "1.0.1",
      "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % "1.0.1"
    )
  )
