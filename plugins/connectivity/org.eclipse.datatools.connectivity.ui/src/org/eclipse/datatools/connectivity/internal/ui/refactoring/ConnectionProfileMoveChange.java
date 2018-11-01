/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.refactoring;

import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.MoveArguments;

public class ConnectionProfileMoveChange extends Change {

	private IConnectionProfile mProfileToMove;
	private IConnectionProfile mSourceRepository;
	private MoveArguments mArguments;

	public ConnectionProfileMoveChange(IConnectionProfile profile,
										MoveArguments arguments) {
		super();
		mProfileToMove = profile;
		mSourceRepository = profile.getParentProfile();
		mArguments = arguments;

	}

	public Object getModifiedElement() {
		return new Object[] { mArguments.getDestination()};
	}

	public Object[] getAffectedObjects() {
		Object o = null;
		if (mSourceRepository == null) {
			o = ProfileManager.getInstance();
		} else {
			o = mSourceRepository;
		}
		return new Object[] { o };
	}

	public String getName() {
		return MessageFormat.format(ConnectivityUIPlugin.getDefault()
				.getResourceString("CPMoveChange.name"), //$NON-NLS-1$
				new Object[] { mProfileToMove.getName()});
	}

	public void initializeValidationData(IProgressMonitor pm) {
	}

	public RefactoringStatus isValid(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		RefactoringStatus result = new RefactoringStatus();

		// Make sure the element exists
		if (mProfileToMove == null) {
			result
					.addFatalError(ConnectivityUIPlugin.getDefault()
							.getResourceString(
									"CPMoveChange.error.ProfileDoesNotExist")); //$NON-NLS-1$
		}

		return result;
	}

	public Change perform(IProgressMonitor pm) throws CoreException {
		CompositeChange undo = new CompositeChange(getName());
		undo.markAsSynthetic();
		undo.add(moveProfile());
		return undo;
	}

	private Change moveProfile() throws CoreException {
		IConnectionProfile oldParent = mProfileToMove.getParentProfile();
		IConnectionProfileRepository oldRepo = ((ConnectionProfile) mProfileToMove)
				.getRepository();
		IConnectionProfileRepository newRepo = getTargetRepository();

		try {
			// Remove it from the old repository
			if (oldRepo == null) {
				InternalProfileManager.getInstance().removeProfile(
						mProfileToMove);
			}
			else {
				oldRepo.removeProfile(mProfileToMove);
			}

			try {
				// Add it to the new repository
				if (newRepo == null) {
					InternalProfileManager.getInstance().addProfile(
							mProfileToMove);
				}
				else {
					newRepo.addProfile(mProfileToMove);
				}
			}
			catch (ConnectionProfileException e) {
				// Try to put it back where it was
				if (oldRepo == null) {
					InternalProfileManager.getInstance().addProfile(
							mProfileToMove);
				}
				else {
					oldRepo.addProfile(mProfileToMove);
				}
				throw e;
			}
		}
		catch (ConnectionProfileException e) {
			Status status = new Status(Status.ERROR, ConnectivityUIPlugin
					.getDefault().getBundle().getSymbolicName(), -1, e
					.getMessage(), e);
			throw new CoreException(status);
		}
		Object o = null;
		if (oldParent == null) {
			o = ProfileManager.getInstance();
		} else {
			o = oldParent;
		}
		return new ConnectionProfileMoveChange(mProfileToMove,
				new MoveArguments(o, mArguments
						.getUpdateReferences()));
	}

	private IConnectionProfileRepository getTargetRepository()
			throws CoreException {
		Object destination = mArguments.getDestination();
		if (destination instanceof ProfileManager) {
			return null;
		}
		else if (destination instanceof IConnectionProfile) {
			IManagedConnection imc = ((IConnectionProfile) destination)
					.getManagedConnection(IConnectionProfileRepository.class
							.getName());
			if (imc == null) {
				// invalid profile
				Status status = new Status(Status.ERROR, ConnectivityUIPlugin
						.getDefault().getBundle().getSymbolicName(), -1,
						ConnectivityUIPlugin.getDefault().getResourceString(
								"CPCopyChange.error.InvalidTarget"), null); //$NON-NLS-1$
				throw new CoreException(status);
			}
			else if (!imc.isConnected() || imc.getConnection() == null
					|| imc.getConnection().getRawConnection() == null) {
				// repository must be connected
				Status status = new Status(
						Status.ERROR,
						ConnectivityUIPlugin.getDefault().getBundle()
								.getSymbolicName(),
						-1,
						ConnectivityUIPlugin
								.getDefault()
								.getResourceString(
										"CPCopyChange.error.RepositoryNotConnected", //$NON-NLS-1$
										new Object[] { ((IConnectionProfile) destination)
												.getName()}), null);
				throw new CoreException(status);
			}
			return (IConnectionProfileRepository) imc.getConnection()
					.getRawConnection();
		}
		// unsupported type
		Status status = new Status(Status.ERROR, ConnectivityUIPlugin
				.getDefault().getBundle().getSymbolicName(), -1,
				ConnectivityUIPlugin.getDefault().getResourceString(
						"CPCopyChange.error.InvalidTarget"), null); //$NON-NLS-1$
		throw new CoreException(status);
	}

}
