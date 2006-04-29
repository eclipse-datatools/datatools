/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.core.services;

import java.util.ArrayList;

import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.Messages;
import org.eclipse.datatools.sqltools.editor.template.GenericSQLContextType;
import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.datatools.sqltools.sql.parser.ParserParameters;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLDelimiter;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTStart;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.osgi.util.NLS;


/**
 * A SQL language related service specific to a database definition.
 * @author Hui Cao
 *
 */
public class SQLService
{

    /**
     * Returns an <code>ISQLSyntax</code> object which can be used to highlight sql statements in SQL editor.
     * 
     * @return an <code>ISQLSyntax</code> object specific to a SQL dialect.
     */
    public ISQLSyntax getSQLSyntax()
    {
        return null;
    }

    /**
     * Returns a specific <code>GenericSQLContextType</code> object which identifies the context type of templates
     * used in SQL editor.
     * 
     * @return a <code>GenericSQLContextType</code> object
     */
    public GenericSQLContextType getSQLContextType()
    {
        // TODO Auto-generated method stub
        return new GenericSQLContextType();
    }

    /**
     * Returns a <code>SQLParser</code> object which is used to parse SQL dialect.
     * 
     * @return a <code>SQLParser</code> object
     */
    public SQLParser getSQLParser()
    {
        return null;
    }

    /**
     * Splits the sql statement into groups of statements according to SQL statement delimiter such as "go" or ";".
     * 
     * @param sql statement to be splitted
     * @return sql statement array
     */
	public String[] splitSQL(String sql) {
		if (getSQLParser() == null)
		{
			return new String[]{sql};
		}
	    ArrayList groups = new ArrayList();
	    try
	    {
	        SQLParser parser = getSQLParser();
	        IDocument doc = new Document(sql);
	        ParsingResult result = parser.parse(sql, new ParserParameters(true));
	        IASTStart root = result.getRootNode();
	        root.setDocument(doc);
	        String group = "";
	        int start = 0;
	        int end = 0;
	        if (root.jjtGetNumChildren() > 0)
	        {
	            for (int i = 0; i < root.jjtGetNumChildren(); i++)
	            {
	                Node node = root.jjtGetChild(i);
	                if (node instanceof IASTSQLDelimiter)
	                {
	                	end = node.getStartOffset();
	                	group = doc.get(start, end - start);
	                	start = node.getEndOffset();
	                    // trim() will remove ascii control characters as well
	                    if (!group.trim().equals(""))
	                    {
	                        groups.add(group);
	                        group = "";
	                    }
	                }
	            }
	        }
	        else
	        {
	            group = sql;
	        }
	        if (end < doc.getLength() )
	        {
	        	group = doc.get(start, doc.getLength() - start );
	        }
	        if (!group.trim().equals(""))
	        {
	            groups.add(group);
	        }
	
	    }
	    catch (Exception e1)
	    {
	        EditorCorePlugin.getDefault().log(NLS.bind(Messages.DefaultSQLSyntax_exception_splitSQL, sql), e1);
	    }
	    return (String[]) groups.toArray(new String[groups.size()]);
	}


}
