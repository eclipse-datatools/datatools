/*
 *******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile;

/**
 * The class that defines package-wide static constants.
 */

final class CommonConstants
{
	public static final String DELIMITER_COMMA = ","; //$NON-NLS-1$
	public static final String DELIMITER_SPACE = " "; //$NON-NLS-1$
	public static final String DELIMITER_DOUBLEQUOTE = "\""; //$NON-NLS-1$
	public static final String KEYWORD_SELECT = "SELECT"; //$NON-NLS-1$
	public static final String KEYWORD_FROM = "FROM"; //$NON-NLS-1$
	public static final String KEYWORD_AS = "AS"; //$NON-NLS-1$
	public static final String KEYWORD_ASTERISK = "*";//$NON-NLS-1$
	public static final String DRIVER_NAME = "ODA FLAT FILE DRIVER";//$NON-NLS-1$
	public static final String PRODUCT_VERSION = "3.0";
	public static final int MaxConnections = 0;
	public static final int MaxStatements = 0;	

	/**
	 * Private constructor to ensure that the class cannot be instantiated.
	 */
	private CommonConstants( )
	{
	}
}