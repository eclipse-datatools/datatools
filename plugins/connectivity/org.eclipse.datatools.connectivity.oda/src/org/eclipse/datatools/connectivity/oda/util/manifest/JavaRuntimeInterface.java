/*
 *************************************************************************
 * Copyright (c) 2004, 2014 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.util.manifest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Level;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;
import org.osgi.framework.Bundle;

/**
 * This class encapsulates the Java runtime interface specific configurations.
 */
public class JavaRuntimeInterface extends RuntimeInterface
{
	private String m_driverClass;
	private boolean m_needSetThreadContextClassLoader;
	private String m_namespace;
	private IPath m_loadedBundlePath;
	
    private static final String sm_className = JavaRuntimeInterface.class.getName();
    private static final String PLUGIN_ROOT_PATH = "/"; //$NON-NLS-1$
    private static final String PLUGIN_VERSION_SEPARATOR = "_"; //$NON-NLS-1$
    private static final String BUNDLE_MANIFEST_RELATIVE_PATH = "META-INF/MANIFEST.MF"; //$NON-NLS-1$
    private static final String UPPER_RELATIVE_PATH = ".."; //$NON-NLS-1$

	JavaRuntimeInterface( String driverClass,
						  boolean needSetThreadContextClassLoader,
						  String namespace )
	{
		m_driverClass = driverClass;
		m_needSetThreadContextClassLoader = needSetThreadContextClassLoader;
		m_namespace = namespace;
	}
	
	public int getInterfaceType()
	{
		return JAVA_TYPE;
	}
	
	/**
	 * Returns the fully qualified concrete class that implements the 
	 * <i>org.eclipse.datatools.connectivity.oda.IDriver</i> interface. 
	 * This is the entry point of the ODA runtime extension.
	 * @return	the fully qualified connection factory class name.
	 */
	public String getDriverClass()
	{
		return m_driverClass;
	}
	
	/**
	 * If true, the consumer of the ODA runtime extension plugin should set the 
	 * thread context class loader to the class loader used to load this driver 
	 * before calling any ODA interface method.  The default is false.
	 * @return	true if the thread context classloader is to be set for the 
	 * 			ODA runtime extension plugin.
	 */
	public boolean needSetThreadContextClassLoader()
	{
		return m_needSetThreadContextClassLoader;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.util.manifest.RuntimeInterface#getLibraryLocation()
	 */
	public URL getLibraryLocation() throws IOException
	{
		return getLocation( PLUGIN_ROOT_PATH );
	}
	
	/**
	 * @see org.eclipse.datatools.connectivity.oda.util.manifest.RuntimeInterface#getDriverFileLocation(java.lang.String)
	 */
	public URL getDriverFileLocation( String filename ) throws IOException
	{
		return getLocation( filename );
	}
	
	private URL getLocation( String entry ) throws IOException
	{
		Bundle bundle = Platform.getBundle( m_namespace );
		if( bundle != null ) 
		{
			URL url = bundle.getEntry( entry );
			return FileLocator.toFileURL( url );
		}
		
		// no bundle handle; probably on non-OSGi platform
		
		if( m_loadedBundlePath == null )  // unknown bundle path
		    throw new IOException( Messages.bind( Messages.manifest_UNKNOWN_BUNDLE_LOCATION,
		            m_namespace ) );

	    IPath entryPath = entry.equals( PLUGIN_ROOT_PATH ) ?
	                        m_loadedBundlePath :
	                        m_loadedBundlePath.append( entry );
	    return entryPath.toFile().toURI().toURL();
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.util.manifest.RuntimeInterface#getLibraries()
	 */
	public String[] getLibraries()
	{
		// Eclipse core classes doesn't expose this information
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Set the location of the ODA runtime extension bundle that contains the specified driver class.
	 * @param driverClass  the class of an ODA extension that implements the 
	 *             {@link org.eclipse.datatools.connectivity.oda.IDriver} interface
	 * @param appContext   an application context Map that might contain a {@link ResourceIdentifiers},
	 *             set by an ODA consumer application, to resolve the bundle location; 
	 *             optional, may be null
	 * @since 3.3.2 (DTP 1.9)
	 */
	public void setLoadedClassLocation( Class<?> driverClass, Object appContext )
	{
	    m_loadedBundlePath = null;     // reset any existing value
	    
	    // first try to resolve the bundle location based on appContext, if exists
	    if( appContext instanceof Map )
	    {
	        ResourceIdentifiers resourceIdentifiers = ResourceIdentifiers.get( appContext );
	        if( resourceIdentifiers != null )
	        {
	            URI driverPathUri = resourceIdentifiers.resolveResourceLocation( m_namespace );
	            m_loadedBundlePath = convertToExistingFilePath( driverPathUri );
	        }
	    }
	    
	    // not able to get from appContext, try to get from the driver class loader
	    if( m_loadedBundlePath == null )
	        m_loadedBundlePath = getLoadedBundlePath( m_namespace, driverClass.getClassLoader() );
	    
	    // next try to get from the loaded class domain
	    if( m_loadedBundlePath == null )
            m_loadedBundlePath = getDomainBundlePath( m_namespace, driverClass );
	}
	
	private static IPath convertToExistingFilePath( URI pathUri )
	{
        final String methodName = "convertToExistingFilePath(URI)"; //$NON-NLS-1$

	    if( pathUri == null )
	        return null;
	    
        try
        {
            URL pathURL = pathUri.toURL();
            IPath filePath = new Path( pathURL.getPath() );
            if( filePath.toFile().exists() )    // the file path exists
                return filePath;
        }
        catch( Exception ex )
        {
            // log and ignore
            ManifestExplorer.getLogger().logp( Level.FINE, sm_className, methodName, 
                    Messages.bind( "The specified URI ({0}) is not a valid file path.", pathUri), ex ); //$NON-NLS-1$
        }
        return null;
	}
	
	private static IPath getLoadedBundlePath( String bundleName, ClassLoader cl )
	{
        final String methodName = "getLoadedBundlePath(String,ClassLoader)"; //$NON-NLS-1$
        
        Enumeration<URL> foundURLs;
        try
        {
            // search for the plugin manifest file, which should always exist 
            // at one level below the plugin path
            foundURLs = cl.getResources( BUNDLE_MANIFEST_RELATIVE_PATH );
        }
        catch( IOException ex )
        {
            // log and ignore
            ManifestExplorer.getLogger().logp( Level.FINE, sm_className, methodName, 
                    Messages.bind( "Unable to locate the installation path of the specified bundle ({0}).", bundleName ),  //$NON-NLS-1$
                    ex );
            return null;
        }
        
        String pluginNameFragment = bundleName + PLUGIN_VERSION_SEPARATOR;
        while( foundURLs.hasMoreElements() )
        {
            URL manifestURL = foundURLs.nextElement();
            if( ! manifestURL.getPath().contains( pluginNameFragment ) )
                continue;

            // found the first plugin in classpath that has matching bundleName 
            // TODO - handle multiple matching entries
            try
            {
                URL pluginLocURL = new URL( manifestURL, UPPER_RELATIVE_PATH );
                return new Path( pluginLocURL.getPath() );
            }
            catch( MalformedURLException ex )
            {
                // log and ignore
                ManifestExplorer.getLogger().logp( Level.FINE, sm_className, methodName, 
                        Messages.bind( "Unable to resolve the installation path ({0}/{1}) of the specified bundle ({2}).",  //$NON-NLS-1$
                                new Object[]{manifestURL, UPPER_RELATIVE_PATH, bundleName} ), 
                        ex );
                return null;
            }
        }

        return null;       
	}
	
	private static IPath getDomainBundlePath( String bundleName, Class<?> clazz )
	{
	    final String methodName = "getDomainBundlePath(Class,String)"; //$NON-NLS-1$
	    
        if( clazz == null )
            return null;

        try
        {
            ProtectionDomain protectionDomain = clazz.getProtectionDomain();
            URL classFileLocation = protectionDomain.getCodeSource().getLocation();
            if( classFileLocation == null ) 
                return null;
            
            // strip path up to plugin parent folder, i.e. matching the bundle name
            IPath classPath = new Path( classFileLocation.getPath() );
            int bundleSegmentIndex = -1;
            String[] classPathSegments = classPath.segments();
            for( int i=0; i < classPathSegments.length; i++ )
            {
                if( bundleName.equals( classPathSegments[i] ) )
                {
                    bundleSegmentIndex = i;
                    break;
                }
            }
            if( bundleSegmentIndex >= 0 )
            {
                IPath bundleRoot = classPath.uptoSegment( bundleSegmentIndex+1 );
                if( bundleRoot.toFile().exists() )
                {
                    return bundleRoot;
                }
            }
        }
        catch( Throwable ex )
        {
            // log and ignore
            ManifestExplorer.getLogger().logp( Level.FINE, sm_className, methodName, 
                    "Unable to obtain bundle installation path from ProtectionDomain.", ex ); //$NON-NLS-1$
        }
        
        // unable to obtain the bundle installation path from the clazz
        return null;
    }
	
}
