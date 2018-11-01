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

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.JoinHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.SourceTargetDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;

//
// Create a join between current table and a selected table/column
//
public class CreateJoinAction extends Action {

    QuerySelect querySelect;
    TableExpression sourceTable;
    SQLDomainModel domainModel;
    QuerySelectStatement querySelectStmt;

    public CreateJoinAction(SQLDomainModel domainModel) {
        super(Messages._UI_ACTION_CREATE_JOIN);
        this.domainModel = domainModel;
    }

    public void setStatement(SQLQueryObject statement) {
        if (statement instanceof QuerySelectStatement) {
            querySelectStmt = (QuerySelectStatement) statement;
            QueryExpressionRoot root = querySelectStmt.getQueryExpr();
            if (root != null) {
                QueryExpressionBody queryExpr = root.getQuery();
                if (queryExpr instanceof QuerySelect) {
                    querySelect = (QuerySelect) queryExpr;
                }
            }
        }
        else if (statement instanceof QuerySelect) {
            QueryStatement qStmt = StatementHelper.getQueryStatementForTableReference((QuerySelect) statement);
            if(qStmt instanceof QuerySelectStatement){
                querySelectStmt = (QuerySelectStatement) qStmt;
                querySelect = (QuerySelect) statement;
            }
        }
        else {
            querySelect = null;
        }
    }

    public void setSourceTable(TableExpression table) {

        sourceTable = table;

    }

    public void run() {
        SourceTargetDialog dialog = new SourceTargetDialog(Display.getCurrent().getActiveShell(), sourceTable, querySelect, domainModel);

        dialog.create();
        dialog.setBlockOnOpen(true);
        int value = dialog.open();
        if (value == Window.CANCEL)
            return;

        // Get the source and target table objects.
        TableExpression srcTable = dialog.getSourceTable();
        TableExpression targetTable = dialog.getTargetTable();
        // Get the source and target column objects.
        ValueExpressionColumn sourceColumn = dialog.getSourceColumn();
        ValueExpressionColumn targetColumn = dialog.getTargetColumn();
        //  create new ValueExpressionColumn objects to be put in the join condition 
        ValueExpressionColumn sourceColumnCopy = ExpressionHelper.createValueExpressionColumn(sourceColumn);
        ValueExpressionColumn targetColumnCopy = ExpressionHelper.createValueExpressionColumn(targetColumn);

        // Get the type of join (inner, left outer, etc.).
        int joinKind = dialog.getJoinType();

        // Add the new join to the From clause.  If a join between the
        // two tables already exists, a new condition will be added to
        // that join.
        if (srcTable != null && targetTable != null && sourceColumn != null && targetColumn != null) {

            if (querySelect != null) {
                List fromClause = querySelect.getFromClause();
                JoinHelper.addJoin(fromClause, srcTable, targetTable, sourceColumnCopy, targetColumnCopy, joinKind);
                SelectHelper.refresh(querySelectStmt);
            }
        }
    }
}
