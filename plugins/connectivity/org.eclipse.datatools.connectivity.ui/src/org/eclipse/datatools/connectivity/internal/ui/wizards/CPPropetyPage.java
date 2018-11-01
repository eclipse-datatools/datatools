/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.ProfileRule;
import org.eclipse.datatools.connectivity.internal.InternalProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.ui.Messages;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

public class CPPropetyPage extends PropertyPage 
	implements IContextProvider {
	
	private static Map sProfileToProfileRuleMap = new HashMap(); // IConnectionProfile,ProfileRule

	private Text txtProfileName;

	private Text txtProfileDesc;

	private Button btnAutoConnect;
	
	private ISchedulingRule profileRule;

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

	/**
	 * Constructor for FileProfilePropetyPage.
	 */
	public CPPropetyPage() {
		super();
		noDefaultAndApplyButton();
	}

	/**
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Label label;
		GridData gd;
		Composite content = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(2, false);
		content.setLayout(layout);

		label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizardPage.cp_name")); //$NON-NLS-1$

		txtProfileName = new Text(content, SWT.BORDER);
		txtProfileName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtProfileName.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				validate();
			}
		});

		label = new Label(content, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizardPage.cp_desc")); //$NON-NLS-1$

		txtProfileDesc = new Text(content, SWT.BORDER);
		txtProfileDesc.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		btnAutoConnect = new Button(content, SWT.CHECK);
		btnAutoConnect.setText(Messages.ConnectionProfileDetailsPage_Autoconnect_startup);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
//		gd.horizontalIndent = 20;
		btnAutoConnect.setLayoutData(gd);

		initControls();
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
//				IHelpConstants.CONTEXT_ID_CP_PROPERTY_PAGE);
		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_CP_PROPERTY_PAGE, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));

		return content;
	}

	public boolean performOk() {
		IConnectionProfile profile = getConnectionProfile();
		boolean autoConnectChanged = btnAutoConnect.getSelection() != profile.isAutoConnect();
		if (autoConnectChanged
				|| !txtProfileName.getText().equals(profile.getName())
				|| !txtProfileDesc.getText().equals(profile.getDescription())) {
			if (autoConnectChanged && profileRule == null) {
				/*
				 * block connect jobs until the dialog is closed. if the user
				 * also changed connection related properties, the connect job
				 * may be executed prior to those changes being committed.
				 */
				profileRule = getProfileRule(profile);
				Job.getJobManager().beginRule(profileRule,
						null);
			}
			try {
				ProfileManager.getInstance().modifyProfile(profile,
						txtProfileName.getText(), txtProfileDesc.getText(),
						new Boolean(btnAutoConnect.getSelection()));
			}
			catch (ConnectionProfileException e) {
				ExceptionHandler.showException(getShell(), ConnectivityUIPlugin
						.getDefault().getResourceString("dialog.exception.title"), //$NON-NLS-1$
						ConnectivityUIPlugin.getDefault().getResourceString(
								"dialog.exception.message", //$NON-NLS-1$
								new Object[] { e.getMessage()}), e); //$NON-NLS-1$
			}
		}
		return true;
	}

	private void initControls() {
		IConnectionProfile profile = getConnectionProfile();

		txtProfileName.setText(profile.getName());
		txtProfileDesc.setText(profile.getDescription());

		boolean showAutoConnect = profile.getProvider()
				.needsMaintainConnection();
		if (!showAutoConnect)

		{
			btnAutoConnect.setSelection(true);
			btnAutoConnect.setEnabled(false);
			btnAutoConnect.setVisible(false);
		}
		else {
			btnAutoConnect.setSelection(profile.isAutoConnect());
		}

		setErrorMessage(null);
	}

	private IConnectionProfile getConnectionProfile() {
		IAdaptable element = getElement();
		IConnectionProfile profile = (IConnectionProfile) element
				.getAdapter(IConnectionProfile.class);
		return profile;
	}

	private void validate() {
		String errorMessage = null;

		if (txtProfileName.getText().trim().length() == 0) {
			errorMessage = ConnectivityUIPlugin.getDefault().getResourceString(
					"NewConnectionProfileWizardPage.Status.NoName"); //$NON-NLS-1$
		}
		else {
			if (!getConnectionProfile().getName().equals(
					txtProfileName.getText())) {
				IConnectionProfile foundProfile = null;
				String path = ProfileManager.getInstance().getProfilePath(getConnectionProfile());
				if ( path != null) {
					String[] parsedPath = ProfileManager.getInstance().tokenize(path, InternalProfileManager.PROFILE_PATH_SEPARATOR);
					parsedPath[parsedPath.length - 1] = txtProfileName.getText();
					String updatedPath = ProfileManager.getInstance().unTokenize(parsedPath);
					foundProfile = ProfileManager.getInstance().getProfileByFullPath(updatedPath);
				}
				if (foundProfile != null) {
					errorMessage = ConnectivityUIPlugin
							.getDefault()
							.getResourceString(
									"NewConnectionProfileWizardPage.Status.DuplicateName"); //$NON-NLS-1$
				}
			}
		}

		setErrorMessage(errorMessage);
		setValid(errorMessage == null);
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

	public void dispose() {
		if (profileRule != null) {
			Job.getJobManager().endRule(profileRule);
			profileRule = null;
		}
		super.dispose();
	}
	
	/**
	 * This is an internal utility method used to workaround some locking issues
	 * caused by the way we're using it. The basic problem here is that the
	 * begin/endRule() calls must be matched, but we can't accommodate that
	 * given the order of execution within the property dialog, so we have:
	 * 
	 * Page1.performOK(), Page2.performOK(), ... PageN.performOK() (which is
	 * where we call IJobManager.beginRule()).
	 * 
	 * followed by:
	 * 
	 * Page1.dispose(), Page2.dispose(), ... PageN.dispose() (which is where we
	 * call IJobManager.endRule()).
	 * 
	 * To work around this, we reuse the same rule instance (provided by this
	 * method) across all pages. This fakes the job manager into thinking the
	 * calls are nested correctly.
	 * 
	 * We use this mechanism to prevent other profile jobs (e.g. connect) from
	 * running until after all pages have performed their OK logic.
	 * 
	 * @param profile
	 * @return the scheduling rule for the profile
	 */
	public static ISchedulingRule getProfileRule(IConnectionProfile profile) {
		ISchedulingRule rule = (ISchedulingRule) sProfileToProfileRuleMap
				.get(profile);
		if (rule == null) {
			rule = new ProfileRule(profile);
			sProfileToProfileRuleMap.put(profile, rule);
		}
		return rule;
	}
}
