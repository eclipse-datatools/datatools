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
package org.eclipse.datatools.sqltools.sqlbuilder.views.graph.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.JoinHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;

public class CreateJoinCommand extends Command {

    private ValueExpressionColumn srcColumn, tgtColumn;
    private TableExpression sourceTable, targetTable;
    private QuerySelect selectStatement;

    public void setSelectStatement(QuerySelect statement) {
        selectStatement = statement;
    }

    public void setSourceColumn(ValueExpressionColumn column) {
        srcColumn = column;
    }

    public void setTargetColumn(ValueExpressionColumn column) {
        tgtColumn = column;
    }

    public void setSourceTable(TableExpression table) {
        sourceTable = table;
    }

    public void setTargetTable(TableExpression table) {
        targetTable = table;
    }

    public void undo() {
    }

    private List getFromClauseList(QuerySelect selectStmt) {
        List fromList = new ArrayList();
        if (selectStmt != null) {
            fromList = selectStmt.getFromClause();
        }
        return fromList;
    }

    public boolean canExecute() {
        if (tgtColumn == null && targetTable == null) {
            return true;
        }

        if (srcColumn != null && tgtColumn != null) {
            List fromContent = getFromClauseList(selectStatement);
            int status = JoinHelper.checkJoin(fromContent, sourceTable, targetTable, srcColumn, tgtColumn, false);
            if (status == JoinHelper.JOIN_VALID) {
                return true;
            }
        }
        return false;
    }

    public void execute() {
        List fromContent = getFromClauseList(selectStatement);
        //create new ValueExpressionColumn objects to be put in the join condition 
        ValueExpressionColumn srcCol = ExpressionHelper.createValueExpressionColumn(srcColumn);
        ValueExpressionColumn tgtCol = ExpressionHelper.createValueExpressionColumn(tgtColumn);

        JoinHelper.checkJoin(fromContent, sourceTable, targetTable, srcCol, tgtCol, false);

        // Get the type of join (inner, left outer, etc.).
        int joinKind = TableJoinedOperator.DEFAULT_INNER;

        // Add the new join to the From clause.  If a join between the
        // two tables already exists, a new condition will be added to
        // that join.
        if (sourceTable != null && targetTable != null && srcCol != null && tgtCol != null) {
            JoinHelper.addJoin(fromContent, sourceTable, targetTable, srcCol, tgtCol, joinKind);
            SelectHelper.refresh(selectStatement);
        }
    }

}
