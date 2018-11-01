/*******************************************************************************
 * Copyright (c) 2006-2007 Sybase, Inc.
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
 * Base implementation for IManagedConnectionListener.
 * 
 * @author rcernich
 *
 * Created on Jan 23, 2006
 */
public class ManagedConnectionAdapter implements
		IManagedConnectionOfflineListener {

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

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IManagedConnectionOfflineListener#aboutToAttach(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public void aboutToAttach(ConnectEvent event) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IManagedConnectionOfflineListener#aboutToDetach(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public void aboutToDetach(ConnectEvent event) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IManagedConnectionOfflineListener#workingOffline(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public void workingOffline(ConnectEvent event) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.IManagedConnectionOfflineListener#okToDetach(org.eclipse.datatools.connectivity.ConnectEvent)
	 */
	public boolean okToDetach(ConnectEvent event) {
		return true;
	}

}
