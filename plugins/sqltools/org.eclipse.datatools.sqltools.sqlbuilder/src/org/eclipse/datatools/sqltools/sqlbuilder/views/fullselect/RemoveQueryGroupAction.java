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

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;


/**
 * Remove the selected column from the MOF model
 */
public class RemoveQueryGroupAction extends Action {

    TableViewer gridViewer;

    public RemoveQueryGroupAction(TableViewer gridViewer) {
        super(Messages._UI_ACTION_REMOVE);
        this.gridViewer = gridViewer;
    }

    public void run() {
        ISelection selection = gridViewer.getSelection();

        if (selection.isEmpty() || !(selection instanceof IStructuredSelection))
            return;

        gridViewer.cancelEditing();
    }
}
