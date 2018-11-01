/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.ui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * This class provides convenience access to many of the resources required by the workbench. The class stores some
 * images as descriptors, and some are stored as real Images in the registry. This is a pure speed-space tradeoff. The
 * trick for users of this class is that images obtained from the registry (using getImage()), don't require disposal
 * since they are shared, while images obtained using getImageDescriptor() will require disposal. Consult the
 * declareImages method to see if a given image is declared as a registry image or just as a descriptor. If you change
 * an image from being stored as a descriptor to a registry image, or vice-versa, make sure to check all users of the
 * image to ensure they are calling the correct getImage... method and handling disposal correctly.
 * 
 * Images: - use getImage(key) to access cached images from the registry. - Less common images are found by calling
 * getImageDescriptor(key) where key can be found in IWorkbenchGraphicConstants
 * 
 * This class initializes the image registry by declaring all of the required graphics. This involves creating image
 * descriptors describing how to create/find the image should it be needed. The image is not actually allocated until
 * requested.
 * 
 * Some Images are also made available to other plugins by being placed in the descriptor table of the SharedImages
 * class.
 * 
 * Where are the images? The images (typically gifs) are found the plugins install directory
 * 
 * How to add a new image Place the gif file into the appropriate directories. Add a constant to
 * IWorkbenchGraphicConstants following the conventions Add the declaration to this file
 */
public class SQLImages
{

    private static Map           descriptors;

    private static ImageRegistry imageRegistry;

    /* Declare Common paths */

    public final static String   ICONS_PATH                = "$nl$/icons/full/";                   //$NON-NLS-1$

    private final static String  PATH_ETOOL                = ICONS_PATH + "etool16/";              // Enabled toolbar
    // icons.//$NON-NLS-1$

    private final static String  PATH_DTOOL                = ICONS_PATH + "dtool16/";              // Disabled toolbar
    // icons.//$NON-NLS-1$

    private final static String  PATH_ELOCALTOOL           = ICONS_PATH + "elcl16/";               // Enabled local
    // toolbar
    // icons.//$NON-NLS-1$

    private final static String  PATH_DLOCALTOOL           = ICONS_PATH + "dlcl16/";               // Disabled local
    // toolbar
    // icons.//$NON-NLS-1$

    private final static String  PATH_EVIEW                = ICONS_PATH + "eview16/";              // View
    // icons//$NON-NLS-1$

    // private final static String PATH_PROD = ICONS_PATH+"prod/"; //Product images
    private final static String  PATH_OBJECT               = ICONS_PATH + "obj16/";                // Model object
    // icons//$NON-NLS-1$

    private final static String  PATH_POINTER              = ICONS_PATH + "pointer/";              // Pointer
    // icons//$NON-NLS-1$

    private final static String  PATH_WIZBAN               = ICONS_PATH + "wizban/";               // Wizard
    // icons//$NON-NLS-1$

    private static final String  NAME_PREFIX               = SQLUIActivator.PLUGIN_ID;

    // private final static String PATH_STAT = ICONS_PATH+"stat/";
    // private final static String PATH_MISC = ICONS_PATH+"misc/";
    // private final static String PATH_OVERLAY = ICONS_PATH+"ovr16/";

    public static final String   IMG_EDT_SQL               = NAME_PREFIX + "sql.gif";
    public static final String   IMG_EDT_SELECT            = NAME_PREFIX + "select.gif";
    public static final String   IMG_EDT_DELETE            = NAME_PREFIX + "delete.gif";
    public static final String   IMG_EDT_UPDATE            = NAME_PREFIX + "update.gif";
    public static final String   IMG_EDT_INSERT            = NAME_PREFIX + "insert.gif";
    public static final String   IMG_EDT_DATABASE          = NAME_PREFIX + "database.gif";
    public static final String   IMG_EDT_TABLE             = NAME_PREFIX + "table.gif";
    public static final String   IMG_EDT_TABLE_ALIAS       = NAME_PREFIX + "table_alias.gif";
    public static final String   IMG_EDT_VIEW              = NAME_PREFIX + "view.gif";

    public static final String   IMG_EDT_PROCEDURE         = NAME_PREFIX + "procedure.gif";
    public static final String   IMG_EDT_SYSTEM_PROCEDURE  = NAME_PREFIX + "system_procedure.gif";

    public static final String   IMG_EDT_FUNCTION          = NAME_PREFIX + "function.gif";
    public static final String   IMG_EDT_EVENT             = NAME_PREFIX + "event.gif";
    public static final String   IMG_EDT_KEYWORD           = NAME_PREFIX + "keyword.gif";
    public static final String   IMG_EDT_UNRESERVEDKEYWORD = NAME_PREFIX + "unreservedkeyword.gif";

    public static final String   IMG_EDT_TRIGGER           = NAME_PREFIX + "trigger.gif";
    // insert_trigger.gif
    public static final String   IMG_EDT_INSERT_TRIGGER    = NAME_PREFIX + "insert_trigger.gif";

    public static final String   IMG_EDT_DEFAULT           = NAME_PREFIX + "default.gif";
    public static final String   IMG_EDT_DATATYPE          = NAME_PREFIX + "datatype.gif";
    public static final String   IMG_EDT_PARAMETER         = NAME_PREFIX + "parameter.gif";

    /**
     * Declares a workbench image given the path of the image file (relative to the workbench plug-in). This is a helper
     * method that creates the image descriptor and passes it to the main <code>declareImage</code> method.
     * 
     * @param key the symbolic name of the image
     * @param path the path of the image file relative to the base of the workbench plug-ins install directory
     * @param shared <code>true</code> if this is a shared image, and <code>false</code> if this is not a shared
     *            image
     */
    private final static void declareImage(String key, String path, boolean shared)
    {
        URL url = Platform.find(SQLUIActivator.getDefault().getBundle(), new Path(path));
        ImageDescriptor desc = ImageDescriptor.createFromURL(url);
        declareImage(key, desc, shared);
    }

    /**
     * Declares all the workbench's images, including both "shared" ones and internal ones.
     */
    private final static void declareImages()
    {

        declareImage(IMG_EDT_DATABASE, PATH_ETOOL + "database.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_SQL, PATH_ETOOL + "sql.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_SELECT, PATH_ETOOL + "select.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_DELETE, PATH_ETOOL + "delete.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_UPDATE, PATH_ETOOL + "update.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_INSERT, PATH_ETOOL + "insert.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_DATABASE, PATH_ETOOL + "database.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_TABLE, PATH_ETOOL + "table.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_TABLE_ALIAS, PATH_ETOOL + "table_alias.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_VIEW, PATH_ETOOL + "view.gif", true); //$NON-NLS-1$

        declareImage(IMG_EDT_PROCEDURE, PATH_ETOOL + "procedure.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_SYSTEM_PROCEDURE, PATH_ETOOL + "system_procedure.gif", true); //$NON-NLS-1$

        declareImage(IMG_EDT_FUNCTION, PATH_ETOOL + "function.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_EVENT, PATH_ETOOL + "event.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_KEYWORD, PATH_ETOOL + "keyword.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_UNRESERVEDKEYWORD, PATH_ETOOL + "unreservedkeyword.gif", true); //$NON-NLS-1$

        declareImage(IMG_EDT_TRIGGER, PATH_ETOOL + "trigger.gif", true); //$NON-NLS-1$
        // insert_trigger.gif
        declareImage(IMG_EDT_INSERT_TRIGGER, PATH_ETOOL + "insert_trigger.gif", true); //$NON-NLS-1$

        declareImage(IMG_EDT_DEFAULT, PATH_ETOOL + "default.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_DATATYPE, PATH_ETOOL + "datatype.gif", true); //$NON-NLS-1$
        declareImage(IMG_EDT_PARAMETER, PATH_ETOOL + "parameter.gif", true); //$NON-NLS-1$

    }

    /**
     * Declares a workbench image.
     * <p>
     * The workbench remembers the given image descriptor under the given name, and makes the image available to
     * plug-ins via {@link org.eclipse.ui.ISharedImages IWorkbench.getSharedImages()}. For "shared" images, the
     * workbench remembers the image descriptor and will manages the image object create from it; clients retrieve
     * "shared" images via {@link org.eclipse.ui.ISharedImages#getImage ISharedImages.getImage()}. For the other,
     * "non-shared" images, the workbench remembers only the image descriptor; clients retrieve the image descriptor via
     * {@link org.eclipse.ui.ISharedImages#getImageDescriptor ISharedImages.getImageDescriptor()} and are entirely
     * responsible for managing the image objects they create from it. (This is made confusing by the historical fact
     * that the API interface is called "ISharedImages".)
     * </p>
     * 
     * @param symbolicName the symbolic name of the image
     * @param descriptor the image descriptor
     * @param shared <code>true</code> if this is a shared image, and <code>false</code> if this is not a shared
     *            image
     * @see org.eclipse.ui.ISharedImages#getImage
     * @see org.eclipse.ui.ISharedImages#getImageDescriptor
     */
    public static void declareImage(String symbolicName, ImageDescriptor descriptor, boolean shared)
    {
        getDescriptors().put(symbolicName, descriptor);
        if (shared)
        {
            getImageRegistry().put(symbolicName, descriptor);
        }
    }

    /**
     * Returns the map from symbolic name to ImageDescriptor.
     * 
     * @return the map from symbolic name to ImageDescriptor.
     */
    private static Map getDescriptors()
    {
        if (descriptors == null)
        {
            initializeImageRegistry();
        }
        return descriptors;
    }

    /**
     * Returns the image stored in the workbench plugin's image registry under the given symbolic name. If there isn't
     * any value associated with the name then <code>null</code> is returned.
     * 
     * The returned Image is managed by the workbench plugin's image registry. Callers of this method must not dispose
     * the returned image.
     * 
     * This method is essentially a convenient short form of WorkbenchImages.getImageRegistry.get(symbolicName).
     */
    public static Image getImage(String symbolicName)
    {
        return getImageRegistry().get(symbolicName);
    }

    /**
     * Returns the image descriptor stored under the given symbolic name. If there isn't any value associated with the
     * name then <code>null
     * </code> is returned.
     * 
     * The class also "caches" commonly used images in the image registry. If you are looking for one of these common
     * images it is recommended you use the getImage() method instead.
     */
    public static ImageDescriptor getImageDescriptor(String symbolicName)
    {
        return (ImageDescriptor) getDescriptors().get(symbolicName);
    }

    /**
     * Returns the ImageRegistry.
     */
    public static ImageRegistry getImageRegistry()
    {
        if (imageRegistry == null)
        {
            initializeImageRegistry();
        }
        return imageRegistry;
    }

    /**
     * Initialize the image registry by declaring all of the required graphics. This involves creating JFace image
     * descriptors describing how to create/find the image should it be needed. The image is not actually allocated
     * until requested.
     * 
     * Prefix conventions Wizard Banners WIZBAN_ Preference Banners PREF_BAN_ Property Page Banners PROPBAN_ Enable
     * toolbar ETOOL_ Disable toolbar DTOOL_ Local enabled toolbar ELCL_ Local Disable toolbar DLCL_ Object large OBJL_
     * Object small OBJS_ View VIEW_ Product images PROD_ Misc images MISC_
     * 
     * Where are the images? The images (typically gifs) are found in the same location as this plugin class. This may
     * mean the same package directory as the package holding this class. The images are declared using this.getClass()
     * to ensure they are looked up via this plugin class.
     * 
     * @see ImageRegistry
     */
    private static void initializeImageRegistry()
    {
        imageRegistry = new ImageRegistry();
        descriptors = new HashMap();
        declareImages();
    }

    /**
     * Disposes and clears the workbench images. Called when the workbench is shutting down.
     * 
     * @since 3.1
     */
    public static void dispose()
    {
        if (imageRegistry != null)
        {
            imageRegistry.dispose();
            imageRegistry = null;
            descriptors = null;
        }
    }

    /**
     * Get the workbench image with the given path relative to ICON_PATH.
     * 
     * @param relativePath
     * @return ImageDescriptor
     */
    public static ImageDescriptor getSQLImageDescriptor(String relativePath)
    {
        return ImageDescriptor.createFromURL(SQLUIActivator.getDefault().getBundle().getEntry(ICONS_PATH + relativePath));
    }
}
