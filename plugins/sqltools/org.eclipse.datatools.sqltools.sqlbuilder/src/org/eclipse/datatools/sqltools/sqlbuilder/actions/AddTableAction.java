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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.WithTableReference;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.AddTableDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.model.DeleteHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.InsertHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.UpdateHelper;

public class AddTableAction extends Action {

    Object object;
    SQLDomainModel domainModel;
    Object selectTableToReplace = null;
    boolean replace = false; // true = replace table, false = add table

    public AddTableAction(SQLDomainModel domainModel) {
        super(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_ADD_TABLE);
        this.domainModel = domainModel;
    }

    public void setTable(Object table) {
        this.selectTableToReplace = table;
        if (table != null) {
            replace = true;
            setText(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_ADD_TABLE_REPLACE);
        }
        else {
            replace = false;
            setText(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_ADD_TABLE);
        }
    }

    public void setElement(Object obj) {
        object = obj;

        if (selectTableToReplace == null) {

            replace = false;
            if (object instanceof QueryInsertStatement) {
                replace = (((QueryInsertStatement) object).getTargetTable() == null) ? false : true;

            }
            else if (object instanceof QueryUpdateStatement) {
                replace = (((QueryUpdateStatement) object).getTargetTable() == null) ? false : true;
            }
            else if (object instanceof QueryDeleteStatement) {
                replace = (((QueryDeleteStatement) object).getTargetTable() == null) ? false : true;
            }

            if (replace) {
                setText(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_ADD_TABLE_REPLACE);
            }
            else {
                setText(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_ADD_TABLE);
            }
        }
    }

    Shell getShell() {
        return org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow().getShell();
    }

    public void run() {
        SQLObject table = null;
        String tableAlias = null;

        // Retrieve referenced table names
        if (object != null) {
            List tableList = new ArrayList();
            if (object instanceof SQLQueryObject) {
                tableList = StatementHelper.getTablesForStatement((SQLQueryObject) object);
            }

            Vector tableNames = new Vector();

            for (int i = 0; i < tableList.size(); i++) {
                Object item = tableList.get(i);

                if (item instanceof TableExpression) {
                    // For those statements where that a correlation object
                    TableExpression tableExpr = (TableExpression) item;
                    tableNames.addElement(tableExpr.getName());
                }
                else if (item instanceof Table) {
                    tableNames.addElement(((Table) item).getName());
                }
            }
        }

        Object action = null;
        if (replace) {
            action = AddTableDialog.REPLACE_TABLE;
        }
        else {
            action = AddTableDialog.ADD_TABLE;
        }

        AddTableDialog dialog = new AddTableDialog(getShell(), domainModel, object, null);
        dialog.setAction(action);
        String tableName = ""; //$NON-NLS-1$
        if (selectTableToReplace != null) {
            if (selectTableToReplace instanceof TableExpression) {
                tableName = ((TableExpression) selectTableToReplace).getName();
            }
            else {
                tableName = ((Table) selectTableToReplace).getName();
            }
        }
        dialog.setReplaceTitle(tableName);
        dialog.create();

        dialog.setBlockOnOpen(true);
        int value = dialog.open();
        if (value == Window.CANCEL)
            return;
        List tableList = dialog.getTablesList();
        //table = dialog.getTableValue();
        if (tableList != null && tableList.size() > 0) {
        	table = (SQLObject) tableList.get(0);
        }
        
        tableAlias = dialog.getTableAlias();

        if (table != null) {
            if (object instanceof QuerySelectStatement || object instanceof QuerySelect) {
                SQLQueryObject stmt = (SQLQueryObject) object;
                String name = stmt.getName();
                String label = stmt.getLabel();
                TableExpression tableExpr = null;
                int replacePosition = 0;

                if (replace) {
                    if (selectTableToReplace instanceof Table) {
                        List tblExprList = StatementHelper.getTablesForStatement(stmt);
                        TableExpression oldTableExpr = TableHelper.getTableExpressionFromTableExprList(((Table) selectTableToReplace).getName(), tblExprList);
                        replacePosition = tblExprList.indexOf(oldTableExpr);
                        if (oldTableExpr != null) {
                            //SelectHelper.removeTableFromStatement(stmt, oldTableExpr);
                            StatementHelper.removeTableExpressionFromQueryStatement(oldTableExpr, stmt);
                        }
                    }
                    else if (selectTableToReplace instanceof WithTableReference) {
                        StatementHelper.removeTableExpressionFromQueryStatement((WithTableReference) selectTableToReplace, stmt);
                    }
                }

                Iterator itr = tableList.iterator();
                while (itr.hasNext()) {
                    table = (SQLObject) itr.next();
                    if (table instanceof Table) {
                        tableExpr = TableHelper.createTableExpressionForTable((Table) table);
                    }
                    else if (table instanceof WithTableSpecification) {
                        tableExpr = StatementHelper.createWithTableReferenceForWithTable((WithTableSpecification) table);
                    }
                    if (tableAlias.trim().length() > 0) {
                        TableHelper.setTableAliasInTableExpression(tableExpr, tableAlias);
                    }
                    if (stmt instanceof QuerySelectStatement) {
                    	if(replace){
                    		SelectHelper.addTableToStatementAtPosition(stmt, tableExpr, replacePosition);
                    	}
                    	else {
                    		SelectHelper.addTableToStatement((QuerySelectStatement) stmt, tableExpr);
                    	}
                    }
                    else if (stmt instanceof QuerySelect) {
                    	if(replace){
                    		SelectHelper.addTableToStatementAtPosition(stmt, tableExpr, replacePosition);
                    	}
                    	else {
                    		SelectHelper.addTableToStatement((QuerySelect) stmt, tableExpr);
                    	}
                    }
                }
                stmt.setName(name);
                stmt.setLabel(label);
            }
            else if (object instanceof QueryInsertStatement) {
                QueryInsertStatement stmt = (QueryInsertStatement) object;
                String name = stmt.getName();
                String label = stmt.getLabel();
                TableInDatabase tableExpr = TableHelper.createTableExpressionForTable((Table) table);
                InsertHelper.clearStatementContents(stmt);
                InsertHelper.setTargetTable(stmt, tableExpr);
                stmt.setName(name);
                stmt.setLabel(label);
            }
            else if (object instanceof QueryUpdateStatement) {
                QueryUpdateStatement stmt = (QueryUpdateStatement) object;
                String name = stmt.getName();
                String label = stmt.getLabel();
                TableInDatabase tableExpr = TableHelper.createTableExpressionForTable((Table) table);
                if (tableAlias.trim().length() > 0) {
                    TableHelper.setTableAliasInTableExpression(tableExpr, tableAlias);
                }
                UpdateHelper.clearStatementContents(stmt);
                UpdateHelper.setTargetTable(stmt, tableExpr);
                stmt.setName(name);
                stmt.setLabel(label);
            }
            else if (object instanceof QueryDeleteStatement) {
                QueryDeleteStatement stmt = (QueryDeleteStatement) object;
                String name = stmt.getName();
                String label = stmt.getLabel();
                TableInDatabase tableExpr = TableHelper.createTableExpressionForTable((Table) table);
                if (tableAlias.trim().length() > 0) {
                    TableHelper.setTableAliasInTableExpression(tableExpr, tableAlias);
                }
                DeleteHelper.clearStatementContents(stmt);
                DeleteHelper.setTargetTable(stmt, tableExpr);
                stmt.setName(name);
                stmt.setLabel(label);

            }
        }
    }
}
