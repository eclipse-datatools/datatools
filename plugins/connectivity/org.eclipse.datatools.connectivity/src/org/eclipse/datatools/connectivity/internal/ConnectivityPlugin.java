/*******************************************************************************
 * Copyright (c) 2005, 2012 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: 
 *      Sybase, Inc. - initial API and implementation
 *      Actuate Corporation - support for OSGi-less platform (Bugzilla 338997)
 *      Actuate Corporation - Bugzilla 330725: fix for OSGi-less platform support
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.internal.services.PluginResourceLocatorImpl;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import com.ibm.icu.text.MessageFormat;

/**
 * The main plugin class.
 */
@SuppressWarnings("deprecation")
public class ConnectivityPlugin extends Plugin {

	public static final int INTERNAL_ERROR = 10001;
	
	public static final String PLUGIN_ID = "org.eclipse.datatools.connectivity";//$NON-NLS-1$
	public static final String PROP_SYSTEM_REPOSITORIES_ENABLED = "org.eclipse.datatools.connectivity.repositoriesEnabled";//$NON-NLS-1$

	// The shared instance.
	private static ConnectivityPlugin plugin;
    private static IPath defaultWorkspace;
    private static Logger jdkLogger;

	private ResourceBundle resourceBundle;
    private Preferences localPreferences;
	
	/**
	 * The constructor.
	 */
	public ConnectivityPlugin() {
		super();
		plugin = this;
	}

	/**
	 * Returns the shared instance.
	 */
	public static ConnectivityPlugin getDefault() {
	    if( plugin == null )
	    {
	        synchronized( ConnectivityPlugin.class )
	        {
	            if( plugin == null )
	                new ConnectivityPlugin();
	        }
	    }
		return plugin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		InternalProfileManager.getInstance().dispose();
		ClassLoaderCacheManager.releaseInstance();
		super.stop(context);
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		try {
			if (resourceBundle == null)
				resourceBundle = ResourceBundle
						.getBundle("org.eclipse.datatools.connectivity.internal.resources"); //$NON-NLS-1$
		}
		catch (MissingResourceException x) {
			resourceBundle = null;
		}
		return resourceBundle;
	}

	public String getResourceString(String key) {
		try {
			ResourceBundle resBundle = getResourceBundle();
			if (resBundle == null) {
				return key;
			}

			return resBundle.getString(key);
		}
		catch (MissingResourceException e) {
			return key;
		}
	}

	public String getResourceString(String key, Object[] arguments) {
		MessageFormat f = new MessageFormat(getResourceString(key));

		return f.format(arguments);
	}

	public static String getSymbolicName() {
	    Bundle theBundle = getDefault().getBundle();
	    return theBundle != null ? 
	            theBundle.getSymbolicName() : 
	            PLUGIN_ID;
	}
	
	/**
	 * Returns the default workspace location of this plug-in.
	 * @return the path of this plug-in's default workspace location.
	 */
	public static IPath getDefaultStateLocation()
	{
        if( defaultWorkspace == null )
        {
            IPath wsPath = PluginResourceLocatorImpl.getPluginStateLocation( PLUGIN_ID );
            if( wsPath == null )
            {
                String messageId = "ConnectivityPlugin.error.noDefaultWorkspace"; //$NON-NLS-1$
                String errorMsg = getDefault().getResourceString( messageId );
                getDefault().logError( errorMsg );
                throw new IllegalStateException( errorMsg );
            }
            
            synchronized( ConnectivityPlugin.class )
            {
                if( defaultWorkspace == null )
                    defaultWorkspace = wsPath;
            }
        }
        return defaultWorkspace;
	}

	/**
	 * Returns the file path of the specified filename in 
	 * this plug-in's default workspace location.  
	 * Provides support for both OSGi and OSGi-less platforms.
	 * @param filename base file name
	 * @return the path of of the specified file in this plug-in's 
	 *         default workspace location
     * @since DTP 1.10
	 */
	public static IPath getWorkspaceFilePath( String filename )
	{
        IPath metadataPath = ConnectivityPlugin.getDefaultStateLocation();
        return metadataPath.append( filename );
	}

	/**
	 * Returns the default storage location for persisted files that are 
	 * configured and consumed by this plug-in, 
	 * such as those of connection profiles and driver definitions.
	 * The storage location is created, if it did not exist prior to this call. 
	 * @return the location path of the default storage area for this plugin
	 */
    public static IPath getStorageLocation() 
    {
        IPath defaultLocation = getDefaultStateLocation();
        
        // create default folder if none exists
        File file = defaultLocation.toFile();
        if( ! file.exists() )
        {
            try
            {
                file.mkdir();
            }
            catch( Exception e )
            {
                // log warning
                getDefault().logWarning( e.getMessage() );
                return null;
            }
        }
        return defaultLocation;
    }
    
    /**
     * Returns a URL to the entry at the specified path in this plug-in.
     * @param path  the path name of the entry
     * @return  A URL to the entry, or null if no entry could be found 
     */
	public static URL getEntry( String path )
	{
	    return PluginResourceLocatorImpl.getPluginEntry( PLUGIN_ID, path, 
	            ConnectivityPlugin.class.getClassLoader() );
	}

	/**
	 * Find the specified resource from this plugin's class loader. 
	 * @param name The name of the resource. See {@link ClassLoader#getResource(String)} 
     *              for a description of the format of a resource name.
	 * @return A URL to the named resource, or null if the resource could not be found 
	 */
	public static URL getResource( String name )
	{
	    return PluginResourceLocatorImpl.getPluginResource( PLUGIN_ID, name, 
	            ConnectivityPlugin.class.getClassLoader() );
	}
	
	/** 
	 * Indicates whether this plug-in is running on the OSGi platform.
	 * @return true if running on the OSGi platform; false otherwise
	 */
	public static boolean isRunningOSGiPlatform()
	{
	    return Platform.getBundle( PLUGIN_ID ) != null;
	}
	
	/**
	 * Sets the current value of the boolean-valued property with the
     * specified name. 
	 * @param name  the name of the property; must not be <code>null</code>
	 * @param value     the new current value of the property
	 */
	public void setPreferenceValue( String name, boolean value ) 
	{
        getPreferences().setValue( name, value );
	}

	/**
     * Sets the current value of the string-valued property with the
     * specified name. 
     * @param name  the name of the property; must not be <code>null</code>
     * @param value     the new current value of the property
     * @since DTP 1.10
	 */
	public void setPreferenceValue( String name, String value ) 
    {
        getPreferences().setValue( name, value );
    }

	/**
	 * Returns the current value of the boolean-valued property with the
     * specified name.
	 * @param name  the name of the property; must not be <code>null</code>
	 * @return the boolean-valued property; or the default value (<code>false</code>) 
     *      if there is no property with the specified name, or if the current value 
     *      cannot be treated as a boolean
	 */
	public boolean getPreferenceBooleanValue( String name ) 
	{
        return getPreferences().getBoolean( name );
	}

	/**
     * Returns the current value of the string-valued property with the
     * specified name.
     * @param name  the name of the property; must not be <code>null</code>
     * @return the string-valued property; or the default-default value (the empty string "") 
     *      if there is no property with the specified name
     * @since DTP 1.10
	 */
	public String getPreferenceStringValue( String name ) 
    {
        return getPreferences().getString( name );
    }

	/**
	 * Saves the non-default-valued preference properties  
     * to the specified output stream.
	 * @param out   the output stream 
     * @param header a comment to be included in the output, or 
     *    <code>null</code> if none
     * @exception IOException if there is a problem saving the preference properties
	 */
	public void storePreferences( OutputStream out, String header ) 
	    throws IOException
	{
        getPreferences().store( out, header );
	}

	/**
	 * Loads the non-default-valued preference properties from the
     * specified input stream. Default property values are not affected.
     * @param in the input stream
     * @exception IOException if there is a problem loading the preference properties
	 */
	public void loadPreferences( InputStream in ) 
	    throws IOException 
	{
	    getPreferences().load( in );
	}
    
    private Preferences getPreferences()
    {
        if( isRunningOSGiPlatform() ) 
            return getPluginPreferences();

        // running on non-OSGi platform, use local Preferences instance instead
       if( localPreferences == null ) 
           localPreferences = new Preferences();
       return localPreferences;
    }
	
	/**
	 * Logs runtime status.
	 * 
	 * @param status Runtime status.
	 */
	public void log(IStatus status) {
        if ( isRunningOSGiPlatform() )
            getLog().log(status);
        else    // not running OSGi platform, use JDK logger instead
            getJdkLogger().log( getLogLevel( status ), status.getMessage() );
	}

	/**
	 * Logs error message.
	 * 
	 * @param message Error message.
	 */
	public void log(String message) {
	    logError(message);
	}
	
    /**
     * Logs error message.
     * @param message   error message
     * @since DTP 1.8
     */
    public void logError(String message) {
        log(createErrorStatus(message));
    }
    
    /**
     * Logs informational message.
     * @param message   informational message
     * @since DTP 1.8
     */
    public void logInfo(String message) {
        log( createStatus( IStatus.INFO, message) );
    }

    public void logWarning( String message ) {
        log( createStatus( IStatus.WARNING, message ) );
    }
    
	/**
	 * Logs and exception.
	 * 
	 * @param e Exception.
	 */
	public void log(Throwable e) {
		log(createErrorStatus(e));
	}

	public IStatus createErrorStatus(String message) {
	    return createStatus( IStatus.ERROR, message );
	}
	
	private IStatus createStatus(int severity, String message) {
		return new Status(severity, getSymbolicName(),
				INTERNAL_ERROR, message, null);
	}

	public IStatus createErrorStatus(Throwable e) {
		String message;
		if (e == null || e.getMessage() == null) {
			message = getResourceString("plugin.internal_error"); //$NON-NLS-1$
		}
		else {
			message = e.getMessage();
		}
		return new Status(IStatus.ERROR, getSymbolicName(),
				INTERNAL_ERROR, message, e);
	}
	
    private static Logger getJdkLogger()
    {
        if( jdkLogger == null )
        {
            synchronized( ConnectivityPlugin.class )
            {
                if( jdkLogger == null )
                    jdkLogger = Logger.getLogger( PLUGIN_ID );
            }
        }
        return jdkLogger;
    }
	
    private static Level getLogLevel( IStatus status )
    {
        int severity = status.getSeverity();
        switch( severity )
        {
            case IStatus.CANCEL:    return Level.INFO;
            case IStatus.ERROR:     return Level.SEVERE;
            case IStatus.INFO:      return Level.INFO;
            case IStatus.OK:        return Level.FINE;
            case IStatus.WARNING:   return Level.WARNING;
        }
        return Level.WARNING;
    }
    
}