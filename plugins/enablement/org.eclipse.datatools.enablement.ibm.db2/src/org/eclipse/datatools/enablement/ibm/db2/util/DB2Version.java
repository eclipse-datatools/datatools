/*******************************************************************************
 * Copyright (c) 2001, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.enablement.ibm.util.ConnectionProfileUtility;
import org.eclipse.datatools.enablement.ibm.util.DBVersion;

/**
 * Abstracts a DB2 version consisting of a product name and three integers: version,
 * release, and mod level. It supports comparisons between versions, as
 * well as comparisons against precisely specified version, release, and/or
 * mod levels.
 * <p>
 * This class also provides a shared set of unique DB2Version instances,
 * which you may construct and access using the getSharedInstance methods,
 * instead of using the constructors directly.
 * Additional methods could be added for contributing a sharable instance
 * and for removing them when they are not needed.
 */
public class DB2Version extends DBVersion
{
    /** Shared instance of the default version. */
    protected static DB2Version sharedDefault;
    /** Reusable DB2Version instances. */
    protected static ArrayList<DB2Version> sharedInstances = new ArrayList<DB2Version>();
    
   /**
    * Gets a shared DB2Version, and creates one if it doesn't already exist.
    * @param product The product name.
    * @param version The version.
    * @param release The release
    * @param mod The modification level.
    * @return A shared DB2Version.
    */
   public static DB2Version getSharedInstance(String product, int version, int release, int mod)
   {
      if (product == null)
         product = WORKSTATION;
      DB2Version db2version = findSharedInstance(product, version, release, mod);
      if (db2version == null)	// Didn't find one; create one:
      {
         db2version = new DB2Version(product, version, release, mod);
         sharedInstances.add(db2version);
      }
      return db2version;
   }

   /**
    * Gets a shared DB2Version, and creates one if it doesn't already exist.
    * @param product The product name.
    * @param productVersion The version string (with version, release, and possibly modification)
    * @return A shared DB2Version.
    */
   public static DB2Version getSharedInstance(String product, String productVersion)
   {
      int[] v = getVersionArray(productVersion);
      return getSharedInstance(product, v[0], v[1], v[2]);
   }

   /**
    * Gets a shared DB2Version, and creates one if it doesn't already exist.
    * @param profile IConnectionProfile
    * @return A shared DB2Version.
    */
   public static DB2Version getSharedInstance(IConnectionProfile profile)
   {
      if (profile == null)
         return getDefault();
      ConnectionInfo conInfo = ConnectionProfileUtility.getConnectionInfo(profile, false);
      if (conInfo != null)
         return getSharedInstance(conInfo);
      DatabaseDefinition dbDef = ConnectionProfileUtility.getDatabaseDefinition(profile);
      return getSharedInstance(dbDef);
   }

   /**
    * Gets a shared DB2Version, and creates one if it doesn't already exist.
    * @param myConnection An RLDBConnection.
    * @return A shared DB2Version.
    */
   public static DB2Version getSharedInstance(ConnectionInfo myConnection)
   {
      if (myConnection == null)
         return getDefault();

      /* obtain version from the connection info
       * or when not set, from the DatabaseDefinition
       */
      DatabaseDefinition dbDef = myConnection.getDatabaseDefinition();
      String productVersion = myConnection.getDatabaseProductVersion();
      if (productVersion == null)
      {
         Connection con = myConnection.getSharedConnection();
         if (con != null)
         {
            try
            {
               DatabaseMetaData dbmd = con.getMetaData();
               productVersion = dbmd.getDatabaseProductVersion();
            }
            catch (SQLException sqle)
            {
               productVersion = null;
            }
         }
      }
      if(isSybase(dbDef)){
          productVersion = dbDef.getVersion();
      } else if (isIMS(dbDef)){
          productVersion = dbDef.getVersion();
      }
      boolean isOracleDatabase = dbDef.getProduct().equalsIgnoreCase(ORACLE) ? true : false;
      if (productVersion == null || isOracleDatabase)
      {
         productVersion = dbDef.getVersion();
      }
      String product = dbDef.getProduct();

      if (productVersion!= null && productVersion.length() > 0) {
         int[] v = getVersionArray(productVersion);
         if (v== null)
            v = new int []{0,0,0};
         else if(isIDS(dbDef)) {
             //only the first digit in the IDS release represents the real release number
             //for example 11.70 represent version 11 release 7. This change is 
             //made to be consistent with informix database definition
             if(v[1]>=10)
                v[1]=v[1]/10;                           
         }


         return getSharedInstance(product, v[0], v[1], v[2]);
      }
      else {
         int[] v = getVersionArray(myConnection.getDatabaseDefinition().getVersion());
         if (v== null)
            v = new int []{0,0,0};

         return getSharedInstance(product, v[0], v[1], v[2]);
      }
   }

   /**
    * Gets a shared DB2Version, and creates one if it doesn't already exist.
    * @param dbDef A database definition.
    * @return A shared DB2Version.
    */
   public static DB2Version getSharedInstance(DatabaseDefinition dbDef)
   {
      if (dbDef == null)
         return getDefault();

      /* obtain version from the connection info
       * or when not set, from the DatabaseDefinition
       */
      String version = dbDef.getVersion();
      String product = dbDef.getProduct();

      int[] v = getVersionArray(version);
      if (v == null)
         v = new int []{0,0,0};

      return getSharedInstance(product, v[0], v[1], v[2]);
   }

   /**
    * Gets a shared DB2Version, and creates one if it doesn't already exist.
    * @param connection A Connection.
    * @return A shared DB2Version.
    */
   public static DB2Version getSharedInstance(Connection connection)
   {
      String versionString = null;
      String product;
      try
      {
         product = connection.getMetaData().getDatabaseProductName();
         versionString = connection.getMetaData().getDatabaseProductVersion();
      }
      catch (SQLException sqle)
      {
         product = WORKSTATION;
         versionString = "8.2.0"; //$NON-NLS-1$
      }
      int[] v = getVersionArray(versionString);
      if (v== null)
         v = new int []{0,0,0};
      return getSharedInstance(product, v[0], v[1], v[2]);
   }

   public DB2Version(Connection connection)
   {
      super(connection);
   }

   public DB2Version(String product, String versionString)
   {
      super(product, versionString);
   }

   public DB2Version(String versionString)
   {
      super(versionString);
   }

   public DB2Version(int version, int release, int mod)
   {
      super(version, release, mod);
   }

   public DB2Version(String prod, int version, int release, int mod)
   {
      super(prod, version, release, mod);
   }

   public DB2Version(DB2Version copyme)
   {
      super(copyme);
   }

   public DB2Version(IConnectionProfile profile)
   {
      super(profile);
   }

   public DB2Version(ConnectionInfo conInfo)
   {
      super(conInfo);
   }

   /**
    *  Gets a shared instance for a DatabaseDefinition.
    *  <p>
    *  @param The shared instance.
    */
   public DB2Version(DatabaseDefinition dbDef)
   {
      super(dbDef);
   }

   /**
    *  Gets the default shared instance, which is a new DB2Version(WORKSTATION,8,2,0).
    *  <p>
    *  @param The default shared instance of DB2Version.
    */
   public static DB2Version getDefault()
   {
      if (sharedDefault == null)
         sharedDefault = new DB2Version(WORKSTATION,8,2,0);
      return sharedDefault;
   }

   /**
    * Gets a shared DB2Version, and returns null if it doesn't exist.
    * @param product The product name.
    * @param version The version.
    * @param release The release
    * @param mod The modification level.
    * @return A shared DB2Version.
    */
   protected static DB2Version findSharedInstance(String product, int version, int release, int mod)
   {
      Iterator<DB2Version> i = sharedInstances.iterator();
      DB2Version db2version;
      while (i.hasNext())
      {
         db2version = i.next();
         if (product.equals(db2version.getProduct())
               && version == db2version.getVersion()
               && release == db2version.getRelease()
               && mod == db2version.getMod())
            return db2version;
      }
      // Didn't find one; return null:
      return null;
   }

}
