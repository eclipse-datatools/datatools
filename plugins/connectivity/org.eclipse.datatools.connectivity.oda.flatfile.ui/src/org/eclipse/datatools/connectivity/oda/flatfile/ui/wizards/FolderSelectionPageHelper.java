/*
 *************************************************************************
 * Copyright (c) 2005, 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile.ui.wizards;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.SortedMap;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.TextProcessorWrapper;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.InvalidResourceException;
import org.eclipse.datatools.connectivity.oda.flatfile.ResourceLocator;
import org.eclipse.datatools.connectivity.oda.flatfile.ui.i18n.Messages;
import org.eclipse.datatools.connectivity.oda.flatfile.ui.util.IHelpConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.ui.util.Utility;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.ui.PingJob;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

public class FolderSelectionPageHelper
{

	private WizardPage wizardPage;
	private PreferencePage propertyPage;
	private ResourceIdentifiers ri;

	private transient Text folderLocation = null;
	private transient Text fileURI = null;
	private transient MenuButton browseLocalFileButton = null;
	private transient Button typeLineCheckBox = null;
	private transient MenuButton browseFolderButton = null;
	private transient Combo charSetSelectionCombo = null;
	private transient Button columnNameLineCheckBox = null;
	private transient Combo flatFileStyleCombo = null;
	private transient Button trailNullColsCheckBox = null;
	private transient Composite parent = null;
	private transient Button homeFolderChoice = null;
	private transient Button fileURIChoice = null;
	private static final String[] fileExtensions = new String[]{
			"*.csv", "*.psv", "*.ssv", "*.tsv", "*.txt", "*.*"}; //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$//$NON-NLS-6$

	private static final String[] flatFileStyles = new String[]{
			Messages.getString( "label.flatfileComma" ), //$NON-NLS-1$
			Messages.getString( "label.flatfileSemicolon" ),//$NON-NLS-1$
			Messages.getString( "label.flatfilePipe" ),//$NON-NLS-1$
			Messages.getString( "label.flatfileTab" ),//$NON-NLS-1$
	};

	private SortedMap<String, Charset> charSetMap;

	static final String DEFAULT_MESSAGE = Messages.getString( "FolderSelectionPageHelper.SelectFolderDialog.Title" ); //$NON-NLS-1$

	private static final int CORRECT_FOLDER = InvalidResourceException.CORRECT_RESOURCE;
	private static final int ERROR_INVALID_PATH = InvalidResourceException.ERROR_INVALID_RESOURCE;
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$

	private static final Integer SELECT_RELATIVE_PATH = 1;
	private static final Integer SELECT_ABSOLUTE_PATH = 2;

	private boolean needsCheckURITest = true;
	private String URIValue = EMPTY_STRING;

	FolderSelectionPageHelper( WizardPage page )
	{
		wizardPage = page;
	}

	FolderSelectionPageHelper( PreferencePage page )
	{
		propertyPage = page;
	}

	/**
	 * 
	 * @param parent
	 */
	void createCustomControl( Composite parent )
	{
		ScrolledComposite sComposite = new ScrolledComposite( parent,
				SWT.H_SCROLL | SWT.V_SCROLL );
		sComposite.setLayout( new GridLayout( ) );
		sComposite.setMinWidth( 550 );
		sComposite.setExpandHorizontal( true );

		this.parent = parent;
		Composite content = new Composite( sComposite, SWT.NONE );
		GridLayout layout = new GridLayout( 3, false );
		content.setLayout( layout );

		// GridData data;
		setupFolderLocation( content );

		setupFileURI( content );

		setupCharset( content );

		setupFlatfileStyleList( content );

		setupColumnNameLineCheckBox( content );

		setupTypeLineCheckBox( content );

		setupTrailNullsCheckBox( content );

		Point size = content.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		content.setSize( size.x, size.y );

		sComposite.setContent( content );

		Utility.setSystemHelp( getControl( ),
				IHelpConstants.CONEXT_ID_DATASOURCE_FLATFILE );
	}

	/**
	 * 
	 * @return
	 */
	String getFolderLocation( )
	{
		if ( folderLocation == null )
			return EMPTY_STRING;
		return getFolderLocationString( );
	}

	String getFileURI( )
	{
		if ( fileURI == null )
			return EMPTY_STRING;
		return getFileURIString( );
	}

	/**
	 * 
	 * @return
	 */
	String getWhetherUseFirstLineAsColumnNameLine( )
	{
		if ( columnNameLineCheckBox == null
				|| !columnNameLineCheckBox.getEnabled( ) )
			return EMPTY_STRING;
		return columnNameLineCheckBox.getSelection( ) ? CommonConstants.INC_COLUMN_NAME_YES
				: CommonConstants.INC_COLUMN_NAME_NO;
	}

	/**
	 * 
	 * @return
	 */
	String getWhetherUseSecondLineAsTypeLine( )
	{
		if ( typeLineCheckBox == null )
			return EMPTY_STRING;
		return typeLineCheckBox.getSelection( ) ? CommonConstants.INC_TYPE_LINE_YES
				: CommonConstants.INC_TYPE_LINE_NO;
	}

	String getWhetherUseTrailNulls( )
	{
		if ( trailNullColsCheckBox == null )
			return EMPTY_STRING;
		return trailNullColsCheckBox.getSelection( ) ? CommonConstants.TRAIL_NULL_COLS_YES
				: CommonConstants.TRAIL_NULL_COLS_NO;
	}

	/**
	 * 
	 * @return
	 */
	String getCharSet( )
	{
		if ( charSetSelectionCombo == null )
			return EMPTY_STRING;
		return charSetSelectionCombo.getItem( charSetSelectionCombo.getSelectionIndex( ) );
	}

	/**
	 * 
	 * @param props
	 * @return
	 */
	Properties collectCustomProperties( Properties props )
	{
		if ( props == null )
			props = new Properties( );

		// set custom driver specific properties
		if ( homeFolderChoice.getSelection( ) )
		{
			props.setProperty( CommonConstants.CONN_HOME_DIR_PROP,
					getFolderLocation( ).trim( ) );
			props.remove( CommonConstants.CONN_FILE_URI_PROP );
		}
		if ( fileURIChoice.getSelection( ) )
		{
			props.setProperty( CommonConstants.CONN_FILE_URI_PROP, getFileURI( ) );
			props.remove( CommonConstants.CONN_HOME_DIR_PROP );
		}

		props.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
				getFlatfileStyle( ) );
		props.setProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP,
				getWhetherUseFirstLineAsColumnNameLine( ) );
		props.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP,
				getWhetherUseSecondLineAsTypeLine( ) );
		props.setProperty( CommonConstants.CONN_CHARSET_PROP, getCharSet( ) );
		props.setProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP,
				getWhetherUseTrailNulls( ) );

		return props;
	}

	/**
	 * 
	 * @param profileProps
	 */
	void initCustomControl( Properties profileProps )
	{
		if ( profileProps == null
				|| profileProps.isEmpty( )
				|| folderLocation == null
				|| fileURI == null )
			return; // nothing to initialize

		String folderPath = profileProps.getProperty( CommonConstants.CONN_HOME_DIR_PROP );
		if ( folderPath != null )
		{
			setFolderLocationString( folderPath );
			switchFileSelectionMode( true );
		}

		String fileURI = profileProps.getProperty( CommonConstants.CONN_FILE_URI_PROP );
		if ( fileURI != null && fileURI.length( ) != 0 )
		{
			setFileURIString( fileURI );
			switchFileSelectionMode( false );
		}

		String delimiterType = profileProps.getProperty( CommonConstants.CONN_DELIMITER_TYPE );
		initFlatfileSytleSelection( delimiterType );

		String hasColumnNameLine = profileProps.getProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP );
		if ( hasColumnNameLine == null )
			hasColumnNameLine = CommonConstants.INC_COLUMN_NAME_YES;
		if ( hasColumnNameLine.equalsIgnoreCase( CommonConstants.INC_COLUMN_NAME_YES ) )
		{
			columnNameLineCheckBox.setSelection( true );

			String useSecondLine = profileProps.getProperty( CommonConstants.CONN_INCLTYPELINE_PROP );
			if ( useSecondLine == null )
				useSecondLine = EMPTY_STRING;
			typeLineCheckBox.setEnabled( true );
			typeLineCheckBox.setSelection( useSecondLine.equalsIgnoreCase( CommonConstants.INC_TYPE_LINE_YES ) );
		}
		else
		{
			columnNameLineCheckBox.setSelection( false );
			typeLineCheckBox.setSelection( false );
			typeLineCheckBox.setEnabled( false );
		}

		String trailNullCols = profileProps.getProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP );
		if ( trailNullCols == null )
			trailNullCols = CommonConstants.TRAIL_NULL_COLS_NO;
		if ( trailNullCols.equalsIgnoreCase( CommonConstants.TRAIL_NULL_COLS_YES ) )
		{
			trailNullColsCheckBox.setSelection( true );
		}
		else
		{
			trailNullColsCheckBox.setSelection( false );
		}

		String charSet = profileProps.getProperty( CommonConstants.CONN_CHARSET_PROP );
		if ( charSet == null || charSet.trim( ).length( ) == 0 )
			charSetSelectionCombo.select( 0 );
		else
			charSetSelectionCombo.select( charSetSelectionCombo.indexOf( charSet ) );
		validatePageStatus( );
	}

	/**
	 * 
	 * @return the selected flatfile style
	 */
	private String getFlatfileStyle( )
	{
		String value = flatFileStyleCombo.getText( );
		// return value;
		if ( value.equals( flatFileStyles[0] ) )
		{
			return CommonConstants.DELIMITER_COMMA;
		}
		else if ( value.equals( flatFileStyles[1] ) )
		{
			return CommonConstants.DELIMITER_SEMICOLON;
		}
		else if ( value.equals( flatFileStyles[2] ) )
		{
			return CommonConstants.DELIMITER_PIPE;
		}
		else if ( value.equals( flatFileStyles[3] ) )
		{
			return CommonConstants.DELIMITER_TAB;
		}
		return CommonConstants.DELIMITER_COMMA;
	}

	/**
	 * 
	 * @param folderPath
	 */
	private void setFolderLocationString( String folderPath )
	{
		folderLocation.setText( TextProcessorWrapper.process( folderPath ) );
	}

	private void setFileURIString( String file )
	{
		file = convertRelativePath( file );
		fileURI.setText( TextProcessorWrapper.process( file ) );
	}

	private String convertRelativePath( String file )
	{
		String path = file;
		if ( file != null && file.length( ) > 0 )
		{
			try
			{
				new URI( file );
			}
			catch ( URISyntaxException e )
			{
				// Contains back slash or invalid.
				try
				{
					URI uri = new URI( file.replace( '\\', '/' ) );
					if ( !uri.isAbsolute( ) )
						path = uri.toString( );
				}
				catch ( URISyntaxException e1 )
				{
				}
			}
		}
		return path;
	}

	/**
	 * 
	 * @return
	 */
	private String getFolderLocationString( )
	{
		return TextProcessorWrapper.deprocess( folderLocation.getText( ) );
	}

	private String getFileURIString( )
	{
		return TextProcessorWrapper.deprocess( convertRelativePath( fileURI.getText( ) ) );
	}

	/**
	 * 
	 * @param delimiterType
	 */
	private void initFlatfileSytleSelection( String delimiterType )
	{
		if ( CommonConstants.DELIMITER_COMMA.equals( delimiterType ) )
		{
			flatFileStyleCombo.select( 0 );
		}
		else if ( CommonConstants.DELIMITER_SEMICOLON.equals( delimiterType ) )
		{
			flatFileStyleCombo.select( 1 );
		}
		else if ( CommonConstants.DELIMITER_PIPE.equals( delimiterType ) )
		{
			flatFileStyleCombo.select( 2 );
		}
		else if ( CommonConstants.DELIMITER_TAB.equals( delimiterType ) )
		{
			flatFileStyleCombo.select( 3 );
		}
	}

	/**
	 * 
	 * @param composite
	 */
	private void setupFolderLocation( Composite composite )
	{
		homeFolderChoice = new Button( composite, SWT.RADIO );
		homeFolderChoice.addSelectionListener( new SelectionListener( ) {

			public void widgetSelected( SelectionEvent e )
			{
				switchFileSelectionMode( true );
				validatePageStatus( );
			}

			public void widgetDefaultSelected( SelectionEvent e )
			{

			}

		} );
		homeFolderChoice.setText( Messages.getString( "label.selectFolder" ) ); //$NON-NLS-1$

		GridData data = new GridData( GridData.FILL_HORIZONTAL );
		folderLocation = new Text( composite, SWT.BORDER );
		folderLocation.setLayoutData( data );
		setPageComplete( false );
		folderLocation.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				validatePageStatus( );
			}

		} );

		browseFolderButton = new MenuButton( composite, SWT.NONE );
		browseFolderButton.setText( Messages.getString( "button.selectFileURI.browse" ) ); //$NON-NLS-1$
		browseFolderButton.setToolTipText( Messages.getString( "button.selectFileURI.browse.tooltips" ) ); //$NON-NLS-1$

		Menu menu = new Menu( composite.getShell( ), SWT.POP_UP );
		SelectionAdapter action = new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if ( e.widget instanceof MenuItem )
				{
					MenuItem item = (MenuItem) e.widget;
					Integer type = (Integer) item.getData( );
					handleFolderSelection( type );
				}
				else if ( e.widget instanceof MenuButton )
				{
					if ( ri != null )
					{
						handleFolderSelection( SELECT_RELATIVE_PATH );
					}
					else
					{
						handleFolderSelection( SELECT_ABSOLUTE_PATH );
					}
				}
			}
		};

		MenuItem item;
		if ( ri != null )
		{
			item = new MenuItem( menu, SWT.PUSH );
			item.setText( Messages.getString( "button.selectFileURI.menuItem.relativePath" ) ); //$NON-NLS-1$
			item.setData( SELECT_RELATIVE_PATH );
			item.addSelectionListener( action );
		}

		item = new MenuItem( menu, SWT.PUSH );
		item.setText( Messages.getString( "button.selectFileURI.menuItem.absolutePath" ) ); //$NON-NLS-1$
		item.setData( SELECT_ABSOLUTE_PATH );
		item.addSelectionListener( action );

		// Add relative path selection support while having resource identifier
		browseFolderButton.setDropDownMenu( menu );
		browseFolderButton.addSelectionListener( action );

		GridData btnData = new GridData( );
		btnData.widthHint = browseFolderButton.computeSize( -1, -1 ).x;
		browseFolderButton.setLayoutData( btnData );
	}

	protected void handleFolderSelection( Integer selectionType )
	{
		if ( selectionType == SELECT_RELATIVE_PATH )
		{
			RelativeFileSelectionDialog dialog = new RelativeFileSelectionDialog( folderLocation.getShell( ),
					new File( getResourceFolder( ) ),
					true );
			if ( dialog.open( ) == Window.OK )
			{
				try
				{
					URI uri = dialog.getSelectedURI( );
					if ( uri != null )
					{
						setFolderLocationString( uri.getPath( ) );;
					}
				}
				catch ( URISyntaxException e )
				{
				}
			}
		}
		else if ( selectionType == SELECT_ABSOLUTE_PATH )
		{
			DirectoryDialog dialog = new DirectoryDialog( folderLocation.getShell( ) );
			String folderLocationValue = getFolderLocationString( );
			if ( folderLocationValue != null
					&& folderLocationValue.trim( ).length( ) > 0 )
			{
				dialog.setFilterPath( folderLocationValue );
			}

			dialog.setMessage( DEFAULT_MESSAGE );
			String selectedLocation = dialog.open( );
			if ( selectedLocation != null )
			{
				setFolderLocationString( selectedLocation );
			}
		}
	}

	private void setupFileURI( Composite composite )
	{
		fileURIChoice = new Button( composite, SWT.RADIO );
		fileURIChoice.addSelectionListener( new SelectionListener( ) {

			public void widgetSelected( SelectionEvent e )
			{
				switchFileSelectionMode( false );
				needsCheckURITest = true;
				validatePageStatus( );
				needsCheckURITest = false;
			}

			public void widgetDefaultSelected( SelectionEvent e )
			{

			}

		} );
		fileURIChoice.setText( Messages.getString( "label.fileURI" ) ); //$NON-NLS-1$

		GridData data = new GridData( GridData.FILL_HORIZONTAL );
		fileURI = new Text( composite, SWT.BORDER );
		fileURI.setLayoutData( data );
		setPageComplete( false );
		fileURI.setToolTipText( Messages.getString( "lable.fileURI.tooltip" ) ); //$NON-NLS-1$
		fileURI.addModifyListener( new ModifyListener( ) {

			public void modifyText( ModifyEvent e )
			{
				if ( !fileURI.getText( ).trim( ).equals( URIValue ) )
				{
					needsCheckURITest = true;
					validatePageStatus( );
					needsCheckURITest = false;
					URIValue = fileURI.getText( ).trim( );
				}
			}

		} );

		browseLocalFileButton = new MenuButton( composite, SWT.NONE );
		browseLocalFileButton.setText( Messages.getString( "button.selectFileURI.browse" ) ); //$NON-NLS-1$
		browseLocalFileButton.setToolTipText( Messages.getString( "button.selectFileURI.browse.tooltips" ) ); //$NON-NLS-1$

		Menu menu = new Menu( composite.getShell( ), SWT.POP_UP );
		SelectionAdapter action = new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				if ( e.widget instanceof MenuItem )
				{
					MenuItem item = (MenuItem) e.widget;
					Integer type = (Integer) item.getData( );
					handleFileSelection( type );
				}
				else if ( e.widget instanceof MenuButton )
				{
					if ( ri != null )
					{
						handleFileSelection( SELECT_RELATIVE_PATH );
					}
					else
					{
						handleFileSelection( SELECT_ABSOLUTE_PATH );
					}
				}
			}
		};

		MenuItem item;
		if ( ri != null )
		{
			item = new MenuItem( menu, SWT.PUSH );
			item.setText( Messages.getString( "button.selectFileURI.menuItem.relativePath" ) ); //$NON-NLS-1$
			item.setData( SELECT_RELATIVE_PATH );
			item.addSelectionListener( action );
		}

		item = new MenuItem( menu, SWT.PUSH );
		item.setText( Messages.getString( "button.selectFileURI.menuItem.absolutePath" ) ); //$NON-NLS-1$
		item.setData( SELECT_ABSOLUTE_PATH );
		item.addSelectionListener( action );

		// Add relative path selection support while having resource identifier
		browseLocalFileButton.setDropDownMenu( menu );
		browseLocalFileButton.addSelectionListener( action );

		GridData btnData = new GridData( );
		btnData.widthHint = browseLocalFileButton.computeSize( -1, -1 ).x;
		browseLocalFileButton.setLayoutData( btnData );
	}

	private void handleFileSelection( int selectionType )
	{
		if ( selectionType == SELECT_RELATIVE_PATH )
		{
			RelativeFileSelectionDialog dialog = new RelativeFileSelectionDialog( fileURI.getShell( ),
					new File( getResourceFolder( ) ),
					false );
			if ( dialog.open( ) == Window.OK )
			{
				try
				{
					URI uri = dialog.getSelectedURI( );
					if ( uri != null )
					{
						setFileURIString( uri.getPath( ) );
					}
				}
				catch ( URISyntaxException e )
				{
				}
			}
		}
		else if ( selectionType == SELECT_ABSOLUTE_PATH )
		{
			FileDialog dialog = new FileDialog( fileURI.getShell( ) );
			String path = getResourceFolder( );
			if ( path != null && path.trim( ).length( ) > 0 )
			{
				dialog.setFilterPath( path );
			}
			dialog.setFilterExtensions( fileExtensions );
			String filePath = dialog.open( );

			if ( filePath != null )
			{
				setFileURIString( filePath );
			}
		}
	}

	private String getResourceFolder( )
	{
		if ( ri != null )
		{
			if ( ri.getApplResourceBaseURI( ) != null )
			{
				return new File( ri.getApplResourceBaseURI( ) ).getAbsolutePath( );
			}
		}
		return null;
	}

	private void switchFileSelectionMode( boolean homeFolder )
	{
		folderLocation.setEnabled( homeFolder );
		browseFolderButton.setEnabled( homeFolder );
		homeFolderChoice.setSelection( homeFolder );

		fileURI.setEnabled( !homeFolder );
		browseLocalFileButton.setEnabled( !homeFolder );
		fileURIChoice.setSelection( !homeFolder );
	}

	/**
	 * 
	 * @return
	 */
	private int verifyFileLocation( )
	{
		try
		{
			return verifyFileLocation( true );
		}
		catch ( InvalidResourceException e )
		{
			return ERROR_INVALID_PATH;
		}
	}

	private void verfiyFileLocation( ) throws InvalidResourceException
	{
		verifyFileLocation( false );
		setMessage( DEFAULT_MESSAGE, IMessageProvider.NONE );
	}

	private int verifyFileLocation( boolean supressException )
			throws InvalidResourceException
	{
		String folderLocationValue = getFolderLocationString( ).trim( );
		String fileURIValue = getFileURIString( ).trim( );
		// folderLocationValue = folderLocationValue.length( ) > 0 ?
		// folderLocationValue
		// : null;
		fileURIValue = fileURIValue.length( ) > 0 ? fileURIValue : null;

		try
		{
			if ( fileURIChoice.getSelection( ) )
				ResourceLocator.validateFileURI( fileURIValue, ri );
			else if ( homeFolderChoice.getSelection( ) )
				ResourceLocator.validateHomeFolder( folderLocationValue, ri );
		}
		catch ( InvalidResourceException ex )
		{
			setMessage( Messages.getString( "error.invalidFlatFilePath" ), IMessageProvider.ERROR ); //$NON-NLS-1$?
			setPageComplete( false );
			if ( wizardPage == null ) // Otherwise, show error.
			{
				setPageComplete( true );
				if ( !supressException )
				{
					throw ex;
				}
			}
			if ( supressException )
			{
				return ERROR_INVALID_PATH;
			}
			else
			{
				throw ex;
			}
		}

		setPageComplete( true );
		setMessage( DEFAULT_MESSAGE, IMessageProvider.NONE );
		return CORRECT_FOLDER;
	}

	/**
	 * @param composite
	 */
	private void setupCharset( Composite composite )
	{
		Label labelCharSet = new Label( composite, SWT.NONE );
		labelCharSet.setText( Messages.getString( "label.selectCharset" ) ); //$NON-NLS-1$

		charSetSelectionCombo = new Combo( composite, SWT.READ_ONLY );

		GridData data = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
		data.horizontalSpan = 2;
		charSetSelectionCombo.setLayoutData( data );

		charSetMap = Charset.availableCharsets( );
		Object[] charSetsArray = charSetMap.keySet( ).toArray( );
		for ( int i = 0; i < charSetsArray.length; i++ )
		{
			String charSetName = charSetMap.get( charSetsArray[i] ).name( );
			charSetSelectionCombo.add( charSetName );
			if ( CommonConstants.CONN_DEFAULT_CHARSET.equalsIgnoreCase( charSetName ) )
				charSetSelectionCombo.select( i );
		}
	}

	/**
	 * To set up the flatfile styles' list
	 * 
	 * @param composite
	 */
	private void setupFlatfileStyleList( Composite composite )
	{
		Label labelCSVType = new Label( composite, SWT.NONE );
		labelCSVType.setText( Messages.getString( "label.selectFlatfileStyle" ) ); //$NON-NLS-1$

		flatFileStyleCombo = new Combo( composite, SWT.READ_ONLY );
		GridData data = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
		data.horizontalSpan = 2;
		flatFileStyleCombo.setLayoutData( data );

		for ( int i = 0; i < flatFileStyles.length; i++ )
		{
			flatFileStyleCombo.add( flatFileStyles[i] );
		}
		flatFileStyleCombo.select( 0 );
	}

	/**
	 * 
	 * @param composite
	 */
	private void setupColumnNameLineCheckBox( Composite composite )
	{
		Label labelFill = new Label( composite, SWT.NONE );
		labelFill.setText( "" ); //$NON-NLS-1$

		columnNameLineCheckBox = new Button( composite, SWT.CHECK );
		columnNameLineCheckBox.setToolTipText( Messages.getString( "tooltip.columnnameline" ) ); //$NON-NLS-1$
		GridData gd = new GridData( );
		gd.horizontalSpan = 3;
		columnNameLineCheckBox.setLayoutData( gd );
		columnNameLineCheckBox.setText( Messages.getString( "label.includeColumnNameLine" ) ); //$NON-NLS-1$
		columnNameLineCheckBox.setSelection( true );
		columnNameLineCheckBox.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				refreshTypeLineCheckBoxStatus( );
			}
		} );

	}

	/**
	 * @param composite
	 */
	private void setupTypeLineCheckBox( Composite composite )
	{
		typeLineCheckBox = new Button( composite, SWT.CHECK );
		typeLineCheckBox.setToolTipText( Messages.getString( "tooltip.typeline" ) ); //$NON-NLS-1$
		GridData data = new GridData( );
		data.horizontalSpan = 3;
		typeLineCheckBox.setLayoutData( data );
		typeLineCheckBox.setText( Messages.getString( "label.includeTypeLine" ) ); //$NON-NLS-1$

	}

	private void setupTrailNullsCheckBox( Composite composite )
	{
		trailNullColsCheckBox = new Button( composite, SWT.CHECK );
		trailNullColsCheckBox.setToolTipText( Messages.getString( "tooltip.trailNull" ) ); //$NON-NLS-1$
		GridData data = new GridData( );
		data.horizontalSpan = 3;
		trailNullColsCheckBox.setLayoutData( data );
		trailNullColsCheckBox.setText( Messages.getString( "label.trailNull" ) ); //$NON-NLS-1$
		trailNullColsCheckBox.setSelection( false );
		trailNullColsCheckBox.setEnabled( true );
	}

	/**
	 * 
	 * @param complete
	 */
	private void setPageComplete( boolean complete )
	{
		if ( wizardPage != null )
			wizardPage.setPageComplete( complete );
		else if ( propertyPage != null )
			propertyPage.setValid( complete );
	}

	/**
	 * 
	 * @param newMessage
	 * @param newType
	 */
	private void setMessage( String newMessage, int newType )
	{
		if ( wizardPage != null )
			wizardPage.setMessage( newMessage, newType );
		else if ( propertyPage != null )
			propertyPage.setMessage( newMessage, newType );
	}

	private Control getControl( )
	{
		if ( wizardPage != null )
			return wizardPage.getControl( );
		if ( propertyPage != null )
			return propertyPage.getControl( );

		return null;
	}

	public Runnable createTestConnectionRunnable(
			final IConnectionProfile profile )
	{
		return new Runnable( ) {

			public void run( )
			{
				IConnection conn = PingJob.createTestConnection( profile );

				Throwable exception = PingJob.getTestConnectionException( conn );

				if ( exception == null ) // succeed in creating connection
				{
					exception = testConnection( );
				}

				PingJob.PingUIJob.showTestConnectionMessage( parent.getShell( ),
						exception );
				if ( conn != null )
				{
					conn.close( );
				}
			}

			private Throwable testConnection( )
			{
				Throwable exception = null;
				try
				{
					verfiyFileLocation( );
				}
				catch ( InvalidResourceException ex )
				{
					exception = ex;
				}
				return exception;
			}
		};
	}

	public void setResourceIdentifiers(
			org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers resourceIdentifiers )
	{
		if ( resourceIdentifiers != null )
		{
			this.ri = DesignSessionUtil.createRuntimeResourceIdentifiers( resourceIdentifiers );
		}
	}

	public void resetUIStatus( )
	{
		if ( getFileURI( ).length( ) > 0 )
		{
			switchFileSelectionMode( false );
		}
		else
		{
			switchFileSelectionMode( true );
		}
	}

	protected void refreshTypeLineCheckBoxStatus( )
	{
		if ( columnNameLineCheckBox.getSelection( ) )
			typeLineCheckBox.setEnabled( columnNameLineCheckBox.isEnabled( ) );
		else
		{
			typeLineCheckBox.setSelection( false );
			typeLineCheckBox.setEnabled( false );
		}
	}

	private void validatePageStatus( )
	{
		int status = 1;
		if ( homeFolderChoice.getSelection( ) )
		{
			// if ( getFolderLocationString( ).trim( ).length( ) == 0 )
			// {
			//				setMessage( Messages.getString( "error.emptyFolderPath" ), //$NON-NLS-1$?
			// IMessageProvider.ERROR );
			// status = -1;
			// }
			// else
			if ( verifyFileLocation( ) == ERROR_INVALID_PATH )
			{
				setMessage( Messages.getString( "error.invalidFlatFilePath" ), IMessageProvider.ERROR ); //$NON-NLS-1$?
				status = -1;
			}
		}
		else if ( fileURIChoice.getSelection( ) )
		{
			if ( getFileURIString( ).trim( ).length( ) == 0 )
			{
				setMessage( Messages.getString( "error.emptyFileURIPath" ),
						IMessageProvider.ERROR );
				status = -1;
			}
			else if ( needsCheckURITest )
			{
				setMessage( Messages.getString( "Connection.warning.untested" ), IMessageProvider.WARNING ); //$NON-NLS-1$
				status = 0;
			}
		}

		if ( status == 1 )
		{
			setMessage( DEFAULT_MESSAGE, IMessageProvider.NONE );
		}

		setPageComplete( status >= 0 );
	}

}
