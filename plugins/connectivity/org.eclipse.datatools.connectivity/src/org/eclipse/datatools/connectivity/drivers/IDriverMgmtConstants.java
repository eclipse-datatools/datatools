/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.drivers;

import java.io.File;

/**
 * This file contains some constants used by the driver management code.
 * 
 * @author brianf
 */
public interface IDriverMgmtConstants {

	// base name of the driver management file
	public static final String DRIVER_FILE = "driverStorage.xml"; //$NON-NLS-1$

	// property prefix
	public static final String PROP_PREFIX = "org.eclipse.datatools.connectivity.drivers.";//$NON-NLS-1$

	// standard jar list delimiter
	public static final String PATH_DELIMITER_COMMA = ","; //$NON-NLS-1$
	public static final String PATH_DELIMITER_SEMICOLON = ";"; //$NON-NLS-1$
	public static final String PATH_DELIMITER = "" + File.pathSeparatorChar; //$NON-NLS-1$
	public static final char PATH_DELIMITER_CHAR = File.pathSeparatorChar;

	// jar list, class, and template type
	public static final String PROP_DEFN_JARLIST = "jarList"; //$NON-NLS-1$
	public static final String PROP_DEFN_CLASS = PROP_PREFIX
			+ "defnDriverClass"; //$NON-NLS-1$
	public static final String PROP_DEFN_TYPE = PROP_PREFIX + "defnType"; //$NON-NLS-1$
//	public static final String DRIVER_VALUES = "DRIVER_VALUES";
}
