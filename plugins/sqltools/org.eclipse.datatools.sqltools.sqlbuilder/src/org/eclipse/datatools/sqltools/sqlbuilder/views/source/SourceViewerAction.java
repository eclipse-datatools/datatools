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
package org.eclipse.datatools.sqltools.sqlbuilder.views.source;

import java.util.ResourceBundle;

import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.IUpdate;
import org.eclipse.ui.texteditor.ResourceAction;

/**
 * An action which retrieves a <code>ITextOperationTarget</code> from its
 * current <code>TextEditor</code>, initializes its state according to
 * whether the configured operation can be executed on the target, and also
 * triggers the configured operation on run
 */
public class SourceViewerAction extends ResourceAction implements IUpdate {

    /** The action's editor */
    protected ISourceViewer fSourceViewer;

    /** The text operation code */
    protected int fOperationCode = -1;

    /** The text operation target */
    protected ITextOperationTarget fOperationTarget;

    private boolean isActive = false;

    /**
     * Creates and initializes the action.
     */
    public SourceViewerAction(ResourceBundle bundle, String prefix, ISourceViewer viewer) {
        super(bundle, prefix);
        setViewer(viewer);
    }

    /**
     * Creates and initializes the action based on the given operation code.
     */
    public SourceViewerAction(ResourceBundle bundle, String prefix, ISourceViewer viewer, int operationCode) {
        super(bundle, prefix);
        fSourceViewer = viewer;
        fOperationCode = operationCode;
        update();
    }

    /**
     * Retargets the action to the specified editor.
     */
    public void setViewer(ISourceViewer viewer) {
        fSourceViewer = viewer;
    }

    /**
     * @see Action#run()
     */
    public void run() {
        if (fOperationCode != -1 && fOperationTarget != null) {
            this.setAsActive(true);
            fOperationTarget.doOperation(fOperationCode);
        }
    }

    /**
     * Updates the enable/disable state of the action.
     */
    public void update() {
        boolean wasEnabled = isEnabled();

        if (fSourceViewer != null && fOperationCode != -1) {
            fOperationTarget = fSourceViewer.getTextOperationTarget();
        }
        else {
            fOperationTarget = null;
        }

        boolean isEnabled = (fOperationTarget != null && fOperationTarget.canDoOperation(fOperationCode));
        setEnabled(isEnabled);

        if (wasEnabled != isEnabled) {
            firePropertyChange(ENABLED, new Boolean(wasEnabled), new Boolean(isEnabled));
        }
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setAsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
