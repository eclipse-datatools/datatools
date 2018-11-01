/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;


/**
 * @author renj
 * 
 */
public class SybaseImages
{
    private static final String         NAME_PREFIX          = "org.eclipse.datatools.enablement.sybase";
    private static final int            NAME_PREFIX_LENGTH   = NAME_PREFIX.length();
    private static URL                  baseURL              = null;

    // The plugin registry
    private static ImageRegistry        imageRegistry        = null;
    private static HashMap              avoidSWTErrorMap     = new HashMap();

    static
    {
        String pathSuffix = "icons/";
        baseURL = ExamplePlugin.getDefault().getBundle().getEntry(pathSuffix);
    }

    public static final String          IMG_SYSTEMPROCEDURE  = NAME_PREFIX + "system_procedure.gif";
    public static final ImageDescriptor DESC_SYSTEMPROCEDURE = createManaged(null, IMG_SYSTEMPROCEDURE);
    public static final String          IMG_SYSTEMTABLE      = NAME_PREFIX + "system_table.gif";
    public static final ImageDescriptor DESC_SYSTEMTABLE     = createManaged(null, IMG_SYSTEMTABLE);
    public static final String          IMG_SYSTEMVIEW       = NAME_PREFIX + "system_view.gif";
    public static final ImageDescriptor DESC_SYSTEMVIEW      = createManaged(null, IMG_SYSTEMVIEW);
    
    // Folder
    public static final String          IMG_FOLDER           = NAME_PREFIX + "folder.gif";
    public static final ImageDescriptor DESC_FOLDER          = createManaged(null, IMG_FOLDER);

    // Primary key
    public static final String          IMG_PK               = NAME_PREFIX + "primaryKey.gif";
    public static final ImageDescriptor DESC_PK              = createManaged(null, IMG_PK);

    // Foreign key
    public static final String          IMG_FK               = NAME_PREFIX + "foreignKey.gif";
    public static final ImageDescriptor DESC_FK              = createManaged(null, IMG_FK);

    // Unqiue constraint
    public static final String          IMG_UNIQUE           = NAME_PREFIX + "unique_constraint.gif";
    public static final ImageDescriptor DESC_UNIQUE          = createManaged(null, IMG_UNIQUE);

    // Check constraint
    public static final String          IMG_CK               = NAME_PREFIX + "check_constraint.gif";
    public static final ImageDescriptor DESC_CK              = createManaged(null, IMG_CK);

    // User
    public static final String          IMG_USER             = NAME_PREFIX + "user.gif";
    public static final ImageDescriptor DESC_USER            = createManaged(null, IMG_USER);

    // Group
    public static final String          IMG_GROUP            = NAME_PREFIX + "group.gif";
    public static final ImageDescriptor DESC_GROUP           = createManaged(null, IMG_GROUP);
    
    // Role
    public static final String          IMG_ROLE             = NAME_PREFIX + "role.gif";
    public static final ImageDescriptor DESC_ROLE            = createManaged(null, IMG_ROLE);
    
    // Role
    public static final String          IMG_TABLE_SCHEMA     = NAME_PREFIX + "table.gif";
    public static final ImageDescriptor DESC_TABLE_SCHEMA    = createManaged(null, IMG_TABLE_SCHEMA);

    // Table
    public static final String          IMG_TABLE_DATA       = NAME_PREFIX + "table_data.gif";
    public static final ImageDescriptor DESC_TABLE_DATA      = createManaged(null, IMG_TABLE_DATA);
    
    // UDT
    public static final String          IMG_UDT       = NAME_PREFIX + "userdefined_datatype.gif";
    public static final ImageDescriptor DESC_UDT       = createManaged(null, IMG_UDT);    

    // stored_procedure
    public static final String          IMG_STORED_PROCEDURE       = NAME_PREFIX + "stored_procedure.gif";
    public static final ImageDescriptor DESC_STORED_PROCEDURE       = createManaged(null, IMG_STORED_PROCEDURE);  
    
    // system stored_procedure
    public static final String          IMG_SYSTEM_STORED_PROCEDURE       = NAME_PREFIX + "system_procedure.gif";
    public static final ImageDescriptor DESC_SYSTEM_STORED_PROCEDURE       = createManaged(null, IMG_SYSTEM_STORED_PROCEDURE); 
    
    // trigger
    public static final String          IMG_TRIGGER       = NAME_PREFIX + "trigger.gif";
    public static final ImageDescriptor DESC_TRIGGER       = createManaged(null, IMG_TRIGGER);  
    
    // UDF
    public static final String          IMG_UDF       = NAME_PREFIX + "udf.gif";
    public static final ImageDescriptor DESC_UDF       = createManaged(null, IMG_UDF);
    
    // TABLE
    public static final String          IMG_TABLE       = NAME_PREFIX + "table.gif";
    public static final ImageDescriptor DESC_TABLE       = createManaged(null, IMG_TABLE);   
    
    // EVENT
    public static final String          IMG_EVENT       = NAME_PREFIX + "event.gif";
    public static final ImageDescriptor DESC_EVENT       = createManaged(null, IMG_TABLE);
    
    // VIEW
    public static final String          IMG_VIEW       = NAME_PREFIX + "view.gif";
    public static final ImageDescriptor DESC_VIEW       = createManaged(null, IMG_VIEW); 
    
    //Column
    public static final String          IMG_COLUMN       = NAME_PREFIX + "column.gif";
    public static final ImageDescriptor DESC_COLUMN       = createManaged(null, IMG_COLUMN); 
    
    //Index
    public static final String          IMG_INDEX       = NAME_PREFIX + "index.gif";
    public static final ImageDescriptor DESC_INDEX       = createManaged(null, IMG_INDEX); 
    
    //System data type
    public static final String          IMG_SYSTEM_DATA_TYPE       = NAME_PREFIX + "system_datatype.gif";
    public static final ImageDescriptor DESC_SYSTEM_DATA_TYPE       = createManaged(null, IMG_SYSTEM_DATA_TYPE); 
    
    //routine parameter(in)
    public static final String          IMG_PARAM_IN       = NAME_PREFIX + "input_param_obj.gif";
    public static final ImageDescriptor DESC_PARAM_IN       = createManaged(null, IMG_PARAM_IN); 
    
    //routine parameter(out)
    public static final String          IMG_PARAM_OUT       = NAME_PREFIX + "output_param_obj.gif";
    public static final ImageDescriptor DESC_PARAM_OUT       = createManaged(null, IMG_PARAM_OUT); 
    
    //routine paramete
    public static final String          IMG_PARAM       = NAME_PREFIX + "param_obj.gif";
    public static final ImageDescriptor DESC_PARAM       = createManaged(null, IMG_PARAM);     
    
    //temporary table
    public static final String          IMG_TEMPORARY_TABLE       = NAME_PREFIX + "temporary_table.gif";
    public static final ImageDescriptor DESC_TEMPORARY_TABLE       = createManaged(null, IMG_TEMPORARY_TABLE);     
    
    //temporary database
    public static final String          IMG_TEMPORARY_DATABASE       = NAME_PREFIX + "temporary_database.gif";
    public static final ImageDescriptor DESC_TEMPORARY_DATABASE       = createManaged(null, IMG_TEMPORARY_DATABASE);  
    
    //system table
    public static final String          IMG_SYSTEM_TABLE       = NAME_PREFIX + "system_table.gif";
    public static final ImageDescriptor DESC_SYSTEM_TABLE       = createManaged(null, IMG_SYSTEM_TABLE); 
    
    //proxy database
    public static final String          IMG_PROXY_DATABASE       = NAME_PREFIX + "proxy_database.gif";
    public static final ImageDescriptor DESC_PROXY_DATABASE        = createManaged(null, IMG_PROXY_DATABASE);
    
    //database
    public static final String          IMG_DATABASE       = NAME_PREFIX + "database.gif";
    public static final ImageDescriptor DESC_DATABASE        = createManaged(null, IMG_DATABASE);
    
    //segment
    public static final String          IMG_SEGMENT       = NAME_PREFIX + "segment.gif";
    public static final ImageDescriptor DESC_SEGMENT        = createManaged(null, IMG_SEGMENT);
    
   //computed column
    public static final String          IMG_COMPUTED_COLUMN       = NAME_PREFIX + "computedColumn.gif";
    public static final ImageDescriptor DESC_COMPUTED_COLUMN        = createManaged(null, IMG_COMPUTED_COLUMN);
    
    // Instead of Trigger
    public static final String          IMG_INSTEAD_OF_TRIGGER       = NAME_PREFIX + "instead_of_trigger.gif";
    public static final ImageDescriptor DESC_INSTEAD_OF_TRIGGER      = createManaged(null, IMG_INSTEAD_OF_TRIGGER);
    
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
