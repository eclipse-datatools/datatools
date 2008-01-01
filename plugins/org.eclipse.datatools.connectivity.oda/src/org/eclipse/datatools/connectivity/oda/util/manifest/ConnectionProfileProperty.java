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

package org.eclipse.datatools.connectivity.oda.util.manifest;

import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * The property definition that references an ODA connection profile.
 */
public class ConnectionProfileProperty
{
    
    // property names for connection profile info
    //      for use as the key in connection properties collection
    public static final String PROFILE_NAME_PROP_KEY = "OdaConnProfileName"; //$NON-NLS-1$
    public static final String PROFILE_STORE_FILE_PATH_PROP_KEY = "OdaConnProfileStorePath"; //$NON-NLS-1$

    //      for use as the key in oda.consumer.IPropertyProvider's 
    //      connection property context map
    public static final String PROFILE_STORE_FILE_PROP_KEY = "OdaConnProfileStore"; //$NON-NLS-1$

    private static final String PROPERTY_GROUP_NAME = "ConnectionProfileProperties"; //$NON-NLS-1$

    
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
