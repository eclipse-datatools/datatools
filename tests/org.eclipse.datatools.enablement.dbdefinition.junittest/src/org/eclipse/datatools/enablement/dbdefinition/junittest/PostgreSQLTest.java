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
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;

public class PostgreSQLTest extends TestCase {
	
	//=========================Version 12.x=======================================

	private final int MAXIMUM_LENGTH_BPCHAR_8X = 32672;
	private final int MAXIMUM_LENGTH_BPCHAR_ARRAY_8X = 32672;
	private final int MAXIMUM_LENGTH_BYTEA_8X = 254;
	private final int MAXIMUM_LENGTH_CIDR_8X = 32;
	private final int MAXIMUM_LENGTH_INET_8X = 15;
	private final int MAXIMUM_LENGTH_INFO_SCHEMA_CHARACTER_DATA_8X = 32672;
	private final int MAXIMUM_LENGTH_INFO_SCHEMA_SQL_IDENTIFIER_8X = 32672;
	private final int MAXIMUM_LENGTH_MACADDR_8X = 128;
	private final int MAXIMUM_LENGTH_NAME_ARRAY_8X = 32672;
	private final int MAXIMUM_LENGTH_TEXT_8X = 32672;
	private final int MAXIMUM_LENGTH_TEXT_ARRAY_8X = 32672;
	private final int MAXIMUM_LENGTH_UNKNOWN_8X = 32672;
	private final int MAXIMUM_LENGTH_VARCHAR_8X = 32672;
	private final int MAXIMUM_LENGTH_VARCHAR_ARRAY_8X = 32672;
	
	private final int MAXIMUM_PRECISION_NUMERIC_8X = 31;
			
	
	//==========================================================================================
	
	private final String strProduct = "postgres";
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

	private DatabaseDefinition definition = null;
	private PredefinedDataTypeDefinition typeDefinition = null;
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
	
//==========================================Version 8.x==============================================
	
	public void testGetMaximumPrecision_NUMERIC_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NUMERIC"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "NUMERIC"),
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

	public void testGetMaximumLength_BPCHAR_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BPCHAR_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("BPCHAR"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "BPCHAR"),
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
	
	public void testGetMaximumLength_BPCHAR_ARRAY_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BPCHAR_ARRAY_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("_BPCHAR"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "BPCHAR_ARRAY"),
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
	
	public void testGetMaximumLength_BYTEA_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BYTEA_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("BYTEA"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "BYTEA"),
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
	
	public void testGetMaximumLength_CIDR_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CIDR_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CIDR"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "CIDR"),
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
	
	public void testGetMaximumLength_INET_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_INET_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("INET"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "INET"),
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
	
	public void testGetMaximumLength_INFO_SCHEMA_CHARACTER_DATA_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_INFO_SCHEMA_CHARACTER_DATA_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CHARACTER_DATA"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "INFO_SCHEMA_CHARACTER_DATA"),
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
	
	public void testGetMaximumLength_INFO_SCHEMA_SQL_IDENTIFIER_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_INFO_SCHEMA_SQL_IDENTIFIER_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("SQL_IDENTIFIER"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "INFO_SCHEMA_SQL_IDENTIFIER"),
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
	
	public void testGetMaximumLength_MACADDR_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_MACADDR_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("MACADDR"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "MACADDR"),
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
	
	public void testGetMaximumLength_NAME_ARRAY_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_NAME_ARRAY_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("_NAME"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "NAME_ARRAY"),
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
	
	public void testGetMaximumLength_TEXT_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_TEXT_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("TEXT"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "TEXT"),
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
	
	public void testGetMaximumLength_TEXT_ARRAY_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_TEXT_ARRAY_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("_TEXT"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "TEXT_ARRAY"),
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
	
	public void testGetMaximumLength_UNKNOWN_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_UNKNOWN_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("UNKNOWN"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "UNKNOWN"),
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
	
	public void testGetMaximumLength_VARCHAR_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_8X;
						
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
	
	public void testGetMaximumLength_VARCHAR_ARRAY_8X(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8.x"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_ARRAY_8X;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("_VARCHAR"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARCHAR_ARRAY"),
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
