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
package org.eclipse.datatools.connectivity.internal;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactoryProvider;
import org.eclipse.datatools.connectivity.IConnectionProfile;

public class CreateConnectionJob extends Job {

	private IConnectionFactoryProvider mFactory;
	private IConnectionProfile mProfile;
	private Object mFamily;
	private IConnection mConnection;

	public CreateConnectionJob(IConnectionFactoryProvider factory,
								IConnectionProfile profile, Object family) {
		super(ConnectivityPlugin.getDefault().getResourceString(
				"CreateConnectionJob.name", //$NON-NLS-1$
				new Object[] { factory.getName(), profile.getName()}));
		setUser(true);
		mFactory = factory;
		mProfile = profile;
		mFamily = family;
	}

	public IConnectionFactoryProvider getConnectionFactoryProvider() {
		return mFactory;
	}

	public IConnectionProfile getConnectionProfile() {
		return mProfile;
	}

	public IConnection getConnection() {
		return mConnection;
	}

	protected IStatus run(IProgressMonitor monitor) {
		IStatus status = Status.OK_STATUS;
		monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);
		try {
			mConnection = mFactory.createConnection(mProfile);
			if (mConnection.getConnectException() != null) {
				status = new Status(IStatus.ERROR, ConnectivityPlugin
						.getDefault().getBundle().getSymbolicName(), -1,
						ConnectivityPlugin.getDefault().getResourceString(
								"CreateConnectionJob.error", //$NON-NLS-1$
								new Object[] {
										getConnectionFactoryProvider()
												.getName(),
										getConnectionProfile().getName(),
										mConnection.getConnectException()
												.getMessage()}), mConnection
								.getConnectException());
			}
		}
		catch (Exception e) {
			status = new Status(IStatus.ERROR, ConnectivityPlugin.getDefault()
					.getBundle().getSymbolicName(), -1, ConnectivityPlugin
					.getDefault().getResourceString(
							"CreateConnectionJob.error", //$NON-NLS-1$
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
