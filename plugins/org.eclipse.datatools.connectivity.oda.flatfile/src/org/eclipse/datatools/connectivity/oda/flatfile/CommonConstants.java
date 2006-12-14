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

public final class CommonConstants
{

	public static final String CONN_HOME_DIR_PROP = "HOME"; //$NON-NLS-1$
	public static final String CONN_CHARSET_PROP = "CHARSET"; //$NON-NLS-1$
	public static final String CONN_INCLCOLUMNNAME_PROP = "INCLCOLUMNNAME"; //$NON-NLS-1$
	public static final String CONN_INCLTYPELINE_PROP = "INCLTYPELINE"; //$NON-NLS-1$
	public static final String CONN_DEFAULT_CHARSET = "UTF-8"; //$NON-NLS-1$
	public static final String CONN_DELIMITER_TYPE = "DELIMTYPE";

	public static final String DELIMITER_COMMA = "COMMA"; //$NON-NLS-1$
	public static final String DELIMITER_COMMA_VALUE = ",";
	// added for flatfiles seperated by semicolon
	public static final String DELIMITER_SEMICOLON = "SEMICOLON"; //$NON-NLS-1$
	public static final String DELIMITER_SEMICOLON_VALUE = ";";
	public static final String DELIMITER_PIPE = "PIPE";
	public static final String DELIMITER_PIPE_VALUE = "|";
	public static final String DELIMITER_TAB = "TAB";
	public static final String DELIMITER_TAB_VALUE = "\t";

	public static final String DELIMITER_SPACE = " "; //$NON-NLS-1$
	public static final char DELIMITER_DOUBLEQUOTE = '"'; //$NON-NLS-1$
	public static final String KEYWORD_SELECT = "SELECT"; //$NON-NLS-1$
	public static final String KEYWORD_FROM = "FROM"; //$NON-NLS-1$
	public static final String KEYWORD_AS = "AS"; //$NON-NLS-1$
	public static final String KEYWORD_ASTERISK = "*";//$NON-NLS-1$
	public static final String DRIVER_NAME = "ODA FLAT FILE DRIVER";//$NON-NLS-1$
	public static final String PRODUCT_VERSION = "3.0"; //$NON-NLS-1$
	public static final String INC_COLUMN_NAME_YES = "YES"; //$NON-NLS-1$
	public static final String INC_COLUMN_NAME_NO = "NO"; //$NON-NLS-1$
	public static final String INC_TYPE_LINE_YES = "YES"; //$NON-NLS-1$
	public static final String INC_TYPE_LINE_NO = "NO"; //$NON-NLS-1$

	public static final int MaxConnections = 0;
	public static final int MaxStatements = 0;

	/**
	 * Private constructor to ensure that the class cannot be instantiated.
	 */
	private CommonConstants( )
	{
	}

	/**
	 * Get the delimiter value.
	 * 
	 * @param delimiterName
	 * @return
	 */
	public static String getDelimiterValue( String delimiterName )
	{
		if ( delimiterName.equalsIgnoreCase( CommonConstants.DELIMITER_COMMA ) )
			return CommonConstants.DELIMITER_COMMA_VALUE;
		else if ( delimiterName.equalsIgnoreCase( CommonConstants.DELIMITER_SEMICOLON ) )
			return CommonConstants.DELIMITER_SEMICOLON_VALUE;
		else if ( delimiterName.equalsIgnoreCase( CommonConstants.DELIMITER_PIPE ) )
			return CommonConstants.DELIMITER_PIPE_VALUE;
		else if ( delimiterName.equalsIgnoreCase( CommonConstants.DELIMITER_TAB ) )
			return CommonConstants.DELIMITER_TAB_VALUE;
		else
			return null;

	}
	
	/**
	 * To see if the delimiter name given is legal or not
	 * 
	 * @param delimiterName
	 * @return
	 */
	public static boolean isValidDelimiterName( String delimiterName )
	{
		if ( delimiterName.equalsIgnoreCase( CommonConstants.DELIMITER_COMMA ) )
			return true;
		else if ( delimiterName.equalsIgnoreCase( CommonConstants.DELIMITER_SEMICOLON ) )
			return true;
		else if ( delimiterName.equalsIgnoreCase( CommonConstants.DELIMITER_PIPE ) )
			return true;
		else if ( delimiterName.equalsIgnoreCase( CommonConstants.DELIMITER_TAB ) )
			return true;
		else
			return false;
	}
	
}