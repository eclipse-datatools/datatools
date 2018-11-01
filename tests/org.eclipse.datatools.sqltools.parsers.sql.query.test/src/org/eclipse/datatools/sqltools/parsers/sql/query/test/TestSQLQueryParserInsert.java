/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query.test;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;

/**
 * @author ckadner
 *
 */
public final  class TestSQLQueryParserInsert extends AbstractTestSQLQueryParser
{

    /**
     * @param name
     */
    public TestSQLQueryParserInsert(String name)
    {
        super(name);
    }

         
        
        
        
   public void testSqlDmlParser001()  throws Exception {
            System.out.println("test 001"); //$NON-NLS-1$
            parserVerifySuccess(
    			"INSERT INTO department VALUES (34,'TestTools','T01',NULL);"+NL+ //$NON-NLS-1$
    			"INSERT INTO department (DEPTNO, DEPTNAME, ADMRDEPT) VALUES (2,DEFAULT,'T01');"+NL+ //$NON-NLS-1$
    			"INSERT INTO department (DEPTNO, DEPTNAME, ADMRDEPT) VALUES (03,DEFAULT,'T01'),(03,654,'T01'),(462,NULL,'T01');", matchInput); //$NON-NLS-1$
        }

    public void testSqlDmlParser001_sourceQuery()  throws Exception {
        System.out.println("test 001_sourceQuery"); //$NON-NLS-1$
        List result = parserVerifySuccess(
    		"insert into tab1 (col1,col2) select col3, col4 from tab2;"+NL+ //$NON-NLS-1$
        	"insert into tab1 (col1,col2) values (1,2),(3,4);", matchInput); //$NON-NLS-1$
        
        QueryInsertStatement insert2 = (QueryInsertStatement) result.get(1);
        List insertValues = insert2.getSourceValuesRowList();
        QueryExpressionRoot insertQuery = insert2.getSourceQuery();
        
        String assertMsg = 
            "VALUES clause of insert stmt should not be parsed as " + //$NON-NLS-1$
            "QueryValues source query"; //$NON-NLS-1$
        assertTrue(assertMsg, insertValues != null && !insertValues.isEmpty());
        assertNull(assertMsg, insertQuery);
    }

    public void testSqlDmlParser001_unaryOperator()  throws Exception {
        System.out.println("test 001_unaryOperator"); //$NON-NLS-1$
        parserVerifySuccess(
    		"INSERT INTO department (DEPTNO, DEPTNAME, ADMRDEPT) VALUES (+3,DEFAULT,'T01');"+NL+ //$NON-NLS-1$
    		"INSERT INTO department (DEPTNO, DEPTNAME, ADMRDEPT) VALUES (-(1+ADMRDEPT)*3,DEFAULT,'T01'),(03,654,'T01'),(3/(1+2),NULL,'T01');"+NL+ //$NON-NLS-1$
    		"INSERT INTO department (DEPTNO, DEPTNAME, ADMRDEPT) VALUES ((1+ADMRDEPT)*-3,DEFAULT,'T01'),(03,654,'T01'),(3/(1+2),NULL,'T01');", matchInput); //$NON-NLS-1$
    }

    public void testSqlDmlParser001_valueExpr()  throws Exception {
        System.out.println("test 001_valueExpr"); //$NON-NLS-1$
        parserVerifySuccess(
    		"INSERT INTO department (DEPTNO, DEPTNAME, ADMRDEPT) VALUES (3+2*5-7.0,DEFAULT,'T01');"+NL+ //$NON-NLS-1$
    		// TODO: values clause can not contain column reference!
    		"INSERT INTO department (DEPTNO, DEPTNAME, ADMRDEPT) VALUES ((1+ADMRDEPT)*3,DEFAULT,'T01'),(03,654,'T01'),(3/(1+2),NULL,'T01');", matchInput); //$NON-NLS-1$
    }

    
    
    
    
}
