val scala3Version = "3.3.3"

//https://www.scala-sbt.org/1.x/docs/Publishing.html
ThisBuild / versionScheme := Some("early-semver")

lazy val configExt = project
  .in(file("."))
  .settings(
    organization := "com.greenfossil",
    name := "typesafe-config-ext",
    version := "1.0.4-SNAPSHOT",
    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.3",
      "org.scalameta" %% "munit" % "1.0.0" % Test
    )
  )

