/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.model;

import org.eclipse.datatools.modelbase.sql.schema.Database;


/**
 * A helper class for determining what database we are running against.
 */
//FIXME See if the comparisons against hardcoded strings can be changed to use constants
public class VendorHelper {

    protected Database database;

    public VendorHelper(Database database) {
        this.database = database;
    }

    public static boolean isFullSelectSupported(Database database) {
        boolean isSupported = true;
        
        if (database != null && (database.getVendor().equals("My SQL") || database.getVendor().equals("DB2 Everyplace") || database.getVendor().equals("Instant DB"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            isSupported = false;
        }
       
        return isSupported;
    }

    public static boolean isWithSupported(Database database) {
        boolean isSupported = false;
        
        if (database != null && database.getVendor().startsWith("DB2 UDB")) { //$NON-NLS-1$
            isSupported = true;
        }
        
        return isSupported;
    }

    public boolean isDB2UDBNT_V8() {
        boolean isDB = false;
        
        if (isDB2UDBNT()) {
            if (database != null && database.getVersion().startsWith("V8")) { //$NON-NLS-1$
                isDB = true;
            }
        }
        
        return isDB;
    }

    public boolean isDB2forLUW_v9() {
        boolean isDB = false;
        
        if (isDB2UDBNT()) {
            if (database != null && database.getVersion().startsWith("V9")) { //$NON-NLS-1$
                isDB = true;
            }
        }
                
        return isDB;
    }
    
    public boolean isDB2UDBOS390_V6() {
        boolean isDB = false;
        
        if (isDB2UDBOS390()) {
            if (database != null && database.getVersion().equals("6")) { //$NON-NLS-1$
                isDB = true;
            }
        }
        
        return isDB;
    }

    public boolean isDB2UDBOS390() {
        boolean isDB = false;
        
        if (database != null && database.getVendor().equals("DB2 UDB zSeries")) { //$NON-NLS-1$
            isDB = true;
        }
            
        return isDB; 
    }

    public boolean isDB2forZOS_v9() {
        boolean isDB = false;
        
        if (isDB2UDBOS390()) {
            if (database != null && database.getVersion().startsWith("9")) { //$NON-NLS-1$
                isDB = true;
            }
        }
        
        return isDB;        
    }
    
    public boolean isDB2Everyplace() {
        boolean isDB = false;
        
        if (database != null && database.getVendor().equals("DB2 Everyplace")) { //$NON-NLS-1$
            isDB = true;
        }
        
        return isDB;
    }

    public boolean isOracle() {
        boolean isDB = false;
        
        if (database != null && database.getVendor().equals("Oracle")) { //$NON-NLS-1$
            isDB = true;
        }
        
        return isDB;
    }

    public boolean isOracle_V8() {
        boolean isDB = false;
        
        if (isOracle()) {
            if (database != null && database.getVersion().equals("8")) { //$NON-NLS-1$
                isDB = true;
            }
        }
        
        return isDB;
    }

    public boolean isOracle_V9() {
        boolean isDB = false;
        
        if (isOracle()) {
            if (database != null && database.getVersion().equals("9")) { //$NON-NLS-1$
                isDB = true;
            }
        }
        
        return isDB;
    }

    public boolean isOracle_V10() {
        boolean isDB = false;
        
        if (isOracle()) {
            if (database != null && database.getVersion().equals("10")) { //$NON-NLS-1$
                isDB = true;
            }
        }
        
        return isDB;
    }

    public boolean isInformix() {
        boolean isDB = false;
        
        if (database != null && database.getVendor().equals("Informix")) { //$NON-NLS-1$
            isDB = true;
        }
        
        return isDB;
    }

    public boolean isMSSQLServer() {
        boolean isDB = false;
        
        if (database != null && database.getVendor().equals("SQL Server")) { //$NON-NLS-1$
            isDB = true;
        }
        
        return isDB;
    }

    public boolean isInstantDB() {
        boolean isDB = false;
        
        if (database != null && database.getVendor().equals("Instant DB")) { //$NON-NLS-1$
            isDB = true;
        }
        
        return isDB;
    }

    public boolean isSybase() {
        boolean isDB = false;
        
        if (database != null && database.getVendor().equals("Sybase")) { //$NON-NLS-1$
            isDB = true;
        }
        
        return isDB;
    }

    public boolean isMySQL() {
        boolean isDB = false;
        
        if (database != null && database.getVendor().equals("My SQL")) { //$NON-NLS-1$
            isDB = true;
        }
        
        return isDB;
    }

    public boolean isDB2() {
        boolean isDB = false;
        
        if (database != null && database.getVendor().startsWith("DB2 UDB")) { //$NON-NLS-1$
            isDB = true;
        }
        
        return isDB;
    }

    public boolean isDB2UDBNT() {
        boolean isDB = false;
        
        if (database != null && database.getVendor().equals("DB2 UDB")) { //$NON-NLS-1$
            isDB = true;
        }
        
        return isDB;
    }

    public boolean isCloudscape() {
        boolean isDB = false;
        
        if (database != null 
         && ( database.getVendor().equals("IBM Cloudscape")  //$NON-NLS-1$
           || database.getVendor().equals("Apache Derby")  //$NON-NLS-1$
           || database.getVendor().equals("Derby")  //$NON-NLS-1$
           )) {
            isDB = true;
        }
        
        return isDB;

    }

}