/*******************************************************************************
 * Copyright (c) 2006-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import java.net.URL;

import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.ProfileImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

public class ProfileImageRegistry {

	private static final String IMG_OBJ_SERVER_DEFAULT = "org.eclipse.datatools.connectivity.ui.server_default_obj.gif"; //$NON-NLS-1$

	private static final String IMG_DESC_SERVER_DEFAULT = "icons/full/obj16/server_obj.gif"; //$NON-NLS-1$

	private static ProfileImageRegistry sInstance = new ProfileImageRegistry();

	private ImageRegistry mImageRegistry;

	public static ProfileImageRegistry getInstance() {
		return sInstance;
	}

	public Image getProfileImage(IConnectionProfileProvider provider) {
		Image image = mImageRegistry.get(provider.getId());
		if (image == null) {
			ProfileImageDescriptor pids[] =
				ProfileImageDescriptor.getProfileImageDescriptorsForProfileID(provider.getId());
			if (pids != null && pids.length > 0) {
				URL iconURL = pids[0].getIconURL();
				if (iconURL != null) {
					ImageDescriptor imageDesc = ImageDescriptor
							.createFromURL(iconURL);
					mImageRegistry.put(provider.getId(), imageDesc);
					image = mImageRegistry.get(provider.getId());
				}
			}
			else {
				URL iconURL = provider.getIconURL();
				if (iconURL != null) {
					ImageDescriptor imageDesc = ImageDescriptor
							.createFromURL(iconURL);
					mImageRegistry.put(provider.getId(), imageDesc);
					image = mImageRegistry.get(provider.getId());
				}
			}
			if (image == null) {
				image = mImageRegistry.get(IMG_OBJ_SERVER_DEFAULT);
			}
		}
		return image;
	}

	private ProfileImageRegistry() {
		mImageRegistry = new ImageRegistry();
		mImageRegistry.put(IMG_OBJ_SERVER_DEFAULT, ImageDescriptor
				.createFromURL(ConnectivityUIPlugin.getDefault().getBundle()
						.getEntry(IMG_DESC_SERVER_DEFAULT)));
	}
}
