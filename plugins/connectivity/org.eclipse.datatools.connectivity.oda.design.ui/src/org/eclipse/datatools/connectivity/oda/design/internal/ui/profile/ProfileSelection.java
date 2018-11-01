/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile;

import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.ProfileReferenceBase;

/**
 * An internal class that represents a selected connection profile instance 
 * collected from one of the Profile Selection wizard or editor pages.
 * For internal use only.
 * @since   3.0.4
 */
class ProfileSelection
{
    private String m_odaDataSourceId;
    private String m_dataSourceDesignName;
    private ProfileReferenceBase m_profileRef;
    
    ProfileSelection( String odaDataSourceId,
                                String newDataSourceName,
                                ProfileReferenceBase profileRef )
    {
        m_odaDataSourceId = odaDataSourceId;
        if( newDataSourceName == null )
            newDataSourceName = ""; //$NON-NLS-1$
        m_dataSourceDesignName = newDataSourceName;
        m_profileRef = profileRef;
    }

    /**
     * Returns the odaDataSourceId of the selected profile instance.
     * @return 
     */
    String getOdaDataSourceId()
    {
        return m_odaDataSourceId;
    }

    /**
     * Returns the dataSourceDesignName collected from profile page.
     */
    String getDataSourceDesignName()
    {
        return m_dataSourceDesignName;
    }

    /**
     * Returns the reference to the selected profile instance.
     */
    ProfileReferenceBase getProfileRef()
    {
        return m_profileRef;
    }
    
}