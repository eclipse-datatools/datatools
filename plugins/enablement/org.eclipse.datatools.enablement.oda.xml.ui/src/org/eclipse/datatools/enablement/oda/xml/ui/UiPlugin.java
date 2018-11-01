/*******************************************************************************
 *  Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.ui;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The main plugin class to be used in the desktop.
 * It contains logic for actions to be performed during the loading
 * and unloading of the plugin.
 * 
 * It also provides mechanism of internationalization, by helping
 * to load strings from the properties file 
 */
public class UiPlugin extends AbstractUIPlugin
{

	//The shared instance.
	private static UiPlugin plugin;
	//Resource bundle.
	private ResourceBundle resourceBundle;
	
	/**
	 * The constructor.
	 */
	public UiPlugin( )
	{
		super( );
		plugin = this;
		try
		{
			resourceBundle = ResourceBundle.getBundle( "org.eclipse.datatools.enablement.oda.xml.ui.messages" ); //$NON-NLS-1$
		}
		catch ( MissingResourceException x )
		{
			resourceBundle = null;
		}
	}

	/**
	 * Returns the shared instance.
	 */
	public static UiPlugin getDefault( )
	{
		return plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString( String key )
	{
		ResourceBundle bundle = UiPlugin.getDefault( ).getResourceBundle( );
		try
		{
			return ( bundle != null ) ? bundle.getString( key ) : key;
		}
		catch ( MissingResourceException e )
		{
			return key;
		}
	}
	
	/**
	 * Returns the string from the Resource bundle, formatted according 
	 * to the arguments specified
	 */
	public static String getFormattedString(String key, Object[] arguments) {
		return MessageFormat.format(getResourceString(key), arguments);
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle( )
	{
		return resourceBundle;
	}
}