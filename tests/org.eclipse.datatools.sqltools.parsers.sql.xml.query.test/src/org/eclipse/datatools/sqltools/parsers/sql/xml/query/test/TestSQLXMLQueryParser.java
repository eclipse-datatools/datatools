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
package org.eclipse.datatools.sqltools.parsers.sql.xml.query.test;

import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionConcat;
import org.eclipse.datatools.sqltools.parsers.sql.query.test.AbstractTestSQLQueryParser;
import org.eclipse.datatools.sqltools.parsers.sql.xml.query.SQLXMLQueryParserManager;


/**
 * @author ckadner
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestSQLXMLQueryParser extends AbstractTestSQLQueryParser {

    /**
     * @param name
     */
    public TestSQLXMLQueryParser(String name) {
        super(name, SQLXMLQueryParserManager.class);
        matchInput = true;
    }

    /**
     * @param name
     * @param parserManagerClass
     */
    public TestSQLXMLQueryParser(String name, Class parserManagerClass) {
        super(name, parserManagerClass);
        matchInput = true;
    }

    
    public void testSqlDmlParser00x_xml_functions()  throws Exception {
        System.out.println("test 00x_xml_functions"); //$NON-NLS-1$
        
        String stmt = 
            "SELECT emp.empno, XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"employee\"," + NL + //$NON-NLS-1$
            "      XMLNAMESPACES(DEFAULT 'http://hr.org'), emp.lastname," + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"job\"," + NL + //$NON-NLS-1$
            "        XMLNAMESPACES(NO DEFAULT), emp.job," + NL + //$NON-NLS-1$
            "        XMLELEMENT(NAME \"department\"," + NL + //$NON-NLS-1$
            "          XMLNAMESPACES(DEFAULT 'http://adm.org'), emp.workdept))) AS CLOB) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee AS emp" + NL + //$NON-NLS-1$
            "  WHERE emp.edlevel = 12;"; //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        // we know we don't get the colRefs qualified
        String input = stmt.replaceAll("emp\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (matchInput) matchInputStatement(input, query);
    }
    
    public void testSqlDmlParser00x_xml_functions_2()  throws Exception {
        System.out.println("test 00x_xml_functions_2"); //$NON-NLS-1$
        
        String stmt =
            "SELECT e.empno, XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Emp\", e.firstnme || ' ' || e.lastname) AS CLOB)" + //$NON-NLS-1$
            "        AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.edlevel = 12;"; //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        // we know we don't get the colRefs qualified
        String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (matchInput) matchInputStatement(input, query);

    }
    
    public void testSqlDmlParser00x_xml_functions_3()  throws Exception {
        System.out.println("test 00x_xml_functions_3"); //$NON-NLS-1$
        
        String stmt =
            "SELECT XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Department\"," + NL + //$NON-NLS-1$
            "      XMLATTRIBUTES(e.workdept AS \"name\")," + NL + //$NON-NLS-1$
            "      XMLAGG( XMLELEMENT(NAME \"emp\", e.lastname) ORDER BY e.lastname ))" + NL + //$NON-NLS-1$
            "    AS CLOB ) AS \"dept_list\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.workdept IN ('C01','E21')" + NL + //$NON-NLS-1$
            "  GROUP BY workdept;"; //$NON-NLS-1$
            
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        // we know we don't get the colRefs qualified
        String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (matchInput) matchInputStatement(input, query);
    }
    
    public void testSqlDmlParser00x_xml_functions_4()  throws Exception {
        System.out.println("test 00x_xml_functions_4"); //$NON-NLS-1$
        
        String stmt =
            "SELECT d.deptno, XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Mgr\"," + //$NON-NLS-1$
            "      XMLATTRIBUTES(d.mgrno AS \"Id\")) AS CLOB) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM department d" + NL + //$NON-NLS-1$
            "  WHERE d.admrdept = 'A00';"; //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        // we know we don't get the colRefs qualified
        String input = stmt.replaceAll("d\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (matchInput) matchInputStatement(input, query);
    }
    
    public void testSqlDmlParser00x_xml_functions_5()  throws Exception {
        System.out.println("test 00x_xml_functions_5"); //$NON-NLS-1$
        
        String stmt =
            "SELECT e.empno, XMLSERIALIZE (CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Emp\"," + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"name\", e.firstnme || ' ' || e.lastname)," + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"hiredate\", e.hiredate))" + NL + //$NON-NLS-1$
            "    AS CLOB) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.edlevel = 12;"; //$NON-NLS-1$
            
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        // we know we don't get the colRefs qualified
        String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (matchInput) matchInputStatement(input, query);
    }
    
    public void testSqlDmlParser00x_xml_functions_6()  throws Exception {
        System.out.println("test 00x_xml_functions_6"); //$NON-NLS-1$
        
        String stmt =
            "SELECT XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Emp_Exempt\"," + NL + //$NON-NLS-1$
            "      XMLATTRIBUTES(e.firstnme AS \"firstname\"," + NL + //$NON-NLS-1$
            "                    e.lastname AS \"lastname\"," + NL + //$NON-NLS-1$
            "                    e.midinit AS \"midinit\")) AS CLOB) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.lastname='GEYER';"; //$NON-NLS-1$
            
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        // we know we don't get the colRefs qualified
        String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (matchInput) matchInputStatement(input, query);

        stmt =
            "SELECT XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Emp_Exempt\"," + NL + //$NON-NLS-1$
            "      XMLATTRIBUTES(e.firstnme AS \"firstname\"," + NL + //$NON-NLS-1$
            "                    e.lastname AS \"name:last\"," + NL + //$NON-NLS-1$
            "                    e.midinit AS \"midinit\")) AS CLOB) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.lastname='GEYER';"; //$NON-NLS-1$
                
        query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        // we know we don't get the colRefs qualified
        input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (matchInput) matchInputStatement(input, query);
    }
    
    public void testSqlDmlParser00x_xml_functions_7()  throws Exception {
        System.out.println("test 00x_xml_functions_7"); //$NON-NLS-1$
        
        String stmt =
            "SELECT e.empno, XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Emp\"," + NL + //$NON-NLS-1$
            "      XMLFOREST(e.firstnme || ' ' || e.lastname AS \"Name\"," + NL + //$NON-NLS-1$
            "                e.hiredate)) AS CLOB) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.edlevel = 12;"; //$NON-NLS-1$
            
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        // we know we don't get the colRefs qualified
        String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (matchInput) matchInputStatement(input, query);
    }
    
    public void testSqlDmlParser00x_xml_functions_8()  throws Exception {
        System.out.println("test 00x_xml_functions_8"); //$NON-NLS-1$
        
        String stmt =
            "SELECT e.empno, XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLCONCAT(" + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"firstname\", e.firstnme)," + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"lastname\", e.lastname)) AS CLOB) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.edlevel = 12;"; //$NON-NLS-1$
            
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        // we know we don't get the colRefs qualified
        String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (matchInput) matchInputStatement(input, query);
    }
    
    public void testSqlDmlParser00x_xml_functions_9()  throws Exception {
        System.out.println("test 00x_xml_functions_9"); //$NON-NLS-1$
        
        String stmt =
            "SELECT empno, XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"adm:employee\"," + NL + //$NON-NLS-1$
            "      XMLNAMESPACES('http://www.adm.com' AS \"adm\")," + NL + //$NON-NLS-1$
            "      XMLATTRIBUTES(workdept AS \"adm:department\")," + NL + //$NON-NLS-1$
            "      lastname) AS CLOB) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee" + NL + //$NON-NLS-1$
            "  WHERE job = 'ANALYST';"; //$NON-NLS-1$
            
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);

        if (matchInput) matchInputStatement(stmt, query);
    }
    
    public void testSqlDmlParser00x_xml_functions_10()  throws Exception {
        System.out.println("test 00x_xml_functions_10"); //$NON-NLS-1$
        
        String stmt =
            "SELECT empno, XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLFOREST(" + NL + //$NON-NLS-1$
            "      XMLNAMESPACES(DEFAULT 'http://hr.org', 'http://fed.gov' AS \"d\")," + NL + //$NON-NLS-1$
            "      lastname," + NL + //$NON-NLS-1$
            "      job AS \"d:job\") AS CLOB) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee" + NL + //$NON-NLS-1$
            "  WHERE edlevel = 12;"; //$NON-NLS-1$
            
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);

        if (matchInput) matchInputStatement(stmt, query);
    }
    
    public void testSqlDmlParser00x_xml_functions_11()  throws Exception {
        System.out.println("test 00x_xml_functions_11"); //$NON-NLS-1$
        
        String stmt =
            "SELECT emp.empno, XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "  XMLELEMENT(NAME \"employee\"," + NL + //$NON-NLS-1$
            "    XMLNAMESPACES(DEFAULT 'http://hr.org')," + NL + //$NON-NLS-1$
            "    emp.lastname," + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"department\"," + NL + //$NON-NLS-1$
            "      XMLNAMESPACES(NO DEFAULT)," + NL + //$NON-NLS-1$
            "      emp.workdept)) AS CLOB) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee emp" + NL + //$NON-NLS-1$
            "  WHERE emp.edlevel = 12;"; //$NON-NLS-1$
            
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        // we know we don't get the colRefs qualified
        String input = stmt.replaceAll("emp\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (matchInput) matchInputStatement(input, query);
    }
    
    public void testSqlDmlParser00x_xml_functions_12()  throws Exception {
        System.out.println("test 00x_xml_functions_12"); //$NON-NLS-1$
//TODO: move to DB2 parser
//        
//        String stmt =
//            //"SELECT XML2CLOB(XMLELEMENT(NAME,emp.lastname)) FROM employee emp;" + NL +
//            "SELECT XML2CLOB(XMLELEMENT(NAME \"employee\",emp.lastname)) FROM employee emp;"; //$NON-NLS-1$
//        
//        QuerySelectStatement query =
//            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
//        // we know we don't get the colRefs qualified
//        String input = stmt.replaceAll("emp\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
//        if (matchInput) matchInputStatement(input, query);
    }
    
    public void testSqlDmlParser00x_xml_functions_13()  throws Exception {
        System.out.println("test 00x_xml_functions_13"); //$NON-NLS-1$
        
//      TODO: move to DB2 parser
//        String stmt =
//            "SELECT emp.empno, XML2CLOB(" + NL + //$NON-NLS-1$
//            "  XMLELEMENT(NAME \"employee\"," + NL + //$NON-NLS-1$
//            "    XMLNAMESPACES(DEFAULT 'http://hr.org')," + NL + //$NON-NLS-1$
//            "    emp.lastname," + NL + //$NON-NLS-1$
//            "    XMLELEMENT(NAME \"department\"," + NL + //$NON-NLS-1$
//            "      XMLNAMESPACES(NO DEFAULT)," + NL + //$NON-NLS-1$
//            "      emp.workdept))) AS \"Result\"" + NL + //$NON-NLS-1$
//            "  FROM employee emp" + NL + //$NON-NLS-1$
//            "  WHERE emp.edlevel = 12;"; //$NON-NLS-1$
//        
//        QuerySelectStatement query =
//            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
//        // we know we don't get the colRefs qualified
//        String input = stmt.replaceAll("emp\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
//        if (matchInput) matchInputStatement(input, query);
            
    }
    
    public void testSqlDmlParser00x_xml_functions_14_XMLAGG()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_14_XMLAGG"); //$NON-NLS-1$
        
        String stmt =
            "SELECT XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Department\"," + NL + //$NON-NLS-1$
            "      XMLATTRIBUTES(e.workdept AS \"name\")," + NL + //$NON-NLS-1$
            "      XMLAGG( XMLELEMENT(NAME \"emp\", e.lastname) ORDER BY e.lastname DESC ))" + NL + //$NON-NLS-1$
            "    AS CLOB ) AS \"dept_list\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.workdept IN ('C01','E21')" + NL + //$NON-NLS-1$
            "  GROUP BY workdept;"; //$NON-NLS-1$

        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
            
    }

    public void testSqlDmlParser00x_xml_functions_15_XMLAGG()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_15_XMLAGG"); //$NON-NLS-1$
        
        String stmt =
            "SELECT XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Department\"," + NL + //$NON-NLS-1$
            "      XMLATTRIBUTES(e.workdept AS \"name\")," + NL + //$NON-NLS-1$
            "      XMLAGG( XMLELEMENT(NAME \"emp\", e.lastname) ORDER BY e.lastname NULLS FIRST ))" + NL + //$NON-NLS-1$
            "    AS CLOB ) AS \"dept_list\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.workdept IN ('C01','E21')" + NL + //$NON-NLS-1$
            "  GROUP BY workdept;"; //$NON-NLS-1$

        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
                if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }

    }

    public void testSqlDmlParser00x_xml_functions_16_XMLAGG()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_16_XMLAGG"); //$NON-NLS-1$
        
        String stmt =
            "SELECT XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Department\"," + NL + //$NON-NLS-1$
            "      XMLATTRIBUTES(e.workdept AS \"name\")," + NL + //$NON-NLS-1$
            "      XMLAGG( XMLELEMENT(NAME \"emp\", e.lastname) ORDER BY e.lastname DESC NULLS LAST ))" + NL + //$NON-NLS-1$
            "    AS CLOB ) AS \"dept_list\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.workdept IN ('C01','E21')" + NL + //$NON-NLS-1$
            "  GROUP BY workdept;"; //$NON-NLS-1$

        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
                if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }

    }
    public void testSqlDmlParser00x_xml_functions_17_XMLAGG()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_17_XMLAGG"); //$NON-NLS-1$
        
        String stmt =
            "SELECT XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Department\"," + NL + //$NON-NLS-1$
            "      XMLATTRIBUTES(e.workdept AS \"name\")," + NL + //$NON-NLS-1$
            "      XMLAGG( XMLELEMENT(NAME \"emp\", e.lastname) ORDER BY" + //$NON-NLS-1$
            " e.lastname DESC, e.firstname RETURNING CONTENT))" + NL + //$NON-NLS-1$
            "    AS CLOB ) AS \"dept_list\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.workdept IN ('C01','E21')" + NL + //$NON-NLS-1$
            "  GROUP BY workdept;"; //$NON-NLS-1$

        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
        
    }
    public void testSqlDmlParser00x_xml_functions_18_XMLAGG()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_18_XMLAGG"); //$NON-NLS-1$
        
        String stmt =
            "SELECT XMLSERIALIZE(CONTENT" + NL + //$NON-NLS-1$
            "    XMLELEMENT(NAME \"Department\"," + NL + //$NON-NLS-1$
            "      XMLATTRIBUTES(e.workdept AS \"name\")," + NL + //$NON-NLS-1$
            "      XMLAGG( XMLELEMENT(NAME \"emp\", e.lastname) ORDER BY" + //$NON-NLS-1$
            " e.lastname DESC, e.firstname RETURNING SEQUENCE))" + NL + //$NON-NLS-1$
            "    AS CLOB ) AS \"dept_list\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.workdept IN ('C01','E21')" + NL + //$NON-NLS-1$
            "  GROUP BY workdept;"; //$NON-NLS-1$

        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }

    }

    public void testSqlDmlParser00x_xml_functions_19_XMLCONCAT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_19_XMLCONCAT"); //$NON-NLS-1$
        
        String stmt =
            "SELECT " + NL + //$NON-NLS-1$
            "    XMLCONCAT(" + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"firstname\", e.firstnme)," + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"lastname\", e.lastname)) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.edlevel = 12;"; //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
           if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
    
        stmt = "SELECT "+ NL + //$NON-NLS-1$
        "    XMLCONCAT(" + NL + //$NON-NLS-1$
        "      XMLELEMENT(NAME \"lastname\", e.lastname))  AS \"Result\"" + NL + //$NON-NLS-1$
        "  FROM employee e" + NL + //$NON-NLS-1$
        "  WHERE e.edlevel = 12;"; //$NON-NLS-1$

        query =   (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
        
        stmt = "SELECT "+ NL + //$NON-NLS-1$
        "    XMLCONCAT()"+ NL + //$NON-NLS-1$
        "  FROM employee e" + NL + //$NON-NLS-1$
        "  WHERE e.edlevel = 12;"; //$NON-NLS-1$

        query = (QuerySelectStatement) parserVerifySuccessSingleQuery(stmt, false);
        ResultColumn resltCol = getResultColumn(query, 0);
         
        assertTrue("XMLCONCAT() should not be interpreted as a XMLConcatFunction, but as UDT without args", 
                !(resltCol.getValueExpr() instanceof XMLValueFunctionConcat));
//        assertTrue("XMLCONCAT() should not be interpreted as a XMLConcatFunction, but as UDT without args", false);
        
    }
    public void testSqlDmlParser00x_xml_functions_20_XMLCONCAT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_20_XMLCONCAT"); //$NON-NLS-1$
        
        String stmt =
            "SELECT " + NL + //$NON-NLS-1$
            "    XMLCONCAT(" + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"firstname \", e.firstnme)," + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"lastname\", e.lastname) RETURNING CONTENT) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.edlevel = 12;"; //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
           if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
    }    
    public void testSqlDmlParser00x_xml_functions_21_XMLCONCAT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_21_XMLCONCAT"); //$NON-NLS-1$
        
        String stmt =
            "SELECT " + NL + //$NON-NLS-1$
            "    XMLCONCAT(" + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"firstname\", e.firstnme)," + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"lastname\", e.lastname) RETURNING SEQUENCE) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" + NL + //$NON-NLS-1$
            "  WHERE e.edlevel = 12;"; //$NON-NLS-1$
        
            QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
                if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_22_XMLCOMMENT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_22_XMLCOMMENT"); //$NON-NLS-1$
        
        String stmt =
            "SELECT " + NL + //$NON-NLS-1$
            "    XMLCONCAT(" + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"firstname\", e.firstnme)," + NL + //$NON-NLS-1$
            "    XMLCOMMENT(\"first name\")," + NL + //$NON-NLS-1$ 
            "      XMLELEMENT(NAME \"lastname\", e.lastname), " + //$NON-NLS-1$
            "    XMLCOMMENT(\"last name\")" + NL + //$NON-NLS-1$ 
            "     RETURNING CONTENT) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" ; //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
                if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
 
    }
    public void testSqlDmlParser00x_xml_functions_23_XMLCOMMENT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_23_XMLCOMMENT"); //$NON-NLS-1$
        
        String stmt =
            "SELECT " + NL + //$NON-NLS-1$
            "    XMLCONCAT(" + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"firstname\", e.firstnme)," + NL + //$NON-NLS-1$
            "    XMLCOMMENT(\"first name\")," + NL + //$NON-NLS-1$ 
            "      XMLELEMENT(NAME \"lastname\", e.lastname), " + //$NON-NLS-1$
            "    XMLCOMMENT(\"last name\")" + NL + //$NON-NLS-1$ 
            "     RETURNING SEQUENCE) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" ; //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
                if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
 
    }

    public void testSqlDmlParser00x_xml_functions_24_XMLCOMMENT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_24_XMLCOMMENT"); //$NON-NLS-1$
        
        String stmt =
            "SELECT " + NL + //$NON-NLS-1$
            "    XMLCONCAT(" + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"firstname\", e.firstnme)," + NL + //$NON-NLS-1$
            "    XMLCOMMENT(\"first name\" RETURNING SEQUENCE)," + NL + //$NON-NLS-1$ 
            "      XMLELEMENT(NAME \"lastname\", e.lastname), " + //$NON-NLS-1$
            "    XMLCOMMENT(\"last name\" || \"end of comment \")" + NL + //$NON-NLS-1$ 
            "    ) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" ; //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
                if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
 
    }
    public void testSqlDmlParser00x_xml_functions_25_XMLCOMMENT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_25_XMLCOMMENT"); //$NON-NLS-1$
        
        String stmt =
            "SELECT " + NL + //$NON-NLS-1$
            "    XMLCONCAT(" + NL + //$NON-NLS-1$
            "      XMLELEMENT(NAME \"firstname\", e.firstnme)," + NL + //$NON-NLS-1$
            "    XMLCOMMENT(\"first name\")," + NL + //$NON-NLS-1$ 
            "      XMLELEMENT(NAME \"lastname\", e.lastname), " + //$NON-NLS-1$
            "    XMLCOMMENT(\"last name\" + \" End of comment \" RETURNING CONTENT)" + NL + //$NON-NLS-1$ 
            "    ) AS \"Result\"" + NL + //$NON-NLS-1$
            "  FROM employee e" ; //$NON-NLS-1$
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
                if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
 
    }
    public void testSqlDmlParser00x_xml_functions_26_XMLCAST()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_26_XMLCAST"); //$NON-NLS-1$
        
        String stmt =
            "INSERT INTO xs.products VALUES ( 3, 10, XMLCAST" + NL + //$NON-NLS-1$
            "(XMLELEMENT(NAME \"firstname\", firstnme) as varchar(20))" + NL + //$NON-NLS-1$
            ")";
        QueryInsertStatement query = (QueryInsertStatement)parserVerifySuccessSingle(stmt, matchInput);
    }

    public void testSqlDmlParser00x_xml_functions_27_XMLCAST()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_27_XMLCAST"); //$NON-NLS-1$
        String stmt =
            "INSERT INTO xs.products VALUES ( 3, 10," + NL + //$NON-NLS-1$
            "XMLCAST( XMLELEMENT(NAME \"firstname\", e.firstnme)" + NL + //$NON-NLS-1$
            "AS VARCHAR(20)))";  //$NON-NLS-1$
        QueryInsertStatement query = (QueryInsertStatement)parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
    }

    public void testSqlDmlParser00x_xml_functions_28_XMLCAST()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_28_XMLCAST"); //$NON-NLS-1$
        String stmt =
            "INSERT INTO xs.products VALUES ( 3, 10," + NL + //$NON-NLS-1$
            "XMLCAST(NULL AS XML))";  //$NON-NLS-1$
        QueryInsertStatement query = (QueryInsertStatement)parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            matchInputStatement(stmt,query);
        }
    }
   //TODO We are not supporting CAST AS <domainname> yet
    // The following 3 test cases need to be run when implement this
    public void testSqlDmlParser00x_xml_functions_29_XMLCAST()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_29_XMLCAST"); //$NON-NLS-1$
        String stmt =
            "INSERT INTO xs.products VALUES ( 3, 10," + NL + //$NON-NLS-1$
            "XMLCAST( XMLELEMENT(NAME \"firstname\", e.firstnme)" + NL + //$NON-NLS-1$
            "AS products.name))";  //$NON-NLS-1$
/*        QueryInsertStatement query = (QueryInsertStatement)parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
*/    }
    public void testSqlDmlParser00x_xml_functions_30_XMLCAST()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_30_XMLCAST"); //$NON-NLS-1$
        String stmt =
            "INSERT INTO xs.products VALUES ( 3, 10," + NL + //$NON-NLS-1$
            "XMLCAST( XMLELEMENT(NAME \"firstname\", e.firstnme)" + NL + //$NON-NLS-1$
            "AS products.name BY REF))";  //$NON-NLS-1$
/*        QueryInsertStatement query = (QueryInsertStatement)parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
*/        
        stmt =
            "INSERT INTO xs.products VALUES ( 3, 10," + NL + //$NON-NLS-1$
            "XMLCAST( XMLELEMENT(NAME \"firstname\", e.firstnme)" + NL + //$NON-NLS-1$
            "AS products.name BY VALUE))";  //$NON-NLS-1$
/*        query = (QueryInsertStatement)parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
*/
    }

    // <empty specification> will be removed from the final ISO 2003 spec
/*    public void testSqlDmlParser00x_xml_functions_31_XMLCAST()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_31_XMLCAST"); //$NON-NLS-1$
        String stmt =
            "INSERT INTO xs.products VALUES ( 3, 10," + NL + //$NON-NLS-1$
            "XMLCAST( ARRAY[]" + NL + //$NON-NLS-1$
            "AS products.name BY REF))";  //$NON-NLS-1$
        parserVerifySuccessSingle(stmt, matchInput);
        
        stmt =
            "INSERT INTO xs.products VALUES ( 3, 10," + NL + //$NON-NLS-1$
            "XMLCAST( MULTISET[]" + NL + //$NON-NLS-1$
            "AS products.name BY REF))";  //$NON-NLS-1$
        parserVerifySuccessSingle(stmt, matchInput);
        
        stmt =
            "INSERT INTO xs.products VALUES ( 3, 10," + NL + //$NON-NLS-1$
            "XMLCAST( MULTISET??(??)" + NL + //$NON-NLS-1$
            "AS products.name BY REF))";  //$NON-NLS-1$
        parserVerifySuccessSingle(stmt, matchInput);

    }
*/
       
    public void testSqlDmlParser00x_xml_functions_32_XMLDOCUMENT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_32_XMLDOCUMENT"); //$NON-NLS-1$
        String stmt =
            "INSERT INTO xs.products VALUES ( 3, 10," + NL + //$NON-NLS-1$
            "XMLDOCUMENT(XMLELEMENT(NAME \"firstname\", e.firstnme)))";  //$NON-NLS-1$

        QueryInsertStatement query = (QueryInsertStatement)parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_33_XMLDOCUMENT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_33_XMLDOCUMENT"); //$NON-NLS-1$
        String stmt =
            "INSERT INTO xs.products VALUES ( 3, 10," + NL + //$NON-NLS-1$
            "XMLDOCUMENT(XMLELEMENT(NAME \"firstname\", e.firstnme) RETURNING CONTENT))";  //$NON-NLS-1$
        QueryInsertStatement query = (QueryInsertStatement)parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
        
        stmt =
            "INSERT INTO xs.products VALUES ( 3, 10," + NL + //$NON-NLS-1$
            "XMLDOCUMENT(XMLELEMENT(NAME \"firstname\", e.firstnme) RETURNING SEQUENCE))";  //$NON-NLS-1$
        query = (QueryInsertStatement)parserVerifySuccessSingle(stmt, false);
        if(matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$
            matchInputStatement(input,query);
        }
    }

    public void testSqlDmlParser00x_xml_functions_34_XMLELEMENT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_34_XMLELEMENT"); //$NON-NLS-1$
        
        String stmt =
            "SELECT e.empno, e.firstnme, e.lastname," + NL + //$NON-NLS-1$
            "   XMLELEMENT ( NAME \"foo:Emp\", "+ NL + //$NON-NLS-1$
            "       XMLNAMESPACES('http://www.foo.com' as \"foo\"),"  + NL + //$NON-NLS-1$
            "       XMLATTRIBUTES(e.empno as \"serial\"), e.firstnme, e.lastname"  + NL + //$NON-NLS-1$
            "   OPTION NULL ON NULL ) AS \"Result\" " + NL + //$NON-NLS-1$
            "FROM employees e WHERE e.edlevel = 12;";  //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
        stmt =
            "SELECT e.empno, e.firstnme, e.lastname," + NL + //$NON-NLS-1$
            "   XMLELEMENT ( NAME \"foo:Emp\", "+ NL + //$NON-NLS-1$
            "       XMLNAMESPACES('http://www.foo.com' as \"foo\")"  + NL + //$NON-NLS-1$
            "   ) AS \"Result\" " + NL + //$NON-NLS-1$
            "FROM employees e WHERE e.edlevel = 12;";  //$NON-NLS-1$
        
         query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_35_XMLELEMENT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_35_XMLELEMENT"); //$NON-NLS-1$
        
        String stmt =
            "SELECT e.empno, e.firstnme, e.lastname," + NL + //$NON-NLS-1$
            "   XMLELEMENT ( NAME \"foo:Emp\", "+ NL + //$NON-NLS-1$
            " e.firstnme, e.lastname  OPTION EMPTY ON NULL ) AS \"Result\" " + NL + //$NON-NLS-1$
            "FROM employees e WHERE e.edlevel = 12;";  //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_36_XMLELEMENT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_36_XMLELEMENT"); //$NON-NLS-1$
        
        String stmt =
            "SELECT e.empno, e.firstnme, e.lastname," + NL + //$NON-NLS-1$
            "   XMLELEMENT ( NAME \"foo:Emp\", "+ NL + //$NON-NLS-1$
            " e.firstnme OPTION ABSENT ON NULL ) AS \"Result\" " + NL + //$NON-NLS-1$
            "FROM employees e WHERE e.edlevel = 12;";  //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_37_XMLELEMENT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_37_XMLELEMENT"); //$NON-NLS-1$
        // OPTION should be used only along with a  <XML element content> 
        String stmt =
            "SELECT e.empno, e.firstnme, e.lastname," + NL + //$NON-NLS-1$
            "   XMLELEMENT ( NAME \"foo:Emp\", "+ NL + //$NON-NLS-1$
            "  OPTION NULL ON NULL ) AS \"Result\" " + NL + //$NON-NLS-1$
            "FROM employees e WHERE e.edlevel = 12;";  //$NON-NLS-1$
            parserVerifyError(stmt);
    }
    public void testSqlDmlParser00x_xml_functions_38_XMLELEMENT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_38_XMLELEMENT"); //$NON-NLS-1$
        
        String stmt =
            "SELECT e.empno, e.firstnme, e.lastname," + NL + //$NON-NLS-1$
            "   XMLELEMENT ( NAME \"foo:Emp\", "+ NL + //$NON-NLS-1$
            "       XMLNAMESPACES('http://www.foo.com' as \"foo\"),"  + NL + //$NON-NLS-1$
            "       XMLATTRIBUTES(e.empno as \"serial\"), e.firstnme, e.lastname"  + NL + //$NON-NLS-1$
            "   OPTION NULL ON NULL RETURNING CONTENT ) AS \"Result\" " + NL + //$NON-NLS-1$
            "FROM employees e WHERE e.edlevel = 12;";  //$NON-NLS-1$
        
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
        
        stmt =
            "SELECT e.empno, e.firstnme, e.lastname," + NL + //$NON-NLS-1$
            "   XMLELEMENT ( NAME \"foo:Emp\", "+ NL + //$NON-NLS-1$
            "       XMLNAMESPACES('http://www.foo.com' as \"foo\"),"  + NL + //$NON-NLS-1$
            "       XMLATTRIBUTES(e.empno as \"serial\"), e.firstnme, e.lastname"  + NL + //$NON-NLS-1$
            "   OPTION NULL ON NULL RETURNING SEQUENCE ) AS \"Result\" " + NL + //$NON-NLS-1$
            "FROM employees e WHERE e.edlevel = 12;";  //$NON-NLS-1$
        
        query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }

        
    }
    //TODO the zOS example didn't have BY VALUE or BY REF - this may be wong
    // Classcast excpetion should not occue once rules are correct
    public void testSqlDmlParser00x_xml_functions_37_XMLEXISTS()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_37_XMLEXISTS"); //$NON-NLS-1$
        
        String stmt =
            "SELECT S.prodno as result" + NL + //$NON-NLS-1$
            "FROM PurchaseOrders PO, Products S"+ NL + //$NON-NLS-1$
            "WHERE XMLEXISTS ('//item[@partNum = $n]'"+ NL + //$NON-NLS-1$
            "PASSING BY VALUE PO.POrder,"+ NL + //$NON-NLS-1$
            "S.prodno AS \"n\")"+ NL + //$NON-NLS-1$
            "AND S.prod_name = 'Baby Monitor'"; //$NON-NLS-1$    
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }

    public void testSqlDmlParser00x_xml_functions_38_XMLEXISTS()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_38_XMLEXISTS"); //$NON-NLS-1$
        
        String stmt =
            "SELECT S.prodno as result" + NL + //$NON-NLS-1$
            "FROM PurchaseOrders PO, Products S"+ NL + //$NON-NLS-1$
            "WHERE XMLEXISTS ('//item[@partNum = $n]'"+ NL + //$NON-NLS-1$
            "PASSING BY REF "+ NL + //$NON-NLS-1$
            "S.prodno AS \"n\")"+ NL + //$NON-NLS-1$
            "AND S.prod_name = 'Baby Monitor'"; //$NON-NLS-1$    
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }

    public void testSqlDmlParser00x_xml_functions_39_XMLEXISTS()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_39_XMLEXISTS"); //$NON-NLS-1$
        
        String stmt =
            "SELECT S.prodno as result" + NL + //$NON-NLS-1$
            "FROM PurchaseOrders PO, Products S"+ NL + //$NON-NLS-1$
            "WHERE XMLEXISTS ('//item[@partNum = $n]')"+ NL + //$NON-NLS-1$
            "AND S.prod_name = 'Baby Monitor'"; //$NON-NLS-1$    
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }
    
    public void testSqlDmlParser00x_xml_functions_40_XMLEXISTS()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_40_XMLEXISTS"); //$NON-NLS-1$
        
        String stmt =
            "SELECT S.prodno as result" + NL + //$NON-NLS-1$
            "FROM PurchaseOrders PO, Products S"+ NL + //$NON-NLS-1$
            "WHERE XMLEXISTS ('//item[@partNum = $n]'"+ NL + //$NON-NLS-1$
            "PASSING BY REF "+ NL + //$NON-NLS-1$
            "S.prodno AS \"n\" BY VALUE)"+ NL + //$NON-NLS-1$
            "AND S.prod_name = 'Baby Monitor'"; //$NON-NLS-1$    

        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, matchInput);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }

    public void testSqlDmlParser00x_xml_functions_42_XMLFOREST()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_42_XMLFOREST"); //$NON-NLS-1$
        
        String stmt =
            "SELECT XMLSERIALIZE(content XMLFOREST(e.lname," + NL + //$NON-NLS-1$
            "e.fname ) as varchar(5000))" +  NL +//$NON-NLS-1$
            "from employee e  where id = 4;";  //$NON-NLS-1$
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }

    public void testSqlDmlParser00x_xml_functions_43_XMLFOREST()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_43_XMLFOREST"); //$NON-NLS-1$
        
        String stmt =
            "SELECT XMLSERIALIZE(content XMLFOREST(e.lname AS \"Name\"," + NL + //$NON-NLS-1$
            "e.fname OPTION EMPTY ON NULL) as varchar(5000))" +  NL + //$NON-NLS-1$
            "from employee e where id = 4;";  //$NON-NLS-1$
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_44_XMLFOREST()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_44_XMLFOREST"); //$NON-NLS-1$
        
        String stmt =
            "SELECT XMLSERIALIZE(content XMLFOREST(" +  NL + //$NON-NLS-1$
            "e.fname OPTION NULL ON NULL) as varchar(5000))" + NL + //$NON-NLS-1$
            "from employee e where id = 4;"  + NL;   //$NON-NLS-1$
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_45_XMLFOREST()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_45_XMLFOREST"); //$NON-NLS-1$
        
        String stmt =
            "SELECT XMLSERIALIZE(content XMLFOREST(" +  NL +//$NON-NLS-1$
            "e.fname OPTION ABSENT ON NULL RETURNING SEQUENCE) as varchar(5000))" + NL + //$NON-NLS-1$
            "from employee e where id = 4;";  //$NON-NLS-1$
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            // we know we don't get the colRefs qualified
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_46_XMLPARSE()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_46_XMLPARSE"); //$NON-NLS-1$
        String stmt =
            "INSERT  into docs values(1, XMLPARSE("+ NL + //$NON-NLS-1$
             "DOCUMENT '<a> here is new row </a>' PRESERVE WHITESPACE))"+  NL ;  //$NON-NLS-1$
        QueryInsertStatement query =
            (QueryInsertStatement) parserVerifySuccessSingle(stmt, matchInput);
        if (matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_47_XMLPARSE()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_47_XMLPARSE"); //$NON-NLS-1$
        String stmt =
            "INSERT  into docs values(1, XMLPARSE("+  NL +//$NON-NLS-1$
             "CONTENT '<a> here is new row </a>' STRIP WHITESPACE))" + NL ;  //$NON-NLS-1$
        QueryInsertStatement query =
            (QueryInsertStatement) parserVerifySuccessSingle(stmt, matchInput);
        if (matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ //$NON-NLS-2$
            matchInputStatement(input, query);
        }
    }
        public void testSqlDmlParser00x_xml_functions_48_XMLPARSE()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_48_XMLPARSE"); //$NON-NLS-1$
        String stmt =
            "INSERT INTO DOCS VALUES(100, XMLPARSE(DOCUMENT"+  NL +//$NON-NLS-1$
                    "(SELECT XMLSERIALIZE(CONTENT doc as character(80)) FROM " + NL + //$NON-NLS-1$ 
                    "DOCS2 WHERE docid=2)))" + NL ;  //$NON-NLS-1$
        QueryInsertStatement query =
            (QueryInsertStatement) parserVerifySuccessSingle(stmt, matchInput);
        if (matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_49_XMLPI()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_49_XMLPI"); //$NON-NLS-1$
        String stmt =
            "SELECT XMLPI(name \"Instruction\", 'Push the red button')"+  NL +//$NON-NLS-1$ 
            "FROM SYSIBM.SYSDUMMY1 " + NL ; //$NON-NLS-1$
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_50_XMLPI()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_50_XMLPI"); //$NON-NLS-1$
        String stmt =
            "SELECT XMLPI(name \"Instruction\" RETURNING CONTENT)"+ NL + //$NON-NLS-1$ 
            "FROM SYSIBM.SYSDUMMY1 " +NL ; //$NON-NLS-1$
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, false);
        if (matchInput){
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_51_XMLQUERY()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_51_XMLQUERY"); //$NON-NLS-1$
        String stmt =
            "SELECT XMLQUERY('\\item[productName=$n]' PASSING BY VALUE POrder," + NL + //$NON-NLS-1$ 
            ":hv AS \"n\"  NULL ON EMPTY) AS \"Result\" FROM PurchaseOrders PO "+NL; //$NON-NLS-1$ 
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, matchInput);
    }
    public void testSqlDmlParser00x_xml_functions_52_XMLQUERY()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_52_XMLQUERY"); //$NON-NLS-1$
        String stmt =
            "SELECT XMLQUERY('\\item[productName=$n]'" + NL + //$NON-NLS-1$ 
            "NULL ON EMPTY) AS \"Result\" FROM PurchaseOrders PO "+NL; //$NON-NLS-1$ 
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, matchInput);
    }
    public void testSqlDmlParser00x_xml_functions_53_XMLQUERY()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_53_XMLQUERY"); //$NON-NLS-1$
        String stmt =
            "SELECT XMLQUERY('\\item[productName=$n]' PASSING BY REF POrder," + NL + //$NON-NLS-1$ 
            ":hv AS \"n\" RETURNING CONTENT BY VALUE EMPTY ON EMPTY) " + NL +
            "AS \"Result\" FROM PurchaseOrders PO "; //$NON-NLS-1$ 
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, matchInput);
    }
    public void testSqlDmlParser00x_xml_functions_54_XMLQUERY()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_54_XMLQUERY"); //$NON-NLS-1$
        String stmt =
            "SELECT XMLQUERY('\\item[productName=$n]' PASSING BY VALUE PO.POrder," +  NL +//$NON-NLS-1$ 
            ":hv AS \"n\" RETURNING SEQUENCE BY REF  EMPTY ON EMPTY) " + NL +
            "AS \"Result\" FROM PurchaseOrders PO, Customer C "; //$NON-NLS-1$ 
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, matchInput);
    }
    public void testSqlDmlParser00x_xml_functions_55_XMLQUERY()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_55_XMLQUERY"); //$NON-NLS-1$
        String stmt =
            "SELECT XMLQUERY('\\item[productName=$n]'" +  NL +//$NON-NLS-1$ 
            "  NULL ON EMPTY ) AS \"Result\" FROM PurchaseOrders PO "+NL; //$NON-NLS-1$ 
        QuerySelectStatement query =
            (QuerySelectStatement) parserVerifySuccessSingle(stmt, matchInput);
    }
    // verifying error when retuning mechanism is given without specifying returning clause
    public void testSqlDmlParser00x_xml_functions_56_XMLQUERY()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_56_XMLQUERY"); //$NON-NLS-1$
        String stmt =
            "SELECT XMLQUERY('\\item[productName=$n]'PASSING BY REF PO.POrder," + NL + //$NON-NLS-1$ 
            ":hv AS \"n\"  BY VALUE) AS \"Result\" FROM PurchaseOrders PO "+NL; //$NON-NLS-1$ 
            parserVerifyError(stmt);
    }
    // verifying error when empty handling is not specified
    public void testSqlDmlParser00x_xml_functions_57_XMLQUERY()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_57_XMLQUERY"); //$NON-NLS-1$
        String stmt =
            "SELECT XMLQUERY('\\item[productName=$n]'PASSING BY VALUE PO.POrder," +  NL +//$NON-NLS-1$ 
            ":hv AS \"n\"  ) AS \"Result\" FROM PurchaseOrders PO "+NL; //$NON-NLS-1$ 
             parserVerifyError(stmt);
    }
    
    public void testSqlDmlParser00x_xml_functions_58_XMLSERIALIZE()  throws Exception {
        System.out
                .println("test testSqlDmlParser00x_xml_functions_58_XMLSERIALIZE"); //$NON-NLS-1$
        String stmt = "SELECT e.id, XMLSERIALIZE( XMLELEMENT ( NAME \"Emp\"," + NL + //$NON-NLS-1$
                "e.fname || ' ' || e.lname)" + NL + //$NON-NLS-1$
                "AS CLOB(100)) AS \"result\" " +  NL +//$NON-NLS-1$
                "FROM employees e"; //$NON-NLS-1$
        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, false);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_59_XMLSERIALIZE()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_59_XMLSERIALIZE"); //$NON-NLS-1$
        String stmt = "SELECT e.id, XMLSERIALIZE( CONTENT XMLELEMENT ( NAME \"Emp\"," + //$NON-NLS-1$
                "e.fname || ' ' || e.lname) " + //$NON-NLS-1$
                "AS CLOB(100) VERSION \'1.1\' INCLUDING XMLDECLARATION) AS \"result\" " + //$NON-NLS-1$
                "FROM employees e"; //$NON-NLS-1$
        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, false);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_60_XMLSERIALIZE()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_60_XMLSERIALIZE"); //$NON-NLS-1$
        String stmt = "SELECT e.id, XMLSERIALIZE( DOCUMENT XMLELEMENT ( NAME \"Emp\"," + NL + //$NON-NLS-1$
                "e.fname || ' ' || e.lname)" + NL + //$NON-NLS-1$
                "AS CLOB(100) EXCLUDING XMLDECLARATION) AS \"result\" " + NL + //$NON-NLS-1$
                "FROM employees e"; //$NON-NLS-1$
        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, false);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
        
        stmt = "SELECT id, XMLSERIALIZE( DOCUMENT"                      + NL + //$NON-NLS-1$
                " XMLELEMENT(NAME \"Emp\", fname) AS CLOB(100)"         + NL + //$NON-NLS-1$
                " ENCODING 'UTF-16') AS \"result\" "             + NL + //$NON-NLS-1$
                "FROM employees e"; //$NON-NLS-1$
        query = (QuerySelectStatement) parserVerifySuccessSingle(stmt, true);

    }
    
    public void testSqlDmlParser00x_xml_functions_61_XMLTABLE()  throws Exception {
        // avoiding all the optional productions
        System.out.println("test testSqlDmlParser00x_xml_functions_61_XMLTABLE"); //$NON-NLS-1$
        String stmt = "SELECT xt.* FROM xs.products as p, " +  NL +//$NON-NLS-1$
                "XMLTABLE(\'/PRODUCT\'  " +  NL +//$NON-NLS-1$
                "COLUMNS quantity INTEGER, name varchar(99)" + NL +//$NON-NLS-1$
                " ) as xt"; //$NON-NLS-1$
        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
    
    public void testSqlDmlParser00x_xml_functions_62_XMLTABLE()  throws Exception {
        // single namespace
        System.out.println("test testSqlDmlParser00x_xml_functions_62_XMLTABLE"); //$NON-NLS-1$
        String stmt = "SELECT xt.* FROM xs.products as p, " +  NL +//$NON-NLS-1$
                "XMLTABLE( XMLNAMESPACES(DEFAULT \'http://hr.org\'), " + NL +//$NON-NLS-1$
                "\'/PRODUCT\' " +  NL +//$NON-NLS-1$
                "COLUMNS quantity INTEGER, name varchar(99)" + NL +//$NON-NLS-1$
                " ) as xt"; //$NON-NLS-1$
        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_63_XMLTABLE()  throws Exception {
        // multiple  namespaces
        System.out.println("test testSqlDmlParser00x_xml_functions_63_XMLTABLE"); //$NON-NLS-1$
        String stmt = "SELECT xt.* FROM xs.products as p, " +  NL +//$NON-NLS-1$
                "XMLTABLE( XMLNAMESPACES(DEFAULT \'http://xml.org\', " + NL +//$NON-NLS-1$
                "\'http://hr.org\' AS \"hr\", \'http://admin.org\' AS \"admin\"), " + NL +//$NON-NLS-1$
                "\'/PRODUCT\' " +  NL +//$NON-NLS-1$
                "COLUMNS quantity INTEGER, name varchar(99)" + NL +//$NON-NLS-1$
                " ) as xt"; //$NON-NLS-1$
        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_64_XMLTABLE()  throws Exception {
        // table argument list 
        System.out.println("test testSqlDmlParser00x_xml_functions_64_XMLTABLE"); //$NON-NLS-1$
        String stmt = "SELECT xt.* FROM xs.products as p, " +  NL +//$NON-NLS-1$
                "XMLTABLE(\'/PRODUCT\' PASSING BY VALUE xmlcol as Z " +  NL +//$NON-NLS-1$
                "COLUMNS quantity INTEGER PATH \'QUANTITY\', name varchar(99)" + NL +//$NON-NLS-1$
                " PATH \'NAME\') as xt"; //$NON-NLS-1$
        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_65_XMLTABLE()  throws Exception {
        // table argument list
        System.out.println("test testSqlDmlParser00x_xml_functions_65_XMLTABLE"); //$NON-NLS-1$
        String stmt = "SELECT xt.* FROM xs.products as p, " +  NL +//$NON-NLS-1$
                "XMLTABLE(\'/PRODUCT\' PASSING BY VALUE xmlcol AS Z,xmlcom2  " +  NL +//$NON-NLS-1$
                "COLUMNS quantity INTEGER PATH \'QUANTITY\', name varchar(99)" + NL +//$NON-NLS-1$
                " PATH \'NAME\') as xt"; //$NON-NLS-1$
        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
        public void testSqlDmlParser00x_xml_functions_66_XMLTABLE()  throws Exception {
        // table argument list 
        System.out.println("test testSqlDmlParser00x_xml_functions_65_XMLTABLE"); //$NON-NLS-1$
        String stmt = "SELECT xt.* FROM xs.products as p, " +  NL +//$NON-NLS-1$
                "XMLTABLE(\'/PRODUCT\' PASSING BY VALUE xmlcol AS col BY VALUE,xmlcom2 BY REF " +  NL +//$NON-NLS-1$
                "COLUMNS quantity INTEGER PATH \'QUANTITY\', name varchar(99)" + NL +//$NON-NLS-1$
                " PATH \'NAME\') as xt"; //$NON-NLS-1$
        QuerySelectStatement query = (
                QuerySelectStatement) parserVerifySuccessSingle(stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
        public void testSqlDmlParser00x_xml_functions_67_XMLTABLE()  throws Exception {
        // table column defenitions
        System.out.println("test testSqlDmlParser00x_xml_functions_67_XMLTABLE"); //$NON-NLS-1$
        String stmt = "SELECT xt.* FROM xs.products as p, " +  NL +//$NON-NLS-1$
                "XMLTABLE(\'/PRODUCT\' PASSING BY VALUE xmlcol as Z " +  NL +//$NON-NLS-1$
                "COLUMNS quantity FOR ORDINALITY) as xt"; //$NON-NLS-1$
        QuerySelectStatement query = (
                QuerySelectStatement) parserVerifySuccessSingle(stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
    public void testSqlDmlParser00x_xml_functions_68_XMLTABLE()  throws Exception {
        // table column defenitions
        System.out.println("test testSqlDmlParser00x_xml_functions_68_XMLTABLE"); //$NON-NLS-1$
        String stmt = "SELECT xt.* FROM xs.products as p, " +  NL +//$NON-NLS-1$
                "XMLTABLE(\'/PRODUCT\' PASSING BY VALUE xmlcol AS Z,xmlcom2  " +  NL +//$NON-NLS-1$
                "COLUMNS quantity INTEGER BY VALUE PATH \'QUANTITY\', name varchar(99)" + NL +//$NON-NLS-1$
                " PATH \'NAME\') as xt"; //$NON-NLS-1$
        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
    //TODO add all default options and run
    public void testSqlDmlParser00x_xml_functions_69_XMLTABLE()  throws Exception {
        // table column defenitions default clause 
        System.out.println("test testSqlDmlParser00x_xml_functions_69_XMLTABLE"); //$NON-NLS-1$
        String stmtHeader = "SELECT xt.* FROM xs.products as p, " +  NL +//$NON-NLS-1$
                "  XMLTABLE(\'PRODUCT\' PASSING BY VALUE xmlcol AS Z, xmlcom2" +  NL +//$NON-NLS-1$
                "    COLUMNS name varchar(99) DEFAULT ";
        String stmtTail = ") as xt"; //$NON-NLS-1$
        parserVerifySuccessSingle(stmtHeader+"USER"+stmtTail, matchInput);
        parserVerifySuccessSingle(stmtHeader+"CURRENT_USER"+stmtTail, matchInput);
        parserVerifySuccessSingle(stmtHeader+"CURRENT_ROLE"+stmtTail, matchInput);
        parserVerifySuccessSingle(stmtHeader+"SESSION_USER"+stmtTail, matchInput);
        parserVerifySuccessSingle(stmtHeader+"SYSTEM_USER"+stmtTail, matchInput);
        parserVerifySuccessSingle(stmtHeader+"CURRENT_PATH"+stmtTail, matchInput);
        parserVerifySuccessSingle(stmtHeader+"'literal'"+stmtTail, matchInput);
        parserVerifySuccessSingle(stmtHeader+"256"+stmtTail, matchInput);
    }
        public void testSqlDmlParser00x_xml_functions_70_XMLTEXT()  throws Exception {
        System.out.println("test testSqlDmlParser00x_xml_functions_70_XMLTEXT"); 
        String stmt = "SELECT XMLTEXT('The stock symbol for Johnson&Johnson is JNJ.')" + NL +//$NON-NLS-1$
                " as \"Result\" FROM SYSIBM.SYSDUMMY1"; //$NON-NLS-1$
                
        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
        public void testSqlDmlParser00x_xml_functions_71_XMLTEXT()  throws Exception {
        // returning
        System.out.println("test testSqlDmlParser00x_xml_functions_71_XMLTEXT"); 
        String stmt = "SELECT XMLTEXT('The stock symbol for Johnson&Johnson " + NL +//$NON-NLS-1$
                "is JNJ.' RETURNING CONTENT)" + NL +//$NON-NLS-1$
                " as \"Result\" FROM SYSIBM.SYSDUMMY1"; //$NON-NLS-1$
                
        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
        System.out.println("test testSqlDmlParser00x_xml_functions_71_XMLTEXT"); 
        stmt = "SELECT XMLTEXT('The stock symbol for Johnson&Johnson " + NL +//$NON-NLS-1$
                "is JNJ.' RETURNING SEQUENCE)" + NL +//$NON-NLS-1$
                " as \"Result\" FROM SYSIBM.SYSDUMMY1"; //$NON-NLS-1$
                
        query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }

    }

        public void testSqlDmlParser00x_xml_functions_72_XMLVALIDATE()  throws Exception {
            // without any options
        System.out.println("test testSqlDmlParser00x_xml_functions_72_XMLVALIDATE"); 
        String stmt = 
            "INSERT INTO  newton.t values(4,XMLVALIDATE( DOCUMENT XMLPARSE(document" + NL +//$NON-NLS-1$
                    "'<?xml version=\"1.0\" ?>"+ NL +//$NON-NLS-1$
                    "<purchaseOrder orderDate=\"2003-11-20\"" + NL +//$NON-NLS-1$
                      "xmlns:xsi=\"http:////www.w3.org/2001/XMLSchema-instance\" "+ NL +//$NON-NLS-1$ 
                      "xsi:noNamespaceSchemaLocation=\"http://www.test.com/po\">"+ NL +//$NON-NLS-1$
                      "<name>Jane Frank</name>"+ NL + //$NON-NLS-1$
                      "<street>123 Main Street</street>"+ NL +//$NON-NLS-1$
                      "<city>Newark</city>"+ NL +//$NON-NLS-1$
                      "<state>DE</state>"+ NL +//$NON-NLS-1$"
                      "<zip>21721</zip>"+ NL +//$NON-NLS-1$
                     "<billTo country=\"US\">"+ NL +//$NON-NLS-1$
                      "<name>John FRANK</name>"+ NL +//$NON-NLS-1$
                      "<street>8 Oak Avenue</street>"+ NL +//$NON-NLS-1$
                      "<street2>Apt 2B</street2>"+ NL +//$NON-NLS-1$
                      "<city>Elkton</city>"+ NL +//$NON-NLS-1$
                      "<state>MD</state>"+ NL +//$NON-NLS-1$
                      "<zip>21921</zip>"+ NL +//$NON-NLS-1$
                      "</billTo>"+ NL +//$NON-NLS-1$
                      "<comment>Please rush!</comment>"+ NL +//$NON-NLS-1$
                     "<items>"+ NL +//$NON-NLS-1$
                     "<item partNum=\"87654-DOA\">"+ NL +//$NON-NLS-1$
                     "<productName>TV</productName>"+ NL +//$NON-NLS-1$
                      "<quantity>1</quantity>"+ NL +//$NON-NLS-1$
                      "<USPrice>549.99</USPrice>"+ NL +//$NON-NLS-1$
                      "<comment>Confirm this is HDTV</comment>"+ NL +//$NON-NLS-1$
                      "</item>"+ NL +//$NON-NLS-1$
                      "<item partNum=\"826-78\">\""+ NL +//$NON-NLS-1$
                      "<productName>Wetness Monitor</productName>"+ NL +//$NON-NLS-1$
                      "<quantity>1</quantity>"+ NL +//$NON-NLS-1$
                      "<USPrice>39.98</USPrice>"+ NL +//$NON-NLS-1$
                      "<shipDate>2003-11-21</shipDate>"+ NL +//$NON-NLS-1$
                      "</item> </items></purchaseOrder>' preserve whitespace)))";
                
/*        QuerySelectStatement query = (QuerySelectStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
*/    }
        public void testSqlDmlParser00x_xml_functions_73_XMLVALIDATE()  throws Exception {
        //according to uri
        System.out.println("test testSqlDmlParser00x_xml_functions_73_XMLVALIDATE"); 
        String stmt = 
            "INSERT INTO newton.t values(9,xmlvalidate  ( DOCUMENT xmlparse(document" + NL +//$NON-NLS-1$
            "'<?xml version=\"1.0\"?>" + NL +//$NON-NLS-1$
            "<note xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+ NL +//$NON-NLS-1$
            " xsi:noNamespaceSchemaLocation=\"nonexsit.xsd\"> "+ NL +//$NON-NLS-1$
            "<to>ToHaiyin</to>"+ NL +//$NON-NLS-1$
            "<from>Jani</from>"+ NL +//$NON-NLS-1$"
            "<heading>Reminder</heading>"+ NL +//$NON-NLS-1$
            "<body>Let us meet this week</body>"+ NL +//$NON-NLS-1$
            "</note>' preserve whitespace)ACCORDING TO XMLSCHEMA URI ''))";//$NON-NLS-1$
        QueryInsertStatement query = (QueryInsertStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }
    }
        public void testSqlDmlParser00x_xml_functions_74_XMLVALIDATE()  throws Exception {
        //according to no namespace
            System.out.println("test testSqlDmlParser00x_xml_functions_74_XMLVALIDATE"); 
        String stmt = 
            "INSERT INTO newton.t values(9,xmlvalidate  ( CONTENT xmlparse(document" + NL +//$NON-NLS-1$
            "'<?xml version=\"1.0\"?>" + NL +//$NON-NLS-1$
            "<note xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+ NL +//$NON-NLS-1$
            " xsi:noNamespaceSchemaLocation=\"nonexsit.xsd\"> "+ NL +//$NON-NLS-1$
            "<to>ToHaiyin</to>"+ NL +//$NON-NLS-1$
            "<from>Jani</from>"+ NL +//$NON-NLS-1$"
            "<heading>Reminder</heading>"+ NL +//$NON-NLS-1$
            "<body>Let us meet this week</body>"+ NL +//$NON-NLS-1$
            "</note>' preserve whitespace)ACCORDING TO XMLSCHEMA NO NAMESPACE))";//$NON-NLS-1$
        QueryInsertStatement query = (QueryInsertStatement) parserVerifySuccessSingle(
                stmt, matchInput);
        if (matchInput) {
            String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
            matchInputStatement(input, query);
        }

        }
        public void testSqlDmlParser00x_xml_functions_75_XMLVALIDATE()  throws Exception {
            //according uri ,schema location
                System.out.println("test testSqlDmlParser00x_xml_functions_75_XMLVALIDATE"); 
            String stmt = 
                "INSERT INTO newton.t values(9,xmlvalidate  ( SEQUENCE xmlparse(document" + NL +//$NON-NLS-1$
                "'<?xml version=\"1.0\"?>" + NL +//$NON-NLS-1$
                "<note xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+ NL +//$NON-NLS-1$
                " xsi:noNamespaceSchemaLocation=\"nonexsit.xsd\"> "+ NL +//$NON-NLS-1$
                "<to>ToHaiyin</to>"+ NL +//$NON-NLS-1$
                "<from>Jani</from>"+ NL +//$NON-NLS-1$
                "<heading>Reminder</heading>"+ NL +//$NON-NLS-1$
                "<body>Let us meet this week</body>"+ NL +//$NON-NLS-1$
                "</note>' preserve whitespace) ACCORDING TO "+ NL +//$NON-NLS-1$
                "XMLSCHEMA URI 'http://www.w3schools.com' LOCATION 'http://www.w3schools.com notes.xsd'))"; //$NON-NLS-1$   
            QueryInsertStatement query = (QueryInsertStatement) parserVerifySuccessSingle(
                    stmt, matchInput);
            if (matchInput) {
                String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
                matchInputStatement(input, query);
            }

        }
        public void testSqlDmlParser00x_xml_functions_76_XMLVALIDATE()  throws Exception {
            //according to no namespace ,location
                System.out.println("test testSqlDmlParser00x_xml_functions_76_XMLVALIDATE"); 
            String stmt = 
                "INSERT INTO newton.t values(9,xmlvalidate( SEQUENCE xmlparse(document" + NL +//$NON-NLS-1$
                "'<?xml version=\"1.0\"?>" + NL +//$NON-NLS-1$
                "<note xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+ NL +//$NON-NLS-1$
                " xsi:noNamespaceSchemaLocation=\"nonexsit.xsd\"> "+ NL +//$NON-NLS-1$
                "<to>ToHaiyin</to>"+ NL +//$NON-NLS-1$
                "<from>Jani</from>"+ NL +//$NON-NLS-1$"
                "<heading>Reminder</heading>"+ NL +//$NON-NLS-1$
                "<body>Let us meet this week</body>"+ NL +//$NON-NLS-1$
                "</note>' preserve whitespace)ACCORDING TO XMLSCHEMA NO NAMESPACE LOCATION 'http://schema2'))";//$NON-NLS-1$
            QueryInsertStatement query = (QueryInsertStatement) parserVerifySuccessSingle(
                    stmt, matchInput);
            if (matchInput) {
                String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
                matchInputStatement(input, query);
            }

        }
        public void testSqlDmlParser00x_xml_functions_77_XMLVALIDATE()  throws Exception {
            //according to identifier
                System.out.println("test testSqlDmlParser00x_xml_functions_77_XMLVALIDATE"); 
            String stmt = 
                "INSERT INTO newton.t values(9,xmlvalidate( DOCUMENT xmlparse(document" + NL +//$NON-NLS-1$
                "'<?xml version=\"1.0\"?>" + NL +//$NON-NLS-1$
                "<no xmlns:xsi=\"http://www.w3.org/2001/XML\""+ NL +//$NON-NLS-1$
                " xsi:no=\"no.xsd\"> "+ NL +//$NON-NLS-1$
                "</no>' preserve whitespace)ACCORDING TO XMLSCHEMA id non))";//$NON-NLS-1$
            QueryInsertStatement query = (QueryInsertStatement) parserVerifySuccessSingle(
                    stmt, matchInput);
            if (matchInput) {
                String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
                matchInputStatement(input, query);
            }

        }
        public void testSqlDmlParser00x_xml_functions_78_XMLVALIDATE()  throws Exception {
            //according to identifier ,element
                System.out.println("test testSqlDmlParser00x_xml_functions_78_XMLVALIDATE"); 
            String stmt = 
                "INSERT INTO newton.t values(9,xmlvalidate( DOCUMENT xmlparse(document" + NL +//$NON-NLS-1$
                "'<?xml version=\"1.0\"?>" + NL +//$NON-NLS-1$
                "<note xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+ NL +//$NON-NLS-1$
                " xsi:noNamespaceSchemaLocation=\"nonexsit.xsd\"> "+ NL +//$NON-NLS-1$
                "</note>' preserve whitespace)ACCORDING TO XMLSCHEMA  id newton.poschema ELEMENT \"purchaseOrder\"))";//$NON-NLS-1$
            QueryInsertStatement query = (QueryInsertStatement) parserVerifySuccessSingle(
                    stmt, matchInput);
            if (matchInput) {
                String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
                matchInputStatement(input, query);
            }

        }
        public void testSqlDmlParser00x_xml_functions_79_XMLVALIDATE()  throws Exception {
            //according to identifier ,element
                System.out.println("test testSqlDmlParser00x_xml_functions_79_XMLVALIDATE"); 
            String stmt = 
                "INSERT INTO newton.t values(9,xmlvalidate(DOCUMENT xmlparse(document" + NL +//$NON-NLS-1$
                "'<?xml version=\"1.0\"?>" + NL +//$NON-NLS-1$
                "<note xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+ NL +//$NON-NLS-1$
                " xsi:noNamespaceSchemaLocation=\"nonexsit.xsd\"> "+ NL +//$NON-NLS-1$
                "</note>' preserve whitespace)ACCORDING TO XMLSCHEMA  id newton.poschema" +NL +//$NON-NLS-1$
                " ELEMENT \"purchaseOrder\"))";//$NON-NLS-1$
            QueryInsertStatement query = (QueryInsertStatement) parserVerifySuccessSingle(
                    stmt, matchInput);
            if (matchInput) {
                String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
                matchInputStatement(input, query);
            }

        }
        public void testSqlDmlParser00x_xml_functions_80_XMLVALIDATE()  throws Exception {
            //according to identifier ,element namesapce
                System.out.println("test testSqlDmlParser00x_xml_functions_80_XMLVALIDATE"); 
            String stmt = 
                "INSERT INTO newton.t values(9,xmlvalidate( DOCUMENT xmlparse(document" + NL +//$NON-NLS-1$
                "'<?xml version=\"1.0\"?>" + NL +//$NON-NLS-1$
                "<note xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+ NL +//$NON-NLS-1$
                " xsi:noNamespaceSchemaLocation=\"nonexsit.xsd\"> "+ NL +//$NON-NLS-1$
                "</note>' preserve whitespace)ACCORDING TO XMLSCHEMA  id newton.poschema" +NL +//$NON-NLS-1$
                " NO NAMESPACE ELEMENT \"purchaseOrder\"))";//$NON-NLS-1$
            QueryInsertStatement query = (QueryInsertStatement) parserVerifySuccessSingle(
                    stmt, matchInput);
            if (matchInput) {
                String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
                matchInputStatement(input, query);
            }

        }
        public void testSqlDmlParser00x_xml_functions_81_XMLVALIDATE()  throws Exception {
            //according to identifier ,element namesapce
                System.out.println("test testSqlDmlParser00x_xml_functions_81_XMLVALIDATE"); 
            String stmt = 
                "INSERT INTO newton.t values(9,xmlvalidate(DOCUMENT xmlparse(document" + NL +//$NON-NLS-1$
                "'<xml/>' preserve whitespace)ACCORDING TO XMLSCHEMA  id newton.poschema" +NL +//$NON-NLS-1$
                "NAMESPACE 'http://a.org/local' ELEMENT \"purchaseOrder\"))";//$NON-NLS-1$
            QueryInsertStatement query = (QueryInsertStatement) parserVerifySuccessSingle(
                    stmt, matchInput);
            if (matchInput) {
                String input = stmt.replaceAll("e\\.", ""); //$NON-NLS-1$ 
                matchInputStatement(input, query);
            }

        }

}
