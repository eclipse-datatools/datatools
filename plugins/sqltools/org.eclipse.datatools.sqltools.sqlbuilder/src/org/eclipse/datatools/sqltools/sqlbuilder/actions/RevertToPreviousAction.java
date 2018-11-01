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
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.SQLBuilderRevertActionDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLSourceViewer;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;


/**
 * This class implements an action which prompts the user whether or not to revert 
 * to last known good (parsable) version of the statement. 
 */
public class RevertToPreviousAction extends SQLBuilderAction {

    /** A dialog to prompt the user whether or not to revert to the previous
     * known parsable version of the statement. */
    private SQLBuilderRevertActionDialog fRevertDialog;

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public RevertToPreviousAction() {
        this(Messages._UI_REVERT_TO_LAST_CORRECT_SOURCE);
    }

    /**
     * Constructs an instance of this class with the given action label.
     * 
     * @param label the action label to use
     */
    public RevertToPreviousAction(String label) {
        super(label);
    }

    /**
     * Runs the action.  The user is prompted whether or not to revert to the previous
     * good (parsable) version of the statement.
     */
    public void run() {
        Shell shell = getShell();
        if (shell != null) {
            if (fRevertDialog == null) {
                String[] buttons = { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL };
                fRevertDialog = new SQLBuilderRevertActionDialog(shell,
                        Messages._UI_REVERT_TO_LAST_CORRECT_SOURCE, null, null, MessageDialog.NONE, buttons, 0);   
            }
            
            if (getSQLBuilder() != null) {
                SQLSourceViewer sourceViewer = getSQLBuilder().getSourceViewer();
                if (sourceViewer != null) {
                    fRevertDialog.setProperSourceString(sourceViewer.getLastKnownProperSource());
                    fRevertDialog.create();
                    int buttonIndex = fRevertDialog.open();
                    if (buttonIndex == 0) {
                        sourceViewer.revertToLastKnownProperSource();
                    }
                }
            }
        }
    }
    
} // end class