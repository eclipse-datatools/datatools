/*******************************************************************************
 * Copyright © 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringTokenizer;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.util.SQLDBUtil;

/**
 * This class provides database product (platform) and version information for a
 * particular database.  The database version consists of three integers: version, 
 * release, and mod level.  This class supports comparisons between versions, as 
 * well as comparisons against specified version, release, and mod levels.
 * 
 * Note: this class is adapted from Development Center's DB2Version 
 * class.
 * 
 * @author bpayton
 */
public class DBVersionHelper {
    public static final String DEFAULT_FAMILY = "DB2";  //$NON-NLS-1$
    public static final String DEFAULT_PRODUCT = "DB2_LUW";  //$NON-NLS-1$
    public static final String DEFAULT_VERSION_STRING = "7.1.0";  //$NON-NLS-1$
    
    public static final String FAMILY_CLOUDSCAPE = "CLOUDSCAPE";  //$NON-NLS-1$
    public static final String FAMILY_DB2        = "DB2";  //$NON-NLS-1$
    public static final String FAMILY_INFORMIX   = "INFORMIX";  //$NON-NLS-1$
    public static final String FAMILY_MYSQL      = "MYSQL";  //$NON-NLS-1$
    public static final String FAMILY_ORACLE     = "ORACLE";  //$NON-NLS-1$
    public static final String FAMILY_SQL_SERVER = "SQL_SERVER";  //$NON-NLS-1$
    public static final String FAMILY_SYBASE     = "SYBASE";  //$NON-NLS-1$
    
    public static final String PRODUCT_CLOUDSCAPE     = "CLOUDSCAPE";  //$NON-NLS-1$
    public static final String PRODUCT_DB2_EVERYPLACE = "DB2_EVERYPLACE";  //$NON-NLS-1$
    public static final String PRODUCT_DB2_ISERIES    = "DB2_ISERIES";  //$NON-NLS-1$
    public static final String PRODUCT_DB2_LUW        = "DB2_LUW";  //$NON-NLS-1$
    public static final String PRODUCT_DB2_ZOS        = "DB2_ZOS";  //$NON-NLS-1$
    public static final String PRODUCT_DB2_VMVSE      = "DB2_VMVSE";  //$NON-NLS-1$
    public static final String PRODUCT_DERBY          = "DERBY";  //$NON-NLS-1$
    public static final String PRODUCT_INFORMIX       = "INFORMIX";  //$NON-NLS-1$
    public static final String PRODUCT_MYSQL          = "MYSQL";  //$NON-NLS-1$
    public static final String PRODUCT_ORACLE         = "ORACLE";  //$NON-NLS-1$
    public static final String PRODUCT_SQL_SERVER     = "SQL_SERVER";  //$NON-NLS-1$
    public static final String PRODUCT_SYBASE_ASA     = "SYBASE_ASA";  //$NON-NLS-1$
    public static final String PRODUCT_SYBASE_ASE     = "SYBASE_ASE";  //$NON-NLS-1$
    
    /* Define product ID strings.  These are constants for the strings that are returned 
     * by Connection.getDatabaseProductName(), DatabaseDefinition.getProduct(), and
     * Database.getVendor().  They are used to determine the database product and family. 
     */
    // TODO: complete the product ID strings for the non-IBM databases.
    public static final String ID_DB2             = "DB2";  //$NON-NLS-1$
    public static final String ID_DB2_PREFIX      = "DB2/";  //$NON-NLS-1$
    public static final String ID_DB2_UDB         = "DB2 UDB";  //$NON-NLS-1$
    public static final String ID_DB2_UDB_ZSERIES = "DB2 UDB zSeries";  //$NON-NLS-1$
    public static final String ID_AS              = "AS";  //$NON-NLS-1$
    public static final String ID_AS400           = "AS400";  //$NON-NLS-1$
    public static final String ID_DB2400_SQL      = "DB2/400 SQL";  //$NON-NLS-1$
    public static final String ID_DB2_UDB_AS400   = "DB2 UDB for AS/400";  //$NON-NLS-1$
    public static final String ID_DB2_UDB_ISERIES = "DB2 UDB iSeries";  //$NON-NLS-1$
    public static final String ID_DB2_EVERYPLACE  = "";  //$NON-NLS-1$
    public static final String ID_SQLDS           = "SQLDS";  //$NON-NLS-1$
    public static final String ID_WORKSTATION     = "WORKSTATION";  //$NON-NLS-1$
    public static final String ID_IBM_CLOUDSCAPE  = "IBM Cloudscape";  //$NON-NLS-1$
    // ID of "Apache Derby" is returned by java.sql.DatabaseMetaData from jdbc Connection
    public static final String ID_APACHE_DERBY    = "Apache Derby";  //$NON-NLS-1$ 
    // ID of "Derby" is returned by DatabaseDefintion and ConnectionInfo
    public static final String ID_DERBY    		  = "Derby";  //$NON-NLS-1$
    public static final String ID_INFORMIX        = "<informix placeholder>";  //$NON-NLS-1$
    public static final String ID_MYSQL           = "<mySQL placeholder>";  //$NON-NLS-1$
    public static final String ID_ORACLE          = "<oracle placeholder>";  //$NON-NLS-1$
    public static final String ID_SQL_SERVER      = "<SQL Server placeholder>";  //$NON-NLS-1$
    public static final String ID_SYBASE_ASA      = "<Sybase ASA placeholder>";  //$NON-NLS-1$
    public static final String ID_SYBASE_ASE      = "<Sybase ASE placeholder>";  //$NON-NLS-1$

    private String fProductFamily = ""; //$NON-NLS-1$
    private String fProduct = "";  //$NON-NLS-1$
    private String fProductIDString = "";  //$NON-NLS-1$
    private String fVersionString = "";  //$NON-NLS-1$
    private int fVersion = 0;
    private int fRelease = 0;
    private int fMod = 0;

    /**
     * Constructs an instance of this class.  This is the default constructor.
     * The product name and version strings are set to default values.
     */
    public DBVersionHelper() {
        fProductFamily = DEFAULT_FAMILY;
        fProduct = DEFAULT_PRODUCT;
        fProductIDString = "";
        fVersionString = DEFAULT_VERSION_STRING;
        parseVersionString(fVersionString);
    }
    
    /**
     * Constructs an instance of this class using the given JDBC connection object.
     * 
     * @param conn the JDBC connection to use to get product and version information
     */
    public DBVersionHelper(Connection conn) {
        this();
        if (conn != null) {
            try {
                fProductIDString = conn.getMetaData().getDatabaseProductName();
                fVersionString = conn.getMetaData().getDatabaseProductVersion();
                parseProductIDString(fProductIDString);
                parseVersionString(fVersionString);
            }
            catch (SQLException e) {
                // do nothing, let it default
            }
        }
    }

    /**
     * Constructs an instance of this class using the given <code>ISQLEditorConnectionInfo</code>
     * object.
     * 
     * @param connInfo the connection info object to use to get product and verions
     * information
     */
    public DBVersionHelper(ISQLEditorConnectionInfo connInfo) {
        this();
        if (connInfo != null) {
        	Database database = connInfo.getDatabase();
        	DatabaseDefinition dbDef = SQLDBUtil.getDatabaseDefinition(connInfo);
        	if (dbDef != null) {
        	    fProductIDString = dbDef.getProduct();
        	    fVersionString = dbDef.getVersion();
        	}
            parseProductIDString(fProductIDString);
            parseVersionString(fVersionString);
        }
    }
    
    /**
     * Constructs an instance of this class using the given <code>Database</code>
     * object.
     * 
     * @param db the database object to use to get product and version information
     */
    public DBVersionHelper(Database db) {
        this();
        if (db != null) {
            fProductIDString = db.getVendor();
            fVersionString = db.getVersion();
            parseProductIDString(fProductIDString);
            parseVersionString(fVersionString);
        }
    }
    
    /**
     * Constructs an instance of this class using the given version string.
     * 
     * The database product is set to the defined default.
     * 
     * @param versionString the version string to use
     */
    public DBVersionHelper(String versionString) {
        this();
        if (versionString != null) {
            fVersionString = versionString;
            parseVersionString(versionString);
        }
    }

    /**
     * Constructs an instance of this class using the given version, release, 
     * and mod level numbers.
     * 
     * The database product is set to the defined default.
     * 
     * @param version the database version
     * @param release the database release
     * @param mod the database mod level
     */
    public DBVersionHelper(int version, int release, int mod) {
        this();
        setVersionReleaseMod(version, release, mod);
    }

    /**
     * Constructs an instance of this class using the given product family,
     * product, version, release, and mod level numbers.  The family should be one
     * of the FAMILY constants defined in this class (or a subclass) and the product
     * should be one of the PRODUCT constants.
     * 
     * @param productFamily the product family to set 
     * @param product the database product to set
     * @param version the database version to set
     * @param release the database release to set
     * @param mod the database mod level to set
     */
    public DBVersionHelper(String productFamily, String product, int version, int release, int mod) {
        if (productFamily != null) {
            fProductFamily = productFamily;
        }
        if (product != null) {
            fProduct = product;
        }
        setVersionReleaseMod(version, release, mod);
    }

    /**
     * Constructs an instance of this class as a copy of the given instance of this class.
     * 
     * @param toCopy the object to copy
     */
    public DBVersionHelper(DBVersionHelper other) {
        if (other != null) {
            fProductFamily = other.getProductFamily(); 
            fProduct = other.getProduct();
            fProductIDString = other.getProductIDString();
            fVersionString = other.getVersionString();
            fVersion = other.getVersion();
            fRelease = other.getRelease();
            fMod = other.getMod();
        }
    }

    /**
     * Gets the mod level.
     * 
     * @return the mod level for the database
     */
    public int getMod() {
        return fMod;
    }

    /**
     * Gets the database product family.  This is one of the FAMILY constants defined
     * in this class (or a subclass).
     * 
     * @return the current database product family constant
     */
    public String getProductFamily() {
        return fProductFamily;
    }
    
    /**
     * Gets the database product.  This is one of the PRODUCT constants defined in
     * this class (or a subclass).
     * 
     * @return the current database product constant
     */
    public String getProduct() {
        return fProduct;
    }

    /**
     * Gets the database product ID string that was used to determine the product.
     * A product ID string is an identifying string for a database product that is 
     * returned by Connection.getDatabaseProductName(), DatabaseDefinition.getProduct(),
     * or Database.getVendor().
     * 
     * @return the current product ID string
     */
    public String getProductIDString() {
        return fProductIDString;
    }
    
    /**
     * Gets the release level.
     * 
     * @return the release level for the database
     */
    public int getRelease() {
        return fRelease;
    }

    /**
     * Gets the version.
     * 
     * @return the version of the database
     */
    public int getVersion() {
        return fVersion;
    }

    /**
     * Gets the current version string.  This is the combination of version,
     * release, and modification.
     * 
     * @return the version string
     */
    public String getVersionString() {
        return fVersionString;
    }

    /**
     * Gets whether or not this database is IBM Cloudscape.
     * 
     * @return true when the current database is Cloudscape, otherwise false
 
     * @return
     */
    public boolean isCloudscape() {
        /* Note: a boolean local variable is used in these "isXXX" methods to make 
         * debugging easier. */
    	boolean isDB = fProduct.equals(PRODUCT_CLOUDSCAPE) 
           || fProduct.equals(PRODUCT_DERBY) 
		   	;
        return isDB;        
    }    

    /** 
     * Gets whether or not this database is a DB2 of any flavor.
     * 
     * @return true when the current database is DB2, otherwise false
     */
    public boolean isDB2() {
        boolean isDB = fProductFamily.equals(FAMILY_DB2);
        return isDB;
    }
    
    /**
     * Gets whether or not this database is a flavor of DB2 UDB. (That is, one
     * of DB2 UDB for iSeries, DB2 UDB for LUW, or DB2 UDB for zOS.) 
     * 
     * @return true when the current database is DB2 UDB, otherwise false
     */
    public boolean isDB2_UDB() {
        boolean isDB = (isDB2_iSeries() || isDB2_LUW() || isDB2_zOS());
        return isDB;
    }
    
    /**
     * Gets whether or not this database is a DB2 for OS/390.  (This is the same
     * as "isDB2_zOS".)
     * 
     * @return true when the current database is DB2 for OS/390 or DB2 for zOS, 
     * otherwise false
     */
    public boolean isDB2_390() {
        boolean isDB = isDB2_zOS();
        return isDB;
    }

    /**
     * Gets whether or not this database is a DB2 for OS/400.  (This is the same as
     * "isDB2_iSeries".)
     * 
     * @return true when the current database is DB2 for OS/400, otherwise false
     */
    public boolean isDB2_400() {
        boolean isDB = isDB2_iSeries();
        return isDB;
    }

    /**
     * Gets whether or not this database is a DB2 Everyplace.
     * 
     * @return true when the current database is DB2 Everyplace, 
     * otherwise false
     */
    public boolean isDB2_Everyplace() {
        boolean isDB = fProduct.equals(PRODUCT_DB2_EVERYPLACE);
        return isDB;
    }
    
    /**
     * Gets whether or not the database is DB2 for iSeries.  (This is the same as
     * "isDB2_400".)
     * 
     * @return true when the current database is DB2 for iSeries, otherwise false
     */
    public boolean isDB2_iSeries() {
        boolean isDB = fProduct.equals(PRODUCT_DB2_ISERIES);
        return isDB;
    }
    
    /**
     * Gets whether or not this database is a DB2 for Linux/Unix/Windows
     * 
     * @return true when the current database is DB2 for LUW, otherwise false
     */
    public boolean isDB2_LUW() {
        boolean isDB = fProduct.equals(PRODUCT_DB2_LUW);
        // Old product name strings for various flavors of DB2 distributed 
        // DB2NT        = "DB2/NT"
        // DB2NT64      = "DB2/NT64"
        // DB2Windows95 = "DB2/Windows 95"
        // DB26000      = "DB2/6000"
        // DB2AIX64     = "DB2/AIX64"
        // DB26000PE    = "DB2/6000 PE"
        // DB2HPUX      = "DB2/HPUX"
        // DB2HPUX64    = "DB2/HP64"
        // DB2SUN       = "DB2/SUN"
        // SUN64        = "DB2/SUN64"
        // LINUX        = "DB2/LINUX"
        // DYNIX        = "DB2/PTX"   // Sequent (NUMA-Q)
        // DB22         = "DB2/2"
//        if ( fProductName != null 
//          && ( fProductName.equals(DB2_WORKSTATION) 
//            || (fProductName.startsWith("DB2") && !isDB2_390() && !isDB2_400())  //$NON-NLS-1$
//             )
//           ) {
//            isDB = true;
//        }
        return isDB;
    }

    /**
     * Gets whether or not this database is DB2 for VM/VSE (SQL/DS).
     * 
     * @return true when the current database is DB2 for VM/VSE, otherwise false
     */
    public boolean isDB2_VMVSE() {
        boolean isDB = fProduct.equals(PRODUCT_DB2_VMVSE);
//        if (fProductName != null  && fProductName.startsWith(DB2_VMVSE)) {
//            isDB = true;
//        }       
        return isDB;
    }

    /**
     * Gets whether or not this database is DB2 for zOS.  (This is the same
     * as "isDB2_390".)
     * 
     * @return true when the current database is DB2 for zOS, otherwise false
     */
    public boolean isDB2_zOS() {
        boolean isDB = fProduct.equals(PRODUCT_DB2_ZOS);
        return isDB;
    }
    
    /**
     * Gets whether or not this database is Derby (AKA Cloudscape).
     * 
     * @return true when the current database is Derby, otherwise false
 
     * @return
     */
    public boolean isDerby() {
        boolean isDB = fProduct.equals(PRODUCT_DERBY) ||
        			   fProduct.equals(PRODUCT_CLOUDSCAPE)	;
        return isDB;
    }
    
    /**
     * Gets whether or not the database is Informix.
     * 
     * @return true when the database is Informix, otherwise false
     */
    public boolean isInformix() {
        boolean isDB = fProduct.equals(PRODUCT_INFORMIX);
        return isDB;
    }
    
    /**
     * Gets whether or not the database is mySQL.
     * 
     * @return true when the database is mySQL, otherwise false
     */
    public boolean isMySQL() {
        boolean isDB = fProduct.equals(PRODUCT_MYSQL);
        return isDB;        
    }

    /**
     * Gets whether or not the database is Oracle.
     * 
     * @return true when the database is Oracle, otherwise false
     */
    public boolean isOracle() {
        boolean isDB = fProduct.equals(PRODUCT_ORACLE);
        return isDB;        
    }
    
    /**
     * Gets whether or not the database is SQL Server.
     * 
     * @return true when the database is SQL Server, otherwise false
     */
    public boolean isSQLServer() {
        boolean isDB = fProduct.equals(PRODUCT_SQL_SERVER);
        return isDB;        
    }
    
    /**
     * Gets whether or not the database is Sybase.
     * 
     * @return true when the database is Sybase, otherwise false
     */
    public boolean isSybase() {
        boolean isDB = fProductFamily.equals(FAMILY_SYBASE);
        return isDB;        
    }

    /**
     * Gets whether or not the database is Sybase ASA.
     * 
     * @return true when the database is Sybase ASA, otherwise false
     */
    public boolean isSybase_ASA() {
        boolean isDB = fProduct.equals(PRODUCT_SYBASE_ASA);
        return isDB;        
    }

    /**
     * Gets whether or not the database is Sybase ASE.
     * 
     * @return true when the database is Sybase ASE, otherwise false
     */
    public boolean isSybase_ASE() {
        boolean isDB = fProduct.equals(PRODUCT_SYBASE_ASA);
        return isDB;        
    }

    /**
     * Gets whether or not the version of this database is at least as high as
     * the given version.
     * 
     * @param version the version to check
     * @return true when the current database version is greater than or equal to 
     * the given version, otherwise false
     */
    public boolean isAtLeast(int version) {
        boolean dbIsAtLeast = false;
        
        if (fVersion >= version) {
            dbIsAtLeast = true;
        }
            
        return dbIsAtLeast;
    }

    /**
     * Gets whether or not the version and release of this database is at least as
     * high as the given version and release.
     *
     * @param version the version to check
     * @param release the release to check
     * @return true when the current database version and release are greater than 
     * or equal to the given version and release, otherwise false
     */
    public boolean isAtLeast(int version, int release) {
        boolean dbIsAtLeast = false;
        
        if (fVersion > version) {
            dbIsAtLeast = true;
        }
        else if (fVersion == version) {
            if (fRelease >= release) {
                dbIsAtLeast = true;
            }
        }
        
        return dbIsAtLeast;
    }

    /**
     * Gets whether or not the version, release, and mod level of this database is
     * at least the given version, release, and mod level.
     * 
     * @param version the version to check
     * @param release the release to check
     * @param mod the mod level to check
     * @return true when the current database version, release, and mod level are 
     * greater than or equal to the given version, release, and mod level, otherwise false
     */
    public boolean isAtLeast(int version, int release, int mod) {
        boolean dbIsAtLeast = false;
        
        if (fVersion > version) {
            dbIsAtLeast = true;
        }
        else if (fVersion == version) {
            if (fRelease > release) {
                dbIsAtLeast = true;
            }
            else if (fRelease == release) {
                if (fMod >= mod) {
                    dbIsAtLeast = true;
                }
            }
        }
        
        return dbIsAtLeast;
    }

    /**
     * Gets whether or not the version of this database is at most the given version.
     * 
     * @param version the version to check
     * @return true when the current database version is less than or equal to 
     * the given version, otherwise false
     */
    public boolean isAtMost(int version) {
        boolean dbIsAtMost = false;
        
        if (fVersion <= version) {
            dbIsAtMost = true;
        }
            
        return dbIsAtMost;
    }

    /**
     * Gets whether or not the version and release of this database are at most the 
     * given version and release.
     *
     * @param version the version to check
     * @param release the release to check
     * @return true when the current database version and release are less than 
     * or equal to the given version and release, otherwise false
     */
    public boolean isAtMost(int version, int release) {
        boolean dbIsAtMost = false;
        
        if (fVersion < version) {
            dbIsAtMost = true;
        }
        else if (fVersion == version) {
            if (fRelease <= release) {
                dbIsAtMost = true;
            }
        }
        
        return dbIsAtMost;
    }

    /**
     * Gets whether or not the version, release, and mod level of this database 
     * are at most the given version, release, and mod level.
     * 
     * @param version the version to check
     * @param release the release to check
     * @param mod the mod level to check
     * @return true when the current database version, release, and mod level are
     * less than or equal to the given version, release, and mod level, otherwise false
     */
    public boolean isAtMost(int version, int release, int mod) {
        boolean dbIsAtMost = false;
        
        if (fVersion < version) {
            dbIsAtMost = true;
        }
        else if (fVersion == version) {
            if (fRelease < release) {
                dbIsAtMost = true;
            }
            else if (fRelease == release) {
                if (fMod <= mod) {
                    dbIsAtMost = true;
                }
            }
        }
        
        return dbIsAtMost;
    }

    /**
     * Gets whether or not the version of this database is exactly the given version.
     * 
     * @param version the version to check
     * @return true when the current database version is exactly the given version,
     * otherwise false 
     */
    public boolean isExactly(int version) {
        boolean dbIsExactly = false;
        
        if (fVersion == version) {
            dbIsExactly = true;
        }
        
        return dbIsExactly;
    }

    /**
     * Gets whether or not the version and release of this database are exactly the 
     * given version and release.
     * 
     * @param version the version to check
     * @param release the release to check
     * @return true when the current database version and release are exactly the given 
     * version and release, otherwise false 
     */
    public boolean isExactly(int version, int release) {
        boolean dbIsExactly = false;
        
        if (fVersion == version && fRelease == release) {
            dbIsExactly = true;
        }
        
        return dbIsExactly;
    }

    /**
     * Gets whether or not the version, release, and mod level of this database 
     * are exactly the given version, release, and mod level.
     * 
     * @param version the version to check
     * @param release the release to check
     * @param mod the mod level to check
     * @return true when the current database version, release and mod level
     * are exactly the given version, release, and mod level, otherwise false 
     */
    public boolean isExactly(int version, int release, int mod) {
        boolean dbIsExactly = false;
        
        if (fVersion == version && fRelease == release && fMod == mod) {
            dbIsExactly = true;
        }
        
        return dbIsExactly;
    }

    /**
     * Parses the given product ID string in order to determine the database product and
     * family it represents.
     * 
     * @param prodStr the product ID string to parse
     */
    protected void parseProductIDString( String prodStr ) {
        prodStr = prodStr.trim();
        if (prodStr.equals(ID_APACHE_DERBY)) {
            fProductFamily = FAMILY_CLOUDSCAPE;
            fProduct = PRODUCT_DERBY;
        }
        if (prodStr.equals(ID_DERBY)) {
            fProductFamily = FAMILY_CLOUDSCAPE;
            fProduct = PRODUCT_DERBY;
        }
        else if (prodStr.equals(ID_IBM_CLOUDSCAPE)) {
            fProductFamily = FAMILY_CLOUDSCAPE;
            fProduct = PRODUCT_CLOUDSCAPE;
        }
        else if (prodStr.equals(ID_DB2_EVERYPLACE)) {
            fProductFamily = FAMILY_DB2;
            fProduct = PRODUCT_DB2_EVERYPLACE;
        }
        else if ( prodStr.equals(ID_AS)
               || prodStr.equals(ID_AS400)
               || prodStr.equals(ID_DB2400_SQL)
               || prodStr.equals(ID_DB2_UDB_AS400)
               || prodStr.equals(ID_DB2_UDB_ISERIES)
                ) {
            fProductFamily = FAMILY_DB2;
            fProduct = PRODUCT_DB2_ISERIES;
        }
        else if ( prodStr.equals(ID_WORKSTATION)
               || prodStr.equals(ID_DB2_UDB)
               || ( prodStr.startsWith(ID_DB2_PREFIX)
                 && !prodStr.equals(ID_DB2400_SQL)
                  )
                ) {
            fProductFamily = FAMILY_DB2;
            fProduct = PRODUCT_DB2_LUW;
        }
        else if (prodStr.equals(ID_DB2) || prodStr.equals(ID_DB2_UDB_ZSERIES)) {
            fProductFamily = FAMILY_DB2;
            fProduct = PRODUCT_DB2_ZOS;
        }
        else if (prodStr.equals(ID_SQLDS)) {
            fProductFamily = FAMILY_DB2;
            fProduct = PRODUCT_DB2_VMVSE;
        }
        else if (prodStr.equals(ID_INFORMIX)) {
            fProductFamily = FAMILY_INFORMIX;
            fProduct = PRODUCT_INFORMIX;
        }
        else if (prodStr.equals(ID_MYSQL)) {
            fProductFamily = FAMILY_MYSQL;
            fProduct = PRODUCT_MYSQL;
        }
        else if (prodStr.equals(ID_ORACLE)) {
            fProductFamily = FAMILY_ORACLE;
            fProduct = PRODUCT_ORACLE;
        }
        else if (prodStr.equals(ID_SQL_SERVER)) {
            fProductFamily = FAMILY_SQL_SERVER;
            fProduct = PRODUCT_SQL_SERVER;
        }
        else if (prodStr.equals(ID_SYBASE_ASA)) {
            fProductFamily = FAMILY_SYBASE;
            fProduct = PRODUCT_SYBASE_ASA;
        }
        else if (prodStr.equals(ID_SYBASE_ASE)) {
            fProductFamily = FAMILY_SYBASE;
            fProduct = PRODUCT_SYBASE_ASE;
        }
    }
    
    /**
     * Parses the given version string into version, release, and mod level, which
     * are then stored in the corresponding fields.  Several different version string 
     * formats are recognized:
     * <ul>
     * <li>Vv.r
     * <li>v.r.m
     * <li>SQLvvrrmm
     * </ul>
     * where v, r, and m are the numeric version, release and mod level.
     * 
     * @param versionString the version string to parse
     */
    protected void parseVersionString(String versionString) {
        if (versionString != null && versionString.length() > 0) {
            // Try to figure out what kind of versionString we have been passed.

            if (versionString.substring(0,1).equalsIgnoreCase("V")) {
                /* When the string starts with "V" or "v" we assume the format is
                 * Vv.r or Vv.r.m.  We send the part after the "V" on for further parsing. */
                parseNumericVersion(versionString.substring(1));
            }
            else if (versionString.startsWith("SQL")) {
                /* When the string starts with "SQL" we assume the format is SQLvvrrmm */
                parseSQLVersion(versionString.substring(3));
            }
            else {
                /* When the string starts with a digit we assume the format is v.r.m */
                char c = versionString.charAt(0);
                if (Character.isDigit(c) == true) {
                    parseNumericVersion(versionString);
                }
            }
        }
    }

    /**
     * Parses the given numeric version string by breaking it up by delimiters into
     * version, release, and mod level, which are then stored in the corresponding
     * fields.  The version string format is assumed to be
     * <code>
     *   <version>[<delim><release>[<delim><mod_level>]]
     * </code>
     * where <version>, <release>, and <mod_level> are integers and <delim> is one
     * of {period, space, tab}.
     * 
     * @param versionString the version string to parse
     */
    protected void parseNumericVersion(String versionString) {
        fVersion = 0;
        fRelease = 0;
        fMod = 0;

        /* Tokenize the version string by '.' or whitespace. */ 
        StringTokenizer st = new StringTokenizer(versionString, ". \t");
        String tkn = null;
        try {
            /* Get the pieces one at at time. */
            if (st.hasMoreTokens()) {
                tkn = st.nextToken();
                fVersion = Integer.parseInt(tkn);
            }
            if (st.hasMoreTokens()) {
                tkn = st.nextToken();
                fRelease = Integer.parseInt(tkn);
            }
            if (st.hasMoreTokens()) {
                tkn = st.nextToken();
                fMod = Integer.parseInt(tkn);
            }
        }
        catch (Exception e) {
            // ignore
        }
    }

    /**
     * Parses the given SQL version string by fixed position into version,
     * release, and mod level, which are then stored in the corresponding
     * fields.  The version string is assumed to be in the form vvrrmmm, where 
     * vv is a two digit version number, rr is a two digit release number, and 
     * mmm is an n-digit mod level. 
     * 
     * @param versionString the version string to parse
     */
    protected void parseSQLVersion(String versionString) {
        fVersion = 0;
        fRelease = 0;
        fMod = 0;
        try {
            String verStr = versionString.substring(0, 2);
            fVersion = Integer.parseInt(verStr);
            
            String relStr = versionString.substring(2, 4);
            fRelease = Integer.parseInt(relStr);
            
            String modStr = versionString.substring(4);
            fMod = Integer.parseInt(modStr);
        }
        catch (NumberFormatException nfe) {
            // ignore
        }
    }

    /**
     * Sets the database product family to the given value.  This should be one of
     * the FAMILY constants defined in this class (or a subclass).
     * 
     * @param family the product family to set
     */
    public void setProductFamily(String family) {
        if (family != null) {
            fProductFamily = family;
        }
    }
    
    /**
     * Sets the database product to the given value.  This should be one of the PRODUCT
     * constants defined in this class (or a subclass).
     * 
     * @param product the product to set
     */
    public void setProduct(String product) {
        if (product != null) {
            fProduct = product;
        }
    }

    /**
     * Sets the product ID string for this database.  A product ID string is an 
     * identifying string for a database product that is returned by 
     * Connection.getDatabaseProductName(), DatabaseDefinition.getProduct(), or
     * Database.getVendor().  They are used to determine the database product and family.
     * 
     * @param prodStr the product ID string to set
     */
    public void setProductIDString( String prodStr ) {
        if (prodStr != null) {
            fProductIDString = prodStr;
            parseProductIDString( prodStr );
        }
    }
    
    /**
     * Sets the current version string to the given string.  The version
     * string includes the version, release, and modification (ie, "7.1.0");
     * The string is parsed to set the numeric version, release, and mod level values.
     * 
     * @param versionString the version string to set 
     */
    public void setVersionString(String versionString) {
        if (versionString != null) {
            fVersionString = versionString;
            parseVersionString(versionString);
        }
    }

    /**
     * Sets the product version, release, and mod level to the given values.  This
     * method also sets the version string to match the values.
     * 
     * @param version the version
     * @param release the release
     * @param the mod level
     */
    public void setVersionReleaseMod(int version, int release, int mod) {
        fVersion = version;
        fRelease = release;
        fMod = mod;
        fVersionString = "" + fVersion + "." + fRelease + "." + fMod;
    }

    /**
     * Gets the content of this object as a string.
     * 
     * @return the string version of this object
     */
    public String toString() {
        String result = "";
        
        result = fProduct + " " + fVersion + "." + fRelease + "." + fMod;
        
        return result;
     }

} // end class

