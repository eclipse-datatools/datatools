package org.eclipse.datatools.enablement.oda.xml.util;



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
/**
 * 
 */

public class XMLPath
{	
	private IXMLPathNode[] path = null;
	
	private String pathString = null;
	
	public XMLPath( IXMLPathNode[] path )
	{
		assert path != null ;
		
		this.path = path;
		
		StringBuffer sb = new StringBuffer( );
		for ( int i=0; i<path.length; i++)
		{
			sb.append( "/" ).append( path[i].getPathString( ) );
		}
		pathString = sb.toString( );
	}
	
	public IXMLPathNode[] getPath( )
	{
		return path;
	}
	
	public String getPathString( )
	{
		return pathString;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode( )
	{
		return pathString.hashCode( );
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
		XMLPath other = (XMLPath) obj;
		return pathString.equals( other.pathString );
	}
	
	
}
