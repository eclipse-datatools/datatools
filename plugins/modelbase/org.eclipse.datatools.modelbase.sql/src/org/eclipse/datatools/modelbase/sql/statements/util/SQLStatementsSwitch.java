/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.statements.util;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.statements.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
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
 * @see org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage
 * @generated
 */
public class SQLStatementsSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SQLStatementsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLStatementsSwitch() {
		if (modelPackage == null) {
			modelPackage = SQLStatementsPackage.eINSTANCE;
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
			case SQLStatementsPackage.SQL_STATEMENT: {
				SQLStatement sqlStatement = (SQLStatement)theEObject;
				Object result = caseSQLStatement(sqlStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLStatementsPackage.SQL_DATA_STATEMENT: {
				SQLDataStatement sqlDataStatement = (SQLDataStatement)theEObject;
				Object result = caseSQLDataStatement(sqlDataStatement);
				if (result == null) result = caseSQLStatement(sqlDataStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLStatementsPackage.SQL_SCHEMA_STATEMENT: {
				SQLSchemaStatement sqlSchemaStatement = (SQLSchemaStatement)theEObject;
				Object result = caseSQLSchemaStatement(sqlSchemaStatement);
				if (result == null) result = caseSQLStatement(sqlSchemaStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLStatementsPackage.SQL_CONTROL_STATEMENT: {
				SQLControlStatement sqlControlStatement = (SQLControlStatement)theEObject;
				Object result = caseSQLControlStatement(sqlControlStatement);
				if (result == null) result = caseSQLStatement(sqlControlStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLStatementsPackage.SQL_DATA_CHANGE_STATEMENT: {
				SQLDataChangeStatement sqlDataChangeStatement = (SQLDataChangeStatement)theEObject;
				Object result = caseSQLDataChangeStatement(sqlDataChangeStatement);
				if (result == null) result = caseSQLDataStatement(sqlDataChangeStatement);
				if (result == null) result = caseSQLStatement(sqlDataChangeStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLStatementsPackage.SQL_STATEMENT_DEFAULT: {
				SQLStatementDefault sqlStatementDefault = (SQLStatementDefault)theEObject;
				Object result = caseSQLStatementDefault(sqlStatementDefault);
				if (result == null) result = caseSQLObject(sqlStatementDefault);
				if (result == null) result = caseSQLStatement(sqlStatementDefault);
				if (result == null) result = caseENamedElement(sqlStatementDefault);
				if (result == null) result = caseEModelElement(sqlStatementDefault);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLStatementsPackage.SQL_CONNECTION_STATEMENT: {
				SQLConnectionStatement sqlConnectionStatement = (SQLConnectionStatement)theEObject;
				Object result = caseSQLConnectionStatement(sqlConnectionStatement);
				if (result == null) result = caseSQLStatement(sqlConnectionStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLStatementsPackage.SQL_DIAGNOSTICS_STATEMENT: {
				SQLDiagnosticsStatement sqlDiagnosticsStatement = (SQLDiagnosticsStatement)theEObject;
				Object result = caseSQLDiagnosticsStatement(sqlDiagnosticsStatement);
				if (result == null) result = caseSQLStatement(sqlDiagnosticsStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLStatementsPackage.SQL_DYNAMIC_STATEMENT: {
				SQLDynamicStatement sqlDynamicStatement = (SQLDynamicStatement)theEObject;
				Object result = caseSQLDynamicStatement(sqlDynamicStatement);
				if (result == null) result = caseSQLStatement(sqlDynamicStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLStatementsPackage.SQL_SESSION_STATEMENT: {
				SQLSessionStatement sqlSessionStatement = (SQLSessionStatement)theEObject;
				Object result = caseSQLSessionStatement(sqlSessionStatement);
				if (result == null) result = caseSQLStatement(sqlSessionStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLStatementsPackage.SQL_TRANSACTION_STATEMENT: {
				SQLTransactionStatement sqlTransactionStatement = (SQLTransactionStatement)theEObject;
				Object result = caseSQLTransactionStatement(sqlTransactionStatement);
				if (result == null) result = caseSQLStatement(sqlTransactionStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLStatement(SQLStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Data Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Data Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLDataStatement(SQLDataStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Schema Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Schema Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLSchemaStatement(SQLSchemaStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Control Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Control Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLControlStatement(SQLControlStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Data Change Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Data Change Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLDataChangeStatement(SQLDataChangeStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Statement Default</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Statement Default</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLStatementDefault(SQLStatementDefault object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Connection Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Connection Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLConnectionStatement(SQLConnectionStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Diagnostics Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Diagnostics Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLDiagnosticsStatement(SQLDiagnosticsStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Dynamic Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Dynamic Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLDynamicStatement(SQLDynamicStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Session Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Session Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLSessionStatement(SQLSessionStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Transaction Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Transaction Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLTransactionStatement(SQLTransactionStatement object) {
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

} //SQLStatementsSwitch
