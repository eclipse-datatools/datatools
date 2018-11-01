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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr;

import java.lang.ref.WeakReference;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;

public class ExpressionElement {

    private QueryValueExpression expression;
    private ValueExpressionCombinedOperator operator = null;
    private WeakReference exprElementVectorRef;

    // paramNum is used when this class is used to represent the rows of an expression whcih forms a paramter to
    // a function.All the rows which for a parameter will have the same paramNum value
    int paramNum = 0;

    public ExpressionElement(QueryValueExpression expr, ValueExpressionCombinedOperator opr) {
        expression = expr;
        operator = opr;
    }

    public ExpressionElement(QueryValueExpression expr, ValueExpressionCombinedOperator opr, int paramNo) {
        expression = expr;
        operator = opr;
        paramNum = paramNo;
    }

    public QueryValueExpression getExpression() {
        return expression;
    }

    // This is method used when this class is used for function parameter value grid
    public int getParameterNum() {
        return paramNum;
    }

    // This method is used when this class is used for function parameter value grid
    public String getColumnLabel() {
        String label = "...";
        Vector elements = getElementsVector();
        ExpressionElement firstElement = (ExpressionElement) elements.get(0);
        if (this.equals(firstElement)) {
            int paramNo = firstElement.getParameterNum();
            label = "#" + String.valueOf(paramNo + 1);
        }
        return label;
    }

    public Vector getElementsVector() {
        Vector elements = null;
        if (exprElementVectorRef != null) {
            elements = (Vector) exprElementVectorRef.get();
        }
        return elements;
    }

    public void setOperator(String newOpString) {
        ValueExpressionCombinedOperator newOperator = ExpressionHelper.createCombinedOperator(newOpString);
        if (newOperator != null) {
            if (operator == null) {
                // new level to be created in the tree by adding  a node.
                ExpressionHelper.addExpression(expression, null, newOperator);
            }
            else {
                ExpressionHelper.replaceOperator(expression, newOperator);
            }
        }
    }

    public ValueExpressionCombinedOperator getOperator() {
        return operator;
    }

    public String getOperatorString() {
        String opStr = "";
        if (operator != null) {
            opStr = ExpressionHelper.getDisplayString(operator.toString());
        }
        return opStr;
    }

    ValueExpressionCombinedOperator getOperatorFromPreviousRow() {
        ValueExpressionCombinedOperator opr = null;
        if (exprElementVectorRef != null) {
            Vector elements = (Vector) exprElementVectorRef.get();
            int currentPos = elements.indexOf(this);
            if (!((currentPos - 1) < 0)) {
                ExpressionElement element = (ExpressionElement) elements.get(currentPos - 1);
                opr = element.getOperator();
            }
        }
        return opr;
    }

    QueryValueExpression getPreviousRowExpr() {
        QueryValueExpression expr = null;
        if (exprElementVectorRef != null) {
            Vector elements = (Vector) exprElementVectorRef.get();
            int currentPos = elements.indexOf(this);
            if (!((currentPos - 1) < 0)) {
                ExpressionElement element = (ExpressionElement) elements.get(currentPos - 1);
                expr = element.getExpression();
            }
        }
        return expr;
    }

    public void setExpression(QueryValueExpression expr) {
        QueryValueExpression newExpr = null;

        // create a copy of the expression, to be set in the expression tree.
        if (expr instanceof ValueExpressionColumn) {
            newExpr = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn) expr);
        }
        else {
            newExpr = expr; //TODO clone?
        }

        if (expression == null) {
            // there is no expression set in this element yet, add the new expression to the tree
            QueryValueExpression prevExpr = getPreviousRowExpr();
            ValueExpressionCombinedOperator opr = getOperatorFromPreviousRow();
            ExpressionHelper.addExpression(prevExpr, newExpr, opr);
        }
        else {
            // an expression is already set, so replace it with the new expression, in the tree
            ExpressionHelper.replaceExpression(expression, newExpr);
        }
        expression = newExpr;
    }

    public void deleteExpression() {
        ExpressionHelper.removeExpression(expression);
        expression = null;
        removeElement();
    }

    public void removeElement() {
        if (exprElementVectorRef != null) {
            Vector elements = (Vector) exprElementVectorRef.get();
            if (elements != null) {
                elements.remove(this);
            }
        }
    }

    public String getExpressionString() {
        String str = "";
        if (expression != null) {
            str = expression.getSQL();

        }
        return str;
    }

    public void setExprElementVectorRef(WeakReference newRef) {
        exprElementVectorRef = newRef;
    }
}