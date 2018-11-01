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

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;

public class PredefinedDataTypeDefinitionGenericTest extends TestCase {
	
	private final String PACKAGE_NAME = "org.eclipse.datatools.enablement.dbdefinition.junittest";
	private final String FILE_NAME = "PredefinedDataTypeDefinition.java";
	private ArrayList definitionCollection = new ArrayList();
	private ArrayList dbInformationCollection = new ArrayList();
	private ArrayList dataTypeCollection = new ArrayList();
	private Iterator dataType;
	private Iterator product = null;
	private Iterator productVersion = null;
	private PredefinedDataTypeDefinition typeDefinition = null;
	private DataType type = null;
	private String strProduct = null;
	private String strProductVersion = null;
	private DatabaseDefinition definition = null;
	
	public void setUp(){
		
		try{
			product = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getProducts();
			assertNotNull("\nMethod: getProducts()" +
					      "\nError: The method returns a NULL value in startUp()!", product);

			while(product.hasNext())
			{
				strProduct = product.next().toString();
				productVersion = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getVersions(strProduct);
				assertNotNull("\nMethod: getVersions()" +
						      "\nError: The method returns a NULL value in startUp()", productVersion);
					
				while(productVersion.hasNext())
				{
					strProductVersion = productVersion.next().toString();
					definition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(strProduct, strProductVersion);
					assertNotNull("\nMethod: getDefinition()" +
							      "\nError: The method returns a NULL value in startUp()", definition);
					
					dbInformationCollection.add(strProduct);
					dbInformationCollection.add(strProductVersion);
					definitionCollection.add(definition);
				}
			}
		}
		catch(Exception e){
			fail("\nMethod: setUp()" +
				 "\nException: " + e.toString() +
			     "\nDescription: The set up is incomplete");
		}
	}
	
	public void testGetLeadingFieldQualifierDefinition(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					assertNotNull(getAssertionFailureMessage("getLeadingFieldQualifierDefinition()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						typeDefinition.getLeadingFieldQualifierDefinition());
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getLeadingFieldQualifierDefinition()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetTrailingFieldQualifierDefinition(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					assertNotNull(getAssertionFailureMessage("getTrailingFieldQualifierDefinition()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						typeDefinition.getTrailingFieldQualifierDefinition());
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getTrailingFieldQualifierDefinition()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDefaultTrailingFieldQualifierDefinition(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getDefaultTrailingFieldQualifierDefinition();
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDefaultTrailingFieldQualifierDefinition()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetDefaultTrailingFieldQualifierDefinition(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setDefaultTrailingFieldQualifierDefinition(null);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("setDefaultTrailingFieldQualifierDefinition(FieldQualifierDefinition value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDefaultLeadingFieldQualifierDefinition(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getDefaultLeadingFieldQualifierDefinition();
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDefaultLeadingFieldQualifierDefinition()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetDefaultLeadingFieldQualifierDefinition(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setDefaultLeadingFieldQualifierDefinition(null);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("setDefaultLeadingFieldQualifierDefinition(FieldQualifierDefinition value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}

	public void testIsLengthSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isLengthSupported();
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("isLengthSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetLengthSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setLengthSupported(true);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setLengthSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsScaleSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isScaleSupported();
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("isScaleSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetScaleSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setScaleSupported(true);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("setScaleSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsPrecisionSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isPrecisionSupported();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("isPrecisionSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetPrecisionSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setPrecisionSupported(true);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("setPrecisionSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsKeyConstraintSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isKeyConstraintSupported();
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("isKeyConstraintSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetKeyConstraintSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setKeyConstraintSupported(true);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setKeyConstraintSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsIdentitySupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isIdentitySupported();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("isIdentitySupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetIdentitySupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setIdentitySupported(true);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setIdentitySupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsMultipleColumnsSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isMultipleColumnsSupported();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("isMultipleColumnsSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetMultipleColumnsSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setMultipleColumnsSupported(true);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setMultipleColumnsSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsNullableSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isNullableSupported();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("isNullableSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetNullableSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setNullableSupported(true);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setNullableSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsDefaultSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isDefaultSupported();
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("isDefaultSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetDefaultSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setDefaultSupported(true);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setDefaultSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsClusteringSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isClusteringSupported();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("isClusteringSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetClusteringSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setClusteringSupported(true);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setClusteringSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsFillFactorSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isFillFactorSupported();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("isFillFactorSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetFillFactorSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setFillFactorSupported(true);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("setFillFactorSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsBitDataSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isBitDataSupported();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("isBitDataSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetBitDataSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setBitDataSupported(true);
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("setBitDataSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetMaximumValue(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getMaximumValue();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumValue()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetMaximumValue(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setMaximumValue(10);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setMaximumValue(long value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetMinimumValue(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getMinimumValue();
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMinimumValue()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetMinimumValue(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setMinimumValue(10);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setMinimumValue(long value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetMaximumLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getMaximumLength();
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetMaximumLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setMaximumLength(10);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setMaximumLength(int value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetMaximumPrecision(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getMaximumPrecision();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumPrecision()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetMaximumPrecision(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setMaximumPrecision(10);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setMaximumPrecision(int value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetMaximumScale(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getMaximumScale();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumScale()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetMaximumScale(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setMaximumScale(10);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setMaximumScale(int value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetMinimumScale(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getMinimumScale();
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getMinimumScale()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetMinimumScale(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setMinimumScale(10);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("setMinimumScale(int value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDefaultValueTypes(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					assertNotNull(getAssertionFailureMessage("getDefaultValueTypes()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						typeDefinition.getDefaultValueTypes());
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getDefaultValueTypes()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetPrimitiveType(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					assertNotNull(getAssertionFailureMessage("getPrimitiveType()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						typeDefinition.getPrimitiveType());
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getPrimitiveType()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetPrimitiveType(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setPrimitiveType(typeDefinition.getPrimitiveType());
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setPrimitiveType(PrimitiveType value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetName(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					assertNotNull(getAssertionFailureMessage("getName()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						typeDefinition.getName());
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getName()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetJdbcEnumType(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getJdbcEnumType();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getJdbcEnumType()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetJdbcEnumType(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setJdbcEnumType((1));
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setJdbcEnumType(int value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetCharacterSet(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					assertNotNull(getAssertionFailureMessage("getCharacterSet()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						typeDefinition.getCharacterSet());
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getCharacterSet()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetEncodingScheme(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					assertNotNull(getAssertionFailureMessage("getEncodingScheme()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						typeDefinition.getEncodingScheme());
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getEncodingScheme()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetCharacterSetSuffix(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					assertNotNull(getAssertionFailureMessage("getCharacterSetSuffix()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						typeDefinition.getCharacterSetSuffix());
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("getCharacterSetSuffix()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetCharacterSetSuffix(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setCharacterSetSuffix(typeDefinition.getCharacterSetSuffix());
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setCharacterSetSuffix(String value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetEncodingSchemeSuffix(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					assertNotNull(getAssertionFailureMessage("getEncodingSchemeSuffix()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						typeDefinition.getEncodingSchemeSuffix());
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getEncodingSchemeSuffix()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetEncodingSchemeSuffix(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setEncodingSchemeSuffix(typeDefinition.getEncodingSchemeSuffix());
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("setEncodingSchemeSuffix(String value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetJavaClassName(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getJavaClassName();
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getJavaClassName()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetJavaClassName(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setJavaClassName("java.lang.String");
				}
		
			}catch(Exception e){
				fail(getExceptionMessage("setJavaClassName(String value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDefaultLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getDefaultLength();
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDefaultLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetDefaultLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setDefaultLength(1);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setDefaultLength(int value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDefaultPrecision(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getDefaultPrecision();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getDefaultPrecision()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetDefaultPrecision(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setDefaultPrecision(1);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setDefaultPrecision(int value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDefaultScale(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getDefaultScale();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getDefaultScale()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetDefaultScale(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setDefaultScale(1);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("setDefaultScale(int value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetCutoffPrecision(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getCutoffPrecision();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getCutoffPrecision()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetCutoffPrecision(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setCutoffPrecision(1);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("setCutoffPrecision(int value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetLengthUnit(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					assertNotNull(getAssertionFailureMessage("getLengthUnit()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						typeDefinition.getLengthUnit());
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getLengthUnit()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetLengthUnit(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setLengthUnit(typeDefinition.getLengthUnit());
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setLengthUnit(LengthUnit value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsOrderingSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isOrderingSupported();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("isOrderingSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetOrderingSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setOrderingSupported(true);
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setOrderingSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsGroupingSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isGroupingSupported();
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("isGroupingSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetGroupingSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setGroupingSupported(true);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("setGroupingSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDisplayName(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getDisplayName();
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDisplayName()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetDisplayName(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setDisplayName("Display Name");
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("setDisplayName(String value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsDisplayNameSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isDisplayNameSupported();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("isDisplayNameSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetDisplayNameSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setDisplayNameSupported(true);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("setDisplayNameSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsLeadingFieldQualifierSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isLeadingFieldQualifierSupported();
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("isLeadingFieldQualifierSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetLeadingFieldQualifierSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setLeadingFieldQualifierSupported(true);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("setLeadingFieldQualifierSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsTrailingFieldQualifierSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.isTrailingFieldQualifierSupported();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("isTrailingFieldQualifierSupported()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetTrailingFieldQualifierSupported(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setTrailingFieldQualifierSupported(true);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("setTrailingFieldQualifierSupported(boolean value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetFieldQualifierSeparator(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.getFieldQualifierSeparator();
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("getFieldQualifierSeparator()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSetFieldQualifierSeparator(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					typeDefinition.setFieldQualifierSeparator("Separator");
				}
			
			}catch(Exception e){
				fail(getExceptionMessage("setFieldQualifierSeparator(String value)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	

	private String getAssertionFailureMessage(String methodName, Object productName, Object versionName){
		
		return ("\nPackage: " + PACKAGE_NAME +
				"\nFile: " + FILE_NAME +
				"\nMethod: " + methodName +
			    "\nProduct: " + productName + 
		        "\nVersion: " + versionName +
	            "\nAssertion Failure: The assertion condtion fails in the try block");
	}
	
	private String getExceptionMessage(String methodName, Object productName, Object versionName, Exception e){
		
		return ("\nPackage: " + PACKAGE_NAME +
				"\nFile: " + FILE_NAME +
				"\nMethod: " + methodName + 
				"\nProduct: " + productName + 
			    "\nVersion: " + versionName +
		        "\nException: " + e.toString() +
		        "\nDescription: The exception is thrown in the test case");
	}
	
	public void tearDown(){
		
		definitionCollection = new ArrayList();
		dbInformationCollection = new ArrayList();
		dataTypeCollection = new ArrayList();
		dataType = null;
		product = null;
		productVersion = null;
		typeDefinition = null;
		type = null;
		strProduct = null;
		strProductVersion = null;
		definition = null;
	}

}
