/*
 *************************************************************************
 * Copyright (c) 2007, 2010 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.profile.db.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.db.DbProfilePropertyPage;

/**
 *  The default ODA data source editor page that wraps the driver-contributed 
 *  property page of a Database connection profile type.
 *  It may be used directly as the page class in an org.eclipse.ui.propertyPages
 *  extension for an ODA data source type.
 */
public class DbProfileEditorPage extends DbProfilePropertyPage
{
    public DbProfileEditorPage()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.db.DbProfilePropertyPage#createTransientProfile(java.util.Properties)
     */
    @Override
    protected IConnectionProfile createTransientProfile(
            Properties connProperties )
    {
        // expose internal API method
        return super.createTransientProfile( connProperties );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.db.DbProfilePropertyPage#setDataSourceDesignProperties(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign, java.util.Properties)
     */
    @Override
    protected void setDataSourceDesignProperties( DataSourceDesign design,
            Properties propertyValuePairs ) throws OdaException
    {
        // expose internal API method
        super.setDataSourceDesignProperties( design, propertyValuePairs );
    }

}
