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
package org.eclipse.datatools.sqltools.sqlbuilder.views.graph;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.JoinHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.AddTableAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.CreateJoinAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.DefineJoinTypeAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.TableAliasAction;
import org.eclipse.datatools.sqltools.sqlbuilder.model.DeleteHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.InsertHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.UpdateHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.VendorHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.ISQLEditPart;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.JoinEditPart;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.SQLRootEditPart;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.TableEditPart;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;

public class GraphContextMenuProvider extends ContextMenuProvider {

    SQLDomainModel domainModel;
    VendorHelper vendorHelper; //for vendor specific actions
    AddTableAction addTable; // all statements
    AddTableAction replaceSelectTable; // used for select statement only
    RemoveSelectedTableAction removeTable; // all statements
    TableAliasAction tableAlias; // used for select,update, delete
    CreateJoinAction createJoin; // used for select statement only
    DefineJoinTypeAction defineJoinType; // used for select statement only
    RemoveJoinAction removeJoin; // used for select statement only

    SelectAllColumnsAction selectAll; // used for select, update
    DeselectAllColumnsAction deselectAll; // used for select, update

    SQLQueryObject statement;

    public GraphContextMenuProvider(EditPartViewer viewer, SQLDomainModel domainModel) {
        super(viewer);
        this.domainModel = domainModel;
        initActions();
        vendorHelper = new VendorHelper(domainModel.getDatabase());
    }

    protected void initActions() {
        addTable = new AddTableAction(domainModel);
        replaceSelectTable = new AddTableAction(domainModel);
        removeTable = new RemoveSelectedTableAction();
        tableAlias = new TableAliasAction(domainModel);
        createJoin = new CreateJoinAction(domainModel);
        defineJoinType = new DefineJoinTypeAction(domainModel);
        removeJoin = new RemoveJoinAction();

        selectAll = new SelectAllColumnsAction();
        deselectAll = new DeselectAllColumnsAction();
        
    }

    protected void addGlobalActionsAtStart(IMenuManager menu) {
        // always have an add menu item
        // for update, delete and insert - actions text changes to replace statement contains a table
        menu.add(addTable);
    }

    protected void addContextActions(IMenuManager menu, List selectedEditParts) {
        EditPart part = null;
        if (selectedEditParts != null) {
            Iterator iter = selectedEditParts.iterator();
            if (iter.hasNext()) {
                part = (EditPart) iter.next();
            }
        }

        if (part instanceof TableEditPart) {
            Object table = null;
            if (statement instanceof QuerySelectStatement) {

                // Add the Define Join menu only if there is more than 1 table in the
                // select statement
                table = ((TableEditPart) part).getTable();
                if (table == null) {
                    if (((TableEditPart) part).getModel() instanceof TableExpression) {
                        table = ((TableEditPart) part).getModel();
                    }
                }
                QuerySelectStatement selectStmt = (QuerySelectStatement) statement;
                List tables = StatementHelper.getTablesForStatement(selectStmt);
                if (tables.size() > 1) {
                    createJoin.setSourceTable((TableExpression) tables.get(0));
                    menu.add(createJoin);
                }
                //replace is an additional menu item for select statement
                menu.add(replaceSelectTable);
                replaceSelectTable.setTable(table);

                menu.add(selectAll);
                selectAll.setTable(table);
                menu.add(deselectAll);
                deselectAll.setTable(table);
            }
            else if (statement instanceof QueryUpdateStatement || statement instanceof QueryDeleteStatement) {
                // handle special case of replace - add/replace action text alternates
                table = ((TableEditPart) part).getTable();
                addTable.setTable(table);
                if (statement instanceof QueryUpdateStatement) {
                    menu.add(selectAll);
                    selectAll.setTable(table);
                    menu.add(deselectAll);
                    deselectAll.setTable(table);
                }
            }
            else if (statement instanceof QueryInsertStatement) {
                // handle special case of replace - add/replace action text alternates
                table = ((TableEditPart) part).getTable();
                addTable.setTable(table);

                menu.add(selectAll);
                selectAll.setTable(table);
                menu.add(deselectAll);
                deselectAll.setTable(table);
            }

            if (!(statement instanceof QueryInsertStatement)) {
                TableExpression tableExpr = (TableExpression) ((TableEditPart) part).getModel();
                // no table alias for Cloudscape and delete statements
                if (!((vendorHelper.isCloudscape() || vendorHelper.isSybase()) && statement instanceof QueryDeleteStatement)) {
                    tableAlias.setTable(tableExpr);
                    menu.add(tableAlias);
                }
                removeTable.setTable(tableExpr);
            }

            menu.add(removeTable);
            setCurrentStatement(statement);
        }

        else if (part instanceof JoinEditPart) {
            JoinEditPart joinPart = (JoinEditPart) part;
            defineJoinType.setSQLJoin(joinPart.getSQLJoin());
            removeJoin.setJoinPart(joinPart);
            menu.add(defineJoinType);
            menu.add(removeJoin);
        }
        else if ((part instanceof SQLRootEditPart)
                && ((statement instanceof QueryUpdateStatement) || (statement instanceof QueryInsertStatement) || (statement instanceof QueryDeleteStatement))) {
            // handle switch back to Add table if table removed - also background popup
            Object stmtTable = null;
            if (statement instanceof QueryInsertStatement) {
                QueryInsertStatement insert = (QueryInsertStatement) statement;
                stmtTable = insert.getTargetTable();
            }
            else if (statement instanceof QueryUpdateStatement) {
                QueryUpdateStatement update = (QueryUpdateStatement) statement;
                stmtTable = update.getTargetTable();
            }
            else if (statement instanceof QueryDeleteStatement) {
                QueryDeleteStatement delete = (QueryDeleteStatement) statement;
                stmtTable = delete.getTargetTable();
            }
            addTable.setTable(stmtTable);
            setCurrentStatement(statement);
        }
        else {
            // No part selected.  Only use global action
            addTable.setTable(null);

            if (statement instanceof QuerySelectStatement) {
                // Add the Define Join menu only if there is more than 1 table in the
                // select statement
                QuerySelectStatement selectStmt = (QuerySelectStatement) statement;
                QueryExpressionRoot root = selectStmt.getQueryExpr();
                if (root != null) {
                    QueryExpressionBody queryExprBody = root.getQuery();
                    if (queryExprBody != null) {
                        List tables = StatementHelper.getTableExpressionsInQueryExpressionBody(queryExprBody);
                        if (tables.size() > 1) {
                            createJoin.setSourceTable((TableExpression) tables.get(0));
                            menu.add(createJoin);
                        }
                    }
                }
            }
        }
    }

    protected SQLQueryObject getCurrentStatement() {
        return statement;
    }

    protected void setCurrentStatement(SQLQueryObject statement) {
        this.statement = statement;
        addTable.setElement(statement);
        replaceSelectTable.setElement(statement);
        tableAlias.setStatement(statement);
        createJoin.setStatement(statement);
    }

    protected void updateCurrentStatement(EditPartViewer viewer) {
        List editParts = viewer.getSelectedEditParts();
        Iterator iter = editParts.iterator();

        while (iter.hasNext()) {
            EditPart part = (EditPart) iter.next();
            if (part instanceof ISQLEditPart) {
                setCurrentStatement(((ISQLEditPart) part).getStatement());
                return;
            }
        }
    }

    public void buildContextMenu(IMenuManager menu) {
        updateCurrentStatement(getViewer());
        addGlobalActionsAtStart(menu);
        addContextActions(menu, getViewer().getSelectedEditParts());
    }

    /**
     * Remove the table from the MOF model
     */
    class RemoveJoinAction extends Action {

        public RemoveJoinAction() {
            super(Messages._UI_ACTION_REMOVE_JOIN);
        }

        JoinEditPart joinPart;

        public void setJoinPart(JoinEditPart part) {
            joinPart = part;
        }

        public void run() {
            joinPart.removeJoin();
        }
    }

    /**
     * Remove the selected table from the MOF model
     */
    class RemoveSelectedTableAction extends Action {

        public RemoveSelectedTableAction() {
            super(Messages._UI_ACTION_REMOVE_TABLE_EDIT);
        }

        TableExpression table;

        public void setTable(TableExpression table) {
            this.table = table;
        }

        public void run() {
            SQLQueryObject stmt = getCurrentStatement();
            if (stmt instanceof QuerySelectStatement || stmt instanceof QuerySelect) {
                String name = stmt.getName();
                String label = stmt.getLabel();
                //SelectHelper.clearStatementContents((QuerySelectStatement)stmt);
                List fromClauseList;
                
                // if the table is part of the join, remove the join first 
                TableJoined joinedTable = table.getTableJoinedLeft();
                if (joinedTable == null) {
                    joinedTable = table.getTableJoinedRight();
                }
                if(joinedTable != null){
	                if(stmt instanceof QuerySelect){
	                    fromClauseList = ((QuerySelect)stmt).getFromClause();
	                    JoinHelper.removeJoinsForTable(fromClauseList,table);
	                }
	                else if(stmt instanceof QuerySelectStatement){
	                    QuerySelect querySelect = SelectHelper.getQuerySelect((QuerySelectStatement)stmt);
	                    fromClauseList = querySelect.getFromClause();
	                    JoinHelper.removeJoinsForTable(fromClauseList,table);
	                }
	                SelectHelper.refresh(stmt);
                }
                StatementHelper.removeTableExpressionFromQueryStatement(table, stmt);
                stmt.setName(name);
                stmt.setLabel(label);
            }
            else if (stmt instanceof QueryInsertStatement) {
                String name = stmt.getName();
                String label = stmt.getLabel();
                InsertHelper.clearStatementContents((QueryInsertStatement) stmt);
                stmt.setName(name);
                stmt.setLabel(label);
            }
            else if (stmt instanceof QueryUpdateStatement) {
                String name = stmt.getName();
                String label = stmt.getLabel();
                UpdateHelper.clearStatementContents((QueryUpdateStatement) stmt);
                stmt.setName(name);
                stmt.setLabel(label);
            }
            else if (stmt instanceof QueryDeleteStatement) {
                String name = stmt.getName();
                String label = stmt.getLabel();
                DeleteHelper.clearStatementContents((QueryDeleteStatement) stmt);
                stmt.setName(name);
                stmt.setLabel(label);
            }
        }
    }

    //
    // Select all columns Action
    //
    class SelectAllColumnsAction extends Action {

        public SelectAllColumnsAction() {
            super(Messages._UI_VIEWS_GRAPH_SELECT_ALL_COLUMN);
        }

        Object table;

        public void setTable(Object table) {
            this.table = table;
        }

        public void run() {
            //QMP-RK
            SQLQueryObject currStatement = getCurrentStatement();
            if (currStatement instanceof QuerySelectStatement || currStatement instanceof QuerySelect) {
                SQLQueryObject selectStatement = currStatement;
                if (table instanceof Table) {
                    Table newTable = ((Table) table);
                    List tblExprList = StatementHelper.getTablesForStatement(selectStatement);
                    TableExpression tblExpr = TableHelper.getTableExpressionFromTableExprList(newTable.getName(), tblExprList);
                    Iterator colItr = newTable.getColumns().iterator();
                    while (colItr.hasNext()) {
                        Column col = (Column) colItr.next();
                        ValueExpressionColumn colExpr = SelectHelper.getSelectColumnFromColumnName(selectStatement, col.getName());
                        if (colExpr == null) {
                            colExpr = TableHelper.getColumnExpressionForColumn(tblExpr, col);
                        }

                        if (colExpr != null && (!SelectHelper.isResultColumn(selectStatement, colExpr))) {
                            ValueExpressionColumn newColExpr = ExpressionHelper.createColumnExpression(ExpressionHelper
                                    .getTableExprForValueExpressionColumn(colExpr), col);
                            SelectHelper.appendResultColumn(selectStatement, newColExpr, ""); //$NON-NLS-1$
                        }
                    }
                }
                SelectHelper.refresh(selectStatement);
            }
            else if (currStatement instanceof QueryInsertStatement) {
                QueryInsertStatement insertStatement = (QueryInsertStatement) currStatement;
                if (table instanceof Table) {
                    Table newTable = ((Table) table);
                    Iterator colItr = newTable.getColumns().iterator();
                    while (colItr.hasNext()) {
                        Column column = (Column) colItr.next();
                        ValueExpressionColumn colExpr = InsertHelper.getColumnExpressionForName(insertStatement, column.getName());
                        if (colExpr == null) //this column is not currently present, hence add it
                        {
                            EditingDomain editDomain = domainModel.getEditingDomain();
                            InsertHelper.addColumn(editDomain, insertStatement, column);
                        }
                    }
                }
            }
            else if (currStatement instanceof QueryUpdateStatement) {
                QueryUpdateStatement updateStatement = (QueryUpdateStatement) currStatement;
                if (table instanceof Table) {
                    Table newTable = ((Table) table);
                    Iterator colItr = newTable.getColumns().iterator();
                    while (colItr.hasNext()) {
                        Column column = (Column) colItr.next();
                        ValueExpressionColumn colExpr = UpdateHelper.getColumnExpressionForName(updateStatement, column.getName());
                        if (colExpr == null) //this column is not currently present, hence add it
                        {
                            UpdateHelper.addColumn(updateStatement, column);
                        }
                    }
                }

            }

        }
    }

    //
    // Deselect all columns Action
    //
    class DeselectAllColumnsAction extends Action {

        public DeselectAllColumnsAction() {
            super(Messages._UI_VIEWS_GRAPH_DESELECT_ALL_COLUMN);
        }

        Object table;

        public void setTable(Object table) {
            this.table = table;
        }

        public void run() {
            SQLQueryObject currStatement = getCurrentStatement();
            if (currStatement instanceof QuerySelectStatement || currStatement instanceof QuerySelect) {

                if (table instanceof Table) {
                    Table newTable = ((Table) table);
                    Iterator colItr = newTable.getColumns().iterator();
                    while (colItr.hasNext()) {
                        Column col = (Column) colItr.next();
                        ValueExpressionColumn colExpr = SelectHelper.getSelectColumnFromColumnName(currStatement, col.getName());

                        if (colExpr != null) {
                            SelectHelper.removeAllColumnFromResultColumns(currStatement, colExpr);

                        }
                    }
                }
                SelectHelper.refresh(currStatement);
            }
            else if (currStatement instanceof QueryInsertStatement) {
                QueryInsertStatement stmt = (QueryInsertStatement) currStatement;
                InsertHelper.removeAllColumnsAndValues(stmt);
            }
            else if (currStatement instanceof QueryUpdateStatement) {
                QueryUpdateStatement updateStatement = (QueryUpdateStatement) currStatement;
                UpdateHelper.clearAssignmentExprList(updateStatement);
            }
         }
    }
}
