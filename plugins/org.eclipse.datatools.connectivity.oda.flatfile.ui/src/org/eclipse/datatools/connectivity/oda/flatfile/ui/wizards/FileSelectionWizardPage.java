/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
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

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.FlatFileDriver;
import org.eclipse.datatools.connectivity.oda.flatfile.ui.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

/**
 * Extends the ODA design ui framework to provide a driver-specific
 * custom editor page to create or edit an ODA data set design instance.
 */
public class FileSelectionWizardPage extends DataSetWizardPage implements
        ISelectionChangedListener
{
    private static String DEFAULT_MESSAGE = Messages
                .getString( "wizard.defaultMessage.selectFile" ); //$NON-NLS-1$
    private static final String ALL_CSV_SUFFIX = "*.csv";  //$NON-NLS-1$
    private static final String CSV_SUFFIX = ".csv";  //$NON-NLS-1$
    private static final String ALL_TXT_SUFFIX = "*.txt";  //$NON-NLS-1$
    private static final String TXT_SUFFIX = ".txt";  //$NON-NLS-1$
    private static final String MATCH_ALL_FILES = "*.*"; //$NON-NLS-1$
    
    private final int DEFAULT_WIDTH = 200;
    private final int DEFAULT_HEIGHT = 200;

    private transient ComboViewer m_fileViewer = null;
    private transient ComboViewer m_fileFilter = null;
    private transient List m_availableList = null;
    private transient List m_selectedList = null;
    private transient Button m_btnAdd = null;
    private transient Button m_btnAddAll = null;
    private transient Button m_btnRemove = null;
    private transient Button m_btnRemoveAll = null;
    private boolean m_initialized = true;

    private String m_charSet = null;
    private String m_inclTypeLine;

    /** store latest selected file */
    private String m_selectedFileFilter;

    /** store latest selected file */
    private File m_selectedFile;

    /**
     * @param pageName
     */
    public FileSelectionWizardPage( String pageName )
    {
        super( pageName );
        setTitle( pageName );
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
        initializeControl();
    }

    private void initializeControl()
    {
        /* 
         * Optionally restores the state of a previous design session.
         * Obtains designer state, using
         *      getInitializationDesignerState(); 
         */

        DataSetDesign dataSetDesign = getInitializationDesign();
        if( dataSetDesign == null )
            return; // nothing to initialize

        String queryText = dataSetDesign.getQueryText();
        if( queryText == null )
            return; // nothing to initialize

        updateValuesFromQuery( queryText );
        
        /*
         * Optionally honor the request for an editable or
         * read-only design session
         *      isSessionEditable();
         */
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
     */
    protected DataSetDesign collectDataSetDesign( DataSetDesign design )
    {
        if( ! hasValidData() )
            return design;
        savePage( design );
        return design;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSetWizardPage#collectResponseState()
     */
    protected void collectResponseState()
    {        
        super.collectResponseState();
        /* 
         * Optionally assigns custom response state, for inclusion
         * in the ODA design session response, using
         *      setResponseSessionStatus( SessionStatus status )
         *      setResponseDesignerState( DesignerState customState ); 
         */
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#canLeave()
     */
    protected boolean canLeave()
    {
        if( ! isPageComplete() )
        {
            setMessage( Messages.getString( "error.selectColumns" ), ERROR ); //$NON-NLS-1$
        }
        return isPageComplete();
    }

    private Control createPageControl( Composite parent )
    {
        Composite composite = new Composite( parent, SWT.NULL );
        final Shell shell = composite.getShell();

        FormLayout layout = new FormLayout();
        composite.setLayout( layout );

        FormData data = new FormData();
        data.left = new FormAttachment( 0, 5 );
        data.top = new FormAttachment( 0, 5 );

        Label label = new Label( composite, SWT.NONE );
        label.setText( Messages.getString( "label.selectFile" ) ); //$NON-NLS-1$
        label.setLayoutData( data );

        data = new FormData();
        data.left = new FormAttachment( label, 5 );
        data.right = new FormAttachment( 80, -5 );
        m_fileViewer = new ComboViewer( composite, SWT.BORDER | SWT.READ_ONLY );
        m_fileViewer.getControl().setLayoutData( data );
        m_fileViewer.setContentProvider( new ArrayContentProvider() );
        m_fileViewer.addSelectionChangedListener( this );
        m_fileViewer.setLabelProvider( new LabelProvider()
        {
            public String getText( Object element )
            {
                return ((File) element).getName();
            }
        } );

        data = new FormData();
        data.left = new FormAttachment( m_fileViewer.getControl(), 5 );
        data.top = new FormAttachment( 0, 5 );

        label = new Label( composite, SWT.NONE );
        label.setText( Messages.getString( "label.fileFilter" ) ); //$NON-NLS-1$
        label.setLayoutData( data );

        data = new FormData();
        data.left = new FormAttachment( label, 5 );
        data.right = new FormAttachment( 100, -5 );
        m_fileFilter = new ComboViewer( composite, SWT.READ_ONLY );
        m_fileFilter.getControl().setLayoutData( data );
        m_fileFilter.addSelectionChangedListener( new ISelectionChangedListener()
        {
            public void selectionChanged( SelectionChangedEvent event )
            {
                String currSelectFilter = m_fileFilter.getCombo().getText();
                if( currSelectFilter.equalsIgnoreCase( m_selectedFileFilter ) )
                    return;

                if( !m_initialized )
                {
                    m_selectedFileFilter = currSelectFilter;
                    updateFileListAndCharSet();
                    m_initialized = true;
                }
                else
                {
                    if( currSelectFilter.equals( MATCH_ALL_FILES ) ||  
                        MessageDialog.openConfirm(
                            shell,
                            Messages.getString( "confirm.reselectFileFilterTitle" ), //$NON-NLS-1$
                            Messages.getString( "confirm.reselectFileFilterMessage" ) ) ) //$NON-NLS-1$
                    {
                        m_selectedFileFilter = currSelectFilter;
                        updateFileListAndCharSet();
                    }
                    else
                    {
                        m_fileFilter.getCombo().setText( m_selectedFileFilter );
                    }
                }
            }
        } );

        data = new FormData();
        data.top = new FormAttachment( m_fileViewer.getControl(), 10,
                SWT.BOTTOM );
        data.left = new FormAttachment( 0, 5 );
        data.right = new FormAttachment( 47, -5 );
        data.bottom = new FormAttachment( 100, -5 );
        data.width = DEFAULT_WIDTH;
        data.height = DEFAULT_HEIGHT;
        m_availableList = new List( composite, SWT.MULTI | SWT.BORDER
                | SWT.H_SCROLL | SWT.V_SCROLL );

        m_availableList.setLayoutData( data );
        m_availableList.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( SelectionEvent e )
            {
                m_btnAdd.setEnabled( true );
                m_btnAddAll.setEnabled( true );
            }
        } );
        
        m_availableList.addMouseListener( new MouseAdapter()
        {
            public void mouseDoubleClick( MouseEvent e )
            {
                addColumns();
            }
        } );
        
        data = new FormData();
        // data.top = new FormAttachment(tableFile, 10, SWT.BOTTOM);
        data.left = new FormAttachment( m_availableList, 5 );
        data.bottom = new FormAttachment( 75 );

        Composite btnComposite = new Composite( composite, SWT.NONE );
        btnComposite.setLayoutData( data );
        FillLayout btnLayout = new FillLayout( SWT.VERTICAL );
        btnLayout.spacing = 5;
        btnComposite.setLayout( btnLayout );

        m_btnAdd = new Button( btnComposite, SWT.NONE );
        m_btnAdd.setText( ">" ); //$NON-NLS-1$
        m_btnAdd.addSelectionListener( new SelectionAdapter()
        {

            public void widgetSelected( SelectionEvent e )
            {
                addColumns();
            }
        } );

        m_btnAddAll = new Button( btnComposite, SWT.NONE );
        m_btnAddAll.setText( ">>" ); //$NON-NLS-1$
        m_btnAddAll.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( SelectionEvent e )
            {
                addItems( m_availableList.getItems(), m_selectedList );
                m_availableList.removeAll();
                m_btnAdd.setEnabled( false );
                m_btnAddAll.setEnabled( false );
                setPageComplete( true );
                m_btnRemoveAll.setEnabled( true );
                setMessage( DEFAULT_MESSAGE );
            }
        } );

        m_btnRemove = new Button( btnComposite, SWT.NONE );
        m_btnRemove.setText( "<" ); //$NON-NLS-1$
        m_btnRemove.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( SelectionEvent e )
            {
                removeColumns();
            }
        } );

        m_btnRemoveAll = new Button( btnComposite, SWT.NONE );
        m_btnRemoveAll.setText( "<<" ); //$NON-NLS-1$
        m_btnRemoveAll.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( SelectionEvent e )
            {
                addItems( m_selectedList.getItems(), m_availableList );
                m_selectedList.removeAll();
                m_btnRemove.setEnabled( false );
                m_btnRemoveAll.setEnabled( false );
                m_btnAddAll.setEnabled( true );
                setPageComplete( false );
            }
        } );

        data = new FormData();
        data.top = new FormAttachment( m_fileViewer.getControl(), 10,
                SWT.BOTTOM );
        data.left = new FormAttachment( btnComposite, 5 );
        data.right = new FormAttachment( 100, -5 );
        data.bottom = new FormAttachment( 100, -5 );
        data.width = DEFAULT_WIDTH;
        m_selectedList = new List( composite, SWT.MULTI | SWT.BORDER
                | SWT.H_SCROLL | SWT.V_SCROLL );
        m_selectedList.setLayoutData( data );
        m_selectedList.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( SelectionEvent e )
            {
                m_btnRemove.setEnabled( true );
                m_btnRemoveAll.setEnabled( true );
            }
        } );

        m_selectedList.addMouseListener( new MouseAdapter()
        {
            public void mouseDoubleClick( MouseEvent e )
            {
                removeColumns();
            }
        } );

        populateFileFilter();
        updateFileListAndCharSet();

        return composite;
    }

    /*
     * File Combo Viewer selection changed listener
     * 
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged( SelectionChangedEvent event )
    {
        File file = (File) ((IStructuredSelection) event.getSelection())
                .getFirstElement();

        if( file.equals( m_selectedFile ) )
            return;
        m_selectedFile = file;
        setPageComplete( false );

        String[] columnNames = getFileColumnNames( file );
        m_selectedList.removeAll();
        m_availableList.removeAll();
        if( columnNames == null || columnNames.length == 0 )
        {
            setMessage( Messages.getString( "error.noColumns" ), ERROR ); //$NON-NLS-1$
            disableAll();
        }
        else
        {
            enableAll();
            m_availableList.setItems( columnNames );
            m_availableList.select( 0 );
            m_btnRemoveAll.setEnabled( false );
            m_btnRemove.setEnabled( false );
            setMessage( DEFAULT_MESSAGE );
        }
    }

    /**
     * Update file list in combo viewer
     */
    private void updateFileListAndCharSet()
    {
        Properties dataSourceProps = getInitializationDesign()
                .getDataSourceDesign().getPublicProperties();

        if( m_fileViewer != null && !m_fileViewer.getControl().isDisposed() )
        {
            String odaHome = dataSourceProps
                    .getProperty( CommonConstants.CONN_HOME_DIR_PROP );
            if( odaHome == null )
            {
                setMessage( "" ); //$NON-NLS-1$
                disableAll();
                return;
            }

            m_charSet = dataSourceProps
                    .getProperty( CommonConstants.CONN_CHARSET_PROP );
            m_inclTypeLine = dataSourceProps
                    .getProperty( CommonConstants.CONN_INCLTYPELINE_PROP );
            
            File folder = new File( odaHome );
            if( folder.isDirectory() && folder.exists() )
            {
                File[] files = folder.getAbsoluteFile().listFiles(
                        new CSVFileFilter( m_fileFilter.getCombo().getText() ) );

                if( files != null )
                {
                    setMessage( DEFAULT_MESSAGE );
                    m_fileViewer.setInput( files );
                }
                else
                {
                    m_fileViewer.setInput( new File[] {} );
                }
            }
            else
            {
                m_fileViewer.setInput( new File[] {} );
            }
            File[] files = (File[]) m_fileViewer.getInput();
            if( files.length > 0 )
            {
                enableAll();
                File toSelectFile = null;
                if( m_selectedFile != null )
                    for( int i = 0; i < files.length; i++)
                    {
                        if( files[i].equals( m_selectedFile ) )
                        {
                            toSelectFile = m_selectedFile;
                            break;
                        }
                    }
                if( toSelectFile == null )
                    toSelectFile = files[0];

                m_fileViewer.setSelection( new StructuredSelection(
                        toSelectFile ) );
            }
            else
            {
                setMessage( Messages.getFormattedString(
                                "error.noCSVFiles", new Object[] { folder.getAbsolutePath() } ) ); //$NON-NLS-1$
                disableAll();
            }
        }
    }

    /**
     * Returns all the column names found in given flatfile.
     * @param file
     * @return
     */
    private String[] getFileColumnNames( File file )
    {
        return getQueryColumnNames( "select * from " + file.getName(), file ); //$NON-NLS-1$
    }

    /**
     * Returns the column names selected by the given query
     * accessing the given flatfile.
     */
    private String[] getQueryColumnNames( String queryText, File file )
    {
        IDriver ffDriver = new FlatFileDriver();
        IConnection conn = null;
        try
        {
            conn = ffDriver.getConnection( null );
            IResultSetMetaData metadata = getResultSetMetaData( queryText,
                    file, conn );
            int columnCount = metadata.getColumnCount();
            if( columnCount == 0 )
                return null;

            String[] result = new String[columnCount];
            for( int i = 0; i < columnCount; i++)
                result[i] = metadata.getColumnName( i + 1 );
            return result;
        }
        catch( OdaException e )
        {
            return null;
        }
        finally
        {
            closeConnection( conn );
        }
    }

    /**
     * Attempts to close given ODA connection.
     * @param conn
     */
    private void closeConnection( IConnection conn )
    {
        try
        {
            if( conn != null )
                conn.close();
        }
        catch( OdaException e )
        {
            // ignore
        }
    }

    /**
     * Obtains the query's result set metadata from
     * the ODA flatfile runtime driver by preparing
     * the given query.
     * Uses the data source connection properties setting.
     * @throws OdaException
     */
    private IResultSetMetaData getResultSetMetaData( 
                String queryText,
                File file, IConnection conn ) throws OdaException
    {
        java.util.Properties prop = new java.util.Properties();
        prop.put( CommonConstants.CONN_HOME_DIR_PROP, file.getParent() );
        prop.put( CommonConstants.CONN_CHARSET_PROP, m_charSet );
        prop.put( CommonConstants.CONN_INCLTYPELINE_PROP, m_inclTypeLine );
        conn.open( prop );

        IQuery query = conn.newQuery( null );
        query.setMaxRows( 1 );
        query.prepare( queryText );
        query.executeQuery();

        return query.getMetaData();
    }

    /**
     * Enable all control of this page
     */
    private void enableAll()
    {
        m_availableList.setEnabled( true );
        m_selectedList.setEnabled( true );
        m_btnAdd.setEnabled( true );
        m_btnAddAll.setEnabled( true );
        m_btnRemove.setEnabled( true );
        m_btnRemoveAll.setEnabled( true );
    }

    /**
     * Disable all control of this page
     */
    private void disableAll()
    {
        m_availableList.setEnabled( false );
        m_selectedList.setEnabled( false );
        m_btnAdd.setEnabled( false );
        m_btnAddAll.setEnabled( false );
        m_btnRemove.setEnabled( false );
        m_btnRemoveAll.setEnabled( false );
        setPageComplete( false );
    }

    /**
     * Add list items to specified list
     * 
     * @param items
     * @param targetList
     */
    private void addItems( String[] items, List targetList )
    {
        for( int n = 0; n < items.length; n++)
        {
            targetList.add( items[n] );
        }
    }

    /**
     * Remove selected item from specified list
     * 
     * @param targetList
     */
    private void removeSelectedItems( List targetList )
    {
        targetList.remove( targetList.getSelectionIndices() );
        targetList.select( targetList.getTopIndex() );
    }

    /**
     * Return query of this data set
     * 
     * @return
     */
    private String getQuery()
    {
        String tableName = null;
        StringBuffer buf = new StringBuffer();
        File file = (File) ((StructuredSelection) m_fileViewer.getSelection())
                .getFirstElement();
        if( file != null )
        {
            tableName = file.getName();
        }
        if( tableName != null )
        {
            if( m_availableList.getItemCount() == 0 )
            {
                buf.append( "select * from " ).append( tableName ); //$NON-NLS-1$
            }
            else
            {
                buf.append( "select " ); //$NON-NLS-1$
                String[] columns = m_selectedList.getItems();
                for( int n = 0; n < columns.length; n++)
                {
                    buf.append( columns[n] );
                    if( n < columns.length - 1 )
                    {
                        buf.append( ", " ); //$NON-NLS-1$
                    }
                }
                buf.append( " from " ).append( tableName ); //$NON-NLS-1$
            }
        }
        return buf.toString();

    }

    /**
     * Update value of query text
     * 
     * @param query
     */
    private void updateValuesFromQuery( String query )
    {
        String[] splitQuery = stripFromClause( query );
        // If the length is equal to 2 then we have a valid query
        if( splitQuery.length == 2 )
        {
            String table = splitQuery[1];
            String columns = stripSelect( splitQuery[0] );
            // If both are not null then we can assume we have a valid query
            if( table != null && columns != null )
            {
                // Now we need to select the table in the list
                // If it doesn't exists then we do not need to process the
                // columns
                File f = selectTableFromQuery( table );
                if( f != null )
                {
                    updateColumnsFromQuery( getColumnsFromQuery( columns ),
                            getFileColumnNames( f ) );
                }
            }
        }

        if( m_selectedList.getItemCount() == 0 )
        {
            m_btnRemoveAll.setEnabled( false );
            setPageComplete( false );
        }
    }

    /**
     * Split Select clause of query text
     * 
     * @param selectedColumns
     * @return
     */
    private String stripSelect( String selectedColumns )
    {
        String[] result = selectedColumns.split( "SELECT " ); //$NON-NLS-1$
        if( result.length == 2 )
        {
            return result[1];
        }

        return null;
    }

    /**
     * Split From clause of query text
     * 
     * @param selectedColumns
     * @return
     */
    private static String[] stripFromClause( String query )
    {
        query = query.toUpperCase();
        return query.split( " FROM " ); //$NON-NLS-1$
    }

    /**
     * @param tableName
     * @return File
     */
    private File selectTableFromQuery( String tableName )
    {
        // for page refresh
        resetInitialized();
        m_fileFilter.setSelection( new StructuredSelection( MATCH_ALL_FILES ) ); 
        File[] files = (File[]) m_fileViewer.getInput();
        for( int n = 0; n < files.length; n++)
        {
            if( files[n].getName().equalsIgnoreCase( tableName ) )
            {
                resetInitialized();
                if( tableName.toLowerCase().endsWith( CSV_SUFFIX ) ) 
                    m_fileFilter
                            .setSelection( new StructuredSelection( ALL_CSV_SUFFIX ) ); 
                else if( tableName.toLowerCase().endsWith( TXT_SUFFIX ) ) 
                    m_fileFilter
                            .setSelection( new StructuredSelection( ALL_TXT_SUFFIX ) ); 

                m_fileViewer.setSelection( new StructuredSelection( files[n] ) );

                return files[n];
            }
        }
        return null;
    }

    private void resetInitialized()
    {
        this.m_initialized = false;
    }

    /**
     * Update selected and unselected column from lastest query text
     * 
     * @param columns
     * @param availableColumns
     */
    private void updateColumnsFromQuery( String[] columns,
            String[] availableColumns )
    {
        m_availableList.setItems( availableColumns );
        m_selectedList.removeAll();
        for( int n = 0; n < columns.length; n++)
        {
            if( columns[n].trim().equals( CommonConstants.KEYWORD_ASTERISK ) )
            {
                // WildCard just select everything
                m_availableList.removeAll();
                m_selectedList.setItems( availableColumns );
                m_btnAdd.setEnabled( false );
                m_btnAddAll.setEnabled( false );
                setPageComplete( true );
                m_btnRemoveAll.setEnabled( true );
                return;
            }
            else
            {
                // Find this column in the m_availableList
                for( int m = 0; m < availableColumns.length; m++)
                {
                    if( availableColumns[m].equalsIgnoreCase( columns[n] ) )
                    {
                        // move this to the m_selectedList
                        m_selectedList.add( availableColumns[m] );
                        if( m_availableList.indexOf( availableColumns[m] ) >= 0 )
                            m_availableList.remove( availableColumns[m] );
                        break;
                    }
                }
            }
        }
        setPageComplete( true );
        m_btnRemoveAll.setEnabled( true );
        m_btnAddAll.setEnabled( true );
        m_btnAdd.setEnabled( false );
        m_btnRemove.setEnabled( false );
        if( m_availableList.getItemCount() == 0 )
        {
            m_btnAddAll.setEnabled( false );
        }
        if( m_selectedList.getItemCount() == 0 )
        {
            m_btnRemoveAll.setEnabled( false );
            setPageComplete( false );
        }
    }

    /**
     * Return all columns from column list
     * @param columnList
     * @return
     */
    private String[] getColumnsFromQuery( String columnList )
    {
        String[] columns = columnList.split( CommonConstants.DELIMITER_COMMA ); //$NON-NLS-1$
        // Remove the as keyword if any
        for( int n = 0; n < columns.length; n++)
        {
            String[] result = columns[n].split( " AS " ); //$NON-NLS-1$
            columns[n] = result[0].trim();
        }

        return columns;
    }

    /**
     * Populate file filters for file combo viewer
     */
    private void populateFileFilter()
    {
        if( m_fileFilter != null && !m_fileFilter.getControl().isDisposed() )
        {
            if( m_fileFilter.getCombo().getSelectionIndex() == -1 )
            {
                m_fileFilter.add( ALL_CSV_SUFFIX ); 
                m_fileFilter.add( ALL_TXT_SUFFIX ); 
                m_fileFilter.add( MATCH_ALL_FILES ); 
                m_fileFilter.getCombo().select( 0 );

                this.m_selectedFileFilter = ALL_CSV_SUFFIX; 
            }
        }
    }

    /**
     * Add selectd columns
     */
    private void addColumns()
    {
        addItems( m_availableList.getSelection(), m_selectedList );
        removeSelectedItems( m_availableList );
        if( m_availableList.getItemCount() == 0 )
        {
            m_btnAddAll.setEnabled( false );
        }
        if( m_availableList.getSelectionCount() == 0 )
            m_btnAdd.setEnabled( false );
        m_btnRemoveAll.setEnabled( true );
        setMessage( DEFAULT_MESSAGE );
        setPageComplete( true );
    }

    private boolean hasValidData()
    {
        if( m_fileViewer == null )
            return false;

        String fileName = m_fileViewer.getCombo().getText().toLowerCase();
        if( !(fileName.endsWith( CSV_SUFFIX ) || fileName.endsWith( TXT_SUFFIX )) )  
        {
            if( !isContinue( Messages
                    .getString( "warning.columnExtensionInvalid" ) ) ) //$NON-NLS-1$
                return false;
        }
        if( isPageComplete() )
        {
            return true;
        }
        setMessage( Messages.getString( "error.selectColumns" ), ERROR ); //$NON-NLS-1$
        return false;
    }

    /**
     * Remove selected columns
     */
    private void removeColumns()
    {
        addItems( m_selectedList.getSelection(), m_availableList );
        removeSelectedItems( m_selectedList );
        if( m_selectedList.getSelectionCount() == 0 )
            m_btnRemove.setEnabled( false );
        if( m_selectedList.getItemCount() == 0 )
        {
            m_btnRemoveAll.setEnabled( false );
            setPageComplete( false );
        }
        m_btnAddAll.setEnabled( true );
    }

    /**
     * Warn user whether action continues
     * 
     * @param warnMsg
     * @return true, continue action
     */
    private boolean isContinue( String warnMsg )
    {
        MessageDialog dialog = new MessageDialog( this.getShell(), Messages
                .getString( "title.warning" ), null, warnMsg, //$NON-NLS-1$
                MessageDialog.WARNING, new String[]
                { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL }, 0 );
        return dialog.open() == MessageDialog.OK;
    }

    /**
     * Updates the given dataSetDesign with the query and its metadata defined
     * in this page.
     * @param dataSetDesign
     */
    private void savePage( DataSetDesign dataSetDesign )
    {
        String queryText = getQuery();
        dataSetDesign.setQueryText( queryText );

        // obtain query's result set metadata, and update
        // the dataSetDesign with it
        IConnection conn = null;
        try
        {
            IDriver ffDriver = new FlatFileDriver();
            conn = ffDriver.getConnection( null );
            IResultSetMetaData metadata = 
                getResultSetMetaData( queryText, m_selectedFile, conn );
            setResultSetMetaData( dataSetDesign, metadata );
        }
        catch( OdaException e )
        {
            // no result set definition available, reset in dataSetDesign
            dataSetDesign.setResultSets( null );
        }
        finally
        {
            closeConnection( conn );
        }
        
        /*
         * See DesignSessionUtil for more convenience methods
         * to define a data set design instance.  
         */     

        /*
         * Since this flatfile driver does not support
         * query parameters and properties, there are
         * no data set parameters and public/private properties
         * to specify in the data set design instance
         */
    }

    private void setResultSetMetaData( DataSetDesign dataSetDesign,
            IResultSetMetaData md ) throws OdaException
    {
        ResultSetColumns columns = DesignSessionUtil.toResultSetColumnsDesign( md );

        ResultSetDefinition resultSetDefn = DesignFactory.eINSTANCE
                .createResultSetDefinition();
        // flat file does not support result set name
        resultSetDefn.setResultSetColumns( columns );

        // no exception; go ahead and assign to specified dataSetDesign
        dataSetDesign.setPrimaryResultSet( resultSetDefn );
        dataSetDesign.getResultSets().setDerivedMetaData( true );
    }

    class CSVFileFilter implements FilenameFilter
    {
        private String extension = null;

        CSVFileFilter( String ext )
        {
            if( ALL_CSV_SUFFIX.equalsIgnoreCase( ext ) ) 
                extension = CSV_SUFFIX; 
            else if( ALL_TXT_SUFFIX.equalsIgnoreCase( ext ) ) 
                extension = TXT_SUFFIX; 
            else
                extension = null;

        }

        public boolean accept( File dir, String name )
        {
            if( extension == null )
            {
                File file = new File( dir + File.separator + name );
                if( file.isFile() && !file.isHidden() )
                    return true;
                else
                    return false;
            }
            else
                return name.toLowerCase().endsWith( extension );
        }
    }

}
