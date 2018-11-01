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

package org.eclipse.datatools.enablement.oda.xml.ui.utils;

import java.util.Iterator;

import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.util.RelationInformation;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;

/**
 * The utility class to get relation info and process relation info
 */

public class XMLRelationInfoUtil
{
	private static final String EMPTY_STRING = "";     //$NON-NLS-1$

    /**
	 * connect the relationInfo with special regular expression "#-#"
	 * 
	 * @param originalStr
	 * @param newInfo
	 * @return
	 */
	public static String concatRelationInfo( String originalStr, String newInfo )
	{
		if ( originalStr == null || originalStr.trim( ).length( ) <= 0 )
			return newInfo;
		else
			return originalStr + "#-#" + newInfo; //$NON-NLS-1$
	}

	/**
	 * get the relation info of table
	 * 
	 * @param info
	 * @param tableName
	 * @return
	 */
	public static String getTableRelationInfo( String info, String tableName )
	{
		String[] splitStr = info.split( "\\Q#-#\\E" ); //$NON-NLS-1$
		for ( int i = 0; i < splitStr.length; i++ )
		{
			if ( splitStr[i].startsWith( tableName ) )
			{
				if ( splitStr[i].indexOf( Constants.QUERYTEXT_TABLE_NAME_DEFN_DELIMITER ) > 0 )
				{
					String relationInfo[] = splitStr[i].split( Constants.QUERYTEXT_TABLE_NAME_DEFN_DELIMITER );
					if ( relationInfo.length == 2 )
						return relationInfo[1];
				}
				else
					return splitStr[i];
			}
		}
		return null;
	}

	/**
	 * get the relation info's size
	 * 
	 * @param info
	 * @return
	 */
	private static int getRelationInfoSize( String info )
	{
		if ( info == null || info.trim( ).length( ) <= 0 )
			return 0;
		String[] splitStr = info.split( "\\Q#-#\\E" ); //$NON-NLS-1$
		return splitStr.length;
	}

	/**
	 * generate a unique table name for the query
	 * 
	 * @param info
	 * @return
	 */
	public static final String getUniqueName( String info )
	{
		String prefix = "table"; //$NON-NLS-1$
		StringBuffer buf = new StringBuffer( );
		int n = getRelationInfoSize( info );
		{
			buf.append( prefix ).append( n++ );
		}
		return buf.toString( )
				+ Constants.QUERYTEXT_TABLE_NAME_DEFN_DELIMITER + buf.toString( );
	}

	/**
	 * replace the table's relation information with new string
	 * 
	 * @param tableName
	 * @param newString
	 * @param relationInformation
	 * @return
	 */
	public static String replaceInfo( String tableName, String newString,
			String relationInformation )
	{
		if ( tableName == null || newString == null )
			return null;

		if ( relationInformation == null
				|| relationInformation.trim( ).length( ) == 0 )
			return newString;
		String[] splitStr = relationInformation.split( "\\Q#-#\\E" ); //$NON-NLS-1$
		for ( int i = 0; i < splitStr.length; i++ )
		{
			if ( splitStr[i].startsWith( tableName ) )
			{
				splitStr[i] = newString;
			}
		}
		return concatRealtionInfo( splitStr );
	}

	/**
	 * connect the splited infomation
	 * 
	 * @param splitStr
	 * @return
	 */
	private static String concatRealtionInfo( String[] splitStr )
	{
		String str = EMPTY_STRING; 
		for ( int i = 0; i < splitStr.length; i++ )
		{
			if ( splitStr[i] != null && splitStr[i].trim( ).length( ) > 0 )
			{
				str = splitStr[i];
				break;
			}
		}

		for ( int i = 1; i < splitStr.length; i++ )
		{
			if ( splitStr[i] != null && splitStr[i].trim( ).length( ) > 0 )
				str = concatRelationInfo( str, splitStr[i] );
		}
		return str;
	}

	/**
	 * get the table index from relation information
	 * 
	 * @param info
	 * @param tableName
	 * @return
	 */
	private static int getTableRelationInfoIndex( String info, String tableName )
	{
		String[] splitStr = info.split( "\\Q#-#\\E" ); //$NON-NLS-1$
		for ( int i = 0; i < splitStr.length; i++ )
		{
			if ( splitStr[i].startsWith( tableName ) )
				return i;
		}
		return -1;
	}

	/**
	 * change the xpath expression in relationInfo, if the xpath changed
	 * 
	 * @param tableName
	 * @param relationInfo
	 * @return
	 */
	public static String replaceXpathExpression( String tableName,
			String relationInfo, String xPath )
	{
		String infoStr = relationInfo;
		String[] splitStr = relationInfo.split( "\\Q#-#\\E" ); //$NON-NLS-1$
		String tableStr = splitStr[0];
		RelationInformation info = null;
		try
		{
			info = new RelationInformation( tableStr );
			String rootPath = info.getTableOriginalRootPath( tableName );
			tableStr = tableStr.replaceFirst( "\\Q" + rootPath + "\\E", xPath ); //$NON-NLS-1$ //$NON-NLS-2$
		}
		catch ( OdaException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace( );
		}
		int index = getTableRelationInfoIndex( infoStr, tableName );
		if ( index >= 0 )
		{
			splitStr[index] = tableStr;
			return concatRealtionInfo( splitStr );
		}
		else
			return infoStr;
	}
	
	/**
	 * get table name from query text
	 * @param queryText
	 * @return
	 */
	public static String getTableName( String queryText )
	{
		RelationInformation info = null;
		if ( queryText != null && queryText.trim( ).length( ) > 0 )
		{
			try
			{
				info = new RelationInformation( queryText );
			}
			catch ( OdaException e )
			{
				return null;
			}
			Iterator iter = info.getTableNames( );
			while ( iter.hasNext( ) )
			{
				return (String) iter.next( );
			}
			return null;
		}
		else
			return null;
	}

	/**
	 */
	public static String getXPathExpression( String queryText, String tableName )
	{
		RelationInformation info = null;
		if ( queryText != null && queryText.trim( ).length( ) > 0 )
		{
			try
			{
				info = new RelationInformation( queryText );
			}
			catch ( OdaException e )
			{
				return null;
			}
			return info.getTableOriginalRootPath( tableName );
		}
		else
			return null;
	}
	
	/**
	 * 
	 * @param control
	 * @param contextId
	 */
	public static void setSystemHelp( Control control, String contextId )
	{
		PlatformUI.getWorkbench( )
				.getHelpSystem( )
				.setHelp( control, contextId );
	}
}
