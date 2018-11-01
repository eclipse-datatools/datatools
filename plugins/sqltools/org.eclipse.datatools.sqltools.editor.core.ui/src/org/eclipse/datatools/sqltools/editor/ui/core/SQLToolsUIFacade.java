/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.editor.ui.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.SQLEditorUIService;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.core.services.SQLUIService;
import org.eclipse.datatools.sqltools.editor.template.GenericSQLContextType;
import org.eclipse.datatools.sqltools.editor.ui.internal.core.SQLDevToolsUIConfigRegistry;
import org.eclipse.datatools.sqltools.editor.ui.internal.core.SQLDevToolsUIConfigRegistryImpl;
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;

/**
 * This should be the central place to query about contributed <code>SQLDevToolsUIConfiguration</code>s. Unlike
 * <code>SQLDevToolsConfigRegistry</code>, the default <code>SQLDevToolsUIConfiguration</code> will be used if no
 * registered <code>SQLDevToolsUIConfiguration</code>.
 * 
 * @author linsong
 * 
 */
public class SQLToolsUIFacade
{

    private static SQLToolsUIFacade _instance = new SQLToolsUIFacade();

    private SQLToolsUIFacade()
    {
    }

    /**
     * Singleton
     * 
     * @return
     */
    public static SQLToolsUIFacade getInstance()
    {
        return _instance;
    }

    /**
     * Gets database factory registry that manages all database contributions
     * 
     * @return
     */
    private static SQLDevToolsUIConfigRegistry getRegistry()
    {
        return EditorCoreUIPlugin.getDatabaseFactoryRegistry();
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
     * Returns the database definition names which has associated <code>SQLDevToolsUIConfiguration</code>s.
     * 
     * @return Full database definition names including product name and version
     */
    public static Collection getSupportedDBDefinitionNames()
    {
        Collection c = getRegistry().getConfigurations();
        ArrayList names = new ArrayList();
        for (Iterator iter = c.iterator(); iter.hasNext();)
        {
            SQLDevToolsUIConfiguration factory = (SQLDevToolsUIConfiguration) iter.next();
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
            SQLDevToolsUIConfiguration factory = (SQLDevToolsUIConfiguration) iter.next();
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
     * Gets the <code>SQLDevToolsUIConfiguration</code> object by the database definition name.
     * 
     * @param dbDefName database definition name, which is product name appended by "_" and version.
     * @return <code>SQLDevToolsUIConfiguration</code> object
     */
    public static SQLDevToolsUIConfiguration getConfigurationByDBDefName(String dbDefName)
    {
        return getConfigurationByVendorIdentifier(new DatabaseVendorDefinitionId(dbDefName));
    }

    /**
     * Gets the <code>SQLDevToolsUIConfiguration</code> object by the <code>DatabaseVendorDefinitionId</code> object
     * 
     * @param id <code>DatabaseVendorDefinitionId</code> object represented by product name and version
     * @return <code>SQLDevToolsUIConfiguration</code> object
     */
    public static SQLDevToolsUIConfiguration getConfigurationByVendorIdentifier(DatabaseVendorDefinitionId vendorId)
    {
        return getConfiguration(null, vendorId);
    }

    /**
     * Gets the <code>SQLDevToolsUIConfiguration</code> object by the connection profile name. Since different versions
     * of a database may use the same connection profile provider id, we'll compare the real version of the server with
     * the version string declared for the <code>SQLDevToolsUIConfiguration</code> and finds the most suitable one.
     * 
     * @param profileName connection profile name
     * @return <code>SQLDevToolsUIConfiguration</code> object
     */
    public static SQLDevToolsUIConfiguration getConfigurationByProfileName(String profileName)
    {
        return getConfigurationByVendorIdentifier(ProfileUtil.getDatabaseVendorDefinitionId(profileName));
    }

    /**
     * Gets the <code>SQLDevToolsUIConfiguration</code> object. This is a utility method for
     * getConfigurationByProfileName(String profileName) and getDBFactoryByDBName(String dbName). It will try to use the
     * first parameter then the second.
     * 
     * @param databaseIdentifier <code>DatabaseIdentifier</code> which contains connection profile name, can be null
     * @param id <code>DatabaseVendorDefinitionId</code> object represented by product name and version, can be null
     * @return <code>SQLDevToolsUIConfiguration</code> object
     */
    public static SQLDevToolsUIConfiguration getConfiguration(DatabaseIdentifier databaseIdentifier,
            DatabaseVendorDefinitionId vendorId)
    {
        SQLDevToolsUIConfiguration f = null;
        if (databaseIdentifier != null)
        {
            // get DatabaseVendorDefinitionId from profile, then call getConfigurationByVendorIdentifier
            // this is to make sure we are using the REAL version
            f = getConfigurationByProfileName(databaseIdentifier.getProfileName());
        }
        if (f == null && vendorId != null)
        {
            vendorId = SQLToolsFacade.getCanonicalDatabaseVendorDefinitionId(vendorId);
            f = getRegistry().getConfigurationByVendorIdentifier(vendorId);
        }
        if (f == null)
        {
            f = getDefaultConfiguration();
        }
        return f;
    }

    /**
     * Gets the default <code>SQLDevToolsUIConfiguration</code> object, which is contributed via the "isDefault"
     * attribute of the "dbConfiguration" extension point, or if there's no such contribution, will use
     * SQLDevToolsUIConfiguration.getDefaultInstance().
     * 
     * @return default <code>SQLDevToolsUIConfiguration</code> object. Will never be null.
     */
    public static SQLDevToolsUIConfiguration getDefaultConfiguration()
    {
        SQLDevToolsUIConfiguration defaultConfiguration = SQLDevToolsUIConfigRegistryImpl.getDefaultConfiguration();
        if (defaultConfiguration == null)
        {
            defaultConfiguration = SQLDevToolsUIConfiguration.getDefaultInstance();
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
     * Returns a database-specific SQL statement service class.
     * 
     */
    public static SQLEditorUIService getSQLEditorService(DatabaseIdentifier databaseIdentifier, String dbType)
    {
        SQLDevToolsUIConfiguration f = getConfiguration(dbType, databaseIdentifier);
        return f.getSQLEditorUIService();
    }

    public static SQLDevToolsUIConfiguration getConfiguration(String dbType, DatabaseIdentifier databaseIdentifier)
    {
        if (dbType == null)
        {
            return getConfiguration(databaseIdentifier, null);
        }
        return getConfiguration(databaseIdentifier, new DatabaseVendorDefinitionId(dbType));
    }
    
    /**
     * Returns a database-specific query plan service class.
     * 
     */
    public static IPlanService getPlanService(DatabaseIdentifier databaseIdentifier)
    {
        SQLDevToolsUIConfiguration f = getConfiguration(null, databaseIdentifier);
        return f.getPlanService();
    }
    
    /**
     * Return all the available plan options
     * 
     * @return
     */
    public static Collection getPlanOptions()
    {
        Collection c = getRegistry().getConfigurations();
        int size = c.size();
        SQLDevToolsUIConfiguration[] fs = (SQLDevToolsUIConfiguration[]) c.toArray(new SQLDevToolsConfiguration[size]);
        Collection planOps = new ArrayList();
        for (int i = 0; i < size; i++)
        {
            planOps.add(fs[i].getPlanService().getPlanOption());
        }
        return planOps;
    }
    

    /**
     * Return a specific GenericSQLContextType object which identifies the context type of templates used in SQL editor.
     * 
     * @param dbType
     * @return
     */
    public static GenericSQLContextType getSQLContextType(String dbType)
    {
        SQLUIService service = getSQLUIService(null, dbType);
        if (service != null)
        {
            return service.getSQLContextType();
        }
        return null;
    }
    
    /**
     * Returns a database-specific SQL statement UI service class.
     * 
     * @param profileName
     * @param dbType
     * @return
     */
    public static SQLUIService getSQLUIService(DatabaseIdentifier databaseIdentifier, String dbType)
    {
        SQLDevToolsUIConfiguration f = getConfiguration(dbType, databaseIdentifier);
        return f.getSQLUIService();
    }

    /**
     * Return all the GenericSQLContextType objects which identify the context type of templates used in SQL editor.
     * 
     * @return
     */
    public static Collection getSQLContextTypes()
    {
        Collection c = getRegistry().getConfigurations();
        int size = c.size();
        SQLDevToolsUIConfiguration[] fs = (SQLDevToolsUIConfiguration[]) c.toArray(new SQLDevToolsConfiguration[size]);
        Collection ctxTypes = new ArrayList();
        for (int i = 0; i < size; i++)
        {
            ctxTypes.add(fs[i].getSQLUIService().getSQLContextType());
        }
        return ctxTypes;
    }
    
    public static boolean showAction(String dbType, String actionId)
    {
        SQLDevToolsUIConfiguration f = null;
        f = getConfigurationByDBDefName(dbType);
        if (f != null)
        {
            return f.getActionService().supportsAction(actionId);
        }
        return false;
    }
    
}
