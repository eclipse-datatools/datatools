/*******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a utility class which is used to provide some functionality 
 * that are used in instance of ISaxParserConsumer
 */
public class SaxParserUtil
{
	public static final String TEMPCOLUMNNAMEPREFIX = "-$TEMP_XML_COLUMN$-";
	public static final String ROOTTEMPCOLUMNNAMEPREFIX = "-$TEMP_XML_COLUMN_ROOT$-";

	private SaxParserUtil()
	{
		
	};

	/**
	 * Remove the redundant ".."
	 * 
	 * @param path
	 * @return
	 */
	public static String removeRedundantParentAxis( String path )
	{
		if (path == null
				|| path.equals( "" )
				|| path.indexOf( ".." ) == -1 // not contain ..
				|| path.indexOf( "///" ) != -1) //invalid path
		{
			return path;
		}
		
		//to differentiate "//" and "/"
		String[] splits = path.replaceAll( "//", "/<>/" ).split("/");
		if (splits.length <= 1) // "/" or just one path element 
		{
			return path;
		}
		List pathElements = new ArrayList();
		pathElements.add( splits[0] );
		for (int i = 1; i < splits.length; i++)
		{
			if (splits[i].equals( ".." ))
			{
				if (pathElements.size( ) > 0)
				{
					Object last = pathElements.get( pathElements.size( ) - 1 );
					if (last.equals( "" )
							||last.equals( ".." )
							|| last.equals( "<>" ))
					{
						//not a redundant one
						pathElements.add( splits[i] );
					}
					else
					{
						//redundant one and it's previous element should be removed
						pathElements.remove( pathElements.size( ) - 1 );
					}
				}
				else
				{
					pathElements.add( splits[i] );
				}
			}
			else
			{
				pathElements.add( splits[i] );
			}
		}
		StringBuffer result = new StringBuffer("");
		
		if (pathElements.get( 0 ).equals( "" ) //start with "/", because Spits of "/A/B" be [][A][B]
				&& !pathElements.get( 1 ).equals( "<>" ) // but not start with "//"
				) 
		{
			result.append( "/" );
		}
		else
		{
			result.append( pathElements.get( 0 ) );
		}
		for (int i = 1; i <= pathElements.size( ) - 1; i++)
		{
			if (pathElements.get( i ).equals( "<>" ))
			{
				//restore to "//"
				result.append( "//" );
			}
			else
			{
				result.append( "/" ).append( pathElements.get( i ) );
			}
		}
		return result.toString( );
	}
	
	/**
	 * Create a temp column name. The temp column are used for filtering.
	 * 
	 * @param index
	 * @return
	 */
	static String createTempColumnName( int index )
	{
		return TEMPCOLUMNNAMEPREFIX + index;
	}
	
	/**
	 * Create a temp column name for root filter. The temp column are used for filtering.
	 * 
	 * @param index
	 * @return
	 */
	static String createTableRootTempColumnNameForFilter( )
	{
		return ROOTTEMPCOLUMNNAMEPREFIX;
	}
	/**
	 * Return if the given column name stands for a temp column.
	 * 
	 * @param name
	 * @return
	 */
	static boolean isTempColumn( String name )
	{
		if( name == null )
			return false;
		else
			return name.startsWith( TEMPCOLUMNNAMEPREFIX ) || name.startsWith( ROOTTEMPCOLUMNNAMEPREFIX );
	}
}
