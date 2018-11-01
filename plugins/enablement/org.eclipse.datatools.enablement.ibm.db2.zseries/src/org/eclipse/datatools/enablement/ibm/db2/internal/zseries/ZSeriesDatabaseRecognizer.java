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
package org.eclipse.datatools.enablement.ibm.db2.internal.zseries;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.IDatabaseRecognizer;

public class ZSeriesDatabaseRecognizer implements IDatabaseRecognizer {
    public static final String PRODUCT = "DB2 UDB zSeries"; //$NON-NLS-1$
	public static final String VERSION7 = "V7"; //$NON-NLS-1$
	public static final String VERSION8_NewFunctionMode = "V8 (New-Function Mode)"; //$NON-NLS-1$
	public static final String VERSION8_CompatMode = "V8 (Compatibility Mode)"; //$NON-NLS-1$
	public static final String VERSION9_NewFunctionMode = "V9 (New-Function Mode)"; //$NON-NLS-1$
	public static final String VERSION9_CompatMode = "V9 (Compatibility Mode)"; //$NON-NLS-1$

    
    public DatabaseDefinition recognize(Connection connection) {
        try {
            String version = connection.getMetaData().getDatabaseProductVersion();
            if(version.startsWith("DSN07")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V7"); //$NON-NLS-1$
            }
            else if(version.startsWith("DSN08")) { //$NON-NLS-1$
                char m = version.charAt(7);
                if(m > '4') {
                    return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V8 (New-Function Mode)"); //$NON-NLS-1$
                }
                else {
                    return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V8 (Compatibility Mode)");                     //$NON-NLS-1$
                }
            }
            else if(version.startsWith("DSN09"))
            {
            	char m = version.charAt(7);
                if(m > '4') {
                    return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V9 (New-Function Mode)"); //$NON-NLS-1$
                }
                else {
                    return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, "V9 (Compatibility Mode)");                     //$NON-NLS-1$
                }
            }
        }
        catch (Exception e) {
        }

        return null;
    }
}
