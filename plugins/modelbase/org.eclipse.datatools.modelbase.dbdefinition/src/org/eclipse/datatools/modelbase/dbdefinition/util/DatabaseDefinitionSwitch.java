/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.dbdefinition.util;

import java.util.List;

import org.eclipse.datatools.modelbase.dbdefinition.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;



/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage
 * @generated
 */
public class DatabaseDefinitionSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DatabaseDefinitionPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseDefinitionSwitch() {
		if (modelPackage == null) {
			modelPackage = DatabaseDefinitionPackage.eINSTANCE;
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
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION: {
				DatabaseVendorDefinition databaseVendorDefinition = (DatabaseVendorDefinition)theEObject;
				Object result = caseDatabaseVendorDefinition(databaseVendorDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION: {
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = (PredefinedDataTypeDefinition)theEObject;
				Object result = casePredefinedDataTypeDefinition(predefinedDataTypeDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION: {
				TableSpaceDefinition tableSpaceDefinition = (TableSpaceDefinition)theEObject;
				Object result = caseTableSpaceDefinition(tableSpaceDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION: {
				StoredProcedureDefinition storedProcedureDefinition = (StoredProcedureDefinition)theEObject;
				Object result = caseStoredProcedureDefinition(storedProcedureDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION: {
				TriggerDefinition triggerDefinition = (TriggerDefinition)theEObject;
				Object result = caseTriggerDefinition(triggerDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.COLUMN_DEFINITION: {
				ColumnDefinition columnDefinition = (ColumnDefinition)theEObject;
				Object result = caseColumnDefinition(columnDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION: {
				ConstraintDefinition constraintDefinition = (ConstraintDefinition)theEObject;
				Object result = caseConstraintDefinition(constraintDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.INDEX_DEFINITION: {
				IndexDefinition indexDefinition = (IndexDefinition)theEObject;
				Object result = caseIndexDefinition(indexDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.EXTENDED_DEFINITION: {
				ExtendedDefinition extendedDefinition = (ExtendedDefinition)theEObject;
				Object result = caseExtendedDefinition(extendedDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.TABLE_DEFINITION: {
				TableDefinition tableDefinition = (TableDefinition)theEObject;
				Object result = caseTableDefinition(tableDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION: {
				SequenceDefinition sequenceDefinition = (SequenceDefinition)theEObject;
				Object result = caseSequenceDefinition(sequenceDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION: {
				UserDefinedTypeDefinition userDefinedTypeDefinition = (UserDefinedTypeDefinition)theEObject;
				Object result = caseUserDefinedTypeDefinition(userDefinedTypeDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.QUERY_DEFINITION: {
				QueryDefinition queryDefinition = (QueryDefinition)theEObject;
				Object result = caseQueryDefinition(queryDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION: {
				SQLSyntaxDefinition sqlSyntaxDefinition = (SQLSyntaxDefinition)theEObject;
				Object result = caseSQLSyntaxDefinition(sqlSyntaxDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION: {
				NicknameDefinition nicknameDefinition = (NicknameDefinition)theEObject;
				Object result = caseNicknameDefinition(nicknameDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.SCHEMA_DEFINITION: {
				SchemaDefinition schemaDefinition = (SchemaDefinition)theEObject;
				Object result = caseSchemaDefinition(schemaDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.VIEW_DEFINITION: {
				ViewDefinition viewDefinition = (ViewDefinition)theEObject;
				Object result = caseViewDefinition(viewDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION: {
				FieldQualifierDefinition fieldQualifierDefinition = (FieldQualifierDefinition)theEObject;
				Object result = caseFieldQualifierDefinition(fieldQualifierDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.DEBUGGER_DEFINITION: {
				DebuggerDefinition debuggerDefinition = (DebuggerDefinition)theEObject;
				Object result = caseDebuggerDefinition(debuggerDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION: {
				PrivilegedElementDefinition privilegedElementDefinition = (PrivilegedElementDefinition)theEObject;
				Object result = casePrivilegedElementDefinition(privilegedElementDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.PRIVILEGE_DEFINITION: {
				PrivilegeDefinition privilegeDefinition = (PrivilegeDefinition)theEObject;
				Object result = casePrivilegeDefinition(privilegeDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION: {
				ConstructedDataTypeDefinition constructedDataTypeDefinition = (ConstructedDataTypeDefinition)theEObject;
				Object result = caseConstructedDataTypeDefinition(constructedDataTypeDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Database Vendor Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Database Vendor Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDatabaseVendorDefinition(DatabaseVendorDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Predefined Data Type Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Predefined Data Type Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePredefinedDataTypeDefinition(PredefinedDataTypeDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Space Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Space Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTableSpaceDefinition(TableSpaceDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stored Procedure Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stored Procedure Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseStoredProcedureDefinition(StoredProcedureDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trigger Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trigger Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTriggerDefinition(TriggerDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Column Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Column Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseColumnDefinition(ColumnDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConstraintDefinition(ConstraintDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Index Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Index Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseIndexDefinition(IndexDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extended Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extended Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExtendedDefinition(ExtendedDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTableDefinition(TableDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSequenceDefinition(SequenceDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Defined Type Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Defined Type Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseUserDefinedTypeDefinition(UserDefinedTypeDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Query Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Query Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseQueryDefinition(QueryDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Syntax Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Syntax Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLSyntaxDefinition(SQLSyntaxDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nickname Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nickname Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseNicknameDefinition(NicknameDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Schema Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Schema Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSchemaDefinition(SchemaDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>View Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>View Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseViewDefinition(ViewDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Field Qualifier Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Field Qualifier Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFieldQualifierDefinition(FieldQualifierDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Debugger Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Debugger Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDebuggerDefinition(DebuggerDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Privileged Element Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Privileged Element Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePrivilegedElementDefinition(PrivilegedElementDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Privilege Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Privilege Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePrivilegeDefinition(PrivilegeDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constructed Data Type Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constructed Data Type Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConstructedDataTypeDefinition(ConstructedDataTypeDefinition object) {
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

} //DatabaseDefinitionSwitch
