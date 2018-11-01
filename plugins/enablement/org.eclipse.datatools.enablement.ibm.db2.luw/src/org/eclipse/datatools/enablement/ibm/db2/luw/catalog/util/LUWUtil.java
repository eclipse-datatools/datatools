/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.datatools.enablement.ibm.db2.luw.DB2LUWPluginActivator;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public class LUWUtil {

    private final static String SINGLE_QUOTE         = "'"; //$NON-NLS-1$
    
	public static String getIdentifier(String name) {
		String identifier = name;
		identifier = identifier.replaceAll("'", "''");
		return identifier;
	}

	
    public static String getSingleQuotedString(String orignal) {
    	if (orignal.startsWith(SINGLE_QUOTE) && orignal.endsWith(SINGLE_QUOTE)) {
    		return orignal;
    	}
    	
    	StringTokenizer tokenizer = new StringTokenizer(orignal, SINGLE_QUOTE);
    	String result = orignal;
  		if (tokenizer.hasMoreTokens()) {
  			result = tokenizer.nextToken();
  		} else {
  			return result;
  		}
        while(tokenizer.hasMoreTokens()) {
            result =  result + SINGLE_QUOTE + SINGLE_QUOTE + tokenizer.nextToken();
        }
        return SINGLE_QUOTE + result + SINGLE_QUOTE;
    }

	public Privilege findPrivileges (Object[] privilegelist, String grantee){
		for (int i = 0; i < privilegelist.length; i++){
			Privilege priv = (Privilege) privilegelist[i];
			if (priv.getGrantee().getName().equals(grantee)){
				return (Privilege) privilegelist[i];
			}
		}
		return null;
	}
	
	public static List<String> getDiscoveredLibraries(LUWDatabase luwdb)
	{
		List<String> libraries = new ArrayList<String>();
		Hashtable wrapperConfigs = new Hashtable();
		WrapperConfigFile wrapperConfig;
		Database database = (Database)luwdb;
		LUWDatabaseImpl db = (LUWDatabaseImpl)database;
      	try 
      	{
      		WrapperConfigManager wrapperConfigMgr = new WrapperConfigManager(db);
      		ServerKindsConfig serverKinds = wrapperConfigMgr.getServerKindsConfig();
      		//constructor initializes itself serverKinds.init(db, conn);
      		wrapperConfigs = serverKinds.getAllWCF();
     	}
      	catch (Exception e) 
      	{
      		DB2LUWPluginActivator.getInstance().log(null,e);
  		}
      	//go through all the configuration files for each wrapper
      	//and get the configuration values (e.g. server type, version, etc.)
      	Enumeration configs = wrapperConfigs.keys();
      	// This is code where data sources are discovered and mentioned in DSE
      	while (configs.hasMoreElements()) 
      	{
      		String configID =(String)configs.nextElement();
      		wrapperConfig = (WrapperConfigFile)wrapperConfigs.get(configID);
      		try
      		{
      			String wrapperID = wrapperConfig.getWrapperID();
      			String folderName = new String();
      			if( wrapperID.equalsIgnoreCase("drda")) 
      			{
      				libraries.add("DB2");
      			}
      			else if( wrapperID.equalsIgnoreCase("sqlnet") || wrapperID.equalsIgnoreCase("net8")) {
      				libraries.add("ORACLE");
      			}
      			else if( wrapperID.equalsIgnoreCase("dblib") || wrapperID.equalsIgnoreCase("ctlib")) {
      				libraries.add("SYBASE");
      			}
      			else if( wrapperID.equalsIgnoreCase("mssql3")) {
      				libraries.add("SQL_SERVER");
      			}
      			else if( wrapperID.equalsIgnoreCase("odbc")) {
      				libraries.add("ODBC");
      			}
      			else if( wrapperID.equalsIgnoreCase("informix")) {
      				libraries.add("INFORMIX");
      			}
      			else if( wrapperID.equalsIgnoreCase("teradata")) {
      				libraries.add("TERADATA");
      			}
      			else if( wrapperID.equalsIgnoreCase("jdbc")) {
      				libraries.add("JDBC");
      			}
      			
      		}
      		catch (Exception e) {
      			DB2LUWPluginActivator.getInstance().log(null,e);     			
      		}
      	}
		return libraries;
	}


}
