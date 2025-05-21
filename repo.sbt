//https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html

ThisBuild / organizationName := "Greenfossil Pte Ltd"
ThisBuild / organizationHomepage := Some(url("https://greenfossil.com/"))

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/greenfossil/typesafe-config-ext"),
    "scm:git@github.com:greenfossil/typesafe-config-ext.git"
  )
)

ThisBuild / homepage := Some(url("https://github.com/Greenfossil/typesafe-config-ext"))

ThisBuild / description := "A Scala 3 extension for Typesafe Config library"

ThisBuild / licenses := List(
  "Apache 2" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt")
)

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishMavenStyle := true

ThisBuild / developers := List(
  Developer(
    "greenfossil",
    "Greenfossil Pte Ltd",
    "devadmin@greenfossil.com",
    url("https://github.com/Greenfossil")
  )
)

ThisBuild / publishTo := {
  val centralSnapshots = "https://central.sonatype.com/repository/maven-snapshots/"
  if (isSnapshot.value) Some("central-snapshots" at centralSnapshots)
  else localStaging.value
}

val username = sys.env.getOrElse("PUBLISH_USER", "")
val password = sys.env.getOrElse("PUBLISH_PASSWORD", "")

ThisBuild / credentials += Credentials(Path.userHome / ".sbt" / "sonatype_central_credentials")

ThisBuild / credentials += Credentials(
  "Sonatype Nexus Repository Manager", "central.sonatype.com", username, password
)

credentials += {
//  val file = Path.userHome / ".sbt" / "sonatype_central_credentials"
//  if (file.exists) Credentials(file)
//  else
  Credentials(
    "Sonatype Nexus Repository Manager",
    "central.sonatype.com",
    sys.env.getOrElse("PUBLISH_USER", ""),
    sys.env.getOrElse("PUBLISH_PASSWORD", "")
  )
}
