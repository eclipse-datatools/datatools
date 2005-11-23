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
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.wizards.BaseWizardPage;
import org.eclipse.datatools.connectivity.ui.PingJob;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

/**
 * @author shongxum
 */
public abstract class ConnectionProfileDetailsPage extends BaseWizardPage {
	
	protected Button btnPing;

	/**
	 * @param name
	 */
	public ConnectionProfileDetailsPage(String name) {
		super(name);
	}

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public ConnectionProfileDetailsPage(String pageName, String title,
										ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		container.setLayout(gridLayout);
		// Client shouldn't call setControl again.
		setControl(container);

		final Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new FillLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		createCustomControl(composite);

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
				IHelpConstants.CONTEXT_ID_CONNECTION_PROFILE_DETAILS_PAGE);
	}

	public abstract void createCustomControl(Composite parent);

	protected void testConnection() {
		IWizard wiz = getWizard();
		if (wiz instanceof NewConnectionProfileWizard) {
			NewConnectionProfileWizard wizard = (NewConnectionProfileWizard) wiz;
			ConnectionProfile profile = new ConnectionProfile(wizard
					.getProfileName(), wizard.getProfileDescription(), wizard
					.getProfileProviderID(), wizard.getParentProfile(), wizard
					.getProfileIsAutoConnect());
			profile.setBaseProperties(wizard.getProfileProperties());

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
}
