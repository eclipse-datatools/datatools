/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
	public void testStringSplit()
	{
		String[] tests = new String[]{
			"",
			"/",
			"//",
			"///",
			"/A",
			"/A/B",
			"/A/"
		};
		
		for (int i = 0; i < tests.length; i++)
		{
			printSplits(tests[i].split( "/" ));
		}
	}
	
	private void printSplits(String[] splits)
	{
		System.out.println( );
		System.out.print("Splits count: " + splits.length + "; Content: ");
		for (int i = 0; i < splits.length; i++)
		{
			System.out.print("[" + splits[i] + "]");
		}
		System.out.println( );
	}
}
