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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.ws.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ws.ui.util.Constants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.IHelpConstants;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSConsole;
import org.eclipse.datatools.enablement.oda.ws.ui.util.WSUIUtil;
import org.eclipse.datatools.enablement.oda.ws.util.WSUtil;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
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
	private transient Button resXSDRadio;
	private transient Button extXSDRadio;
	private transient Button dftXMLRadio;
	private transient Button extXMLRadio;

	private Button XSDBrowseBtn, connectBtn, browseXMLBtn;

	private transient Text xmlFileURI;
	private transient Text xsdFileURI;
	private transient Text soapEndPoint;

	private boolean saved = false;
	private boolean initialized = false;

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
		WSUIUtil.setSystemHelp( getControl( ), IHelpConstants.CONEXT_ID_WS_SOAP_RESPONSE );
		initialized = true;
	}

	private Control createPageControl( Composite parent )
	{
		ScrolledComposite sComposite = new ScrolledComposite( parent,
				SWT.H_SCROLL | SWT.V_SCROLL );
		sComposite.setLayout( new GridLayout( ) );
		sComposite.setLayoutData( new GridData( GridData.FILL_BOTH ) );
		sComposite.setMinWidth( 600 );
		sComposite.setExpandHorizontal( true );

		Composite composite = new Composite( sComposite, SWT.NONE );
		GridLayout layout = new GridLayout( 1, false );
		layout.verticalSpacing = 20;
		composite.setLayout( layout );

		GridData layoutData = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		composite.setLayoutData( layoutData );

		setupXSDGroup( composite );
		setupXMLGroup( composite );

		Point size = composite.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		composite.setSize( size.x, size.y );

		sComposite.setContent( composite );
		setControl( sComposite );

		return sComposite;
	}

	private void setupXSDGroup( Composite parent )
	{
		Group group = new Group( parent, SWT.SHADOW_ETCHED_IN );
		group.setText( Messages.getString( "soapResponsePage.group.schema" ) );//$NON-NLS-1$
		group.setLayout( new GridLayout( 3, false ) );
		GridData layoutData = new GridData( GridData.FILL_HORIZONTAL );
		group.setLayoutData( layoutData );

		setupDFTXSDRadio( group );
		setupRESXSDRadio( group );
		setupEXTXSDRadio( group );
		
		initXSDGroupStatus( );
	}
	
	private void initXSDGroupStatus( )
	{
		if ( getInitializationDesign( ) == null
				|| getInitializationDesign( ).getPrivateProperties( ) == null )
		{
			setToDefaultXSDSelection( );
			return;
		}

		String schema = getInitializationDesign( ).getPrivateProperties( )
				.getProperty( Constants.RESPONSE_SCHEMA );
		if ( schema != null )
		{
			if ( Constants.FROM_WS_SERVER.equals( schema ) )
			{
				resXSDRadio.setSelection( true );
				WSConsole.getInstance( ).setPropertyValue( Constants.RESPONSE_SCHEMA, Constants.FROM_WS_SERVER );
			}
			else if ( Constants.FROM_EXTERNAL_SCHEMA.equals( schema ) )
			{
				extXSDRadio.setSelection( true );
				WSConsole.getInstance( ).setPropertyValue( Constants.RESPONSE_SCHEMA, Constants.FROM_EXTERNAL_SCHEMA );
			}
			else
			{
				setToDefaultXSDSelection( );
			}
		}
		else
		{
			setToDefaultXSDSelection( );
		}
		
		updateXSDGroupControl( );

	}

	private void setToDefaultXSDSelection( )
	{
		dftXSDRadio.setSelection( true );
		updateXSDGroupControl( );
		WSConsole.getInstance( )
				.setPropertyValue( Constants.RESPONSE_SCHEMA,
						Constants.FROM_WSDL );
	}

	private void setupDFTXSDRadio( Composite parent )
	{
		dftXSDRadio = new Button( parent, SWT.RADIO );
		GridData layoutData = new GridData( );
		layoutData.horizontalSpan = 3;
		dftXSDRadio.setLayoutData( layoutData );
		dftXSDRadio.setText( Messages.getString( "soapResponsePage.radio.defaultSchema" ) );//$NON-NLS-1$
		
		dftXSDRadio.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				WSConsole.getInstance( ).setPropertyValue( Constants.XML_TEMP_FILE_URI, "" ); //$NON-NLS-1$
				WSConsole.getInstance( ).setPropertyValue( Constants.RESPONSE_SCHEMA, Constants.FROM_WSDL );
				updateXSDGroupControl( );
				updatePageStatus( );
			}

		} );
	}
	
	private void setupRESXSDRadio( Composite parent )
	{
		resXSDRadio = new Button( parent, SWT.RADIO );
		GridData layoutData = new GridData( );
		layoutData.horizontalSpan = 3;
		resXSDRadio.setLayoutData( layoutData );
		resXSDRadio.setText( Messages.getString( "soapResponsePage.radio.reponseSchema" ) );//$NON-NLS-1$
		resXSDRadio.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				WSConsole.getInstance( ).setPropertyValue( Constants.XML_TEMP_FILE_URI, "" ); //$NON-NLS-1$
				WSConsole.getInstance( ).setPropertyValue( Constants.RESPONSE_SCHEMA, Constants.FROM_WS_SERVER );
				updateXSDGroupControl( );
				updatePageStatus( );
			}

		} );
	}

	private void setupEXTXSDRadio( Composite parent )
	{
		extXSDRadio = new Button( parent, SWT.RADIO );
		GridData layoutData = new GridData( );
		extXSDRadio.setLayoutData( layoutData );
		extXSDRadio.setText( Messages.getString( "soapResponsePage.radio.externalSchema" ) );//$NON-NLS-1$
		extXSDRadio.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				WSConsole.getInstance( ).setPropertyValue( Constants.RESPONSE_SCHEMA, Constants.FROM_EXTERNAL_SCHEMA );
				updatePageStatus( );
			}

		} );
		xsdFileURI = new Text( parent, SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		xsdFileURI.setLayoutData( layoutData );
		xsdFileURI.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				updatePageStatus( );
			}
		} );
		XSDBrowseBtn = new Button( parent, SWT.NONE );
		layoutData = new GridData( );
		XSDBrowseBtn.setLayoutData( layoutData );
		XSDBrowseBtn.setText( Messages.getString( "soapResponsePage.button.browse1" ) );//$NON-NLS-1$
		XSDBrowseBtn.addSelectionListener( new SelectionAdapter( ) {

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
				}
				updateXSDGroupControl( );
			}

		} );
	}
	
	private void updateXSDGroupControl( )
	{
		xsdFileURI.setEnabled( extXSDRadio.getSelection( ) );
		XSDBrowseBtn.setEnabled( extXSDRadio.getSelection( ) );
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
		
		initXMLRadioGroupStatus( );
	}
	
	private void initXMLRadioGroupStatus( )
	{
		if ( getInitializationDesign( ) == null
				|| getInitializationDesign( ).getPrivateProperties( ) == null )
		{
			updateSOAPEndPointSelection( true );
			return;
		}

		String useSOAPEndPoint = getInitializationDesign( ).getPrivateProperties( )
				.getProperty( Constants.RESPONSE_USE_SOAPENDPOINT );
		if ( useSOAPEndPoint != null )
		{
			updateSOAPEndPointSelection( Boolean.valueOf( useSOAPEndPoint ) );
		}
		else
		{
			updateSOAPEndPointSelection( true );
		}

	}

	private void updateSOAPEndPointSelection( boolean SOAPEndPointSelected )
	{
		dftXMLRadio.setSelection( SOAPEndPointSelected );
		extXMLRadio.setSelection( !SOAPEndPointSelected );
		WSConsole.getInstance( )
				.setPropertyValue( Constants.RESPONSE_USE_SOAPENDPOINT,
						String.valueOf( SOAPEndPointSelected ) );
	}

	private void setupDFTXMLRadio( Composite parent )
	{
		dftXMLRadio = new Button( parent, SWT.RADIO );
		GridData layoutData = new GridData( );
		layoutData.horizontalSpan = 2;
		dftXMLRadio.setLayoutData( layoutData );
		dftXMLRadio.setText( Messages.getString( "soapResponsePage.radio.endPoint" ) );//$NON-NLS-1$

		dftXMLRadio.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				updateXMLControlStatus( );
			}

		} );

		soapEndPoint = new Text( parent, SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		soapEndPoint.setLayoutData( layoutData );

		connectBtn = new Button( parent, SWT.NONE );
		layoutData = new GridData( );
		connectBtn.setLayoutData( layoutData );
		connectBtn.setText( Messages.getString( "soapResponsePage.button.connect" ) );//$NON-NLS-1$
		connectBtn.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
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

		soapEndPoint.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				connectBtn.setEnabled( soapEndPoint.getText( ).trim( ).length( ) > 0 );
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
		extXMLRadio.addSelectionListener( new SelectionAdapter( ) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected( SelectionEvent e )
			{
				updateXMLControlStatus( );
			}

		} );


		xmlFileURI = new Text( parent, SWT.BORDER );
		layoutData = new GridData( GridData.FILL_HORIZONTAL );
		xmlFileURI.setLayoutData( layoutData );

		browseXMLBtn = new Button( parent, SWT.NONE );
		layoutData = new GridData( );
		browseXMLBtn.setLayoutData( layoutData );
		browseXMLBtn.setText( Messages.getString( "soapResponsePage.button.browse2" ) );//$NON-NLS-1$
		browseXMLBtn.addSelectionListener( new SelectionAdapter( ) {

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
				}
			}

		} );
	}
	
	private void updateXMLControlStatus( )
	{
		boolean defaultXMLSelection = dftXMLRadio.getSelection( );
		soapEndPoint.setEnabled( defaultXMLSelection );
		connectBtn.setEnabled( defaultXMLSelection
				&& soapEndPoint.getText( ).trim( ).length( ) > 0 );

		xmlFileURI.setEnabled( !defaultXMLSelection );
		browseXMLBtn.setEnabled( !defaultXMLSelection );
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
		if ( !WSUtil.isNull( xsd ) )
		{
			dftXSDRadio.setSelection( false );
			extXSDRadio.setSelection( true );
			xsdFileURI.setText( xsd );
		}

		String xml = WSConsole.getInstance( )
				.getPropertyValue( Constants.XML_FILE_URI );
		if ( !WSUtil.isNull( xml ) )
		{
			xmlFileURI.setText( xml );
		}

		String value = WSConsole.getInstance( )
				.getPropertyValue( Constants.SOAP_ENDPOINT );
		if ( value != null )
			soapEndPoint.setText( value );
		
		updateXMLControlStatus( );
		
		saved = false;
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
		if ( !initialized
				|| dftXMLRadio == null
				|| !WSConsole.getInstance( ).isSessionOK( ) )
			return;

		// ok being clicked without leaving the page
		if ( isControlCreated( ) && !saved )
			saveToModel( );

		design.getPrivateProperties( ).setProperty( Constants.XML_FILE_URI,
				WSConsole.getInstance( )
						.getPropertyValue( Constants.XML_FILE_URI ) );
		design.getPrivateProperties( ).setProperty( Constants.XSD_FILE_URI,
				WSConsole.getInstance( )
						.getPropertyValue( Constants.XSD_FILE_URI ) );
		design.getPrivateProperties( ).setProperty( Constants.RESPONSE_SCHEMA,
				WSConsole.getInstance( )
						.getPropertyValue( Constants.RESPONSE_SCHEMA ) );
		design.getPrivateProperties( )
				.setProperty( Constants.RESPONSE_USE_SOAPENDPOINT,
						String.valueOf( dftXMLRadio.getSelection( ) ) );

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
		if( extXSDRadio.getSelection( ) && WSUtil.isNull( xsdFileURI.getText( ) ) )
		{
			return false;
		}
		return isPageComplete( );
	}
	
	/**
	 * 
	 * @return
	 */
	public void updatePageStatus( )
	{
		if( extXSDRadio.getSelection( ) && WSUtil.isNull( xsdFileURI.getText( ) ) )
		{
			setPageComplete( false );
		}
		setPageComplete( true );
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
		if ( !initialized || extXSDRadio == null || extXSDRadio.isDisposed( ) )
			return;

		if ( extXSDRadio.getSelection( ) && xsdFileURI != null )
		{
			WSConsole.getInstance( ).setPropertyValue( Constants.XSD_FILE_URI,
					xsdFileURI.getText( ) );
		}
		else
		{
			WSConsole.getInstance( ).setPropertyValue( Constants.XSD_FILE_URI,
					WSUtil.EMPTY_STRING );
		}

		if ( soapEndPoint != null )
		{
			WSConsole.getInstance( ).setPropertyValue( Constants.SOAP_ENDPOINT,
					soapEndPoint.getText( ) );
		}

		if ( xmlFileURI != null )
		{
			WSConsole.getInstance( ).setPropertyValue( Constants.XML_FILE_URI,
					xmlFileURI.getText( ) );
		}
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
