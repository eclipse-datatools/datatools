/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ResourceBundle;

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

	private static URL iconLocation;
   
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
        Bundle bundle = RDBCoreUIPlugin.getDefault().getBundle();
        iconLocation = bundle.getEntry("/" + ICONS_DIRECTORY); //$NON-NLS-1$
        
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
    
    private Image queryImage (URL imageLocation)
    {
		Image image = null;
		if ((image = plugin.getImageRegistry().get(imageLocation.toString())) == null)
		{
			image = ImageDescriptor.createFromURL(imageLocation).createImage();
			plugin.getImageRegistry().put(imageLocation.toString(), image);
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
        try {
			return queryImage (new URL(iconLocation,imagePath));
		}
		catch (MalformedURLException e) {
			return null;
		}
    }
    
    public Image queryImageFromRegistry (URL imageLocation)
    {
    	return queryImage(imageLocation);
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
        ReadableByteChannel rbc = null;
        WritableByteChannel wbc = null;
        try
        {
            URL imageURL = new URL(iconLocation,name);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            rbc = Channels.newChannel(imageURL.openStream());
            wbc = Channels.newChannel(baos);

            ByteBuffer buf = ByteBuffer.allocate(128); // These icons shouldn't be very big

            while (rbc.read(buf)>0) {
            	wbc.write(buf);
            }

            return baos.toByteArray();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
        	if (rbc != null) {
        		try {
        			rbc.close();
				}
				catch (IOException e) {
				}
        	}
        	if (wbc != null) {
        		try {
        			wbc.close();
				}
				catch (IOException e) {
				}
        	}
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
        	return stringID;
        }
    }
}
