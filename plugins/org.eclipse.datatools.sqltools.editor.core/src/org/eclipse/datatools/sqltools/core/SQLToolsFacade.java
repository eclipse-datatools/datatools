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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.core.services.SQLDataService;
import org.eclipse.datatools.sqltools.core.services.SQLEditorService;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionInitializer;
import org.eclipse.datatools.sqltools.editor.template.GenericSQLContextType;
import org.eclipse.datatools.sqltools.internal.core.SQLDevToolsConfigRegistry;
import org.eclipse.datatools.sqltools.internal.core.SQLDevToolsConfigRegistryImpl;
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.sql.ISQLSyntax;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;

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
     * Gets the <code>SQLDevToolsConfiguration</code> object. This is a utility method for getConfigurationByProfileName(String
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
        	//get DatabaseVendorDefinitionId from profile, then call getConfigurationByVendorIdentifier
        	//this is to make sure we are using the REAL version
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

	public static DatabaseVendorDefinitionId getCanonicalDatabaseVendorDefinitionId(DatabaseVendorDefinitionId vendorId) {
		SQLDevToolsConfiguration f;
		f = getRegistry().getConfigurationByVendorIdentifier(vendorId);
		//find the aliases
		if (f == null)
		{
			vendorId = recognize(vendorId.getProductName(), vendorId.getVersion());
			f = getRegistry().getConfigurationByVendorIdentifier(vendorId);
		}
		if (f == null)
		{
			//try to use the latest version
			Collection versions = getRegistry().getVersions(vendorId.getProductName());
			if (versions != null)
			{
				String version = "0";
				for (Iterator iter = versions.iterator(); iter.hasNext();) {
					String v = (String) iter.next();
					if (v.compareTo(version) >=0 )
					{
						version = v;
					}
				}
				vendorId = new DatabaseVendorDefinitionId(vendorId.getProductName(), version);
				f = getRegistry().getConfigurationByVendorIdentifier(vendorId);
			}
		}
		return vendorId;
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
    	SQLDevToolsConfiguration defaultConfiguration = SQLDevToolsConfigRegistryImpl.getDefaultConfiguration();
    	if (defaultConfiguration == null)
    	{
    		defaultConfiguration = SQLDevToolsConfiguration.getDefaultInstance();
    	}
		return defaultConfiguration;
    }
    
    public static DatabaseVendorDefinitionId recognize(String product, String version)
    {
    	DatabaseVendorDefinitionId defaultId = getDefaultConfiguration().getDatabaseVendorDefinitionId();
    	Collection configs = getConfigurations();
    	for (Iterator iter = configs.iterator(); iter.hasNext();) {
    		SQLDevToolsConfiguration conf = (SQLDevToolsConfiguration) iter.next();
			if (conf.recognize(product, version) && !(conf.getDatabaseVendorDefinitionId().equals(defaultId)))
			{
				return conf.getDatabaseVendorDefinitionId();
			}
		}
		return defaultId;
    }
    
    //temporary methods, to be inlined.
    public static int getConnectionId(DatabaseIdentifier databaseIdentifier, Connection conn)
    {
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

	public static ConnectionService getConnectionService(DatabaseIdentifier databaseIdentifier) {
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
        return getDBHelper(databaseIdentifier,null);
    }

    /**
     * 
     * @param dbType
     * @return
     */
    public static DBHelper getDBHelper(String dbType)
    {
        return getDBHelper(null,dbType);
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

    /**
     * Return a specific GenericSQLContextType object which identifies the context type of templates used in SQL editor.
     * 
     * @param dbType
     * @return
     */
    public static GenericSQLContextType getSQLContextType(String dbType)
    {
        SQLService service = getSQLService(null, dbType);
        if (service != null)
        {
            return service.getSQLContextType();
        }
        return null;
    }

    /**
     * Returns a database-specific SQL statement service class.
     * 
     */
    public static SQLEditorService getSQLEditorService(DatabaseIdentifier databaseIdentifier, String dbType)
    {
        SQLDevToolsConfiguration f = getConfiguration(dbType, databaseIdentifier);
        return f.getSQLEditorService();
    }


    /**
     * Returns a database-specific query plan service class.
     * 
     */
    public static IPlanService getPlanService(DatabaseIdentifier databaseIdentifier)
    {
        SQLDevToolsConfiguration f = getConfiguration(null, databaseIdentifier);
        return f.getPlanService();
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

    /**
     * Return all the GenericSQLContextType objects which identify the context type of templates used in SQL editor.
     * 
     * @return
     */
    public static Collection getSQLContextTypes()
    {
        Collection c = getRegistry().getConfigurations();
        int size = c.size();
        SQLDevToolsConfiguration[] fs = (SQLDevToolsConfiguration[]) c.toArray(new SQLDevToolsConfiguration[size]);
        Collection ctxTypes = new ArrayList();
        for (int i = 0; i < size; i++)
        {
            ctxTypes.add(fs[i].getSQLService().getSQLContextType());
        }
        return ctxTypes;
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
        SQLDevToolsConfiguration[] fs = (SQLDevToolsConfiguration[]) c.toArray(new SQLDevToolsConfiguration[size]);
        Collection planOps = new ArrayList();
        for (int i = 0; i < size; i++)
        {
            planOps.add(fs[i].getPlanService().getPlanOption());
        }
        return planOps;
    }

    public static boolean showAction(String dbType, String actionId)
    {
        SQLDevToolsConfiguration f = null;
        f = getConfigurationByDBDefName(dbType);
        if (f != null)
        {
            Collection excludes = f.getSQLEditorService().getExcludedActionIds();
            if (excludes != null)
            {
                return !excludes.contains(actionId);
            }
        }
        return true;
    }

    public static SQLDevToolsConfiguration getConfiguration(String dbType, DatabaseIdentifier databaseIdentifier)
    {
    	if(dbType == null)
    	{
    		return getConfiguration(databaseIdentifier, null);
    	}
        return getConfiguration(databaseIdentifier, new DatabaseVendorDefinitionId(dbType));
    }
}
