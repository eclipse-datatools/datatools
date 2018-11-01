/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Brian Fitzpatrick - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sqlite.driver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.drivers.DefaultDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.IDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.osgi.framework.Bundle;

/**
 * Driver Values Provider class for SQLite that checks to see if the SQLite
 * JDBC driver is wrapped in the environment. If so, it will try to find
 * the pure version of the driver and adjust the jar list accordingly.
 * 
 * @author brianf
 *
 */
public class SQLiteDriverValuesProvider extends DefaultDriverValuesProvider
		implements IDriverValuesProvider {
	
	public static String SQLITE_DRIVER_PLUGIN_ID = "org.sqlite.JDBC";//$NON-NLS-1$
	public static String SQLITE_DRIVER_JAR_PURE = "lib/sqlitejdbc-v051-pure.jar";//$NON-NLS-1$

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.drivers.DefaultDriverValuesProvider#createDefaultValue(java.lang.String)
	 */
	public String createDefaultValue(String key) {
		/**
		 * Check to see if the org.sqlite.JDBC wrapper plug-in is 
		 * in the Eclipse environment. If it is,
		 * we'll use it and grab the SQLite JDBC jar from there.
		 */
		if (key.equals(IDriverValuesProvider.VALUE_CREATE_DEFAULT)) {
			Bundle[] bundles =
				Platform.getBundles(SQLITE_DRIVER_PLUGIN_ID, null);
			if (bundles != null && bundles.length > 0) {
				// look for the pure JDBC/Java jar first
				URL url =
					bundles[0].getEntry(SQLITE_DRIVER_JAR_PURE);
				if (url != null) {
					return Boolean.toString(true);
				}
			}
		}
		if (key.equals(IDriverValuesProvider.VALUE_JARLIST)) {
			Bundle[] bundles =
				Platform.getBundles(SQLITE_DRIVER_PLUGIN_ID, null);
			if (bundles != null && bundles.length > 0) {
				URL url =
					bundles[0].getEntry(SQLITE_DRIVER_JAR_PURE);
				if (url != null) {
					try {
						url = FileLocator.toFileURL(url);
						IPath path = new Path(url.getFile());
						return path.toOSString();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (key.equals(IJDBCDriverDefinitionConstants.URL_PROP_ID)) {
			String defaultURLPrefix = "jdbc:sqlite:";//$NON-NLS-1$
			String path = System.getProperty("user.home") + File.separator + "MySQLiteDB";//$NON-NLS-1$ //$NON-NLS-2$
			String finalURL = defaultURLPrefix + path;
			return finalURL;
		}
		return super.createDefaultValue(key);
	}
}
