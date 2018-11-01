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

import java.util.Hashtable;

//import com.ibm.datatools.db2.luw.serverdiscovery.WrapperConfigFile;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl;

public class WrapperConfigManager {

   private ServerKindsConfig skc;
   private Hashtable wrapperConfigHT;
   private WrapperConfigFile wcf;
   private static LUWDatabase database; 

   /*# private WrapperConfigManager _objectTypeManager; */
  private static WrapperConfigManager instance = null;


  public WrapperConfigManager(LUWDatabaseImpl _database) throws Exception {
    try {
      skc = new ServerKindsConfig();
      skc.init(_database);
    }
    catch (Exception e) { throw e; }
      wrapperConfigHT = new Hashtable();
    }

  /**
   * Gets the singleton instance of the ObjectTypeManager.
   * @return The singleton ObjectTypeManager.
   * @exception ICMAPIException
   * @exception ICMSQLException
   */
   public static synchronized WrapperConfigManager getInstance (LUWDatabaseImpl _database) throws Exception {
    try{
		if (instance == null) {
			database = _database;
            instance = new WrapperConfigManager(_database);
		}
		else if(!database.getName().equals(_database.getName())){
		    database = _database;
			instance = new WrapperConfigManager(_database);
		}
     }catch (Exception e) {
		 database = null;
		 throw e;
	  }
    return (WrapperConfigManager)instance;
  }


  //method to return the wrapper config file for a given wrapper library
  //initialize the ServerKindsConfig file once,
  //if serverKinds had already been initialized, search the wrapper config hashtable and return the wrapperconfig file for a given wrapper (library)
  //if the hashtable doesn't contain that library key, create and init the wrapper config and put it in the hash table for next time
  public WrapperConfigFile getWrapperConfigFile(String libName) throws Exception { 

    try {

       if(!wrapperConfigHT.containsKey(libName))
          {
             String id = skc.getWrapperID(libName);
             wcf = new WrapperConfigFile();
             wcf.initFile(id, database);
             wrapperConfigHT.put(libName,wcf);
             return wcf;
          }
       else {
             return (WrapperConfigFile)wrapperConfigHT.get(libName);
          }
       }
     catch (Exception e) {
        throw e;
     }
   }

   public ServerKindsConfig getServerKindsConfig() {
      return skc;
   }


}
