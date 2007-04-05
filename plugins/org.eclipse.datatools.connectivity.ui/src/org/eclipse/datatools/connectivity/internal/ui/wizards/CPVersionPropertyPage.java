/*******************************************************************************
 * Copyright (c) 2005-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent.IChangedProperty;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * @author rcernich
 * 
 * Created on Jun 3, 2005
 */
public class CPVersionPropertyPage extends PropertyPage implements
		IWorkbenchPropertyPage {

	private static final int INDENT_TECH_VERSION = 20;

	private Button mUpdateVersionInfoButton;

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
						.getChangedProperty(IConnectionProfile.CONNECTED_PROPERTY_ID);
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

			Label label = new Label(serverVersionContainer, SWT.NULL);
			label
					.setText(props
							.getProperty(ConnectionProfileConstants.PROP_SERVER_NAME) + ':');
			data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
			label.setLayoutData(data);

			label = new Label(serverVersionContainer, SWT.NULL);
			label
					.setText(props
							.getProperty(ConnectionProfileConstants.PROP_SERVER_VERSION));
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
						"CPVersionPropertyPage.button.updateVersion"));
		mUpdateVersionInfoButton.setLayoutData(new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING
						| GridData.VERTICAL_ALIGN_END));
		mUpdateVersionInfoButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				getConnectionProfile().connect(null);
			}
		});

		mUpdateVersionInfoButton.setEnabled(!getConnectionProfile()
				.isConnected());
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

}