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

package org.eclipse.datatools.enablement.oda.xml.util;

import junit.framework.TestCase;

/**
 * 
 */

public class XPathParserTest extends TestCase
{

	private void assertEquals( boolean[] booleans, String[] strings,
			String regex )
	{
		assert strings.length == booleans.length;

		if ( regex != null )
		{
			for ( int i = 0; i < strings.length; i++ )
				assertEquals( booleans[i], XPathParserUtil.match( strings[i],
						regex ) );
		}
	}

	public void testDoubleSlash( )
	{
		String[] strings = {
				"/",
				"//",
				"/Book[1]//Title[1]/",
				"/Book[1]/",
				"/Book[1]/Title[1]"
		};
		boolean[] booleans = {
				true, false, true, true, false,
		};

		for ( int i = 0; i < strings.length; i++ )
			assertEquals( booleans[i],
					strings[i].matches( XPathParserUtil.REPL_DOUBLE_SLASH ) );
	}

	public void testAbsoluateLocation( )
	{
		String regex = "/Book/Title";
		String[] strings = {
				"/Book[1]/Title[1]",
				"/Book[1]/Title[2]",
				"/Books[1]/Book[1]/Title[1][@subtitle]",
				"/Book[1]/Title[1]/Author[2]",
				"/Book[1]/Title[1][@subtitle]",
				"/Books[1]/Fiction[1][@count]"
		};
		boolean[] booleans = {
				true, true, false, false, false, false
		};

		assertEquals( booleans, strings, regex );
	}

	public void testRelativeLocation( )
	{
		String regex = "//Book//Title";
		String[] strings = {
				"/Book[1]/Title[1]",
				"/Books[1]/Book[1]/Author[1]",
				"/Books[1]/Fiction[1]/Book[1]/Titles[1]/Title[1]",
				"/Books[1]/Fiction[1]/Book[1]/Titles[1]/Title[1][@sub]"
		};
		boolean[] booleans = {
				true, false, true, false
		};

		assertEquals( booleans, strings, regex );
	}

	public void testPredicates( )
	{
		String regex = "//Book//Title[1]";
		String[] strings = {
				"/Book[2]/Title[1]",
				"/Books[2]/Book[1]/Title[1]",
				"/Book[2]/Fiction[2]/Title[2]",
				"/Book[2]/Fiction[2]/Titles[2]/Title[1]"
		};
		boolean[] booleans = {
				true, true, false, true
		};

		assertEquals( booleans, strings, regex );
	}

	public void testMisc( )
	{
		String regex = "//Book/Fiction//Title[1]";
		String[] strings = {
				"/Book[2]/Title[1]",
				"/Books[2]/Book[1]/Title[1]",
				"/Book[2]/Fiction[2]/Title[2]",
				"/Book[2]/Fiction[2]/Titles[2]/Title[1]"
		};
		boolean[] booleans = {
				false, false, false, true
		};

		assertEquals( booleans, strings, regex );
	}

	public void testWildCardXPath( )
	{
		String regex = "//Book/*//Title";
		String[] strings = {
				"/Book[2]/Title[1]",
				"/Books[2]/Book[1]/Title[1]",
				"/Book[2]/Fiction[2]/Title[2]",
				"/Book[2]/Fiction[2]/Titles[2]/Title[1]"
		};
		boolean[] booleans = {
				false, false, true, true
		};

		assertEquals( booleans, strings, regex );
	}

	public void testRegression( )
	{
		String[] strings = {
				"/root[1]/entries[1]/entry[1]/field[1][@a]",
				"/library[1]/book[1][@category]",
				"/library[1]/book[1]/author[1][@name]"
		};
		String[] regexs = {
				"//entry/field[*]", "//[@category]", "/library/*/*[@name]"
		};
		boolean[] booleans = {
				false, true, true
		};
		for ( int i = 0; i < strings.length; i++ )
			assertEquals( booleans[i], XPathParserUtil.match( strings[i],
					regexs[i] ) );
	}

}
