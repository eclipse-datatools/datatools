/*******************************************************************************
 * Copyright (c) 2010, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/** Cache small strings */
public class StringCache
{
	// Don't keep strong references to the strings
	private static int INITIAL_SIZE = 32768;
	private static Map<String, WeakReference<String>> stringCache = new WeakHashMap<String, WeakReference<String>>( INITIAL_SIZE );

	public static final String EMPTY = getUniqueString( "" ); //$NON-NLS-1$

	/**
	 * Get the cached object for a value
	 * 
	 * @return The common object, or the argument if it was not yet in the cache
	 */
	public static String getUniqueString( String value )
	{
		if ( value == null )
		{
			return value;
		}

		synchronized (stringCache)
		{
			WeakReference<String> existingRef = stringCache.get( value );
			String existing = (existingRef != null)
					? existingRef.get()
					: null;

			if ( existing == null )
			{
				stringCache.put( value, new WeakReference<String>( value ) );
			}
			else if ( value != existing )
			{
				value = existing;
			}
		}

		return value;
	}

	private StringCache()
	{
		// no instances
	}
}
