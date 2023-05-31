/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.model.util;


import java.util.List;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAdminServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWCursorDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleArrayDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCondition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCursorDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleDistinctType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleFunction;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleGlobalVariable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleProcedure;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleRowDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRowDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteDataSet;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteDataSet;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Column;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Database;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Function;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Index;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Member;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Table;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2UserDefinedFunction;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2View;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.CollectionDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.ConstructedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.ElementType;
import org.eclipse.datatools.modelbase.sql.datatypes.RowDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.DerivedTable;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage
 * @generated
 */
public class LUWSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static LUWPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWSwitch() {
		if (modelPackage == null) {
			modelPackage = LUWPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case LUWPackage.LUW_PARTITION_GROUP: {
				LUWPartitionGroup luwPartitionGroup = (LUWPartitionGroup)theEObject;
				Object result = caseLUWPartitionGroup(luwPartitionGroup);
				if (result == null) result = caseSQLObject(luwPartitionGroup);
				if (result == null) result = caseENamedElement(luwPartitionGroup);
				if (result == null) result = caseEModelElement(luwPartitionGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_TABLE_SPACE: {
				LUWTableSpace luwTableSpace = (LUWTableSpace)theEObject;
				Object result = caseLUWTableSpace(luwTableSpace);
				if (result == null) result = caseSQLObject(luwTableSpace);
				if (result == null) result = caseENamedElement(luwTableSpace);
				if (result == null) result = caseEModelElement(luwTableSpace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_DATABASE_PARTITION: {
				LUWDatabasePartition luwDatabasePartition = (LUWDatabasePartition)theEObject;
				Object result = caseLUWDatabasePartition(luwDatabasePartition);
				if (result == null) result = caseSQLObject(luwDatabasePartition);
				if (result == null) result = caseENamedElement(luwDatabasePartition);
				if (result == null) result = caseEModelElement(luwDatabasePartition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_DATABASE_CONTAINER: {
				LUWDatabaseContainer luwDatabaseContainer = (LUWDatabaseContainer)theEObject;
				Object result = caseLUWDatabaseContainer(luwDatabaseContainer);
				if (result == null) result = caseSQLObject(luwDatabaseContainer);
				if (result == null) result = caseENamedElement(luwDatabaseContainer);
				if (result == null) result = caseEModelElement(luwDatabaseContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_ADMIN_SERVER: {
				LUWAdminServer luwAdminServer = (LUWAdminServer)theEObject;
				Object result = caseLUWAdminServer(luwAdminServer);
				if (result == null) result = caseSQLObject(luwAdminServer);
				if (result == null) result = caseENamedElement(luwAdminServer);
				if (result == null) result = caseEModelElement(luwAdminServer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_BUFFER_POOL: {
				LUWBufferPool luwBufferPool = (LUWBufferPool)theEObject;
				Object result = caseLUWBufferPool(luwBufferPool);
				if (result == null) result = caseSQLObject(luwBufferPool);
				if (result == null) result = caseENamedElement(luwBufferPool);
				if (result == null) result = caseEModelElement(luwBufferPool);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_TABLE: {
				LUWTable luwTable = (LUWTable)theEObject;
				Object result = caseLUWTable(luwTable);
				if (result == null) result = caseDB2Table(luwTable);
				if (result == null) result = caseLUWStorageTable(luwTable);
				if (result == null) result = casePersistentTable(luwTable);
				if (result == null) result = caseBaseTable(luwTable);
				if (result == null) result = caseTable(luwTable);
				if (result == null) result = caseSQLObject(luwTable);
				if (result == null) result = caseENamedElement(luwTable);
				if (result == null) result = caseEModelElement(luwTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_VIEW: {
				LUWView luwView = (LUWView)theEObject;
				Object result = caseLUWView(luwView);
				if (result == null) result = caseDB2View(luwView);
				if (result == null) result = caseViewTable(luwView);
				if (result == null) result = caseDerivedTable(luwView);
				if (result == null) result = caseTable(luwView);
				if (result == null) result = caseSQLObject(luwView);
				if (result == null) result = caseENamedElement(luwView);
				if (result == null) result = caseEModelElement(luwView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_PARTITION_KEY: {
				LUWPartitionKey luwPartitionKey = (LUWPartitionKey)theEObject;
				Object result = caseLUWPartitionKey(luwPartitionKey);
				if (result == null) result = caseSQLObject(luwPartitionKey);
				if (result == null) result = caseENamedElement(luwPartitionKey);
				if (result == null) result = caseEModelElement(luwPartitionKey);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_NICKNAME: {
				LUWNickname luwNickname = (LUWNickname)theEObject;
				Object result = caseLUWNickname(luwNickname);
				if (result == null) result = caseLUWTable(luwNickname);
				if (result == null) result = caseDB2Table(luwNickname);
				if (result == null) result = caseLUWStorageTable(luwNickname);
				if (result == null) result = casePersistentTable(luwNickname);
				if (result == null) result = caseBaseTable(luwNickname);
				if (result == null) result = caseTable(luwNickname);
				if (result == null) result = caseSQLObject(luwNickname);
				if (result == null) result = caseENamedElement(luwNickname);
				if (result == null) result = caseEModelElement(luwNickname);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_FUNCTION_MAPPING: {
				LUWFunctionMapping luwFunctionMapping = (LUWFunctionMapping)theEObject;
				Object result = caseLUWFunctionMapping(luwFunctionMapping);
				if (result == null) result = caseSQLObject(luwFunctionMapping);
				if (result == null) result = caseENamedElement(luwFunctionMapping);
				if (result == null) result = caseEModelElement(luwFunctionMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_WRAPPER: {
				LUWWrapper luwWrapper = (LUWWrapper)theEObject;
				Object result = caseLUWWrapper(luwWrapper);
				if (result == null) result = caseSQLObject(luwWrapper);
				if (result == null) result = caseENamedElement(luwWrapper);
				if (result == null) result = caseEModelElement(luwWrapper);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_NON_RELATIONAL_NICKNAME: {
				LUWNonRelationalNickname luwNonRelationalNickname = (LUWNonRelationalNickname)theEObject;
				Object result = caseLUWNonRelationalNickname(luwNonRelationalNickname);
				if (result == null) result = caseLUWNickname(luwNonRelationalNickname);
				if (result == null) result = caseLUWTable(luwNonRelationalNickname);
				if (result == null) result = caseDB2Table(luwNonRelationalNickname);
				if (result == null) result = caseLUWStorageTable(luwNonRelationalNickname);
				if (result == null) result = casePersistentTable(luwNonRelationalNickname);
				if (result == null) result = caseBaseTable(luwNonRelationalNickname);
				if (result == null) result = caseTable(luwNonRelationalNickname);
				if (result == null) result = caseSQLObject(luwNonRelationalNickname);
				if (result == null) result = caseENamedElement(luwNonRelationalNickname);
				if (result == null) result = caseEModelElement(luwNonRelationalNickname);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_NON_RELATIONAL_SERVER: {
				LUWNonRelationalServer luwNonRelationalServer = (LUWNonRelationalServer)theEObject;
				Object result = caseLUWNonRelationalServer(luwNonRelationalServer);
				if (result == null) result = caseLUWServer(luwNonRelationalServer);
				if (result == null) result = caseSQLObject(luwNonRelationalServer);
				if (result == null) result = caseENamedElement(luwNonRelationalServer);
				if (result == null) result = caseEModelElement(luwNonRelationalServer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_NON_RELATIONAL_WRAPPER: {
				LUWNonRelationalWrapper luwNonRelationalWrapper = (LUWNonRelationalWrapper)theEObject;
				Object result = caseLUWNonRelationalWrapper(luwNonRelationalWrapper);
				if (result == null) result = caseLUWWrapper(luwNonRelationalWrapper);
				if (result == null) result = caseSQLObject(luwNonRelationalWrapper);
				if (result == null) result = caseENamedElement(luwNonRelationalWrapper);
				if (result == null) result = caseEModelElement(luwNonRelationalWrapper);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_RELATIONAL_NICKNAME: {
				LUWRelationalNickname luwRelationalNickname = (LUWRelationalNickname)theEObject;
				Object result = caseLUWRelationalNickname(luwRelationalNickname);
				if (result == null) result = caseLUWNickname(luwRelationalNickname);
				if (result == null) result = caseLUWTable(luwRelationalNickname);
				if (result == null) result = caseDB2Table(luwRelationalNickname);
				if (result == null) result = caseLUWStorageTable(luwRelationalNickname);
				if (result == null) result = casePersistentTable(luwRelationalNickname);
				if (result == null) result = caseBaseTable(luwRelationalNickname);
				if (result == null) result = caseTable(luwRelationalNickname);
				if (result == null) result = caseSQLObject(luwRelationalNickname);
				if (result == null) result = caseENamedElement(luwRelationalNickname);
				if (result == null) result = caseEModelElement(luwRelationalNickname);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_GENERIC_USER_MAPPING: {
				LUWGenericUserMapping luwGenericUserMapping = (LUWGenericUserMapping)theEObject;
				Object result = caseLUWGenericUserMapping(luwGenericUserMapping);
				if (result == null) result = caseLUWUserMapping(luwGenericUserMapping);
				if (result == null) result = caseSQLObject(luwGenericUserMapping);
				if (result == null) result = caseENamedElement(luwGenericUserMapping);
				if (result == null) result = caseEModelElement(luwGenericUserMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_RELATIONAL_WRAPPER: {
				LUWRelationalWrapper luwRelationalWrapper = (LUWRelationalWrapper)theEObject;
				Object result = caseLUWRelationalWrapper(luwRelationalWrapper);
				if (result == null) result = caseLUWWrapper(luwRelationalWrapper);
				if (result == null) result = caseSQLObject(luwRelationalWrapper);
				if (result == null) result = caseENamedElement(luwRelationalWrapper);
				if (result == null) result = caseEModelElement(luwRelationalWrapper);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_SERVER: {
				LUWServer luwServer = (LUWServer)theEObject;
				Object result = caseLUWServer(luwServer);
				if (result == null) result = caseSQLObject(luwServer);
				if (result == null) result = caseENamedElement(luwServer);
				if (result == null) result = caseEModelElement(luwServer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_TYPE_MAPPING: {
				LUWTypeMapping luwTypeMapping = (LUWTypeMapping)theEObject;
				Object result = caseLUWTypeMapping(luwTypeMapping);
				if (result == null) result = caseSQLObject(luwTypeMapping);
				if (result == null) result = caseENamedElement(luwTypeMapping);
				if (result == null) result = caseEModelElement(luwTypeMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_USER_MAPPING: {
				LUWUserMapping luwUserMapping = (LUWUserMapping)theEObject;
				Object result = caseLUWUserMapping(luwUserMapping);
				if (result == null) result = caseSQLObject(luwUserMapping);
				if (result == null) result = caseENamedElement(luwUserMapping);
				if (result == null) result = caseEModelElement(luwUserMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_OPTION: {
				LUWOption luwOption = (LUWOption)theEObject;
				Object result = caseLUWOption(luwOption);
				if (result == null) result = caseSQLObject(luwOption);
				if (result == null) result = caseENamedElement(luwOption);
				if (result == null) result = caseEModelElement(luwOption);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_RELATIONAL_SERVER: {
				LUWRelationalServer luwRelationalServer = (LUWRelationalServer)theEObject;
				Object result = caseLUWRelationalServer(luwRelationalServer);
				if (result == null) result = caseLUWServer(luwRelationalServer);
				if (result == null) result = caseSQLObject(luwRelationalServer);
				if (result == null) result = caseENamedElement(luwRelationalServer);
				if (result == null) result = caseEModelElement(luwRelationalServer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_DATABASE: {
				LUWDatabase luwDatabase = (LUWDatabase)theEObject;
				Object result = caseLUWDatabase(luwDatabase);
				if (result == null) result = caseDB2Database(luwDatabase);
				if (result == null) result = caseDatabase(luwDatabase);
				if (result == null) result = caseSQLObject(luwDatabase);
				if (result == null) result = caseENamedElement(luwDatabase);
				if (result == null) result = caseEModelElement(luwDatabase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_COLUMN: {
				LUWColumn luwColumn = (LUWColumn)theEObject;
				Object result = caseLUWColumn(luwColumn);
				if (result == null) result = caseDB2Column(luwColumn);
				if (result == null) result = caseColumn(luwColumn);
				if (result == null) result = caseTypedElement(luwColumn);
				if (result == null) result = caseSQLObject(luwColumn);
				if (result == null) result = caseENamedElement(luwColumn);
				if (result == null) result = caseEModelElement(luwColumn);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_GENERIC_NICKNAME: {
				LUWGenericNickname luwGenericNickname = (LUWGenericNickname)theEObject;
				Object result = caseLUWGenericNickname(luwGenericNickname);
				if (result == null) result = caseLUWNickname(luwGenericNickname);
				if (result == null) result = caseLUWTable(luwGenericNickname);
				if (result == null) result = caseDB2Table(luwGenericNickname);
				if (result == null) result = caseLUWStorageTable(luwGenericNickname);
				if (result == null) result = casePersistentTable(luwGenericNickname);
				if (result == null) result = caseBaseTable(luwGenericNickname);
				if (result == null) result = caseTable(luwGenericNickname);
				if (result == null) result = caseSQLObject(luwGenericNickname);
				if (result == null) result = caseENamedElement(luwGenericNickname);
				if (result == null) result = caseEModelElement(luwGenericNickname);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_GENERIC_SERVER: {
				LUWGenericServer luwGenericServer = (LUWGenericServer)theEObject;
				Object result = caseLUWGenericServer(luwGenericServer);
				if (result == null) result = caseLUWServer(luwGenericServer);
				if (result == null) result = caseSQLObject(luwGenericServer);
				if (result == null) result = caseENamedElement(luwGenericServer);
				if (result == null) result = caseEModelElement(luwGenericServer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE: {
				LUWMaterializedQueryTable luwMaterializedQueryTable = (LUWMaterializedQueryTable)theEObject;
				Object result = caseLUWMaterializedQueryTable(luwMaterializedQueryTable);
				if (result == null) result = caseDB2MaterializedQueryTable(luwMaterializedQueryTable);
				if (result == null) result = caseLUWStorageTable(luwMaterializedQueryTable);
				if (result == null) result = caseDerivedTable(luwMaterializedQueryTable);
				if (result == null) result = caseTable(luwMaterializedQueryTable);
				if (result == null) result = caseSQLObject(luwMaterializedQueryTable);
				if (result == null) result = caseENamedElement(luwMaterializedQueryTable);
				if (result == null) result = caseEModelElement(luwMaterializedQueryTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_GENERIC_WRAPPER: {
				LUWGenericWrapper luwGenericWrapper = (LUWGenericWrapper)theEObject;
				Object result = caseLUWGenericWrapper(luwGenericWrapper);
				if (result == null) result = caseLUWWrapper(luwGenericWrapper);
				if (result == null) result = caseSQLObject(luwGenericWrapper);
				if (result == null) result = caseENamedElement(luwGenericWrapper);
				if (result == null) result = caseEModelElement(luwGenericWrapper);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_STORAGE_TABLE: {
				LUWStorageTable luwStorageTable = (LUWStorageTable)theEObject;
				Object result = caseLUWStorageTable(luwStorageTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.RELATIONAL_REMOTE_SERVER: {
				RelationalRemoteServer relationalRemoteServer = (RelationalRemoteServer)theEObject;
				Object result = caseRelationalRemoteServer(relationalRemoteServer);
				if (result == null) result = caseSQLObject(relationalRemoteServer);
				if (result == null) result = caseRemoteServer(relationalRemoteServer);
				if (result == null) result = caseENamedElement(relationalRemoteServer);
				if (result == null) result = caseEModelElement(relationalRemoteServer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.RELATIONAL_REMOTE_DATA_SET: {
				RelationalRemoteDataSet relationalRemoteDataSet = (RelationalRemoteDataSet)theEObject;
				Object result = caseRelationalRemoteDataSet(relationalRemoteDataSet);
				if (result == null) result = caseSQLObject(relationalRemoteDataSet);
				if (result == null) result = caseRemoteDataSet(relationalRemoteDataSet);
				if (result == null) result = caseENamedElement(relationalRemoteDataSet);
				if (result == null) result = caseEModelElement(relationalRemoteDataSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.REMOTE_SERVER: {
				RemoteServer remoteServer = (RemoteServer)theEObject;
				Object result = caseRemoteServer(remoteServer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.REMOTE_DATA_SET: {
				RemoteDataSet remoteDataSet = (RemoteDataSet)theEObject;
				Object result = caseRemoteDataSet(remoteDataSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_INDEX: {
				LUWIndex luwIndex = (LUWIndex)theEObject;
				Object result = caseLUWIndex(luwIndex);
				if (result == null) result = caseDB2Index(luwIndex);
				if (result == null) result = caseIndex(luwIndex);
				if (result == null) result = caseSQLObject(luwIndex);
				if (result == null) result = caseENamedElement(luwIndex);
				if (result == null) result = caseEModelElement(luwIndex);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_ATTRIBUTE_DEFINITION: {
				LUWAttributeDefinition luwAttributeDefinition = (LUWAttributeDefinition)theEObject;
				Object result = caseLUWAttributeDefinition(luwAttributeDefinition);
				if (result == null) result = caseAttributeDefinition(luwAttributeDefinition);
				if (result == null) result = caseTypedElement(luwAttributeDefinition);
				if (result == null) result = caseSQLObject(luwAttributeDefinition);
				if (result == null) result = caseENamedElement(luwAttributeDefinition);
				if (result == null) result = caseEModelElement(luwAttributeDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.FEDERATED_PROCEDURE: {
				FederatedProcedure federatedProcedure = (FederatedProcedure)theEObject;
				Object result = caseFederatedProcedure(federatedProcedure);
				if (result == null) result = caseDB2Procedure(federatedProcedure);
				if (result == null) result = caseProcedure(federatedProcedure);
				if (result == null) result = caseDB2Routine(federatedProcedure);
				if (result == null) result = caseRoutine(federatedProcedure);
				if (result == null) result = caseDB2AccessPlan(federatedProcedure);
				if (result == null) result = caseSQLObject(federatedProcedure);
				if (result == null) result = caseENamedElement(federatedProcedure);
				if (result == null) result = caseEModelElement(federatedProcedure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.FEDERATED_PARAMETER: {
				FederatedParameter federatedParameter = (FederatedParameter)theEObject;
				Object result = caseFederatedParameter(federatedParameter);
				if (result == null) result = caseParameter(federatedParameter);
				if (result == null) result = caseTypedElement(federatedParameter);
				if (result == null) result = caseSQLObject(federatedParameter);
				if (result == null) result = caseENamedElement(federatedParameter);
				if (result == null) result = caseEModelElement(federatedParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_PARTITION_EXPRESSION: {
				LUWPartitionExpression luwPartitionExpression = (LUWPartitionExpression)theEObject;
				Object result = caseLUWPartitionExpression(luwPartitionExpression);
				if (result == null) result = caseSQLObject(luwPartitionExpression);
				if (result == null) result = caseENamedElement(luwPartitionExpression);
				if (result == null) result = caseEModelElement(luwPartitionExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_PARTITION_ELEMENT: {
				LUWPartitionElement luwPartitionElement = (LUWPartitionElement)theEObject;
				Object result = caseLUWPartitionElement(luwPartitionElement);
				if (result == null) result = caseSQLObject(luwPartitionElement);
				if (result == null) result = caseENamedElement(luwPartitionElement);
				if (result == null) result = caseEModelElement(luwPartitionElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_DATA_PARTITION: {
				LUWDataPartition luwDataPartition = (LUWDataPartition)theEObject;
				Object result = caseLUWDataPartition(luwDataPartition);
				if (result == null) result = caseSQLObject(luwDataPartition);
				if (result == null) result = caseENamedElement(luwDataPartition);
				if (result == null) result = caseEModelElement(luwDataPartition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_DATA_PARTITION_KEY: {
				LUWDataPartitionKey luwDataPartitionKey = (LUWDataPartitionKey)theEObject;
				Object result = caseLUWDataPartitionKey(luwDataPartitionKey);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_DATABASE_PACKAGE: {
				LUWDatabasePackage luwDatabasePackage = (LUWDatabasePackage)theEObject;
				Object result = caseLUWDatabasePackage(luwDatabasePackage);
				if (result == null) result = caseDB2Package(luwDatabasePackage);
				if (result == null) result = caseDB2AccessPlan(luwDatabasePackage);
				if (result == null) result = caseSQLObject(luwDatabasePackage);
				if (result == null) result = caseENamedElement(luwDatabasePackage);
				if (result == null) result = caseEModelElement(luwDatabasePackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MODULE: {
				LUWModule luwModule = (LUWModule)theEObject;
				Object result = caseLUWModule(luwModule);
				if (result == null) result = caseSQLObject(luwModule);
				if (result == null) result = caseENamedElement(luwModule);
				if (result == null) result = caseEModelElement(luwModule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MODULE_OBJECT: {
				LUWModuleObject luwModuleObject = (LUWModuleObject)theEObject;
				Object result = caseLUWModuleObject(luwModuleObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MODULE_FUNCTION: {
				LUWModuleFunction luwModuleFunction = (LUWModuleFunction)theEObject;
				Object result = caseLUWModuleFunction(luwModuleFunction);
				if (result == null) result = caseDB2UserDefinedFunction(luwModuleFunction);
				if (result == null) result = caseLUWModuleObject(luwModuleFunction);
				if (result == null) result = caseUserDefinedFunction(luwModuleFunction);
				if (result == null) result = caseDB2Function(luwModuleFunction);
				if (result == null) result = caseFunction(luwModuleFunction);
				if (result == null) result = caseDB2Routine(luwModuleFunction);
				if (result == null) result = caseRoutine(luwModuleFunction);
				if (result == null) result = caseDB2AccessPlan(luwModuleFunction);
				if (result == null) result = caseSQLObject(luwModuleFunction);
				if (result == null) result = caseENamedElement(luwModuleFunction);
				if (result == null) result = caseEModelElement(luwModuleFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MODULE_PROCEDURE: {
				LUWModuleProcedure luwModuleProcedure = (LUWModuleProcedure)theEObject;
				Object result = caseLUWModuleProcedure(luwModuleProcedure);
				if (result == null) result = caseDB2Procedure(luwModuleProcedure);
				if (result == null) result = caseLUWModuleObject(luwModuleProcedure);
				if (result == null) result = caseProcedure(luwModuleProcedure);
				if (result == null) result = caseDB2Routine(luwModuleProcedure);
				if (result == null) result = caseRoutine(luwModuleProcedure);
				if (result == null) result = caseDB2AccessPlan(luwModuleProcedure);
				if (result == null) result = caseSQLObject(luwModuleProcedure);
				if (result == null) result = caseENamedElement(luwModuleProcedure);
				if (result == null) result = caseEModelElement(luwModuleProcedure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MODULE_CONDITION: {
				LUWModuleCondition luwModuleCondition = (LUWModuleCondition)theEObject;
				Object result = caseLUWModuleCondition(luwModuleCondition);
				if (result == null) result = caseSQLObject(luwModuleCondition);
				if (result == null) result = caseLUWModuleObject(luwModuleCondition);
				if (result == null) result = caseENamedElement(luwModuleCondition);
				if (result == null) result = caseEModelElement(luwModuleCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_GLOBAL_VARIABLE: {
				LUWGlobalVariable luwGlobalVariable = (LUWGlobalVariable)theEObject;
				Object result = caseLUWGlobalVariable(luwGlobalVariable);
				if (result == null) result = caseTypedElement(luwGlobalVariable);
				if (result == null) result = caseSQLObject(luwGlobalVariable);
				if (result == null) result = caseENamedElement(luwGlobalVariable);
				if (result == null) result = caseEModelElement(luwGlobalVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MODULE_TYPE: {
				LUWModuleType luwModuleType = (LUWModuleType)theEObject;
				Object result = caseLUWModuleType(luwModuleType);
				if (result == null) result = caseLUWModuleObject(luwModuleType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MODULE_ROW_DATA_TYPE: {
				LUWModuleRowDataType luwModuleRowDataType = (LUWModuleRowDataType)theEObject;
				Object result = caseLUWModuleRowDataType(luwModuleRowDataType);
				if (result == null) result = caseLUWRowDataType(luwModuleRowDataType);
				if (result == null) result = caseLUWModuleType(luwModuleRowDataType);
				if (result == null) result = caseUserDefinedType(luwModuleRowDataType);
				if (result == null) result = caseRowDataType(luwModuleRowDataType);
				if (result == null) result = caseLUWModuleObject(luwModuleRowDataType);
				if (result == null) result = caseConstructedDataType(luwModuleRowDataType);
				if (result == null) result = caseDataType(luwModuleRowDataType);
				if (result == null) result = caseSQLObject(luwModuleRowDataType);
				if (result == null) result = caseENamedElement(luwModuleRowDataType);
				if (result == null) result = caseEModelElement(luwModuleRowDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MODULE_ARRAY_DATA_TYPE: {
				LUWModuleArrayDataType luwModuleArrayDataType = (LUWModuleArrayDataType)theEObject;
				Object result = caseLUWModuleArrayDataType(luwModuleArrayDataType);
				if (result == null) result = caseLUWArrayDataType(luwModuleArrayDataType);
				if (result == null) result = caseLUWModuleType(luwModuleArrayDataType);
				if (result == null) result = caseArrayDataType(luwModuleArrayDataType);
				if (result == null) result = caseUserDefinedType(luwModuleArrayDataType);
				if (result == null) result = caseLUWModuleObject(luwModuleArrayDataType);
				if (result == null) result = caseCollectionDataType(luwModuleArrayDataType);
				if (result == null) result = caseConstructedDataType(luwModuleArrayDataType);
				if (result == null) result = caseDataType(luwModuleArrayDataType);
				if (result == null) result = caseSQLObject(luwModuleArrayDataType);
				if (result == null) result = caseENamedElement(luwModuleArrayDataType);
				if (result == null) result = caseEModelElement(luwModuleArrayDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MODULE_DISTINCT_TYPE: {
				LUWModuleDistinctType luwModuleDistinctType = (LUWModuleDistinctType)theEObject;
				Object result = caseLUWModuleDistinctType(luwModuleDistinctType);
				if (result == null) result = caseDistinctUserDefinedType(luwModuleDistinctType);
				if (result == null) result = caseLUWModuleType(luwModuleDistinctType);
				if (result == null) result = caseUserDefinedType(luwModuleDistinctType);
				if (result == null) result = caseLUWModuleObject(luwModuleDistinctType);
				if (result == null) result = caseDataType(luwModuleDistinctType);
				if (result == null) result = caseSQLObject(luwModuleDistinctType);
				if (result == null) result = caseENamedElement(luwModuleDistinctType);
				if (result == null) result = caseEModelElement(luwModuleDistinctType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MODULE_GLOBAL_VARIABLE: {
				LUWModuleGlobalVariable luwModuleGlobalVariable = (LUWModuleGlobalVariable)theEObject;
				Object result = caseLUWModuleGlobalVariable(luwModuleGlobalVariable);
				if (result == null) result = caseLUWGlobalVariable(luwModuleGlobalVariable);
				if (result == null) result = caseLUWModuleObject(luwModuleGlobalVariable);
				if (result == null) result = caseTypedElement(luwModuleGlobalVariable);
				if (result == null) result = caseSQLObject(luwModuleGlobalVariable);
				if (result == null) result = caseENamedElement(luwModuleGlobalVariable);
				if (result == null) result = caseEModelElement(luwModuleGlobalVariable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_ARRAY_DATA_TYPE: {
				LUWArrayDataType luwArrayDataType = (LUWArrayDataType)theEObject;
				Object result = caseLUWArrayDataType(luwArrayDataType);
				if (result == null) result = caseArrayDataType(luwArrayDataType);
				if (result == null) result = caseUserDefinedType(luwArrayDataType);
				if (result == null) result = caseCollectionDataType(luwArrayDataType);
				if (result == null) result = caseConstructedDataType(luwArrayDataType);
				if (result == null) result = caseDataType(luwArrayDataType);
				if (result == null) result = caseSQLObject(luwArrayDataType);
				if (result == null) result = caseENamedElement(luwArrayDataType);
				if (result == null) result = caseEModelElement(luwArrayDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_ROW_DATA_TYPE: {
				LUWRowDataType luwRowDataType = (LUWRowDataType)theEObject;
				Object result = caseLUWRowDataType(luwRowDataType);
				if (result == null) result = caseUserDefinedType(luwRowDataType);
				if (result == null) result = caseRowDataType(luwRowDataType);
				if (result == null) result = caseConstructedDataType(luwRowDataType);
				if (result == null) result = caseDataType(luwRowDataType);
				if (result == null) result = caseSQLObject(luwRowDataType);
				if (result == null) result = caseENamedElement(luwRowDataType);
				if (result == null) result = caseEModelElement(luwRowDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.PLSQL_PACKAGE: {
				PLSQLPackage plsqlPackage = (PLSQLPackage)theEObject;
				Object result = casePLSQLPackage(plsqlPackage);
				if (result == null) result = caseLUWModule(plsqlPackage);
				if (result == null) result = caseDB2Routine(plsqlPackage);
				if (result == null) result = caseRoutine(plsqlPackage);
				if (result == null) result = caseDB2AccessPlan(plsqlPackage);
				if (result == null) result = caseSQLObject(plsqlPackage);
				if (result == null) result = caseENamedElement(plsqlPackage);
				if (result == null) result = caseEModelElement(plsqlPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.PLSQL_PACKAGE_BODY: {
				PLSQLPackageBody plsqlPackageBody = (PLSQLPackageBody)theEObject;
				Object result = casePLSQLPackageBody(plsqlPackageBody);
				if (result == null) result = caseSource(plsqlPackageBody);
				if (result == null) result = caseSQLObject(plsqlPackageBody);
				if (result == null) result = caseENamedElement(plsqlPackageBody);
				if (result == null) result = caseEModelElement(plsqlPackageBody);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_CURSOR_DATA_TYPE: {
				LUWCursorDataType luwCursorDataType = (LUWCursorDataType)theEObject;
				Object result = caseLUWCursorDataType(luwCursorDataType);
				if (result == null) result = caseUserDefinedType(luwCursorDataType);
				if (result == null) result = caseDataType(luwCursorDataType);
				if (result == null) result = caseSQLObject(luwCursorDataType);
				if (result == null) result = caseENamedElement(luwCursorDataType);
				if (result == null) result = caseEModelElement(luwCursorDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MODULE_CURSOR_DATA_TYPE: {
				LUWModuleCursorDataType luwModuleCursorDataType = (LUWModuleCursorDataType)theEObject;
				Object result = caseLUWModuleCursorDataType(luwModuleCursorDataType);
				if (result == null) result = caseLUWCursorDataType(luwModuleCursorDataType);
				if (result == null) result = caseLUWModuleType(luwModuleCursorDataType);
				if (result == null) result = caseUserDefinedType(luwModuleCursorDataType);
				if (result == null) result = caseLUWModuleObject(luwModuleCursorDataType);
				if (result == null) result = caseDataType(luwModuleCursorDataType);
				if (result == null) result = caseSQLObject(luwModuleCursorDataType);
				if (result == null) result = caseENamedElement(luwModuleCursorDataType);
				if (result == null) result = caseEModelElement(luwModuleCursorDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION: {
				LUWBufferPoolSizeException luwBufferPoolSizeException = (LUWBufferPoolSizeException)theEObject;
				Object result = caseLUWBufferPoolSizeException(luwBufferPoolSizeException);
				if (result == null) result = caseSQLObject(luwBufferPoolSizeException);
				if (result == null) result = caseENamedElement(luwBufferPoolSizeException);
				if (result == null) result = caseEModelElement(luwBufferPoolSizeException);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_MEMBER: {
				LUWMember luwMember = (LUWMember)theEObject;
				Object result = caseLUWMember(luwMember);
				if (result == null) result = caseDB2Member(luwMember);
				if (result == null) result = caseSQLObject(luwMember);
				if (result == null) result = caseENamedElement(luwMember);
				if (result == null) result = caseEModelElement(luwMember);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_SECURITY_POLICY: {
				LUWSecurityPolicy luwSecurityPolicy = (LUWSecurityPolicy)theEObject;
				Object result = caseLUWSecurityPolicy(luwSecurityPolicy);
				if (result == null) result = caseSQLObject(luwSecurityPolicy);
				if (result == null) result = caseENamedElement(luwSecurityPolicy);
				if (result == null) result = caseEModelElement(luwSecurityPolicy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT: {
				LUWSecurityLabelComponent luwSecurityLabelComponent = (LUWSecurityLabelComponent)theEObject;
				Object result = caseLUWSecurityLabelComponent(luwSecurityLabelComponent);
				if (result == null) result = caseSQLObject(luwSecurityLabelComponent);
				if (result == null) result = caseENamedElement(luwSecurityLabelComponent);
				if (result == null) result = caseEModelElement(luwSecurityLabelComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_SECURITY_LABEL: {
				LUWSecurityLabel luwSecurityLabel = (LUWSecurityLabel)theEObject;
				Object result = caseLUWSecurityLabel(luwSecurityLabel);
				if (result == null) result = caseSQLObject(luwSecurityLabel);
				if (result == null) result = caseENamedElement(luwSecurityLabel);
				if (result == null) result = caseEModelElement(luwSecurityLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT: {
				LUWSecurityLabelComponentElement luwSecurityLabelComponentElement = (LUWSecurityLabelComponentElement)theEObject;
				Object result = caseLUWSecurityLabelComponentElement(luwSecurityLabelComponentElement);
				if (result == null) result = caseSQLObject(luwSecurityLabelComponentElement);
				if (result == null) result = caseENamedElement(luwSecurityLabelComponentElement);
				if (result == null) result = caseEModelElement(luwSecurityLabelComponentElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_STORAGE_GROUP: {
				LUWStorageGroup luwStorageGroup = (LUWStorageGroup)theEObject;
				Object result = caseLUWStorageGroup(luwStorageGroup);
				if (result == null) result = caseSQLObject(luwStorageGroup);
				if (result == null) result = caseENamedElement(luwStorageGroup);
				if (result == null) result = caseEModelElement(luwStorageGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE: {
				LUWTemporaryStorageTable luwTemporaryStorageTable = (LUWTemporaryStorageTable)theEObject;
				Object result = caseLUWTemporaryStorageTable(luwTemporaryStorageTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_TEMPORARY_TABLE: {
				LUWTemporaryTable luwTemporaryTable = (LUWTemporaryTable)theEObject;
				Object result = caseLUWTemporaryTable(luwTemporaryTable);
				if (result == null) result = caseTemporaryTable(luwTemporaryTable);
				if (result == null) result = caseLUWTemporaryStorageTable(luwTemporaryTable);
				if (result == null) result = caseBaseTable(luwTemporaryTable);
				if (result == null) result = caseTable(luwTemporaryTable);
				if (result == null) result = caseSQLObject(luwTemporaryTable);
				if (result == null) result = caseENamedElement(luwTemporaryTable);
				if (result == null) result = caseEModelElement(luwTemporaryTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.ARRAY_INDEX_ELEMENT_TYPE: {
				ArrayIndexElementType arrayIndexElementType = (ArrayIndexElementType)theEObject;
				Object result = caseArrayIndexElementType(arrayIndexElementType);
				if (result == null) result = caseElementType(arrayIndexElementType);
				if (result == null) result = caseTypedElement(arrayIndexElementType);
				if (result == null) result = caseSQLObject(arrayIndexElementType);
				if (result == null) result = caseENamedElement(arrayIndexElementType);
				if (result == null) result = caseEModelElement(arrayIndexElementType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LUWPackage.LUW_PARTITION_EVERY_CLAUSE_ELEMENT: {
				LUWPartitionEveryClauseElement luwPartitionEveryClauseElement = (LUWPartitionEveryClauseElement)theEObject;
				Object result = caseLUWPartitionEveryClauseElement(luwPartitionEveryClauseElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Partition Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Partition Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWPartitionGroup(LUWPartitionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Space</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Space</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWTableSpace(LUWTableSpace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Database Partition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Database Partition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWDatabasePartition(LUWDatabasePartition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Database Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Database Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWDatabaseContainer(LUWDatabaseContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Admin Server</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Admin Server</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWAdminServer(LUWAdminServer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Buffer Pool</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Buffer Pool</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWBufferPool(LUWBufferPool object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWTable(LUWTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWView(LUWView object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Partition Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Partition Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWPartitionKey(LUWPartitionKey object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nickname</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nickname</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWNickname(LUWNickname object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWFunctionMapping(LUWFunctionMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wrapper</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wrapper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWWrapper(LUWWrapper object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Non Relational Nickname</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Non Relational Nickname</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWNonRelationalNickname(LUWNonRelationalNickname object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Non Relational Server</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Non Relational Server</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWNonRelationalServer(LUWNonRelationalServer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Non Relational Wrapper</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Non Relational Wrapper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWNonRelationalWrapper(LUWNonRelationalWrapper object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relational Nickname</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relational Nickname</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWRelationalNickname(LUWRelationalNickname object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic User Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic User Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWGenericUserMapping(LUWGenericUserMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relational Wrapper</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relational Wrapper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWRelationalWrapper(LUWRelationalWrapper object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Server</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Server</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWServer(LUWServer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWTypeMapping(LUWTypeMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWUserMapping(LUWUserMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Option</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Option</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWOption(LUWOption object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relational Server</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relational Server</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWRelationalServer(LUWRelationalServer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Database</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Database</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWDatabase(LUWDatabase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Column</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Column</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWColumn(LUWColumn object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Nickname</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Nickname</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWGenericNickname(LUWGenericNickname object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Server</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Server</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWGenericServer(LUWGenericServer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Materialized Query Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Materialized Query Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWMaterializedQueryTable(LUWMaterializedQueryTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Wrapper</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Wrapper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWGenericWrapper(LUWGenericWrapper object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Storage Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Storage Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWStorageTable(LUWStorageTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relational Remote Server</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relational Remote Server</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRelationalRemoteServer(RelationalRemoteServer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relational Remote Data Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relational Remote Data Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRelationalRemoteDataSet(RelationalRemoteDataSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remote Server</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remote Server</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRemoteServer(RemoteServer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Remote Data Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Remote Data Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRemoteDataSet(RemoteDataSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Index</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Index</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWIndex(LUWIndex object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWAttributeDefinition(LUWAttributeDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Federated Procedure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Federated Procedure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFederatedProcedure(FederatedProcedure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Federated Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Federated Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFederatedParameter(FederatedParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Partition Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Partition Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWPartitionExpression(LUWPartitionExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Partition Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Partition Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWPartitionElement(LUWPartitionElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Partition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Partition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWDataPartition(LUWDataPartition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Partition Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Partition Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWDataPartitionKey(LUWDataPartitionKey object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Database Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Database Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWDatabasePackage(LUWDatabasePackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWModule(LUWModule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWModuleObject(LUWModuleObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWModuleFunction(LUWModuleFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Procedure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Procedure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWModuleProcedure(LUWModuleProcedure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWModuleCondition(LUWModuleCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Global Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Global Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWGlobalVariable(LUWGlobalVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWModuleType(LUWModuleType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Row Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Row Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWModuleRowDataType(LUWModuleRowDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Array Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Array Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWModuleArrayDataType(LUWModuleArrayDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Distinct Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Distinct Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWModuleDistinctType(LUWModuleDistinctType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Global Variable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Global Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWModuleGlobalVariable(LUWModuleGlobalVariable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Array Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWArrayDataType(LUWArrayDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Row Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Row Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWRowDataType(LUWRowDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>PLSQL Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>PLSQL Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePLSQLPackage(PLSQLPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>PLSQL Package Body</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>PLSQL Package Body</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePLSQLPackageBody(PLSQLPackageBody object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cursor Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cursor Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWCursorDataType(LUWCursorDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module Cursor Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module Cursor Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWModuleCursorDataType(LUWModuleCursorDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Buffer Pool Size Exception</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Buffer Pool Size Exception</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWBufferPoolSizeException(LUWBufferPoolSizeException object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Member</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Member</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWMember(LUWMember object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Security Policy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Security Policy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWSecurityPolicy(LUWSecurityPolicy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Security Label Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Security Label Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWSecurityLabelComponent(LUWSecurityLabelComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Security Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Security Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWSecurityLabel(LUWSecurityLabel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Security Label Component Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Security Label Component Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWSecurityLabelComponentElement(LUWSecurityLabelComponentElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Storage Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Storage Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWStorageGroup(LUWStorageGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Temporary Storage Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Temporary Storage Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWTemporaryStorageTable(LUWTemporaryStorageTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Temporary Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Temporary Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWTemporaryTable(LUWTemporaryTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Array Index Element Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array Index Element Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseArrayIndexElementType(ArrayIndexElementType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Partition Every Clause Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Partition Every Clause Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLUWPartitionEveryClauseElement(LUWPartitionEveryClauseElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEModelElement(EModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseENamedElement(ENamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLObject(SQLObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTable(Table object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Base Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Base Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseBaseTable(BaseTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Persistent Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Persistent Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePersistentTable(PersistentTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Table(DB2Table object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Derived Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Derived Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDerivedTable(DerivedTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>View Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>View Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseViewTable(ViewTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2View(DB2View object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Database</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Database</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDatabase(Database object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Database</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Database</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Database(DB2Database object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTypedElement(TypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Column</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Column</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseColumn(Column object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Column</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Column</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Column(DB2Column object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Materialized Query Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Materialized Query Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2MaterializedQueryTable(DB2MaterializedQueryTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Index</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Index</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseIndex(Index object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Index</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Index</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Index(DB2Index object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAttributeDefinition(AttributeDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Routine</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Routine</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRoutine(Routine object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Procedure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Procedure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseProcedure(Procedure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Access Plan</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Access Plan</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2AccessPlan(DB2AccessPlan object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Routine</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Routine</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Routine(DB2Routine object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Procedure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Procedure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Procedure(DB2Procedure object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseParameter(Parameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Package(DB2Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFunction(Function object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Defined Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Defined Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseUserDefinedFunction(UserDefinedFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Function(DB2Function object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 User Defined Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 User Defined Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2UserDefinedFunction(DB2UserDefinedFunction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDataType(DataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Defined Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Defined Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseUserDefinedType(UserDefinedType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constructed Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constructed Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConstructedDataType(ConstructedDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Row Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Row Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRowDataType(RowDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCollectionDataType(CollectionDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Array Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseArrayDataType(ArrayDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Distinct User Defined Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Distinct User Defined Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDistinctUserDefinedType(DistinctUserDefinedType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSource(Source object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Member</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Member</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Member(DB2Member object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Temporary Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Temporary Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTemporaryTable(TemporaryTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseElementType(ElementType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //LUWSwitch
