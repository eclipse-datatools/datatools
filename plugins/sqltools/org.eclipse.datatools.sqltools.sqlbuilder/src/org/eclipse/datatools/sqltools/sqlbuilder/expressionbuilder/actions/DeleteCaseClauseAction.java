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

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseClauseElement;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseElseClauseElement;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseSearchPage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseSearchWhenContentElement;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseSimplePage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseSimpleWhenContentElement;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;


public class DeleteCaseClauseAction extends Action {

    TableViewer tableViewer;
    WizardPage wp;

    public DeleteCaseClauseAction(TableViewer tableViewer, WizardPage wp) {
        super(Messages._UI_ACTION_DELETE_CLAUSE);
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

        while (elements.hasNext()) {
            Object item = elements.next();

            if (item instanceof CaseSearchWhenContentElement) {
                CaseSearchWhenContentElement caseSearchWhenContentElement = (CaseSearchWhenContentElement) item;
                caseSearchWhenContentElement.deleteSearchCondition();
                tableViewer.refresh();
                if (wp instanceof CaseSearchPage) {
                    ((CaseSearchPage) wp).updateFinishButton();
                }
            }
            else if (item instanceof CaseSimpleWhenContentElement) {
                CaseSimpleWhenContentElement caseSimpleWhenContentElement = (CaseSimpleWhenContentElement) item;
                caseSimpleWhenContentElement.deleteSimpleWhenClause();
                tableViewer.refresh();
                if (wp instanceof CaseSimplePage) {
                    ((CaseSimplePage) wp).updateFinishButton();
                }
            }
            else if (item instanceof CaseClauseElement) {
                CaseClauseElement caseClauseElement = (CaseClauseElement) item;
                caseClauseElement.deleteCaseClause();
                tableViewer.refresh();
                if (wp instanceof CaseSimplePage) {
                    ((CaseSimplePage) wp).updateFinishButton();
                }
            }
            else if (item instanceof CaseElseClauseElement) {
                CaseElseClauseElement caseElseClauseElement = (CaseElseClauseElement) item;
                caseElseClauseElement.deleteElseCondition();
                tableViewer.refresh();
                if (wp instanceof CaseSearchPage) {
                    ((CaseSearchPage) wp).updateFinishButton();
                }
                else if (wp instanceof CaseSimplePage) {
                    ((CaseSimplePage) wp).updateFinishButton();
                }
            }
        }
    }
}