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

import java.util.List;

import org.eclipse.gef.commands.Command;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.JoinHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SearchConditionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.ColumnEditPart;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.JoinEditPart;

public class MoveJoinCommand extends Command {

    JoinEditPart joinEditPart;
    ColumnEditPart target;
    QuerySelect selectStatement;

    public MoveJoinCommand() {
        super("wire connection"); //$NON-NLS-1$
    }

    public void cancel() {
    }

    public boolean canExecute() {
        return updateJoin(false);
    }

    public void execute() {

        if (joinEditPart == null || target == null)
            return;

        updateJoin(true);
    }

    /** 
     * Checks if the join is valid, and if it is, and if the changeJoin flag 
     * is true, updates the join.
     * @param changeJoin when true, update the join (if valid), otherwise
     * only do the validity check
     * @return true when the join is valid, otherwise false
     */
    private boolean updateJoin(boolean changeJoin) {
        boolean isValid = false;

        // Get the existing source table and source column from the JoinEditPart.
        TableExpression oldSourceTable = joinEditPart.getSourceTable();
        ValueExpressionColumn oldSourceColumn = joinEditPart.getSourceColumn();

        // Get the existing target table and target column from the JoinEditPart.
        TableExpression oldTargetTable = joinEditPart.getTargetTable();
        ValueExpressionColumn oldTargetColumn = joinEditPart.getTargetColumn();

        // Get the new target table and new target column.
        TableExpression newTargetTable = (TableExpression) target.getTableEditPart().getModel();
        ValueExpressionColumn newColumn = (ValueExpressionColumn) target.getModel();
        // Determine if we are changing the source or the target.
        TableExpression sourceTable, targetTable;
        ValueExpressionColumn sourceColumn, targetColumn;

        if (isChangeSource() == true) {
            // We're changing the source side.
            sourceTable = newTargetTable;
            sourceColumn = newColumn;

            targetTable = oldTargetTable;
            targetColumn = oldTargetColumn;
        }
        else {
            // We're changing the target side.
            sourceTable = oldSourceTable;
            sourceColumn = oldSourceColumn;

            targetTable = newTargetTable;
            targetColumn = newColumn;
        }

        List fromContent = selectStatement.getFromClause();
        // Check the validity of the new join.
        int validJoin = JoinHelper.checkJoin(fromContent, sourceTable, targetTable, sourceColumn, targetColumn, true);
        if (validJoin == JoinHelper.JOIN_VALID) {
            isValid = true;
            // If the join is valid and the changeJoin flag is true 
            // then go ahead and add the join.
            if (changeJoin == true) {
                //create new ValueExpressionColumn objects to be put in the join condition
                ValueExpressionColumn srcCol = ExpressionHelper.createValueExpressionColumn(sourceColumn);
                ValueExpressionColumn tgtCol = ExpressionHelper.createValueExpressionColumn(targetColumn);

                // Get the current join type from the join.  We don't want to change it.
                
                SQLQueryObject  joinSource = joinEditPart.getSQLJoin();
                int joinType = TableJoinedOperator.DEFAULT_INNER;
                if(joinSource instanceof TableJoined){
                	joinType = ((TableJoined)joinSource).getJoinOperator().getValue();
                	// Remove the old condition from the model.
                	joinEditPart.removeJoin();
                	// Update the join with the changed join condition.
                	JoinHelper.addJoin(fromContent, sourceTable, targetTable, srcCol, tgtCol, joinType);
                }
                else if(joinSource instanceof QuerySearchCondition){
                	Predicate newPred = SearchConditionHelper.buildPredicateBasic(srcCol, tgtCol, 
                			joinEditPart.getSQLPredicate().getComparisonOperator().toString());
                	QuerySearchCondition newCondition = SearchConditionHelper.replacePredicate((QuerySearchCondition)joinSource,
                			joinEditPart.getSQLPredicate(), newPred);
                	selectStatement.setWhereClause(newCondition);
                }
                SelectHelper.refresh(selectStatement);
            }
        }

        return isValid;
    }

    public String getDescription() {
        return "MoveJoin change"; //$NON-NLS-1$
    }

    public JoinEditPart getJoinPart() {
        return joinEditPart;
    }

    //
    // Set the JoinView Object
    //
    public void setJoinPart(JoinEditPart source) {
        joinEditPart = source;
    }

    //
    // Set the target ColumnEditPart
    //
    public ColumnEditPart getColumnPart() {
        return target;
    }

    //
    // Set the target ColumnEditPart
    //
    public void setColumnPart(ColumnEditPart target) {
        this.target = target;
    }

    boolean changeSource;

    /**
     * Get the value of changeSource.  This determines if the move join command is changing the source table or the target table
     * @return value of changeSource.
     */
    public boolean isChangeSource() {
        return changeSource;
    }

    /**
     * Set the value of changeSource.  This determines if the move join command is changing the source table or the target table
     * @param v  Value to assign to changeSource.
     */
    public void setChangeSource(boolean v) {
        this.changeSource = v;
    }

    public void setSelectStatement(QuerySelect statement) {
        selectStatement = statement;
    }

    public void redo() {
    }

    public void undo() {
    }

}
