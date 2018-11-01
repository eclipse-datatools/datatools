/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.sqltools.parsers.sql.SQLParserLogger;

/** 
 * Singleton <code>SQLQueryParserManagerProvider</code> holds a registry of
 * <code>SQLQueryParserManager</code> extensions and creates instances of
 * <code>SQLQueryParserManager</code>s, that are registered for a specific
 * database product and version.
 * As plugin in an eclipse runtime environment, the registration of
 * <code>SQLQueryParserManager</code> extensions is done at start time of the
 * SQL Query parser plugin.
 * The registry is implemented following the lazy loading
 * startegy and the plugin extending the SQL Query parser plugin is only loaded
 * the first time the parser for the specific SQL dialect is needed.
 * 
 * @see #getParserManager(String, String)
 * @see #registerParserManager(String, String, String)
 * 
 * @author ckadner
 */
public class SQLQueryParserManagerProvider 
{
    
    private class ParserManagerDBSupportInfo {
        String dbProduct = null;
        String dbLatestVersion = null;
        String dbEarliestVersion = null;
        String parserManagerClassName = null;
        
        public boolean equals(Object obj)
        {
            boolean isEqual = false;
            if (obj instanceof ParserManagerDBSupportInfo)
            {
                ParserManagerDBSupportInfo pmInfo =
                    (ParserManagerDBSupportInfo) obj;
                
                isEqual = pmInfo.dbProduct.equals(dbProduct);
                isEqual &= (pmInfo.dbLatestVersion != null
                                && dbLatestVersion != null
                                && pmInfo.dbLatestVersion.equals(dbLatestVersion))
                                || (pmInfo.dbLatestVersion == null && dbLatestVersion == null);
                isEqual &= (pmInfo.dbEarliestVersion != null
                                && dbEarliestVersion != null
                                && pmInfo.dbEarliestVersion.equals(dbEarliestVersion))
                                || (pmInfo.dbEarliestVersion == null && dbEarliestVersion == null);
                // don't compare the ParserManager class because we can't have
                // two different PM for the same dbProduct and version anyway!!
            }
            return isEqual;
        }
    }
    
    
    /** Mapping of database products to yet another mapping of product versions
     *  to the <code>Class</code> name of a <code>SQLQueryParserManager</code>
     *  that supports the SQL dialect of the database product version.
     * <ul>
     *   <li>keys: (String) dbProduct,
     *   <li>values: (HashMap) dbVersion-to-parserManager<ul>
     *         <li>keys: (String) dbVersion,
     * 		   <li>values: (String) fully qualified <code>Class</code> name of
     * 		       the supporting <code>SQLQueryParserManager</code></ul>
     * </ul> */
    private Map dbProductMap = null;

    private SQLQueryParserManagerProvider()
    {
        dbProductMap = new HashMap();
    }

    private static SQLQueryParserManagerProvider instance = null;
    
    /**
     * Returns the singleton <code>SQLQueryParserManagerProvider</code> instance.
     * @return <code>SQLQueryParserManagerProvider</code>
     */
    public static SQLQueryParserManagerProvider getInstance()
    {
        if (instance == null)
        {
            instance = new SQLQueryParserManagerProvider();
        }
        return instance;
    }
    
    
    
    /**
     * Returns the best compliant <code>SQLQueryParserManager</code>
     * supporting the SQL dialect of the database described by the given
     * database product information.
     * 
     * @param dbProduct
     *            the database vendor and product name, e.g. <i>"DB2 UDB" </i>
     * @param dbVersion
     *            the database version and release, e.g. <i>"V8.1" </i>
     * @return <code>SQLQueryParserManager</code> supporting the SQL dialect
     *         of the specified database
     */
    public SQLQueryParserManager getParserManager(String dbProduct, String dbVersion)
    {
        SQLQueryParserManager parserManager = null;
        String parserManagerClassName = null;
        
        // TODO: work in the registration of ParserManagerDBSupportInfo List
        //     and versionchecking
        
        
        if (dbProduct != null && dbProduct.length() > 0
                        && dbProductMap.containsKey(dbProduct))
        {
            // HashMap permits null values! we will use that
            HashMap dbProductVersionMap = (HashMap) dbProductMap.get(dbProduct);
            
            if (dbProductVersionMap.containsKey(dbVersion))
            {
                parserManagerClassName =
                    (String) dbProductVersionMap.get(dbVersion);
            }
            // if there is only one parser extension for the product with
            // no version specification, use it
            else if (dbProductVersionMap.size() == 1
                            && dbProductVersionMap.containsKey(null))
            {
                parserManagerClassName =
                    (String) dbProductVersionMap.get(null);
            }
            else
            {
                // iteraterate through the extensions we have and find the best
                // fitting SQLQueryParserManager
                
                // TODO: find best fitting registered SQLQueryParserManager
                // do the check for another version that might include the
                // requested dbVersion
                // we need some kind of general (for all vendors) representation
                // of versions and releases structured like (int version-number, int release-number)
                // and some generic component comparing that inclusively
                // fromVersion 3.1 toVersion 8.2 should include version 4.5
                
                // ... code!
                
                // TODO: remove that short sighted workaround!
                // check for a null-mapping that might fit generally for the product
                if (dbProductVersionMap.containsKey(null))
                {
                    parserManagerClassName =
                        (String) dbProductVersionMap.get(null);
                }
                else
                {
                    if (!dbProductVersionMap.isEmpty())
                    {
                        parserManagerClassName =
                            (String) dbProductVersionMap.values().iterator().next();
                    }
                }
                
            }
        }
        
        if (parserManagerClassName != null)
        {
            parserManager = SQLQueryParserPlugin.getDefault().instanciateParserManager(parserManagerClassName);
            
            // direct instanciation doesn't work as our classloader has no
            // handle on the unknown extension plugins
            //Class parserManagerClass = Class.forName(parserManagerClassName);
            //parserManager = (SQLQueryParserManager) parserManagerClass.newInstance();
        }
        
        // if no SQLQueryParserManager was registered, take the standard one
        if (parserManager == null)
        {
            // TODO: log that no specific vendor extension was found for the db
            parserManager = new SQLQueryParserManager();
        }
        
        return parserManager;
    }
    
    /**
     * Clears the registry of <code>SQLQueryParserManager</code>
     * extensions.
     */
    public void clearParserManagerRegistry()
    {
        if (dbProductMap != null)
        {
            dbProductMap.clear();
        }
    }
    
    /**
     * Registers the <code>SQLQueryParserManager</code> <code>Class</code>
     * name of the SQL Query parser plugin extension. For the purpose of lazy
     * loading we only register the <code>Class</code> name of the
     * <code>SQLQueryParserManager</code> extension and instanciate the
     * <code>Class</code> only at actual execution of a SQL query for the
     * specific db vendor dialect.
     * 
     * @param parserManagerClassName
     *            the fully qualified name of the
     *            <code>SQLQueryParserManager</code> extension to be
     *            registered
     * @param dbProduct
     *            the database vendor and product name, e.g. <i>"DB2 UDB" </i>
     * @param fromDbVersion
     *            the earliest database version and release, e.g. <i>"V6.1" </i>
     *            that is supported by the ParserManager
     * @param toDbVersion
     *            the latest database version and release, e.g. <i>"V8.1" </i>
     *            that is supported by the ParserManager
     */
    public void registerParserManager(String parserManagerClassName,
                                      String dbProduct,
                                      String fromDbVersion,
                                      String toDbVersion)
    {
        if (dbProduct != null && dbProduct.length() > 0)
        {
            // HashMap permits null values! we will use that
            if (toDbVersion != null && toDbVersion.trim().length() == 0)
            {
                toDbVersion = null;
            }
            
            // if we did not yet have a registered SQLQueryParserManager for any
            // version of the dbProduct, create a "registry" Map
            if (!dbProductMap.containsKey(dbProduct))
            {
                dbProductMap.put(dbProduct, new HashMap());
                // TODO: enable this code, disable code above
                //dbProductMap.put(dbProduct, new ArrayList());
            }
            
            // HashMap permits null values! we will use that
            HashMap dbVersionToPMClassNameMap =
                (HashMap) dbProductMap.get(dbProduct);
            // TODO: enable this code, disable code above
            //List dbVersionPMInfoList = (List) dbProductMap.get(dbProduct);
            
            
            
            // register the SQLQueryParserManager for the db product and version
            // if it hasn't been one registered already
            if (dbVersionToPMClassNameMap.containsKey(toDbVersion))
            {
                String registeredPMClassName = (String) dbVersionToPMClassNameMap.get(toDbVersion);
                //TODO: log conflicting parser extensions
                SQLParserLogger.getLogger().writeLog(
                        "conflicting parser extensions for " + //$NON-NLS-1$
                        dbProduct + " " + toDbVersion + //$NON-NLS-1$
                        ": " + //$NON-NLS-1$
                        registeredPMClassName + " != " + //$NON-NLS-1$
                        parserManagerClassName);
            }
            else
            {
                dbVersionToPMClassNameMap.put(toDbVersion, parserManagerClassName);
            }
            // TODO: enable this code, disable code above
//            ParserManagerDBSupportInfo pmInfo = new ParserManagerDBSupportInfo();
//            pmInfo.dbProduct = dbProduct;
//            pmInfo.dbEarliestVersion = fromDbVersion;
//            pmInfo.dbLatestVersion = toDbVersion;
//            pmInfo.parserManagerClassName = parserManagerClassName;
//            
//            if (!dbVersionPMInfoList.contains(pmInfo))
//            {
//                dbVersionPMInfoList.add(pmInfo);
//            }
//            else
//            {
//                // report doublicate mapping!
//            }
            

        }
    }


    /**
     * Unregisters the <code>SQLQueryParserManager</code> <code>Class</code>
     * name of the SQL Query parser plugin extension, for example if the
     * extending plugin was stoped
     * {@link org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)}.
     * 
     * @param parserManagerClassName
     *            the fully qualified name of the
     *            <code>SQLQueryParserManager</code> extension to be
     *            registered
     * @param dbProduct
     *            the database vendor and product name, e.g. <i>"DB2 UDB" </i>
     * @param dbVersion
     *            the database version and release, e.g. <i>"V8.1" </i>
     */
    public void unregisterParserManager(String parserManagerClassName)
    {
        for (Iterator it = dbProductMap.entrySet().iterator(); it.hasNext();)
        {
            // key: db product, value: Map of versions
            Map.Entry productToVersionMap = (Map.Entry) it.next();
            
            // Map of db versions to ParserManager class names
            Map dbVersionToPMClassNameMap = (Map) productToVersionMap.getValue();
            
            // if we have at least one mapping for the given parserManager,
            // we have to clear out the Map
            if (dbVersionToPMClassNameMap.containsValue(parserManagerClassName))
            {
                for (Iterator vpmIt = dbVersionToPMClassNameMap.entrySet().iterator(); vpmIt.hasNext();)
                {
                    Map.Entry dbVersionToPMClassName = (Map.Entry) vpmIt.next();
                    String registeredPMClassName =
                        (String) dbVersionToPMClassName.getValue(); 
                    
                    if (parserManagerClassName.equals(registeredPMClassName))
                    {
                        vpmIt.remove();
                    }
                }
                
                // check if we still have parserManagers for the db product
                if (dbVersionToPMClassNameMap.isEmpty())
                {
                    it.remove();
                }
            }
            
            
            
        }
        
        
//        {
//        	// HashMap permits null values! we will use that
//            HashMap dbProductVersionMap = (HashMap) dbProductMap.get(dbProduct);
//            
//            // register the SQLQueryParserManager for the db product and version
//            // if it hasn't been one registered already
//            if (dbProductVersionMap.containsKey(dbVersion))
//            {
//                String registeredPMClassName =
//                    (String) dbProductVersionMap.get(dbVersion);
//                //TODO: log conflicting parser extensions
//                SQLParserManager.logError("conflicting parser extensions for "+
//                                dbProduct + " " + dbVersion +
//                                ": " +
//                                registeredPMClassName + " != " +
//                                parserManagerClassName);
//            }
//            else
//            {
//                dbProductVersionMap.put(dbVersion, parserManagerClassName);
//            }
//            
//
//        }
    }


}
