/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.apache.derby.internal.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The main plugin class to be used in the desktop.
 */
public class DerbyUIPlugin extends AbstractUIPlugin
{
	//The shared instance.
	private static DerbyUIPlugin plugin;

	/**
	 * The constructor.
	 */
	public DerbyUIPlugin()
	{
		super();
		plugin = this;
	}

	/**
	 * Returns the shared instance.
	 */
	public static DerbyUIPlugin getDefault()
	{
		return plugin;
	}	
	
	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path.
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.datatools.connectivity.apache.derby.ui", path); //$NON-NLS-1$
	}
}
