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

public class OracleTest extends TestCase {
	
	//=========================Version 8=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_8 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_8 = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_8 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_8 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_8 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_8 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_8 = 30;
	
	private final int MAXIMUM_LENGTH_CHAR_8 = 2000;
	private final int MAXIMUM_LENGTH_NCHAR_8 = 2000;
	private final int MAXIMUM_LENGTH_NVARCHAR2_8 = 4000;
	private final int MAXIMUM_LENGTH_RAW_8 = 2000;
	private final int MAXIMUM_LENGTH_VARCHAR2_8 = 4000;
	
	private final int MAXIMUM_PRECISION_FLOAT_8 = 38;
	private final int MAXIMUM_PRECISION_NUMBER_8 = 38;
	
	private final int MAXIMUM_SCALE_NUMBER_8 = 127;
	private final int MINIMUM_SCALE_NUMBER_8 = -84;
	
    //=========================Version 9=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_9 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_9 = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_9 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_9 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_9 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_9 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_9 = 30;
	
	private final int MAXIMUM_LENGTH_CHAR_9 = 2000;
	private final int MAXIMUM_LENGTH_NCHAR_9 = 2000;
	private final int MAXIMUM_LENGTH_NVARCHAR2_9 = 4000;
	private final int MAXIMUM_LENGTH_RAW_9 = 2000;
	private final int MAXIMUM_LENGTH_VARCHAR2_9 = 4000;
	
	private final int MAXIMUM_PRECISION_FLOAT_9 = 38;
	private final int MAXIMUM_PRECISION_NUMBER_9 = 38;
	private final int MAXIMUM_PRECISION_TIMESTAMP_9 = 9;
	private final int MAXIMUM_PRECISION_TIMESTAMP_WITH_LOCAL_TIME_ZONE_9 = 9;
	private final int MAXIMUM_PRECISION_TIMESTAMP_WITH_TIME_ZONE_9 = 9;
	
	private final int MAXIMUM_SCALE_NUMBER_9 = 127;
	private final int MINIMUM_SCALE_NUMBER_9 = -84;
	
	//=========================Version 10==========================================

	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_10 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_10 = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_10 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_10 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_10 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_10 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_10 = 30;
	
	private final int MAXIMUM_LENGTH_CHAR_10 = 2000;
	private final int MAXIMUM_LENGTH_NCHAR_10 = 2000;
	private final int MAXIMUM_LENGTH_NVARCHAR2_10 = 4000;
	private final int MAXIMUM_LENGTH_RAW_10 = 2000;
	private final int MAXIMUM_LENGTH_VARCHAR2_10 = 4000;
	
	private final int MAXIMUM_PRECISION_FLOAT_10 = 38;
	private final int MAXIMUM_PRECISION_NUMBER_10 = 38;
	private final int MAXIMUM_PRECISION_TIMESTAMP_10 = 9;
	private final int MAXIMUM_PRECISION_TIMESTAMP_WITH_LOCAL_TIME_ZONE_10 = 9;
	private final int MAXIMUM_PRECISION_TIMESTAMP_WITH_TIME_ZONE_10 = 9;
	
	private final int MAXIMUM_SCALE_NUMBER_10 = 127;
	private final int MINIMUM_SCALE_NUMBER_10 = -84;
	
	//==========================================================================================
	
	private final String strProduct = "Oracle";
	private final String PACKAGE_NAME = "org.eclipse.datatools.enablement.dbdefinition.junittest";
	
	private int expectedValue = 0;
	private int actualValue = 0;
	
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
	
//==========================================Version 8==============================================
	
	public void testGetMaximumPrecision_NUMBER_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_NUMBER_8;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NUMBER"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "NUMBER"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMinumumScale_NUMBER_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MINIMUM_SCALE_NUMBER_8;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NUMBER"))
							{	
								assertEquals(getAssertionFailureMessage("getMinimumScale()", dbInformationCollection.get(i), "NUMBER"),
										expectedValue, typeDefinition.getMinimumScale());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMinimumScale()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumScale_NUMBER_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_SCALE_NUMBER_8;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NUMBER"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumScale()", dbInformationCollection.get(i), "NUMBER"),
										expectedValue, typeDefinition.getMaximumScale());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumScale()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_FLOAT_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_FLOAT_8;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("FLOAT"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "FLOAT"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumLength_CHAR_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_CHAR_8;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "CHAR"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumLength_NCHAR_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_NCHAR_8;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "NCHAR"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumLength_VARCHAR2_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_VARCHAR2_8;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARCHAR2"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARCHAR2"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumLength_NVARCHAR2_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_NVARCHAR2_8;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NVARCHAR2"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "NVARCHAR2"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumLength_RAW_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("8"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_RAW_8;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("RAW"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "RAW"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(1), e));
			}
		}
	}
		
	public void testGetDatabaseMaximumIdentifierLength_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("8"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_8;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("8"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_8;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							actualValue, expectedValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("8"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TABLE_8;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("8"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_VIEW_8;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
		
	public void testGetColumnMaximumIdentifierLength_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("8"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_8;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTablespaceMaximumIdentifierLength_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("8"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_8;
				
					assertEquals(getAssertionFailureMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_8(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("8"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_8;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(1), e));
			}
		}
	}
	
	
	
//==========================================Version 9==============================================
	
	public void testGetMaximumPrecision_NUMBER_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_NUMBER_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NUMBER"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "NUMBER"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMinumumScale_NUMBER_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MINIMUM_SCALE_NUMBER_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NUMBER"))
							{	
								assertEquals(getAssertionFailureMessage("getMinimumScale()", dbInformationCollection.get(i), "NUMBER"),
										expectedValue, typeDefinition.getMinimumScale());
							}
						}
					}
				}

			}catch(Exception e){
				
				fail(getExceptionMessage("getMinimumScale()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumScale_NUMBER_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_SCALE_NUMBER_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NUMBER"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumScale()", dbInformationCollection.get(i), "NUMBER"),
										expectedValue, typeDefinition.getMaximumScale());
							}
						}
					}
				}

			}catch(Exception e){
				
				fail(getExceptionMessage("getMaximumScale()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_FLOAT_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_FLOAT_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("FLOAT"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "FLOAT"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_CHAR_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_CHAR_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "CHAR"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_NCHAR_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_NCHAR_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "NCHAR"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_VARCHAR2_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_VARCHAR2_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARCHAR2"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARCHAR2"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_NVARCHAR2_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_NVARCHAR2_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NVARCHAR2"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "NVARCHAR2"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_RAW_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_RAW_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("RAW"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "RAW"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_TIMESTAMP_WITH_TIME_ZONE_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_TIMESTAMP_WITH_TIME_ZONE_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("TIMESTAMP WITH TIME ZONE"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "TIMESTAMP WITH TIME ZONE"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_TIMESTAMP_WITH_LOCAL_TIME_ZONE_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_TIMESTAMP_WITH_LOCAL_TIME_ZONE_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("TIMESTAMP WITH LOCAL TIME ZONE"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "TIMESTAMP WITH LOCAL TIME ZONE"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_TIMESTAMP_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_TIMESTAMP_9;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("TIMESTAMP"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "TIMESTAMP"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetDatabaseMaximumIdentifierLength_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_9;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_9;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							actualValue, expectedValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TABLE_9;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_VIEW_9;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
		
	public void testGetColumnMaximumIdentifierLength_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_9;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTablespaceMaximumIdentifierLength_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_9;
				
					assertEquals(getAssertionFailureMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_9(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("9"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_9;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(2), e));
			}
		}
	}
	
//==========================================Version 10==============================================
		
public void testGetMaximumPrecision_NUMBER_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_NUMBER_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NUMBER"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "NUMBER"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMinumumScale_NUMBER_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MINIMUM_SCALE_NUMBER_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NUMBER"))
							{	
								assertEquals(getAssertionFailureMessage("getMinimumScale()", dbInformationCollection.get(i), "NUMBER"),
										expectedValue, typeDefinition.getMinimumScale());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMinimumScale()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumScale_NUMBER_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_SCALE_NUMBER_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NUMBER"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumScale()", dbInformationCollection.get(i), "NUMBER"),
										expectedValue, typeDefinition.getMaximumScale());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumScale()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_FLOAT_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_FLOAT_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("FLOAT"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "FLOAT"),
										expectedValue, typeDefinition.getMaximumPrecision());
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
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_CHAR_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "CHAR"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumLength_NCHAR_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_NCHAR_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "NCHAR"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumLength_VARCHAR2_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_VARCHAR2_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARCHAR2"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARCHAR2"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumLength_NVARCHAR2_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_NVARCHAR2_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NVARCHAR2"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "NVARCHAR2"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumLength_RAW_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_RAW_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("RAW"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "RAW"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_TIMESTAMP_WITH_TIME_ZONE_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_TIMESTAMP_WITH_TIME_ZONE_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("TIMESTAMP WITH TIME ZONE"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "TIMESTAMP WITH TIME ZONE"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_TIMESTAMP_WITH_LOCAL_TIME_ZONE_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_TIMESTAMP_WITH_LOCAL_TIME_ZONE_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("TIMESTAMP WITH LOCAL TIME ZONE"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "TIMESTAMP WITH LOCAL TIME ZONE"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_TIMESTAMP_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("10"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_TIMESTAMP_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("TIMESTAMP"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "TIMESTAMP"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetDatabaseMaximumIdentifierLength_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("10"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_10;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("10"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_10;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							actualValue, expectedValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("10"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TABLE_10;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("10"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_VIEW_10;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
		
	public void testGetColumnMaximumIdentifierLength_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("10"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_10;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTablespaceMaximumIdentifierLength_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("10"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_10;
				
					assertEquals(getAssertionFailureMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("10"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_10;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(0), e));
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
		
		expectedValue = 0;
		actualValue = 0;
		
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
