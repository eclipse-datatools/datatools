/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
	
	public static ImageDescriptor getRoleDescriptor ()
	{
		return getDescriptor (ImagePath.ROLE);
	}
	public static ImageDescriptor getUserDescriptor ()
	{
		return getDescriptor (ImagePath.USER);
	}
	public static ImageDescriptor getGroupDescriptor ()
	{
		return getDescriptor (ImagePath.GROUP);
	}
	
	public static ImageDescriptor getColumnDescriptor ()
	{
	    return getDescriptor (ImagePath.COLUMN);
	}
	
	public static ImageDescriptor getPKDescriptor ()
	{
	    return getDescriptor (ImagePath.PK_COLUMN);
	}
	
	public static ImageDescriptor getTriggerDescriptor ()
	{
	    return getDescriptor (ImagePath.TRIGGER);
	}
	
	public static ImageDescriptor getIndexDescriptor ()
	{
	    return getDescriptor (ImagePath.INDEX);
	}
	
	public static ImageDescriptor getTableDescriptor ()
	{
	    return getDescriptor (ImagePath.TABLE);
	}
	
	public static ImageDescriptor getViewDescriptor ()
	{
	    return getDescriptor (ImagePath.VIEW);
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
	
	public static ImageDescriptor getGenerateCodeDescriptor ()
	{
		return getDescriptor(ImagePath.GENERATE_CODE);
	}

	public static ImageDescriptor getSchemaDescriptor ()
	{
		return getDescriptor(ImagePath.SCHEMA);
	}

	public static ImageDescriptor getTableCheckConstraintDescriptor()
	{
		return getDescriptor (ImagePath.TABLECHECKCONSTRAINT);
	}

	public static ImageDescriptor getUDTDescriptor()
	{
		return getDescriptor (ImagePath.UDT);
	}

	public static ImageDescriptor getDatabaseDescriptor ()
	{
		return getDescriptor (ImagePath.DATABASE);
	}
	
    public static ImageDescriptor getSampleContent ()
    {
        return getDescriptor (ImagePath.SAMPLE_CONTENT);
    }
    
    public static ImageDescriptor getFilterDecorationDescriptor ()
    {
    	return getDescriptor (ImagePath.FILTER_DECORATION);
    }
    
    public static ImageDescriptor getFilterWizardDescriptor ()
    {
        return getDescriptor (ImagePath.FILTER_WIZARD);
    }
}
