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

import org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn;
import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;

/**
 * Order by for a fullselect
 */
public class OrderByTableElement {

    protected OrderBySpecification orderBy;
    protected QueryValueExpression columnExpr;

    protected QuerySelectStatement fullSelect;

    public OrderByTableElement(QuerySelectStatement target, OrderBySpecification ordrBy) {
        fullSelect = target;
        orderBy = ordrBy;
    }

    public QuerySelectStatement getFullSelectStatement() {
        return fullSelect;
    }

    public OrderBySpecification getOrderBy() {
        return orderBy;
    }

    /**
     * Set the mof object from the grid value
     */
    public void modify(Object key, Object propValue) {
        if (key == SQLBuilderConstants.P_STATEMENT_COLUMN && propValue instanceof QueryValueExpression) {
            SelectHelper.appendOrderByColumn(fullSelect, (QueryValueExpression) propValue, getSortType());
        }
        else if (key == SQLBuilderConstants.P_STATEMENT_SORTTYPE) {
            updateSortType((String) propValue);
        }
        else if (key == SQLBuilderConstants.P_STATEMENT_SORTORDER) {
            updateSortOrder((String) propValue);
        }
        SelectHelper.refresh(fullSelect);
    }

    /**
     * Get the mof value and return it
     */
    public String getColumnText(int columnIndex) {
        String colText = "";
        if (columnIndex == 0 && orderBy != null) {
            colText = getOrderBySQL();
        }
        else if (columnIndex == 1) // Sort type
        {
            colText = getSortType();
        }
        else if (columnIndex == 2) // Sort order
        {
            colText = getSortOrder();
        }
        return colText;
    }

    String getOrderBySQL() {
        String sql = "";
        if (orderBy instanceof OrderByValueExpression) {
            sql = ((OrderByValueExpression) orderBy).getValueExpr().getSQL();
        }
        else if (orderBy instanceof OrderByResultColumn) {
            sql = ((OrderByResultColumn) orderBy).getResultCol().getSQL();
        }
        else if (orderBy != null) {
            sql = orderBy.getSQL();
        }
        return sql;
    }    

    /**
     * Update the sort type - ascending, descending or none
     */
    private void updateSortType(String value) {
        if (orderBy != null && value != null) {
            if (value.equalsIgnoreCase(Messages._UI_COMBO_SORT_ASCENDING)) {
                orderBy.setDescending(false);
            }
            else if (value.equalsIgnoreCase(Messages._UI_COMBO_SORT_DESCENDING)) {
                orderBy.setDescending(true);
            }
        }
    }
    
    // UI shows position starting from 1. Model uses position starting from 0.
    private void updateSortOrder(String value) {
        if (fullSelect != null && orderBy != null) {
            int position = Integer.parseInt(value) - 1;
            List orderList = fullSelect.getOrderByClause();
            SelectHelper.moveOrderByToPosition(orderBy, orderList, position);            
        }
    }    

    /**
     * return the order by 
     */
    String getOrderByExpr() {
        String exprString = "";
        if (orderBy != null) {
            exprString = orderBy.getSQL();
        }
        return exprString;
    }

    String getSortType() {
        String result = "";
        if (orderBy != null) {
            boolean isDescending = orderBy.isDescending();
            if (isDescending) {
                result = Messages._UI_COMBO_SORT_DESCENDING;
            }
            else {
                result = Messages._UI_COMBO_SORT_ASCENDING;
            }            
        }
        return result;

    }

    String getSortOrder() {
        String sortOrder = "";
        if (fullSelect != null && orderBy != null) {
            List orderList = fullSelect.getOrderByClause();
            int position = orderList.indexOf(orderBy);
            if (position != -1) {
                sortOrder = (new Integer(position + 1)).toString();
            }
        }
        return sortOrder;
    }
}
