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

import org.eclipse.swt.graphics.Image;

/**
 * Internal interface for the menu button provider.
 * @since 3.2.6 (DTP 1.9.2)
 */
public interface IMenuButtonProvider
{
	/**
	 * Set Menu button for the provider
	 * @param input - ProfileStoreBrowseButton to use
	 */
	public void setInput( ProfileStoreBrowseButton input );

	/**
	 * @return the menu items that should be added to the 
	 * ProfileStoreBrowseButton
	 */
	public String[] getMenuItems( );

	/**
	 * @param menuItem 
	 * @return image to display for the menuItem
	 */
	public Image getMenuItemImage( String menuItem );

	/**
	 * @param menuItem
	 * @return text to display for the menuItem
	 */
	public String getMenuItemText( String menuItem );

	/**
	 * Selection event passed when a menu item is selected
	 * @param type - absolute or relative
	 */
	public void handleSelectionEvent( String type );

	/**
	 * @return the text to display on the button
	 */
	public String getButtonText( );

    /**
     * @return the tooltip text to display for the button
     */
    public String getToolTipText( );

	/**
	 * The IBrowseButton host can call this function on the 
	 * provider to set a property
	 * @param key
	 * @param value
	 */
	public void setProperty( String key, Object value );
	
	/**
	 * @param key the property key to look up
	 * @return the value of a property
	 */
	public Object getProperty( String key );

}
