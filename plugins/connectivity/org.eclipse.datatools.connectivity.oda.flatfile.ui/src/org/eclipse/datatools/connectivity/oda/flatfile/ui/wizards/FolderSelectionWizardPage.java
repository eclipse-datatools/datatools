/*
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.datatools.connectivity.oda.flatfile.ui.i18n.Messages;
import org.eclipse.swt.widgets.Composite;

public class FolderSelectionWizardPage extends DataSourceWizardPage
{

	private FolderSelectionPageHelper pageHelper;
	private Properties folderProperties;

	public FolderSelectionWizardPage( String pageName )
	{
		super( pageName );
		setMessage( Messages.getString( "wizard.WizardTitle.DEFAULT_MESSAGE" ) );  //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		if ( pageHelper == null )
			pageHelper = new FolderSelectionPageHelper( this );
		pageHelper.setResourceIdentifiers( getHostResourceIdentifiers( ) );
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
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#refresh()
     */
    public void refresh()
    {
        // enable/disable all controls on page in respect of the editable session state
        enableAllControls( getControl(), isSessionEditable() );
        
        if ( pageHelper != null && isSessionEditable() )
        	pageHelper.resetUIStatus( );
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
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	public void setVisible( boolean visible )
	{
		super.setVisible( visible );
		getControl( ).setFocus( );
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceWizardPageCore#createTestConnectionRunnable(org.eclipse.datatools.connectivity.IConnectionProfile)
	 */
	protected Runnable createTestConnectionRunnable( IConnectionProfile profile )
	{
		return pageHelper.createTestConnectionRunnable( profile );
	}

}
