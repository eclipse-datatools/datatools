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
package org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts;

import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.ColumnEditPart.ColumnFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;

public class ColumnSelectionEditPolicy extends SelectionEditPolicy {

    protected void setSelectedState(int arg0) {
        super.setSelectedState(arg0);
        if (arg0 == EditPart.SELECTED) {
            ColumnEditPart editPart = (ColumnEditPart) getHost();
            boolean checkStatus = editPart.checkBox.isSelected();
            editPart.checkBoxAction(!checkStatus);
        }
    }

    protected void hideSelection() {
        ((ColumnFigure) getHostFigure()).setDeselectedColors();
    }

    /**
     * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#showSelection()
     */
    protected void showSelection() {
        ((ColumnFigure) getHostFigure()).setSelectedColors();
    }

}
