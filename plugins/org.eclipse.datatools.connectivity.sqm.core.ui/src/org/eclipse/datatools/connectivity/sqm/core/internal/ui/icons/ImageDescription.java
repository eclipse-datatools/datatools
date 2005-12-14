/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.icons;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ImagePath;
import org.eclipse.jface.resource.ImageDescriptor;


/**
 * @author ljulien
 */
public class ImageDescription
{
    /**
     * @param urlPath the path to the picture
     * @return the descriptor for this url
     */
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
    
    public static ImageDescriptor getBookmarkDescriptor ()
    {
        return getDescriptor (ImagePath.BOOKMARK);
    }
    
    public static ImageDescriptor getFKDecorationDescriptor ()
    {
        return getDescriptor (ImagePath.FK_DECORATION);
    }
    
    public static ImageDescriptor getPKFKDecorationDescriptor ()
    {
        return getDescriptor (ImagePath.PKFK_DECORATION);
    }
    
    public static ImageDescriptor getPKDecorationDescriptor ()
    {
        return getDescriptor (ImagePath.PK_DECORATION);
    }
    
    public static ImageDescriptor getNullableColumnDescriptor ()
    {
        return getDescriptor (ImagePath.NULL_COLUMN_DECORATION);
    }
    
    public static ImageDescriptor getSequenceDescriptor ()
    {
        return getDescriptor (ImagePath.SEQUENCE);
    }
    
    public static ImageDescriptor getGenerateDDLWizard ()
    {
        return getDescriptor (ImagePath.GENERATE_DDL_WIZARD);
    }
    
    public static ImageDescriptor getServerExplorer ()
    {
        return getDescriptor (ImagePath.SERVER_EXPLORER);
    }
}
