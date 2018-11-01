/*******************************************************************************
 * Copyright (c) 2006, 2007, 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.connectivity;

import org.eclipse.datatools.connectivity.Version;

/**
 * Helper class for handling Ingres version strings.
 * 
 * @author enrico.schenk@ingres.com
 */
public class VersionHelper {

	/**
	 * Returns a Version object according to the given argument. For Ingres the
	 * string looks like "II 9.2.0 (int.w32/118)". The returned object contains
	 * major version "9", minor version "2", release version "0" and the build
	 * version "(int.w32/118)". If the argument is no Ingres version string the
	 * result of Version.valueOf(String) is returned.
	 * 
	 * @param version string with version information
	 * @return Version object according the the argument
	 */
	public static Version valueOf(String version) {
		if (version == null || version.trim().length() == 0
				|| version.equals(Version.NULL_VERSION.toString())) {
			return Version.NULL_VERSION;
		}
		int major = 0, minor = 0, release = 0;
		String build = "";
		String[] components = version.split(" ", 3); //$NON-NLS-1$

		Version result = null;

		if (components.length > 1) {
			String[] versionComponents = components[1].split("\\.", 4); //$NON-NLS-1$
			if (versionComponents[0].trim().length() > 0) {
				try {
					major = Integer.parseInt(versionComponents[0].trim());
				} catch (NumberFormatException e) {
				}
			}
			if (versionComponents.length > 1
					&& versionComponents[1].trim().length() > 0) {
				try {
					minor = Integer.parseInt(versionComponents[1].trim());
				} catch (NumberFormatException e) {
				}
			}
			if (versionComponents.length > 2
					&& versionComponents[2].trim().length() > 0) {
				try {
					release = Integer.parseInt(versionComponents[2].trim());
				} catch (NumberFormatException e) {
				}
			}

			if (components.length > 2 && components[2].trim().length() > 0) {
				build = components[2].trim();
			}

			result = new Version(major, minor, release, build);
		} else {
			result = Version.valueOf(version);
		}

		return result;
	}

}
