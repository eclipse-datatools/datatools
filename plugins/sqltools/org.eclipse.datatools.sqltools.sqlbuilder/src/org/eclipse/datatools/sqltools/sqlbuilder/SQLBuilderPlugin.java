/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl;
import org.eclipse.datatools.modelbase.sql.query.provider.SQLQueryModelItemProviderAdapterFactory;
import org.eclipse.datatools.modelbase.sql.query.util.SQLLogUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;

/**
 * The plugin class for the SQL Builder.
 */
public class SQLBuilderPlugin extends AbstractUIPlugin {

    protected static SQLBuilderPlugin plugin = null;
    protected static SQLQueryModelItemProviderAdapterFactory adapterFactory = new SQLQueryModelItemProviderAdapterFactory();
    public final static String SQL_PARTITIONING= "__sql_partitioning";   //$NON-NLS-1$
    public static final IPath IMAGES_PATH= new Path("images"); //$NON-NLS-1$
    
    private SQLLogUtil logger = null;

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public SQLBuilderPlugin() {
        super();
        plugin = this;
    }

    /**
     * Returns the resource bundle for this plugin.
     * @return the resource bundle
     */
    public ResourceBundle getResourceBundle() {
        Bundle bundle = getBundle();
        return Platform.getResourceBundle(bundle);
    }

    /**
     * Returns the SQL Query model package object.
     * @return The SQL Query model package
     */
    public SQLQueryModelPackage getSQLQueryModelPackage() {
        return SQLQueryModelPackageImpl.eINSTANCE;
    }

    /**
     * Returns the SQL Model schema package object.
     * @return the SQL Model schema package
     */
    public SQLSchemaPackage getRDBSchemaPackage() {
        return SQLSchemaPackageImpl.eINSTANCE;
    }

    /**
     * Returns the SQL Query model factory instance 
     * @return the SQL Query model factory
     */
    public SQLQueryModelFactory getSQLQueryFactory() {
        return (SQLQueryModelFactory) getSQLQueryModelPackage().getEFactoryInstance();
    }

    /**
     * Returns the single instance of the SQLBuilder plugin.
     * @return the plugin instance 
     */
    public static SQLBuilderPlugin getPlugin() {
        return plugin;
    }

    /**
     * Returns the Image for a given file name
     * @param iconName - the icon for which the image is to be obtained
     * @return the Image instance corrospnding to the icon 
     */
    public static Image getSQLImage(String iconName) {
        return getPlugin().getImage(iconName);
    }

    /**
     * Returns the adapter factory for the SQL Query model.
     * @return the adapter factory
     */
    public static SQLQueryModelItemProviderAdapterFactory getAdapterFactory() {
        return adapterFactory;
    }

    /**
     * Returns an image (icon) with the given name. 
     * @param iconName the name of the image to get
     * @return the image
     */
    public Image getImage(String iconName) {
        ImageRegistry imageRegistry = getImageRegistry();

        if (imageRegistry.get(iconName) != null) {
            return imageRegistry.get(iconName);
        }
        imageRegistry.put(iconName, ImageDescriptor.createFromFile(getClass(), iconName));
        return imageRegistry.get(iconName);
    }

    public URL getInstallURL() {
        Bundle bundle = getBundle();
        return bundle.getEntry("/"); //$NON-NLS-1$
    }

    /**
     * Returns a .gif from the images folder with the given key.
     * @param the key (name) of the image to get
     */
    public ImageDescriptor getImageDescriptor( String key ) {
        IPath path = IMAGES_PATH.append(key + ".gif"); //$NON-NLS-1$   
        URL imageURL = FileLocator.find(getBundle(), path, null);     
            
        return ImageDescriptor.createFromURL(imageURL);

    }



    /**
     * Returns the single logger object for this plugin.
     * @return the logger object
     */
    public SQLLogUtil getLogger() {
        if (logger == null) {
            logger = new SQLLogUtil(getPlugin());
        }
        return logger;
    }

}