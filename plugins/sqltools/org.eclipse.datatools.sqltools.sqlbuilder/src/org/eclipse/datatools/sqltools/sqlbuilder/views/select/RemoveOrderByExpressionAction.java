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

package org.eclipse.datatools.sqltools.sqlbuilder.views.select;

import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;

/**
 * Remove the selected column from the MOF model
 */
public class RemoveOrderByExpressionAction extends Action {

    TableViewer gridViewer;

    public RemoveOrderByExpressionAction(TableViewer gridViewer) {
        super(Messages._UI_ACTION_REMOVE_ORDER_BY_EXPRESSION);
        this.gridViewer = gridViewer;
    }

    public void run() {
        ISelection selection = gridViewer.getSelection();

        if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
            return;

        IStructuredSelection es = (IStructuredSelection) selection;
        Iterator elements = es.iterator();

        gridViewer.cancelEditing();

        while (elements.hasNext()) {
            Object item = elements.next();

            if (item instanceof SelectTableElement) {
                SelectTableElement tableElement = (SelectTableElement) item;

                SQLQueryObject selectStatement = tableElement.getSelectStatement();

                QueryValueExpression sqlExpr = tableElement.getSQLExpression();

                if (sqlExpr != null) {
                    SelectHelper.removeColumnFromOrderBy(selectStatement, sqlExpr);
                    SelectHelper.refresh(selectStatement);
                }
            }
        }
    }
}