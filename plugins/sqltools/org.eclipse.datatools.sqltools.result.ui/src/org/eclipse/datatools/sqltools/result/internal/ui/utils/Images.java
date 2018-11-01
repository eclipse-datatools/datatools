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
package org.eclipse.datatools.sqltools.result.internal.ui.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
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
    private static ILogger              _log                         = ResultsViewUIPlugin.getLogger(null);
    private static final String         NAME_PREFIX                  = "org.eclipse.datatools.sqltools.result";
    private static final int            NAME_PREFIX_LENGTH           = NAME_PREFIX.length();
    private static URL                  _baseURL                     = null;
    // The plugin image registry
    private static ImageRegistry        fgImageRegistry              = null;
    private static HashMap              fgAvoidSWTErrorMap           = new HashMap();

    static
    {
        String pathSuffix = "icons/";
        _baseURL = ResultsViewUIPlugin.getDefault().getBundle().getEntry(pathSuffix);
    }

    // Remove all
    public static final String          IMG_REMOVEALL   = NAME_PREFIX + "removeall.gif";
    public static final ImageDescriptor DESC_REMOVEALL  = createManaged(IMG_REMOVEALL);
    
    public static final String          IMG_REMOVEALL_DISABLE   = NAME_PREFIX + "removeall_disable.gif";
    public static final ImageDescriptor DESC_REMOVEALL_DISABLE  = createManaged(IMG_REMOVEALL_DISABLE);
    
    // Remove
    public static final String          IMG_REMOVE      = NAME_PREFIX + "remove.gif";
    public static final ImageDescriptor DESC_REMOVE     = createManaged(IMG_REMOVE);
    
    public static final String          IMG_REMOVE_DISABLE      = NAME_PREFIX + "remove_disable.gif";
    public static final ImageDescriptor DESC_REMOVE_DISABLE     = createManaged(IMG_REMOVE_DISABLE);

    // Success
    public static final String          IMG_SUCCESS     = NAME_PREFIX + "success.gif";
    public static final ImageDescriptor DESC_SUCCESS    = createManaged(IMG_SUCCESS);

    // Warning
    public static final String          IMG_WARNING     = NAME_PREFIX + "warning.gif";
    public static final ImageDescriptor DESC_WARNING    = createManaged(IMG_WARNING);

    // Fail
    public static final String          IMG_FAIL        = NAME_PREFIX + "fail.gif";
    public static final ImageDescriptor DESC_FAIL       = createManaged(IMG_FAIL);

    // Critical error
    public static final String          IMG_CRITICAL    = NAME_PREFIX + "critical.gif";
    public static final ImageDescriptor DESC_CRITICAL   = createManaged(IMG_CRITICAL);

    // Terminate
    public static final String          IMG_TERMINATE   = NAME_PREFIX + "terminate.gif";
    public static final ImageDescriptor DESC_TERMINATE  = createManaged(IMG_TERMINATE);
    
    public static final String          IMG_TERMINATE_DISABLE   = NAME_PREFIX + "terminate_disable.gif";
    public static final ImageDescriptor DESC_TERMINATE_DISABLE  = createManaged(IMG_TERMINATE_DISABLE);

    // Running
    public static final String          IMG_RUNNING     = NAME_PREFIX + "running.gif";
    public static final ImageDescriptor DESC_RUNNING    = createManaged(IMG_RUNNING);

    // Started
    public static final String          IMG_STARTED     = NAME_PREFIX + "started.gif";
    public static final ImageDescriptor DESC_STARTED    = createManaged(IMG_STARTED);

    // Results view warning
    public static final String          IMG_RESULT_VIEW_WARN  = NAME_PREFIX + "warning_st_obj.gif";
    public static final ImageDescriptor DESC_RESULT_VIEW_WARN = createManaged(IMG_RESULT_VIEW_WARN);

    // Horizontal results view
    public static final String          IMG_HORIZONTAL_RESULTS_VIEW  = NAME_PREFIX + "horizontal_results_view.gif";
    public static final ImageDescriptor DESC_HORIZONTAL_RESULTS_VIEW = createManaged(IMG_HORIZONTAL_RESULTS_VIEW);
    
    // Vertical results view
    public static final String          IMG_VERTICAL_RESULTS_VIEW    = NAME_PREFIX + "vertical_results_view.gif";
    public static final ImageDescriptor DESC_VERTICAL_RESULTS_VIEW   = createManaged(IMG_VERTICAL_RESULTS_VIEW);
    
    // Export icon
    public static final String          IMG_EXPORT_RESULT            = NAME_PREFIX + "export_result.gif";
    public static final ImageDescriptor DESC_EXPORT_RESULT           = createManaged(IMG_EXPORT_RESULT);
    
    // Results view filter
    public static final String          IMG_RESULT_VIEW_FILTER       = NAME_PREFIX + "result_view_filter.gif";
    public static final ImageDescriptor DESC_RESULT_VIEW_FILTER      = createManaged(IMG_RESULT_VIEW_FILTER);

    // Single tab mode
    public static final String          IMG_SINGLE_TAB               = NAME_PREFIX + "single_tab.gif";
    public static final ImageDescriptor DESC_SINGLE_TAB              = createManaged(IMG_SINGLE_TAB);

    // Text mode
    public static final String          IMG_TEXT_MODE                = NAME_PREFIX + "text_mode.gif";
    public static final ImageDescriptor DESC_TEXT_MODE               = createManaged(IMG_TEXT_MODE);
    
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
            _log.error("Debugger_Images_malformedURLException", name, e);
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
