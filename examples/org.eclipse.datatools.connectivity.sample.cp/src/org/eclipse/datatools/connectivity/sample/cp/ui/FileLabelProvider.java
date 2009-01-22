/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.cp.ui;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
 * This represents an existing label provider used for working the object type
 * representing connections for this profile (java.io.File).
 * 
 * @author rcernich
 * 
 * Created on Mar 15, 2004
 */
public class FileLabelProvider extends LabelProvider {

	public FileLabelProvider() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		Image image;
		if (element instanceof File) {
			File file = (File) element;
			if (file.isDirectory()) {
				image = WorkbenchPlugin.getDefault().getSharedImages()
						.getImage(ISharedImages.IMG_OBJ_FOLDER);
			}
			else {
				image = WorkbenchPlugin.getDefault().getSharedImages()
						.getImage(ISharedImages.IMG_OBJ_FILE);
			}
		}
		else {
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
		if (element instanceof File) {
			text = ((File) element).getName();
		}
		else {
			text = super.getText(element);
		}
		return text;
	}
}
