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
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;
import org.eclipse.swt.widgets.Shell;

/**
 * Renames a profile and adds an undo action (rename back).
 * @author brianf
 *
 */
public class ConnectionProfileRenameChange extends ConnectionProfileChange {
	
	private RenameArguments mRenameArguments;
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.internal.ui.refactoring.ConnectionProfileChange#getName()
	 */
	public String getName() {
		return MessageFormat.format(ConnectivityUIPlugin.getDefault().getResourceString("CPRenameChange.name"), //$NON-NLS-1$
				new Object[] { mSource.getName()});
	}

	/**
	 * @param source
	 * @param args
	 */
	public ConnectionProfileRenameChange(IConnectionProfile source, RenameArguments args) {
		super(source, null);
		mRenameArguments = args;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#initializeValidationData(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void initializeValidationData(IProgressMonitor pm) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#isValid(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		return new RefactoringStatus();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#perform(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public Change perform(IProgressMonitor pm) throws CoreException {
		CompositeChange undo = new CompositeChange(getName());
		undo.markAsSynthetic();
		undo.add(renameProfile(pm));
		return undo;
	}
	
	/**
	 * @param pm
	 * @return
	 */
	private Change renameProfile(IProgressMonitor pm) {
		Shell shell = new Shell();
		String oldName = mSource.getName();
		RenameArguments oldArgs = new RenameArguments(oldName, true);
		try {
			ProfileManager.getInstance().modifyProfile(mSource, mRenameArguments.getNewName(), mSource.getDescription());
		} catch (ConnectionProfileException e) {
			ExceptionHandler.showException(shell, ConnectivityUIPlugin
					.getDefault().getResourceString(
							"CPRenameChange.title.error"), e //$NON-NLS-1$
					.getLocalizedMessage(), e);
			return new NullChange();
		}
		return new ConnectionProfileRenameChange(mSource, oldArgs);
	}

}
