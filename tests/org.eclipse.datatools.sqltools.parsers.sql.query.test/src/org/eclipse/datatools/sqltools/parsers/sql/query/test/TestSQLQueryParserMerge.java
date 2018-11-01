/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query.test;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement;

public class TestSQLQueryParserMerge extends AbstractTestSQLQueryParser {
    
    /**
     * Constructs an instance of this class with the given test case name,
     * 
     * @param name the name of the test case
     */
    public TestSQLQueryParserMerge(String name) {
        super(name);
    }

    public void testMerge_Basic() throws Exception {
        StatementCache stmtCache = StatementCache.getInstance();
        
        List basicStmtList = stmtCache.getStatementList("MERGE", "BASIC");
        Iterator stmtListIter = basicStmtList.iterator();
        while (stmtListIter.hasNext()) {
            String stmt = (String) stmtListIter.next();
            parserVerifySuccess(stmt, true);
        }
    }
    
    public void testGenerateAsKeywordForCorrID() throws Exception {
        StatementCache stmtCache = StatementCache.getInstance();
        
        String sourceSQL = stmtCache.getStatement("MERGE", "OTHER", "querySource");
        QueryMergeStatement mergeStmt = (QueryMergeStatement) parserVerifySuccessSingleQuery(sourceSQL, false);
        String genSQL = mergeStmt.getSQL();
        String templateSQLWithAS = 
            "MERGE INTO INVENTORY AS IN USING" + 
            "  (SELECT PARTNO, DESCRIPTION, COUNT FROM SHIPMENT WHERE PARTNO IS NOT NULL) AS SH" +
            "   ON (IN.PARTNO = SH.PARTNO)" +
            " WHEN MATCHED THEN UPDATE SET" +
            "   DESCRIPTION = SH.DESCRIPTION, QUANTITY = QUANTITY + COUNT" +
            " WHEN NOT MATCHED THEN INSERT (PARTNO, DESCRIPTION, QUANTITY)" +
            "    VALUES (SH.PARTNO, SH.DESCRIPTION, COUNT)";
        compareGeneratedSQLToTemplateSQL(genSQL, templateSQLWithAS, true, false);
        
        setGenerateAsKeywordForTableCorrID(false);
        String templateSQLNoAS = 
            "MERGE INTO INVENTORY IN USING" + 
            "  (SELECT PARTNO, DESCRIPTION, COUNT FROM SHIPMENT WHERE PARTNO IS NOT NULL) SH" +
            "   ON (IN.PARTNO = SH.PARTNO)" +
            " WHEN MATCHED THEN UPDATE SET" +
            "   DESCRIPTION = SH.DESCRIPTION, QUANTITY = QUANTITY + COUNT" +
            " WHEN NOT MATCHED THEN INSERT (PARTNO, DESCRIPTION, QUANTITY)" +
            "    VALUES (SH.PARTNO, SH.DESCRIPTION, COUNT)";    
        compareGeneratedSQLToTemplateSQL(genSQL, templateSQLNoAS, true, false);
    }
}
