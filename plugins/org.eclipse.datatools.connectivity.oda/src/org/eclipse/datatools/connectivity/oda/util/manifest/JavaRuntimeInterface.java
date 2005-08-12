/*
 *************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.util.manifest;

import java.io.IOException;
import java.net.URL;
import org.eclipse.birt.core.framework.IBundle;
import org.eclipse.birt.core.framework.Platform;

/**
 * This class encapsulates the Java runtime interface specific configurations.
 */
public class JavaRuntimeInterface extends RuntimeInterface
{
	private String m_driverClass;
	private boolean m_needSetThreadContextClassLoader;
	private String m_namespace;
	
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
		return getLocation( "/" );
	}
	
	/**
	 * @see org.eclipse.datatools.connectivity.oda.util.manifest.RuntimeInterface#getDriverFileLocation()
	 */
	public URL getDriverFileLocation( String filename ) throws IOException
	{
		return getLocation( filename );
	}
	
	private URL getLocation( String entry ) throws IOException
	{
		IBundle bundle = Platform.getBundle( m_namespace );
		URL url = bundle.getEntry( entry );
		return Platform.asLocalURL( url );
	}

	/**
	 * @see org.eclipse.datatools.connectivity.oda.util.manifest.RuntimeInterface#getLibraries()
	 */
	public String[] getLibraries()
	{
		// Eclipse core classes doesn't expose this information
		throw new UnsupportedOperationException();
	}
}
