/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.ltk.core.refactoring.participants.CopyArguments;
import org.eclipse.ltk.core.refactoring.participants.DeleteArguments;
import org.eclipse.swt.widgets.Shell;

/**
 * Duplicates a profile and adds an undo action (delete duplicate).
 * @author brianf
 *
 */
public class ConnectionProfileCopyChange extends Change {

	private IConnectionProfile mProfileToDuplicate;

	/**
	 * @param profile
	 * @param args
	 */
	public ConnectionProfileCopyChange(IConnectionProfile profile, CopyArguments args)
	{
		super();
		mProfileToDuplicate = profile;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	public String getName()
	{
		return MessageFormat.format(ConnectivityUIPlugin.getDefault().getResourceString("CPCopyChange.name"),
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
		if (mProfileToDuplicate == null || ProfileManager.getInstance().getProfileByName(mProfileToDuplicate.getName()) == null)
		{
			result.addFatalError(ConnectivityUIPlugin.getDefault().getResourceString("CPCopyChange.error.ProfileDoesNotExist"));
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
		undo.add(duplicateProfile(pm));
		return undo;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getModifiedElement()
	 */
	public Object getModifiedElement()
	{
		return mProfileToDuplicate;
	}

	/**
	 * @param pm
	 * @return
	 */
	private Change duplicateProfile(IProgressMonitor pm) {
		try {
			String newProfile = ProfileManager.getInstance().duplicateProfile(
					mProfileToDuplicate);
			IConnectionProfile profile = ProfileManager.getInstance()
					.getProfileByName(newProfile);
			if (profile.isAutoConnect())
				profile.connect();
			
			return new ConnectionProfileBaseDeleteChange(profile, new DeleteArguments());
		}
		catch (ConnectionProfileException e) {
			Shell shell = new Shell();
			ExceptionHandler.showException(shell, ConnectivityUIPlugin
					.getDefault().getResourceString("CPCopyChange.title.error"), e //$NON-NLS-1$
					.getMessage(), e);
			return new NullChange();
		}
	}

}
