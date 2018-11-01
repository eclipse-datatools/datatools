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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.DeleteArguments;
import org.eclipse.ltk.core.refactoring.participants.DeleteProcessor;
import org.eclipse.ltk.core.refactoring.participants.ParticipantManager;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;

/**
 * Uses the delete profile change to create an undoable/redoable delete.
 * Note that due to some weirdness in the refactoring undo/redo code in
 * Eclipse, creates and deletes, unless part of some other action, can
 * not be undone/redone.
 *  
 * @author brianf
 *
 */
public class ConnectionProfileDeleteProcessor extends DeleteProcessor {

	public static final String PROCESSOR_ID = "org.eclipse.datatools.connectivity.ui.deleteProcessor"; //$NON-NLS-1$
	private IConnectionProfile mProfile;

	/**
	 * @param profile
	 */
	public ConnectionProfileDeleteProcessor(IConnectionProfile profile)
	{
		super();
		mProfile = profile;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getElements()
	 */
	public Object[] getElements()
	{
		return new Object[] {mProfile};
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
		return MessageFormat.format(ConnectivityUIPlugin.getDefault().getResourceString("CPDeleteChange.name"), //$NON-NLS-1$
				new Object[] { mProfile.getName()});
//		return ConnectivityUIPlugin.getDefault().getResourceString("CPDeleteProcessor.name");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#isApplicable()
	 */
	public boolean isApplicable() throws CoreException
	{
		// Do some checking to make sure we can delete these profiles.
		// We should always be able to delete any profile
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
//		CompositeChange change = new CompositeChange(new String());
//		change.markAsSynthetic();
//		change.add(new ConnectionProfileDeleteChange(mProfile, new DeleteArguments()));
//
//		return change;
		return new ConnectionProfileDeleteChange(mProfile, new DeleteArguments());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#loadParticipants(org.eclipse.ltk.core.refactoring.RefactoringStatus, org.eclipse.ltk.core.refactoring.participants.SharableParticipants)
	 */
	public RefactoringParticipant[] loadParticipants(RefactoringStatus status, SharableParticipants sharedParticipants) throws CoreException
	{
		List participants = new ArrayList();
		participants.addAll(Arrays.asList(ParticipantManager.loadDeleteParticipants(status, this, mProfile,
					new DeleteArguments(), new String[0], sharedParticipants)));

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
