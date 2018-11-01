/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: sairhart - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

/**
 * @author sairhart
 * 
 */
public class CCOverLayIconUtility {

	private final static URL BASE_URL = ConnectivityUIPlugin.getDefault()
			.getBundle().getEntry("/"); //$NON-NLS-1$

	public static final ImageDescriptor RUNNING = create(
			"icons/full/ovr16/", "obj_ovr_server.gif"); //$NON-NLS-1$ //$NON-NLS-2$
	public static final ImageDescriptor OFFLINE = create(
			"icons/full/ovr16/", "obj_ovr_server_off.gif"); //$NON-NLS-1$ //$NON-NLS-2$

	private static CCOverLayIconUtility _Default;

	private CCOverLayIconUtility() {
		super();
	}

	public static CCOverLayIconUtility getDefault() {
		if (_Default == null) {
			_Default = new CCOverLayIconUtility();
		}

		return _Default;
	}

	public Image addOverlayIcon(Image baseImage, ImageDescriptor overlay) {
		InternalOverlayIcon icon;
		if (overlay == RUNNING) {
			icon = new InternalOverlayIcon(baseImage, RUNNING, true, false);
		}
		else if (overlay == OFFLINE) {
			icon = new InternalOverlayIcon(baseImage, OFFLINE, true, false);
		}
		else {
			icon = new InternalOverlayIcon(baseImage, null, true, true);
		}
		return icon.createImage();
	}

	private static ImageDescriptor create(String prefix, String name) {
		return ImageDescriptor.createFromURL(makeImageURL(prefix, name));
	}

	private static URL makeImageURL(String prefix, String name) {
		String path = prefix + name;
		URL url = null;
		try {
			url = new URL(BASE_URL, path);
		}
		catch (MalformedURLException e) {
			return null;
		}
		return url;
	}

	private class InternalOverlayIcon extends CompositeImageDescriptor {

		private ImageDescriptor m_Overlay;
		private Image m_image;
		private boolean m_left;
		private boolean m_top;

		public InternalOverlayIcon(Image baseImage, ImageDescriptor overlay,
									boolean left, boolean top) {
			m_image = baseImage;
			m_Overlay = overlay;
			m_left = left;
			m_top = top;
		}

		protected void drawCompositeImage(int width, int height) {
			drawImage(m_image.getImageData(), 0, 0);
			if (m_Overlay != null) {
				ImageData id = m_Overlay.getImageData();
				int ox, oy;
				if (m_left)
					ox = 0;
				else
					ox = width - id.width;
				if (m_top)
					oy = 0;
				else
					oy = height - id.height;
				if (id != null) {
					drawImage(id, ox, oy);
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.resource.CompositeImageDescriptor#getSize()
		 */
		protected Point getSize() {
			return new Point(16, 16);
		}
	}
}