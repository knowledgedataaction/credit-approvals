package org.kda.credit.rules

import org.drools.core.ClassObjectFilter
import org.junit.Assert._
import org.junit.{Ignore, Test}
import org.kda.credit.model.{Application, Customer, PendingApplication}
import org.kie.api.KieServices

class CreditApprovalRulesTest {

  val kContainer = KieServices.Factory.get().newKieClasspathContainer()

  @Test
  def creditLineDenied : Unit = {
    val application = PendingApplication( "001" )
    val customer = Customer( "001", 299 )

    val session = kContainer.newKieSession( "credit-approvals-ksession" )
    session.insert( application )
    session.insert( customer )
    session.fireAllRules()

    val objects = session.getObjects( new ClassObjectFilter( classOf[ Application ] ) )
    assertTrue( objects.size == 1 )

    val i = objects.iterator()
    val output = i.next().asInstanceOf[ Application ]
    assertTrue( output.creditLine.isEmpty )
  }

  @Test
  def creditLineApprovedWithStandardCreditLineUpperEdgeCase : Unit = {
    val application = PendingApplication( "001" )
    val customer = Customer( "001", 300 )

    val session = kContainer.newKieSession( "credit-approvals-ksession" )
    session.insert( application )
    session.insert( customer )
    session.fireAllRules()

    val objects = session.getObjects( new ClassObjectFilter( classOf[ Application ] ) )
    assertTrue( objects.size == 1 )

    val i = objects.iterator()
    val output = i.next().asInstanceOf[ Application ]
    assertFalse( output.creditLine.isEmpty )
    assertEquals( output.creditLine.get.creditLimit, BigDecimal( 10000.0 ) )
  }

  @Test
  def creditLineApprovedWithStandardCreditLineLowerEdgeCase : Unit = {
    val application = PendingApplication( "001" )
    val customer = Customer( "001", 500 )

    val session = kContainer.newKieSession( "credit-approvals-ksession" )
    session.insert( application )
    session.insert( customer )
    session.fireAllRules()

    val objects = session.getObjects( new ClassObjectFilter( classOf[ Application ] ) )
    assertTrue( objects.size == 1 )

    val i = objects.iterator()
    val output = i.next().asInstanceOf[ Application ]
    assertFalse( output.creditLine.isEmpty )
    assertEquals( output.creditLine.get.creditLimit, BigDecimal( 10000.0 ) )
  }

  @Test
  def creditLineApprovedWithExtendedCreditLine : Unit = {
    val application = PendingApplication( "001" )
    val customer = Customer( "001", 700 )

    val session = kContainer.newKieSession( "credit-approvals-ksession" )
    session.insert( application )
    session.insert( customer )
    session.fireAllRules()

    val objects = session.getObjects( new ClassObjectFilter( classOf[ Application ] ) )
    assertTrue( objects.size == 1 )

    val i = objects.iterator()
    val output = i.next().asInstanceOf[ Application ]
    assertFalse( output.creditLine.isEmpty )
    assertEquals( output.creditLine.get.creditLimit, BigDecimal( 15000.0 ) )
  }
}