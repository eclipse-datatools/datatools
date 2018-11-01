/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Brian Fitzpatrick - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sqlite.dbdefcreation;

/*
 * Example file to create the db definition file for SQLite 
 */

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.modelbase.dbdefinition.CheckOption;
import org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionFactory;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType;
import org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * @author brianf
 */
public class ExampleForVendorPrimitiveWrite {

	public static void main(String[] arg) {
			URI uri = URI.createFileURI("C:\\temp\\SQLITE_3.5.9.xmi");
			Resource rf = new XMIResourceImpl(uri);
			
			// Database vendor definitions
			DatabaseVendorDefinition databaseVendorDefinition = DatabaseDefinitionFactory.eINSTANCE.createDatabaseVendorDefinition();
			databaseVendorDefinition.setVendor("SQLITE");
			databaseVendorDefinition.setVersion("3.5.9");
			databaseVendorDefinition.setMaximumIdentifierLength(64);
			databaseVendorDefinition.setMaximumCommentLength(64);
			databaseVendorDefinition.setMQTSupported(true);
			databaseVendorDefinition.setAliasSupported(true);
			databaseVendorDefinition.setSchemaSupported(false);
			
			ColumnDefinition columnDefinition = DatabaseDefinitionFactory.eINSTANCE.createColumnDefinition();
			columnDefinition.setIdentitySupported(true);
			columnDefinition.setComputedSupported(true);
			columnDefinition.setIdentityStartValueSupported(true);
			columnDefinition.setIdentityIncrementSupported(true);
			columnDefinition.setIdentityMaximumSupported(true);
			columnDefinition.setIdentityMinimumSupported(true);
			columnDefinition.setIdentityCycleSupported(true);
			databaseVendorDefinition.setColumnDefinition(columnDefinition);
			
			ConstraintDefinition constraintDefinition = DatabaseDefinitionFactory.eINSTANCE.createConstraintDefinition();
			constraintDefinition.setClusteredPrimaryKeySupported(true);
			constraintDefinition.setClusteredUniqueConstraintSupported(true);
			constraintDefinition.getParentDeleteDRIRuleType().add(ParentDeleteDRIRuleType.RESTRICT_LITERAL);
			constraintDefinition.getParentDeleteDRIRuleType().add(ParentDeleteDRIRuleType.CASCADE_LITERAL);
			constraintDefinition.getParentDeleteDRIRuleType().add(ParentDeleteDRIRuleType.SET_NULL_LITERAL);
			constraintDefinition.getParentDeleteDRIRuleType().add(ParentDeleteDRIRuleType.NO_ACTION_LITERAL);
			constraintDefinition.getParentUpdateDRIRuleType().add(ParentUpdateDRIRuleType.RESTRICT_LITERAL);
			constraintDefinition.getParentUpdateDRIRuleType().add(ParentUpdateDRIRuleType.NO_ACTION_LITERAL);
			constraintDefinition.getCheckOption().add(CheckOption.NONE_LITERAL);
			constraintDefinition.getCheckOption().add(CheckOption.LOCAL_LITERAL);
			constraintDefinition.getCheckOption().add(CheckOption.CASCADE_LITERAL);
			databaseVendorDefinition.setConstraintDefinition(constraintDefinition);
			
			
			// Primitive type definitions
			
			// INTEGER
			PredefinedDataTypeDefinition integerDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			integerDataTypeDefinition.setPrimitiveType(PrimitiveType.INTEGER_LITERAL);
			integerDataTypeDefinition.getName().add("INTEGER");
			integerDataTypeDefinition.setKeyConstraintSupported(true);
			integerDataTypeDefinition.setIdentitySupported(true);
			integerDataTypeDefinition.getDefaultValueTypes().add("NULL");
			integerDataTypeDefinition.setJdbcEnumType(4);
			integerDataTypeDefinition.setJavaClassName("int");
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(integerDataTypeDefinition);
			((XMIResource)rf).setID(integerDataTypeDefinition, PrimitiveType.INTEGER_LITERAL+"_1");

			// TEXT
			PredefinedDataTypeDefinition characterDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			characterDataTypeDefinition.setPrimitiveType(PrimitiveType.CHARACTER_LITERAL);
			characterDataTypeDefinition.getName().add("TEXT");
			characterDataTypeDefinition.setMaximumLength(254);
			characterDataTypeDefinition.setKeyConstraintSupported(true);
			characterDataTypeDefinition.getDefaultValueTypes().add("CURRENT_USER");
			characterDataTypeDefinition.getDefaultValueTypes().add("NULL");
			characterDataTypeDefinition.setLengthSupported(true);
			characterDataTypeDefinition.setJdbcEnumType(1);
			characterDataTypeDefinition.setJavaClassName("java.lang.String");
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(characterDataTypeDefinition);
			((XMIResource)rf).setID(characterDataTypeDefinition, PrimitiveType.CHARACTER_LITERAL+"_1");
							
			// REAL
			PredefinedDataTypeDefinition decimalDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			decimalDataTypeDefinition.setPrimitiveType(PrimitiveType.DECIMAL_LITERAL);
			decimalDataTypeDefinition.getName().add("REAL");
			decimalDataTypeDefinition.setPrecisionSupported(true);
			decimalDataTypeDefinition.setScaleSupported(true);
			decimalDataTypeDefinition.setKeyConstraintSupported(true);
			decimalDataTypeDefinition.setIdentitySupported(true);
			decimalDataTypeDefinition.getDefaultValueTypes().add("NULL");
			decimalDataTypeDefinition.setJdbcEnumType(3);
			decimalDataTypeDefinition.setJavaClassName("java.math.BigDecimal");
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(decimalDataTypeDefinition);
			((XMIResource)rf).setID(decimalDataTypeDefinition, PrimitiveType.DECIMAL_LITERAL+"_1");
				
			// BLOB
			PredefinedDataTypeDefinition numericDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			numericDataTypeDefinition.setPrimitiveType(PrimitiveType.BINARY_LARGE_OBJECT_LITERAL);
			numericDataTypeDefinition.getName().add("BLOB");
			numericDataTypeDefinition.setKeyConstraintSupported(true);
			numericDataTypeDefinition.setPrecisionSupported(true);
			numericDataTypeDefinition.setScaleSupported(true);
			numericDataTypeDefinition.getDefaultValueTypes().add("NULL");
			numericDataTypeDefinition.setJdbcEnumType(2);
			numericDataTypeDefinition.setJavaClassName("java.lang.Object");
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(numericDataTypeDefinition);
			((XMIResource)rf).setID(numericDataTypeDefinition, PrimitiveType.BINARY_LARGE_OBJECT_LITERAL+"_1");
					
			if (rf != null) {
				EList resourceContents = rf.getContents();
				resourceContents.add(databaseVendorDefinition);
				try {
					Map options = new HashMap();
					options.put(XMIResource.OPTION_DECLARE_XML, Boolean.TRUE);
					rf.save(options);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
}

