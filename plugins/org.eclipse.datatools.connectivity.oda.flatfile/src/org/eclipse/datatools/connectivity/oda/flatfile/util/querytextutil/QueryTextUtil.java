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

	private static final char queryTextDelimiter = ':';

	private static final char columnsInfoStartSymbol = '{';

	private static final char columnsInfoEndSymbol = '}';

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
	 * @param queryText
	 * @return
	 * @throws OdaException
	 */
	private static String[] splitQueryText( String queryText )
			throws OdaException
	{
		String[] splittedQueryText = {
				"", ""
		};
		char[] queryTextChars = queryText.toCharArray( );
		boolean isQuoteStart = false;
		boolean isQuoteEnd = false;
		int queryTextDelimiterIndex = 0;
		int columnsInfoStartIndex = 0;
		int columnsInfoEndIndex = 0;

		for ( int i = 0; i < queryTextChars.length; i++ )
		{
			if ( queryTextChars[i] == '"' )
			{
				if ( !isQuoteStart && !isQuoteEnd )
				{
					isQuoteStart = !isQuoteStart;
				}
				else if ( isQuoteStart && !isQuoteEnd )
				{
					isQuoteStart = !isQuoteStart;
				}

			}
			else if ( queryTextChars[i] == queryTextDelimiter )
			{
				if ( !isQuoteStart && !isQuoteEnd )
				{
					queryTextDelimiterIndex = i;
				}
			}
			else if ( queryTextChars[i] == columnsInfoStartSymbol )
			{
				if ( !isQuoteStart && !isQuoteEnd )
				{
					columnsInfoStartIndex = i;
				}
			}
			else if ( queryTextChars[i] == columnsInfoEndSymbol )
			{
				if ( !isQuoteStart && !isQuoteEnd )
				{
					columnsInfoEndIndex = i;
					break;
				}
			}
		}

		if ( queryTextDelimiterIndex != 0
				&& columnsInfoStartIndex > queryTextDelimiterIndex
				&& columnsInfoEndIndex > columnsInfoStartIndex )
		{
			splittedQueryText[0] = queryText.substring( 0,
					queryTextDelimiterIndex ).trim( );
			splittedQueryText[1] = queryText.substring( columnsInfoStartIndex + 1,
					columnsInfoEndIndex )
					.trim( );
		}
		else if ( queryTextDelimiterIndex == 0 )
		{
			splittedQueryText[0] = queryText.trim( );
		}
		else
		{
			throw new OdaException( Messages.getString( "query_text_error" ) );
		}

		return splittedQueryText;
	}

}
