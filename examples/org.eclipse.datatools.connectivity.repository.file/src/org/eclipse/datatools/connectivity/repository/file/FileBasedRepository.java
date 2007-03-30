/*******************************************************************************
 * Copyright (c) 2006-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.repository.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.internal.repository.ConnectionProfileRepositoryBase;

public class FileBasedRepository extends ConnectionProfileRepositoryBase {

	public FileBasedRepository(IConnectionProfile profile) {
		super(profile);
	}

	protected Collection loadProfiles() throws CoreException {
		File repoFile = getRepositoryFile();
		if (!repoFile.exists()) {
			try {
				ConnectionProfileMgmt.saveCPs(new IConnectionProfile[0],repoFile,null);
			}
			catch (CoreException e) {
				// we can eat this since we'll be flagged as read-only
			}
			return new ArrayList();
		}
		return Arrays.asList(ConnectionProfileMgmt.loadCPs(repoFile));
	}

	public void save() {
		try {
			ConnectionProfileMgmt.saveCPs(getProfiles(),getRepositoryFile(),null);
		}
		catch (CoreException e) {
			ConnectivityPlugin.getDefault().log(e.getStatus());
		}
	}

	public boolean isReadOnly() {
		return !getRepositoryFile().canWrite();
	}

	public boolean supportsProfileType(String providerID) {
		// we support 'em all
		return true;
	}

	public boolean supportsCategory(String id) {
		// we support 'em all
		return true;
	}

	public File getRepositoryFile() {
		String fileName = getRepositoryProfile().getBaseProperties()
				.getProperty(FileRepositoryPlugin.FILE_NAME_PROP_ID);
		return new File(fileName);
	}

}
