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

public class DB2iSeriesTest extends TestCase {
	
	//=========================V5R2=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V5R2 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V5R2 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V5R2 = 10;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V5R2 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_V5R2 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V5R2 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_V5R2 = 128;
	
	private final int MAXIMUM_LENGTH_BLOB_V5R2 = 2147483647;
	private final int MAXIMUM_LENGTH_CLOB_V5R2 = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V5R2 = 32766;
	private final int MAXIMUM_LENGTH_DATALINK_V5R2 = 32717;
	private final int MAXIMUM_LENGTH_DBCLOB_V5R2 = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V5R2 = 16383;
	private final int MAXIMUM_LENGTH_VARCHAR_V5R2 = 32740;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V5R2 = 16370;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V5R2 = 31;
	private final int MAXIMUM_PRECISION_FLOAT_V5R2 = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V5R2 = 31;	
	
    //=========================V5R3=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V5R3 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V5R3 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V5R3 = 10;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V5R3 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_V5R3 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V5R3 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_V5R3 = 128;
	
	private final int MAXIMUM_LENGTH_BLOB_V5R3 = 2147483647;
	private final int MAXIMUM_LENGTH_CLOB_V5R3 = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V5R3 = 32766;
	private final int MAXIMUM_LENGTH_DATALINK_V5R3 = 32717;
	private final int MAXIMUM_LENGTH_DBCLOB_V5R3 = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V5R3 = 16383;
	private final int MAXIMUM_LENGTH_VARBINARY_V5R3 = 32740;
	private final int MAXIMUM_LENGTH_VARCHAR_V5R3 = 32740;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V5R3 = 16370;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V5R3 = 63;
	private final int MAXIMUM_PRECISION_FLOAT_V5R3 = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V5R3 = 63;	
	
//	=========================V5R4=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V5R4 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V5R4 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V5R4 = 10;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V5R4 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_V5R4 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V5R4 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_V5R4 = 128;
	
	private final int MAXIMUM_LENGTH_BLOB_V5R4 = 2147483647;
	private final int MAXIMUM_LENGTH_CLOB_V5R4 = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V5R4 = 32766;
	private final int MAXIMUM_LENGTH_DATALINK_V5R4 = 32717;
	private final int MAXIMUM_LENGTH_DBCLOB_V5R4 = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V5R4 = 16383;
	private final int MAXIMUM_LENGTH_VARBINARY_V5R4 = 32740;
	private final int MAXIMUM_LENGTH_VARCHAR_V5R4 = 32740;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V5R4 = 16370;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V5R4 = 63;
	private final int MAXIMUM_PRECISION_FLOAT_V5R4 = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V5R4 = 63;	
	
	//==========================================================================================
	
	private final String strProduct = "DB2 UDB iSeries";
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
	
//==========================================V5R2==============================================
	
	public void testGetMaximumPrecision_DECIMAL_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V5R2;
						
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
	
	public void testGetMaximumPrecision_NUMERIC_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V5R2;
						
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
	
	public void testGetMaximumPrecision_FLOAT_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V5R2;
						
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
	
	public void testGetMaximumLength_CHAR_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_V5R2;
						
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
	
	public void testGetMaximumLength_GRAPHIC_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V5R2;
						
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
	
	public void testGetMaximumLength_VARCHAR_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V5R2;
						
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
	
	public void testGetMaximumLength_CLOB_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CLOB_V5R2;
						
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
	
	public void testGetMaximumLength_BLOB_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BLOB_V5R2;
						
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
	
	public void testGetMaximumLength_DATALINK_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DATALINK_V5R2;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DATALINK"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "DATALINK"),
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
	
	public void testGetMaximumLength_DBCLOB_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V5R2;
						
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
		
	public void testGetMaximumLength_VARGRAPHIC_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V5R2;
						
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
	
	public void testGetDatabaseMaximumIdentifierLength_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V5R2;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V5R2;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_V5R2;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_V5R2;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V5R2;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V5R2;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_V5R2(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("V5R2"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V5R2;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(0), e));
			}
		}
	}
	
//==========================================V5R3==============================================
	
	public void testGetMaximumPrecision_DECIMAL_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V5R3;
						
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
	
	public void testGetMaximumPrecision_NUMERIC_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V5R3;
						
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
	
	public void testGetMaximumPrecision_FLOAT_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V5R3;
						
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
	
	public void testGetMaximumLength_CHAR_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_V5R3;
						
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
	
	public void testGetMaximumLength_GRAPHIC_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V5R3;
						
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
	
	public void testGetMaximumLength_VARCHAR_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V5R3;
						
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
	
	public void testGetMaximumLength_VARBINARY_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARBINARY_V5R3;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARCHAR FOR BIT DATA"))
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
	
	public void testGetMaximumLength_CLOB_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CLOB_V5R3;
						
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
	
	public void testGetMaximumLength_BLOB_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BLOB_V5R3;
						
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
	
	public void testGetMaximumLength_DATALINK_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DATALINK_V5R3;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DATALINK"))
							{							
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "DATALINK"),
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
	
	public void testGetMaximumLength_DBCLOB_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V5R3;
						
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
	
	public void testGetMaximumLength_VARGRAPHIC_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R3"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V5R3;
						
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
	
	public void testGetDatabaseMaximumIdentifierLength_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R3"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V5R3;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R3"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V5R3;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R3"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_V5R3;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R3"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_V5R3;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R3"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V5R3;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R3"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V5R3;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_V5R3(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("V5R3"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V5R3;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(1), e));
			}
		}
	}
	
//==========================================V5R4==============================================
	
	public void testGetMaximumPrecision_DECIMAL_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V5R4;
						
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
	
	public void testGetMaximumPrecision_NUMERIC_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V5R4;
						
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
	
	public void testGetMaximumPrecision_FLOAT_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V5R4;
						
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
	
	public void testGetMaximumLength_CHAR_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_V5R4;
						
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
	
	public void testGetMaximumLength_GRAPHIC_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V5R4;
						
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
	
	public void testGetMaximumLength_VARCHAR_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V5R4;
						
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
	
	public void testGetMaximumLength_VARBINARY_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARBINARY_V5R4;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("VARCHAR FOR BIT DATA"))
							{									
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "VARBINARY"),
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
	
	public void testGetMaximumLength_CLOB_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CLOB_V5R4;
						
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
	
	public void testGetMaximumLength_BLOB_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BLOB_V5R4;
						
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
	
	public void testGetMaximumLength_DATALINK_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DATALINK_V5R4;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("DATALINK"))
							{							
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "DATALINK"),
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
	
	public void testGetMaximumLength_DBCLOB_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V5R4;
						
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
	
	public void testGetMaximumLength_VARGRAPHIC_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V5R4"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V5R4;
						
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
	
	public void testGetDatabaseMaximumIdentifierLength_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R4"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V5R4;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R4"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V5R4;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R4"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_V5R4;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R4"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_V5R4;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R4"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V5R4;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V5R4"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V5R4;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_V5R4(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("V5R4"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V5R4;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(2), e));
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
