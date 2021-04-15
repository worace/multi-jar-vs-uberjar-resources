ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "2.12.13"
ThisBuild / version      := "0.1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "com.example" %% "lib-a" % "0.1.0-SNAPSHOT",
  "com.example" %% "lib-b" % "0.1.0-SNAPSHOT"
)
