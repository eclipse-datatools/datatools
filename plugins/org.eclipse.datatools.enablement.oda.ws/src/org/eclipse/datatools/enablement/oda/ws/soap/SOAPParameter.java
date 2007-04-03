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

package org.eclipse.datatools.enablement.oda.ws.soap;

import java.sql.Types;

/**
 * 
 */

public class SOAPParameter
{

	private int id;
	private String name;
	private int type;
	private String value;

	public SOAPParameter( int id, String name )
	{
		this( id, name, Types.VARCHAR );
	}

	public SOAPParameter( int id, String name, int type )
	{
		this( id, name, type, "" );
	}

	public SOAPParameter( int id, String name, String defaultValue )
	{
		this( id, name, Types.VARCHAR, defaultValue );
	}

	public SOAPParameter( int id, String name, int type, String defaultValue )
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.value = defaultValue;
	}

	public int getId( )
	{
		return id;
	}

	public String getName( )
	{
		return name;
	}

	public int getType( )
	{
		return type;
	}

	public String getDefaultValue( )
	{
		return value;
	}

	public void setDefaultValue( String value )
	{
		this.value = value;
	}

}
