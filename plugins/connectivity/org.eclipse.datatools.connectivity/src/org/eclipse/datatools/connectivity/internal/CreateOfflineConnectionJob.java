/*******************************************************************************
 * Copyright (c) 2007, 2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.IConnectionFactoryProvider;
import org.eclipse.datatools.connectivity.IConnectionProfile;

public class CreateOfflineConnectionJob extends Job {

	private ManagedConnection mManagedConnection;
	private Object mFamily;

	public CreateOfflineConnectionJob(ManagedConnection managedConnection,
										Object family) {
		super(ConnectivityPlugin.getDefault().getResourceString(
				"CreateOfflineConnectionJob.name", //$NON-NLS-1$
				new Object[] {
						managedConnection.getConnectionFactoryProvider()
								.getName(),
						managedConnection.getConnectionProfile().getName()}));
		setUser(true);
		mManagedConnection = managedConnection;
		mFamily = family;
	}

	public ManagedConnection getManagedConnection() {
		return mManagedConnection;
	}

	private IConnectionFactoryProvider getConnectionFactoryProvider() {
		return mManagedConnection.getConnectionFactoryProvider();
	}

	private IConnectionProfile getConnectionProfile() {
		return mManagedConnection.getConnectionProfile();
	}

	protected IStatus run(IProgressMonitor monitor) {
		IStatus status = Status.OK_STATUS;
		monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);
		try {
			if (mManagedConnection.canWorkOffline()) {
				mManagedConnection.workOffline(monitor);
			}
			else if (mManagedConnection.isConnected()) {
				mManagedConnection.close();
			}
		}
		catch (CoreException e) {
			status = e.getStatus();
		}
		catch (Exception e) {
			status = new Status(IStatus.ERROR, ConnectivityPlugin.getSymbolicName(), -1, 
					ConnectivityPlugin.getDefault().getResourceString(
							"CreateOfflineConnectionJob.error", //$NON-NLS-1$
							new Object[] {
									getConnectionFactoryProvider().getName(),
									getConnectionProfile().getName(),
									e.getMessage()}), e);
		}
		monitor.done();
		return status;
	}

	public boolean belongsTo(Object family) {
		return mFamily != null && family == mFamily;
	}

}
