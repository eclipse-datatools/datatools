
/*******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *******************************************************************************/
package org.eclipse.datatools.connectivity.oda.flatfile.util;

import java.text.ParseException;
import java.util.Date;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;

import java.text.DateFormat;
import com.ibm.icu.util.ULocale;

/**
 * A utility class. The convert method converts the source object into an Date
 * object given specified type. If no reasonable conversion can be made, throw a
 * OdaException.
 */
public final class DateUtil
{

	// Defalult Locale, if we have any problem parse string to date for Locale.getDefault()
	// we will try to parse it for Locale.US
	private static ULocale DEFAULT_LOCALE = ULocale.US;

	// Default Date/Time Style 
	private static int DEFAULT_DATE_STYLE = DateFormat.MEDIUM;

	public static long count = 0;

	/**
	 * Number -> Date
	 * 		new Date((long)Number)
	 * String -> Date
	 * 		toDate(String)  
	 * @param source
	 * @return
	 * @throws OdaException
	 */
	public static synchronized Date toDate( Object source ) throws OdaException
	{
		if ( source == null )
			return null;

		if ( source instanceof Date )
		{
			return new Date( ( (Date) source ).getTime( ) );
		}
		else if ( source instanceof String )
		{
			return toDate( (String) source );
		}
		else
		{
			throw new OdaException( Messages.getString( "dateUtil.ConvertFails" ) + source.toString( ) ); //$NON-NLS-1$
		}
	}

	/**
	 * convert String with the specified locale to java.util.Date
	 * 
	 * @param source
	 *            the String to be convert
	 * @param locate
	 * 			  the locate of the string
	 * @return result Date
	 */
	public synchronized static Date toDate( String source, ULocale locale )
			throws OdaException
	{
		if ( source == null )
			return null;

		DateFormat dateFormat = null;
		Date resultDate = null;
		
		boolean existTime = source.matches( ".*[0-9]+:[0-9]+:[0-9]+.*" ) //$NON-NLS-1$
				|| source.matches( ".*[0-9]+:[0-9]+.*" ); //$NON-NLS-1$

		for ( int i = DEFAULT_DATE_STYLE; i <= DateFormat.SHORT; i++ )
		{
			for ( int j = DEFAULT_DATE_STYLE; j <= DateFormat.SHORT; j++ )
			{
				dateFormat = DateFormatFactory.getDateTimeInstance( i, j, locale );
				try
				{
					resultDate = dateFormat.parse( source );
					return resultDate;
				}
				catch ( ParseException e1 )
				{
				}
			}

			// only Date, no Time 
			if ( !existTime )
			{
				dateFormat = DateFormatFactory.getDateInstance( i, locale );
				try
				{
					resultDate = dateFormat.parse( source );
					return resultDate;
				}
				catch ( ParseException e1 )
				{
				}
			}
		}

		// for the String can not be parsed, throws a OdaException
		if ( resultDate == null )
		{
			throw new OdaException( Messages.getString("dateUtil.ConvertFails")+source.toString( )); //$NON-NLS-1$
		}

		// never access here
		return resultDate;
	}

	/**
	 * Convert String without specified locale to java.util.Date
	 * Try to format the given String for JRE default Locale,
	 * if it fails, try to format the String for Locale.US 
	 * @param source
	 *            the String to be convert
	 * @param locate
	 * 			  the locate of the string
	 * @return result Date
	 */
	private static Date toDate( String source ) throws OdaException
	{
		try
		{
			return toDateISO8601( source );
		}
		catch ( OdaException e )
		{
			try
			{
				// format the String for JRE default locale
				return toDate( source, ULocale.getDefault( ) );
			}
			catch ( OdaException use )
			{
				// format the String for Locale.US
				return toDate( source, DEFAULT_LOCALE );
			}
		}
	}
	
	/**
	 * convert String with ISO8601 date format to java.util.Date
	 * 
	 * @param source
	 *            the String to be convert
	 * @param locate
	 *            the locate of the string
	 * @return result Date
	 */
	private static Date toDateISO8601( String source ) throws OdaException
	{
		Date resultDate = null;

		try
		{
			resultDate = DateFormatISO8601.parse( source );
			return resultDate;
		}
		catch ( ParseException e1 )
		{
			throw new OdaException( Messages.getString( "dateUtil.ConvertFails" ) + source.toString( ) ); //$NON-NLS-1$
		}
	}
}
