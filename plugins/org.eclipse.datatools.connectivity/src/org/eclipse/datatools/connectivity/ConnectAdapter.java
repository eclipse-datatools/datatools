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
package org.eclipse.datatools.connectivity;

import org.eclipse.core.runtime.CoreException;

/**
 * @author rcernich
 * 
 * Created on May 24, 2005
 */
public class ConnectAdapter implements IConnectListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectListener#okToClose(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public boolean okToClose(ConnectEvent event) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectListener#openConnection(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public void openConnection(ConnectEvent event) throws CoreException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.IConnectListener#closeConnection(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public void closeConnection(ConnectEvent event) throws CoreException {
	}

}