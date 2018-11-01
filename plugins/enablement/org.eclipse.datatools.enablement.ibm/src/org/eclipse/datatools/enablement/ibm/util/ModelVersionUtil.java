/*******************************************************************************
 * Copyright (c) 2009, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilities for managing {@link ModelVersion} enums
 * @author Mike Kwong
 *
 */
public class ModelVersionUtil {
	// url prefix to use for IDA 7.5.1.1 and 7.5.2
	public static final String PREFIX = "http://www.ibm.com/com.ibm.datatools.core/model/";
	// url prefix to use for IDA 7.5.2+
	public static final String DATAMODEL_PREFIX = "http://www.ibm.com/com.ibm.datatools.core/model/data/";
	// uri pattern to use for IDA 7.5.2.1 and beyond
	public static final Pattern MODEL_URI_PATTERN = Pattern.compile(DATAMODEL_PREFIX + "(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)");
	public static final Pattern OLD_MODEL_URI_PATTERN = Pattern.compile(PREFIX + "(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)");
	
	private static Map<String, ModelVersion> map;
	
	static Map<String, ModelVersion> getMap() {
		if (map == null) {
			map = new HashMap<String, ModelVersion>();
			ModelVersion dummy = ModelVersion.CURRENT_VERSION; // Force ModelVersion to load and populate map
			if (dummy != null) { 
				// Needed null check, to handle the case when one of the Model_Version enum constants (e.g. WTP)
				// is accessed before getMap() is called.  This would cause getMap to be invoked without
				// CURRENT_VERSION being initialized.  This is harmless, as CURRENT_VERSION would be
				// initialized soon after, as long as we don't try to access dummy.toString
		//		DataToolsPlugin.getDefault().getLog().log(new Status(IStatus.INFO, DataToolsPlugin.getPluginID(), "Current Version is " + dummy.toString()));
			}
		}
		return map;
	}

	/**
	 * Register version object and associate with specified URI
	 * @param uri
	 * @param version
	 */
	public static void add(String uri, ModelVersion version) {
		getMap().put(uri, version);
	}

	/**
	 * @param uri
	 * @return version object associated with specified URI,
	 *         {@link ModelVersion#VALID_PATTERN} if the namespace conforms to the
	 *         correct pattern, {@link ModelVersion#UNKNOWN} if the namespace
	 *         does not conform to the correct pattern, or null if the version
	 *         string is missing
	 */
	public static ModelVersion uriToModelVersion(String uri) {
		if (uri == null) {
			// Missing version
			return null; 
		}
		// Look up uri to find corresponding version
		ModelVersion version = getMap().get(uri);
		if (version != null) {
			// Known uri with corresponding version
			return version;
		} else {
			Matcher matcher = MODEL_URI_PATTERN.matcher(uri);
			if (matcher.matches()) {
				// Conforms to MODEL_URI_PATTERN
				return ModelVersion.VALID_PATTERN;
			} else {
				// Does not conform to MODEL_URI_PATTERN
				return ModelVersion.UNKNOWN;
			}
		}
	}
	
	/**
	 * @param versionNamespace
	 *            string containing the namespace string indicating the version
	 * @return string array containing the namespace prefix, major, minor,
	 *         service and modifier components; or null if the string is not a
	 *         valid version namespace
	 */
	public static String[] parseVersionNamespace(String versionNamespace) {
		Matcher matcher = MODEL_URI_PATTERN.matcher(versionNamespace);
		Matcher oldMatcher = OLD_MODEL_URI_PATTERN.matcher(versionNamespace);
		if (matcher.matches()) {
			return new String[] { DATAMODEL_PREFIX, matcher.group(1),
					matcher.group(2), matcher.group(3), matcher.group(4) };
		} else if (oldMatcher.matches()) {
			return new String[] { PREFIX, oldMatcher.group(1),
					oldMatcher.group(2), oldMatcher.group(3),
					oldMatcher.group(4) };
		} else {
			return null;
		}
	}
}
