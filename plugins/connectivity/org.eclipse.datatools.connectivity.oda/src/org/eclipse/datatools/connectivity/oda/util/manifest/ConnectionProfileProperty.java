/*
 *************************************************************************
 * Copyright (c) 2007, 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.util.manifest;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * The property definition that references an ODA connection profile.
 */
public class ConnectionProfileProperty
{
    
    // property names for connection profile info
    //      for use as a key in connection properties collection
    public static final String PROFILE_NAME_PROP_KEY = "OdaConnProfileName"; //$NON-NLS-1$
    public static final String PROFILE_STORE_FILE_PATH_PROP_KEY = "OdaConnProfileStorePath"; //$NON-NLS-1$

    // @since 3.3.3 (DTP 1.9.2)
    //      for use as a key in a transient collection of connection properties 
    public static final String TRANSIENT_PROFILE_STORE_RESOLVED_PATH_PROP_KEY = 
                "org.eclipse.datatools.connectivity.oda_transient.profileStorePath"; //$NON-NLS-1$

    //      for use as a key in oda.consumer.IPropertyProvider's 
    //      connection property context map
    public static final String PROFILE_STORE_FILE_PROP_KEY = "OdaConnProfileStore"; //$NON-NLS-1$

    private static final String PROPERTY_GROUP_NAME = "ConnectionProfileProperties"; //$NON-NLS-1$

    /**
     * Checks whether the specified connection properties 
     * contain the property that references an external profile by name
     * @param connProperties   data source connection properties 
     * @return  true if the specified properties contain a non-empty value 
     *          for the profile name property; false otherwise
     * @since 3.2.2 (DTP 1.7.2)
     */
    public static boolean hasProfileName( Properties connProperties )
    {
        if( connProperties == null )
            return false;
        String profileName = connProperties.getProperty( PROFILE_NAME_PROP_KEY );
        return ( profileName != null && profileName.length() > 0 );
    }
    
    /**
     * Returns a new property definition for the specified property name.
     * Such property definitions are defined by the ODA framework,
     * and may be automatically added to an
     * ODA runtme extension's manifest, if not explicitly defined. 
     * @param propertyName  must be one of those defined here.
     * @return
     */
    static Property createPropertyDefinition( String propertyName )
    {
        assert( propertyName != null );
        assert( propertyName == PROFILE_NAME_PROP_KEY ||
                propertyName == PROFILE_STORE_FILE_PATH_PROP_KEY );
        
        // get localized display names
        String propDisplayName = getPropertyDisplayName( propertyName );
        String groupDisplayName = getPropertyDisplayName( PROPERTY_GROUP_NAME );
             
        Property newProp = new Property( propertyName, propDisplayName, 
                             PROPERTY_GROUP_NAME, groupDisplayName );
        // profile_name value should be handled as a literal string
        if( propertyName == PROFILE_NAME_PROP_KEY )
            newProp.setAllowsEmptyValueAsNull( false ); 
        return newProp;
    }
    
    static private String getPropertyDisplayName( String propertyName )
    {
        if( propertyName == PROFILE_NAME_PROP_KEY )
            return Messages.profileProperty_PROFILE_NAME;
        
        if( propertyName == PROFILE_STORE_FILE_PATH_PROP_KEY )
            return Messages.profileProperty_PROFILE_STORE_PATH;
        
        if( propertyName == PROPERTY_GROUP_NAME )
            return Messages.profileProperty_GROUP_PROPERTIES;

        return propertyName;    // use name for display name
    }
}
