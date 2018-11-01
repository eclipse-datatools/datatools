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


/**
 * @author ckadner
 *
 */
public final class TestSQLQueryParserDelete extends AbstractTestSQLQueryParser
{

    /**
     * @param name
     */
    public TestSQLQueryParserDelete(String name)
    {
        super(name);
    }

        
        
    public void testSqlDmlParser003()  throws Exception {
        System.out.println("test 003"); //$NON-NLS-1$
        parserVerifySuccess(
            "delete from tbl;" + NL +  //$NON-NLS-1$
            "delete from v1 al;" + NL +  //$NON-NLS-1$
            "delete from nn as al where col1 = 10;", matchInput); //$NON-NLS-1$
    }

    public void testSqlDmlParser003_betweenPredicate()  throws Exception {
        System.out.println("test 003_betweenPredicate"); //$NON-NLS-1$
        boolean matchInputBefore = matchInput;
        matchInput = false;
        parserVerifySuccess(
            "delete from emp where col1 between 30 and 50;" + NL +  //$NON-NLS-1$
            "delete from emp where col1 not between 20 and 30;", matchInput); //$NON-NLS-1$
        matchInput = matchInputBefore;
    }

    public void testSqlDmlParser003_booleanExpr()  throws Exception {
        System.out.println("test 003_booleanExpr"); //$NON-NLS-1$
        parserVerifySuccess(
            "delete from tbl where col0 = 1 is true;" + NL +  //$NON-NLS-1$
            "delete from tbl where col1 = 1 is not false;" + NL +  //$NON-NLS-1$
            "delete from tbl where col2 = 2 is not true;" + NL +  //$NON-NLS-1$
            "delete from tbl where not  col3 = 3;" + NL +  //$NON-NLS-1$
            "delete from tbl where not  col4 = 4 is true;" + NL +  //$NON-NLS-1$
            "delete from tbl where not (col5 = 5 is true);" + NL +  //$NON-NLS-1$
            "delete from tbl where not  col6 = 6 is not false;" + NL +  //$NON-NLS-1$
            "delete from tbl where not (col7 = 7 is not false);" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$
    }

    public void testSqlDmlParser003_booleanExpr_in_cond()  throws Exception {
        System.out.println("test 003_booleanExpr_in_cond"); //$NON-NLS-1$
        parserVerifySuccess(
            "delete from tbl where col1 = 10 is true;" + NL +  //$NON-NLS-1$
            "delete from tbl where col1 = 10 is not false or col2 = 23;" + NL +  //$NON-NLS-1$
            "delete from tbl where col1 = 10 is not true and col2 is null;" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$
        parserVerifyError(
            "delete from tbl where col1 = 10 is not (true and col2 is null);" + NL +  //$NON-NLS-1$
            ""); //$NON-NLS-1$
    }

    public void testSqlDmlParser003_comment()  throws Exception {
        System.out.println("test 003_comment"); //$NON-NLS-1$
        parserVerifySuccess(
            "delete from tbl --comment \n where col0 like '%test%';" + NL +  //$NON-NLS-1$
            "--comment \n delete from tbl where col2 like '5_aa%' escape 'aa';" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$
    }

    public void testSqlDmlParser003_like()  throws Exception {
        System.out.println("test 003_like"); //$NON-NLS-1$
        parserVerifySuccess(
            "delete from tbl where col0 like '%test%';" + NL +  //$NON-NLS-1$
            "delete from tbl where col1 not like '%SYSTEM%';" + NL +  //$NON-NLS-1$
            "delete from tbl where col2 like '5_+%' escape '+';" + NL +  //$NON-NLS-1$
            "delete from tbl where col2 like '5_aa%' escape 'aa';" + NL +  //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
    }

    public void testSqlDmlParser003_null()  throws Exception {
        System.out.println("test 003_null"); //$NON-NLS-1$
        parserVerifySuccess(
            "delete from tbl where col0 = null;" + NL +  //$NON-NLS-1$
            "delete from tbl where col1 is null;" + NL +  //$NON-NLS-1$
            "delete from tbl where col2 is not null;" + NL +  //$NON-NLS-1$
            "delete from tbl where not col3 is null;" + NL +  //$NON-NLS-1$
            "delete from tbl where not col4 is not null;" + NL +  //$NON-NLS-1$
            "", matchInput); //$NON-NLS-1$
    }

    public void testSqlDmlParser003_nullPredicate()  throws Exception {
        System.out.println("test 003_nullPredicate"); //$NON-NLS-1$
        parserVerifySuccess(
            "delete from tbl where col1 is null;" + NL +  //$NON-NLS-1$
            "delete from tbl where col1 is not null;" + NL +  //$NON-NLS-1$
            "delete from tbl where 3 + 5 is not null;", matchInput //$NON-NLS-1$
            );
    }

    public void testSqlDmlParser003_softKeyword_schemaName()  throws Exception {
        System.out.println("test 003_softKeyword_schemaName"); //$NON-NLS-1$
        parserVerifySuccess(
            "delete from schema0.tbl where col0 = 10;" + NL +  //$NON-NLS-1$
            "delete from schema1.tbl as where col1 = 10;" + NL +  //$NON-NLS-1$
            "delete from schema2.tbl as where as.col2 = 10;" + NL +  //$NON-NLS-1$
            "delete from schema3.tbl as as where tbl.col3 = 10;" + NL +  //$NON-NLS-1$
            "delete from schema4.tbl as as where TBL.col4 = 10;" + NL +  //$NON-NLS-1$
            "", false); //$NON-NLS-1$
    }

   
    
    
    
}
