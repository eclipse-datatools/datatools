/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.RDBCoreUIPlugin;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

/**
 * @author ljulien
 */
public class ResourceLoader
{
	private static final RDBCoreUIPlugin plugin = RDBCoreUIPlugin.getDefault();
	private static final String RESOURCE_PATH = "org/eclipse/datatools/connectivity/sqm/core/internal/ui/l10n/"; //$NON-NLS-1$
	private static final String ICONS_DIRECTORY = "icons/"; //$NON-NLS-1$
	private static final String UI_RESOURCES = "datatoolsCoreUI"; //$NON-NLS-1$
	private static final String NO_RESOURCE_FOUND = "NO_RESOURCE_FOUND"; //$NON-NLS-1$
    private static final ResourceLoader instance = new ResourceLoader ();

	private ResourceBundle bundle = null;

	private static String iconLocation;
   
    /**
    * @return Will return the resource loader
    * -> The resource loader can be used to create images and get the String resources
    */
   	public static ResourceLoader getResourceLoader ()
   	{
   		return instance;
   	}
   
    private ResourceLoader()
    {
        try
        {
            Bundle bundle = RDBCoreUIPlugin.getDefault().getBundle();
            iconLocation = FileLocator.resolve(bundle.getEntry("/")).getPath() + ICONS_DIRECTORY; //$NON-NLS-1$
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        this.bundle = ResourceBundle.getBundle(RESOURCE_PATH + UI_RESOURCES);
    }
    
    private Image queryImage (String imagePath)
    {
		Image image = null;
		if ((image = plugin.getImageRegistry().get(imagePath)) == null)
		{
			image = new Image (Display.getDefault(), imagePath);
			plugin.getImageRegistry().put(imagePath, image);
		}
		return image;
    }
    
    /**
     * Client should use this query Image, as the registry will dispose the image
     * @param imagePath - The path should be related to the Class Loader
     * @return the image
     */
    public Image queryImageFromRegistry (String imagePath)
    {
        return queryAbsolutePathImageFromRegistry (iconLocation + imagePath);
    }
 
    /**
     * Image should not be disposed 
     * @param imagePath the full path to the image. Example: c:\...\myImage.gif
     * @return the image or null if not found
     */
    public Image queryAbsolutePathImageFromRegistry (String imagePath)
    {
        return queryImage (imagePath);
    }
    
    public Image queryAbsolutePathImageFromRegistry (Bundle bundle, String imagePath)
    {
		Image image = null;
		if ((image = plugin.getImageRegistry().get(imagePath)) == null)
		{
			try
			{
            	URL fullPathString = FileLocator.find(bundle, new Path(imagePath), null);
            	fullPathString = fullPathString != null ? fullPathString : new URL(imagePath);
            	if (fullPathString != null) 
            	{
            		image = ImageDescriptor.createFromURL(fullPathString).createImage();
            		plugin.getImageRegistry().put(imagePath, image);
            	}
			} 
			catch (IOException e)
			{
				return image;
			}
		}
		return image;
    }
    
    public static byte[] getImageData (EObject eObject)
    {
        if (eObject instanceof BaseTable)
        {
            return getImageData (ImagePath.TABLE);
        }
        else if (eObject instanceof ViewTable)
        {
            return getImageData (ImagePath.VIEW);
        }
        return new byte[100];
    }
    
    public static byte[] getImageData(String name)
    {
        String image = iconLocation + name;
        try
        {
            FileInputStream fis = new FileInputStream(image);
            int size;
            size = fis.available();
            byte[] imageBytes = new byte[size];
            fis.read(imageBytes);
            fis.close();
            return imageBytes;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @param stringID - the key to look for
     * @return - the localized string
     */
    public String queryString (String stringID)
    {
        try
        {
            String resource = null;
            resource = this.bundle.getString(stringID);
            return (resource);
        }
        catch (Throwable e)
        {
        	return NO_RESOURCE_FOUND;
        }
    }
}
