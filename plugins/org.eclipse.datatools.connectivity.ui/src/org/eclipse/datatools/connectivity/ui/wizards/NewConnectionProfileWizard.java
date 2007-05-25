/*******************************************************************************
 * Copyright (c) 2005-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import java.util.Properties;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.SharedImages;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.internal.ui.refactoring.ConnectionProfileCreateChange;
import org.eclipse.datatools.connectivity.internal.ui.wizards.BaseWizard;
import org.eclipse.datatools.connectivity.internal.ui.wizards.SummaryWizardPage;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ltk.core.refactoring.PerformChangeOperation;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;

/**
 * Base connection profile wizard
 * 
 * @author shongxum
 */
public abstract class NewConnectionProfileWizard extends BaseWizard implements
		INewWizard, ICPWizard, IContextProvider {

	protected NewConnectionProfileWizardPage mProfilePage;
	protected SummaryWizardPage mSummaryPage;
	protected String mProviderID;
	protected IConnectionProfile mParentProfile;

	public NewConnectionProfileWizard() {
		setWindowTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizard.title")); //$NON-NLS-1$
		setDefaultPageImageDescriptor(SharedImages.DESC_WIZBAN);
	}

	/**
	 * Clients need to make "return super.performFinish" as the last line of
	 * their code if they want to override this method
	 * 
	 * @see Wizard#performFinish
	 */
	public boolean performFinish() {
		try {
			doFinish();
//			ProfileManager.getInstance().createProfile(
//					mProfilePage.getProfileName(),
//					mProfilePage.getProfileDescription(), mProviderID,
//					getProfileProperties(), getParentProfile(),
//					mProfilePage.getAutoConnect());
//		} catch (ConnectionProfileException e) {
//			ExceptionHandler.showException(getShell(), ConnectivityUIPlugin
//					.getDefault().getResourceString(
//							"NewConnectionProfileWizard.create.failure"), e //$NON-NLS-1$
//					.getLocalizedMessage(), e);
//			return false;
		} catch (CoreException e) {
			ExceptionHandler.showException(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString(
							"NewConnectionProfileWizard.create.failure"), e //$NON-NLS-1$
					.getLocalizedMessage(), e);
			return false;
		}
		return true;
	}
	
	private void doFinish() throws CoreException {
    	// Refactor for finish
		IConnectionProfile repo = mProfilePage.getRepository();
		PerformChangeOperation refOperation = new PerformChangeOperation(
				new ConnectionProfileCreateChange(
						mProfilePage.getProfileName(), mProfilePage
								.getProfileDescription(), mProviderID,
						getProfileProperties(), repo == null ? null : repo
								.getName(), mProfilePage.getAutoConnect(),
						getShell()));
    	try 
    	{
    		ResourcesPlugin.getWorkspace().run(refOperation, null);
    	}
    	catch (OperationCanceledException oce) 
    	{
    		throw new OperationCanceledException();			
    	}
    	catch (CoreException ce) 
    	{
    		throw ce;
    	}	
    	
        // get the view part of DSE.
		IWorkbenchPart part = PlatformUI
				.getWorkbench()
				.getActiveWorkbenchWindow()
				.getActivePage()
				.findView(
						"org.eclipse.datatools.connectivity.DataSourceExplorerNavigator");//$NON-NLS-1$
		
    	// select the newly created CP in DSE.
		if (part != null) {
			final ISelection targetSelection = new StructuredSelection(
					ProfileManager.getInstance().getProfileByName(
							getProfileName()));
			ISetSelectionTarget target = null;
			if (part instanceof ISetSelectionTarget) {
				target = (ISetSelectionTarget) part;
			} else {
				target = (ISetSelectionTarget) part
						.getAdapter(ISetSelectionTarget.class);
			}

			if (target != null) {
				final ISetSelectionTarget finalTarget = target;
				getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						finalTarget.selectReveal(targetSelection);
					}
				});
			}
		}
	}

	/**
	 * @see Wizard#init
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// Do nothing
	}

	/**
	 * @see org.eclipse.datatools.connectivity.ui.wizards.ICPWizard#setProviderID(java.lang.String)
	 */
	public void initProviderID(String providerID) {
		mProviderID = providerID;
	}

	/**
	 * Clients needs to add their customized pages here
	 */
	public abstract void addCustomPages();

	public String getProfileProviderID() {
		return mProviderID;
	}

	public String getProfileName() {
		return mProfilePage.getProfileName();
	}

	public String getProfileDescription() {
		return mProfilePage.getProfileDescription();
	}

	public boolean getProfileIsAutoConnect() {
		return mProfilePage.getAutoConnect();
	}

	/**
	 * Clients need provide implementation to get extra profile properties
	 * 
	 * @return
	 */
	public abstract Properties getProfileProperties();

	/**
	 * Clients may override this method to return the real parent profile
	 * 
	 * @return
	 */
	public String getParentProfile() {
		return mParentProfile == null ? new String() : mParentProfile.getName();
	}

	public void setParentProfile(IConnectionProfile profile) {
		mParentProfile = profile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		super.addPages();

		mProfilePage = new NewConnectionProfileWizardPage();
		addPage(mProfilePage);

		addCustomPages();

		mSummaryPage = new SummaryWizardPage(this);
		addPage(mSummaryPage);
	}

	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp(getShell(), HelpUtil.getContextId(
				IHelpConstants.CONTEXT_ID_NEW_CONNECTION_PROFILE_WIZARD,
				ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));
	}

	private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(
			ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}
}