val scala3Version = "3.3.1"

lazy val configExt = project
  .in(file("."))
  .settings(
    organization := "com.greenfossil",
    name := "typesafe-config-ext",
    version := "1.0.2",
    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.3",
      "org.scalameta" %% "munit" % "0.7.29" % Test
    )
  )

