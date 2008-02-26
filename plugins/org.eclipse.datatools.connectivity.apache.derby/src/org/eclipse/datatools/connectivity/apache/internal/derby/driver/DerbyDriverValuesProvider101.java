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

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.drivers.DefaultDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.IDriverValuesProvider;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.osgi.framework.Bundle;

public class DerbyDriverValuesProvider101 extends DefaultDriverValuesProvider {

	public String createDefaultValue(String key) {
		/**
		 * Check to see if the org.apache.derby.core wrapper plug-in is 
		 * in the Eclipse environment. If it is (most recently with 10.3 support),
		 * we'll use it and grab the derby jar from there.
		 * 
		 * If not, we'll check to see if the org.apache.derby plug-in is
		 * in the Eclipse environment. This is an Orbit plug-in. If it is, 
		 * it only currently supports 10.1 and 10.0, so we will use it for
		 * those drivers only.
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
			bundles = 
				Platform.getBundles("org.apache.derby", null); //$NON-NLS-1$
			if (bundles != null && bundles.length > 0) {
				return Boolean.toString(true);
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
			bundles = Platform.getBundles("org.apache.derby", null); //$NON-NLS-1$
			if (bundles != null && bundles.length > 0) {
				try {
					String path2 = FileLocator.resolve(bundles[0].getEntry("/")).getPath();//$NON-NLS-1$
					if (path2.endsWith("!/"))//$NON-NLS-1$
						path2 = path2.substring(0, path2.length() - 2);
					URL url = new URL(path2);
					if (url != null) {
						url = FileLocator.toFileURL(url);
						IPath path = new Path(url.getFile());
						if (!path.getFileExtension().equals("jar"))//$NON-NLS-1$
							path = path.addFileExtension("jar");//$NON-NLS-1$
						return path.toOSString();
					}
				} catch (IOException e1) {
					ConnectivityPlugin.getDefault().log(e1);
				} 
			}
		}
		return super.createDefaultValue(key);
	}

}
