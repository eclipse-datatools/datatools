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

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.JoinHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.DefineJoinTypeDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SearchConditionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;


/**
 * Define the Join Type (default, inner, outer)
 */
public class DefineJoinTypeAction extends Action {

    SQLDomainModel domainModel;
    SQLQueryObject joinSource;

    public DefineJoinTypeAction(SQLDomainModel domainModel) {
        super(Messages._UI_ACTION_SPECIFY_JOIN_TYPE);
        this.domainModel = domainModel;
    }

    public void setSQLJoin(SQLQueryObject join) {
        this.joinSource = join;
    }

    public void run() {
    	int joinType = TableJoinedOperator.DEFAULT_INNER;
    	if(joinSource instanceof TableJoined ){
    		joinType = ((TableJoined)joinSource).getJoinOperator().getValue();
    	}
        DefineJoinTypeDialog dialog = new DefineJoinTypeDialog(Display.getCurrent().getActiveShell(), joinType, domainModel);
        dialog.create();
        dialog.setBlockOnOpen(true);
        int value = dialog.open();

        if (value == Window.OK) {
        	int newType = dialog.getJoinType();
        	if(joinSource instanceof TableJoined ){
        		((TableJoined)joinSource).setJoinOperator(TableJoinedOperator.get(newType));
        	}
        	// if the condition is from a where claues, move all the join like conditions in the where cluase
        	// to the from clause
        	else if (joinSource instanceof QuerySearchCondition){
        		QuerySearchCondition condition = (QuerySearchCondition)joinSource;
        		
        		QuerySelect querySelect = condition.getQuerySelectWhere();
        		List fromClause = querySelect.getFromClause();
        		
        		List predicates = SearchConditionHelper.getAllPredicates(condition);
        		Iterator itr = predicates.iterator();
        		while(itr.hasNext()){
        			Predicate predicate = (Predicate)itr.next();
        			if (predicate instanceof PredicateBasic){
        				PredicateBasic predicateBasic = (PredicateBasic)predicate;
        				
        				QueryValueExpression leftExpr = predicateBasic.getLeftValueExpr();
        				QueryValueExpression rightExpr = predicateBasic.getRightValueExpr();
        				ValueExpressionColumn sourceCol = null,targetCol = null;
        				TableExpression sourceTbl = null,targetTbl = null;
 
        				if(leftExpr instanceof ValueExpressionColumn){
        					sourceCol =  (ValueExpressionColumn)leftExpr;
        					sourceTbl = sourceCol.getTableExpr();
        				}
        				if(rightExpr instanceof ValueExpressionColumn){
        					targetCol =  (ValueExpressionColumn)rightExpr;
        					targetTbl = targetCol.getTableExpr();
        				}
        				
        				// if this predicate has ValueExpressionColumn on both sides then remove it and add it
        				// as a join
        				if(targetCol != null && sourceCol != null && sourceTbl != null && targetTbl != null){
            				condition = SearchConditionHelper.removePredicateFromCondition(predicate, condition);
            				JoinHelper.addJoin(fromClause, sourceTbl, targetTbl, sourceCol, targetCol, newType);
        				}
        			}
        		}
        		// if the condition does not have any children after moving predicates to the join tree, 
        		// set the condition accordingly 
        		setWhereClauseInQuerySelect(querySelect, condition);
        	}
            //TODO the notifications does not fire automatically
            // force a refresh
            QueryStatement stmt = domainModel.getSQLStatement();
            if (stmt instanceof QuerySelectStatement) {
                SelectHelper.refresh((QuerySelectStatement) stmt);
            }

        }
    }
    private void setWhereClauseInQuerySelect(QuerySelect querySelect, QuerySearchCondition condition){
	    if (condition instanceof PredicateBasic) {
            PredicateBasic sqlPredicate = (PredicateBasic) condition;
            if(sqlPredicate.getRightValueExpr() == null && sqlPredicate.getLeftValueExpr() == null){
            	querySelect.setWhereClause(null);
            }else {
    			querySelect.setWhereClause(condition);
    		}
		}
	    else if(condition instanceof SearchConditionCombined) {
            SearchConditionCombined grp = (SearchConditionCombined) condition;
            if(grp.getLeftCondition() == null && grp.getRightCondition() == null){
            	querySelect.setWhereClause(null);
            }else {
    			querySelect.setWhereClause(condition);
    		}
	    }
	    else if(condition instanceof SearchConditionNested) {
	    	QuerySearchCondition cond = ((SearchConditionNested) condition).getNestedCondition();
	    	setWhereClauseInQuerySelect(querySelect, cond);
	    }
	    else {
	    	querySelect.setWhereClause(condition);
	    }
    }
    
}
