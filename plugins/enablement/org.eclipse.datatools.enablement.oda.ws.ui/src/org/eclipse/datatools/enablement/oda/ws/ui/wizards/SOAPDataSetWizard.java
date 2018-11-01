/*
 *************************************************************************
 * Copyright (c) 2010 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.enablement.oda.ws.ui.wizards;

import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizard;
import org.eclipse.jface.wizard.IWizardPage;

/**
 *  Extended implementation of the ODA Data Set Wizard.
 */
public class SOAPDataSetWizard extends DataSetWizard
{

    public SOAPDataSetWizard()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPage(org.eclipse.jface.wizard.IWizardPage)
     */
    @Override
    public void addPage( IWizardPage page )
    {
        // skip the SOAPParametersPage if editing an existing data set design
        if( page instanceof SOAPParametersPage && ! isCreatingNewDesign() )
            return;
        
        super.addPage( page );
    }

}
