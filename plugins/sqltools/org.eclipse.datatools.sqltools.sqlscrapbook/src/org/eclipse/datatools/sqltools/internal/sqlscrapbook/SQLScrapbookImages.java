/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * @author lihuang
 *
 */
public final class SQLScrapbookImages {
      private SQLScrapbookImages() {
		// Do not instantiate
	  }

      private static final String         NAME_PREFIX          = "org.eclipse.datatools.sqltools.sqlscrapbook";
      private static final int            NAME_PREFIX_LENGTH   = NAME_PREFIX.length();
      private static URL                  baseURL              = null;

      // The plugin registry
      private static ImageRegistry        imageRegistry        = null;
      private static HashMap              avoidSWTErrorMap     = new HashMap();

      static
      {
          String pathSuffix = "images/";
          baseURL = SqlscrapbookPlugin.getDefault().getBundle().getEntry(pathSuffix);
      }
      
      public static final String          IMG_ATTACH_PROFILE  = NAME_PREFIX + "attach_profile.gif";
      public static final ImageDescriptor DESC_ATTACH_PROFILE = createManaged(null, IMG_ATTACH_PROFILE);
      
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
              return result;
          }
          catch (MalformedURLException e)
          {
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
