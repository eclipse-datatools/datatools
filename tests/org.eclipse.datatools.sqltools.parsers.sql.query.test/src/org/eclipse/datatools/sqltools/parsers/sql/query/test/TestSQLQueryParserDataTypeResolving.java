/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query.test;

import org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.ElementType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.MultisetDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.emf.ecore.EAttribute;


/**
 * Tests the <code>SQLQueryParser</code>s data type resolving.
 * 
 * @author ckadner
 * 
 */
public class TestSQLQueryParserDataTypeResolving extends AbstractTestSQLQueryParser {

    /**
     * @param name
     */
    public TestSQLQueryParserDataTypeResolving(String name) {
        super(name);
    }


    public void testSqlDmlParser_resolveDataTypes()  throws Exception {
        System.out.println("test resolveDataTypes"); //$NON-NLS-1$

        QuerySelectStatement stmt =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(
                "WITH "                                                   + NL + //$NON-NLS-1$
                "  temp1 (col1,col2) AS (VALUES (2563497.12,'string'))"   + NL + //$NON-NLS-1$
                "SELECT col1, col2"                                       + NL + //$NON-NLS-1$
                "  FROM temp1;", matchInput);                                    //$NON-NLS-1$

        assertDataType(getResultColumn(stmt, 0).getValueExpr(),
                NumericalDataType.class, PrimitiveType.DECIMAL_LITERAL);
        assertDataTypeProperty(getResultColumn(stmt, 0).getValueExpr().getDataType(),
                "precision", "9"); //$NON-NLS-1$ //$NON-NLS-2$
        assertDataTypeProperty(getResultColumn(stmt, 0).getValueExpr().getDataType(),
                "scale", "2"); //$NON-NLS-1$ //$NON-NLS-2$

        assertDataType(getResultColumn(stmt, 1).getValueExpr(),
                CharacterStringDataType.class, PrimitiveType.CHARACTER_LITERAL);
        assertDataTypeProperty(getResultColumn(stmt, 1).getValueExpr().getDataType(),
                "length", "6"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    
    public void testSqlDmlParser_resolveDataTypes_functions()  throws Exception {
        System.out.println("test resolveDataTypes_functions"); //$NON-NLS-1$

        QuerySelectStatement stmt =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(
                "WITH "                                                   + NL + //$NON-NLS-1$
                "  temp1 (col1,col2) AS (VALUES (2563497.12,'string'))"   + NL + //$NON-NLS-1$
                "SELECT count(col1)"                                      + NL + //$NON-NLS-1$
                "  FROM temp1;", matchInput);                                    //$NON-NLS-1$

        // get the data type of the value expr associated with the first result
        // column of the parsed stmt, and assert the expected data type
        // and its precision
        assertDataType(getResultColumn(stmt, 0).getValueExpr(),
                IntegerDataType.class, PrimitiveType.BIGINT_LITERAL);
        
        
        
        stmt = (QuerySelectStatement) parserVerifySuccessSingleQuery(
                "WITH EMPLOYEE_VIEW (SALARY)"           + NL + //$NON-NLS-1$ 
                "  AS (VALUES  ((256),(128)))"          + NL + //$NON-NLS-1$ 
                "SELECT INT(SUM(SALARY)) AS SAL,"       + NL + //$NON-NLS-1$ 
                "       SMALLINT(COUNT(*)) AS R"        + NL + //$NON-NLS-1$  
                "  FROM EMPLOYEE_VIEW"                  + NL + //$NON-NLS-1$ 
                "  ORDER BY SAL;"                       + NL + //$NON-NLS-1$
                "", matchInput);                               //$NON-NLS-1$
        
        // get the data type of the value expr associated with the result
        // columns of the parsed stmt, and assert the expected data type
        // and its precision
        assertDataType(getResultColumn(stmt, 0).getValueExpr(),
                IntegerDataType.class, PrimitiveType.BIGINT_LITERAL);
        assertDataType(getResultColumn(stmt, 1).getValueExpr(),
                IntegerDataType.class, PrimitiveType.SMALLINT_LITERAL);
    }

    
    public void testSqlDmlParser_resolveDataTypes_CollectionTypes_Array()  throws Exception {
        System.out.println("testSqlDmlParser_resolveDataTypes_CollectionTypes_Array"); //$NON-NLS-1$

        QuerySelectStatement stmt =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(
                "SELECT CAST(col1 AS CHARACTER ARRAY), CAST(col2 AS CHARACTER ARRAY[10])"                                       + NL + //$NON-NLS-1$
                "  FROM table1;", matchInput);                                    //$NON-NLS-1$

        assertDataType(getResultColumn(stmt, 0).getValueExpr(),
                ArrayDataType.class);
        assertDataTypeProperty(getResultColumn(stmt, 0).getValueExpr().getDataType(),
                "maxCardinality", "0"); //$NON-NLS-1$ //$NON-NLS-2$

        DataType dataType = getResultColumn(stmt, 0).getValueExpr().getDataType();
        assertTrue("collection data type not set right, expected ArrayDataType, got"+dataType.getName(), dataType instanceof ArrayDataType);
        ElementType elementType = ((ArrayDataType) dataType).getElementType();
        assertTrue("collection element type is null", elementType != null);
        DataType elementDataType = elementType.getDataType();
        assertTrue("collection element data type not set right, expected CHAR, got"+dataType.getName(), elementDataType instanceof CharacterStringDataType);
        
        assertDataType(getResultColumn(stmt, 1).getValueExpr(),
                ArrayDataType.class);
        assertDataTypeProperty(getResultColumn(stmt, 1).getValueExpr().getDataType(),
                "maxCardinality", "10"); //$NON-NLS-1$ //$NON-NLS-2$

        // ARRAY[] without cardinality in brackets not allowed
        parserVerifyErrorSingle(
                "SELECT CAST(col1 AS CHAR ARRAY[])"                                       + NL + //$NON-NLS-1$
                "  FROM table1;"); 
    
    }

    public void testSqlDmlParser_resolveDataTypes_CollectionTypes_Multiset()  throws Exception {
        System.out.println("testSqlDmlParser_resolveDataTypes_CollectionTypes_Multiset"); //$NON-NLS-1$

        QuerySelectStatement stmt =
            (QuerySelectStatement) parserVerifySuccessSingleQuery(
                "SELECT CAST(col1 AS CHARACTER MULTISET)"                                       + NL + //$NON-NLS-1$
                "  FROM table1;", matchInput);                                    //$NON-NLS-1$

        assertDataType(getResultColumn(stmt, 0).getValueExpr(),
                MultisetDataType.class);


        // MULTISET[] with brackets not allowed
        parserVerifyErrorSingle(
                "SELECT CAST(col1 AS CHAR MULTISET[])"                                       + NL + //$NON-NLS-1$
                "  FROM table1;"); 
    
    }

    
    
    // TODO: add more test cases for different ValueExpression types
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    protected void assertDataType(QueryValueExpression valueExpr,
            Class dataTypeClass) {
        
        assertTrue("ValueExpression is null", valueExpr != null);               //$NON-NLS-1$
        
        DataType dataType = valueExpr.getDataType();
        
        assertTrue("DataType for expression \""+valueExpr.getSQL()+"\" is null", //$NON-NLS-1$ //$NON-NLS-2$
                dataType != null);
        
        assertTrue("DataType "+dataType.getClass()+" doesn't match required "+dataTypeClass.getName(), //$NON-NLS-1$ //$NON-NLS-2$
                dataTypeClass != null && dataTypeClass.isAssignableFrom(dataType.getClass()));
        
    }

    protected void assertDataType(QueryValueExpression valueExpr,
            Class dataTypeClass, PrimitiveType primitiveType) {

        assertTrue("ValueExpression is null", valueExpr != null);               //$NON-NLS-1$
        
        DataType dataType = valueExpr.getDataType();
        
        assertDataType(valueExpr, dataTypeClass);
        
        assertTrue("primitive data type "+((PredefinedDataType) dataType).getPrimitiveType().getName()+" doesn't match required "+primitiveType.getName(), //$NON-NLS-1$
                primitiveType != null
                && dataType instanceof PredefinedDataType
                && ((PredefinedDataType) dataType).getPrimitiveType() == primitiveType);
    }

    
    /**
     * Tests value assertion on a property of a <code>DataType</code>, e.g.
     * <code>length</code>.
     * 
     * @param dataType
     * @param propertyName
     * @param propertyValue
     */
    protected void assertDataTypeProperty(DataType dataType, String propertyName, String propertyValue) {
        EAttribute eProperty =  (EAttribute) dataType.eClass().getEStructuralFeature(propertyName);
        
        String dtPropertyValue = dataType.eGet(eProperty).toString();
        
        assertTrue("DataType "+propertyName+" "+dtPropertyValue+ //$NON-NLS-1$ //$NON-NLS-2$
                " doesn't match required "+propertyValue, //$NON-NLS-1$
                propertyValue == null || propertyValue.length() == 0
                || (eProperty != null && dtPropertyValue.equals(propertyValue)));
    }

    
}
