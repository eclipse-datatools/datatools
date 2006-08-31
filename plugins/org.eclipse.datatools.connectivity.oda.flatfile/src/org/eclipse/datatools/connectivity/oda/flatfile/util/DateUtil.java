
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
package org.eclipse.datatools.connectivity.oda.flatfile.util;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;

/**
 * A utility function The convert method converts the source object, which can
 * be any supported data type, into an object given specified type. If no
 * reasonable conversion can be made, throw a OdaException.
 */
public final class DateUtil
{

	// Defalult Locale, if we have any problem parse string to date for Locale.getDefault()
	// we will try to parse it for Locale.US
	private static ULocale DEFAULT_LOCALE = ULocale.US;

	// Default Date/Time Style 
	private static int DEFAULT_DATE_STYLE = DateFormat.MEDIUM;

	//all SimpleDateFormatter of ICU
	private static SimpleDateFormat[] simpleDateFormatter = null;
	
	//all SimpleDateFormatter of ICU
	private static SimpleDateFormat[] simpleTimeFormatter = null;
	
	public static long count = 0;

	static
	{
		// date format pattern defined in ISO8601
		// notice the order is significant.
		String[] dateFormatPattern = {
				"yyyy-MM-dd HH:mm:ss.S z",
				"yyyy-MM-dd HH:mm:ss.Sz",
				"yyyy-MM-dd HH:mm:ss.S",
				"yyyy-MM-dd HH:mm:ss z",
				"yyyy-MM-dd HH:mm:ssz",
				"yyyy-MM-dd HH:mm:ss",
				"yyyy-MM-dd HH:mm z",
				"yyyy-MM-dd HH:mmz",
				"yyyy-MM-dd HH:mm",
				"yyyy-MM-dd",
				"yyyy-MM",
				"yyyy",
		};
		String[] timeFormatPattern = {
				"HH:mm:ss.S z",
				"HH:mm:ss.Sz",
				"HH:mm:ss.S",
				"HH:mm:ss z",
				"HH:mm:ssz",
				"HH:mm:ss",
				"HH:mm z",
				"HH:mmz",
				"HH:mm",
		};
		simpleDateFormatter = new SimpleDateFormat[dateFormatPattern.length];
		for ( int i = 0; i < dateFormatPattern.length; i++ )
		{
			simpleDateFormatter[i] = new SimpleDateFormat( dateFormatPattern[i] );
			simpleDateFormatter[i].setLenient( false );
		}
		
		simpleTimeFormatter = new SimpleDateFormat[dateFormatPattern.length];
		for ( int i = 0; i < timeFormatPattern.length; i++ )
		{
			simpleTimeFormatter[i] = new SimpleDateFormat( timeFormatPattern[i] );
			simpleTimeFormatter[i].setLenient( false );
		}
	}

	/**
	 * Number -> Date
	 * 		new Date((long)Number)
	 * String -> Date
	 * 		toDate(String)  
	 * @param source
	 * @return
	 * @throws OdaException
	 */
	public static Date toDate( Object source ) throws OdaException
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
			throw new OdaException( Messages.getString( "DateUtil.ConvertFails" ) + source.toString( ) ); //$NON-NLS-1$
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
	public static Date toDate( String source, ULocale locale )
			throws OdaException
	{
		if ( source == null )
			return null;

		DateFormat dateFormat = null;
		Date resultDate = null;
		
		boolean existTime = source.matches( ".*[0-9]+:[0-9]+:[0-9]+.*" )
				|| source.matches( ".*[0-9]+:[0-9]+.*" );

		for ( int i = DEFAULT_DATE_STYLE; i <= DateFormat.SHORT; i++ )
		{
			for ( int j = DEFAULT_DATE_STYLE; j <= DateFormat.SHORT; j++ )
			{
				dateFormat = DateFormatHolder.getDateTimeInstance( i, j, locale );
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
				dateFormat = DateFormatHolder.getDateInstance( i, locale );
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
			throw new OdaException( Messages.getString("DateUtil.ConvertFails")+source.toString( )); //$NON-NLS-1$
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

		source = source.replaceFirst( "T", " " );
		
		boolean onlyTime = source.matches( "[0-9]+:[0-9]+:[0-9]+.*" )
				|| source.matches( "[0-9]+:[0-9]+.*" );
		
		SimpleDateFormat[] simpleFormatter = null;
		
		if(onlyTime)
			simpleFormatter = simpleTimeFormatter;
		else
			simpleFormatter = simpleDateFormatter;
			
		
		for ( int i = 0; i < simpleFormatter.length - 1; i++ )
		{
			try
			{
				resultDate = simpleFormatter[i].parse( source );
				return resultDate;
			}
			catch ( ParseException e1 )
			{
			}
		}
//		Only string matching "[0-9]+" can be applied to simpleDateFormatter.
		if ( source.length( ) <= 4 && source.matches( "[0-9]+" ) )
		{
			try
			{
				resultDate = simpleDateFormatter[simpleDateFormatter.length - 1].parse( source );
				return resultDate;
			}
			catch ( ParseException e1 )
			{
			}
		}
		// for the String can not be parsed, throws a OdaException
		if ( resultDate == null )
		{
			throw new OdaException( Messages.getString("DateUtil.ConvertFails")+source.toString( )); //$NON-NLS-1$
		}

		// never access here
		return resultDate;
	}
}
/**
 * 
 *
 */
class DateFormatHolder
{
	//
	private static Map dateTimeFormatholder = DateFormatUtil.getAllDateTimeFormat( );
	private static Map dateFormatHolder = DateFormatUtil.getAllDateFormat( );

	/**
	 * 
	 *
	 */
	private DateFormatHolder( )
	{
	}

	/**
	 * 
	 * @param dateStyle
	 * @param timeStyle
	 * @param locale
	 * @return
	 */
	public static DateFormat getDateTimeInstance( int dateStyle, int timeStyle,
			ULocale locale )
	{
		//DateFormatIdentifier key = new DateFormatIdentifier(dateStyle,timeStyle,locale) ;
		String key = String.valueOf( dateStyle )
				+ ":" + String.valueOf( timeStyle ) + ":" + locale.getName( );
		DateFormat result = (DateFormat) dateTimeFormatholder.get( key );
		
		//This code block is added to solve the problem that the uncached datetimeformatter being used	
		if ( result == null )
		{
			synchronized ( dateTimeFormatholder )
			{
				result = (DateFormat) dateTimeFormatholder.get( key );
				if ( result == null )
				{
					result = DateFormat.getDateTimeInstance( dateStyle,
							timeStyle,
							locale );
					result.setLenient( false );
					dateTimeFormatholder.put( key, result );
				}
			}
		}
		
		return result;
	}

	/**
	 * 
	 * @param dateStyle
	 * @param locale
	 * @return
	 */
	public static DateFormat getDateInstance( int dateStyle, ULocale locale )
	{
		String key = String.valueOf( dateStyle ) + ":" + locale.getName( );
		//DateFormatIdentifier key = new DateFormatIdentifier(dateStyle,0,locale) ;
		DateFormat result = (DateFormat) dateFormatHolder.get( key );
		
		//This code block is added to solve the problem that the uncached datetimeformatter being used	
		if ( result == null )
		{
			synchronized ( dateTimeFormatholder )
			{
				result = (DateFormat)dateFormatHolder.get( key );
				if ( result == null )
				{
					result = DateFormat.getDateInstance( dateStyle, locale );
					result.setLenient( false );
					dateFormatHolder.put( key, result );
				}
			}
		}
		return result;
	}
}
