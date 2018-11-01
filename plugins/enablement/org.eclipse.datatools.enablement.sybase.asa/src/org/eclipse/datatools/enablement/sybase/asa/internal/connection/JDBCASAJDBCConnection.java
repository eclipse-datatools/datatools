/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.internal.connection;

import java.sql.Driver;
import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.db.generic.IDBConnectionProfileConstants;
import org.eclipse.datatools.connectivity.db.generic.JDBCConnection;

public class JDBCASAJDBCConnection extends JDBCConnection {

    public JDBCASAJDBCConnection(IConnectionProfile profile,
										Class factoryClass) {
		super(profile, factoryClass);
	}
    
    protected Object createConnection(ClassLoader cl) throws Throwable
    {
        Properties props = getConnectionProfile().getBaseProperties();
        Properties connectionProps = new Properties();
        
        String driverClass = getDriverDefinition().getProperty(
                IDBConnectionProfileConstants.DRIVER_CLASS_PROP_ID);
        String connectURL = props
                .getProperty(IDBConnectionProfileConstants.URL_PROP_ID);
        String uid = props
                .getProperty(IDBConnectionProfileConstants.USERNAME_PROP_ID);
        String pwd = props
                .getProperty(IDBConnectionProfileConstants.PASSWORD_PROP_ID);
        String nameValuePairs = props
                .getProperty(IDBConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID);
        String propDelim = ",";//$NON-NLS-1$

        if (uid != null) {
            connectionProps.setProperty("user", uid); //$NON-NLS-1$
        }
        if (pwd != null) {
            connectionProps.setProperty("password", pwd); //$NON-NLS-1$
        }

        if (nameValuePairs != null && nameValuePairs.length() > 0) {
            String[] pairs = parseString(nameValuePairs, ","); //$NON-NLS-1$
            String addPairs = ""; //$NON-NLS-1$
            for (int i = 0; i < pairs.length; i++) {
                String[] namevalue = parseString(pairs[i], "="); //$NON-NLS-1$
                connectionProps.setProperty(namevalue[0], namevalue[1]);
                if (i == 0 || i < pairs.length - 1) {
                    addPairs = addPairs + propDelim;
                }
                addPairs = addPairs + pairs[i];
            }
        }

        //Add a internal name to the connection
        String name = InternalNameGenerator.getName();
        connectionProps.put("REMOTEPWD", ",,CON=" + name);
        
        Driver jdbcDriver = (Driver) cl.loadClass(driverClass).newInstance();
        return jdbcDriver.connect(connectURL, connectionProps);
    }

    public static class InternalNameGenerator
    {
        private static int _sequence;
        
        public static final String PREFIX    = "__dmpinternalname_";
        
        public synchronized static String getName()
        {
            return PREFIX + (_sequence++);
        }
    }
}
