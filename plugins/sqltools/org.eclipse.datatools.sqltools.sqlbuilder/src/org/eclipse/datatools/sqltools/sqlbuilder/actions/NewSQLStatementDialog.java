/*******************************************************************************
 * Copyright 2000, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


/**
 * This class implements a dialog which allows the user to name a new SQL
 * statement, select it's type, and select an editor with which to edit it.
 */
public class NewSQLStatementDialog extends Dialog implements ModifyListener {

    private NewSQLStatementComposite fDialogArea;
    private IInputValidator stmtNameValidator;

    /**
     * Creates an instance of this class.
     * 
     * @param parent the parent Shell for this dialog
     */
    public NewSQLStatementDialog(Shell parent, IInputValidator validator) {
        super(parent);
        stmtNameValidator = validator;
    }

    /**
     * Configures the given shell in preparation for opening this window in it.
     * 
     * @param shell the shell to configure
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);

        // Give this dialog a title.
        shell.setText(Messages.datatools_sqlbuilder_newStatementDialog_title);
    }

    /**
     * Creates this window's widgetry in a new top-level shell.
     * 
     * @see org.eclipse.jface.window.Window#create()
     */
    public void create() {
        super.create();

        // Disable the OK button since the name field is initially empty.
        getButton(IDialogConstants.OK_ID).setEnabled(false);
    }

    /**
     * Creates and returns the contents of the upper part of this dialog (above
     * the button bar).
     * 
     * @param parent the parent composite
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent) {
        fDialogArea = new NewSQLStatementComposite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 25;
        layout.marginWidth = 5;
        fDialogArea.setLayout(layout);

        // Add a listener to the statement name text control in the dialog area
        // composite. We need to enable or disable the OK button depending on
        // the state of the control.
        fDialogArea.getStatementNameText().addModifyListener(this);
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(fDialogArea, SQLBuilderContextIds.SQLD_NEW_STMT_DIALOG);
        
        return fDialogArea;
    }

    /**
     * Gets the composite that comprises the "dialog area" of this dialog. (That
     * is, the upper part of the dialog, above the button bar.)
     * 
     * @return the dialog area composite
     */
    public NewSQLStatementComposite getDialogAreaComposite() {
        return fDialogArea;
    }

    /**
     * Gets called when the text widget that this object is listening to is
     * modified.
     * 
     * @param evt the modify event
     */
    public void modifyText(ModifyEvent evt) {
        Object source = evt.getSource();
        if (source == fDialogArea.getStatementNameText()) {
            // Disable the OK button if statement name is invalid, otherwise
            // enable it.
            String statementName = fDialogArea.getStatementNameText().getText().trim();
            if (stmtNameValidator != null) {
                String error = stmtNameValidator.isValid(statementName);
                if (error != null) {
                    getButton(IDialogConstants.OK_ID).setEnabled(false);
                    fDialogArea.setErrorLabelText(error);
                }
                else {
                    getButton(IDialogConstants.OK_ID).setEnabled(true);
                    fDialogArea.setErrorLabelText(SQLBuilderConstants.EMPTY_STRING);
                }

            }
            else {
                getButton(IDialogConstants.OK_ID).setEnabled(true);
            }
        }
    }

}