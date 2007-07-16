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
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.dialogs.MessageDialog;
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

/**
 * @author shongxum
 */
public abstract class ConnectionProfileDetailsPage 
	extends BaseWizardPage
	implements IContextProvider {
	
	protected Button btnPing;

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

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

//		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
//				IHelpConstants.CONTEXT_ID_CONNECTION_PROFILE_DETAILS_PAGE);
		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_PROFILE_DETAILS_PROPERTY_PAGE, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));
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
			
			// ping fails if an error message is set for the page
			// this fixes BZ 173568 - BTF
			if (this.getErrorMessage() != null) {
				MessageDialog.openError(getWizard().getContainer().getShell(),
						ConnectivityUIPlugin.getDefault().getResourceString(
								"actions.ping.failure"), //$NON-NLS-1$
								this.getErrorMessage());
				return;
			}
			
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

	protected void setPingButtonEnabled(boolean enabled)
    {
        if (btnPing != null && !btnPing.isDisposed())
        {
            btnPing.setEnabled(enabled);
        }
    }

	protected void setPingButtonVisible(boolean visible)
    {
        if (btnPing != null && !btnPing.isDisposed())
        {
            btnPing.setVisible(visible);
        }
    }
}
