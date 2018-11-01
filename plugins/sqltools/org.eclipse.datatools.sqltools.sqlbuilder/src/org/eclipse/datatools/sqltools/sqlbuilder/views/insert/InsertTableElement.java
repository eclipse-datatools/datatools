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
package org.eclipse.datatools.sqltools.sqlbuilder.views.insert;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Display;
import org.eclipse.datatools.modelbase.sql.tables.Column;

import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.MultilineInputDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.InsertHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

/**
 * InsertTableElement is applicable for insert grid view
 */
public class InsertTableElement {

    protected QueryInsertStatement insertStatement;
    protected ValueExpressionColumn insertColumn;
    protected QueryValueExpression valueExpr;
    SQLDomainModel domainModel;

    public InsertTableElement(SQLDomainModel model, QueryInsertStatement statement, ValueExpressionColumn column, QueryValueExpression value) {
        domainModel = model;
        insertStatement = statement;
        insertColumn = column;
        valueExpr = value;
    }

    public QueryInsertStatement getInsertStatement() {
        return insertStatement;
    }

    public ValueExpressionColumn getColumn() {
        return insertColumn;
    }

    public QueryValueExpression getExpression() {
        return valueExpr;
    }

    public void modify(Object key, Object value) {
        Object newValue = null;
        if (key == SQLBuilderConstants.P_STATEMENT_VALUE) {

            if (value instanceof String) {
                String strVal = (String) value;
                if (strVal.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || strVal.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)
                        || strVal.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION)) {

                    newValue = showExpressionBuilder(getExpression(), false, (String) value);
                }
                else if (strVal.equals(SQLBuilderConstants.P_VALUE_DEFAULT)) {
                    newValue = ExpressionHelper.createValueExpressionDefaultValue();
                }
                else if (strVal.equals(SQLBuilderConstants.P_VALUE_NULL)) {
                    newValue = ExpressionHelper.createValueExpressionNullValue();
                }
                else if (strVal.equals(SQLBuilderConstants.P_EDIT_INPUT_VALUE)) {
                	newValue = showInputEditor(valueExpr.getSQL());                      
                }

            }
            else if (value instanceof QueryValueExpression) {
                newValue = value;
            }

            if (valueExpr == null) {
                if (newValue == null) {
                    newValue = ExpressionHelper.createExpression(getColumnText(1));
                }
                InsertHelper.addInsertColumnValuePair(insertStatement, insertColumn, (QueryValueExpression) newValue);
            }
            else {
                InsertHelper.updateInsertValueForColumn(insertStatement, insertColumn, (QueryValueExpression) newValue);
            }
        }
        //insert values is setup in the UI 
        else if (key == SQLBuilderConstants.P_STATEMENT_COLUMN) {
            if (insertColumn != null) { //a column is already selected in the grid
                InsertHelper.replaceColumn(insertStatement, insertColumn, (ValueExpressionColumn) value);
            }
            else {//a column is being added from the grid
                EditingDomain editDomain = domainModel.getEditingDomain();
                Column col = TableHelper.getColumnForColumnExpression(insertStatement.getTargetTable(), (ValueExpressionColumn) value);
                InsertHelper.addColumn(editDomain, insertStatement, col);
            }
        }
    }

    /**
     * Get the mof value and return it
     */
    public String getColumnText(int columnIndex) {
        String columnText = ""; //$NON-NLS-1$
        if (columnIndex == 0) {
            if (insertColumn != null) {
                columnText = insertColumn.getName();
            }
        }
        else if (columnIndex == 1) {
            if (valueExpr != null) {
                columnText = valueExpr.getSQL();
            }
        }
        return columnText;
    }

    public QueryValueExpression showExpressionBuilder(Object obj, boolean isColumn, String action) {

        ExpressionBuilderWizard wizard;
        wizard = new ExpressionBuilderWizard(domainModel, domainModel.getSQLStatement());
        if (obj instanceof QueryValueExpression) {
            if (action.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)) {
                if (obj != null) {
                    wizard.setInputExpression((QueryValueExpression) obj);
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
        // Cancel pressed
        if (obj != null) {
            return (QueryValueExpression) obj; // return original expression
        }
        return null;

    }
    
    /**
     * Launches the input editor to optain user value
     * @param initialText the initial text to show in the editor
     * @return the input from the editor as a QueryValueExpression
     */
    protected QueryValueExpression showInputEditor(String initialText) {

    	MultilineInputDialog inputDialog = new MultilineInputDialog(Display.getDefault().getActiveShell(), 
    			Messages._UI_SPECIFY_VALUE_TITLE, insertColumn.getName());
    	inputDialog.setText(initialText);
    	inputDialog.open();
    	String text = inputDialog.getText();
        QueryValueExpression expression = ExpressionHelper.createExpression(text);
        return expression;
    }

}
