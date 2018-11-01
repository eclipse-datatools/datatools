/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
    public static final String TEMPCOLUMNNAMEPREFIX = "-$TEMP_XML_COLUMN$-";//$NON-NLS-1$
	public static final String ROOTTEMPCOLUMNNAMEPREFIX = "-$TEMP_XML_COLUMN_ROOT$-";//$NON-NLS-1$

	private static final String EMPTY_STRING = ""; 		//$NON-NLS-1$
    private static final String FORWARD_SLASH = "/"; 	//$NON-NLS-1$
    private static final String DOUBLE_SLASH = "//"; 	//$NON-NLS-1$
    private static final String ANGLE_BRACKET = "<>"; 	//$NON-NLS-1$

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
				|| path.equals( EMPTY_STRING )
				|| path.indexOf( ".." ) == -1 // not contain ..		//$NON-NLS-1$
				|| path.indexOf( "///" ) != -1) //invalid path		//$NON-NLS-1$
		{
			return path;
		}
		
		//to differentiate "//" and "/"
		String[] splits = path.replaceAll( DOUBLE_SLASH, "/<>/" ).split( FORWARD_SLASH );	//$NON-NLS-1$
		if (splits.length <= 1) // "/" or just one path element 
		{
			return path;
		}
		List pathElements = new ArrayList();
		pathElements.add( splits[0] );
		for (int i = 1; i < splits.length; i++)
		{
			if (splits[i].equals( ".." ))	//$NON-NLS-1$
			{
				if (pathElements.size( ) > 0)
				{
					Object last = pathElements.get( pathElements.size( ) - 1 );
					if (last.equals( EMPTY_STRING )
							||last.equals( ".." )	//$NON-NLS-1$
							|| last.equals( ANGLE_BRACKET ))
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
		StringBuffer result = new StringBuffer(EMPTY_STRING);
		
		if (pathElements.get( 0 ).equals( EMPTY_STRING ) //start with "/", because Spits of "/A/B" be [][A][B]
				&& !pathElements.get( 1 ).equals( ANGLE_BRACKET ) // but not start with "//"
				) 
		{
			result.append( FORWARD_SLASH );
		}
		else
		{
			result.append( pathElements.get( 0 ) );
		}
		for (int i = 1; i <= pathElements.size( ) - 1; i++)
		{
			if (pathElements.get( i ).equals( ANGLE_BRACKET ))
			{
				//restore to "//"
				result.append( DOUBLE_SLASH );
			}
			else
			{
				result.append( FORWARD_SLASH ).append( pathElements.get( i ) );
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
	
	/**
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
    static boolean isTwoValueMatch( Object value1, Object value2 )
	{
		if ( value1 == null && value2 == null )
			return true;
		if ( value1 != null && value2 == null )
			return false;
		if ( value1 == null && value2 != null )
			return false;
		return value1.equals( value2 );
	}
}
