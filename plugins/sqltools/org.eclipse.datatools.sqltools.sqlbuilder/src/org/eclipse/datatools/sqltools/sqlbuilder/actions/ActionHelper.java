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
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderFileEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WorkbenchUtility;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

/**
 * This class provides a set of helpful functions that support New and Open actions.
 */
public class ActionHelper {

    /**
     * Creates and returns an <code>IEditorInput</code> object that is suitable for the
     * given editor.
     * 
     * @param resource the resource (typically a file resource) with which to create
     * the editor input
     * @param editorID the editor ID (defined in the editor's plugin.xml) for which
     * an <code>IEditorInput</code> object is needed.  The supported editors are the
     * SQL Builder and the SQL Editor.
     * @param connInfo a <code>ISQLEditorConnectionInfo</cod> object to use to create the 
     * editor input
     * @return the edit input object
     */
    public static IEditorInput createEditorInput(IResource resource, String editorID, ISQLEditorConnectionInfo connInfo) {
        IEditorInput editorInput = null;
        if (editorID.equals(WorkbenchUtility.SQL_BUILDER_ID)) {
            if (resource instanceof IFile) {
                IFile fileResource = (IFile) resource;
                FileEditorInput fileEditorInput = new FileEditorInput(fileResource);
                editorInput = fileEditorInput;
            }
        }
        else if (editorID.equals(WorkbenchUtility.SQL_EDITOR_ID)) {
            if (resource instanceof IFile) {
                IFile fileResource = (IFile) resource;
                SQLBuilderFileEditorInput sqlEditorInput = new SQLBuilderFileEditorInput(fileResource);
                sqlEditorInput.setConnectionInfo(connInfo);
                editorInput = sqlEditorInput;
            }
        }

        return editorInput;
    }

    /**
     * Gets an array of strings containing a set of template DDL statements.
     *
     * @return the array of DDL statements
     */
    public static String[] getTemplateDDLStatements() {
        String[] ddlStatements = new String[] { "CREATE TABLE table_name (col1 VARCHAR(10) NOT NULL, col2 INTEGER) ;",
                "CREATE VIEW view_name AS SELECT col1, col2 FROM table1 ;", "CREATE UNIQUE INDEX index_name ON table_name (col1, col2);",
                "CREATE SCHEMA schema_name;", //$NON-NLS-1$
                "ALTER TABLE table_name ADD COLUMN column_name INTEGER NOT NULL;", //$NON-NLS-1$
                "DROP TABLE table_name ;", //$NON-NLS-1$
                "DROP VIEW view_name ;", //$NON-NLS-1$
                "DROP INDEX index_name ;" //$NON-NLS-1$
        };

        return ddlStatements;
    }

    /**
     * Gets an array of strings containing a set of template DML statements.
     * 
     * @return the array of DML statements
     */
    public static String[] getTemplateDMLStatements() {
        String[] dmlStatements = new String[] { "SELECT col1, col2 FROM table1, table2 WHERE table1.col1 = table2.col2 ;", //$NON-NLS-1$
                "INSERT INTO table_name (col1, col2) VALUES ('val1', 'val2') ;", //$NON-NLS-1$
                "UPDATE table_name SET col1='val1' WHERE table_name.col2 = '123' ;", //$NON-NLS-1$
                "DELETE FROM table_name WHERE table_name.col_name = '123' ;" //$NON-NLS-1$
        };

        return dmlStatements;
    }

    /**
     * Gets template SQL for the given statement type.  Depending on the statement
     * type, the template may be one or several SQL statements.
     * The statement types are defined in <code>StatementHelper</code>, plus
     * some additional SQL Editor-specific statement types defined in
     * <code>NewSQLStatementControl</code>  The statement types defined in 
     * <code>StatementHelper</code> return a single template SQL statement.
     * The statement types defined in <code>NewSQLStatementControl</code> return
     * either several template SQL statements or none.
     * 
     * @param statementType the type of statement for which the SQL template
     * is needed
     * @return the template SQL for the statement type 
     * @see org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper
     * @see org.eclipse.datatools.sqltools.sqlbuilder.actions.NewSQLStatementComposite
     */
    public static String getTemplateSQLForStatementType(int statementType) {
        String sql = null;

        // Statement types below STATEMENT_TYPE_SAMPLE are individual DML statements.
        // Use the StatementHelper to create the statements, then get their SQL representation.
        if (statementType < NewSQLStatementComposite.STATEMENT_TYPE_SAMPLE) {
            QueryStatement statement = StatementHelper.createQueryStatement(statementType, "TempName"); //$NON-NLS-1$
            sql = statement.getSQL();
        }
        // The STATEMENT_TYPE_SAMPLE type should contain template SQL statements for the
        // most important SQL DML and DDL statements. 
        else if (statementType == NewSQLStatementComposite.STATEMENT_TYPE_SAMPLE) {
            StringBuffer sb = new StringBuffer();
            // Append the DML templates.
            sb.append("\n\n-- " + Messages._UI_SAMPLE_DML_TEMPLATE_STATEMENTS + " -- \n\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            String[] stmtArray = getTemplateDMLStatements();
            for (int i = 0; i < stmtArray.length; i++) {
                sb.append(stmtArray[i] + "\n\n"); //$NON-NLS-1$
            }

            // Append the DDL templates.
            sb.append("\n\n-- " + Messages._UI_SAMPLE_DDL_TEMPLATE_STATEMENTS + " -- \n\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            stmtArray = getTemplateDDLStatements();
            for (int i = 0; i < stmtArray.length; i++) {
                sb.append(stmtArray[i] + "\n\n"); //$NON-NLS-1$
            }
            sql = sb.toString();
        }
        // The STATEMENT_TYPE_NONE type is just that.
        else if (statementType == NewSQLStatementComposite.STATEMENT_TYPE_NONE) {
            sql = ""; //$NON-NLS-1$
        }
        return sql;
    }

}
