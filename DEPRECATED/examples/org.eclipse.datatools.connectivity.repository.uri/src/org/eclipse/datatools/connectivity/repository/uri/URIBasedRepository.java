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
package org.eclipse.datatools.connectivity.repository.uri;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.internal.repository.ConnectionProfileRepositoryBase;

public class URIBasedRepository extends ConnectionProfileRepositoryBase {

	public URIBasedRepository(IConnectionProfile profile) {
		super(profile);
	}

	protected Collection loadProfiles() throws CoreException {
		File repoFile = getRepositoryFile();
		if (repoFile == null) {
			// Houston, we have a problem here
			return null;
		}
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
			if (getRepositoryFile().canWrite())
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

	public URI getRepositoryURI() throws URISyntaxException {
		String URIString = getRepositoryProfile().getBaseProperties()
				.getProperty(URIRepositoryPlugin.URI_PATH_PROP_ID);
		return new URI(URIString);
	}
	
	public File getRepositoryFile() {
		try {
			URI repoURI = getRepositoryURI();
			File repoFile = new File ( repoURI);
			return repoFile;
		} catch (URISyntaxException e) {
			// catch and throw the exception?
			return null;
		}
	}

}
