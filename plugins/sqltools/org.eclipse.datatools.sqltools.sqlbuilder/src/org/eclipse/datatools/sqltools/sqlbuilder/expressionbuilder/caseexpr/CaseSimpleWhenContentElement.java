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
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;

public class CaseSimpleWhenContentElement {

    private ValueExpressionCaseSimple sqlCaseSimpleWhenClause;
    private ValueExpressionCaseSimpleContent content;
    private boolean firstClause;
    private Object when = null, result = null;

    public CaseSimpleWhenContentElement(ValueExpressionCaseSimple caseSimpleClause, ValueExpressionCaseSimpleContent content, 
            boolean firstClause) {
        this.sqlCaseSimpleWhenClause = caseSimpleClause;
        this.content = content;
        this.firstClause = firstClause;
    }

    public ValueExpressionCaseSimple getSQLCaseSimpleWhenClause() {
        return sqlCaseSimpleWhenClause;
    }

    public ValueExpressionCaseSimpleContent getSQLCaseSimpleWhenContent(int i) {
        return (ValueExpressionCaseSimpleContent) sqlCaseSimpleWhenClause.getContentList().get(i);
    }

    public void setWhenExpression(Object whenObj) {
        when = whenObj;
        if (content == null) {
            initializeSimpleContent();
        }
        content.setWhenValueExpr((QueryValueExpression) when);
    }

    public void setResultExpression(Object resultObj) {
        result = resultObj;
        if (content == null) {
            initializeSimpleContent();
        }
        content.setResultValueExpr((QueryValueExpression) result);
    }

    private void initializeSimpleContent() {
        if (content == null) {
            SQLQueryModelFactory factory = SQLQueryModelFactoryImpl.eINSTANCE;

            content = factory.createValueExpressionCaseSimpleContent();
            content.setWhenValueExpr(ExpressionHelper.createExpression(""));
            content.setResultValueExpr(ExpressionHelper.createExpression(""));

            sqlCaseSimpleWhenClause.getContentList().add(content);
        }
    }

    protected void updateWhen() {
        QueryValueExpression expr;
        if (!(when instanceof QueryValueExpression)) {
            expr = ExpressionHelper.createExpression(when);

            if (expr != null) {
                content.setWhenValueExpr(expr);
            }
        }
        else {
            content.setWhenValueExpr((QueryValueExpression) when);
        }
    }

    protected void updateOperator() {
    }

    protected void updateResult() {
        QueryValueExpression expr;
        if (!(result instanceof QueryValueExpression)) {
            expr = ExpressionHelper.createExpression(result);

            if (expr != null) {
                content.setResultValueExpr(expr);
            }
        }
        else {
            content.setResultValueExpr((QueryValueExpression) result);
        }
    }

    public Object getWhen() {
        if (content != null) {
            return content.getWhenValueExpr();
        }
        return null;
    }

    protected String getOperator() {
        return "";
    }

    public Object getResult() {
        if (content != null) {
            return content.getResultValueExpr();
        }
        return null;
    }

    public void deleteSimpleWhenClause() {
        if (content != null) {
            sqlCaseSimpleWhenClause.getContentList().remove(content);
            content = null;
            if (sqlCaseSimpleWhenClause.getContentList().size() == 0) {
                initializeSimpleContent();
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
            if (content != null) {
                when = getWhen();
            }
            if (when != null) {
                if (when instanceof QueryValueExpression)
                    return ((QueryValueExpression) when).getSQL();

                return when.toString();
            }
        }
        else if (columnIndex == 2) {
            if (firstClause) {
                return "THEN";
            }

            return "....";
        }
        else if (columnIndex == 3) {
            if (content != null) {
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