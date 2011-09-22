/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.browse;

import java.io.File;

import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;

/**
 * Internal provider for ProfileStoreBrowseButton.
 * @since 3.2.6 (DTP 1.9.2)
 */
public class MenuButtonProvider implements IMenuButtonProvider
{
	private static String BUTTON_BROWSE_TEXT = Messages.profilePage_button_browse;
	private static String RELATIVE_PATH_MENU = Messages.profilePage_button_relativepath;
	private static String ABSOLUTE_PATH_MENU = Messages.profilePage_button_absolutepath;
    private static String BUTTON_BROWSE_TOOLTIP_TEXT = Messages.profilePage_button_browse_tooltip;

    private static String RELATIVE_PATH_TYPE = "RelativePath"; //$NON-NLS-1$
	private static String ABSOLUTE_PATH_TYPE = "AbsolutePath"; //$NON-NLS-1$

	private ProfileStoreBrowseButton button;
	private String[] optionTypes;

	public MenuButtonProvider( )
	{
		optionTypes = new String[]{
			ABSOLUTE_PATH_TYPE
		};
	}

	public String getDefaultOptionType( )
	{
		return optionTypes == null || optionTypes.length == 0 ? null
				: optionTypes[0];
	}

	public String[] getMenuItems( )
	{
		return optionTypes;
	}

	public Image getMenuItemImage( String type )
	{
		return null;
	}

	public String getMenuItemText( String type )
	{
		if ( RELATIVE_PATH_TYPE.equals( type ) )
		{
			return RELATIVE_PATH_MENU;
		}
		return ABSOLUTE_PATH_MENU;
	}

	/**
	 * Selection event passed when a menu item is selected.
	 * If absolute menu option is selected a standard eclipse swt FileDialog
	 * is displayed. If a relative path is selected a ProfilePathSelectionDialog
	 * is displayed
	 */
	public void handleSelectionEvent( String type )
	{
		Object value = getProperty( IBrowseButtonHost.RESOURCE_FILE_DIR );
		Object cpvalue = getProperty( IBrowseButtonHost.IS_CREATE_PROFILE );
		Object spvalue = getProperty( IBrowseButtonHost.STORED_PATH );
		String[] fileNames = null;
		boolean isRelative = RELATIVE_PATH_TYPE.equals( type );
		if ( isRelative )
		{
			if ( value != null && value instanceof File &&
					cpvalue != null && cpvalue instanceof Boolean )
			{
				ProfilePathSelectionDialog dialog = new ProfilePathSelectionDialog( button.getControl( )
						.getShell( ),
						(File) value,
						((Boolean) cpvalue).booleanValue(),
						(String)spvalue);
				if ( dialog.open( ) == Window.OK )
				{
					fileNames = dialog.getSelectedItems( );
				}
			}
			else
			{
				MessageBox box = new MessageBox( UIUtil.getDefaultShell( ), SWT.ICON_ERROR );
				box.setText( Messages.ui_errorLabel );
				box.setMessage( Messages.profilePage_resourcebaseuri_failure );
				box.open( );
			}
		}
		else
		{
			FileDialog dialog = new FileDialog( button.getControl( ).getShell( ) );
			if( spvalue instanceof String )
			    dialog.setFileName( (String)spvalue );
			fileNames = new String[1];
			fileNames[0] = dialog.open();
		}
		
		if ( fileNames != null && fileNames[0] != null)
			button.handleSelection( fileNames[0], isRelative );

	}

	public void setInput( ProfileStoreBrowseButton input )
	{
		this.button = input;		
	}
	
	public void resetProperties( )
	{
		boolean supportsRelativePath = getProperty( IBrowseButtonHost.RESOURCE_FILE_DIR ) instanceof File;
		if ( supportsRelativePath )
		{
			optionTypes = new String[]{
					RELATIVE_PATH_TYPE,
					ABSOLUTE_PATH_TYPE
			};
		}
		else
		{
			optionTypes = new String[]{
				ABSOLUTE_PATH_TYPE
			};
		}
	}

	public String getButtonText( )
	{
		return BUTTON_BROWSE_TEXT;
	}

    public String getToolTipText() 
    {
        return BUTTON_BROWSE_TOOLTIP_TEXT;
    }
	
	public Object getProperty( String key )
	{
		return button.getControl( ).getData( key );
	}

	public void setProperty( String key, Object value )
	{
		button.getControl( ).setData( key, value );		
	}

}
