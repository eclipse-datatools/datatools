/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.ui.status;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.ConnectionUtil;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * A <code>IJobChangeListener</code> to handle <code>DatatoolsStatus</code> 
 *
 */
public class DatatoolsStatusListener extends JobChangeAdapter
{
	private IConnectionProfile profile;
	
	public DatatoolsStatusListener(IConnectionProfile profile)
	{
		this.profile = profile;
	}
	
	public void done(IJobChangeEvent event) {
		IManagedConnection managedConnection = 
			profile.getManagedConnection(ConnectionUtil.CONNECTION_TYPE);

		if (managedConnection != null) {
			IConnection internalConnection = managedConnection.getConnection();
			if (internalConnection == null || internalConnection.getConnectException() == null) {
				return;
			}
			
			IStatus datatoolsStatus = new DatatoolsStatus(
					IStatus.ERROR, 
					ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName(),
					internalConnection.getConnectException(),
					profile,
					internalConnection);
			
			((DatatoolsStatus)datatoolsStatus).setName(DatatoolsStatusTypes.CONNECTION_STATUS);
			StatusManager.getManager().handle(datatoolsStatus, 
					StatusManager.LOG | StatusManager.SHOW | StatusManager.BLOCK);
		}
	}
}
