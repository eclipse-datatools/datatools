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

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.ui.PingJob;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;

/**
 * Base property page implementation for connection profiles.
 * 
 * This implementation takes care of updating the connection profile when
 * OK is pressed.
 * 
 * @author shongxum
 */
public abstract class ProfileDetailsPropertyPage extends ProfilePropertyPage {

	protected Button btnPing;

	protected ProfileDetailsPropertyPage() {
		super(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite container = (Composite)super.createContents(parent);

		btnPing = new Button(container, SWT.NONE);
		btnPing.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				testConnection();
			}
		});
		btnPing.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		btnPing.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ConnectionProfileDetailsPage.Button.TestConnection")); //$NON-NLS-1$
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
				IHelpConstants.CONTEXT_ID_PROFILE_DETAILS_PROPERTY_PAGE);

		return container;
	}

	protected void testConnection() {
		IConnectionProfile cp = getConnectionProfile();
		ConnectionProfile profile = new ConnectionProfile(cp.getName(), cp
				.getDescription(), cp.getProviderId(),
				cp.getParentProfile() == null ? "" : cp.getParentProfile() //$NON-NLS-1$
						.getName(), false);
		profile.setBaseProperties(collectProperties());

		final Job pingJob = new PingJob(getShell(), profile);
		pingJob.schedule();
		BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {

			public void run() {
				try {
					pingJob.join();
				}
				catch (InterruptedException e) {
				}
			}
		});
	}

}
