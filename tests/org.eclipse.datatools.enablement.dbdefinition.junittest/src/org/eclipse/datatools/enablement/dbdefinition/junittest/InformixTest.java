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

public class InformixTest extends TestCase {
	
	//=========================Version 9.2=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_92 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_92 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_92 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_92 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_92 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_92 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_92 = 128;
	
	private final int MAXIMUM_LENGTH_CHAR_92 = 32767;
	private final int MAXIMUM_LENGTH_LVARCHAR_92 = 32739;
	private final int MAXIMUM_LENGTH_NCHAR_92 = 32767;
	private final int MAXIMUM_LENGTH_VARCHAR_92 = 255;
	
	private final int MAXIMUM_PRECISION_DECIMAL_92 = 32;
	private final int MAXIMUM_PRECISION_MONEY_92 = 32;
	
    //=========================Version 9.3=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_93 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_93 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_93 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_93 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_93 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_93 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_93 = 128;
	
	private final int MAXIMUM_LENGTH_CHAR_93 = 32767;
	private final int MAXIMUM_LENGTH_LVARCHAR_93 = 32739;
	private final int MAXIMUM_LENGTH_NCHAR_93 = 32767;
	private final int MAXIMUM_LENGTH_VARCHAR_93 = 255;
	
	private final int MAXIMUM_PRECISION_DECIMAL_93 = 32;
	private final int MAXIMUM_PRECISION_MONEY_93 = 32;
	
	//=========================Version 9.4=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_94 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_94 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_94 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_94 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_94 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_94 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_94 = 128;
	
	private final int MAXIMUM_LENGTH_CHAR_94 = 32767;
	private final int MAXIMUM_LENGTH_LVARCHAR_94 = 32739;
	private final int MAXIMUM_LENGTH_NCHAR_94 = 32767;
	private final int MAXIMUM_LENGTH_VARCHAR_94 = 255;
	
	private final int MAXIMUM_PRECISION_DECIMAL_94 = 32;
	private final int MAXIMUM_PRECISION_MONEY_94 = 32;
	
	//=========================Version 10==========================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_10 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_10 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_10 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_10 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_10 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_10 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_10 = 128;
	
	private final int MAXIMUM_LENGTH_CHAR_10 = 32767;
	private final int MAXIMUM_LENGTH_LVARCHAR_10 = 32739;
	private final int MAXIMUM_LENGTH_NCHAR_10 = 32767;
	private final int MAXIMUM_LENGTH_VARCHAR_10 = 255;
	
	private final int MAXIMUM_PRECISION_DECIMAL_10 = 32;
	private final int MAXIMUM_PRECISION_MONEY_10 = 32;

	
	//==========================================================================================
	
	private final String strProduct = "Informix";
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
	
//==========================================Version 9.2==============================================
	
	public void testGetMaximumPrecision_DECIMAL_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_DECIMAL_92;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DECIMAL"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "DECIMAL"),
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
	
	public void testGetMaximumPrecision_MONEY_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_MONEY_92;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("MONEY"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "MONEY"),
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
	
	public void testGetMaximumLength_CHAR_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_CHAR_92;
						
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
	
	public void testGetMaximumLength_NCHAR_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_NCHAR_92;
						
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
	
	public void testGetMaximumLength_VARCHAR_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_VARCHAR_92;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARCHAR"),
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
	
	public void testGetMaximumLength_LVARCHAR_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_LVARCHAR_92;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("LVARCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "LVARCHAR"),
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
	
	public void testGetDatabaseMaximumIdentifierLength_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.2"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_92;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.2"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_92;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							actualValue, expectedValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.2"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TABLE_92;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.2"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_VIEW_92;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
		
	public void testGetColumnMaximumIdentifierLength_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.2"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_92;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.2"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_92;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_92(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("9.2"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_92;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(1), e));
			}
		}
	}
	
//==========================================Version 9.3==============================================

	public void testGetMaximumPrecision_DECIMAL_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_DECIMAL_93;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DECIMAL"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "DECIMAL"),
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
	
	public void testGetMaximumPrecision_MONEY_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_MONEY_93;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("MONEY"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "MONEY"),
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
	
	public void testGetMaximumLength_CHAR_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_CHAR_93;
						
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
	
	public void testGetMaximumLength_NCHAR_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_NCHAR_93;
						
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
	
	public void testGetMaximumLength_VARCHAR_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_VARCHAR_93;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARCHAR"),
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
	
	public void testGetMaximumLength_LVARCHAR_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_LVARCHAR_93;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("LVARCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "LVARCHAR"),
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
	
	public void testGetDatabaseMaximumIdentifierLength_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.3"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_93;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.3"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_93;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							actualValue, expectedValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.3"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TABLE_93;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.3"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_VIEW_93;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
		
	public void testGetColumnMaximumIdentifierLength_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.3"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_93;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.3"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_93;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_93(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("9.3"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_93;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(2), e));
			}
		}
	}
//==========================================Version 9.4==============================================	

	public void testGetMaximumPrecision_DECIMAL_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_DECIMAL_94;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DECIMAL"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "DECIMAL"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(3), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_MONEY_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_PRECISION_MONEY_94;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("MONEY"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "MONEY"),
										expectedValue, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(3), e));
			}
		}
	}
	
	public void testGetMaximumLength_CHAR_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_CHAR_94;
						
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
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(3), e));
			}
		}
	}
	
	public void testGetMaximumLength_NCHAR_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_NCHAR_94;
						
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
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(3), e));
			}
		}
	}
	
	public void testGetMaximumLength_VARCHAR_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_VARCHAR_94;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARCHAR"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(3), e));
			}
		}
	}
	
	public void testGetMaximumLength_LVARCHAR_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("9.4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedValue = MAXIMUM_LENGTH_LVARCHAR_94;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("LVARCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "LVARCHAR"),
										expectedValue, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(3), e));
			}
		}
	}
	
	public void testGetDatabaseMaximumIdentifierLength_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.4"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_94;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(3), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.4"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_94;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							actualValue, expectedValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(3), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.4"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TABLE_94;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(3), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.4"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_VIEW_94;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(3), e));
			}
		}
	}
		
	public void testGetColumnMaximumIdentifierLength_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.4"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_94;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(3), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("9.4"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_94;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(3), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_94(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("9.4"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_94;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(3), e));
			}
		}
	}
	
//==========================================Version 10==============================================
	public void testGetMaximumPrecision_DECIMAL_10(){
		
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
						expectedValue = MAXIMUM_PRECISION_DECIMAL_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DECIMAL"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "DECIMAL"),
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
	
	public void testGetMaximumPrecision_MONEY_10(){
		
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
						expectedValue = MAXIMUM_PRECISION_MONEY_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("MONEY"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "MONEY"),
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
					
					if((dbInformationCollection.get(i).toString()).equals("10.0"))
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
					
					if((dbInformationCollection.get(i).toString()).equals("10.0"))
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
						expectedValue = MAXIMUM_LENGTH_VARCHAR_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARCHAR"),
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
	
	public void testGetMaximumLength_LVARCHAR_10(){
		
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
						expectedValue = MAXIMUM_LENGTH_LVARCHAR_10;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("LVARCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "LVARCHAR"),
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
	
	public void testGetDatabaseMaximumIdentifierLength_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("10.0"))
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
				if((dbInformationCollection.get(i).toString()).equals("10.0"))
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
				if((dbInformationCollection.get(i).toString()).equals("10.0"))
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
				if((dbInformationCollection.get(i).toString()).equals("10.0"))
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
				if((dbInformationCollection.get(i).toString()).equals("10.0"))
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
	
	public void testGetTriggerMaximumIdentifierLength_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("10.0"))
				{
					actualValue = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedValue = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_10;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedValue, actualValue);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_10(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("10.0"))
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
