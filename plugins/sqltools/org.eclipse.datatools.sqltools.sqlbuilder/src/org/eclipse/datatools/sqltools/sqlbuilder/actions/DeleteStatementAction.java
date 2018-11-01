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
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;

/**
 * Delete a SQL object
 */
public class DeleteStatementAction extends Action {

    Object object;
    SQLDomainModel domainModel;
    ContentOutlinePage outline;

    public DeleteStatementAction(SQLDomainModel domainModel, ContentOutlinePage outline) {
        super(Messages._UI_ACTION_DELETE_STATEMENT);
        this.domainModel = domainModel;
        this.outline = outline;
    }

    public void setElement(Object obj) {
        object = obj;
    }

    public void run() {
        Object newObjectToSelect = null;
        if (object instanceof QueryExpressionBody) {
            QueryExpressionBody queryExprBody = (QueryExpressionBody) object;
            QueryCombined parent = queryExprBody.getCombinedRight();
            if (parent != null) {
                // this is a right side child
                parent.setRightQuery(null);
            }
            else {
                parent = queryExprBody.getCombinedLeft();
                if (parent != null) {
                    //this is the left side child , so move the right child to this node's place
                    QueryExpressionBody rightChild = parent.getRightQuery();
                    parent.setLeftQuery(rightChild);
                }
            }
        }
        else if (object instanceof ValuesRow) {
            ValuesRow row = (ValuesRow) object;
            QueryValues values = row.getQueryValues();
            List rowList = values.getValuesRowList();
            rowList.remove(row);
        }
        else if (object instanceof WithTableSpecification) {
            WithTableSpecification withTable = (WithTableSpecification) object;
            QueryExpressionRoot qRoot = withTable.getQueryExpressionRoot();
            List wTableList = withTable.getWithTableReferences();
            if (wTableList.size() > 0) {
                for (int i = 0; i < wTableList.size(); i++) {
                    StatementHelper
                            .removeTableExpressionFromQueryStatement((TableExpression) wTableList.get(i), (SQLQueryObject) domainModel.getSQLStatement());
                }
            }
            qRoot.getWithClause().remove(object);
            newObjectToSelect = domainModel.getSQLStatement();
            SelectHelper.refresh(domainModel.getSQLStatement());
        }

        if (newObjectToSelect != null) {
            outline.setSelection(new StructuredSelection(newObjectToSelect));
        }

    }
}
