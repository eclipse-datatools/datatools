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

package org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect;

import java.util.List;

import org.eclipse.swt.widgets.Display;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

public class ValueTableElement {

    ValuesRow valuesRow;
    SQLDomainModel domainModel;
    QueryValueExpression expression;
    List exprList;

    public ValueTableElement(SQLDomainModel model, ValuesRow valRow, QueryValueExpression expr) {
        domainModel = model;
        valuesRow = valRow;
        expression = expr;
    }
    
    public void removeExpression() {
        if (expression != null) {
            List rowExprList = getValuesRow().getExprList();
            rowExprList.remove(expression);
        }
    }

    QueryValueExpression getExpression() {
        return expression;
    }

    public ValuesRow getValuesRow() {
        return valuesRow;
    }

    /**
     * Get the MOF object value
     */
    public String getColumnText(int columnIndex) {
        String colText = "";
        if (columnIndex == 0) {
            if (expression != null) {
                colText = expression.getSQL();
            }
        }
        return colText;
    }

    public void modify(Object key, Object propValue) {
        if (key == SQLBuilderConstants.P_STATEMENT_VALUE) {
            if (propValue instanceof String
                    && (((String) propValue).equals(SQLBuilderConstants.P_BUILD_EXPRESSION)
                            || ((String) propValue).equals(SQLBuilderConstants.P_EDIT_EXPRESSION) || ((String) propValue)
                            .equals(SQLBuilderConstants.P_REPLACE_EXPRESSION))) {
                QueryValueExpression expr = showExpressionBuilder(key, false, (String) propValue);
                if (expr != null) {
                    addExpression(expr);
                }
            }
            else if (propValue instanceof QueryValueExpression) {
                addExpression((QueryValueExpression) propValue);
            }
        }
    }

    void addExpression(QueryValueExpression newExpr) {
        if (valuesRow != null) {
            List rowExprList = valuesRow.getExprList();
            if (rowExprList != null) {
                int position = rowExprList.indexOf(expression);
                if (position != -1) {
                    rowExprList.add(position, newExpr);
                }
                rowExprList.add(newExpr);
            }
        }
    }

    /**
     * Launch the expression builder
     */

    public QueryValueExpression showExpressionBuilder(Object key, boolean isColumn, String action) {
        ExpressionBuilderWizard wizard;
        wizard = new ExpressionBuilderWizard(domainModel, domainModel.getSQLStatement());
        if (key == SQLBuilderConstants.P_STATEMENT_VALUE) {
            if (action.equals(SQLBuilderConstants.P_BUILD_EXPRESSION) || action.equals(SQLBuilderConstants.P_REPLACE_EXPRESSION))

            {
                wizard.setInputExpression(null);
            }
            else if (action.equals(SQLBuilderConstants.P_EDIT_EXPRESSION)) {
                if (expression != null) {
                    wizard.setInputExpression(expression);
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
}
