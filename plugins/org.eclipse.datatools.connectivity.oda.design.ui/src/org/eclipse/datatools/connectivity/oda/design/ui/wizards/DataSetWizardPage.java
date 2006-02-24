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
    private DataSetDesign m_dataSetDesign;
    
    /**
     * Creates customized control for this wizard page under the
     * given parent composite.
     * @param parent    the parent composite
     */
    public abstract void createPageCustomControl( Composite parent );

    /**
     * Sets the initial data set design definition to initialize the
     * customized control of this wizard page.
     * <br>This method may be called before #createPageCustomControl, 
     * which should then initialize with the given design instance.
     * @param dataSetDesign   a previously defined ODA data set design instance
     */
    public abstract void setInitialDesign( DataSetDesign dataSetDesign );

    /**
     * Returns the updated data set design instance with values 
     * collected in the custom wizard page(s).
     * <br>This method is called when the wizard performs finish.
     * @return
     */
    public DataSetDesign collectDataSetDesign()
    {
        // TODO
        return m_dataSetDesign;
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

}
