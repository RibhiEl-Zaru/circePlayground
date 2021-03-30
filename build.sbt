lazy val root = (project in file("."))
  .settings(
    name := "Circe Playground",
    moduleName := "circePlayground",
    scalaVersion := "2.12.11"
  )

val circeVersion = "0.12.3"


libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)