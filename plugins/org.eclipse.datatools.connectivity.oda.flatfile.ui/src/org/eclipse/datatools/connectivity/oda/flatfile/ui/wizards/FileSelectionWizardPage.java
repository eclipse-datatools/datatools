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

package org.eclipse.datatools.connectivity.oda.flatfile.ui.wizards;

import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;

/**
 *
 */
public class FileSelectionWizardPage extends DataSetWizardPage
{

    /**
     * @param pageName
     */
    public FileSelectionWizardPage( String pageName )
    {
        super( pageName );
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pageName
     * @param title
     * @param titleImage
     */
    public FileSelectionWizardPage( String pageName, String title,
            ImageDescriptor titleImage )
    {
        super( pageName, title, titleImage );
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPageCustomControl( Composite parent )
    {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#setInitialDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
     */
    public void setInitialDesign( DataSetDesign dataSetDesign )
    {
        // TODO Auto-generated method stub

    }

}
