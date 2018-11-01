/*******************************************************************************
 * Copyright (c) 2005, 2007 2008 Sybase, Inc.
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

import java.util.Properties;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.ui.PingJob;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Base property page implementation for connection profiles.
 * 
 * This implementation takes care of updating the connection profile when
 * OK is pressed.
 * 
 * Bug 237720 - had to pull in super.createContents(Composite) code to get around funky issue with setting help
 * 
 * @author shongxum, brianf
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
		Composite container = new Composite(parent, SWT.NULL);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		container.setLayout(gridLayout);

		final Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new FillLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( getControl(), HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_PROFILE_DETAILS_PROPERTY_PAGE, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));

		createCustomContents(composite);

		btnPing = new Button(container, SWT.NONE);
		btnPing.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				testConnection();
			}
		});
		btnPing.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		btnPing.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ConnectionProfileDetailsPage.Button.TestConnection")); //$NON-NLS-1$

		return container;
	}

	protected void testConnection() {
		IConnectionProfile cp = getConnectionProfile();
		ConnectionProfile profile = new ConnectionProfile(cp.getName(), cp
				.getDescription(), cp.getProviderId(),
				cp.getParentProfile() == null ? "" : cp.getParentProfile() //$NON-NLS-1$
						.getName(), false);
		profile.setBaseProperties(collectProperties());

        BusyIndicator.showWhile( getShell().getDisplay(), 
                createTestConnectionRunnable( profile ) );          
	}

	public void setPingButtonEnabled(boolean enabled)
    {
        if (btnPing != null && !btnPing.isDisposed())
        {
            btnPing.setEnabled(enabled);
            if( enabled )
                enableParent( btnPing );
        }
    }
    
    /**
     * Enables the specified control's composite.
     */
    private void enableParent( Control control )
    {
        Composite parent = control.getParent( );
        if( parent == null || parent instanceof Shell )
            return;

        if( ! parent.isEnabled() )
            parent.setEnabled( true );

        enableParent( parent );
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

	protected Properties collectProperties() {
		return null;
	}
}
