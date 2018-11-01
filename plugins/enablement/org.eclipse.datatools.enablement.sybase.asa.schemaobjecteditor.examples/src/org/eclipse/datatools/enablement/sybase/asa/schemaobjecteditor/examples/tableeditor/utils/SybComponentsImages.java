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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;


public class SybComponentsImages
{
    private static final String                                        NAME_PREFIX                = "com.sybase.stf.dmp.sybcomponents";

    private static final int                                           NAME_PREFIX_LENGTH         = NAME_PREFIX
                                                                                                          .length();

    private static URL                                                 _baseURL                   = null;

    // The plugin registry
    private static ImageRegistry                                       fgImageRegistry            = null;

    private static HashMap                                             fgAvoidSWTErrorMap         = new HashMap();

    static
    {
        String pathSuffix = "icons/";
        _baseURL = ExamplePlugin.getDefault().getBundle().getEntry(pathSuffix);
    }

    private static final String                                        T_WIZARD                   = "wizard";
    
    public static final String                                         IMG_NEW_DATABASE            = NAME_PREFIX
                                                                                                           + "new_database.gif";

    public static final ImageDescriptor                                DESC_NEW_DATABASE           = createManaged(
                                                                                                           T_WIZARD,
                                                                                                           IMG_NEW_DATABASE);

    public static final String                                         IMG_NEW_TEMP_TABLE         = NAME_PREFIX
                                                                                                          + "new_temporary_table.gif";

    public static final ImageDescriptor                                DESC_NEW_TEMP_TABLE        = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_TEMP_TABLE);

    public static final String                                         IMG_NEW_TABLE              = NAME_PREFIX
                                                                                                          + "new_table.gif";

    public static final ImageDescriptor                                DESC_NEW_TABLE             = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_TABLE);

    public static final String                                         IMG_NEW_PROCEDURE          = NAME_PREFIX
                                                                                                          + "new_procedure.gif";

    public static final ImageDescriptor                                DESC_NEW_PROCEDURE         = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_PROCEDURE);

    public static final String                                         IMG_NEW_VIEW               = NAME_PREFIX
                                                                                                          + "new_view.gif";

    public static final ImageDescriptor                                DESC_NEW_VIEW              = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_VIEW);

    public static final String                                         IMG_NEW_INDEX              = NAME_PREFIX
                                                                                                          + "new_index.gif";

    public static final ImageDescriptor                                DESC_NEW_INDEX             = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_INDEX);

    public static final String                                         IMG_NEW_UDT                = NAME_PREFIX
                                                                                                          + "new_user_defined_datatype.gif";

    public static final ImageDescriptor                                DESC_NEW_UDT               = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_UDT);

    public static final String                                         IMG_NEW_DEFAULT            = NAME_PREFIX
                                                                                                          + "new_default.gif";

    public static final ImageDescriptor                                DESC_NEW_DEFAULT           = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_DEFAULT);

    public static final String                                         IMG_NEW_RULE               = NAME_PREFIX
                                                                                                          + "new_rule.gif";

    public static final ImageDescriptor                                DESC_NEW_RULE              = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_RULE);

    public static final String                                         IMG_NEW_WEB_SERVICE_TABLE  = NAME_PREFIX
                                                                                                          + "new_webservice_tables.gif";

    public static final ImageDescriptor                                DESC_NEW_WEB_SERVICE_TABLE = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_WEB_SERVICE_TABLE);

    public static final String                                         IMG_NEW_FUNCTION           = NAME_PREFIX
                                                                                                          + "new_function.gif";

    public static final ImageDescriptor                                DESC_NEW_FUNCTION          = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_FUNCTION);

    public static final String                                         IMG_NEW_EVENT              = NAME_PREFIX
                                                                                                          + "new_event.gif";

    public static final ImageDescriptor                                DESC_NEW_EVENT             = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_EVENT);

    public static final String                                         IMG_NEW_JOIN_INDEX         = NAME_PREFIX
                                                                                                          + "new_join_index.gif";

    public static final ImageDescriptor                                DESC_NEW_JOIN_INDEX        = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_JOIN_INDEX);
    
    public static final String                                         IMG_NEW_TRIGGER            = NAME_PREFIX
                                                                                                          + "new_trigger.gif";

    public static final ImageDescriptor                                DESC_NEW_TRIGGER           = createManaged(
                                                                                                          T_WIZARD,
                                                                                                          IMG_NEW_TRIGGER);

    public static final String                                         IMG_NEW_INSTEAD_OF_TRIGGER    = NAME_PREFIX
                                                                                                             + "new_instead_of_trigger.gif";

    public static final ImageDescriptor                                DESC_NEW_INSTEAD_OF_TRIGGER   = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_NEW_INSTEAD_OF_TRIGGER);


    public static final String                                         IMG_DDL_GENERATOR          = NAME_PREFIX
                                                                                                          + "ddl_generator.gif";

    public static final ImageDescriptor                                DESC_DDL_GENERATOR          = createManaged("",
                                                                                                           IMG_DDL_GENERATOR);

    public static final String                                         IMG_COLUMN_DELETE_DISABLED  = NAME_PREFIX
                                                                                                           + "column_delete_disabled.gif";

    public static final ImageDescriptor                                DESC_COLUMN_DELETE_DISABLED = createManaged("",
                                                                                                           IMG_COLUMN_DELETE_DISABLED);

    public static final String                                         IMG_COLUMN_ADD              = NAME_PREFIX
                                                                                                           + "column_add.gif";

    public static final ImageDescriptor                                DESC_COLUMN_ADD             = createManaged("",
                                                                                                           IMG_COLUMN_ADD);

    public static final String                                         IMG_COLUMN_ADD_DISABLED     = NAME_PREFIX
                                                                                                           + "column_add_disabled.gif";

    public static final ImageDescriptor                                DESC_COLUMN_ADD_DISABLED    = createManaged("",
                                                                                                           IMG_COLUMN_ADD_DISABLED);
    public static final String                                         IMG_COLUMN_DELETE           = NAME_PREFIX
                                                                                                           + "column_delete.gif";

    public static final ImageDescriptor                                DESC_COLUMN_DELETE          = createManaged("",
                                                                                                           IMG_COLUMN_DELETE);

    public static final String                                         IMG_DDLGEN_DATABASE         = NAME_PREFIX
                                                                                                           + "ddlgen_database.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_DATABASE          = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_DATABASE);

    public static final String                                         IMG_DDLGEN_TEMP_TABLE         = NAME_PREFIX
                                                                                                             + "ddlgen_temporary_table.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_TEMP_TABLE        = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_TEMP_TABLE);

    public static final String                                         IMG_DDLGEN_TABLE              = NAME_PREFIX
                                                                                                             + "ddlgen_table.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_TABLE             = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_TABLE);

    public static final String                                         IMG_DDLGEN_PROCEDURE          = NAME_PREFIX
                                                                                                             + "ddlgen_procedure.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_PROCEDURE         = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_PROCEDURE);

    public static final String                                         IMG_DDLGEN_VIEW               = NAME_PREFIX
                                                                                                             + "ddlgen_view.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_VIEW              = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_VIEW);

    public static final String                                         IMG_DDLGEN_INDEX              = NAME_PREFIX
                                                                                                             + "ddlgen_index.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_INDEX             = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_INDEX);

    public static final String                                         IMG_DDLGEN_UDT                = NAME_PREFIX
                                                                                                             + "ddlgen_user_defined_datatype.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_UDT               = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_UDT);

    public static final String                                         IMG_DDLGEN_DEFAULT            = NAME_PREFIX
                                                                                                             + "ddlgen_default.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_DEFAULT           = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_DEFAULT);

    public static final String                                         IMG_DDLGEN_RULE               = NAME_PREFIX
                                                                                                             + "ddlgen_rule.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_RULE              = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_RULE);

    public static final String                                         IMG_DDLGEN_WEB_SERVICE_TABLE  = NAME_PREFIX
                                                                                                             + "ddlgen_webservice_tables.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_WEB_SERVICE_TABLE = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_WEB_SERVICE_TABLE);

    public static final String                                         IMG_DDLGEN_FUNCTION           = NAME_PREFIX
                                                                                                             + "ddlgen_function.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_FUNCTION          = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_FUNCTION);

    public static final String                                         IMG_DDLGEN_EVENT              = NAME_PREFIX
                                                                                                             + "ddlgen_event.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_EVENT             = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_EVENT);

    public static final String                                         IMG_DDLGEN_JOIN_INDEX         = NAME_PREFIX
                                                                                                             + "ddlgen_join_index.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_JOIN_INDEX        = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_JOIN_INDEX);

    public static final String                                         IMG_DDLGEN_TRIGGER            = NAME_PREFIX
                                                                                                             + "ddlgen_trigger.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_TRIGGER           = createManaged(
                                                                                                             T_WIZARD,
                                                                                                             IMG_DDLGEN_TRIGGER);
    
    public static final String                                         IMG_DDLGEN_INSTEAD_OF_TRIGGER  = NAME_PREFIX
                                                                                                              + "ddlgen_iot.gif";

    public static final ImageDescriptor                                DESC_DDLGEN_INSTEAD_OF_TRIGGER = createManaged(
                                                                                                              T_WIZARD,
                                                                                                              IMG_DDLGEN_INSTEAD_OF_TRIGGER);

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
                // EditorCorePluginlog_ErrorMessage("Internal Error: Image
                // registry already defined"); //$NON-NLS-1$
            }
            return result;
        }
        catch (MalformedURLException e)
        {
        	ExamplePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, Messages.debugger_DmpImages_malformedURLException));
            return ImageDescriptor.getMissingImageDescriptor();
        }

    }

    private static URL makeIconFileURL(String prefix, String name) throws MalformedURLException
    {
        if (_baseURL == null)
            throw new MalformedURLException();
        StringBuffer buffer = new StringBuffer(prefix);
        if (buffer.length() != 0)
        {
            buffer.append('/');
        }
        buffer.append(name);
        return new URL(_baseURL, buffer.toString());
    }
}