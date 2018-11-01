/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.dbdefinition.junittest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;


public class IBMCloudscapeTest extends TestCase {
	
	//=========================Version 10.0=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_10 = 30;
	
	private final int MAXIMUM_LENGTH_CHAR_10 = 254;
	private final int MAXIMUM_LENGTH_VARCHAR_10 = 32672;
	
	private final int MAXIMUM_PRECISION_DECIMAL_10 = 31;
	private final int MAXIMUM_PRECISION_FLOAT_10 = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_10 = 31;	
//=============================================================================
	
	private final String strProduct = "IBM Cloudscape";
	private final String PACKAGE_NAME = "org.eclipse.datatools.enablement.dbdefinition.junittest";
	
	private int expectedMaxPrecision = 0;
	private int expectedMaxLength = 0;
	private int expectedIdentifierLength = 0;
	private int actualIdentifierLength = 0;

	
	private Iterator productVersion = null;
	private Iterator dataType;
	private List dataTypeList;
	private ArrayList definitionCollection = new ArrayList();
	private ArrayList dbInformationCollection = new ArrayList();
	
	private String strProductVersion = null;

	private DatabaseVendorDefinition databaseVendorDefinition = null;
	private DatabaseDefinition definition = null;
	private PredefinedDataTypeDefinition typeDefinition = null;
	private DataType type = null;
	private Procedure procedure = null;
	
	public void setUp(){
		
		try{	
			productVersion = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getVersions(strProduct);
			assertNotNull("\nMethod: getVersions()" +
						  "\nError: The method returns a NULL value in startUp()", productVersion);
					
			while(productVersion.hasNext())
			{
				strProductVersion = productVersion.next().toString();
				definition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(strProduct, strProductVersion);
				assertNotNull("\nMethod: getDefinition()" +
							  "\nError: The method returns a NULL value in startUp()", definition);
					
				dbInformationCollection.add(strProductVersion);
				definitionCollection.add(definition);
			}
		}
		catch(Exception e){
			fail("\nMethod: setUp()" +
				 "\nException: " + e.toString() +
			     "\nDescription: The set up is incomplete");
		}
	}
	
//==========================================Version 10.0==============================================	
	
	public void testGetMaximumPrecision_FLOAT_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10.0"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("FLOAT"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "FLOAT"),
										expectedMaxPrecision, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumLength_CHAR_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10.0"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "CHAR"),
										expectedMaxLength, typeDefinition.getMaximumLength());
							}
						}
					}
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumLength_VARCHAR_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10.0"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARCHAR"),
										expectedMaxLength, typeDefinition.getMaximumLength());
							}
						}
					}
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(0), e));
			}
		}
	}
		
	private String getAssertionFailureMessage(String methodName, Object versionName, String dataName){
		
		String temp = "\nData type: ";
		
		if(dataName.equals(""))
		{
			temp = "";
		}
		
		return ("\nPackage: " + PACKAGE_NAME +
				"\nMethod: " + methodName +
			    "\nProduct: " + strProduct +
		        "\nVersion: " + versionName +
		        temp + dataName +
		        "\nResult: The actual value does not agree with vendor doc. ");
	}
	
	private String getExceptionMessage(String methodName, Object versionName, Exception e){
		
		return ("\nMethod: " + methodName + 
				"\nProduct: " + strProduct + 
			    "\nVersion: " + versionName +
		        "\nException: " + e.toString() +
		        "\nDescription: The exception is thrown in the test case");
	}
	
	private int getProperIndex(int rawIndex){
		
		if(rawIndex == 0)
			return 1;
		else if(rawIndex == 1)
			return 2;
		else
			return 0;
	}
	
	public void tearDown(){
		
		expectedMaxPrecision = 0;
		expectedMaxLength = 0;
		expectedIdentifierLength = 0;
		actualIdentifierLength = 0;
		
		productVersion = null;
		dataType = null;
		dataTypeList = null;
		definitionCollection = new ArrayList();
		dbInformationCollection = new ArrayList();
		
		strProductVersion = null;

		definition = null;
		typeDefinition = null;
		procedure = null;
	}
}
