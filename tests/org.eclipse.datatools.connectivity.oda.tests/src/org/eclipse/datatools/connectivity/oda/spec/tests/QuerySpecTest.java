/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.spec.tests;

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.IValidator;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification.ParameterIdentifier;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ExtensionContributor;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ResultExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.spec.result.ColumnIdentifier;
import org.eclipse.datatools.connectivity.oda.spec.result.CustomAggregate;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultProjection;
import org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification;
import org.eclipse.datatools.connectivity.oda.spec.util.ExpressionFactory;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;

@SuppressWarnings("restriction")
public class QuerySpecTest extends TestCase
{
    static final String TEST_EXTENSION_ID = "org.eclipse.datatools.connectivity.oda.consumer.testdriver.dynamicResultSetExtension"; //$NON-NLS-1$
    static final String TARGET_DATA_SOURCE_ID = "org.eclipse.datatools.connectivity.oda.consumer.testdriver"; //$NON-NLS-1$
    static final String TARGET_DATA_SET_ID = "org.eclipse.datatools.connectivity.oda.consumer.testdriver.dataSet"; //$NON-NLS-1$

    public void testCreateQuerySpec() throws Exception
    {
        QuerySpecificationHelper specHelper = new QuerySpecificationHelper( TEST_EXTENSION_ID );
        QuerySpecification querySpec = specHelper.createQuerySpecification();
        querySpec.setProperty( "prop1", Integer.valueOf( 1 ) );
        querySpec.setProperty( "prop2", Integer.valueOf( 2 ) );
        assertEquals( 2, querySpec.getProperties().size() );
        assertEquals( Integer.valueOf( 1 ), querySpec.getProperty( "prop1" ));
        assertEquals( Integer.valueOf( 2 ), querySpec.getProperties().get( "prop2" ));
        
        // test setting parameter value by name only
        String paramName = "param1";
        String paramValue = "paramValue1";
        querySpec.setParameterValue( paramName, paramValue );
        assertEquals( 1, querySpec.getParameterValues().size() );
        assertEquals( paramValue, querySpec.getParameterValue( paramName ) );
        assertEquals( paramValue, querySpec.getParameterValue( querySpec.new ParameterIdentifier( paramName, 100 )) );
        assertEquals( paramValue, querySpec.getParameterValues().get( querySpec.new ParameterIdentifier( paramName )) );
        
        // test case sensitive parameter name comparison
        assertNotSame( paramValue, querySpec.getParameterValue( "Param1" ) );
      
        // test setting parameter value by position only
        int paramId = 2;
        querySpec.setParameterValue( paramId, "paramValue2" );
        querySpec.setParameterValue( querySpec.new ParameterIdentifier( paramId ), "overrideParamValue"  );
        assertEquals( 2, querySpec.getParameterValues().size() );
        assertEquals( "overrideParamValue", querySpec.getParameterValue( paramId ) );
        assertEquals( "overrideParamValue", querySpec.getParameterValue( querySpec.new ParameterIdentifier( "dummyName", paramId ) ) );
        assertEquals( "overrideParamValue", querySpec.getParameterValues().get( querySpec.new ParameterIdentifier( paramId ) ) );

        // test setting parameter value by name and position
        paramName = "param3";
        paramId = 3;
        paramValue = "paramValue3";
        ParameterIdentifier paramIdentifier = querySpec.new ParameterIdentifier( paramName, paramId );
        querySpec.setParameterValue( paramIdentifier, paramValue );
        assertEquals( paramValue, querySpec.getParameterValue( paramName ) );
        assertEquals( paramValue, querySpec.getParameterValue( paramId ) );
        assertEquals( paramValue, querySpec.getParameterValue( paramIdentifier ) );
        assertEquals( paramValue, querySpec.getParameterValue( querySpec.new ParameterIdentifier( paramName, paramId ) ) );
        
        Object actualValue = querySpec.getParameterValue( "param1" );
        assertNotNull( actualValue );
        assertNotSame( paramValue, actualValue );
        
        actualValue = querySpec.getParameterValue( 2 );
        assertNotNull( actualValue );
        assertNotSame( paramValue, actualValue );
        
        // formatting of querySpec content
        String contentStr = QuerySpecificationHelper.getContentAsString( querySpec );
        assertTrue( contentStr.length() > 390); // not very useful testing; more for manual visualization
}
    
//    public void testCreateResultProjectionAggregates() throws Exception
//    {
//        // setup aggregate expression projection
//        CustomAggregate orderNumAggr = ExpressionFactory.createCustomAggregate( TEST_EXTENSION_ID, "COUNT",
//                                                new ExpressionVariable( "ORDERNUMBER" ) );
//        orderNumAggr.setIgnoreDuplicateValues( true );
//    
//        CustomAggregate creditLimitAggr = ExpressionFactory.createCustomAggregate( TEST_EXTENSION_ID, "COUNT",
//                                                new ExpressionVariable( "CREDITLIMIT" ) );
//        
//        QuerySpecificationHelper specHelper = new QuerySpecificationHelper( TEST_EXTENSION_ID );
//        ResultProjection resultProj = specHelper.createResultProjection();
//        resultProj.setProjection( new ColumnIdentifier( 2 ), orderNumAggr );
//        resultProj.setProjection( new ColumnIdentifier( 4 ), creditLimitAggr );
//               
//        ExpressionVariable newColumnVar = new ExpressionVariable( "NEWCREDITLIMIT" );
//        resultProj.addResultColumn( newColumnVar );
//        resultProj.setProjection( new ColumnIdentifier( newColumnVar.getAlias() ), creditLimitAggr );
//        
//        // test aggregate getters of ResultProjection
//        assertEquals( 3, resultProj.getAggregatedColumns().size() );
//        assertNull( resultProj.getAggregateProjection( new ColumnIdentifier( 1 )));
//        assertEquals( orderNumAggr, resultProj.getAggregateProjection( new ColumnIdentifier( 2 )));
//        assertEquals( creditLimitAggr, resultProj.getAggregateProjection( new ColumnIdentifier( 4 )));
//        assertEquals( creditLimitAggr, resultProj.getAggregateProjection( new ColumnIdentifier( newColumnVar.getAlias() )));
//
//        assertEquals( 1, resultProj.getAddedResultColumns().size() );
//       
//        // test hiding a new projected aggregate column
//        resultProj.hideResultColumn( new ColumnIdentifier( newColumnVar.getAlias() ) );
//        assertEquals( 2, resultProj.getAggregatedColumns().size() );
//        assertEquals( 0, resultProj.getAddedResultColumns().size() );
//        assertEquals( 0, resultProj.getHiddenResultColumns().size() );        
//        
//        // test accessing ResultProjection from a QuerySpecification
//        QuerySpecification querySpec = specHelper.createQuerySpecification( resultProj );       
//        ResultProjection resultProjOut = querySpec.getResultSetSpecification().getResultProjection();
//        assertEquals( resultProj, resultProjOut );
//        
//        // formatting of querySpec content
//        String contentStr = QuerySpecificationHelper.getContentAsString( querySpec );
//        assertTrue( contentStr.length() > 850);
//    }
    
    public void testCreateResultProjectionColumns() throws Exception
    {
        QuerySpecificationHelper specHelper = new QuerySpecificationHelper( TEST_EXTENSION_ID );
        ResultProjection resultProj = specHelper.createResultProjection();
        
        resultProj.addResultColumn( new ExpressionVariable( "compute column 1") );
        ExpressionVariable exprVar2 = new ExpressionVariable( "compute column 2" );
        resultProj.addResultColumn( exprVar2 );
        
        assertEquals( 2, resultProj.getAddedResultColumns().size() );
        assertEquals( exprVar2, resultProj.getAddedResultColumns().get( new ColumnIdentifier( exprVar2.getAlias() ) ));
        
        // test hiding a dynamcially added column
        resultProj.hideResultColumn( new ColumnIdentifier(  "compute column 2" ));  // should be same as var2.getAlias()
        assertEquals( 1, resultProj.getAddedResultColumns().size() );
        assertEquals( 0, resultProj.getHiddenResultColumns().size() );
        
        // test hiding a predefined result column
        resultProj.hideResultColumn( new ColumnIdentifier( "result column 3" ));
        assertEquals( 1, resultProj.getHiddenResultColumns().size() );
    }
    
//    public void testCreateAndValidateSortSpec() throws Exception
//    {
//        ExtensionContributor contributor =
//            ResultExtensionExplorer.getInstance().getExtensionContributor( TEST_EXTENSION_ID );
//        assertTrue( contributor.supportsDynamicRowOrdering() );
//        assertFalse( contributor.supportsNullValueOrdering() );
//        
//        QuerySpecificationHelper specHelper = new QuerySpecificationHelper( TEST_EXTENSION_ID );
//        SortSpecification sortSpec = specHelper.createSortSpecification();
//        sortSpec.addSortKey( new ColumnIdentifier("Column1"), SortSpecification.ORDERING_DESC );
//        sortSpec.addSortKey( new ColumnIdentifier(2), SortSpecification.ORDERING_ASC, 
//                SortSpecification.NULL_ORDERING_NONE );
//        
//        ValidationContext context = new ValidationContext( contributor );
//        context.setQueryText( "test query text" );
//        
//        QuerySpecification querySpec = specHelper.createQuerySpecification( sortSpec );
//
//        // setup test property values used by test driver to validate sort keys
//        querySpec.setProperty( "Column1", Integer.valueOf( SortSpecification.ORDERING_DESC ) );
//        querySpec.setProperty( "2", Integer.valueOf( SortSpecification.ORDERING_ASC ) );
//        querySpec.setProperty( "TESTER_EXPECTED_QUERY", "test query text" );
//        
//        IValidator validator = context.getValidator();
//        try
//        {
//            validator.validate( querySpec.getResultSetSpecification(), context );
//        }
//        catch( OdaException e )
//        {
//            fail();     // test failed; expected test driver to pass validation
//        }
//        
//        // formatting of querySpec content
//        String contentStr = QuerySpecificationHelper.getContentAsString( querySpec );
//        assertTrue( contentStr.length() > 440); // not very useful testing; more for manual visualization
//    }
 
//    public void testCreateCustomSortSpec() throws Exception
//    {
//        // test use of extended factory
//        QuerySpecificationHelper specHelper = new QuerySpecificationHelper( TEST_EXTENSION_ID );
//        SortSpecification sortSpec = specHelper.createSortSpecification();
//        assertEquals( "MySortSpecification", sortSpec.getClass().getSimpleName() ); //$NON-NLS-1$
//        
//        // test use of default factory
//        specHelper = new QuerySpecificationHelper( (String) null );
//        sortSpec = specHelper.createSortSpecification();
//        assertEquals( "SortSpecification", sortSpec.getClass().getSimpleName() ); //$NON-NLS-1$
//    }

    public void testSortSpecMode() throws Exception
    {
        SortSpecification sortSpec = new QuerySpecificationHelper( (String) null ).createSortSpecification();
        
        // no sort keys
        assertEquals( IDataSetMetaData.sortModeNone, sortSpec.getSortMode() );
        
        // single sort key
        sortSpec.addSortKey( new ColumnIdentifier( "A" ), SortSpecification.ORDERING_ASC ); //$NON-NLS-1$
        assertEquals( IDataSetMetaData.sortModeSingleColumn, sortSpec.getSortMode() );
        
        // multiple sort keys with same sort direction
        sortSpec.addSortKey( new ColumnIdentifier( "B" ), SortSpecification.ORDERING_ASC ); //$NON-NLS-1$
        assertEquals( IDataSetMetaData.sortModeSingleOrder, sortSpec.getSortMode() );
        
        // additional sort key with different sort direction
        sortSpec.addSortKey( new ColumnIdentifier( "C" ), SortSpecification.ORDERING_DESC ); //$NON-NLS-1$
        assertEquals( IDataSetMetaData.sortModeColumnOrder, sortSpec.getSortMode() );
    }

}
