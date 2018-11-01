/*******************************************************************************
 * Copyright (c) 2006-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.refactoring;

import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CopyArguments;
import org.eclipse.ltk.core.refactoring.participants.DeleteArguments;
import org.eclipse.ltk.core.refactoring.participants.ReorgExecutionLog;

/**
 * Duplicates a profile and adds an undo action (delete duplicate).
 * @author brianf
 *
 */
public class ConnectionProfileCopyChange extends Change {

	private IConnectionProfile mProfileToDuplicate;
	private IConnectionProfile mNewProfile;
	private CopyArguments mArguments;
	
	/**
	 * @param profile
	 * @param args
	 */
	public ConnectionProfileCopyChange(IConnectionProfile profile, CopyArguments arguments)
	{
		super();
		mProfileToDuplicate = profile;
		mArguments = arguments;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	public String getName()
	{
		return MessageFormat.format(ConnectivityUIPlugin.getDefault().getResourceString("CPCopyChange.name"), //$NON-NLS-1$
				new Object[] { mProfileToDuplicate.getName()});
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#initializeValidationData(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void initializeValidationData(IProgressMonitor pm)
	{
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#isValid(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException, OperationCanceledException
	{
		RefactoringStatus result = new RefactoringStatus();

		// Make sure the element exists
		if (mProfileToDuplicate == null)
		{
			result.addFatalError(ConnectivityUIPlugin.getDefault().getResourceString("CPCopyChange.error.ProfileDoesNotExist")); //$NON-NLS-1$
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public Change perform(IProgressMonitor pm) throws CoreException
	{
		CompositeChange undo = new CompositeChange(getName());
		undo.markAsSynthetic();
		undo.add(copyProfile(pm));
		return undo;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getModifiedElement()
	 */
	public Object getModifiedElement()
	{
		// RJC: I think this should be the underlying repository
		// return mArguments.getDestination();
		return mProfileToDuplicate;
	}

	public Object[] getAffectedObjects() {
		return new Object[] { mArguments.getDestination()};
	}

	public Object getAdapter(Class adapter)
	{
		if (adapter.isAssignableFrom(ReorgExecutionLog.class))
		{
			return mArguments.getExecutionLog();
		}
		return super.getAdapter(adapter);
	}
	
	/**
	 * @param pm
	 * @return
	 */
	private Change copyProfile(IProgressMonitor pm) throws CoreException {
		try {
			Object destination = mArguments.getDestination();
			IConnectionProfile repo = destination instanceof IConnectionProfile ? (IConnectionProfile) destination
					: null;
			mNewProfile = InternalProfileManager.getInstance().copyProfile(
					mProfileToDuplicate,
					repo,
					mArguments.getExecutionLog()
							.getNewName(mProfileToDuplicate));
			return new ConnectionProfileBaseDeleteChange(mNewProfile,
					new DeleteArguments());
		}
		catch (ConnectionProfileException e) {
			Status status = new Status(Status.ERROR, ConnectivityUIPlugin
					.getDefault().getBundle().getSymbolicName(), -1, e
					.getMessage(), e);
			throw new CoreException(status);
		}
	}

}
