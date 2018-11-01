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
package org.eclipse.datatools.sqltools.sqlbuilder.views.insert;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.InsertHelper;

/**
 * Remove the inserted column from the MOF model
 */
public class RemoveInsertColumnAction extends Action {

    TableViewer gridViewer;

    public RemoveInsertColumnAction(TableViewer gridViewer) {
        super(Messages._UI_ACTION_REMOVE_COLUMN);
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

            if (item instanceof InsertTableElement) {
                removeColumn((InsertTableElement) item);
            }
        }
    }

    private void removeColumn(InsertTableElement insertElement) {
        QueryInsertStatement insertStmt = insertElement.getInsertStatement();
        List columnList = insertStmt.getTargetColumnList();
        if (columnList != null) {
            ValueExpressionColumn col = insertElement.getColumn();
            
            InsertHelper.removeColumn((QueryInsertStatement) insertStmt, col);        
        } 
    }
}
