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
import org.eclipse.datatools.sqltools.internal.core.DatabaseFactoryRegistry;

/**
 * This should be the central place to query about contributed <code>IDBFactory</code>s.
 * Unlike <code>DatabaseFactoryRegistry</code>, a <code>DefaultDBFactory</code> will be used 
 * if no registered <code>IDBFactory</code>.
 * 
 * @author Hui Cao
 * 
 */
public class SQLToolsFacade
{

    private static SQLToolsFacade   _instance = new SQLToolsFacade();
    private static DefaultDBFactory _default  = new DefaultDBFactory();

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
    private static DatabaseFactoryRegistry getRegistry()
    {
        return EditorCorePlugin.getDatabaseFactoryRegistry();
    }

    /**
     * Gets all the contributed database factories
     * 
     * @return
     */
    public static Collection getDBFactories()
    {
        Collection c = getRegistry().getFactories();
        return c;
    }

    /**
     * Returns the database definition names which has associated <code>IDBFactory</code>s.
     * 
     * @return Full database definition names including product name and version
     */
    public static Collection getSupportedDBDefinitionNames()
    {
        Collection c = getRegistry().getFactories();
        ArrayList names = new ArrayList();
        for (Iterator iter = c.iterator(); iter.hasNext();) {
			IDBFactory factory = (IDBFactory) iter.next();
			String vendor = factory.getDatabaseVendorDefinitionId().getProductName();
			String version = factory.getDatabaseVendorDefinitionId().getVersion();
			
			names.add( vendor + "_" + version); 
		}
        return names;
    }


    /**
     * Gets the <code>IDBFactory</code> object by the database definition name.
     * 
     * @param dbDefName database definition name, which is product name appended by "_" and version.
     * @return <code>IDBFactory</code> object
     */
    public static IDBFactory getDBFactoryByDBDefName(String dbDefName)
    {
        return getDBFactoryByVendorIdentifier(new DatabaseVendorDefinitionId(dbDefName));
    }

    /**
     * Gets the <code>IDBFactory</code> object by the <code>DatabaseVendorDefinitionId</code> object
     * @param id <code>DatabaseVendorDefinitionId</code> object represented by product name and version
     * @return <code>IDBFactory</code> object
     */
    public static IDBFactory getDBFactoryByVendorIdentifier(DatabaseVendorDefinitionId vendorId)
    {
        return getDBFactory(null, vendorId);
    }

    /**
     * Gets the <code>IDBFactory</code> object by the connection profile name. Since different versions of a database
     * may use the same connection profile provider id, we'll compare the real version of the server with the version
     * string declared for the <code>IDBFactory</code> and finds the most suitable one.
     * 
     * @param profileName connection profile name
     * @return <code>IDBFactory</code> object
     */
    public static IDBFactory getDBFactoryByProfileName(String profileName)
    {
		return getDBFactoryByVendorIdentifier(ProfileUtil.getDatabaseVendorDefinitionId(profileName));
    }



    /**
     * Gets the <code>IDBFactory</code> object. This is a utility method for getDBFactoryByProfileName(String
     * profileName) and getDBFactoryByDBName(String dbName). It will try to use the first parameter then the second.
     * 
     * @param databaseIdentifier <code>DatabaseIdentifier</code> which contains connection profile name, can be null
     * @param id <code>DatabaseVendorDefinitionId</code> object represented by product name and version, can be null
     * @return <code>IDBFactory</code> object
     */
    public static IDBFactory getDBFactory(DatabaseIdentifier databaseIdentifier, DatabaseVendorDefinitionId vendorId)
    {
        IDBFactory f = null;
        if (databaseIdentifier != null)
        {
            f = getDBFactoryByProfileName(databaseIdentifier.getProfileName());
        }
        if (f == null && vendorId != null)
        {
            f = getRegistry().getDBFactoryByVendorIdentifier(vendorId);
        }
        if (f == null)
        {
            f = _default;
        }
        return f;
    }

}
