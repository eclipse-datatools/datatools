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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;

public class URIRepositoryConnection implements IConnection {

	private URIBasedRepository mRepository;
	private CoreException mConnectException;

	public URIRepositoryConnection(IConnectionProfile profile) {
		mRepository = new URIBasedRepository(profile);
		try {
			mRepository.load();
		}
		catch (CoreException e) {
			mConnectException = e;
		}
	}

	public void close() {
		mRepository.dispose();
	}

	public Throwable getConnectException() {
		return mConnectException;
	}

	public IConnectionProfile getConnectionProfile() {
		return mRepository.getRepositoryProfile();
	}

	public Object getRawConnection() {
		return mRepository;
	}

}
