
/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ws.util;

/**
 * 
 */

public class WSLeafNode
{
	private String name;
	private String type;
	private String prefix;
	
	/**
	 * 
	 * @return
	 */
	public String getName( )
	{
		return name;
	}

	
	public String getPrefix( )
	{
		return prefix;
	}

	
	public void setPrefix( String prefix )
	{
		this.prefix = prefix;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName( String name )
	{
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getType( )
	{
		return type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType( String type )
	{
		this.type = type;
	}
}
