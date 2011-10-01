/*
 *************************************************************************
 * Copyright (c) 2006, 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.flatfile.ui.wizards;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Extends the ODA design ui framework to provide a driver-specific custom
 * editor page to create or edit an ODA data set design instance.
 */
public class FileSelectionWizardPage extends DataSetWizardPage
		implements
			ISelectionChangedListener
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

	private static String name = Messages.getString( "editor.title.name" ); //$NON-NLS-1$
	private static String originalName = Messages.getString( "editor.title.originalName" ); //$NON-NLS-1$
	private static String dataType = Messages.getString( "editor.title.type" ); //$NON-NLS-1$

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

	private final int DEFAULT_WIDTH = 200;
	private final int DEFAULT_HEIGHT = 200;

	private transient ComboViewer fileViewer = null;
	private transient ComboViewer fileFilter = null;
	private transient List availableList = null;
	private transient TableViewer selectedColumnsViewer = null;
	private transient Button btnAdd = null;
	private transient Button btnRemove = null;
	private transient Button btnMoveUp = null;
	private transient Button btnMoveDown = null;
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
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPageCustomControl( Composite parent )
	{
		setControl( createPageControl( parent ) );
		initializeControl( );

		Utility.setSystemHelp( getControl( ),
				IHelpConstants.CONEXT_ID_DATASET_FLATFILE );
	}

	/**
	 * 
	 * 
	 */
	private void createColumnTypeMap( )
	{
		dataTypeDisplayNameMap.put( new Integer(4), 
				Messages.getString( "datatypes.integer" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer(8), 
				Messages.getString( "datatypes.float" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer(12),
				Messages.getString( "datatypes.string" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer(91),
				Messages.getString( "datatypes.date" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer(92),
				Messages.getString( "datatypes.time" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer(93),
				Messages.getString( "datatypes.dateTime" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer(2), 
				Messages.getString( "datatypes.decimal" ) ); //$NON-NLS-1$
		dataTypeDisplayNameMap.put( new Integer(16), 
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
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	protected DataSetDesign collectDataSetDesign( DataSetDesign design )
	{
		//if this page in DataSetEditor hasn't been activated
		if ( fileViewer == null )
			return design;
		
		savePage( design );
		return design;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSetWizardPage#collectResponseState()
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
	private Control createPageControl( Composite parent )
	{
		Composite composite = new Composite( parent, SWT.NULL );

		FormLayout layout = new FormLayout( );
		composite.setLayout( layout );

		FormData data = new FormData( );
		data.left = new FormAttachment( 0, 5 );
		data.top = new FormAttachment( 0, 5 );

		Label label = new Label( composite, SWT.NONE );
		label.setText( Messages.getString( "label.selectFile" ) ); //$NON-NLS-1$
		label.setLayoutData( data );

		createTopComposite( composite, label );

		createLeftComposite( composite );

		Composite btnComposite = createAddBtnComposite( composite );

		createRightComposite( composite, btnComposite );

		setupEditors( );

		loadProperties( );
		populateFileFilter( );
		updateFileListAndCharSet( );
		return composite;
	}
	
	/**
	 * Create the top composite of the page
	 * 
	 * @param composite
	 * @param label
	 */
	private void createTopComposite( Composite composite, Label label )
	{
		final Shell shell = composite.getShell( );
		FormData data = new FormData( );
		data.left = new FormAttachment( label, 5 );
		data.right = new FormAttachment( 80, -5 );
		fileViewer = new ComboViewer( composite, SWT.BORDER | SWT.READ_ONLY );
		fileViewer.getControl( ).setLayoutData( data );
		fileViewer.setContentProvider( new ArrayContentProvider( ) );
		fileViewer.addSelectionChangedListener( this );
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

		data = new FormData( );
		data.left = new FormAttachment( fileViewer.getControl( ), 5 );
		data.top = new FormAttachment( 0, 5 );

		label = new Label( composite, SWT.NONE );
		label.setText( Messages.getString( "label.fileFilter" ) ); //$NON-NLS-1$
		label.setLayoutData( data );

		data = new FormData( );
		data.left = new FormAttachment( label, 5 );
		data.right = new FormAttachment( 100, -5 );
		fileFilter = new ComboViewer( composite, SWT.READ_ONLY );
		fileFilter.getControl( ).setLayoutData( data );
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
							|| MessageDialog.openConfirm( shell,
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
		FormData data = new FormData( );
		data.top = new FormAttachment( fileViewer.getControl( ), 10, SWT.BOTTOM );
		data.left = new FormAttachment( 0, 5 );
		data.right = new FormAttachment( 40, -5 );
		data.bottom = new FormAttachment( 100, -5 );
		data.width = DEFAULT_WIDTH;
		data.height = DEFAULT_HEIGHT;
		availableList = new List( composite, SWT.MULTI
				| SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );

		availableList.setLayoutData( data );
		availableList.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				selectedColumnsViewer.getTable( ).deselectAll( );
				btnAdd.setEnabled( true );
				btnRemove.setEnabled( false );
				btnMoveDown.setEnabled( false );
				btnMoveUp.setEnabled( false );
			}
		} );

		availableList.addMouseListener( new MouseAdapter( ) {

			public void mouseDoubleClick( MouseEvent e )
			{
				addColumns( );
			}
		} );
	}

	/**
	 * Create the middle button composite that displays ADD button
	 * 
	 * @param composite
	 * @return
	 */
	private Composite createAddBtnComposite( Composite composite )
	{
		FormData data = new FormData( );
		data.top = new FormAttachment( 50, 5 );
		data.left = new FormAttachment( availableList, 3 );

		Composite btnComposite = new Composite( composite, SWT.NONE );
		btnComposite.setLayoutData( data );
		GridLayout layout = new GridLayout( );
		layout.numColumns = 1;
		btnComposite.setLayout( layout );

		btnAdd = new Button( btnComposite, SWT.NONE );
		GridData gridData = new GridData( GridData.VERTICAL_ALIGN_CENTER
				| GridData.FILL_HORIZONTAL );
		gridData.heightHint = 25;
		btnAdd.setLayoutData( gridData );
		btnAdd.setToolTipText( Messages.getString( "tooltip.button.add" ) ); //$NON-NLS-1$

		if ( btnAdd.getStyle( ) ==( btnAdd.getStyle( )|SWT.LEFT_TO_RIGHT))
		{
			btnAdd.setImage( PlatformUI.getWorkbench( )
					.getSharedImages( )
					.getImage( ISharedImages.IMG_TOOL_FORWARD ) );
		}
		else
		{
			btnAdd.setImage( PlatformUI.getWorkbench( )
					.getSharedImages( )
					.getImage( ISharedImages.IMG_TOOL_BACK ) );
		}
		
		btnAdd.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				addColumns( );
			}
		} );

		return btnComposite;
	}

	/**
	 * Create the right composite of the page
	 * 
	 * @param composite
	 * @param btnComposite
	 */
	private void createRightComposite( Composite composite,
			Composite btnComposite )
	{
		FormData data = new FormData( );
		data.top = new FormAttachment( fileViewer.getControl( ), 10, SWT.BOTTOM );
		data.left = new FormAttachment( btnComposite, 3 );
		data.right = new FormAttachment( 100, -2 );
		data.bottom = new FormAttachment( 100, -5 );
		
		Composite rightComposite = new Composite( composite, SWT.BORDER );
		rightComposite.setLayoutData( data );
		GridLayout layout = new GridLayout( );
		layout.numColumns = 2;
		rightComposite.setLayout( layout );

		selectedColumnsViewer = new TableViewer( rightComposite, SWT.MULTI
				| SWT.FULL_SELECTION | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		selectedColumnsViewer.getTable( ).setHeaderVisible( true );
		selectedColumnsViewer.getTable( ).setLinesVisible( true );
		selectedColumnsViewer.getTable( ).setLayoutData( new GridData( GridData.FILL_BOTH ) );
		
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

		selectedColumnsViewer.getTable( )
				.addSelectionListener( new SelectionAdapter( ) {

					public void widgetSelected( SelectionEvent e )
					{
						btnAdd.setEnabled( false );
						availableList.deselectAll( );

						int count = selectedColumnsViewer.getTable( )
								.getSelectionCount( );
						int index = selectedColumnsViewer.getTable( )
								.getSelectionIndex( );
						if ( count == 1 )
						{
							btnRemove.setEnabled( true );
							if( selectedColumnsViewer.getTable( ).getItemCount( ) == 1 )
							{
								btnMoveUp.setEnabled( false );
								btnMoveDown.setEnabled( false );
							}
							else if ( index == 0 )
							{
								btnMoveUp.setEnabled( false );
								btnMoveDown.setEnabled( true );
							}
							else if ( index == ( selectedColumnsViewer.getTable( )
									.getItemCount( ) - 1 ) )
							{
								btnMoveUp.setEnabled( true );
								btnMoveDown.setEnabled( false );
							}
							else
							{
								btnMoveUp.setEnabled( true );
								btnMoveDown.setEnabled( true );
							}
						}
						else if ( count > 1 )
						{
							btnRemove.setEnabled( true );
							btnMoveUp.setEnabled( false );
							btnMoveDown.setEnabled( false );
						}
						else
						{
							btnRemove.setEnabled( false );
							btnMoveUp.setEnabled( false );
							btnMoveDown.setEnabled( false );
						}
					}
				} );

		setColumnsViewerContent( );

		setColumnsViewerLabels( );

		selectedColumnsViewer.getTable( )
				.addMouseListener( new MouseAdapter( ) {

					public void mouseDoubleClick( MouseEvent e )
					{
						removeColumns( );
					}
				} );
		
		createEditBtnGroup( rightComposite );
	}
	
	/**
	 * Create the right button group that displays the UP,DOWN and REMOVE buttons
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

		btnRemove = new Button( btnComposite, SWT.NONE );
		btnRemove.setText( Messages.getString( "button.delete" ) ); //$NON-NLS-1$
		btnRemove.setToolTipText( Messages.getString( "tooltip.button.delete" ) ); //$NON-NLS-1$
		btnRemove.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				removeColumns( );
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
		int widthHint = Math.max( btnMoveUp.computeSize( SWT.DEFAULT,
				SWT.DEFAULT ).x, btnRemove.computeSize( SWT.DEFAULT,
				SWT.DEFAULT ).x );
		widthHint = Math.max( widthHint, btnMoveDown.computeSize( SWT.DEFAULT,
				SWT.DEFAULT ).x );
		widthHint = Math.max( widthHint, 52 );
		GridData btnGd = new GridData( );
		btnGd.widthHint = widthHint;
		btnMoveUp.setLayoutData( btnGd );
		btnRemove.setLayoutData( btnGd );
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
	 * setup the editors in the table viewer
	 * 
	 */
	private void setupEditors( )
	{
		CellEditor[] editors = new CellEditor[3];
		editors[0] = new TextCellEditor( selectedColumnsViewer.getTable( ),
				SWT.NONE );
		editors[2] = new ComboBoxCellEditor( selectedColumnsViewer.getTable( ),
				dataTypeDisplayNames,
				SWT.READ_ONLY );

		selectedColumnsViewer.setColumnProperties( new String[]{
				name, originalName, dataType
		} );
		selectedColumnsViewer.setCellEditors( editors );
		selectedColumnsViewer.setCellModifier( new ICellModifier( ) {

			public boolean canModify( Object element, String property )
			{
				return true;
			}

			public Object getValue( Object element, String property )
			{
				Object value = null;
				if ( name.equals( property ) )
				{
					value = ( (String[]) element )[0];
				}
				else if ( originalName.equals( property ) )
				{
					value = ( (String[]) element )[1];
				}
				else if ( dataType.equals( property ) )
				{
					String temp = ( (String[]) element )[2];
					if ( temp == null )
					{
						value = new Integer( 0 );
					}
					else
					{
						for ( int i = 0; i < dataTypeDisplayNames.length; i++ )
						{
							if ( temp.equals( dataTypeDisplayNames[i] ) )
							{
								value = new Integer( i );
								break;
							}
						}
					}
				}
				return value;
			}

			public void modify( Object element, String property, Object value )
			{
				String[] actualElement = (String[]) ( (TableItem) element ).getData( );
				if ( value != null )
				{
					if ( name.equals( property ) )
					{
						if ( isUnique( actualElement, (String) value )
								&& !isEmpty( (String) value ) )
						{
							replace( actualElement, property, (String) value );

							selectedColumnsViewer.refresh( );
							setMessage( DEFAULT_MESSAGE );
						}
						else
						{
							setMessage( Messages.getString( "error.duplicatedNameValueOrEmpty" ), //$NON-NLS-1$
									WARNING );
						}
					}
					else if ( dataType.equals( property ) )
					{
						int index = ( (Integer) value ).intValue( );
						if ( !( dataTypeDisplayNames[index] ).equals( actualElement[2] ) )
						{
							replace( actualElement,
									property,
									dataTypeDisplayNames[index] );
							selectedColumnsViewer.refresh( );
						}
					}
					return;

				}
				else
					return;

			}

		} );
	}

	/**
	 * see if the value in the selected element of the saved selected columns
	 * information is unique or not
	 * 
	 * @param element
	 *            the selected element
	 * @param value
	 *            the value
	 * @return
	 */
	private boolean isUnique( String[] element, String value )
	{
		for ( int i = 0; i < savedSelectedColumnsInfoList.size( ); i++ )
		{
			if ( i != savedSelectedColumnsInfoList.indexOf( element )
					&& value.equalsIgnoreCase( ( (String[]) savedSelectedColumnsInfoList.get( i ) )[0] ) )
				return false;
		}
		return true;
	}

	private boolean isEmpty( String value )
	{
		return ( value.trim( ).equals( "" ) ); //$NON-NLS-1$
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
			if ( ( columnName + "_" + count ).equals( ( (String[]) savedSelectedColumnsInfoList.get( j ) )[0] ) ) //$NON-NLS-1$
			{
				count++;
				j = -1;
			}
		}
		

		return count;
	}

	/**
	 * replace the element in the saved selected columns informaiton list
	 * 
	 * @param element
	 *            the selected element
	 * @param property
	 *            the element's selected property
	 * @param value
	 *            the value of the selected property in the element
	 */
	private void replace( String[] element, String property, String value )
	{
		int index = savedSelectedColumnsInfoList.indexOf( (Object) element );

		if ( name.equals( property ) )
		{
			element[0] = value;
			savedSelectedColumnsInfoList.set( index, element );
		}
		else if ( dataType.equals( property ) )
		{
			element[2] = value;
			savedSelectedColumnsInfoList.set( index, element );
		}
	}

	/*
	 * File Combo Viewer selection changed listener
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged( SelectionChangedEvent event )
	{
		Object file = ( (IStructuredSelection) event.getSelection( ) ).getFirstElement( );

		// File URI mode
		if ( fileURI != null && fileURI.length( ) > 0 )
		{
			if ( file != null ) // Update column info.
			{
				updateAvailableColumnsInfo( (String) file );
				validateSelectedColumns( );
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
			updateAvailableColumnsInfo( ((File) file).getName( ) );
			
			if ( selectedFile != null ) // File selection changed.
			{
				if ( savedSelectedColumnsInfoList.size( ) > 0  )
				{
					if ( MessageDialog.openConfirm( getShell( ),
							Messages.getString( "confirm.reselectFileNameTitle" ), //$NON-NLS-1$
							Messages.getString( "confirm.reselectFileNameMessage" ) ) ) //$NON-NLS-1$
					{
						validateSelectedColumns( );
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
	}
	
	private void updateAvailableColumnsInfo( String fileName )
	{
		String[] columnNames = getFileColumnNames( fileName );

		if ( columnNames != null && columnNames.length != 0 )
		{
			enableListAndViewer( );
			availableList.setItems( columnNames );
			availableList.select( 0 );
			btnRemove.setEnabled( false );
			btnMoveUp.setEnabled( false );
			btnMoveDown.setEnabled( false );
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
	
	private void validateSelectedColumns( )
	{
		boolean pageComplete = true;
		String[] columnNames = availableList.getItems( );
		for ( int i = 0; i < savedSelectedColumnsInfoList.size( ); i++ )
		{
			String columnName = ( (String[]) savedSelectedColumnsInfoList.get( i ) )[0];
			boolean columnExists = false;
			for ( int k = 0; k < columnNames.length; k++ )
			{
				if ( columnName != null && columnName.equals( columnNames[k] ) )
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
			}
		}
		if ( savedSelectedColumnsInfoList.size( ) <= 0)
			pageComplete = false;
		
		if ( pageComplete )
		{
			setMessage( DEFAULT_MESSAGE );
			setPageComplete( true );
		}
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
            File cpFile = DesignUtil.convertPathToResourceFile( sourcePath, dataSourceDesign.getHostResourceIdentifiers() );
            if ( cpFile == null )
			{
				setMessage( Messages.getFormattedString( "error.invalidConnectionFilePath", new Object[]{ sourcePath } ), ERROR ); //$NON-NLS-1$
				return;
			}
		}
		
		odaHome = dataSourceProps.getProperty( CommonConstants.CONN_HOME_DIR_PROP );
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
		else if ( odaHome != null && odaHome.length( ) > 0 )
		{
			try
			{
				ResourceLocator.validateHomeFolder( odaHome );
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
			if ( odaHome != null )
			{
				File folder = new File( odaHome );
				if ( folder.isDirectory( ) && folder.exists( ) )
				{
					File[] files = folder.getAbsoluteFile( )
					.listFiles( new CSVFileFilter( fileFilter.getCombo( )
							.getText( ) ) );
					
					allFiles.addAll( Arrays.asList( files ) );
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
			if (!( nameOfFileWithErrorInLastAccess != null
					&& nameOfFileWithErrorInLastAccess.equals( fileViewer.getCombo( )
							.getText( ) ) ))
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
			IResultSetMetaData metadata = getResultSetMetaData( queryText,
					conn );

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

				result[2] = getDataTypeDisplayName( new Integer ( metadata.getColumnType( i + 1 ) ) );
				columnList.add( result );
			}
			return columnList;
		}
		catch ( OdaException e )
		{
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

	/**
	 * 
	 *
	 */
	private void disableAvailableListAndButtons( )
	{
		availableList.setEnabled( false );
		btnAdd.setEnabled( false );
		btnRemove.setEnabled( false );
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
		if( odaHome != null )
		{
			prop.put( CommonConstants.CONN_HOME_DIR_PROP, odaHome );
		}
		if( fileURI != null )
		{
			prop.put( CommonConstants.CONN_FILE_URI_PROP, fileURI );
		}
		if( flatfileDelimiterType != null )		
		{
			prop.put( CommonConstants.CONN_DELIMITER_TYPE, flatfileDelimiterType );
		}
		if( charSet != null )		
		{
			prop.put( CommonConstants.CONN_CHARSET_PROP, charSet );
		}
		if( inclColumnNameLine != null )		
		{
			prop.put( CommonConstants.CONN_INCLCOLUMNNAME_PROP, inclColumnNameLine );
		}
		if( inclTypeLine != null )		
		{
			prop.put( CommonConstants.CONN_INCLTYPELINE_PROP, inclTypeLine );
		}
		if( trailNullCols != null )		
		{
			prop.put( CommonConstants.CONN_TRAILNULLCOLS_PROP, trailNullCols );
		}

		savedSelectedColumnsInfoString = (new QueryTextUtil( queryText )).getColumnsInfo( );

        Map<String, Object> appContext = 
                DesignSessionUtil.createResourceIdentifiersContext( getHostResourceIdentifiers() );
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
        return DesignSessionUtil.createRuntimeResourceIdentifiers( getHostResourceIdentifiers() );
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
	private void setDisplayContent( java.util.List<String[]> list, TableViewer tViewer )
	{
		tViewer.getTable( ).removeAll( );
		tViewer.setInput( list );
		tViewer.getTable( ).select( tViewer.getTable( ).getTopIndex( ) );
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
				tableName = ((File)file).getName( );
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

		if ( selectedColumnsViewer.getTable( ).getItemCount( ) == 0 )
		{
			setPageComplete( false );
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

		setDisplayContent( savedSelectedColumnsInfoList, selectedColumnsViewer );

		setPageComplete( true );
		btnAdd.setEnabled( false );
		btnRemove.setEnabled( false );
		btnMoveUp.setEnabled( false );
		btnMoveDown.setEnabled( false );
		if ( selectedColumnsViewer.getTable( ).getItemCount( ) == 0 )
		{
			setPageComplete( false );
		}
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

	/**
	 * Add selectd columns
	 */

	private void addColumns( )
	{
		java.util.List<String[]> addedItems = createAddedColumnsInfo( availableList.getSelection( ) );
		
		for ( int i = 0; i < addedItems.size( ); i++ )
		{
			savedSelectedColumnsInfoList.add( addedItems.get( i ) );
		}

		setDisplayContent( savedSelectedColumnsInfoList, selectedColumnsViewer );

		selectedColumnsViewer.getTable( )
				.setSelection( selectedColumnsViewer.getTable( ).getItemCount( ) - 1 );

		int nextSelectionIndex = availableList.getSelectionIndex( ) + 1;
		if( nextSelectionIndex < availableList.getItemCount( ) )
		{
			availableList.setSelection( nextSelectionIndex );
			btnAdd.setEnabled( true );
		}
		else if ( availableList.getSelectionCount( ) == 0 )
		{
			btnAdd.setEnabled( false );
		}

		selectedColumnsViewer.getTable( ).setSelection( -1 );
		
		setMessage( DEFAULT_MESSAGE );
		setPageComplete( true );
	}

	/**
	 * create a list of the columns info in accordience to the given column
	 * names
	 * 
	 * @param addedColumnNames
	 * @return
	 */
	private java.util.List<String[]> createAddedColumnsInfo( String[] addedColumnNames )
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
		int index = selectedColumnsViewer.getTable( ).getSelectionIndex( );
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

		if ( index > 0 )
			selectedColumnsViewer.getTable( ).setSelection( index - 1 );
		else
			selectedColumnsViewer.getTable( ).setSelection( index );

		if ( selectedColumnsViewer.getTable( ).getSelectionCount( ) == 0 )
			btnRemove.setEnabled( false );

		if ( savedSelectedColumnsInfoList.size( ) <= 1 )
		{
			btnMoveDown.setEnabled( false );
			btnMoveUp.setEnabled( false );
		}

		if ( selectedColumnsViewer.getTable( ).getItemCount( ) == 0 )
		{
			setPageComplete( false );
		}
	}

	/**
	 * remove the given items from the saved selectedComlumnsInfo
	 * 
	 * @param removedItemsList
	 *            list that contains the given elements that are going to be
	 *            removed
	 */
	private void removeItemsFromSelectedOnes( java.util.List<String[]> removedItemsList )
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
		
		validateSelectedColumns( );
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
			IResultSetMetaData metadata = getResultSetMetaData( queryText,
					conn );
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
	 * @return query text
	 */
	private String getQueryText( )
	{
		String query = getQuery( );
		String queryText = query.length( ) > 0 
				? query
				+ CommonConstants.DELIMITER_SPACE + queryTextDelimiter
				+ CommonConstants.DELIMITER_SPACE + columnsInfoStartSymbol
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
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	public void setVisible( boolean visible )
	{
		super.setVisible( visible );
		getControl( ).setFocus( );
	}

}
