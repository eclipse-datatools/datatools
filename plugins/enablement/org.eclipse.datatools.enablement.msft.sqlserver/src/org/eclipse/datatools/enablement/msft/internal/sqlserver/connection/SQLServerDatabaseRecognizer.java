/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.connection;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.IDatabaseRecognizer;

public class SQLServerDatabaseRecognizer implements IDatabaseRecognizer {
    public static final String PRODUCT = "SQL Server"; //$NON-NLS-1$
	public static final String VERSION2000 = "2000"; //$NON-NLS-1$
	public static final String VERSION2005 = "2005"; //$NON-NLS-1$
	public static final String VERSION2008 = "2008"; //$NON-NLS-1$
	public static final String VERSION2012 = "2012"; //$NON-NLS-1$
	public static final String VERSION2014 = "2014"; //$NON-NLS-1$
	public static final String VERSION2016 = "2016"; //$NON-NLS-1$
    
    public DatabaseDefinition recognize(Connection connection) {
        try {
        	String product = connection.getMetaData().getDatabaseProductName();
        	if (product.indexOf(PRODUCT) < 0)
        		return null;
        	
        	String version = connection.getMetaData().getDatabaseProductVersion();
            if (version == null) {
            	return null;
            }
            Pattern p = Pattern.compile("[\\d]+[.][\\d]+[.][\\d]+"); //$NON-NLS-1$
            Matcher m = p.matcher(version);
            m.find();
            version = m.group();
            if(version.startsWith("8.")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION2000);
            }
            else if(version.startsWith("9.")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION2005);
            }
            else if(version.startsWith("10.")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION2008);
            }
            else if(version.startsWith("11.")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION2012);
            }
            else if(version.startsWith("12.")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION2014);
            }
            else if(version.startsWith("13.")) { //$NON-NLS-1$
                return DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(PRODUCT, VERSION2016);
            }
        }
        catch (Exception e) {
        }

        return null;
    }
}
