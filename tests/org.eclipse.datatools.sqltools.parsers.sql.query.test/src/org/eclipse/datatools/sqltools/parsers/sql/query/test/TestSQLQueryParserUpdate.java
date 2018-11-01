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

import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;

/**
 * @author ckadner
 *
 */
public final class TestSQLQueryParserUpdate extends AbstractTestSQLQueryParser
{

    /**
     * @param name
     */
    public TestSQLQueryParserUpdate(String name)
    {
        super(name);
    }
        
    public void testSqlDmlParser002_updateBasic() throws Exception {
        System.out.println("test 002 update basic"); //$NON-NLS-1$
        parserVerifySuccess(
    	        "update cl_sched" +  //$NON-NLS-1$
    	        " set col1 = '3565'" +  //$NON-NLS-1$
    	        " where col2 = '000190';" + NL +  //$NON-NLS-1$
    	        
    	        "update cl_sched as an" +  //$NON-NLS-1$
    	        " set col1 = 'abc', col2 = 256, col3 = null" +  //$NON-NLS-1$
    	        " where col4 = col5 + 250;" + NL + //$NON-NLS-1$
    
    	        "update cl_sched" +  //$NON-NLS-1$
    	        " set (col1, col2, col3) = ('abc', 256, null)" +  //$NON-NLS-1$
    	        " where col4 = '000250';", matchInput  //$NON-NLS-1$
    	        );
    }
    

    public void testSqlDmlParser002_updateColRefs()  throws Exception {
        System.out.println("test 002_updateColRefs"); //$NON-NLS-1$
        parserVerifySuccess(
    
            "update cl_sched as an" +  //$NON-NLS-1$
            " set col1 = 'abc', col2 = 256, col3 = null" +  //$NON-NLS-1$
            " where col4 = cl_sched.col5 + 250;" + NL + //$NON-NLS-1$
    
            "update cl_sched an" +  //$NON-NLS-1$
            " set col1 = col3 + 100" +  //$NON-NLS-1$
            " where col2 = an.col4 - 10;" + NL + //$NON-NLS-1$
            
            "update cl_sched as an" +  //$NON-NLS-1$
            " set col1 = 'abc', col2 = 256, col3 = null" +  //$NON-NLS-1$
            " where col4 = other.col4 + 250;" + NL, false  //$NON-NLS-1$
            
            );
    }
    

    public void testSqlDmlParser002_updateCondition()  throws Exception {
        System.out.println("test 002_updateCondition"); //$NON-NLS-1$
        String u = "update cl_sched set col0 = 00120"; //$NON-NLS-1$
        parserVerifySuccess(
            u+ " where (col0 = '000190');" + NL + //$NON-NLS-1$
            u+ " where (col1 = '000190') or  col2 = '000180';" + NL + //$NON-NLS-1$
            u+ " where (col2 = '000190') or (col2 = '000180');" + NL + //$NON-NLS-1$
            u+ " where  col3 = '000190'  or (col2 = '000180');" + NL + //$NON-NLS-1$
            u+ " where  col4 = '000190'  or (col2 = '000180') and col3 = 500;" + NL + //$NON-NLS-1$
            u+ " where  col5 = '000190'  or (col2 = '000180'  and col3 = 500);" + NL + //$NON-NLS-1$
            u+ " where  col6 = '000190'  or  col2 = '000180'  and col3 = 500;" + NL +  //$NON-NLS-1$
            u+ " where (col7 = '000190'  or  col7 = '000180') and col3 = 500;" + NL +  //$NON-NLS-1$
            u+ " where NOT  col8 = '000250' and col1 = 'ABC';" + NL +  //$NON-NLS-1$
            u+ " where NOT (col9 = '000250' and col1 = 'ABC');" + NL + //$NON-NLS-1$
            u+ " where NOT (NOT col10 = '000250' and col1 = 'ABC');" + NL + //$NON-NLS-1$
            u+ " where NOT ((NOT col11 = '000250' and col1 = 'ABC'));" + NL + //$NON-NLS-1$
            u+ " where NOT (NOT (NOT col12 = '000250' and col1 = 'ABC'));" + NL, matchInput //$NON-NLS-1$
        );
    }
    

    public void testSqlDmlParser002_updateModel()  throws Exception {
        System.out.println("test 002_updateModel"); //$NON-NLS-1$
    
        List update = parserVerifySuccess(
            	        "update cl_sched" +  //$NON-NLS-1$
            	        " set col1 = '3565'" +  //$NON-NLS-1$
            	        " where col1 = col3 + 100;", matchInput );  //$NON-NLS-1$
        QueryUpdateStatement updateStmt = (QueryUpdateStatement)update.get(0);
        QuerySearchCondition searchCond = (QuerySearchCondition)updateStmt.getWhereClause();
        PredicateBasic predicate = (PredicateBasic)searchCond;
        ValueExpressionColumn columnExpr = (ValueExpressionColumn)predicate.getLeftValueExpr();
        // model related assertion/test, wrong place here. 
        // check if association between table and column, deleted ownership relation between predicate and column
        assertNotNull("Check there's no owner relationship between "+ValueExpressionColumn.class+" and "+TableExpression.class,columnExpr); //$NON-NLS-1$ //$NON-NLS-2$
    }
    

    public void testSqlDmlParser002_updateSourceQuery()  throws Exception {
        System.out.println("test 002_updateSourceQuery"); //$NON-NLS-1$
        parserVerifySuccess(
    		"update table2 set col0 = (select col0 from table1);" + NL + //$NON-NLS-1$
    		"update table2 set (col1,col2,col3) = (select col1,col2,col3 from table1);" + NL + //$NON-NLS-1$
    		"update table2 set (col1,col2,col3) = (select col1,col2,col3 from table1)," + NL + //$NON-NLS-1$
    		"                  (col3,col4)      = (select col3,col4 from table1);", false); //$NON-NLS-1$
    }
    

    public void testSqlDmlParser002_updateValueComparison()  throws Exception {
        System.out.println("test 002_updateValueComparison"); //$NON-NLS-1$
        String u = "update cl_sched set col0 = 00120"; //$NON-NLS-1$
        parserVerifySuccess(
            u+ " where col0 >  0 and col0 < 100;" + NL + //$NON-NLS-1$
            u+ " where col0 >= 1 and col0 <= 99;" + NL + //$NON-NLS-1$
            u+ " where col1 <> 0 or  col2 > 180;" + NL, matchInput //$NON-NLS-1$
    		);
    }

    
    public void testSqlDmlParser002_updateWhereExists()  throws Exception {
        System.out.println("test 002_updateWhereExists"); //$NON-NLS-1$
        parserVerifySuccess(
                "update employee set salary = salary * 0.1 where exists (select * from department where mgrno = empno);" + NL, matchInput //$NON-NLS-1$
                );
            parserVerifySuccess(
                "update employee as e set e.salary = e.salary * 0.1 where exists (select * from department d where d.mgrno = e.empno);" + NL, false //$NON-NLS-1$
                );
    }  
    
    
}
