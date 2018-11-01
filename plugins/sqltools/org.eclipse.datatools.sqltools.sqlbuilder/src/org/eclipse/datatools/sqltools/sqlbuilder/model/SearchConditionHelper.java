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
package org.eclipse.datatools.sqltools.sqlbuilder.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator;
import org.eclipse.datatools.modelbase.sql.query.PredicateExists;
import org.eclipse.datatools.modelbase.sql.query.PredicateIn;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPredicateExists;

/**
 * This class provides "helper" functions for building and manipulating query search conditions. 
 * 
 * The Query Builder builds search conditions in a particular way.  Conditions
 * are structured as a tree of Predicate and SearchConditionCombined objects that 
 * "grows" from the bottom left as new conditions are added to the condition grid.  
 * That is, new Predicate and SearchConditionCombined objects are added to the right,
 * creating a new "root" SearchConditionCombined as each predicate is added.
 * 
 * Let's say you have a single condition in the Conditions grid in the UI. This 
 * condition is represented in the model as a single Predicate object, call it P1.  
 * When the user adds a new condition as the 2nd row of the Condition grid, another
 * Predicate, P2, is created, and P1 and P2 are combined using a SearchConditionCombined
 * (call it SCC1), like this:
 *    
 *       SCC1  
 *       /  \  
 *     P1    P2
 * 
 * When another condition is added in the third row of the Conditions grid, the model
 * is modified like this:
 * 
 *         SCC2 
 *         /  \  
 *       SCC1   P3 
 *       /  \  
 *     P1    P2 
 * 
 * Adding a fourth condition results in the following tree:
 *         
 *           SCC3
 *           /  \ 
 *         SCC2  P4
 *         /  \  
 *       SCC1  P3 
 *       /  \  
 *     P1    P2
 * 
 * And so on.
 * 
 * Navigating the search condition tree in the code can be confusing.  The model
 * provides calls to get the left or right parent SearchConditionCombined (if any) 
 * of a node, and to get the left or right child condition (if any) of a 
 * SearchConditionCombined.  (The child can be a Predicate or a SearchConditionCombined.)
 * 
 * Considering a node in the tree (the node can be either a Predicate or a 
 * SearchConditionCombined), the other nodes in relation to it are as follows: 
 * 
 *  combinedRight      combinedLeft
 *               \    /
 *                node
 *               /    \
 *   leftCondition      rightCondition
 * 
 * The methods in the model for navigating the tree are named accordingly:
 *   getCombinedRight  - get the SearchConditionCombined for which this node is the right child
 *   getCombinedLeft   - get the SearchConditionCombined for which this node is the left child
 *   getLeftCondition  - get the condition (Predicate or SearchConditionCombined) that is
 *     the left child of this node (which must be a SearchConditionCombined)
 *   getRightCondition - get the condition (Predicate or SearchConditionCombined) that is
 *     the right child of this node (which must be a SearchConditionCombined)
 * 
 * Notes: 
 * 1. For any node, getCombinedLeft and getCombinedRight cannot both return non-null.
 *    (Both may return null, which indicates the node is the root of the condition tree.) 
 * 2. In the trees built by the Query Builder, getRightCondition will always return a
 *    Predicate, never a SearchConditionCombined.   
 */
public class SearchConditionHelper {

    SQLDomainModel domainModel;
    ExpressionHelper eHelper = new ExpressionHelper();
    boolean havingClause = false;    
    public static final String operators[] = { "=", "<", "<=", ">", ">=", "<>", "BETWEEN", "NOT BETWEEN", "IS NULL", "IS NOT NULL", "LIKE", "NOT LIKE", "IN",
            "NOT IN", "EXISTS", "XMLEXISTS" };

    /**
     * Constructs an instance of this class, with the given domain model object.
     * @param domainModel the query builder domain model to use
     */
    public SearchConditionHelper(SQLDomainModel domainModel) {
        this.domainModel = domainModel;        
    }

    /**
     * Builds the Predicate for the given comparison kind.
     * @param currStmt the SQL statement for which new predicate will be part of
     * @param left the QueryValueExpression represents left side of the predicate
     * @param right the String value for right side of the predicate
     * @param comparisonKind String value that indicates what type of Predicate it is going to be 
     * @return the built predicate
     */
    public Predicate buildNewPredicate(SQLQueryObject currStmt, QueryValueExpression left, String right, String comparisonKind) {
        Predicate pred = null;
        if (comparisonKind.trim().length() == 0) {
            pred = buildEmptyPredicate();
            ((PredicateBasic) pred).setLeftValueExpr(ExpressionHelper.createExpression(left));
            ((PredicateBasic) pred).setRightValueExpr(ExpressionHelper.createExpression(right));
        }
        else {
            String fromClause = TableHelper.createFromClauseForStatement(currStmt);
            String leftStr = "";
            String rightStr = "";
            if (left != null) {
                leftStr = left.getSQL();
            }
            if (right != null) {
                rightStr = right;
            }
            else {
                rightStr = "";
            }

            if (fromClause.length() > 0) {
                String stmtString = "Select 1 " + fromClause + " where " + leftStr + " " + comparisonKind + " " + rightStr;
                Predicate newPred = null;
                try {
                    QuerySelectStatement newStmt = (QuerySelectStatement) domainModel.parse(stmtString);
                    QueryExpressionRoot queryExprRoot = newStmt.getQueryExpr();
                    QueryExpressionBody queryExprBody = queryExprRoot.getQuery();
                    QuerySelect qSelect = (QuerySelect) queryExprBody;
                    List tableRefList = StatementHelper.getTablesForStatement(currStmt);
                    eHelper.resolveColumnReferencesInTemporaryStatement(qSelect, tableRefList);
                    newPred = (Predicate) qSelect.getWhereClause();
                }
                catch (Exception exp) {
                    newPred = null;
                    //SQLBuilderPlugin.getPlugin().getLogger().writeLog(exp.getMessage());
                }
                if (newPred != null) {
                    pred = newPred;
                }
            }
        }
        return pred;
    }

    /**
     * Creates an empty predicate.
     * @return an empty predicate
     */
    public static Predicate buildEmptyPredicate() {
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        PredicateBasic sqlPredicate = factory.createPredicateBasic();
        return sqlPredicate;
    }

    /**
     * Creates a new predicate and adds it to the given search condition.
     * @param currentSearchCon the existing search condition or null 
     * @param leftExpr the left side expression of the new predicate
     * @param rightExpr the right side expression of the new predicate
     * @param oper the operator string (eg: "=" )  
     * @return the new search condition object
     */
    public static QuerySearchCondition buildSearchCondition(QuerySearchCondition currentSearchCon, QueryValueExpression leftExpr,
            QueryValueExpression rightExpr, String oper) {
        QuerySearchCondition newCondition = null;
        PredicateBasic pred = buildPredicateBasic(leftExpr, rightExpr, oper);
        if (currentSearchCon == null) {
            newCondition = pred;
        }
        else {
            SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
            SearchConditionCombined combined = factory.createSearchConditionCombined();
            combined.setLeftCondition(currentSearchCon);
            combined.setRightCondition(pred);
            combined.setCombinedOperator(SearchConditionCombinedOperator.get("AND"));
            newCondition = combined;
        }

        return newCondition;
    }

    /**
     * Creates a PredicateBasic object using the given expressions and operator
     * @param leftExpr the left side expression
     * @param rightExpr the right side expression
     * @param oper the operator
     * @return the new PredicateBasic object
     */
    public static PredicateBasic buildPredicateBasic(QueryValueExpression leftExpr, QueryValueExpression rightExpr, String oper) {
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        PredicateBasic pred = factory.createPredicateBasic();
        pred.setLeftValueExpr(leftExpr);
        pred.setRightValueExpr(rightExpr);
        String operLiteral = SearchConditionHelper.getComparisonLiteralFromSymbol(oper);
        PredicateComparisonOperator operEnum = PredicateComparisonOperator.get(operLiteral);
        pred.setComparisonOperator(operEnum);
        return pred;
    }

    /**
     * Creates a new predicate with the given values and append it to the give search condition.
     * @param currentSearchCon the current QuerySearchCondition, null is passed if building search condition first time.
     * @param left  the QueryValueExpression represents left side of the predicate
     * @param right  the String value for right side of the predicate
     * @param comparisonKind String value that indicates what type of Predicate needs to build
     * @return the new search condition
     */
    public QuerySearchCondition buildSearchCondition(SQLQueryObject currStmt, QuerySearchCondition currentSearchCon, QueryValueExpression left, String right,
            String comparisonKind) {
        QuerySearchCondition newSearchCon = null;
        Predicate predicate = buildNewPredicate(currStmt, left, right, comparisonKind);
        if (currentSearchCon == null) {
            newSearchCon = predicate;
        }
        else {
            SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
            SearchConditionCombined sqlGroup = factory.createSearchConditionCombined();
            sqlGroup.setLeftCondition(currentSearchCon);
            sqlGroup.setRightCondition(predicate);
            sqlGroup.setCombinedOperator(SearchConditionCombinedOperator.get("AND")); // Default.  Remove if adding row is via selecting and/or
            newSearchCon = sqlGroup;
        }
        return newSearchCon;
    }

    /**
     * Removes the given predicate from the search condition.
     * @param pred the Predicate which needs to be removed from the condition
     * @param searchCon the SearchCondition from which predicate needs to be removed
     */
    public static QuerySearchCondition removePredicateFromCondition(Predicate pred, QuerySearchCondition searchCon) {
        //  Refer to top of this class to see the tree representation

        // Case : if only node  then searchCon = new pred
        if (pred.getCombinedRight() == null && pred.getCombinedLeft() == null) {
            searchCon = null;
        }
        else if (pred.getCombinedRight() != null && pred.getCombinedRight().getCombinedLeft() == null) {
            // Case : any right most (last) node then searchCon points to left (previous) sibling (Combine/pred) 
            searchCon = pred.getCombinedRight().getLeftCondition();
            if (searchCon != null) {
                searchCon.setCombinedLeft(null);
            }
        }
        else if (pred.getCombinedLeft() != null) {
            // Case : any left most (first) node then if more than 2 nodes then parent combined replaces with right sibling  
            //                           otherwise  searchCon points to right sibling (pred)
            if (pred.getCombinedLeft().getCombinedLeft() != null) {
                pred.getCombinedLeft().getCombinedLeft().setLeftCondition(pred.getCombinedLeft().getRightCondition());
            }
            else {
                searchCon = pred.getCombinedLeft().getRightCondition();
            }
        }
        else if (pred.getCombinedRight() != null && pred.getCombinedRight().getCombinedLeft() != null) {
            // Case : any middle node then assign right (next) pred to parent and assignt parent's
            //                     left combine to parent's parent's combine left
            //                     if second from right then searchCon points to parent

            SearchConditionCombined currentGroup = pred.getCombinedRight();
            pred.getCombinedRight().setRightCondition(pred.getCombinedRight().getCombinedLeft().getRightCondition());
            currentGroup.setCombinedLeft(currentGroup.getCombinedLeft().getCombinedLeft());
            if (currentGroup.getCombinedLeft() == null) {
                searchCon = currentGroup;
            }
        }
        return searchCon;
    }

    /**
     * Removes the given predicate from the search condition.
     * @param pred the Predicate which needs to be removed from the condition
     * @param searchCon the SearchCondition from which predicate needs to be removed
     * @param currStmt the statement containing the search condition
     */
    public void removePredicateFromCondition(Predicate pred, QuerySearchCondition searchCon, SQLQueryObject currStmt) {
        QuerySearchCondition newSearchCon = removePredicateFromCondition(pred, searchCon);
        // If pred was the only predicate in searchCon, the  above call will remove pred , and set searchCon to null
        // Hence add an empty predicate to searchCon
        replaceSearchCondition(currStmt, newSearchCon);
    }

    /**
     * Returns the left value for the given predicate
     * @param pred the predicate for which left expression needs to be returned
     * @return the left expression 
     */
    public QueryValueExpression getLeftFromPredicate(Predicate pred) {
        QueryValueExpression valExpr = null;
        if (pred instanceof PredicateBasic) {
            valExpr = ((PredicateBasic) pred).getLeftValueExpr();
        }
        else if (pred instanceof PredicateBetween) {
            valExpr = ((PredicateBetween) pred).getLeftValueExpr();
        }
        else if (pred instanceof PredicateIsNull) {
            valExpr = ((PredicateIsNull) pred).getValueExpr();
        }
        else if (pred instanceof PredicateLike) {
            valExpr = ((PredicateLike) pred).getMatchingValueExpr();
        }
        else if (pred instanceof PredicateIn) {
            if (pred instanceof PredicateInValueList) {
                valExpr = ((PredicateInValueList) pred).getValueExpr();
            }
        }
        else if (pred instanceof PredicateExists) {
            // An EXISTS predicate does not have left side
        }
        else if (pred instanceof XMLPredicateExists) {
            // An XMLEXISTS predicate does not have left side
        }        
        return valExpr;
    }

    /**
     * Returns the right value for the given predicate
     * @param pred the predicate for which right expression needs to be returned
     * @return the string value for right side expression 
     */
    public String getRightFromPredicate(Predicate pred) {
        String retVal = "";
        if (pred instanceof PredicateBasic) {
            QueryValueExpression valExpr = ((PredicateBasic) pred).getRightValueExpr();
            if (valExpr != null) {
                retVal = valExpr.getSQL();
            }
        }
        else if (pred instanceof PredicateBetween) {
            QueryValueExpression valExpr1 = ((PredicateBetween) pred).getRightValueExpr1();
            QueryValueExpression valExpr2 = ((PredicateBetween) pred).getRightValueExpr2();
            String rightVal1 = "";
            String rightVal2 = "";
            if (valExpr1 != null) {
                rightVal1 = valExpr1.getSQL();
            }
            if (valExpr2 != null) {
                rightVal2 = valExpr2.getSQL();
            }
            if (rightVal1.trim().length() > 0) {
                if (rightVal2.trim().length() > 0) {
                    retVal = rightVal1.trim().concat(" AND ").concat(rightVal2.trim());
                }
                else {
                    retVal = rightVal1.trim();
                }
            }
            else {
                retVal = rightVal2.trim();
            }
        }
        else if (pred instanceof PredicateIsNull) {
            //PredicateIsNull does not have a left side 
        }
        else if (pred instanceof PredicateLike) {
            QueryValueExpression valueExpr = ((PredicateLike) pred).getPatternValueExpr();
            if (valueExpr != null) {
                retVal = valueExpr.getSQL();
            }
            QueryValueExpression escValueExpr = ((PredicateLike) pred).getEscapeValueExpr();
            if (escValueExpr != null) {
                retVal = " ESCAPE " + escValueExpr.getSQL();
            }

        }
        else if (pred instanceof PredicateIn) {
            if (pred instanceof PredicateInValueList) {
                List inValueList = ((PredicateInValueList) pred).getValueExprList();
                String vals = "";
                for (Iterator valItr = inValueList.iterator(); valItr.hasNext();) {
                    if (vals.trim().length() > 0) {
                        vals = vals.concat(",");
                    }
                    QueryValueExpression inValExpr = (QueryValueExpression) valItr.next();
                    String inValSQL = inValExpr.getSQL();
                    vals = vals.concat(inValSQL);
                }
                if (vals.trim().length() > 0) {
                    retVal = "(" + vals + ")";
                }
            }
        }
        else if (pred instanceof PredicateExists) {
            retVal = ((PredicateExists) pred).getQueryExpr().getSQL();
            QueryExpressionBody exprBody = ((PredicateExists) pred).getQueryExpr();
            if (exprBody != null) {
                retVal = exprBody.getSQL();
            }
            /* The generated SQL includes newlines, which look ugly in the Criteria grid.  
             * So remove them. */
            retVal = retVal.replaceAll("\n", "");
            /* The SQL subquery needs parens around it in order to be parseable again in the
             * Criteria grid context.  So add them. */
            retVal = "(" + retVal + ")";
        }
        else if (pred instanceof XMLPredicateExists) {
            retVal = ((XMLPredicateExists) pred).getXqueryExpr().getXqueryExprContent();          
        }
        return retVal;
    }

    /**
     * Returns the operator for the given predicate
     * @param pred the predicate for which operator needs to be returned
     * @return the string value for the operator  
     */
    public String getPredicateOperator(Predicate pred) {
        String retVal = "";
        if (pred instanceof PredicateBasic) {
            String tempOpr = ((PredicateBasic) pred).getComparisonOperator().getName();
            retVal = SearchConditionHelper.getComparisonSymbolFromLiteral(tempOpr);
        }
        else if (pred instanceof PredicateBetween) {
            if (((PredicateBetween) pred).isNotBetween()) {
                retVal = "NOT BETWEEN";
            }
            else {
                retVal = "BETWEEN";
            }
        }
        else if (pred instanceof PredicateIsNull) {
            if (((PredicateIsNull) pred).isNotNull()) {
                retVal = "IS NOT NULL";
            }
            else {
                retVal = "IS NULL";
            }
        }
        else if (pred instanceof PredicateLike) {
            if (((PredicateLike) pred).isNotLike()) {
                retVal = "NOT LIKE";
            }
            else {
                retVal = "LIKE";
            }
        }
        else if (pred instanceof PredicateIn) {
            if (((PredicateIn) pred).isNotIn()) {
                retVal = "NOT IN";
            }
            else {
                retVal = "IN";
            }
        }
        else if (pred instanceof PredicateExists) {
            retVal = "EXISTS";
        }
        else if (pred instanceof XMLPredicateExists) {
            retVal = "XMLEXISTS";
        }


        return retVal;
    }

    /**
     * Returns the ComparisonOperator literal from the given symbol.
     * @param compKind the String symbol for which Comparison Literal is needed 
     * @return string literal name
     */
    private static String getComparisonLiteralFromSymbol(String compKind) {
        String retVal = "";
        if (compKind.equalsIgnoreCase("=")) {
            retVal = "EQUAL";
        }
        else if (compKind.equalsIgnoreCase("<")) {
            retVal = "LESS_THAN";
        }
        else if (compKind.equalsIgnoreCase("<=")) {
            retVal = "LESS_THAN_OR_EQUAL";
        }
        else if (compKind.equalsIgnoreCase(">")) {
            retVal = "GREATER_THAN";
        }
        else if (compKind.equalsIgnoreCase(">=")) {
            retVal = "GREATER_THAN_OR_EQUAL";
        }
        else if (compKind.equalsIgnoreCase("<>")) {
            retVal = "NOT_EQUAL";
        }
        return retVal;
    }

    /**
     * Returns the ComparisonOperator symbol from the given ComparisonOperator literal.
     * @param compLit the String literal for which Comparison symbol is needed 
     * @return string symbol 
     */
    private static String getComparisonSymbolFromLiteral(String compLit) {
        String retVal = "";
        if (compLit.equalsIgnoreCase("EQUAL")) {
            retVal = "=";
        }
        else if (compLit.equalsIgnoreCase("LESS_THAN")) {
            retVal = "<";
        }
        else if (compLit.equalsIgnoreCase("LESS_THAN_OR_EQUAL")) {
            retVal = "<=";
        }
        else if (compLit.equalsIgnoreCase("GREATER_THAN")) {
            retVal = ">";
        }
        else if (compLit.equalsIgnoreCase("GREATER_THAN_OR_EQUAL")) {
            retVal = ">=";
        }
        else if (compLit.equalsIgnoreCase("NOT_EQUAL")) {
            retVal = "<>";
        }
        return retVal;
    }

    /**
     * @param condition
     * @return
     */
    public static List getAllPredicates(QuerySearchCondition condition){
    	List predicates =  new ArrayList();
        if(condition instanceof SearchConditionCombined){
	        SearchConditionCombined combinedCondition = (SearchConditionCombined)condition;	

	        QuerySearchCondition leftCondition = combinedCondition.getLeftCondition();
	        predicates.addAll(getAllPredicates(leftCondition));
	        
	        QuerySearchCondition rightCondition = combinedCondition.getRightCondition();
	        predicates.addAll(getAllPredicates(rightCondition));
        }else if(condition instanceof SearchConditionNested){
        	SearchConditionNested nestedConsition = (SearchConditionNested)condition;
        	predicates.addAll(SearchConditionHelper.getAllPredicates(nestedConsition.getNestedCondition()));
        }else if(condition instanceof Predicate){
        	predicates.add(condition);
        }
        return predicates;
    }    
        
        
        
/*        
	        // process the left side 
	        if(leftCondition instanceof Predicate){
	        	predicates.add(leftCondition);
	        }
	        else if(leftCondition instanceof SearchConditionCombined){
	        	SearchConditionCombined tempCondition = (SearchConditionCombined)leftCondition;
	        	predicates.addAll(SearchConditionHelper.getAllPredicates(tempCondition));
	        }
	        
	        // process the right side 
	        if(rightCondition instanceof Predicate){
	        	predicates.add(rightCondition);
	        }
	        else if(rightCondition instanceof SearchConditionNested){
	        	SearchConditionNested nestedConsition = (SearchConditionNested)rightCondition;
	        	predicates.addAll(SearchConditionHelper.getAllPredicates(nestedConsition.getNestedCondition()));
	        }
	        else if(rightCondition instanceof SearchConditionCombined){
	        	SearchConditionCombined tempCondition = (SearchConditionCombined)rightCondition;
	        	predicates.addAll(SearchConditionHelper.getAllPredicates(tempCondition));
	        }
        } else if (condition instanceof Search)
    	return predicates;
    }
*/    /**
     * Checks if the old and new comparison operators belong to same type of predicate.
     * @param currentOperator the String current comparison operator symbol e.g. =,<,<= etc.
     * @param newOperator the String new comparison operator symbol
     * @return true if old and new operators belongs to say type otherwise false
     */
    private boolean isCompatible(String currentOperator, String newOperator) {
        boolean retVal = false;
        String currOprType = "x"; // initialize current and new with diff value
        String newOprType = "y"; // initialize current and new with diff value
        if (currentOperator.equalsIgnoreCase("") || currentOperator.equalsIgnoreCase("=") || currentOperator.equalsIgnoreCase("<")
                || currentOperator.equalsIgnoreCase("<=") || currentOperator.equalsIgnoreCase(">") || currentOperator.equalsIgnoreCase(">=")
                || currentOperator.equalsIgnoreCase("<>")) {

            currOprType = "basic";

        }
        else if (currentOperator.equalsIgnoreCase("BETWEEN") || currentOperator.equalsIgnoreCase("NOT BETWEEN")) {

            currOprType = "between";

        }
        else if (currentOperator.equalsIgnoreCase("IS NULL") || currentOperator.equalsIgnoreCase("IS NOT NULL")) {

            currOprType = "null";

        }
        else if (currentOperator.equalsIgnoreCase("LIKE") || currentOperator.equalsIgnoreCase("NOT LIKE")) {
            currOprType = "like";

        }
        else if (currentOperator.equalsIgnoreCase("IN") || currentOperator.equalsIgnoreCase("NOT IN")) {
            currOprType = "in";

        }
        else if (currentOperator.equalsIgnoreCase("EXISTS")) {

            currOprType = "exists";

        }

        if (newOperator.equalsIgnoreCase("") || newOperator.equalsIgnoreCase("=") || newOperator.equalsIgnoreCase("<") || newOperator.equalsIgnoreCase("<=")
                || newOperator.equalsIgnoreCase(">") || newOperator.equalsIgnoreCase(">=") || newOperator.equalsIgnoreCase("<>")) {

            newOprType = "basic";

        }
        else if (newOperator.equalsIgnoreCase("BETWEEN") || newOperator.equalsIgnoreCase("NOT BETWEEN")) {

            newOprType = "between";

        }
        else if (newOperator.equalsIgnoreCase("IS NULL") || newOperator.equalsIgnoreCase("IS NOT NULL")) {

            newOprType = "null";

        }
        else if (newOperator.equalsIgnoreCase("LIKE") || newOperator.equalsIgnoreCase("NOT LIKE")) {
            newOprType = "like";

        }
        else if (newOperator.equalsIgnoreCase("IN") || newOperator.equalsIgnoreCase("NOT IN")) {
            newOprType = "in";

        }
        else if (newOperator.equalsIgnoreCase("EXISTS")) {

            newOprType = "exists";

        }

        if (currOprType.equals(newOprType)) {
            retVal = true;
        }

        return retVal;
    }

    /**
     * Sets the ValueExpression as Left value in the given predicate. Returns 
     * true if value is successfully set otherwise returns false.
     * @param pred the Predicate for which left expression needs to be set
     * @param value the QueryValueExpression needs to set for the predicate
     * @return the status for set operation.
     */
    public boolean setLeftInPredicate(Predicate pred, QueryValueExpression value) {
        boolean updated = false;
        if (pred instanceof PredicateBasic) {
            ((PredicateBasic) pred).setLeftValueExpr(value);
            updated = true;
        }
        else if (pred instanceof PredicateBetween) {
            ((PredicateBetween) pred).setLeftValueExpr(value);
            updated = true;
        }
        else if (pred instanceof PredicateIsNull) {
            ((PredicateIsNull) pred).setValueExpr(value);
            updated = true;
        }
        else if (pred instanceof PredicateLike) {
            ((PredicateLike) pred).setMatchingValueExpr(value);
            updated = true;
        }
        else if (pred instanceof PredicateIn) {
            if (pred instanceof PredicateInValueList) {
                ((PredicateInValueList) pred).setValueExpr(value);
                updated = true;
            }
        }
        else if (pred instanceof PredicateExists) {
            //	An EXISTS predicate does not have left side
        }
        else if (pred instanceof XMLPredicateExists) {
            //	An XMLEXISTS predicate does not have left side
        }
        return updated;
    }

    /**
     * Sets the given value as the operator in the given predicate. 
     * It also changes the nature of current predicate. 
     * Returns true if value is successfully set otherwise returns false.
     * @param pred the Predicate for which operator needs to be set
     * @param value the String value needs to set as operator for the predicate
     * @return the status for set operation.
     */
    public boolean setOperatorInPredicate(SQLQueryObject currStmt, QuerySearchCondition searchCon, Predicate pred, String value) {
        boolean updated = false;
        String currPredOpr = getPredicateOperator(pred);
        String newPredOpr = value.trim();
        if (isCompatible(currPredOpr, newPredOpr) == false) {
            Predicate newPred = buildNewPredicate(currStmt, getLeftFromPredicate(pred), getRightFromPredicate(pred), newPredOpr);
            String newRight = "";
            QueryValueExpression newLeft = null;
            if (newPred == null) {
                newRight = getDefaultRight(newPredOpr);
                newPred = buildNewPredicate(currStmt, getLeftFromPredicate(pred), newRight, newPredOpr);
            }
            if (newPred == null) {
                newLeft = getDefaultLeft(newPredOpr);
                newPred = buildNewPredicate(currStmt, newLeft, getRightFromPredicate(pred), newPredOpr);
            }
            if (newPred == null) {
                newPred = buildNewPredicate(currStmt, newLeft, newRight, newPredOpr);
            }
            if (newPred != null) {
                this.replacePredicate(currStmt, searchCon, pred, newPred);
                updated = true;
            }
            return updated;
        }

        if (pred instanceof PredicateBasic) {
            String compOpratorString = SearchConditionHelper.getComparisonLiteralFromSymbol(newPredOpr);
            if (compOpratorString.length() > 0) {
                PredicateComparisonOperator predOpr = PredicateComparisonOperator.get(compOpratorString);
                ((PredicateBasic) pred).setComparisonOperator(predOpr);
            }
            else {
                if (value.trim().length() == 0) {
                    ((PredicateBasic) pred).setComparisonOperator(null);
                }
            }
        }
        else if (pred instanceof PredicateBetween) {
            if (value.equalsIgnoreCase("NOT BETWEEN")) {
                ((PredicateBetween) pred).setNotBetween(true);
            }
            else {
                ((PredicateBetween) pred).setNotBetween(false);
            }
        }
        else if (pred instanceof PredicateIsNull) {
            if (value.equalsIgnoreCase("IS NOT NULL")) {
                ((PredicateIsNull) pred).setNotNull(true);
            }
            else {
                ((PredicateIsNull) pred).setNotNull(false);
            }
        }
        else if (pred instanceof PredicateLike) {
            if (value.equalsIgnoreCase("NOT LIKE")) {
                ((PredicateLike) pred).setNotLike(true);
            }
            else {
                ((PredicateLike) pred).setNotLike(false);
            }
        }
        else if (pred instanceof PredicateIn) {
            if (value.equalsIgnoreCase("NOT IN")) {
                ((PredicateIn) pred).setNotIn(true);
            }
            else {
                ((PredicateIn) pred).setNotIn(false);
            }
        }
        else if (pred instanceof PredicateExists) {
            //Nothing is needed for Exists
        }
        else if (pred instanceof XMLPredicateExists) {
            //Nothing is needed for XMLExists
        }
        return updated;

    }

    /**
     * Returns the default right side of a predicate for the given operator.
     * @param predOpr the String predicate operator for which default value is needed
     * @return the default right value
     */
    public String getDefaultRight(String predOpr) {
        String retval = "";
        if (predOpr.equalsIgnoreCase("=") || predOpr.equalsIgnoreCase("<") || predOpr.equalsIgnoreCase("<=") || predOpr.equalsIgnoreCase(">")
                || predOpr.equalsIgnoreCase(">=") || predOpr.equalsIgnoreCase("<>")) {

            retval = "null";

        }
        else if (predOpr.equalsIgnoreCase("BETWEEN") || predOpr.equalsIgnoreCase("NOT BETWEEN")) {
            //retval = "'temp1' AND 'temp2'"  ;
            retval = "null AND null";

        }
        else if (predOpr.equalsIgnoreCase("IS NULL") || predOpr.equalsIgnoreCase("IS NOT NULL")) {
            retval = null;

        }
        else if (predOpr.equalsIgnoreCase("LIKE") || predOpr.equalsIgnoreCase("NOT LIKE")) {
            retval = "'%'";
        }
        else if (predOpr.equalsIgnoreCase("IN") || predOpr.equalsIgnoreCase("NOT IN")) {
            retval = "(null,null)";
        }
        else if (predOpr.equalsIgnoreCase("EXISTS")) {
            //We might want to change it to either some generic impl. for table name 
            //or pass curr. stmt to this method and get first table to form a valid sql. 
            retval = " (SELECT * FROM temp) ";
        }
        else if (predOpr.equalsIgnoreCase("XMLEXISTS")) {
            retval = " ('<temp/>') ";
        }        
        return retval;
    }

    /**
     * Returns the default left side of a predicate for the given operator.
     * @param predOpr the String predicate operator for which default value is needed
     * @return the default left value
     */
    public QueryValueExpression getDefaultLeft(String predOpr) {
        QueryValueExpression retval = null;
        if (predOpr.equalsIgnoreCase("=") || predOpr.equalsIgnoreCase("<") || predOpr.equalsIgnoreCase("<=") || predOpr.equalsIgnoreCase(">")
                || predOpr.equalsIgnoreCase(">=") || predOpr.equalsIgnoreCase("<>")) {

            retval = ExpressionHelper.createExpression("null");

        }
        else if (predOpr.equalsIgnoreCase("BETWEEN") || predOpr.equalsIgnoreCase("NOT BETWEEN")) {
            retval = ExpressionHelper.createExpression("null");

        }
        else if (predOpr.equalsIgnoreCase("IS NULL") || predOpr.equalsIgnoreCase("IS NOT NULL")) {
            retval = ExpressionHelper.createExpression("null");

        }
        else if (predOpr.equalsIgnoreCase("LIKE") || predOpr.equalsIgnoreCase("NOT LIKE")) {

            retval = ExpressionHelper.createExpression("null");

        }
        else if (predOpr.equalsIgnoreCase("IN") || predOpr.equalsIgnoreCase("NOT IN")) {

            retval = ExpressionHelper.createExpression("null");

        }
        else if (predOpr.equalsIgnoreCase("EXISTS")) {
            retval = null;
        }
        return retval;
    }

    /**
     * Sets the And/Or for the condition, creates the new predicate if needed and returns the searchCondition with
     * new added predicate.
     * @param currStmt the current QueryStatement
     * @param searchCon the QuerySearchCondition to which predicate is added if needed
     * @param pred the current Predicate on which And/Or needs to be set
     * @param andOrValue the String And/Or value 
     * @return the updated QuerySearchCondition
     */
    public QuerySearchCondition setAndOrInSearchCondition(SQLQueryObject currStmt, QuerySearchCondition searchCon, Predicate pred, String andOrValue) {
        // if pred is the only node  -OR-  pred is right most node (last node) create new root for tree and add
        // empty node as the right most node 
        if ((pred.getCombinedRight() == null && pred.getCombinedLeft() == null)
                || (pred.getCombinedRight() != null && pred.getCombinedRight().getCombinedLeft() == null)) {
            searchCon = buildSearchCondition(currStmt, searchCon, null, null, "");
        }

        QuerySearchCondition tempSearchCon = pred;
        if (tempSearchCon.getCombinedRight() != null) {
            tempSearchCon = tempSearchCon.getCombinedRight();
        }
        tempSearchCon.getCombinedLeft().setCombinedOperator(SearchConditionCombinedOperator.get(andOrValue));
        return searchCon;
    }

    public static QuerySearchCondition replacePredicate(QuerySearchCondition searchCon, Predicate oldPred, Predicate newPred) {
        if (oldPred.getCombinedRight() == null && oldPred.getCombinedLeft() == null) {
            searchCon = newPred;
        }
        else if (oldPred.getCombinedLeft() != null) {
            oldPred.getCombinedLeft().setLeftCondition(newPred);
        }
        else if (oldPred.getCombinedRight() != null) {
            oldPred.getCombinedRight().setRightCondition(newPred);
        }
        return searchCon;
    }

    /**
     * Replaces the given predicate with the new predicate
     * @param stmt the current QueryStatement
     * @param searchCon the QuerySearchCondition which needs to be updated with new predicate
     * @param oldPred the Predicate which needs to be replaced
     * @param newPred the new Predicate
     */
    public void replacePredicate(SQLQueryObject stmt, QuerySearchCondition searchCon, Predicate oldPred, Predicate newPred) {
        if (oldPred.getCombinedRight() == null && oldPred.getCombinedLeft() == null) {
            searchCon = newPred;
            replaceSearchCondition(stmt, searchCon);
        }
        else if (oldPred.getCombinedLeft() != null) {
            oldPred.getCombinedLeft().setLeftCondition(newPred);
        }
        else if (oldPred.getCombinedRight() != null) {
            oldPred.getCombinedRight().setRightCondition(newPred);
        }
    }

    /**
     * 
     * @param statement
     * @param searchCon
     */
    public void replaceSearchCondition(SQLQueryObject statement, QuerySearchCondition searchCon) {
        if (statement instanceof QueryUpdateStatement) {
            ((QueryUpdateStatement) statement).setWhereClause(searchCon);
        }
        if (statement instanceof QueryDeleteStatement) {
            ((QueryDeleteStatement) statement).setWhereClause(searchCon);
        }
        if (statement instanceof QuerySelectStatement) {
            QuerySelect query = (QuerySelect) ((QuerySelectStatement) statement).getQueryExpr().getQuery();
            if (isHavingClause()) {
                query.setHavingClause(searchCon);
            }
            else {
                query.setWhereClause(searchCon);
            }
        }
        if (statement instanceof QuerySelect) {
            QuerySelect query = (QuerySelect) statement;
            if (isHavingClause()) {
                query.setHavingClause(searchCon);
            }
            else {
                query.setWhereClause(searchCon);
            }
        }

    }

    /**
     * @return Returns the havingClause.
     */
    public boolean isHavingClause() {
        return havingClause;
    }

    /**
     * @param havingClause The havingClause to set.
     */
    public void setHavingClause(boolean havingClause) {
        this.havingClause = havingClause;
    }
}