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

package org.eclipse.datatools.connectivity.oda.flatfile.util.querytextutil;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for extracting column names, original column names, and column
 * types from columns information
 */

public class ColumnsInfoUtil
{

	/**
	 * 
	 * 
	 */
	private ColumnsInfoUtil( )
	{

	}

	/**
	 * Extracts the column names from the columsInfo string
	 * 
	 * @param columnsInfo
	 * @return
	 */
	public static String[] getColumnNames( String columnsInfo )
	{
		assert columnsInfo != null;
		List columnsInfoVector = getColumnsInfoList( columnsInfo );
		String[] columnNames = new String[columnsInfoVector.size( )];
		for ( int i = 0; i < columnNames.length; i++ )
		{
			columnNames[i] = ( (String[]) columnsInfoVector.get( i ) )[0];
		}

		return columnNames;
	}

	/**
	 * Extracts the column type names from the columsInfo string
	 * 
	 * @param columnsInfo
	 * @return
	 */
	public static String[] getColumnTypeNames( String columnsInfo )
	{
		List columnsInfoVector = getColumnsInfoList( columnsInfo );
		String[] columnTypeNames = new String[columnsInfoVector.size( )];
		for ( int i = 0; i < columnTypeNames.length; i++ )
		{
			columnTypeNames[i] = ( (String[]) columnsInfoVector.get( i ) )[2];
		}
		return columnTypeNames;
	}

	/**
	 * Extracts the original column names from the columsInfo string
	 * 
	 * @param columnsInfo
	 * @return
	 */
	public static String[] getOriginalColumnNames( String columnsInfo )
	{
		List columnsInfoVector = getColumnsInfoList( columnsInfo );
		String[] originalColumnNames = new String[columnsInfoVector.size( )];
		for ( int i = 0; i < originalColumnNames.length; i++ )
		{
			originalColumnNames[i] = ( (String[]) columnsInfoVector.get( i ) )[1];
		}

		return originalColumnNames;
	}

	/**
	 * 
	 * @param columnsInfo
	 * @return
	 */
	private static List getColumnsInfoList( String columnsInfo )
	{
		List columnsInfoList = new ArrayList( );
		char[] columnsInfoChars = columnsInfo.toCharArray( );
		boolean isEscaped = false;
		String[] columnInfo = {
				"", "", ""
		};
		int index = 0;

		for ( int i = 0; i < columnsInfoChars.length; i++ )
		{
			if ( columnsInfoChars[i] == '"'
					|| columnsInfoChars[i] == '|' || columnsInfoChars[i] == ':'
					|| columnsInfoChars[i] == ':' || columnsInfoChars[i] == '<'
					|| columnsInfoChars[i] == '>' || columnsInfoChars[i] == '?'
					|| columnsInfoChars[i] == '*' || columnsInfoChars[i] == '{'
					|| columnsInfoChars[i] == '/' )
			{
				if ( isEscaped )
				{
					columnInfo[index] = columnInfo[index] + columnsInfoChars[i];
					isEscaped = !isEscaped;
				}
			}
			else if ( columnsInfoChars[i] == '\\' )
			{
				if ( isEscaped )
				{
					columnInfo[index] = columnInfo[index] + columnsInfoChars[i];
					isEscaped = !isEscaped;
				}
				else
					isEscaped = !isEscaped;
			}
			else if ( columnsInfoChars[i] == ',' )
			{
				if ( isEscaped )
				{
					columnInfo[index] = columnInfo[index] + columnsInfoChars[i];
					isEscaped = !isEscaped;
				}
				else
				{
					index++;
				}
			}
			else if ( columnsInfoChars[i] == ';'
					|| i == ( columnsInfoChars.length - 1 ) )
			{
				if ( isEscaped )
				{
					columnInfo[index] = columnInfo[index] + columnsInfoChars[i];
					isEscaped = !isEscaped;
				}
				else
				{

					if ( i == ( columnsInfoChars.length - 1 ) )
					{
						columnInfo[index] = columnInfo[index]
								+ columnsInfoChars[i];

						columnsInfoList.add( columnInfo );
					}
					else
					{
						columnsInfoList.add( columnInfo );
						index = 0;
						columnInfo = new String[3];
						columnInfo[0] = columnInfo[1] = columnInfo[2] = "";
					}
				}
			}
			else
			{
				columnInfo[index] = columnInfo[index] + columnsInfoChars[i];
			}
		}

		return columnsInfoList;
	}

	/**
	 * 
	 * @param charactor
	 * @return
	 */
	public static boolean isColumnsInfoKeyWord( char charactor )
	{
		return ( charactor == '"'
				|| charactor == ';' || charactor == ',' || charactor == '|'
				|| charactor == '\\' || charactor == '/' || charactor == '<'
				|| charactor == '>' || charactor == '*' || charactor == ':'
				|| charactor == '?' || charactor == '{' );
	}

}
