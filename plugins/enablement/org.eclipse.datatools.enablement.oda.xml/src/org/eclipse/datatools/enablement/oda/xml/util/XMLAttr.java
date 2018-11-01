
/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.util;

/**
 * 
 */

public class XMLAttr implements IXMLPathNode
{
	private String name;
	private String pathString;
	
	public XMLAttr( String name )
	{
		assert name != null;
		this.name = name;
		this.pathString = "@" + name;
	}
	
	

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.enablement.oda.xml.util.IXMLPathNode#getPathString()
	 */
	public String getPathString( )
	{
		return pathString;
	}



	/* (non-Javadoc)
	 * @see org.eclipse.datatools.enablement.oda.xml.util.IXMLPathNode#getName()
	 */
	public String getName( )
	{
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode( )
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + name.hashCode( );
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass( ) != obj.getClass( ) )
			return false;
		XMLAttr other = (XMLAttr) obj;
		return name.equals( other.name );
	}
}
