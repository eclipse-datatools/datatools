/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

/**
 * Base implementation for IManagedConnectionListener.
 * 
 * @author rcernich
 *
 * Created on Jan 23, 2006
 */
public class ManagedConnectionAdapter implements IManagedConnectionListener {

	public ManagedConnectionAdapter() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IManagedConnectionListener#opened(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public void opened(ConnectEvent event) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IManagedConnectionListener#modified(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public void modified(ConnectEvent event) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IManagedConnectionListener#okToClose(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public boolean okToClose(ConnectEvent event) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IManagedConnectionListener#aboutToClose(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public void aboutToClose(ConnectEvent event) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IManagedConnectionListener#closed(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public void closed(ConnectEvent event) {
	}

}
