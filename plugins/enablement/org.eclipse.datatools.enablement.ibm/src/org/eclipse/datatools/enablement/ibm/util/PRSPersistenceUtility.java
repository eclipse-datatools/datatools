/*******************************************************************************
 * Copyright (c) 2012, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.StringTokenizer;

public class PRSPersistenceUtility
{
	public static final String LINESEP = System.getProperty( "line.separator" ); //$NON-NLS-1$
	private static final String STR_HASH = "#"; //$NON-NLS-1$

	private static final char MARKER_NULL = 'N';
	private static final char MARKER_STRING = '"';
	private static final char MARKER_CHAR = 'c';
	private static final char MARKER_INT = 'i';
	private static final char MARKER_LONG = 'l';
	private static final char MARKER_DOUBLE = 'd';
	private static final char MARKER_BOOLEAN = 'b';
	private static final char MARKER_DECIMAL = 'D';
	private static final char MARKER_TIMESTAMP = 'T';

	public static LineNumberReader openFileForRead( File file )
	{
		Reader rdr;

		try
		{
			rdr = new FileReader( file );
		}
		catch (FileNotFoundException e)
		{
			return null;
		}

		return new LineNumberReader( rdr );
	}

	public static PrintWriter openFileForAppend( File file )
	{
		return openFileForWrite( file, true );
	}

	public static PrintWriter openFileForCreate( File file )
	{
		return openFileForWrite( file, false );
	}

	private static PrintWriter openFileForWrite( File file, boolean append )
	{
		if (file == null) {
			return null;
		}
		OutputStream os;

		try
		{
			os = new FileOutputStream( file, append );
		}
		catch (FileNotFoundException e)
		{
			return null;
		}

		return new PrintWriter( os );
	}

	public static void addLine( StringBuilder sb, String line )
	{
		sb.append( line );
		newline( sb );
	}

	public static void newline( StringBuilder sb )
	{
		sb.append( LINESEP );
	}

	public static void append( StringBuilder sb, Object o )
	{
		if ( o == null )
		{
			appendNull( sb );
			return;
		}

		if ( o instanceof String )
		{
			append( sb, (String)o );
		}
		else if ( o instanceof Long )
		{
			append( sb, ((Long)o).longValue() );
		}
		else if ( o instanceof Double )
		{
			append( sb, ((Double)o).doubleValue() );
		}
		else if ( o instanceof BigDecimal )
		{
			append( sb, (BigDecimal)o );
		}
		else if ( o instanceof Timestamp )
		{
			append( sb, (Timestamp)o );
		}
		else
		{
			append( sb, o.toString() );
		}
	}

	public static void appendNull( StringBuilder sb )
	{
		sb.append( MARKER_NULL );
		sb.append( "|" ); //$NON-NLS-1$
	}

	public static void append( StringBuilder sb, String s )
	{
		if ( s == null )
		{
			appendNull( sb );

			return;
		}

		sb.append( MARKER_STRING );
		sb.append( encodeString( s ) );
		sb.append( '|' );
	}

	public static String encodeString( String s )
	{
		if ( s == null )
		{
			return ""; //$NON-NLS-1$
		}

		int len = s.length();

		StringBuilder sb = new StringBuilder();

		for ( int ii = 0; ii < len; ++ii )
		{
			sb.append( encodeChar( s.charAt( ii ) ) );
		}

		return sb.toString();
	}

	private static String encodeChar( char ch )
	{
		return ((ch >= 32) && (ch < 127) && (ch != '|') && (ch != '#'))
				? "" + ch //$NON-NLS-1$
				: String.format( "#%04x", Integer.valueOf( ch & 0xFFFF ) ); //$NON-NLS-1$
	}

	public static void append( StringBuilder sb, int i )
	{
		sb.append( MARKER_INT );
		sb.append( i );
		sb.append( '|' );
	}

	public static void append( StringBuilder sb, long l )
	{
		sb.append( MARKER_LONG );
		sb.append( l );
		sb.append( '|' );
	}

	public static void append( StringBuilder sb, double d )
	{
		sb.append( MARKER_DOUBLE );
		sb.append( d );
		sb.append( '|' );
	}

	public static void append( StringBuilder sb, BigDecimal d )
	{
		sb.append( MARKER_DECIMAL );
		sb.append( d );
		sb.append( '|' );
	}

	public static void append( StringBuilder sb, Timestamp t )
	{
		sb.append( MARKER_TIMESTAMP );
		sb.append( t.getTime() );
		sb.append( '|' );
	}

	public static void append( StringBuilder sb, char ch )
	{
		sb.append( MARKER_CHAR );
		sb.append( encodeChar( ch ) );
		sb.append( '|' );
	}

	public static void append( StringBuilder sb, boolean b )
	{
		sb.append( MARKER_BOOLEAN );
		sb.append( (b)
				? 't'
				: 'f' );
		sb.append( '|' );
	}

	public static Object getToken( StringTokenizer st )
	{
		if ( !st.hasMoreTokens() )
		{
			return null;
		}

		String tok = st.nextToken();

		if ( (tok == null) || (tok.length() < 1) )
		{
			return null;
		}

		char marker = tok.charAt( 0 );
		tok = tok.substring( 1 );

		switch (marker)
		{
			case MARKER_STRING:
			case MARKER_CHAR:
				return decodeString( tok );

			case MARKER_INT:
				return new Integer( tok );

			case MARKER_LONG:
				return new Long( tok );

			case MARKER_DOUBLE:
				return new Double( tok );

			case MARKER_DECIMAL:
				return new BigDecimal( tok );

			case MARKER_BOOLEAN:
				return Boolean.valueOf( tok.charAt( 0 ) == 't' );

			case MARKER_TIMESTAMP:
				return new Timestamp( Long.parseLong( tok ) );

			case MARKER_NULL:
			default:
				return null;
		}
	}

	public static String getStringToken( StringTokenizer st )
	{
		Object o = getToken( st );

		return (o instanceof String)
				? (String)o
				: null;
	}

	private static String decodeString( String s )
	{
		int hash = s.indexOf( STR_HASH );

		if ( hash < 0 )
		{
			return s;
		}

		StringBuilder sb = new StringBuilder( s );
		//--hash;

		while ( (hash >= 0) && (hash < (sb.length() - 4)) )
		{
			char ch = (char)Integer.parseInt( sb.substring( hash + 1, hash + 5 ), 16 );

			sb.setCharAt( hash, ch );
			sb.delete( hash + 1, hash + 5 );

			if ( hash < (sb.length() - 1) )
			{
				hash = sb.indexOf( STR_HASH, hash + 1 );
			}
		}

		return sb.toString();
	}

	public static int getIntToken( StringTokenizer st )
	{
		Object o = getToken( st );

		return (o instanceof Integer)
				? ((Integer)o).intValue()
				: 0;
	}

	public static long getLongToken( StringTokenizer st )
	{
		Object o = getToken( st );

		return (o instanceof Long)
				? ((Long)o).longValue()
				: 0;
	}

	public static char getCharToken( StringTokenizer st )
	{
		Object o = getToken( st );

		return ((o instanceof String) && ((String)o).length() > 0)
				? ((String)o).charAt( 0 )
				: 0;
	}

	public static boolean getBooleanToken( StringTokenizer st )
	{
		Object o = getToken( st );

		return (o instanceof Boolean) && ((Boolean)o).booleanValue();
	}
}
