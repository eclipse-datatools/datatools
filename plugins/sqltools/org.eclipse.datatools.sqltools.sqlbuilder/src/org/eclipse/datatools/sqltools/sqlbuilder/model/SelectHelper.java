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

import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist;
import org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn;
import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderingSpecType;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupType;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableReference;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;


/**
 * Helper class for SelectStatement
 * 
 */
public class SelectHelper {

    /**
     * Refreshes the model that ultimately refreseh the UI,  this is temporary.
     * @param stmt the QuerySelectStatement which needs to be refreshed
     */
    public static void refresh(QuerySelectStatement stmt) {
        if (stmt != null) {
            QueryExpressionRoot queryExpr = stmt.getQueryExpr();
            if (queryExpr != null) {
                QueryExpressionBody queryBody = queryExpr.getQuery();
                queryExpr.setQuery(queryBody);
                stmt.setQueryExpr(queryExpr);
            }
        }
    }

    /**
     * Refreshes the model that ultimately refreseh the UI,  this is temporary.
     * @param stmt the QuerySelect which needs to be refreshed
     */
    public static void refresh(QuerySelect stmt) {
        QueryStatement qStmt = StatementHelper.getQueryStatementForTableReference(stmt);
        if(qStmt instanceof QuerySelectStatement){
	        QuerySelectStatement selectstmt = (QuerySelectStatement)qStmt; 
	        refresh(selectstmt);
	        stmt.setWhereClause(stmt.getWhereClause());
        }
        else if (qStmt instanceof QueryInsertStatement) {
            // This is a work around solution for insert statements with
            // a Select sub statement
            QueryInsertStatement insertStmt = (QueryInsertStatement)qStmt;
            QueryExpressionRoot sourceQuery = insertStmt.getSourceQuery();
            if (sourceQuery != null) {               
                insertStmt.setSourceQuery(sourceQuery);                 
            }        	
        }
        else if (qStmt instanceof QueryUpdateStatement) {
        	stmt.setWhereClause(stmt.getWhereClause());
        }
    }

    /**
     * Refreshes the model that ultimately refreseh the UI,  this is temporary.
     * @param stmt the SQLQueryObject which needs to be refreshed
     */
    public static void refresh(SQLQueryObject stmt) {
        if (stmt instanceof QuerySelect) {
            refresh((QuerySelect) stmt);
        }
        else if (stmt instanceof QuerySelectStatement) {
            refresh((QuerySelectStatement) stmt);
        }

    }

    /**
     * Moves the given  OrderBySpecification to the gievn position in the given List containing it
     * @param orderBy the OrderBySpecification
     * @param orderByList the List containing the OrderBySpecification
     * @param position the position in the list to which the OrderBySpecification is to be moved
     */
    public static void moveOrderByToPosition(OrderBySpecification orderBy, List orderByList, int position) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{orderBy, orderByList, new Integer(position)});
        }
        if (orderBy != null && position != -1) {
            orderByList.remove(orderBy);
            orderByList.add(position, orderBy);
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    public static void addTableToStatementAtPosition(SQLQueryObject stmt, TableExpression tableExpr, int position) {
        List tableExprList = null;
    	if (stmt instanceof QuerySelectStatement) {
            QueryExpressionRoot queryExpr = ((QuerySelectStatement)stmt).getQueryExpr();
            if (queryExpr != null) {
                QuerySelect qSelect = getQuerySelect((QuerySelectStatement)stmt);
                if (qSelect != null) {
                	tableExprList = qSelect.getFromClause();
                }
            }
        }
        else if (stmt instanceof QuerySelect) {
            tableExprList = ((QuerySelect)stmt).getFromClause();
        }
    	
    	if (tableExprList != null && position <= tableExprList.size()){
    		tableExprList.add(position, tableExpr);
    	}
    	
    }
    
    /**
     * Adds the given table expression to list of tables for a select statement. 
     * @param stmt the QuerySelectStatement in which table needs to be added
     * @param tableExpr the TableExpression which needs to be added
     */
    public static void addTableToStatement(QuerySelectStatement stmt, TableExpression tableExpr) {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                new Object[]{stmt, tableExpr});
        }

        QueryExpressionRoot queryExpr = stmt.getQueryExpr();

        if (queryExpr != null) {
            QuerySelect qSelect = getQuerySelect(stmt);
            if (qSelect != null) {
                List tableExprList = qSelect.getFromClause();
                tableExprList.add(tableExpr);
            }
        }
        else {
            queryExpr = StatementHelper.createQueryExpressionRoot();
            QuerySelect qSelect = StatementHelper.createQuerySelect();

            List tableExprList = qSelect.getFromClause();
            tableExprList.add(tableExpr);
            queryExpr.setQuery(qSelect);
            stmt.setQueryExpr(queryExpr);
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(stmt));
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
    }

    /**
     * Adds the given table expression to list of tables for a select statement. 
     * @param qSelect the QuerySelect in which table needs to be added
     * @param tableExpr the TableExpression which needs to be added
     */
    public static void addTableToStatement(QuerySelect qSelect, TableExpression tableExpr) {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                new Object[]{qSelect, tableExpr});
        }
        
        if (qSelect != null) {
            List tableExprList = qSelect.getFromClause();
            tableExprList.add(tableExpr);
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(qSelect));
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
    }

    /**
     * Sets the given QueryExpressionBody object as a child of the given Querycombined object.
     * Attempts to add the child on the left side first, but if there is already a child on the left side then 
     * sets the given child object as the right side. 
     * @param combined the QueryCombined 
     * @param child the QueryExpressionbody object
     */
    public static void setChildForQueryCombined(QueryCombined combined, QueryExpressionBody child) {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                new Object[]{combined, child});
        }
        
        if (combined != null && child != null) {
            QueryExpressionBody left = combined.getLeftQuery();
            if (left == null) {
                combined.setLeftQuery(child);
            }
            else {
                combined.setRightQuery(child);
            }
        }
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
    }

    /**
     * Checks if the given QueryCombined object's left and right children are set. 
     * @param node the QueryCombined object
     * @return false if either the left or the right side child of the given node is null
     */
    public static boolean isNodeFull(QueryCombined node) {
        boolean isFull = true;
        if (node != null) {
            if (node.getLeftQuery() == null || node.getRightQuery() == null) {
                isFull = false;
            }
        }
        return isFull;
    }

    /**
     * Checks if the given QueryCombined object has either a left side child or a right side child.
     * @param node the QueryCombined object
     * @return true if node has either a left side child or a right side child
     */
    public static boolean hasChild(QueryCombined node) {
        boolean hasChild = false;
        if (node != null) {
            if (node.getLeftQuery() != null || node.getRightQuery() != null) {
                hasChild = true;
            }
        }
        return hasChild;
    }

    /**
     * Creates the Query Select and appends it to QueryExpressionRoot.  
     * @param stmt the QuerySelectStatement in which table needs to be added
     */
    public static void initSelectStmt(QuerySelectStatement stmt) {
        if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                new Object[]{stmt});
        }
        
        if (stmt != null) {
            QueryExpressionRoot queryExpr = stmt.getQueryExpr();
            if (queryExpr == null) {
                queryExpr = StatementHelper.createQueryExpressionRoot();
            }
            if (queryExpr.getQuery() == null) {
                QuerySelect qSelect = StatementHelper.createQuerySelect();
                queryExpr.setQuery(qSelect);
                stmt.setQueryExpr(queryExpr);
            }
        }
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
    }

    /**
     * Removes the table from the given Select statement
     * @param stmt the QuerySelectStatement from which table needs to be removed
     * @param tableExpr the TableExpression which needs to be removed
     * @return true if a value was removed otherwise false 
     */
    public static boolean removeTableFromStatement(QuerySelectStatement stmt, TableExpression tableExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{stmt, tableExpr});
        }

        boolean retVal = false;
        QueryExpressionRoot queryExpr = stmt.getQueryExpr();
        if (queryExpr != null) {
            QueryExpressionBody query = queryExpr.getQuery();
            if (query != null) {
                retVal = removeTableFromQueryExpressionBody(query, tableExpr);

            }
            SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(stmt));

        }
        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( retVal );
    }

    /**
     * Removes the table from the given Query Expression
     * @param query the QueryExpressionBody from which table needs to be removed
     * @param tableExpr the TableExpression which needs to be removed
     * @return true if a value was removed otherwise false
     */
    private static boolean removeTableFromQueryExpressionBody(QueryExpressionBody query, TableExpression tableExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{query, tableExpr});
        }

        boolean retVal = false;
        if (query != null) {
            if (query instanceof QuerySelect) {
                QuerySelect querySelect = (QuerySelect) query;
                List tableExprList = querySelect.getFromClause();
                if (tableExprList != null && !tableExprList.isEmpty()) {
                    retVal = tableExprList.remove(tableExpr);
                }
            }
            //              else if (query instanceof QueryCombined)
            //              {
            //                  QueryCombined combined = (QueryCombined) query;
            //                  retVal = removeTableFromQueryExpressionBody(combined.getLeftQuery(),tableExpr) ;
            //                  retVal = removeTableFromQueryExpressionBody(combined.getRightQuery(),tableExpr);
            //              }
            SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(query));

        }
        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(retVal);
    }

    /**
     * Clears the contents of the given Select statement.Containments which are objects, are set to null
     * and containments which are lists, are cleared.
     * @param stmt the statement being modified
     */
    public static void clearStatementContents(QuerySelectStatement stmt) {
        //TODO  NEED to add code when dealing with JOIN
        if (stmt != null) {
            stmt.setQueryExpr(null);
            stmt.getOrderByClause().clear();
            stmt.setSourceInfo(null);
            stmt.setName(null);
            stmt.setLabel(null);
        }
    }

    /**
     * Replaces contents of the statement from the given new statement
     * @param oldStmt the QuerySelectStatement that needs to be refreshed
     * @param newStmt the QuerySelectStatement from which contents needs to be replaced
     */
    public static void replaceStatementContents(QuerySelectStatement oldStmt, QuerySelectStatement newStmt) {
        if (oldStmt != null && newStmt != null) {
            oldStmt.setLabel(newStmt.getLabel());
            oldStmt.setName(newStmt.getName());
            oldStmt.setSourceInfo(newStmt.getSourceInfo());

            oldStmt.setQueryExpr(newStmt.getQueryExpr());
            // Since notifications isn't working, deeper replacement is needed for refreshing full select viewers 
            QueryExpressionRoot expr = oldStmt.getQueryExpr();
            if (expr != null) {
                QueryExpressionBody qBody = expr.getQuery();
                if (qBody instanceof QueryCombined) {
                	((QueryCombined) qBody).setLeftQuery(((QueryCombined) qBody).getLeftQuery());
                }
            }           
            oldStmt.getOrderByClause().clear();
            oldStmt.getOrderByClause().addAll(newStmt.getOrderByClause());
        }
    }

    /**
     * Returns the QueryExpressionBody from the Select statement
     * @param stmt the QuerySelectStatement from which QuerySelect needs tobe returned
     * @return the QueryExpressionBody if exists otherwise null
     */
    public static QueryExpressionBody getQueryExpressionBody(QuerySelectStatement stmt) {
        QueryExpressionBody body = null;
        QueryExpressionRoot qRoot = stmt.getQueryExpr();
        if (qRoot != null) {
            body = qRoot.getQuery();

        }
        return body;
    }

    /**
     * Returns the QuerySelectStatement whose QueryExpressionRoot contain the given QueryExpressionBody object
     * @param queryExprBody the QueryExpressionBody
     * @return the QuerySelectStatement or null.
     */
    public static QuerySelectStatement getQuerySelectStatement(QueryExpressionBody queryExprBody) {
        QuerySelectStatement statement = null;
        if (queryExprBody != null) {
            QueryExpressionRoot exprRoot = queryExprBody.getQueryExpression();
            if (exprRoot != null) {
                statement = exprRoot.getSelectStatement();
            }
        }

        return statement;
    }

    /**
     * Returns a string to create a QueryCombinedOperator,based on the given string, which is the 
     * SQL of the operator
     * @param inString
     * @return
     */
    public static String getCombinedOperatorStringConstant(String inString) {
        String opString = "";
        if (inString.equals("UNION")) {
            opString = "UNION";
        }
        else if (inString.equals("UNION ALL")) {
            opString = "UNION_ALL";
        }
        else if (inString.equals("INTERSECT")) {
            opString = "INTERSECT";
        }
        else if (inString.equals("INTERSECT ALL")) {
            opString = "INTERSECT_ALL";
        }
        else if (inString.equals("EXCEPT")) {
            opString = "EXCEPT";
        }
        else if (inString.equals("EXCEPT ALL")) {
            opString = "EXCEPT_ALL";
        }

        return opString;
    }

    public static String getCombinedOperatorSQLString(String inString) {
        String opString = "";
        if (inString.equals("UNION")) {
            opString = "UNION";
        }
        else if (inString.equals("UNION_ALL")) {
            opString = "UNION ALL";
        }
        else if (inString.equals("INTERSECT")) {
            opString = "INTERSECT";
        }
        else if (inString.equals("INTERSECT_ALL")) {
            opString = "INTERSECT ALL";
        }
        else if (inString.equals("EXCEPT")) {
            opString = "EXCEPT";
        }
        else if (inString.equals("EXCEPT_ALL")) {
            opString = "EXCEPT ALL";
        }

        return opString;
    }

    /*	  public static List getAllResultColumnExpressions(QueryExpressionBody queryExpr){
     List list =  new ArrayList();
     if(queryExpr instanceof QueryCombined){
     QueryExpressionBody leftChild = ((QueryCombined)queryExpr).getLeftQuery();
     if(leftChild != null){
     list.addAll(getAllResultColumnExpressions(leftChild));
     }
     QueryExpressionBody rightChild = ((QueryCombined)queryExpr).getRightQuery();
     if(rightChild != null){
     list.addAll(getAllResultColumnExpressions(rightChild));
     }
     }
     else if(queryExpr instanceof QuerySelect){
     QuerySelect qSelect = (QuerySelect)queryExpr;
     List sList = qSelect.getSelectClause();
     list.addAll(getColumnExpressions(sList));
     }
     return list;
     }
     */
    /**
     * Returns a list of QueryValueExpressions contained in the ResultColumn objects in a given list
     * of QueryResultSpecification object
     * @param selClauseList the input list contaiinng QueryResultSpecification objects
     * @return the list of QuerValueExpressions
     *
     public static List getColumnExpressions(List selClauseList){
     List columns =  new ArrayList();
     if(selClauseList != null){
     Iterator itr = selClauseList.iterator();
     while(itr.hasNext()){
     QueryResultSpecification resultSpec = (QueryResultSpecification)itr.next();
     if(resultSpec instanceof ResultColumn){
     QueryValueExpression expr = ((ResultColumn)resultSpec).getValueExpr();
     columns.add(expr);
     }
     
     }
     }
     return columns;
     }
     */
    /**
     * Returns the QuerySelect from the Select statement
     * @param stmt the QuerySelectStatement from which QuerySelect needs tobe returned
     * @return the QuerySelect if exists otherwise null
     */
    public static QuerySelect getQuerySelect(QuerySelectStatement stmt) {
        QuerySelect qSelect = null;
        if (stmt != null) {
            QueryExpressionRoot qRoot = stmt.getQueryExpr();
            if (qRoot != null && qRoot.getQuery() instanceof QuerySelect) {
                qSelect = (QuerySelect) qRoot.getQuery();
            }
        }
        return qSelect;
    }

    /**
     * Creates the Result column from the given ValueExpression and appends it to the select column 
     * list in the given Select statement.
     * @param selectStmt the QuerySelectStatement to which new column needs to added
     * @param colExpr the given ValueExpression for the column
     * @param alias the Sting value for the alias name of the column 
     * @return the created ResultColumn
     */
    public static ResultColumn appendResultColumn(SQLQueryObject selectStmt, QueryValueExpression colExpr, String alias) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, colExpr,alias});
        }

        ResultColumn resCol = null;
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        resCol = factory.createResultColumn();
        if (colExpr != null) {
            if (getOrderByColIndexFromValueExpr(selectStmt, colExpr) >= 0) {
                removeColumnFromOrderBy(selectStmt, colExpr);
            }
            resCol.setValueExpr(colExpr);
        }
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(
                    new Object[]{selectStmt});
        }
        resCol = appendResultColumn(selectStmt, resCol, alias);
        
        return (ResultColumn) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( resCol );
    }

    /**
     * Appends the given ResultColumn to the select column list in the given Select statement.
     * @param selectStmt the QuerySelectStatement to which new column needs to added 
     * @param resCol the given ResultColumn that needs to be added
     * @param alias the Sting value for the alias name of the column 
     * @return the appended ResultColumn
     */
    public static ResultColumn appendResultColumn(SQLQueryObject selectStmt, ResultColumn resCol, String alias) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, resCol, alias });
        }
        
        if (resCol != null) {
            if (alias != null && alias.trim().length() > 0)
                resCol.setName(alias);

            QuerySelect qSelect = null;
            if (selectStmt instanceof QuerySelectStatement) {
                qSelect = getQuerySelect((QuerySelectStatement) selectStmt);
            }
            else if (selectStmt instanceof QuerySelect) {
                qSelect = (QuerySelect) selectStmt;
            }

            if (qSelect != null) {
                List qColList = qSelect.getSelectClause();
                /* If there is a "table all columns" (tablename.*) object in the list, check
                 * if this new column is in the table referenced.  If so, remove
                 * the "all columns" object from the query select list. */
                QueryValueExpression resColValExpr = resCol.getValueExpr();
                if (resColValExpr instanceof ValueExpressionColumn) {
                    ValueExpressionColumn resColValExprCol = (ValueExpressionColumn) resColValExpr;
                    /* Get the table associated with the new result column. */
                    TableExpression resultColTableExpr = resColValExprCol.getTableExpr();
                    
                    /* Scan the current query select (result column) list for 
                     * "table all column" objects. */
                    Iterator qColListIter = qColList.iterator();
                    while (qColListIter.hasNext()) {
                        QueryResultSpecification qResultSpec = (QueryResultSpecification) qColListIter.next();
                        /* If we find an "all columns" object, see if it has the same
                         * table expression as the new result column. If so, remove it. */
                        if (qResultSpec instanceof ResultTableAllColumns) {
                            ResultTableAllColumns qResultAllCols = (ResultTableAllColumns) qResultSpec;
                            TableExpression qResultAllColsTableExpr = qResultAllCols.getTableExpr();
                            if (qResultAllColsTableExpr == resultColTableExpr) {
                                qColListIter.remove();
                            }
                        }
                    }
                        
                }
                qColList.add(resCol);
            }
        }
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(
                    new Object[]{selectStmt});
        }
        
        return (ResultColumn) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( resCol );
    }

    /**
     * Creates the OrderByValueExpression column for "Order By" clause from the given value expression 
     * if not already exists and appends it to Order By list.
     * @param selectStmt the QuerySelectStatement to which new Order By column needs to added 
     * @param colExpr the given ValueExpression for the Order By column
     * @param sortType the sort order for the column, it can be ASC or DESC
     * @return the created OrderByValueExpression column
     */
    public static OrderByValueExpression appendOrderByColumn(QuerySelectStatement selectStmt, QueryValueExpression colExpr, String sortType) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt,colExpr,sortType });
        }
        
        OrderByValueExpression ordValExpr = null;
        if (colExpr != null) {
            int resColIdx = getOrderByColIndexFromValueExpr(selectStmt, colExpr);
            if (resColIdx < 0) {
                SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
                ordValExpr = factory.createOrderByValueExpression();
                ordValExpr.setValueExpr(colExpr);
                if (sortType.equalsIgnoreCase("ASC")) {
                    ordValExpr.setDescending(false);
                    ordValExpr.setOrderingSpecOption(OrderingSpecType.get(OrderingSpecType.ASC));
                }
                else {
                    ordValExpr.setDescending(true);
                    ordValExpr.setOrderingSpecOption(OrderingSpecType.get(OrderingSpecType.DESC));
                }
                selectStmt.getOrderByClause().add(ordValExpr);
            }
            else {
                OrderBySpecification ordSpec = (OrderBySpecification) selectStmt.getOrderByClause().get(resColIdx);
                if (sortType.equalsIgnoreCase("ASC")) {
                    ordSpec.setDescending(false);
                    ordSpec.setOrderingSpecOption(OrderingSpecType.get(OrderingSpecType.ASC));
                }
                else {
                    ordSpec.setDescending(true);
                    ordSpec.setOrderingSpecOption(OrderingSpecType.get(OrderingSpecType.DESC));
                }
            }
        }
        
        return (OrderByValueExpression) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(ordValExpr);
    }

    /**
     * Creates the OrderByResultColumn column for "Order By" clause from the given ResultColumn 
     * if not already exists and appends it to Order By list.
     * @param selectStmt the QuerySelectStatement to which new Order By column needs to added 
     * @param resCol the given ResultColumn that needs to be added to the Order By column
     * @param alias the Sting value for the alias name of the column
     * @param sortType the sort order for the column, it can be ASC or DESC
     * @return the created OrderByResultColumn column
     */
    public static OrderByResultColumn appendOrderByColumn(QuerySelectStatement selectStmt, ResultColumn resCol, String alias, String sortType) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, resCol, alias, sortType });
        }
        
        OrderByResultColumn ordResultCol = null;
        if (resCol != null) {
            int resColIdx = getOrderByColIndexFromValueExpr(selectStmt, resCol.getValueExpr());
            if (resColIdx < 0) {
                if (alias != null && alias.trim().length() > 0) {
                    resCol.setName(alias);
                }
                SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
                ordResultCol = factory.createOrderByResultColumn();
                ordResultCol.setResultCol(resCol);
                if (sortType.equalsIgnoreCase("ASC")) {
                    ordResultCol.setDescending(false);
                    ordResultCol.setOrderingSpecOption(OrderingSpecType.get(OrderingSpecType.ASC));
                }
                else {
                    ordResultCol.setDescending(true);
                    ordResultCol.setOrderingSpecOption(OrderingSpecType.get(OrderingSpecType.DESC));
                }
                selectStmt.getOrderByClause().add(ordResultCol);
            }
            else {
                if (selectStmt.getOrderByClause().get(resColIdx) instanceof OrderByResultColumn) {
                    if (alias != null && alias.trim().length() > 0) {
                        resCol.setName(alias);
                    }
                    ordResultCol = (OrderByResultColumn) selectStmt.getOrderByClause().get(resColIdx);
                    ordResultCol.setResultCol(resCol);
                    if (sortType.equalsIgnoreCase("ASC")) {
                        ordResultCol.setDescending(false);
                        ordResultCol.setOrderingSpecOption(OrderingSpecType.get(OrderingSpecType.ASC));
                    }
                    else {
                        ordResultCol.setDescending(true);
                        ordResultCol.setOrderingSpecOption(OrderingSpecType.get(OrderingSpecType.DESC));
                        
                    }
                }
                else {
                    selectStmt.getOrderByClause().remove(resColIdx);
                    ordResultCol = appendOrderByColumn(selectStmt, resCol, alias, sortType);
                }
            }
           SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
       }

        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
        return ordResultCol;
    }

    /**
     * Returns true if the give ResultColumn is part of the given select statement.
     * @param selectStmt the given QuerySelectStatement 
     * @param resCol the given ResultColumn that needs to be find in the given select statement
     * @return true if the given column exits in the statement otherwise false
     */
    public static boolean isResultColumn(SQLQueryObject selectStmt, ResultColumn resCol) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, resCol });
        }
        
        boolean retVal = false;
        QuerySelect qSelect = null;
        if (selectStmt instanceof QuerySelectStatement) {
            qSelect = getQuerySelect((QuerySelectStatement) selectStmt);
        }
        else if (selectStmt instanceof QuerySelect) {
            qSelect = (QuerySelect) selectStmt;
        }
        if (qSelect != null) {
            List qColList = qSelect.getSelectClause();
            if (qColList != null && !qColList.isEmpty()) {
                Iterator cols = qColList.iterator();
                while (cols.hasNext()) {
                    Object col = cols.next();

                    if (col instanceof ResultColumn) {
                        ResultColumn currCol = (ResultColumn) col;
                        if (currCol == resCol) {
                            retVal = true;
                        }
                    }
                }
            }
        }
        
        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( retVal );
    }

    /**
     * Returns true if the give ResultColumn is part of the given select.
     * @param qSelectStmt the given QuerySelect 
     * @param resCol the given ResultColumn that needs to be find in the given select statement
     * @return true if the given column exits in the statement otherwise false
     */
    public static boolean isResultColumn(QuerySelect qSelect, ResultColumn resCol) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{qSelect, resCol });
        }
        
        boolean retVal = false;
        if (qSelect != null) {
            List qColList = qSelect.getSelectClause();
            if (qColList != null && !qColList.isEmpty()) {
                Iterator cols = qColList.iterator();
                while (cols.hasNext()) {
                    Object col = cols.next();

                    if (col instanceof ResultColumn) {
                        ResultColumn currCol = (ResultColumn) col;
                        if (currCol == resCol) {
                            retVal = true;
                        }
                    }
                }
            }
        }
        
        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( retVal );
    }

    /**
     * Returns true if the give ValueExpression is part of the given select statement's result columns.
     * @param selectStmtselectStmt the given QuerySelectStatement 
     * @param resColExpr the given ValueExpression that needs to be find in the given select statement
     * @return true if the given ValueExpression exits in the statement otherwise false
     */
    public static boolean isResultColumn(SQLQueryObject selectStmt, ValueExpressionColumn resColExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, resColExpr });
        }
        
        boolean retVal = false;
        QuerySelect qSelect = null;
        if (selectStmt instanceof QuerySelectStatement) {
            qSelect = getQuerySelect((QuerySelectStatement) selectStmt);
        }
        else if (selectStmt instanceof QuerySelect) {
            qSelect = (QuerySelect) selectStmt;
        }
        if (qSelect != null && resColExpr != null) {
            List qColList = qSelect.getSelectClause();
            if (qColList != null && !qColList.isEmpty() && !qColList.isEmpty()) {
                TableExpression resColTableExpr = ExpressionHelper.getTableExprForValueExpressionColumn(resColExpr);
                Iterator cols = qColList.iterator();
                while (cols.hasNext()) {
                    Object col = cols.next();

                    if (col instanceof ResultColumn) {
                        ResultColumn currCol = (ResultColumn) col;
                        if (currCol.getValueExpr() instanceof ValueExpressionColumn) {
                            ValueExpressionColumn currColExpr = (ValueExpressionColumn) currCol.getValueExpr();
                            TableExpression currColTableExpr = ExpressionHelper.getTableExprForValueExpressionColumn(currColExpr);
                            if (resColTableExpr == currColTableExpr) {
                                if (currColExpr.getName().equalsIgnoreCase(resColExpr.getName())) {
                                    retVal = true;
                                }
                            }
                        }
                        else if (currCol.getValueExpr() == resColExpr) {
                            retVal = true;
                        }
                    }
                }
            }
        }
        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( retVal );
    }

    /**
     * Returns true if the give ValueExpression is part of the given query select's result columns.
     * @param qSelect the given QuerySelect 
     * @param resColExpr the given ValueExpression that needs to be find in the given select statement
     * @return true if the given ValueExpression exits in the statement otherwise false
     */
    public static boolean isResultColumn(QuerySelect qSelect, ValueExpressionColumn resColExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{qSelect, resColExpr });
        }
        
        boolean retVal = false;
        if (qSelect != null) {
            List qColList = qSelect.getSelectClause();
            if (qColList != null && !qColList.isEmpty() && !qColList.isEmpty()) {
                TableExpression resColTableExpr = ExpressionHelper.getTableExprForValueExpressionColumn(resColExpr);
                Iterator cols = qColList.iterator();
                while (cols.hasNext()) {
                    Object col = cols.next();
                    if (col instanceof ResultColumn) {
                        ResultColumn currCol = (ResultColumn) col;
                        if (currCol.getValueExpr() instanceof ValueExpressionColumn) {
                            ValueExpressionColumn currColExpr = (ValueExpressionColumn) currCol.getValueExpr();
                            TableExpression currColTableExpr = ExpressionHelper.getTableExprForValueExpressionColumn(currColExpr);
                            if (resColTableExpr == currColTableExpr) {
                                if (currColExpr.getName().equalsIgnoreCase(resColExpr.getName())) {
                                    retVal = true;
                                }
                            }
                        }
                        else if (currCol.getValueExpr() == resColExpr) {
                            retVal = true;
                        }
                    }
                }
            }
        }
        
        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( retVal );
    }

    /**
     * Gets whether or not the given QuerySelect object is a "select *" query.
     * 
     * @param querySelect the QuerySelect to check
     * @return true when the QuerySelect is "select * from", otherwise false
     */
    public static boolean isSelectStarQuery(QuerySelect querySelect) {
        boolean isSelectStar = false;
        
        List selectClauseList = querySelect.getSelectClause();
        isSelectStar = selectClauseList.isEmpty();
        
        return isSelectStar;
    }
    
    /**
     * Moves a column in the selectClause to another position (ie new index)
     * in the List.  The column is first removed then added to list.
     * @param selectStmt the QuerySelectStatement that the column belongs to
     * @param aResultColumn the ResultColumn that needs to be moved
     * @param movePosition the position to be moved.  
     * <p>
     * -1 moves the column up one position (newIndex = oldIndex -1)
     * +1 moves the column down one position
     * @return true if column successfully moved, false if not
     */
    public static boolean moveColumnInStatement(QuerySelectStatement selectStmt, ResultColumn aResultColumn, int movePosition) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, aResultColumn, new Integer(movePosition)});
        }

        boolean moved = false;
        QuerySelect qSelect = getQuerySelect(selectStmt);
        if (qSelect != null && aResultColumn != null && movePosition != 0) {
            List qColList = qSelect.getSelectClause();
            if (qColList != null && !qColList.isEmpty()) {
                int columnIndex = qColList.indexOf(aResultColumn);
                if (columnIndex > -1) {
                    // found column
                    if (columnIndex + movePosition >= 0 && columnIndex + movePosition < qColList.size()) {
                        QueryValueExpression exp = aResultColumn.getValueExpr();
                        removeColumnFromResultColumns(selectStmt, exp);
                        addResultColumn(selectStmt, aResultColumn, null, columnIndex + movePosition);
                        moved = true;
                    }
                }
            }
                SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
        }

        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( moved );
    }

    /**
     * Adds a column to to the select statement's result columns list
     * @param selectStmt the QuerySelectStatement object that the column is added
     * @param aResultColumn the ResultColumn object to be added
     * @param index the index of the columns list to add to
     */
    public static void addResultColumn(QuerySelectStatement selectStmt, ResultColumn aResultColumn, String alias, int index) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, aResultColumn, alias});
        }
        
        if (aResultColumn != null && index > -1) {
            if (alias != null && alias.trim().length() > 0)
                aResultColumn.setName(alias);

            QuerySelect qSelect = getQuerySelect(selectStmt);
            List qColList = qSelect.getSelectClause();
            qColList.add(index, aResultColumn);
            
            if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
                SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
            }
        }
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );    
    }

    /**
     * Adds a QueryValueExpression to the Order by clause of a QuerySelectStatement
     * @param selectStmt the QueryValueExpression object containing the Order by
     * clause that the QueryValueExpression is to be added to
     * @param anExpr the QueryValueExpression to be added
     * @param sortType the sorttype
     * @param position the position in the order by clause to be inserted
     */
    public static void addOrderByColumn(QuerySelectStatement selectStmt, QueryValueExpression anExpr, String sortType, int position) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, anExpr, sortType});
        }
        
        OrderByValueExpression ordValExpr = null;
        if (anExpr != null && position > -1) {
            SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
            ordValExpr = factory.createOrderByValueExpression();
            ordValExpr.setValueExpr(anExpr);
            if ("ASC".equalsIgnoreCase(sortType)) {
                ordValExpr.setDescending(false);
                ordValExpr.setOrderingSpecOption(OrderingSpecType.get(OrderingSpecType.ASC));
            }
            else {
                ordValExpr.setDescending(true);
                ordValExpr.setOrderingSpecOption(OrderingSpecType.get(OrderingSpecType.DESC));
            }
            selectStmt.getOrderByClause().add(position, ordValExpr);
            
                SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
        }
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
    }

    /**
     * Moves a column in the order by clause to another position (ie new index
     * in the list).  The column is first removed then added to the list in
     * the new position.
     * @param selectStmt the QuerySelectStatement holding the order by clause
     * @param anExpr the OrderBySpecification object to move
     * @param movePosition the number of places to move
     * <p>
     * -1 moves the column up one position (newIndex = oldIndex -1)
     * +1 moves the column down one position
     * @return true if column successfully moved, false if not
     */
    public static boolean moveOrderByInStatement(QuerySelectStatement selectStmt, OrderBySpecification anExpr, int movePosition) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, anExpr, new Integer(movePosition)});
        }
        
        boolean moved = false;
        if (selectStmt != null && anExpr != null && movePosition != 0) {
            List orderList = selectStmt.getOrderByClause();
            if (orderList != null && !orderList.isEmpty()) {
                int columnIndex = orderList.indexOf(anExpr);
                if (columnIndex > -1) {
                    // item found
                    if (columnIndex + movePosition >= 0 && columnIndex + movePosition < orderList.size()) {
                        QueryValueExpression value = ((OrderByValueExpression) anExpr).getValueExpr();
                        String sortType = "ASC";
                        if (((OrderByValueExpression) anExpr).isDescending()) {
                            sortType = "DESC";
                        }
                        removeColumnFromOrderBy(selectStmt, value);
                        
                        addOrderByColumn(selectStmt, value, sortType, columnIndex + movePosition);
                        moved = true;
                            SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
                    }
                }
            }
        }

        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( moved );
    }

    /**
     * Removes the result column that contains the given ValueExpression from the given statement.
     * @param selectStmt the given QuerySelectStatement from which column needs to be removed
     * @param sqlExpr the ValueExpression which needs to be matched  
     */
    public static void removeColumnFromResultColumns(SQLQueryObject selectStmt, QueryValueExpression sqlExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, sqlExpr});
        }
        
        QuerySelect qSelect = null;
        if (selectStmt instanceof QuerySelectStatement) {
            qSelect = getQuerySelect((QuerySelectStatement) selectStmt);
        }
        else if (selectStmt instanceof QuerySelect) {
            qSelect = (QuerySelect) selectStmt;
        }
        if (qSelect != null) {
            List qColList = qSelect.getSelectClause();
            if (qColList != null && !qColList.isEmpty()) {
                Iterator cols = qColList.iterator();
                while (cols.hasNext()) {
                    Object col = cols.next();
                    if (col instanceof ResultColumn) {
                        ResultColumn resCol = (ResultColumn) col;
                        if (resCol.getValueExpr() == sqlExpr) {
                            removeColumnFromOrderBy(selectStmt, resCol);
                            cols.remove();
                        }
                    }
                }
                
                if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
                    SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
                }
            }
        }
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
    }

    /**
     * Removes the order by column that contains the given ResultColumn from the given statement.
     * @param selectStmt the give QuerySelectStatement from which column needs to be removed 
     * @param resCol the ResultColumn which needs to be matched
     */
    public static void removeColumnFromOrderBy(SQLQueryObject selectStmt, ResultColumn resCol) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, resCol});
        }
        
        List ordByColList = new ArrayList();
        QuerySelectStatement sStmt = null;
        if (selectStmt instanceof QuerySelect) {
            QueryStatement qStmt = StatementHelper.getQueryStatementForTableReference((QuerySelect) selectStmt);
            if (qStmt != null && qStmt instanceof QuerySelectStatement) {
                sStmt = (QuerySelectStatement) qStmt;
                ordByColList = sStmt.getOrderByClause();
            }
        }
        else if (selectStmt != null && selectStmt instanceof QuerySelectStatement) {
            sStmt = (QuerySelectStatement) selectStmt;
            ordByColList = sStmt.getOrderByClause();
        }
        if (ordByColList != null && !ordByColList.isEmpty()) {
            Iterator cols = ordByColList.iterator();
            boolean deleted = false;
            while (cols.hasNext() && !deleted) {
                Object col = cols.next();
                if (col instanceof OrderByResultColumn) {
                    OrderByResultColumn rstCol = (OrderByResultColumn) col;
                    if (rstCol.getResultCol() == resCol) {
                        cols.remove();
                        deleted = true;
                    }
                }
            }
                SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
        }

        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );        
    }
    

    /**
     * Removes the order by column that contains the given value expression from the given statement.
     * @param selectStmt the give QuerySelectStatement from which column needs to be removed 
     * @param sqlExpr the Value Expression which needs to be matched
     */
    public static void removeColumnFromOrderBy(SQLQueryObject selectStmt, QueryValueExpression sqlExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, sqlExpr});
        }
        
        List ordByColList = new ArrayList();
        QuerySelectStatement sStmt = null;
        if (selectStmt instanceof QuerySelect) {
            QueryStatement qStmt = StatementHelper.getQueryStatementForTableReference((QuerySelect) selectStmt);
            if (qStmt != null && qStmt instanceof QuerySelectStatement) {
                sStmt = (QuerySelectStatement) qStmt;
                ordByColList = sStmt.getOrderByClause();
            }
        }
        else if (selectStmt != null && selectStmt instanceof QuerySelectStatement) {
            sStmt = (QuerySelectStatement) selectStmt;
            ordByColList = sStmt.getOrderByClause();
        }
        if (ordByColList != null && !ordByColList.isEmpty()) {
            Iterator cols = ordByColList.iterator();
            boolean deleted = false;
            while (cols.hasNext() && !deleted) {
                Object col = cols.next();

                if (col instanceof OrderByValueExpression) {
                    OrderByValueExpression ordCol = (OrderByValueExpression) col;
                    if (ordCol.getValueExpr() == sqlExpr) {
                        //cols.remove() ;
                        ordByColList.remove(col);
                        deleted = true;
                    }
                }
                else if (col instanceof OrderByResultColumn) {
                    OrderByResultColumn rstCol = (OrderByResultColumn) col;
                    if (rstCol.getResultCol().getValueExpr() == sqlExpr) {
                        ordByColList.remove(col);
                        deleted = true;
                    }
                }

            }
            
                SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
        }
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
       
    }
    
    /**
     * Repositions the order by column that contains the given value expression in the given statement.
     * @param selectStmt the give QuerySelectStatement from which column needs to be removed 
     * @param sqlExpr the Value Expression which needs to be matched
     * @param position the new position for the column
     * @return true if orderby column found and moved, otherwise false.
     */
    public static boolean repositionColumnInOrderBy(SQLQueryObject selectStmt, QueryValueExpression sqlExpr, int position) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, sqlExpr});
        }
       
        List ordByColList = new ArrayList();
        QuerySelectStatement sStmt = null;
        if (selectStmt instanceof QuerySelect) {
            QueryStatement qStmt = StatementHelper.getQueryStatementForTableReference((QuerySelect) selectStmt);
            if (qStmt != null && qStmt instanceof QuerySelectStatement) {
                sStmt = (QuerySelectStatement) qStmt;
                ordByColList = sStmt.getOrderByClause();
            }
        }
        else if (selectStmt != null && selectStmt instanceof QuerySelectStatement) {
            sStmt = (QuerySelectStatement) selectStmt;
            ordByColList = sStmt.getOrderByClause();
        }
        boolean moved = false;
        if (ordByColList != null && !ordByColList.isEmpty()) {
            Iterator cols = ordByColList.iterator();
            while (cols.hasNext() && !moved) {
                Object col = cols.next();

                if (col instanceof OrderByValueExpression) {
                	OrderByValueExpression ordCol = (OrderByValueExpression) col;
                	if (ordCol.getValueExpr() == sqlExpr) {
                		ordByColList.remove(ordCol);
                		ordByColList.add(position, ordCol);
                		moved = true;
                	}
                }
                else if (col instanceof OrderByResultColumn) {
                	OrderByResultColumn rstCol = (OrderByResultColumn) col;
                	if (rstCol.getResultCol().getValueExpr() == sqlExpr) {
                		ordByColList.remove(rstCol);
                		ordByColList.add(position, rstCol);
                		moved = true;
                	}
                }
            }
            SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
        }
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
        return moved;
    }

    /**
     * Replaces the value expression in the given result column of a statement.
     * @param selectStmt the QuerySelectStatement of which the result column is part of 
     * @param resCol the given ResultColum for which new ValueExpression needs to be replaced
     * @param newValExpr the new QueryValueExpression that needs to be assigned 
     * @return true if value is replaced, otherwise false
     */
    public static boolean replaceColumnValueExpr(QuerySelectStatement selectStmt, ResultColumn resCol, QueryValueExpression newValExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, resCol,newValExpr});
        }
        
        boolean retVal = false;
        QuerySelect qSelect = getQuerySelect(selectStmt);
        List qColList = qSelect.getSelectClause();
        if (qColList != null && !qColList.isEmpty()) {
            Iterator cols = qColList.iterator();
            while (cols.hasNext()) {
                Object col = cols.next();

                if (col instanceof ResultColumn) {
                    ResultColumn currCol = (ResultColumn) col;
                    if (currCol == resCol) {
                        currCol.setValueExpr(newValExpr);
                        retVal = true;
                    }
                }
            }
            
                SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
        }

        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( retVal );
    }

    /**
     * Replaces the value expression in the given result column of a statement.
     * @param selectStmt the SQLQueryObject of which the result column is part of 
     * @param resCol the given ResultColum for which new ValueExpression needs to be replaced
     * @param newValExpr the new QueryValueExpression that needs to be assigned 
     * @return true if value is replaced, otherwise false
     */
    public static boolean replaceColumnValueExpr(SQLQueryObject selectStmt, ResultColumn resCol, QueryValueExpression newValExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, resCol,newValExpr});
        }
        
        boolean retVal = false;
        QuerySelect qSelect = null;
        if (selectStmt instanceof QuerySelectStatement) {
            qSelect = getQuerySelect((QuerySelectStatement) selectStmt);
        }
        else if (selectStmt instanceof QuerySelect) {
            qSelect = (QuerySelect) selectStmt;
        }
        if (qSelect != null) {
            List qColList = qSelect.getSelectClause();
            if (qColList != null && !qColList.isEmpty()) {
                Iterator cols = qColList.iterator();
                while (cols.hasNext()) {
                    Object col = cols.next();

                    if (col instanceof ResultColumn) {
                        ResultColumn currCol = (ResultColumn) col;
                        if (currCol == resCol) {
                            currCol.setValueExpr(newValExpr);
                            retVal = true;
                        }
                    }
                }
                
                if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
                    SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
                }
            }
        }

        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( retVal );
    }

    /**
     * Replaces the value expression of the Order By column that matches the value expression of the given 
     * result column of a statement.
     * @param selectStmt the QuerySelectStatement that contain the order by 
     * @param resCol the ResultColumn that needs to be matched with
     * @param newValExpr the new ValueExpression
     * @return true if value is replaced, otherwise false
     */
    public static boolean replaceColumnValueExprForOrderBy(QuerySelectStatement selectStmt, ResultColumn resCol, QueryValueExpression newValExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, resCol, newValExpr});
        }
        
        boolean retVal = false;
        List qColList = selectStmt.getOrderByClause();
        if (qColList != null && !qColList.isEmpty()) {
            Iterator cols = qColList.iterator();
            while (cols.hasNext()) {
                Object ordCol = cols.next();
                if (ordCol instanceof OrderByValueExpression) {
                    QueryValueExpression valExpr = ((OrderByValueExpression) ordCol).getValueExpr();
                    if (valExpr == resCol.getValueExpr()) {
                        ((OrderByValueExpression) ordCol).setValueExpr(newValExpr);
                        retVal = true;
                    }

                }
                else if (ordCol instanceof OrderByResultColumn) {
                    ResultColumn currCol = ((OrderByResultColumn) ordCol).getResultCol();
                    if (currCol == resCol) {
                        currCol.setValueExpr(newValExpr);
                        retVal = true;
                    }
                }
            }
            
            if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
                SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
            }
        }

        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( retVal );
    }

    /**
     * Returns the position of the column that matches the given ValueExpression in the order by column list.
     * @param selectStmt the given QuerySelectStatement that contains the order by 
     * @param searchValExpr the ValueExpression that needs to be searched
     * @return the position of the order by column
     */
    public static int getOrderByColIndexFromValueExpr(SQLQueryObject selectStmt, QueryValueExpression searchValExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, searchValExpr});
        }
        
        int retVal = -1;
        List qColList = new ArrayList();
        QuerySelectStatement sStmt = null;
        if (selectStmt instanceof QuerySelect) {
            QueryStatement qStmt = StatementHelper.getQueryStatementForTableReference((QuerySelect) selectStmt);
            if (qStmt != null && qStmt instanceof QuerySelectStatement) {
                sStmt = (QuerySelectStatement) qStmt;
                qColList = sStmt.getOrderByClause();
            }
        }
        else if (selectStmt != null && selectStmt instanceof QuerySelectStatement) {
            sStmt = (QuerySelectStatement) selectStmt;
            qColList = sStmt.getOrderByClause();
        }
        if (qColList != null && !qColList.isEmpty()) {
            Iterator cols = qColList.iterator();
            int currIndex = 0;
            while (cols.hasNext() && retVal < 0) {
                Object ordCol = cols.next();
                if (ordCol instanceof OrderByValueExpression) {
                    QueryValueExpression valExpr = ((OrderByValueExpression) ordCol).getValueExpr();
                    if (valExpr == searchValExpr) {
                        retVal = currIndex;
                    }

                }
                else if (ordCol instanceof OrderByResultColumn) {
                    ResultColumn currCol = ((OrderByResultColumn) ordCol).getResultCol();
                    if (currCol != null && currCol.getValueExpr() == searchValExpr) {
                        retVal = currIndex;
                    }
                }
                currIndex++;
            }
        }
        
        return SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( retVal );
    }

    /**
     * Assigns the alias to the given result column.
     * @param resultColumn the ResultColumn for which alias needs to be set
     * @param alias the String value for the alias
     */
    public static void setResultColumnAlias(ResultColumn resultColumn, String alias) {
        if (alias.trim().length() > 0) {
            resultColumn.setName(alias);
        }
        else {
            resultColumn.setName(null);
        }
    }

    /**
     * Looks for a ValueExpressionColumn in the list of assignment select columns in the given statement,
     * with name same as the given name
     * @param statement the statement on which the search needs to be performed 
     * @param columnName the name of the ValueExpressionColumn to search for
     * @return the ValueExpressionColumn with a matching name or null if no match is found
     */
    public static ValueExpressionColumn getSelectColumnFromColumnName(SQLQueryObject selectStmt, String columnName) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, columnName});
        }
        
        ValueExpressionColumn colExpr = null;
        QuerySelect qSelect = null;
        if (selectStmt instanceof QuerySelectStatement) {
            qSelect = getQuerySelect((QuerySelectStatement) selectStmt);
        }
        else if (selectStmt instanceof QuerySelect) {
            qSelect = (QuerySelect) selectStmt;
        }
        if (qSelect != null) {
            List qColList = qSelect.getSelectClause();
            if (qColList != null && !qColList.isEmpty()) {
                Iterator cols = qColList.iterator();
                while (cols.hasNext()) {
                    Object col = cols.next();
                    if (col instanceof ResultColumn) {
                        ResultColumn currCol = (ResultColumn) col;
                        if (currCol.getValueExpr() instanceof ValueExpressionColumn) {
                            ValueExpressionColumn tmpColExpr = (ValueExpressionColumn) currCol.getValueExpr();
                            if (tmpColExpr.getName().equalsIgnoreCase(columnName)) {
                                colExpr = tmpColExpr;
                            }
                        }
                    }
                }
            }
        }
        
        return (ValueExpressionColumn) SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( colExpr );
    }

    /**
     * Removes the order by column that contains the given value expression from the given statement.
     * @param selectStmt the give QuerySelectStatement from which column needs to be removed 
     * @param sqlExpr the Value Expression which needs to be matched
     */
    public static void removeAllColumnFromOrderBy(QuerySelectStatement selectStmt, QueryValueExpression sqlExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, sqlExpr});
        }

        List ordByColList = selectStmt.getOrderByClause();
        if (ordByColList != null && !ordByColList.isEmpty()) {
            Iterator cols = ordByColList.iterator();
            boolean deleted = false;
            while (cols.hasNext() && !deleted) {
                Object col = cols.next();
                if (col instanceof OrderByResultColumn) {
                    OrderByResultColumn rstCol = (OrderByResultColumn) col;
                    if (rstCol.getResultCol().getValueExpr() instanceof ValueExpressionColumn && sqlExpr instanceof ValueExpressionColumn) {
                        ValueExpressionColumn colExpr = (ValueExpressionColumn) rstCol.getResultCol().getValueExpr();
                        if (colExpr.getName().equalsIgnoreCase(sqlExpr.getName())
                                && ExpressionHelper.getTableExprForValueExpressionColumn(colExpr) == ExpressionHelper
                                        .getTableExprForValueExpressionColumn((ValueExpressionColumn) sqlExpr)) {
                            ordByColList.remove(col);
                            deleted = true;
                        }
                    }
                }
            }
            
            if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
                SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
            }
        }
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
    }

    /**
     * Removes the result column that contains the given ValueExpression from the given statement.
     * @param selectStmt the given QuerySelectStatement from which column needs to be removed
     * @param sqlExpr the ValueExpression which needs to be matched  
     */
    public static void removeAllColumnFromResultColumns(SQLQueryObject selectStmt, QueryValueExpression sqlExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, sqlExpr});
        }
        
        QuerySelect qSelect = null;
        if (selectStmt instanceof QuerySelectStatement) {
            qSelect = getQuerySelect((QuerySelectStatement) selectStmt);
        }
        else if (selectStmt instanceof QuerySelect) {
            qSelect = (QuerySelect) selectStmt;
        }
        if (qSelect != null) {
            List qColList = qSelect.getSelectClause();
            if (qColList != null && !qColList.isEmpty()) {
                Iterator cols = qColList.iterator();
                while (cols.hasNext()) {
                    Object col = cols.next();

                    if (col instanceof ResultColumn) {
                        ResultColumn resCol = (ResultColumn) col;
                        if (resCol.getValueExpr() instanceof ValueExpressionColumn && sqlExpr instanceof ValueExpressionColumn) {
                            ValueExpressionColumn colExpr = (ValueExpressionColumn) resCol.getValueExpr();
                            if (colExpr.getName().equalsIgnoreCase(sqlExpr.getName())
                                    && ExpressionHelper.getTableExprForValueExpressionColumn(colExpr) == (ExpressionHelper
                                            .getTableExprForValueExpressionColumn((ValueExpressionColumn) sqlExpr))) {
                                removeColumnFromOrderBy(selectStmt, resCol);
                                cols.remove();
                            }
                        }

                    }
                }
                
                if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
                    SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(selectStmt));
                }
            }
        }
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
    }
    
    /**
     * Removes the functions from the result columns whose parameters use columns in the 
     * table that is being removed.
     * @param selectStmt the given QuerySelectStatement from which column needs to be removed
     * @param aRef the TableReference that is being removed  
     */
    public static void removeColFunctionsForTable(SQLQueryObject selectStmt, TableReference aRef) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{selectStmt, aRef});
        }
        
        String tableName = aRef.getName();
        
        QuerySelect qSelect = null;
        if (selectStmt instanceof QuerySelectStatement) {
            qSelect = getQuerySelect((QuerySelectStatement) selectStmt);
        }
        else if (selectStmt instanceof QuerySelect) {
            qSelect = (QuerySelect) selectStmt;
        }
        if (qSelect != null) {
			List qColList = qSelect.getSelectClause();
			if (qColList != null && !qColList.isEmpty()) {
				Iterator cols = qColList.iterator();
				while (cols.hasNext()) {
					Object col = cols.next();

					if (col instanceof ResultColumn) {
						ResultColumn resCol = (ResultColumn) col;
						if (resCol.getValueExpr() instanceof ValueExpressionFunction) {
							ValueExpressionFunction colFunc = (ValueExpressionFunction) resCol
									.getValueExpr();
							Iterator iter = colFunc.getParameterList()
									.iterator();
							while (iter.hasNext()) {
								// Check if any of the parameters come from the
								// table
								Object obj = iter.next();
								if (obj instanceof ValueExpressionColumn) {
									ValueExpressionColumn colExpr = (ValueExpressionColumn) obj;
									if (ExpressionHelper.getTableExprForValueExpressionColumn(colExpr) == aRef) {												
										removeColumnFromOrderBy(selectStmt,
												resCol);
										cols.remove();
										break;
									}
								}
							}
						}
					}
				}

				if (SQLBuilderPlugin.getPlugin().getLogger().isTracing()) {
					SQLBuilderPlugin
							.getPlugin()
							.getLogger()
							.writeTrace(
									"Modified statement: "
											+ StatementHelper
													.getSQLSourceUnformatted(selectStmt));
				}
			}
		}
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit( null );
    }

    /***************************************************************************
	 * BEGIN : Group By Releated Methods
	 **************************************************************************/

    /**
     * Creates the GroupingExpression and set the given ValueExpression
     * @param valExpr the ValueExpression that needs to be set in the new GroupingExpressing
     * @return new GroupingExpression
     */
    public static GroupingExpression createGroupingExpression(QueryValueExpression valExpr) {
        GroupingExpression retExpr = null;
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        retExpr = factory.createGroupingExpression();
        retExpr.setValueExpr(valExpr);
        return retExpr;
    }

    /**
     * Creates the SuperGroup and sets the given type.
     * @param superGroupType the Int type of the new SuperGroup.
     * @return the new SuperGroup
     */
    public static SuperGroup createSuperGroup(int superGroupType) {
        SuperGroup retExpr = null;
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        retExpr = factory.createSuperGroup();
        retExpr.setSuperGroupType(SuperGroupType.get(superGroupType));
        return retExpr;
    }

    /**
     * Creates the SuperGroupElementExpression and set the given ValueExpression
     * @param valExpr the ValueExpression that needs to be set in the new SuperGroupElementExpression
     * @return the new SuperGroupElementExpression
     */
    public static SuperGroupElementExpression createSuperGroupElementExpression(QueryValueExpression valExpr) {
        SuperGroupElementExpression retExpr = null;
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        retExpr = factory.createSuperGroupElementExpression();
        GroupingExpression grpExpr = createGroupingExpression(valExpr);
        retExpr.setGroupingExpr(grpExpr);
        return retExpr;
    }

    /**
     * Creates the SuperGroupElementSublist.
     * @return the new SuperGroupElementSublist
     */
    public static SuperGroupElementSublist createSuperGroupElementSublist() {
        SuperGroupElementSublist retExpr = null;
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        retExpr = factory.createSuperGroupElementSublist();
        return retExpr;
    }

    /**
     * Creates the GroupingSets.
     * @return the new GroupingSets
     */
    public static GroupingSets createGroupingSets() {
        GroupingSets retExpr = null;
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        retExpr = factory.createGroupingSets();
        return retExpr;
    }

    /**
     * Creates the GroupingSetsElementExpression and set the given ValueExpression
     * @param valExpr the ValueExpression that needs to be set in the new GroupingSetsElementExpression
     * @return the new GroupingSetsElementExpression
     */
    public static GroupingSetsElementExpression createGroupingSetsElementExpression(QueryValueExpression valExpr) {
        GroupingSetsElementExpression retExpr = null;
        retExpr = createGroupingSetsElementExpression();
        GroupingExpression grpExpr = createGroupingExpression(valExpr);
        retExpr.setGrouping(grpExpr);
        return retExpr;
    }

    /**
     * Creates the GroupingSetsElementExpression
     * @return the new GroupingSetsElementExpression
     */
    public static GroupingSetsElementExpression createGroupingSetsElementExpression() {
        GroupingSetsElementExpression retExpr = null;
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        retExpr = factory.createGroupingSetsElementExpression();
        return retExpr;
    }

    /**
     * Creates the GroupingSetsElementSublist
     * @return the new GroupingSetsElementSublist
     */
    public static GroupingSetsElementSublist createGroupingSetsElementSublist() {
        GroupingSetsElementSublist retExpr = null;
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        retExpr = factory.createGroupingSetsElementSublist();
        return retExpr;
    }

    /**
     * Creates the WithTableSpecification.
     * @return the new TableWithSpecificatio
     */
    public static WithTableSpecification createWithTableSpecification() {
        WithTableSpecification retExpr = null;
        SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;
        retExpr = factory.createWithTableSpecification();
        return retExpr;
    }

}