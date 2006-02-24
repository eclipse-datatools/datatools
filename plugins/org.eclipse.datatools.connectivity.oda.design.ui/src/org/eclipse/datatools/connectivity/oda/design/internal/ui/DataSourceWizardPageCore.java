/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.internal.ui;

import org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * The core implementation of the Data Source Wizard Page base class 
 * provided in the ODA designer UI framework.  It extends the 
 * DTP connection profile wizard framework.  
 * <br>It can be used, outside of the Data Source 
 * Explorer UI, by an user to create an extended ODA data source definition 
 * with connection properties and corresponding values.
 */
public abstract class DataSourceWizardPageCore extends
        ConnectionProfileDetailsPage
{

    /*
     * Implements base class constructor.
     */
    public DataSourceWizardPageCore( String name )
    {
        super( name );
    }

    /*
     * Implements base class constructor.
     */
    public DataSourceWizardPageCore( String pageName, String title,
            ImageDescriptor titleImage )
    {
        super( pageName, title, titleImage );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizardPage#getNextPage()
     */
    public IWizardPage getNextPage()
    {
        // skip profile summary page if in ODA design session
        NewDataSourceWizardBase wizard = getOdaWizard();       
        return( wizard != null && wizard.isInOdaDesignSession() ) ? 
                null : super.getNextPage();
    }

    /**
     * Returns the page's wizard container that is extended
     * in the ODA Design UI framework to handle creation
     * of a new data source design.
     * @return
     */
    protected NewDataSourceWizardBase getOdaWizard()
    {
        if( getWizard() instanceof NewDataSourceWizardBase )
            return (NewDataSourceWizardBase) getWizard();
        return null;
    }

}
