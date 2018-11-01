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
package org.eclipse.datatools.enablement.ibm.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.enablement.ibm.util.ConnectionProfileUtility;
import org.eclipse.datatools.enablement.ibm.util.ReuseStringBuffer;
import org.eclipse.datatools.enablement.ibm.util.SQLIdentifier;

/**
 * Abstracts a database version consisting of a product name and three integers: version,
 * release, and mod level. It supports comparisons between versions, as
 * well as comparisons against precisely specified version, release, and/or
 * mod levels.
 * <p>
 * This class also provides a shared set of unique DBVersion instances,
 * which you may construct and access using the getSharedInstance methods,
 * instead of using the constructors directly.
 * Additional methods could be added for contributing a sharable instance
 * and for removing them when they are not needed.
 */
public class DBVersion
{
   public static final String PRODUCT_DB2 = "DB2"; //$NON-NLS-1$
   public static final String DB2FAMILY =	"DB2 Family"; //$NON-NLS-1$
   public static final String DB2390 =	"DB2"; //$NON-NLS-1$

   public static final String DB2400 =	"AS"; //$NON-NLS-1$
   public static final String DB2400SQL =	"DB2/400 SQL";	 // AS/400 Toolbox JDBC //$NON-NLS-1$
   public static final String DB2400UDB =	"DB2 UDB for AS/400";	 // AS/400 Toolbox JDBC version V4R4 after drivers //$NON-NLS-1$

   public static final String DB2ISERIES = "DB2 UDB iSeries"; //$NON-NLS-1$
   public static final String IBMCLOUDSCAPE = "IBM Cloudscape"; //$NON-NLS-1$
   public static final String APACHE_DERBY = "Apache Derby";  // returned by DatabaseMetaData for Derby //$NON-NLS-1$
   public static final String DERBY = "Derby"; //$NON-NLS-1$
   public static final String DB2UDB = "DB2 UDB"; //$NON-NLS-1$
   public static final String DB2ZSERIES = "DB2 UDB zSeries"; //$NON-NLS-1$
   public static final String IDS = "Informix"; //$NON-NLS-1$
   public static final String IDS_ALIAS = "IDS"; //$NON-NLS-1$
   public static final String ORACLE = "Oracle"; //$NON-NLS-1$
   public static final String SYBASE= "Sybase"; //$NON-NLS-1$
   public static final String DB2ALIAS = "DB2 ALIAS"; //$NON-NLS-1$
   public static final String POSTGRES = "Postgres"; //$NON-NLS-1$

   public static final String DB2NT =	"DB2/NT"; //$NON-NLS-1$
   public static final String DB2NT64 =	"DB2/NT64"; //$NON-NLS-1$
   public static final String DB2Windows95 =	"DB2/Windows 95"; //$NON-NLS-1$
   public static final String DB26000 =	"DB2/6000"; //$NON-NLS-1$
   public static final String DB2AIX64 =	"DB2/AIX64"; //$NON-NLS-1$
   public static final String DB26000PE =	"DB2/6000 PE"; //$NON-NLS-1$
   public static final String DB2HPUX =	"DB2/HPUX"; //$NON-NLS-1$
   public static final String DB2HPUX64 =	"DB2/HP64"; //$NON-NLS-1$
   public static final String DB2SUN =	"DB2/SUN"; //$NON-NLS-1$
   public static final String SUN64 =	"DB2/SUN64"; //$NON-NLS-1$
   public static final String LINUX =	"DB2/LINUX"; //$NON-NLS-1$
   public static final String DYNIX =	"DB2/PTX";	 // Sequent (NUMA-Q) //$NON-NLS-1$
   public static final String DB22 =	"DB2/2"; //$NON-NLS-1$
   public static final String WORKSTATION =	"WORKSTATION"; //$NON-NLS-1$
   public static final String AS400 =	"AS400"; //$NON-NLS-1$
   public static final String DB2LINUX390 =	"DB2/LINUX390"; //$NON-NLS-1$
   public static final String IMS =	"IMS"; //$NON-NLS-1$

// DB2 versions

   public static final int DB_VERSION_4 =	4;
   public static final int DB_VERSION_5 =	5;
   public static final int DB_VERSION_6 =	6;
   public static final int DB_VERSION_7 =	7;
   public static final int DB_VERSION_8 =	8;
   //
   public static final int DB_VERSION_9 =   9;
   public static final int DB_VERSION_10 =  10;
   public static final double DB_SDK_VERSION_6 =	6.1;
   public static final double DB_SDK_VERSION_7 =	7.0;
   public static final double DB_SDK_VERSION_8 =	8.0;
   public static final double DB_VERSION_5_REL_1 =	5.1;
   public static final double DB_VERSION_5_REL_2 =	5.2;
   public static final double DB_VERSION_5_REL_3 =	5.3;

   private static final int[] SUPPORTS_SKIP_LEVEL_COMPAT_MODE = { 10 };
   
// DB2 versions (String format)

   public static final String DB_VER_4 =	"V4"; //$NON-NLS-1$
   public static final String DB_VER_5 =	"V5"; //$NON-NLS-1$
   public static final String DB_VER_6 =	"V6"; //$NON-NLS-1$
   public static final String DB_VER_7 =	"V7"; //$NON-NLS-1$
   public static final String DB_VER_8 =	"V8"; //$NON-NLS-1$
   public static final String DB_VER_9 =	"V9"; //$NON-NLS-1$
   public static final String DB_VER_5_REL_1 =	"V5.1"; //$NON-NLS-1$
   public static final String DB_VER_5_REL_2 =	"V5.2"; //$NON-NLS-1$
   public static final String DB_VER_5_REL_3 =	"V5.3"; //$NON-NLS-1$
   // DB version String literals extracted from extensions to ext pt.
   // com.ibm.datatools.core.databaseDefinition
   /** @deprecated */
   public static final String DB_ZSERIES_VERSION_6 = "V6"; //$NON-NLS-1$
   public static final String DB_ZSERIES_VERSION_7 = "V7"; //$NON-NLS-1$
   public static final String DB_ZSERIES_VERSION_8_COMPAT = "V8 (Compatibility Mode)"; //$NON-NLS-1$
   public static final String DB_ZSERIES_VERSION_8_NEWFUN = "V8 (New-Function Mode)"; //$NON-NLS-1$
   // RATLC001118919 and wsdbu00057906
   //public static final String DB_ZSERIES_VERSION_9 = "V9"; //$NON-NLS-1$
   public static final String DB_ZSERIES_VERSION_9_COMPAT = "V9 (Compatibility Mode)"; //$NON-NLS-1$
   public static final String DB_ZSERIES_VERSION_9_NEWFUN = "V9 (New-Function Mode)"; //$NON-NLS-1$
   public static final String DB_ZSERIES_VERSION_10_COMPAT = "V10 (Conversion Mode)"; //$NON-NLS-1$
   public static final String DB_ZSERIES_VERSION_10_NEWFUN = "V10 (New-Function Mode)";//$NON-NLS-1$
   public static final String DB_ZSERIES_VERSION_11_COMPAT = "V11 (Conversion Mode)"; //$NON-NLS-1$
   public static final String DB_ZSERIES_VERSION_11_NEWFUN = "V11 (New-Function Mode)";//$NON-NLS-1$
   
   public static final String DB_VERSION_8_REL_1 = "V8.1"; //$NON-NLS-1$
   public static final String DB_VERSION_8_REL_2 = "V8.2"; //$NON-NLS-1$
   public static final String DB_UDB_VERSION_9 = "V9.1"; //$NON-NLS-1$
   public static final String DB_UDB_VERSION_9_5 = "V9.5"; //$NON-NLS-1$
   public static final String DB_UDB_VERSION_9_7 = "V9.7"; //$NON-NLS-1$
   public static final String DB_UDB_VERSION_10_1 = "V10.1";//$NON-NLS-1$
   public static final String DB_UDB_VERSION_10_5 = "V10.5";//$NON-NLS-1$
   /** @deprecated */
   public static final String DB_ISERIES_VERSION_5_REL_1 = "V5R1"; //$NON-NLS-1$
   /** @deprecated */
   public static final String DB_ISERIES_VERSION_5_REL_2 = "V5R2"; //$NON-NLS-1$
   public static final String DB_ISERIES_VERSION_5 = "5"; //$NON-NLS-1$
   public static final String DB_ISERIES_VERSION_5_REL_3 = "5R3"; //$NON-NLS-1$
   /** @deprecated */
   public static final String DB_ISERIES_VERSION_5_REL_3_NEW = "V05R03"; //$NON-NLS-1$
   public static final String DB_ISERIES_VERSION_5_REL_4 = "5R4"; //$NON-NLS-1$
   /** @deprecated */
   public static final String DB_ISERIES_VERSION_5_REL_4_NEW = "V05R04"; //$NON-NLS-1$
   public static final String DB_CLOUDSCAPE_VERSION_10_REL_0 = "10.0"; //$NON-NLS-1$
   public static final String DB_CLOUDSCAPE_VERSION_10_REL_1 = "10.1"; //$NON-NLS-1$
   public static final String DB_CLOUDSCAPE_VERSION_10_REL_2 = "10.2"; //$NON-NLS-1$
   public static final String DB_CLOUDSCAPE_VERSION_10_REL_3 = "10.3"; //$NON-NLS-1$
   public static final String DB_CLOUDSCAPE_VERSION_10_REL_4 = "10.4"; //$NON-NLS-1$
   public static final String DB_CLOUDSCAPE_VERSION_10_REL_5 = "10.5"; //$NON-NLS-1$
   public static final String DB_CLOUDSCAPE_VERSION_10_REL_6 = "10.6"; //$NON-NLS-1$
   public static final String DB_IDS_VERSION_10_REL_0 = "10.00"; //$NON-NLS-1$
   public static final String DB_IDS_VERSION_9_REL_5 = "9.50"; //$NON-NLS-1$
   public static final String DB_IDS_VERSION_9_REL_4 = "9.40"; //$NON-NLS-1$
   public static final String DB_IDS_VERSION_9_REL_3 = "9.30"; //$NON-NLS-1$
   public static final String DB_IDS_VERSION_9_REL_2 = "9.20"; //$NON-NLS-1$

   
   //Oracle versions
   public static final String ORACLE_VERSION_10 = "10"; //$NON-NLS-1$
   public static final String ORACLE_VERSION_11 = "11"; //$NON-NLS-1$
   
   public static final String SYBASE_VERSION_12 = "12.0";
   public static final String SYBASE_VERSION_12_REL_5 = "12.5";
   public static final String SYBASE_VERSION_12_REL_X = "12.x";
   public static final String SYBASE_VERSION_15 = "15.0";
	   
   
   /** Database product. */
   protected String product = null;
   /** Database version. */
   protected int version = 0;
   /** Database release. */
   protected int release = 0;
   /** Database modification. */
   protected int mod = 0;
   /** Database delimiter, defaults to a double-quotation mark. */
   protected char delim = '"';
   /* True if we have confirmed the delimiter from the connection. *
   protected boolean delimOK = false;
    */

   /** Shared instance of the default version. */
   protected static DBVersion sharedDefault;
   /** Reusable DBVersion instances. */
   protected static ArrayList<DBVersion> sharedInstances = new ArrayList<DBVersion>();

   /** LUWO: Linux, UNIX, Windows, and OS/2. */
   public static final int PLATFORM_LUWO = 0x1;
   /** OS/390, z/OS. */
   public static final int PLATFORM_390 = 0x2;
   /** OS/400, iSeries. */
   public static final int PLATFORM_400 = 0x4;
   /** LUWO, 390, and 400 */
   public static final int PLATFORM_ALL = 0x7;
   /** Any other platform. */
   public static final int PLATFORM_OTHER = 0x10;

   private static final String ZOS_NEW_FUNCTION = "New-Function Mode"; //$NON-NLS-1$

   /**
    * Gets a shared DBVersion, and returns null if it doesn't exist.
    * @param product The product name.
    * @param version The version.
    * @param release The release
    * @param mod The modification level.
    * @return A shared DBVersion.
    */
   protected static DBVersion findSharedInstance(String product, int version, int release, int mod)
   {
      Iterator<DBVersion> i = sharedInstances.iterator();
      DBVersion dbversion;
      while (i.hasNext())
      {
         dbversion = i.next();
         if (product.equals(dbversion.getProduct())
               && version == dbversion.getVersion()
               && release == dbversion.getRelease()
               && mod == dbversion.getMod())
            return dbversion;
      }
      // Didn't find one; return null:
      return null;
   }

   /**
    * Gets a shared DBVersion, and creates one if it doesn't already exist.
    * @param product The product name.
    * @param version The version.
    * @param release The release
    * @param mod The modification level.
    * @return A shared DBVersion.
    */
   public static DBVersion getSharedInstance(String product, int version, int release, int mod)
   {
      if (product == null)
         product = WORKSTATION;
      DBVersion dbversion = findSharedInstance(product, version, release, mod);
      if (dbversion == null)	// Didn't find one; create one:
      {
         dbversion = new DBVersion(product, version, release, mod);
         sharedInstances.add(dbversion);
      }
      return dbversion;
   }

   /**
    * Gets a shared DBVersion, and creates one if it doesn't already exist.
    * @param product The product name.
    * @param productVersion The version string (with version, release, and possibly modification)
    * @return A shared DBVersion.
    */
   public static DBVersion getSharedInstance(String product, String productVersion)
   {
      int[] v = getVersionArray(productVersion);
      return getSharedInstance(product, v[0], v[1], v[2]);
   }

   /**
    * Gets a shared DBVersion, and creates one if it doesn't already exist.
    * @param myConnection An RLDBConnection.
    * @return A shared DBVersion.
    */
   public static DBVersion getSharedInstance(ConnectionInfo myConnection)
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
    * Gets a shared DBVersion, and creates one if it doesn't already exist.
    * @param profile IConnectionProfile
    * @return A shared DBVersion.
    */
   public static DBVersion getSharedInstance(IConnectionProfile profile)
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
    * Gets a shared DBVersion, and creates one if it doesn't already exist.
    * @param dbDef A database definition.
    * @return A shared DBVersion.
    */
   public static DBVersion getSharedInstance(DatabaseDefinition dbDef)
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
    * Gets a shared DBVersion, and creates one if it doesn't already exist.
    * @param connection A Connection.
    * @return A shared DBVersion.
    */
   public static DBVersion getSharedInstance(Connection connection)
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

   /**
    * The Universal JDBC driver formats the version string with a cookie ccc
    * with the format <code>cccvvrrm</code>.
    */
   protected static String SQLCookies = "SQL QSQ DSN"; //$NON-NLS-1$

   public DBVersion(Connection connection)
   {
      String versionString = null;
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
      setVersion(versionString);
   }

   public DBVersion(String product, String versionString)
   {
      this.product = product;
      setVersion(versionString);
   }

   public DBVersion(String versionString)
   {
      product = WORKSTATION;
      setVersion(versionString);
   }

   public DBVersion(int version, int release, int mod)
   {
      product = WORKSTATION;
      setVersion(version, release, mod);
   }

   public DBVersion(String prod, int version, int release, int mod)
   {
      product = prod;
      setVersion(version, release, mod);
   }

   public DBVersion(DBVersion copyme)
   {
      if (copyme != null)
      {
         product = copyme.getProduct();
         version = copyme.getVersion();
         release = copyme.getRelease();
         mod = copyme.getMod();
         //setDelimiter(copyme.getDelimiter());
      }
   }

   public DBVersion(IConnectionProfile profile)
   {
      String[] vv = ConnectionProfileUtility.getVendorVersion(profile);
      setVersion(vv[1]);
   }

   public DBVersion(ConnectionInfo conInfo)
   {
      if (conInfo != null)
      {
         /* obtain version from the connection info
          * or when not set, from the DatabaseDefinition
          */
         IConnectionProfile profile = conInfo.getConnectionProfile();
         DatabaseDefinition dbDef = conInfo.getDatabaseDefinition();
         String productVersion = dbDef.getVersion();
         product = dbDef.getProduct();

         if (ConnectionProfileUtility.isConnected(profile))
         {
            try
            {
               Connection jdbcConn = conInfo.getSharedConnection();
               productVersion = jdbcConn.getMetaData().getDatabaseProductVersion();
               product = jdbcConn.getMetaData().getDatabaseProductName();        			 
            }
            catch(Exception ex)
            {
               // ignore
            }
         }     
         setVersion(productVersion);
      }
   }

   /**
    *  Gets a shared instance for a DatabaseDefinition.
    *  <p>
    *  @param The shared instance.
    */
   public DBVersion(DatabaseDefinition dbDef)
   {
      product = dbDef.getProduct();
      setVersion(dbDef.getVersion());
   }

   /**
    *  Gets the default shared instance, which is a new DBVersion(WORKSTATION,8,2,0).
    *  <p>
    *  @param The default shared instance of DBVersion.
    */
   public static DBVersion getDefault()
   {
      if (sharedDefault == null)
         sharedDefault = new DBVersion(WORKSTATION,8,2,0);
      return sharedDefault;
   }

   /**
    *  @deprecated Use SQLIdentifier.getDelimiter(ConnectionInfo).
    *  Gets the delimiter for an SQL identifier
    *  which should be obtained from the JDBC database metadata.
    *  <p>
    *  @return A delimiter char.
    */
   public char getDelimiter()
   {
      return delim;
   }

   /**
    *  @deprecated
    *  Sets the delimiter for an SQL identifier,
    *  which should be obtained from the JDBC database metadata.
    *  <p>
    *  @param A delimiter char.
    */
   public void setDelimiter(char d)
   {
      delim = d;
   }

   public void setProduct(String prod)
   {
      product = prod;
   }

   public String getProduct()
   {
      return product;
   }

   public boolean isDB2Alias(){
	   return(product != null && DB2ALIAS.equals(product));
   }
   /**
    * Determines if an SQLVendor represents a DB2 product.
    * @return True if the vendor is DB2.
    */
   public boolean isDB2()
   {
      return isUNO() || isDB400() || isDB390();
   }

   public boolean isDB390()
   {
      if (product != null // false by default
      && (DB2390.equals(product)
      || DB2ZSERIES.equals(product)))
         return true;
      else
         return false;
   }

   /**
    * Returns true for "AS400", "AS", "DB2/400 SQL", "DB2 UDB for AS/400"
    */
   public boolean isDB400()
   {
      if (product != null // false by default
            && (AS400.equals(product)
                  || DB2400.equals(product)
                  || DB2400SQL.equals(product)
                  || DB2400UDB.equals(product)
                  || DB2ISERIES.equals(product)))
         return true;
      else
         return false;
   }

   public boolean isIBMCloudscape()
   {
      // Since product is different depending on the
      // dbDefintion files used, we check for all
      // variations
      if (product != null // false by default
            && (IBMCLOUDSCAPE.equals(product)
                  || APACHE_DERBY.equals(product)
                  || DERBY.equals(product)))
         return true;
      else
         return false;
   }

   public boolean isDerby()
   {
      // Since product is different depending on the
      // dbDefintion files used, we check for all
      // variations
      if (product != null // false by default
            && (DERBY.equals(product)
                  || APACHE_DERBY.equals(product)
                  || IBMCLOUDSCAPE.equals(product)))
         return true;
      else
         return false;
   }

   /**
    * Returns true for the rest of the database product names belong to UNO:
    * <pre>
    * DB2NT =        "DB2/NT"
    * DB2NT64 =      "DB2/NT64"
    * DB2Windows95 = "DB2/Windows 95"
    * DB26000 =      "DB2/6000"
    * DB2AIX64 =     "DB2/AIX64"
    * DB26000PE =    "DB2/6000 PE"
    * DB2HPUX =      "DB2/HPUX"
    * DB2HPUX64 =    "DB2/HP64"
    * DB2SUN =       "DB2/SUN"
    * SUN64 =        "DB2/SUN64"
    * LINUX =        "DB2/LINUX"
    * DYNIX =        "DB2/PTX"   * Sequent (NUMA-Q)
    * DB22 =         "DB2/2"
    * </pre>
    */
   public boolean isUNO()
   {
      if (product == null || WORKSTATION.equals(product) || DB2NT.equals(product)
            || ( product.startsWith(PRODUCT_DB2) 
               && !isDB390() && !isDB400() && !isIBMCloudscape()))
         return true;
      else
         return false;
   }
   public boolean isIDS()
   {
      if (product != null && (product.startsWith(IDS)||product.startsWith( IDS_ALIAS )))
         return true;
      else
         return false;
   }
   public boolean isOracle()
   {
      if (product != null && (product.equalsIgnoreCase(ORACLE) || product.startsWith(ORACLE)))
         return true;
      else
         return false;
   }
   
   public boolean isPostgres()
   {
      if (product != null && (product.equalsIgnoreCase(POSTGRES) || product.startsWith(POSTGRES)))
         return true;
      else
         return false;
   }
   
   public boolean isSybase()
   {
	   if(product != null && (product.equalsIgnoreCase(SYBASE)))
			   return true;
	   else
		   return false;
   }

   /**
    *  Returns the SQLIdentifier code for the DB2 platform.
    *  <p>
    *  @return   The platform code; one of the constants:
    *  <dl>
    *  <dt>SQLIdentifier.PLATFORM_LUWO   <dd>DB2 UDB for Linux, UNIX, Windows, and OS/2
    *  <dt>SQLIdentifier.PLATFORM_390   <dd>DB2 UDB for OS/390 (and z/OS)
    *  <dt>SQLIdentifier.PLATFORM_400   <dd>DB2 UDB for OS/400 (and iSeries)
    *  <dt>SQLIdentifier.PLATFORM_OTHER   <dd>Not DB2
    *  </dl>
    *  @see SQLIdentifier
    */
   public int getSQLIdentifierPlatform()
   {
      if (isDB390())
         return PLATFORM_390;
      else if (isDB400())
         return PLATFORM_400;
      else if (WORKSTATION.equals(product)
            || (product != null // OTHER by default
                  && product.startsWith(PRODUCT_DB2)))
         return PLATFORM_LUWO;
      else
         return PLATFORM_OTHER;
   }

   /**
    * Determines if an SQLVendor represents a DB2 product.
    * @return True if the vendor is DB2.
    */
   public static boolean isDB2(DatabaseDefinition definition)
   {
      return isDB2UDB(definition) || isDB2AS400(definition) || isDB2OS390(definition) || isDB2Alias(definition);
   }
   /**
    * Determines if an SQLVendor represents a DB2 product
    * for the workstation.
    * @return True if the vendor is DB2.
    */
   public static boolean isDB2UDB(DatabaseDefinition definition)
   {
      return isProductSupported (definition, DB2UDB);
   }
   /**
    * Determines if an SQLVendor represents a DB2 product
    * for z/OS.
    * @return True if the vendor is DB2.
    */
   public static boolean isDB2OS390(DatabaseDefinition definition)
   {
      return isProductSupported (definition, DB2ZSERIES);
   }
   /**
    * Determines if an SQLVendor represents a DB2 product
    * for iSeries.
    * @return True if the vendor is DB2.
    */
   public static boolean isDB2AS400(DatabaseDefinition definition)
   {
      return isProductSupported (definition, DB2ISERIES);
   }

   public static boolean isDBCloudscape(DatabaseDefinition definition)
   {
      // Since product is different depending on the
      // dbDefintion files used, we check for all
      // variations
      // No need to check for APACHE_DERBY, since that only comes from
      // connection DatabaseMetaData
      return isProductSupported (definition, IBMCLOUDSCAPE) ||
      isProductSupported (definition, DERBY);
   }

   /**
    * Determines if an SQLVendor represents an IDS product for the workstation.
    * <p>
    * @return <code>True</code> if the vendor is IDS.
    */
   public static boolean isIDS(DatabaseDefinition definition) {
      return isProductSupported (definition, IDS);
   }
   
   public static boolean isSybase(DatabaseDefinition definition) {
	      return isProductSupported (definition, SYBASE);
   }
   
	public static boolean isIMS(DatabaseDefinition definition) {
		boolean retVal = false;
		String prodStr = definition.getProductDisplayString();
		if (prodStr != null && IMS.equals(prodStr)) {
			retVal = true;
		}
		
		return retVal;
   }

   /**
    * Determines if an SQLVendor represents an Oracle product for the workstation.
    * <p>
    * @return <code>True</code> if the vendor is Oracle.
    */
   public static boolean isOracle(DatabaseDefinition definition) {
      return isProductSupported (definition, ORACLE);
   }
   
   /**
    * Determines if an SQLVendor represents a DB2 alias
    * @param definition
    * @return true if the vendor is a DB2 alias
    */
public static boolean isDB2Alias(DatabaseDefinition definition){
	   
	   return isProductSupported(definition, DB2ALIAS);
   }

   private static boolean isProductSupported (DatabaseDefinition definition, String product)
   {
      if (definition == null)
      {
         return false;
      }
      else
      {
    	  return definition.getProduct().equalsIgnoreCase(product) ? true : false;
      }
   }

   /**
    * Parses the versionString and returns the version, release, and mod ints in an array.
    * @return An array of version, release, and mod ints.
    */
   public static int[] getVersionArray(String versionString)
   {
      int[] v = new int[3];
      if (versionString == null || versionString.length() == 0)
      {
         v[0] = 8;
         v[1] = -1;
         v[2] = -1;
      }
      else
      {
         // try to figure out what kind of versionString we have
         // been passed
         int clen = 3;
         int cookieIdx = -1;
         if (versionString.length() >= clen) {
            String cookie = versionString.substring(0, clen);
            cookieIdx = SQLCookies.indexOf(cookie);
            if(cookieIdx == -1 && cookie.equals("IFX")) cookieIdx = 0;
         }

         if (cookieIdx > -1)
            v = parseSQLVersion(versionString.substring(clen));
         else
            v = parseNumericVersion(versionString);
      }
      return v;
   }

   /**
    * Parses the versionString and sets the version, release, and mod ints.
    */
   public void setVersion(String versionString)
   {
      int v[] = getVersionArray(versionString);
      if (v != null)
      {
         setVersion(v[0], v[1], v[2]);
      }
   }

   public void setVersion(int version, int release, int mod)
   {
      this.version = version;
      this.release = release;
      this.mod = mod;
   }

   /**
    * Note: DB2 LUW FP level update:
    * 
    * The  architecture of  product and version information ( Product ID) 
    * for DB2 LUW client and server is in the form PPPVVRRM, where: 
    * PPP 
    * is SQL  ( DB2 LUW Product Identifier)
    * VV 
    * identifies a 2-digit version number (with high-order 0 in the case of a 1-digit version)
    * RR 
    * identifies a 2-digit release number (with high-order 0 in the case of a 1-digit release)
    * M 
    * identifies a 1-character modification level (0-9 or A-Z) ( or fixpack level)
    * 
    * @param String, representing the numeric portion of the version, such as
    * 09074 or 0901A
    * 
    * @return int[], representing the version, release, and mod (fixpack) level
    * If the mod level is a letter, i.e. > 9, the letter will be mapped back to an integer
    * A=10, B=11, etc.
    * 
    * In the future, this can be refactored to return the actual mod level if 
    * callers need it
    * 
    * */
    protected static int[] parseSQLVersion(String s)
    {
 	   int tmpversion = 0;
 	   int tmprelease = 0;
 	   int tmpmod = 0;
 	   // Get values according to substring:
 	   try {
 		   tmpversion = Integer.parseInt(s.substring(0,2));
 		   tmprelease = Integer.parseInt(s.substring(2,4));
         
 		   String mod = s.substring(4);
 		   // this character may be a letter for DB2 LUW
 		   if (Character.isLetter(mod.charAt(0))) {
 			   //assume the character is a valid uppercase letter, otherwise set mod level to 0
 			   tmpmod = Math.max(0, mod.charAt(0) - 'A' + 10);
 		   } else {
 			   tmpmod = Integer.parseInt(s.substring(4));
 		   }
 	   } catch(NumberFormatException nfe) { 
 		   return null; 
 	   }

       return new int[]{tmpversion, tmprelease, tmpmod};
    }
    
   protected static int[] parseNumericVersion(String s)
   {
      int tmpversion = 0;
      int tmprelease = 0;
      int tmpmod = 0;      
      // tokenize:
      StringTokenizer st = new StringTokenizer(s, "VRM .\t\n\r\f", false); //$NON-NLS-1$
      try
      {
         if (st.hasMoreTokens())
         {
            tmpversion = Integer.parseInt(st.nextToken());
         }
         if (st.hasMoreTokens())
         {
            tmprelease = Integer.parseInt(st.nextToken());
         }
         if (st.hasMoreTokens())
         {
            tmpmod = Integer.parseInt(st.nextToken());
         }
      }
      // catch runtime exceptions; could be nosuchelement or numberformat..
      catch(NumberFormatException rte) 
      {    	  
         // return major version if already successfully parsed.
         if (tmpversion == 0)
            return null;
         else if (tmpversion >= 8)
         {
            if (s.indexOf(ZOS_NEW_FUNCTION) > -1)
            {
               tmprelease = 1;
               tmpmod = 5;    			  
            }
         }
      }      		
      catch(java.util.NoSuchElementException nse) 
      { 
         // return major version if already successfully parsed.
         if (tmpversion == 0)
            return null;
         else if (tmpversion >= 8)
         {
            if (s.indexOf(ZOS_NEW_FUNCTION) > -1)
            {
               tmprelease = 1;
               tmpmod = 5;    			
            }
         }
      }

      // must be OK
      return new int[]{tmpversion, tmprelease, tmpmod};
   }

   /**
    * Parses the SQLVendor type to return the version and release.
    * SQLVendor doesn't return the version
    * except in the domain type literal, which doesn't have dots,
    * so we treat the first character after "_V" as the version
    * and the second as the release.
    * @param An SQLVendorType.toString().
    * @return An int array with version,release,0.
    */
   public static int[] parseSQLVendorType(String domain)
   {
      int tmpversion = 0;
      int tmprelease = 1;
      // tokenize:
      int vpos = domain.indexOf("_V") + 2; //$NON-NLS-1$
      try
      {
         tmpversion = Integer.parseInt(domain.substring(vpos, vpos + 1));
         if (vpos + 1 <= domain.length() - 1)
            tmprelease = Integer.parseInt(domain.substring(vpos + 1, vpos + 2));
      }
      catch (NumberFormatException nfe)
      {
         // return zeros
      }
      return new int[]{tmpversion, tmprelease, 0};
   }

   public int getVersion() { return version; }
   public int getRelease() { return release; }
   public int getMod()   { return mod;   }

   public boolean isZSeriesV10CM8()
   {
	   if (isDB390() && (isExactly(10, 1, 0) || isExactly(10, 0, 0))) {
		   return true;
	   }
	   return false;
   }
   
   public boolean isAtLeast(int version)
   {
	   int currentVersion = version;
	   if (isZSeriesV10CM8()) {
		   currentVersion = 8;
	   }
	   return currentVersion >= version;
   }

   public boolean isAtLeast(int version, int release)
   {
      if (this.version > version)
         return true;
      if (this.version == version)
         return this.release >= release;
         return false;
   }

   /**
    * Tests the version, release, and modification levels.
    * For example:
    * <pre>
    *    9,0,5  isAtLeast 9,0,5
    *    9,1,0  isAtLeast 9,0,5 -- Therefore, if you want 9 NFM, use -1 for the release:
    *    9,-1,5 isAtLeast 9,0,5
    * </pre>
    */
   public boolean isAtLeast(int version, int release, int mod)
   {
	   int currentVersion = this.version;
	   if (isDB390() && isZSeriesSkipLevelCompatMode()) {
	   	  currentVersion -= 1;
	   }
	      
	   if (currentVersion > version) {
		   return true;
	   }
	      
	   if (currentVersion == version)
      {
         if (release < 0)
         {
            return this.mod >= mod;
         }
         else
         {
            if (this.release > release)
               return true;
            if (this.release == release)
               return this.mod >= mod;
         }
      }
      return false;
   }

   private boolean isZSeriesSkipLevelCompatMode()
   {
	   if (version < DB_VERSION_10) {
		   return false;
	   }
	   
	   boolean supportsSkipLevelCompatMode = false;
	   for (int candidateVersion : SUPPORTS_SKIP_LEVEL_COMPAT_MODE) {
		   if (candidateVersion == version) {
			   supportsSkipLevelCompatMode = true;
			   break;
		   }
	   }
	   
	   if (supportsSkipLevelCompatMode &&  mod < 2) {
		   return true;
	   }
	   
	   return false;
   }
   
   public boolean isExactly(int version)
   {
      return this.version == version;
   }

   public boolean isExactly(int version, int release)
   {
      return this.version == version
      && this.release == release;
   }

   public boolean isExactly(int version, int release, int mod)
   {
      return this.version == version
      && this.release == release
      && this.mod == mod;
   }

   public boolean isAtMost(int version)
   {
      return this.version <= version;
   }

   public boolean isAtMost(int version, int release)
   {
      if (this.version < version)
         return true;
      if (this.version == version)
         return this.release <= release;
      return false;
   }

   /**
    * Determines if the target is no later than the given v,r,m.
    * To test for compatibility mode, call, isAtMost(v,-1,4).
    */
   public boolean isAtMost(int version, int release, int mod)
   {
      if (this.version < version)
         return true;
      if (this.version == version)
      {
         if (release < 0)
         {
            return this.mod <= mod;
         }
         else
         {
            if (this.release < release)
               return true;
            if (this.release == release)
               return this.mod <= mod;
         }
      }
      return false;
   }

   public String toString()
   {
      StringBuffer str = new StringBuffer();
      if (product == null)
         str.append("DB2Version "); //$NON-NLS-1$
      else
         str.append(product).append(' ');
      str.append(version).append('.').append(release).append('.').append(mod);
      return str.toString();
   }

   /**
    * If this database type, and the one represented by the argument are of
    * the same server platform, return <code>true</code>.
    * <p>
    * @param aVer Database to compare against
    * @return <code>true</code> if the databases are of the same server type.
    */
   public boolean isSameServerType(DBVersion aVer) {
      boolean isSameType = false;
      if (aVer != null) {
         // wsdbu098517;  V9 NFM to V9 CM or V8 either NFM or CM is considered unlike for sql procedures
    	  //V10 NFM to V10 CM or V10 NFM to V9 should be considered unlike
         if ( aVer.isDB390() && this.isDB390() ) {
//            if  ((aVer.isAtMost(8) && this.isAtLeast(9,-1,5)) ||
//                  (aVer.isAtMost(9,-1,4) && this.isAtLeast(9,-1,5)) ||
//            		(aVer.isAtMost(9)) && this.isAtLeast(10,-1,5) ||
//            		 (aVer.isAtMost(10,-1,4) && this.isAtLeast(10,-1,5)))
//               isSameType = false;
//            else
               isSameType = true;
         }
         if ((aVer.isUNO() && this.isUNO()) ||
               //(aVer.isDB390() && this.isDB390()) ||
               (aVer.isDB400() && this.isDB400()) ||
               // Removed test for IBMCloudscape, since isDerby also
               // checks for IBMCloudscape string
               (aVer.isDerby() && this.isDerby()))
         {
            isSameType = true;
         }
         if (aVer.isOracle() && this.isOracle())
         {
        	isSameType = true;
         }
         if (aVer.isIDS() && this.isIDS()){
        	 isSameType = true;
         }
         if (aVer.isSybase() && this.isSybase())
         {
        	 isSameType = true;
         }
      }
      return isSameType;
   }

   /**
    * Normalizes the product and version names to match the keys
    * in our DatabaseDefinition xmi files.
    * @return An array whose first element is the product key
    * and whose second element is the version key. 
    */
   public String[] normalizeDatabaseDefinitionKeys()
   {
      String[] dbDefKeys = new String[]{"",""}; //$NON-NLS-1$ //$NON-NLS-2$
      if (isUNO()) {
         dbDefKeys[0] = DBVersion.DB2UDB;
         switch (getVersion()) 
         {
         case 10:
        	 switch (getRelease()) 
             {
             case 1: dbDefKeys[1] = DB_UDB_VERSION_10_5; break;
             default: dbDefKeys[1] = DB_UDB_VERSION_10_1;
             }
        	 break;
         case 9:
            switch (getRelease()) 
            {
            case 1: dbDefKeys[1] = DB_UDB_VERSION_9_5; break;
            default: dbDefKeys[1] = DB_UDB_VERSION_9;
            }
            break;
         default:
            switch (getRelease()) 
            {
            case 1: dbDefKeys[1] = DB_VERSION_8_REL_1; break;
            default: dbDefKeys[1] = DB_VERSION_8_REL_2; 
            }
         }
      }
      else if (isDB390()) 
      {
         dbDefKeys[0] = DBVersion.DB2ZSERIES; 
         switch (getVersion()) 
         {
         case 7: dbDefKeys[1] = DB_ZSERIES_VERSION_7; break;
         case 8: if (getMod() < 5)
            dbDefKeys[1] = DB_ZSERIES_VERSION_8_COMPAT; 
         else 
            dbDefKeys[1] = DB_ZSERIES_VERSION_8_NEWFUN; 
         break;
         case 9: if (getMod() < 5)
            dbDefKeys[1] = DB_ZSERIES_VERSION_9_COMPAT; 
         else
            dbDefKeys[1] = DB_ZSERIES_VERSION_9_NEWFUN; 
         break;
         case 10: if (getMod() < 5)
             dbDefKeys[1] = DB_ZSERIES_VERSION_10_COMPAT; 
          else
             dbDefKeys[1] = DB_ZSERIES_VERSION_10_NEWFUN; 
          break;
         case 11: if (getMod() < 5)
             dbDefKeys[1] = DB_ZSERIES_VERSION_11_COMPAT; 
          else
             dbDefKeys[1] = DB_ZSERIES_VERSION_11_NEWFUN; 
          break;
         default: dbDefKeys[1] = DB_ZSERIES_VERSION_8_NEWFUN;
         }
      }
      else if (isDB400()) 
      {
         dbDefKeys[0] = DBVersion.DB2ISERIES;
         if (isAtLeast(5)) 
         {
            switch (getRelease()) 
            {
            case 3: dbDefKeys[1] = DB_ISERIES_VERSION_5_REL_3; break;
            case 4: dbDefKeys[1] = DB_ISERIES_VERSION_5_REL_4; break;
            default: dbDefKeys[1] = DB_ISERIES_VERSION_5_REL_4;
            }
         }
      }
      else if (isIBMCloudscape() || isDerby()) 
      {
         dbDefKeys[0] = DBVersion.IBMCLOUDSCAPE;
         if (isExactly(10))
            dbDefKeys[1] = DB_CLOUDSCAPE_VERSION_10_REL_0;
      }
      return dbDefKeys;
   }

   /**
    * Normalizes the product and version to match the name
    * of the DatabaseDefinition xmi files.
    * @return A name in the form: "product_version.zmi." 
    */
   public String normalizeDatabaseDefinitionName()
   {
      ReuseStringBuffer dbDefName = ReuseStringBuffer.getBuffer();
      if (isUNO()) 
      {
         dbDefName.append(DBVersion.DB2UDB).append('_');
         switch (getVersion()) 
         {
         case 9:
            switch (getRelease()) 
            {
            case 1: dbDefName.append("9.5"); break; //$NON-NLS-1$
            default: dbDefName.append("9.1"); //$NON-NLS-1$
            }
            break;
         default: // 8
            switch (getRelease()) 
            {
            case 1: dbDefName.append("8.1"); break; //$NON-NLS-1$
            default: dbDefName.append("8.2");  //$NON-NLS-1$
            }
         }
      }
      else if (isDB390()) 
      {
         dbDefName.append(DBVersion.DB2ZSERIES).append('_'); 
         switch (getVersion()) 
         {
         case 7: dbDefName.append("7"); break; //$NON-NLS-1$
         case 8: if (getMod() < 5)
            dbDefName.append("8 (Compatibility Mode)");  //$NON-NLS-1$
         else 
            dbDefName.append("8 (New-Function Mode)");  //$NON-NLS-1$
         break;
         case 9: if (getMod() < 5)
            dbDefName.append(DB_ZSERIES_VERSION_9_COMPAT); 
         else
            dbDefName.append(DB_ZSERIES_VERSION_9_NEWFUN); 
         break;
         case 10: if (getMod() < 5)
             dbDefName.append(DB_ZSERIES_VERSION_10_COMPAT); 
          else
             dbDefName.append(DB_ZSERIES_VERSION_10_NEWFUN); 
          break;
         case 11: if (getMod() < 5)
             dbDefName.append(DB_ZSERIES_VERSION_11_COMPAT); 
          else
             dbDefName.append(DB_ZSERIES_VERSION_11_NEWFUN); 
          break;
         }
      }
      else if (isDB400()) 
      {
         dbDefName.append(DBVersion.DB2ISERIES).append('_');
         if (isAtLeast(5)) 
         {
            switch (getRelease()) 
            {
            case 3: dbDefName.append(DB_ISERIES_VERSION_5_REL_3); break;
            // there is no 5R4 .xmi file
//            case 4: dbDefName.append(DB_ISERIES_VERSION_5_REL_4); break;
            default: dbDefName.append(DB_ISERIES_VERSION_5_REL_3);
            }
         }
      }
      else if (isIBMCloudscape() || isDerby()) 
      {
         dbDefName.append(DBVersion.IBMCLOUDSCAPE).append('_');
         if (isExactly(10))
            dbDefName.append(DB_CLOUDSCAPE_VERSION_10_REL_0);
      }

      dbDefName.append(".xmi"); //$NON-NLS-1$
      return ReuseStringBuffer.toString(dbDefName);
   }

}
