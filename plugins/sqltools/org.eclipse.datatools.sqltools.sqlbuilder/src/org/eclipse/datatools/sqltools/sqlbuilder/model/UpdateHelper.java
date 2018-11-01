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

//DOCME
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.tables.Column;

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;

/**
 * Helper class for UpdateStatement
 * 
 */
public class UpdateHelper {

    static SQLQueryModelFactory factory;
    static {
        factory = SQLQueryModelFactoryImpl.eINSTANCE;
    }

    /**
     * 
     * @param statement
     */
    public static void clearAssignmentExprList(QueryUpdateStatement statement) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{statement});
        }
        List assignExprList = statement.getAssignmentClause();
        assignExprList.clear();
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Returns the list of columns that the given update statement modifies
     * @param statement the given update statement
     * @return the list of columns referred in the assignment clause of the update statement.
     */
    public static List getTargetColumns(QueryUpdateStatement statement) {
        List columns = null;
        List assignExprList = statement.getAssignmentClause();
        if (assignExprList != null) {
            columns = new ArrayList();
            Iterator assignExprItr = assignExprList.iterator();
            List colList;
            Iterator colListItr;
            while (assignExprItr.hasNext()) {
                UpdateAssignmentExpression assignExpr = (UpdateAssignmentExpression) assignExprItr.next();
                colList = assignExpr.getTargetColumnList();
                if (colList != null) {
                    colListItr = colList.iterator();
                    while (colListItr.hasNext()) {
                        columns.add(colListItr.next());
                    }
                }
            }
        }
        return columns;
    }

    /**
     * Returns the UpdateAssignmentExpression from the given update statement, which contains the given column
     * @param statement the update statement
     * @param column the column for which the assisnment expression is to be found
     * @return the UpdateAssignmentExpression containing the column or null if none is found
     */
    public static UpdateAssignmentExpression getAssignmentExprForColumn(QueryUpdateStatement statement, ValueExpressionColumn column) {
        UpdateAssignmentExpression assignExpr = null;
        if (statement != null && column != null) {
            Iterator exprListItr = statement.getAssignmentClause().iterator();
            UpdateAssignmentExpression tempExpr;
            ValueExpressionColumn tempColumn;
            boolean found = false;
            while (!(found) && exprListItr.hasNext()) {
                tempExpr = (UpdateAssignmentExpression) exprListItr.next();
                Iterator colItr = tempExpr.getTargetColumnList().iterator();
                while (!(found) && colItr.hasNext()) {
                    tempColumn = (ValueExpressionColumn) colItr.next();
                    if (tempColumn.getName().equals(column.getName())) {
                        assignExpr = tempExpr;
                        found = true;
                    }

                }
            }
        }
        return assignExpr;
    }

    /**
     * Removes the given ValueExpressionColumn from the UpdateAssignmentExpression containing it, in the given update
     * statement.If the removed column is the only column in the target column list of the assignment expression
     * then UpdateAssignmentExpression is removed from the list of assignment expressions in the update statement
     * 
     * @param statement the QueryUpdateStatement from which the given column is removed
     * @param column the ValueExpressionColumn being removed
     */
    public static void removeColumn(QueryUpdateStatement statement, ValueExpressionColumn column) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{statement, column});
        }
        if (statement != null && column != null) {
            UpdateAssignmentExpression expr = UpdateHelper.getAssignmentExprForColumn(statement, column);
            if (expr != null) {
                List colList = expr.getTargetColumnList();
                int index = colList.indexOf(column);
                // check for the name because it might be a clone
                // such as when a statement is saved and reloaded
                if (index == -1) {
                    int size = colList.size();
                    String removedColumnName = column.getName();
                    for (int i=0;i<size;i++) {
                        ValueExpressionColumn targetColumn = (ValueExpressionColumn)colList.get(i);
                        if (removedColumnName.equalsIgnoreCase(targetColumn.getName())) {
                            // found matching name
                            index = i;                            
                            break;
                        }                        
                    }
                }                
                
                if (index != -1) {
                    colList.remove(index);
                    UpdateSource source = expr.getUpdateSource();
                    if (source instanceof UpdateSourceExprList) {
                        List values = ((UpdateSourceExprList) source).getValueExprList();
                        if (values != null) {
                            values.remove(index);
                        }
                    }
                }
                if (colList.size() == 0) {
                    expr.setUpdateSource(null);
                    List assignExprList = statement.getAssignmentClause();
                    assignExprList.remove(expr);
                }
                // this is a temporary work around to make the notification wrok 
                // when there are still columns remaiinng in the list
                else {
                    List assignExprList = statement.getAssignmentClause();
                    int indx = assignExprList.indexOf(expr);
                    assignExprList.set(indx, expr);
                    //assignExprList.remove(expr);
                    //assignExprList.add(expr);

                }
            SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(statement));

            }
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * This is a wrapper method around removeColumn(QueryUpdateStatement statement,ValueExpressionColumn column)
     * in this class
     * @param statement the QueryUpdateStatement from which the given column is removed
     * @param column the Column to be removed
     */
    public static void removeColumn(QueryUpdateStatement statement, Column column) {
        ValueExpressionColumn valExprCol = TableHelper.getColumnExpressionForColumn(statement.getTargetTable(), column);
        UpdateHelper.removeColumn(statement, valExprCol);
    }

    /**
     * Replaces the containments in one update statement with the containments in another update statement 
     * @param oldStmt statement in which the containments are being replaced
     * @param newStmt statement whose contents are used to replace contents in oldStmt
     */
    public static void replaceStatementContents(QueryUpdateStatement oldStmt, QueryUpdateStatement newStmt) {
        if (oldStmt != null && newStmt != null) {
            oldStmt.setLabel(newStmt.getLabel());
            oldStmt.setName(newStmt.getName());

            oldStmt.setSourceInfo(newStmt.getSourceInfo());
            oldStmt.setWhereClause(newStmt.getWhereClause());
            oldStmt.setWhereCurrentOfClause(newStmt.getWhereCurrentOfClause());
            oldStmt.setTargetTable(newStmt.getTargetTable());

            clearAssignmentExprList(oldStmt);
            List oldList = oldStmt.getAssignmentClause();
            List newList = newStmt.getAssignmentClause();
            oldList.addAll(newList);
        }
    }

    /**
     * Creates an UpdateAssinmentExpression which contains the given Column and its default value,
     * and adds it to the list of assignment expressions in the given update statement
     * @param statement the update statement being modified
     * @param column the column being added
     */
    public static void addColumn(QueryUpdateStatement statement, Column column) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{statement, column });
        }
        if (statement != null && column != null) {
            ValueExpressionColumn valueExprCol = TableHelper.getColumnExpressionForColumn(statement.getTargetTable(), column);
            QueryValueExpression valueExpr = getDefaultColumnValue(column);

            UpdateAssignmentExpression assignExpr = factory.createUpdateAssignmentExpression();
            assignExpr.getTargetColumnList().add(valueExprCol);

            UpdateSourceExprList sourceExprList = factory.createUpdateSourceExprList();
            sourceExprList.getValueExprList().add(valueExpr);
            assignExpr.setUpdateSource(sourceExprList);

            List assignExprList = statement.getAssignmentClause();
            assignExprList.add(assignExpr);
            SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(statement));
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Creates an UpdateAssignmentExpression which contains the given ValueExpressionColumn
     * and its QueryValueExpression and add it to the list of assignment expressions to the
     * QueryUpdateStatement
     * @param statement the update statement to which the UpdateAssignmentExpression will
     * be assigned
     * @param valueExprCol the ValueExpressionColumn being added
     * @param valueExpr the QueryValueExpression associated with valueExprCol
     */
    public static void addColumn(QueryUpdateStatement statement, ValueExpressionColumn valueExprCol, QueryValueExpression valueExpr) {
        if (statement == null || valueExprCol == null || valueExpr == null) {
            return;
        }
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{statement});
        }
        UpdateAssignmentExpression assignExpr = factory.createUpdateAssignmentExpression();
        assignExpr.getTargetColumnList().add(valueExprCol);

        UpdateSourceExprList sourceExprList = factory.createUpdateSourceExprList();
        sourceExprList.getValueExprList().add(valueExpr);
        assignExpr.setUpdateSource(sourceExprList);

        List assignExprList = statement.getAssignmentClause();
        assignExprList.add(assignExpr);
        SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(statement));
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Creates an UpdateAssinmentExpression which contains  the given list of Columns and their default values,
     * and adds it to the list of assignment expressions in the given update statement
     * @param statement the update statement being modified
     * @param columns the list of Columns being added to the statement
     */
    public static void addColumns(QueryUpdateStatement statement, List columns) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{statement, columns});
        }
        if (statement != null && columns != null) {
            UpdateAssignmentExpression assignExpr = factory.createUpdateAssignmentExpression();
            List targetColList = assignExpr.getTargetColumnList();
            UpdateSourceExprList sourceExprList = factory.createUpdateSourceExprList();
            List valuesList = sourceExprList.getValueExprList();

            //UpdateSourceQuery sourceQuery = factory.createUpdateSourceQuery();
            Iterator colsItr = columns.iterator();
            while (colsItr.hasNext()) {
                Column col = (Column) colsItr.next();
                ValueExpressionColumn valueExprCol = TableHelper.getColumnExpressionForColumn(statement.getTargetTable(), col);
                targetColList.add(valueExprCol);
                QueryValueExpression valueExpr = getDefaultColumnValue(col);
                valuesList.add(valueExpr);
            }
            assignExpr.setUpdateSource(sourceExprList);

            List assignExprList = statement.getAssignmentClause();
            assignExprList.add(assignExpr);
            SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(statement));
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }
//TODO change this so that the default value depending on the column specific to a database is returned
    public static QueryValueExpression getDefaultColumnValue(Column column) {
        QueryValueExpression valueExpr = factory.createValueExpressionDefaultValue();
        return valueExpr;
    }

    /**
     * Gets a QueryValueExpression in a statement corresponding to the given column
     * @param statement the statement from which the value is to be searched
     * @param column the column for which the value is to be searched for
     * @return the value of the column or null if column not found in the statement
     * or the value is not set
     */
    public static QueryValueExpression getValueForColumn(QueryUpdateStatement statement, ValueExpressionColumn column) {
        if (statement == null || column == null) {
            return null;
        }
        QueryValueExpression valueExpr = null;
        List updateAssignmentExprList = statement.getAssignmentClause();
        if (updateAssignmentExprList == null || updateAssignmentExprList.isEmpty()) {
            return null;
        }
        Iterator assignmentIter = updateAssignmentExprList.iterator();
        while (assignmentIter.hasNext()) {
            UpdateAssignmentExpression updExpr = ((UpdateAssignmentExpression) assignmentIter.next());
            int columnIndex = updExpr.getTargetColumnList().indexOf(column);
            if (columnIndex >= 0) {
                UpdateSource updateSource = updExpr.getUpdateSource();
                if (updateSource instanceof UpdateSourceExprList) {
                    valueExpr = (QueryValueExpression) ((UpdateSourceExprList) updateSource).getValueExprList().get(columnIndex);
                    break;
                }
            }
        }

        return valueExpr;

    }

    /**
     * Looks for a ValueExpressionColumn in the list of assignment expressions in the given statement,
     * with name same as the given name
     * @param statement the statement on which the search needs to be performed 
     * @param columnName the name of the ValueExpressionColumn to search for
     * @return the ValueExpressionColumn with a matching name or null if no match is found
     */
    public static ValueExpressionColumn getColumnExpressionForName(QueryUpdateStatement statement, String columnName) {
        ValueExpressionColumn column = null, tempColumn = null;
        boolean found = false;
        Iterator exprListItr = statement.getAssignmentClause().iterator();
        while (!(found) && exprListItr.hasNext()) {
            UpdateAssignmentExpression expr = (UpdateAssignmentExpression) exprListItr.next();
            Iterator colItr = expr.getTargetColumnList().iterator();
            while (colItr.hasNext()) {
                tempColumn = (ValueExpressionColumn) colItr.next();
                if (tempColumn.getName().equals(columnName)) {
                    found = true;
                    column = tempColumn;
                }
            }
        }
        return column;
    }

 
    /**
     * Removes sets the target table of the given Update statement to null
     * @param statement the update statement 
     */
    public static void removeTableFromStatement(QueryUpdateStatement statement) {
        statement.setTargetTable(null);
    }

    /**
     * Updates the value of the given column in the given UpdatesourceExpressionList
     * @param source the UpdatesourceExpressionList containing the value for the column
     * @param column the column for which the value is to be updated
     * @param value the new value
     */
    public static void setValueForColumn(UpdateSourceExprList source, ValueExpressionColumn column, QueryValueExpression value) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{source, column,value });
        }
        if (source != null) {
            UpdateAssignmentExpression assignExpr = source.getUpdateAssignmentExpr();
            List colList = assignExpr.getTargetColumnList();
            int index = colList.indexOf(column);
            if (index != -1) {
                List values = (source).getValueExprList();
                values.set(index, value);
            }
            List exprList = assignExpr.getUpdateStatement().getAssignmentClause();
            //temporary fix to trigger notifications
            exprList.set(0, exprList.get(0));
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Clears the contents of the given update statement.Containments which are objects, are set to null
     * and containments which are lists, are cleared.
     * @param stmt the statement being modified
     */
    public static void clearStatementContents(QueryUpdateStatement stmt) {
        if (stmt != null) {
            stmt.setTargetTable(null);
            clearAssignmentExprList(stmt);
            stmt.setWhereClause(null);
            stmt.setWhereCurrentOfClause(null);
            // stmt.setSourceInfo(null);
            stmt.setName(null);
            stmt.setLabel(null);
        }
    }

    /**
     * Sets the target TableExpression of the given Update statement  
     * @param statement the statement to be modified
     * @param targetTable the new TargetTableExpression
     */
    public static void setTargetTable(QueryUpdateStatement statement, TableInDatabase targetTable) {
        if (statement != null) {
            statement.setTargetTable(targetTable);
            /*	  	temporary fix to trigger notifications
             List exprList = statement.getAssignmentClause();
             UpdateAssignmentExpression expr = factory.createUpdateAssignmentExpression();
             exprList.add(expr);
             exprList.remove(expr);
             */

        }
    }

    /**
     * Refreshes the model that ultimately refreseh the UI,  this is temporary.
     * @param stmt the QueryUpdateStatement which needs to be refreshed
     */
    public static void refresh(QueryUpdateStatement stmt) {
        stmt.setWhereClause(stmt.getWhereClause());
    }
    
    /**
     * Creates an UpdateSourceQuery which contains the given QueryExpressionBody,
     * and adds it to the given AssignmentExpression
     * 
     * @param assignExpr the update assignment expression to be modified
     * @param query the query expression body to connect to the assignment expression.
     */
    public static void createUpdateSourceQuery(UpdateAssignmentExpression assignExpr,
    		QueryExpressionBody query) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{assignExpr, query });
        }
        
        UpdateSourceQuery updateSource = factory.createUpdateSourceQuery();
        updateSource.setQueryExpr(query);
        assignExpr.setUpdateSource(updateSource);
        
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }
    /**
     * Creates an UpdateSourceExpressionList which contains the given QueryValueExpression
     * for the given column and adds it to the given AssignmentExpression
     * 
     * @param assignExpr the update assignment expression to be modified
     * @param column the column for the expression.
     * @param query the query value expression to connect to the assignment expression.
     */
    public static void createUpdateSourceExpressionList(UpdateAssignmentExpression assignExpr, 
    		ValueExpressionColumn column, QueryValueExpression valueExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{assignExpr, valueExpr });
        }
        
        UpdateSourceExprList updateSource = factory.createUpdateSourceExprList();
        List valuesList = updateSource.getValueExprList();
        List colList = assignExpr.getTargetColumnList();
        
        // Initialize value list
        Iterator colsItr = colList.iterator();
        while (colsItr.hasNext()) {
        	colsItr.next(); 
        	// TODO: Get correct column so correct default can be made
        	// Currently getDefaultColumnValue ignores the column passed
            QueryValueExpression defaultValueExpr = getDefaultColumnValue(null);
            valuesList.add(defaultValueExpr);
        }
        
        // Update specified column
        int index = colList.indexOf(column);
        if (index != -1) {
        	valuesList.set(index, valueExpr);
        }
        assignExpr.setUpdateSource(updateSource);
      
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }


}