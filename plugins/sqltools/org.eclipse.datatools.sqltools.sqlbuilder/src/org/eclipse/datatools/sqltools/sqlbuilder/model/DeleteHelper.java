/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.model;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;

/**
 * Helper class for DeleteStatement
 * 
 */
public class DeleteHelper {

    /**
     * Sets the target table of the given delete statement to null
     * @param statement the statement to be modified
     */
    public static void removeTableFromStatement(QueryDeleteStatement statement) {
        statement.setTargetTable(null);
    }

    /**
     * Sets the target TableExpression of the given Delete statement  
     * @param statement the statement to be modified
     * @param targetTable the new TargetTableExpression
     */
    public static void setTargetTable(QueryDeleteStatement statement, TableInDatabase targetTable) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{statement, targetTable });
        }
        if (statement != null) {
            statement.setTargetTable(targetTable);
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(statement));
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Clears the contents of the given delete statement.Containments which are objects, are set to null
     * and containments which are lists, are cleared.
     * @param stmt the statement being modified
     */
    public static void clearStatementContents(QueryDeleteStatement stmt) {
        if (stmt != null) {
            stmt.setTargetTable(null);
            stmt.setWhereClause(null);
            stmt.setWhereCurrentOfClause(null);
            //stmt.setSourceInfo(null);
            stmt.setName(null);
            stmt.setLabel(null);
        }
    }

    /**
     * Replaces the containments in one insert statement with the containments in another delete statement 
     * @param oldStmt statement in which the containments are being replaced
     * @param newStmt statement whose contents are used to replace contents in oldStmt
     */
    public static void replaceStatementContents(QueryDeleteStatement oldStmt, QueryDeleteStatement newStmt) {
        if (oldStmt != null && newStmt != null) {
            oldStmt.setLabel(newStmt.getLabel());
            oldStmt.setName(newStmt.getName());

            oldStmt.setSourceInfo(newStmt.getSourceInfo());
            oldStmt.setWhereClause(newStmt.getWhereClause());
            oldStmt.setWhereCurrentOfClause(newStmt.getWhereCurrentOfClause());
            oldStmt.setTargetTable(newStmt.getTargetTable());
        }
    }

    /**
     * Refreshes the model that ultimately refreseh the UI,  this is temporary.
     * @param stmt the QueryDeleteStatement which needs to be refreshed
     */
    public static void refresh(QueryDeleteStatement stmt) {
        stmt.setWhereClause(stmt.getWhereClause());
    }

}