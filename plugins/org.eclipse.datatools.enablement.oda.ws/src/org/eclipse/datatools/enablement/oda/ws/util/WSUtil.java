/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.ws.util;

/**
 * 
 */

public class WSUtil
{

	public static boolean isNull( Object object )
	{
		if ( object == null )
			return true;

		if ( object instanceof String )
			return ( (String) object ).trim( ).length( ) == 0;

		return false;
	}

	public static String getNonNullString( String value )
	{
		return value == null ? "" : value;
	}

}
