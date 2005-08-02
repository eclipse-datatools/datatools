/*******************************************************************************
 * Copyright (c) 2002, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.wizards;

import org.eclipse.datatools.connectivity.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Creates a new snippet page
 */
public class NewSQLScrapbookFileCreationWizard extends Wizard implements
        INewWizard {

    private NewSQLScrapbookFileWizardPage fPage;

    private IStructuredSelection fSelection;

    private ConnectionInfo connectionInfo; // tau 04.07.04

    public NewSQLScrapbookFileCreationWizard() {
        setNeedsProgressMonitor(true);
        setWindowTitle(SqlscrapbookPlugin
                .getResourceString("NewSQLScrapbookFileCreationWizard.title")); //$NON-NLS-1$
    }

    /*
     * @see Wizard#addPages
     */
    public void addPages() {
        super.addPages();
        if (fSelection == null) {
            fSelection = StructuredSelection.EMPTY;
        }
        fPage = new NewSQLScrapbookFileWizardPage(fSelection);
        addPage(fPage);
    }

    /*
     * @see Wizard#performFinish
     */
    public boolean performFinish() {
        return fPage.finish(connectionInfo); // tau 04.07.04
    }

    /*
     * @see org.eclipse.ui.IWorkbenchWizard#init
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        fSelection = selection;
        // setDefaultPageImageDescriptor(JavaPluginImages.DESC_WIZBAN_NEWSCRAPPAGE);
    }

    /**
     * @param connection
     *            The connection to set. tau 04.07.04
     */
    public void setConnectionInfo(ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }
}
