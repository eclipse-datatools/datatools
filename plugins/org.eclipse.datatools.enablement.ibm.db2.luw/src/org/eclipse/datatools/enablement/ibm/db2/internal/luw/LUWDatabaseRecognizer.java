/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
	public static final String VERSION81 = "V8.1"; //$NON-NLS-1$
	public static final String VERSION82 = "V8.2"; //$NON-NLS-1$
	public static final String VERSION9 = "V9.1"; //$NON-NLS-1$
    private static final String PRODUCT = "DB2 UDB"; //$NON-NLS-1$
    
    public DatabaseDefinition recognize(Connection connection) {
        try {
            String version = connection.getMetaData().getDatabaseProductVersion();
            if(version.startsWith("SQL0801")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION81);
            }
            else if(version.startsWith("SQL0802")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION82);                
            }
            else if(version.startsWith("SQL0901")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION9);                               
            } 
        }
        catch (Exception e) {
        }

        return null;
    }
}
