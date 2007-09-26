/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts;

import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.ColumnEditPart.ColumnFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;

/*
 * Licensed Materials - Property of IBM
 * org.eclipse.datatools.sqltools.sqlbuilder
 * (C) Copyright IBM Corporation 2000, 2005. All Rights Reserved.
 *
 * Note to U.S. Government Users Restricted Rights:
 *   Use, duplication or disclosure restricted 
 *   by GSA ADP Schedule Contract with IBM Corp.
 */
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
