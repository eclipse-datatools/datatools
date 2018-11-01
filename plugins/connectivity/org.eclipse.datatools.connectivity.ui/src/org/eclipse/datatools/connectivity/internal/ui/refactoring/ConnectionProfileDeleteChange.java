/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
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
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.NullChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.DeleteArguments;
import org.eclipse.swt.widgets.Shell;

/**
 * Deletes a profile and adds an undo action (create).
 * @author brianf
 *
 */
public class ConnectionProfileDeleteChange extends Change {

	private IConnectionProfile mProfileToDelete;

	/**
	 * @param profile
	 * @param args
	 */
	public ConnectionProfileDeleteChange(IConnectionProfile profile, DeleteArguments args)
	{
		super();
		mProfileToDelete = profile;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	public String getName()
	{
		return MessageFormat.format(ConnectivityUIPlugin.getDefault().getResourceString("CPDeleteChange.name"), //$NON-NLS-1$
				new Object[] { mProfileToDelete.getName()});
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
		if (mProfileToDelete == null) // || ProfileManager.getInstance().getProfileByName(mProfileToDelete.getName()) == null)
		{
			result.addFatalError(ConnectivityUIPlugin.getDefault().getResourceString("CPDeleteChange.error.ProfileDoesNotExist")); //$NON-NLS-1$
		}
		else {
			String path = ProfileManager.getInstance().getProfilePath(mProfileToDelete);
			if (ProfileManager.getInstance().getProfileByFullPath(path) == null) {
				result.addFatalError(ConnectivityUIPlugin.getDefault().getResourceString("CPDeleteChange.error.ProfileDoesNotExist")); //$NON-NLS-1$
			}
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
		undo.add(deleteProfile(pm));
		return undo;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getModifiedElement()
	 */
	public Object getModifiedElement()
	{
		return mProfileToDelete;
	}

	/**
	 * @param pm
	 * @return
	 */
	private Change deleteProfile(IProgressMonitor pm) {
		Shell shell = new Shell();
		try {
			ProfileManager.getInstance().deleteProfile(mProfileToDelete);
		} catch (ConnectionProfileException e) {
			ExceptionHandler.showException(shell, ConnectivityUIPlugin
					.getDefault().getResourceString(
							"CPDeleteChange.delete.failure"), e //$NON-NLS-1$
					.getLocalizedMessage(), e);
			return new NullChange();
		}
		return new ConnectionProfileCreateChange(
				mProfileToDelete,
				shell);
	}
}