/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Brian Fitzpatrick - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.hsqldb.internal;

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
 *
 */
public class HSQLDBDBDefinitionWrite {

	/**
	 * @param arg
	 */
	public static void main(String[] arg) {
			URI uri = URI.createFileURI("C:\\temp\\HSQLDB_1.8.xmi"); //$NON-NLS-1$
			Resource rf = new XMIResourceImpl(uri);
			
			// Database vendor definitions
			DatabaseVendorDefinition databaseVendorDefinition = DatabaseDefinitionFactory.eINSTANCE.createDatabaseVendorDefinition();
			databaseVendorDefinition.setVendor("HSQLDB"); //$NON-NLS-1$
			databaseVendorDefinition.setVersion("1.8"); //$NON-NLS-1$
			databaseVendorDefinition.setViewTriggerSupported(false);
			databaseVendorDefinition.setStoredProcedureSupported(false);
			databaseVendorDefinition.setMaximumIdentifierLength(64);
			databaseVendorDefinition.setMaximumCommentLength(64);
			databaseVendorDefinition.setSequenceSupported(true);
			databaseVendorDefinition.setMQTSupported(false);
			databaseVendorDefinition.setAliasSupported(true);
			
			ColumnDefinition columnDefinition = DatabaseDefinitionFactory.eINSTANCE.createColumnDefinition();
			columnDefinition.setIdentitySupported(true);
			columnDefinition.setComputedSupported(false);
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
			integerDataTypeDefinition.getName().add("INTEGER"); //$NON-NLS-1$
			integerDataTypeDefinition.getName().add("INT"); //$NON-NLS-1$
			integerDataTypeDefinition.setKeyConstraintSupported(true);
			integerDataTypeDefinition.setIdentitySupported(true);
			integerDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			integerDataTypeDefinition.setJdbcEnumType(java.sql.Types.INTEGER);
			integerDataTypeDefinition.setJavaClassName("int"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(integerDataTypeDefinition);
			((XMIResource)rf).setID(integerDataTypeDefinition, PrimitiveType.INTEGER_LITERAL+"_1"); //$NON-NLS-1$

			// DOUBLE
			PredefinedDataTypeDefinition doublePrecisionDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			doublePrecisionDataTypeDefinition.setPrimitiveType(PrimitiveType.DOUBLE_PRECISION_LITERAL);
			doublePrecisionDataTypeDefinition.getName().add("DOUBLE"); //$NON-NLS-1$
			doublePrecisionDataTypeDefinition.getName().add("DOUBLE PRECISION"); //$NON-NLS-1$
			doublePrecisionDataTypeDefinition.getName().add("FLOAT"); //$NON-NLS-1$
			doublePrecisionDataTypeDefinition.setKeyConstraintSupported(true);
			doublePrecisionDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			doublePrecisionDataTypeDefinition.setJdbcEnumType(java.sql.Types.DOUBLE);
			doublePrecisionDataTypeDefinition.setJavaClassName("double"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(doublePrecisionDataTypeDefinition);
			((XMIResource)rf).setID(doublePrecisionDataTypeDefinition, PrimitiveType.DOUBLE_PRECISION_LITERAL+"_1"); //$NON-NLS-1$
					
			// VARCHAR
			PredefinedDataTypeDefinition varcharDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			varcharDataTypeDefinition.setPrimitiveType(PrimitiveType.CHARACTER_LITERAL);
			varcharDataTypeDefinition.getName().add("VARCHAR"); //$NON-NLS-1$
			varcharDataTypeDefinition.setMaximumLength(Integer.MAX_VALUE);
			varcharDataTypeDefinition.setKeyConstraintSupported(true);
			varcharDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			varcharDataTypeDefinition.setLengthSupported(true);
			varcharDataTypeDefinition.setJdbcEnumType(java.sql.Types.VARCHAR);
			varcharDataTypeDefinition.setJavaClassName("java.lang.String"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(varcharDataTypeDefinition);
			((XMIResource)rf).setID(varcharDataTypeDefinition, PrimitiveType.CHARACTER_LITERAL+"_2"); //$NON-NLS-1$

			// VARCHAR IGNORECASE
			PredefinedDataTypeDefinition varcharIgnoreCaseDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			varcharIgnoreCaseDataTypeDefinition.setPrimitiveType(PrimitiveType.CHARACTER_LITERAL);
			varcharIgnoreCaseDataTypeDefinition.getName().add("VARCHAR_IGNORECASE"); //$NON-NLS-1$
			varcharIgnoreCaseDataTypeDefinition.setMaximumLength(Integer.MAX_VALUE);
			varcharIgnoreCaseDataTypeDefinition.setKeyConstraintSupported(true);
			varcharIgnoreCaseDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			varcharIgnoreCaseDataTypeDefinition.setLengthSupported(true);
			varcharIgnoreCaseDataTypeDefinition.setJdbcEnumType(java.sql.Types.VARCHAR);
			varcharIgnoreCaseDataTypeDefinition.setJavaClassName("java.lang.String"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(varcharIgnoreCaseDataTypeDefinition);
			((XMIResource)rf).setID(varcharIgnoreCaseDataTypeDefinition, PrimitiveType.CHARACTER_LITERAL+"_3"); //$NON-NLS-1$

			// CHARACTER
			PredefinedDataTypeDefinition characterDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			characterDataTypeDefinition.setPrimitiveType(PrimitiveType.CHARACTER_LITERAL);
			characterDataTypeDefinition.getName().add("CHAR"); //$NON-NLS-1$
			characterDataTypeDefinition.getName().add("CHARACTER"); //$NON-NLS-1$
			characterDataTypeDefinition.setMaximumLength(Integer.MAX_VALUE);
			characterDataTypeDefinition.setKeyConstraintSupported(true);
			characterDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			characterDataTypeDefinition.setLengthSupported(true);
			characterDataTypeDefinition.setJdbcEnumType(java.sql.Types.CHAR);
			characterDataTypeDefinition.setJavaClassName("java.lang.String"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(characterDataTypeDefinition);
			((XMIResource)rf).setID(characterDataTypeDefinition, PrimitiveType.CHARACTER_LITERAL+"_1"); //$NON-NLS-1$
							
			// LONGVARCHAR
			PredefinedDataTypeDefinition longvarcharDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			longvarcharDataTypeDefinition.setPrimitiveType(PrimitiveType.CHARACTER_LITERAL);
			longvarcharDataTypeDefinition.getName().add("LONGVARCHAR"); //$NON-NLS-1$
			longvarcharDataTypeDefinition.setMaximumLength(Integer.MAX_VALUE);
			longvarcharDataTypeDefinition.setKeyConstraintSupported(true);
			longvarcharDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			longvarcharDataTypeDefinition.setLengthSupported(true);
			longvarcharDataTypeDefinition.setJdbcEnumType(java.sql.Types.LONGVARCHAR);
			longvarcharDataTypeDefinition.setJavaClassName("java.lang.String"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(longvarcharDataTypeDefinition);
			((XMIResource)rf).setID(longvarcharDataTypeDefinition, PrimitiveType.CHARACTER_LITERAL+"_4"); //$NON-NLS-1$

			// DATE
			PredefinedDataTypeDefinition dateDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			dateDataTypeDefinition.setPrimitiveType(PrimitiveType.DATE_LITERAL);
			dateDataTypeDefinition.getName().add("DATE"); //$NON-NLS-1$
			dateDataTypeDefinition.setKeyConstraintSupported(true);
			dateDataTypeDefinition.setIdentitySupported(true);
			dateDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			dateDataTypeDefinition.setJdbcEnumType(java.sql.Types.DATE);
			dateDataTypeDefinition.setJavaClassName("java.sql.Date"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(dateDataTypeDefinition);
			((XMIResource)rf).setID(dateDataTypeDefinition, PrimitiveType.DATE_LITERAL+"_1"); //$NON-NLS-1$

			// TIME
			PredefinedDataTypeDefinition timeDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			timeDataTypeDefinition.setPrimitiveType(PrimitiveType.TIME_LITERAL);
			timeDataTypeDefinition.getName().add("TIME"); //$NON-NLS-1$
			timeDataTypeDefinition.setKeyConstraintSupported(true);
			timeDataTypeDefinition.setIdentitySupported(true);
			timeDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			timeDataTypeDefinition.setJdbcEnumType(java.sql.Types.TIME);
			timeDataTypeDefinition.setJavaClassName("java.sql.Time"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(timeDataTypeDefinition);
			((XMIResource)rf).setID(timeDataTypeDefinition, PrimitiveType.TIME_LITERAL+"_1"); //$NON-NLS-1$

			// TIMESTAMP
			PredefinedDataTypeDefinition timestampDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			timestampDataTypeDefinition.setPrimitiveType(PrimitiveType.TIMESTAMP_LITERAL);
			timestampDataTypeDefinition.getName().add("TIMESTAMP"); //$NON-NLS-1$
			timestampDataTypeDefinition.getName().add("DATETIME"); //$NON-NLS-1$
			timestampDataTypeDefinition.setKeyConstraintSupported(true);
			timestampDataTypeDefinition.setIdentitySupported(true);
			timestampDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			timestampDataTypeDefinition.setJdbcEnumType(java.sql.Types.TIMESTAMP);
			timestampDataTypeDefinition.setJavaClassName("java.sql.Timestamp"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(timestampDataTypeDefinition);
			((XMIResource)rf).setID(timestampDataTypeDefinition, PrimitiveType.TIMESTAMP_LITERAL+"_1"); //$NON-NLS-1$

			// DECIMAL
			PredefinedDataTypeDefinition decimalDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			decimalDataTypeDefinition.setPrimitiveType(PrimitiveType.DECIMAL_LITERAL);
			decimalDataTypeDefinition.getName().add("DECIMAL"); //$NON-NLS-1$
			decimalDataTypeDefinition.setPrecisionSupported(true);
			decimalDataTypeDefinition.setScaleSupported(true);
			decimalDataTypeDefinition.setKeyConstraintSupported(true);
			decimalDataTypeDefinition.setIdentitySupported(true);
			decimalDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			decimalDataTypeDefinition.setJdbcEnumType(java.sql.Types.DECIMAL);
			decimalDataTypeDefinition.setJavaClassName("java.math.BigDecimal"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(decimalDataTypeDefinition);
			((XMIResource)rf).setID(decimalDataTypeDefinition, PrimitiveType.DECIMAL_LITERAL+"_1"); //$NON-NLS-1$
				
			// NUMERIC
			PredefinedDataTypeDefinition numericDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			numericDataTypeDefinition.setPrimitiveType(PrimitiveType.NUMERIC_LITERAL);
			numericDataTypeDefinition.getName().add("NUMERIC"); //$NON-NLS-1$
			numericDataTypeDefinition.setKeyConstraintSupported(true);
			numericDataTypeDefinition.setPrecisionSupported(true);
			numericDataTypeDefinition.setScaleSupported(true);
			numericDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			numericDataTypeDefinition.setJdbcEnumType(java.sql.Types.NUMERIC);
			numericDataTypeDefinition.setJavaClassName("java.math.BigDecimal"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(numericDataTypeDefinition);
			((XMIResource)rf).setID(numericDataTypeDefinition, PrimitiveType.NUMERIC_LITERAL+"_1"); //$NON-NLS-1$
					
			// BOOLEAN/BIT
			PredefinedDataTypeDefinition booleanDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			booleanDataTypeDefinition.setPrimitiveType(PrimitiveType.BOOLEAN_LITERAL);
			booleanDataTypeDefinition.getName().add("BOOLEAN"); //$NON-NLS-1$
			booleanDataTypeDefinition.getName().add("BIT"); //$NON-NLS-1$
			booleanDataTypeDefinition.setKeyConstraintSupported(true);
			booleanDataTypeDefinition.setIdentitySupported(true);
			booleanDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			booleanDataTypeDefinition.setJdbcEnumType(java.sql.Types.BOOLEAN);
			booleanDataTypeDefinition.setJavaClassName("boolean"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(booleanDataTypeDefinition);
			((XMIResource)rf).setID(booleanDataTypeDefinition, PrimitiveType.BOOLEAN_LITERAL+"_1"); //$NON-NLS-1$

			// TINYINT
			PredefinedDataTypeDefinition tinyintDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			tinyintDataTypeDefinition.setPrimitiveType(PrimitiveType.SMALLINT_LITERAL);
			tinyintDataTypeDefinition.getName().add("TINYINT"); //$NON-NLS-1$
			tinyintDataTypeDefinition.setKeyConstraintSupported(true);
			tinyintDataTypeDefinition.setIdentitySupported(true);
			tinyintDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			tinyintDataTypeDefinition.setJdbcEnumType(java.sql.Types.TINYINT);
			tinyintDataTypeDefinition.setJavaClassName("byte"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(tinyintDataTypeDefinition);
			((XMIResource)rf).setID(tinyintDataTypeDefinition, PrimitiveType.SMALLINT_LITERAL+"_2"); //$NON-NLS-1$

			// SMALLINT
			PredefinedDataTypeDefinition smallintDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			smallintDataTypeDefinition.setPrimitiveType(PrimitiveType.SMALLINT_LITERAL);
			smallintDataTypeDefinition.getName().add("SMALLINT"); //$NON-NLS-1$
			smallintDataTypeDefinition.setKeyConstraintSupported(true);
			smallintDataTypeDefinition.setIdentitySupported(true);
			smallintDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			smallintDataTypeDefinition.setJdbcEnumType(java.sql.Types.SMALLINT);
			smallintDataTypeDefinition.setJavaClassName("short"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(smallintDataTypeDefinition);
			((XMIResource)rf).setID(smallintDataTypeDefinition, PrimitiveType.SMALLINT_LITERAL+"_1"); //$NON-NLS-1$

			// BIGINT
			PredefinedDataTypeDefinition bigintDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			bigintDataTypeDefinition.setPrimitiveType(PrimitiveType.BIGINT_LITERAL);
			bigintDataTypeDefinition.getName().add("BIGINT"); //$NON-NLS-1$
			bigintDataTypeDefinition.setKeyConstraintSupported(true);
			bigintDataTypeDefinition.setIdentitySupported(true);
			bigintDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			bigintDataTypeDefinition.setJdbcEnumType(java.sql.Types.BIGINT);
			bigintDataTypeDefinition.setJavaClassName("long"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(bigintDataTypeDefinition);
			((XMIResource)rf).setID(bigintDataTypeDefinition, PrimitiveType.BIGINT_LITERAL+"_1"); //$NON-NLS-1$

			// REAL
			PredefinedDataTypeDefinition realDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			realDataTypeDefinition.setPrimitiveType(PrimitiveType.REAL_LITERAL);
			realDataTypeDefinition.getName().add("REAL"); //$NON-NLS-1$
			realDataTypeDefinition.setKeyConstraintSupported(true);
			realDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			realDataTypeDefinition.setJdbcEnumType(java.sql.Types.REAL);
			realDataTypeDefinition.setJavaClassName("double"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(realDataTypeDefinition);
			((XMIResource)rf).setID(realDataTypeDefinition, PrimitiveType.REAL_LITERAL+"_1"); //$NON-NLS-1$

			// BINARY
			PredefinedDataTypeDefinition binaryDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			binaryDataTypeDefinition.setPrimitiveType(PrimitiveType.BINARY_LITERAL);
			binaryDataTypeDefinition.getName().add("BINARY"); //$NON-NLS-1$
			binaryDataTypeDefinition.setKeyConstraintSupported(true);
			binaryDataTypeDefinition.setMaximumValue(Integer.MAX_VALUE);
			binaryDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			binaryDataTypeDefinition.setJdbcEnumType(java.sql.Types.BINARY);
			binaryDataTypeDefinition.setJavaClassName("byte[]"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(binaryDataTypeDefinition);
			((XMIResource)rf).setID(binaryDataTypeDefinition, PrimitiveType.BINARY_LITERAL+"_1"); //$NON-NLS-1$

			// VARBINARY
			PredefinedDataTypeDefinition varbinaryDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			varbinaryDataTypeDefinition.setPrimitiveType(PrimitiveType.BINARY_VARYING_LITERAL);
			varbinaryDataTypeDefinition.getName().add("VARBINARY"); //$NON-NLS-1$
			varbinaryDataTypeDefinition.setKeyConstraintSupported(true);
			varbinaryDataTypeDefinition.setMaximumValue(Integer.MAX_VALUE);
			varbinaryDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			varbinaryDataTypeDefinition.setJdbcEnumType(java.sql.Types.VARBINARY);
			varbinaryDataTypeDefinition.setJavaClassName("byte[]"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(varbinaryDataTypeDefinition);
			((XMIResource)rf).setID(varbinaryDataTypeDefinition, PrimitiveType.BINARY_VARYING_LITERAL+"_1"); //$NON-NLS-1$

			// LONGVARBINARY
			PredefinedDataTypeDefinition longvarbinaryDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			longvarbinaryDataTypeDefinition.setPrimitiveType(PrimitiveType.BINARY_VARYING_LITERAL);
			longvarbinaryDataTypeDefinition.getName().add("LONGVARBINARY"); //$NON-NLS-1$
			longvarbinaryDataTypeDefinition.setKeyConstraintSupported(true);
			longvarbinaryDataTypeDefinition.setMaximumValue(Integer.MAX_VALUE);
			longvarbinaryDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			longvarbinaryDataTypeDefinition.setJdbcEnumType(java.sql.Types.LONGVARBINARY);
			longvarbinaryDataTypeDefinition.setJavaClassName("byte[]"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(longvarbinaryDataTypeDefinition);
			((XMIResource)rf).setID(longvarbinaryDataTypeDefinition, PrimitiveType.BINARY_VARYING_LITERAL+"_2"); //$NON-NLS-1$

			// OTHER
			PredefinedDataTypeDefinition otherDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			otherDataTypeDefinition.setPrimitiveType(PrimitiveType.BINARY_LARGE_OBJECT_LITERAL);
			otherDataTypeDefinition.getName().add("OTHER"); //$NON-NLS-1$
			otherDataTypeDefinition.getName().add("OBJECT"); //$NON-NLS-1$
			otherDataTypeDefinition.setKeyConstraintSupported(true);
			otherDataTypeDefinition.setMaximumValue(Integer.MAX_VALUE);
			otherDataTypeDefinition.getDefaultValueTypes().add("NULL"); //$NON-NLS-1$
			otherDataTypeDefinition.setJdbcEnumType(java.sql.Types.OTHER);
			otherDataTypeDefinition.setJavaClassName("java.lang.Object"); //$NON-NLS-1$
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(otherDataTypeDefinition);
			((XMIResource)rf).setID(otherDataTypeDefinition, PrimitiveType.BINARY_LARGE_OBJECT_LITERAL+"_1"); //$NON-NLS-1$

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


