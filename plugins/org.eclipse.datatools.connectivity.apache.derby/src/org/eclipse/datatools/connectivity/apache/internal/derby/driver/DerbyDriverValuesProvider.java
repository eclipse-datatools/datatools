/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.apache.internal.derby.driver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.drivers.DefaultDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.IDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.osgi.framework.Bundle;

public class DerbyDriverValuesProvider extends DefaultDriverValuesProvider {

	public String createDefaultValue(String key) {
		/**
		 * Check to see if the org.apache.derby.core wrapper plug-in is 
		 * in the Eclipse environment. If it is (most recently with 10.3 support),
		 * we'll use it and grab the derby jar from there.
		 */
		if (key.equals(IDriverValuesProvider.VALUE_CREATE_DEFAULT)) {
			Bundle[] bundles =
				Platform.getBundles("org.apache.derby.core", null); //$NON-NLS-1$
			if (bundles != null && bundles.length > 0) {
				URL url =
					bundles[0].getEntry("derby.jar"); //$NON-NLS-1$
				if (url != null) {
					return Boolean.toString(true);
				}
			}
		}
		if (key.equals(IDriverValuesProvider.VALUE_JARLIST)) {
			Bundle[] bundles =
				Platform.getBundles("org.apache.derby.core", null); //$NON-NLS-1$
			if (bundles != null && bundles.length > 0) {
				URL url =
					bundles[0].getEntry("derby.jar"); //$NON-NLS-1$
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
			String defaultURLPrefix = "jdbc:derby:";
			String defaultURLSuffix = ";create=true";
			String path = System.getProperty("user.home") + File.separator + "MyDB";
			String finalURL = defaultURLPrefix + path + defaultURLSuffix;
			return finalURL;
		}
		return super.createDefaultValue(key);
	}

}
