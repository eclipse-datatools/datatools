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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;

public class DatabaseDefinitionGenericTest extends TestCase {
	
	private final String PACKAGE_NAME = "org.eclipse.datatools.enablement.dbdefinition.junittest";
	private final String FILE_NAME = "DatabaseDefinition.java";
	private DatabaseDefinition definition = null;
	private Iterator product = null;
	private Iterator productVersion = null;
	private ArrayList definitionCollection = new ArrayList();
	private ArrayList dbInformationCollection = new ArrayList();
	private String strProduct = null;
	private String strProductVersion = null;
	private String strException = null;
	private Iterator dataType;
	private PredefinedDataTypeDefinition typeDefinition = null;
	private DataType type = null;
	private PredefinedDataType predefDataType = null;
	
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
	
	public void testGetProduct(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{	
				assertNotNull(getAssertionFailureMessage("getProduct()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)), 
					((DatabaseDefinition)definitionCollection.get(i)).getProduct());
				
			}catch(Exception e){
				fail(getExceptionMessage("getProduct()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetProductDisplayString(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{	
			try{
				assertNotNull(getAssertionFailureMessage("getProductDisplayString()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getProductDisplayString());
		
			}catch(Exception e){
				fail(getExceptionMessage("getProductDisplayString()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetVersion(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{	
				assertNotNull(getAssertionFailureMessage("getVersion()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getVersion());
				
			}catch(Exception e){
				fail(getExceptionMessage("getVersion()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}

	public void testGetVersionDisplayString()throws IOException{
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getVersionDisplayString()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getVersionDisplayString());
				
			}catch(Exception e){
				fail(getExceptionMessage("getVersionDisplayString()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDescription(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getDescription();
			
			}catch(Exception e){
				fail(getExceptionMessage("getDescription()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDataModelElementFactory(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{	
			try{
				assertNotNull(getAssertionFailureMessage("getDataModelElementFactory()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getDataModelElementFactory());
			
			}catch(Exception e){
				fail(getExceptionMessage("getDataModelElementFactory()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetPredefinedDataTypes(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{	
			try{
				assertNotNull(getAssertionFailureMessage("getPredefinedDataTypes()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes());
	
			}catch(Exception e){
				fail(getExceptionMessage("getPredefinedDataTypes()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetSequenceSupportedPredefinedDataTypes(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getSequenceSupportedPredefinedDataTypes()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)), 
					((DatabaseDefinition)definitionCollection.get(i)).getSequenceSupportedPredefinedDataTypes());
				
			}catch(Exception e){
				fail(getExceptionMessage("getSequenceSupportedPredefinedDataTypes()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetIdentityColumnSupportedPredefinedDataTypes(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{	
			try{
				assertNotNull(getAssertionFailureMessage("getIdentityColumnSupportedPredefinedDataTypes()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getIdentityColumnSupportedPredefinedDataTypes());
		
			}catch(Exception e){
				fail(getExceptionMessage("getIdentityColumnSupportedPredefinedDataTypes()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsSchema(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsSchema();
			
			}catch(Exception e){
				fail(getExceptionMessage("supportsSchema()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsIdentityColumns(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsIdentityColumns();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsIdentityColumns()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsComputedColumns(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsComputedColumns();
			
			}catch(Exception e){
				fail(getExceptionMessage("supportsComputedColumns()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsSequence(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsSequence();
			
			}catch(Exception e){
				fail(getExceptionMessage("supportsSequence()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsMQT(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsMQT();
					
			}catch(Exception e){
				fail(getExceptionMessage("supportsMQT()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsMQTIndex(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsMQTIndex();

			}catch(Exception e){
				fail(getExceptionMessage("supportsMQTIndex()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsDeferrableConstraints(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsDeferrableConstraints();
		
			}catch(Exception e){
				fail(getExceptionMessage("supportsDeferrableConstraints()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsInformationalConstraints(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsInformationalConstraints();
		
			}catch(Exception e){
				fail(getExceptionMessage("supportsInformationalConstraints()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsClusteredIndexes(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsClusteredIndexes();
		
			}catch(Exception e){
				fail(getExceptionMessage("supportsClusteredIndexes()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsUniqueKeyNullable(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).isUniqueKeyNullable();
				
			}catch(Exception e){
				fail(getExceptionMessage("isUniqueKeyNullable()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetParentDeleteDRIRules(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getParentDeleteDRIRules()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getParentDeleteDRIRules());
			
			}catch(Exception e){
				fail(getExceptionMessage("getParentDeleteDRIRules()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetParentUpdateDRIRules(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getParentUpdateDRIRules()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getParentUpdateDRIRules());
					
			}catch(Exception e){
				fail(getExceptionMessage("getParentUpdateDRIRules()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsConstraints(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsConstraints();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsConstraints()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testQueryMaxCommentLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).queryMaxCommentLength();
					
			}catch(Exception e){
				fail(getExceptionMessage("queryMaxCommentLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testQueryMaxIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).queryMaxIdentifierLength();
			
			}catch(Exception e){
				fail(getExceptionMessage("queryMaxIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testQueryMaxCheckExpression(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).queryMaxCheckExpression();
				
			}catch(Exception e){
				fail(getExceptionMessage("queryMaxCheckExpression()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testQueryTriggerMaxReferencePartLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).queryTriggerMaxReferencePartLength();
				
			}catch(Exception e){
				fail(getExceptionMessage("queryTriggerMaxReferencePartLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testQueryTriggerMaxActionBodyLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).queryTriggerMaxActionBodyLength();
							
			}catch(Exception e){
				fail(getExceptionMessage("queryTriggerMaxActionBodyLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testQueryStoredProcedureMaxActionBodyLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).queryStoredProcedureMaxActionBodyLength();
						
			}catch(Exception e){
				fail(getExceptionMessage("queryStoredProcedureMaxActionBodyLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsStoredProcedureNullInputAction(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsStoredProcedureNullInputAction();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsStoredProcedureNullInputAction()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsNicknames(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsNicknames();
					
			}catch(Exception e){
				fail(getExceptionMessage("supportsNicknames()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsNicknameConstraints(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsNicknameConstraints();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsNicknameConstraints()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsNicknameIndex(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsNicknameIndex();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsNicknameIndex()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsQuotedDML(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsQuotedDML();
					
			}catch(Exception e){
				fail(getExceptionMessage("supportsQuotedDML()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsQuotedDDL(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsQuotedDDL();
					
			}catch(Exception e){
				fail(getExceptionMessage("supportsQuotedDDL()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsAlias(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsAlias();
					
			}catch(Exception e){
				fail(getExceptionMessage("supportsAlias()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsSynonym(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsSynonym();
			
			}catch(Exception e){
				fail(getExceptionMessage("supportsSynonym()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsTriggers(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsTriggers();
					
			}catch(Exception e){
				fail(getExceptionMessage("supportsTriggers()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsTriggerTypes(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try
			{
				((DatabaseDefinition)definitionCollection.get(i)).supportsTriggerTypes();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsTriggerTypes()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsInsteadOfTrigger(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsInsteadOfTrigger();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsInsteadOfTrigger()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsPerColumnUpdateTrigger(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsPerColumnUpdateTrigger();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsPerColumnUpdateTrigger()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsTriggerReferencesClause(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsTriggerReferencesClause();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsTriggerReferencesClause()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsRowTriggerReference(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsRowTriggerReference();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsRowTriggerReference()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsTableTriggerReference(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsTableTriggerReference();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsTableTriggerReference()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportTriggerWhenClause(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportTriggerWhenClause();
			
			}catch(Exception e){
				fail(getExceptionMessage("supportTriggerWhenClause()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsTriggerGranularity(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsTriggerGranularity();
		
			}catch(Exception e){
				fail(getExceptionMessage("supportsTriggerGranularity()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsUserDefinedType(){
			
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsUserDefinedType();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsUserDefinedType()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsSnapshotViews(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsSnapshotViews();
			
			}catch(Exception e){
				fail(getExceptionMessage("supportsSnapshotViews()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsViewTriggers(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsViewTriggers();
			
			}catch(Exception e){
				fail(getExceptionMessage("supportsViewTriggers()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
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
					type = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataType(typeDefinition);
					
					((DatabaseDefinition)definitionCollection.get(i)).isKeyConstraintSupported(type);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("isKeyConstraintSupported(DataType dataType)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetIdentifierQuoteString(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getIdentifierQuoteString()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getIdentifierQuoteString());
				
			}catch(Exception e){
				fail(getExceptionMessage("getIdentifierQuoteString()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetHostVariableMarker(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getHostVariableMarker()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getHostVariableMarker());
				
			}catch(Exception e){
				fail(getExceptionMessage("getHostVariableMarker()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsHostVariableMarker(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsHostVariableMarker();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsHostVariableMarker()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsCastExpression(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsCastExpression();
				
			}catch(Exception e){
				fail(getExceptionMessage("supportsCastExpression()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsDefaultKeywordForInsertValue(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsDefaultKeywordForInsertValue();
			
			}catch(Exception e){
				fail(getExceptionMessage("supportsDefaultKeywordForInsertValue()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsExtendedGrouping(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsExtendedGrouping();
			
			}catch(Exception e){
				fail(getExceptionMessage("supportsExtendedGrouping()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsTableAliasInDelete(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsTableAliasInDelete();
		
			}catch(Exception e){
				fail(getExceptionMessage("supportsTableAliasInDelete()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetProcedureLanguageType(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getProcedureLanguageType()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getProcedureLanguageType());
				
			}catch(Exception e){
				fail(getExceptionMessage("getProcedureLanguageType()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetFunctionLanguageType(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getFunctionLanguageType()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getFunctionLanguageType());
			
			}catch(Exception e){
				fail(getExceptionMessage("getFunctionLanguageType()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsSQLStatement(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsSQLStatement();
			
			}catch(Exception e){
				fail(getExceptionMessage("supportsSQLStatement()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetSQLKeywords(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getSQLKeywords()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getSQLKeywords());
		
			}catch(Exception e){
				fail(getExceptionMessage("getSQLKeywords()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetSQLOperators(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getSQLOperators()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getSQLOperators());
			
			}catch(Exception e){
				fail(getExceptionMessage("getSQLOperators()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsSQLKeyword(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).isSQLKeyword("AND");
				
			}catch(Exception e){
				fail(getExceptionMessage("isSQLKeyword(String word)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testIsSQLOperator(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).isSQLOperator("+");
				
			}catch(Exception e){
				fail(getExceptionMessage("isSQLOperator(String word)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetSQLTerminationCharacter(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getSQLTerminationCharacter()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getSQLTerminationCharacter());
					
			}catch(Exception e){
				fail(getExceptionMessage("getSQLTerminationCharacter()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength();
					
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetMaximumIdentifierLengthBySQLObject(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					type = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataType(typeDefinition);
					
					((DatabaseDefinition)definitionCollection.get(i)).getMaximumIdentifierLength(type);
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getMaximumIdentifierLength(SQLObject sqlObject)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDatabaseMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getDatabaseMaximumIdentifierLength();
				
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetSchemaMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getSchemaMaximumIdentifierLength();
				
			}catch(Exception e){
				fail(getExceptionMessage("getSchemaMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetTableMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getTableMaximumIdentifierLength();
			
			}catch(Exception e){
				fail(getExceptionMessage("getTableMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetViewMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getViewMaximumIdentifierLength();
			
			}catch(Exception e){
				fail(getExceptionMessage("getViewMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetColumnMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getColumnMaximumIdentifierLength();
				
			}catch(Exception e){
				fail(getExceptionMessage("getColumnMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetTriggerMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getTriggerMaximumIdentifierLength();
				
			}catch(Exception e){
				fail(getExceptionMessage("getTriggerMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetPrimarykeyIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getPrimarykeyIdentifierLength();
				
			}catch(Exception e){
				fail(getExceptionMessage("getPrimarykeyIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetForeignKeyMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getForeignKeyMaximumIdentifierLength();
				
			}catch(Exception e){
				fail(getExceptionMessage("getForeignKeyMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetCheckConstraintMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getCheckConstraintMaximumIdentifierLength();
				
			}catch(Exception e){
				fail(getExceptionMessage("getCheckConstraintMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetNicknameMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getNicknameMaximumIdentifierLength();
			
			}catch(Exception e){
				fail(getExceptionMessage("getNicknameMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetUserDefinedTypeMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getUserDefinedTypeMaximumIdentifierLength();
			
			}catch(Exception e){
				fail(getExceptionMessage("getUserDefinedTypeMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetTablespaceMaximumIdentifierLength(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getTablespaceMaximumIdentifierLength();
				
			}catch(Exception e){
				fail(getExceptionMessage("getTablespaceMaximumIdentifierLength()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetPredefinedDataTypeDefinitionsByJDBCEnumType(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getPredefinedDataTypeDefinitionsByJDBCEnumType(int jdbcEnumType)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypeDefinitionsByJDBCEnumType(1));
		
			}catch(Exception e){
				fail(getExceptionMessage("getPredefinedDataTypeDefinitionsByJDBCEnumType(int jdbcEnumType)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetPredefinedDataTypesByJDBCEnumType(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getPredefinedDataTypesByJDBCEnumType(int jdbcEnumType)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypesByJDBCEnumType(1));
			
			}catch(Exception e){
				fail(getExceptionMessage("getPredefinedDataTypesByJDBCEnumType(int jdbcEnumType)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetPredefinedDataType(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getPredefinedDataType(String dataTypeName)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataType("numeric"));
			
			}catch(Exception e){
				fail(getExceptionMessage("getPredefinedDataType(String dataTypeName)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetPredefinedDataTypeDefinition(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getPredefinedDataTypeDefinition(String dataTypeName)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypeDefinition("numeric"));
				
			}catch(Exception e){
				fail(getExceptionMessage("getPredefinedDataTypeDefinition(String dataTypeName)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}

	public void testGetPredefinedDataTypeByPredefinedDataTypeDefinition(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				dataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypes();
				
				while(dataType.hasNext())
				{
					typeDefinition = (PredefinedDataTypeDefinition) dataType.next();
					
					assertNotNull(getAssertionFailureMessage("getPredefinedDataType(PredefinedDataTypeDefinition predefinedDataTypeDefinition)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataType(typeDefinition));
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getPredefinedDataType(PredefinedDataTypeDefinition predefinedDataTypeDefinition)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetPredefinedDataTypeDefinitionByNameAndJDBCEnumType(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypeDefinitionByNameAndJDBCEnumType("int", 1);
				
			}catch(Exception e){
				fail(getExceptionMessage("getPredefinedDataTypeDefinitionByNameAndJDBCEnumType(String dataTypeName, int jdbcEnumType)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}

	public void testGetPredefinedDataTypeByNameAndJDBCEnumType(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypeByNameAndJDBCEnumType("int", 1);
				
			}catch(Exception e){
				fail(getExceptionMessage("getPredefinedDataTypeByNameAndJDBCEnumType(String dataTypeName, int jdbcEnumType)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetPredefinedDataTypeFormattedName(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				predefDataType = ((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataType("numeric");
				
				assertNotNull(getAssertionFailureMessage("getPredefinedDataTypeFormattedName(PredefinedDataType predefinedDataType)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getPredefinedDataTypeFormattedName(predefDataType));
		
			}catch(Exception e){
				fail(getExceptionMessage("getPredefinedDataTypeFormattedName(PredefinedDataType predefinedDataType)", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDdlParser(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).getDdlParser();
				
			}catch(Exception e){
				fail(getExceptionMessage("getDdlParser()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDatabaseCatalogProvider(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getDatabaseCatalogProvider()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getDatabaseCatalogProvider());
			
			}catch(Exception e){
				fail(getExceptionMessage("getDatabaseCatalogProvider()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDDLGenerator(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				/**
				 * MySqlDdlGenerator.java in org.eclipse.datatools.enablement.mysql
				 * is disabled; therefore, if the product is MySql, an exception will be thrown.
				 * Remove the line: String product = ((DatabaseDefinition)definitionCollection.get(i)).getProduct();
				 * and the if statement after MySqlDdlGenerator code is enabled.
				 */ 
				
				String product = ((DatabaseDefinition)definitionCollection.get(i)).getProduct();
				
				if(!product.equals("MySql"))
				{
					assertNotNull(getAssertionFailureMessage("getDDLGenerator()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
						((DatabaseDefinition)definitionCollection.get(i)).getDDLGenerator());
				}
				
			}catch(Exception e){
				fail(getExceptionMessage("getDDLGenerator()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testGetDeltaDDLGenerator(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				assertNotNull(getAssertionFailureMessage("getDeltaDDLGenerator()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1)),
					((DatabaseDefinition)definitionCollection.get(i)).getDeltaDDLGenerator());
			
			}catch(Exception e){
				fail(getExceptionMessage("getDeltaDDLGenerator()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
			}
		}
	}
	
	public void testSupportsXML(){
		
		for(int i = 0, j = 0; i < definitionCollection.size(); i++, j += 2)
		{
			try{
				((DatabaseDefinition)definitionCollection.get(i)).supportsXML();
			
			}catch(Exception e){
				fail(getExceptionMessage("supportsXML()", dbInformationCollection.get(j), dbInformationCollection.get(j + 1), e));
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
		
		definition = null;
		product = null;
		productVersion = null;
		definitionCollection = new ArrayList();
		dbInformationCollection = new ArrayList();
		strProduct = null;
		strProductVersion = null;
		strException = null;
		dataType = null;
		typeDefinition = null;
		type = null;
		predefDataType = null;
	}
}
