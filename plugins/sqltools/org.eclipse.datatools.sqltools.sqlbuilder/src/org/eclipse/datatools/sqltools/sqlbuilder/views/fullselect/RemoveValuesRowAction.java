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

import java.util.Iterator;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;


/**
 * Remove the selected column from the MOF model
 */
public class RemoveValuesRowAction extends Action {

    TableViewer gridViewer;

    public RemoveValuesRowAction(TableViewer gridViewer) {
        super(Messages._UI_ACTION_REMOVE_VALUES_ROW);
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

            if (item instanceof ValueTableElement) {
                ValueTableElement tableElement = (ValueTableElement) item;
                tableElement.removeExpression();
            }
        }
    }
}
