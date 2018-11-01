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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.BaseTest;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestConstants;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestUtil;

/**
 * 
 */
public class SchemaPopulationUtilTest extends BaseTest
{
	static String lineSeparator = (String) java.security.AccessController.doPrivileged(
            new sun.security.action.GetPropertyAction("line.separator"));

	/*
	 * Test method for 'org.eclipse.datatools.enablement.oda.xml.util.ui.SchemaPopulationUtil.getSchemaTree(String)'
	 */
	public void testGetSchemaTree( ) throws IOException, OdaException, URISyntaxException
	{
			File file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_XSD);
			
			if( file.exists() )
				file.delete();
			File path = new File( file.getParent() );
			if( !path.exists())
				path.mkdir();
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream( file );
		
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.TEST_XSD, null, 0, null ),0,fos);
			//printTree(SchemaPopulationUtil.getSchemaTree( "C:\\Documents and Settings\\lzhu\\Desktop\\data1\\pubSchema.xsd" ),0,fos);
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_XSD),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GOLDEN_XSD)));
			
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XSD);
			if( file.exists() )
				file.delete();
			path = new File( file.getParent() );
			if( !path.exists())
				path.mkdir();
			file.createNewFile();
			fos = new FileOutputStream( file );
		
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_INPUT_XSD, null, 0, null ),0,fos);
			//printTree(SchemaPopulationUtil.getSchemaTree( "C:\\Documents and Settings\\lzhu\\Desktop\\data1\\pubSchema.xsd" ),0,fos);
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XSD),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XSD)));
			

			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML);
			
			if( file.exists() )
				file.delete();
			file.createNewFile();
			fos = new FileOutputStream( file );
			printTree(SchemaPopulationUtil.getSchemaTree( null, TestConstants.BOOKSTORE_XML_FILE, 0, null ),0,fos);
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XML),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML)));
			
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XSD_WITHOUT_XML );

			if ( file.exists( ) )
				file.delete( );
			file.createNewFile( );
			fos = new FileOutputStream( file );
			printTree( SchemaPopulationUtil.getSchemaTree( TestConstants.BOOKSTORE_XSD_FILE, null, 0, null ), 0, fos );
			fos.close( );

			assertTrue( TestUtil.compareTextFile( 
						new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XSD_WITHOUT_XML ),
						new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XSD_WITHOUT_XML ) ) );
			
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML_WITH_XSD );

			if ( file.exists( ) )
				file.delete( );
			file.createNewFile( );
			fos = new FileOutputStream( file );
			printTree( SchemaPopulationUtil.getSchemaTree( TestConstants.BOOKSTORE_XSD_FILE, TestConstants.BOOKSTORE_XML_FILE, 0, null ), 0, fos );
			fos.close( );

			assertTrue( TestUtil.compareTextFile( 
						new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XML_WITH_XSD ),
						new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML_WITH_XSD ) ) );
			
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML_BOOKSTORE);
			
			if( file.exists() )
				file.delete();
			file.createNewFile();
			fos = new FileOutputStream( file );
			printTree(SchemaPopulationUtil.getSchemaTree( null, TestConstants.BOOKSTORE_XML_FILE,0,null ),0,fos);
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XML_BOOKSTORE),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML_BOOKSTORE)));
		
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_NEST_XSD);
			
			if( file.exists() )
				file.delete();
			path = new File( file.getParent() );
			if( !path.exists())
				path.mkdir();
			file.createNewFile();
			fos = new FileOutputStream( file );
		
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.NESTED_COMPLEXTYPE_XSD, null, 0, null ),0,fos);
			//printTree(SchemaPopulationUtil.getSchemaTree( "C:\\Documents and Settings\\lzhu\\Desktop\\data1\\pubSchema.xsd" ),0,fos);
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_NEST_XSD),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GOLDEN_NEST_XSD)));
	
	
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_DATATYPE);
			
			if( file.exists() )
				file.delete();
			path = new File( file.getParent() );
			if( !path.exists())
				path.mkdir();
			file.createNewFile();
			fos = new FileOutputStream( file );
		
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.DATATYPE_XSD, null,0, null ),0,fos, true);
			
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_DATATYPE),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GOLDEN_DATATYPE)));
			

			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_GROUP);
			
			if( file.exists() )
				file.delete();
			path = new File( file.getParent() );
			if( !path.exists())
				path.mkdir();
			file.createNewFile();
			fos = new FileOutputStream( file );
		
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.TEST_XSD_GROUP, null, 0, null ),0,fos, true);
			
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_GROUP),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GOLDEN_GROUP)));
			
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_COMPLEX);
			
			if( file.exists() )
				file.delete();
			path = new File( file.getParent() );
			if( !path.exists())
				path.mkdir();
			file.createNewFile();
			fos = new FileOutputStream( file );

		//	printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.TEST_XSD_COMPLEX, true, 0 ),0,fos, true);
			
			fos.close();
			
		/*	assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_COMPLEX),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GOLDEN_COMPLEX)));
		*/	
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_SELFRECURSIVE);
			
			if( file.exists() )
				file.delete();
			path = new File( file.getParent() );
			if( !path.exists())
				path.mkdir();
			file.createNewFile();
			fos = new FileOutputStream( file );
			
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.TEST_XSD_SELFRECURSIVE, null, 0, null ),0,fos, true);
			
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_SELFRECURSIVE),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GOLDEN_SELFRECURSIVE)));
			
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_COMPLEXRECURSIVE);
			
			if( file.exists() )
				file.delete();
			path = new File( file.getParent() );
			if( !path.exists())
				path.mkdir();
			file.createNewFile();
			fos = new FileOutputStream( file );
			
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.TEST_XSD_COMPLEXRECURSIVE, null, 0, null ),0,fos, true);
			
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_COMPLEXRECURSIVE),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GOLDEN_COMPLEXRECURSIVE)));
		
	}
	
	private static void printTree( ATreeNode root, int level, FileOutputStream fos ) throws IOException, OdaException
	{
		printTree( root, level, fos, false );
	}
	
	private static void printTree( ATreeNode root, int level, FileOutputStream fos, boolean withType ) throws IOException, OdaException
	{
		if( level > 10 )
			return;
		String space = "  ";
		for(int i = 0; i < level; i++)
		{
			space = space+"  ";
		}
		String toBeWriten = space + root.getValue().toString()+":"+root.getType()+":"+level + (withType?(" dataType:" + root.getDataType( )):"");
		fos.write( toBeWriten.getBytes());
		fos.write(lineSeparator.getBytes());
		System.out.println( toBeWriten );
		for(int i = 0; i < root.getChildren().length; i++)
		{
			printTree( (ATreeNode)(root.getChildren()[i]), level+1, fos, withType);
		}
	}
}
