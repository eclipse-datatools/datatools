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
package org.eclipse.datatools.enablement.oda.xml;


import org.eclipse.datatools.enablement.oda.xml.util.RelationInformation;

/**
 * 
 */
public class RelationInformationTest extends BaseTest
{
	private String testString = "person#:#[/personnel/person/name]#:#{name.family;String;/family},{name.given;String;/given},{email;String;../email},{link.subordinates;String;../link/@subordinates}"+
	                            "#-#name#:#[//name]#:#{family;String;/family},{given;String;/given}"+
	                            "#-#def#:#[//name]#:#{col1;String;../../../@stage},{col2;String;../../B/@stage}"+
								"#-#abc#:#[//abcd/ok/../def/gh]#:#{column1;String;../../abc/../abc/../abc},{column2;String;abc/../../../abc},{column3;String;abc/def/ghi/../abc},{column4;String;../../../../../ab}"+
	                            "#-#nested#:#[//*/def]#:#{column1;String;@attr1},{column2;String;../ghi/@attr2},{column3;String;../@attr3},{column4;String;../jkl/@attr5},{column5;String;../../@attr6}"+
								"#-#nested2#:#[//book]#:#{column1;String;@attr1},{column2;String;../@attr2}";
	                            
	private RelationInformation ri;
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		this.ri= new RelationInformation(testString);
	}

	protected void tearDown( ) throws Exception
	{
		super.tearDown( );
	}

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.RelationInformation.getTableColumnPath(String, String)'
	 */
	public void testGetTableColumnPath( )
	{
		assertEquals(ri.getTableColumnPath("person","name.family"), "/personnel/person/name/family");
		assertEquals(ri.getTableColumnPath("person","name.given"),"/personnel/person/name/given");
		assertEquals(ri.getTableColumnPath("person","email"),"/personnel/person/email");
		assertEquals(ri.getTableColumnPath("person","link.subordinates"),"/personnel/person/link[@subordinates]");
		assertEquals(ri.getTableColumnPath("name","family"), "//name/family");
		assertEquals(ri.getTableColumnPath("name","given"),"//name/given");
		
	}
	
	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.RelationInformation.getTableColumnType(String, String)'
	 */
	public void testGetTableColumnType( )
	{
		assertEquals(ri.getTableColumnType("person","name.family"), "String");
		assertEquals(ri.getTableColumnType("person","name.given"),"String");
		assertEquals(ri.getTableColumnType("person","email"),"String");
		assertEquals(ri.getTableColumnType("person","link.subordinates"),"String");
		assertEquals(ri.getTableColumnType("name","family"),"String");
		assertEquals(ri.getTableColumnType("name","given"),"String");
	}
	
	public void testGetTabelRootPath()
	{
		assertEquals(ri.getTableRootPath("person"),"/personnel/person/name");
		assertEquals(ri.getTableRootPath("name"),"//name");
	}

	public void testGetNestedColumnBackRefNumber()
	{
		assertEquals(ri.getTableNestedColumnBackRefNumber("abc","column1"),2);
		assertEquals(ri.getTableNestedColumnBackRefNumber("abc","column2"),2);
		assertEquals(ri.getTableNestedColumnBackRefNumber("abc","column3"),0);
		assertEquals(ri.getTableNestedColumnBackRefNumber("abc","column4"),5);
		assertEquals(ri.getTableNestedColumnBackRefNumber("def","col1"),3);
		assertEquals(ri.getTableNestedColumnBackRefNumber("def","col2"),2);
	}
	
	public void testGetComplexNestColumnNames()
	{
		String[] names = ri.getTableComplexNestedXMLColumnNames( "nested" );
		assertEquals(names.length, 2);
		assertEquals(names[0],"column2");
		assertEquals(names[1],"column4");
	}
	
	public void testGetSimpleNestColumnNames1()
	{
		String[] names = ri.getTableSimpleNestedXMLColumnNames( "nested" );
		assertEquals(names.length, 2);
		assertEquals(names[0],"column3");
		assertEquals(names[1],"column5");
	}
	
	public void testGetSimpleNestColumnNames2()
	{
		String[] names = ri.getTableSimpleNestedXMLColumnNames( "nested2" );
		assertEquals(names.length, 1);
		assertEquals(names[0],"column2");
	}
}
