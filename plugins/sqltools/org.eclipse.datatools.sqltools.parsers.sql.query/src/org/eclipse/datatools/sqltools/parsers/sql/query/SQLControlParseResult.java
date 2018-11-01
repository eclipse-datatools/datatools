/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.CallStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseResult;

/**
 * This class contains the result of parsing a SQL "control" statement.  (In ISO SQL terms,
 * CALL and RETURN are "control" statements, probably meaning "transfer of control".)  The 
 * parse result contains the statement (if the statement parsed successfully) and an parse
 * error message list (when it did not parse sucessfully).
 * 
 * @author Sangeetha
 */
public class SQLControlParseResult extends SQLParseResult {

    /**
     * Constructs an instance of this class with the given control statement and 
     * parse error list.
     * 
     * @param stmt the control statement model resulting from a parse
     * @param errorList an error list resulting from a parse
     */
    public SQLControlParseResult( final SQLControlStatement stmt, final List errorList ) {
        super( stmt, errorList );
    }
    
    /**
     * Gets a CALL statement model from the parse result, if it contains one.
     * 
     * @return the call statement contained in the parse result, otherwise null
     */
    public CallStatement getCallStatement() {
        CallStatement callStmt = null;
        
        SQLStatement sqlStmt = getSQLStatement();
        if (sqlStmt instanceof CallStatement) {
            callStmt = (CallStatement) sqlStmt;
        }
        
        return callStmt;
    }

    /**
     * Sets the SQL statement model in the parse result to the given CALL statement.
     * 
     * @param stmt the CALL statement to set
     */
    public void setCallStatement( final CallStatement stmt ) {
        setSQLStatement( stmt );
    }
}
