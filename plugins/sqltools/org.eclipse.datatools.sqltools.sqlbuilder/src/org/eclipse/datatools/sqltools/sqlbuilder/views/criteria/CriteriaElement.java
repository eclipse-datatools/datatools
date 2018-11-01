/*******************************************************************************
 * Copyright © 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.criteria;

import java.lang.ref.WeakReference;
import java.util.Vector;

import org.eclipse.swt.widgets.Display;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLStringHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SearchConditionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;

/**
 * CritieriaElement provides the IElement for the critera grid view.
 * It is applicable for select, update, and delete statements
 */
public class CriteriaElement {

    protected SQLQueryObject statement;
    protected QuerySearchCondition searchCon;
    protected Predicate searchPred;
    protected boolean isHaving = false;

    // The Criteria Grid columns
    protected QueryValueExpression column;
    protected String operator = "";
    protected Object value;
    protected String andOr = "";
    protected SQLDomainModel domainModel;
    private boolean firstClause;
    private SearchConditionHelper searchConHelper;
    private WeakReference criteriaElementVectorRef;

    /**
     * @param target - a select, update or delete SQL statement
     * @param whereClause - the where clause part of the above statement
     * @param predicate - the predicate the made up the table row
     * @param provide - the content provider that creates this element
     */
    public CriteriaElement(SQLDomainModel domainModel, Object target, Object clause, 
            Predicate predicate, boolean isHaving, boolean firstClause) {
        this.domainModel = domainModel;
        statement = (SQLQueryObject) target;
        searchCon = (QuerySearchCondition) clause;
        this.isHaving = isHaving;
        this.searchPred = predicate;
        this.firstClause = firstClause; // this is for first row of the Where clause UI
        searchConHelper = new SearchConditionHelper(domainModel);
        searchConHelper.setHavingClause(isHaving);
    }

    public boolean getFirstClause() {
        return firstClause;
    }

    /**
     * Return the lists of SQLPredicates that made up the search conditon
     */
    public Predicate getCurrentPredicate() {
        return searchPred;
    }

    /**
     * Return the lists of SQLPredicates that made up the search conditon
     */
    public SearchConditionHelper getSearchConditionHelper() {
        return searchConHelper;
    }

    /**
     * Return the current statement (either select, update, or delete)
     */
    public SQLQueryObject getSQLStatement() {
        return statement;
    }

    /**
     * Return the Search Condition for the current statement
     */
    public QuerySearchCondition getSearchCondition() {
        return searchCon;
    }

    /**
     * Return the having clause for the current statement
     */
    /*
     public QuerySearchCondition getHavingClause()
     {
     return searchCon;
     }
     */

    /**
     * Set the mof object from the grid value
     */
    public void setElementProperty(Object key, Object propValue) {
        if (propValue == null) {
            return;
        }

        boolean changed = false;

        if (key == CriteriaGridViewer.P_STATEMENT_COLUMN) {
            if (propValue instanceof String
                    && (((String) propValue).equals(SQLBuilderConstants.P_BUILD_EXPRESSION)
                            || ((String) propValue).equals(SQLBuilderConstants.P_EDIT_EXPRESSION) || ((String) propValue)
                            .equals(SQLBuilderConstants.P_REPLACE_EXPRESSION))) {
                QueryValueExpression expr = showExpressionBuilder(key, false, (String) propValue);
                if (expr != null) {
                    column = expr;
                }
            }
            else if (propValue instanceof String && ((String) propValue).trim().equals("")) {
                column = null;
            }
            else {
                if (propValue instanceof QueryValueExpression) {
                    if (propValue instanceof ValueExpressionColumn) {
                        column = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn) propValue);
                    }
                    else {
                        column = (QueryValueExpression) propValue;
                    }
                }
            }

/*            if (searchPred == null) {
                changed = createSearchCondition();
            }
            else {
                changed = updateColumn();
            }
*/          if(column != null) {
				changed = updateColumn();
			}
        }
        else if (key == CriteriaGridViewer.P_STATEMENT_OPERATOR) {
            // Update the operator only if there is a search condition
			//  created by selecting a column in the first row of the grid
			if (searchCon != null) {
				if (propValue instanceof String) {
					operator = (String) propValue;
					/*
					 * if (searchPred == null) { changed =
					 * createSearchCondition(); } else { changed =
				cascadeuser
					 * updateOperator((String) propValue); }
					 */
					changed = updateOperator((String) propValue);
				}
			}
			// if operator is EXISTS then we need to create a search condition
        	// because it does not require a left predicate
        	else if (propValue instanceof String && 
        			(SQLBuilderConstants.P_OPERATOR_EXISTS.equals(propValue) ||
        			 SQLBuilderConstants.P_OPERATOR_XMLEXISTS.equals(propValue))) {        		
        		createSearchCondition();       	
        		changed = searchConHelper.setOperatorInPredicate(statement, StatementHelper.getSearchCondition(statement),
        				searchPred, (String) propValue);        		       		
        	}
        }
        else if (key == CriteriaGridViewer.P_STATEMENT_VALUE) {
            // Update the value only if there is a search condition
            //  created by selecting a column in the first row of the grid
            if(searchCon != null){
	        	if (propValue instanceof String
						&& (((String) propValue)
								.equals(SQLBuilderConstants.P_BUILD_EXPRESSION)
								|| ((String) propValue)
										.equals(SQLBuilderConstants.P_EDIT_EXPRESSION) || ((String) propValue)
								.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION))) {
					QueryValueExpression expr = showExpressionBuilder(key,
							false, (String) propValue);
					if (expr != null) {
						value = expr.getSQL(); // value should contain string
					}
				} else if (propValue instanceof String
						&& ((String) propValue).trim().equals("")) {
					value = null;
				} else {
					if (propValue instanceof QueryValueExpression) {
						value = ((QueryValueExpression) propValue).getSQL(); // value
																			 // should
																			 // contain
																			 // string
					}
				}

				/*
				 * if (searchPred == null) { changed = createSearchCondition(); }
				 * else { changed = updateValue(); }
				 */
	        	if (searchPred instanceof XMLPredicateExists)
	        		value = " (" + value + " )";
				changed = updateValue();
			}
        }
        else if (key == CriteriaGridViewer.P_STATEMENT_ANDOR) {
			if (propValue instanceof String) {
				if (searchCon != null) {
					if (!((String) propValue).equals("")) {
						andOr = (String) propValue;
						changed = setAndOr((String) propValue);
					} 
					else // removing AND/OR
					{

						Vector criteriaElements = (Vector) this
								.getCriteriaElementVectorRef().get();
						int currIdx = criteriaElements.indexOf(this);
						CriteriaElement tempCriteriaElement = null;
						if (criteriaElements.size() > (currIdx + 1)) {
							tempCriteriaElement = (CriteriaElement) criteriaElements
									.get(currIdx + 1);
						}
						if (tempCriteriaElement != null) {
							if ((tempCriteriaElement.getColumn() == null || tempCriteriaElement
									.getColumn().getSQL().equalsIgnoreCase(
											"NULL"))
									&& (tempCriteriaElement.getValue().equals(
											"") || tempCriteriaElement
											.getValue()
											.equalsIgnoreCase("NULL"))
									&& (tempCriteriaElement.getOperator()
											.equals("") || tempCriteriaElement
											.getOperator().equals("="))) {

								Predicate nextPred = tempCriteriaElement
										.getCurrentPredicate();
								searchConHelper.removePredicateFromCondition(
										nextPred, searchCon, statement);
								changed = true;
							}
						}
					}
				}
			}
		}
        //TODO: This is a hack to get notifications to work.  Need to figure out how to remove.
        if (changed) {            
            if (statement instanceof QueryUpdateStatement) {
                ((QueryUpdateStatement) statement).setWhereClause(((QueryUpdateStatement) statement).getWhereClause());
            }
            if (statement instanceof QueryDeleteStatement) {
                ((QueryDeleteStatement) statement).setWhereClause(((QueryDeleteStatement) statement).getWhereClause());
            }
            if (statement instanceof QuerySelectStatement) {
                ((QuerySelectStatement) statement).setQueryExpr(((QuerySelectStatement) statement).getQueryExpr());
            }
            else if (statement instanceof QuerySelect) {
                SelectHelper.refresh(statement);
            }            
        }

    }

    /**
     * Get the mof value and return it
     */
    public String getColumnText(int columnIndex) {
        if (columnIndex == 0) {
            if (searchPred != null) {
                column = getColumn();
            }
            if (column != null) {
                String result = SQLStringHelper.trimBlanks(column.getSQL());
                return result;
            }
        }
        else if (columnIndex == 1) {
            if (searchPred != null) {
                operator = getOperator();
            }
            return operator;
        }
        else if (columnIndex == 2) {
            if (searchPred != null) {
                value = getValue();
            }
            if (value != null) {
                String result = SQLStringHelper.trimBlanks(value.toString());
                return result;
            }
        }
        else if (columnIndex == 3) {
            if (searchPred != null) {
                andOr = getAndOr();
            }
            return andOr;
        }

        return "";
    }    

    /**
     * Create a new Predicate in the MOF model
     * If not all the values are specified, provide the defaults to create
     * the SQLPredicate object
     */
    protected boolean createSearchCondition() {
        boolean created = false;

        if (searchPred == null) {
            if (searchCon == null) {
                QuerySearchCondition newSearchCon = searchConHelper.buildSearchCondition(statement, 
                        null, column, (String) value, operator);
                if (newSearchCon != null) {
                    if (!isHaving) {
                        StatementHelper.setWhereClauseForStatement(newSearchCon, statement);
                    }
                    else {
                        StatementHelper.setHavingClauseForStatement(newSearchCon, statement);
                    }
                    searchPred = (Predicate) newSearchCon;
                    created = true;
                }
            }
            else {
                QuerySearchCondition newSearchCon = searchConHelper.buildSearchCondition(statement, 
                        searchCon, column, (String) value, operator);
                if (newSearchCon != null) {
                    searchPred = (Predicate) newSearchCon.getCombinedRight();
                    created = true;
                }
            }            
        }

        return created;
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

    private boolean isColumnNeeded() {
        if (operator.equals("EXISTS")) {
            return false;
        }
        else if (operator.equals("XMLEXISTS"))  //$NON-NLS-1$        	
        	return false;
        return true;
    }

    /**
     * Return the "column" from the grid
     * This corresponds to the left hand side of the predicate
     */
    public QueryValueExpression getColumn() {
        return searchConHelper.getLeftFromPredicate(searchPred);
    }

    /**
     * Return the "value" from the grid
     * This corresponds to the right hand side of the predicate
     */
    public String getValue() {
        return searchConHelper.getRightFromPredicate(searchPred);
    }

    /**
     * Return the comparison operator
     */
    public String getOperator() {
        if (searchPred != null) {
            return searchConHelper.getPredicateOperator(searchPred);
        }
        
        return null;
    }

    /**
     * If the current predicate is contained in a SearchConditionCombined,
     * get the group operator. Because it is a binary tree, traverse the
     * left branch to get the operator value
     */
    public String getAndOr() {
        String opValue = ""; // Default the operator value to blank

        if (searchPred.getCombinedLeft() != null) {
            SearchConditionCombined scg = searchPred.getCombinedLeft();
            if (scg != null) {
                //
                // Go up one level. If exist, return its operator value
                //
                //      	SearchConditionCombined target = scg.getCombinedLeft();
                //        if (target != null)
                //        {
                //          value = target.getCombinedOperator().toString();
                //        }
                opValue = scg.getCombinedOperator().toString();
            }
        }
        else {
            SearchConditionCombined scg = searchPred.getCombinedRight();
            if (scg != null) {
                //
                // Go up one level. If exist, return its operator value
                //
                SearchConditionCombined target = scg.getCombinedLeft();
                if (target != null) {
                    opValue = target.getCombinedOperator().toString();
                }
            }
        }

        return opValue;
    }

    /**
     * Update the left hand side of the predicate
     */
    protected boolean updateColumn() {
        boolean updated = false;
        if(column != null){
        	if(searchCon == null ){
        		createSearchCondition();
        	}
	        QueryValueExpression expr = ExpressionHelper.createExpression(column);
	        updated = searchConHelper.setLeftInPredicate(searchPred, expr);        
    	}
        return updated;
    }

    /**
     * Update the comparison operator
     */
    protected boolean updateOperator(String newValue) {        
        if(searchCon != null){
	    	searchConHelper.setOperatorInPredicate(statement, searchCon, searchPred, newValue);
	
	        if (!isValueNeeded()) {
	            value = null;
	            // the call to searchConHelper.setOperatorInPredicate(...)
	            // already accomplished the same thing as updateValue().
	            // In addition, the previous call modifies searchCon making 
	            // a call to updateValue() will produce incorrect results
	            // RATLC01124939 <QVO> <2006-08-23>
	            //updateValue(); // clear the value
	        }
	        else if (!isColumnNeeded()) {
	            column = null;
	            updateColumn(); // clear the column
	        }
        }
        return true;
    }

    /**
     * Update the right hand side of the predicate
     */
    protected boolean updateValue() {        
        boolean updated = true;
        String newRight = "";
        QueryValueExpression newLeft = null;
        Predicate newPredicate = null;
        if (value != null && !value.equals("") ) {
            newPredicate = searchConHelper.buildNewPredicate(statement, column, value.toString(), operator);
            if (newPredicate == null) {
                newLeft = searchConHelper.getDefaultLeft(operator);
                newPredicate = searchConHelper.buildNewPredicate(statement, newLeft, value.toString(), operator);
            }
        }
        if (newPredicate == null) {
            newRight = searchConHelper.getDefaultRight(operator);
            newPredicate = searchConHelper.buildNewPredicate(statement, column, newRight, operator);
        }
        if (newPredicate == null) {
            newPredicate = searchConHelper.buildNewPredicate(statement, newLeft, newRight, operator);
        }
        if (newPredicate != null) {
            searchConHelper.replacePredicate(statement, searchCon, searchPred, newPredicate);
            searchPred = newPredicate;
            updated = true;
        }
        else {
            updated = false;
        }
        return updated;
    }
    
    public boolean setAndOr(String andOrString) {
        QuerySearchCondition tmpSearchCon = 
            searchConHelper.setAndOrInSearchCondition(statement, searchCon, searchPred, andOrString);
        if (tmpSearchCon != searchCon) {
            if (!isHaving)
                StatementHelper.setWhereClauseForStatement(tmpSearchCon, statement);
            else
                StatementHelper.setHavingClauseForStatement(tmpSearchCon, statement);
        }
        return true;        
    }

    /**
     * Launch the expression builder
     */
    public QueryValueExpression showExpressionBuilder(boolean isColumn, String sProperty) {
        ExpressionBuilderWizard wizard;

        wizard = new ExpressionBuilderWizard(domainModel, statement);

        if (sProperty.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || sProperty.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {
            wizard.setInputExpression(null);
        }
        else if (sProperty.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)) {
            if (searchPred != null) {
                QueryValueExpression expr = ExpressionHelper.createExpression(searchConHelper.getRightFromPredicate(searchPred));
                wizard.setInputExpression(expr);
            }
            else // The left expression hasn't been created yet so return null and
            { // don't launch the expression builder
                return null;
            }
        }

        wizard.setIsColumn(isColumn);
        ExpressionBuilderDialog dialog = new ExpressionBuilderDialog(Display.getDefault().getActiveShell(), wizard);
        dialog.create();
        dialog.setBlockOnOpen(true);
        int result = dialog.open();

        if (result == 0) {
            return wizard.getSQLExpression();
        }
        return null;
    }

    /**
     * Launch the expression builder
     */
    public QueryValueExpression showExpressionBuilder(Object key, boolean isColumn, String sProperty) {
        ExpressionBuilderWizard wizard;

        wizard = new ExpressionBuilderWizard(domainModel, statement);
        if ((key == CriteriaGridViewer.P_STATEMENT_COLUMN)
                || (value instanceof QueryValueExpression && key == CriteriaGridViewer.P_STATEMENT_VALUE)) {
            if (sProperty.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || 
                    sProperty.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {
                wizard.setInputExpression(null);
            }
            else if (sProperty.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)) {
                if (key == CriteriaGridViewer.P_STATEMENT_COLUMN) {
                    if (column != null) {
                        wizard.setInputExpression(column); // searchCondition.getLeft());
                    }
                }
                else if (key == CriteriaGridViewer.P_STATEMENT_VALUE) {
                    if (value != null) {
                        wizard.setInputExpression((QueryValueExpression) value); // searchCondition.getRight());
                    }
                }
                else {
                    wizard.setInputExpression(null);
                }
            }
        }

        wizard.setIsColumn(isColumn);
        ExpressionBuilderDialog dialog = new ExpressionBuilderDialog(Display.getDefault().getActiveShell(), wizard);
        dialog.create();
        dialog.setBlockOnOpen(true);
        int result = dialog.open();
        if (result == 0) {
            return wizard.getSQLExpression();
        }

        return null;
    }

    /**
     * @return Returns the criteriaElementVectorRef.
     */
    public WeakReference getCriteriaElementVectorRef() {
        return criteriaElementVectorRef;
    }

    /**
     * @param criteriaElementVectorRef The criteriaElementVectorRef to set.
     */
    public void setCriteriaElementVectorRef(WeakReference criteriaElementVectorRef) {
        this.criteriaElementVectorRef = criteriaElementVectorRef;
    }
}
