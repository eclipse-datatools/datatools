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

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;

public class CaseClauseElement {

    private ValueExpressionCaseSimple sqlCaseSimpleWhenClause = null;
    private Object testExpr;

    public CaseClauseElement(ValueExpressionCaseSimple caseSimpleClause) {
        this.sqlCaseSimpleWhenClause = caseSimpleClause;
    }

    public ValueExpressionCaseSimple getSQLCaseSimpleWhenClause() {
        return sqlCaseSimpleWhenClause;
    }

    public void setCaseClause(Object testExprObj) {
        testExpr = testExprObj;
        if (sqlCaseSimpleWhenClause != null) {
            if (testExpr != null) {
                sqlCaseSimpleWhenClause.setValueExpr((QueryValueExpression) testExpr);
            }
            else {
                sqlCaseSimpleWhenClause.setValueExpr(null);
            }
        }
    }

    protected Object getCaseClause() {
        if (sqlCaseSimpleWhenClause != null) {
            return sqlCaseSimpleWhenClause.getValueExpr();
        }
        return null;
    }

    public String getColumnText(int columnIndex) {
        if (columnIndex == 0) {
            return "CASE";
        }
        else if (columnIndex == 1) {
            if (getCaseClause() != null) {
                return getCaseClause().toString();
            }
        }

        return "";
    }

    public void deleteCaseClause() {
        if (sqlCaseSimpleWhenClause != null) {
            sqlCaseSimpleWhenClause.setValueExpr(null);
        }
    }
}