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
package org.eclipse.datatools.enablement.oda.xml.util.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.enablement.oda.xml.util.SaxParserUtil;
import org.eclipse.datatools.enablement.oda.xml.util.UtilConstants;

/**
 * This class is a Utility class which is used to help UI to populate the list of possible 
 * XPath Expressions. 
 */

final public class XPathPopulationUtil
{
	private static final String XPATH_WILDCARD = "*"; //$NON-NLS-1$
	private static final String XPATH_ATTR_HEADER_WITH_SLASH = "/@"; //$NON-NLS-1$
	private static final String XPATH_ATTR_HEADER_WITH_SQUARE_PATTERN = "\\Q[@\\E"; //$NON-NLS-1$
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
    private static final String FORWARD_SLASH = "/"; //$NON-NLS-1$
	
	/**
	 * This method is used to populate the possible root path expressions List
	 * @param absolutePath must be the absolute path of root path 
	 * @return
	 */
	public static List populateRootPath( String absolutePath )
	{
		assert absolutePath != null;
		
		List result = new ArrayList();
		result.add( absolutePath );
		if( absolutePath.startsWith(UtilConstants.XPATH_SLASH) )
			absolutePath = absolutePath.replaceFirst(UtilConstants.XPATH_SLASH, EMPTY_STRING);
		String[] xPathFrags = absolutePath.split(UtilConstants.XPATH_SLASH);
		
		for ( int i = 1; i < xPathFrags.length;i++)
		{
			String temp = UtilConstants.XPATH_DOUBLE_SLASH;
			temp = addXPathFragsToAString( xPathFrags, i, temp );
			result.add( temp );
		}
		return result;
	}

	/**
	 * This method is used to populate the possible root path expressions List
	 * 
	 * @param absolutePath
	 *            must be the absolute path of root path
	 * @return List
	 */
	public static List getPathList( String absolutePath )
	{
		if ( absolutePath == null )
			return null;

		List result = new ArrayList( );
		result.add( absolutePath );
		if ( absolutePath.startsWith( UtilConstants.XPATH_SLASH ) )
			absolutePath = absolutePath.replaceFirst( UtilConstants.XPATH_SLASH,
					EMPTY_STRING );
		String[] xPathFrags = absolutePath.split( UtilConstants.XPATH_SLASH );
		if ( xPathFrags.length > 0 )
		{
			String temp = UtilConstants.XPATH_DOUBLE_SLASH;
			temp = addXPathFragsToAString( xPathFrags,
					xPathFrags.length - 1,
					temp );
			result.add( temp );
		}
		return result;
	}

	/**
	 * This method appends the items starting from certain index 
	 * in an array to a String to build an XPath expression
	 * 
	 * @param xPathFrags the String array
	 * @param i the index
	 * @param s the string
	 * @return
	 */
	private static String addXPathFragsToAString( String[] xPathFrags, int i, String s )
	{
		StringBuffer buf = new StringBuffer( s );
		for( int j = i; j < xPathFrags.length;j++)
		{
			if( j < xPathFrags.length - 1 )
				buf.append( xPathFrags[j] + UtilConstants.XPATH_SLASH );
			else
				buf.append( xPathFrags[j] );
		}
		return buf.toString( );
	}
	
	/**
	 * This method is used to populate the possible column path expressions List
	 *  
	 * @param rootPath the root path of the table the column in, must be absolute path.
	 * @param columnPath the absolute column path.
	 * @return
	 */
	public static String populateColumnPath( String rootPath, String columnPath )
	{	
		assert rootPath != null;
		assert columnPath != null;
	
		rootPath = SaxParserUtil.removeRedundantParentAxis( rootPath );
		if( rootPath.equals( "//" )||rootPath.equals( "//*" )) //$NON-NLS-1$ //$NON-NLS-2$
		{
			String[] temp = columnPath.split( "\\Q/\\E" ); //$NON-NLS-1$
			if( temp.length <=2 )
				return null;
			else
			{
				StringBuffer result = new StringBuffer();
				for(int i = 2; i < temp.length; i++)
				{
					result.append( FORWARD_SLASH+temp[i] );
				}
				return result.toString( );
			}
		}
			
		if ( isDescendantOrSelf( columnPath, rootPath ) )
		{
			return columnPath.replaceFirst("\\Q"+rootPath+"\\E", EMPTY_STRING); //$NON-NLS-1$ //$NON-NLS-2$
		}else
		{
			return getXPathExpression( rootPath, columnPath );
		}
	}
	
	/**
	 * Retrieves whether the candidate is DescendantOrSelf of the target
	 * 
	 * @param candidate
	 * @param target
	 * @return
	 */
	private static boolean isDescendantOrSelf( String candidate, String target )
	{
		if ( !candidate.startsWith( target ) )
			return false;

		String[] candidateFgt = candidate.split( UtilConstants.XPATH_SLASH );
		String[] targetFgt = target.split( UtilConstants.XPATH_SLASH );

		for ( int i = 0; i < targetFgt.length; i++ )
		{
			if ( !targetFgt[i].equals( candidateFgt[i] ) )
				return false;
		}

		return true;
	}

	/**
	 * @param rootPath
	 * @param columnPath
	 * @return
	 */
	private static String getXPathExpression( String rootPath, String columnPath )
	{
		String[] rootPathFrags = rootPath.replaceAll(UtilConstants.XPATH_ELEM_INDEX_PATTERN, EMPTY_STRING).split(UtilConstants.XPATH_SLASH);
		String[] columnPathFrags = columnPath.replaceAll(UtilConstants.XPATH_ELEM_INDEX_PATTERN, EMPTY_STRING).split(UtilConstants.XPATH_SLASH);
		
		//The length of rootPathFrags and columnPathFrags should larger than 2,
		//for the simplest path would be /elementName, which, if being splitted by "/",
		//would produces a 2 element string array.
		if( rootPathFrags.length < 2 || columnPathFrags.length < 2 )
			return null;
		
		//The position which starting the common part of root path and column path in columnPathFrags array.
		int startingIndex = 0;
		
		//The position which ending the common part of root path and column path in columnPathFrags array 
		int endingIndex = 0;
		
		//If rootPath starting with "//", then mean the rootPath is a relative path, else,
		//the rootPath is an absolute path
		if( !rootPath.startsWith(UtilConstants.XPATH_DOUBLE_SLASH))
		{
			//If rootPath is absolute path, then the startingIndex must be 1.If not then
			//the rootPath and columnPath has nothing in common.
			if( !is2FragmentsEqual(columnPathFrags[1],rootPathFrags[1]))
				return null;
			else
			{
				rootPathFrags = (UtilConstants.XPATH_SLASH+rootPath).split(UtilConstants.XPATH_SLASH);
			}
		}
		
		assert rootPathFrags.length >= 3;
		
		String commonRoot = rootPathFrags[2];
		
		startingIndex = getStartingIndex( columnPathFrags, commonRoot );
		
		//If startingIndex == 0, that means the given column path do not have common
		if( columnPathFrags.length < startingIndex+1 || startingIndex == 0)
			return null;

		endingIndex = getEndingIndex( rootPathFrags, columnPathFrags, startingIndex );
		
		return populateXpathExpression( columnPath, rootPathFrags, startingIndex, endingIndex );
	}

	/**
	 * @param columnPathFrags
	 * @param startingIndex
	 * @param commonRoot
	 * @return
	 */
	private static int getStartingIndex( String[] columnPathFrags, String commonRoot )
	{
		int startingIndex = 0;
		for( int i = 1; i < columnPathFrags.length; i++)
		{
			if( is2FragmentsEqual(commonRoot,columnPathFrags[i]))
			{
				startingIndex = i;
				break;
			}
		}
		return startingIndex;
	}
	
	/**
	 * 
	 * @param frag1
	 * @param frag2
	 * @return
	 */
	private static boolean is2FragmentsEqual(String frag1, String frag2)
	{
		if( frag1.equals(XPATH_WILDCARD)||frag2.equals(XPATH_WILDCARD))
			return true;
		else
			return frag1.equals(frag2);
	}
	
	/**
	 * @param rootPathFrags
	 * @param columnPathFrags
	 * @param startingIndex
	 * @param endingIndex
	 * @return
	 */
	private static int getEndingIndex( String[] rootPathFrags, String[] columnPathFrags, int startingIndex )
	{
		int start = startingIndex;
		int endingIndex = 0;
		for( int i = startingIndex+1; i < columnPathFrags.length && i - startingIndex + 2< rootPathFrags.length; i++ )
		{
			if( !is2FragmentsEqual(columnPathFrags[i],rootPathFrags[i - startingIndex + 2]))
			{
				endingIndex = i - 1;
				break;
			}
			start = i;
		}

		if( endingIndex == 0 && startingIndex!= 0)
		{
			endingIndex = start;
		}
		return endingIndex;
	}

	/**
	 * @param columnPath
	 * @param rootPathFrags
	 * @param startingIndex
	 * @param endingIndex
	 * @return
	 */
	private static String populateXpathExpression( String columnPath, String[] rootPathFrags, int startingIndex, int endingIndex )
	{
		StringBuffer result = new StringBuffer();
		
		int fetchBackLevel = rootPathFrags.length - 3 - (endingIndex - startingIndex);
		for( int i = 0; i < fetchBackLevel; i ++)
		{
			result.append("../"); //$NON-NLS-1$
		}
		
		return addXPathFragsToAString( columnPath.replaceAll( XPATH_ATTR_HEADER_WITH_SQUARE_PATTERN,
				XPATH_ATTR_HEADER_WITH_SLASH )
				.split( UtilConstants.XPATH_SLASH ),
				endingIndex + 1,
				result.toString( ) );
	}
}
