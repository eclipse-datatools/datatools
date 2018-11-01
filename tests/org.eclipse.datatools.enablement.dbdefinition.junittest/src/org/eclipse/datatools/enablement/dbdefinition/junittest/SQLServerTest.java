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

public class SQLServerTest extends TestCase {
	
	//=========================Version 2000=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_2000 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_2000 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_2000 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_2000 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_2000 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_2000 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_2000 = 128;
	
	private final int MAXIMUM_LENGTH_BINARY_2000 = 8000;
	private final int MAXIMUM_LENGTH_CHAR_2000 = 8000;
	private final int MAXIMUM_LENGTH_NCHAR_2000 = 4000;
	private final int MAXIMUM_LENGTH_VARBINARY_2000 = 8000;
	private final int MAXIMUM_LENGTH_VARCHAR_2000 = 8000;
	
	private final int MAXIMUM_PRECISION_DECIMAL_2000 = 38;
	private final int MAXIMUM_PRECISION_FLOAT_2000 = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_2000 = 38;	
	
    //=========================Version 2005=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_2005 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_2005 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_2005 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_2005 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_2005 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_2005 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_2005 = 128;
	
	private final int MAXIMUM_LENGTH_BINARY_2005 = 8000;
	private final int MAXIMUM_LENGTH_CHAR_2005 = 8000;
	private final int MAXIMUM_LENGTH_NCHAR_2005 = 4000;
	private final int MAXIMUM_LENGTH_VARBINARY_2005 = 8000;
	private final int MAXIMUM_LENGTH_VARCHAR_2005 = 8000;
	
	private final int MAXIMUM_PRECISION_DECIMAL_2005 = 38;
	private final int MAXIMUM_PRECISION_FLOAT_2005 = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_2005 = 38;
	
	
	//==========================================================================================
	
	private final String strProduct = "SQL Server";
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
	
//==========================================Version 2000==============================================
	
	public void testGetMaximumPrecision_DECIMAL_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2000"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_2000;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DECIMAL"))
							{								
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "DECIMAL"),
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
	
	public void testGetMaximumPrecision_NUMERIC_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2000"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_2000;
						
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
	
	public void testGetMaximumPrecision_FLOAT_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2000"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_2000;
						
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
	
	public void testGetMaximumLength_CHAR_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2000"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_2000;
						
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
	
	public void testGetMaximumLength_NCHAR_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2000"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_NCHAR_2000;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "NCHAR"),
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
	
	public void testGetMaximumLength_VARCHAR_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2000"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_2000;
						
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
	
	public void testGetMaximumLength_BINARY_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2000"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BINARY_2000;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("BINARY"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "BINARY"),
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
	
	public void testGetMaximumLength_VARBINARY_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2000"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARBINARY_2000;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARBINARY"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARBINARY"),
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
	
	public void testGetDatabaseMaximumIdentifierLength_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2000"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_2000;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2000"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_2000;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2000"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_2000;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2000"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_2000;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2000"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_2000;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2000"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_2000;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_2000(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("2000"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_2000;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(0), e));
			}
		}
	}
	
//	==========================================Version 2005==============================================
	
	public void testGetMaximumPrecision_DECIMAL_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2005"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_2005;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DECIMAL"))
							{								
								assertEquals(getAssertionFailureMessage("getMaximumPrecision()", dbInformationCollection.get(i), "DECIMAL"),
										expectedMaxPrecision, typeDefinition.getMaximumPrecision());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_NUMERIC_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2005"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_2005;
						
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
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_FLOAT_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2005"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_2005;
						
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
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumLength_CHAR_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2005"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_2005;
						
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
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumLength_NCHAR_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2005"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_NCHAR_2005;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("NCHAR"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "NCHAR"),
										expectedMaxLength, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumLength_VARCHAR_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2005"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_2005;
						
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
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumLength_BINARY_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2005"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BINARY_2005;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("BINARY"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "BINARY"),
										expectedMaxLength, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetMaximumLength_VARBINARY_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("2005"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARBINARY_2005;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARBINARY"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARBINARY"),
										expectedMaxLength, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetDatabaseMaximumIdentifierLength_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2005"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_2005;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2005"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_2005;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2005"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_2005;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2005"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_2005;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2005"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_2005;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("2005"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_2005;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_2005(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("2005"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_2005;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(1), e));
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
