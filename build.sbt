val scala3Version = "3.6.3"

//https://www.scala-sbt.org/1.x/docs/Publishing.html
ThisBuild / versionScheme := Some("early-semver")

lazy val configExt = project
  .in(file("."))
  .settings(
    organization := "com.greenfossil",
    name := "typesafe-config-ext",
    version := "1.2.0",
    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.3",
      "org.scalameta" %% "munit" % "1.1.0" % Test
    )
  )

