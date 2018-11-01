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
package org.eclipse.datatools.sqltools.parsers.sql;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;


public class SQLParseResult {
    
    private SQLStatement sqlStatement = null;
    private List errorList = null;
    
    /**
     * @param sqlStatement
     * @param errorList
     */
    public SQLParseResult(SQLStatement stmt, List errorList)
    {
        this.sqlStatement = stmt;
        this.errorList = errorList;
    }
    /**
     * @return Returns the errorList.
     */
    public List getErrorList()
    {
        return errorList;
    }
    /**
     * @param errorList The errorList to set.
     */
    public void setErrorList(List errorList)
    {
        this.errorList = errorList;
    }
    /**
     * @return Returns the sqlStatement.
     */
    public SQLStatement getSQLStatement()
    {
        return sqlStatement;
    }
    /**
     * @param sqlStatement The sqlStatement to set.
     */
    public void setSQLStatement(SQLStatement stmt)
    {
        this.sqlStatement = stmt;
    }
}