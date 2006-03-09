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

package org.eclipse.datatools.connectivity.oda.design.ui.wizards;

import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSetWizardPageCore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;

/**
 * The abstract base class for implementation of a customized
 * ODA data set wizard page.  
 * An ODA data set UI extension must extend this to
 * provide customized wizard page with page control and related behavior.
 * This is the base class of 
 * the <i>dataSetPage.wizardPageClass</i> attribute defined in the
 * <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code> extension point.
 */
public abstract class DataSetWizardPage extends DataSetWizardPageCore
{
    /**
     * Creates customized control for this wizard page under the
     * given parent composite.  
     * Initializes control as appropriate
     * with the DataSetDesign initialization instance.
     * @param parent    the parent composite
     * @see #getInitializationDesign
     */
    public abstract void createPageCustomControl( Composite parent );

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSetWizardPageCore#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
     */
    protected DataSetDesign collectDataSetDesign( DataSetDesign design )
    {
        // default implementation does nothing;
        // sub-class may override to update the given data set design
        return design;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSetWizardPageCore#cleanup()
     */
    protected void cleanup()
    {
        // default implementation does nothing;
        // sub-class may override
    }

    /**
     * Constructor with single argument for wizard page name.
     * This single-argument constructor is used by the 
     * ODA data set wizard to create a custom wizard page.
     * Subclass may override to provide additional page attributes, 
     * such as customized title, message, title image.
     * @param pageName  wizard page name
     */
    public DataSetWizardPage( String pageName )
    {
        super( pageName );
    }

    /*
     * Implements base class constructor.
     */
    public DataSetWizardPage( String pageName, String title, 
                ImageDescriptor titleImage )
    {
        super( pageName, title, titleImage );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl( Composite parent )
    {
        super.createControl( parent );
        createPageCustomControl( parent );
    }

}
