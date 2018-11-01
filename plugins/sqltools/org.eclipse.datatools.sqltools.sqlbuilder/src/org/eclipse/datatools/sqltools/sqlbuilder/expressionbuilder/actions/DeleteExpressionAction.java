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

package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.actions;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr.ExpressionElement;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr.ExpressionsByOperatorsPage;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;

public class DeleteExpressionAction extends Action {

    TableViewer tableViewer;
    WizardPage wp;

    public DeleteExpressionAction(TableViewer tableViewer, WizardPage wp) {
        super(Messages._UI_ACTION_DELETE_EXPRESSION);
        this.tableViewer = tableViewer;
        this.wp = wp;
    }

    public void run() {
        ISelection selection = tableViewer.getSelection();

        if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
            return;
        }

        IStructuredSelection es = (IStructuredSelection) selection;
        Iterator elements = es.iterator();

        tableViewer.cancelEditing();
        ExpressionElement expressionElement = null;
        while (elements.hasNext()) {
            Object item = elements.next();

            if (item instanceof ExpressionElement) {
                expressionElement = (ExpressionElement) item;
                expressionElement.deleteExpression();
                //tableViewer.refresh();

            }
        }
        if (expressionElement != null) {
            QueryValueExpression expr = null;
            Vector elementsVector = expressionElement.getElementsVector();
            if (elementsVector != null && !elementsVector.isEmpty()) {
                ExpressionElement e = (ExpressionElement) elementsVector.get(0);
                expr = e.getExpression();
            }
            if (expr == null) {
                // all the rows have been deleted hence set a new expression to build the tree
                expr = ExpressionHelper.createExpression();
            }
            if (wp instanceof ExpressionsByOperatorsPage) {
                ((ExpressionsByOperatorsPage) wp).setExpression(expr);
                ((ExpressionsByOperatorsPage) wp).updateFinishButton();
            }
            tableViewer.setInput(expr);
        }
    }
}