import sbt.Keys._

name := "lotus"
version := "1.1.0"
scalaVersion := "2.12.6"
organization := "io.lotus"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

lazy val scalaGuice = "net.codingwell" %% "scala-guice" % "4.2.1"
lazy val scalaTestPlus = "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2"

libraryDependencies ++= Seq(guice, scalaGuice, scalaTestPlus % Test)

coverageExcludedPackages := "<empty>;controllers.javascript;router"

dockerExposedPorts := Seq(9000)
dockerBaseImage := "openjdk:jre-alpine"
dockerLabels := Map[String, String]("maintainer" -> "vsabreu.dev@gmail.com")

packageName in Docker := "vsabreu/lotus"
version in Docker := "1.1.0"

enablePlugins(AshScriptPlugin)
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)