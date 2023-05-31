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
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.ibm.db.models.db2.luw.LUWPackage
 * @generated
 */
public class LUWAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static LUWPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = LUWPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWSwitch modelSwitch =
		new LUWSwitch() {
			public Object caseLUWPartitionGroup(LUWPartitionGroup object) {
				return createLUWPartitionGroupAdapter();
			}
			public Object caseLUWTableSpace(LUWTableSpace object) {
				return createLUWTableSpaceAdapter();
			}
			public Object caseLUWDatabasePartition(LUWDatabasePartition object) {
				return createLUWDatabasePartitionAdapter();
			}
			public Object caseLUWDatabaseContainer(LUWDatabaseContainer object) {
				return createLUWDatabaseContainerAdapter();
			}
			public Object caseLUWAdminServer(LUWAdminServer object) {
				return createLUWAdminServerAdapter();
			}
			public Object caseLUWBufferPool(LUWBufferPool object) {
				return createLUWBufferPoolAdapter();
			}
			public Object caseLUWTable(LUWTable object) {
				return createLUWTableAdapter();
			}
			public Object caseLUWView(LUWView object) {
				return createLUWViewAdapter();
			}
			public Object caseLUWPartitionKey(LUWPartitionKey object) {
				return createLUWPartitionKeyAdapter();
			}
			public Object caseLUWNickname(LUWNickname object) {
				return createLUWNicknameAdapter();
			}
			public Object caseLUWFunctionMapping(LUWFunctionMapping object) {
				return createLUWFunctionMappingAdapter();
			}
			public Object caseLUWWrapper(LUWWrapper object) {
				return createLUWWrapperAdapter();
			}
			public Object caseLUWNonRelationalNickname(LUWNonRelationalNickname object) {
				return createLUWNonRelationalNicknameAdapter();
			}
			public Object caseLUWNonRelationalServer(LUWNonRelationalServer object) {
				return createLUWNonRelationalServerAdapter();
			}
			public Object caseLUWNonRelationalWrapper(LUWNonRelationalWrapper object) {
				return createLUWNonRelationalWrapperAdapter();
			}
			public Object caseLUWRelationalNickname(LUWRelationalNickname object) {
				return createLUWRelationalNicknameAdapter();
			}
			public Object caseLUWGenericUserMapping(LUWGenericUserMapping object) {
				return createLUWGenericUserMappingAdapter();
			}
			public Object caseLUWRelationalWrapper(LUWRelationalWrapper object) {
				return createLUWRelationalWrapperAdapter();
			}
			public Object caseLUWServer(LUWServer object) {
				return createLUWServerAdapter();
			}
			public Object caseLUWTypeMapping(LUWTypeMapping object) {
				return createLUWTypeMappingAdapter();
			}
			public Object caseLUWUserMapping(LUWUserMapping object) {
				return createLUWUserMappingAdapter();
			}
			public Object caseLUWOption(LUWOption object) {
				return createLUWOptionAdapter();
			}
			public Object caseLUWRelationalServer(LUWRelationalServer object) {
				return createLUWRelationalServerAdapter();
			}
			public Object caseLUWDatabase(LUWDatabase object) {
				return createLUWDatabaseAdapter();
			}
			public Object caseLUWColumn(LUWColumn object) {
				return createLUWColumnAdapter();
			}
			public Object caseLUWGenericNickname(LUWGenericNickname object) {
				return createLUWGenericNicknameAdapter();
			}
			public Object caseLUWGenericServer(LUWGenericServer object) {
				return createLUWGenericServerAdapter();
			}
			public Object caseLUWMaterializedQueryTable(LUWMaterializedQueryTable object) {
				return createLUWMaterializedQueryTableAdapter();
			}
			public Object caseLUWGenericWrapper(LUWGenericWrapper object) {
				return createLUWGenericWrapperAdapter();
			}
			public Object caseLUWStorageTable(LUWStorageTable object) {
				return createLUWStorageTableAdapter();
			}
			public Object caseRelationalRemoteServer(RelationalRemoteServer object) {
				return createRelationalRemoteServerAdapter();
			}
			public Object caseRelationalRemoteDataSet(RelationalRemoteDataSet object) {
				return createRelationalRemoteDataSetAdapter();
			}
			public Object caseRemoteServer(RemoteServer object) {
				return createRemoteServerAdapter();
			}
			public Object caseRemoteDataSet(RemoteDataSet object) {
				return createRemoteDataSetAdapter();
			}
			public Object caseLUWIndex(LUWIndex object) {
				return createLUWIndexAdapter();
			}
			public Object caseLUWAttributeDefinition(LUWAttributeDefinition object) {
				return createLUWAttributeDefinitionAdapter();
			}
			public Object caseFederatedProcedure(FederatedProcedure object) {
				return createFederatedProcedureAdapter();
			}
			public Object caseFederatedParameter(FederatedParameter object) {
				return createFederatedParameterAdapter();
			}
			public Object caseLUWPartitionExpression(LUWPartitionExpression object) {
				return createLUWPartitionExpressionAdapter();
			}
			public Object caseLUWPartitionElement(LUWPartitionElement object) {
				return createLUWPartitionElementAdapter();
			}
			public Object caseLUWDataPartition(LUWDataPartition object) {
				return createLUWDataPartitionAdapter();
			}
			public Object caseLUWDataPartitionKey(LUWDataPartitionKey object) {
				return createLUWDataPartitionKeyAdapter();
			}
			public Object caseLUWDatabasePackage(LUWDatabasePackage object) {
				return createLUWDatabasePackageAdapter();
			}
			public Object caseLUWModule(LUWModule object) {
				return createLUWModuleAdapter();
			}
			public Object caseLUWModuleObject(LUWModuleObject object) {
				return createLUWModuleObjectAdapter();
			}
			public Object caseLUWModuleFunction(LUWModuleFunction object) {
				return createLUWModuleFunctionAdapter();
			}
			public Object caseLUWModuleProcedure(LUWModuleProcedure object) {
				return createLUWModuleProcedureAdapter();
			}
			public Object caseLUWModuleCondition(LUWModuleCondition object) {
				return createLUWModuleConditionAdapter();
			}
			public Object caseLUWGlobalVariable(LUWGlobalVariable object) {
				return createLUWGlobalVariableAdapter();
			}
			public Object caseLUWModuleType(LUWModuleType object) {
				return createLUWModuleTypeAdapter();
			}
			public Object caseLUWModuleRowDataType(LUWModuleRowDataType object) {
				return createLUWModuleRowDataTypeAdapter();
			}
			public Object caseLUWModuleArrayDataType(LUWModuleArrayDataType object) {
				return createLUWModuleArrayDataTypeAdapter();
			}
			public Object caseLUWModuleDistinctType(LUWModuleDistinctType object) {
				return createLUWModuleDistinctTypeAdapter();
			}
			public Object caseLUWModuleGlobalVariable(LUWModuleGlobalVariable object) {
				return createLUWModuleGlobalVariableAdapter();
			}
			public Object caseLUWArrayDataType(LUWArrayDataType object) {
				return createLUWArrayDataTypeAdapter();
			}
			public Object caseLUWRowDataType(LUWRowDataType object) {
				return createLUWRowDataTypeAdapter();
			}
			public Object casePLSQLPackage(PLSQLPackage object) {
				return createPLSQLPackageAdapter();
			}
			public Object casePLSQLPackageBody(PLSQLPackageBody object) {
				return createPLSQLPackageBodyAdapter();
			}
			public Object caseLUWCursorDataType(LUWCursorDataType object) {
				return createLUWCursorDataTypeAdapter();
			}
			public Object caseLUWModuleCursorDataType(LUWModuleCursorDataType object) {
				return createLUWModuleCursorDataTypeAdapter();
			}
			public Object caseLUWBufferPoolSizeException(LUWBufferPoolSizeException object) {
				return createLUWBufferPoolSizeExceptionAdapter();
			}
			public Object caseLUWMember(LUWMember object) {
				return createLUWMemberAdapter();
			}
			public Object caseLUWSecurityPolicy(LUWSecurityPolicy object) {
				return createLUWSecurityPolicyAdapter();
			}
			public Object caseLUWSecurityLabelComponent(LUWSecurityLabelComponent object) {
				return createLUWSecurityLabelComponentAdapter();
			}
			public Object caseLUWSecurityLabel(LUWSecurityLabel object) {
				return createLUWSecurityLabelAdapter();
			}
			public Object caseLUWSecurityLabelComponentElement(LUWSecurityLabelComponentElement object) {
				return createLUWSecurityLabelComponentElementAdapter();
			}
			public Object caseLUWStorageGroup(LUWStorageGroup object) {
				return createLUWStorageGroupAdapter();
			}
			public Object caseLUWTemporaryStorageTable(LUWTemporaryStorageTable object) {
				return createLUWTemporaryStorageTableAdapter();
			}
			public Object caseLUWTemporaryTable(LUWTemporaryTable object) {
				return createLUWTemporaryTableAdapter();
			}
			public Object caseArrayIndexElementType(ArrayIndexElementType object) {
				return createArrayIndexElementTypeAdapter();
			}
			public Object caseLUWPartitionEveryClauseElement(LUWPartitionEveryClauseElement object) {
				return createLUWPartitionEveryClauseElementAdapter();
			}
			public Object caseEModelElement(EModelElement object) {
				return createEModelElementAdapter();
			}
			public Object caseENamedElement(ENamedElement object) {
				return createENamedElementAdapter();
			}
			public Object caseSQLObject(SQLObject object) {
				return createSQLObjectAdapter();
			}
			public Object caseTable(Table object) {
				return createTableAdapter();
			}
			public Object caseBaseTable(BaseTable object) {
				return createBaseTableAdapter();
			}
			public Object casePersistentTable(PersistentTable object) {
				return createPersistentTableAdapter();
			}
			public Object caseDB2Table(DB2Table object) {
				return createDB2TableAdapter();
			}
			public Object caseDerivedTable(DerivedTable object) {
				return createDerivedTableAdapter();
			}
			public Object caseViewTable(ViewTable object) {
				return createViewTableAdapter();
			}
			public Object caseDB2View(DB2View object) {
				return createDB2ViewAdapter();
			}
			public Object caseDatabase(Database object) {
				return createDatabaseAdapter();
			}
			public Object caseDB2Database(DB2Database object) {
				return createDB2DatabaseAdapter();
			}
			public Object caseTypedElement(TypedElement object) {
				return createTypedElementAdapter();
			}
			public Object caseColumn(Column object) {
				return createColumnAdapter();
			}
			public Object caseDB2Column(DB2Column object) {
				return createDB2ColumnAdapter();
			}
			public Object caseDB2MaterializedQueryTable(DB2MaterializedQueryTable object) {
				return createDB2MaterializedQueryTableAdapter();
			}
			public Object caseIndex(Index object) {
				return createIndexAdapter();
			}
			public Object caseDB2Index(DB2Index object) {
				return createDB2IndexAdapter();
			}
			public Object caseAttributeDefinition(AttributeDefinition object) {
				return createAttributeDefinitionAdapter();
			}
			public Object caseRoutine(Routine object) {
				return createRoutineAdapter();
			}
			public Object caseProcedure(Procedure object) {
				return createProcedureAdapter();
			}
			public Object caseDB2AccessPlan(DB2AccessPlan object) {
				return createDB2AccessPlanAdapter();
			}
			public Object caseDB2Routine(DB2Routine object) {
				return createDB2RoutineAdapter();
			}
			public Object caseDB2Procedure(DB2Procedure object) {
				return createDB2ProcedureAdapter();
			}
			public Object caseParameter(Parameter object) {
				return createParameterAdapter();
			}
			public Object caseDB2Package(DB2Package object) {
				return createDB2PackageAdapter();
			}
			public Object caseFunction(Function object) {
				return createFunctionAdapter();
			}
			public Object caseUserDefinedFunction(UserDefinedFunction object) {
				return createUserDefinedFunctionAdapter();
			}
			public Object caseDB2Function(DB2Function object) {
				return createDB2FunctionAdapter();
			}
			public Object caseDB2UserDefinedFunction(DB2UserDefinedFunction object) {
				return createDB2UserDefinedFunctionAdapter();
			}
			public Object caseDataType(DataType object) {
				return createDataTypeAdapter();
			}
			public Object caseUserDefinedType(UserDefinedType object) {
				return createUserDefinedTypeAdapter();
			}
			public Object caseConstructedDataType(ConstructedDataType object) {
				return createConstructedDataTypeAdapter();
			}
			public Object caseRowDataType(RowDataType object) {
				return createRowDataTypeAdapter();
			}
			public Object caseCollectionDataType(CollectionDataType object) {
				return createCollectionDataTypeAdapter();
			}
			public Object caseArrayDataType(ArrayDataType object) {
				return createArrayDataTypeAdapter();
			}
			public Object caseDistinctUserDefinedType(DistinctUserDefinedType object) {
				return createDistinctUserDefinedTypeAdapter();
			}
			public Object caseSource(Source object) {
				return createSourceAdapter();
			}
			public Object caseDB2Member(DB2Member object) {
				return createDB2MemberAdapter();
			}
			public Object caseTemporaryTable(TemporaryTable object) {
				return createTemporaryTableAdapter();
			}
			public Object caseElementType(ElementType object) {
				return createElementTypeAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWPartitionGroup <em>Partition Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWPartitionGroup
	 * @generated
	 */
	public Adapter createLUWPartitionGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWTableSpace <em>Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWTableSpace
	 * @generated
	 */
	public Adapter createLUWTableSpaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWDatabasePartition <em>Database Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWDatabasePartition
	 * @generated
	 */
	public Adapter createLUWDatabasePartitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWDatabaseContainer <em>Database Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWDatabaseContainer
	 * @generated
	 */
	public Adapter createLUWDatabaseContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWAdminServer <em>Admin Server</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWAdminServer
	 * @generated
	 */
	public Adapter createLUWAdminServerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWBufferPool <em>Buffer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWBufferPool
	 * @generated
	 */
	public Adapter createLUWBufferPoolAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWTable
	 * @generated
	 */
	public Adapter createLUWTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWView <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWView
	 * @generated
	 */
	public Adapter createLUWViewAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWPartitionKey <em>Partition Key</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWPartitionKey
	 * @generated
	 */
	public Adapter createLUWPartitionKeyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWNickname <em>Nickname</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWNickname
	 * @generated
	 */
	public Adapter createLUWNicknameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWFunctionMapping <em>Function Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWFunctionMapping
	 * @generated
	 */
	public Adapter createLUWFunctionMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWWrapper <em>Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWWrapper
	 * @generated
	 */
	public Adapter createLUWWrapperAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWNonRelationalNickname <em>Non Relational Nickname</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWNonRelationalNickname
	 * @generated
	 */
	public Adapter createLUWNonRelationalNicknameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWNonRelationalServer <em>Non Relational Server</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWNonRelationalServer
	 * @generated
	 */
	public Adapter createLUWNonRelationalServerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWNonRelationalWrapper <em>Non Relational Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWNonRelationalWrapper
	 * @generated
	 */
	public Adapter createLUWNonRelationalWrapperAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWRelationalNickname <em>Relational Nickname</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWRelationalNickname
	 * @generated
	 */
	public Adapter createLUWRelationalNicknameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWGenericUserMapping <em>Generic User Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWGenericUserMapping
	 * @generated
	 */
	public Adapter createLUWGenericUserMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWRelationalWrapper <em>Relational Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWRelationalWrapper
	 * @generated
	 */
	public Adapter createLUWRelationalWrapperAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWServer
	 * @generated
	 */
	public Adapter createLUWServerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWTypeMapping <em>Type Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWTypeMapping
	 * @generated
	 */
	public Adapter createLUWTypeMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWUserMapping <em>User Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWUserMapping
	 * @generated
	 */
	public Adapter createLUWUserMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWOption <em>Option</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWOption
	 * @generated
	 */
	public Adapter createLUWOptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWRelationalServer <em>Relational Server</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWRelationalServer
	 * @generated
	 */
	public Adapter createLUWRelationalServerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWDatabase
	 * @generated
	 */
	public Adapter createLUWDatabaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWColumn
	 * @generated
	 */
	public Adapter createLUWColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWGenericNickname <em>Generic Nickname</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWGenericNickname
	 * @generated
	 */
	public Adapter createLUWGenericNicknameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWGenericServer <em>Generic Server</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWGenericServer
	 * @generated
	 */
	public Adapter createLUWGenericServerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWMaterializedQueryTable <em>Materialized Query Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWMaterializedQueryTable
	 * @generated
	 */
	public Adapter createLUWMaterializedQueryTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWGenericWrapper <em>Generic Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWGenericWrapper
	 * @generated
	 */
	public Adapter createLUWGenericWrapperAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWStorageTable <em>Storage Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWStorageTable
	 * @generated
	 */
	public Adapter createLUWStorageTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.RelationalRemoteServer <em>Relational Remote Server</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.RelationalRemoteServer
	 * @generated
	 */
	public Adapter createRelationalRemoteServerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.RelationalRemoteDataSet <em>Relational Remote Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.RelationalRemoteDataSet
	 * @generated
	 */
	public Adapter createRelationalRemoteDataSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.RemoteServer <em>Remote Server</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.RemoteServer
	 * @generated
	 */
	public Adapter createRemoteServerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.RemoteDataSet <em>Remote Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.RemoteDataSet
	 * @generated
	 */
	public Adapter createRemoteDataSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWIndex
	 * @generated
	 */
	public Adapter createLUWIndexAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWAttributeDefinition <em>Attribute Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWAttributeDefinition
	 * @generated
	 */
	public Adapter createLUWAttributeDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.FederatedProcedure <em>Federated Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.FederatedProcedure
	 * @generated
	 */
	public Adapter createFederatedProcedureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.FederatedParameter <em>Federated Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.FederatedParameter
	 * @generated
	 */
	public Adapter createFederatedParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWPartitionExpression <em>Partition Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWPartitionExpression
	 * @generated
	 */
	public Adapter createLUWPartitionExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWPartitionElement <em>Partition Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWPartitionElement
	 * @generated
	 */
	public Adapter createLUWPartitionElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWDataPartition <em>Data Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWDataPartition
	 * @generated
	 */
	public Adapter createLUWDataPartitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWDataPartitionKey <em>Data Partition Key</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWDataPartitionKey
	 * @generated
	 */
	public Adapter createLUWDataPartitionKeyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWDatabasePackage <em>Database Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWDatabasePackage
	 * @generated
	 */
	public Adapter createLUWDatabasePackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWModule
	 * @generated
	 */
	public Adapter createLUWModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWModuleObject <em>Module Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWModuleObject
	 * @generated
	 */
	public Adapter createLUWModuleObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWModuleFunction <em>Module Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWModuleFunction
	 * @generated
	 */
	public Adapter createLUWModuleFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWModuleProcedure <em>Module Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWModuleProcedure
	 * @generated
	 */
	public Adapter createLUWModuleProcedureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWModuleCondition <em>Module Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWModuleCondition
	 * @generated
	 */
	public Adapter createLUWModuleConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWGlobalVariable <em>Global Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWGlobalVariable
	 * @generated
	 */
	public Adapter createLUWGlobalVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWModuleType <em>Module Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWModuleType
	 * @generated
	 */
	public Adapter createLUWModuleTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWModuleRowDataType <em>Module Row Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWModuleRowDataType
	 * @generated
	 */
	public Adapter createLUWModuleRowDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWModuleArrayDataType <em>Module Array Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWModuleArrayDataType
	 * @generated
	 */
	public Adapter createLUWModuleArrayDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWModuleDistinctType <em>Module Distinct Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWModuleDistinctType
	 * @generated
	 */
	public Adapter createLUWModuleDistinctTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWModuleGlobalVariable <em>Module Global Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWModuleGlobalVariable
	 * @generated
	 */
	public Adapter createLUWModuleGlobalVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWArrayDataType <em>Array Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWArrayDataType
	 * @generated
	 */
	public Adapter createLUWArrayDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWRowDataType <em>Row Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWRowDataType
	 * @generated
	 */
	public Adapter createLUWRowDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.PLSQLPackage <em>PLSQL Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.PLSQLPackage
	 * @generated
	 */
	public Adapter createPLSQLPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.PLSQLPackageBody <em>PLSQL Package Body</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.PLSQLPackageBody
	 * @generated
	 */
	public Adapter createPLSQLPackageBodyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWCursorDataType <em>Cursor Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWCursorDataType
	 * @generated
	 */
	public Adapter createLUWCursorDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWModuleCursorDataType <em>Module Cursor Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWModuleCursorDataType
	 * @generated
	 */
	public Adapter createLUWModuleCursorDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWBufferPoolSizeException <em>Buffer Pool Size Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWBufferPoolSizeException
	 * @generated
	 */
	public Adapter createLUWBufferPoolSizeExceptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWMember <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWMember
	 * @generated
	 */
	public Adapter createLUWMemberAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWSecurityPolicy <em>Security Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWSecurityPolicy
	 * @generated
	 */
	public Adapter createLUWSecurityPolicyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWSecurityLabelComponent <em>Security Label Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWSecurityLabelComponent
	 * @generated
	 */
	public Adapter createLUWSecurityLabelComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWSecurityLabel <em>Security Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWSecurityLabel
	 * @generated
	 */
	public Adapter createLUWSecurityLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWSecurityLabelComponentElement <em>Security Label Component Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWSecurityLabelComponentElement
	 * @generated
	 */
	public Adapter createLUWSecurityLabelComponentElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWStorageGroup <em>Storage Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWStorageGroup
	 * @generated
	 */
	public Adapter createLUWStorageGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWTemporaryStorageTable <em>Temporary Storage Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWTemporaryStorageTable
	 * @generated
	 */
	public Adapter createLUWTemporaryStorageTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWTemporaryTable <em>Temporary Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWTemporaryTable
	 * @generated
	 */
	public Adapter createLUWTemporaryTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.ArrayIndexElementType <em>Array Index Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.ArrayIndexElementType
	 * @generated
	 */
	public Adapter createArrayIndexElementTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.luw.LUWPartitionEveryClauseElement <em>Partition Every Clause Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.luw.LUWPartitionEveryClauseElement
	 * @generated
	 */
	public Adapter createLUWPartitionEveryClauseElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.EModelElement
	 * @generated
	 */
	public Adapter createEModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.ENamedElement <em>ENamed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.ENamedElement
	 * @generated
	 */
	public Adapter createENamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject <em>SQL Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject
	 * @generated
	 */
	public Adapter createSQLObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.Table <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table
	 * @generated
	 */
	public Adapter createTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.BaseTable <em>Base Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.BaseTable
	 * @generated
	 */
	public Adapter createBaseTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.PersistentTable <em>Persistent Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.PersistentTable
	 * @generated
	 */
	public Adapter createPersistentTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Table <em>DB2 Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Table
	 * @generated
	 */
	public Adapter createDB2TableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.DerivedTable <em>Derived Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.DerivedTable
	 * @generated
	 */
	public Adapter createDerivedTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.ViewTable <em>View Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ViewTable
	 * @generated
	 */
	public Adapter createViewTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2View <em>DB2 View</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2View
	 * @generated
	 */
	public Adapter createDB2ViewAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.Database <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Database
	 * @generated
	 */
	public Adapter createDatabaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Database <em>DB2 Database</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Database
	 * @generated
	 */
	public Adapter createDB2DatabaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.TypedElement
	 * @generated
	 */
	public Adapter createTypedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.Column <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column
	 * @generated
	 */
	public Adapter createColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Column <em>DB2 Column</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Column
	 * @generated
	 */
	public Adapter createDB2ColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2MaterializedQueryTable <em>DB2 Materialized Query Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2MaterializedQueryTable
	 * @generated
	 */
	public Adapter createDB2MaterializedQueryTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.constraints.Index <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index
	 * @generated
	 */
	public Adapter createIndexAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Index <em>DB2 Index</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Index
	 * @generated
	 */
	public Adapter createDB2IndexAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition <em>Attribute Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition
	 * @generated
	 */
	public Adapter createAttributeDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.Routine <em>Routine</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine
	 * @generated
	 */
	public Adapter createRoutineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.Procedure <em>Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Procedure
	 * @generated
	 */
	public Adapter createProcedureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2AccessPlan <em>DB2 Access Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2AccessPlan
	 * @generated
	 */
	public Adapter createDB2AccessPlanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Routine <em>DB2 Routine</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Routine
	 * @generated
	 */
	public Adapter createDB2RoutineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Procedure <em>DB2 Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Procedure
	 * @generated
	 */
	public Adapter createDB2ProcedureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Package <em>DB2 Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Package
	 * @generated
	 */
	public Adapter createDB2PackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Function
	 * @generated
	 */
	public Adapter createFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction <em>User Defined Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction
	 * @generated
	 */
	public Adapter createUserDefinedFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Function <em>DB2 Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Function
	 * @generated
	 */
	public Adapter createDB2FunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2UserDefinedFunction <em>DB2 User Defined Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2UserDefinedFunction
	 * @generated
	 */
	public Adapter createDB2UserDefinedFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DataType
	 * @generated
	 */
	public Adapter createDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType <em>User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType
	 * @generated
	 */
	public Adapter createUserDefinedTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.ConstructedDataType <em>Constructed Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ConstructedDataType
	 * @generated
	 */
	public Adapter createConstructedDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.RowDataType <em>Row Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.RowDataType
	 * @generated
	 */
	public Adapter createRowDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.CollectionDataType <em>Collection Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CollectionDataType
	 * @generated
	 */
	public Adapter createCollectionDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType <em>Array Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType
	 * @generated
	 */
	public Adapter createArrayDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType <em>Distinct User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType
	 * @generated
	 */
	public Adapter createDistinctUserDefinedTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.Source <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Source
	 * @generated
	 */
	public Adapter createSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Member <em>DB2 Member</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Member
	 * @generated
	 */
	public Adapter createDB2MemberAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.TemporaryTable <em>Temporary Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.TemporaryTable
	 * @generated
	 */
	public Adapter createTemporaryTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.ElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ElementType
	 * @generated
	 */
	public Adapter createElementTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //LUWAdapterFactory
