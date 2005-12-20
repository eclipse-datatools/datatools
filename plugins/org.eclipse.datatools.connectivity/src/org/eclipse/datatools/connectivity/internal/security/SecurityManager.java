/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.security;

/**
 * This class is intended manage security extensions used by the connection
 * profile framework.
 * 
 * Initially, only a non-configurable default encryption provider will be
 * implemented.
 * 
 * @author rcernich
 * 
 * Created on Dec 19, 2005
 */
public class SecurityManager {

	private static SecurityManager sInstance;

	private ICipherProvider mDefaultCipherProvider;

	public static SecurityManager getInstance() {
		if (sInstance == null) {
			sInstance = new SecurityManager();
		}
		return sInstance;
	}

	private SecurityManager() {
		super();
		mDefaultCipherProvider = new DefaultCipherProvider();
	}

	public ICipherProvider getDefaultCipherProvider() {
		return mDefaultCipherProvider;
	}

}
