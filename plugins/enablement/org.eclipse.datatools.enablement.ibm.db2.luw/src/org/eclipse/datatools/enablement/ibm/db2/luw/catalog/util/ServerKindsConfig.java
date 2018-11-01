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

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
//import com.ibm.datatools.db2.luw.serverdiscovery.WrapperConfigFile;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl;
 
public class ServerKindsConfig {

   private Hashtable libnameToWrapperid = null;
   private Hashtable wrapperidToName = null;
   private Hashtable nameToWrapperid = null;
   private Hashtable nameToDescription = null;
   private Hashtable nameToLibname = null;
   private Hashtable allWCFiles = null;
//   private WrapperConfigFile wrapperConfigFile;
   /**
   *  Initalize data from the XML files.
   *
   * @param  Database object to use for connection
   */
    public void init (LUWDatabaseImpl aDatabase) throws Exception {
      try{
        //call WrapperConfigFiles.initAllFiles to get all data
        //hashtable keys are wrapperids
      	
        Hashtable wcFiles = WrapperConfigFile.initAllFiles(aDatabase);
        allWCFiles = wcFiles;
        //build hastables with data
        Set keys = wcFiles.keySet();
        Iterator iterKeys =  keys.iterator();

        //create hash tables
        libnameToWrapperid = new Hashtable();
        wrapperidToName = new Hashtable();
        nameToWrapperid = new Hashtable();
        nameToDescription = new Hashtable();
        nameToLibname = new Hashtable();

        //iterate through configuration files
        while(iterKeys.hasNext()){
           String wrapperid = (String)iterKeys.next();
           WrapperConfigFile wcf = (WrapperConfigFile)wcFiles.get(wrapperid);
           String libname = wcf.getLibname();
           //skip wrapper if library is empty
           if(libname.length() > 0){
             libnameToWrapperid.put(libname, wrapperid);
             Hashtable sk =  wcf.getServerKinds();
             if(!sk.isEmpty() ){
               Iterator iterServerKinds = sk.keySet().iterator();
               while(iterServerKinds.hasNext()){
                 String skName = (String)iterServerKinds.next();
                 String description = (String)sk.get(skName);
                 nameToDescription.put(skName, description);
                 wrapperidToName.put(wrapperid, skName);
                 nameToWrapperid.put(skName, wrapperid);
                 nameToLibname.put(skName, libname);
               }
             }
           }
        }
       }catch(Exception e){
          throw e;
       }
    }

   /**
   *  Get the list of server kinds from all the wrapper congifuration files.
   *
   * @param Enumeration of server kind names
   */
    public Enumeration getServerKinds() throws Exception {
        return nameToDescription.keys();
    }

   /**
   * Get the wrapper id from the library name.
   * @param library name
   * @return wrapper id
   */
    public String getWrapperID(String libName)  throws Exception{
        return (String)libnameToWrapperid.get(libName);
    }


   /**
   * Get the library name from the server kind name.
   * @param serverKindName the display name for the kind of server, returned by getServerKinds()
   * @return library name
   */
    public String getLibname(String serverKindName)  throws Exception{
        return (String)nameToLibname.get(serverKindName);

    }

   /**
   * Get the server kinds name from the wrapper id
   * @param wrapperID String returned by getWrapperID
   * @return server kinds name
   */
    public String getServerKindName(String wrapperID)  throws Exception{
        return (String)wrapperidToName.get(wrapperID);

    }

   /**
   * Get the description form the server kind name.
   * @param serverKindName the display name for the kind of server, returned by getServerKinds()
   * @return library name
   */
    public String getDescription(String serverKindName)  throws Exception{
        return (String)nameToDescription.get(serverKindName);

    }

   /**
   * Get the list of WrapperConfigFile objects returned from initAllFiles.
   * @param serverKindName the display name for the kind of server, returned by getServerKinds()
   * @return Hashtable key is wrapper id, object is initalized WrapperConfigFile
   */
    public Hashtable getAllWCF()  {
        return allWCFiles;
    }

    public Enumeration getAllWrapperIDs() throws Exception{ 
		return wrapperidToName.keys();
	}

 }
