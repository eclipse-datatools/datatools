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
package org.eclipse.datatools.enablement.ibm.db2.model.util;


import org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Column;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Database;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2DistinctUserDefinedType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Function;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Index;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Member;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Method;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2MultidimensionalIndex;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Period;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ProcedureDeploy;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Source;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2SystemSchema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Table;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Transaction;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2UniqueConstraintExtension;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2UserDefinedFunction;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2View;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Method;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.ObjectExtension;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.DerivedTable;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
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
 * @see com.ibm.db.models.db2.DB2ModelPackage
 * @generated
 */
public class DB2ModelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DB2ModelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2ModelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DB2ModelPackage.eINSTANCE;
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
	protected DB2ModelSwitch modelSwitch =
		new DB2ModelSwitch() {
			public Object caseDB2Database(DB2Database object) {
				return createDB2DatabaseAdapter();
			}
			public Object caseDB2Package(DB2Package object) {
				return createDB2PackageAdapter();
			}
			public Object caseDB2Table(DB2Table object) {
				return createDB2TableAdapter();
			}
			public Object caseDB2Trigger(DB2Trigger object) {
				return createDB2TriggerAdapter();
			}
			public Object caseDB2Procedure(DB2Procedure object) {
				return createDB2ProcedureAdapter();
			}
			public Object caseDB2Schema(DB2Schema object) {
				return createDB2SchemaAdapter();
			}
			public Object caseDB2Routine(DB2Routine object) {
				return createDB2RoutineAdapter();
			}
			public Object caseDB2DatabaseManager(DB2DatabaseManager object) {
				return createDB2DatabaseManagerAdapter();
			}
			public Object caseDB2View(DB2View object) {
				return createDB2ViewAdapter();
			}
			public Object caseDB2ApplicationProcess(DB2ApplicationProcess object) {
				return createDB2ApplicationProcessAdapter();
			}
			public Object caseDB2Transaction(DB2Transaction object) {
				return createDB2TransactionAdapter();
			}
			public Object caseDB2SystemSchema(DB2SystemSchema object) {
				return createDB2SystemSchemaAdapter();
			}
			public Object caseDB2Source(DB2Source object) {
				return createDB2SourceAdapter();
			}
			public Object caseDB2AccessPlan(DB2AccessPlan object) {
				return createDB2AccessPlanAdapter();
			}
			public Object caseDB2UserDefinedFunction(DB2UserDefinedFunction object) {
				return createDB2UserDefinedFunctionAdapter();
			}
			public Object caseDB2Method(DB2Method object) {
				return createDB2MethodAdapter();
			}
			public Object caseDB2ExtendedOptions(DB2ExtendedOptions object) {
				return createDB2ExtendedOptionsAdapter();
			}
			public Object caseDB2Alias(DB2Alias object) {
				return createDB2AliasAdapter();
			}
			public Object caseDB2MaterializedQueryTable(DB2MaterializedQueryTable object) {
				return createDB2MaterializedQueryTableAdapter();
			}
			public Object caseDB2Index(DB2Index object) {
				return createDB2IndexAdapter();
			}
			public Object caseDB2MultidimensionalIndex(DB2MultidimensionalIndex object) {
				return createDB2MultidimensionalIndexAdapter();
			}
			public Object caseDB2Function(DB2Function object) {
				return createDB2FunctionAdapter();
			}
			public Object caseDB2JavaOptions(DB2JavaOptions object) {
				return createDB2JavaOptionsAdapter();
			}
			public Object caseDB2ProcedureDeploy(DB2ProcedureDeploy object) {
				return createDB2ProcedureDeployAdapter();
			}
			public Object caseDB2OLAPObject(DB2OLAPObject object) {
				return createDB2OLAPObjectAdapter();
			}
			public Object caseDB2RoutineExtension(DB2RoutineExtension object) {
				return createDB2RoutineExtensionAdapter();
			}
			public Object caseDB2IdentitySpecifier(DB2IdentitySpecifier object) {
				return createDB2IdentitySpecifierAdapter();
			}
			public Object caseDB2Jar(DB2Jar object) {
				return createDB2JarAdapter();
			}
			public Object caseDB2Column(DB2Column object) {
				return createDB2ColumnAdapter();
			}
			public Object caseDB2XSRObject(DB2XSRObject object) {
				return createDB2XSRObjectAdapter();
			}
			public Object caseDB2XMLSchema(DB2XMLSchema object) {
				return createDB2XMLSchemaAdapter();
			}
			public Object caseDB2XMLSchemaDocument(DB2XMLSchemaDocument object) {
				return createDB2XMLSchemaDocumentAdapter();
			}
			public Object caseDB2XMLSchemaDocProperties(DB2XMLSchemaDocProperties object) {
				return createDB2XMLSchemaDocPropertiesAdapter();
			}
			public Object caseDB2PackageStatement(DB2PackageStatement object) {
				return createDB2PackageStatementAdapter();
			}
			public Object caseDB2DistinctUserDefinedType(DB2DistinctUserDefinedType object) {
				return createDB2DistinctUserDefinedTypeAdapter();
			}
			public Object caseDB2Period(DB2Period object) {
				return createDB2PeriodAdapter();
			}
			public Object caseDB2Cluster(DB2Cluster object) {
				return createDB2ClusterAdapter();
			}
			public Object caseDB2Member(DB2Member object) {
				return createDB2MemberAdapter();
			}
			public Object caseDB2UniqueConstraintExtension(DB2UniqueConstraintExtension object) {
				return createDB2UniqueConstraintExtensionAdapter();
			}
			public Object caseDB2Mask(DB2Mask object) {
				return createDB2MaskAdapter();
			}
			public Object caseDB2Permission(DB2Permission object) {
				return createDB2PermissionAdapter();
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
			public Object caseDatabase(Database object) {
				return createDatabaseAdapter();
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
			public Object caseTrigger(Trigger object) {
				return createTriggerAdapter();
			}
			public Object caseRoutine(Routine object) {
				return createRoutineAdapter();
			}
			public Object caseProcedure(Procedure object) {
				return createProcedureAdapter();
			}
			public Object caseSchema(Schema object) {
				return createSchemaAdapter();
			}
			public Object caseDerivedTable(DerivedTable object) {
				return createDerivedTableAdapter();
			}
			public Object caseViewTable(ViewTable object) {
				return createViewTableAdapter();
			}
			public Object caseSource(Source object) {
				return createSourceAdapter();
			}
			public Object caseFunction(Function object) {
				return createFunctionAdapter();
			}
			public Object caseUserDefinedFunction(UserDefinedFunction object) {
				return createUserDefinedFunctionAdapter();
			}
			public Object caseMethod(Method object) {
				return createMethodAdapter();
			}
			public Object caseIndex(Index object) {
				return createIndexAdapter();
			}
			public Object caseIdentitySpecifier(IdentitySpecifier object) {
				return createIdentitySpecifierAdapter();
			}
			public Object caseTypedElement(TypedElement object) {
				return createTypedElementAdapter();
			}
			public Object caseColumn(Column object) {
				return createColumnAdapter();
			}
			public Object caseDataType(DataType object) {
				return createDataTypeAdapter();
			}
			public Object caseUserDefinedType(UserDefinedType object) {
				return createUserDefinedTypeAdapter();
			}
			public Object caseDistinctUserDefinedType(DistinctUserDefinedType object) {
				return createDistinctUserDefinedTypeAdapter();
			}
			public Object caseObjectExtension(ObjectExtension object) {
				return createObjectExtensionAdapter();
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
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Trigger <em>DB2 Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Trigger
	 * @generated
	 */
	public Adapter createDB2TriggerAdapter() {
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
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Schema <em>DB2 Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Schema
	 * @generated
	 */
	public Adapter createDB2SchemaAdapter() {
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
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2DatabaseManager <em>DB2 Database Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2DatabaseManager
	 * @generated
	 */
	public Adapter createDB2DatabaseManagerAdapter() {
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
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2ApplicationProcess <em>DB2 Application Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2ApplicationProcess
	 * @generated
	 */
	public Adapter createDB2ApplicationProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Transaction <em>DB2 Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Transaction
	 * @generated
	 */
	public Adapter createDB2TransactionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2SystemSchema <em>DB2 System Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2SystemSchema
	 * @generated
	 */
	public Adapter createDB2SystemSchemaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Source <em>DB2 Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Source
	 * @generated
	 */
	public Adapter createDB2SourceAdapter() {
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
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Method <em>DB2 Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Method
	 * @generated
	 */
	public Adapter createDB2MethodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2ExtendedOptions <em>DB2 Extended Options</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2ExtendedOptions
	 * @generated
	 */
	public Adapter createDB2ExtendedOptionsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Alias <em>DB2 Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Alias
	 * @generated
	 */
	public Adapter createDB2AliasAdapter() {
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
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2MultidimensionalIndex <em>DB2 Multidimensional Index</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2MultidimensionalIndex
	 * @generated
	 */
	public Adapter createDB2MultidimensionalIndexAdapter() {
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
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2JavaOptions <em>DB2 Java Options</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2JavaOptions
	 * @generated
	 */
	public Adapter createDB2JavaOptionsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2ProcedureDeploy <em>DB2 Procedure Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2ProcedureDeploy
	 * @generated
	 */
	public Adapter createDB2ProcedureDeployAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2OLAPObject <em>DB2OLAP Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2OLAPObject
	 * @generated
	 */
	public Adapter createDB2OLAPObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2RoutineExtension <em>DB2 Routine Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2RoutineExtension
	 * @generated
	 */
	public Adapter createDB2RoutineExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2IdentitySpecifier <em>DB2 Identity Specifier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2IdentitySpecifier
	 * @generated
	 */
	public Adapter createDB2IdentitySpecifierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Jar <em>DB2 Jar</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Jar
	 * @generated
	 */
	public Adapter createDB2JarAdapter() {
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
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2XSRObject <em>DB2XSR Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2XSRObject
	 * @generated
	 */
	public Adapter createDB2XSRObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2XMLSchema <em>DB2XML Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2XMLSchema
	 * @generated
	 */
	public Adapter createDB2XMLSchemaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2XMLSchemaDocument <em>DB2XML Schema Document</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2XMLSchemaDocument
	 * @generated
	 */
	public Adapter createDB2XMLSchemaDocumentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2XMLSchemaDocProperties <em>DB2XML Schema Doc Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2XMLSchemaDocProperties
	 * @generated
	 */
	public Adapter createDB2XMLSchemaDocPropertiesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2PackageStatement <em>DB2 Package Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2PackageStatement
	 * @generated
	 */
	public Adapter createDB2PackageStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2DistinctUserDefinedType <em>DB2 Distinct User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2DistinctUserDefinedType
	 * @generated
	 */
	public Adapter createDB2DistinctUserDefinedTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Period <em>DB2 Period</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Period
	 * @generated
	 */
	public Adapter createDB2PeriodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Cluster <em>DB2 Cluster</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Cluster
	 * @generated
	 */
	public Adapter createDB2ClusterAdapter() {
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
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2UniqueConstraintExtension <em>DB2 Unique Constraint Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2UniqueConstraintExtension
	 * @generated
	 */
	public Adapter createDB2UniqueConstraintExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Mask <em>DB2 Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Mask
	 * @generated
	 */
	public Adapter createDB2MaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.ibm.db.models.db2.DB2Permission <em>DB2 Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.ibm.db.models.db2.DB2Permission
	 * @generated
	 */
	public Adapter createDB2PermissionAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger
	 * @generated
	 */
	public Adapter createTriggerAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.Schema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema
	 * @generated
	 */
	public Adapter createSchemaAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.Method <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Method
	 * @generated
	 */
	public Adapter createMethodAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier <em>Identity Specifier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier
	 * @generated
	 */
	public Adapter createIdentitySpecifierAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.ObjectExtension <em>Object Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.ObjectExtension
	 * @generated
	 */
	public Adapter createObjectExtensionAdapter() {
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

} //DB2ModelAdapterFactory
