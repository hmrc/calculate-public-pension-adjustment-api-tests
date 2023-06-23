import sbt._

object Dependencies {

  val test = Seq(
    "org.scalatest"       %% "scalatest"               % "3.2.0"   % Test,
    "com.vladsch.flexmark" % "flexmark-all"            % "0.35.10" % Test,
    "com.typesafe"         % "config"                  % "1.3.2"   % Test,
    "com.typesafe.play"   %% "play-ahc-ws-standalone"  % "2.1.2"   % Test,
    "org.slf4j"            % "slf4j-simple"            % "1.7.25"  % Test,
    "com.typesafe.play"   %% "play-ws-standalone-json" % "2.1.2"   % Test,
    "org.scalatest"       %% "scalatest"               % "3.2.9"   % Test,
    "io.circe"            %% "circe-core"              % "0.14.1"  % Test,
    "io.circe"            %% "circe-parser"            % "0.14.1"  % Test
  )
}
