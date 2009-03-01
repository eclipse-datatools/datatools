/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.spec.tests;

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ResultExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.spec.result.CustomAggregate;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultProjection;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultSetSpecification;

@SuppressWarnings("restriction")
public class QuerySpecTest extends TestCase
{
    private static final String TEST_EXTENSION_ID = "org.eclipse.datatools.connectivity.oda.consumer.testdriver.dynamicResultSetExtension"; //$NON-NLS-1$

    public void testCreateQuerySpec() throws Exception
    {
        QuerySpecification querySpec = new QuerySpecification();
        querySpec.setProperty( "prop1", Integer.valueOf( 1 ) );
        querySpec.setProperty( "prop2", Integer.valueOf( 2 ) );
        assertEquals( 2, querySpec.getProperties().size() );
        assertEquals( Integer.valueOf( 1 ), querySpec.getProperty( "prop1" ));
        assertEquals( Integer.valueOf( 2 ), querySpec.getProperties().get( "prop2" ));
        
        querySpec.setParameterValue( "param1", "paramValue1" );
        assertEquals( 1, querySpec.getParameterValues().size() );
        assertEquals( "paramValue1", querySpec.getParameterValue( "param1" ) );
        assertEquals( "paramValue1", querySpec.getParameterValues().get( querySpec.new ParameterIdentifier( "param1" )) );
        
        // test case sensitive parameter name comparison
        assertNotSame( "paramValue1", querySpec.getParameterValue( "Param1" ) );
      
        int paramId = 2;
        querySpec.setParameterValue( paramId, "paramValue2" );
        querySpec.setParameterValue( querySpec.new ParameterIdentifier( paramId ), "overrideParamValue"  );
        assertEquals( 2, querySpec.getParameterValues().size() );
        assertEquals( "overrideParamValue", querySpec.getParameterValue( paramId ) );
        assertEquals( "overrideParamValue", querySpec.getParameterValues().get( querySpec.new ParameterIdentifier( paramId ) ) );
    }
    
    public void testCreateResultProjection() throws Exception
    {
        QuerySpecification querySpec = new QuerySpecification();
        querySpec.setResultSetSpecification( new ResultSetSpecification() );

        CustomAggregate orderNumAggr = ResultExtensionExplorer.getInstance().getExtensionAggregateDefinition( TEST_EXTENSION_ID, "COUNT" )
            .createExpression( new ExpressionVariable( "ORDERNUMBER" ) );
        orderNumAggr.setIgnoreDuplicateValues( true );
    
        CustomAggregate creditLimitAggr = ResultExtensionExplorer.getInstance().getExtensionAggregateDefinition( TEST_EXTENSION_ID, "AVG" )
            .createExpression( new ExpressionVariable( "CREDITLIMIT" ) );
        
        ResultProjection resultProj = new ResultProjection();
        resultProj.setProjection( 2, orderNumAggr );
        resultProj.setProjection( 4, creditLimitAggr );
        querySpec.getResultSetSpecification().setResultProjection( resultProj );
        
        ResultProjection resultProjOut = querySpec.getResultSetSpecification().getResultProjection();
        assertEquals( resultProj, resultProjOut );
        assertNull( resultProjOut.getAggregateProjection( 1 ));
        assertEquals( orderNumAggr, resultProjOut.getAggregateProjection( 2 ));
    }
    
}
