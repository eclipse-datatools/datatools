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
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;

public class CaseElseClauseElement {

    private ValueExpressionCaseSearch sqlCaseSearchWhenClause = null;
    private ValueExpressionCaseSimple sqlCaseSimpleWhenClause = null;
    private Object result;

    public CaseElseClauseElement(ValueExpressionCaseSearch caseSearchClause) {
        this.sqlCaseSearchWhenClause = caseSearchClause;
    }

    public CaseElseClauseElement(ValueExpressionCaseSimple caseSimpleClause) {
        this.sqlCaseSimpleWhenClause = caseSimpleClause;
    }

    public ValueExpressionCaseSearch getSQLCaseSearchWhenClause() {
        return sqlCaseSearchWhenClause;
    }

    public ValueExpressionCaseSimple getSQLCaseSimpleWhenClause() {
        return sqlCaseSimpleWhenClause;
    }

    public void setResult(Object resultObj) {
        result = resultObj;
        if (sqlCaseSearchWhenClause != null) {
            if (sqlCaseSearchWhenClause.getCaseElse() != null) {
                sqlCaseSearchWhenClause.getCaseElse().setValueExpr((QueryValueExpression) result);
            }
        }
        else if (sqlCaseSimpleWhenClause != null) {
            if (sqlCaseSimpleWhenClause.getCaseElse() != null) {
                sqlCaseSimpleWhenClause.getCaseElse().setValueExpr((QueryValueExpression) result);
            }
        }
    }

    public Object getResult() {
        if (sqlCaseSearchWhenClause != null) {
            if (sqlCaseSearchWhenClause.getCaseElse() != null) {
                return sqlCaseSearchWhenClause.getCaseElse().getValueExpr();
            }
        }
        else if (sqlCaseSimpleWhenClause != null) {
            if (sqlCaseSimpleWhenClause.getCaseElse() != null) {
                return sqlCaseSimpleWhenClause.getCaseElse().getValueExpr();
            }
        }
        return null;
    }

    public String getColumnText(int columnIndex) {
        if (columnIndex == 0) {
            return "ELSE";
        }
        else if (columnIndex == 1) {
            if (getResult() != null) {
                return ((QueryValueExpression) getResult()).getSQL();
            }
        }

        return "";
    }

    public void deleteElseCondition() {
        if (sqlCaseSearchWhenClause != null) {
            if (sqlCaseSearchWhenClause.getCaseElse() != null) {
                sqlCaseSearchWhenClause.getCaseElse().setValueExpr(null);
            }
        }
        else if (sqlCaseSimpleWhenClause != null) {
            if (sqlCaseSimpleWhenClause.getCaseElse() != null) {
                sqlCaseSimpleWhenClause.getCaseElse().setValueExpr(null);
            }
        }
    }
}