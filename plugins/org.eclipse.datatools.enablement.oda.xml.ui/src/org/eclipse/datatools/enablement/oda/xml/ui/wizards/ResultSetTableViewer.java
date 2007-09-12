/*******************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

import org.eclipse.datatools.enablement.oda.xml.impl.Connection;
import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.ExceptionHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Table viewer of result set, it contains refresh action to get the results of
 * xml file.
 * 
 * @version $Revision: 1.2 $ $Date: 2007/07/13 02:50:52 $
 */
public final class ResultSetTableViewer
{
	private Table viewer;
	private Composite mainControl;
	private Button btnRefresh;
	private MenuItem itmRefresh;
	private String[][] resultSet;
	private TableColumn column;
	private final int MAX_ROW = 500;

	/**
	 * result set table viewer
	 */
	public ResultSetTableViewer( Composite parent, boolean showMenus,
			boolean showButtons, boolean enableKeyStrokes )
	{
		mainControl = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.numColumns = 2;
		mainControl.setLayout( layout );
	
		GridData data = null;
		viewer = new Table( mainControl, SWT.FULL_SELECTION );
		data = new GridData( GridData.FILL_BOTH );
		viewer.setLayoutData( data );

		viewer.setHeaderVisible( true );
		viewer.setLinesVisible( true );

		if ( showButtons )
		{
			Composite btnComposite = new Composite( mainControl, SWT.NONE );
			data = new GridData( );
			data.verticalAlignment = SWT.CENTER;
			btnComposite.setLayoutData( data );
			GridLayout btnLayout = new GridLayout( );
			layout.verticalSpacing = 20;
			btnComposite.setLayout( btnLayout );

			GridData btnData = new GridData( GridData.CENTER );
			btnData.widthHint = 20;
			btnData.heightHint = 20;
			btnRefresh = new Button( btnComposite, SWT.PUSH );
			//TODO to externalize into message file
			btnRefresh.setToolTipText( "Preview the result set" );
			btnRefresh.setImage( PlatformUI.getWorkbench( )
					.getSharedImages( )
					.getImage( ISharedImages.IMG_DEF_VIEW ) );
			btnRefresh.setLayoutData( btnData );
			btnRefresh.addSelectionListener( new SelectionListener( ) {

				public void widgetSelected( SelectionEvent e )
				{
					btnRefresh.setEnabled( false );
					retrieveResult();
					btnRefresh.setEnabled( true );
				}

				public void widgetDefaultSelected( SelectionEvent e )
				{
				}

			} );
		}

		if ( showMenus )
		{
			Menu menu = new Menu( viewer );

			itmRefresh = new MenuItem( menu, SWT.NONE );
			itmRefresh.setText( Messages.getString( "button.refresh" ) ); //$NON-NLS-1$
			itmRefresh.addSelectionListener( new SelectionAdapter( ) {

				public void widgetSelected( SelectionEvent e )
				{
					retrieveResult( );
				}

			} );
			viewer.setMenu( menu );
		}
		if ( enableKeyStrokes )
		{
			viewer.addKeyListener( new KeyListener( ) {

				public void keyPressed( KeyEvent e )
				{
				}

				public void keyReleased( KeyEvent e )
				{
					if ( e.keyCode == SWT.ALT )
					{
						retrieveResult( );
					}
				}

			} );
		}
	}
	
	private void retrieveResult( )
	{
		BusyIndicator.showWhile( viewer.getDisplay( ), new Runnable( ) {

			/*
			 * @see java.lang.Runnable#run()
			 */
			public void run( )
			{
				refresh( );
			}
		} );
	}

	/**
	 * get the resultset and refresh
	 *
	 */
	protected void refresh( )
	{
		Connection conn = new Connection( );
		IResultSet rs = null;

		Properties properties = new Properties( );
		String xmlFile = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_XML_FILE );
		if ( xmlFile == null || xmlFile.trim( ).length( ) == 0 )
		{
			xmlFile = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_FILELIST );
		}

		properties.setProperty( Constants.CONST_PROP_FILELIST, xmlFile == null
				? "" : xmlFile );
		
		String xmlEncoding = XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_ENCODINGLIST );
		properties.setProperty( Constants.CONST_PROP_ENCODINGLIST,
				xmlEncoding == null ? "" : xmlEncoding );
		try
		{
			conn.open( properties );

			IQuery query = conn.newQuery( null );

			int maxRow = Integer.parseInt( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_MAX_ROW ) != null
					? XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_MAX_ROW )
					: "-1" ); //$NON-NLS-1$
			query.setMaxRows( maxRow );
			query.prepare( XMLInformationHolder.getPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION ) );
			rs = query.executeQuery( );
			refreshTable( rs );
		}
		catch ( Exception ex )
		{
			ExceptionHandler.showException( this.getControl( ).getShell( ),
					Messages.getString( "error.label" ),
					ex.getMessage( ),
					ex );
		}
		finally
		{
			try
			{
				if ( rs != null )
					rs.close( );
				if ( conn != null )
					conn.close( );
			}
			catch ( OdaException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * refresh table viewer
	 * @param rs
	 */
	private void refreshTable( IResultSet rs )
	{  

		viewer.removeAll( );
		for ( int i = 0; i < viewer.getColumnCount( ); i++  )
		{
            viewer.getColumn( i ).setText( "" );
		}

		while ( viewer.getColumnCount( ) > 0 )
		{
			viewer.getColumn( 0 ).dispose( );
		}
		int count = 0;
		String[] columnName = null;
		int rowCount = 0;
		if ( rs != null )
		{
			IResultSetMetaData metadata = null;

			try
			{
				metadata = rs.getMetaData( );
				count = metadata.getColumnCount( );
				resultSet = new String[MAX_ROW][count];
				columnName = new String[count];
				int[] columnType = new int[count];
				for ( int i = 0; i < count; i++ )
				{
					columnName[i] = metadata.getColumnName( i + 1 );
					columnType[i] = metadata.getColumnType( i + 1 );
				}

				while ( rs.next( ) )
				{

					for ( int i = 0; i < count && rowCount < MAX_ROW; i++ )
					{
						switch ( columnType[i] )
						{
							case DataTypes.STRING :
							{
								resultSet[rowCount][i] = rs.getString( i + 1 );
								break;
							}
							case DataTypes.INT :
							{
								resultSet[rowCount][i] = String.valueOf( rs.getInt( i + 1 ) );
								break;
							}
							case DataTypes.DOUBLE :
							{
								resultSet[rowCount][i] = String.valueOf( rs.getDouble( i + 1 ) );
								break;
							}
							case DataTypes.DATE :
							{
								resultSet[rowCount][i] = String.valueOf( rs.getDate( i + 1 ) );
								break;
							}
							case DataTypes.TIME :
							{
								resultSet[rowCount][i] = String.valueOf( rs.getTime( i + 1 ) );
								break;
							}
							case DataTypes.TIMESTAMP :
							{
								resultSet[rowCount][i] = String.valueOf( rs.getTimestamp( i + 1 ) );
								break;
							}
							case DataTypes.BIGDECIMAL :
							{
								resultSet[rowCount][i] = String.valueOf( rs.getBigDecimal( i + 1 ) );
								break;
							}
							case DataTypes.BLOB :
							{
								resultSet[rowCount][i] = String.valueOf( rs.getBlob( i + 1 ) );
								break;
							}
							case DataTypes.BOOLEAN :
							{
								resultSet[rowCount][i] = String.valueOf( rs.getBoolean( i + 1 ) );
								break;
							}
							default :
								resultSet[rowCount][i] = rs.getString( i + 1 );
						}
						if( rs.wasNull() )
							resultSet[rowCount][i] = null;
					}
					rowCount++;
				}
				if ( rowCount > MAX_ROW )
				{
					MessageDialog.openInformation( this.getControl( )
							.getShell( ),
							Messages.getString( "ColumnMappingDialog.prompt.dialog.title" ),
							Messages.getString( "ColumnMappingDialog.prompt.maxRow" ) );
					rowCount = MAX_ROW;
				}
			}
			catch ( OdaException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace( );
			}

			column = new TableColumn( viewer, SWT.LEFT );
			column.setText( " " ); //$NON-NLS-1$
			column.setResizable( false );
			column.setWidth( 20 );
			for ( int i = 0; i < count; i++ )
			{
				if ( viewer.getColumnCount( ) <= i + 1 )
					column = new TableColumn( viewer, SWT.LEFT );
				else
					column = viewer.getColumn( i + 1 );
				column.setText( columnName[i] ); //$NON-NLS-1$
				column.setWidth( 100 );
				addColumnSortListener( column, i+1 );
			}
			for ( int i = 0; i < rowCount; i++ )
			{
				TableItem tableItem = new TableItem( viewer, SWT.NONE );
				String[] record = new String[count + 1];
				for ( int j = 0; j < count; j++ )
				{
					record[j + 1] = resultSet[i][j];
				}
				tableItem.setText( record );
				viewer.redraw( );
			}
		}
	}

	/**
	 * Add listener to a column
	 * @param column
	 * @param n
	 */
	private void addColumnSortListener( TableColumn column, final int index )
	{
		column.addSelectionListener( new SelectionListener( ) {

			private boolean asc = false;

			public void widgetSelected( SelectionEvent e )
			{
				sort( index, asc );
				asc = !asc;
			}

			public void widgetDefaultSelected( SelectionEvent e )
			{

			}
		} );
	}

	/**
	 * Carry out sort operation against certain column
	 * @param columnIndex the column based on which the sort operation would be carried out 
	 * @param asc the sort direction
	 */	
	private void sort(final int columnIndex, final boolean asc)
	{
		TableItem[] tableItems = viewer.getItems( );

		Arrays.sort( tableItems, new Comparator( ) {

			public int compare( Object o1, Object o2 )
			{
				TableItem it1 = (TableItem) o1;
				TableItem it2 = (TableItem) o2;
				int result = 0;
				if ( asc )
				{
					result = it1.getText( columnIndex )
							.compareTo( it2.getText( columnIndex ) );
				}
				else
				{
					result = it2.getText( columnIndex )
							.compareTo( it1.getText( columnIndex ) );
				}
				return result;
			}
		} );
		
		String[][] records = mapTableItemsTo2DArray( tableItems );
		
		viewer.removeAll();
		TableItem tableItem;
		for (int i = 0; i < tableItems.length; i++)
		{
			tableItem = new TableItem( viewer,SWT.NONE);
			tableItem.setText(records[i]);
		}
	}

	/**
	 * Map TableItems to a 2-dimension array
	 * @param tableItems
	 * @return
	 */
	private String[][] mapTableItemsTo2DArray( TableItem[] tableItems )
	{
		String[][] records = new String[tableItems.length][viewer.getColumnCount( )];
		
		for ( int i = 0; i < tableItems.length; i++)
		{	
			for ( int j = 0; j < viewer.getColumnCount( ); j++ )
			{
				records[i][j] = tableItems[i].getText(j);
			}
		}
		return records;
	}
    
	/**
	 * get tableViwer's table
	 * @return
	 */
	public Table getViewer( )
	{
		return viewer;
	}

	/**
	 * get main control
	 * @return
	 */
	public Composite getControl( )
	{
		return mainControl;
	}

	/**
	 * get refresh button
	 * @return
	 */
	public Button getRefreshButton( )
	{
		return btnRefresh;
	}

	/**
	 * get refresh menuItem
	 * @return
	 */
	public MenuItem getRefreshMenu( )
	{
		return itmRefresh;
	}
  }
