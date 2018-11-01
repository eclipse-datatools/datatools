/*******************************************************************************
 * Copyright (c) 2005, 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: 
 *  rcernich - initial API and implementation
 *  Actuate Corporation - refactored to improve extensibility
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.UIJob;

/**
 * Executes a ping operation as a background job.
 */
public class PingJob extends Job {

	private IConnectionProfile icp;
	private Shell shell;

	/**
	 * @param exceptions
	 * @param name
	 */
	public PingJob(Shell shell, IConnectionProfile profile) {
		super(ConnectivityUIPlugin.getDefault().getResourceString(
				"actions.ping.job")); //$NON-NLS-1$
		setSystem(false);
		setUser(true);
		this.shell = shell;
		icp = profile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask(ConnectivityUIPlugin.getDefault().getResourceString(
				"actions.ping.title"), //$NON-NLS-1$
				IProgressMonitor.UNKNOWN);

		IConnection con = createTestConnection( icp );

		monitor.done();

		new PingUIJob( shell, getTestConnectionException( con ) )
				.schedule();
		
		if( con != null )
		    con.close();
		
		return Status.OK_STATUS;
	}

	public static IConnection createTestConnection( IConnectionProfile icp )
	{
	    if( icp == null )
	        return null;
	    return icp.createConnection( ConnectionProfileConstants.PING_FACTORY_ID );
	}
	
	public static Throwable getTestConnectionException( IConnection conn )
	{
	    return conn != null ? conn.getConnectException()
                : new RuntimeException( ConnectivityUIPlugin.getDefault()
                        .getResourceString( "actions.ping.failure" )); //$NON-NLS-1$
	}
	
	public static class PingUIJob extends UIJob {

		private Shell shell;
		private Throwable exception;

		/**
		 * @param name
		 */
		public PingUIJob(Shell shell, Throwable exception) {
			super(ConnectivityUIPlugin.getDefault().getResourceString(
					"actions.ping.uijob")); //$NON-NLS-1$
			setSystem(false);
			this.exception = exception;
			this.shell = shell;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.progress.UIJob#runInUIThread(org.eclipse.core.runtime.IProgressMonitor)
		 */
		public IStatus runInUIThread(IProgressMonitor monitor) {
		    showTestConnectionMessage( shell, exception );
			return Status.OK_STATUS;
		}
		
	    public static void showTestConnectionMessage( Shell shell, Throwable exception )
	    {
	        if (exception == null) {
	            MessageDialog.openInformation(shell,
	                    ConnectivityUIPlugin.getDefault().getResourceString(
	                            "dialog.title.success"), //$NON-NLS-1$
	                    ConnectivityUIPlugin.getDefault().getResourceString(
	                            "actions.ping.success")); //$NON-NLS-1$
	        }
	        else {
	            ExceptionHandler.showException(shell, ConnectivityUIPlugin
	                    .getDefault().getResourceString("dialog.title.error"), //$NON-NLS-1$
	                    ConnectivityUIPlugin.getDefault().getResourceString(
	                            "actions.ping.failure"), //$NON-NLS-1$
	                    exception);
	        }
	    }
	    
	}
	
}
