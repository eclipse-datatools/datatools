/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.cp;

import java.io.File;
import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.VersionProviderConnection;

/**
 * @author rcernich
 * 
 * Created on Mar 15, 2004
 */
public class FileConnection extends VersionProviderConnection implements
		IConnection {

	private final static String TECHNOLOGY_ROOT_KEY = "fileSample";

	private File mFile;
	private Exception mConnectException;

	public FileConnection(IConnectionProfile profile) {
		super(profile, FileConnectionFactory.class);
		Properties props = profile.getBaseProperties();
		String path = props
				.getProperty(IFileProfilePropertyConstants.FILE_PATH);
		if (path == null) {
			mFile = null;
		}
		else {
			mFile = new File(path);
			if (!mFile.isDirectory() || !mFile.exists()) {
				mFile = null;
			}
		}
		if (mFile == null) {
			mConnectException = new Exception(
					"Specified directory does not exist.");
			clearVersionCache();
		}
		else {
			updateVersionCache();
		}
	}

	public Object getRawConnection() {
		return mFile;
	}

	public void close() {
		// Nothing to do here. We don't really open anything.
		mFile = null;
		mConnectException = null;
	}

	public Throwable getConnectException() {
		return mConnectException;
	}

	protected String getTechnologyRootKey() {
		return TECHNOLOGY_ROOT_KEY;
	}

	public String getProviderName() {
		// We'll use the OS name for the technology type
		return System.getProperty("os.name");
	}

	public Version getProviderVersion() {
		// We'll use the OS version for the technology version
		return Version.valueOf(System.getProperty("os.version"));
	}

	public String getTechnologyName() {
		// We'll use the JVM name for the provider name
		return System.getProperty("java.vendor");
	}

	public Version getTechnologyVersion() {
		// We'll use the JVM version for the provider version
		return Version.valueOf(System.getProperty("java.version"));
	}

}