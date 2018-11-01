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
package org.eclipse.datatools.sqltools.sqlbuilder.views.criteria;

import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;

/**
 * Invoke the Expression Builder to build the value part of the
 * current search condition
 */
public class CriteriaValueExpression extends Action {

    TableViewer gridViewer;

    public CriteriaValueExpression(TableViewer gridViewer) {
        super(Messages._UI_ACTION_UPDATE_VALUE_EXPRESSON);
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

            if (item instanceof CriteriaElement) {
                specifyValue((CriteriaElement) item);
            }
        }
    }

    private void specifyValue(CriteriaElement criteriaElement) {
        QueryValueExpression result = criteriaElement.showExpressionBuilder(false, SQLBuilderConstants.P_EDIT_EXPRESSION);

        if (result == null)
            return;

        criteriaElement.setElementProperty(CriteriaGridViewer.P_STATEMENT_VALUE, result);        
    }
}
