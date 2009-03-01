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

import java.sql.Types;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.ITester;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable.VariableType;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ExtensionContributor;
import org.eclipse.datatools.connectivity.oda.spec.manifest.FilterExpressionDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ResultExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.AndExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CompositeExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.CustomExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.NotExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.filter.OrExpression;



import junit.framework.TestCase;

@SuppressWarnings("restriction")
public class FilterExtensionsTest extends TestCase
{
    private static final String TEST_EXTENSION_ID = "org.eclipse.datatools.connectivity.oda.consumer.testdriver.dynamicResultSetExtension"; //$NON-NLS-1$
    private static final String DBPROFILE_DATA_SOURCE_ID = "org.eclipse.datatools.connectivity.oda.consumer.testdriver"; //$NON-NLS-1$
    private static final String DBPROFILE_DATA_SET_ID = "org.eclipse.datatools.connectivity.oda.consumer.testdriver.dataSet"; //$NON-NLS-1$

    private static final String EQUAL_EXPR_ID = "0"; //$NON-NLS-1$
    private static final String BETWEEN_EXPR_ID = "1001"; //$NON-NLS-1$
    private static final String ISNULL_EXPR_ID = "1005"; //$NON-NLS-1$
    private static final String INSTANCE_EXPR_ID = "instanceOf"; //$NON-NLS-1$
    private static final String RESTRICTED_CLASS_NAME = "org.eclipse.datatools.connectivity.oda.consumer.testdriver.OrderItem"; //$NON-NLS-1$
    
    public void testContributorManifest() throws Exception
    {
        ExtensionContributor[] contributors =
            ResultExtensionExplorer.getInstance().getContributorsOfDataSet( DBPROFILE_DATA_SOURCE_ID, DBPROFILE_DATA_SET_ID  );
        assertTrue( contributors.length > 0 );

        ExtensionContributor contributor = contributors[0];

        FilterExpressionDefinition[] contributorDefns = ResultExtensionExplorer.getInstance().getContributedFilterDefinitions( contributor );
        assertTrue( contributorDefns.length >= 5 );

        // test that the 2 explorer entry points found the same set of definitions
        FilterExpressionDefinition[] extensionDefns = ResultExtensionExplorer.getInstance().getExtensionFilterDefinitions( TEST_EXTENSION_ID );
        if( contributor.getDeclaringExtensionId().equalsIgnoreCase( TEST_EXTENSION_ID ) )
            assertTrue( contributorDefns.length == extensionDefns.length ); 
        
        assertTrue( contributor.supportsOdaFilterExpression( AndExpression.getName() ));
        assertTrue( contributor.supportsOdaFilterExpression( OrExpression.getName() ));
        assertTrue( contributor.supportsOdaFilterExpression( NotExpression.getName() ));
        
        assertTrue( contributor.supportsDataSetType( DBPROFILE_DATA_SOURCE_ID, DBPROFILE_DATA_SET_ID ));
    }
    
    public void testGetExpressionDefinition() throws Exception
    {
        ExtensionContributor[] contributors =
            ResultExtensionExplorer.getInstance().getContributorsOfDataSet( DBPROFILE_DATA_SOURCE_ID, DBPROFILE_DATA_SET_ID  );
        assertTrue( contributors.length > 0 );
        ExtensionContributor contributor = contributors[0];

        FilterExpressionDefinition equalDefn =
            ResultExtensionExplorer.getInstance().getContributedFilterDefinition( contributor, EQUAL_EXPR_ID );
        assertEquals( EQUAL_EXPR_ID, equalDefn.getId() );
        assertEquals( contributor, equalDefn.getContributor() );
        assertTrue( equalDefn.getTester() instanceof ITester );

        FilterExpressionDefinition betweenDefn =
            ResultExtensionExplorer.getInstance().getExtensionFilterDefinition( contributor.getDeclaringExtensionId(), BETWEEN_EXPR_ID );
        assertEquals( BETWEEN_EXPR_ID, betweenDefn.getId() );
        assertEquals( contributor.getDeclaringExtensionId(), betweenDefn.getDeclaringExtensionId() );
        assertEquals( 1, betweenDefn.getMinArguments().intValue() );
        assertEquals( 2, betweenDefn.getMaxArguments().intValue() );
        assertFalse( betweenDefn.supportsUnboundedMaxArguments() );
        
        // test the content of variableRestriction elements
        assertEquals( 1, betweenDefn.getVariableRestrictions().getRestrictedVariableTypes().length );
        assertTrue( betweenDefn.getVariableRestrictions().supportsVariableType( VariableType.RESULT_SET_COLUMN ));
        assertTrue( betweenDefn.getVariableRestrictions().hasDataTypeRestrictions( VariableType.RESULT_SET_COLUMN ) );
        assertTrue( betweenDefn.getVariableRestrictions().getResultColumnRestrictedOdaDataTypes().length >= 7 );
        assertTrue( betweenDefn.getVariableRestrictions().supportsOdaDataType( VariableType.RESULT_SET_COLUMN, Types.CHAR ) );
        
        assertFalse( betweenDefn.getVariableRestrictions().supportsVariableType( VariableType.INSTANCE_OF ));
        assertFalse( betweenDefn.getVariableRestrictions().hasDataTypeRestrictions( VariableType.INSTANCE_OF ) );
        assertEquals( 0, betweenDefn.getVariableRestrictions().getInstanceRestrictedTypes().length );
        assertFalse( betweenDefn.getVariableRestrictions().supportsClassType( VariableType.INSTANCE_OF, RESTRICTED_CLASS_NAME ) );

        FilterExpressionDefinition isInstanceDefn =
            ResultExtensionExplorer.getInstance().getExtensionFilterDefinition( contributor.getDeclaringExtensionId(), INSTANCE_EXPR_ID );
        assertTrue( isInstanceDefn.isNegatable() );
        assertFalse( isInstanceDefn.isOptionable() );

        assertEquals( 1, isInstanceDefn.getVariableRestrictions().getRestrictedVariableTypes().length );
        assertTrue( isInstanceDefn.getVariableRestrictions().supportsVariableType( VariableType.INSTANCE_OF ));
        assertTrue( isInstanceDefn.getVariableRestrictions().hasDataTypeRestrictions( VariableType.INSTANCE_OF ) );
        assertTrue( isInstanceDefn.getVariableRestrictions().getInstanceRestrictedTypes().length >= 1 );
        assertTrue( isInstanceDefn.getVariableRestrictions().supportsClassType( VariableType.INSTANCE_OF, RESTRICTED_CLASS_NAME ) );
        
        assertFalse( isInstanceDefn.getVariableRestrictions().supportsVariableType( VariableType.RESULT_SET_COLUMN ));
        assertFalse( isInstanceDefn.getVariableRestrictions().hasDataTypeRestrictions( VariableType.RESULT_SET_COLUMN ) );
        assertEquals( 0, isInstanceDefn.getVariableRestrictions().getResultColumnRestrictedOdaDataTypes().length );
        assertFalse( isInstanceDefn.getVariableRestrictions().supportsOdaDataType( VariableType.RESULT_SET_COLUMN, Types.CHAR ) );
    }
    
    public void testCreateExpression() throws Exception
    {
        FilterExpressionDefinition equalDefn =
            ResultExtensionExplorer.getInstance().getExtensionFilterDefinition( TEST_EXTENSION_ID, EQUAL_EXPR_ID );
        CustomExpression equalExpr = equalDefn.createExpression();
        equalExpr.setVariable( new ExpressionVariable( "ORDERNUMBER" ) ); //$NON-NLS-1$
        equalExpr.setArguments( new ExpressionArguments( 10199 ) );

        String testKey = "test"; //$NON-NLS-1$
        String testValue = "testValue"; //$NON-NLS-1$
        equalExpr.setData( testKey, testValue );
        assertEquals( testValue, equalExpr.getData( testKey ) );

        CustomExpression equalExpr2 = equalDefn.createExpression( 
                new ExpressionVariable( "STATE" ),  //$NON-NLS-1$
                new ExpressionArguments( "NY" ) ); //$NON-NLS-1$

        NotExpression notExpr = new NotExpression( equalExpr2 );
        
        FilterExpressionDefinition betweenDefn =
            ResultExtensionExplorer.getInstance().getExtensionFilterDefinition( TEST_EXTENSION_ID, BETWEEN_EXPR_ID );
        CustomExpression betweenExpr = betweenDefn.createExpression();
        betweenExpr.setVariable( new ExpressionVariable( "(CREDITLIMIT / 100)" ) ); //$NON-NLS-1$
        ExpressionArguments betweenArgs = new ExpressionArguments();
        betweenArgs.addValue( 100.0 )
            .addValue( 700.0 );
        betweenExpr.setArguments( betweenArgs );

        FilterExpressionDefinition isNullDefn =
            ResultExtensionExplorer.getInstance().getExtensionFilterDefinition( TEST_EXTENSION_ID, ISNULL_EXPR_ID );
        CustomExpression isNullExpr = isNullDefn.createExpression();
        isNullExpr.setVariable( new ExpressionVariable( "VIP" ) ); //$NON-NLS-1$
        
        CompositeExpression andExpr1 = new AndExpression();
        andExpr1.add( equalExpr )
            .add( notExpr )
            .add( betweenExpr ); 

        CompositeExpression rootExpr = new OrExpression();
        rootExpr.add( andExpr1 )
            .add( new NotExpression( isNullExpr ) );
        
        assertEquals( 2, rootExpr.childCount() );
        assertEquals( 2, rootExpr.getChildren().length );
        // test sequence of children is expected to be kept in order added
        assertTrue( rootExpr.getChildren()[0] instanceof AndExpression );   
        assertEquals( 3, ((AndExpression)rootExpr.getChildren()[0]).childCount() );       
        
        // TODO - enrich the custom tester in test extension
        ValidationContext context = new ValidationContext( equalDefn.getContributor() );
        try
        {
            rootExpr.validate( context );
        }
        catch( OdaException ex )
        {
            fail();
        }        
    }
    
}
