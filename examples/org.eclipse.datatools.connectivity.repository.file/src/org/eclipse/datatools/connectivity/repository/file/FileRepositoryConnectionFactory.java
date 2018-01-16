/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.repository.file;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactory;
import org.eclipse.datatools.connectivity.IConnectionProfile;


public class FileRepositoryConnectionFactory implements IConnectionFactory {

	public IConnection createConnection(IConnectionProfile profile) {
		return createConnection(profile,null,null);
	}

	public IConnection createConnection(IConnectionProfile profile, String uid,
			String pwd) {
		return new FileRepositoryConnection(profile);
	}

}
