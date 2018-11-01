/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.jdbc.dbprofile.sample.ui.impl;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider;

/**
 *  Sample template implementation of IPropertyProvider that gets called
 *  when a data source design is exported to an external connection profile.
 */
public class ProfilePropertyProvider implements IPropertyProvider
{

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider#getDataSourceProperties(java.util.Properties, java.lang.Object)
     */
    public Properties getDataSourceProperties( Properties candidateProperties, Object appContext ) 
        throws OdaException
    {
        // exporting data source properties to be saved in an external db profile
        
        // makes a copy of the specified properties
        Properties profileProps = new Properties();
        profileProps.putAll( candidateProperties );
        
        // TODO - adjust the profile properties returned to be saved in an external connection profile
        
        return profileProps;
    }

}
