/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     nexB - implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.msft.internal.sqlserver.driver;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.drivers.DefaultDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.IDriverValuesProvider;
import org.osgi.framework.Bundle;

public abstract class SQLServerDriverValuesProvider extends DefaultDriverValuesProvider {

	public abstract String getDriverDirName();
	
	public String createDefaultValue(String key) {
		/**
		 * Check to see if the wrapper plug-in is in the Eclipse environment.
		 * If it is we'll use it and grab the sqljdbc jar from there.
		 */
		if (key.equals(IDriverValuesProvider.VALUE_CREATE_DEFAULT)) {
			Bundle[] bundles =
				Platform.getBundles("org.eclipse.datatools.enablement.msft.sqlserver.driver", null); //$NON-NLS-1$
			if (bundles != null && bundles.length > 0) {
				Enumeration jars = bundles[0].findEntries(getDriverDirName(), "*.jar", true); //$NON-NLS-1$
				while (jars != null && jars.hasMoreElements()) { 
					URL url = (URL) jars.nextElement();
					if (url != null) {
						return Boolean.toString(true);
					}
				}
			}
		}
		if (key.equals(IDriverValuesProvider.VALUE_JARLIST)) {
			Bundle[] bundles =
				Platform.getBundles("org.eclipse.datatools.enablement.msft.sqlserver.driver", null); //$NON-NLS-1$
			if (bundles != null && bundles.length > 0) {
				Enumeration jars = bundles[0].findEntries(getDriverDirName(), "*.jar", true); //$NON-NLS-1$
				StringBuffer urls = null;
				while (jars != null && jars.hasMoreElements()) { 
					URL url = (URL) jars.nextElement();

					if (url != null) {
						try {
							url = FileLocator.toFileURL(url);
							IPath path = new Path(url.getFile());
							if (urls == null) {
								urls = new StringBuffer();
							}
							if (urls.length() > 0) {
								urls.append(";");
							}
							urls.append(path.toOSString());
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				if (urls != null && urls.length() > 0) {
					return urls.toString();
				}
			}
		}
		return super.createDefaultValue(key);
	}

}
