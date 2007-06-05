/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import java.util.Iterator;
import java.util.Properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.wizards.CPPropetyPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * Base property page implementation for connection profiles.
 * 
 * This implementation takes care of updating the connection profile when
 * OK is pressed.
 * 
 * @author shongxum
 */
public abstract class ProfilePropertyPage extends PropertyPage {
	
	private boolean mAffectsConnectionProperties;

	private ISchedulingRule mProfileRule;

	protected ProfilePropertyPage() {
		this(false);
	}

	protected ProfilePropertyPage(boolean affectsConnectionProperties) {
		super();
		mAffectsConnectionProperties = affectsConnectionProperties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		container.setLayout(gridLayout);

		final Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new FillLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createCustomContents(composite);

		return container;
	}

	protected abstract void createCustomContents(Composite parent);

	protected abstract Properties collectProperties();

	protected IConnectionProfile getConnectionProfile() {
		IAdaptable element = getElement();
		IConnectionProfile profile = (IConnectionProfile) element
				.getAdapter(IConnectionProfile.class);
		return profile;
	}
	
	protected String getPropertiesID() {
		return getConnectionProfile().getProviderId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 */
	public boolean performOk() {
		IConnectionProfile profile = getConnectionProfile();
		Properties oldProps = profile.getProperties(getPropertiesID());
		Properties newProps = collectProperties();

		boolean changed = false;
		for (Iterator itr = newProps.keySet().iterator(); !changed
				&& itr.hasNext();) {
			Object key = itr.next();
			Object oldObj = oldProps.get(key);
			Object newObj = newProps.get(key);
			changed = (newObj != null && !newObj.equals(oldObj))
					|| (newObj == null && oldObj != null);
		}

		if (changed) {
			profile.setProperties(getPropertiesID(),newProps);

			if (mAffectsConnectionProperties
					&& profile.getConnectionState() != IConnectionProfile.DISCONNECTED_STATE) {
				if (MessageDialog
						.openQuestion(
								getShell(),
								ConnectivityUIPlugin
										.getDefault()
										.getResourceString(
												"ConnectionProfileDetailsPage.AskConfirmation"), //$NON-NLS-1$
								ConnectivityUIPlugin
										.getDefault()
										.getResourceString(
												"ConnectionProfileDetailsPage.AskReconnect"))) { //$NON-NLS-1$

					if (mProfileRule == null) {
						/*
						 * block the actual connect attempt until after the dialog
						 * is closed. this will allow any other property pages to
						 * commit their changes on the profile prior to the connect
						 * being executed.
						 */
						mProfileRule = CPPropetyPage.getProfileRule(profile);
						Platform.getJobManager().beginRule(mProfileRule,
								null);
						profile.disconnect(null);
						profile.connect(null);
					}
				}
			}
		}

		return super.performOk();
	}

	public void dispose() {
		if (mProfileRule != null) {
			Platform.getJobManager().endRule(mProfileRule);
			mProfileRule = null;
		}
		super.dispose();
	}

}
