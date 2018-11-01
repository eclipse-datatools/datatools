/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFactory;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelFactory;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class DB2LUWModelElementFactory implements DataModelElementFactory {

	public EObject create(EClass metaclass) {
		if (metaclass == SQLSchemaPackage.eINSTANCE.getDatabase()) {
			return LUWFactory.eINSTANCE.createLUWDatabase();
		}
		else if (metaclass == SQLSchemaPackage.eINSTANCE.getSchema()) {
			return DB2ModelFactory.eINSTANCE.createDB2Schema();
		}
		else if (metaclass == SQLTablesPackage.eINSTANCE.getPersistentTable()) {
			return LUWFactory.eINSTANCE.createLUWTable();
		}
		else if (metaclass == SQLTablesPackage.eINSTANCE.getViewTable()) {
			return LUWFactory.eINSTANCE.createLUWView();
		}
		else if (metaclass == SQLTablesPackage.eINSTANCE.getColumn()) {
			return LUWFactory.eINSTANCE.createLUWColumn();
		}
		else if (metaclass == SQLTablesPackage.eINSTANCE.getTrigger()) {
			return DB2ModelFactory.eINSTANCE.createDB2Trigger();
		}
		else if (metaclass == SQLConstraintsPackage.eINSTANCE.getIndex()) {
			return LUWFactory.eINSTANCE.createLUWIndex();
		}
		else if (metaclass == SQLRoutinesPackage.eINSTANCE.getProcedure()) {
			return DB2ModelFactory.eINSTANCE.createDB2Procedure();
		}
		else if (metaclass == SQLRoutinesPackage.eINSTANCE.getUserDefinedFunction()) {
			return DB2ModelFactory.eINSTANCE.createDB2UserDefinedFunction();
		}
		else if (metaclass == SQLRoutinesPackage.eINSTANCE.getMethod()) {
			return DB2ModelFactory.eINSTANCE.createDB2Method();
		}
		else if (metaclass == SQLRoutinesPackage.eINSTANCE.getSource()) {
			return DB2ModelFactory.eINSTANCE.createDB2Source();
		}
		else if (metaclass == DB2ModelPackage.eINSTANCE.getDB2Alias()) {
			return DB2ModelFactory.eINSTANCE.createDB2Alias();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWMaterializedQueryTable()) {
			return LUWFactory.eINSTANCE.createLUWMaterializedQueryTable();
		}
		else if (metaclass == SQLSchemaPackage.eINSTANCE.getIdentitySpecifier()) {
			return DB2ModelFactory.eINSTANCE.createDB2IdentitySpecifier();
		}
//		else if (metaclass == LUWPackage.eINSTANCE.getLUWRelationalNicknameDefault()) {
//			return LUWFactory.eINSTANCE.createLUWRelationalNicknameDefault();
//		}
//		else if (metaclass == LUWPackage.eINSTANCE.getLUWRelationalServerDefault()) {
//			return LUWFactory.eINSTANCE.createLUWRelationalServerDefault();
//		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWPartitionGroup()) {
			return LUWFactory.eINSTANCE.createLUWPartitionGroup();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWTableSpace()) {
			return LUWFactory.eINSTANCE.createLUWTableSpace();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWDatabaseContainer()) {
			return LUWFactory.eINSTANCE.createLUWDatabaseContainer();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWPartitionKey()) {
			return LUWFactory.eINSTANCE.createLUWPartitionKey();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWDatabasePartition()) {
			return LUWFactory.eINSTANCE.createLUWDatabasePartition();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWBufferPool()) {
			return LUWFactory.eINSTANCE.createLUWBufferPool();
		}		
		else if (metaclass == SQLDataTypesPackage.eINSTANCE.getAttributeDefinition()) {
			return LUWFactory.eINSTANCE.createLUWAttributeDefinition();
		}
		else if (metaclass == DB2ModelPackage.eINSTANCE.getDB2Package()) {
			return LUWFactory.eINSTANCE.createLUWDatabasePackage();
		}
		else if (metaclass == DB2ModelPackage.eINSTANCE.getDB2PackageStatement()) {
			return DB2ModelFactory.eINSTANCE.createDB2PackageStatement();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWGlobalVariable()) {
			return LUWFactory.eINSTANCE.createLUWGlobalVariable();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWModule()) {
			return LUWFactory.eINSTANCE.createLUWModule();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWModuleCondition()) {
			return LUWFactory.eINSTANCE.createLUWModuleCondition();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWModuleGlobalVariable()) {
			return LUWFactory.eINSTANCE.createLUWModuleGlobalVariable();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWModuleFunction()) {
			return LUWFactory.eINSTANCE.createLUWModuleFunction();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWModuleProcedure()) {
			return LUWFactory.eINSTANCE.createLUWModuleProcedure();
		}
		else if (metaclass == SQLDataTypesPackage.eINSTANCE.getArrayDataType()){
			return LUWFactory.eINSTANCE.createLUWArrayDataType();
		}
		else if (metaclass == SQLDataTypesPackage.eINSTANCE.getRowDataType()){
			return LUWFactory.eINSTANCE.createLUWRowDataType();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWWrapper()) {
			return LUWFactory.eINSTANCE.createLUWGenericWrapper();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWServer()) {
			return LUWFactory.eINSTANCE.createLUWGenericServer();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWUserMapping()) {
			return LUWFactory.eINSTANCE.createLUWGenericUserMapping();
		}
		else if (metaclass == LUWPackage.eINSTANCE.getLUWNickname()) {
			return LUWFactory.eINSTANCE.createLUWGenericNickname();
		}		
		else if (metaclass == LUWPackage.eINSTANCE.getFederatedProcedure()) {
			return LUWFactory.eINSTANCE.createFederatedProcedure();
		}
		else {
			return metaclass.getEPackage().getEFactoryInstance().create(metaclass);
		}
	}
}
