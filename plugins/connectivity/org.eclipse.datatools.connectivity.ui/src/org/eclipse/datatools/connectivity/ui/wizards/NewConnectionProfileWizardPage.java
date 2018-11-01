/*******************************************************************************
 * Copyright (c) 2004-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileProvider;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepository;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.RepositoriesDropList;
import org.eclipse.datatools.connectivity.internal.ui.wizards.BaseWizardPage;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author shongxum
 */
public class NewConnectionProfileWizardPage 
	extends BaseWizardPage 
	implements IContextProvider {

	private Text mCPName;
	private Text mCPDesc;
	private Button mLocalRepository;
	private RepositoriesDropList mRepositories;

	private String mCPNameStr;
	private String mCPDescStr;
    private boolean mCPAutoConnectValue = false;
    private boolean mCPAutoConnectOnFinishValue = false;
	private boolean _showAutoConnect = true;

    private static final String EMPTY_STRING = "";      //$NON-NLS-1$
    
	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

	/**
	 * Constructor
	 */
	public NewConnectionProfileWizardPage() {
		this("NewConnectionProfileWizardPage"); //$NON-NLS-1$
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	public NewConnectionProfileWizardPage(String name) {
		super(name);
		setTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizardPage.title")); //$NON-NLS-1$
		setDescription(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizardPage.desc")); //$NON-NLS-1$
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		String providerID = ((NewConnectionProfileWizard) getWizard())
				.getProfileProviderID();
		IConnectionProfileProvider provider = ConnectionProfileManager
				.getInstance().getProvider(providerID);
		_showAutoConnect = provider.needsMaintainConnection();

		GridData gd;
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		composite.setLayout(gl);
		// Connection profile name
		new Label(composite, SWT.NONE).setText(ConnectivityUIPlugin
				.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.cp_name")); //$NON-NLS-1$
		mCPName = new Text(composite, SWT.BORDER);
		mCPName.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				handleModify();
			}
		});

		mCPName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// Connection profile description
		new Label(composite, SWT.NONE).setText(ConnectivityUIPlugin
				.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.cp_desc")); //$NON-NLS-1$
		mCPDesc = new Text(composite, SWT.BORDER);
		mCPDesc.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		mLocalRepository = new Button(composite, SWT.CHECK);
		gd = new GridData();
		gd.horizontalSpan = 2;
		mLocalRepository.setLayoutData(gd);
		mLocalRepository.setText(ConnectivityUIPlugin.getDefault()
				.getResourceString(
						"NewConnectionProfileWizardPage.localRepository")); //$NON-NLS-1$
		mLocalRepository.setSelection(true);
		mLocalRepository.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent arg0) {
				handleLocalRepositoryChecked();
			}
			
		});

		Label label = new Label(composite, SWT.NONE);
		label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizardPage.chooseRepository")); //$NON-NLS-1$
		mRepositories = new RepositoriesDropList(
				getWizard() instanceof NewConnectionProfileWizard ? (ConnectionProfileProvider) ConnectionProfileManager
						.getInstance().getProvider(
								((NewConnectionProfileWizard) getWizard())
										.getProfileProviderID())
						: null, composite);
		mRepositories.getCombo().setLayoutData(
				new GridData(GridData.FILL_HORIZONTAL));
		mRepositories
				.setInput(ProfileManager
						.getInstance()
						.getProfilesByCategory(
								IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID));
		mRepositories.getCombo().setEnabled(!mLocalRepository.getSelection());
		mRepositories.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				validate();
			}
			
		});
		if (mRepositories.hasRepositories()) {
			if (getWizard() instanceof NewConnectionProfileWizard) {
				IConnectionProfileProvider icpp = ConnectionProfileManager
						.getInstance().getProvider(
								((NewConnectionProfileWizard) getWizard())
										.getProfileProviderID());
				if (icpp.getConnectionFactories().containsKey(
						IConnectionProfileRepository.class.getName())) {
					// prevent nesting of repositories
					mLocalRepository.setEnabled(false);
					mLocalRepository.setVisible(false);
					label.setVisible(false);
					mRepositories.getCombo().setVisible(false);
				}
				else {
					IConnectionProfile parentProfile = ProfileManager
							.getInstance().getProfileByName(
									((NewConnectionProfileWizard) getWizard())
											.getParentProfile());
					if (parentProfile == null) {
						mRepositories.getCombo().select(0);
					}
					else {
						mRepositories.setSelection(new StructuredSelection(
								parentProfile), true);
						if (mRepositories.getSelection().isEmpty()) {
							mRepositories.getCombo().select(0);
						}
						else {
							mLocalRepository.setSelection(false);
							handleLocalRepositoryChecked();
						}
					}
				}
			}
			else {
				mRepositories.getCombo().select(0);
			}
		}
		else {
			mLocalRepository.setEnabled(false);
			mLocalRepository.setVisible(false);
			label.setVisible(false);
			mRepositories.getCombo().setVisible(false);
		}

		// Separate
		label = new Label(composite, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		if (mCPNameStr != null) {
			mCPName.setText(mCPNameStr);
		}
		if (mCPDescStr != null) {
			mCPDesc.setText(mCPDescStr);
		}

		setControl(composite);

		validate();
		setErrorMessage(null);
		
		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_NEW_CONNECTION_PROFILE_PAGE, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
//				IHelpConstants.CONTEXT_ID_NEW_CONNECTION_PROFILE_PAGE);
	}

	private void handleModify() {
		validate();
	}
	
	private void handleLocalRepositoryChecked() {
		mRepositories.getCombo().setEnabled(!mLocalRepository.getSelection());
		validate();
	}
	
	private void validate() {
		String errorMessage = null;
		String cpName = mCPName.getText();
		IConnectionProfile foundProfile = null;
		String updatedPath = cpName;
		if (!mLocalRepository.getSelection()
				&& !mRepositories.getSelection().isEmpty()) {
			updatedPath = mRepositories.getCombo().getText() + InternalProfileManager.PROFILE_PATH_SEPARATOR + cpName;
		}
		foundProfile = ProfileManager.getInstance().getProfileByFullPath(updatedPath);

		if (cpName == null || cpName.trim().length() == 0) {
			errorMessage = ConnectivityUIPlugin.getDefault().getResourceString(
					"NewConnectionProfileWizardPage.Status.NoName"); //$NON-NLS-1$
		}
		else if (cpName.trim().length() < cpName.length() ) {
			errorMessage = ConnectivityUIPlugin.getDefault().getResourceString(
					"NewConnectionProfileWizardPage.Status.NoSpacesInName"); //$NON-NLS-1$
		}
		else if (foundProfile != null) { 
			errorMessage = ConnectivityUIPlugin.getDefault().getResourceString(
					"NewConnectionProfileWizardPage.Status.DuplicateName"); //$NON-NLS-1$
		}
		else if (!mLocalRepository.getSelection()
				&& mRepositories.getSelection().isEmpty()) {
			errorMessage = ConnectivityUIPlugin.getDefault().getResourceString(
					"NewConnectionProfileWizardPage.Status.NoRepository"); //$NON-NLS-1$
		}
		setErrorMessage(errorMessage);
		setPageComplete(errorMessage == null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			mCPName.setFocus();
		}
	}

	public String getProfileName() {
        return ( mCPName == null || mCPName.isDisposed() ) ? 
                mCPNameStr : mCPName.getText();
	}

	public void setProfileName(String name) {
		this.mCPNameStr = name;
		if ( mCPName != null && ! mCPName.isDisposed() ) {
            if ( name == null )
                name = EMPTY_STRING;
			mCPName.setText(name);
		}
	}

	public String getProfileDescription() {
        return ( mCPDesc == null || mCPDesc.isDisposed() ) ? 
                mCPDescStr : mCPDesc.getText();
	}

	public void setProfileDescription(String desc) {
		this.mCPDescStr = desc;
		if ( mCPDesc != null && ! mCPDesc.isDisposed() ) {
            if ( desc == null )
                desc = EMPTY_STRING;
			mCPDesc.setText(desc);
		}
	}

	public boolean getAutoConnect() {
        return mCPAutoConnectValue;
	}

	public boolean getAutoConnectOnFinish() {
        return mCPAutoConnectOnFinishValue;
	}

	public void setAutoConnect(boolean selection) {
        mCPAutoConnectValue = selection;
	}

	public void setAutoConnectOnFinish(boolean selection) {
        mCPAutoConnectOnFinishValue = selection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.suade.common.ui.wizards.ISummaryDataSource#getSummaryData()
	 */
	public List getSummaryData() {
		List data = new ArrayList();

		data.add(new String[] {
				ConnectivityUIPlugin.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.summary_cp_name"), //$NON-NLS-1$
				getProfileName()});

		data.add(new String[] {
				ConnectivityUIPlugin.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.summary_cp_desc"), //$NON-NLS-1$
				getProfileDescription()});

		if (_showAutoConnect) {
			data.add(new String[] {
					ConnectivityUIPlugin
						.getDefault()
							.getResourceString(
								"NewConnectionProfileWizardPage.summary_autoconnect"), //$NON-NLS-1$
				getAutoConnect() ? ConnectivityUIPlugin.getDefault().getResourceString("Boolean.true") //$NON-NLS-1$
							: ConnectivityUIPlugin.getDefault().getResourceString("Boolean.false") }); //$NON-NLS-1$
			data.add(new String[] {
					ConnectivityUIPlugin
						.getDefault()
							.getResourceString(
								"NewConnectionProfileWizardPage.summary_autoconnect_on_finish"), //$NON-NLS-1$
					getAutoConnectOnFinish() ? ConnectivityUIPlugin.getDefault().getResourceString("Boolean.true") //$NON-NLS-1$
							: ConnectivityUIPlugin.getDefault().getResourceString("Boolean.false") }); //$NON-NLS-1$
		}

		return data;
	}
	
	public IConnectionProfile getRepository() {
		if (mLocalRepository.getSelection()) {
			return null;
		}
		return (IConnectionProfile) ((IStructuredSelection) mRepositories
				.getSelection()).getFirstElement();
	}
	
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
