/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;



/**
 * DateFormatISO8601 is a utility class for formatting and parsing dates
 * according to date format defined by ISO8601.
 */

public class DateFormatISO8601
{

	/**
	 * Parse a date/time string.
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date parse( String source ) throws ParseException,
			ParseException
	{
		if( source == null )
		{
			return null;
		}
		Date resultDate = null;
		source = cleanDate( source );
		Object simpleDateFormatter = DateFormatFactory.getPatternInstance( PatternKey.getPatterKey( source ) );
		if ( simpleDateFormatter != null )
		{
			try
			{
				resultDate = ( (SimpleDateFormat) simpleDateFormatter ).parse( source );
				return resultDate;
			}
			catch ( ParseException e1 )
			{
			}
		}
		// for the String can not be parsed, throws a BirtException
		if ( resultDate == null )
		{
			throw new ParseException( Messages.getString("dateFormatISO8601.inconvertibleValueError") + source, //$NON-NLS-1$
					0 );
		}

		// never access here
		return resultDate;
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	private static String cleanDate( String s )
	{
		s = s.trim( );
		if ( s.indexOf( 'T' ) < 12 )
		{
			s = s.replaceFirst( "T", " " );//$NON-NLS-1$ //$NON-NLS-2$
		}
		
		int zoneIndex = s.indexOf( "GMT" ); //$NON-NLS-1$
		if( zoneIndex > 0 )
		{
			return s.substring( 0, zoneIndex ).trim( );
		}
		zoneIndex = s.indexOf( 'Z' );
		if ( zoneIndex == s.length( ) - 1 )
		{
			return s.substring( 0, zoneIndex ).trim( );
		}
		zoneIndex = getZoneIndex( s );
		if ( zoneIndex > 0 )
		{
			return s.substring( 0, zoneIndex ).trim( );
		}
		
		return s;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	private static int getZoneIndex( String s )
	{
		int index = s.indexOf( '+' );
		if ( index > 0 )
		{
			return index;
		}
		
		index = s.indexOf( '-' ); //first '-'
		if ( index > 0 )
		{
			index = s.indexOf( '-', index + 1 ); //second '-'
		}
		else
		{
			return index;
		}
		if ( index > 0 )
		{
			index = s.indexOf( '-', index + 1 ); //third '-'
		}
		else
		{
			return index;
		}
		return index;
	}

}
