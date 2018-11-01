/*******************************************************************************
 * Copyright (c) 2005-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 * 			IBM Corporation = bug fix #203829
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent.IChangedProperty;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * @author rcernich
 * 
 * Created on Jun 3, 2005
 */
public class CPVersionPropertyPage extends PropertyPage implements
		IWorkbenchPropertyPage, IContextProvider {

	private static final int INDENT_TECH_VERSION = 20;

	private Button mUpdateVersionInfoButton;
	
	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

	private IPropertySetListener mPropertySetListener = new IPropertySetListener() {

		public void propertySetChanged(IPropertySetChangeEvent event) {
			if (ConnectionProfileConstants.VERSION_INFO_PROFILE_EXTENSION_ID
					.equals(event.getPropertySetType())) {
				getControl().getDisplay().asyncExec(new Runnable() {

					public void run() {
						updateVersionInformation();
					}
				});
			}
			else if (IConnectionProfile.CONNECTION_PROFILE_PROPERTY_SET
					.equals(event.getPropertySetType())) {
				final IChangedProperty icp = event
						.getChangedProperty(IConnectionProfile.CONNECTION_STATE_PROPERTY_ID);
				if (icp != null) {
					getControl().getDisplay().asyncExec(new Runnable() {

						public void run() {
							if (Boolean.valueOf(icp.getNewValue())
									.booleanValue()) {
								updateVersionInformation();
								mUpdateVersionInfoButton.setEnabled(false);
							}
							else {
								mUpdateVersionInfoButton.setEnabled(true);
							}
						}
					});
				}
			}
		}
	};

	/**
	 * 
	 */
	public CPVersionPropertyPage() {
		super();
		noDefaultAndApplyButton();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
        Composite content = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		content.setLayout(layout);

		createControls(content);

		getConnectionProfile().addPropertySetListener(mPropertySetListener);

        getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_PROFILE_VERSION_PROPERTIES, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));
		return content;
	}

	public void dispose() {
		getConnectionProfile().removePropertySetListener(mPropertySetListener);
		super.dispose();
	}

	private void updateVersionInformation() {
		Composite content = (Composite) getControl();
		if (content == null || content.isDisposed()) {
			return;
		}
		deleteControls(content);
		createControls(content);
		content.layout(true);
		content.redraw();
	}

	private void createControls(Composite content) {
		IConnectionProfile profile = getConnectionProfile();
		setDescription(ConnectivityUIPlugin.getDefault().getResourceString(
				"CPVersionPropertyPage.desc", //$NON-NLS-1$
				new Object[] { profile.getName()}));

		Properties props = profile
				.getProperties(ConnectionProfileConstants.VERSION_INFO_PROFILE_EXTENSION_ID);
		if (props.getProperty(ConnectionProfileConstants.PROP_SERVER_NAME) == null) {
			Label label = new Label(content, SWT.NULL);
			label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"CPVersionPropertyPage.label.versionUnavailable")); //$NON-NLS-1$
			GridData data = new GridData(GridData.FILL_HORIZONTAL);
			label.setLayoutData(data);
		}
		else {
			Composite serverVersionContainer = new Composite(content, SWT.NULL);
			GridLayout layout = new GridLayout(2, false);
			layout.marginHeight = layout.marginWidth = 0;
			serverVersionContainer.setLayout(layout);
			GridData data = new GridData(GridData.FILL_HORIZONTAL);
			serverVersionContainer.setLayoutData(data);
		
			String serverName = props
				.getProperty(ConnectionProfileConstants.PROP_SERVER_NAME);
			String serverVersion = props
				.getProperty(ConnectionProfileConstants.PROP_SERVER_VERSION);
			Label label = new Label(serverVersionContainer, SWT.NULL);
			if (serverVersion == null){
				label.setText( serverName);
			} else {
				label.setText( serverName + ':');
			}
			data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
			label.setLayoutData(data);

			label = new Label(serverVersionContainer, SWT.NULL);
			if (serverVersion != null){
				label.setText(serverVersion);
			}
			data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
			label.setLayoutData(data);

			List techVersions = new ArrayList();
			for (Iterator it = props.entrySet().iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = (String) entry.getKey();
				if (key != null
						&& key
								.startsWith(ConnectionProfileConstants.PROP_TECHNOLOGY_NAME_PREFIX)) {
					String name = ((String) entry.getValue()) + ':';
					String verKey = ConnectionProfileConstants.PROP_TECHNOLOGY_VERSION_PREFIX
							+ key.substring(key.lastIndexOf('.') + 1);
					String version = props.getProperty(verKey);
					if (version == null || version.length() == 0) {
						version = ConnectionProfileConstants.UNKNOWN_VERSION;
					}
					techVersions.add(new String[] { name, version});
				}
			}
			if (techVersions.size() > 0) {
				label = new Label(content, SWT.NULL);
				label.setText(ConnectivityUIPlugin.getDefault()
						.getResourceString(
								"CPVersionPropertyPage.label.techVersions")); //$NON-NLS-1$
				data = new GridData();
				data.horizontalSpan = 2;
				label.setLayoutData(data);

				Composite techVersionContainer = new Composite(content,
						SWT.NULL);
				layout = new GridLayout(2, false);
				techVersionContainer.setLayout(layout);
				data = new GridData(GridData.FILL_HORIZONTAL);
				data.horizontalIndent = INDENT_TECH_VERSION;
				techVersionContainer.setLayoutData(data);

				for (Iterator it = techVersions.iterator(); it.hasNext();) {
					String pair[] = (String[]) it.next();

					label = new Label(techVersionContainer, SWT.NULL);
					label.setText(pair[0]);
					data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
					label.setLayoutData(data);

					label = new Label(techVersionContainer, SWT.NULL);
					label.setText(pair[1]);
					data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
					label.setLayoutData(data);
				}
			}
		}

		mUpdateVersionInfoButton = new Button(content, SWT.PUSH);
		mUpdateVersionInfoButton
				.setText(ConnectivityUIPlugin.getDefault().getResourceString(
						"CPVersionPropertyPage.button.updateVersion")); //$NON-NLS-1$
		mUpdateVersionInfoButton.setLayoutData(new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING
						| GridData.VERTICAL_ALIGN_END));
		mUpdateVersionInfoButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				Display.getCurrent().syncExec( new Runnable() {

					public void run() {
						getConnectionProfile().connect(new IJobChangeListener() {

							public void aboutToRun(IJobChangeEvent event) {
								// ignore event
							}

							public void awake(IJobChangeEvent event) {
								// ignore event
							}

							public void done(IJobChangeEvent event) {
								final Composite content = (Composite) getControl();
								if (content == null || content.isDisposed()) {
									return;
								}
								Display.getDefault().syncExec( new Runnable() {

									public void run() {
										deleteControls(content);
										createControls(content);
										content.layout(true);
										content.redraw();
									}
								});
							}

							public void running(IJobChangeEvent event) {
								// ignore event
							}

							public void scheduled(IJobChangeEvent event) {
								// ignore event
							}

							public void sleeping(IJobChangeEvent event) {
								// ignore event
							}
						});
					}
				});
			}
		});

		mUpdateVersionInfoButton.setEnabled(getConnectionProfile()
				.getConnectionState() == IConnectionProfile.DISCONNECTED_STATE);
	}

	private void deleteControls(Composite content) {
		for (Iterator it = Arrays.asList(content.getChildren()).iterator(); it
				.hasNext();) {
			((Control) it.next()).dispose();
		}
	}

	private IConnectionProfile getConnectionProfile() {
		IAdaptable element = getElement();
		IConnectionProfile profile = (IConnectionProfile) element
				.getAdapter(IConnectionProfile.class);
		return profile;
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