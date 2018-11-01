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
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.datatools.connectivity.ui.PingJob;
import org.eclipse.datatools.enablement.oda.ws.ui.util.Constants;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 */

public class WebServiceSelectionPage extends DataSourceWizardPage
{
	private static final String HTTPHEAD = "http://"; //$NON-NLS-1$
	private WebServiceSelectionPageHelper pageHelper;
	private Properties wsProperties;

	/**
	 * 
	 * @param pageName
	 */
	public WebServiceSelectionPage( String pageName )
	{
		super( pageName );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		if ( pageHelper == null )
			pageHelper = new WebServiceSelectionPageHelper( this );

		pageHelper.createCustomControl( parent );
		pageHelper.initCustomControl( wsProperties );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#setInitialProperties(java.util.Properties)
	 */
	public void setInitialProperties( Properties dataSourceProps )
	{
		wsProperties = dataSourceProps;
		if ( pageHelper == null )
			return; // ignore, wait till createPageCustomControl to initialize

		pageHelper.initCustomControl( wsProperties );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#collectCustomProperties()
	 */
	public Properties collectCustomProperties( )
	{
		if ( pageHelper != null )
			return pageHelper.collectCustomProperties( wsProperties );

		return ( wsProperties != null ) ? wsProperties : new Properties( );
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
	 * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceWizardPageCore#createTestConnectionRunnable(org.eclipse.datatools.connectivity.IConnectionProfile)
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
                	Properties properties = collectCustomProperties( );
                	String wsdlUri = ( String )properties.get( Constants.WSDL_URI );
                	if( wsdlUri != null && !wsdlUri.startsWith( HTTPHEAD ) )
                	{
                		properties.put( Constants.WSDL_URI, HTTPHEAD + wsdlUri.trim( ) );
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
