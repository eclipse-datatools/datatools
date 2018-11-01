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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileProvider;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.MoveArguments;
import org.eclipse.ltk.core.refactoring.participants.MoveProcessor;
import org.eclipse.ltk.core.refactoring.participants.ParticipantManager;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;

/**
 * 
 * @author tqiu
 * 
 * Uses the Move change to perform move operation
 * 
 * 
 */
public class ConnectionProfileMoveProcessor extends MoveProcessor {

	public static final String PROCESSOR_ID = "org.eclipse.datatools.connectivity.ui.moveProcessor"; //$NON-NLS-1$

	private IConnectionProfile[] mProfiles;
	private List mProfilesToMove;
	private MoveArguments mArguments;

	public ConnectionProfileMoveProcessor(IConnectionProfile[] profiles,
											Object destination) {
		super();
		mProfiles = profiles;
		mProfilesToMove = new ArrayList();
		mArguments = new MoveArguments(destination, true);
	}

	public RefactoringStatus checkFinalConditions(IProgressMonitor pm,
			CheckConditionsContext context) throws CoreException,
			OperationCanceledException {
		RefactoringStatus result = new RefactoringStatus();

		// Find target repository
		IConnectionProfileRepository repo = getTargetRepository();

		// RJC: TODO: we should really prompt the user for overwrites. i.e.
		// behavior should be similar to moving a resource in the workspace
		for (int index = 0, count = mProfiles.length; index < count; ++index) {
			// Verify that the profile is compatible with the repository
			if (repo != null) {
				if (repo.isReadOnly()
						|| repo.getProfileByName(mProfiles[index].getName()) != null
						|| !repo.supportsProfileType(mProfiles[index]
								.getProviderId())
						|| !((ConnectionProfileProvider) mProfiles[index]
								.getProvider()).compatibleWithRepository(repo
								.getRepositoryProfile())) {
					continue;
				}
			}
			else if (InternalProfileManager.getInstance().getProfileByName(
					mProfiles[index].getName(), false) != null) {
				continue;
			}

			mProfilesToMove.add(mProfiles[index]);
		}

		return result;
	}

	public RefactoringStatus checkInitialConditions(IProgressMonitor pm)
			throws CoreException, OperationCanceledException {
		RefactoringStatus result = new RefactoringStatus();
		return result;
	}

	public Change createChange(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		CompositeChange change = new CompositeChange(new String());
		change.markAsSynthetic();

		for (Iterator it = mProfilesToMove.iterator(); it.hasNext();) {
			change.add(new ConnectionProfileMoveChange((IConnectionProfile) it
					.next(), mArguments));
		}

		return change;
	}

	public Object[] getElements() {
		return mProfiles;
	}

	public String getIdentifier() {
		return PROCESSOR_ID;
	}

	public String getProcessorName() {
		return ConnectivityUIPlugin.getDefault().getResourceString(
				"CPMoveProcessor.name"); //$NON-NLS-1$
	}

	public boolean isApplicable() throws CoreException {
		// do some checking if we can move these profiles
		return true;
	}

	public RefactoringParticipant[] loadParticipants(RefactoringStatus status,
			SharableParticipants sharedParticipants) throws CoreException {
		List participants = new ArrayList();
		for (int index = 0, count = mProfiles.length; index < count; ++index) {
			participants.addAll(Arrays.asList(ParticipantManager
					.loadMoveParticipants(status, this, mProfiles[index],
							mArguments, new String[0], sharedParticipants)));
		}

		return (RefactoringParticipant[]) participants
				.toArray(new RefactoringParticipant[participants.size()]);
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
