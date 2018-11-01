/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

/**
 * @author rcernich
 * 
 * Created on May 31, 2005
 */
public class Version implements Comparable {

	public static final Version NULL_VERSION = new Version(0, 0, 0,
			new String()) {

		public String toString() {
			return ConnectionProfileConstants.UNKNOWN_VERSION;
		}
	};

	private Integer mHash;
	private int mMajor;
	private int mMinor;
	private int mRelease;
	private String mBuild;

	public static Version valueOf(String version) {
		if (version == null || version.trim().length() == 0
				|| version.equals(NULL_VERSION.toString())) {
			return NULL_VERSION;
		}
		int major = 0, minor = 0, release = 0;
		String build = new String();
		String[] components = version.split("\\.", 4); //$NON-NLS-1$
		if (components[0].trim().length() > 0) {
			try {
				major = Integer.parseInt(components[0].trim());
			}
			catch (NumberFormatException e) {
			}
		}
		if (components.length > 1 && components[1].trim().length() > 0) {
			try {
				minor = Integer.parseInt(components[1].trim());
			}
			catch (NumberFormatException e) {
			}
		}
		if (components.length > 2 && components[2].trim().length() > 0) {
			try {
				release = Integer.parseInt(components[2].trim());
			}
			catch (NumberFormatException e) {
			}
		}
		if (components.length > 3 && components[3].trim().length() > 0) {
			build = components[3].trim();
		}

		return new Version(major, minor, release, build);
	}

	public Version(int major, int minor, int release, String build) {
		mMajor = major;
		mMinor = minor;
		mRelease = release;
		mBuild = build;
	}

	/**
	 * @return the major component of this version
	 */
	public int getMajor() {
		return mMajor;
	}

	/**
	 * @return the minor component of this version
	 */
	public int getMinor() {
		return mMinor;
	}

	/**
	 * @return the release of this version
	 */
	public int getRelease() {
		return mRelease;
	}

	/**
	 * @return the build of this version
	 */
	public String getBuild() {
		return mBuild;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		int retVal;
		Version v = (Version) o;
		if (mMajor != v.mMajor) {
			retVal = mMajor - v.mMajor;
		}
		else if (mMinor != v.mMinor) {
			retVal = mMinor - v.mMinor;
		}
		else if (mRelease != v.mRelease) {
			retVal = mRelease - v.mRelease;
		}
		else {
			retVal = mBuild.compareTo(v.mBuild);
		}

		if (retVal == 0) {
			// NULL_VERSION should be < 0.0.0.
			if (v == NULL_VERSION) {
				if (this != NULL_VERSION) {
					retVal = 1;
				}
			}
			else if (this == NULL_VERSION) {
				retVal = -1;
			}
		}
		return retVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		boolean retVal;
		if (obj instanceof Version) {
			retVal = compareTo(obj) == 0;
		}
		else {
			retVal = false;
		}
		return retVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		if (mHash == null) {
			mHash = new Integer(toString().hashCode());
		}
		return mHash.intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(getMajor());
		buf.append('.').append(getMinor());
		buf.append('.').append(getRelease());
		if (getBuild() != null && getBuild().length() > 0) {
			buf.append('.').append(getBuild());
		}
		return buf.toString();
	}

}