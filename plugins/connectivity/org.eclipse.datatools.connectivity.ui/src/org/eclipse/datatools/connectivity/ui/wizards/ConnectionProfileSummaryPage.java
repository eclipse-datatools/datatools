/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.wizards.ISummaryDataSource;
import org.eclipse.datatools.connectivity.internal.ui.wizards.SummaryWizardPage;
import org.eclipse.datatools.connectivity.ui.PingJob;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Summary Page + Test Connection
 * 
 * @author shongxum
 */
public class ConnectionProfileSummaryPage 
	extends SummaryWizardPage
	implements IContextProvider {

	private ISummaryDataSource dataSource;

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

	/**
	 * @param source
	 */
	public ConnectionProfileSummaryPage(ISummaryDataSource source) {
		super(source);
		dataSource = source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
		Composite container = (Composite) getControl();
		final Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				testConnection();
			}
		});
		button.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		button.setText(ConnectivityUIPlugin.getDefault().getResourceString("ConnectionProfileSummaryPage.button.testConnection")); //$NON-NLS-1$
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
//				IHelpConstants.CONTEXT_ID_CONNECTION_PROFILE_SUMMARY_PAGE);
		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_CONNECTION_PROFILE_SUMMARY_PAGE, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));

	}

	protected void testConnection() {
		if (dataSource instanceof NewConnectionProfileWizard) {
			NewConnectionProfileWizard wizard = (NewConnectionProfileWizard) dataSource;
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