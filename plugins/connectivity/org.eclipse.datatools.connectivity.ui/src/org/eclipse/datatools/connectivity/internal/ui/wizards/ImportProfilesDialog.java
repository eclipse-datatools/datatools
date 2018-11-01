/*******************************************************************************
 * Copyright (c) 2005, 2011 Sybase, Inc. and others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 *      Actuate Corporation - added the cipherProvider extension point [BZ 358686]
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.io.File;
import java.io.FileInputStream;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.internal.repository.IConnectionProfileRepositoryConstants;
import org.eclipse.datatools.connectivity.internal.security.SecurityManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.RepositoriesDropList;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author shongxum
 */
public class ImportProfilesDialog extends TrayDialog implements IContextProvider {

	private Text txtFile;

	private Button mLocalRepository;
	
	private RepositoriesDropList mRepositories;

	private Button btnOverwrite;

	private Throwable mException;

	private IConnectionProfile[] mProfiles;

	private boolean mOverwrite;
	
	private boolean mUseLocalRepository;
	
	private IConnectionProfile mRepository;

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

	public ImportProfilesDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE);
	}

	protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = 20;
		gridLayout.numColumns = 3;
		container.setLayout(gridLayout);
		{
			Text text = new Text(container, SWT.BORDER | SWT.MULTI
					| SWT.READ_ONLY | SWT.WRAP);
			text.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"ImportProfilesDialog.text.text")); //$NON-NLS-1$
			final GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.heightHint = 60;
			gridData.horizontalSpan = 3;
			text.setLayoutData(gridData);
		}
		{
			final Label label = new Label(container, SWT.NONE);
			final GridData gridData = new GridData(
					GridData.HORIZONTAL_ALIGN_FILL);
			gridData.horizontalSpan = 3;
			gridData.widthHint = 495;
			label.setLayoutData(gridData);
		}
		{
			final Label label = new Label(container, SWT.NONE);
			final GridData gridData = new GridData();
			gridData.horizontalIndent = 5;
			label.setLayoutData(gridData);
			label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"ImportProfilesDialog.label.text")); //$NON-NLS-1$
		}
		{
			txtFile = new Text(container, SWT.BORDER);
			final GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.widthHint = 243;
			txtFile.setLayoutData(gridData);
		}
		{
			final Button button = new Button(container, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					String text = new FileDialog(getShell()).open();
					if (text != null)
						txtFile.setText(text);
				}
			});
			button
					.setLayoutData(new GridData(
							GridData.HORIZONTAL_ALIGN_CENTER));
			button.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"ImportProfilesDialog.button.text")); //$NON-NLS-1$
		}
		{
			btnOverwrite = new Button(container, SWT.CHECK);
			final GridData gridData = new GridData(GridData.GRAB_HORIZONTAL);
			gridData.horizontalIndent = 10;
			gridData.horizontalSpan = 3;
			btnOverwrite.setLayoutData(gridData);
			btnOverwrite
					.setText(ConnectivityUIPlugin.getDefault()
							.getResourceString(
									"ImportProfilesDialog.btnOverwrite.text")); //$NON-NLS-1$
		}
		{
			Label spacer = new Label(container,SWT.NONE);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.heightHint = 20;
			gd.horizontalSpan = 3;
			spacer.setLayoutData(gd);
			
			Label separator = new Label(container,SWT.SEPARATOR|SWT.HORIZONTAL);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 3;
			separator.setLayoutData(gd);

			mLocalRepository = new Button(container, SWT.CHECK);
			gd = new GridData();
			gd.horizontalSpan = 3;
			mLocalRepository.setLayoutData(gd);
			mLocalRepository.setText(ConnectivityUIPlugin.getDefault()
					.getResourceString(
							"NewConnectionProfileWizardPage.localRepository")); //$NON-NLS-1$
			mUseLocalRepository = true;
			mLocalRepository.setSelection(mUseLocalRepository);
			mLocalRepository.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent arg0) {
					mUseLocalRepository = mLocalRepository.getSelection();
					mRepositories.getCombo().setEnabled(!mUseLocalRepository);
				}
				
			});

			Label label = new Label(container, SWT.NONE);
			label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"NewConnectionProfileWizardPage.chooseRepository")); //$NON-NLS-1$
			mRepositories = new RepositoriesDropList(null, container);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			mRepositories.getCombo().setLayoutData(gd);
			mRepositories
					.setInput(ProfileManager
							.getInstance()
							.getProfilesByCategory(
									IConnectionProfileRepositoryConstants.REPOSITORY_CATEGORY_ID));
			mRepositories
					.addSelectionChangedListener(new ISelectionChangedListener() {

						public void selectionChanged(SelectionChangedEvent event) {
							ISelection selection = event.getSelection();
							if (selection.isEmpty()) {
								mRepository = null;
							}
							else {
								mRepository = (IConnectionProfile) ((IStructuredSelection) selection)
										.getFirstElement();
							}
						}

					});
			mRepositories.getCombo()
					.setEnabled(!mLocalRepository.getSelection());
			if (!mRepositories.hasRepositories()) {
				mLocalRepository.setEnabled(false);
				mLocalRepository.setVisible(false);
				label.setVisible(false);
				separator.setVisible(false);
				mRepositories.getCombo().setVisible(false);
			}

		}

		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp( getShell(), 
        		HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_IMPORT_PROFILES_DIALOG, 
        				ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));

		return container;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ImportProfilesDialog.null.title")); //$NON-NLS-1$
	}

	public Throwable getException() {
		return mException;
	}

	public IConnectionProfile[] getProfiles() {
		return mProfiles;
	}

	public boolean isOverwritten() {
		return mOverwrite;
	}
	
	public boolean getUseLocalRepository() {
		return mUseLocalRepository;
	}
	
	public IConnectionProfile getSelectedRepository() {
		return mRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		if (txtFile.getText().length() == 0) {
			MessageDialog.openError(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString("dialog.title.error"), //$NON-NLS-1$
					ConnectivityUIPlugin.getDefault().getResourceString(
							"actions.export.nofile")); //$NON-NLS-1$
			return;
		}
		else if (!mUseLocalRepository && mRepository == null) {
			MessageDialog.openError(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString("dialog.title.error"), //$NON-NLS-1$
					ConnectivityUIPlugin.getDefault().getResourceString(
							"actions.export.norepository")); //$NON-NLS-1$
			return;
		}
		mOverwrite = btnOverwrite.getSelection();
		try {
			byte[] bytes = new byte[5];
			char[] xml = {'<','?','x','m','l'};
			FileInputStream fis = new FileInputStream(txtFile.getText());
			fis.read(bytes);
			fis.close();
			boolean isXML = true;
			for (int i = 0; isXML && i < 5; ++i) {
				isXML = bytes[i] == xml[i];
			}
			if (isXML) {
				// not encrpyted
				mProfiles = ConnectionProfileMgmt.importCPs(new File(txtFile
						.getText()), null, mOverwrite);
			}
			else {
				// encrypted
                File importFile = new File(txtFile.getText());
                mProfiles = ConnectionProfileMgmt.importCPs( importFile, 
                                SecurityManager.getInstance().getCipherProvider( importFile ), 
                                mOverwrite );
			}
		}
		catch (Exception e) {
			mException = e;
		}
		
		super.okPressed();
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