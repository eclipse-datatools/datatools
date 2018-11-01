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

import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;

public class FullSelectTableElement {    

    QueryExpressionBody queryExprBody;
    QueryCombined parent;
    QueryCombinedOperator operator;

    public FullSelectTableElement(QueryExpressionBody query, QueryCombinedOperator opr, QueryCombined parentNode) {
        queryExprBody = query;
        operator = opr;
        parent = parentNode;
    }

    public QueryExpressionBody getQuery() {
        return queryExprBody;
    }

    public String getColumnText(int columnIndex) {
        String colText = "";

        if (columnIndex == 0) {
            //TODO change after a proper name generation is implemented
            if (queryExprBody != null) {
                if (queryExprBody instanceof QueryCombined) {
                    colText = Messages._UI_VIEWS_FULLSELECTELEMENT_FULLSELECT;
                }
                else if (queryExprBody instanceof QuerySelect) {
                    colText = Messages._UI_VIEWS_FULLSELECTELEMENT_SELECT;
                }
                else if (queryExprBody instanceof QueryValues) {
                    colText = Messages._UI_VIEWS_FULLSELECTELEMENT_VALUES;
                }

            }
        }
        else if (columnIndex == 1) {
            if (operator != null) {
                colText = SelectHelper.getCombinedOperatorSQLString(operator.toString());
            }
        }
        return colText;
    }    

    public void modify(Object key, Object propValue) {
        if (key == SQLBuilderConstants.P_STATEMENT_OPERATOR && propValue instanceof String) {
            if (operator != null) {//if operator is null, then this element 
                //represents the right side child, hence no need to set

                String value = ((String) propValue).toUpperCase();
                String opString = SelectHelper.getCombinedOperatorStringConstant(value);
                QueryCombinedOperator newOp = QueryCombinedOperator.get(opString);
                if (newOp != null) {
                    operator = newOp;
                }
                parent.setCombinedOperator(newOp);
            }
        }

        QueryStatement stmt = StatementHelper.getQueryStatementForTableReference(parent);
        if (stmt != null) {
            SelectHelper.refresh(stmt);
        }
    }    
}