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
package org.eclipse.datatools.enablement.ibm.db2.internal.iseries;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.IDatabaseRecognizer;

public class ISeriesDatabaseRecognizer implements IDatabaseRecognizer {
    private static final String PRODUCT = "DB2 UDB iSeries"; //$NON-NLS-1$
    
    public DatabaseDefinition recognize(Connection connection) {
        try {
            String version = connection.getMetaData().getDatabaseProductVersion();          
            // Support for the version string returned by the Toolbox for Java driver
            if (version.indexOf("V5R1") > -1){ //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V5R1"); //$NON-NLS-1$
            }
            else if (version.indexOf("V5R2") > -1){ //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V5R2"); //$NON-NLS-1$
            }
            else if (version.indexOf("V5R3") > -1){ //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V5R3"); //$NON-NLS-1$
            }
            // Support for the version string returned by the Universal driver
            else if (version.indexOf("V05R01") > -1){ //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V5R1"); //$NON-NLS-1$
            }
            else if (version.indexOf("V05R02") > -1){ //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V5R2"); //$NON-NLS-1$
            }
            else if (version.indexOf("V05R03") > -1){ //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V5R3"); //$NON-NLS-1$
            }            
            // Not sure what driver this code is supporting
            else if(version.startsWith("QSQ050")) { //$NON-NLS-1$
                    char r = version.charAt(6);
                    if(r == '1') {
                        return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V5R1");                     //$NON-NLS-1$
                    }
                    else if(r == '2') {
                        return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V5R2"); //$NON-NLS-1$
                    }
                    else if (r == '3'){
                        return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V5R3"); //$NON-NLS-1$
                    }
                    else if(r == '4') {
                        return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V5R4"); //$NON-NLS-1$
                    }
                }
        }
        catch (Exception e) {
        }

        return null;
    }
}
