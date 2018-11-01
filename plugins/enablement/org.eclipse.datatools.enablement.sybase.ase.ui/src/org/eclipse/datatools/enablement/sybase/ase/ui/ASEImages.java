/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ase.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @author Li Huang
 * 
 */
public class ASEImages
{
    private static final String         NAME_PREFIX          = "org.eclipse.datatools.enablement.sybase.ase.ui";
    private static final int            NAME_PREFIX_LENGTH   = NAME_PREFIX.length();
    private static URL                  baseURL             = null;

    // The plugin registry
    private static ImageRegistry        imageRegistry       = null;
    private static HashMap              avoidSWTErrorMap    = new HashMap();

    static
    {
        String pathSuffix = "icons/";
        baseURL = ASEUIPlugin.getDefault().getBundle().getEntry(pathSuffix);
    }

    public static final String          IMG_WEBSERVICETABLE  = NAME_PREFIX + "webserviceastable.gif";
    public static final String          IMG_DIRPROXYTABLE  = NAME_PREFIX + "dirproxytable.gif";
    public static final String          IMG_FILEPROXYTABLE  = NAME_PREFIX + "fileproxytable.gif";
    public static final String          IMG_REMOTETABLE  = NAME_PREFIX + "remotetable.gif";
    public static final String          IMG_RPCPROXYTABLE  = NAME_PREFIX + "rpcproxytable.gif";
    public static final String          IMG_DISABLEDTRIGGER  = NAME_PREFIX + "disabled_trigger.gif";
    public static final String          IMG_PROXYDATABASE  = NAME_PREFIX + "proxy_database.gif";
    public static final ImageDescriptor DESC_WEBSERVICETABLE = createManaged(null, IMG_WEBSERVICETABLE);
    public static final ImageDescriptor DESC_DIRPROXYTABLE = createManaged(null, IMG_DIRPROXYTABLE);
    public static final ImageDescriptor DESC_FILEPROXYTABLE = createManaged(null, IMG_FILEPROXYTABLE);
    public static final ImageDescriptor DESC_REMOTETABLE = createManaged(null, IMG_REMOTETABLE);
    public static final ImageDescriptor DESC_RPCPROXYTABLE = createManaged(null, IMG_RPCPROXYTABLE);
    public static final ImageDescriptor DESC_DISABLEDTRIGGER = createManaged(null, IMG_DISABLEDTRIGGER);
    public static final ImageDescriptor DESC_PROXYDATABASE = createManaged(null, IMG_PROXYDATABASE);

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

    /*
     * Helper method to access the image registry from the JDIDebugUIPlugin class.
     */
    static ImageRegistry getImageRegistry()
    {
        if (imageRegistry == null)
        {
            imageRegistry = new ImageRegistry();
            for (Iterator iter = avoidSWTErrorMap.keySet().iterator(); iter.hasNext();)
            {
                String key = (String) iter.next();
                imageRegistry.put(key, (ImageDescriptor) avoidSWTErrorMap.get(key));
            }
            avoidSWTErrorMap = null;
        }
        return imageRegistry;
    }

    private static ImageDescriptor createManaged(String prefix, String name)
    {
        try
        {
            ImageDescriptor result = ImageDescriptor.createFromURL(makeIconFileURL(prefix, name
                    .substring(NAME_PREFIX_LENGTH)));

            avoidSWTErrorMap.put(name, result);
            if (imageRegistry != null)
            {
                // _logger.error("RepServerImages.registryError"); //$NON-NLS-1$
            }
            return result;
        }
        catch (MalformedURLException e)
        {
            // _logger.error("RepServerImages.malformedURLException", name, e);
            return ImageDescriptor.getMissingImageDescriptor();
        }

    }

    private static URL makeIconFileURL(String prefix, String name) throws MalformedURLException
    {
        if (baseURL == null)
        {
            throw new MalformedURLException();
        }
        StringBuffer buffer = new StringBuffer();
        if (prefix != null)
        {
            buffer = new StringBuffer(prefix);
            buffer.append('/');
        }
        buffer.append(name);
        return new URL(baseURL, buffer.toString());
    }
}
