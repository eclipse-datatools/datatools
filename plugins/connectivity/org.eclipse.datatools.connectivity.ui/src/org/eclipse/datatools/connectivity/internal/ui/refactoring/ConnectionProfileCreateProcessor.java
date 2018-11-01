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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.CreateArguments;
import org.eclipse.ltk.core.refactoring.participants.ParticipantManager;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;
import org.eclipse.swt.widgets.Shell;

/**
 * Uses the create profile change to create an undoable/redoable create.
 * Note that due to some weirdness in the refactoring undo/redo code in
 * Eclipse, creates and deletes, unless part of some other action, can
 * not be undone/redone. 
 * 
 * @author brianf
 *
 */
public class ConnectionProfileCreateProcessor extends RefactoringProcessor {

	public static final String PROCESSOR_ID = "org.eclipse.datatools.connectivity.ui.createProcessor"; //$NON-NLS-1$

	private String mNewProfileName;
	private String mNewProfileDescription;
	private String mNewProfileProviderID;
	private Properties mNewProfileProperties;
	private String mNewProfileParentProfile;
	private boolean mNewProfileAutoConnect;
	private Shell mShell;

	/**
	 * @param name
	 * @param description
	 * @param providerID
	 * @param baseProperties
	 * @param parentProfile
	 * @param autoConnect
	 * @param shell
	 */
	public ConnectionProfileCreateProcessor(String name, String description,
			String providerID, Properties baseProperties, String parentProfile,
			boolean autoConnect, Shell shell)
	{
		super();
		mNewProfileName = name;
		mNewProfileDescription = description;
		mNewProfileProviderID = providerID;
		mNewProfileProperties = baseProperties;
		mNewProfileParentProfile = parentProfile;
		mNewProfileAutoConnect = autoConnect;
		mShell = shell;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getElements()
	 */
	public Object[] getElements()
	{
		return new Object[] {mNewProfileName};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getIdentifier()
	 */
	public String getIdentifier()
	{
		return PROCESSOR_ID;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getProcessorName()
	 */
	public String getProcessorName()
	{
		return MessageFormat.format(ConnectivityUIPlugin.getDefault().getResourceString("CPCreateChange.name"), //$NON-NLS-1$
				new Object[] { mNewProfileName });
//		return ConnectivityUIPlugin.getDefault().getResourceString("CPCreateProcessor.name");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#isApplicable()
	 */
	public boolean isApplicable() throws CoreException
	{
		// Do some checking to make sure we can rename these profiles.
		// We should always be able to rename any profile
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public RefactoringStatus checkInitialConditions(IProgressMonitor pm) throws CoreException, OperationCanceledException
	{
		RefactoringStatus result = new RefactoringStatus();
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
	 */
	public RefactoringStatus checkFinalConditions(IProgressMonitor pm, CheckConditionsContext context) throws CoreException, OperationCanceledException
	{
		RefactoringStatus result = new RefactoringStatus();

		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException
	{
		CompositeChange change = new CompositeChange(new String());
		change.markAsSynthetic();
		Change createchange = new ConnectionProfileCreateChange(
				mNewProfileName,
				mNewProfileDescription, 
				mNewProfileProviderID,
				mNewProfileProperties, 
				mNewProfileParentProfile,
				mNewProfileAutoConnect,
				mShell);
		change.add(createchange);

		return change;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#loadParticipants(org.eclipse.ltk.core.refactoring.RefactoringStatus, org.eclipse.ltk.core.refactoring.participants.SharableParticipants)
	 */
	public RefactoringParticipant[] loadParticipants(RefactoringStatus status, SharableParticipants sharedParticipants) throws CoreException
	{
		List participants = new ArrayList();
		participants.addAll(Arrays.asList(ParticipantManager.loadCreateParticipants(status, this, mNewProfileName,
					new CreateArguments(), new String[0], sharedParticipants)));

		return (RefactoringParticipant[]) participants.toArray(new RefactoringParticipant[participants.size()]);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.PlatformObject#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter)
	{
		//if (adapter.isAssignableFrom(ReorgExecutionLog.class))
		//{
		//	return mArguments.getDestination();
		//}
		return super.getAdapter(adapter);
	}
}
