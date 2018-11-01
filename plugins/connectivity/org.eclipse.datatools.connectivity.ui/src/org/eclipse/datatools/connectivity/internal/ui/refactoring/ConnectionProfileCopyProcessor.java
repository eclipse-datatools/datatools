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
import org.eclipse.datatools.connectivity.internal.ConnectivityPlugin;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.CopyArguments;
import org.eclipse.ltk.core.refactoring.participants.CopyProcessor;
import org.eclipse.ltk.core.refactoring.participants.ParticipantManager;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.ReorgExecutionLog;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;
import org.eclipse.osgi.util.TextProcessor;

/**
 * Uses the Duplicate change to perform a duplicate that can be undone/redone.
 * 
 * @author brianf
 * 
 */
public class ConnectionProfileCopyProcessor extends CopyProcessor {

	public static final String PROCESSOR_ID = "org.eclipse.datatools.connectivity.ui.copyProcessor"; //$NON-NLS-1$
	private IConnectionProfile[] mProfiles;
	private List mProfilesToCopy;
	private CopyArguments mArguments;

	/**
	 * @param profile
	 */
	public ConnectionProfileCopyProcessor(IConnectionProfile[] profiles,
											Object destination) {
		super();
		mProfiles = profiles;
		mProfilesToCopy = new ArrayList();
		mArguments = new CopyArguments(destination, new ReorgExecutionLog());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getElements()
	 */
	public Object[] getElements() {
		return mProfiles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getIdentifier()
	 */
	public String getIdentifier() {
		return PROCESSOR_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getProcessorName()
	 */
	public String getProcessorName() {
		return ConnectivityUIPlugin.getDefault().getResourceString(
				"CPDuplicateProcessor.name"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#isApplicable()
	 */
	public boolean isApplicable() throws CoreException {
		// Do some checking to make sure we can rename these profiles.
		// We should always be able to rename any profile
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public RefactoringStatus checkInitialConditions(IProgressMonitor pm)
			throws CoreException, OperationCanceledException {
		RefactoringStatus result = new RefactoringStatus();
		// verify status of target repository
		getTargetRepository();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor,
	 *      org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
	 */
	public RefactoringStatus checkFinalConditions(IProgressMonitor pm,
			CheckConditionsContext context) throws CoreException,
			OperationCanceledException {
		RefactoringStatus result = new RefactoringStatus();

		ReorgExecutionLog reorgLog = mArguments.getExecutionLog();

		// Find target repository
		IConnectionProfileRepository repo = getTargetRepository();

		// RJC: TODO: we should really prompt the user for the new name. i.e.
		// behavior should be similar to copying a resource in the workspace
		// Calculate new names for copied objects
		for (int index = 0, count = mProfiles.length; index < count; ++index) {
			// Verify that the profile is compatible with the repository
			if (repo != null
					&& (repo.isReadOnly()
							|| !repo.supportsProfileType(mProfiles[index]
									.getProviderId()) || !((ConnectionProfileProvider) mProfiles[index]
							.getProvider()).compatibleWithRepository(repo
							.getRepositoryProfile()))) {
				continue;
			}

			// calculate new name
			String newName = calculateNewName(mProfiles[index], repo);

			// set name in the copy args
			reorgLog.setNewName(mProfiles[index], TextProcessor.process(newName));

			mProfilesToCopy.add(mProfiles[index]);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public Change createChange(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		CompositeChange change = new CompositeChange(new String());
		change.markAsSynthetic();

		for (Iterator it = mProfilesToCopy.iterator(); it.hasNext();) {
			change.add(new ConnectionProfileCopyChange((IConnectionProfile) it
					.next(), mArguments));
		}

		return change;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#loadParticipants(org.eclipse.ltk.core.refactoring.RefactoringStatus,
	 *      org.eclipse.ltk.core.refactoring.participants.SharableParticipants)
	 */
	public RefactoringParticipant[] loadParticipants(RefactoringStatus status,
			SharableParticipants sharedParticipants) throws CoreException {
		List participants = new ArrayList();
		for (int index = 0, count = mProfiles.length; index < count; ++index) {
			participants.addAll(Arrays.asList(ParticipantManager
					.loadCopyParticipants(status, this, mProfiles[index],
							mArguments, new String[0], sharedParticipants)));
		}

		return (RefactoringParticipant[]) participants
				.toArray(new RefactoringParticipant[participants.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.PlatformObject#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		if (adapter.isAssignableFrom(ReorgExecutionLog.class)) {
			return mArguments.getExecutionLog();
		}
		return super.getAdapter(adapter);
	}

	private String calculateNewName(IConnectionProfile profile,
			IConnectionProfileRepository repo) {
		String newName = profile.getName();
		String profileName = profile.getName();
		if (repo == null) {
			InternalProfileManager ipm = InternalProfileManager.getInstance();
			int i = 0;
			while (ipm.getProfileByName(newName,false) != null) {
				newName = ConnectivityPlugin.getDefault().getResourceString(
						"duplicate.profile.name", //$NON-NLS-1$
						new Object[] { profileName, new Integer(i)});
				i++;
			}
		}
		else {
			int i = 0;
			while (repo.getProfileByName(newName) != null) {
				newName = ConnectivityPlugin.getDefault().getResourceString(
						"duplicate.profile.name", //$NON-NLS-1$
						new Object[] { profileName, new Integer(i)});
				i++;
			}
		}
		return newName;
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
