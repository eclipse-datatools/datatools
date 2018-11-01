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

package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SearchConditionHelper;

public class CaseSearchWhenContentElement {

    private ValueExpressionCaseSearch sqlCaseSearchWhenClause;
    private ValueExpressionCaseSearchContent content;
    private Predicate searchPred;
    private int clauseNumber;
    private boolean firstClause;
    private Object left, right, result;
    private String operator = "";
    private String andOr = "";
    private SearchConditionHelper searchConHelper;
    private SQLQueryObject sqlStatement;
    ExpressionHelper eHelper = new ExpressionHelper();

    public CaseSearchWhenContentElement(SQLDomainModel domainModel, SQLQueryObject sqlStmt, 
            ValueExpressionCaseSearch caseSearchClause, ValueExpressionCaseSearchContent content, Predicate predicate, int clauseNumber, boolean firstClause) {
        this.sqlStatement = sqlStmt;
        this.sqlCaseSearchWhenClause = caseSearchClause;
        this.content = content;
        this.searchPred = predicate;
        this.clauseNumber = clauseNumber;
        this.firstClause = firstClause;
        searchConHelper = new SearchConditionHelper(domainModel);
    }

    public boolean getFirstClause() {
        return firstClause;
    }

    public int getClauseNumber() {
        return clauseNumber;
    }

    public Predicate getPredicate() {
        return searchPred;
    }

    public ValueExpressionCaseSearch getSQLCaseSearchWhenClause() {
        return sqlCaseSearchWhenClause;
    }

    public ValueExpressionCaseSearchContent getSQLCaseSearchWhenContent(int i) {
        return (ValueExpressionCaseSearchContent) sqlCaseSearchWhenClause.getSearchContentList().get(i);
    }

    public void setOperator(String op) {
        operator = op;
        if (searchPred == null) {
            createSearchCondition();
        }
        else {
            updateOperator();
        }
    }

    public void setLeftExpression(Object leftObj) {
        left = leftObj;
        if (searchPred == null) {
            createSearchCondition();
        }
        else {
            updateLeft();
        }
    }

    public void setRightExpression(Object rightObj) {
        right = rightObj;
        if (searchPred == null) {
            createSearchCondition();
        }
        else {
            updateRight();
        }
    }

    public void setAndOr(Object andOrObj) {
        andOr = (String) andOrObj;
        if (searchPred == null) {
            createSearchCondition();
        }

        if (firstClause) {
            if (searchPred.getCombinedRight() == null && searchPred.getCombinedLeft() == null) {
                if (content.getSearchCondition() == null) {
                    searchPred = SearchConditionHelper.buildEmptyPredicate();
                    content.setSearchCondition(searchPred);

                }
                else {
                    QuerySearchCondition newCond = searchConHelper.buildSearchCondition(null, content.getSearchCondition(), null, null, "");
                    content.setSearchCondition(newCond);
                }

                if (content.getSearchCondition() instanceof SearchConditionCombined) {
                    SearchConditionCombined scc = (SearchConditionCombined) content.getSearchCondition();
                    scc.setCombinedOperator(SearchConditionCombinedOperator.get(andOr));
                }
            }
            else {
                updateAndOr();
            }
        }
        else {
            if (searchPred.getCombinedRight() == null && searchPred.getCombinedLeft() == null) {
                if (content.getSearchCondition() == null) {
                    searchPred = SearchConditionHelper.buildEmptyPredicate();
                    content.setSearchCondition(searchPred);

                }
                else {
                    QuerySearchCondition newCond = searchConHelper.buildSearchCondition(null, content.getSearchCondition(), null, null, "");
                    content.setSearchCondition(newCond);
                }

                if (content.getSearchCondition() instanceof SearchConditionCombined) {
                    SearchConditionCombined scc = (SearchConditionCombined) content.getSearchCondition();
                    scc.setCombinedOperator(SearchConditionCombinedOperator.get(andOr));
                }
            }
            else {
                updateAndOr();
            }
        }
    }

    public void setResultExpression(Object resultObj) {
        result = resultObj;
        initializeSearchContent();
        if (searchPred == null) {
            createSearchCondition();
            content.setValueExpr((QueryValueExpression) result);
        }
        else {
            updateResult();
        }
    }

    private void initializeSearchContent() {
        if (content == null) {
            SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;

            content = factory.createValueExpressionCaseSearchContent();
            content.setSearchCondition(null);
            content.setValueExpr(null);

            sqlCaseSearchWhenClause.getSearchContentList().add(content);
        }
    }

    public void createSearchCondition() {
        if (searchPred == null) // && left != null)
        {
            String rightStr = "";
            initializeSearchContent();
            if (right != null) {
                if (right instanceof QueryValueExpression) {
                    rightStr = ((QueryValueExpression) right).getSQL();
                }
                else if (right instanceof String) {
                    rightStr = (String) right;
                }
            }
            Predicate newPred = searchConHelper.buildNewPredicate(sqlStatement, (QueryValueExpression) left, rightStr, operator);
            if (newPred != null) {
                searchPred = newPred;
                content.setSearchCondition(newPred);
            }
        }
    }

    protected void updateLeft() {
        QueryValueExpression expr;
        if (!(left instanceof QueryValueExpression)) {
            expr = ExpressionHelper.createExpression(left);

            if (expr != null) {
                searchConHelper.setLeftInPredicate(searchPred, expr);
            }
        }
        else {
            searchConHelper.setLeftInPredicate(searchPred, (QueryValueExpression) left);

        }
    }

    protected void updateOperator() {
        searchConHelper.setOperatorInPredicate(sqlStatement, content.getSearchCondition(), searchPred, operator.trim());

        if (!isValueNeeded()) {
            right = null;
            updateRight(); // clear the value
        }
    }

    /**
     * Determine if a value field needs to be specified for the condition
     */
    private boolean isValueNeeded() {
        if (operator.equals("IS NULL") || operator.equals("IS NOT NULL")) {
            return false;
        }
        return true;
    }

    protected void updateRight() {
        String newRight = "";
        String orgRight = "";
        QueryValueExpression orgLeft = null;
        QueryValueExpression newLeft = null;
        Predicate newPredicate = null;
        if (right instanceof QueryValueExpression) {
            orgRight = ((QueryValueExpression) right).getSQL();
        }
        else if (right instanceof String) {
            orgRight = (String) right;
        }
        if (left instanceof QueryValueExpression) {
            orgLeft = (QueryValueExpression) left;
        }
        else {
            orgLeft = ExpressionHelper.createExpression(left);
        }
        if (right != null) {
            newPredicate = searchConHelper.buildNewPredicate(sqlStatement, orgLeft, orgRight, operator);
            if (newPredicate == null) {
                newLeft = searchConHelper.getDefaultLeft(operator);
                newPredicate = searchConHelper.buildNewPredicate(sqlStatement, newLeft, orgRight, operator);
            }
        }
        if (newPredicate == null) {
            newRight = searchConHelper.getDefaultRight(operator);
            newPredicate = searchConHelper.buildNewPredicate(sqlStatement, orgLeft, newRight, operator);
        }
        if (newPredicate == null) {
            newPredicate = searchConHelper.buildNewPredicate(sqlStatement, newLeft, newRight, operator);
        }
        if (newPredicate != null) {
            QuerySearchCondition newCon = SearchConditionHelper.replacePredicate(content.getSearchCondition(), searchPred, newPredicate);
            content.setSearchCondition(newCon);
            searchPred = newPredicate;
        }
    }

    protected void updateAndOr() {
        QuerySearchCondition newCon = searchConHelper.setAndOrInSearchCondition(sqlStatement, content.getSearchCondition(), searchPred, andOr);
        content.setSearchCondition(newCon);
    }

    protected void updateResult() {
        QueryValueExpression expr;
        if (!(result instanceof QueryValueExpression)) {
            expr = ExpressionHelper.createExpression(result);

            if (expr != null) {
                content.setValueExpr(expr);
            }
        }
        else {
            content.setValueExpr((QueryValueExpression) result);
        }
    }

    public Object getLeft() {
        if (searchPred != null) {

            return searchConHelper.getLeftFromPredicate(searchPred);
        }
        return null;
    }

    protected String getOperator() {

        return searchConHelper.getPredicateOperator(searchPred);
    }

    public Object getRight() {
        if (searchPred != null) {

            return searchConHelper.getRightFromPredicate(searchPred);
        }
        return null;
    }

    protected String getAndOr() {
        String value = ""; // Default the operator value to blank

        if (searchPred.getCombinedLeft() != null) {
            value = searchPred.getCombinedLeft().getCombinedOperator().toString();
        }
        else {
            SearchConditionCombined scg = searchPred.getCombinedRight();

            if (scg != null) {
                //
                // Go up one level. If exist, return its operator value
                //
                SearchConditionCombined target = scg.getCombinedLeft();
                if (target != null) {
                    value = target.getCombinedOperator().toString();
                }
            }
        }
        return value;
    }

    public Object getResult() {
        if (content != null) {
            return content.getValueExpr();
        }
        return null;
    }

    public void deleteSearchCondition() {    	
    	if (content != null) {
    		sqlCaseSearchWhenClause.getSearchContentList().remove(content);
    		content = null;
    		if (sqlCaseSearchWhenClause.getSearchContentList().size() == 0 ){
    			initializeSearchContent();
    		}
    	}    
    }

    public String getColumnText(int columnIndex) {
        if (columnIndex == 0) {
            if (firstClause) {
                return "WHEN";
            }

            return "....";
        }
        else if (columnIndex == 1) {
            if (searchPred != null) {
                left = getLeft();
            }
            if (left != null) {
                if (left instanceof QueryValueExpression)
                    return ((QueryValueExpression) left).getSQL();

                return left.toString();
            }
        }
        else if (columnIndex == 2) {
            if (searchPred != null) {
                operator = getOperator();
            }
            return operator;
        }
        else if (columnIndex == 3) {
            if (searchPred != null) {
                right = getRight();
            }
            if (right != null) {
                if (right instanceof QueryValueExpression)
                    return ((QueryValueExpression) right).getSQL();
                
                return right.toString();
            }
        }
        else if (columnIndex == 4) {
            if (searchPred != null) {
                andOr = getAndOr();
            }
            return andOr;
        }
        else if (columnIndex == 5) {
            if (searchPred != null) {
                result = getResult();
            }
            if (result != null) {
                if (firstClause) {
                    //return result.toString();
                    if (result instanceof QueryValueExpression)
                        return ((QueryValueExpression) result).getSQL();
                    
                    return result.toString();
                }

                return "....";
            }
        }

        return "";
    }
}