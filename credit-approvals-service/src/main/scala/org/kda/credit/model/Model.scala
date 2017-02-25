package org.kda.credit.model

import scala.beans.BeanInfo

@BeanInfo
case class Customer( id : String, creditScore : Int )

@BeanInfo
case class CreditLine( customer : Customer, creditLimit : BigDecimal )

trait Application {

  val customerId : String
  val creditLine : Option[ CreditLine ]

  def approved( creditLine : CreditLine ) : Application = {
    ApprovedApplication( customerId, Some( creditLine ) )
  }

  def unapproved : Application = {
    UnapprovedApplication( customerId )
  }
}

@BeanInfo
case class PendingApplication( customerId : String, creditLine : Option[ CreditLine ] = None ) extends Application

@BeanInfo
case class ApprovedApplication( customerId : String, creditLine : Option[ CreditLine ] ) extends Application

@BeanInfo
case class UnapprovedApplication( customerId : String, creditLine : Option[ CreditLine ] = None ) extends Application
