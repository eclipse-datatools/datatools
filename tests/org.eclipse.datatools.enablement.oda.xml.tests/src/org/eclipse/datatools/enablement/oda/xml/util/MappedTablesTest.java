
/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
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

public class MappedTablesTest extends TestCase
{
	public void testDetectParameters( )
	{
		String[] ps = MappedTables.detectParameters( "{??}" );
		assertEquals( 0, ps.length );
		
		ps = MappedTables.detectParameters( "{? ?}" );
		assertEquals( 0, ps.length );
		
		ps = MappedTables.detectParameters( "{? ab ?}" );
		assertEquals( 1, ps.length );
		assertEquals( "ab", ps[0]);
		
		ps = MappedTables.detectParameters( "{? ab ?}{?cd?}" );
		assertEquals( 2, ps.length );
		assertEquals( "ab", ps[0]);
		assertEquals( "cd", ps[1]);
		
		ps = MappedTables.detectParameters( "{? ab ?}{?cd?}{?ab?}" );
		assertEquals( 2, ps.length );
		assertEquals( "ab", ps[0]);
		assertEquals( "cd", ps[1]);
		
		ps = MappedTables.detectParameters( "{? ab ?}{?ab?}{?cd?}" );
		assertEquals( 2, ps.length );
		assertEquals( "ab", ps[0]);
		assertEquals( "cd", ps[1]);
	}
	
	public void testSetParameterValue( )
	{
		String result = MappedTables.setParameterValue( "{??}", "ab", "1" );
		assertEquals( "{??}", result );
		
		result = MappedTables.setParameterValue( "{? ?}", "ab", "1" );
		assertEquals( "{? ?}", result );
		
		result = MappedTables.setParameterValue( "{? ab ?}", "ab", "1" );
		assertEquals( "1", result );
		
		result = MappedTables.setParameterValue( "{? ab ?}{?cd?}", "ab", "1" );
		result = MappedTables.setParameterValue( result, "cd", "2" );
		assertEquals( "12", result );
		
		result = MappedTables.setParameterValue( "{? ab ?}{?cd?}{?ab?}", "ab", "1" );
		result = MappedTables.setParameterValue( result, "cd", "2" );
		assertEquals( "121", result );
		
		result = MappedTables.setParameterValue( "{? ab ?}{?ab?}{?cd?}", "ab", "1" );
		result = MappedTables.setParameterValue( result, "cd", "2" );
		assertEquals( "112", result );
	}
}
