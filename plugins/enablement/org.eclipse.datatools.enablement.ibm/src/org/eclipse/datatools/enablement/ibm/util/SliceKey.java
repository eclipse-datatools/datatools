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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SliceKey
{
	public static final SliceKey EMPTY = new SliceKey( new String[ 0 ] );

	String[] keyvalues;
	String keystring;

	public SliceKey( String... values )
	{
		if ( values == null )
		{
			this.keyvalues = new String[ 0 ];
		}
		else
		{
			this.keyvalues = new String[ values.length ];

			for ( int ii = 0; ii < values.length; ++ii )
			{
				if ( values[ ii ] == null )
				{
					values[ ii ] = ""; //$NON-NLS-1$
				}

				this.keyvalues[ ii ] = StringCache.getUniqueString( values[ ii ].trim() );
			}
		}

		StringBuilder sb = new StringBuilder();

		for ( String s : this.keyvalues )
		{
			if ( s == null )
			{
				sb.setLength( 0 );
				break;
			}

			addKeyValue( sb, s );
		}

		this.keystring = StringCache.getUniqueString( sb.toString() );
	}

	@Override
	public int hashCode()
	{
		if ( keyvalues == null )
		{
			return 0;
		}

		int hash = 0;

		for ( int ii = 0; ii < this.keyvalues.length; ++ii )
		{
			hash <<= 1;

			String kv = this.keyvalues[ ii ];

			if ( kv != null )
			{
				hash ^= kv.hashCode();
			}
		}

		return hash;
	}

	@Override
	public boolean equals( Object o )
	{
		if ( this == o )
		{
			return true;
		}

		if ( !(o instanceof SliceKey) )
		{
			return false;
		}

		SliceKey other = (SliceKey)o;

		if ( this.keyvalues.length != other.keyvalues.length )
		{
			return false;
		}

		for ( int ii = 0; ii < this.keyvalues.length; ++ii )
		{
			String kv1 = this.keyvalues[ ii ];
			String kv2 = other.keyvalues[ ii ];

			if ( kv1 == null )
			{
				if ( kv2 != null )
				{
					return false;
				}
			}
			else if ( !kv1.equals( kv2 ) )
			{
				return false;
			}
		}

		return true;
	}

	public boolean isEmpty()
	{
		return (this.keyvalues == null) || (this.keyvalues.length == 0);
	}

	public String toString()
	{
		return getKeyString();
	}

	public String getKeyString()
	{
		return this.keystring;
	}

	public static String[] extractFilterValues( String keystring )
	{
		if ( keystring == null )
		{
			return new String[ 0 ];
		}
		StringTokenizer toker = new StringTokenizer( keystring, "." ); //$NON-NLS-1$

		List<String> filterValues = new ArrayList<String>();

		while ( toker.hasMoreTokens() )
		{
			filterValues.add( decode( toker.nextToken() ) );
		}

		return filterValues.toArray( new String[ filterValues.size() ] );
	}

	private static String decode( String encoded )
	{
		int amp = encoded.indexOf( '&' );

		if ( amp < 0 )
		{
			return encoded;
		}

		StringBuilder sb = new StringBuilder( encoded );

		while ( (amp >= 0) && (amp < sb.length() - 1) )
		{
			sb.deleteCharAt( amp );

			if ( sb.charAt( amp ) == 'd' )
			{
				sb.setCharAt( amp, '.' );
			}

			amp = sb.indexOf( "&", amp + 1 ); //$NON-NLS-1$
		}

		return sb.toString();
	}

	private static void addKeyValue( StringBuilder sb, String value )
	{
		if ( sb.length() > 0 )
		{
			sb.append( '.' );
		}

		if ( (value.indexOf( '.' ) >= 0) || (value.indexOf( '&' ) >= 0) )
		{
			for ( int ii = 0; ii < value.length(); ++ii )
			{
				char c = value.charAt( ii );

				switch (c)
				{
					case '.':
						sb.append( "&d" ); //$NON-NLS-1$
						break;
					case '&':
						sb.append( "&&" ); //$NON-NLS-1$
						break;
					default:
						sb.append( c );
						break;
				}
			}
		}
		else
		{
			sb.append( value );
		}
	}
}
