/*******************************************************************************
 * Copyright (c) 2005, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;
import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.util.RelationInformation;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * Table viewer of column mapping
 * 
 * @version $Revision: 1.11 $ $Date: 2008/05/07 10:06:33 $
 */
public final class ColumnMappingTableViewer
{
	private TableViewer viewer;
	private Composite mainControl;
	private Button btnAdd;
	private Button btnEdit;
	private Button btnRemove;
	private Button btnUp;
	private Button btnDown;
	private MenuItem itmRemove;
	private MenuItem itmRemoveAll;
    
	/**
	 * column mapping table viewer. it supplies the button of remove, up , down
	 * and the menu of remove,removeAll....
	 */
	public ColumnMappingTableViewer( Composite parent, boolean showMenus,
			boolean showButtons, boolean enableKeyStrokes )
	{
		mainControl = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout( );
		layout.numColumns = 2;
		layout.marginWidth = layout.marginHeight = 0;
		mainControl.setLayout( layout );

		viewer = new TableViewer( mainControl, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER );
		GridData data = new GridData( GridData.FILL_BOTH );
		mainControl.setLayoutData( data );
		viewer.getControl( ).setLayoutData( data );

		viewer.getTable( ).setHeaderVisible( true );
		viewer.getTable( ).setLinesVisible( true );

		if ( showButtons )
		{
			Composite btnComposite = new Composite( mainControl, SWT.NONE );
			data = new GridData( );
			data.verticalAlignment = SWT.CENTER;
			btnComposite.setLayoutData( data );
			GridLayout btnLayout = new GridLayout( );
			btnLayout.marginWidth = layout.marginHeight = 0;
			btnLayout.verticalSpacing = 10;
			btnComposite.setLayout( btnLayout );

			btnAdd = new Button( btnComposite, SWT.NONE );
			btnAdd.setText( Messages.getString( "menu.button.add" ) ); //$NON-NLS-1$
			btnAdd.setEnabled( true );

			btnEdit = new Button( btnComposite, SWT.NONE );
			btnEdit.setText( Messages.getString( "menu.button.edit" ) ); //$NON-NLS-1$
			btnEdit.setEnabled( false );

			btnRemove = new Button( btnComposite, SWT.NONE );
			btnRemove.setText( Messages.getString( "menu.button.remove" ) ); //$NON-NLS-1$
			btnRemove.setEnabled( false );

			btnUp = new Button( btnComposite, SWT.NONE );
			btnUp.setText( Messages.getString( "menu.button.up" ) ); //$NON-NLS-1$
			btnUp.setEnabled( false );

			btnDown = new Button( btnComposite, SWT.NONE );
			btnDown.setText( Messages.getString( "menu.button.down" ) ); //$NON-NLS-1$
			btnDown.setEnabled( false );

			btnDown.addSelectionListener( new SelectionListener( ) {

				public void widgetSelected( SelectionEvent e )
				{
				}

				public void widgetDefaultSelected( SelectionEvent e )
				{
				}

			} );

			int maxWidth = getMaxWidth( btnAdd, 60 );
			maxWidth = getMaxWidth( btnEdit, maxWidth );
			maxWidth = getMaxWidth( btnRemove, maxWidth );
			maxWidth = getMaxWidth( btnUp, maxWidth );
			maxWidth = getMaxWidth( btnDown, maxWidth );

			GridData btnData = new GridData( );
			btnData.widthHint = maxWidth;
			btnAdd.setLayoutData( btnData );
			btnEdit.setLayoutData( btnData );
			btnRemove.setLayoutData( btnData );
			btnUp.setLayoutData( btnData );
			btnDown.setLayoutData( btnData );

		}

		if ( showMenus )
		{
			Menu menu = new Menu( viewer.getTable( ) );
			menu.addMenuListener( new MenuAdapter( ) {

				public void menuShown( MenuEvent e )
				{
					viewer.cancelEditing( );
				}
			} );
			itmRemove = new MenuItem( menu, SWT.NONE );
			itmRemove.setText( Messages.getString( "menu.menuItem.remove" ) ); //$NON-NLS-1$

			itmRemoveAll = new MenuItem( menu, SWT.NONE );
			itmRemoveAll.setText( Messages.getString( "menu.menuItem.removeAll" ) ); //$NON-NLS-1$

			viewer.getTable( ).setMenu( menu );
		}

		if ( enableKeyStrokes )
		{
			viewer.getTable( ).addKeyListener( new KeyListener( ) {

				public void keyPressed( KeyEvent e )
				{
				}

				public void keyReleased( KeyEvent e )
				{
					if ( e.keyCode == SWT.DEL )
					{
					}
				}

			} );
		}
	}

	private int getMaxWidth( Control control, int size )
	{
		int width = control.computeSize( -1, -1 ).x;
		return width > size ? width : size;
	}

	/**
	 * get the table viewer
	 * 
	 * @return
	 */
	public TableViewer getViewer( )
	{
		return viewer;
	}

	/**
	 * get the main control
	 * 
	 * @return
	 */
	public Composite getControl( )
	{
		return mainControl;
	}

	/**
	 * get the Add button
	 * 
	 * @return
	 */
	public Button getAddButton( )
	{
		return btnAdd;
	}

	/**
	 * get the up button
	 * 
	 * @return
	 */
	public Button getUpButton( )
	{
		return btnUp;
	}

	/**
	 * get the down button
	 * 
	 * @return
	 */
	public Button getDownButton( )
	{
		return btnDown;
	}

	/**
	 * get the remove button
	 * 
	 * @return
	 */
	public Button getRemoveButton( )
	{
		return btnRemove;
	}

	/**
	 * get the edit button
	 * 
	 * @return
	 */
	public Button getEditButton( )
	{
		return btnEdit;
	}
	
	/**
	 * get the remove menu item
	 * 
	 * @return
	 */
	public MenuItem getRemoveMenuItem( )
	{
		return itmRemove;
	}

	/**
	 * get the remove all menu item
	 * 
	 * @return
	 */
	public MenuItem getRemoveAllMenuItem( )
	{
		return itmRemoveAll;
	}

	/**
	 * According to the relation information of the query, refresh the table items
	 * 
	 * @param info
	 * @param tableName
	 * @return
	 */
	public List refresh( RelationInformation info, String tableName,
			Map columnMapping )
	{
		ArrayList columnsList = new ArrayList();
		if ( info == null )
			return columnsList;
		String[] columnName = info.getTableColumnNames( tableName );
		String[] columnType = new String[columnName.length];
		String[] columnPath = new String[columnName.length];
		ColumnMappingElement element = null;
		for ( int i = 0; i < columnName.length; i++ )
		{
			columnType[i] = info.getTableColumnType( tableName, columnName[i] );
			columnPath[i] = info.getTableOriginalColumnPath( tableName,
					columnName[i] );
			element = new ColumnMappingElement( );
			element.setColumnName( columnName[i] );
			element.setXPath( columnPath[i] );
			try
			{
				// Set type to its display name.
				element.setType( DataTypeUtil.getDataTypeDisplayName( DataTypes.getType( columnType[i] ) ) );
			}
			catch ( OdaException e )
			{
				//Should not arrive here.
			}
			columnMapping.put( columnName[i], element );
			columnsList.add( element );
		}
		return columnsList;
	}
}
