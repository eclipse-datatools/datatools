/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * Maintains the list of IDs and image descriptors of several commonly used
 * images.
 * 
 * @author shongxum
 */
public class SharedImages {

	private static URL _iconBaseURL;

	static {
		String prefix = "icons/full/"; //$NON-NLS-1$

		_iconBaseURL = ConnectivityUIPlugin.getDefault().getBundle().getEntry(
				prefix);
	}

	private static final ImageRegistry IMAGE_REGISTRY = ConnectivityUIPlugin
			.getDefault().getImageRegistry();

	private static final String NAME_PREFIX = "org.eclipse.datatools.connectivity.ui."; //$NON-NLS-1$

	/*
	 * Set of predefined image identifiers.
	 */

	public static String IMAGE_RUNNING = "/image/running"; //$NON-NLS-1$

	public static String IMAGE_OFFLINE = "/image/offline"; //$NON-NLS-1$

	public static final String IMG_OBJ_SERVER_STARTED = NAME_PREFIX
			+ "server_started_obj.gif"; //$NON-NLS-1$

	public static final String IMG_OBJ_SERVER_STOPPED = NAME_PREFIX
			+ "server_stopped_obj.gif"; //$NON-NLS-1$

	public static final String IMG_OBJ_DEFAULT_SERVER_STARTED = NAME_PREFIX
			+ "default_server_started_obj.gif"; //$NON-NLS-1$

	public static final String IMG_OBJ_DEFAULT_SERVER_STOPPED = NAME_PREFIX
			+ "default_server_stopped_obj.gif"; //$NON-NLS-1$

	public static final String IMG_ADD_SERVER = NAME_PREFIX + "add_server.gif"; //$NON-NLS-1$

	public static final String IMG_REMOVE_SERVER = NAME_PREFIX
			+ "remove_server.gif"; //$NON-NLS-1$

	public static final String IMG_OBJ_CHECK = NAME_PREFIX + "check_obj.gif"; //$NON-NLS-1$

	public static final String IMG_OBJ_UNCHECK = NAME_PREFIX
			+ "uncheck_obj.gif"; //$NON-NLS-1$

	public static final String IMG_CVIEW_EXPLORER = NAME_PREFIX + "enterprise_explorer.gif"; //$NON-NLS-1$

	// console
	public static final String IMG_CLEAR = NAME_PREFIX + "clear_co.gif"; //$NON-NLS-1$

	public static final String IMG_WIZBAN = NAME_PREFIX + "new_wiz.gif"; //$NON-NLS-1$

	public static final String IMG_LOCAL_REPOSITORY = NAME_PREFIX + "local_repository.gif"; //$NON-NLS-1$

	private static final String T_OBJ = "obj16"; //$NON-NLS-1$

	private static final String T_WIZBAN = "wizban"; //$NON-NLS-1$

	private static final String T_CLCL = "clcl16"; //$NON-NLS-1$

	private static final String T_DLCL = "dlcl16"; //$NON-NLS-1$

	private static final String T_ELCL = "elcl16"; //$NON-NLS-1$

	private static final String T_CVIEW = "cview16"; //$NON-NLS-1$

	public static final ImageDescriptor DESC_OBJ_SERVER_STARTED = createManaged(
			T_OBJ, IMG_OBJ_SERVER_STARTED);

	public static final ImageDescriptor DESC_OBJ_SERVER_STOPPED = createManaged(
			T_OBJ, IMG_OBJ_SERVER_STOPPED);

	public static final ImageDescriptor DESC_OBJ_DEFAULT_SERVER_STARTED = createManaged(
			T_OBJ, IMG_OBJ_DEFAULT_SERVER_STARTED);

	public static final ImageDescriptor DESC_OBJ_DEFAULT_SERVER_STOPPED = createManaged(
			T_OBJ, IMG_OBJ_DEFAULT_SERVER_STOPPED);

	public static final ImageDescriptor DESC_LOCAL_REPOSITORY = createManaged(T_CLCL,
			IMG_LOCAL_REPOSITORY);

	public static final ImageDescriptor DESC_ADD_SERVER = createManaged(T_CLCL,
			IMG_ADD_SERVER);

	public static final ImageDescriptor DESC_REMOVE_SERVER = createManaged(
			T_CLCL, IMG_REMOVE_SERVER);

	public static final ImageDescriptor DESC_OBJ_CHECK = createManaged(T_OBJ,
			IMG_OBJ_CHECK);

	public static final ImageDescriptor DESC_OBJ_UNCHECK = createManaged(T_OBJ,
			IMG_OBJ_UNCHECK);

	public static final ImageDescriptor DESC_CVIEW_EXPLORER = createManaged(T_CVIEW,
			IMG_CVIEW_EXPLORER);

	// console
	public static final ImageDescriptor DESC_CLEAR = createManaged(T_CLCL,
			IMG_CLEAR);

	public static final ImageDescriptor DESC_CLEAR_DISABLED = createManaged(
			T_DLCL, IMG_CLEAR);

	public static final ImageDescriptor DESC_CLEAR_ENABLED = createManaged(
			T_ELCL, IMG_CLEAR);

	// wizard banners
	public static final ImageDescriptor DESC_WIZBAN = createManaged(T_WIZBAN,
			IMG_WIZBAN);

	/**
	 * Retrieves image that corresponds to a given identifier.
	 * 
	 * @param key Identifier of the image
	 * @return Image that corresponds to the supplied identifier.
	 */
	public static Image get(String key) {
		return IMAGE_REGISTRY.get(key);
	}

	/**
	 * Returns image registry.
	 * 
	 * @return Image registry object.
	 */
	public static ImageRegistry getImageRegistry() {
		return IMAGE_REGISTRY;
	}

	/**
	 * Creates descriptor of the image and adds it to the registry cache.
	 * 
	 * @param id Identifier of the image
	 * @return Descriptor of the image
	 */
	private static ImageDescriptor createManaged(String prefix, String id) {
		String name = id.substring(NAME_PREFIX.length());

		try {
			ImageDescriptor result = ImageDescriptor
					.createFromURL(createIconFileURL(prefix, name));

			IMAGE_REGISTRY.put(id, result);

			return result;
		}
		catch (MalformedURLException e) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

	/**
	 * Creates a URL for the icon with the given file name.
	 * 
	 * @param name File name of the icon
	 * @return URL that encapulates path to the icon
	 */
	private static URL createIconFileURL(String prefix, String name)
			throws MalformedURLException {
		if (_iconBaseURL == null) {
			throw new MalformedURLException();
		}

		StringBuffer buffer = new StringBuffer(prefix);
		buffer.append('/');
		buffer.append(name);

		return new URL(_iconBaseURL, buffer.toString());
	}
}