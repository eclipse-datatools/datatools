/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf & shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.ftp.internal;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sample.ftp.IFtpProfileConstants;
import org.eclipse.datatools.connectivity.ui.IContentExtension;
import org.eclipse.datatools.connectivity.ui.ManagedContentExtensionBase;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
 * @see IContentExtension
 */
public class FTPContentExtension extends ManagedContentExtensionBase {

	public FTPContentExtension(IConnectionProfile profile) {
		super(profile, IFtpProfileConstants.FTP_FACTORY_ID);
	}

	public Image getImage() {
		return WorkbenchPlugin.getDefault().getSharedImages().getImage(
				ISharedImages.IMG_OBJ_FILE);
	}

	public String getLabel() {
		return "FTP Content (Sample Extension)";
	}
	
	public boolean isVisible() {
		return true;
	}

}
