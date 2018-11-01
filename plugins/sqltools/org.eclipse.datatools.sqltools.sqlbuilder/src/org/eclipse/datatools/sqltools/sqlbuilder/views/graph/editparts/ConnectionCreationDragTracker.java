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

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.tools.ConnectionDragCreationTool;

public class ConnectionCreationDragTracker extends ConnectionDragCreationTool implements DragTracker {

    private ColumnEditPart editpart;

    public ConnectionCreationDragTracker(ColumnEditPart owner) {
        super();
        editpart = owner;
    }

    protected boolean handleButtonDown(int button) {
        if (isInState(STATE_INITIAL) && button == 1) {
            updateTargetRequest();
            updateTargetUnderMouse();
            super.handleButtonDown(button);
            handleDrag();
            return true;
        }
        
        return super.handleButtonDown(button);
    }

    /**
     * @see org.eclipse.gef.tools.ConnectionDragCreationTool#handleButtonUp(int)
     */
    protected boolean handleButtonUp(int button) {

        int toolState = getState();
        if ((toolState != STATE_DRAG_IN_PROGRESS) && (toolState == STATE_DRAG)) {
            performConditionalSelection();
        }
        return super.handleButtonUp(button);
    }

    /**
     * @see org.eclipse.gef.tools.ConnectionDragCreationTool#handleDragStarted()
     */
    protected boolean handleDragStarted() {
        performConditionalSelection();
        return super.handleDragStarted();
    }

    protected void performConditionalSelection() {
        if (getSourceEditPart().getSelected() == EditPart.SELECTED_NONE)
            performSelection();
    }

    protected EditPart getSourceEditPart() {
        return editpart;
    }

    protected void performSelection() {
        EditPartViewer viewer = getCurrentViewer();
        java.util.List selectedObjects = viewer.getSelectedEditParts();

        if (getCurrentInput().isControlKeyDown()) {
            if (selectedObjects.contains(getSourceEditPart()))
                viewer.deselect(getSourceEditPart());
            else
                viewer.appendSelection(getSourceEditPart());
        }
        else if (getCurrentInput().isShiftKeyDown())
            viewer.appendSelection(getSourceEditPart());
        else
            viewer.select(getSourceEditPart());
    }

}
