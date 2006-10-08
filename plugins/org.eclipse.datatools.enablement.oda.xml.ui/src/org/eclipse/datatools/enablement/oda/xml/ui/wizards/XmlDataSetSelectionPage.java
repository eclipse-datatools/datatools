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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * Select xml data set page
 */

public class XmlDataSetSelectionPage extends DataSetWizardPage
{

	private static String DEFAULT_MESSAGE = Messages.getString( "wizard.defaultMessage.selectFile" ); //$NON-NLS-1$
	private static final int ERROR_FOLDER = 1;
	private static final int ERROR_EMPTY_PATH = 2;

	private transient Text folderLocation;
	private transient Button previewNumCheckBox;
	private transient Button useDataSourceXMLDataCheckBox;
	private transient Text numberText;
	private transient Button browseFolderButton;
	private boolean selected = false;
	private boolean useDataSourceXMLDataSelected = true;
	
	private String fileLocation;
	
	private final int UNUSED_ROW_CACHE = -1;
	private final int INVALID_ROW_NUMBER = -2;
	private final int NEGATIVE_ROW_NUMBER = -4;

	private int maxRow = UNUSED_ROW_CACHE;

	/**
	 * @param string
	 */
	public XmlDataSetSelectionPage( )
	{
		this( Messages.getString( "wizard.title.newDataSet" ) );
	}

	/**
	 * @param pageName
	 */
	public XmlDataSetSelectionPage( String pageName )
	{
		super( pageName );
		setTitle( pageName );
		setMessage( DEFAULT_MESSAGE );
		setPageComplete( false );
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		setControl( createPageControl( parent ) );
		initializeControl();
		
		XMLRelationInfoUtil.setSystemHelp( getControl( ),
				IHelpConstants.CONEXT_ID_DATASET_XML_SAMPLE );
	}

	/**
	 * 
	 * @param parent
	 * @return
	 */
	public Control createPageControl( Composite parent )
	{
		initializeDialogUnits( parent );
		
		Composite composite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( 3, false );
		composite.setLayout( layout );

		GridData data = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		data.horizontalSpan = 3;
		final Label label1 = new Label( composite, SWT.NONE );
		label1.setText( Messages.getString( "lable.selectXmlFile" ) ); //$NON-NLS-1$
		label1.setLayoutData( data );
		
		//GridData data;
		setupXMLFolderLocation( composite );
		
		setUseDataSourceXMLDataSelection( composite );
		
		setRowSelection( composite );
		
		
		return composite;
	}

	/**
	 * initial the info property after the control has been created.
	 *
	 */
	private void initializeControl( )
	{
		DataSetDesign dataSetDesign = getDataSetDesign( );

		XMLInformationHolder.destory( );
		XMLInformationHolder.start( dataSetDesign );

		if ( dataSetDesign == null )
			return; // nothing to initialize
		String xmlFile = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_XML_FILE );
		if ( xmlFile == null || xmlFile.trim( ).length( ) == 0 )
		{
			this.useDataSourceXMLDataSelected = true;
			this.folderLocation.setText( "" );
			enableFolderLocation( false );
		}
		else
		{
			this.useDataSourceXMLDataSelected = false;
			this.folderLocation.setText( xmlFile );
			enableFolderLocation( true );
		}
		useDataSourceXMLDataCheckBox.setSelection( this.useDataSourceXMLDataSelected );
		
		String rowNumber = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_MAX_ROW );
		try
		{
			maxRow = Integer.parseInt( rowNumber );
			if ( maxRow == UNUSED_ROW_CACHE
					|| maxRow == INVALID_ROW_NUMBER
					|| maxRow == NEGATIVE_ROW_NUMBER )
				maxRow = UNUSED_ROW_CACHE;
			else
			{
				selected = true;
				previewNumCheckBox.setSelection( selected );
				numberText.setEnabled( selected );
				numberText.setText( rowNumber );
			}
			setPageStatus( );
		}
		catch ( NumberFormatException e )
		{
			maxRow = UNUSED_ROW_CACHE;
		}
		setPageComplete( true );
		if ( !isSessionEditable( ) )
			getControl( ).setEnabled( false );
	}

	/**
	 * create row selection group
	 * @param composite
	 */
	private void setRowSelection( Composite composite )
	{
		Composite rowSelectionGroup = new Composite( composite, SWT.NONE );

		GridLayout layout = new GridLayout( );
		layout.numColumns = 3;
		layout.marginWidth = 0;
		layout.marginHeight = 3;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;

		rowSelectionGroup.setLayout( layout );
		rowSelectionGroup.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL ) );

		previewNumCheckBox = new Button( rowSelectionGroup, SWT.CHECK );
		GridData data = new GridData( );
		data.horizontalSpan = 1;
		previewNumCheckBox.setLayoutData( data );
		previewNumCheckBox.setText( Messages.getString( "label.preview" ) );
		
		numberText = new Text( rowSelectionGroup, SWT.BORDER );
		numberText.setEnabled( false );
		data = new GridData( );
		data.horizontalSpan = 1;
		Point minSize = numberText.computeSize( SWT.DEFAULT, SWT.DEFAULT, true );
		data.widthHint = Math.max( 50, minSize.x );
		numberText.setLayoutData( data );
	
		numberText.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				if ( numberText.getText( ) != null
						&& numberText.getText( ).trim( ).length( ) > 0 )
				{
					setPageStatus( );
				}
			}
		} );
		final Label label = new Label( rowSelectionGroup, SWT.BEGINNING );
		label.setText( Messages.getString( "xmlDataSetSelectionPage.messages.lineofdata" ) );

		previewNumCheckBox.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				selected = !selected;
				numberText.setEnabled( selected );
				if ( !selected )
				{
					maxRow = UNUSED_ROW_CACHE;
					setPageComplete( true );
					XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_MAX_ROW,
							Integer.toString( maxRow ) );
					setPageStatus( );
				}
				else
				{
					if ( numberText.getText( ) != null
							&& numberText.getText( ).trim( ).length( ) > 0 )
					{
						setPageStatus( );
					}
				}
			}

			public void widgetDefaultSelected( SelectionEvent e )
			{
			}
		} );
	}

	/**
	 * Create control by select which we will use xml data file in data source when 
	 * preview.
	 * 
	 * @param composite
	 */
	private void setUseDataSourceXMLDataSelection( Composite composite )
	{
		Composite rowSelectionGroup = new Composite( composite, SWT.NONE );

		GridLayout layout = new GridLayout( );
		layout.numColumns = 3;
		layout.marginWidth = 0;
		layout.marginHeight = 1;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		GridData data = new GridData( GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL );
		data.horizontalSpan = 3;
		rowSelectionGroup.setLayout( layout );
		rowSelectionGroup.setLayoutData( data );

		useDataSourceXMLDataCheckBox = new Button( rowSelectionGroup, SWT.CHECK );

		useDataSourceXMLDataCheckBox.setLayoutData( data );
		useDataSourceXMLDataCheckBox.setText( Messages.getString( "label.useXMLFileFromDataSource" ) );
		useDataSourceXMLDataCheckBox.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				useDataSourceXMLDataSelected = !useDataSourceXMLDataSelected;
				
				if ( useDataSourceXMLDataSelected )
				{
					enableFolderLocation( false );
					fileLocation = "";
				}
				else
				{
					enableFolderLocation( true );
					fileLocation = folderLocation.getText( );
				}
				setXMLFileLocaiton();
				setPageStatus( );
			}

			public void widgetDefaultSelected( SelectionEvent e )
			{
			}
		} );
	}

	/**
	 * 
	 * @param enable
	 */
	private void enableFolderLocation( boolean enable )
	{
		folderLocation.setEnabled( enable );
		browseFolderButton.setEnabled( enable );
	}
	/**
	 * set page status based on row number
	 *
	 */
	private void setPageStatus( )
	{
		if ( numberText == null || !this.previewNumCheckBox.getSelection( ) )
		{
			setMessage( DEFAULT_MESSAGE );
			return;
		}
		String rowNumber = numberText.getText( );
		maxRow = validateRowNumber( rowNumber );
		if ( maxRow == INVALID_ROW_NUMBER || maxRow == NEGATIVE_ROW_NUMBER )
		{
			setPageComplete( false );
			setDetailsMessage( Messages.getString( "error.dataset.maxRowNumberError" ),
					IMessageProvider.ERROR );
		}
		else
		{
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_MAX_ROW,
					Integer.toString( maxRow ) );
			setPageComplete( true );
			setMessage( DEFAULT_MESSAGE );
		}
	}
	
	/**
	 * validate row number, if row number is invalid, it returns
	 * INVALID_ROW_NUMBER or NEGATIVE_ROW_NUMBER or returns its value
	 * 
	 * @param maxRow
	 * @return
	 */
	private int validateRowNumber( String maxRow )
	{
		int rowNumber = 0;
		try
		{
			rowNumber = Integer.parseInt( maxRow );
		}
		catch ( NumberFormatException e )
		{
			return INVALID_ROW_NUMBER;
		}
		if ( rowNumber < 0 )
		{
			return NEGATIVE_ROW_NUMBER;
		}
		else
		{
			return rowNumber;
		}
	}
	
	/**
	 * setup xml folder location
	 * @param composite
	 */
	private void setupXMLFolderLocation( Composite composite )
	{

		GridData data = new GridData( GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_FILL );
		data.horizontalSpan = 2;
		folderLocation = new Text( composite, SWT.BORDER | SWT.SINGLE );
		folderLocation.setLayoutData( data );
		setPageComplete( false );
		folderLocation.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				setXMLFileLocaiton( );
			}

		} );
		browseFolderButton = new Button( composite, SWT.PUSH );
		data = new GridData( );
		data.widthHint = convertHorizontalDLUsToPixels( IDialogConstants.BUTTON_WIDTH );
     	browseFolderButton.setLayoutData( data );
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
						.getActiveShell( ), SWT.SAVE );
				dialog.setFilterExtensions( new String[]{
						"*.xml", "*.*"
				} );
				if ( folderLocation.getText( ) != null
						&& folderLocation.getText( ).trim( ).length( ) > 0 )
				{
					dialog.setFilterPath( folderLocation.getText( ) );
				}

				String selectedLocation = dialog.open( );
				if ( selectedLocation != null )
				{
					folderLocation.setText( selectedLocation );
					setXMLFileLocaiton( );
				}
			}
		} );

	}

	/**
	 * verify the file location
	 * @param fileLocation
	 * @return
	 */
	private int varifyFileLocation( String fileLocation )
	{
		int result = 0;
		// TODO For migrate into ODA3.0,the relative path is not supported
		// if( this.dataSetHandle.getModuleHandle( ).findResource( fileLocation,
		// IResourceLocator.LIBRARY )!= null)
		// return result;
		
		if ( fileLocation != null && fileLocation.trim( ).length( ) > 0 )
		{
			try
			{
				new URL( fileLocation );
			}
			catch ( MalformedURLException e )
			{
				File file = new File( fileLocation );
				if ( file.exists( ) )
				{
					setMessage( DEFAULT_MESSAGE );
				}
				else
				{
					setDetailsMessage( Messages.getString( "error.selectFolder" ),
							IMessageProvider.ERROR );
					result = ERROR_FOLDER;
				}
			}
		}
		else if( !useDataSourceXMLDataSelected )
		{
			setDetailsMessage( Messages.getString( "error.emptyPath" ),
					IMessageProvider.ERROR );
			result = ERROR_EMPTY_PATH;
		}
		return result;
	}

	/**
	 * set xml file URL
	 *
	 */
	private void setXMLFileLocaiton( )
	{
		if( this.useDataSourceXMLDataSelected )
		{
			this.fileLocation = "";
		}
		else 
		{
			this.fileLocation = this.folderLocation.getText( ) == null ? ""
					: this.folderLocation.getText( );
		}
		XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_XML_FILE, fileLocation );
	}
	
	/**
	 * get dataSetDesign from super
	 * @return
	 */
	private DataSetDesign getDataSetDesign( )
	{
		DataSetDesign dataSetDesign = getInitializationDesign();
		return dataSetDesign;
	}
		
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
    protected DataSetDesign collectDataSetDesign( DataSetDesign design )
	{
		try
		{
			savePage( design );
		}
		catch ( OdaException e )
		{
		}
		return design;
	}
    
    /**
     * Updates the given dataSetDesign with the query and its metadata defined
     * in this page.
     * 
     * @param dataSetDesign
     * @throws OdaException 
     */
    private void savePage( DataSetDesign dataSetDesign ) throws OdaException
	{
		if ( dataSetDesign.getPublicProperties( ) == null )
		{
			try
			{
				dataSetDesign.setPublicProperties( DesignSessionUtil.createDataSetPublicProperties( dataSetDesign.getOdaExtensionDataSourceId( ),
						dataSetDesign.getOdaExtensionDataSetId( ),
						getPageProperties( ) ) );
			}
			catch ( OdaException e )
			{
				e.printStackTrace( );
			}
		}
		dataSetDesign.getPublicProperties( )
				.findProperty( Constants.CONST_PROP_XML_FILE )
				.setNameValue( Constants.CONST_PROP_XML_FILE, fileLocation );
		dataSetDesign.getPublicProperties( )
				.findProperty( Constants.CONST_PROP_MAX_ROW )
				.setNameValue( Constants.CONST_PROP_MAX_ROW,
						Integer.toString( maxRow ) );
		if ( dataSetDesign.getQueryText( ) != null
				&& !dataSetDesign.getQueryText( )
						.equals( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) ) )
		{
			DataSetDesignPopulator.populateResultSet( dataSetDesign );
			dataSetDesign.setQueryText( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
		}
	}

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizardPage#getNextPage()
	 */
	public IWizardPage getNextPage( )
	{
		int result = varifyFileLocation( this.fileLocation );
		boolean doNext = true;
		if ( result == ERROR_FOLDER )
			doNext = MessageDialog.openQuestion( getShell( ),
					DEFAULT_MESSAGE,
					Messages.getFormattedString( "xmlDataSetSelectionPage.warning.errorReadXMLFile",
							new Object[]{
									fileLocation,
									Messages.getString( "error.selectFolder" )
							} ) );
		else if ( result == ERROR_EMPTY_PATH )
			doNext = MessageDialog.openQuestion( getShell( ),
					DEFAULT_MESSAGE,
					Messages.getFormattedString( "xmlDataSetSelectionPage.warning.errorReadXMLFile",
							new Object[]{
									fileLocation,
									Messages.getString( "error.emptyPath" )
							} ) );
		if ( isValid( ) && doNext )
		{
			XMLInformationHolder.setPropertyValue( Constants.CONST_PROP_MAX_ROW,
					Integer.toString( maxRow ) );
			return super.getNextPage( );
		}
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage( )
	{
		return isValid( );
	}
	
	/**
	 * if the numberText has been selected, check the input number is valid or
	 * not.
	 * @return
	 */
	private boolean isValid( )
	{
		if ( maxRow == UNUSED_ROW_CACHE || maxRow >= 0 )
			return true;
		else
			return false;
	}

	/**
	 * get page property
	 * @return
	 */
	private static Properties getPageProperties( )
	{
		Properties	prop = new Properties( );
		prop.setProperty( Constants.CONST_PROP_XML_FILE, "" );
		prop.setProperty( Constants.CONST_PROP_MAX_ROW, "-1" );
		return prop;
	}

	/**
	 * set messages
	 */
	public void setMessage( String newMessage )
	{
		super.setMessage( newMessage );
	}
    
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#canLeave()
	 */
    protected boolean canLeave( )
	{
		return isValid( );
	}
	
	/**
	 * set detailed message
	 * @param message
	 * @param type
	 */
	private void setDetailsMessage( String message, int type )
	{
		this.setMessage( message, type );
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
