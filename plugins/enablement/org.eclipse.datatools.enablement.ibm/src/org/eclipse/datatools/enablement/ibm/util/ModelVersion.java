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

import org.eclipse.datatools.enablement.ibm.util.ModelVersionUtil;

/**
 * Enum for file version numbers. Version are presented as
 * major.minor.service.modifier, where each segment represents a integer.
 * 
 * @author Mike Kwong
 * 
 */
public enum ModelVersion {	
	
	/**
	 * Represents an UNKNOWN, unrecognized version of the model. Likely an
	 * error. First in the list of Enums to be treated inferior in comparison 
	 * to any of the recognized model versions.
	 */
	UNKNOWN(ModelVersionUtil.DATAMODEL_PREFIX, "-1", "0", "0", "0"),
	
	/**
	 * Represents RDA 7.0.0.x
	 */
	WTP("7", "0", "0", "0"),
	
	/**
	 * Represents RDA 7.5 and IDA 7.5.1
	 */
	DTP_75("7", "5", "0", "0"),
	
	/**
	 * Represents IDA 7.5.1.1
	 */
	DTP_7511("7", "5", "1", "1"),

	/**
	 * Represents IDA 7.5.2.0
	 */
	DTP_752("7", "5", "2", "0"),
	
	/**
	 * Represents special version for InfoSphere Discovery delivery
	 * (corresponds to IDA 7.5.2.1) 
	 */
	DISCOVERY_2009_10(ModelVersionUtil.DATAMODEL_PREFIX, "1", "0", "0", "0"),
	
	/**
	 * Represents IDA 7.5.2.1 and DS 2.2.1 (Nov 2009 release)
	 */
	DTP_2009_11(ModelVersionUtil.DATAMODEL_PREFIX, "1", "0", "0", "1"),
	
	/**
	 * Represents IDA 7.5.2.2 and DS 2.2.2.1 (Mar 2010 release)
	 */
	DTP_2010_03(ModelVersionUtil.DATAMODEL_PREFIX, "1", "1", "0", "0"),
	
	/**
	 * Represents IDA 7.5.3/ODS 2.2.3/ODA 2.2.3 and DS 2.3 (Oct 2010 release)
	 */
	DTP_2010_10(ModelVersionUtil.DATAMODEL_PREFIX, "1", "2", "0", "0"),
	
	/**
	 * Represents IDA 7.5.3.1/ODS 2.2.3.1/ODA 2.2.3.1 (DS 2011-1Q release)
	 */
	DTP_2011_1Q(ModelVersionUtil.DATAMODEL_PREFIX, "1", "2", "1", "0"),
	
	/**
	 * Represents IDA 7.6/ODS 2.3 (DS 2011-4Q release)
	 */
	DTP_2011_4Q(ModelVersionUtil.DATAMODEL_PREFIX, "1", "3", "0", "0"),
	
	/**
	 * Represents IDA 7.6 Patch
	 */
	DTP_2011_4Q_PATCH(ModelVersionUtil.DATAMODEL_PREFIX, "1", "3", "0", "1"),
	
	/**
	 * Represents IDA 8.1/DS 3.1.1 (DS 2012-1H release)
	 */
	DTP_2012_1H(ModelVersionUtil.DATAMODEL_PREFIX, "1", "4", "0", "0"),
	
	/**
	 * Represents IDA 8.5(DS 2012-2H release)
	 */
	DTP_2012_2H(ModelVersionUtil.DATAMODEL_PREFIX, "1", "5", "0", "0"),
	
	/**
	 * Represents IDA 9.1(DS 2013-2Q release)
	 */
	DTP_2013_2Q(ModelVersionUtil.DATAMODEL_PREFIX, "1", "6", "0", "0"),

	/**
	 * Represents IDA 9.1.1(DS 2013-2H release)
	 */
	DTP_2013_2H(ModelVersionUtil.DATAMODEL_PREFIX, "1", "7", "0", "0"),

	
	/**
	 * Represents a version namespace URI that conforms the correct pattern (
	 * {@link ModelVersionUtil#MODEL_URI_PATTERN}. Usually refers to a future
	 * version of the model. Note: reading in a model created with a newer
	 * version of the product than the current one can cause data loss.
	 * Restriction: does not currently contain information about the actual
	 * version for the model. TODO: Future enhancement.
	 */
	VALID_PATTERN(ModelVersionUtil.DATAMODEL_PREFIX, "100000", "0", "0", "0");
	
	public static ModelVersion CURRENT_VERSION = ModelVersion.DTP_2013_2H;
	
	private String major;
	private String minor;
	private String service;
	private String modifier;
	private String prefix;
	private String uriString;
	
	ModelVersion(String major, String minor, String service, String modifier) {
		this.major = major;
		this.minor = minor;
		this.service = service;
		this.modifier = modifier;
		this.prefix = ModelVersionUtil.PREFIX;
		this.uriString = prefix + major + "." + minor + "." + service + "." + modifier;
		ModelVersionUtil.add(this.uriString, this);
	}
	
	/**
	 * @param prefix {@link ModelVersionUtil#PREFIX} or {@link ModelVersionUtil#DATAMODEL_PREFIX}
	 * @param major
	 * @param minor
	 * @param service
	 * @param modifier
	 */
	ModelVersion(String prefix, String major, String minor, String service, String modifier) {
		this.major = major;
		this.minor = minor;
		this.service = service;
		this.modifier = modifier;
		this.prefix = prefix;
		this.uriString = prefix + major + "." + minor + "." + service + "." + modifier;
		ModelVersionUtil.add(this.uriString, this);
	}
	
	/**
	 * @return uri string appropriate for the version namespace for the model
	 *         file
	 */
	public String getURIString() {
		return uriString;
	}

	/**
	 * Major number should be incremented if the model format has changed in an
	 * incompatible manner.
	 * 
	 * @return string containing the major version; should represent an integer
	 */
	public String getMajor() {
		return major;
	}
	
	/**
	 * Major number should be incremented if the model format has changed in an
	 * incompatible manner.
	 * 
	 * @return integration representing the major version
	 */
	public Integer getMajorNumber() {
		return Integer.parseInt(major);
	}

	/**
	 * Minor number should be incremented if the model format has changed in a
	 * backwards compatible manner to accommodate significant new function.
	 * 
	 * @return string containing the minor version; should represent an integer
	 */
	public String getMinor() {
		return minor;
	}
	
	/**
	 * Minor number should be incremented if the model format has changed in a
	 * backwards compatible manner to accommodate significant new function.
	 * 
	 * @return integer representing the minor version
	 */
	public Integer getMinorNumber() {
		return Integer.parseInt(minor);
	}

	/**
	 * Service number should be incremented if the model format has changed in
	 * any way.
	 * 
	 * @return string containing the service version; should represent an
	 *         integer
	 */
	public String getService() {
		return service;
	}
	
	/**
	 * Service number should be incremented if the model format has changed in
	 * any way.
	 * 
	 * @return integer representing the service version
	 */
	public Integer getServiceNumber() {
		return Integer.parseInt(service);
	}

	/**
	 * Modifier number should be incremented for all official fix packs and
	 * releases. This can be used to catch unexpected bugs.
	 * 
	 * @return string containing the modifier version; should represent an
	 *         integer
	 */
	public String getModifier() {
		return modifier;
	}
	
	/**
	 * Modifier number should be incremented for all official fix packs and
	 * releases. This can be used to catch unexpected bugs.
	 * 
	 * @return integer representing the modifier version
	 */
	public Integer getModifierNumber() {
		return Integer.parseInt(modifier);
	}

	/**
	 * @return the prefix used to construct the corresponding namespace URI
	 */
	public String getPrefix() {
		return prefix;
	}
	
	/**
	 * Compares this version to version to determine which is newer. Only the
	 * namespace prefix, major, minor and service are treated as significant.
	 * Modifier differences are ignored for comparison purposes.
	 * 
	 * @param version
	 * @return -1 if this version if older than version being compared; 0 if
	 *         they are equal; 1 if this version is newer
	 * @see Comparable#compareTo(Object)
	 */
	public int compareToIgnoresModifier(ModelVersion version) {
		if (version == null) {
			return 1;
		}
		String myNamespacePrefix = this.getPrefix();
		String theirNamespacePrefix = version.getPrefix();
		int myMajor = getMajorNumber();
		int theirMajor = version.getMajorNumber();
		int myMinor = getMinorNumber();
		int theirMinor = version.getMinorNumber();
		int myService = getServiceNumber();
		int theirService = version.getServiceNumber();

		return compareVersionComponents(myNamespacePrefix, myMajor, myMinor,
				myService, theirNamespacePrefix, theirMajor, theirMinor,
				theirService);
	}
	
	/**
	 * Compares this version to a version string to determine which is newer. Only the
	 * namespace prefix, major, minor and service are treated as significant.
	 * Modifier differences are ignored for comparison purposes.
	 * 
	 * @param version
	 * @return -1 if this version if older than version being compared; 0 if
	 *         they are equal; 1 if this version is newer
	 * @see Comparable#compareTo(Object)
	 */
	public int compareToIgnoresModifier(String versionNamespace) {
		String[] components = ModelVersionUtil
				.parseVersionNamespace(versionNamespace);
		if (components == null) {
			// Should not get to this point
			throw new IllegalArgumentException();
		}
		return compareVersionComponents(getPrefix(), Integer
				.parseInt(getMajor()), Integer.parseInt(getMinor()), Integer
				.parseInt(getService()), components[0], Integer
				.parseInt(components[1]), Integer.parseInt(components[2]),
				Integer.parseInt(components[3]));
	}
	
	/**
	 * Compares version 1 with version 2 to determine which is newer. 
	 * 
	 * @param namespacePrefix1 namespace prefix of version 1
	 * @param major1 major number of version 1
	 * @param minor1
	 * @param service1
	 * @param namespacePrefix2
	 * @param major2
	 * @param minor2
	 * @param service2
	 * @return -1 if version 1 if older than version 2; 0 if
	 *         they are equal; 1 if version 2 is newer
	 */
	public static int compareVersionComponents(String namespacePrefix1,
			int major1, int minor1, int service1,
			String namespacePrefix2, int major2, int minor2,
			int service2) {
		if (namespacePrefix1 == null) {
			// Should not get here
			throw new IllegalArgumentException();
		}
		if (!namespacePrefix1.equals(namespacePrefix2)) {
			if (namespacePrefix1.equals(ModelVersionUtil.PREFIX)
					&& namespacePrefix2
							.equals(ModelVersionUtil.DATAMODEL_PREFIX)) {
				return -1;
			} else if (namespacePrefix1
					.equals(ModelVersionUtil.DATAMODEL_PREFIX)
					&& namespacePrefix2.equals(ModelVersionUtil.PREFIX)) {
				return 1;
			} else {
				// Should not get here
				throw new IllegalArgumentException();
			}
		} else {
			int majorDifference = major1 - major2;
			int minorDifference = minor1 - minor2;
			int serviceDifference = service1 - service2;
			if (majorDifference < 0) {
				return -1;
			} else if (majorDifference > 0) {
				return 1;
			} else if (minorDifference < 0) {
				return -1;
			} else if (minorDifference > 0) {
				return 1;
			} else if (serviceDifference < 0) {
				return -1;
			} else if (serviceDifference > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
