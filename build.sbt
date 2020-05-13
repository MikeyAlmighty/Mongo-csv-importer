name := "Mongo-csv-importer"

version := "0.1"

libraryDependencies ++= Seq(
  "org.mongodb.scala"           %% "mongo-scala-driver"     % "2.9.0",
  "io.monix"                    %% "monix"                  % "3.2.1",
  "org.typelevel"               %% "cats-core"              % "2.1.1",
  "ch.qos.logback"              % "logback-classic"         % "1.2.3",
  "com.typesafe.scala-logging"  %% "scala-logging"          % "3.9.2",
  "com.typesafe"                % "config"                  % "1.4.0"
)

scalaVersion := "2.13.2"
