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
package org.eclipse.datatools.sqltools.plan.internal.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * Utility class to load images
 * 
 * @author Dafan Yang
 */
public class Images
{
    private static ILogger              _log                         = PlanViewPlugin.getLogger(null);
    private static final String         NAME_PREFIX                  = "org.eclipse.datatools.sqltools.result";
    private static final int            NAME_PREFIX_LENGTH           = NAME_PREFIX.length();
    private static URL                  _baseURL                     = null;
    // The plugin image registry
    private static ImageRegistry        fgImageRegistry              = null;
    private static HashMap              fgAvoidSWTErrorMap           = new HashMap();

    static
    {
        String pathSuffix = "icons/";
        _baseURL = PlanViewPlugin.getDefault().getBundle().getEntry(pathSuffix);
    }

    // Horizontal plan view
    public static final String          IMG_HORIZONTAL_PLAN_VIEW  = NAME_PREFIX + "horizontal_plan_view.gif";
    public static final ImageDescriptor DESC_HORIZONTAL_PLAN_VIEW = createManaged(IMG_HORIZONTAL_PLAN_VIEW);

    // Vertical plan view
    public static final String          IMG_VERTICAL_PLAN_VIEW    = NAME_PREFIX + "vertical_plan_view.gif";
    public static final ImageDescriptor DESC_VERTICAL_PLAN_VIEW   = createManaged(IMG_VERTICAL_PLAN_VIEW);
    
    // Current operator
    public static final String          IMG_CURRENT_OPERATOR      = NAME_PREFIX + "current_operator.gif";
    public static final ImageDescriptor DESC_CURRENT_OPERATOR     = createManaged(IMG_CURRENT_OPERATOR);

    // Plan history
    public static final String          IMG_SHOWPLAN              = NAME_PREFIX + "showplan.gif";
    public static final ImageDescriptor DESC_SHOWPLAN             = createManaged(IMG_SHOWPLAN);

    // Remove all plans -- disable
    public static final String          IMG_REMOVEALL_DISABLE     = NAME_PREFIX + "removeall_disable.gif";
    public static final ImageDescriptor DESC_REMOVEALL_DISABLE    = createManaged(IMG_REMOVEALL_DISABLE);

    // Remove all plans
    public static final String          IMG_REMOVEALL             = NAME_PREFIX + "removeall.gif";
    public static final ImageDescriptor DESC_REMOVEALL            = createManaged(IMG_REMOVEALL);

    // Remove plan -- disable
    public static final String          IMG_REMOVE_DISABLE        = NAME_PREFIX + "remove_disable.gif";
    public static final ImageDescriptor DESC_REMOVE_DISABLE       = createManaged(IMG_REMOVE_DISABLE);

    // Remove plan
    public static final String          IMG_REMOVE                = NAME_PREFIX + "remove.gif";
    public static final ImageDescriptor DESC_REMOVE               = createManaged(IMG_REMOVE);

    // Text plan
    public static final String          IMG_TEXT_PLAN             = NAME_PREFIX + "text_plan.gif";
    public static final ImageDescriptor DESC_TEXT_PLAN            = createManaged(IMG_TEXT_PLAN);

    // Graphic plan
    public static final String          IMG_GRAPHIC_PLAN          = NAME_PREFIX + "graphic_plan.gif";
    public static final ImageDescriptor DESC_GRAPHIC_PLAN         = createManaged(IMG_GRAPHIC_PLAN);
    
    // Plan failed
    public static final String          IMG_FAILED_PLAN           = NAME_PREFIX + "failed_plan.gif";
    public static final ImageDescriptor DESC_FAILED_PLAN          = createManaged(IMG_FAILED_PLAN);
    
    // Export plan
    public static final String          IMG_EXPORT_PLAN           = NAME_PREFIX + "export_plan.gif";
    public static final ImageDescriptor DESC_EXPORT_PLAN          = createManaged(IMG_EXPORT_PLAN);
    
    // Export plan (disable)
    public static final String          IMG_EXPORT_PLAN_DISABLE   = NAME_PREFIX + "export_plan_disable.gif";
    public static final ImageDescriptor DESC_EXPORT_PLAN_DISABLE  = createManaged(IMG_EXPORT_PLAN_DISABLE);

    // Import plan
    public static final String          IMG_IMPORT_PLAN           = NAME_PREFIX + "import_plan.gif";
    public static final ImageDescriptor DESC_IMPORT_PLAN          = createManaged(IMG_IMPORT_PLAN);

    // Import plan (disable)
    public static final String          IMG_IMPORT_PLAN_DISABLE   = NAME_PREFIX + "import_plan_disable.gif";
    public static final ImageDescriptor DESC_IMPORT_PLAN_DISABLE  = createManaged(IMG_IMPORT_PLAN_DISABLE);
    
    // Plan type switch
    public static final String          IMG_PLAN_TYPE             = NAME_PREFIX + "plan_type_switch.gif";
    public static final ImageDescriptor DESC_PLAN_TYPE            = createManaged(IMG_PLAN_TYPE);
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
            _log.error("Debugger.Images.malformedURLException", name, e);
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
