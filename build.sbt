import sbt._
import Dependencies._

organization := "org.kda"
name := "credit-approvals"
version := "0.0.1-SNAPSHOT"
scalaVersion in ThisBuild := "2.11.8"

ivyScala := ivyScala.value map { _.copy( overrideScalaVersion = true ) }

resolvers in ThisBuild ++= Seq( "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
                                "Spray IO Repository" at "http://repo.spray.io/",
                                "Maven Central" at "https://repo1.maven.org/maven2/" )

//@formatter:off
lazy val root = ( project in file( "." ) ).aggregate( creditApprovalService )

lazy val creditApprovalService = ( project in file( "credit-approvals-service" ) )
                                    .settings( libraryDependencies ++=
                                               slf4j ++
                                               logback ++
                                               drools ++
                                               kie ++
                                               junit ++
                                               junitInterface )
//@formatter:on