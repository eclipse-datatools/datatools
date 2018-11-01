/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf & mdow - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.ftp.internal;

import org.apache.commons.net.ftp.FTPFile;
import org.eclipse.datatools.connectivity.ui.navigator.ConnectionProfileLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
 * @author mdow and brianf
 */
public class FtpLabelProvider extends ConnectionProfileLabelProvider  {

    /**
     * Constructor
     */
    public FtpLabelProvider() {
        super();
    }

	/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    public Image getImage(Object element) {
        Image image;
        if (element instanceof FTPFileObject) {
        		FTPFile file = ((FTPFileObject) element).getFTPFile();
                if (file.isDirectory()) {
                    image = WorkbenchPlugin.getDefault().getSharedImages()
                            .getImage(ISharedImages.IMG_OBJ_FOLDER);
                } else {
                    image = WorkbenchPlugin.getDefault().getSharedImages()
                            .getImage(ISharedImages.IMG_OBJ_FILE);
                }
        } else {
            image = super.getImage(element);
        }
        return image;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    public String getText(Object element) {
        String text;
        if (element instanceof FTPFileObject) {
      		FTPFile file = ((FTPFileObject) element).getFTPFile();
      		text = file.getName();
        } else {
            text = super.getText(element);
        }
        return text;
    }
}
