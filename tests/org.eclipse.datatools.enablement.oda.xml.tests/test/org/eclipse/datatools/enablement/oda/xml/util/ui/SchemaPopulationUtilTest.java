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
package org.eclipse.datatools.enablement.oda.xml.util.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestConstants;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestUtil;
import org.eclipse.datatools.enablement.oda.xml.util.ui.ATreeNode;
import org.eclipse.datatools.enablement.oda.xml.util.ui.SchemaPopulationUtil;

import junit.framework.TestCase;

/**
 * 
 */
public class SchemaPopulationUtilTest extends TestCase
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
		
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.TEST_XSD, true, 0 ),0,fos);
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
		
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_INPUT_XSD, true, 0 ),0,fos);
			//printTree(SchemaPopulationUtil.getSchemaTree( "C:\\Documents and Settings\\lzhu\\Desktop\\data1\\pubSchema.xsd" ),0,fos);
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XSD),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XSD)));
			
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XSD_WITHOUT_ATTR);
			
			if( file.exists() )
				file.delete();
			file.createNewFile();
			fos = new FileOutputStream( file );
		
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_INPUT_XSD, false, 0 ),0,fos);
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XSD_WITHOUT_ATTR),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XSD_WITHOUT_ATTR)));

			
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML);
			
			if( file.exists() )
				file.delete();
			file.createNewFile();
			fos = new FileOutputStream( file );
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.BOOKSTORE_XML_FILE, true, 0 ),0,fos);
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XML),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML)));
			
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML_WITHOUT_ATTR);
			
			if( file.exists() )
				file.delete();
			file.createNewFile();
			fos = new FileOutputStream( file );
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.SMALL_XML_FILE, false, 0 ),0,fos);
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XML_WITHOUT_ATTR),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML_WITHOUT_ATTR)));
			
			file = new File( TestConstants.SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML_BOOKSTORE);
			
			if( file.exists() )
				file.delete();
			file.createNewFile();
			fos = new FileOutputStream( file );
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.BOOKSTORE_XML_FILE, true, 0 ),0,fos);
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
		
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.NESTED_COMPLEXTYPE_XSD, true, 0 ),0,fos);
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
		
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.DATATYPE_XSD, true, 0 ),0,fos, true);
			
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
		
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.TEST_XSD_GROUP, true, 0 ),0,fos, true);
			
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
			
			printTree(SchemaPopulationUtil.getSchemaTree( TestConstants.TEST_XSD_SELFRECURSIVE, true, 0 ),0,fos, true);
			
			fos.close();
			
			assertTrue( TestUtil.compareTextFile(
					    new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_OUTPUT_SELFRECURSIVE),
						new File(TestConstants.SCHEMA_POPULATION_UTIL_TEST_GOLDEN_SELFRECURSIVE)));
	}
	
	private static void printTree( ATreeNode root, int level, FileOutputStream fos ) throws IOException
	{
		printTree( root, level, fos, false );
	}
	
	private static void printTree( ATreeNode root, int level, FileOutputStream fos, boolean withType ) throws IOException
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
		
		for(int i = 0; i < root.getChildren().length; i++)
		{
			printTree( (ATreeNode)(root.getChildren()[i]), level+1, fos, withType);
		}
	}

}
