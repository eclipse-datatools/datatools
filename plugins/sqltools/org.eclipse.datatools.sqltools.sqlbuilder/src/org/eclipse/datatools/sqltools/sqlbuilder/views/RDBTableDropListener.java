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
package org.eclipse.datatools.sqltools.sqlbuilder.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.GraphControl;


public class RDBTableDropListener extends DropTargetAdapter {

    protected Viewer viewer;
    protected SQLDomainModel domainModel;

    public RDBTableDropListener(Viewer viewer, SQLDomainModel domainModel) {
        this.viewer = viewer;
        this.domainModel = domainModel;
    }

    public void dropAccept(DropTargetEvent event) {
    }

    /**
     * Determines whether or not it is ok to add view
     * @return true if ok, false if not
     */
    private boolean okToAddView(ViewTable view) {
        if (view != null) {
            QueryStatement oQuery = domainModel.getSQLStatement();
            if (oQuery instanceof QuerySelectStatement) {
                QueryExpression expr = view.getQueryExpression();
                if (expr instanceof QueryExpressionRoot) {
                    if (oQuery == ((QueryExpressionRoot) expr).getSelectStatement()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void drop(DropTargetEvent event) {
        // A drop has occurred, copy over the data
        if (event.data == null) { // no data to copy, indicate failure in event.detail
            event.detail = DND.DROP_NONE;
            return;
        }
        Table tables[] = getTables(event);

        if (tables != null)
        {
            SQLQueryObject currentStatement = getCurrentStatement();
            if (currentStatement != null) {

                String dbName = null;
                Database db = domainModel.getDatabase();
                if (db != null) {
                    dbName = db.getName();
                }

                // select statements
                if (currentStatement instanceof QuerySelectStatement) {
                    QuerySelectStatement select = (QuerySelectStatement) currentStatement;
                    boolean parentView = false;
                    for (int i = 0; i < tables.length; i++) {
                        if (tables[i].getSchema().getDatabase().getName().equals( dbName )) {
                            if (tables[i] instanceof ViewTable && !okToAddView((ViewTable) tables[i])) {
                                parentView = true;
                            }
                            // check to see if table is already in from clause
                            String tableSchema = tables[i].getSchema().getName();
                            String tableName = tables[i].getName();
                            QueryExpressionRoot root = select.getQueryExpr();
                            if (root != null) {
                                QueryExpressionBody body = root.getQuery();
                                if (body instanceof QuerySelect) {
                                    List tableRefList = ((QuerySelect) body).getFromClause();
                                    // get list of table expressions, inlcuding the ones in joins if any
                                    List tableExprList = TableHelper.getTableExpressionsInTableReferenceList(tableRefList);
                                    TableExpression tableExpr = TableHelper.findTableExpressionInTableExpressionList(tableSchema, tableName, tableExprList);
                                    if (tableExpr == null && !parentView) {
                                        // can add table to fromclause
                                        tableExpr = TableHelper.createTableExpressionForTable(tables[i]);
                                        SelectHelper.addTableToStatement(select, tableExpr);
                                    }
                                    // TODO handle else (!parentView)???
                                }
                            }
                        }                        
                    } // end of for ()
                }
                if (currentStatement instanceof QuerySelect) {
                    QuerySelect select = (QuerySelect) currentStatement;
                    boolean parentView = false;
                    for (int i = 0; i < tables.length; i++) {
                        if (tables[i].getSchema().getDatabase().getName().equals( dbName )) {
                            if (tables[i] instanceof ViewTable && !okToAddView((ViewTable) tables[i])) {
                                parentView = true;
                            }
                            // check to see if table is already in from clause
                            String tableSchema = tables[i].getSchema().getName();
                            String tableName = tables[i].getName();
                            List tableRefList = select.getFromClause();
                            // get list of table expressions, inlcuding the ones in joins if any
                            List tableExprList = TableHelper.getTableExpressionsInTableReferenceList(tableRefList);
                            TableExpression tableExpr = TableHelper.findTableExpressionInTableExpressionList(tableSchema, tableName, tableExprList);
                            if (tableExpr == null && !parentView) {
                                // can add table to fromclause
                                tableExpr = TableHelper.createTableExpressionForTable(tables[i]);
                                SelectHelper.addTableToStatement(select, tableExpr);
                            }
                        }                        
                    } // end of for ()
                }
                // this check was originally checking for whether the DB objects match, but seems like we have to go on the name for now
                else if (tables[0].getSchema().getDatabase().getName().equals( dbName )) {
                    if (currentStatement instanceof QueryInsertStatement) {
                        QueryInsertStatement insert = (QueryInsertStatement) currentStatement;
                        TableInDatabase rdbTable = TableHelper.createTableExpressionForTable(tables[0]);
                        insert.setTargetTable(rdbTable);
                    }
                    else if (currentStatement instanceof QueryUpdateStatement) {
                        QueryUpdateStatement update = (QueryUpdateStatement) currentStatement;
                        TableInDatabase rdbTable = TableHelper.createTableExpressionForTable(tables[0]);
                        update.setTargetTable(rdbTable);
                    }
                    else if (currentStatement instanceof QueryDeleteStatement) {
                        QueryDeleteStatement delete = (QueryDeleteStatement) currentStatement;
                        TableInDatabase rdbTable = TableHelper.createTableExpressionForTable(tables[0]);
                        delete.setTargetTable(rdbTable);

                    }
                }
                
                // [wsdbu00076103] rk 02May2006
                // check if any of the tables that were dropped were from a different database,
                // if so issue a warning that, they can'tbe added
                boolean hasWorngTable = false;
                for (int i = 0; i < tables.length; i++) {
                    if ( ! (tables[i].getSchema().getDatabase().equals( db ) )) {
                    	hasWorngTable = true;
                    	break;
                    }
                }
                if(hasWorngTable){
                	MessageDialog.openWarning(Display.getCurrent().getActiveShell(), Messages._UI_DIALOG_WARNING_TITLE,
                			Messages._UI_INVALID_TABLE_ADDED);
                }
            }
        }
        // update view after DND
        if (viewer instanceof GraphControl) {
            ((GraphControl) viewer).updateForDND();
        }
        event.detail = DND.DROP_NONE;
    }

    protected SQLQueryObject getCurrentStatement() {
        Object obj = viewer.getInput();

        if (obj instanceof QueryStatement || obj instanceof QuerySelect ) {
            return  (SQLQueryObject)obj;
        }
        return null;
    }

    /**
     * Gets the tables dropped on the target
     * @return an array of tables dropped
     */
    protected Table[] getTables(DropTargetEvent event) {
        Object obj = event.data;
        if (obj instanceof StructuredSelection) {
            Object[] selections = ((StructuredSelection) obj).toArray();
            Table[] tables = new Table[selections.length];
            for (int i = 0; i < selections.length; i++) {
                if (selections[i] instanceof Table) {
                    tables[i] = (Table) selections[i];
                }
                else {
                    return null;
                }
            }
            return tables;
        }

        // the rest of this is for drag and drop from the navigator
        // view.  We aren't going to allow this yet.
        else if (event.data instanceof IResource[]) {
            IResource resources[] = (IResource[]) event.data;
            List tables = new ArrayList();

            for (int i = 0; i < resources.length; i++) {
                IResource resource = resources[i];
                if (resource instanceof IFile) {
                    IFile file = (IFile) resource;
                    String extension = file.getFileExtension();
                    if (extension.equalsIgnoreCase("TBLXMI") && domainModel.getIFile().getParent().equals(file.getParent())) {
                        //Table table = getTable(file);
                        //TODO QMP persistence             SQLBuilderPlugin.getPlugin().getLogger().writeTrace("table = " + table);
                        //tables.add(table);
                    }
                }
            }
            Table result[] = new Table[tables.size()];
            return (Table[]) tables.toArray(result);
        }
        return null;
    }

    // This method is also for drag and drop from the navigator.
    // Again, we don't allow this yet.
    //protected Table getTable(IFile tableFile) {
        //QMP-NB Since we don't allow it  
        /*    IPath tableFilePath = tableFile.getProjectRelativePath();
         String baseName = tableFilePath.removeFileExtension().lastSegment();
         StringTokenizer tokenizer = new StringTokenizer(baseName, "_");

         if (tokenizer.countTokens() >= 4)
         {
         String hostName   = tokenizer.nextToken();
         String dbName     = tokenizer.nextToken();
         String schemaName = tokenizer.nextToken();
         String tableName  = tokenizer.nextToken();
         while (tokenizer.hasMoreTokens())
         {
         tableName += "_" + tokenizer.nextToken();
         } // end of while ()
         return domainModel.getDatabase().findTable(dbName+"."+schemaName+"."+tableName);
         }
         */
         //return null;
    //}

}
