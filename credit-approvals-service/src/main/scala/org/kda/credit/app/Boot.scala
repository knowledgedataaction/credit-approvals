package org.kda.credit.app

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._
import scala.util.Properties

import org.kda.credit.service.CreditApprovalsServiceActor

object Boot extends App {
  implicit val system = ActorSystem( "credit-approvals-serviceF" )
  val service = system.actorOf( Props[ CreditApprovalsServiceActor ], "credit-approvals-service" )

  implicit val timeout = Timeout( 5.seconds )
  val port = Properties.envOrElse( "PORT", "8080" ).toInt
  println( "HTTP port is : " + port )
  IO( Http ) ? Http.Bind( service, interface = "0.0.0.0", port = port )
}
