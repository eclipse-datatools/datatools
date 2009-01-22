/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.cp;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ui.IContentExtension;
import org.eclipse.datatools.connectivity.ui.ManagedContentExtensionBase;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
 * @see IContentExtension
 */
public class DirectoryContentExtension extends ManagedContentExtensionBase {

	public DirectoryContentExtension(IConnectionProfile profile) {
		super(profile, IFileProfileConstants.FILE_FACTORY_ID);
	}

	public Image getImage() {
		return WorkbenchPlugin.getDefault().getSharedImages().getImage(
				ISharedImages.IMG_OBJ_FILE);
	}

	public String getLabel() {
		return "Directory Content (Sample Extension)";
	}
	
	public boolean isVisible() {
		return true;
	}

}
