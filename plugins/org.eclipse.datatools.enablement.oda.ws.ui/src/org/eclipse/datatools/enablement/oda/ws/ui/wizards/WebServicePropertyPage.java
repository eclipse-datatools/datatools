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

import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 */

public class WebServicePropertyPage extends DataSourceEditorPage
{

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

}
