/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.internal.core.SQLDevToolsConfigRegistry;
import org.eclipse.datatools.sqltools.internal.core.SQLDevToolsConfigRegistryImpl;

/**
 * This should be the central place to query about contributed
 * <code>SQLDevToolsConfiguration</code>s. Unlike
 * <code>SQLDevToolsConfigRegistry</code>, the default
 * <code>SQLDevToolsConfiguration</code> will be used if no registered
 * <code>SQLDevToolsConfiguration</code>.
 * 
 * @author Hui Cao
 * 
 */
public class SQLToolsFacade
{

    private static SQLToolsFacade   _instance = new SQLToolsFacade();

    private SQLToolsFacade()
    {
    }

    /**
     * Singleton
     * 
     * @return
     */
    public static SQLToolsFacade getInstance()
    {
        return _instance;
    }

    /**
     * Gets database factory registry that manages all database contributions
     * 
     * @return
     */
    private static SQLDevToolsConfigRegistry getRegistry()
    {
        return EditorCorePlugin.getDatabaseFactoryRegistry();
    }

    /**
     * Gets all the contributed database factories
     * 
     * @return
     */
    public static Collection getConfigurations()
    {
        Collection c = getRegistry().getConfigurations();
        return c;
    }

    /**
     * Returns the database definition names which has associated <code>SQLDevToolsConfiguration</code>s.
     * 
     * @return Full database definition names including product name and version
     */
    public static Collection getSupportedDBDefinitionNames()
    {
        Collection c = getRegistry().getConfigurations();
        ArrayList names = new ArrayList();
        for (Iterator iter = c.iterator(); iter.hasNext();) {
			SQLDevToolsConfiguration factory = (SQLDevToolsConfiguration) iter.next();
			String vendor = factory.getDatabaseVendorDefinitionId().getProductName();
			String version = factory.getDatabaseVendorDefinitionId().getVersion();
			
			names.add( vendor + "_" + version); 
		}
        return names;
    }


    /**
     * Gets the <code>SQLDevToolsConfiguration</code> object by the database definition name.
     * 
     * @param dbDefName database definition name, which is product name appended by "_" and version.
     * @return <code>SQLDevToolsConfiguration</code> object
     */
    public static SQLDevToolsConfiguration getConfigurationByDBDefName(String dbDefName)
    {
        return getConfigurationByVendorIdentifier(new DatabaseVendorDefinitionId(dbDefName));
    }

    /**
     * Gets the <code>SQLDevToolsConfiguration</code> object by the <code>DatabaseVendorDefinitionId</code> object
     * @param id <code>DatabaseVendorDefinitionId</code> object represented by product name and version
     * @return <code>SQLDevToolsConfiguration</code> object
     */
    public static SQLDevToolsConfiguration getConfigurationByVendorIdentifier(DatabaseVendorDefinitionId vendorId)
    {
        return getConfiguration(null, vendorId);
    }

    /**
     * Gets the <code>SQLDevToolsConfiguration</code> object by the connection profile name. Since different versions of a database
     * may use the same connection profile provider id, we'll compare the real version of the server with the version
     * string declared for the <code>SQLDevToolsConfiguration</code> and finds the most suitable one.
     * 
     * @param profileName connection profile name
     * @return <code>SQLDevToolsConfiguration</code> object
     */
    public static SQLDevToolsConfiguration getConfigurationByProfileName(String profileName)
    {
		return getConfigurationByVendorIdentifier(ProfileUtil.getDatabaseVendorDefinitionId(profileName));
    }



    /**
     * Gets the <code>SQLDevToolsConfiguration</code> object. This is a utility method for getDBFactoryByProfileName(String
     * profileName) and getDBFactoryByDBName(String dbName). It will try to use the first parameter then the second.
     * 
     * @param databaseIdentifier <code>DatabaseIdentifier</code> which contains connection profile name, can be null
     * @param id <code>DatabaseVendorDefinitionId</code> object represented by product name and version, can be null
     * @return <code>SQLDevToolsConfiguration</code> object
     */
    public static SQLDevToolsConfiguration getConfiguration(DatabaseIdentifier databaseIdentifier, DatabaseVendorDefinitionId vendorId)
    {
        SQLDevToolsConfiguration f = null;
        if (databaseIdentifier != null)
        {
            f = getConfigurationByProfileName(databaseIdentifier.getProfileName());
        }
        if (f == null && vendorId != null)
        {
            f = getRegistry().getConfigurationByVendorIdentifier(vendorId);
        }
        if (f == null)
        {
            f = getDefaultConfiguration();
        }
        return f;
    }

    /**
	 * Gets the default <code>SQLDevToolsConfiguration</code> object, which is
	 * contributed via the "isDefault" attribute of the "dbConfiguration"
	 * extension point, or if there's no such contribution, will use
	 * SQLDevToolsConfiguration.getDefaultInstance().
	 * 
	 * @return default <code>SQLDevToolsConfiguration</code> object. Will
	 *         never be null.
	 */
    public static SQLDevToolsConfiguration getDefaultConfiguration()
    {
    	return SQLDevToolsConfigRegistryImpl.getDefaultConfiguration();
    }
    
}
