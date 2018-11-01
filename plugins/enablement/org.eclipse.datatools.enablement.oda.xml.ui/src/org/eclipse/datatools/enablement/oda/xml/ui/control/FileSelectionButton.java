/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.enablement.oda.xml.ui.control;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.datatools.enablement.oda.xml.ui.i18n.Messages;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * File selection button which supports absolute and relative path selection.
 * <p>
 * The relative path selection is enabled (appears on the menu ) only when the
 * base folder is provided. The base folder is the folder against which the
 * relative path is calculated.
 * 
 */
public class FileSelectionButton
{

	private static final int ABSOLUTE_PATH = 1;
	private static final int RELATIVE_PATH = 2;

	private IMenuActionHandler handler;
	private int defaultAction;
	private MenuButton menuButton;
	private Menu menu;
	private SelectionAdapter menuAction;
	
	public FileSelectionButton( Composite parent, int style )
	{
		this( parent, style, RELATIVE_PATH );
	}

	public FileSelectionButton( Composite parent, int style, final int defaultAction )
	{
		menuButton = new MenuButton( parent, style );
		this.defaultAction = defaultAction;
		initMenuItems( false );
	}
	
	public void setText( String text )
	{
		menuButton.setText( text );
	}
	
	private void initSelectionListener( )
	{
		menuAction = new SelectionAdapter( ) {

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
					defaultAction = handler != null
							&& handler.getBaseFolder( ) != null ? RELATIVE_PATH
							: ABSOLUTE_PATH;
					handleFileSelection( defaultAction );
				}
			}
		};
		
	}

	private void initMenuItems( boolean supportRelative )
	{
		if ( menuAction == null )
		{
			initSelectionListener( );
			menuButton.addSelectionListener( menuAction );
		}
		
		if( menu != null )
		{
			menu.dispose( );
		}
		
		menu = new Menu( menuButton.getShell( ), SWT.POP_UP );
		MenuItem item = new MenuItem( menu, SWT.PUSH );
		item.setText( Messages.getString( "FileSelectionButton.menuItem.absolutePath" ) ); //$NON-NLS-1$
		item.setData( ABSOLUTE_PATH );
		item.addSelectionListener( menuAction );

		if ( supportRelative )
		{
			MenuItem relativeItem = new MenuItem( menu, SWT.PUSH );
			relativeItem.setText( Messages.getString( "FileSelectionButton.menuItem.relativePath" ) ); //$NON-NLS-1$
			relativeItem.setData( RELATIVE_PATH );
			relativeItem.addSelectionListener( menuAction );
		}

		menuButton.setDropDownMenu( menu );
	}
	
	private void handleFileSelection( int selectionType )
	{
		if ( handler == null )
			return;
		if ( selectionType == RELATIVE_PATH )
		{
			RelativeFileSelectionDialog dialog = new RelativeFileSelectionDialog( menuButton.getShell( ),
					handler.getBaseFolder( ) ,
					handler.getExtensionsFilter( ) );
			if ( dialog.open( ) == Window.OK )
			{
				try
				{
					URI uri = dialog.getSelected( );
					if ( uri != null )
					{
						handler.setPath( uri.getPath( ) );
					}
				}
				catch ( URISyntaxException e )
				{
				}
			}
		}
		else if ( selectionType == ABSOLUTE_PATH )
		{
			FileDialog dialog = new FileDialog( menuButton.getShell( ) );
			dialog.setFilterExtensions( handler.getExtensionsFilter( ) );
			String path = handler.getFilePath( );
			if ( path != null && path.trim( ).length( ) > 0 )
			{
				File f = new File( path );
				if ( f.exists( ) && f.isFile( ) )
					dialog.setFileName( path );
				else 
					dialog.setFilterPath( path );
			}
			else
			{
				dialog.setFilterPath( handler.getBaseFolder( ) );
			}

			String filePath = dialog.open( );
			if ( filePath != null )
			{
				handler.setPath( new File( filePath ).getAbsolutePath( ) );
			}
		}
	}

	public void setActionHandler( IMenuActionHandler action )
	{
		handler = action;
		if ( handler.getBaseFolder( ) != null )
		{
			initMenuItems( true );
		}
	}

}
