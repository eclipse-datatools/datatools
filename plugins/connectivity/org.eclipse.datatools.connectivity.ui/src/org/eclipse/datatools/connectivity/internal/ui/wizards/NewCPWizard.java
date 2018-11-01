/*******************************************************************************
 * Copyright (c) 2005, 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 *  Actuate Corporation - refactored to improve extensibility
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.SharedImages;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * @see Wizard
 */
public class NewCPWizard extends BaseWizard implements INewWizard, IContextProvider {

	private CPWizardSelectionPage mProfilePage;

    private ViewerFilter[] mViewerFilters;
	
	private IConnectionProfile mParentProfile;

	/**
	 * 
	 */
	public NewCPWizard() {
		setDefaultPageImageDescriptor(SharedImages.DESC_WIZBAN);
		setWindowTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewCPWizard.title")); //$NON-NLS-1$
	}

	/**
	 * Constructor with a single ViewerFilter and the parent connection profile.
	 * @param filter
	 * @param parentProfile
	 */
	public NewCPWizard(ViewerFilter filter,IConnectionProfile parentProfile) {
        this(parentProfile);
        if ( filter != null )
            mViewerFilters = new ViewerFilter[]{ filter };
	}

	/**
	 * Constructor with an array of ViewerFilter and the parent connection profile.
	 * @param filters  an array of ViewerFilter; may be an empty array, in which case
	 *             the default NewCPWizardCategoryFilter will be used
	 * @param parentProfile
	 * @since DTP 1.6
	 */
	public NewCPWizard(ViewerFilter[] filters,IConnectionProfile parentProfile) {
        this(parentProfile);
        mViewerFilters = filters;
	}

	private NewCPWizard(IConnectionProfile parentProfile) {
        this();
        mParentProfile = parentProfile;
	}
	
	/**
	 * @see Wizard#performFinish
	 */
	public boolean performFinish() {
		if (mProfilePage != null && !mProfilePage.getControl().isDisposed()) {
			IWizardNode selectedNode = mProfilePage.getSelectedNode();
			if (selectedNode == null)
				return false;
			
			IWizard wizard = selectedNode.getWizard();

			if (wizard == null) {
				return false;
			}
			else if (wizard.canFinish())
				return wizard.performFinish();
		}
		// mStore.setValue(DONNT_SHOW_INRO, mIntroPage.isHideIntro());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		super.addPages();

		mProfilePage = new CPWizardSelectionPage(CPWizardSelectionPage.class
				.getName(), mViewerFilters);
		addPage(mProfilePage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (mViewerFilters == null || mViewerFilters.length == 0) {
			mViewerFilters = new ViewerFilter[]{ new NewCPWizardCategoryFilter(null) };
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#needsPreviousAndNextButtons()
	 */
	public boolean needsPreviousAndNextButtons() {
		return true;
	}
	
	public IConnectionProfile getParentProfile() {
		return mParentProfile;
	}

	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
//		HelpUtil.setHelp(getShell(), IHelpConstants.CONTEXT_ID_NEW_CP_WIZARD);
		HelpUtil.setHelp(getShell(), HelpUtil.getContextId(
				IHelpConstants.CONTEXT_ID_NEW_CP_WIZARD,
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

	public boolean canFinish() {
		return super.canFinish();
	}

}