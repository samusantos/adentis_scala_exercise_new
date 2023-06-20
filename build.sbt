ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "adentis_scale_exercise_new",
    idePackagePrefix := Some("com.samuel")
  )

libraryDependencies += "org.postgresql" % "postgresql" % "42.6.0"
