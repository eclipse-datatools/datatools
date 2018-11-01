/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.browse;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Widget;

/**
 * Internal class for implementing a Browse button with two 
 * menu items - one for browsing using a path relative to
 * the host resource folder, and the second one for 
 * browsing using an absolute path.
 * 
 * @since 3.2.6 (DTP 1.9.2)
 */
public class ProfileStoreBrowseButton
{
    private static final int BUTTON_MIN_WIDTH = 80;
    
	private MenuButton button;
	private Menu menu;

	private IMenuButtonProvider provider;
	private IBrowseButtonHost host;

	private SelectionAdapter listener = new SelectionAdapter( ) {

		public void widgetSelected( SelectionEvent e )
		{
			Widget widget = e.widget;
            host.browseSelected();
			if ( widget instanceof MenuItem )
			{
				provider.handleSelectionEvent( (String) widget.getData( ) );
			}
			else if ( widget instanceof MenuButton )
			{
				provider.handleSelectionEvent( ( (MenuButtonProvider) provider ).getDefaultOptionType( ) );
			}
		}

	};

	public ProfileStoreBrowseButton( Composite parent, int style,
			IMenuButtonProvider provider, IBrowseButtonHost host)
	{
		this.host = host;
		button = new MenuButton( parent, style );
		button.addSelectionListener( listener );
		button.addSelectionListener( new SelectionAdapter( ) {

			public void widgetSelected( SelectionEvent e )
			{
				refreshMenuItems( );
			}

		} );

		menu = new Menu( parent.getShell( ), SWT.POP_UP );
		button.setDropDownMenu( menu );
		button.setText( provider.getButtonText( ) );
        button.setToolTipText( provider.getToolTipText() );
		setMenuButtonProvider( provider );
		refresh( );
	}
	
	public void setLayoutData(Object layoutData)
	{
		button.setLayoutData( layoutData );	
	}

	public void refreshMenuItems( )
	{
		( (MenuButtonProvider) this.provider ).resetProperties( );
		populateMenuItems( );
	}

	public void setEnabled( boolean enable )
	{
		button.setEnabled( enable );
	}

	public boolean isEnabled( )
	{
		return button.isEnabled( );
	}

	public MenuButton getControl( )
	{
		return button;
	}

	public IMenuButtonProvider getMenuButtonProvider()
	{
		return provider;
	}

	public void refresh( )
	{
	}

	public void setMenuButtonProvider( IMenuButtonProvider provider )
	{
		if ( provider != null && provider != this.provider )
		{
			this.provider = provider;

			provider.setInput( this );

			populateMenuItems( );
		}
	}

	private void populateMenuItems( )
	{
		for ( int i = 0; i < menu.getItemCount( ); i++ )
		{
			menu.getItem( i ).dispose( );
			i--;
		}

		String[] types = this.provider.getMenuItems( );
		for ( int i = 0; i < types.length; i++ )
		{
			MenuItem item = new MenuItem( menu, SWT.PUSH );
			item.setText( provider.getMenuItemText( types[i] ) );
			item.setData( types[i] );
			item.setImage( this.provider.getMenuItemImage( types[i] ) );
			item.addSelectionListener( listener );
		}

		if ( menu.getItemCount( ) <= 0 )
		{
			button.setDropDownMenu( null );
		}
		refresh( );
	}

	public void handleSelection( String path, boolean isRelative )
	{
		host.setProfileStorePath( path, isRelative);
	}
	
	public int computeButtonWidth( )
    {
		return computeButtonWidth(button);
    }
	
    static int computeButtonWidth( MenuButton button )
    {
        int defaultWidth = button.computeSize( SWT.DEFAULT, SWT.DEFAULT ).x;
        return ( defaultWidth < BUTTON_MIN_WIDTH ) ? 
                BUTTON_MIN_WIDTH : defaultWidth;
    }
	
}
