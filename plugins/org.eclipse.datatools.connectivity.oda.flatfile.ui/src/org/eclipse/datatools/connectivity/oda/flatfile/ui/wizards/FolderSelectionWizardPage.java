/*
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.swt.widgets.Composite;

public class FolderSelectionWizardPage extends DataSourceWizardPage
{

	private FolderSelectionPageHelper pageHelper;
	private Properties folderProperties;

	public FolderSelectionWizardPage( String pageName )
	{
		super( pageName );
		setMessage( FolderSelectionPageHelper.DEFAULT_MESSAGE );
		// page title is specified in extension manifest
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		if ( pageHelper == null )
			pageHelper = new FolderSelectionPageHelper( this );
		pageHelper.createCustomControl( parent );
		pageHelper.initCustomControl( folderProperties ); // in case init was called before create 

		/* 
		 * Optionally hides the Test Connection button, using
		 *      setPingButtonVisible( false );  
		 */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#initPageCustomControl(java.util.Properties)
	 */
	public void setInitialProperties( Properties dataSourceProps )
	{
		folderProperties = dataSourceProps;
		if ( pageHelper == null )
			return; // ignore, wait till createPageCustomControl to initialize
		pageHelper.initCustomControl( folderProperties );
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#collectCustomProperties()
	 */
	public Properties collectCustomProperties( )
	{
		/* 
		 * Optionally assign a custom designer state, for inclusion
		 * in the ODA design session response, using
		 * setResponseDesignerState( DesignerState customState ); 
		 */

		if ( pageHelper != null )
			return pageHelper.collectCustomProperties( folderProperties );

		return ( folderProperties != null ) ? folderProperties
				: new Properties( );
	}

}
