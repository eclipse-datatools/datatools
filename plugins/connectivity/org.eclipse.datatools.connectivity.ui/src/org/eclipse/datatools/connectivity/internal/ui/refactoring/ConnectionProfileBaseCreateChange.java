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
import java.util.Properties;

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
 * Creates a new profile but does not add an undo action (delete)
 * @author brianf
 *
 */
public class ConnectionProfileBaseCreateChange extends Change {
	
	private String mNewProfileName;
	private String mNewProfileDescription;
	private String mNewProfileProviderID;
	private Properties mNewProfileProperties;
	private String mNewProfileParentProfile;
	private boolean mNewProfileAutoConnect;
	private IConnectionProfile mProfile;
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
	public ConnectionProfileBaseCreateChange(String name, String description,
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
	
	public ConnectionProfileBaseCreateChange ( IConnectionProfile profile, Shell shell) {
		super();
		mProfile = profile;
		mShell = shell;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	public String getName()
	{
		if (mNewProfileName != null)
			return MessageFormat.format(ConnectivityUIPlugin.getDefault().getResourceString("CPCreateChange.name"), //$NON-NLS-1$
					new Object[] { mNewProfileName });
		if (mProfile != null)
			return MessageFormat.format(ConnectivityUIPlugin.getDefault().getResourceString("CPCreateChange.name"), //$NON-NLS-1$
					new Object[] { mProfile.getName() });
		return MessageFormat.format(ConnectivityUIPlugin.getDefault().getResourceString("CPCreateChange.name"), //$NON-NLS-1$
				new Object[] { "" }); //$NON-NLS-1$
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
		
		if (mProfile != null)
			return result;

		// Make sure we have appropriate information
		if (mNewProfileName == null || mNewProfileName.trim().length() == 0)
		{
			result.addFatalError(ConnectivityUIPlugin.getDefault().getResourceString("CPCreateChange.error.NewProfileDoesNotHaveName")); //$NON-NLS-1$
		}
		else if ( ProfileManager.getInstance().getProfileByName(mNewProfileName) != null)
		{
			result.addFatalError(ConnectivityUIPlugin.getDefault().getResourceString("CPCreateChange.error.NewProfileAlreadyExists")); //$NON-NLS-1$
		}
		
		if (mNewProfileProviderID == null || mNewProfileProviderID.trim().length() == 0)
		{
			result.addFatalError(ConnectivityUIPlugin.getDefault().getResourceString("CPCreateChange.error.NewProfileDoesNotHaveProviderID")); //$NON-NLS-1$
		}

		if (mNewProfileProperties == null)
		{
			result.addFatalError(ConnectivityUIPlugin.getDefault().getResourceString("CPCreateChange.error.NewProfileDoesNotHaveNonNullPropertiesObject")); //$NON-NLS-1$
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
		undo.add(createProfile(pm));
		return new NullChange();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getModifiedElement()
	 */
	public Object getModifiedElement()
	{
		return mNewProfileName;
	}

	/**
	 * @param pm
	 * @return
	 */
	private Change createProfile(IProgressMonitor pm) {
		try {
			if (mProfile != null) {
				ProfileManager.getInstance().addProfile(mProfile);
			}
			else {
				if (mNewProfileDescription == null)
					mNewProfileDescription = ""; //$NON-NLS-1$
				if (mNewProfileParentProfile == null)
					mNewProfileParentProfile = ""; //$NON-NLS-1$
				ProfileManager.getInstance().createProfile(
						mNewProfileName,
						mNewProfileDescription, 
						mNewProfileProviderID,
						mNewProfileProperties, 
						mNewProfileParentProfile,
						mNewProfileAutoConnect);
			}
		}
		catch (ConnectionProfileException e) {
			ExceptionHandler.showException(mShell, ConnectivityUIPlugin
					.getDefault().getResourceString(
							"CPCreateChange.create.failure"), e //$NON-NLS-1$
					.getLocalizedMessage(), e);
			return new NullChange();
		}
		
		IConnectionProfile newProfile = null;
		
		if (mNewProfileName != null)
			newProfile = ProfileManager.getInstance().getProfileByName(mNewProfileName);
		else 
			newProfile = ProfileManager.getInstance().getProfileByName(mProfile.getName());
		
		return new ConnectionProfileBaseDeleteChange(newProfile, new DeleteArguments());
	}
}
