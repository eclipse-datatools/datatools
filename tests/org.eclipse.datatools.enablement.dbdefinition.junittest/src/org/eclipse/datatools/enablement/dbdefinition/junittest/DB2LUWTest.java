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
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;


public class DB2LUWTest extends TestCase {
	
	//=========================Version 7.2=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V72 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V72 = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V72 = 18;

	private final int MAXIMUM_LENGTH_BINARY_V72 = 254;
	private final int MAXIMUM_LENGTH_BLOB_V72 = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V72 = 254;
	private final int MAXIMUM_LENGTH_DATALINK_V72 = 200;
	private final int MAXIMUM_LENGTH_DBCLOB_V72 = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V72 = 127;
	private final int MAXIMUM_LENGTH_VARBINARY_V72 = 32672;
	private final int MAXIMUM_LENGTH_VARCHAR_V72 = 32672;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V72 = 16336;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V72 = 31;
	private final int MAXIMUM_PRECISION_FLOAT_V72 = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V72 = 31;
	
	//=========================Version 8.1=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V81 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V81 = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_NICKNAME_V81 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V81 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V81 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_V81 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V81 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V81 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_UDT_V81 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_V81 = 128;
	
	private final int MAXIMUM_LENGTH_BINARY_V81 = 254;
	private final int MAXIMUM_LENGTH_BLOB_V81 = 2147483647;
	private final int MAXIMUM_LENGTH_CLOB_V81 = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V81 = 254;
	private final int MAXIMUM_LENGTH_DATALINK_V81 = 200;
	private final int MAXIMUM_LENGTH_DBCLOB_V81 = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V81 = 127;
	private final int MAXIMUM_LENGTH_VARBINARY_V81 = 32672;
	private final int MAXIMUM_LENGTH_VARCHAR_V81 = 32672;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V81 = 16336;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V81 = 31;
	private final int MAXIMUM_PRECISION_FLOAT_V81 = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V81 = 31;	
	
    //=========================Version 8.2=======================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V82 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V82 = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_NICKNAME_V82 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V82 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V82 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_V82 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V82 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V82 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_UDT_V82 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_V82 = 128;
	
	private final int MAXIMUM_LENGTH_BINARY_V82 = 254;
	private final int MAXIMUM_LENGTH_BLOB_V82 = 2147483647;
	private final int MAXIMUM_LENGTH_CLOB_V82 = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V82 = 254;
	private final int MAXIMUM_LENGTH_DATALINK_V82 = 200;
	private final int MAXIMUM_LENGTH_DBCLOB_V82 = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V82 = 127;
	private final int MAXIMUM_LENGTH_VARBINARY_V82 = 32672;
	private final int MAXIMUM_LENGTH_VARCHAR_V82 = 32672;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V82 = 16336;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V82 = 31;
	private final int MAXIMUM_PRECISION_FLOAT_V82 = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V82 = 31;
	
	//=========================Version 9.1==========================================
	private final int MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V91 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V91 = 8;
	private final int MAXIMUM_IDENTIFIER_LENGTH_NICKNAME_V91 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V91 = 30;
	private final int MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V91 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLE_V91 = 128;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V91 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V91 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_UDT_V91 = 18;
	private final int MAXIMUM_IDENTIFIER_LENGTH_VIEW_V91 = 128;
	
	private final int MAXIMUM_LENGTH_BINARY_V91 = 254;
	private final int MAXIMUM_LENGTH_BLOB_V91 = 2147483647;
	private final int MAXIMUM_LENGTH_CLOB_V91 = 2147483647;
	private final int MAXIMUM_LENGTH_CHAR_V91 = 254;
	private final int MAXIMUM_LENGTH_DATALINK_V91 = 200;
	private final int MAXIMUM_LENGTH_DBCLOB_V91 = 1073741823;
	private final int MAXIMUM_LENGTH_GRAPHIC_V91 = 127;
	private final int MAXIMUM_LENGTH_VARBINARY_V91 = 32672;
	private final int MAXIMUM_LENGTH_VARCHAR_V91 = 32672;
	private final int MAXIMUM_LENGTH_VARGRAPHIC_V91 = 16336;
	
	private final int MAXIMUM_PRECISION_DECIMAL_V91 = 31;
	private final int MAXIMUM_PRECISION_FLOAT_V91 = 53;
	private final int MAXIMUM_PRECISION_NUMERIC_V91 = 31;
	//==========================================================================================
	
	private final String strProduct = "DB2 UDB";
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
	
//==========================================Version 7.2==============================================	
	

	public void testGetMaximumPrecision_DECIMAL_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V72;
						
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
	
	public void testGetMaximumPrecision_NUMERIC_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V72;
						
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
	
	public void testGetMaximumPrecision_FLOAT_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V72;
						
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
	
	public void testGetMaximumLength_CHAR_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_V72;
						
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
	
	public void testGetMaximumLength_GRAPHIC_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V72;
						
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
	
	public void testGetMaximumLength_VARCHAR_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V72;
						
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
	
	public void testGetMaximumLength_BINARY_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BINARY_V72;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CHAR FOR BIT DATA"))
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
	
	public void testGetMaximumLength_VARBINARY_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARBINARY_V72;
						
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
	
	public void testGetMaximumLength_BLOB_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BLOB_V72;
						
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
	
	public void testGetMaximumLength_DATALINK_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DATALINK_V72;
						
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
	
	public void testGetMaximumLength_DBCLOB_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V72;
						
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
	
	public void testGetMaximumLength_VARGRAPHIC_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V7.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V72;
						
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
	
	public void testGetDatabaseMaximumIdentifierLength_V72(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V7.2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V72;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
//==========================================Version 8.1==============================================
	
	public void testGetMaximumPrecision_DECIMAL_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V81;
						
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
	
	public void testGetMaximumPrecision_NUMERIC_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V81;
						
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
	
	public void testGetMaximumPrecision_FLOAT_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V81;
						
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
	
	public void testGetMaximumLength_CHAR_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_V81;
						
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
	
	public void testGetMaximumLength_GRAPHIC_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V81;
						
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
	
	public void testGetMaximumLength_VARCHAR_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V81;
						
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
	
	public void testGetMaximumLength_BINARY_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BINARY_V81;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CHAR FOR BIT DATA"))
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
	
	public void testGetMaximumLength_VARBINARY_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARBINARY_V81;
						
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
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetMaximumLength_CLOB_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CLOB_V81;
						
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
	
	public void testGetMaximumLength_BLOB_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BLOB_V81;
						
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
	
	public void testGetMaximumLength_DATALINK_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DATALINK_V81;
						
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
	
	public void testGetMaximumLength_DBCLOB_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V81;
						
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
	
	public void testGetMaximumLength_VARGRAPHIC_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V81;
						
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
	
	public void testGetDatabaseMaximumIdentifierLength_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V81;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V81;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_V81;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_V81;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetNicknameMaximumIdentifierLength_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getNicknameMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_NICKNAME_V81;
				
					assertEquals(getAssertionFailureMessage("getNicknameMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getNicknameMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V81;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetUDTMaximumIdentifierLength_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getUserDefinedTypeMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_UDT_V81;
				
					assertEquals(getAssertionFailureMessage("getUserDefinedTypeMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getUserDefinedTypeMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V81;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetTablespaceMaximumIdentifierLength_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V81;
				
					assertEquals(getAssertionFailureMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(0), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_V81(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("V8.1"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V81;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(0), e));
			}
		}
	}
	
//==========================================Version 8.2==============================================
	
	public void testGetMaximumPrecision_DECIMAL_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V82;
						
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
	
	public void testGetMaximumPrecision_NUMERIC_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V82;
						
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
	
	public void testGetMaximumPrecision_FLOAT_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V82;
						
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
	
	public void testGetMaximumLength_CHAR_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_V82;
						
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
	
	public void testGetMaximumLength_GRAPHIC_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V82;
						
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
	
	public void testGetMaximumLength_VARCHAR_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V82;
						
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
	
	public void testGetMaximumLength_BINARY_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BINARY_V82;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CHAR FOR BIT DATA"))
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
	
	public void testGetMaximumLength_VARBINARY_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARBINARY_V82;
						
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
	
	public void testGetMaximumLength_CLOB_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CLOB_V82;
						
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
	
	public void testGetMaximumLength_BLOB_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BLOB_V82;
						
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
	
	public void testGetMaximumLength_DATALINK_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DATALINK_V82;
						
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
	
	public void testGetMaximumLength_DBCLOB_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V82;
						
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
	
	public void testGetMaximumLength_VARGRAPHIC_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V8.2"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V82;
						
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
	
	public void testGetDatabaseMaximumIdentifierLength_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V82;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V82;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_V82;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_V82;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetNicknameMaximumIdentifierLength_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getNicknameMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_NICKNAME_V82;
				
					assertEquals(getAssertionFailureMessage("getNicknameMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getNicknameMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V82;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetUDTMaximumIdentifierLength_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getUserDefinedTypeMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_UDT_V82;
				
					assertEquals(getAssertionFailureMessage("getUserDefinedTypeMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getUserDefinedTypeMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V82;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetTablespaceMaximumIdentifierLength_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V8.2"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V82;
				
					assertEquals(getAssertionFailureMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(1), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_V82(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("V8.2"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V82;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(1), e));
			}
		}
	}
	
//==========================================Version 9.1==============================================
	
	public void testGetMaximumPrecision_DECIMAL_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_DECIMAL_V91;
						
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
	
	public void testGetMaximumPrecision_NUMERIC_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_NUMERIC_V91;
						
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
	
	public void testGetMaximumPrecision_FLOAT_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxPrecision = MAXIMUM_PRECISION_FLOAT_V91;
						
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
	
	public void testGetMaximumLength_CHAR_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CHAR_V91;
						
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
	
	public void testGetMaximumLength_GRAPHIC_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_GRAPHIC_V91;
						
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
	
	public void testGetMaximumLength_VARCHAR_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARCHAR_V91;
						
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
	
	public void testGetMaximumLength_BINARY_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BINARY_V91;
						
						for(int j = 0; j < dataTypeList.size(); j++)
						{
							if((dataTypeList.get(j)).equals("CHAR FOR BIT DATA"))
							{	
								assertEquals(getAssertionFailureMessage("getMaximumLength()", dbInformationCollection.get(i), "BINARY"),
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
	
	public void testGetMaximumLength_VARBINARY_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARBINARY_V91;
						
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
	
	public void testGetMaximumLength_CLOB_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_CLOB_V91;
						
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
	
	public void testGetMaximumLength_BLOB_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_BLOB_V91;
						
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
	
	public void testGetMaximumLength_DATALINK_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DATALINK_V91;
						
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
	
	public void testGetMaximumLength_DBCLOB_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_DBCLOB_V91;
						
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
	
	public void testGetMaximumLength_VARGRAPHIC_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					if((dbInformationCollection.get(i).toString()).equals("V9.1"))
					{
						dataTypeList = typeDefinition.getName();
						expectedMaxLength = MAXIMUM_LENGTH_VARGRAPHIC_V91;
						
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
	
	public void testGetDatabaseMaximumIdentifierLength_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V9.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_DATABASE_V91;
				
					assertEquals(getAssertionFailureMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V9.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_SCHEMA_V91;
				
					assertEquals(getAssertionFailureMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V9.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLE_V91;
				
					assertEquals(getAssertionFailureMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V9.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_VIEW_V91;
				
					assertEquals(getAssertionFailureMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetNicknameMaximumIdentifierLength_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V9.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getNicknameMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_NICKNAME_V91;
				
					assertEquals(getAssertionFailureMessage("getNicknameMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getNicknameMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V9.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_COLUMN_V91;
				
					assertEquals(getAssertionFailureMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetUDTMaximumIdentifierLength_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V9.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getUserDefinedTypeMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_UDT_V91;
				
					assertEquals(getAssertionFailureMessage("getUserDefinedTypeMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getUserDefinedTypeMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V9.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TRIGGER_V91;
				
					assertEquals(getAssertionFailureMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetTablespaceMaximumIdentifierLength_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				if((dbInformationCollection.get(i).toString()).equals("V9.1"))
				{
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_TABLESPACE_V91;
				
					assertEquals(getAssertionFailureMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(2), e));
			}
		}
	}
	
	public void testGetStoredProcedureMaximumIdentifierLength_V91(){
		
		for(int i = 0; i < definitionCollection.size(); i++)
		{
			try{
				
				if((dbInformationCollection.get(i).toString()).equals("V9.1"))
				{
					procedure = SQLRoutinesFactory.eINSTANCE.createProcedure();
					actualIdentifierLength = ((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(procedure);
					expectedIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_STORED_PROCEDURE_V91;
				
					assertEquals(getAssertionFailureMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(i), ""),
							expectedIdentifierLength, actualIdentifierLength);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(2), e));
			}
		}
	}
	
	/*
	 System.out.println("Version: " + dbInformationCollection.get(i).toString() + "\n");
	 System.out.println("Actual: " + typeDefinition.getMaximumPrecision() + "\n");
	 
	 System.out.println("Product: " + (dataTypeList.get(j)));
	 System.out.println("Version: " + dbInformationCollection.get(i).toString());
	 System.out.println("Expected: " + expectedMaxPrecision);
	 System.out.println("Actual: " + typeDefinition.getMaximumPrecision() + "\n");
	 
	 System.out.println("Product: " + (dataTypeList.get(j)));
	 System.out.println("Version: " + dbInformationCollection.get(i).toString());
	 System.out.println("Expected: " + expectedMaxLength);
	 System.out.println("Actual: " + typeDefinition.getMaximumLength() + "\n");
	 
	 System.out.println("Expected: " + expectedIdentifierLength);
	 System.out.println("Actual: " + actualIdentifierLength + "\n");
	 
	 
	 
	 System.out.println("Product: " + (dataTypeList.get(j)));
	 System.out.println("Version: " + dbInformationCollection.get(i).toString());
	 System.out.println("Expected: " + expectedValue);
	 System.out.println("Actual: " + typeDefinition.getMaximumPrecision() + "\n");
	 
	 System.out.println("Product: " + (dataTypeList.get(j)));
	 System.out.println("Version: " + dbInformationCollection.get(i).toString());
	 System.out.println("Expected: " + expectedValue);
	 System.out.println("Actual: " + typeDefinition.getMaximumLength() + "\n");
	 
	 System.out.println("Product: " + (dataTypeList.get(j)));
	 System.out.println("Version: " + dbInformationCollection.get(i).toString());
	 System.out.println("Expected: " + expectedValue);
	 System.out.println("Actual: " + typeDefinition.getMinimumScale() + "\n");
	 
	 System.out.println("Product: " + (dataTypeList.get(j)));
	 System.out.println("Version: " + dbInformationCollection.get(i).toString());
	 System.out.println("Expected: " + expectedValue);
	 System.out.println("Actual: " + typeDefinition.getMaximumScale() + "\n");
	 
	 System.out.println("Expected: " + expectedValue);
	 System.out.println("Actual: " + actualValue + "\n");
	 */
	
	private String getAssertionFailureMessage(String methodName, Object versionName, String dataName){
		
		String temp = "\nData type: ";
		
		if(dataName.equals(""))
		{
			temp = "";
		}
		
		return ("\nPackage: " + PACKAGE_NAME +
				"\nMethod: " + methodName +
			    "\nProduct: " + strProduct + " LUW" +
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
