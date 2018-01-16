/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.profiles;

import org.eclipse.datatools.connectivity.db.generic.ui.GenericDBProfileDetailsWizardPage;

/**
 * Ingres specific wizard page for the connection profile.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresProfileDetailsWizardPage extends
        GenericDBProfileDetailsWizardPage {

    /**
     * Constructor
     * 
     * @param pageName
     *                the page name
     */
    public IngresProfileDetailsWizardPage(String pageName) {
        super(pageName);
        
        // define driver category for this property page, so only Ingres related
        // drivers will be shown in the driver selection dialog
        setDriverCategory("org.eclipse.datatools.enablement.ingres.2006.driverCategory"); //$NON-NLS-1$
    }

}
