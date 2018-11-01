/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

public final class RoutineEditorImages {
	
	/**
	 * Gets the image (.gif file) corresponding to the given key.
	 * 
	 * @return the requested image, or <code>null</code> if not found
	 */
	public static Image getImage(String key) {
		ImageRegistry imageRegistry = RoutineEditorUIActivator.getDefault()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null) {
			ImageDescriptor descriptor = null;
			try {
				URL baseURL = RoutineEditorUIActivator.getDefault().getBundle()
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
			descriptor = ImageDescriptor.createFromImage(image);
		}
		return descriptor;
	}

}