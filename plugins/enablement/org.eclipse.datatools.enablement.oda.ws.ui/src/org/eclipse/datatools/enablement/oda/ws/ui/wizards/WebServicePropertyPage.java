/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.ws.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.datatools.connectivity.ui.PingJob;
import org.eclipse.datatools.enablement.oda.ws.ui.util.Constants;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 */

public class WebServicePropertyPage extends DataSourceEditorPage
{
	private static final String HTTPHEAD = "http://"; //$NON-NLS-1$
	private WebServiceSelectionPageHelper pageHelper;

	/**
	 * 
	 */
	public WebServicePropertyPage( )
	{
		super( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceEditorPage#collectCustomProperties(java.util.Properties)
	 */
	public Properties collectCustomProperties( Properties profileProps )
	{
		if ( pageHelper == null )
			return profileProps;

		return pageHelper.collectCustomProperties( profileProps );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceEditorPage#createAndInitCustomControl(org.eclipse.swt.widgets.Composite,
	 *      java.util.Properties)
	 */
	protected void createAndInitCustomControl( Composite parent,
			Properties profileProps )
	{
		if ( pageHelper == null )
			pageHelper = new WebServiceSelectionPageHelper( this );

		pageHelper.createCustomControl( parent );
		pageHelper.initCustomControl( profileProps );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#refresh()
	 */
	public void refresh( )
	{
		enableAllControls( getControl( ), isSessionEditable( ) );
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceEditorPageCore#createTestConnectionRunnable(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	protected Runnable createTestConnectionRunnable( final IConnectionProfile profile )
    {
        return new Runnable() 
        {
            public void run() 
            {
                IConnection conn = PingJob.createTestConnection( profile );

                Throwable exception = PingJob.getTestConnectionException( conn );
                if( conn != null )
                    conn.close( );
                if( exception != null )
                {
                	Properties properties = collectProperties( );
					String wsdlUri = (String) properties.get( Constants.WSDL_URI );
					if ( wsdlUri != null && !wsdlUri.startsWith( HTTPHEAD ) )
					{
						properties.put( Constants.WSDL_URI, HTTPHEAD
								+ wsdlUri.trim( ) );
					}
                	profile.setBaseProperties( properties );
                	conn = PingJob.createTestConnection( profile );
                	if( PingJob.getTestConnectionException( conn ) == null )
                	{
                		pageHelper.setWsdlURIString( HTTPHEAD + wsdlUri.trim( ) );
                		exception = null;
                	}
                	if( conn != null )
                        conn.close();
                }
                PingJob.PingUIJob.showTestConnectionMessage( getShell( ), exception );
            }
        };
    }
}
