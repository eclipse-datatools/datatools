/*
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.ui.nls.TextProcessorWrapper;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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
    private static final String AUTO_ENCODING = Messages.getString( "wizard.autoEncoding" ); //$NON-NLS-1$
	private WizardPage m_wizardPage;
    private PreferencePage m_propertyPage;

    private transient Text m_folderLocation = null;
    private transient Text m_schemaLocation = null;

	private transient Button browseFolderButton = null;
	private transient Combo encodingCombo = null;

    static final String DEFAULT_MESSAGE = 
        Messages.getString( "wizard.defaultMessage.selectFolder" ); //$NON-NLS-1$

    private static final String EMPTY_STRING = ""; //$NON-NLS-1$
    
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
		
		data = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		data.horizontalSpan = 2;
		final Label label2 = new Label( composite, SWT.NONE );
		label2.setText( Messages.getString( "label.selectEncoding" ) ); //$NON-NLS-1$
		label2.setLayoutData( data );
		setupEncodingControl( composite );
		
		XMLRelationInfoUtil.setSystemHelp( getControl(),
				IHelpConstants.CONEXT_ID_DATASOURCE_XML );
    }
    
    String getFolderLocation()
    {
        if( m_folderLocation == null )
            return EMPTY_STRING;
        return getFolderLocationString( );
    }

    String getSchemaFileLocation()
    {
        if( m_schemaLocation == null )
            return EMPTY_STRING;
        return getSchemaLocationString( );
    }

    String getEncoding( )
	{
		if ( encodingCombo == null
				|| encodingCombo.getText( ).equals( AUTO_ENCODING ) )
			return EMPTY_STRING;
		else
			return encodingCombo.getText( );
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
		props.setProperty( Constants.CONST_PROP_ENCODINGLIST, getEncoding( ) );
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
        
        String encoding = profileProps.getProperty( Constants.CONST_PROP_ENCODINGLIST );
        if ( encoding == null )
		{//use auto encoding
			encodingCombo.select( 0 );
		}
		else
		{
			encodingCombo.select( getIndex( encoding ) );
		}
        
        setFolderLocation( folderPath );
        String schemaPath = profileProps.getProperty( Constants.CONST_PROP_SCHEMA_FILELIST );
        if( schemaPath == null )
        	schemaPath = EMPTY_STRING;
        setSchemaLocation( schemaPath );

    }

    private int getIndex( String encoding )
	{
		return Arrays.binarySearch( encodingCombo.getItems( ), encoding );
	}

	/**
     * @param composite
     */
    private void setupEncodingControl( Composite composite )
    {
    	GridData data = new GridData( GridData.FILL_HORIZONTAL );
    	encodingCombo = new Combo(composite, SWT.READ_ONLY);
    	encodingCombo.setLayoutData( data );
    	encodingCombo.add( AUTO_ENCODING );
    	for ( Iterator i = Charset.availableCharsets( ).keySet( ).iterator( ); i.hasNext( ); )
		{
			String	encoding = (String) i.next( );
			encodingCombo.add( encoding );			
		}
    	encodingCombo.select( 0 );
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
						"*.xml", "*.*" //$NON-NLS-1$ //$NON-NLS-2$
				} );
                String folderLocationValue = getFolderLocationString( );
				if ( folderLocationValue != null
						&& folderLocationValue.trim( ).length( ) > 0 )
				{
					dialog.setFilterPath( folderLocationValue );
				}

				String selectedLocation = dialog.open( );
				if ( selectedLocation != null )
				{
					setFolderLocation( selectedLocation );
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
						"*.xsd", "*.*" //$NON-NLS-1$ //$NON-NLS-2$
				} );

                String schemaLocationValue = getSchemaLocationString( );
				if ( schemaLocationValue != null
						&& schemaLocationValue.trim( ).length( ) > 0 )
				{
					dialog.setFilterPath( schemaLocationValue );
				}

				String selectedLocation = dialog.open( );
				if ( selectedLocation != null )
				{
					setSchemaLocation( selectedLocation );
				}
			}

		} );
	}
	
	/**
	 * 
	 * @param text
	 */
	private void setFolderLocation( String text )
	{
		m_folderLocation.setText( TextProcessorWrapper.process( text ) );
	}
	
	/**
	 * 
	 * @return
	 */
	private String getFolderLocationString( )
	{
        return TextProcessorWrapper.deprocess( m_folderLocation.getText( ) );
	}
	
	/**
	 * 
	 * @param text
	 */
	private void setSchemaLocation( String text )
	{
		m_schemaLocation.setText( TextProcessorWrapper.process( text ) );
	}
	
	/**
	 * 
	 * @return
	 */
	private String getSchemaLocationString( )
	{
        return TextProcessorWrapper.deprocess( m_schemaLocation.getText( ) );
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
    
    private Control getControl()
    {
        if ( m_wizardPage != null )
            return m_wizardPage.getControl();
        assert( m_propertyPage != null );
        return m_propertyPage.getControl();
    }

}
