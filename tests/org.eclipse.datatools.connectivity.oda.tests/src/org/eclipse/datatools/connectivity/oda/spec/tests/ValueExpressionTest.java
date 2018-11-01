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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable.VariableType;
import org.eclipse.datatools.connectivity.oda.spec.manifest.CombinedExpressionOperatorDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ExtensionContributor;
import org.eclipse.datatools.connectivity.oda.spec.manifest.FunctionExpressionDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ResultExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.spec.result.ColumnIdentifier;
import org.eclipse.datatools.connectivity.oda.spec.util.ExpressionFactory;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.ColumnValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.CombinedValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.CombinedValueExpressionOperator;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.CustomFunction;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.FunctionValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.NestedValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.SimpleValueExpression;

@SuppressWarnings("restriction")
public class ValueExpressionTest extends TestCase
{
    private static final String TEST_EXTENSION_ID = QuerySpecTest.TEST_EXTENSION_ID;
    private static final String TARGET_DATA_SOURCE_ID = QuerySpecTest.TARGET_DATA_SOURCE_ID;
    private static final String TARGET_DATA_SET_ID = QuerySpecTest.TARGET_DATA_SET_ID;

    private static final String CUSTOM_DOT_OP_ID = "org.eclipse.datatools.connectivity.oda.consumer.testdriver.combinedOperator.dot"; //$NON-NLS-1$
    private static final String CUSTOM_AT_OP_ID = "org.eclipse.datatools.connectivity.oda.consumer.testdriver.combinedOperator.at"; //$NON-NLS-1$
    
//    public void testGetCombinedOperatorDefinitions() throws Exception
//    {
//        CombinedExpressionOperatorDefinition[] combinedOpDefns =
//            ResultExtensionExplorer.getInstance().getExtensionCombinedOperatorDefinitions( TEST_EXTENSION_ID );
//        assertTrue( combinedOpDefns.length >= 5 );
//
//        ExtensionContributor contributor = getTestContributor();
//        assertFalse( contributor.supportsOdaCombinedOperator( CombinedValueExpressionOperator.DIVIDE ));
//        assertTrue( contributor.supportsCombinedValueExpressionType() );
//
//        // "Add" operator type
//        assertTrue( contributor.supportsOdaCombinedOperator( CombinedValueExpressionOperator.ADD ));
//        CombinedExpressionOperatorDefinition opDefn =
//            ResultExtensionExplorer.getInstance().getExtensionCombinedOperatorDefinition( TEST_EXTENSION_ID, 
//                    CombinedValueExpressionOperator.ADD );
//        assertEquals( CombinedValueExpressionOperator.ADD, opDefn.getDisplayName() );
//        assertTrue( opDefn.isBuiltInOperator() );
//        
//        CombinedValueExpressionOperator opInstanceFromFactory = 
//            ExpressionFactory.getCombinedOperator( TEST_EXTENSION_ID, CombinedValueExpressionOperator.ADD );
//        CombinedValueExpressionOperator opInstance = opDefn.getOperator();
//        assertEquals( opInstanceFromFactory, opInstance );
//        assertEquals( "+", opInstance.getLiteral() );
//        assertNull( opInstance.get( CUSTOM_DOT_OP_ID ) );
//        
//        // Concatenate operator type with overridden literal representation
//        assertTrue( contributor.supportsOdaCombinedOperator( CombinedValueExpressionOperator.CONCATENATE ));
//        opDefn = ResultExtensionExplorer.getInstance().getExtensionCombinedOperatorDefinition( TEST_EXTENSION_ID, 
//                    CombinedValueExpressionOperator.CONCATENATE );
//        assertTrue( opDefn.isBuiltInOperator() );
//
//        opInstanceFromFactory = 
//            ExpressionFactory.getCombinedOperator( TEST_EXTENSION_ID, CombinedValueExpressionOperator.CONCATENATE );
//        opInstance = opDefn.getOperator();
//        assertEquals( opInstanceFromFactory, opInstance );
//        assertEquals( opInstance, opDefn.getOperator() );    // same instance should be returned
//        assertEquals( "||", opInstance.getLiteral() );   // overriden literal by custom class
//            // custom concatenate operator overrides the default literal symbol '+'
//        assertFalse( CombinedValueExpressionOperator.CONCATENATE_LITERAL.getLiteral().equals( opInstance.getLiteral() ));
//        
//        // custom combined operator type - Dot
//        assertFalse( contributor.supportsOdaCombinedOperator( CUSTOM_DOT_OP_ID )); // not a built-in operator
//        opDefn = ResultExtensionExplorer.getInstance().getExtensionCombinedOperatorDefinition( TEST_EXTENSION_ID, CUSTOM_DOT_OP_ID );
//        assertEquals( "Dot", opDefn.getDisplayName() );
//        assertFalse( opDefn.isBuiltInOperator() );
//
//        opInstanceFromFactory = 
//            ExpressionFactory.getCombinedOperator( TEST_EXTENSION_ID, CUSTOM_DOT_OP_ID );
//        opInstance = opDefn.getOperator();
//        assertEquals( opInstanceFromFactory, opInstance );
//        assertEquals( opInstance, opDefn.getOperator() );    // same instance should be returned
//        assertEquals( ".", opDefn.getOperator().getLiteral() );  
//        
//        // custom combined operator type - At
//        assertFalse( contributor.supportsOdaCombinedOperator( CUSTOM_AT_OP_ID )); // not a built-in operator
//        opDefn = ResultExtensionExplorer.getInstance().getExtensionCombinedOperatorDefinition( TEST_EXTENSION_ID, CUSTOM_AT_OP_ID );
//        assertEquals( "At", opDefn.getDisplayName() );
//        assertFalse( opDefn.isBuiltInOperator() );
//
//        opInstanceFromFactory = 
//            ExpressionFactory.getCombinedOperator( TEST_EXTENSION_ID, CUSTOM_AT_OP_ID );
//        opInstance = opDefn.getOperator();
//        assertEquals( opInstanceFromFactory, opInstance );
//        assertEquals( opInstance, opDefn.getOperator() );    // same instance should be returned
//        assertEquals( "@", opDefn.getOperator().getLiteral() );  
//    }
    
//    public void testGetFunctionDefinitions() throws Exception
//    {
//        FunctionExpressionDefinition[] funcDefns =
//            ResultExtensionExplorer.getInstance().getExtensionFunctionDefinitions( TEST_EXTENSION_ID );
//        assertTrue( funcDefns.length >= 10 );
//
//        ExtensionContributor contributor = getTestContributor();
//        assertTrue( contributor.supportsFunctionValueExpressionType() );
//        
//        // MOD function
//        FunctionExpressionDefinition funcDefn =
//            ResultExtensionExplorer.getInstance().getExtensionFunctionDefinition( TEST_EXTENSION_ID, "MOD" );
//        assertNotNull( funcDefn );
//        assertEquals( funcDefn.getId(), funcDefn.getName() );   // name is not specified, default to id
//        assertTrue( funcDefn.getDisplayName().startsWith( "Modulo" ) );
//        assertTrue( funcDefn.getDescription().startsWith( "Returns the remainder" ) );
//        assertEquals( Integer.valueOf( 2 ), funcDefn.getMinArguments() );
//        assertEquals( Integer.valueOf( 2 ), funcDefn.getMaxArguments() );
//        assertFalse( funcDefn.canIgnoreDuplicateValues() );
//        
//        assertTrue( funcDefn.getVariableRestrictions().supportsOdaNumericDataTypes( VariableType.QUERY_EXPRESSION ));
//        // implied support of ResultSetColumn if QUERY_EXPRESSION is supported
//        assertTrue( funcDefn.getVariableRestrictions().supportsVariableType( VariableType.RESULT_SET_COLUMN ));
//        assertTrue( funcDefn.getVariableRestrictions().supportsOdaNumericDataTypes( VariableType.RESULT_SET_COLUMN ));
//        
//        // LOWER function
//        funcDefn =
//            ResultExtensionExplorer.getInstance().getExtensionFunctionDefinition( TEST_EXTENSION_ID, "LOWER" );
//        assertNotNull( funcDefn );
//        assertTrue( funcDefn.getDisplayName().startsWith( "Lower" ) );
//        assertEquals( Integer.valueOf( 1 ), funcDefn.getMinArguments() );   // default value
//        assertEquals( Integer.valueOf( 1 ), funcDefn.getMaxArguments() );
//        assertTrue( funcDefn.getVariableRestrictions().supportsVariableType( VariableType.RESULT_SET_COLUMN ));
//        assertTrue( funcDefn.getVariableRestrictions().supportsOdaStringDataTypes( VariableType.RESULT_SET_COLUMN ));
//        
//        // MONTH function
//        funcDefn =
//            ResultExtensionExplorer.getInstance().getExtensionFunctionDefinition( TEST_EXTENSION_ID, "MONTH" );
//        assertNotNull( funcDefn );
//        assertTrue( funcDefn.getDisplayName().startsWith( "Month" ) );
//        assertEquals( Integer.valueOf( 1 ), funcDefn.getMinArguments() );
//        assertEquals( Integer.valueOf( 1 ), funcDefn.getMaxArguments() );
//        assertTrue( funcDefn.getVariableRestrictions().supportsVariableType( VariableType.RESULT_SET_COLUMN ));
//        assertTrue( funcDefn.getVariableRestrictions().supportsOdaDatetimeDataTypes( VariableType.RESULT_SET_COLUMN ));
//        
//        assertFalse( funcDefn.getVariableRestrictions().supportsOdaStringDataTypes( VariableType.QUERY_EXPRESSION ));
//        assertFalse( funcDefn.getVariableRestrictions().supportsOdaNumericDataTypes( VariableType.RESULT_SET_COLUMN ));
//        assertFalse( funcDefn.getVariableRestrictions().supportsOdaBooleanDataTypes( VariableType.QUERY_EXPRESSION ));
//        
//        // CURRENT_DATE function
//        funcDefn =
//            ResultExtensionExplorer.getInstance().getExtensionFunctionDefinition( TEST_EXTENSION_ID, "CURRENT_DATE" );
//        assertNotNull( funcDefn );
//        assertEquals( Integer.valueOf( 0 ), funcDefn.getMinArguments() );
//        assertEquals( Integer.valueOf( 0 ), funcDefn.getMaxArguments() );
//        
//        // no explicit variableRestriction; default to support RESULT_SET_COLUMN type
//        assertTrue( funcDefn.getVariableRestrictions().supportsVariableType( VariableType.RESULT_SET_COLUMN ));
//        assertFalse( funcDefn.getVariableRestrictions().hasDataTypeRestrictions( VariableType.RESULT_SET_COLUMN ));
//
//        assertTrue( funcDefn.getVariableRestrictions().supportsOdaDatetimeDataTypes( VariableType.RESULT_SET_COLUMN ));        
//        assertTrue( funcDefn.getVariableRestrictions().supportsOdaStringDataTypes( VariableType.RESULT_SET_COLUMN ));
//        assertTrue( funcDefn.getVariableRestrictions().supportsOdaNumericDataTypes( VariableType.RESULT_SET_COLUMN ));
//        assertTrue( funcDefn.getVariableRestrictions().supportsOdaBooleanDataTypes( VariableType.RESULT_SET_COLUMN ));
//    }
    
//    public void testCreateCustomFunction() throws Exception
//    {
//        CustomFunction customFunc =
//            ExpressionFactory.createCustomFunction( TEST_EXTENSION_ID, "100" ); //$NON-NLS-1$
//        assertNotNull( customFunc );
//        assertEquals( "MyCustomFunction", customFunc.getClass().getSimpleName() );
//        assertTrue( customFunc.getDefinition().getName().equals( "CustomFunc" ) );
//        assertTrue( customFunc.getName().equals( "MyCustomFunc" ) );    // overriden by extended class
//        assertTrue( customFunc.getDefinition().getDisplayName().equals( "Custom Function" ) );        
//    }
    
    public void testCreateSimpleValueExpressionVariables() throws Exception
    {
        // simple Column value expression with name
        String identifierText = "simple column 1";
        ExpressionVariable exprVar = new ExpressionVariable( identifierText );
        String varString = exprVar.toString();
        assertEquals( VariableType.RESULT_SET_COLUMN, exprVar.getType() );
        assertEquals( identifierText, exprVar.getIdentifier() );
        
        // simple Column value expression with name and pos
        identifierText = "column 2";
        exprVar = new ExpressionVariable( new ColumnIdentifier( 2, identifierText ) );
        varString = exprVar.toString();
        assertEquals( VariableType.RESULT_SET_COLUMN, exprVar.getType() );
        assertEquals( identifierText, exprVar.getIdentifier() );
        assertTrue( exprVar.getValueExpression() instanceof ColumnValueExpression );
        assertEquals( 2, ((ColumnValueExpression) exprVar.getValueExpression()).getColumnNumber().intValue() );
    }
    
//    public void testCreateComplexValueExpressions() throws Exception
//    {
//        // CustomFunction with arguments
//        CustomFunction func = ExpressionFactory.createCustomFunction( TEST_EXTENSION_ID, "MOD" );
//        func.setArguments( (new ExpressionArguments()).addValue( new SimpleValueExpression( "Col") )
//                .addValue( 3 ));
//        ExpressionVariable exprVar = new ExpressionVariable( func );
//        String varString = exprVar.toString();
//        assertEquals( VariableType.QUERY_EXPRESSION, exprVar.getType() );
//        ExpressionArguments funcArgs = ((FunctionValueExpression) exprVar.getValueExpression()).getArguments();
//        assertEquals( 2, funcArgs.valueCount() );
//        assertEquals( "MOD", exprVar.getValueExpression().getName() );
//        assertEquals( "Col", funcArgs.getValueExpression( 0 ).getName() );
//        assertEquals( "3", funcArgs.getValueExpression( 1 ).getName() );
//
//        // test this extension supports the various complex expression types
//        ExtensionContributor contributor = getTestContributor();
//        assertTrue( contributor.supportsCombinedValueExpressionType() );
//        assertTrue( contributor.supportsNestedValueExpressionType() );
//        assertTrue( contributor.supportsFunctionValueExpressionType() );
//        
//        //  col1 * (1 + col2)
//        String complexExprText1 = "col1 * (1 + col2)"; //$NON-NLS-1$
//        CombinedValueExpressionOperator customAddOp = 
//            ExpressionFactory.getCombinedOperator( TEST_EXTENSION_ID, CombinedValueExpressionOperator.ADD );
//        CombinedValueExpressionOperator customMultiplyOp = 
//            ExpressionFactory.getCombinedOperator( TEST_EXTENSION_ID, CombinedValueExpressionOperator.MULTIPLY );
//
//        CombinedValueExpression complexExpr1 = new CombinedValueExpression( 
//                new ColumnValueExpression( new ColumnIdentifier( 1, "col1") ), 
//                customMultiplyOp, 
//                new NestedValueExpression(
//                        new CombinedValueExpression(
//                                new SimpleValueExpression( Integer.valueOf(1) ),
//                                customAddOp,
//                                new ColumnValueExpression( new ColumnIdentifier( 2, "col2") )) ) );
//        exprVar = new ExpressionVariable( complexExpr1 );
//        varString = exprVar.toString();
//        exprVar.getValueExpression().validate();
//        
//        //  (colA || UPPER( CustomerName || State )) + col1
//        String complexExprText2 = "(colA || UPPER( CustomerName || State )) + col1"; //$NON-NLS-1$
//            // custom concatenate operator overrides the default symbol '+'
//        CombinedValueExpressionOperator customConcatOp = 
//            ExpressionFactory.getCombinedOperator( TEST_EXTENSION_ID, CombinedValueExpressionOperator.CONCATENATE );
//        func = ExpressionFactory.createCustomFunction( TEST_EXTENSION_ID, "UPPER" );
//        
//        CombinedValueExpression customerNameState = new CombinedValueExpression(
//                new ColumnValueExpression( new ColumnIdentifier("CustomerName") ),
//                customConcatOp,
//                new ColumnValueExpression( new ColumnIdentifier("State") ));
//
//        func.setArguments( (new ExpressionArguments()).addValue( customerNameState ));
//        
//        CombinedValueExpression complexExpr2 = new CombinedValueExpression( 
//                new NestedValueExpression(
//                        new CombinedValueExpression(
//                                new SimpleValueExpression( "colA" ),
//                                customConcatOp,
//                                func ) ),
//                customAddOp, 
//                new ColumnValueExpression( new ColumnIdentifier( 1, "col1") ) );
//        exprVar = new ExpressionVariable( complexExpr2 );
//        varString = exprVar.toString();
//        exprVar.getValueExpression().validate();
//        
//        // test complex value expression in ExpressionArguments
//        ExpressionArguments args3 =
//            (new ExpressionArguments()).addValue( complexExpr1 ).addValue( 1 ).addValue( complexExpr2 );
//        assertEquals( 3, args3.valueCount() );
//        assertTrue( args3.getValueExpression(0) instanceof CombinedValueExpression );
//        assertTrue( args3.getValueExpression(1) instanceof SimpleValueExpression );
//        assertEquals( Integer.valueOf("1"), ((SimpleValueExpression)args3.getValueExpression(1)).getValue() );
//        assertTrue( args3.getValueExpression(2) instanceof CombinedValueExpression );        
//    }

    private ExtensionContributor getTestContributor() throws OdaException
    {
        ExtensionContributor[] contributors =
            ResultExtensionExplorer.getInstance().getContributorsOfDataSet( TARGET_DATA_SOURCE_ID, TARGET_DATA_SET_ID  );
        assertTrue( contributors.length > 0 );
        ExtensionContributor contributor = contributors[0];
        return contributor;
    }
}
