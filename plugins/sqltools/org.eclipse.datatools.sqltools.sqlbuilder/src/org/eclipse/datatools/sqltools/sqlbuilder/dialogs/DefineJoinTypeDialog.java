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

package org.eclipse.datatools.sqltools.sqlbuilder.dialogs;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


/**
 * Specify the join type - inner, outer, full etc
 */

public class DefineJoinTypeDialog extends org.eclipse.jface.dialogs.Dialog {

    int joinKind;
    SQLDomainModel domainModel;
    String dialogInstr = "";
    DefineTypeComposite defineTypeComposite;

    //
    // Creates a dialog with an OK and Cancel button.
    //
    public DefineJoinTypeDialog(Shell shell, int kind, SQLDomainModel domainModel) {
        super(shell);
        joinKind = kind;
        this.domainModel = domainModel;
        setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.BORDER | SWT.SYSTEM_MODAL);
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages._UI_DEFINE_JOIN_DIALOG_TITLE);
    }

    //
    // createInner method comment.
    //
    public Control createDialogArea(Composite parent) {
        defineTypeComposite = new DefineTypeComposite(parent, domainModel, joinKind);
        return defineTypeComposite.getControl();
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == Dialog.OK) {
            joinKind = defineTypeComposite.getJoinKind();
        }
        close();
    }

    /**
     * Return the Join type
     */
    public int getJoinType() {
        return joinKind;
    }
}
