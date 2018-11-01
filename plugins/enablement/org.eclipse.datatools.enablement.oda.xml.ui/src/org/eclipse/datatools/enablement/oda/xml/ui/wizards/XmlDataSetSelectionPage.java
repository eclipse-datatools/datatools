/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
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
import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.IHelpConstants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;
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

	private static final String MINUS_ONE = "-1";  //$NON-NLS-1$
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$
    private static String DEFAULT_MESSAGE = Messages.getString( "wizard.defaultMessage.selectFile" ); //$NON-NLS-1$
	private static final int ERROR_FOLDER = 1;
	private static final int ERROR_EMPTY_PATH = 2;

	private transient Text folderLocation;
	private transient Button useXMLDataSourceButton;
	private transient Button enterXMLSourceButton;
	private transient Text numberText;
	private transient Button browseFolderButton;
	
	private String fileLocation;
	
	private static final int UNUSED_ROW_CACHE = -1;
	private static final int INVALID_ROW_NUMBER = -2;
	private static final int NEGATIVE_ROW_NUMBER = -4;

	private int maxRow = UNUSED_ROW_CACHE;
	
	private final String[] XML_FILTER = new String[]{"*.xml", "*.*"};   //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * @param string
	 */
	public XmlDataSetSelectionPage( )
	{
		this( Messages.getString( "wizard.title.newDataSet" ) ); //$NON-NLS-1$
	}

	/**
	 * @param pageName
	 */
	public XmlDataSetSelectionPage( String pageName )
	{
		super( pageName );
		setTitle( pageName );
		setMessage( DEFAULT_MESSAGE );
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
		GridLayout layout = new GridLayout( );
		composite.setLayout( layout );

		createRadioButtons( composite );
		createNumOfLinesGroup( composite );
		
		return composite;
	}

	/**
	 * initial the info property after the control has been created.
	 *
	 */
	private void initializeControl( )
	{
		DataSetDesign dataSetDesign = getDataSetDesign( );
		if ( dataSetDesign == null )
			return; // nothing to initialize

		if ( XMLInformationHolder.hasDestroyed( ) )
			XMLInformationHolder.start( dataSetDesign );
		
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
				numberText.setText( rowNumber );
			}
			updatePageStatus( );
		}
		catch ( NumberFormatException e )
		{
			maxRow = UNUSED_ROW_CACHE;
		}

		String xmlFile = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_XML_FILE );
		if ( xmlFile == null || xmlFile.trim( ).length( ) == 0 )
		{
			useXMLDataSourceButton.setSelection( true );
			enterXMLSourceButton.setSelection( false );
			this.folderLocation.setText( EMPTY_STRING );
			enableFolderLocation( false );
		}
		else
		{
			useXMLDataSourceButton.setSelection( false );
			enterXMLSourceButton.setSelection( true );
			this.folderLocation.setText( xmlFile );
			enableFolderLocation( true );
		}
		
		setPageComplete( true );
		if ( !isSessionEditable( ) )
			getControl( ).setEnabled( false );
	}

	/**
	 * Create radio button group
	 * 
	 * @param composite
	 */
	private void createRadioButtons( Composite composite )
	{
		Composite radioGroup = new Composite( composite, SWT.NONE );
		GridLayout gridLayout = new GridLayout( );
		gridLayout.numColumns = 3;
		radioGroup.setLayout( gridLayout );
		GridData gridData = new GridData( GridData.FILL_HORIZONTAL );
		radioGroup.setLayoutData( gridData );
		setupSourceSelectionButtons( radioGroup );
		setupXMLFolderLocation( radioGroup );
	}

	/**
	 * Create number of lines' group
	 * 
	 * @param composite
	 */
	private void createNumOfLinesGroup( Composite composite )
	{
		Composite numOfLinesGroup = new Composite( composite, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.numColumns = 3;
		numOfLinesGroup.setLayout( layout );
		GridData gridData = new GridData( GridData.FILL_HORIZONTAL );
		gridData.verticalIndent = 8;
		numOfLinesGroup.setLayoutData( gridData );

		GridData data = new GridData( );
		Label previewLabel = new Label( numOfLinesGroup, SWT.NONE );
		previewLabel.setText( Messages.getString( "label.preview" ) ); //$NON-NLS-1$
		previewLabel.setData( data );

		numberText = new Text( numOfLinesGroup, SWT.BORDER );
		data = new GridData( );
		Point minSize = numberText.computeSize( SWT.DEFAULT, SWT.DEFAULT, true );
		data.widthHint = Math.max( 60, minSize.x );
		numberText.setLayoutData( data );
		numberText.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				updatePageStatus( );
			}
		} );
		final Label label = new Label( numOfLinesGroup, SWT.BEGINNING );
		label.setText( Messages.getString( "xmlDataSetSelectionPage.messages.lineofdata" ) );     //$NON-NLS-1$
	}

	/**
	 * Create control by select which we will use xml data file in data source when 
	 * preview.
	 * 
	 * @param composite
	 */
	private void setupSourceSelectionButtons( Composite composite )
	{
		GridData data = new GridData( GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_FILL );
		data.horizontalSpan = 3;
		
		useXMLDataSourceButton = new Button( composite, SWT.RADIO );
		
		useXMLDataSourceButton.setSelection( true );
		useXMLDataSourceButton.setText( Messages.getString( "label.useXMLFileFromDataSource" ) );    //$NON-NLS-1$
		
		useXMLDataSourceButton.setLayoutData( data );
		
		SelectionAdapter sa = new SelectionAdapter( ) {
			public void widgetSelected( SelectionEvent e )
			{	
				if ( useXMLDataSourceButton.getSelection( ) )
				{
					enableFolderLocation( false );
					fileLocation = EMPTY_STRING;
				}
				else
				{
					enableFolderLocation( true );
					fileLocation = folderLocation.getText( );
				}
				setXMLFileLocaiton();
				updatePageStatus( );
			}

			public void widgetDefaultSelected( SelectionEvent e )
			{
			}
		};
		useXMLDataSourceButton.addSelectionListener( sa );
		
		GridData sourceData = new GridData( GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_FILL );
		sourceData.horizontalSpan = 3;
		sourceData.verticalIndent = 8;		
		enterXMLSourceButton = new Button( composite, SWT.RADIO );
 		enterXMLSourceButton.setLayoutData( sourceData );
		enterXMLSourceButton.setText( Messages.getString( "lable.selectXmlFile" ) );       //$NON-NLS-1$
		enterXMLSourceButton.addSelectionListener( sa );
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
	private void updatePageStatus( )
	{
		if ( numberText == null )
		{
			setMessage( DEFAULT_MESSAGE );
			return;
		}
		
		if( enterXMLSourceButton!= null && enterXMLSourceButton.getSelection( ) )
		{
			if( fileLocation == null || fileLocation.trim( ).length( ) == 0 )
			{
				setDetailsMessage( Messages.getString( "error.dataset.Error.MissingFileLocation" ), //$NON-NLS-1$
						IMessageProvider.ERROR );
				setPageComplete( false );
				return;
			}
		}		
		String rowNumber = numberText.getText( );
		maxRow = validateRowNumber( rowNumber );
		if ( maxRow == INVALID_ROW_NUMBER || maxRow == NEGATIVE_ROW_NUMBER )
		{
			setPageComplete( false );
			setDetailsMessage( Messages.getString( "error.dataset.maxRowNumberError" ), //$NON-NLS-1$
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
		if( maxRow.trim( ).length( ) == 0 )
			return UNUSED_ROW_CACHE;
		
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
		GridData gridData = new GridData( GridData.FILL_HORIZONTAL );
		composite.setLayoutData( gridData );

		Button blankButton = new Button( composite, SWT.RADIO );
		GridData blankBtnData = new GridData( );
		blankBtnData.horizontalSpan = 1;
		blankBtnData.horizontalAlignment = SWT.BEGINNING;
		blankButton.setLayoutData( blankBtnData );
		blankButton.setVisible( false );

		GridData txtGridData = new GridData( GridData.FILL_HORIZONTAL );
		txtGridData.horizontalSpan = 1;
		folderLocation = new Text( composite, SWT.BORDER );
		folderLocation.setLayoutData( txtGridData );
		setPageComplete( false );
		folderLocation.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				setXMLFileLocaiton( );
				updatePageStatus( );
			}

		} );

		GridData browseBtnData = new GridData( );
		browseBtnData.horizontalSpan = 1;
		browseBtnData.horizontalIndent = 10;
		browseFolderButton = new Button( composite, SWT.NONE );
		browseFolderButton.setText( Messages.getString( "file.choose" ) ); //$NON-NLS-1$
		browseFolderButton.setLayoutData( browseBtnData );
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
				dialog.setFilterExtensions( XML_FILTER );
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
				updatePageStatus( );
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
					setDetailsMessage( Messages.getString( "error.selectFolder" ),     //$NON-NLS-1$
							IMessageProvider.ERROR );
					result = ERROR_FOLDER;
				}
			}
		}
		else if( !this.useXMLDataSourceButton.getSelection( ) )
		{
			setDetailsMessage( Messages.getString( "error.emptyPath" ),                //$NON-NLS-1$
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
		if( this.useXMLDataSourceButton.getSelection( ) )
		{
			this.fileLocation = EMPTY_STRING;
		}
		else 
		{
			this.fileLocation = this.folderLocation.getText( ) == null ? EMPTY_STRING
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
    	if( XMLInformationHolder.hasDestroyed( ) )
    		return;
    	
		if ( dataSetDesign.getPrivateProperties( ) == null )
		{
			try
			{
				dataSetDesign.setPrivateProperties( DesignSessionUtil.createDataSetNonPublicProperties( dataSetDesign.getOdaExtensionDataSourceId( ),
						dataSetDesign.getOdaExtensionDataSetId( ),
						getPageProperties( ) ) );
			}
			catch ( OdaException e )
			{
			}
		}
	
		if ( dataSetDesign.getPrivateProperties( ) != null )
		{
			if ( dataSetDesign.getPrivateProperties( )
					.findProperty( Constants.CONST_PROP_XML_FILE ) != null )
				dataSetDesign.getPrivateProperties( )
						.findProperty( Constants.CONST_PROP_XML_FILE )
						.setNameValue( Constants.CONST_PROP_XML_FILE,
								XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_XML_FILE ) );
			if ( dataSetDesign.getPrivateProperties( )
					.findProperty( Constants.CONST_PROP_MAX_ROW ) != null )
				dataSetDesign.getPrivateProperties( )
						.findProperty( Constants.CONST_PROP_MAX_ROW )
						.setNameValue( Constants.CONST_PROP_MAX_ROW,
								XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_MAX_ROW ) );
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
					Messages.getFormattedString( "xmlDataSetSelectionPage.warning.errorReadXMLFile",   //$NON-NLS-1$
							new Object[]{
									fileLocation,
									Messages.getString( "error.selectFolder" )                         //$NON-NLS-1$
							} ) );
		else if ( result == ERROR_EMPTY_PATH )
			doNext = MessageDialog.openQuestion( getShell( ),
					DEFAULT_MESSAGE,
					Messages.getFormattedString( "xmlDataSetSelectionPage.warning.errorReadXMLFile",   //$NON-NLS-1$
							new Object[]{
									fileLocation,
									Messages.getString( "error.emptyPath" )                            //$NON-NLS-1$
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
		if ( isPageComplete( ) && ( maxRow == UNUSED_ROW_CACHE || maxRow >= 0 ) )
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
		prop.setProperty( Constants.CONST_PROP_XML_FILE, EMPTY_STRING );
		prop.setProperty( Constants.CONST_PROP_MAX_ROW, MINUS_ONE );
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
    	updatePageStatus( );
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
	
	/*
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#cleanup()
	 */
    protected void cleanup()
    {
    	XMLInformationHolder.destory( );
    }
}
