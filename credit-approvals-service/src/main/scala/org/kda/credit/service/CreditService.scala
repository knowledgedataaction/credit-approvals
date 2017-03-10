package org.kda.credit.service

import akka.actor.Actor
import spray.httpx.SprayJsonSupport
import spray.routing.{HttpService, Route}
import org.kda.credit.model._
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
  import org.kda.credit.model.{ApprovedApplication, PendingApplication, CreditLine, Customer}
  import org.kda.credit.service.CreditApprovalJSonProtocol._

  //@formatter:off
  val applicationRoute: Route =
    path( "credit-approvals" ) {
      get {
        complete {
          PendingApplication( "123" ) // you can POST this payload to the service to convert it into an approved application
        }
      } ~
      post {
        respondWithMediaType( `application/json` )
        entity( as[ PendingApplication ] ) { application =>
          detach() {
            // in Part 4 this code will be integrated with the rules engine to actually perform the business logic
            val customer = Customer( application.customerId, 400 )
            val credit = CreditLine( customer, BigDecimal( 5000 ) )
            complete {
              ApprovedApplication( customer.id, Some( credit ) )
            }
          }
        }
      }
    }
}