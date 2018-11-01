/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal;

import java.net.URL;
import java.util.ResourceBundle;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;

public final class SQLEditorResources extends NLS {
	private static final String BUNDLE_FOR_CONSTRUCTED_KEYS= "org.eclipse.datatools.sqltools.sqleditor.internal.ConstructedSQLEditorResources";//$NON-NLS-1$
	private static ResourceBundle fgBundleForConstructedKeys= ResourceBundle.getBundle(BUNDLE_FOR_CONSTRUCTED_KEYS);

	/**
	 * Returns the message bundle which contains constructed keys.
	 *
	 * @return the message bundle
	 */
	public static ResourceBundle getResourceBundle() {
		return fgBundleForConstructedKeys;
	}

	private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources";//$NON-NLS-1$

	private SQLEditorResources() {
		// Do not instantiate
	}

	public static String common_error;
    public static String SQLEditor_could_not_save_as;
    public static String SQLEditor_file_deleted_or_not_accessible;
    public static String SQLEditor_problem_save_as;
	public static String SQLUpdater_nonportable;
	public static String SQLUpdater_error_location;
	public static String SQLUpdater_error_annotation;
	public static String SQLUpdater_error_removemarker;
    public static String SQLUpdate_dialog_title;
    public static String SQLUpdate_dialog_message;
    public static String SQLUpdate_dialog_toggle;
	public static String SQLErrorHover_makeStickyHint = null;
	public static String SQLEditor_outlinePage_sqlSegment_titlePattern;
	public static String SQLEditor_connection_status_noConnection;
	public static String plugin_internal_error;
	public static String SQLEditorConnectionInfo_decode_error;
	public static String EditorManager_operationFailed;
	public static String Save;
	public static String SQLEditor_error_while_trying_to_install_sql_updater;
	public static String SQLEditorStorage_default_name;
	public static String SQLEditor_status_profile;
	public static String SQLEditor_status_database;
	public static String SQLEditor_status_dbType;
	public static String SQLEditor_save_message;
	public static String SQLEditor_status_profile_connected;
	public static String SQLEditor_status_profile_notconnected;


	static {
		NLS.initializeMessages(BUNDLE_NAME, SQLEditorResources.class);
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
	                  URL baseURL = SQLEditorPlugin.getDefault().getBundle().getEntry("icons/"); //$NON-NLS-1$
	                  URL imageURL = new URL( baseURL, key + ".gif" ); //$NON-NLS-1$ //$NON-NLS-2$
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

}