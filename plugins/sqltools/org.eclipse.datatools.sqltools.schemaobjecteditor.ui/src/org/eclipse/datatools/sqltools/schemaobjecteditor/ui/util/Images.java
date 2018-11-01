/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
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
    private static final String         NAME_PREFIX                  = "org.eclipse.datatools.sqltools.schemaobjecteditor.pages";
    private static final int            NAME_PREFIX_LENGTH           = NAME_PREFIX.length();
    private static URL                  _baseURL                     = null;
    // The plugin image registry
    private static ImageRegistry        fgImageRegistry              = null;
    private static HashMap              fgAvoidSWTErrorMap           = new HashMap();
    static
    {
        String pathSuffix = "icons/";
        _baseURL = SOEUIPlugin.getDefault().getBundle().getEntry(pathSuffix);
    }

    // Help
    public static final String          IMG_HELP   = NAME_PREFIX + "help.gif";
    public static final ImageDescriptor DESC_HELP  = createManaged(IMG_HELP);

    // Save as wizard
    public static final String          IMG_SAVEAS   = NAME_PREFIX + "saveas_wiz.png";
    public static final ImageDescriptor DESC_SAVEAS  = createManaged(IMG_SAVEAS);

    // Refresh
    public static final String          IMG_REFRESH        = NAME_PREFIX + "refresh_from_server.gif";
    public static final ImageDescriptor DESC_REFRESH       = createManaged(IMG_REFRESH);

    // Revert
    public static final String          IMG_REVERT         = NAME_PREFIX + "revert_editor.gif";
    public static final ImageDescriptor DESC_REVERT        = createManaged(IMG_REVERT);
    
    // Save to database
    public static final String          IMG_SAVE_TO_DB     = NAME_PREFIX + "save_to_database.gif";
    public static final ImageDescriptor DESC_SAVE_TO_DB    = createManaged(IMG_SAVE_TO_DB);
    
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
    public static ImageRegistry getImageRegistry()
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
        throw new MalformedURLException();
        StringBuffer buffer = new StringBuffer();
        buffer.append(name);
        return new URL(_baseURL, buffer.toString());
    }
}
