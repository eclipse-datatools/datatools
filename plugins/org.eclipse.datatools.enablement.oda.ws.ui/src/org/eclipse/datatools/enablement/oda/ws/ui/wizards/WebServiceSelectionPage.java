/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.ws.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 */

public class WebServiceSelectionPage extends DataSourceWizardPage
{

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

}
