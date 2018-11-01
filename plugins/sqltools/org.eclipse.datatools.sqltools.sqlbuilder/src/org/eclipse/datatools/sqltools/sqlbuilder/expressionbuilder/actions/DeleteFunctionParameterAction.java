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
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.function.FunctionBuilderPage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr.ExpressionElement;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;

public class DeleteFunctionParameterAction extends Action {

    TableViewer tableViewer;
    WizardPage wp;

    public DeleteFunctionParameterAction(TableViewer tableViewer, WizardPage wp) {
        super(Messages._UI_ACTION_DELETE_PARAMETER);
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

        FunctionBuilderPage page = ((FunctionBuilderPage) wp);

        tableViewer.cancelEditing();

        if (es.size() == 1) {
            Object item = elements.next();
            if (item instanceof ExpressionElement) {
                ExpressionElement element = (ExpressionElement) item;
                Vector elementsVector = element.getElementsVector();
                ExpressionElement firstElement = null;
                if (elementsVector != null && !elementsVector.isEmpty()) {
                    firstElement = (ExpressionElement) elementsVector.get(0);
                }
                element.deleteExpression();
                int paramNo = element.getParameterNum();
                QueryValueExpression expr = null, root = null;
                expr = firstElement.getExpression();
                if (expr != null) {
                    root = ExpressionHelper.getRoot(expr);
                }
                page.setParamValue(paramNo, root);

            }
        }
        else { //multiple rows are selected in the UI grid for deletion
            Set treeSet = new TreeSet();
            while (elements.hasNext()) {
                Object item = elements.next();

                if (item instanceof ExpressionElement) {
                    ExpressionElement element = (ExpressionElement) item;
                    element.deleteExpression();

                    Vector elementsVector = element.getElementsVector();
                    if (element != null && !elementsVector.isEmpty()) {
                        ExpressionElement e = (ExpressionElement) elementsVector.get(0);
                        treeSet.add(e);
                    }
                }
            }

            Iterator itr = treeSet.iterator();
            while (itr.hasNext()) {
                ExpressionElement element = (ExpressionElement) itr.next(); //first element in the rows of the ui,
                // which build one param 
                int paramNo = element.getParameterNum();
                QueryValueExpression expr = element.getExpression();
                QueryValueExpression root = null;
                if (expr != null) {
                    root = ExpressionHelper.getRoot(expr);
                }

                page.setParamValue(paramNo, root);

            }
        }

        tableViewer.refresh();
        page.updateFinishButton();
    }
}