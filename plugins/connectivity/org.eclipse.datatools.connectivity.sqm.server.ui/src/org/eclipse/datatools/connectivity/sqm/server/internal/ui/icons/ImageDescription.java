/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.icons;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ImagePath;
import org.eclipse.jface.resource.ImageDescriptor;


/**
 * @author ljulien
 */
public class ImageDescription
{
	private static ImageDescriptor getDescriptor (String urlPath)
	{
		try
        {
            return ImageDescriptor.createFromURL(new URL(ImagePath.CORE_UI_ICONS_FOLDER_URL + urlPath));
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return null;
        }
	}
	public static ImageDescriptor getFilterDecorationDescriptor ()
	{
	    return getDescriptor (ImagePath.FILTER_DECORATION);
	}
}
