/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.common.ui.util;

/**
 * DmpImages: This class defines all images (icons) handling code.
 * 
 * @author Samir Nigam
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

public class Images
{

    private static final String         NAME_PREFIX          = "org.eclipse.datatools.sqltools.common.ui";
    private static final int            NAME_PREFIX_LENGTH   = NAME_PREFIX.length();
    private static URL                  _baseURL             = null;

    // The plugin registry
    private static ImageRegistry        fgImageRegistry      = null;
    private static HashMap              fgAvoidSWTErrorMap   = new HashMap();

    static
    {
        String pathSuffix = "icons/";
        _baseURL = Activator.getDefault().getBundle().getEntry(pathSuffix);
    }
    // ------------------------------------------------------------------------------------------
    private static final String         T_OTHER              = "other";
    private static final String         PATH                 = "";    

    public static final String          IMG_OTHER_CHECKED    = NAME_PREFIX + "checked.gif";
    public static final String          IMG_OTHER_UNCHECKED  = NAME_PREFIX + "unchecked.gif";
    public static final String          IMG_SAVEAS           = NAME_PREFIX + "saveas_wiz.png";                //$NON-NLS-1$

    public static final ImageDescriptor DESC_OTHER_CHECKED   = createManaged(T_OTHER, IMG_OTHER_CHECKED);
    public static final ImageDescriptor DESC_OTHER_UNCHECKED = createManaged(T_OTHER, IMG_OTHER_UNCHECKED);
    public static final ImageDescriptor DESC_SAVEAS          = createManaged(T_OTHER, IMG_SAVEAS);

    public static final String          IMG_CHECKED        = NAME_PREFIX + "checked.gif";       //$NON-NLS-1$
    public static final String          IMG_UNCHECKED      = NAME_PREFIX + "unchecked.gif";     //$NON-NLS-1$
    public static final String          IMG_CHECKED_READONLY   = NAME_PREFIX + "checked_readonly.gif";  //$NON-NLS-1$
    public static final String          IMG_UNCHECKED_READONLY = NAME_PREFIX + "unchecked_readonly.gif"; //$NON-NLS-1$

    public static final ImageDescriptor DESC_CHECKED       = createManaged(PATH, IMG_CHECKED);
    public static final ImageDescriptor DESC_UNCHECKED     = createManaged(PATH, IMG_UNCHECKED);
    public static final ImageDescriptor DESC_CHECKED_READONLY   = createManaged(PATH, IMG_CHECKED_READONLY);
    public static final ImageDescriptor DESC_UNCHECKED_READONLY = createManaged(PATH, IMG_UNCHECKED_READONLY);
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
    /* package */static ImageRegistry getImageRegistry()
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

    private static ImageDescriptor createManaged(String prefix, String name)
    {
        try
        {
            ImageDescriptor result = ImageDescriptor.createFromURL(makeIconFileURL(prefix, name
                    .substring(NAME_PREFIX_LENGTH)));

            fgAvoidSWTErrorMap.put(name, result);
            if (fgImageRegistry != null)
            {
                // DmptoolPlugin.log_ErrorMessage("Internal Error: Image registry already defined"); //$NON-NLS-1$
            }
            return result;
        }
        catch (MalformedURLException e)
        {
            Activator.getDefault().log(e);
            return ImageDescriptor.getMissingImageDescriptor();
        }

    }

    private static URL makeIconFileURL(String prefix, String name) throws MalformedURLException
    {
        if (_baseURL == null)
            throw new MalformedURLException();
        
        StringBuffer buffer;
        
        if("".equals(prefix) || prefix == null)
        {
        	buffer = new StringBuffer();
        }
        else
        {
        	buffer = new StringBuffer(prefix);
            buffer.append('/');
        }
        buffer.append(name);
        return new URL(_baseURL, buffer.toString());
    }
}