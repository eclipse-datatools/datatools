/*
 *************************************************************************
 * Copyright (c) 2006 - 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  IBM Corporation - defect fix #213266
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceWizardPageCore;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.DesignerUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * The abstract base class for implementation of a customized
 * ODA data source wizard page.  
 * An ODA data source UI extension must extend this to
 * provide customized wizard page with page control and related behavior.
 * This must be used as the base class of 
 * the <i>newWizard.class</i> attribute defined in the
 * <code>org.eclipse.datatools.connectivity.connectionProfile</code> extension point.
 */
public abstract class DataSourceWizardPage extends DataSourceWizardPageCore
{
    /**
     * Creates customized control for this wizard page under the
     * given parent composite.
     * @param parent    the parent composite
     */
    public abstract void createPageCustomControl( Composite parent );

    /**
     * Sets the initial properties and values to initialize the
     * customized control of this wizard page.
     * <br>This method may be called before #createPageCustomControl, 
     * which should then initialize with these given properties.
     * @param dataSourceProps   custom properties of the extended
     *                          ODA data source
     */
    public abstract void setInitialProperties( Properties dataSourceProps );

    /**
     * Returns custom properties updated with values 
     * collected in custom wizard page(s).
     * @return  a collection of custom properties; may be empty
     */
    public abstract Properties collectCustomProperties();

    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceWizardPageCore#collectDataSourceDesign(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     */
    protected DataSourceDesign collectDataSourceDesign( DataSourceDesign design )
    {
        // default implementation does nothing;
        // sub-class may override to update the given data source design
        return design;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceWizardPageCore#cleanup()
     */
    protected void cleanup()
    {
        // default implementation does nothing;
        // sub-class may override
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceWizardPageCore#refresh()
     */
    public void refresh()
    {
        // default implementation does nothing;
        // sub-class may override
    }

    /**
     * An utility method to enable/disable the specified control and all its nested
     * children, according to the specified <code>enabled</code> state.
     * The TestConnection button state is excluded from the state changes.
     * This is typically used by an extended refresh method.
     * @param parent  a control
     * @param enabled   the new enabled state
     * @since 3.0.4
     */
    protected void enableAllControls( Control parent, boolean enabled )
    {
        DesignerUtil.enableAllControls( parent, enabled, this.btnPing );
    }
    
    /**
     * Constructor with single argument for wizard page name.
     * This single-argument constructor is used by the 
	 * ODA data source wizard to create a custom wizard page.
     * Subclass may override to provide additional page attributes, 
     * such as customized title, message, title image.
     * @param pageName  wizard page name
     */
    public DataSourceWizardPage( String pageName )
    {
        super( pageName );
    }

    /*
     * Implements base class constructor.
     */
    public DataSourceWizardPage( String pageName, String title, ImageDescriptor titleImage )
    {
        super( pageName, title, titleImage );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage#createCustomControl(org.eclipse.swt.widgets.Composite)
     */
    public void createCustomControl( Composite parent )
    {
        // calls abstract method provided by custom extension
        createPageCustomControl( parent );
        refresh();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceWizardPageCore#getOdaDataSourceId()
     */
    public String getOdaDataSourceId()
    {
        return super.getOdaDataSourceId();
    }

}
