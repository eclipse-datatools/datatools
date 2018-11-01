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

import java.util.Vector;

import org.eclipse.datatools.connectivity.drivers.DriverValidator;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.DriversProvider;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider and decorator for the driver tree
 * 
 * @author brianf
 */
public class DriverTreeLabelProvider extends LabelProvider implements
		ILabelDecorator {

	// Images
	private Image mNullImage = DriverImages.NULLIMAGE.createImage();
	private Image mDriverImage = DriverImages.DRIVER.createImage();
	private Image mCategoryImage = DriverImages.CATEGORY.createImage();
    
	// List of images
	private static DriverImages driverImage_ = new DriverImages();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		// no resources to dispose
		this.mNullImage.dispose();
		this.mDriverImage.dispose();
		this.mCategoryImage.dispose();
		this.mNullImage = null;
		this.mDriverImage = null;
		this.mCategoryImage = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		Image returnImage = null;
        if (element instanceof DriversProvider)
			returnImage = null;
		else if (element instanceof CategoryDescriptor) {
			returnImage = this.mCategoryImage;
		}
		else if (element instanceof TemplateDescriptor) {
			returnImage = this.mDriverImage;
		}
		else if (element instanceof IPropertySet) {
			returnImage = this.mDriverImage;
		}

		return returnImage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		if (element instanceof DriversProvider)
			return null;
		else if (element instanceof CategoryDescriptor) {
			return ((CategoryDescriptor) element).getName();
		}
		else if (element instanceof TemplateDescriptor) {
			return ((TemplateDescriptor) element).getName();
		}
		else if (element instanceof IPropertySet) {
			return ((IPropertySet) element).getName();
		}
		return ""; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image,
	 *      java.lang.Object)
	 */
	public Image decorateImage(Image image, Object element) {
		if (!(element instanceof IPropertySet))
			return null;

		IPropertySet instance = (IPropertySet) element;
		String driverType = instance.getBaseProperties().getProperty(
				IDriverMgmtConstants.PROP_DEFN_TYPE);
		TemplateDescriptor template = TemplateDescriptor
				.getDriverTemplateDescriptor(driverType);
		DriverValidator validator = new DriverValidator(template, instance);
		if (validator.isValid())
			return null;

		Vector decoratorImageKeys = new Vector();
		decoratorImageKeys.add(DriverImages.ERROR_KEY);

		if (decoratorImageKeys.size() != 0) {
			image = drawIconImage(image, decoratorImageKeys);
			return image;
		}
		// The resource need not be decorated
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ILabelDecorator#decorateText(java.lang.String,
	 *      java.lang.Object)
	 */
	public String decorateText(String text, Object element) {
		return null;
	}

	/**
	 * Function to draw icon image
	 * 
	 * @param baseImage base image of the object resource
	 * @param decoratorImageKeys vector of image keys
	 * 
	 * @return icon image with which the resource is to be decorated
	 */
	private Image drawIconImage(Image baseImage, Vector decoratorImageKeys) {
		Image image;
		OverlayImageIcon overlayIcon = new OverlayImageIcon(baseImage,
				driverImage_, decoratorImageKeys);
		image = overlayIcon.getImage();
		return image;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		// nothing
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
		// nothing
	}
}
