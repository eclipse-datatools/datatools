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
package org.eclipse.datatools.sqltools.parsers.sql.query;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParseResult;

/**
 * @author ckadner
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SQLQueryParseResult extends SQLParseResult
{

    /**
     * @param query
     * @param errorList
     */
    public SQLQueryParseResult(QueryStatement query, List errorList)
    {
        super((SQLStatement)query, errorList);
    }

    
    
    
    public QueryStatement getQueryStatement()
    {
        return (QueryStatement) getSQLStatement();
    }
    
    public void setQueryStatement(QueryStatement query)
    {
        setSQLStatement(query);
    }
}
