ThisBuild / organizationName := "Greenfossil Pte Ltd"
ThisBuild / organizationHomepage := Some(url("https://greenfossil.com/"))

ThisBuild / developers := List(
  Developer(
    "greenfossil",
    "Greenfossil Pte Ltd",
    "devadmin@greenfossil.com",
    url("https://github.com/Greenfossil")
  )
)

ThisBuild / licenses := List(
  "Apache 2" -> new URL("https://www.apache.org/licenses/LICENSE-2.0.txt")
)

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }

ThisBuild / publishTo := {
  // For accounts created after Feb 2021:
  //https://central.sonatype.org/publish/publish-portal-ossrh-staging-api/#plugins-tested-for-compatibility
  if (isSnapshot.value) Some("snapshots" at "https://central.sonatype.com/repository/maven-snapshots/")
  else Some("releases" at "https://ossrh-staging-api.central.sonatype.com/service/local/")
}

val username = sys.env.getOrElse("PUBLISH_USER", "")
val password = sys.env.getOrElse("PUBLISH_PASSWORD", "")

ThisBuild / credentials += Credentials(
  "Sonatype Nexus Repository Manager", "central.sonatype.com", username, password
)

ThisBuild / publishMavenStyle := true
