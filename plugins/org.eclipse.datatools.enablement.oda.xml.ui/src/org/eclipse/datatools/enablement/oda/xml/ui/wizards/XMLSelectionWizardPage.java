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
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * xml selection page
 */

public class XMLSelectionWizardPage extends DataSourceWizardPage
{

	private XMLSelectionPageHelper m_pageHelper;
	private Properties m_folderProperties;

	public XMLSelectionWizardPage( String pageName )
	{
		super( pageName );
        setMessage( XMLSelectionPageHelper.DEFAULT_MESSAGE );
		// page title is specified in extension manifest
	}

	public void createPageCustomControl( Composite parent )
	{
		if ( m_pageHelper == null )
			m_pageHelper = new XMLSelectionPageHelper( this );
		m_pageHelper.createCustomControl( parent );
		m_pageHelper.initCustomControl( m_folderProperties ); // in case init was called before create 

	}

	public void setInitialProperties( Properties dataSourceProps )
	{
		m_folderProperties = dataSourceProps;
		if ( m_pageHelper == null )
			return; // ignore, wait till createPageCustomControl to initialize
		m_pageHelper.initCustomControl( m_folderProperties );
	}

	public Properties collectCustomProperties( )
	{
		if ( m_pageHelper != null )
			return m_pageHelper.collectCustomProperties( m_folderProperties );

		return ( m_folderProperties != null ) ? m_folderProperties
				: new Properties( );
	}
	
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	public void setVisible( boolean visible )
	{
		super.setVisible( visible );
		getControl( ).setFocus( );
	}

}
