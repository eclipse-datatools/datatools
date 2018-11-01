/*
 ******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 ******************************************************************************
 */

package org.eclipse.datatools.enablement.oda.ws.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.enablement.oda.ws.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	public static final String ICONS_PATH = "icons/";//$NON-NLS-1$
	public static final String ICON_WSDL = "iconWSDL";//$NON-NLS-1$
	public static final String ICON_SERVICE = "iconService";//$NON-NLS-1$
	public static final String ICON_PORT = "iconPort";//$NON-NLS-1$
	public static final String ICON_OPERATION = "iconOpeartion";//$NON-NLS-1$
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		declareImages( );
	}
	
	private void declareImages( )
	{
		declareImage( ICON_WSDL, ICONS_PATH + "wsdl.gif" ); //$NON-NLS-1$
		declareImage( ICON_SERVICE, ICONS_PATH + "service.gif" ); //$NON-NLS-1$
		declareImage( ICON_PORT, ICONS_PATH + "port.gif" ); //$NON-NLS-1$
		declareImage( ICON_OPERATION, ICONS_PATH + "operation.gif" );//$NON-NLS-1$
	}

	private void declareImage( String key, String path )
	{
		URL url = null;
		try
		{
			url = new URL( Activator.getDefault( ).getBundle( ).getEntry( "/" ), //$NON-NLS-1$
					path );
		}
		catch ( MalformedURLException e )
		{
		}
		ImageDescriptor desc = ImageDescriptor.createFromURL( url );
		declareImage( key, desc );
	}

	private void declareImage( String symbolicName, ImageDescriptor descriptor )
	{
		getImageRegistry( ).put( symbolicName, descriptor );
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
