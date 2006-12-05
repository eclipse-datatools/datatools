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
								"#-#nested2#:#[//book]#:#{column1;String;@attr1},{column2;String;../@attr2}"+
								"#-#table1#:#[/root/country]#:#{name;String;/city[@iscapital='y']/@name}"+
								"#-#table2#:#[/root/country]#:#{name;String;/city[2]/@name}"+
								"#-#table3#:#[/root/country]#:#{name;String;/city[2][@iscapital='y']/@name}"+
								"#-#table4#:#[/root/country/city]#:#{name;String;[@iscapital='y']/@name}"
								;
	                   
	private RelationInformation ri1;

	protected void setUp( ) throws Exception
	{
		super.setUp( );
		this.ri1= new RelationInformation(testString);
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
		assertEquals(ri1.getTableColumnPath("person","name.family"), "/personnel/person/name/family");
		assertEquals(ri1.getTableColumnPath("person","name.given"),"/personnel/person/name/given");
		assertEquals(ri1.getTableColumnPath("person","email"),"/personnel/person/email");
		assertEquals(ri1.getTableColumnPath("person","link.subordinates"),"/personnel/person/link[@subordinates]");
		assertEquals(ri1.getTableColumnPath("name","family"), "//name/family");
		assertEquals(ri1.getTableColumnPath("name","given"),"//name/given");
		assertEquals(ri1.getTableColumnPath( "table1", "name" ),"/root/country/city[@name]");
		assertEquals(ri1.getTableColumnPath( "table2", "name" ),"/root/country/city[2][@name]");
		assertEquals(ri1.getTableColumnPath( "table3", "name" ),"/root/country/city[2][@name]");
		assertEquals(ri1.getTableColumnPath( "table4", "name" ),"/root/country/city[@name]");
	}
	
	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.RelationInformation.getTableColumnType(String, String)'
	 */
	public void testGetTableColumnType( )
	{
		assertEquals(ri1.getTableColumnType("person","name.family"), "String");
		assertEquals(ri1.getTableColumnType("person","name.given"),"String");
		assertEquals(ri1.getTableColumnType("person","email"),"String");
		assertEquals(ri1.getTableColumnType("person","link.subordinates"),"String");
		assertEquals(ri1.getTableColumnType("name","family"),"String");
		assertEquals(ri1.getTableColumnType("name","given"),"String");
		assertEquals(ri1.getTableColumnType( "table1", "name" ),"String");
		assertEquals(ri1.getTableColumnType( "table2", "name" ),"String");
		assertEquals(ri1.getTableColumnType( "table3", "name" ),"String");
		assertEquals(ri1.getTableColumnType( "table4", "name" ),"String");
	}
	
	public void testGetTabelRootPath()
	{
		assertEquals(ri1.getTableRootPath("person"),"/personnel/person/name");
		assertEquals(ri1.getTableRootPath("name"),"//name");
		assertEquals(ri1.getTableRootPath("table1"),"/root/country");
		assertEquals(ri1.getTableRootPath("table2"),"/root/country");
		assertEquals(ri1.getTableRootPath("table3"),"/root/country");
		assertEquals(ri1.getTableRootPath("table4"),"/root/country/city");
	}

	public void testGetNestedColumnBackRefNumber()
	{
		assertEquals(ri1.getTableNestedColumnBackRefNumber("abc","column1"),2);
		assertEquals(ri1.getTableNestedColumnBackRefNumber("abc","column2"),2);
		assertEquals(ri1.getTableNestedColumnBackRefNumber("abc","column3"),0);
		assertEquals(ri1.getTableNestedColumnBackRefNumber("abc","column4"),5);
		assertEquals(ri1.getTableNestedColumnBackRefNumber("def","col1"),3);
		assertEquals(ri1.getTableNestedColumnBackRefNumber("def","col2"),2);
		assertEquals(ri1.getTableNestedColumnBackRefNumber("table1","name"),0);
		assertEquals(ri1.getTableNestedColumnBackRefNumber("table2","name"),0);
		assertEquals(ri1.getTableNestedColumnBackRefNumber("table3","name"),0);
		assertEquals(ri1.getTableNestedColumnBackRefNumber("table4","name"),0);
	}
	
	public void testGetComplexNestColumnNames()
	{
		String[] names = ri1.getTableComplexNestedXMLColumnNames( "nested" );
		assertEquals(names.length, 2);
		assertEquals(names[0],"column2");
		assertEquals(names[1],"column4");
	}
	
	public void testGetSimpleNestColumnNames1()
	{
		String[] names = ri1.getTableSimpleNestedXMLColumnNames( "nested" );
		assertEquals(names.length, 2);
		assertEquals(names[0],"column3");
		assertEquals(names[1],"column5");
	}
	
	public void testGetSimpleNestColumnNames2()
	{
		String[] names = ri1.getTableSimpleNestedXMLColumnNames( "nested2" );
		assertEquals(names.length, 1);
		assertEquals(names[0],"column2");
	}
}
