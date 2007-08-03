/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.ws.util;

/**
 * 
 */

public class WSUtil
{

	public static final String EMPTY_STRING = ""; //$NON-NLS-1$

	/**
	 * Tests whether or not the given object is null
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isNull( Object object )
	{
		if ( object == null )
			return true;

		if ( object instanceof String )
			return ( (String) object ).trim( ).length( ) == 0;

		return false;
	}

	/**
	 * Gets an empty string if the given string is null, the string itself will
	 * be returned as is if otherwise
	 * 
	 * @param value
	 * @return
	 */
	public static String getNonNullString( String value )
	{
		return value == null ? EMPTY_STRING : value;
	}

	/**
	 * Parses the string argument as a signed decimal <code>long</code>.
	 * 
	 * @param string
	 * @return 0 if the argument is null or empty string
	 */
	public static long parseLong( String string )
	{
		if ( WSUtil.isNull( string ) )
			return 0;

		return Long.parseLong( string );
	}

}
