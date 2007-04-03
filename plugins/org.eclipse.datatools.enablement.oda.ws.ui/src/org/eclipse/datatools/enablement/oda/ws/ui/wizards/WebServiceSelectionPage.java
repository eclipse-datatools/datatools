/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
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

	public WebServiceSelectionPage( String pageName )
	{
		super( pageName );
		// TODO Auto-generated constructor stub
	}

	public void createPageCustomControl( Composite parent )
	{
		if ( pageHelper == null )
			pageHelper = new WebServiceSelectionPageHelper( this );

		pageHelper.createCustomControl( parent );
		pageHelper.initCustomControl( wsProperties );
	}

	public void setInitialProperties( Properties dataSourceProps )
	{
		wsProperties = dataSourceProps;
		if ( pageHelper == null )
			return; // ignore, wait till createPageCustomControl to initialize
		
		pageHelper.initCustomControl( wsProperties );
	}

	public Properties collectCustomProperties( )
	{
		if ( pageHelper != null )
			return pageHelper.collectCustomProperties( wsProperties );

		return ( wsProperties != null ) ? wsProperties : new Properties( );
	}

}
