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

import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.commands.SetLocationCommand;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

/**
 * This class responsible for the root view object layout policies
 */
public class RootViewXYLayoutEditPolicy extends XYLayoutEditPolicy {

    protected Command getCreateCommand(CreateRequest request) {
        return createChangeConstraintCommand((EditPart) (request.getNewObject()), getConstraintFor(request));
    }

    protected Command createAddCommand(EditPart child, Object constraint) {
        return createChangeConstraintCommand(child, constraint);
    }

    protected EditPolicy createChildEditPolicy(EditPart child) {
        return new ResizableEditPolicy();
    }

    /**
     * This command is invoked when the table is dragged from one
     * location to another
     * @param child - the MOF object that is backed by the TableEditPart
     */
    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        if (child instanceof TableEditPart) {
            return setLocationCommand((TableEditPart) child, constraint);
        }
        return null;
    }

    protected Command getDeleteDependantCommand(Request commandRequest) {
        return null;
    }

    protected Command getOrphanChildrenCommand(Request request) {
        return null;
    }

    /**
     * Create a SetLocationCommand and pass in the new location
     */
    private SetLocationCommand setLocationCommand(TableEditPart editPart, Object constraint) {
        SetLocationCommand locationCommand = new SetLocationCommand();

        locationCommand.setPart(editPart);
        locationCommand.setConstraint((Rectangle) constraint);

        return locationCommand;
    }
}
