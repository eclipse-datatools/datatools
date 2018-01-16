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

package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class CaseExpressionDialog extends WizardDialog {

    public CaseExpressionDialog(Shell shell, Wizard wizardArg) {
        super(shell, wizardArg);
        setShellStyle(SWT.MAX | SWT.MIN | SWT.CLOSE | SWT.RESIZE | SWT.APPLICATION_MODAL);
    }

}

