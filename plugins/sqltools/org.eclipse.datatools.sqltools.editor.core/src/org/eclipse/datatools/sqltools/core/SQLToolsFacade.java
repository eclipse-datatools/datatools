/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.core.services.SQLDataService;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionInitializer;
import org.eclipse.datatools.sqltools.internal.core.SQLDevToolsConfigRegistry;
import org.eclipse.datatools.sqltools.internal.core.SQLDevToolsConfigRegistryImpl;
import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;

/**
 * This should be the central place to query about contributed <code>SQLDevToolsConfiguration</code>s. Unlike
 * <code>SQLDevToolsConfigRegistry</code>, the default <code>SQLDevToolsConfiguration</code> will be used if no
 * registered <code>SQLDevToolsConfiguration</code>.
 * 
 * @author Hui Cao
 * 
 */
public class SQLToolsFacade
{

    private static SQLToolsFacade _instance = new SQLToolsFacade();

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
        for (Iterator iter = c.iterator(); iter.hasNext();)
        {
            SQLDevToolsConfiguration factory = (SQLDevToolsConfiguration) iter.next();
            String vendor = factory.getDatabaseVendorDefinitionId().getProductName();
            String version = factory.getDatabaseVendorDefinitionId().getVersion();

            names.add(vendor + "_" + version);
        }
        return names;
    }

    /**
     * Returns the registered database definition names which are declared as connectible.
     * 
     * @return Full database definition names including product name and version
     */
    public static Collection getConnectibleDBDefinitionNames()
    {
        Iterator iter = DatabaseDefinitionRegistryImpl.INSTANCE.getConnectibleProducts();
        ArrayList names = new ArrayList();
        for (; iter.hasNext();)
        {
            String vendor = (String) iter.next();
            Iterator versions = DatabaseDefinitionRegistryImpl.INSTANCE.getConnectibleVersions(vendor);
            for (; versions.hasNext();)
            {
                String version = (String) versions.next();
                names.add(vendor + "_" + version);
            }
        }
        return names;
    }

    /**
     * Returns all the database definition names which can be used in SQL Dev Tools. This includes the combination of
     * getSupportedDBDefinitionNames() and getConnectibleDBDefinitionNames().
     * 
     * @return Full database definition names including product name and version
     */
    public static Collection getAllAvailableDBDefinitionIds()
    {
        // user Set to avoid duplication
        HashSet ids = new HashSet();
        Collection c = getRegistry().getConfigurations();
        for (Iterator iter = c.iterator(); iter.hasNext();)
        {
            SQLDevToolsConfiguration factory = (SQLDevToolsConfiguration) iter.next();
            ids.add(factory.getDatabaseVendorDefinitionId());
        }
        Iterator iter = DatabaseDefinitionRegistryImpl.INSTANCE.getConnectibleProducts();
        for (; iter.hasNext();)
        {
            String vendor = (String) iter.next();
            Iterator versions = DatabaseDefinitionRegistryImpl.INSTANCE.getConnectibleVersions(vendor);
            for (; versions.hasNext();)
            {
                String version = (String) versions.next();
                ids.add(new DatabaseVendorDefinitionId(vendor, version));
            }
        }
        return ids;
    }

    public static Collection getAllAvailableDBDefinitionNames()
    {
        Collection Ids = getAllAvailableDBDefinitionIds();
        Collection names = new TreeSet();
        for (Iterator it = Ids.iterator(); it.hasNext();)
        {
            DatabaseVendorDefinitionId id = (DatabaseVendorDefinitionId) it.next();
            names.add(id.toString());
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
     * 
     * @param id <code>DatabaseVendorDefinitionId</code> object represented by product name and version
     * @return <code>SQLDevToolsConfiguration</code> object
     */
    public static SQLDevToolsConfiguration getConfigurationByVendorIdentifier(DatabaseVendorDefinitionId vendorId)
    {
        return getConfiguration(null, vendorId);
    }

    /**
     * Gets the <code>SQLDevToolsConfiguration</code> object by the connection profile name. Since different versions
     * of a database may use the same connection profile provider id, we'll compare the real version of the server with
     * the version string declared for the <code>SQLDevToolsConfiguration</code> and finds the most suitable one.
     * 
     * @param profileName connection profile name
     * @return <code>SQLDevToolsConfiguration</code> object
     */
    public static SQLDevToolsConfiguration getConfigurationByProfileName(String profileName)
    {
        return getConfigurationByVendorIdentifier(ProfileUtil.getDatabaseVendorDefinitionId(profileName));
    }

    /**
     * Gets the <code>SQLDevToolsConfiguration</code> object. This is a utility method for
     * getConfigurationByProfileName(String profileName) and getDBFactoryByDBName(String dbName). It will try to use the
     * first parameter then the second.
     * 
     * @param databaseIdentifier <code>DatabaseIdentifier</code> which contains connection profile name, can be null
     * @param id <code>DatabaseVendorDefinitionId</code> object represented by product name and version, can be null
     * @return <code>SQLDevToolsConfiguration</code> object
     */
    public static SQLDevToolsConfiguration getConfiguration(DatabaseIdentifier databaseIdentifier,
            DatabaseVendorDefinitionId vendorId)
    {
        SQLDevToolsConfiguration f = null;
        if (databaseIdentifier != null)
        {
            // get DatabaseVendorDefinitionId from profile, then call getConfigurationByVendorIdentifier
            // this is to make sure we are using the REAL version
            f = getConfigurationByProfileName(databaseIdentifier.getProfileName());
        }
        if (f == null && vendorId != null)
        {
            vendorId = getCanonicalDatabaseVendorDefinitionId(vendorId);
            f = getRegistry().getConfigurationByVendorIdentifier(vendorId);
        }
        if (f == null)
        {
            f = getDefaultConfiguration();
        }
        return f;
    }

    /**
     * Returns the canonical DatabaseVendorDefinitionId referenced by the corresponding SQLDevToolsConfiguration. If no exact 
     * versions match, the latest version of the same product would be used.
     * @param vendorId
     * @return
     */
    public static DatabaseVendorDefinitionId getCanonicalDatabaseVendorDefinitionId(DatabaseVendorDefinitionId vendorId)
    {
        DatabaseVendorDefinitionId oldVendorId = vendorId;
        
        DatabaseVendorDefinitionId defaultId = getDefaultConfiguration().getDatabaseVendorDefinitionId();
        //try to find SQLDevToolsConfiguration directly
        SQLDevToolsConfiguration f = getRegistry().getConfigurationByVendorIdentifier(vendorId);
        if (f != null)
        {
            return f.getDatabaseVendorDefinitionId();
        }
        else
        {
            // find the aliases
            Collection configs = getConfigurations();
            ArrayList ids = new ArrayList();
            for (Iterator iter = configs.iterator(); iter.hasNext();)
            {
                SQLDevToolsConfiguration conf = (SQLDevToolsConfiguration) iter.next();
                if (conf.recognize(oldVendorId.getProductName(), oldVendorId.getVersion())
                        && !(conf.getDatabaseVendorDefinitionId().equals(defaultId)))
                {
                    ids.add(conf.getDatabaseVendorDefinitionId());
                }
            }
            if (ids.size() == 1)
            {
                return (DatabaseVendorDefinitionId) ids.get(0);
            }
            else if (ids.size() > 1)
            {
                //to filter out some incorrectly implemented SQLDevToolsConfiguration which claim to handle all server types
                //(of course, the generic jdbc SQLDevToolsConfiguration is an exception)
                for (Iterator it = ids.iterator(); it.hasNext();)
                {
                    DatabaseVendorDefinitionId id = (DatabaseVendorDefinitionId) it.next();
                    // at most just minor version differences
                    if (id.equals(oldVendorId))
                    {
                        return id;
                    }
                }
                return (DatabaseVendorDefinitionId) ids.get(0);
            }
            else
            {
                //find all the ids belonging to the same product
                for (Iterator iter = configs.iterator(); iter.hasNext();)
                {
                    SQLDevToolsConfiguration conf = (SQLDevToolsConfiguration) iter.next();
                    if (conf.recognize(oldVendorId.getProductName(), "x")
                            && !(conf.getDatabaseVendorDefinitionId().equals(defaultId)))
                    {
                        ids.add(conf.getDatabaseVendorDefinitionId());
                    }
                }
                // try to use the latest version
                DatabaseVendorDefinitionId.VersionComparator comparator = new DatabaseVendorDefinitionId.VersionComparator();
                if (ids.size() > 0)
                {
                    String version = "0";
                    for (Iterator iter = ids.iterator(); iter.hasNext();)
                    {
                        DatabaseVendorDefinitionId v = (DatabaseVendorDefinitionId) iter.next();
                        if (comparator.compare(v.getVersion(), version) >= 0)
                        {
                            version = v.getVersion();
                        }
                    }
                    return new DatabaseVendorDefinitionId(vendorId.getProductName(), version);
                }
            }
        }

        return defaultId;
    }

    /**
     * Returns the declared DatabaseVendorDefinitionId matching the given parameter. If none is found, the original one is returned.
     * @param vendorId
     * @return
     */
    public static DatabaseVendorDefinitionId getDeclaredDatabaseVendorDefinitionId(DatabaseVendorDefinitionId vendorId)
    {
        Collection allIds = getAllAvailableDBDefinitionIds();
        for (Iterator it = allIds.iterator(); it.hasNext();)
        {
            DatabaseVendorDefinitionId id = (DatabaseVendorDefinitionId) it.next();
            if (id.equals(vendorId))
            {
                return id;
            }
        }
        
        //find all the ids belonging to the same product
        DatabaseVendorDefinitionId defaultId = getDefaultConfiguration().getDatabaseVendorDefinitionId();
        Collection configs = getConfigurations();
        ArrayList ids = new ArrayList();
        for (Iterator iter = configs.iterator(); iter.hasNext();)
        {
            SQLDevToolsConfiguration conf = (SQLDevToolsConfiguration) iter.next();
            if (conf.recognize(vendorId.getProductName(), "x")
                    && !(conf.getDatabaseVendorDefinitionId().equals(defaultId)))
            {
                ids.add(conf.getDatabaseVendorDefinitionId());
            }
        }

        if (ids.size() > 0)
        {
            DatabaseVendorDefinitionId.VersionComparator comparator = new DatabaseVendorDefinitionId.VersionComparator();
            for (Iterator it = ids.iterator(); it.hasNext();)
            {
                DatabaseVendorDefinitionId id = (DatabaseVendorDefinitionId) it.next();
                // at most just minor version differences
                if (comparator.compare(id.getVersion(), vendorId.getVersion()) == 0)
                {
                    return id;
                }
            }
            for (Iterator it = ids.iterator(); it.hasNext();)
            {
                DatabaseVendorDefinitionId id = (DatabaseVendorDefinitionId) it.next();
                id = new DatabaseVendorDefinitionId(id.getProductName(), vendorId.getVersion());
                for (Iterator it1 = allIds.iterator(); it1.hasNext();)
                {
                    DatabaseVendorDefinitionId idDef = (DatabaseVendorDefinitionId) it1.next();
                    if (idDef.equals(id))
                    {
                        return idDef;
                    }
                }
                //all items in ids belong to the same product
                break;
            }
            return (DatabaseVendorDefinitionId)ids.get(0);
        }
        
        return vendorId;
    }

    /**
     * Gets the default <code>SQLDevToolsConfiguration</code> object, which is contributed via the "isDefault"
     * attribute of the "dbConfiguration" extension point, or if there's no such contribution, will use
     * SQLDevToolsConfiguration.getDefaultInstance().
     * 
     * @return default <code>SQLDevToolsConfiguration</code> object. Will never be null.
     */
    public static SQLDevToolsConfiguration getDefaultConfiguration()
    {
        SQLDevToolsConfiguration defaultConfiguration = SQLDevToolsConfigRegistryImpl.getDefaultConfiguration();
        if (defaultConfiguration == null)
        {
            defaultConfiguration = SQLDevToolsConfiguration.getDefaultInstance();
        }
        return defaultConfiguration;
    }

    /**
     * Returns the DatabaseVendorDefinitionId which contributes to the sql dev tools framework as a default
     * configuration.
     * 
     * @return
     */
    public static DatabaseVendorDefinitionId getDefaultDatabaseVendorDefinitionId()
    {
        return getDefaultConfiguration().getDatabaseVendorDefinitionId();
    }

    /**
     * Returns the DatabaseVendorDefinitionId which is not bind to any database
     * 
     * @return
     */
    public static DatabaseVendorDefinitionId getNonSpecificDatabaseVendorDefinitionId()
    {
        return SQLDevToolsConfiguration.getDefaultInstance().getDatabaseVendorDefinitionId();
    }

    /**
     * Returns a DatabaseVendorDefinitionId matching the product and version. If no exact matches are found, the generic
     * DatabaseVendorDefinitionId (Genrice_JDBC_1.x) will be returned.
     * 
     * @param product
     * @param version
     * @return
     */
    public static DatabaseVendorDefinitionId recognize(String product, String version)
    {
        DatabaseVendorDefinitionId defaultId = getDefaultConfiguration().getDatabaseVendorDefinitionId();
        Collection configs = getConfigurations();
        for (Iterator iter = configs.iterator(); iter.hasNext();)
        {
            SQLDevToolsConfiguration conf = (SQLDevToolsConfiguration) iter.next();
            if (conf.recognize(product, version) && !(conf.getDatabaseVendorDefinitionId().equals(defaultId)))
            {
                return conf.getDatabaseVendorDefinitionId();
            }
        }
        return defaultId;
    }

    // temporary methods, to be inlined.
    public static int getConnectionId(DatabaseIdentifier databaseIdentifier, Connection conn)
    {
        if (databaseIdentifier == null || conn == null)
        {
            return 0;
        }
        try
        {
            if (conn.isClosed())
            {
                return 0;
            }
        }
        catch (SQLException e)
        {
            EditorCorePlugin.getDefault().log(e);
        }
        ConnectionService service = getConnectionService(databaseIdentifier);
        return service.getConnectionId(databaseIdentifier, conn);
    }

    public static ConnectionService getConnectionService(DatabaseIdentifier databaseIdentifier)
    {
        ConnectionService service = getConfiguration(null, databaseIdentifier).getConnectionService();
        return service;
    }

    public static Runnable getConnectionKiller(DatabaseIdentifier databaseIdentifier, Connection conn)
    {
        ConnectionService service = getConnectionService(databaseIdentifier);
        if (service != null)
        {
            return service.getConnectionKiller(databaseIdentifier, conn);
        }
        return null;
    }

    public static IDatabaseSetting getDatabaseSetting(DatabaseIdentifier databaseIdentifier)
    {
        SQLDevToolsConfiguration f = getConfiguration(null, databaseIdentifier);
        return f.getDatabaseSetting(databaseIdentifier);

    }

    public static IConnectionInitializer getConnectionInitializer(DatabaseIdentifier databaseIdentifier)
    {
        ConnectionService service = getConnectionService(databaseIdentifier);
        if (service != null)
        {
            return service.getConnectionInitializer();
        }
        return null;
    }

    public static DBHelper getDBHelper(DatabaseIdentifier databaseIdentifier, String dbType)
    {
        SQLDevToolsConfiguration f = getConfiguration(dbType, databaseIdentifier);
        return f.getDBHelper();
    }

    /**
     * 
     * @param databaseIdentifier
     * @return
     */
    public static DBHelper getDBHelper(DatabaseIdentifier databaseIdentifier)
    {
        return getDBHelper(databaseIdentifier, null);
    }

    /**
     * 
     * @param dbType
     * @return
     */
    public static DBHelper getDBHelper(String dbType)
    {
        return getDBHelper(null, dbType);
    }

    /**
     * Returns a database-specific SQL Data service class.
     * 
     * @param profileName
     * @param dbType
     * @return
     */
    public static SQLDataService getSQLDataService(DatabaseIdentifier databaseIdentifier, String dbType)
    {
        SQLDevToolsConfiguration f = getConfiguration(dbType, databaseIdentifier);
        return f.getSQLDataService();
    }

    /**
     * Return a special SQLDataValidator to verify user's input value
     * 
     * @param profileName
     * @return
     */
    public static ISqlDataValidator getSQLDataValidator(DatabaseIdentifier databaseIdentifier)
    {
        SQLDataService s = getSQLDataService(databaseIdentifier, null);
        if (s != null)
        {
            return s.getSQLDataValidator(databaseIdentifier);
        }

        return null;
    }

    /**
     * Returns a database-specific SQL statement service class.
     * 
     * @param profileName
     * @param dbType
     * @return
     */
    public static SQLService getSQLService(DatabaseIdentifier databaseIdentifier, String dbType)
    {
        SQLDevToolsConfiguration f = getConfiguration(dbType, databaseIdentifier);
        return f.getSQLService();
    }

    /**
     * 
     * Return an ISQLSyntax object which can be used to highlight sql statements in SQL editor.
     * 
     * @param dbType
     * @return
     */
    public static ISQLSyntax getSQLSyntax(String dbType)
    {
        SQLService s = getSQLService(null, dbType);
        if (s != null)
        {
            return s.getSQLSyntax();
        }
        return null;
    }

    public static int[] getDBTypes()
    {
        Collection c = getRegistry().getConfigurations();
        int size = c.size();
        String[] types = (String[]) c.toArray(new String[size]);
        int[] ts = new int[size];
        for (int i = 0; i < size; i++)
        {
            ts[i] = Integer.parseInt(types[i]);
        }
        return ts;
    }

    public static SQLDevToolsConfiguration getConfiguration(String dbType, DatabaseIdentifier databaseIdentifier)
    {
        if (dbType == null)
        {
            return getConfiguration(databaseIdentifier, null);
        }
        return getConfiguration(databaseIdentifier, new DatabaseVendorDefinitionId(dbType));
    }
    

    /**
     * Return a SQLParser which is used to parse database dialect
     * 
     * @param profileName
     * @param dbType
     * @return
     */
    public static SQLParser getSQLParser(String profileName, String dbType)
    {
        SQLService s = getSQLService(new DatabaseIdentifier(profileName), dbType);
        if (s != null)
        {
            return s.getSQLParser();
        }
        return null;
    }
}
