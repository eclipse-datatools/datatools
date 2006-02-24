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

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceEditorPageCore;
import org.eclipse.swt.widgets.Composite;

/**
 * The abstract base class for implementation of a customized
 * ODA data source property page.  
 * <br>An ODA data source UI extension must extend this base class to
 * provide customized property page with page control 
 * and related behavior, and implements the 
 * <code>org.eclipse.ui.propertyPages</code> extension point.
 */
public abstract class DataSourceEditorPage extends DataSourceEditorPageCore
{    
    /**
     * Creates and initializes custom control for this wizard page 
     * under the given parent composite.
     * @param parent        parent composite
     * @param profileProps  current custom properties and values 
     *                      to initialize the custom controls
     */
    protected abstract void createAndInitCustomControl( Composite parent,
                                        Properties profileProps );

    /**
     * Update profile's custom properties with values collected in 
     * custom page(s).
     * @param profileProps  the current set of custom properties,
     *                      before any updates in this page
     * @return  the updated custom profile properties and values
     */
    public abstract Properties collectCustomProperties( Properties profileProps );

    /**
     * Sub-class may override this method to further update
     * the given data source design, as needed.
     * <br>Examples of data source design updates include 
     * setting its private properties,
     * and a property's design attributes specified dynamically 
     * per data source instance.
     * <br>This method is called when performing finish on a
     * data source editing session.
     * @param design
     * @return
     */
    protected DataSourceDesign collectDataSourceDesign( DataSourceDesign design )
    {
        // sub-class may override, and update the given data source design
        return design;
    }

    
    public DataSourceEditorPage()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.wizards.ProfileDetailsPropertyPage#createCustomContents(org.eclipse.swt.widgets.Composite)
     */
    protected void createCustomContents( Composite parent )
    {
        Properties props = getDataSourceProperties();
        // calls abstract method provided by custom page extension
        createAndInitCustomControl( parent, props );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.wizards.ProfileDetailsPropertyPage#collectProperties()
     */
    protected Properties collectProperties()
    {
        Properties props = getDataSourceProperties();
        // calls abstract method provided by custom page extension
        return collectCustomProperties( props );
    }

    /**
     * Performs finish on the current ODA design session to
     * edit a data source design definition.
     * Calls subclass extended method to provide further
     * updates to the data source design definition.
     * @return  a new instance of edited data source design definition
     * @throws OdaException
     */
    protected DataSourceDesign finishEditDataSource()
        throws OdaException
    {
        // gets a copy of the data source design, and updates
        // with the pubic properties collected by
        // this editor page
        DataSourceDesign editedDesign = super.finishEditDataSource();

        // let subclass implementation further specifies the data source design
        editedDesign = 
            collectDataSourceDesign( editedDesign );
        return editedDesign;
    }
    
}
