/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
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
 * Tests parsing of CALL statements.
 */
public final class TestSQLQueryParserCallStmt extends
        AbstractTestSQLQueryParser {

    public TestSQLQueryParserCallStmt(String name) {
        super(name);

        DEBUG = true;
        CONTROL = true;
        PRINT_ERRORS = true;
        syntaxCheckOnly = false;
    }

    public void testCallStmt1() throws Exception {
        StatementCache stmtCache = StatementCache.getInstance();

        String sourceScript = stmtCache.getScript("CALL", "BASIC");
        String templateScript = stmtCache.getTemplateScript("CALL", "BASIC");
        parserVerifySuccess(sourceScript, templateScript);
        
//        String sourceSQL = "CALL EPT (51, ?)";
//        CallStatement callStmt = (CallStatement) parserVerifySuccessSingleCall(
//                sourceSQL, false);
//        String genSQL = callStmt.getSQL();
//        String templateSQL = "CALL SRINIVSA.DEPT (51, ?)";
//        compareGeneratedSQLToTemplateSQL(genSQL, templateSQL, true, false);
    }
    
    

}
