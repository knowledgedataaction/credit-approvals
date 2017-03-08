import sbt.Keys._
import sbt._

object Dependencies {

  val slf4jVersion = "1.7.5"
  val logbackVersion = "1.1.7"

  val junitVersion = "4.12"
  val junitInterfaceVersion = "0.8"

  val droolsVersion = "6.5.0.Final"

  val akkaVersion = "2.3.9"
  val sprayVersion = "1.3.3"
  val sprayJsonVersion = "1.3.2"


  val slf4j = Seq( "org.slf4j" % "slf4j-api" % slf4jVersion )

  val akka = Seq( "com.typesafe.akka" %% "akka-actor" % akkaVersion )

  val spray = Seq( "io.spray" %% "spray-can" % sprayVersion,
                   "io.spray" %% "spray-util" % sprayVersion,
                   "io.spray" %% "spray-routing" % sprayVersion )

  val sprayJson = Seq( "io.spray" %% "spray-json" % sprayJsonVersion )

  val drools = Seq( "org.drools" % "drools-core" % droolsVersion,
                    "org.drools" % "drools-compiler" % droolsVersion )

  val kie = Seq( "org.kie" % "kie-api" % droolsVersion,
                 "org.kie" % "kie-internal" % droolsVersion )

  val logback = Seq( "ch.qos.logback" % "logback-classic" % logbackVersion )

  val junit = Seq( "junit" % "junit" % junitVersion % "test" )

  val junitInterface = Seq( "com.novocode" % "junit-interface" % junitInterfaceVersion % "test" )

}