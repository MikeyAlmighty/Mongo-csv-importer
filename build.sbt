name := "Mongo-csv-importer"

version := "0.1"

libraryDependencies ++= {
  val scalaTest: Seq[ModuleID] = {
    val version = "3.2.0"
    Seq(
      "org.scalactic" %% "scalactic"        % version,
      "org.scalatest" % "scalatest_2.13"    % version   % "test"
    )
  }

  Seq(
    "org.mongodb.scala"           %% "mongo-scala-driver"     % "2.9.0",
    "io.monix"                    %% "monix"                  % "3.2.2",
    "org.typelevel"               %% "cats-core"              % "2.1.1",
    "ch.qos.logback"              % "logback-classic"         % "1.2.3",
    "com.typesafe.scala-logging"  %% "scala-logging"          % "3.9.2",
    "com.typesafe"                % "config"                  % "1.4.0",
    "com.beachape"                %% "enumeratum"             % "1.6.1",
  ) ++ scalaTest
}

scalaVersion := "2.13.2"

//http://www.scalatest.org/user_guide/using_scalatest_with_sbt
logBuffered in Test := false
