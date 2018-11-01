/*******************************************************************************
 * Copyright (c) 2005-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: 
 *  shongxum - initial API and implementation
 *  Actuate Corporation - refactored to improve extensibility
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.SharedImages;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.internal.ui.refactoring.ConnectionProfileCreateChange;
import org.eclipse.datatools.connectivity.internal.ui.wizards.BaseWizard;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ISkippable;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ISummaryDataSource;
import org.eclipse.datatools.connectivity.internal.ui.wizards.SummaryWizardPage;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
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
	
    private boolean mSkipProfileNamePage = false;
    private String mProfileName;
    private String mProfileDesc;
	private IFinishTask mFinishTask;
	
	private boolean mIsFinished = false;

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
		if( mIsFinished )
			return true;    // has already completed the finish task
		
	    // if the finish task is delegated, use it to perform finish
	    if( mFinishTask != null ) {
	    	mIsFinished = mFinishTask.performFinish( this );
	    	return mIsFinished;
	    }
	    
	    // no delegation is specified, perform default finish task
		try {
			doFinish();
		} catch (CoreException e) {
			ExceptionHandler.showException(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString(
							"NewConnectionProfileWizard.create.failure"), e //$NON-NLS-1$
					.getLocalizedMessage(), e);
			mIsFinished = false;
			return false;
		}
		mIsFinished = true;
		return true;
	}
	
	public boolean isFinished() {
		return mIsFinished;
	}
	
	private void doFinish() throws CoreException {
    	// Refactor for finish
		IConnectionProfile repo = mProfilePage.getRepository();
		PerformChangeOperation refOperation = new PerformChangeOperation(
				new ConnectionProfileCreateChange(
						mProfilePage.getProfileName(), mProfilePage
								.getProfileDescription(), mProviderID,
						getProfileProperties(), repo == null ? null : repo
								.getInstanceID(), mProfilePage.getAutoConnect(),
						getShell(), mProfilePage.getAutoConnectOnFinish()));
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
    	IWorkbenchPart part = null;
    	if( PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null )
			part = PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.findView(
							"org.eclipse.datatools.connectivity.DataSourceExplorerNavigator");//$NON-NLS-1$
		
    	// select the newly created CP in DSE.
		if (part != null) {
			IConnectionProfile profile = null;
			if (repo != null ) {
				IManagedConnection imc = ((IConnectionProfile) repo)
					.getManagedConnection(IConnectionProfileRepository.class
						.getName());				
				if (imc != null) {
					IConnectionProfileRepository repository = 
						(IConnectionProfileRepository) imc.getConnection().getRawConnection();
					profile = repository.getProfileByName(getProfileName());
				}
			}
			else {
				profile = ProfileManager.getInstance().getProfileByName(
						getProfileName());
			}
			if (profile == null)
				return;
			
			final ISelection targetSelection = new StructuredSelection(
					profile);
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

	public void setProfileNameAndDescription() {
		if( mProfileName != null )
			mProfilePage.setProfileName( mProfileName );
		if( mProfileDesc != null )
			mProfilePage.setProfileDescription( mProfileDesc );
	}

	public List getSummaryData() {
		List data = new ArrayList();
		IWizardPage[] pages = getPages();
		for (int i = 0; i < pages.length; i++) {
			if (pages[i] instanceof ISummaryDataSource) {
				if (pages[i] instanceof ISkippable) {
					ISkippable page = (ISkippable) pages[i];
					if (page instanceof NewConnectionProfileWizardPage){
						// ignore
					}
					else if (page.getSkippable()) {
						continue;
					}
				}
				data.addAll(((ISummaryDataSource) pages[i]).getSummaryData());
			}
		}
		return data;
	}
	
	public String getProfileProviderID() {
		return mProviderID;
	}

	public String getProfileName() {
		return mProfilePage.getProfileName();
	}

	public void setProfileName( String name )
	{
        // cache the value, in case the page is not yet added
	    mProfileName = name;
	    if( mProfilePage != null )
	        mProfilePage.setProfileName( mProfileName );
	}
	
	public void setProfileDescription( String desc )
	{
        // cache the value, in case the page is not yet added
		mProfileDesc = desc;
	    if( mProfilePage != null )
	        mProfilePage.setProfileDescription( mProfileDesc );
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
        if( mProfileName != null )
            mProfilePage.setProfileName( mProfileName );
        if( mSkipProfileNamePage )
            doSkipProfileNamePage( true );

		addCustomPages();

		setProfileNameAndDescription();

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
	
	public void setSkipProfileNamePage( boolean skippable )
	{
	    // cache the latest setting, in case the page is not yet added
	    mSkipProfileNamePage = skippable;	    
	    doSkipProfileNamePage( skippable );
	}
	
	protected void doSkipProfileNamePage( boolean skippable )
	{
        if( mProfilePage == null )
            return;     // page does not exist yet to skip

		mProfilePage.setSkippable( skippable );
	}
	
	public boolean isProfileNamePageSkippable()
	{
	    if( mProfilePage != null )
	        return mProfilePage.getSkippable();
	    return mSkipProfileNamePage;
	}
	
	public void delegatesTask( IFinishTask task )
	{
	    mFinishTask = task;
	}
	
	/**
	 * Delegation of the wizard's performFinish task.
	 */
	public interface IFinishTask
	{
	    public boolean performFinish( NewConnectionProfileWizard delegatingWizard );	    
	}
	
	public NewConnectionProfileWizardPage getProfilePage() {
		return mProfilePage;
	}

}