/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.profiles;

import org.eclipse.datatools.connectivity.db.generic.GenericDBPlugin;
import org.eclipse.datatools.connectivity.db.generic.ui.NewConnectionProfileWizard;

/**
 * Ingres connection profile wizard.
 * 
 * @author enrico.schenk@ingres.com
 */
public class NewIngresConnectionProfileWizard extends
        NewConnectionProfileWizard {

    /**
     * Constructor
     */
    public NewIngresConnectionProfileWizard() {
        super();
        setWindowTitle(GenericDBPlugin.getDefault().getResourceString(
                "NewConnectionProfileWizard.title")); //$NON-NLS-1$
    }

    /**
     * Add's the Ingres details page to the wizard
     * 
     * @see org.eclipse.datatools.connectivity.db.generic.ui.NewConnectionProfileWizard#addCustomPages()
     */
    public void addCustomPages() {
        mPropPage = new IngresProfileDetailsWizardPage("detailsPage"); //$NON-NLS-1$
        addPage(mPropPage);
    }

}
