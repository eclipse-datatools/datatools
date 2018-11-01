/*
 *************************************************************************
 * Copyright (c) 2006, 2011 Actuate Corporation.
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
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.FlatFileDriver;
import org.eclipse.datatools.connectivity.oda.flatfile.InvalidResourceException;
import org.eclipse.datatools.connectivity.oda.flatfile.ResourceLocator;
import org.eclipse.datatools.connectivity.oda.flatfile.ui.i18n.Messages;
import org.eclipse.datatools.connectivity.oda.flatfile.ui.util.IHelpConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.ui.util.Utility;
import org.eclipse.datatools.connectivity.oda.flatfile.util.querytextutil.ColumnsInfoUtil;
import org.eclipse.datatools.connectivity.oda.flatfile.util.querytextutil.QueryTextUtil;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.util.manifest.ConnectionProfileProperty;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * Extends the ODA design ui framework to provide a driver-specific custom
 * editor page to create or edit an ODA data set design instance.
 */
public class FileSelectionWizardPage extends DataSetWizardPage
{

	private static String DEFAULT_MESSAGE = Messages.getString( "wizard.defaultMessage.selectFile" ); //$NON-NLS-1$
	private static final String ALL_CSV_EXTENSION = "*.csv"; //$NON-NLS-1$
	private static final String CSV_EXTENSION = ".csv"; //$NON-NLS-1$
	private static final String ALL_SSV_EXTENSION = "*.ssv"; //$NON-NLS-1$
	private static final String SSV_EXTENSION = ".ssv"; //$NON-NLS-1$
	private static final String ALL_TSV_EXTENSION = "*.tsv"; //$NON-NLS-1$
	private static final String TSV_EXTENSION = ".tsv"; //$NON-NLS-1$
	private static final String ALL_PSV_EXTENSION = "*.psv"; //$NON-NLS-1$
	private static final String PSV_EXTENSION = ".psv"; //$NON-NLS-1$
	private static final String ALL_TXT_EXTENSION = "*.txt"; //$NON-NLS-1$
	private static final String TXT_EXTENSION = ".txt"; //$NON-NLS-1$
	private static final String MATCH_ALL_FILES = "*.*"; //$NON-NLS-1$

	private static final String queryTextDelimiter = ":"; //$NON-NLS-1$
	private static final String columnsInfoStartSymbol = "{"; //$NON-NLS-1$
	private static final String columnsInfoEndSymbol = "}"; //$NON-NLS-1$

	private static String[] dataTypeDisplayNames = new String[]{
			Messages.getString( "datatypes.dateTime" ), //$NON-NLS-1$
			Messages.getString( "datatypes.decimal" ), //$NON-NLS-1$
			Messages.getString( "datatypes.float" ), //$NON-NLS-1$
			Messages.getString( "datatypes.integer" ), //$NON-NLS-1$
			Messages.getString( "datatypes.date" ), //$NON-NLS-1$
			Messages.getString( "datatypes.time" ), //$NON-NLS-1$
			Messages.getString( "datatypes.string" ), //$NON-NLS-1$
			Messages.getString( "datatypes.boolean" ) //$NON-NLS-1$
	};

	private HashMap<Integer, String> dataTypeDisplayNameMap = new HashMap<Integer, String>( );

	private HashMap<String, String> dataTypeValueMape = new HashMap<String, String>( );

	private HashMap<String, Boolean> flatFileStatusCache = new HashMap<String, Boolean>( );

	private transient ComboViewer fileViewer = null;
	private transient ComboViewer fileFilter = null;
	private transient List availableList = null;
	private transient TableViewer selectedColumnsViewer = null;
	private transient Button btnAdd, btnAddAll, btnRemove, btnRemoveAll,
			btnMoveUp, btnMoveDown;
	private boolean initialized = true;

	private String odaHome;
	private String fileURI;
	private String charSet = null;
	private String inclColumnNameLine;
	private String flatfileDelimiterType;
	private String inclTypeLine;
	private String savedSelectedColumnsInfoString;
	private String trailNullCols;

	/** store latest selected file */
	private String selectedFileFilter;

	/** store latest selected file */
	private Object selectedFile;

	private String nameOfFileWithErrorInLastAccess = null;

	private java.util.List<String[]> originalFileColumnsInfoList = new ArrayList<String[]>( );
	private java.util.List<String[]> savedSelectedColumnsInfoList = new ArrayList<String[]>( );

	/**
	 * @param pageName
	 */
	public FileSelectionWizardPage( String pageName )
	{
		super( pageName );
		setTitle( pageName );
		createColumnTypeMap( );
		setMessage( DEFAULT_MESSAGE );

		setPageComplete( false );
	}

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public FileSelectionWizardPage( String pageName, String title,
			ImageDescriptor titleImage )
	{
		super( pageName, title, titleImage );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage
	 * #createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		ScrolledComposite sComposite = new ScrolledComposite( parent,
				SWT.H_SCROLL | SWT.V_SCROLL );
		sComposite.setLayout( new FillLayout( ) );
		sComposite.setLayoutData( new GridData( GridData.FILL_BOTH ) );
		sComposite.setMinWidth( 560 );
		sComposite.setExpandHorizontal( true );
		sComposite.setMinHeight( 300 );
		sComposite.setExpandVertical( true );

		Composite control = createPageControl( sComposite );

		initializeControl( );

		Point size = control.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		control.setSize( size.x, size.y );

		sComposite.setContent( control );
		setControl( sComposite );

		updateButtonStatus( );
		validatePageStatus( );

		Utility.setSystemHelp( getControl( ),
				IHelpConstants.CONEXT_ID_DATASET_FLATFILE );
	}

	/**
	 * 
	 * 
	 */
	private void createColumnTypeMap( )
	{
		dataTypeDisplayNameMap.put( new Integer( 4 ),
				Messages.getString( "datatypes.integer" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer( 8 ),
				Messages.getString( "datatypes.float" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer( 12 ),
				Messages.getString( "datatypes.string" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer( 91 ),
				Messages.getString( "datatypes.date" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer( 92 ),
				Messages.getString( "datatypes.time" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer( 93 ),
				Messages.getString( "datatypes.dateTime" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer( 2 ),
				Messages.getString( "datatypes.decimal" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer( 16 ),
				Messages.getString( "datatypes.boolean" ) ); //$NON-NLS-1$

		dataTypeValueMape.put( Messages.getString( "datatypes.integer" ), "INT" ); //$NON-NLS-1$ //$NON-NLS-2$
		dataTypeValueMape.put( Messages.getString( "datatypes.float" ), //$NON-NLS-1$
				"DOUBLE" ); //$NON-NLS-1$
		dataTypeValueMape.put( Messages.getString( "datatypes.string" ), //$NON-NLS-1$
				"STRING" ); //$NON-NLS-1$
		dataTypeValueMape.put( Messages.getString( "datatypes.date" ), "DATE" ); //$NON-NLS-1$ //$NON-NLS-2$
		dataTypeValueMape.put( Messages.getString( "datatypes.time" ), "TIME" ); //$NON-NLS-1$ //$NON-NLS-2$
		dataTypeValueMape.put( Messages.getString( "datatypes.dateTime" ), //$NON-NLS-1$
				"TIMESTAMP" ); //$NON-NLS-1$
		dataTypeValueMape.put( Messages.getString( "datatypes.decimal" ), //$NON-NLS-1$
				"BIGDECIMAL" ); //$NON-NLS-1$
		dataTypeValueMape.put( Messages.getString( "datatypes.boolean" ), //$NON-NLS-1$
				"BOOLEAN" ); //$NON-NLS-1$
	}

	/**
	 * 
	 *
	 */
	private void initializeControl( )
	{
		/*
		 * Optionally restores the state of a previous design session. Obtains
		 * designer state, using getInitializationDesignerState();
		 */

		DataSetDesign dataSetDesign = getInitializationDesign( );
		if ( dataSetDesign == null )
			return; // nothing to initialize

		updateFileFilterComboStatus( dataSetDesign );

		String queryText = dataSetDesign.getQueryText( );
		if ( queryText == null )
			return; // nothing to initialize

		updateValuesFromQuery( queryText );

		/*
		 * Optionally honor the request for an editable or read-only design
		 * session isSessionEditable();
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage
	 * #collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.
	 * DataSetDesign)
	 */
	protected DataSetDesign collectDataSetDesign( DataSetDesign design )
	{
		// if this page in DataSetEditor hasn't been activated
		if ( fileViewer == null )
			return design;

		savePage( design );
		return design;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSetWizardPage
	 * #collectResponseState()
	 */
	protected void collectResponseState( )
	{
		super.collectResponseState( );
		/*
		 * Optionally assigns custom response state, for inclusion in the ODA
		 * design session response, using setResponseSessionStatus(
		 * SessionStatus status ) setResponseDesignerState( DesignerState
		 * customState );
		 */
	}

	/**
	 * 
	 * @param parent
	 * @return
	 */
	private Composite createPageControl( Composite parent )
	{

		Composite mainComposite = new Composite( parent, SWT.NONE );
		mainComposite.setLayout( new GridLayout( ) );
		mainComposite.setLayoutData( new GridData( GridData.FILL_BOTH ) );

		createTopComposite( mainComposite );

		Composite composite = new Composite( mainComposite, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.numColumns = 4;
		composite.setLayout( layout );
		composite.setLayoutData( new GridData( GridData.FILL_BOTH ) );

		createLeftComposite( composite );

		createCenterBtnComposite( composite );

		createRightTableComposite( composite );

		createEditBtnGroup( composite );

		loadProperties( );
		populateFileFilter( );
		updateFileListAndCharSet( );
		selectFileChanged( );

		return mainComposite;
	}

	/**
	 * Create the top composite of the page
	 * 
	 * @param composite
	 * @param label
	 */
	private void createTopComposite( Composite parent )
	{
		Composite composite = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.numColumns = 4;
		composite.setLayout( layout );
		composite.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

		Label label = new Label( composite, SWT.NONE );
		label.setText( Messages.getString( "label.selectFile" ) ); //$NON-NLS-1$
		label.setLayoutData( new GridData( ) );

		fileViewer = new ComboViewer( composite, SWT.BORDER | SWT.READ_ONLY );
		fileViewer.getControl( )
				.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
		fileViewer.setContentProvider( new ArrayContentProvider( ) );
		fileViewer.getCombo( ).addSelectionListener( new SelectionListener( ){

			public void widgetDefaultSelected( SelectionEvent arg0 )
			{
				
			}

			public void widgetSelected( SelectionEvent arg0 )
			{
				selectFileChanged( );
			}
			
		});
		fileViewer.setLabelProvider( new LabelProvider( ) {

			public String getText( Object element )
			{
				if ( element instanceof File )
					return ( (File) element ).getName( );
				if ( element instanceof String )
					return (String) element;
				return element.toString( );
			}
		} );

		label = new Label( composite, SWT.NONE );
		label.setText( Messages.getString( "label.fileFilter" ) ); //$NON-NLS-1$
		label.setLayoutData( new GridData( ) );

		fileFilter = new ComboViewer( composite, SWT.READ_ONLY );
		fileFilter.getControl( )
				.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
		fileFilter.addSelectionChangedListener( new ISelectionChangedListener( ) {

			public void selectionChanged( SelectionChangedEvent event )
			{
				if ( fileURI != null && fileURI.length( ) > 0 )
					return;

				String currSelectFilter = fileFilter.getCombo( ).getText( );
				if ( currSelectFilter.equalsIgnoreCase( selectedFileFilter ) )
					return;

				if ( !initialized )
				{
					selectedFileFilter = currSelectFilter;
					updateFileListAndCharSet( );
					initialized = true;
				}
				else
				{
					if ( currSelectFilter.equals( MATCH_ALL_FILES )
							|| MessageDialog.openConfirm( fileViewer.getCombo( )
									.getShell( ),
									Messages.getString( "confirm.reselectFileFilterTitle" ), //$NON-NLS-1$
									Messages.getString( "confirm.reselectFileFilterMessage" ) ) ) //$NON-NLS-1$
					{
						selectedFileFilter = currSelectFilter;
						updateFileListAndCharSet( );
					}
					else
					{
						fileFilter.getCombo( ).setText( selectedFileFilter );
					}
				}
			}
		} );
	}

	/**
	 * Create the left composite of the page
	 * 
	 * @param composite
	 */
	private void createLeftComposite( Composite composite )
	{
		GridData gd = new GridData( GridData.FILL_BOTH );
		gd.widthHint = 230;
		gd.heightHint = 300;
		availableList = new List( composite, SWT.MULTI
				| SWT.BORDER
				| SWT.H_SCROLL
				| SWT.V_SCROLL );

		availableList.setLayoutData( gd );
		availableList.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				selectedColumnsViewer.getTable( ).deselectAll( );
				updateButtonStatus( );
			}
		} );

		availableList.addMouseListener( new MouseAdapter( ) {

			public void mouseDoubleClick( MouseEvent e )
			{
				addColumns( true );

				updateButtonStatus( );
				validatePageStatus( );
			}
		} );
	}

	/**
	 * Create the middle button composite that displays ADD button
	 * 
	 * @param composite
	 * @return
	 */
	private void createCenterBtnComposite( Composite composite )
	{
		Composite btnComposite = new Composite( composite, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.verticalSpacing = 5;
		btnComposite.setLayout( layout );

		GridData gridData = new GridData( );
		gridData.widthHint = 40;
		btnAdd = new Button( btnComposite, SWT.NONE );
		btnAdd.setLayoutData( gridData );
		btnAdd.setText( ">" ); //$NON-NLS-1$
		btnAdd.setToolTipText( Messages.getString( "tooltip.button.add" ) ); //$NON-NLS-1$

		btnAdd.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				addColumns( true );

				updateButtonStatus( );
				validatePageStatus( );
			}
		} );

		gridData = new GridData( );
		gridData.widthHint = 40;
		btnAddAll = new Button( btnComposite, SWT.NONE );
		btnAddAll.setLayoutData( gridData );
		btnAddAll.setText( ">>" ); //$NON-NLS-1$
		btnAddAll.setToolTipText( Messages.getString( "tooltip.button.AddAll" ) ); //$NON-NLS-1$

		btnAddAll.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				addAllAvailableColumns( );

				updateButtonStatus( );
				validatePageStatus( );
			}
		} );

		gridData = new GridData( );
		gridData.widthHint = 40;
		btnRemove = new Button( btnComposite, SWT.NONE );
		btnRemove.setLayoutData( gridData );
		btnRemove.setText( "<" ); //$NON-NLS-1$
		btnRemove.setToolTipText( Messages.getString( "tooltip.button.remove" ) ); //$NON-NLS-1$

		btnRemove.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				removeColumns( );
				updateButtonStatus( );
				validatePageStatus( );
			}
		} );

		gridData = new GridData( );
		gridData.widthHint = 40;
		btnRemoveAll = new Button( btnComposite, SWT.NONE );
		btnRemoveAll.setLayoutData( gridData );
		btnRemoveAll.setText( "<<" ); //$NON-NLS-1$
		btnRemoveAll.setToolTipText( Messages.getString( "tooltip.button.RemoveAll" ) ); //$NON-NLS-1$

		btnRemoveAll.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				removeAllColumns( );
				updateButtonStatus( );
				validatePageStatus( );
			}
		} );

	}

	/**
	 * Create the right composite of the page
	 * 
	 * @param composite
	 * @param btnComposite
	 */
	private void createRightTableComposite( Composite composite )
	{
		selectedColumnsViewer = new TableViewer( composite, SWT.MULTI
				| SWT.FULL_SELECTION
				| SWT.BORDER
				| SWT.H_SCROLL
				| SWT.V_SCROLL );
		selectedColumnsViewer.getTable( ).setHeaderVisible( true );
		selectedColumnsViewer.getTable( ).setLinesVisible( true );
		selectedColumnsViewer.getTable( )
				.setLayoutData( new GridData( GridData.FILL_BOTH ) );

		TableColumn column = new TableColumn( selectedColumnsViewer.getTable( ),
				SWT.NONE );
		column.setText( Messages.getString( "editor.title.name" ) ); //$NON-NLS-1$
		column.setWidth( 100 );
		column = new TableColumn( selectedColumnsViewer.getTable( ), SWT.NONE );
		column.setText( Messages.getString( "editor.title.originalName" ) ); //$NON-NLS-1$
		column.setWidth( 100 );
		column = new TableColumn( selectedColumnsViewer.getTable( ), SWT.NONE );
		column.setText( Messages.getString( "editor.title.type" ) ); //$NON-NLS-1$
		column.setWidth( 100 );

		Menu menu = new Menu( selectedColumnsViewer.getTable( ) );
		menu.addMenuListener( new MenuAdapter( ) {

			public void menuShown( MenuEvent e )
			{
				selectedColumnsViewer.cancelEditing( );
			}
		} );

		final MenuItem menuRemove = new MenuItem( menu, SWT.NONE );
		menuRemove.setText( Messages.getString( "FileSelectionWizardPage.MenuItem.remove" ) ); //$NON-NLS-1$
		menuRemove.setEnabled( selectedColumnsViewer.getTable( )
				.getSelectionCount( ) > 0 );
		menuRemove.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				removeColumns( );
				updateButtonStatus( );
				validatePageStatus( );
			}

		} );
		MenuItem menuRemoveAll = new MenuItem( menu, SWT.NONE );
		menuRemoveAll.setText( Messages.getString( "FileSelectionWizardPage.MenuItem.removeAll" ) ); //$NON-NLS-1$
		menuRemoveAll.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				removeAllColumns( );
				updateButtonStatus( );
				validatePageStatus( );
			}

		} );

		selectedColumnsViewer.getTable( ).setMenu( menu );

		selectedColumnsViewer.getTable( )
				.addSelectionListener( new SelectionAdapter( ) {

					public void widgetSelected( SelectionEvent e )
					{
						menuRemove.setEnabled( selectedColumnsViewer.getTable( )
								.getSelectionCount( ) > 0 );

						availableList.deselectAll( );
						updateButtonStatus( );

					}
				} );

		selectedColumnsViewer.addDoubleClickListener( new IDoubleClickListener( ) {

			public void doubleClick( DoubleClickEvent event )
			{
				doEdit( );
			}

		} );

		selectedColumnsViewer.getTable( ).addKeyListener( new KeyListener( ) {

			public void keyPressed( KeyEvent e )
			{
				if ( e.keyCode == SWT.DEL )
				{
					removeColumns( );
					updateButtonStatus( );
					validatePageStatus( );
				}
			}

			public void keyReleased( KeyEvent e )
			{

			}
		} );

		setColumnsViewerContent( );

		setColumnsViewerLabels( );

	}

	protected void doEdit( )
	{
		if ( selectedColumnsViewer.getTable( ).getSelection( ).length > 0 )
		{
			ColumnEditDialog editDialog = new ColumnEditDialog( PlatformUI.getWorkbench( )
					.getDisplay( )
					.getActiveShell( ) );

			TableItem item = selectedColumnsViewer.getTable( ).getSelection( )[0];
			editDialog.setInput( item.getText( 0 ),
					item.getText( 1 ),
					item.getText( 2 ) );

			if ( editDialog.open( ) == Window.OK )
			{
				int index = selectedColumnsViewer.getTable( )
						.getSelectionIndex( );

				savedSelectedColumnsInfoList.set( index,
						new String[]{
								editDialog.getColumnName( ),
								editDialog.getOriginalName( ),
								editDialog.getDataType( )
						} );

				selectedColumnsViewer.refresh( );
				validatePageStatus( );

			}

		}

	}

	/**
	 * Create the right button group that displays the UP,DOWN and REMOVE
	 * buttons
	 * 
	 * @param rightComposite
	 */
	private void createEditBtnGroup( Composite rightComposite )
	{
		Composite btnComposite = new Composite( rightComposite, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.verticalSpacing = 5;
		btnComposite.setLayout( layout );

		btnMoveUp = new Button( btnComposite, SWT.NONE );
		btnMoveUp.setText( Messages.getString( "button.moveUp" ) ); //$NON-NLS-1$
		btnMoveUp.setToolTipText( Messages.getString( "tooltip.button.up" ) ); //$NON-NLS-1$
		btnMoveUp.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				moveUpItem( );
			}
		} );

		btnMoveDown = new Button( btnComposite, SWT.NONE );
		btnMoveDown.setText( Messages.getString( "button.moveDown" ) ); //$NON-NLS-1$
		btnMoveDown.setToolTipText( Messages.getString( "tooltip.button.down" ) ); //$NON-NLS-1$
		btnMoveDown.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				moveDownItem( );
			}
		} );

		resetButtonWidth( );
	}

	private void resetButtonWidth( )
	{
		int widthHint = btnMoveUp.computeSize( SWT.DEFAULT, SWT.DEFAULT ).x;
		widthHint = Math.max( widthHint,
				btnMoveDown.computeSize( SWT.DEFAULT, SWT.DEFAULT ).x );
		widthHint = Math.max( widthHint, 52 );
		GridData btnGd = new GridData( );
		btnGd.widthHint = widthHint;
		btnMoveUp.setLayoutData( btnGd );
		btnMoveDown.setLayoutData( btnGd );
	}

	/**
	 * Set the labels of the ColumnsViewer
	 * 
	 */
	private void setColumnsViewerLabels( )
	{
		selectedColumnsViewer.setLabelProvider( new ITableLabelProvider( ) {

			public Image getColumnImage( Object element, int columnIndex )
			{
				return null;
			}

			public String getColumnText( Object element, int columnIndex )
			{
				return ( (String[]) element )[columnIndex];
			}

			public void addListener( ILabelProviderListener listener )
			{

			}

			public void dispose( )
			{

			}

			public boolean isLabelProperty( Object element, String property )
			{
				return false;
			}

			public void removeListener( ILabelProviderListener listener )
			{

			}

		} );
	}

	/**
	 * Set the content of the ColumnsViewer
	 * 
	 */
	private void setColumnsViewerContent( )
	{
		selectedColumnsViewer.setContentProvider( new IStructuredContentProvider( ) {

			@SuppressWarnings("rawtypes")
			public Object[] getElements( Object inputElement )
			{
				if ( inputElement instanceof java.util.List )
				{
					return ( (java.util.List) inputElement ).toArray( );
				}

				return new Object[0];
			}

			public void dispose( )
			{

			}

			public void inputChanged( Viewer viewer, Object oldInput,
					Object newInput )
			{

			}

		} );
	}

	/**
	 * get the count of hte existence of the given column name in the already
	 * saved selected columns information
	 * 
	 * @param columnName
	 *            given column name
	 * @return
	 */

	private int getExistenceCount( String columnName )
	{
		int count = 0;
		java.util.List<String[]> existedColumns = new ArrayList<String[]>( );

		for ( int i = 0; i < savedSelectedColumnsInfoList.size( ); i++ )
		{
			if ( columnName.equals( ( (String[]) savedSelectedColumnsInfoList.get( i ) )[1] ) )
			{
				count++;
				existedColumns.add( savedSelectedColumnsInfoList.get( i ) );
			}
		}

		for ( int j = 0; j < existedColumns.size( ); j++ )
		{
			if ( ( columnName + "_" + count ).equals( ( (String[]) existedColumns.get( j ) )[0] ) ) //$NON-NLS-1$
			{
				count++;
				j = -1;
			}
		}

		return count;
	}


	private void updateAvailableColumnsInfo( String fileName )
	{
		String[] columnNames = getFileColumnNames( fileName );

		if ( columnNames != null && columnNames.length != 0 )
		{
			enableListAndViewer( );
			availableList.setItems( columnNames );
			availableList.select( 0 );

			updateButtonStatus( );

			if ( !( fileName.endsWith( CSV_EXTENSION )
					|| fileName.endsWith( TXT_EXTENSION )
					|| fileName.endsWith( SSV_EXTENSION )
					|| fileName.endsWith( TSV_EXTENSION ) || fileName.endsWith( PSV_EXTENSION ) ) )
			{
				setMessage( Messages.getString( "warning.fileExtensionInvalid" ), //$NON-NLS-1$
						WARNING );
			}
			else
				setMessage( DEFAULT_MESSAGE );
		}
	}

	private void updateButtonStatus( )
	{
		int availableListItemCount = availableList.getItemCount( );
		int selectedColumnsItemCount = selectedColumnsViewer.getTable( )
				.getItemCount( );

		if ( availableList.getSelectionCount( ) > 0 )
		{
			btnAdd.setEnabled( true );
			btnRemove.setEnabled( false );
			btnMoveUp.setEnabled( false );
			btnMoveDown.setEnabled( false );
		}
		else
		{
			int count = selectedColumnsViewer.getTable( ).getSelectionCount( );

			if ( count < 1 )
			{
				btnAdd.setEnabled( false );
				btnRemove.setEnabled( false );
				btnMoveUp.setEnabled( false );
				btnMoveDown.setEnabled( false );
			}
			else if ( count > 1 )
			{
				btnAdd.setEnabled( false );
				btnRemove.setEnabled( true );
				btnMoveUp.setEnabled( false );
				btnMoveDown.setEnabled( false );
			}
			else
			{
				int index = selectedColumnsViewer.getTable( )
						.getSelectionIndex( );
				btnAdd.setEnabled( false );
				btnRemove.setEnabled( true );
				btnMoveUp.setEnabled( index > 0 );
				btnMoveDown.setEnabled( index >= 0
						&& index < ( selectedColumnsItemCount - 1 ) );
			}

		}

		btnAddAll.setEnabled( availableListItemCount > 0 );
		btnRemoveAll.setEnabled( selectedColumnsItemCount > 0 );
	}

	private void updateAvailableListSelection( )
	{
		int[] indices = availableList.getSelectionIndices( );
		availableList.deselectAll( );
		if ( indices.length > 0 )
		{
			int nextIndex = indices[indices.length - 1] + 1;
			if ( availableList.getItemCount( ) > nextIndex )
			{
				availableList.select( nextIndex );
			}
			else
			{
				availableList.select( availableList.getItemCount( ) - 1 );
			}
		}
	}

	private void updateSelectedItemsSelection( int nextIndex )
	{
		int itemCount = selectedColumnsViewer.getTable( ).getItemCount( );

		availableList.deselectAll( );
		selectedColumnsViewer.getTable( ).deselectAll( );

		if ( itemCount == 0 )
		{
			if ( availableList.getItemCount( ) > 0 )
			{
				availableList.select( 0 );
			}
		}
		else if ( itemCount > nextIndex )
		{
			selectedColumnsViewer.getTable( ).select( nextIndex );
		}
		else if ( itemCount > 0 )
		{
			selectedColumnsViewer.getTable( ).select( itemCount - 1 );
		}
	}

	private boolean validateSelectedFileStatus( )
	{
		String fileName = fileViewer.getCombo( ).getText( ).trim( );
		if ( flatFileStatusCache.containsKey( fileName ) )
		{
			return flatFileStatusCache.get( fileName );
		}
		return true;
	}

	private void clearSelectedFileStatus( )
	{
		String fileName = fileViewer.getCombo( ).getText( ).trim( );
		if ( flatFileStatusCache.containsKey( fileName ) )
		{
			flatFileStatusCache.remove( fileName );
		}
	}

	private void validatePageStatus( )
	{
		if ( !validateSelectedFileStatus( ) )
		{
			clearSelectedFileStatus( );
			return;
		}
		boolean pageComplete = true;
		String[] columnNames = availableList.getItems( );
		for ( int i = 0; i < savedSelectedColumnsInfoList.size( ); i++ )
		{
			String columnName = ( (String[]) savedSelectedColumnsInfoList.get( i ) )[0];
			if ( columnName == null || isNumeric( columnName ) )
			{
				setMessage( Messages.getString( "FileSelectionWizardPage.error.selectColumn.numberName" ), //$NON-NLS-1$
						ERROR );
				pageComplete = false;
				break;
			}
			String originalName = ( (String[]) savedSelectedColumnsInfoList.get( i ) )[1];
			boolean columnExists = false;
			for ( int k = 0; k < columnNames.length; k++ )
			{
				if ( ( columnName != null && columnName.equals( columnNames[k] ) )
						|| ( originalName != null && originalName.equals( columnNames[k] ) ) )
				{
					columnExists = true;
					break;
				}
			}
			if ( !columnExists )
			{
				setMessage( Messages.getFormattedString( "warning.columnNotExist", new Object[]{columnName} ), //$NON-NLS-1$
						ERROR );
				pageComplete = false;
				break;
			}
		}
		if ( savedSelectedColumnsInfoList.size( ) <= 0 )
		{
			setMessage( Messages.getString( "FileSelectionWizardPage.error.selectColumn.NoColumnSelected" ), //$NON-NLS-1$
					ERROR );
			pageComplete = false;
		}

		if ( pageComplete )
		{
			setMessage( DEFAULT_MESSAGE );
		}
		setPageComplete( pageComplete );
	}

	/**
	 * If text is a decimal presentation of int32 value, then text is index. In
	 * this case return true and make send out error message. Otherwise return
	 * false means the column name is right.
	 */
	private boolean isNumeric( String text )
	{
		long indexTest = indexFromString( text );
		if ( indexTest >= 0 )
		{
			return true;
		}
		return false;
	}

	private long indexFromString( String str )
	{
		// The length of the decimal string representation of
		// Integer.MAX_VALUE, 2147483647
		final int MAX_VALUE_LENGTH = 10;

		int len = str.length( );
		if ( len > 0 )
		{
			int i = 0;
			boolean negate = false;
			int c = str.charAt( 0 );
			if ( c == '-' )
			{
				if ( len > 1 )
				{
					c = str.charAt( 1 );
					i = 1;
					negate = true;
				}
			}
			c -= '0';
			if ( 0 <= c
					&& c <= 9
					&& len <= ( negate ? MAX_VALUE_LENGTH + 1
							: MAX_VALUE_LENGTH ) )
			{
				// Use negative numbers to accumulate index to handle
				// Integer.MIN_VALUE that is greater by 1 in absolute value
				// then Integer.MAX_VALUE
				int index = -c;
				int oldIndex = 0;
				i++;
				if ( index != 0 )
				{
					// Note that 00, 01, 000 etc. are not indexes
					while ( i != len
							&& 0 <= ( c = str.charAt( i ) - '0' )
							&& c <= 9 )
					{
						oldIndex = index;
						index = 10 * index - c;
						i++;
					}
				}
				// Make sure all characters were consumed and that it
				// couldn't
				// have overflowed.
				if ( i == len
						&& ( oldIndex > ( Integer.MIN_VALUE / 10 ) || ( oldIndex == ( Integer.MIN_VALUE / 10 ) && c <= ( negate ? -( Integer.MIN_VALUE % 10 )
								: ( Integer.MAX_VALUE % 10 ) ) ) ) )
				{
					return 0xFFFFFFFFL & ( negate ? index : -index );
				}
			}
		}
		return -1L;
	}

	/**
	 * Load the custom properties
	 */
	private void loadProperties( )
	{
		DataSourceDesign dataSourceDesign = getInitializationDesign( ).getDataSourceDesign( );
		java.util.Properties dataSourceProps = null;
		try
		{
			dataSourceProps = DesignSessionUtil.getEffectiveDataSourceProperties( dataSourceDesign );
		}
		catch ( OdaException e )
		{
			this.setMessage( e.getLocalizedMessage( ), ERROR );
			return;
		}

		String sourcePath = dataSourceProps.getProperty( ConnectionProfileProperty.PROFILE_STORE_FILE_PATH_PROP_KEY );
		if ( sourcePath != null )
		{
			File cpFile = DesignUtil.convertPathToResourceFile( sourcePath,
					dataSourceDesign.getHostResourceIdentifiers( ) );
			if ( cpFile == null )
			{
				setMessage( Messages.getFormattedString( "error.invalidConnectionFilePath", new Object[]{sourcePath} ), ERROR ); //$NON-NLS-1$
				return;
			}
		}

		odaHome = dataSourceProps.getProperty( CommonConstants.CONN_HOME_DIR_PROP );
		if ( odaHome == null )
			odaHome = "";
		fileURI = dataSourceProps.getProperty( CommonConstants.CONN_FILE_URI_PROP );
		charSet = dataSourceProps.getProperty( CommonConstants.CONN_CHARSET_PROP );
		flatfileDelimiterType = dataSourceProps.getProperty( CommonConstants.CONN_DELIMITER_TYPE );
		inclColumnNameLine = dataSourceProps.getProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP );
		inclTypeLine = dataSourceProps.getProperty( CommonConstants.CONN_INCLTYPELINE_PROP );
		trailNullCols = dataSourceProps.getProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP );

		verifyFileLocation( );
	}

	private void verifyFileLocation( )
	{
		if ( fileURI != null && fileURI.length( ) > 0 )
		{
			try
			{
				ResourceLocator.validateFileURI( fileURI,
						this.getResourceIdentifiers( ) );
			}
			catch ( InvalidResourceException ex )
			{
				this.setMessage( Messages.getFormattedString( "Connection.error.invalidFileURI", //$NON-NLS-1$
						new Object[]{
							fileURI
						} ),
						ERROR );
				fileURI = null;
			}
		}
		else if ( odaHome != null )
		{
			try
			{
				ResourceLocator.validateHomeFolder( odaHome,
						this.getResourceIdentifiers( ) );
			}
			catch ( InvalidResourceException e )
			{
				this.setMessage( Messages.getFormattedString( "Connection.error.invalidHomeFolder", //$NON-NLS-1$
						new Object[]{
							odaHome
						} ),
						ERROR );
				odaHome = null;
			}
		}
		else
		{
			this.setMessage( Messages.getString( "Connection.error.invalidPath" //$NON-NLS-1$
			), ERROR );
			fileURI = null;
			odaHome = null;
		}
	}

	/**
	 * Update file list in combo viewer
	 */
	private void updateFileListAndCharSet( )
	{
		if ( fileViewer != null && !fileViewer.getControl( ).isDisposed( ) )
		{
			if ( odaHome == null && fileURI == null )
			{
				disableAll( );
				return;
			}

			ArrayList<Object> allFiles = new ArrayList<Object>( );
			if ( fileURI == null )
			{
				try
				{
					File folder = ResourceLocator.getHomeFolderFile( odaHome,
							this.getResourceIdentifiers( ) );
					if ( folder.isDirectory( ) && folder.exists( ) )
					{
						File[] files = folder.getAbsoluteFile( )
								.listFiles( new CSVFileFilter( fileFilter.getCombo( )
										.getText( ) ) );

						allFiles.addAll( Arrays.asList( files ) );
					}
				}
				catch ( InvalidResourceException e )
				{
				}
			}
			else if ( fileURI != null )
			{
				allFiles.add( fileURI );
			}
			fileViewer.setInput( allFiles.toArray( ) );

			if ( allFiles.size( ) <= 0 )
			{
				setMessage( Messages.getFormattedString( "error.noCSVFiles", //$NON-NLS-1$
						new Object[]{
							new File( odaHome ).getAbsolutePath( )
						} ) );
				disableAll( );
			}
			else
			{
				updateFileSelection( );
				updateButtonStatus( );
			}
		}
	}

	private void updateFileSelection( )
	{
		Object[] files = (Object[]) fileViewer.getInput( );
		if ( files.length > 0 )
		{
			enableListAndViewer( );
			Object toSelectFile = null;
			if ( selectedFile != null )
				for ( int i = 0; i < files.length; i++ )
				{
					if ( files[i].equals( selectedFile ) )
					{
						toSelectFile = selectedFile;
						break;
					}
				}
			if ( toSelectFile == null )
				toSelectFile = files[0];

			fileViewer.setSelection( new StructuredSelection( toSelectFile ) );
			if ( !( nameOfFileWithErrorInLastAccess != null && nameOfFileWithErrorInLastAccess.equals( fileViewer.getCombo( )
					.getText( ) ) ) )
				setMessage( DEFAULT_MESSAGE );
		}
		else
		{
			setMessage( Messages.getFormattedString( "error.noCSVFiles", //$NON-NLS-1$
					new Object[]{
						new File( odaHome ).getAbsolutePath( )
					} ) );
			disableAll( );
		}
	}

	/**
	 * Returns all the column names found in given flatfile.
	 * 
	 * @param file
	 * @return
	 */
	private String[] getFileColumnNames( String file )
	{
		java.util.List<String[]> propList = getQueryColumnsInfo( "select * from " + QueryTextUtil.getQuotedName( file ) ); //$NON-NLS-1$

		String[] result;
		if ( propList != null )
		{
			originalFileColumnsInfoList = new ArrayList<String[]>( propList );
			result = new String[propList.size( )];
			for ( int i = 0; i < propList.size( ); i++ )
				result[i] = propList.get( i )[1];
		}
		else
			result = new String[0];

		return result;
	}

	/**
	 * 
	 * @param queryText
	 * @param file
	 * @return
	 */
	private java.util.List<String[]> getQueryColumnsInfo( String queryText )
	{
		IDriver ffDriver = new FlatFileDriver( );
		IConnection conn = null;
		java.util.List<String[]> columnList = new ArrayList<String[]>( );
		try
		{
			conn = ffDriver.getConnection( null );
			IResultSetMetaData metadata = getResultSetMetaData( queryText, conn );

			int columnCount = metadata.getColumnCount( );
			if ( columnCount == 0 )
				return new ArrayList<String[]>( );

			for ( int i = 0; i < columnCount; i++ )
			{
				String[] result = new String[3];
				result[0] = metadata.getColumnName( i + 1 );

				result[1] = getOriginalColumnName( result[0],
						savedSelectedColumnsInfoString,
						metadata );

				result[2] = getDataTypeDisplayName( new Integer( metadata.getColumnType( i + 1 ) ) );
				columnList.add( result );
			}
			flatFileStatusCache.put( fileViewer.getCombo( ).getText( ).trim( ),
					true );
			return columnList;
		}
		catch ( OdaException e )
		{
			flatFileStatusCache.put( fileViewer.getCombo( ).getText( ).trim( ),
					false );
			setMessage( e.getLocalizedMessage( ), ERROR );
			updateExceptionInfo( );
			return new ArrayList<String[]>( );
		}
		finally
		{
			closeConnection( conn );
		}

	}

	private void updateExceptionInfo( )
	{
		nameOfFileWithErrorInLastAccess = fileViewer.getCombo( ).getText( );
		if ( availableList.getItemCount( ) == 0 )
			disableAvailableListAndButtons( );
	}

	private void disableAvailableListAndButtons( )
	{
		availableList.setEnabled( false );
		btnAdd.setEnabled( false );
		btnAddAll.setEnabled( false );
		btnRemove.setEnabled( false );
		btnRemoveAll.setEnabled( false );
		btnMoveDown.setEnabled( false );
		btnMoveUp.setEnabled( false );
	}

	/**
	 * get the data type display name
	 * 
	 * @param type
	 * @return
	 */
	private String getDataTypeDisplayName( Integer type )
	{
		if ( dataTypeDisplayNameMap.get( type ) != null )
			return (String) dataTypeDisplayNameMap.get( type );
		else
			return Messages.getString( "datatypes.string" ); //$NON-NLS-1$
	}

	/**
	 * 
	 * @param displayName
	 * @return
	 */
	private String getDataTypeValue( String displayName )
	{
		if ( dataTypeValueMape.get( displayName ) != null )
			return (String) dataTypeValueMape.get( displayName );
		else
			return "STRING"; //$NON-NLS-1$
	}

	/**
	 * Get the original column name in the flatfile of the given column name
	 * 
	 * @param name
	 * @param columnsInfo
	 * @param metadata
	 * @return
	 */
	private String getOriginalColumnName( String name, String columnsInfo,
			IResultSetMetaData metadata )
	{
		String originalName = null;
		if ( columnsInfo.length( ) != 0 )
		{
			ColumnsInfoUtil ciu = new ColumnsInfoUtil( columnsInfo );
			String[] names = ciu.getColumnNames( );
			for ( int i = 0; i < names.length; i++ )
			{
				if ( name.equals( names[i] ) )
				{
					originalName = ciu.getOriginalColumnNames( )[i];
					break;
				}
			}
		}

		// if this name was not selected in the viewer
		if ( originalName == null )
		{

			try
			{
				for ( int j = 0; j < metadata.getColumnCount( ); j++ )
				{
					if ( name.equals( metadata.getColumnName( j + 1 ) ) )
						originalName = name;
				}
			}
			catch ( OdaException e )
			{
				e.printStackTrace( );
			}

		}

		return originalName;
	}

	/**
	 * Attempts to close given ODA connection.
	 * 
	 * @param conn
	 */
	private void closeConnection( IConnection conn )
	{
		try
		{
			if ( conn != null )
				conn.close( );
		}
		catch ( OdaException e )
		{
			// ignore
		}
	}

	/**
	 * 
	 * @param queryText
	 * @param file
	 * @param conn
	 * @return
	 * @throws OdaException
	 */
	private IResultSetMetaData getResultSetMetaData( String queryText,
			IConnection conn ) throws OdaException
	{
		java.util.Properties prop = new java.util.Properties( );
		if ( odaHome != null )
		{
			prop.put( CommonConstants.CONN_HOME_DIR_PROP, odaHome );
		}
		if ( fileURI != null )
		{
			prop.put( CommonConstants.CONN_FILE_URI_PROP, fileURI );
		}
		if ( flatfileDelimiterType != null )
		{
			prop.put( CommonConstants.CONN_DELIMITER_TYPE,
					flatfileDelimiterType );
		}
		if ( charSet != null )
		{
			prop.put( CommonConstants.CONN_CHARSET_PROP, charSet );
		}
		if ( inclColumnNameLine != null )
		{
			prop.put( CommonConstants.CONN_INCLCOLUMNNAME_PROP,
					inclColumnNameLine );
		}
		if ( inclTypeLine != null )
		{
			prop.put( CommonConstants.CONN_INCLTYPELINE_PROP, inclTypeLine );
		}
		if ( trailNullCols != null )
		{
			prop.put( CommonConstants.CONN_TRAILNULLCOLS_PROP, trailNullCols );
		}

		savedSelectedColumnsInfoString = ( new QueryTextUtil( queryText ) ).getColumnsInfo( );

		Map<String, Object> appContext = DesignSessionUtil.createResourceIdentifiersContext( getHostResourceIdentifiers( ) );
		conn.setAppContext( appContext );
		conn.open( prop );

		IQuery query = conn.newQuery( null );
		query.setMaxRows( 1 );
		query.prepare( queryText );
		query.executeQuery( );

		return query.getMetaData( );
	}

	private ResourceIdentifiers getResourceIdentifiers( )
	{
		return DesignSessionUtil.createRuntimeResourceIdentifiers( getHostResourceIdentifiers( ) );
	}

	/**
	 * Enable all control of this page
	 */
	private void enableListAndViewer( )
	{
		availableList.setEnabled( true );
		selectedColumnsViewer.getTable( ).setEnabled( true );
	}

	/**
	 * Disable all control of this page
	 */
	private void disableAll( )
	{
		availableList.setEnabled( false );
		selectedColumnsViewer.getTable( ).setEnabled( false );
		btnAdd.setEnabled( false );
		btnRemove.setEnabled( false );
		btnRemoveAll.setEnabled( false );
		btnMoveUp.setEnabled( false );
		btnMoveDown.setEnabled( false );
		setPageComplete( false );
	}

	/**
	 * display the content of the list in the table viewer
	 * 
	 * @param list
	 *            list that contains the dispay content
	 * @param tViewer
	 *            the table viewer
	 */
	private void setDisplayContent( java.util.List<String[]> list )
	{
		selectedColumnsViewer.getTable( ).removeAll( );
		selectedColumnsViewer.setInput( list );
		selectedColumnsViewer.getTable( )
				.select( selectedColumnsViewer.getTable( ).getTopIndex( ) );
	}

	/**
	 * get the query text(select clause) of this data set
	 * 
	 * @return query
	 * 
	 */
	private String getQuery( )
	{
		if ( selectedColumnsViewer.getTable( ).getItemCount( ) == 0 )
			return ""; //$NON-NLS-1$

		String tableName = null;
		StringBuffer buf = new StringBuffer( );
		Object file = ( (StructuredSelection) fileViewer.getSelection( ) ).getFirstElement( );
		if ( file != null )
		{
			if ( file instanceof File )
				tableName = ( (File) file ).getName( );
			else if ( file instanceof String )
				tableName = (String) file;
		}
		if ( tableName != null )
		{
			tableName = QueryTextUtil.getQuotedName( tableName );
			if ( availableList.getItemCount( ) == 0 )
			{
				buf.append( "select * from " ).append( tableName ); //$NON-NLS-1$
			}
			else
			{
				buf.append( "select " ); //$NON-NLS-1$
				String[] columns = new String[selectedColumnsViewer.getTable( )
						.getItemCount( )];
				for ( int m = 0; m < columns.length; m++ )
					columns[m] = selectedColumnsViewer.getTable( )
							.getItem( m )
							.getText( 1 );

				for ( int n = 0; n < columns.length; n++ )
				{
					StringBuffer sb = new StringBuffer( );
					char[] columnChars = columns[n].toCharArray( );
					for ( int i = 0; i < columnChars.length; i++ )
					{
						if ( columnChars[i] == '"' )
							sb.append( "\\\"" ); //$NON-NLS-1$
						else if ( columnChars[i] == '\\' )
							sb.append( "\\\\" ); //$NON-NLS-1$
						else
							sb.append( columnChars[i] );
					}

					buf.append( CommonConstants.DELIMITER_DOUBLEQUOTE
							+ sb.toString( )
							+ CommonConstants.DELIMITER_DOUBLEQUOTE );
					if ( n < columns.length - 1 )
					{
						buf.append( ", " ); //$NON-NLS-1$
					}
				}
				buf.append( " from " ).append( tableName ); //$NON-NLS-1$
			}
		}
		return buf.toString( );
	}

	/**
	 * Update value of query text
	 * 
	 * @param queryText
	 */

	private void updateValuesFromQuery( String queryText )
	{
		if ( queryText.length( ) == 0 )
			return;

		try
		{
			String query = ( new QueryTextUtil( queryText ) ).getQuery( );
			String[] metadata = QueryTextUtil.getQueryMetaData( query );

			// The query must have a table name and columns.
			if ( metadata != null && metadata[0] != null && metadata[2] != null )
			{
				// Now select the table in the list. If it doesn't exists, no
				// need to process the columns.
				String f = selectTableFromQuery( metadata[2] );
				if ( f != null )
				{
					updateColumnsFromQuery( queryText, f );
				}
			}
		}
		catch ( OdaException e )
		{
			setMessage( e.getLocalizedMessage( ), ERROR );
			updateExceptionInfo( );
		}

	}

	private void updateFileFilterComboStatus( DataSetDesign dataSetDesign )
	{
		Properties properties = dataSetDesign.getDataSourceDesign( )
				.getPublicProperties( );
		Object value = properties.getProperty( CommonConstants.CONN_HOME_DIR_PROP );
		if ( value instanceof String )
		{
			String folder = (String) value;
			if ( folder == null || folder.trim( ).length( ) == 0 )
			{
				fileFilter.getCombo( ).setEnabled( false );
			}
		}
		else
		{
			fileFilter.getCombo( ).setEnabled( false );
		}
	}

	/**
	 * 
	 * @param queryText
	 * @param file
	 */
	private void updateColumnsFromQuery( String queryText, String file )
	{
		availableList.setItems( getFileColumnNames( file ) );
		selectedColumnsViewer.getTable( ).removeAll( );

		savedSelectedColumnsInfoList.clear( );

		savedSelectedColumnsInfoList = getQueryColumnsInfo( queryText );

		setDisplayContent( savedSelectedColumnsInfoList );

		setPageComplete( true );

		if ( selectedColumnsViewer.getTable( ).getItemCount( ) == 0 )
		{
			setPageComplete( false );
		}

		updateButtonStatus( );

	}

	/**
	 * @param tableName
	 * @return File
	 */
	private String selectTableFromQuery( String tableName )
	{
		String selected = null;
		if ( fileURI != null && fileURI.length( ) > 0 )
		{
			if ( !fileURI.equals( tableName ) )
			{
				availableList.removeAll( );
				nameOfFileWithErrorInLastAccess = null;
				MessageDialog.openWarning( getShell( ),
						Messages.getString( "fileURIChanged.warning.reselectColumnsTitle" ), //$NON-NLS-1$
						Messages.getString( "fileURIChanged.warning.reselectColumnsMessage" ) ); //$NON-NLS-1$

				updateAvailableColumnsInfo( fileURI );
				selectedColumnsViewer.getTable( ).removeAll( );
				savedSelectedColumnsInfoList.clear( );
				fileViewer.setSelection( new StructuredSelection( fileURI ) );
				setPageComplete( false );
				setMessage( Messages.getString( "error.selectColumns" ), ERROR ); //$NON-NLS-1$
			}
			else
				selected = tableName;
		}
		else
		{
			resetInitialized( );
			fileFilter.setSelection( new StructuredSelection( MATCH_ALL_FILES ) );
			Object[] files = (Object[]) fileViewer.getInput( );
			if ( files != null )
			{
				for ( int n = 0; n < files.length; n++ )
				{
					File f = (File) files[n];
					if ( f.getName( ).equalsIgnoreCase( tableName ) )
					{
						selectedFile = f;
						resetInitialized( );
						setFileFilter( tableName );
						fileViewer.setSelection( new StructuredSelection( files[n] ) );
						selected = f.getName( );
						break;
					}
				}
			}
		}
		return selected;
	}

	private void setFileFilter( String table )
	{
		String tableName = table.toLowerCase( );
		if ( tableName.endsWith( CSV_EXTENSION ) )
			fileFilter.setSelection( new StructuredSelection( ALL_CSV_EXTENSION ) );
		else if ( tableName.endsWith( TXT_EXTENSION ) )
			fileFilter.setSelection( new StructuredSelection( ALL_TXT_EXTENSION ) );
		else if ( tableName.endsWith( SSV_EXTENSION ) )
			fileFilter.setSelection( new StructuredSelection( ALL_SSV_EXTENSION ) );
		else if ( tableName.endsWith( TSV_EXTENSION ) )
			fileFilter.setSelection( new StructuredSelection( ALL_TSV_EXTENSION ) );
		else if ( tableName.endsWith( PSV_EXTENSION ) )
			fileFilter.setSelection( new StructuredSelection( ALL_PSV_EXTENSION ) );
		else
			fileFilter.setSelection( new StructuredSelection( ALL_PSV_EXTENSION ) );
	}

	private void resetInitialized( )
	{
		this.initialized = false;
	}

	/**
	 * Populate file filters for file combo viewer
	 */
	private void populateFileFilter( )
	{
		if ( fileFilter != null && !fileFilter.getControl( ).isDisposed( ) )
		{
			if ( fileFilter.getCombo( ).getSelectionIndex( ) == -1 )
			{
				if ( CommonConstants.DELIMITER_COMMA.equalsIgnoreCase( flatfileDelimiterType ) )
				{
					fileFilter.add( ALL_CSV_EXTENSION );
					this.selectedFileFilter = ALL_CSV_EXTENSION;
				}
				else if ( CommonConstants.DELIMITER_SEMICOLON.equalsIgnoreCase( flatfileDelimiterType ) )
				{
					fileFilter.add( ALL_SSV_EXTENSION );
					this.selectedFileFilter = ALL_SSV_EXTENSION;
				}
				else if ( CommonConstants.DELIMITER_TAB.equalsIgnoreCase( flatfileDelimiterType ) )
				{
					fileFilter.add( ALL_TSV_EXTENSION );
					this.selectedFileFilter = ALL_TSV_EXTENSION;
				}
				else if ( CommonConstants.DELIMITER_PIPE.equalsIgnoreCase( flatfileDelimiterType ) )
				{
					fileFilter.add( ALL_PSV_EXTENSION );
					this.selectedFileFilter = ALL_PSV_EXTENSION;
				}
				else
					this.selectedFileFilter = MATCH_ALL_FILES;

				fileFilter.add( ALL_TXT_EXTENSION );
				fileFilter.add( MATCH_ALL_FILES );
				fileFilter.getCombo( ).select( 0 );
			}
		}
	}

	private void moveUpItem( )
	{
		int count = selectedColumnsViewer.getTable( ).getItemCount( );
		int index = selectedColumnsViewer.getTable( ).getSelectionIndex( );

		if ( index > 0 && index < count )
		{
			if ( !btnMoveDown.isEnabled( ) )
				btnMoveDown.setEnabled( true );

			String[] columnInfo = savedSelectedColumnsInfoList.get( index );
			savedSelectedColumnsInfoList.set( index,
					savedSelectedColumnsInfoList.get( index - 1 ) );
			savedSelectedColumnsInfoList.set( index - 1, columnInfo );
			selectedColumnsViewer.refresh( );
			selectedColumnsViewer.getTable( ).setSelection( index - 1 );
		}

		if ( index == 1 )
			btnMoveUp.setEnabled( false );
	}

	private void moveDownItem( )
	{
		int count = selectedColumnsViewer.getTable( ).getItemCount( );
		int index = selectedColumnsViewer.getTable( ).getSelectionIndex( );

		if ( index > -1 && index < count - 1 )
		{
			if ( !btnMoveUp.isEnabled( ) )
				btnMoveUp.setEnabled( true );

			String[] columnInfo = savedSelectedColumnsInfoList.get( index );
			savedSelectedColumnsInfoList.set( index,
					savedSelectedColumnsInfoList.get( index + 1 ) );
			savedSelectedColumnsInfoList.set( index + 1, columnInfo );
			selectedColumnsViewer.refresh( );
			selectedColumnsViewer.getTable( ).setSelection( index + 1 );
		}

		if ( index == count - 2 )
			btnMoveDown.setEnabled( false );
	}

	private void addAllAvailableColumns( )
	{
		java.util.List<String[]> addedItems = createAddedColumnsInfo( availableList.getItems( ) );
		addColumns( addedItems, false );
	}

	/**
	 * Add selectd columns
	 */
	private void addColumns( boolean updateSelection )
	{
		java.util.List<String[]> addedItems = createAddedColumnsInfo( availableList.getSelection( ) );
		addColumns( addedItems, updateSelection );
	}

	private void addColumns( java.util.List<String[]> addedItems,
			boolean updateSelection )
	{
		for ( int i = 0; i < addedItems.size( ); i++ )
		{
			savedSelectedColumnsInfoList.add( addedItems.get( i ) );
		}

		setDisplayContent( savedSelectedColumnsInfoList );

		selectedColumnsViewer.getTable( ).deselectAll( );
		if ( updateSelection )
			updateAvailableListSelection( );
	}

	/**
	 * create a list of the columns info in accordience to the given column
	 * names
	 * 
	 * @param addedColumnNames
	 * @return
	 */
	private java.util.List<String[]> createAddedColumnsInfo(
			String[] addedColumnNames )
	{
		java.util.List<String[]> addedColumnsInfo = new ArrayList<String[]>( );
		int count = 0;

		for ( int i = 0; i < addedColumnNames.length; i++ )
		{
			count = getExistenceCount( addedColumnNames[i] );
			String[] addedColumns;
			addedColumns = new String[3];
			if ( count == 0 )
				addedColumns[0] = addedColumnNames[i];
			else
				addedColumns[0] = addedColumnNames[i] + "_" + count; //$NON-NLS-1$

			addedColumns[1] = addedColumnNames[i];
			addedColumns[2] = getColumnTypeName( addedColumnNames[i] );

			addedColumnsInfo.add( addedColumns );
		}

		return addedColumnsInfo;
	}

	/**
	 * 
	 * @param columnName
	 * @return
	 */
	private String getColumnTypeName( String columnName )
	{
		for ( int i = 0; i < originalFileColumnsInfoList.size( ); i++ )
		{
			if ( columnName.equals( ( (String[]) originalFileColumnsInfoList.get( i ) )[1] ) )
				return ( (String[]) originalFileColumnsInfoList.get( i ) )[2];
		}
		return null;
	}

	/**
	 * Remove selected columns
	 */
	private void removeColumns( )
	{
		TableItem[] tis = selectedColumnsViewer.getTable( ).getSelection( );
		int index = selectedColumnsViewer.getTable( ).getSelectionIndex( )
				- selectedColumnsViewer.getTable( ).getSelectionCount( )
				+ 1;
		String[] removedColumnInfo = null;

		java.util.List<String[]> removedItems = new ArrayList<String[]>( );
		for ( int i = 0; i < tis.length; i++ )
		{
			removedColumnInfo = new String[3];
			removedColumnInfo[0] = tis[i].getText( 0 );
			removedColumnInfo[1] = tis[i].getText( 1 );
			removedColumnInfo[2] = tis[i].getText( 2 );
			removedItems.add( removedColumnInfo );
		}

		removeItemsFromSelectedOnes( removedItems );

		selectedColumnsViewer.refresh( );

		availableList.deselectAll( );
		updateSelectedItemsSelection( index );

		if ( selectedColumnsViewer.getTable( ).getItemCount( ) == 0 )
		{
			setPageComplete( false );
		}
	}

	private void removeAllColumns( )
	{
		selectedColumnsViewer.getTable( ).removeAll( );
		savedSelectedColumnsInfoList.clear( );

		selectedColumnsViewer.refresh( );

		updateSelectedItemsSelection( -1 );
		setPageComplete( false );
	}

	/**
	 * remove the given items from the saved selectedComlumnsInfo
	 * 
	 * @param removedItemsList
	 *            list that contains the given elements that are going to be
	 *            removed
	 */
	private void removeItemsFromSelectedOnes(
			java.util.List<String[]> removedItemsList )
	{
		for ( int i = 0; i < removedItemsList.size( ); i++ )
		{
			for ( int j = 0; j < savedSelectedColumnsInfoList.size( ); j++ )
			{
				if ( ( (String[]) removedItemsList.get( i ) )[0].equals( ( (String[]) savedSelectedColumnsInfoList.get( j ) )[0] )
						&& ( (String[]) removedItemsList.get( i ) )[1].equals( ( (String[]) savedSelectedColumnsInfoList.get( j ) )[1] )
						&& ( (String[]) removedItemsList.get( i ) )[2].equals( ( (String[]) savedSelectedColumnsInfoList.get( j ) )[2] ) )
				{
					savedSelectedColumnsInfoList.remove( j );
					break;
				}

			}
		}

		validatePageStatus( );
	}

	/**
	 * Updates the given dataSetDesign with the query and its metadata defined
	 * in this page.
	 * 
	 * @param dataSetDesign
	 */
	private void savePage( DataSetDesign dataSetDesign )
	{
		String queryText = getQueryText( );
		if ( queryText.equals( dataSetDesign.getQueryText( ) ) )
			return;
		dataSetDesign.setQueryText( queryText );

		// obtain query's result set metadata, and update
		// the dataSetDesign with it
		IConnection conn = null;
		try
		{
			IDriver ffDriver = new FlatFileDriver( );
			conn = ffDriver.getConnection( null );
			IResultSetMetaData metadata = getResultSetMetaData( queryText, conn );
			setResultSetMetaData( dataSetDesign, metadata );
		}
		catch ( OdaException e )
		{
			// no result set definition available, reset in dataSetDesign
			dataSetDesign.setResultSets( null );
		}
		finally
		{
			closeConnection( conn );
		}

		/*
		 * See DesignSessionUtil for more convenience methods to define a data
		 * set design instance.
		 */

		/*
		 * Since this flatfile driver does not support query parameters and
		 * properties, there are no data set parameters and public/private
		 * properties to specify in the data set design instance
		 */
	}

	/**
	 * Gets the query text
	 * 
	 * @return query text
	 */
	private String getQueryText( )
	{
		String query = getQuery( );
		String queryText = query.length( ) > 0 ? query
				+ CommonConstants.DELIMITER_SPACE
				+ queryTextDelimiter
				+ CommonConstants.DELIMITER_SPACE
				+ columnsInfoStartSymbol
				+ createSelectedColumnsInfoString( )
				+ columnsInfoEndSymbol : ""; //$NON-NLS-1$
		return queryText;
	}

	/**
	 * create the SelectedColumnsinfo string
	 * 
	 * @param dataSetDesign
	 *            the current dataSetDesign
	 * @return String that contains the seleced columns infomation
	 */
	private String createSelectedColumnsInfoString( )
	{
		String prop = ""; //$NON-NLS-1$
		// If the length is equal to 2 then we have a valid query

		for ( int i = 0; i < savedSelectedColumnsInfoList.size( ); i++ )
		{
			char[] columnNameChars = ( (String[]) savedSelectedColumnsInfoList.get( i ) )[0].toCharArray( );
			StringBuffer columnNameBuf = new StringBuffer( );

			char[] originalColumnNameChars = ( (String[]) savedSelectedColumnsInfoList.get( i ) )[1].toCharArray( );
			StringBuffer originalColumnNameBuf = new StringBuffer( );
			for ( int m = 0; m < columnNameChars.length; m++ )
			{
				if ( ColumnsInfoUtil.isColumnsInfoKeyWord( columnNameChars[m] ) )
					columnNameBuf.append( "\\" + columnNameChars[m] ); //$NON-NLS-1$
				else
					columnNameBuf.append( columnNameChars[m] );

			}

			prop = prop
					+ CommonConstants.DELIMITER_DOUBLEQUOTE
					+ columnNameBuf.toString( )
					+ CommonConstants.DELIMITER_DOUBLEQUOTE
					+ CommonConstants.DELIMITER_COMMA_VALUE;

			for ( int m = 0; m < originalColumnNameChars.length; m++ )
			{
				if ( ColumnsInfoUtil.isColumnsInfoKeyWord( originalColumnNameChars[m] ) )
					originalColumnNameBuf.append( "\\" //$NON-NLS-1$
							+ originalColumnNameChars[m] );
				else
					originalColumnNameBuf.append( originalColumnNameChars[m] );

			}

			prop = prop
					+ CommonConstants.DELIMITER_DOUBLEQUOTE
					+ originalColumnNameBuf.toString( )
					+ CommonConstants.DELIMITER_DOUBLEQUOTE
					+ CommonConstants.DELIMITER_COMMA_VALUE;

			if ( i != savedSelectedColumnsInfoList.size( ) - 1 )
			{
				prop = prop
						+ getDataTypeValue( ( (String[]) savedSelectedColumnsInfoList.get( i ) )[2] )
						+ CommonConstants.DELIMITER_SEMICOLON_VALUE;
			}
			else
			{
				prop = prop
						+ getDataTypeValue( ( (String[]) savedSelectedColumnsInfoList.get( i ) )[2] );
			}

		}

		savedSelectedColumnsInfoString = prop;

		return savedSelectedColumnsInfoString;

	}

	/**
	 * 
	 * @param dataSetDesign
	 * @param md
	 * @throws OdaException
	 */
	private void setResultSetMetaData( DataSetDesign dataSetDesign,
			IResultSetMetaData md ) throws OdaException
	{
		ResultSetColumns columns = DesignSessionUtil.toResultSetColumnsDesign( md );

		ResultSetDefinition resultSetDefn = DesignFactory.eINSTANCE.createResultSetDefinition( );
		// flat file does not support result set name
		resultSetDefn.setResultSetColumns( columns );

		// no exception; go ahead and assign to specified dataSetDesign
		dataSetDesign.setPrimaryResultSet( resultSetDefn );
		dataSetDesign.getResultSets( ).setDerivedMetaData( true );
	}

	class CSVFileFilter implements FilenameFilter
	{

		private String extension = null;

		CSVFileFilter( String ext )
		{
			if ( ALL_CSV_EXTENSION.equalsIgnoreCase( ext ) )
				extension = CSV_EXTENSION;
			else if ( ALL_TXT_EXTENSION.equalsIgnoreCase( ext ) )
				extension = TXT_EXTENSION;
			else if ( ALL_SSV_EXTENSION.equalsIgnoreCase( ext ) )
				extension = SSV_EXTENSION;
			else if ( ALL_TSV_EXTENSION.equalsIgnoreCase( ext ) )
				extension = TSV_EXTENSION;
			else if ( ALL_PSV_EXTENSION.equalsIgnoreCase( ext ) )
				extension = PSV_EXTENSION;
			else
				extension = null;

		}

		public boolean accept( File dir, String name )
		{
			if ( extension == null )
			{
				File file = new File( dir + File.separator + name );
				if ( file.isFile( ) && !file.isHidden( ) )
					return true;
				else
					return false;
			}
			else
				return name.toLowerCase( ).endsWith( extension );
		}
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

	private void selectFileChanged( )
	{
		String file = fileViewer.getCombo( ).getText( ).trim( );
		if ( file.length( ) == 0 )
		{
			return;
		}

		// File URI mode
		if ( fileURI != null && fileURI.length( ) > 0 )
		{
			if ( file != null ) // Update column info.
			{
				updateAvailableColumnsInfo( file );
				validatePageStatus( );
			}
			return;
		}

		// Home folder mode
		if ( file.equals( selectedFile ) )
			return;
		else
		{
			// Not initialized or file selection changed.
			setPageComplete( false );
			availableList.removeAll( );
			nameOfFileWithErrorInLastAccess = null;
			updateAvailableColumnsInfo( file );

			if ( selectedFile != null ) // File selection changed.
			{
				if ( savedSelectedColumnsInfoList.size( ) > 0 )
				{
					if ( MessageDialog.openConfirm( getShell( ),
							Messages.getString( "confirm.reselectFileNameTitle" ), //$NON-NLS-1$
							Messages.getString( "confirm.reselectFileNameMessage" ) ) ) //$NON-NLS-1$
					{
						validatePageStatus( );
					}
					else
					{
						selectedColumnsViewer.getTable( ).removeAll( );
						savedSelectedColumnsInfoList.clear( );
					}
				}
			}
			selectedFile = file;
		}
		updateButtonStatus( );
	}

	private class ColumnEditDialog extends StatusDialog
	{

		String columnName, columnOriginalName, columnDataType;

		public ColumnEditDialog( Shell parent )
		{
			super( parent );

		}

		public void setInput( String columnName, String columnOriginalName,
				String columnDataType )
		{
			this.columnName = columnName;
			this.columnOriginalName = columnOriginalName;
			this.columnDataType = columnDataType;
		}

		protected boolean isResizable( )
		{
			return true;
		}

		public void create( )
		{
			super.create( );

			Point pt = getShell( ).computeSize( -1, -1 );
			pt.x = Math.max( pt.x, 400 );
			pt.y = Math.max( pt.y, 250 );
			getShell( ).setSize( pt );
			getShell( ).setText( getTitle( ) );
		}

		protected Control createDialogArea( Composite parent )
		{
			Composite composite = new Composite( parent, SWT.None );

			GridLayout layout = new GridLayout( );
			layout.marginLeft = layout.marginTop = layout.marginRight = 20;
			layout.marginBottom = 5;
			layout.numColumns = 2;
			composite.setLayout( layout );
			GridData data = new GridData( GridData.FILL_BOTH );
			composite.setLayoutData( data );

			GridData lableData = new GridData( );
			lableData.widthHint = 100;

			Label columnNameLabel = new Label( composite, SWT.BOLD );
			columnNameLabel.setLayoutData( lableData );
			columnNameLabel.setText( Messages.getString( "FileSelectionWizardPage.label.columnName" ) ); //$NON-NLS-1$
			int width = columnNameLabel.computeSize( -1, -1 ).x;
			if ( width > lableData.widthHint )
			{
				lableData.widthHint = width;
			}
			columnNameLabel.setLayoutData( lableData );

			final Text columnNameText = new Text( composite, SWT.BORDER );
			columnNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
			columnNameText.setText( this.columnName );
			columnNameText.addModifyListener( new ModifyListener( ) {

				public void modifyText( ModifyEvent arg0 )
				{
					columnName = columnNameText.getText( ).trim( );
					validate( );
				}

			} );

			Label originalNameLabel = new Label( composite, SWT.BOLD );
			originalNameLabel.setText( Messages.getString( "FileSelectionWizardPage.label.originalName" ) ); //$NON-NLS-1$
			width = originalNameLabel.computeSize( -1, -1 ).x;
			if ( width > lableData.widthHint )
			{
				lableData.widthHint = width;
			}
			originalNameLabel.setLayoutData( lableData );

			Text originalNameText = new Text( composite, SWT.BORDER
					| SWT.READ_ONLY );
			originalNameText.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
			originalNameText.setText( this.columnOriginalName );

			Label typeLabel = new Label( composite, SWT.BOLD );
			typeLabel.setLayoutData( lableData );
			typeLabel.setText( Messages.getString( "FileSelectionWizardPage.label.dataType" ) ); //$NON-NLS-1$

			final CCombo combo = new CCombo( composite, SWT.READ_ONLY
					| SWT.BORDER );
			combo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
			combo.setItems( dataTypeDisplayNames );
			combo.setText( columnDataType );
			combo.addSelectionListener( new SelectionListener( ) {

				public void widgetDefaultSelected( SelectionEvent arg0 )
				{

				}

				public void widgetSelected( SelectionEvent arg0 )
				{
					columnDataType = combo.getText( );
				}

			} );

			Utility.setSystemHelp( composite,
					IHelpConstants.CONEXT_ID_COLUMN_EDIT_DIALOG_FLATFILE );

			return parent;
		}

		private boolean isDuplicatedName( )
		{
			for ( int i = 0; i < savedSelectedColumnsInfoList.size( ); i++ )
			{
				if ( this.columnOriginalName != null
						&& this.columnOriginalName.equals( savedSelectedColumnsInfoList.get( i )[1] ) )
				{
					continue;
				}

				if ( this.columnName.equals( savedSelectedColumnsInfoList.get( i )[0] ) )
				{
					return true;
				}
			}
			return false;
		}

		/**
		 * Validate the page status
		 * 
		 */
		private void validate( )
		{
			Status status;
			if ( this.columnName.trim( ).length( ) == 0 )
			{
				status = getMiscStatus( IStatus.ERROR,
						Messages.getString( "FileSelectionWizardPage.error.selectColumn.EmptyName" ) ); //$NON-NLS-1$
			}
			else if ( isDuplicatedName( ) )
			{
				status = getMiscStatus( IStatus.ERROR,
						Messages.getString( "FileSelectionWizardPage.error.selectColumn.duplicatedFileName" ) ); //$NON-NLS-1$
			}
			else if ( isNumeric( this.columnName.trim( ) ) )
			{
				status = getMiscStatus( IStatus.ERROR,
						Messages.getString( "FileSelectionWizardPage.error.selectColumn.numberName" ) ); //$NON-NLS-1$			
			}
			else
			{
				status = getOKStatus( );
			}
			updateStatus( status );
		}

		private Status getOKStatus( )
		{
			return getMiscStatus( IStatus.OK, "" ); //$NON-NLS-1$
		}

		/**
		 * 
		 * @param severity
		 * @param message
		 * @return
		 */
		private Status getMiscStatus( int severity, String message )
		{
			return new Status( severity,
					PlatformUI.PLUGIN_ID,
					severity,
					message,
					null );
		}

		public String getColumnName( )
		{
			return this.columnName;
		}

		public String getOriginalName( )
		{
			return this.columnOriginalName;
		}

		public String getDataType( )
		{
			return this.columnDataType;
		}

	}

}
