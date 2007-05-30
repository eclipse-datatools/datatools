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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.ws.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ws.ui.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSConsole;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSUIUtil;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * 
 */

public class SOAPResponsePage extends DataSetWizardPage
{

	private transient Button dftXSDRadio;
	private transient Button extXSDRadio;
	private transient Button dftXMLRadio;
	private transient Button extXMLRadio;

	private transient Text xmlFileURI;
	private transient Text xsdFileURI;
	private transient Text soapEndPoint;

	/**
	 * 
	 * @param pageName
	 */
	public SOAPResponsePage( String pageName )
	{
		super( pageName );
		setMessage( DEFAULT_MESSAGE );
	}

	private static String DEFAULT_MESSAGE = Messages.getString( "soapResponsePage.message.default" ); //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		setControl( createPageControl( parent ) );
		initializeControl( );
	}

	private Control createPageControl( Composite parent )
	{
		Composite composite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 1, false );
		layout.verticalSpacing = 20;
		composite.setLayout( layout );

		GridData layoutData = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		composite.setLayoutData( layoutData );

		setupXSDGroup( composite );
		setupXMLGroup( composite );

		return composite;
	}

	private void setupXSDGroup( Composite parent )
	{
		Group group = new Group( parent, SWT.SHADOW_ETCHED_IN );
		group.setText( Messages.getString( "soapResponsePage.group.schema" ) );//$NON-NLS-1$
		group.setLayout( new GridLayout( 3, false ) );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		group.setLayoutData( layoutData );

		setupDFTXSDRadio( group );
		setupEXTXSDRadio( group );
	}

	private void setupDFTXSDRadio( Composite parent )
	{
		dftXSDRadio = new Button( parent, SWT.RADIO );
		GridData layoutData = new GridData( );
		layoutData.horizontalSpan = 3;
		dftXSDRadio.setLayoutData( layoutData );
		dftXSDRadio.setText( Messages.getString( "soapResponsePage.radio.defaultSchema" ) );//$NON-NLS-1$
		dftXSDRadio.setSelection( true );
		dftXSDRadio.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				xsdFileURI.setText( WSUIUtil.EMPTY_STRING );
			}

		} );
	}

	private void setupEXTXSDRadio( Composite parent )
	{
		extXSDRadio = new Button( parent, SWT.RADIO );
		GridData layoutData = new GridData( );
		extXSDRadio.setLayoutData( layoutData );
		extXSDRadio.setText( Messages.getString( "soapResponsePage.radio.externalSchema" ) );//$NON-NLS-1$

		xsdFileURI = new Text( parent, SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		xsdFileURI.setLayoutData( layoutData );

		Button button = new Button( parent, SWT.NONE );
		layoutData = new GridData( );
		layoutData.widthHint = 100;
		button.setLayoutData( layoutData );
		button.setText( Messages.getString( "soapResponsePage.button.browse" ) );//$NON-NLS-1$
		button.addSelectionListener( new SelectionAdapter( ) {

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
						"*.xsd", "*.*" //$NON-NLS-1$//$NON-NLS-2$
				} );
				if ( xsdFileURI.getText( ) != null
						&& xsdFileURI.getText( ).trim( ).length( ) > 0 )
				{
					dialog.setFilterPath( xsdFileURI.getText( ) );
				}

				String selectedLocation = dialog.open( );
				if ( selectedLocation != null )
				{
					xsdFileURI.setText( selectedLocation );
					extXSDRadio.setSelection( true );
					dftXSDRadio.setSelection( false );
				}
			}

		} );
	}

	private void setupXMLGroup( Composite parent )
	{
		Group group = new Group( parent, SWT.SHADOW_ETCHED_IN );
		group.setLayout( new GridLayout( 2, false ) );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		group.setLayoutData( layoutData );
		group.setText( Messages.getString( "soapResponsePage.group.xml" ) );//$NON-NLS-1$

		setupDFTXMLRadio( group );
		setupEXTXMLRadio( group );
	}

	private void setupDFTXMLRadio( Composite parent )
	{
		dftXMLRadio = new Button( parent, SWT.RADIO );
		GridData layoutData = new GridData( );
		layoutData.horizontalSpan = 2;
		dftXMLRadio.setLayoutData( layoutData );
		dftXMLRadio.setText( Messages.getString( "soapResponsePage.radio.endPoint" ) );//$NON-NLS-1$
		dftXMLRadio.setSelection( true );
		dftXMLRadio.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				xmlFileURI.setText( WSUIUtil.EMPTY_STRING );
			}

		} );

		soapEndPoint = new Text( parent, SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		soapEndPoint.setLayoutData( layoutData );

		Button button = new Button( parent, SWT.NONE );
		layoutData = new GridData( );
		layoutData.widthHint = 100;
		button.setLayoutData( layoutData );
		button.setText( Messages.getString( "soapResponsePage.button.connect" ) );//$NON-NLS-1$
		button.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				try
				{
					saveToModel( );
					WSConsole.getInstance( ).createXMLTempFileURI( );
					setMessage( DEFAULT_MESSAGE );
				}
				catch ( OdaException e1 )
				{
					setMessage( e1.getMessage( ), IMessageProvider.ERROR );
				}
			}
		} );
	}

	private void setupEXTXMLRadio( Composite parent )
	{
		GridData layoutData;
		extXMLRadio = new Button( parent, SWT.RADIO );
		layoutData = new GridData( );
		layoutData.horizontalSpan = 2;
		extXMLRadio.setLayoutData( layoutData );
		extXMLRadio.setText( Messages.getString( "soapResponsePage.radio.externalXML" ) );//$NON-NLS-1$

		xmlFileURI = new Text( parent, SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		xmlFileURI.setLayoutData( layoutData );

		Button button = new Button( parent, SWT.NONE );
		layoutData = new GridData( );
		layoutData.widthHint = 100;
		button.setLayoutData( layoutData );
		button.setText( Messages.getString( "soapResponsePage.button.browse" ) );//$NON-NLS-1$
		button.addSelectionListener( new SelectionAdapter( ) {

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
						"*.xml", "*.*" //$NON-NLS-1$ //$NON-NLS-2$
				} );
				if ( xmlFileURI.getText( ) != null
						&& xmlFileURI.getText( ).trim( ).length( ) > 0 )
				{
					dialog.setFilterPath( xmlFileURI.getText( ) );
				}

				String selectedLocation = dialog.open( );
				if ( selectedLocation != null )
				{
					xmlFileURI.setText( selectedLocation );
					extXMLRadio.setSelection( true );
					dftXMLRadio.setSelection( false );
				}
			}

		} );
	}

	/**
	 * Initializes the page control with the last edited data set design.
	 */
	private void initializeControl( )
	{
		initWSConsole( );
		initFromModel( );
	}

	private void initWSConsole( )
	{
		if ( !WSConsole.getInstance( ).isSessionOK( ) )
			WSConsole.getInstance( ).start( getInitializationDesign( ) );
	}

	private void initFromModel( )
	{
		String xsd = WSConsole.getInstance( )
				.getPropertyValue( Constants.XSD_FILE_URI );
		if ( !WSUIUtil.isNull( xsd ) )
		{
			dftXSDRadio.setSelection( false );
			extXSDRadio.setSelection( true );
			xsdFileURI.setText( xsd );
		}

		String xml = WSConsole.getInstance( )
				.getPropertyValue( Constants.XML_FILE_URI );
		if ( !WSUIUtil.isNull( xml ) )
		{
			dftXMLRadio.setSelection( false );
			extXMLRadio.setSelection( true );
			xmlFileURI.setText( xml );
		}

		soapEndPoint.setText( WSUIUtil.getNonNullString( WSConsole.getInstance( )
				.getPropertyValue( Constants.SOAP_ENDPOINT ) ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected DataSetDesign collectDataSetDesign( DataSetDesign design )
	{
		savePage( design );
		return design;
	}

	private void savePage( DataSetDesign design )
	{
		if ( !WSConsole.getInstance( ).isSessionOK( ) )
			return;

		design.getPublicProperties( ).setProperty( Constants.XML_FILE_URI,
				WSConsole.getInstance( )
						.getPropertyValue( Constants.XML_FILE_URI ) );
		design.getPublicProperties( ).setProperty( Constants.XSD_FILE_URI,
				WSConsole.getInstance( )
						.getPropertyValue( Constants.XSD_FILE_URI ) );

		// TODO: necessary?
		design.getDataSourceDesign( )
				.getPublicProperties( )
				.setProperty( Constants.SOAP_ENDPOINT,
						WSConsole.getInstance( )
								.getPropertyValue( Constants.SOAP_ENDPOINT ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#refresh(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected void refresh( DataSetDesign dataSetDesign )
	{
		super.refresh( dataSetDesign );

		if ( isDirty( dataSetDesign ) )
			refresh( );
	}

	// TODO
	private boolean isDirty( DataSetDesign dataSetDesign )
	{
		return false;
	}

	void refresh( )
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#canLeave()
	 */
	protected boolean canLeave( )
	{
		saveToModel( );
		return super.canLeave( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage( )
	{
		return isPageComplete( );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage( )
	{
		saveToModel( );

		IWizardPage page = super.getNextPage( );
		if ( page instanceof XMLTableMappingPage )
			( (XMLTableMappingPage) page ).refresh( );

		return page;
	}

	private void saveToModel( )
	{
		WSConsole.getInstance( ).setPropertyValue( Constants.XSD_FILE_URI,
				xsdFileURI.getText( ) );
		WSConsole.getInstance( ).setPropertyValue( Constants.SOAP_ENDPOINT,
				soapEndPoint.getText( ) );
		WSConsole.getInstance( ).setPropertyValue( Constants.XML_FILE_URI,
				xmlFileURI.getText( ) );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#cleanup()
	 */
	protected void cleanup( )
	{
		WSConsole.getInstance( ).terminateSession( );
	}

}
