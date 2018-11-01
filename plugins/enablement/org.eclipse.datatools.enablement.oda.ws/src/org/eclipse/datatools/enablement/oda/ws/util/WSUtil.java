/*******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
