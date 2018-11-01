/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: balajik - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.util.Vector;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

/**
 * @author balajik
 * 
 * This class is used for overlaying image icons
 * 
 * This class was taken from the Eclipse article.
 */
public class OverlayImageIcon extends CompositeImageDescriptor {

	/**
	 * Base image of the object
	 */
	private Image baseImage_;

	/**
	 * Size of the base image
	 */
	private Point sizeOfImage_;

	/**
	 * Vector of image keys
	 */
	private Vector imageKey_;

	/**
	 * Demo Image instance
	 */
	private DriverImages driverImage_;

	private static final int TOP_LEFT = 0;
	private static final int TOP_RIGHT = 1;
	private static final int BOTTOM_LEFT = 2;
	private static final int BOTTOM_RIGHT = 3;

	/**
	 * Constructor for overlayImageIcon.
	 */
	public OverlayImageIcon(Image baseImage, DriverImages demoImage,
							Vector imageKey) {
		// Base image of the object
		this.baseImage_ = baseImage;
		// Demo Image Object
		this.driverImage_ = demoImage;
		this.imageKey_ = imageKey;
		this.sizeOfImage_ = new Point(baseImage.getBounds().width, baseImage
				.getBounds().height);
	}

	/**
	 * @see org.eclipse.jface.resource.CompositeImageDescriptor#drawCompositeImage(int,
	 *      int) DrawCompositeImage is called to draw the composite image.
	 * 
	 */
	protected void drawCompositeImage(int arg0, int arg1) {
		// Draw the base image
		drawImage(this.baseImage_.getImageData(), 0, 0);
		int[] locations = organizeImages();
		for (int i = 0; i < this.imageKey_.size(); i++) {
			ImageData imageData = this.driverImage_
					.getImageData((String) this.imageKey_.get(i));
			switch (locations[i]) {
			// Draw on the top left corner
			case TOP_LEFT:
				drawImage(imageData, 0, 0);
				break;

			// Draw on top right corner
			case TOP_RIGHT:
				drawImage(imageData, this.sizeOfImage_.x - imageData.width, 0);
				break;

			// Draw on bottom left
			case BOTTOM_LEFT:
				drawImage(imageData, 0, this.sizeOfImage_.y - imageData.height);
				break;

			// Draw on bottom right corner
			case BOTTOM_RIGHT:
				drawImage(imageData, this.sizeOfImage_.x - imageData.width,
						this.sizeOfImage_.y - imageData.height);
				break;

			}
		}

	}

	/**
	 * Organize the images. This function scans through the image key and finds
	 * out the location of the images
	 */
	private int[] organizeImages() {
		int[] locations = new int[this.imageKey_.size()];
		String imageKeyValue;
		for (int i = 0; i < this.imageKey_.size(); i++) {
			imageKeyValue = (String) this.imageKey_.get(i);
			if (imageKeyValue.equals(DriverImages.ERROR_KEY)) {
				// Draw he lock icon in top left corner.
				locations[i] = TOP_LEFT;
			}
			if (imageKeyValue.equals(DriverImages.WARNING_KEY)) //$NON-NLS-1$
			{
				// Draw dirty flag indicator in the top right corner
				locations[i] = TOP_RIGHT;
			}
		}
		return locations;
	}

	/**
	 * @see org.eclipse.jface.resource.CompositeImageDescriptor#getSize() get
	 *      the size of the object
	 */
	protected Point getSize() {
		return this.sizeOfImage_;
	}

	/**
	 * Get the image formed by overlaying different images on the base image
	 * 
	 * @return composite image
	 */
	public Image getImage() {
		return createImage();
	}

}
