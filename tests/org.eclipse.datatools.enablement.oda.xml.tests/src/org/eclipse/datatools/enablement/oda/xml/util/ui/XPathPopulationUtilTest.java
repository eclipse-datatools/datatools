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
package org.eclipse.datatools.enablement.oda.xml.util.ui;

import java.util.List;

import org.eclipse.datatools.enablement.oda.xml.BaseTest;

/**
 * 
 */
public class XPathPopulationUtilTest extends BaseTest
{

	protected void setUp( ) throws Exception
	{
		super.setUp( );
	}

	protected void tearDown( ) throws Exception
	{
		super.tearDown( );
	}
	
	public void testPopulateRootPath()
	{
		String testString1 = "/tool/root/abc";
		String[] expectedResults1 = new String[]{"/tool/root/abc", "//root/abc","//abc"};
		List list = XPathPopulationUtil.populateRootPath( testString1 );
		
		assertEquals( expectedResults1.length, list.size());
		for( int i = 0; i < list.size(); i ++)
		{
			assertEquals( expectedResults1[i], list.get(i));
		}
		
		String testString2 = "/tool";
		list = XPathPopulationUtil.populateRootPath( testString2 );
		assertEquals( 1, list.size());
		assertEquals( "/tool", list.get(0));
	}
	
	public void testPopulateColumnPath()
	{
		String testRootString1 = "/root/tool/abc";
		String testColumnString1 = "/root/man/def";
		String result = XPathPopulationUtil.populateColumnPath( testRootString1, testColumnString1);
		
		assertEquals( result, "../../man/def");
		
		String testRootString2 = "//root/tool/abc";
		String testColumnString2 = "/abcd/efgh/root/tool/notRoot/man/def";
		result = XPathPopulationUtil.populateColumnPath( testRootString2, testColumnString2);
		
		assertEquals( result, "../notRoot/man/def");
		
		String testRootString3 = "/root/tool/abc";
		String testColumnString3 = "/abcd/efgh/root/tool/notRoot/man/def";
		result = XPathPopulationUtil.populateColumnPath( testRootString3, testColumnString3);
		
		assertEquals( result, null);
		
		String testRootString4 = "/root/tool/abc";
		String testColumnString4 = "/root/tool/man/def";
		result = XPathPopulationUtil.populateColumnPath( testRootString4, testColumnString4);
		
		assertEquals( result, "../man/def");
		
		String testRootString5 = "//tool/abc";
		String testColumnString5 = "/root/tool/tool/man/def";
		result = XPathPopulationUtil.populateColumnPath( testRootString5, testColumnString5);
		
		assertEquals( result, "../tool/man/def");
				
		String testRootString6 = "/tool/root/abc";
		String testColumnString6 = "/root/tool/tool/man/def";
		result = XPathPopulationUtil.populateColumnPath( testRootString6, testColumnString6);
		
		assertEquals( result, null);
		
		String testRootString7 = "//tool/root/abc";
		String testColumnString7 = "/root/tool/tool/man/def";
		result = XPathPopulationUtil.populateColumnPath( testRootString7, testColumnString7);
		
		assertEquals( result, "../../tool/man/def");
		
		String testRootString8 = "//tool/root";
		String testColumnString8 = "/kk/tool/goods/@abc";
		result = XPathPopulationUtil.populateColumnPath( testRootString8, testColumnString8);
		
		assertEquals( result, "../goods/@abc");
		

		String testRootString9 = "//book/match/author";
		String testColumnString9 = "/c/library/category/book/match/author/@abc";
		result = XPathPopulationUtil.populateColumnPath( testRootString9, testColumnString9);
		
		assertEquals( result, "@abc");
		
		String testRootString10 = "//book";
		String testColumnString10 = "/c/library/category/book/@abc";
		result = XPathPopulationUtil.populateColumnPath( testRootString10, testColumnString10);
		
		assertEquals( result, "@abc");
		
		String testRootString11 = "//book/*/abc/def";
		String testColumnString11 = "/c/library/category/book/kick/abc/@attr";
		result = XPathPopulationUtil.populateColumnPath( testRootString11, testColumnString11);
		
		assertEquals( result, "../@attr");
		
		String testRootString12 = "//library/book[1]/kick/abc/def[1]";
		String testColumnString12 = "/c/library/book/kick/abc[2]/@attr";
		result = XPathPopulationUtil.populateColumnPath( testRootString12, testColumnString12);
		
		assertEquals( result, "../@attr");
		
		String testRootString13 = "//";
		String testColumnString13 = "/c/library/book/kick/abc[2]/@attr";
		result = XPathPopulationUtil.populateColumnPath( testRootString13, testColumnString13);
		
		assertEquals( result, "/library/book/kick/abc[2]/@attr");
		
		String testRootString14 = "//*";
		String testColumnString14 = "/c/library/book/kick/abc[2]/@attr";
		result = XPathPopulationUtil.populateColumnPath( testRootString14, testColumnString14);
		
		assertEquals( result, "/library/book/kick/abc[2]/@attr");
		
		String testRootString15 = "//*";
		String testColumnString15 = "/c";
		result = XPathPopulationUtil.populateColumnPath( testRootString15, testColumnString15);
		
		assertEquals( result, null);
		
	}
}
