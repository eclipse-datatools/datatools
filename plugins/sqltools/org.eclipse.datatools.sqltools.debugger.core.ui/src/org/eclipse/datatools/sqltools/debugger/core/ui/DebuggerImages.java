/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.debugger.core.ui;

import java.net.URL;

import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * @author Hui Cao
 * 
 */
public class DebuggerImages {

	/**
	 * Gets the image (.gif file) corresponding to the given key.
	 * 
	 * @return the requested image, or <code>null</code> if not found
	 */
	public static Image getImage(String key) {
		ImageRegistry imageRegistry = DebuggerCoreUIPlugin.getDefault()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null) {
			ImageDescriptor descriptor = null;
			try {
				URL baseURL = DebuggerCorePlugin.getDefault().getBundle()
						.getEntry("icons/"); //$NON-NLS-1$
				URL imageURL = new URL(baseURL, key + ".gif"); //$NON-NLS-1$ //$NON-NLS-2$
				descriptor = ImageDescriptor.createFromURL(imageURL);
			} catch (Exception e) {
			}
			if (descriptor != null) {
				imageRegistry.put(key, descriptor);
				image = imageRegistry.get(key);
			}
		}
		return image;
	}

	/**
	 * Gets the image descriptor (.gif file) corresponding to the given key.
	 * 
	 * @return the requested image descriptor, or <code>null</code> if not
	 *         found
	 */
	public static ImageDescriptor getImageDescriptor(String key) {
		ImageDescriptor descriptor = null;
		Image image = getImage(key);
		if (image != null) {
			ImageDescriptor.createFromImage(image);
		}
		return descriptor;
	}

}
