/*******************************************************************************
 * Copyright (c) 2006, 2011 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class CloseManagedConnectionJob extends Job {

	private Object mFamily;
	private ManagedConnection mConnection;

	public CloseManagedConnectionJob(ManagedConnection connection, Object family) {
		super(ConnectivityPlugin.getDefault()
				.getResourceString(
						"CloseManagedConnectionJob.name", //$NON-NLS-1$
						new Object[] {
								connection.getConnectionProfile().getProvider()
										.getConnectionFactory(
												connection.getFactoryID())
										.getName(),
								connection.getConnectionProfile().getName()}));
		setUser(true);
		mConnection = connection;
		mFamily = family;
	}

	protected IStatus run(IProgressMonitor monitor) {
		IStatus status = Status.OK_STATUS;
		monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);
		try {
			mConnection.close();
		}
		catch (Exception e) {
			status = new Status(IStatus.ERROR, 
			        ConnectivityPlugin.getSymbolicName(), -1, 
			        ConnectivityPlugin.getDefault().getResourceString(
							"CloseManagedConnectionJob.error", //$NON-NLS-1$
							new Object[] {
									mConnection.getConnectionProfile()
											.getProvider()
											.getConnectionFactory(
													mConnection.getFactoryID())
											.getName(),
									mConnection.getConnectionProfile()
											.getName(), e.getMessage()}), e);
		}
		monitor.done();
		return status;
	}

	public boolean belongsTo(Object family) {
		return mFamily != null && family == mFamily;
	}

}
