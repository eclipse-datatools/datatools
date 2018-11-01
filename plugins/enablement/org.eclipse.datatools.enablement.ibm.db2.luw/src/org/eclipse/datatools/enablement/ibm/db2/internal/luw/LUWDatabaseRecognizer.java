/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.internal.luw;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.IDatabaseRecognizer;

public class LUWDatabaseRecognizer implements IDatabaseRecognizer {
    private static final String VERSION71 = "V7.1"; //$NON-NLS-1$
    private static final String VERSION72 = "V7.2"; //$NON-NLS-1$
    public static final String VERSION81 = "V8.1"; //$NON-NLS-1$
    public static final String VERSION82 = "V8.2"; //$NON-NLS-1$
    public static final String VERSION9 = "V9.1"; //$NON-NLS-1$
    public static final String VERSION95 = "V9.5"; //$NON-NLS-1$
    public static final String VERSION97 = "V9.7"; //$NON-NLS-1$
    public static final String VERSION98 = "V9.8"; //$NON-NLS-1$
    public static final String VERSION101 = "V10.1"; //$NON-NLS-1$
    public static final String VERSION105 = "V10.5"; //$NON-NLS-1$

    private static final String PRODUCT = "DB2 UDB"; //$NON-NLS-1$
    
    // !IMPORTANT!
    // PLEASE NOTE AND READ THIS BEFORE MAKING MODIFICATIONS TO THIS FILE
    //
    // The below code is for version recognition of DB2 LUW databases.
    // This code is executed at the time when we connect to a DB2 LUW
    // database. It is vital that the correct DB2 LUW version is
    // recognized as this is the version that all of our code looks at and
    // a lot of our code is version specific. A bug in this code can cause
    // huge issues. As an example there was a bug where the version check
    // for DB2 LUW v10.1 was as follows.
    // What it was in error
    // else if(version.startsWith("SQL10010"))
    // What it should have been
    // else if(version.startsWith("SQL1001"))
    // 
    // That extra zero caused us to ONLY properly recognize DB2 LUW v10.1
    // GA version. DB2 LUW v10.1 FP1 or later would not fall into this
    // category and then would default to DB2 LUW v9.1. First off since
    // this is done at connect time absolutely every user who uses our
    // product in conjunction with DB2 LUW v10.1 FP1 or later hit this
    // issue 100 percent of the time. This causes problems everywhere,
    // loading code, generateDDL, modeling...
    //
    // The Product Release Code that we get from the DB2 LUW database
    // is in the form of "SQL" followed by VRF (Version, Release and
    // Fixpack). We only care about and should only be checking for
    // "SQL" followed by VR (Version and Release).
    //
    // Example:
    // Below are a few databases and their Product Release Code
    // DB2 LUW v9.8 FP1  - SQL09081
    // DB2 LUW v9.8 FP4  - SQL09084
    // DB2 LUW v10.1 GA  - SQL10010
    // DB2 LUW v10.1 FP2 - SQL10012
    //
    // The below version checks will identify and categorize
    // all of the above databases IGNORING the fixpack value.
    //
    // else if(version.startsWith("SQL0908"))
    // else if(version.startsWith("SQL1001"))
    //
    // Please see below an example of how to add a new version
    // recognizer if a new Version or Release of DB2 LUW came out.
    // Example: DB2 LUW v11.5 (made up)
    //
    // 1. Add a new variable to represent the new Version and/or Release.
    //
    //    public static final String VERSION115 = "V11.5"; //$NON-NLS-1$
    //
    //
    // 2. Add a new 'else if' at the bottom to catch databases of this
    //    type of Version and Release.
    //
    //    else if(version.startsWith("SQL1105")) { //$NON-NLS-1$
    //         return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION115);
    //    }
    //
    //    NOTE: This 'else if' will catch all databases of v11.5 regardless of fixpack.
    //    NOTE: If you were to change it to startsWith("SQL11050") then it WOULD ONLY
    //          CATCH DB2 LUW v11.5 GA AND NOT ANY OF THE FIXPACKS which is of course wrong.
    //          So please make sure to quadruple check the string you will be matching.
    //
    //
    // PLEASE NOTE AND READ THIS BEFORE MAKING MODIFICATIONS TO THIS FILE
    // !IMPORTANT!
    
    public DatabaseDefinition recognize(Connection connection) {
        try {
            String version = connection.getMetaData().getDatabaseProductVersion();
            
            // Some versions are recognized in DTP
            if(version.startsWith("SQL0701")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION71);
            }
            else if(version.startsWith("SQL0702")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION72);                
            }
            else if(version.startsWith("SQL0801")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION81);
            }
            else if(version.startsWith("SQL0802")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION82);                
            }
            else if(version.startsWith("SQL0901")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION9);                               
            } 
            else if(version.startsWith("SQL0905")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION95);                
            } 
            else if(version.startsWith("SQL0907")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION97);                
            }
            else if(version.startsWith("SQL0908")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION98);                
            }
            else if(version.startsWith("SQL1001")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION101);
            }
            //Temporary hack to make 10.2 connection point to 10.5 connection profile
            else if(version.startsWith("SQL1002")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION105);
            }
            else if(version.startsWith("SQL1005")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION105);
            }
        }
        catch (Exception e) {
        }

        return null;
    }
}
