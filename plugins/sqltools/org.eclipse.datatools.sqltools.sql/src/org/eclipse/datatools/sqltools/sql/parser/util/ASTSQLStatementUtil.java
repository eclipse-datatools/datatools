/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sql.parser.util;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.parser.ast.SimpleNode;


public class ASTSQLStatementUtil
{
    public static String toString(int type, SimpleNode node, Collection params)
    {
        String s = toString(type, node);
        if (params != null && params.size() > 0)
        {
            s += "(";
            for (Iterator iter = params.iterator(); iter.hasNext();)
            {
                String name = (String) iter.next();
                s += name;
                if (iter.hasNext())
                {
                    s += ",";
                }
            }
            s += ")";
        }
        return s;
    }
    public static String toString(int type, SimpleNode node)
    {
        //used to identify the statement
        switch (type)
        {
            case SQLParserConstants.TYPE_SQL_SELECT:
                return Messages.getString("ASTSQLStatement.select");
            case SQLParserConstants.TYPE_SQL_INSERT:
                return Messages.getString("ASTSQLStatement.insert");
            case SQLParserConstants.TYPE_SQL_DELETE:
                return Messages.getString("ASTSQLStatement.delete");
            case SQLParserConstants.TYPE_SQL_UPDATE:
                return Messages.getString("ASTSQLStatement.update");
            case SQLParserConstants.TYPE_SQL_CREATE_DATABASE:
                return Messages.getString("ASTSQLStatement.create.database");
            case SQLParserConstants.TYPE_SQL_ALTER_DATABASE:
                return Messages.getString("ASTSQLStatement.alter.database");
            case SQLParserConstants.TYPE_SQL_CREATE_TABLE:
                return Messages.getString("ASTSQLStatement.create.table");
            case SQLParserConstants.TYPE_SQL_ALTER_TABLE:
                return Messages.getString("ASTSQLStatement.alter.table");
            case SQLParserConstants.TYPE_SQL_CREATE_VIEW:
                return Messages.getString("ASTSQLStatement.create.view");
                //alter view
            case SQLParserConstants.TYPE_SQL_ALTER_VIEW:
                return Messages.getString("ASTSQLStatement.alter.view");
                //drop view
            case SQLParserConstants.TYPE_SQL_DROP_VIEW:
                return Messages.getString("ASTSQLStatement.drop.view");
            case SQLParserConstants.TYPE_SQL_CREATE_PROCEDURE:
                return Messages.getString("ASTSQLStatement.create.procedure");
                //alter procedure
            case SQLParserConstants.TYPE_SQL_ALTER_PROCEDURE:
                return Messages.getString("ASTSQLStatement.alter.procedure");

            case SQLParserConstants.TYPE_SQL_CREATE_FUNCTION:
                return Messages.getString("ASTSQLStatement.create.function");
            case SQLParserConstants.TYPE_SQL_ALTER_FUNCTION:
                return Messages.getString("ASTSQLStatement.alter.function");           
            case SQLParserConstants.TYPE_SQL_CREATE_EVENT:
                return Messages.getString("ASTSQLStatement.create.event");
            case SQLParserConstants.TYPE_SQL_ALTER_EVENT:
                return Messages.getString("ASTSQLStatement.alter.event");
            case SQLParserConstants.TYPE_SQL_CREATE_TRIGGER:
                return Messages.getString("ASTSQLStatement.create.trigger");
            case SQLParserConstants.TYPE_SQL_CREATE_DEFAULT:
                return Messages.getString("ASTSQLStatement.create.default");
            case SQLParserConstants.TYPE_SQL_DECLARE:
                return Messages.getString("ASTSQLStatement.declare");
            case SQLParserConstants.TYPE_SQL_CALL:
                return Messages.getString("ASTSQLStatement.call");
            case SQLParserConstants.TYPE_SQL_BEGIN:
                return Messages.getString("ASTSQLStatement.begin");
            case SQLParserConstants.TYPE_SQL_BEGIN_TRANSACTION:
                return Messages.getString("ASTSQLStatement.begin.transaction");
            case SQLParserConstants.TYPE_SQL_ALTER_TRIGGER:
                return Messages.getString("ASTSQLStatement.alter.trigger");
                //create index
            case SQLParserConstants.TYPE_SQL_CREATE_INDEX:
                return Messages.getString("ASTSQLStatement.create.index");
                //alter index
            case SQLParserConstants.TYPE_SQL_ALTER_INDEX:
                return Messages.getString("ASTSQLStatement.alter.index");
            case SQLParserConstants.TYPE_SQL_OTHERS:
            default:
                return Messages.getString("ASTSQLStatement.others", node.getFirstToken().image.toUpperCase());

        }
    }
}
