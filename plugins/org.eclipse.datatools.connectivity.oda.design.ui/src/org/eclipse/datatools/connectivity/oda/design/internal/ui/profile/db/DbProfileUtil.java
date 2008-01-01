/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.db;

import java.util.Properties;

import org.eclipse.datatools.connectivity.internal.ui.ProfileUIManager;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.connectivity.oda.profile.Constants;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile;
import org.eclipse.datatools.connectivity.ui.wizards.ProfilePropertyPage;

/**
 *  Utility class for the Database Connection Profile UI wrappers.
 */
public class DbProfileUtil
{
    private static final String DB_PROFILE_PROVIDER_ID = 
                            Constants.DB_PROFILE_PROVIDER_ID;
    /**
     * Obtains the provider id of the db connection profile type from the 
     * specified ODA connection profile's properties.
     * @param odaProfile
     * @return  may be null if no such property
     */
    static String getDbProviderIdFromProfileProperties( OdaConnectionProfile odaDbProfile )
    {
        Properties profileProps = odaDbProfile.getBaseProperties();
        return profileProps.getProperty( DB_PROFILE_PROVIDER_ID );
    }

    /**
     * Set/reset the specified db profile provider id to the 
     * specified properties.
     */
    static void setDbProviderIdInProperties( Properties dbProfileProps,
            String dbProviderId )
    {
        dbProfileProps.setProperty( DB_PROFILE_PROVIDER_ID, 
                                    dbProviderId );        
    }
    
    /**
     * Creates and returns the db driver contributed property page
     * of the db connection profile type wrapped in the specified
     * ODA connection profile.
     * @param odaDbProfile
     * @param odaWrapperExtensionId
     * @return
     */
    static ProfilePropertyPage createDbPropertyPage( 
            OdaConnectionProfile odaDbProfile,
            String odaWrapperExtensionId )
    {
        updateProfileDbProviderInfo( odaDbProfile, odaWrapperExtensionId );
        
        // create the db profile's custom property page contribution
        odaDbProfile.setHideWrapperId( true );
        return ProfileUIManager.createPropertyPage( odaDbProfile );        
    }

    /**
     * Assigns the db profile's provider info in the specified
     * ODA connection profile. 
     * @param odaDbProfile
     * @param odaWrapperExtensionId
     * @return  the updated connection profile
     */
    private static void updateProfileDbProviderInfo( 
            OdaConnectionProfile odaDbProfile,
            String odaWrapperExtensionId )
    {
        String dbProviderId = getDbProviderIdFromProfileProperties( odaDbProfile );
        odaDbProfile.setDirectProviderId( dbProviderId );

        odaDbProfile.setOdaWrapperExtensionId( odaWrapperExtensionId );
    }
    
    /**
     * Updates the specified data source design instance with the specified
     * db connection profile properties for those properties that are defined 
     * in the ODA data source extension manifest.
     * @param design
     * @param dbPropertyValues
     * @throws OdaException
     */
    static void updateDataSourceDesignManifestProperties(
                    DataSourceDesign design,
                    Properties dbPropertyValues ) 
        throws OdaException
    {
        if( dbPropertyValues == null || dbPropertyValues.isEmpty() )
            return;     // nothing to set
        
        // saves the linked profile properties in design
        design.setPublicProperties(
                    DesignSessionUtil.createDataSourcePublicProperties( 
                            design.getOdaExtensionDataSourceId(),
                            dbPropertyValues ));
        
        // copies the db provider id as a private property
        updateDbProviderIdInDesign( design, dbPropertyValues );
    }
    
    /**
     * Copies the db provider id as a private property in the design.
     */
    private static void updateDbProviderIdInDesign( 
            DataSourceDesign design,
            Properties dbPropertyValues ) 
    {
        // since a data source design that uses a db profile must use external profile
        // reference, do not import a profile's base properties into the design definition;
        // only the db provider id info is applied
        String dbProviderIdKey = DB_PROFILE_PROVIDER_ID;
        if( dbPropertyValues.containsKey( dbProviderIdKey ) )
        {
            Properties designProps = new Properties();
            designProps.setProperty( dbProviderIdKey, 
                    dbPropertyValues.getProperty( dbProviderIdKey ) );

            design.setPrivateProperties( 
                    DesignUtil.convertToDesignProperties( designProps ) );
        }
    }
   
}
