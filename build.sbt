val scala3Version = "3.2.1"

lazy val configExt = project
  .in(file("."))
  .settings(
    organization := "com.greenfossil",
    name := "typesafe-config-ext",
    version := "1.0.0",
    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.2",
      "org.scalameta" %% "munit" % "0.7.29" % Test
    )
  )

