/**
 * Created on 2004-11-16
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
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
        int size = c.size();
        ArrayList names = new ArrayList();
        for (int i = 0; i < size; i++)
        {
        	String vendor = ((IDBFactory)c).getDatabaseVendorDefinitionId().getProductName();
        	String version = ((IDBFactory)c).getDatabaseVendorDefinitionId().getVersion();
        	
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
        return getRegistry().getDBFactoryByName(dbDefName);
    }

    /**
     * Gets the <code>IDBFactory</code> object by the <code>DatabaseVendorDefinitionId</code> object
     * @param id <code>DatabaseVendorDefinitionId</code> object represented by product name and version
     * @return <code>IDBFactory</code> object
     */
    public static IDBFactory getDBFactoryByVendorIdentifier(DatabaseVendorDefinitionId vendorId)
    {
        return getRegistry().getDBFactoryByVendorIdentifier(vendorId);
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
        try
        {
            String profileId = ProfileUtil.getConnectionProfileId(profileName);
            Collection fs = getRegistry().getDBFactoryByConnectionProfileId(profileId);
            if (fs != null && fs.size() == 1)
            {
                return (IDBFactory) fs.iterator().next();
            }
            else if (fs != null && fs.size() >= 1)
            {
                // note: version could be null. In that case, we always try to get the latest version
            	DatabaseVendorDefinitionId vendorId = ProfileUtil.getDatabaseVendorDefinitionId(profileName);
                String realVersion = vendorId.getVersion();
                
                DatabaseVendorDefinitionId.VersionComparator comp = new DatabaseVendorDefinitionId.VersionComparator();
                IDBFactory last = null;
                for (Iterator iter = fs.iterator(); iter.hasNext();)
                {
                    IDBFactory factory = (IDBFactory) iter.next();
                    int compare = comp.compare(realVersion, factory.getDatabaseVendorDefinitionId().getVersion());
                    if (compare == 0)
                    {
                        return factory;
                    }
                    else if (compare < 0)
                    {
                        if (last != null)
                        {
                            return last;
                        }
                        else
                        {
                            return factory;
                        }
                    }
                    last = factory;
                }
                return last;
            }
            return null;
        }
        catch (Exception e)
        {
            return null;
        }
    }



    /**
     * Gets the <code>IDBFactory</code> object. This is a utility method for getDBFactoryByProfileName(String
     * profileName) and getDBFactoryByDBName(String dbName). It will try to use the first parameter then the second.
     * 
     * @param databaseIdentifier <code>DatabaseIdentifier</code> which contains connection profile name
     * @param dbDefName database definition name, which is product name appended by "_" and version.
     * @return <code>IDBFactory</code> object
     */
    public static IDBFactory getDBFactory(DatabaseIdentifier databaseIdentifier, String dbDefName)
    {
        IDBFactory f = null;
        if (databaseIdentifier != null)
        {
            f = getDBFactoryByProfileName(databaseIdentifier.getProfileName());
        }
        if (f == null && dbDefName != null)
        {
            f = getRegistry().getDBFactoryByName(dbDefName);
        }
        if (f == null)
        {
            f = _default;
        }
        return f;
    }

}
