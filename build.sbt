val scala3Version = "3.3.3"

lazy val configExt = project
  .in(file("."))
  .settings(
    organization := "com.greenfossil",
    name := "typesafe-config-ext",
    version := "1.0.4",
    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.3",
      "org.scalameta" %% "munit" % "1.0.0" % Test
    )
  )

