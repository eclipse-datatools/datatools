/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.core.profile;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;

/**
 * Utility class for <code>IConnectionProfile</code> in connectivity layer. Encapsulating all the code to processing
 * <code>IConnectionProfile</code> object can reduce the maintainence effort and make it easy for consumers in the SQL
 * Dev Tools to use.
 * 
 * @author Hui Cao
 * 
 */
public class ProfileUtil
{
	
    /**
     * Returns the associated DatabaseVendorDefinition object from the given connection profile.
     * The DatabaseVendorDefinition object is contributed by vendor tool plugins.
     * @param profile
     * @return
     */
    public static DatabaseVendorDefinition getDatabaseVendorDefinition(String profileName)
    {
        DatabaseVendorDefinitionId id = getVendorIdentifier(profileName);
        //TODO get DatabaseVendorDefinition from DatabaseVendorDefinitionId by looking up the registry
        return null;
    }

    /**
     * Returns a <code>IConnectionProfile</code> object by the name.
     * @param name connection profile name
     * @return <code>IConnectionProfile</code>
     * @throws NoSuchProfileException when no connection profile identified by the given name can be found
     */
    public static IConnectionProfile getProfile(String name) throws NoSuchProfileException
    {
    	IConnectionProfile p = ProfileManager.getInstance().getProfileByName(name);
        if (p == null)
        {
            throw new NoSuchProfileException(name);
        }
        else
        {
            return p;
        }
    }



    /**
     * Given the connection profile name, return a DataVendorIdentifier object which identifies the data server type
     * that profileName points to. Basically, there are 2 approaches to do it: 1. connect to server using a specific
     * factoryId (which is not defined by DTP connectivity layer yet), then get DatabaseDefinition from the
     * ISQLEditorConnectionInfo object; 2. find driver template, then get the vendor and version info from it. Since the latter
     * one allows us to do the job without having to connect, we'll use it in this method.
     * 
     * @param profileName
     * @return
     */
    public static DatabaseVendorDefinitionId getVendorIdentifier(String profileName)
    {
        try
        {
        	IConnectionProfile profile = getProfile(profileName);
            // TODO: now we still get vendor and version from connection profile properties. Later should get them from
            // driver which declares these information when contributing.
            // NOTE: we can't use the following approach for now because it's not reliable. If for some reason, (such as
            // build version incompatible),
            // Enterprise Explorer can't connect to server, we'll fail to get product and version info.
            String vendorName = profile.getBaseProperties().getProperty(ConnectionProfileConstants.PROP_SERVER_NAME);
            String version = profile.getBaseProperties().getProperty(ConnectionProfileConstants.PROP_SERVER_VERSION);
            return new DatabaseVendorDefinitionId(vendorName, version);
        }
        catch (NoSuchProfileException e)
        {
            return null;
        }
    }

    /**
     * Gets the user name defined in the <code>IConnectionProfile </code> object.
     * @param profile the <code>IConnectionProfile </code>
     * @return user name
     */
    public static String getUserName(IConnectionProfile profile)
    {
    	if (profile == null)
    	{
    		return null;
    	}
        return profile.getBaseProperties().getProperty(ConnectionProfileConstants.PROP_UID);
    }

    /**
     * Gets the password defined in the <code>IConnectionProfile </code> object.
     * @param profile the <code>IConnectionProfile </code>
     * @return user name
     */
    public static String getPassword(IConnectionProfile profile)
    {
    	if (profile == null)
    	{
    		return null;
    	}
    	return profile.getBaseProperties().getProperty(ConnectionProfileConstants.PROP_PWD);
    }

    /**
     * Gets the connection profile provider id by the profile name.
     * @param profileName connection profile name
     * @return the provider id for the connection profile
     */
    public static String getConnectionProfileId(String profileName) throws NoSuchProfileException
    {
        try
        {
            IConnectionProfile profile = getProfile(profileName);
            return profile.getProviderId();
        }
        catch (Exception e)
        {
            return "";
        }
    }
    
}
