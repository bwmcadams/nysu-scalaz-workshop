lazy val commonSettings = Seq(
  version := "1.0.0",
  scalaVersion := "2.11.7",
  scalacOptions ++= compileOptions,
  parallelExecution in Test := false,
  fork in Test := true,
  libraryDependencies ++= Seq(
    "org.typelevel" %% "scalaz-scalatest" % "0.3.0" % "test",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "org.scalaz" %% "scalaz-core" % "7.1.4"
  )
)

lazy val disjunctions = (project in file("disjunctions")).
  settings(commonSettings).
  settings(
    name := "scalaz-workshop-disjunctions"
  )

lazy val validation = (project in file("validation")).
  settings(commonSettings).
  settings(
    name := "scalaz-workshop-validation"
  )

val compileOptions = Seq(
  "-deprecation",
  "-unchecked"
)
