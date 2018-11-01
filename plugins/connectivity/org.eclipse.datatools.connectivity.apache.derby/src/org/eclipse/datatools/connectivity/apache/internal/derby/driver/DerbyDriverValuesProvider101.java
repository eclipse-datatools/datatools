/*******************************************************************************
 * Copyright (c) 2008, 2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: 
 *      brianf - initial API and implementation
 *      Actuate Corporation - support for OSGi-less platform (Bugzilla 338997)
 ******************************************************************************/

package org.eclipse.datatools.connectivity.apache.internal.derby.driver;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.connectivity.drivers.DefaultDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.IDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.services.PluginResourceLocator;

public class DerbyDriverValuesProvider101 extends DefaultDriverValuesProvider {

	public String createDefaultValue(String key) {
		/**
		 * Check to see if the org.apache.derby.core wrapper plug-in is 
		 * in the installed environment. If it is (most recently with 10.3 support),
		 * we'll use it and grab the derby jar from there.
		 * 
		 * If not, we'll check to see if the org.apache.derby plug-in is
		 * in the installed environment. This is an Orbit plug-in. If it is, 
		 * it only currently supports 10.1 and 10.0, so we will use it for
		 * those drivers only.
		 */
		if (key.equals(IDriverValuesProvider.VALUE_CREATE_DEFAULT)) {
            URL url = PluginResourceLocator.getPluginEntry( "org.apache.derby.core",  //$NON-NLS-1$
                        "derby.jar" ); //$NON-NLS-1$
            if (url != null)
                return Boolean.toString(true);

            IPath path = PluginResourceLocator.getPluginRootPath( "org.apache.derby" ); //$NON-NLS-1$
			if (path != null ) {
				return Boolean.toString(true);
			}
		}

		if (key.equals(IDriverValuesProvider.VALUE_JARLIST)) {
            IPath path = PluginResourceLocator.getPluginEntryPath( "org.apache.derby.core",  //$NON-NLS-1$
                            "derby.jar" ); //$NON-NLS-1$
            if (path != null) 
                return path.toOSString();           

            path = PluginResourceLocator.getPluginRootPath( "org.apache.derby" ); //$NON-NLS-1$
            if (path != null) 
            {
                if (!path.getFileExtension().equals("jar"))//$NON-NLS-1$
                    path = path.addFileExtension("jar");//$NON-NLS-1$
                return path.toOSString();                
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
