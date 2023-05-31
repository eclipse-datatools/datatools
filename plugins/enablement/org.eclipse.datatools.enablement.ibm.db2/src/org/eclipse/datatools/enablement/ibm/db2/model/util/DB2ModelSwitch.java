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


import java.util.List;

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
 * @see com.ibm.db.models.db2.DB2ModelPackage
 * @generated
 */
public class DB2ModelSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DB2ModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2ModelSwitch() {
		if (modelPackage == null) {
			modelPackage = DB2ModelPackage.eINSTANCE;
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
			case DB2ModelPackage.DB2_DATABASE: {
				DB2Database db2Database = (DB2Database)theEObject;
				Object result = caseDB2Database(db2Database);
				if (result == null) result = caseDatabase(db2Database);
				if (result == null) result = caseSQLObject(db2Database);
				if (result == null) result = caseENamedElement(db2Database);
				if (result == null) result = caseEModelElement(db2Database);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_PACKAGE: {
				DB2Package db2Package = (DB2Package)theEObject;
				Object result = caseDB2Package(db2Package);
				if (result == null) result = caseDB2AccessPlan(db2Package);
				if (result == null) result = caseSQLObject(db2Package);
				if (result == null) result = caseENamedElement(db2Package);
				if (result == null) result = caseEModelElement(db2Package);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_TABLE: {
				DB2Table db2Table = (DB2Table)theEObject;
				Object result = caseDB2Table(db2Table);
				if (result == null) result = casePersistentTable(db2Table);
				if (result == null) result = caseBaseTable(db2Table);
				if (result == null) result = caseTable(db2Table);
				if (result == null) result = caseSQLObject(db2Table);
				if (result == null) result = caseENamedElement(db2Table);
				if (result == null) result = caseEModelElement(db2Table);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_TRIGGER: {
				DB2Trigger db2Trigger = (DB2Trigger)theEObject;
				Object result = caseDB2Trigger(db2Trigger);
				if (result == null) result = caseTrigger(db2Trigger);
				if (result == null) result = caseDB2AccessPlan(db2Trigger);
				if (result == null) result = caseSQLObject(db2Trigger);
				if (result == null) result = caseENamedElement(db2Trigger);
				if (result == null) result = caseEModelElement(db2Trigger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_PROCEDURE: {
				DB2Procedure db2Procedure = (DB2Procedure)theEObject;
				Object result = caseDB2Procedure(db2Procedure);
				if (result == null) result = caseProcedure(db2Procedure);
				if (result == null) result = caseDB2Routine(db2Procedure);
				if (result == null) result = caseRoutine(db2Procedure);
				if (result == null) result = caseDB2AccessPlan(db2Procedure);
				if (result == null) result = caseSQLObject(db2Procedure);
				if (result == null) result = caseENamedElement(db2Procedure);
				if (result == null) result = caseEModelElement(db2Procedure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_SCHEMA: {
				DB2Schema db2Schema = (DB2Schema)theEObject;
				Object result = caseDB2Schema(db2Schema);
				if (result == null) result = caseSchema(db2Schema);
				if (result == null) result = caseSQLObject(db2Schema);
				if (result == null) result = caseENamedElement(db2Schema);
				if (result == null) result = caseEModelElement(db2Schema);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_ROUTINE: {
				DB2Routine db2Routine = (DB2Routine)theEObject;
				Object result = caseDB2Routine(db2Routine);
				if (result == null) result = caseRoutine(db2Routine);
				if (result == null) result = caseDB2AccessPlan(db2Routine);
				if (result == null) result = caseSQLObject(db2Routine);
				if (result == null) result = caseENamedElement(db2Routine);
				if (result == null) result = caseEModelElement(db2Routine);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_DATABASE_MANAGER: {
				DB2DatabaseManager db2DatabaseManager = (DB2DatabaseManager)theEObject;
				Object result = caseDB2DatabaseManager(db2DatabaseManager);
				if (result == null) result = caseSQLObject(db2DatabaseManager);
				if (result == null) result = caseENamedElement(db2DatabaseManager);
				if (result == null) result = caseEModelElement(db2DatabaseManager);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_VIEW: {
				DB2View db2View = (DB2View)theEObject;
				Object result = caseDB2View(db2View);
				if (result == null) result = caseViewTable(db2View);
				if (result == null) result = caseDerivedTable(db2View);
				if (result == null) result = caseTable(db2View);
				if (result == null) result = caseSQLObject(db2View);
				if (result == null) result = caseENamedElement(db2View);
				if (result == null) result = caseEModelElement(db2View);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_APPLICATION_PROCESS: {
				DB2ApplicationProcess db2ApplicationProcess = (DB2ApplicationProcess)theEObject;
				Object result = caseDB2ApplicationProcess(db2ApplicationProcess);
				if (result == null) result = caseSQLObject(db2ApplicationProcess);
				if (result == null) result = caseENamedElement(db2ApplicationProcess);
				if (result == null) result = caseEModelElement(db2ApplicationProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_TRANSACTION: {
				DB2Transaction db2Transaction = (DB2Transaction)theEObject;
				Object result = caseDB2Transaction(db2Transaction);
				if (result == null) result = caseSQLObject(db2Transaction);
				if (result == null) result = caseENamedElement(db2Transaction);
				if (result == null) result = caseEModelElement(db2Transaction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_SYSTEM_SCHEMA: {
				DB2SystemSchema db2SystemSchema = (DB2SystemSchema)theEObject;
				Object result = caseDB2SystemSchema(db2SystemSchema);
				if (result == null) result = caseDB2Schema(db2SystemSchema);
				if (result == null) result = caseSchema(db2SystemSchema);
				if (result == null) result = caseSQLObject(db2SystemSchema);
				if (result == null) result = caseENamedElement(db2SystemSchema);
				if (result == null) result = caseEModelElement(db2SystemSchema);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_SOURCE: {
				DB2Source db2Source = (DB2Source)theEObject;
				Object result = caseDB2Source(db2Source);
				if (result == null) result = caseSource(db2Source);
				if (result == null) result = caseSQLObject(db2Source);
				if (result == null) result = caseENamedElement(db2Source);
				if (result == null) result = caseEModelElement(db2Source);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_ACCESS_PLAN: {
				DB2AccessPlan db2AccessPlan = (DB2AccessPlan)theEObject;
				Object result = caseDB2AccessPlan(db2AccessPlan);
				if (result == null) result = caseSQLObject(db2AccessPlan);
				if (result == null) result = caseENamedElement(db2AccessPlan);
				if (result == null) result = caseEModelElement(db2AccessPlan);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_USER_DEFINED_FUNCTION: {
				DB2UserDefinedFunction db2UserDefinedFunction = (DB2UserDefinedFunction)theEObject;
				Object result = caseDB2UserDefinedFunction(db2UserDefinedFunction);
				if (result == null) result = caseUserDefinedFunction(db2UserDefinedFunction);
				if (result == null) result = caseDB2Function(db2UserDefinedFunction);
				if (result == null) result = caseFunction(db2UserDefinedFunction);
				if (result == null) result = caseDB2Routine(db2UserDefinedFunction);
				if (result == null) result = caseRoutine(db2UserDefinedFunction);
				if (result == null) result = caseDB2AccessPlan(db2UserDefinedFunction);
				if (result == null) result = caseSQLObject(db2UserDefinedFunction);
				if (result == null) result = caseENamedElement(db2UserDefinedFunction);
				if (result == null) result = caseEModelElement(db2UserDefinedFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_METHOD: {
				DB2Method db2Method = (DB2Method)theEObject;
				Object result = caseDB2Method(db2Method);
				if (result == null) result = caseMethod(db2Method);
				if (result == null) result = caseDB2Function(db2Method);
				if (result == null) result = caseFunction(db2Method);
				if (result == null) result = caseDB2Routine(db2Method);
				if (result == null) result = caseRoutine(db2Method);
				if (result == null) result = caseDB2AccessPlan(db2Method);
				if (result == null) result = caseSQLObject(db2Method);
				if (result == null) result = caseENamedElement(db2Method);
				if (result == null) result = caseEModelElement(db2Method);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS: {
				DB2ExtendedOptions db2ExtendedOptions = (DB2ExtendedOptions)theEObject;
				Object result = caseDB2ExtendedOptions(db2ExtendedOptions);
				if (result == null) result = caseSQLObject(db2ExtendedOptions);
				if (result == null) result = caseENamedElement(db2ExtendedOptions);
				if (result == null) result = caseEModelElement(db2ExtendedOptions);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_ALIAS: {
				DB2Alias db2Alias = (DB2Alias)theEObject;
				Object result = caseDB2Alias(db2Alias);
				if (result == null) result = caseTable(db2Alias);
				if (result == null) result = caseSQLObject(db2Alias);
				if (result == null) result = caseENamedElement(db2Alias);
				if (result == null) result = caseEModelElement(db2Alias);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE: {
				DB2MaterializedQueryTable db2MaterializedQueryTable = (DB2MaterializedQueryTable)theEObject;
				Object result = caseDB2MaterializedQueryTable(db2MaterializedQueryTable);
				if (result == null) result = caseDerivedTable(db2MaterializedQueryTable);
				if (result == null) result = caseTable(db2MaterializedQueryTable);
				if (result == null) result = caseSQLObject(db2MaterializedQueryTable);
				if (result == null) result = caseENamedElement(db2MaterializedQueryTable);
				if (result == null) result = caseEModelElement(db2MaterializedQueryTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_INDEX: {
				DB2Index db2Index = (DB2Index)theEObject;
				Object result = caseDB2Index(db2Index);
				if (result == null) result = caseIndex(db2Index);
				if (result == null) result = caseSQLObject(db2Index);
				if (result == null) result = caseENamedElement(db2Index);
				if (result == null) result = caseEModelElement(db2Index);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_MULTIDIMENSIONAL_INDEX: {
				DB2MultidimensionalIndex db2MultidimensionalIndex = (DB2MultidimensionalIndex)theEObject;
				Object result = caseDB2MultidimensionalIndex(db2MultidimensionalIndex);
				if (result == null) result = caseIndex(db2MultidimensionalIndex);
				if (result == null) result = caseSQLObject(db2MultidimensionalIndex);
				if (result == null) result = caseENamedElement(db2MultidimensionalIndex);
				if (result == null) result = caseEModelElement(db2MultidimensionalIndex);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_FUNCTION: {
				DB2Function db2Function = (DB2Function)theEObject;
				Object result = caseDB2Function(db2Function);
				if (result == null) result = caseDB2Routine(db2Function);
				if (result == null) result = caseRoutine(db2Function);
				if (result == null) result = caseDB2AccessPlan(db2Function);
				if (result == null) result = caseSQLObject(db2Function);
				if (result == null) result = caseENamedElement(db2Function);
				if (result == null) result = caseEModelElement(db2Function);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_JAVA_OPTIONS: {
				DB2JavaOptions db2JavaOptions = (DB2JavaOptions)theEObject;
				Object result = caseDB2JavaOptions(db2JavaOptions);
				if (result == null) result = caseSQLObject(db2JavaOptions);
				if (result == null) result = caseENamedElement(db2JavaOptions);
				if (result == null) result = caseEModelElement(db2JavaOptions);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_PROCEDURE_DEPLOY: {
				DB2ProcedureDeploy db2ProcedureDeploy = (DB2ProcedureDeploy)theEObject;
				Object result = caseDB2ProcedureDeploy(db2ProcedureDeploy);
				if (result == null) result = caseSQLObject(db2ProcedureDeploy);
				if (result == null) result = caseENamedElement(db2ProcedureDeploy);
				if (result == null) result = caseEModelElement(db2ProcedureDeploy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2OLAP_OBJECT: {
				DB2OLAPObject db2OLAPObject = (DB2OLAPObject)theEObject;
				Object result = caseDB2OLAPObject(db2OLAPObject);
				if (result == null) result = caseSQLObject(db2OLAPObject);
				if (result == null) result = caseENamedElement(db2OLAPObject);
				if (result == null) result = caseEModelElement(db2OLAPObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_ROUTINE_EXTENSION: {
				DB2RoutineExtension db2RoutineExtension = (DB2RoutineExtension)theEObject;
				Object result = caseDB2RoutineExtension(db2RoutineExtension);
				if (result == null) result = caseSQLObject(db2RoutineExtension);
				if (result == null) result = caseENamedElement(db2RoutineExtension);
				if (result == null) result = caseEModelElement(db2RoutineExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_IDENTITY_SPECIFIER: {
				DB2IdentitySpecifier db2IdentitySpecifier = (DB2IdentitySpecifier)theEObject;
				Object result = caseDB2IdentitySpecifier(db2IdentitySpecifier);
				if (result == null) result = caseIdentitySpecifier(db2IdentitySpecifier);
				if (result == null) result = caseSQLObject(db2IdentitySpecifier);
				if (result == null) result = caseENamedElement(db2IdentitySpecifier);
				if (result == null) result = caseEModelElement(db2IdentitySpecifier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_JAR: {
				DB2Jar db2Jar = (DB2Jar)theEObject;
				Object result = caseDB2Jar(db2Jar);
				if (result == null) result = caseSQLObject(db2Jar);
				if (result == null) result = caseENamedElement(db2Jar);
				if (result == null) result = caseEModelElement(db2Jar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_COLUMN: {
				DB2Column db2Column = (DB2Column)theEObject;
				Object result = caseDB2Column(db2Column);
				if (result == null) result = caseColumn(db2Column);
				if (result == null) result = caseTypedElement(db2Column);
				if (result == null) result = caseSQLObject(db2Column);
				if (result == null) result = caseENamedElement(db2Column);
				if (result == null) result = caseEModelElement(db2Column);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2XSR_OBJECT: {
				DB2XSRObject db2XSRObject = (DB2XSRObject)theEObject;
				Object result = caseDB2XSRObject(db2XSRObject);
				if (result == null) result = caseSQLObject(db2XSRObject);
				if (result == null) result = caseENamedElement(db2XSRObject);
				if (result == null) result = caseEModelElement(db2XSRObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2XML_SCHEMA: {
				DB2XMLSchema db2XMLSchema = (DB2XMLSchema)theEObject;
				Object result = caseDB2XMLSchema(db2XMLSchema);
				if (result == null) result = caseDB2XSRObject(db2XMLSchema);
				if (result == null) result = caseSQLObject(db2XMLSchema);
				if (result == null) result = caseENamedElement(db2XMLSchema);
				if (result == null) result = caseEModelElement(db2XMLSchema);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT: {
				DB2XMLSchemaDocument db2XMLSchemaDocument = (DB2XMLSchemaDocument)theEObject;
				Object result = caseDB2XMLSchemaDocument(db2XMLSchemaDocument);
				if (result == null) result = caseSQLObject(db2XMLSchemaDocument);
				if (result == null) result = caseENamedElement(db2XMLSchemaDocument);
				if (result == null) result = caseEModelElement(db2XMLSchemaDocument);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES: {
				DB2XMLSchemaDocProperties db2XMLSchemaDocProperties = (DB2XMLSchemaDocProperties)theEObject;
				Object result = caseDB2XMLSchemaDocProperties(db2XMLSchemaDocProperties);
				if (result == null) result = caseSQLObject(db2XMLSchemaDocProperties);
				if (result == null) result = caseENamedElement(db2XMLSchemaDocProperties);
				if (result == null) result = caseEModelElement(db2XMLSchemaDocProperties);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT: {
				DB2PackageStatement db2PackageStatement = (DB2PackageStatement)theEObject;
				Object result = caseDB2PackageStatement(db2PackageStatement);
				if (result == null) result = caseSQLObject(db2PackageStatement);
				if (result == null) result = caseENamedElement(db2PackageStatement);
				if (result == null) result = caseEModelElement(db2PackageStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_DISTINCT_USER_DEFINED_TYPE: {
				DB2DistinctUserDefinedType db2DistinctUserDefinedType = (DB2DistinctUserDefinedType)theEObject;
				Object result = caseDB2DistinctUserDefinedType(db2DistinctUserDefinedType);
				if (result == null) result = caseDistinctUserDefinedType(db2DistinctUserDefinedType);
				if (result == null) result = caseUserDefinedType(db2DistinctUserDefinedType);
				if (result == null) result = caseDataType(db2DistinctUserDefinedType);
				if (result == null) result = caseSQLObject(db2DistinctUserDefinedType);
				if (result == null) result = caseENamedElement(db2DistinctUserDefinedType);
				if (result == null) result = caseEModelElement(db2DistinctUserDefinedType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_PERIOD: {
				DB2Period db2Period = (DB2Period)theEObject;
				Object result = caseDB2Period(db2Period);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_CLUSTER: {
				DB2Cluster db2Cluster = (DB2Cluster)theEObject;
				Object result = caseDB2Cluster(db2Cluster);
				if (result == null) result = caseSQLObject(db2Cluster);
				if (result == null) result = caseENamedElement(db2Cluster);
				if (result == null) result = caseEModelElement(db2Cluster);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_MEMBER: {
				DB2Member db2Member = (DB2Member)theEObject;
				Object result = caseDB2Member(db2Member);
				if (result == null) result = caseSQLObject(db2Member);
				if (result == null) result = caseENamedElement(db2Member);
				if (result == null) result = caseEModelElement(db2Member);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_UNIQUE_CONSTRAINT_EXTENSION: {
				DB2UniqueConstraintExtension db2UniqueConstraintExtension = (DB2UniqueConstraintExtension)theEObject;
				Object result = caseDB2UniqueConstraintExtension(db2UniqueConstraintExtension);
				if (result == null) result = caseSQLObject(db2UniqueConstraintExtension);
				if (result == null) result = caseObjectExtension(db2UniqueConstraintExtension);
				if (result == null) result = caseENamedElement(db2UniqueConstraintExtension);
				if (result == null) result = caseEModelElement(db2UniqueConstraintExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_MASK: {
				DB2Mask db2Mask = (DB2Mask)theEObject;
				Object result = caseDB2Mask(db2Mask);
				if (result == null) result = caseSQLObject(db2Mask);
				if (result == null) result = caseENamedElement(db2Mask);
				if (result == null) result = caseEModelElement(db2Mask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DB2ModelPackage.DB2_PERMISSION: {
				DB2Permission db2Permission = (DB2Permission)theEObject;
				Object result = caseDB2Permission(db2Permission);
				if (result == null) result = caseSQLObject(db2Permission);
				if (result == null) result = caseENamedElement(db2Permission);
				if (result == null) result = caseEModelElement(db2Permission);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
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
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Trigger(DB2Trigger object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Schema</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Schema</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Schema(DB2Schema object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Database Manager</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Database Manager</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2DatabaseManager(DB2DatabaseManager object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Application Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Application Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2ApplicationProcess(DB2ApplicationProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Transaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Transaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Transaction(DB2Transaction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 System Schema</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 System Schema</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2SystemSchema(DB2SystemSchema object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Source(DB2Source object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Method(DB2Method object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Extended Options</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Extended Options</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2ExtendedOptions(DB2ExtendedOptions object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Alias</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Alias</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Alias(DB2Alias object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Multidimensional Index</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Multidimensional Index</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2MultidimensionalIndex(DB2MultidimensionalIndex object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Java Options</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Java Options</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2JavaOptions(DB2JavaOptions object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Procedure Deploy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Procedure Deploy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2ProcedureDeploy(DB2ProcedureDeploy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2OLAP Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2OLAP Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2OLAPObject(DB2OLAPObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Routine Extension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Routine Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2RoutineExtension(DB2RoutineExtension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Identity Specifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Identity Specifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2IdentitySpecifier(DB2IdentitySpecifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Jar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Jar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Jar(DB2Jar object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>DB2XSR Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2XSR Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2XSRObject(DB2XSRObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2XML Schema</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2XML Schema</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2XMLSchema(DB2XMLSchema object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2XML Schema Document</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2XML Schema Document</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2XMLSchemaDocument(DB2XMLSchemaDocument object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2XML Schema Doc Properties</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2XML Schema Doc Properties</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2XMLSchemaDocProperties(DB2XMLSchemaDocProperties object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Package Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Package Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2PackageStatement(DB2PackageStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Distinct User Defined Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Distinct User Defined Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2DistinctUserDefinedType(DB2DistinctUserDefinedType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Period</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Period</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Period(DB2Period object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Cluster</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Cluster</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Cluster(DB2Cluster object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Unique Constraint Extension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Unique Constraint Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2UniqueConstraintExtension(DB2UniqueConstraintExtension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Mask</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Mask</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Mask(DB2Mask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DB2 Permission</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DB2 Permission</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDB2Permission(DB2Permission object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTrigger(Trigger object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Schema</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Schema</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSchema(Schema object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMethod(Method object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Identity Specifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Identity Specifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseIdentitySpecifier(IdentitySpecifier object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Object Extension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object Extension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseObjectExtension(ObjectExtension object) {
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

} //DB2ModelSwitch
