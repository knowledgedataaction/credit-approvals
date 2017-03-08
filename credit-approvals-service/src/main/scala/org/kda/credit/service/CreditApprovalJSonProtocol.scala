package org.kda.credit.service

import org.kda.credit.model._
import spray.json.DefaultJsonProtocol

object CreditApprovalJSonProtocol extends DefaultJsonProtocol {
  implicit val customerFormat = jsonFormat2( Customer.apply )
  implicit val creditLineFormat = jsonFormat2( CreditLine.apply )
  implicit val pendingFormat = jsonFormat2( PendingApplication )
  implicit val approvedFormat = jsonFormat2( ApprovedApplication.apply )
  implicit val unapprovedFormat = jsonFormat2( UnapprovedApplication.apply )
}
