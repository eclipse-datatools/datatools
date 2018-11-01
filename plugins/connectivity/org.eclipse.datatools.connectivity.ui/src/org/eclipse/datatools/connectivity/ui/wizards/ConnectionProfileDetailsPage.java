/*******************************************************************************
 * Copyright (c) 2005, 2009 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: 
 *  shongxum - initial API and implementation
 *  Actuate Corporation - refactored to improve extensibility
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.wizards.BaseWizardPage;
import org.eclipse.datatools.connectivity.ui.Messages;
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
import org.eclipse.swt.events.SelectionListener;
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
	
	private boolean defaultAutoConnectOnFinishFlag = true;

	private Button autoConnectOnFinishButton = null;
	private Button autoConnectOnStartupButton = null;

	private boolean _showAutoConnect = true;
	private boolean _showAutoConnectOnFinish = true;
	private boolean _showPing = true;

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
		final GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		container.setLayout(gridLayout);
		
		// Client shouldn't call setControl again.
		setControl(container);

		// setting help now rather than at the end so that 
		// extenders can override with different context IDs in their
		// custom UI
		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_NEW_CONNECTION_PROFILE_PAGE, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));

		final Composite composite = new Composite(container, SWT.NONE);
		FillLayout flayout = new FillLayout();
		flayout.marginHeight = 0;
		flayout.marginWidth = 0;
		composite.setLayout(flayout);
        GridData compositeGD = new GridData(GridData.FILL_BOTH);
        compositeGD.horizontalSpan = 2;
		composite.setLayoutData(compositeGD);

		createCustomControl(composite);
		
		
		if (_showAutoConnectOnFinish) {
			autoConnectOnFinishButton = new Button(container, SWT.CHECK);
			autoConnectOnFinishButton.setText(Messages.ConnectionProfileDetailsPage_Autoconnect_finish);
			autoConnectOnFinishButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
			autoConnectOnFinishButton.addSelectionListener(new SelectionListener() {
	
				public void widgetDefaultSelected(SelectionEvent e) {
					ConnectionProfileDetailsPage.this.setAutoConnectFinish(
							ConnectionProfileDetailsPage.this.autoConnectOnFinishButton.getSelection());
				}
	
				public void widgetSelected(SelectionEvent e) {
					widgetDefaultSelected(e);
				}
			});
			autoConnectOnFinishButton.setSelection(defaultAutoConnectOnFinishFlag);
		}

		if (_showPing) {
			btnPing = new Button(container, SWT.NONE);
			btnPing.addSelectionListener(new SelectionAdapter() {
	
				public void widgetSelected(SelectionEvent e) {
					testConnection();
				}
			});

			GridData pingGD = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.FILL_HORIZONTAL);
			if (!_showAutoConnectOnFinish)
				pingGD.horizontalSpan = 2;
			btnPing.setLayoutData(pingGD);
			btnPing.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"ConnectionProfileDetailsPage.Button.TestConnection")); //$NON-NLS-1$

		}

		if (_showAutoConnect) {
			autoConnectOnStartupButton = new Button(container, SWT.CHECK);
			autoConnectOnStartupButton.setText(Messages.ConnectionProfileDetailsPage_Autoconnect_startup);
			GridData acStartupGD = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
			if (!_showPing)
				acStartupGD.horizontalSpan = 2;
			autoConnectOnStartupButton.setLayoutData(acStartupGD);
			autoConnectOnStartupButton.addSelectionListener(new SelectionListener() {
	
				public void widgetDefaultSelected(SelectionEvent e) {
					ConnectionProfileDetailsPage.this.setAutoConnect(
							ConnectionProfileDetailsPage.this.autoConnectOnStartupButton.getSelection());
				}
	
				public void widgetSelected(SelectionEvent e) {
					widgetDefaultSelected(e);
				}
			});
		}

		if (this.getWizard() instanceof NewConnectionProfileWizard) {
			NewConnectionProfileWizard wiz =
				(NewConnectionProfileWizard) this.getWizard();
			wiz.getProfilePage().setAutoConnectOnFinish(defaultAutoConnectOnFinishFlag);
		}
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

			BusyIndicator.showWhile( getShell().getDisplay(), 
			        createTestConnectionRunnable( profile ) );			
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
	
	protected Runnable createTestConnectionRunnable( final IConnectionProfile profile )
	{
        final Job pingJob = new PingJob( getShell(), profile );
        pingJob.schedule();
        return new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    pingJob.join();
                }
                catch (InterruptedException e) 
                {
                }
            }
        };
	}
	
	private void setAutoConnectFinish ( boolean flag ) {
		if (this.getWizard() instanceof NewConnectionProfileWizard) {
			NewConnectionProfileWizard wiz =
				(NewConnectionProfileWizard) this.getWizard();
			wiz.getProfilePage().setAutoConnectOnFinish(flag);
		}
	}

	private void setAutoConnect ( boolean flag ) {
		if (this.getWizard() instanceof NewConnectionProfileWizard) {
			NewConnectionProfileWizard wiz =
				(NewConnectionProfileWizard) this.getWizard();
			wiz.getProfilePage().setAutoConnect(flag);
		}
	}
	
	protected void setAutoConnectOnFinishDefault( boolean flag ){
		this.defaultAutoConnectOnFinishFlag = flag;
	}
	
	protected boolean getAutoConnectOnFinishDefault() {
		return this.defaultAutoConnectOnFinishFlag;
	}
	
	protected void setShowAutoConnectOnFinish ( boolean flag ) {
		this._showAutoConnectOnFinish = flag;
	}

	protected void setShowAutoConnect ( boolean flag ) {
		this._showAutoConnect = flag;
	}

	/**
	 * Specifies whether to create the controls for the auto connect options on this page.
	 * @param flag true to create related controls; false otherwise
     * @since DTP 1.7.2
	 */
	public void setCreateAutoConnectControls( boolean flag )
	{
        setAutoConnectOnFinishDefault( flag );
        setShowAutoConnectOnFinish( flag );
        setShowAutoConnect( flag );
	}
	
	protected void setShowPing ( boolean flag ) {
		this._showPing = flag;
	}
}
