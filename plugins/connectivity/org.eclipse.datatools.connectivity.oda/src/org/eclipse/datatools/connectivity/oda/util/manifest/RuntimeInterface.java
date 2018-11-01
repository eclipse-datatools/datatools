/*
 *************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.util.manifest;

import java.io.IOException;
import java.net.URL;

/**
 * Encapsulates the runtime interface specific configurations
 * of an ODA driver that implements a data source extension.
 */
public abstract class RuntimeInterface
{
	/**
	 * Implements C/C++ runtime interface.
	 */
	public static final int C_TYPE = 0;
	
	/**
	 * Implements Java runtime interface.
	 */
	public static final int JAVA_TYPE = 1;
	
	/**
	 * Returns the interface type of the runtime.  Either RuntimeInterface.C_TYPE 
	 * or RuntimeInterface.JAVA_TYPE.
	 * @return	runtime interface type: RuntimeInterface.C_TYPE or 
	 * 			RuntimeInterface.JAVA_TYPE.
	 */
	public abstract int getInterfaceType();
	
	/**
	 * Returns the library location for the current OS platform.
	 * @return	the library location URL.
	 * @throws IOException	if an IO error occurs.
	 */
	public abstract URL getLibraryLocation() throws IOException;
	
	/**
	 * Returns the driver file location for the current OS platform.
	 * @param filename	the name of the driver file.
	 * @return	the driver file location URL.
	 * @throws IOException	if an IO error occurs.
	 */
	public abstract URL getDriverFileLocation( String filename ) throws IOException;
	
	/**
	 * Returns the list of libraries for the current OS platform.
	 * @return	the list of libraries.
	 */
	public abstract String[] getLibraries();
}
