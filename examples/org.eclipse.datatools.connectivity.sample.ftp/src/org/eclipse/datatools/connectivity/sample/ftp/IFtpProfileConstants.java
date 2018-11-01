/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf & mdow - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.ftp;

/**
 * This class exposes the property names for FTP connection profiles.
 * 
 * @author mdow and brianf
 */
public interface IFtpProfileConstants {

	// Property name for the base path of the file
	public static final String BASE_PATH = "basePath"; //$NON-NLS-1$

	// Property name for the name of the file
	public static final String FILE_NAME = "name"; //$NON-NLS-1$

	// Property name for the message delimiter within the file
	public static final String MESSAGE_DELIMITER = "delimiter"; //$NON-NLS-1$

	// The id of the file connection factory
	public static final String FTP_FACTORY_ID = "org.eclipse.datatools.connectivity.sample.ftp.profile.connectionFactory"; //$NON-NLS-1$
	
	// the name of the FTP server
	public static final String FTP_SERVER = "serverName"; //$NON-NLS-1$
	public static final String FTP_PORT = "port"; //$NON-NLS-1$
	
	// the user name and password for the FTP server
	public static final String FTP_UID = "userId"; //$NON-NLS-1$
	public static final String FTP_PWD = "password"; //$NON-NLS-1$
	
	public static final String FTP_PROVIDER_ID = "org.eclipse.datatools.connectivity.sample.ftp.profile"; //$NON-NLS-1$
	
	// Phantom property for directory
	public static final String FTP_PHANTOM_PROPERTY_IS_DIRECTORY = "org.eclipse.datatools.connectivity.ftp.isdirectory";	
}