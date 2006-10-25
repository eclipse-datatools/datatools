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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;

/**
 * Utility class for splitting query text into query and columns information
 */

public class QueryTextUtil
{

	private static final char QUERY_TEXT_DELIMITER = ':';

	private static final char COLUMNSINFO_BEGIN_DELIMITER = '{';

	/**
	 * 
	 */
	private QueryTextUtil( )
	{

	}

	/**
	 * 
	 * @param queryText
	 * @return
	 * @throws OdaException
	 */
	public static String getQuery( String queryText ) throws OdaException
	{
		return splitQueryText( queryText )[0];
	}

	/**
	 * 
	 * @param queryText
	 * @return
	 * @throws OdaException
	 */
	public static String getColumnsInfo( String queryText ) throws OdaException
	{
		assert queryText != null;
		return splitQueryText( queryText )[1];

	}

	/**
	 * 
	 */
	private static String[] splitQueryText( String queryText )
			throws OdaException
	{
		int delimiterIndex = -1;
		int columnsInfoBeginIndex = -1;
		
		String trimmedQueryText = queryText.trim( );
		
		String[] splittedQueryText = {
				"", ""
		};
		boolean inQuote = false;
		boolean isEscaped = false;
		char[] chars = trimmedQueryText.toCharArray( );
		
		for ( int i = 0; i < chars.length; i++ )
		{
			if ( chars[i] == '"' )
			{
				if ( !isEscaped )
					inQuote = !inQuote;
				else
					isEscaped = !isEscaped;
			}
			else if ( chars[i] == '\\' )
			{
				isEscaped = !isEscaped;
			}
			else if ( ( !inQuote ) && chars[i] == QUERY_TEXT_DELIMITER )
				delimiterIndex = i;
			else if ( ( !inQuote ) && chars[i] == COLUMNSINFO_BEGIN_DELIMITER )
			{
				columnsInfoBeginIndex = i;
				break;
			}
		}

		if ( inQuote )
			throw new OdaException( Messages.getString( "query_text_error" ) );

		if ( delimiterIndex != -1
				&& columnsInfoBeginIndex != -1 )
		{
			splittedQueryText[0] = trimmedQueryText.substring( 0, delimiterIndex )
					.trim( );
			splittedQueryText[1] = trimmedQueryText.substring( columnsInfoBeginIndex + 1,
					trimmedQueryText.length( )-1 )
					.trim( );
		}
		else if ( delimiterIndex == -1
				&& columnsInfoBeginIndex == -1 )
			splittedQueryText[0] = trimmedQueryText;
		else
			throw new OdaException( Messages.getString( "query_text_error" ) );

		return splittedQueryText;
	}
}
