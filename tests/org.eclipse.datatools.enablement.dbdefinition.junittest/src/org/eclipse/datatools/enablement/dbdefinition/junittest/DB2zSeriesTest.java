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

public class DB2zSeriesTest extends TestCase {
	
	//=========================Version 7=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V7 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V7 = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V7 = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V7 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_V7 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V7 = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V7 = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_V7 = 18;
	
	private final int MAXIMUM_LENGTH_BLOB_V7 = 2147483647;
	private final int MAXIMUM_LENGTH_CLOB_V7 = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V7 = 255;
	private final int MAXIMUM_LENGTH_DBCLOB_V7 = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V7 = 127;
	private final int MAXIMUM_LENGTH_VARCHAR_V7 = 32704;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V7 = 16352;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V7 = 31;
	private final int MAXIMUM_PRECISION_FLOAT_V7 = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V7 = 31;	
	
    //=========================Version 8 Compatibility====================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V8_COMPATIBILITY = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V8_COMPATIBILITY = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V8_COMPATIBILITY = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V8_COMPATIBILITY = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_V8_COMPATIBILITY = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V8_COMPATIBILITY = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V8_COMPATIBILITY = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_V8_COMPATIBILITY = 128;
	
	private final int MAXIMUM_LENGTH_BLOB_V8_COMPATIBILITY = 2147483647;
	private final int MAXIMUM_LENGTH_CLOB_V8_COMPATIBILITY = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V8_COMPATIBILITY = 255;
	private final int MAXIMUM_LENGTH_DBCLOB_V8_COMPATIBILITY = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V8_COMPATIBILITY = 127;
	private final int MAXIMUM_LENGTH_VARCHAR_V8_COMPATIBILITY = 32704;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V8_COMPATIBILITY = 16352;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V8_COMPATIBILITY = 31;
	private final int MAXIMUM_PRECISION_FLOAT_V8_COMPATIBILITY = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V8_COMPATIBILITY = 31;
	
	//=========================Version 8 New Function=====================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V8_NEW_FUNCTION = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V8_NEW_FUNCTION = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V8_NEW_FUNCTION = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V8_NEW_FUNCTION = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_V8_NEW_FUNCTION = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V8_NEW_FUNCTION = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V8_NEW_FUNCTION = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_V8_NEW_FUNCTION = 128;
	
	private final int MAXIMUM_LENGTH_BLOB_V8_NEW_FUNCTION = 2147483647;
	private final int MAXIMUM_LENGTH_CLOB_V8_NEW_FUNCTION = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V8_NEW_FUNCTION = 255;
	private final int MAXIMUM_LENGTH_DBCLOB_V8_NEW_FUNCTION = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V8_NEW_FUNCTION = 127;
	private final int MAXIMUM_LENGTH_VARCHAR_V8_NEW_FUNCTION = 32704;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V8_NEW_FUNCTION = 16352;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V8_NEW_FUNCTION = 31;
	private final int MAXIMUM_PRECISION_FLOAT_V8_NEW_FUNCTION = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V8_NEW_FUNCTION = 31;
	
//	=========================Version 9 Compatibility====================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V9_COMPATIBILITY = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V9_COMPATIBILITY = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V9_COMPATIBILITY = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V9_COMPATIBILITY = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_V9_COMPATIBILITY = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V9_COMPATIBILITY = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V9_COMPATIBILITY = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_V9_COMPATIBILITY = 128;
	
	private final int MAXIMUM_LENGTH_BLOB_V9_COMPATIBILITY = 2147483647;
	private final int MAXIMUM_LENGTH_CLOB_V9_COMPATIBILITY = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V9_COMPATIBILITY = 255;
	private final int MAXIMUM_LENGTH_DBCLOB_V9_COMPATIBILITY = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V9_COMPATIBILITY = 127;
	private final int MAXIMUM_LENGTH_VARCHAR_V9_COMPATIBILITY = 32704;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V9_COMPATIBILITY = 16352;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V9_COMPATIBILITY = 31;
	private final int MAXIMUM_PRECISION_FLOAT_V9_COMPATIBILITY = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V9_COMPATIBILITY = 31;
	
	//=========================Version 9 New Function=====================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V9_NEW_FUNCTION = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V9_NEW_FUNCTION = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V9_NEW_FUNCTION = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V9_NEW_FUNCTION = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_V9_NEW_FUNCTION = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V9_NEW_FUNCTION = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V9_NEW_FUNCTION = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_V9_NEW_FUNCTION = 128;
	
	private final int MAXIMUM_LENGTH_BLOB_V9_NEW_FUNCTION = 2147483647;
	private final int MAXIMUM_LENGTH_CLOB_V9_NEW_FUNCTION = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V9_NEW_FUNCTION = 255;
	private final int MAXIMUM_LENGTH_DBCLOB_V9_NEW_FUNCTION = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V9_NEW_FUNCTION = 127;
	private final int MAXIMUM_LENGTH_VARCHAR_V9_NEW_FUNCTION = 32704;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V9_NEW_FUNCTION = 16352;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V9_NEW_FUNCTION = 31;
	private final int MAXIMUM_PRECISION_FLOAT_V9_NEW_FUNCTION = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V9_NEW_FUNCTION = 31;
	//==========================================================================================
	
	private final String strProduct = "DB2 UDB zSeries";
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
	
//==========================================Version 7==============================================
	
	public void testGetMaximumPrecision_DECIMAL_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V7;
						
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
	
	public void testGetMaximumPrecision_NUMERIC_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V7;
						
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
	
	public void testGetMaximumPrecision_FLOAT_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V7;
						
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
	
	public void testGetMaximumLength_CHAR_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_V7;
						
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
	
	public void testGetMaximumLength_GRAPHIC_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V7;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("GRAPHIC"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "GRAPHIC"),
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
	
	public void testGetMaximumLength_VARCHAR_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V7;
						
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
	
	public void testGetMaximumLength_CLOB_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CLOB_V7;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CLOB"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "CLOB"),
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
	
	public void testGetMaximumLength_BLOB_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BLOB_V7;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("BLOB"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "BLOB"),
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
	
	public void testGetMaximumLength_DBCLOB_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V7;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DBCLOB"))
							{		
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "DBCLOB"),
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
	
	public void testGetMaximumLength_VARGRAPHIC_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V7;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARGRAPHIC"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARGRAPHIC"),
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
	
	public void testGetDatabaseMaximumIdentifierLength_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V7"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V7;
					
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V7"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V7;
					
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V7"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_V7;
					
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V7"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_V7;

					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V7"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V7;
		
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V7"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V7;

					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTablespaceMaximumIdentifierLength_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V7"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V7;
		
					assertEquals(getAssertionFailureMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_V7(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("V7"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V7;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(0), e));
			}
		}
	}
	
//===================================Version 8 Compatibility==============================
	
	public void testGetMaximumPrecision_DECIMAL_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V8_COMPATIBILITY;
						
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
	
	public void testGetMaximumPrecision_NUMERIC_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V8_COMPATIBILITY;
						
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
	
	public void testGetMaximumPrecision_FLOAT_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V8_COMPATIBILITY;
						
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
	
	public void testGetMaximumLength_CHAR_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_V8_COMPATIBILITY;
						
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
	
	public void testGetMaximumLength_GRAPHIC_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V8_COMPATIBILITY;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("GRAPHIC"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "GRAPHIC"),
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
	
	public void testGetMaximumLength_VARCHAR_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V8_COMPATIBILITY;
						
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
	
	public void testGetMaximumLength_CLOB_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CLOB_V8_COMPATIBILITY;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CLOB"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "CLOB"),
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
	
	public void testGetMaximumLength_BLOB_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BLOB_V8_COMPATIBILITY;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("BLOB"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "BLOB"),
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
	
	public void testGetMaximumLength_DBCLOB_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V8_COMPATIBILITY;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DBCLOB"))
							{		
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "DBCLOB"),
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
	
	public void testGetMaximumLength_VARGRAPHIC_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V8_COMPATIBILITY;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARGRAPHIC"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARGRAPHIC"),
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
	
	public void testGetDatabaseMaximumIdentifierLength_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V8_COMPATIBILITY;
							
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V8_COMPATIBILITY;
					
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_V8_COMPATIBILITY;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_V8_COMPATIBILITY;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V8_COMPATIBILITY;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V8_COMPATIBILITY;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTablespaceMaximumIdentifierLength_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V8_COMPATIBILITY;

					assertEquals(getAssertionFailureMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_V8Compatibility(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("V8 (Compatibility Mode)"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V8_COMPATIBILITY;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(1), e));
			}
		}
	}
	
//====================================Version 8 New Function==============================
	
	public void testGetMaximumPrecision_DECIMAL_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V8_NEW_FUNCTION;
						
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
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_NUMERIC_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V8_NEW_FUNCTION;
						
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
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumPrecision_FLOAT_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V8_NEW_FUNCTION;
						
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
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_CHAR_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_V8_NEW_FUNCTION;
						
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
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_GRAPHIC_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V8_NEW_FUNCTION;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("GRAPHIC"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "GRAPHIC"),
										expectedMaxLength, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_VARCHAR_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V8_NEW_FUNCTION;
						
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
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_CLOB_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CLOB_V8_NEW_FUNCTION;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CLOB"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "CLOB"),
										expectedMaxLength, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_BLOB_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BLOB_V8_NEW_FUNCTION;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("BLOB"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "BLOB"),
										expectedMaxLength, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_DBCLOB_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V8_NEW_FUNCTION;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DBCLOB"))
							{		
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "DBCLOB"),
										expectedMaxLength, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetMaximumLength_VARGRAPHIC_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V8_NEW_FUNCTION;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARGRAPHIC"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARGRAPHIC"),
										expectedMaxLength, typeDefinition.getMaximumLength());
							}
						}
					}
				}

			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetDatabaseMaximumIdentifierLength_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V8_NEW_FUNCTION;
					
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V8_NEW_FUNCTION;
					
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_V8_NEW_FUNCTION;
					
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_V8_NEW_FUNCTION;
					
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V8_NEW_FUNCTION;
					
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V8_NEW_FUNCTION;
					
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTablespaceMaximumIdentifierLength_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V8_NEW_FUNCTION;
					
					assertEquals(getAssertionFailureMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}

			}catch(Exception e){
				fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_V8NewFunction(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("V8 (New-Function Mode)"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V8_NEW_FUNCTION;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(2), e));
			}
		}
	}
	
	
//	===================================Version 9 Compatibility==============================
		
		public void testGetMaximumPrecision_DECIMAL_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V9_COMPATIBILITY;
							
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
					fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetMaximumPrecision_NUMERIC_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V9_COMPATIBILITY;
							
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
					fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetMaximumPrecision_FLOAT_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V9_COMPATIBILITY;
							
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
					fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetMaximumLength_CHAR_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_CHAR_V9_COMPATIBILITY;
							
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
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetMaximumLength_GRAPHIC_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V9_COMPATIBILITY;
							
							for(int j = 0; j < dataTypeList.size(); j++)
							{
								if((dataTypeList.get(j)).equals("GRAPHIC"))
								{	
									assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "GRAPHIC"),
											expectedMaxLength, typeDefinition.getMaximumLength());
								}
							}
						}
					}

				}catch(Exception e){
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetMaximumLength_VARCHAR_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V9_COMPATIBILITY;
							
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
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetMaximumLength_CLOB_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_CLOB_V9_COMPATIBILITY;
							
							for(int j = 0; j < dataTypeList.size(); j++)
							{
								if((dataTypeList.get(j)).equals("CLOB"))
								{	
									assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "CLOB"),
											expectedMaxLength, typeDefinition.getMaximumLength());
								}
							}
						}
					}

				}catch(Exception e){
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetMaximumLength_BLOB_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_BLOB_V9_COMPATIBILITY;
							
							for(int j = 0; j < dataTypeList.size(); j++)
							{
								if((dataTypeList.get(j)).equals("BLOB"))
								{	
									assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "BLOB"),
											expectedMaxLength, typeDefinition.getMaximumLength());
								}
							}
						}
					}

				}catch(Exception e){
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetMaximumLength_DBCLOB_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V9_COMPATIBILITY;
							
							for(int j = 0; j < dataTypeList.size(); j++)
							{
								if((dataTypeList.get(j)).equals("DBCLOB"))
								{		
									assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "DBCLOB"),
											expectedMaxLength, typeDefinition.getMaximumLength());
								}
							}
						}
					}

				}catch(Exception e){
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetMaximumLength_VARGRAPHIC_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V9_COMPATIBILITY;
							
							for(int j = 0; j < dataTypeList.size(); j++)
							{
								if((dataTypeList.get(j)).equals("VARGRAPHIC"))
								{	
									assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARGRAPHIC"),
											expectedMaxLength, typeDefinition.getMaximumLength());
								}
							}
						}
					}

				}catch(Exception e){
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetSchemaMaximumIdentifierLength_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V9_COMPATIBILITY;
						
						assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}

				}catch(Exception e){
					fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetTableMaximumIdentifierLength_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_V9_COMPATIBILITY;
					
						assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}
					
				}catch(Exception e){
					fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetViewMaximumIdentifierLength_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_V9_COMPATIBILITY;
					
						assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}
					
				}catch(Exception e){
					fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetColumnMaximumIdentifierLength_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V9_COMPATIBILITY;
					
						assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}
					
				}catch(Exception e){
					fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetTriggerMaximumIdentifierLength_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V9_COMPATIBILITY;
					
						assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}
					
				}catch(Exception e){
					fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(3), e));
				}
			}
		}
		
		public void testGetTablespaceMaximumIdentifierLength_V9Compatibility(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (Compatibility Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V9_COMPATIBILITY;

						assertEquals(getAssertionFailureMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}

				}catch(Exception e){
					fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(3), e));
				}
			}
		}
				
//	====================================Version 9 New Function==============================
		
		public void testGetMaximumPrecision_DECIMAL_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V9_NEW_FUNCTION;
							
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
					fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetMaximumPrecision_NUMERIC_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V9_NEW_FUNCTION;
							
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
					fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetMaximumPrecision_FLOAT_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V9_NEW_FUNCTION;
							
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
					fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetMaximumLength_CHAR_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_CHAR_V9_NEW_FUNCTION;
							
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
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetMaximumLength_GRAPHIC_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V9_NEW_FUNCTION;
							
							for(int j = 0; j < dataTypeList.size(); j++)
							{
								if((dataTypeList.get(j)).equals("GRAPHIC"))
								{	
									assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "GRAPHIC"),
											expectedMaxLength, typeDefinition.getMaximumLength());
								}
							}
						}
					}

				}catch(Exception e){
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetMaximumLength_VARCHAR_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V9_NEW_FUNCTION;
							
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
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetMaximumLength_CLOB_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_CLOB_V9_NEW_FUNCTION;
							
							for(int j = 0; j < dataTypeList.size(); j++)
							{
								if((dataTypeList.get(j)).equals("CLOB"))
								{	
									assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "CLOB"),
											expectedMaxLength, typeDefinition.getMaximumLength());
								}
							}
						}
					}

				}catch(Exception e){
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetMaximumLength_BLOB_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_BLOB_V9_NEW_FUNCTION;
							
							for(int j = 0; j < dataTypeList.size(); j++)
							{
								if((dataTypeList.get(j)).equals("BLOB"))
								{	
									assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "BLOB"),
											expectedMaxLength, typeDefinition.getMaximumLength());
								}
							}
						}
					}

				}catch(Exception e){
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetMaximumLength_DBCLOB_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V9_NEW_FUNCTION;
							
							for(int j = 0; j < dataTypeList.size(); j++)
							{
								if((dataTypeList.get(j)).equals("DBCLOB"))
								{		
									assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "DBCLOB"),
											expectedMaxLength, typeDefinition.getMaximumLength());
								}
							}
						}
					}

				}catch(Exception e){
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetMaximumLength_VARGRAPHIC_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
					
					while(dataType.hasNext())
					{
						typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
						
						if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
						{
							dataTypeList = typeDefinition.getName();
							expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V9_NEW_FUNCTION;
							
							for(int j = 0; j < dataTypeList.size(); j++)
							{
								if((dataTypeList.get(j)).equals("VARGRAPHIC"))
								{	
									assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARGRAPHIC"),
											expectedMaxLength, typeDefinition.getMaximumLength());
								}
							}
						}
					}

				}catch(Exception e){
					fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetSchemaMaximumIdentifierLength_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V9_NEW_FUNCTION;
						
						assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}

				}catch(Exception e){
					fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetTableMaximumIdentifierLength_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_V9_NEW_FUNCTION;
						
						assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}

				}catch(Exception e){
					fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetViewMaximumIdentifierLength_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_V9_NEW_FUNCTION;
						
						assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}

				}catch(Exception e){
					fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetColumnMaximumIdentifierLength_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V9_NEW_FUNCTION;
						
						assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}

				}catch(Exception e){
					fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetTriggerMaximumIdentifierLength_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V9_NEW_FUNCTION;
						
						assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}

				}catch(Exception e){
					fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(4), e));
				}
			}
		}
		
		public void testGetTablespaceMaximumIdentifierLength_V9NewFunction(){
			
			for(int i = 0; i < definitionCollection.size(); i++)
			{
				try{
					if((dbInformationCollection.get(i).toString()).equals("V9 (New-Function Mode)"))
					{
						actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
						expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V9_NEW_FUNCTION;
						
						assertEquals(getAssertionFailureMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
								expectedIdentifierLength, actualIdentifierLength);
					}

				}catch(Exception e){
					fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(4), e));
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
