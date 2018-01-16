package org.eclipse.datatools.enablement.sybase.asa;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.drivers.DefaultDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.IDriverValuesProvider;
import org.osgi.framework.Bundle;

public class ASADriverValuesProvider extends DefaultDriverValuesProvider {

	public String createDefaultValue(String key) {
		/**
		 * Check to see if the org.apache.derby.core wrapper plug-in is 
		 * in the Eclipse environment. If it is (most recently with 10.3 support),
		 * we'll use it and grab the derby jar from there.
		 */
		if (key.equals(IDriverValuesProvider.VALUE_CREATE_DEFAULT)) {
			Bundle[] bundles =
				Platform.getBundles("com.sybase.jconnect60", null); //$NON-NLS-1$
			if (bundles != null && bundles.length > 0) {
				URL url =
					bundles[0].getEntry("\\lib\\jconn3.jar"); //$NON-NLS-1$
				if (url != null) {
					return Boolean.toString(true);
				}
			}
		}
		if (key.equals(IDriverValuesProvider.VALUE_JARLIST)) {
			Bundle[] bundles =
				Platform.getBundles("com.sybase.jconnect60", null); //$NON-NLS-1$
			if (bundles != null && bundles.length > 0) {
				URL url =
					bundles[0].getEntry("\\lib\\jconn3.jar"); //$NON-NLS-1$
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
		return super.createDefaultValue(key);
	}

}
