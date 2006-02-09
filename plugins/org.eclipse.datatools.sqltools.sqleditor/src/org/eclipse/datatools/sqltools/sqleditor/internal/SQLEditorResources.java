/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal;

import java.net.URL;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

public class SQLEditorResources {

	private static final String RESOURCE_BUNDLE = "org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources"; //$NON-NLS-1$

	private static ResourceBundle fgResourceBundle;

    /* This constructor is private so can't create an instance of this class.
     */
	private SQLEditorResources() {
	}

   /**
    * Gets the image (.gif file) corresponding to the given key.
    * 
    * @return the requested image, or <code>null</code> if not found
    */
   public static Image getImage( String key ) {
          ImageRegistry imageRegistry = SQLEditorPlugin.getDefault().getImageRegistry();
          Image image = imageRegistry.get( key );
          if (image == null) {
              ImageDescriptor descriptor = null;
              try {
                  URL baseURL = SQLEditorPlugin.getDefault().getBundle().getEntry("/"); //$NON-NLS-1$
                  URL imageURL = new URL( baseURL, "icons" + java.io.File.separator + key + ".gif" ); //$NON-NLS-1$ //$NON-NLS-2$
                  descriptor = ImageDescriptor.createFromURL( imageURL );
              }
              catch (Exception e) {
              }
              if (descriptor != null) {
                  imageRegistry.put( key, descriptor );
                  image = imageRegistry.get(key);
              }
          }
          return image;
       }

   /**
    * Gets the image descriptor (.gif file) corresponding to the given key.
    * 
    * @return the requested image descriptor, or <code>null</code> if not found
    */
   public static ImageDescriptor getImageDescriptor( String key ) {
	   ImageDescriptor descriptor = null;
	   Image image = getImage( key );
	   if (image != null) {
		   ImageDescriptor.createFromImage(image);
	   }
	   return descriptor;
   }
   
    /**
     * Gets the resource string identified by the given resource key.
     * Returns "!<key>!" if resource not found.
     * 
     * @param key the key of the desired resource
     * @return the desired resource string
     */
	public static String getString( String key ) {
        String str = null;
        ResourceBundle bundle = getResourceBundle();
        try {
            str = (bundle != null) ? bundle.getString( key ) : key;
        } catch (MissingResourceException e) {
            str = "!" + key + "!";//$NON-NLS-2$ //$NON-NLS-1$
        }
        return str;
	}
	
    /**
     * Gets the resource string identified by the given resource key,
     * with the given substitutions array.  The contents of the substitutions
     * array are spliced into the resource string in place of parameter
     * markers {0}, {1}, etc. in the string.  
     * 
     * @param key the key of the desired resource
     * @param substitutions an array containing substitions
     * @return the desired resource string, with substitutions
     */
    public static String getString( String key, Object[] substitutions ) {
        String str = getString( key );
        if (substitutions != null)
            str = MessageFormat.format( str, substitutions );
        return str;
    }
       
    /**
     * Gets the resource bundle associated with this plug-in, or <code>null</code>
     * if not found.
     * 
     * @return the plug-in's resource bundle
     */
    public static ResourceBundle getResourceBundle() {
        if (fgResourceBundle == null) {
            try {
                fgResourceBundle = ResourceBundle.getBundle( RESOURCE_BUNDLE );
            } catch (MissingResourceException x) {
                fgResourceBundle = null;
            }
        }
        return fgResourceBundle;
    }

} // end class
