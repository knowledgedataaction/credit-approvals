package org.kda.credit.service

import akka.actor.Actor
import spray.httpx.SprayJsonSupport
import spray.routing.{HttpService, Route}
import org.kda.credit.model.{ApprovedApplication, CreditLine, Customer, UnapprovedApplication}
import spray.httpx.SprayJsonSupport._

class CreditApprovalsServiceActor extends Actor with CreditApprovalsService {
  def actorRefFactory = {
    context
  }

  def receive = {
    runRoute( applicationRoute )
  }
}

trait CreditApprovalsService extends HttpService with SprayJsonSupport {

  import spray.http.MediaTypes._
  import org.kda.credit.model.{ApprovedApplication, CreditLine, Customer}
  import org.kda.credit.service.CreditApprovalJSonProtocol._

  //@formatter:off
  val applicationRoute: Route =
    path( "credit-approvals" ) {
      get {
        respondWithMediaType( `application/json` )
        val customer = Customer( "123", 600 )
        val credit = CreditLine( customer, BigDecimal( 100 ) )
        complete {
          ApprovedApplication( customer.id, Some( credit ) )
        }
      }
    }
}