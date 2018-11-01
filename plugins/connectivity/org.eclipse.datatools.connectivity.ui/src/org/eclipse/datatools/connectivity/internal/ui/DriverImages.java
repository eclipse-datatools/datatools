/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Set of images that are used for decorating resources are maintained here.
 * This acts as a image registry and hence there is a single copy of the image
 * files floating around the project.
 * 
 * @author brianf
 * 
 */
public class DriverImages {

	// image keys
	public static String ERROR_KEY = "Error"; //$NON-NLS-1$
	public static String WARNING_KEY = "Warning"; //$NON-NLS-1$
	public static String MISSING_KEY = "Missing"; //$NON-NLS-1$
	public static String DRIVER_KEY = "Driver"; //$NON-NLS-1$
	public static String CATEGORY_KEY = "Category"; //$NON-NLS-1$
	public static String CHANGE_KEY = "Change"; //$NON-NLS-1$

	// image descriptors
	public static ImageDescriptor ERROR = AbstractUIPlugin
			.imageDescriptorFromPlugin(ConnectivityUIPlugin.getDefault()
					.getBundle().getSymbolicName(),
					"icons/full/ovr16/error_co.gif"); //$NON-NLS-1$
	public static ImageDescriptor WARNING = AbstractUIPlugin
			.imageDescriptorFromPlugin(ConnectivityUIPlugin.getDefault()
					.getBundle().getSymbolicName(),
					"icons/full/ovr16/warning_co.gif"); //$NON-NLS-1$
	public static ImageDescriptor NULLIMAGE = ImageDescriptor
			.getMissingImageDescriptor();
	public static ImageDescriptor DRIVER = AbstractUIPlugin
			.imageDescriptorFromPlugin(ConnectivityUIPlugin.getDefault()
					.getBundle().getSymbolicName(), "icons/driver_obj.gif"); //$NON-NLS-1$
	public static ImageDescriptor CATEGORY = AbstractUIPlugin
			.imageDescriptorFromPlugin(ConnectivityUIPlugin.getDefault()
					.getBundle().getSymbolicName(),
					"icons/driver_category_obj.gif"); //$NON-NLS-1$
	public static ImageDescriptor CHANGE = AbstractUIPlugin
		.imageDescriptorFromPlugin(ConnectivityUIPlugin.getDefault()
				.getBundle().getSymbolicName(), "icons/change_obj.gif"); //$NON-NLS-1$

	/**
	 * Constructor for DriverImages.
	 */
	public DriverImages() {
		super();
	}

	public ImageData getErrorImageData() {
		return ERROR.getImageData();
	}

	public ImageData getWarningImageData() {
		return WARNING.getImageData();
	}

	public ImageData getMissingImageData() {
		return NULLIMAGE.getImageData();
	}

	public ImageData getDriverImageData() {
		return DRIVER.getImageData();
	}

	public ImageData getCategoryImageData() {
		return CATEGORY.getImageData();
	}

	public ImageData getChangeImageData() {
		return CHANGE.getImageData();
	}

	/**
	 * Get the image data depending on the key
	 * 
	 * @return image data
	 * 
	 */
	public ImageData getImageData(String imageKey) {
		if (imageKey.equals(ERROR_KEY)) {
			return getErrorImageData();
		}
		if (imageKey.equals(WARNING_KEY)) {
			return getWarningImageData();
		}
		if (imageKey.equals(MISSING_KEY)) {
			return getMissingImageData();
		}
		if (imageKey.equals(DRIVER_KEY)) {
			return getDriverImageData();
		}
		if (imageKey.equals(CATEGORY_KEY)) {
			return getCategoryImageData();
		}
		if (imageKey.equals(CHANGE_KEY)) {
			return getChangeImageData();
		}
		return null;
	}

}
