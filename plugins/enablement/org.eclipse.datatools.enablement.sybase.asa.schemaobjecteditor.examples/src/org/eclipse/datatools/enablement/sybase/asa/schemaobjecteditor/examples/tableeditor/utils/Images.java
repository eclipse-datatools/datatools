/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;


/**
 * Utility class to load images
 * 
 * @author Idull
 */
public class Images
{
    private static final String         NAME_PREFIX        = "com.sybase.stf.dmp.sybcomponents";
    private static final int            NAME_PREFIX_LENGTH = NAME_PREFIX.length();
    private static URL                  _baseURL           = null;
    // The plugin image registry
    private static ImageRegistry        fgImageRegistry    = null;
    private static HashMap              fgAvoidSWTErrorMap = new HashMap();
    static
    {
        String pathSuffix = "icons/";
        _baseURL = ExamplePlugin.getDefault().getBundle().getEntry(pathSuffix);
    }

    // Vertical
    public static final String          IMG_VERTICAL       = NAME_PREFIX + "th_vertical.gif";
    public static final ImageDescriptor DESC_VERTICAL      = createManaged(IMG_VERTICAL);

    // Horizontal
    public static final String          IMG_HORIZONTAL     = NAME_PREFIX + "th_horizontal.gif";
    public static final ImageDescriptor DESC_HORIZONTAL    = createManaged(IMG_HORIZONTAL);

    // Collapse All
    public static final String          IMG_COLLAPSE_ALL   = NAME_PREFIX + "collapseall.gif";
    public static final ImageDescriptor DESC_COLLAPSE_ALL  = createManaged(IMG_COLLAPSE_ALL);
    
    /**
     * Returns the image managed under the given key in this registry.
     * 
     * @param key the image's key
     * @return the image managed under the given key
     */
    public static Image get(String key)
    {
        return getImageRegistry().get(key);
    }

    /**
     * Helper method to convert HashMap to ImageRegistry
     * 
     * @return the image registry
     */
    static ImageRegistry getImageRegistry()
    {
        if (fgImageRegistry == null)
        {
            fgImageRegistry = new ImageRegistry();
            for (Iterator iter = fgAvoidSWTErrorMap.keySet().iterator(); iter.hasNext();)
            {
                String key = (String) iter.next();
                fgImageRegistry.put(key, (ImageDescriptor) fgAvoidSWTErrorMap.get(key));
            }
            fgAvoidSWTErrorMap = null;
        }
        return fgImageRegistry;
    }

    private static ImageDescriptor createManaged(String name)
    {
        try
        {
            ImageDescriptor result = ImageDescriptor.createFromURL(makeIconFileURL(name.substring(NAME_PREFIX_LENGTH)));
            fgAvoidSWTErrorMap.put(name, result);
            return result;
        }
        catch (MalformedURLException e)
        {
            return ImageDescriptor.getMissingImageDescriptor();
        }

    }

    private static URL makeIconFileURL(String name) throws MalformedURLException
    {
        if (_baseURL == null)
        {
            throw new MalformedURLException();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(name);
        return new URL(_baseURL, buffer.toString());
    }
}
