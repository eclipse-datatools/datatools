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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.datatools.modelbase.sql.tables.Column;

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;

/**
 * Helper class for InsertStatement
 */
public class InsertHelper {

    /**
     * Adds the given column to the target list of columns in the given insert
     * statement. If the statement does not have a source query , then a default
     * insert value is set up for the added column, in the insert row.
     * @param editDomain
     *          the current editing domain (command processor)
     * @param statement
     *          the statement to which the column is added
     * @param column
     *          the column being added
     */
    public static void addColumn(EditingDomain editDomain, QueryInsertStatement statement, Column column) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{statement, column});
        }

        ValueExpressionColumn addedColumnExpr = TableHelper.getColumnExpressionForColumn(statement.getTargetTable(), column);
        if (addedColumnExpr != null) {
            // If there is a source query, simply add the column to the target column list.
            if (statement.getSourceQuery() != null) {
                statement.getTargetColumnList().add(addedColumnExpr);
            }
            // Otherwise if there is no source query, there must be a values row in the
            // statement, and along with adding the column to the target column list, 
            // a default value for the column needs to be added to the values row, 
            // corresponding to the column's datatype.
            else {
                InsertHelper.addDefaultInsertValue(editDomain, statement, column);
            }
            SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(statement));
        }
        //temporary fix for notifcations
        List valuesRowList = statement.getSourceValuesRowList();
        if (valuesRowList.size() > 0) {
            valuesRowList.set(0, valuesRowList.get(0));
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Removes the given Column and its value from the given insert statement
     * 
     * @param statement
     *          the given insert statement
     * @param column
     *          the column to be removed
     */
    public static void removeColumn(QueryInsertStatement statement, Column column) {
        ValueExpressionColumn removedColumn = TableHelper.getColumnExpressionForColumn(statement.getTargetTable(), column);
        removeColumn(statement, removedColumn);
    }

    /**
     * Temporary to method to refresh the viewers in cases where notification does not work
     * @param insert
     */
    public static void refresh(QueryInsertStatement insert) {
        if (insert != null) {
            insert.setSourceQuery(insert.getSourceQuery());

        }
    }

    /**
     * Removes the given ValueExpressionColumn and its value from the given insert statement
     * 
     * @param statement
     *          the given insert statement
     * @param removedColumn
     *          the ValueExpressionColumn to be removed
     */
    public static void removeColumn(QueryInsertStatement statement, ValueExpressionColumn removedColumn) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{statement, removedColumn});
        }

        if (removedColumn != null) {
            List columnList = statement.getTargetColumnList();
            int index = columnList.indexOf(removedColumn);
            statement.getTargetColumnList().remove(removedColumn);
            // check for the name because it might be a clone
            if (index == -1) {
            	int size = columnList.size();
                String removedColumnName = removedColumn.getName();
                for (int i=0;i<size;i++) {
                	ValueExpressionColumn column = (ValueExpressionColumn)columnList.get(i);
                    if (removedColumnName.equalsIgnoreCase(column.getName())) {
                    	// found matching name, remove the column
                        index = i;
                        columnList.remove(index);
                        break;
                    }                    
                }
            }            
            // If there is no source query, there must be a values row in the
            // statement
            // and default value corresponding to the column must be removed
            if (index != -1 && statement.getSourceQuery() == null) {
                //The statement is properly set up with a value for the column being
                // removed.Hence the valuesrow should exist now and will be initialized.
                List valuesRowList = statement.getSourceValuesRowList();
                ValuesRow row = (ValuesRow) valuesRowList.get(0);
                List exprList = row.getExprList();

                //Remove the value corrsponding to the column index
                exprList.remove(index);
                SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(statement));
            }
            //temporary fix for notifcations
            List valuesRowList = statement.getSourceValuesRowList();
            if (valuesRowList.size() > 0) {
                valuesRowList.set(0, valuesRowList.get(0));
            }
        }
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Adds a default value for the given column in the given insert statement.
     * @param editDomain
     *          the current editing domain (command processor)
     * @param statement
     *          the insert statement
     * @param colun
     *          the column for which the value is to be added
     */
    //TODO Check the vendor and set the default value for a column accordingly
    public static void addDefaultInsertValue(EditingDomain editDomain, QueryInsertStatement statement, Column column) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{statement, column});
        }

        if (statement != null && column != null) {
            // Check if the sourcValueRowList is empty. If so create a SQLValuesRow
            // and add it to the list.  We will deal with cases where we insert only
            // one row, for now.
            List valuesRowList = statement.getSourceValuesRowList();
            ValuesRow row;
            if (valuesRowList.isEmpty()) {
                row = SQLQueryModelFactory.eINSTANCE.createValuesRow();
                Command addRowCmd = AddCommand.create(editDomain, statement, SQLQueryModelPackage.eINSTANCE.getQueryInsertStatement_SourceValuesRowList(), row);
                addRowCmd.execute();
            }
            else {
                row = (ValuesRow) valuesRowList.get(0);
            }

            // Add the new column to the list of target columns.
            TableExpression tableExpr = statement.getTargetTable();
            ValueExpressionColumn columnValue = TableHelper.getColumnExpressionForColumn(tableExpr, column);
            Command addColCmd = AddCommand.create(editDomain, statement, SQLQueryModelPackage.eINSTANCE.getQueryInsertStatement_TargetColumnList(), columnValue);
            addColCmd.execute();
            QueryValueExpression expr;
            String defaultValue = column.getDefaultValue();
            if (defaultValue != null) {
                expr = SQLQueryModelFactory.eINSTANCE.createValueExpressionDefaultValue();
            }
            else {
                if (column.isNullable()) {
                    expr = SQLQueryModelFactory.eINSTANCE.createValueExpressionNullValue();
                }
                else {
                    expr = SQLQueryModelFactory.eINSTANCE.createValueExpressionSimple();
                    // [RATLC00485883] bgp 13Sept2006 - begin
                    // Since we didn't get a default value from the SQL model, and the
                    // column isn't nullable, we have to determine a valid default
                    // value ourselves.
                    defaultValue = ExpressionHelper.getDefaultValueForColumn(column);
                    ((ValueExpressionSimple) expr).setValue(defaultValue);
                    // [RATLC00485883] bgp 13Sept2006 - end
                }
            }
            // Create a command to add the default value expression to the values row. 
            Command addExprCmd = AddCommand.create(editDomain, statement.getSourceValuesRowList().get(0), SQLQueryModelPackage.eINSTANCE.getValuesRow_ExprList(), expr);
            addExprCmd.execute();
        }
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /*
     * use this piece of code as reference, when setting the default values
     *  
     * if (!insStatement.findColumn(column)) { 
     *   SQLInsertClause ic = insStatement.getInsertClause(); 
     *   if (ic instanceof SQLInsertList || ic == null) { 
     *     if (column.getDefaultValue() == null) { 
     *       if ((column.getAllowNull()).booleanValue()) {
     *         insStatement.buildSimpleStatement(column, SQLValueKind.NULL, "NULL");
     *       }
     *       else { 
     *         insStatement.buildSimpleStatement(column, SQLValueKind.EXPRESSION, ""); 
     *       } 
     *     } 
     *     else { 
     *       VendorHelper vendorHelper = new VendorHelper( insStatement.getDatabase() );
     *       if (vendorHelper.isDB2()) {
     *         insStatement.buildSimpleStatement(column, SQLValueKind.DEFAULT, "DEFAULT");
     *       }
     *       else { 
     *         insStatement.buildSimpleStatement(column, SQLValueKind.EXPRESSION, column.getDefaultValue());
     *       }
     *     }
     *   }
     * }
     */

    /**
     * Adds an empty query expresion to the insert statement.
     * 
     * @param insertStmt
     *          the statement to be modified
     */
    public static void addInsertQuery(QueryInsertStatement insertStmt) {
        QueryExpressionRoot queryExpr = SQLQueryModelFactory.eINSTANCE.createQueryExpressionRoot();
        insertStmt.setSourceQuery(queryExpr);
    }

    /**
     * Clears the contents of the given insert statement.Containments which are objects, are set to null
     * and containments which are lists, are cleared.
     */
    public static void clearStatementContents(QueryInsertStatement stmt) {
        if (stmt != null) {
            removeTableFromStatement(stmt);
            stmt.setSourceQuery(null);
            stmt.setName(null);
            stmt.setLabel(null);
        }
    }

    public static void removeAllColumnsAndValues(QueryInsertStatement stmt) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{stmt});
        }

        if (stmt != null) {
            List colList = stmt.getTargetColumnList();
            colList.clear();
            List valList = stmt.getSourceValuesRowList();
            valList.clear();
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Updates the insert value of the column in the given insert statement.
     * 
     * @param insertStmt
     *          the insert statement to be updated
     * @param column
     *          the column for which the value is updated
     * @param valueExpr
     *          the new value
     */
    public static void updateInsertValueForColumn(QueryInsertStatement insertStmt, ValueExpressionColumn column, QueryValueExpression valueExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{insertStmt, column});
        }

        List columnList = insertStmt.getTargetColumnList();
        int columnPosition = columnList.indexOf(column);
        if (columnPosition != -1) {
            List valuesRowList = insertStmt.getSourceValuesRowList();
            if (valuesRowList != null && valuesRowList.size() > 0) {
                ValuesRow valuesRow = (ValuesRow) valuesRowList.get(0); //first
                // row
                List exprList = valuesRow.getExprList();
                if (exprList != null) {
                    exprList.set(columnPosition, valueExpr);
                }
            }
            //temporary fix for firing notifications
            if (valuesRowList.size() > 0) {
                valuesRowList.set(0, valuesRowList.get(0));
            }
            SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(insertStmt));

        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Adds the given column and value to the given insert statement.
     * 
     * @param insertStmt
     *            the insert statement , to which the column and the value are
     *            added
     * @param column
     *            the column being added
     * @param valueExpr
     *            the value for the column
     */
    public static void addInsertColumnValuePair(QueryInsertStatement insertStmt, ValueExpressionColumn column, QueryValueExpression valueExpr) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{insertStmt, column, valueExpr});
        }

        List columnList = insertStmt.getTargetColumnList();
        columnList.add(column);
        List valuesRowList = insertStmt.getSourceValuesRowList();

        // create new ValuesRow if there is none
        if (valuesRowList.size() == 0) {
            ValuesRow row = SQLQueryModelFactory.eINSTANCE.createValuesRow();
            valuesRowList.add(row);
        }

        ValuesRow valuesRow = (ValuesRow) valuesRowList.get(0); //first row
        List exprList = valuesRow.getExprList();
        exprList.add(valueExpr);
        //  temporary fix for notifcations
        if (valuesRowList.size() > 0) {
            valuesRowList.set(0, valuesRowList.get(0));
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Modified statement: " + StatementHelper.getSQLSourceUnformatted(insertStmt));
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Replaces an existing column in the insert statement with a new column.
     * 
     * @param insertStmt
     *          the insert statement to be modified
     * @param oldColumn
     *          the column being replaced
     * @param newColumn
     *          the column which replaces the existing column
     */
    public static void replaceColumn(QueryInsertStatement insertStmt, ValueExpressionColumn oldColumn, ValueExpressionColumn newColumn) {
        if(SQLBuilderPlugin.getPlugin().getLogger().isTracing()){
            SQLBuilderPlugin.getPlugin().getLogger().writeTraceEntry(
                    new Object[]{insertStmt, oldColumn, newColumn});
        }
        List columnList = insertStmt.getTargetColumnList();
        int columnIndex = columnList.indexOf(oldColumn);
        if (columnIndex != -1) {
            columnList.set(columnIndex, newColumn);
        }
        //temporary fix for notifcations
        List valuesRowList = insertStmt.getSourceValuesRowList();
        if (valuesRowList.size() > 0) {
            valuesRowList.set(0, valuesRowList.get(0));
        }
        SQLBuilderPlugin.getPlugin().getLogger().writeTraceExit(null);
    }

    /**
     * Returns the SQLValueExpressionColumn in the givern insert statement, which
     * has the given name.
     * 
     * @param insertStmt
     *          the insert statment to search on
     * @param columnName
     *          the column name to search for
     * @return the SQLValueExpressionColumn matching the given name or null if
     *         there is no match
     */
    public static ValueExpressionColumn getColumnExpressionForName(QueryInsertStatement insertStmt, String columnName) {
        ValueExpressionColumn column = null;
        boolean found = false;
        Iterator colItr = insertStmt.getTargetColumnList().iterator();
        ValueExpressionColumn tempColumn;
        while (colItr.hasNext() && found == false) {
            tempColumn = ((ValueExpressionColumn) colItr.next());
            if (tempColumn.getName().equals(columnName)) {
                column = tempColumn;
                found = true;
            }
        }
        return column;
    }

    /**
     * Returns the SQLValueExpression from the first row of insert values in the
     * given statement, corresponding to the given column,
     * 
     * @param insertStmt
     *          the statement from which the value is to be searched for
     * @param colExpr
     *          the column for which the value is to be searched for
     * @return the value of the column or null if column is not found in the
     *         statement or the value is not set
     */
    public static QueryValueExpression getInsertValueforColumn(QueryInsertStatement insertStmt, ValueExpressionColumn colExpr) {
        QueryValueExpression valueExpr = null;
        List valuesRowList = insertStmt.getSourceValuesRowList();
        if (valuesRowList != null && valuesRowList.isEmpty() == false) {
            List targetColList = insertStmt.getTargetColumnList();
            // If there is no target column list, then use the list of columns from
            // the target table.
            if (targetColList.isEmpty()) {
                targetColList = insertStmt.getTargetTable().getColumnList();
            }
            int colIndex = targetColList.indexOf(colExpr);
            if (colIndex >= 0) {
                ValuesRow valueRow = (ValuesRow) valuesRowList.get(0);
                if (valueRow != null) {
                    List exprList = valueRow.getExprList();
                    if (exprList.isEmpty() == false) {
                        valueExpr = (QueryValueExpression) exprList.get(colIndex);
                    }
                }
            }
        }
        return valueExpr;
    }

    /**
     * Adds or replaces the target table in the given statement with the given table expression.
     * @param targetTable the new table to use
     * @param stmt the statement to modify
     */
    public static void addOrReplaceTargetTable(TableInDatabase targetTable, QueryInsertStatement stmt) {
        TableExpression currentTableExpr = stmt.getTargetTable();
        if (targetTable != currentTableExpr) {
            removeTableFromStatement(stmt);
            stmt.setTargetTable(targetTable);
        }
    }

    /**
     * Removes the target table from the given statement.
     * @param statement the statement to modify
     */
    public static void removeTableFromStatement(QueryInsertStatement statement) {
        statement.getTargetColumnList().clear();
        statement.getSourceValuesRowList().clear();
        statement.setTargetTable(null);
    }

    /**
     * Replaces the containments in one insert statement with the containments in another insert statement 
     * @param oldStmt statement in which the containments are being replaced
     * @param newStmt statement whose contents are used to replace contents in oldStmt
     */
    public static void replaceStatementContents(QueryInsertStatement oldStmt, QueryInsertStatement newStmt) {
        if (oldStmt != null && newStmt != null) {
            oldStmt.setLabel(newStmt.getLabel());
            oldStmt.setName(newStmt.getName());

            oldStmt.setSourceInfo(newStmt.getSourceInfo());
            oldStmt.setSourceQuery(newStmt.getSourceQuery());
            oldStmt.setTargetTable(newStmt.getTargetTable());

            removeAllColumnsAndValues(oldStmt);

            List oldList = oldStmt.getTargetColumnList();
            List newList = newStmt.getTargetColumnList();
            oldList.addAll(newList);

            oldList = oldStmt.getSourceValuesRowList();
            newList = newStmt.getSourceValuesRowList();
            oldList.addAll(newList);
        }
    }

    /**
     * Sets the target TableExpression of the given Insert statement  
     * @param statement the statement to be modified
     * @param targetTable the new TargetTableExpression
     */
    public static void setTargetTable(QueryInsertStatement statement, TableInDatabase targetTable) {
        if (statement != null) {
            statement.setTargetTable(targetTable);
        }
    }
}