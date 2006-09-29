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

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * xml selection page helper class
 * 
 */
public class XMLSelectionPageHelper
{
    private WizardPage m_wizardPage;
    private PreferencePage m_propertyPage;

    private transient Text m_folderLocation = null;
    private transient Text m_schemaLocation = null;

	private transient Button browseFolderButton = null;

    static final String DEFAULT_MESSAGE = 
        Messages.getString( "wizard.defaultMessage.selectFolder" ); //$NON-NLS-1$

    private static final String EMPTY_STRING = "";
    
    XMLSelectionPageHelper( WizardPage page )
    {
        m_wizardPage = page;
    }

    XMLSelectionPageHelper( PreferencePage page )
    {
        m_propertyPage = page;
    }

    void createCustomControl( Composite parent )
    {
    	this.setMessage( DEFAULT_MESSAGE );
		Composite composite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.numColumns = 2;
		composite.setLayout( layout );

		GridData data = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		data.horizontalSpan = 2;
		final Label label1 = new Label( composite, SWT.NONE );
		label1.setText( Messages.getString( "lable.selectXmlFile" ) ); //$NON-NLS-1$
		label1.setLayoutData( data );

		// GridData data;
		setupXMLFolderLocation( composite );

		data = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		data.horizontalSpan = 2;
		final Label label3 = new Label( composite, SWT.NONE );
		label3.setText( Messages.getString( "lable.selectXmlSchmaFile" ) ); //$NON-NLS-1$
		label3.setLayoutData( data );

		setupSchemaFolderLocation( composite );
		
		XMLRelationInfoUtil.setSystemHelp( composite,
				IHelpConstants.CONEXT_ID_DATASOURCE_XML );
    }
    
    String getFolderLocation()
    {
        if( m_folderLocation == null )
            return EMPTY_STRING;
        return m_folderLocation.getText();
    }

    String getSchemaFileLocation()
    {
        if( m_schemaLocation == null )
            return EMPTY_STRING;
        return m_schemaLocation.getText( );
    }

    Properties collectCustomProperties( Properties props )
    {
        if( props == null )
            props = new Properties();
        
        // set custom driver specific properties
		props.setProperty( Constants.CONST_PROP_FILELIST,
				getFolderLocation( ) );
		props.setProperty( Constants.CONST_PROP_SCHEMA_FILELIST,
				getSchemaFileLocation( ) );
		return props;
    }
    
    void initCustomControl( Properties profileProps )
    {
        if( profileProps == null || profileProps.isEmpty() || 
            m_folderLocation == null )
            return;     // nothing to initialize
        
        String folderPath = profileProps.getProperty( Constants.CONST_PROP_FILELIST );
        if( folderPath == null )
            folderPath = EMPTY_STRING;
        m_folderLocation.setText( folderPath );

        String schemaPath = profileProps.getProperty( Constants.CONST_PROP_SCHEMA_FILELIST );
        if( schemaPath == null )
        	schemaPath = EMPTY_STRING;
        m_schemaLocation.setText( schemaPath );

    }


	/**
	 * @param composite
	 */
	private void setupXMLFolderLocation( Composite composite )
	{

		GridData data = new GridData( GridData.FILL_HORIZONTAL );
		m_folderLocation = new Text( composite, SWT.BORDER );
		m_folderLocation.setLayoutData( data );

    	browseFolderButton = new Button( composite, SWT.NONE );
		browseFolderButton.setText( Messages.getString( "file.choose" ) ); //$NON-NLS-1$
		browseFolderButton.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				FileDialog dialog = new FileDialog( PlatformUI.getWorkbench( )

				.getDisplay( ).getActiveShell( ), SWT.OPEN );
				dialog.setFilterExtensions( new String[]{
						"*.xml", "*.*"
				} );
				if ( m_folderLocation.getText( ) != null
						&& m_folderLocation.getText( ).trim( ).length( ) > 0 )
				{
					dialog.setFilterPath( m_folderLocation.getText( ) );
				}

				String selectedLocation = dialog.open( );
				if ( selectedLocation != null )
				{
					m_folderLocation.setText( selectedLocation );
				}
			}

		} );
	}

	/**
	 * @param composite
	 */
	private void setupSchemaFolderLocation( Composite composite )
	{

		GridData data = new GridData( GridData.FILL_HORIZONTAL );

		m_schemaLocation = new Text( composite, SWT.BORDER );
		m_schemaLocation.setLayoutData( data );

		browseFolderButton = new Button( composite, SWT.NONE );
		browseFolderButton.setText( Messages.getString( "file.choose" ) ); //$NON-NLS-1$
		browseFolderButton.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				FileDialog dialog = new FileDialog( PlatformUI.getWorkbench( )
						.getDisplay( )
						.getActiveShell( ), SWT.OPEN );
				dialog.setFilterExtensions( new String[]{
						"*.xsd", "*.*"
				} );

				if ( m_schemaLocation.getText( ) != null
						&& m_schemaLocation.getText( ).trim( ).length( ) > 0 )
				{
					dialog.setFilterPath( m_schemaLocation.getText( ) );
				}

				String selectedLocation = dialog.open( );
				if ( selectedLocation != null )
				{
					m_schemaLocation.setText( selectedLocation );
				}
			}

		} );
	}
	
	/**
	 * set message
	 * @param message
	 */
	private void setMessage( String message )
	{
		if ( m_wizardPage != null )
			m_wizardPage.setMessage( message );
		else if ( m_propertyPage != null )
			m_propertyPage.setMessage( message );
	}
}