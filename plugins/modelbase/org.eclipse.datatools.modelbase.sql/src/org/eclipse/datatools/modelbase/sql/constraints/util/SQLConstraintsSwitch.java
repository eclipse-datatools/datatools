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
package org.eclipse.datatools.modelbase.sql.constraints.util;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.constraints.*;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
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
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage
 * @generated
 */
public class SQLConstraintsSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SQLConstraintsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLConstraintsSwitch() {
		if (modelPackage == null) {
			modelPackage = SQLConstraintsPackage.eINSTANCE;
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
			case SQLConstraintsPackage.ASSERTION: {
				Assertion assertion = (Assertion)theEObject;
				Object result = caseAssertion(assertion);
				if (result == null) result = caseConstraint(assertion);
				if (result == null) result = caseSQLObject(assertion);
				if (result == null) result = caseENamedElement(assertion);
				if (result == null) result = caseEModelElement(assertion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLConstraintsPackage.CONSTRAINT: {
				Constraint constraint = (Constraint)theEObject;
				Object result = caseConstraint(constraint);
				if (result == null) result = caseSQLObject(constraint);
				if (result == null) result = caseENamedElement(constraint);
				if (result == null) result = caseEModelElement(constraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLConstraintsPackage.TABLE_CONSTRAINT: {
				TableConstraint tableConstraint = (TableConstraint)theEObject;
				Object result = caseTableConstraint(tableConstraint);
				if (result == null) result = caseConstraint(tableConstraint);
				if (result == null) result = caseSQLObject(tableConstraint);
				if (result == null) result = caseENamedElement(tableConstraint);
				if (result == null) result = caseEModelElement(tableConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLConstraintsPackage.REFERENCE_CONSTRAINT: {
				ReferenceConstraint referenceConstraint = (ReferenceConstraint)theEObject;
				Object result = caseReferenceConstraint(referenceConstraint);
				if (result == null) result = caseTableConstraint(referenceConstraint);
				if (result == null) result = caseConstraint(referenceConstraint);
				if (result == null) result = caseSQLObject(referenceConstraint);
				if (result == null) result = caseENamedElement(referenceConstraint);
				if (result == null) result = caseEModelElement(referenceConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLConstraintsPackage.CHECK_CONSTRAINT: {
				CheckConstraint checkConstraint = (CheckConstraint)theEObject;
				Object result = caseCheckConstraint(checkConstraint);
				if (result == null) result = caseTableConstraint(checkConstraint);
				if (result == null) result = caseConstraint(checkConstraint);
				if (result == null) result = caseSQLObject(checkConstraint);
				if (result == null) result = caseENamedElement(checkConstraint);
				if (result == null) result = caseEModelElement(checkConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLConstraintsPackage.FOREIGN_KEY: {
				ForeignKey foreignKey = (ForeignKey)theEObject;
				Object result = caseForeignKey(foreignKey);
				if (result == null) result = caseReferenceConstraint(foreignKey);
				if (result == null) result = caseTableConstraint(foreignKey);
				if (result == null) result = caseConstraint(foreignKey);
				if (result == null) result = caseSQLObject(foreignKey);
				if (result == null) result = caseENamedElement(foreignKey);
				if (result == null) result = caseEModelElement(foreignKey);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT: {
				UniqueConstraint uniqueConstraint = (UniqueConstraint)theEObject;
				Object result = caseUniqueConstraint(uniqueConstraint);
				if (result == null) result = caseReferenceConstraint(uniqueConstraint);
				if (result == null) result = caseTableConstraint(uniqueConstraint);
				if (result == null) result = caseConstraint(uniqueConstraint);
				if (result == null) result = caseSQLObject(uniqueConstraint);
				if (result == null) result = caseENamedElement(uniqueConstraint);
				if (result == null) result = caseEModelElement(uniqueConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLConstraintsPackage.PRIMARY_KEY: {
				PrimaryKey primaryKey = (PrimaryKey)theEObject;
				Object result = casePrimaryKey(primaryKey);
				if (result == null) result = caseUniqueConstraint(primaryKey);
				if (result == null) result = caseReferenceConstraint(primaryKey);
				if (result == null) result = caseTableConstraint(primaryKey);
				if (result == null) result = caseConstraint(primaryKey);
				if (result == null) result = caseSQLObject(primaryKey);
				if (result == null) result = caseENamedElement(primaryKey);
				if (result == null) result = caseEModelElement(primaryKey);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLConstraintsPackage.INDEX: {
				Index index = (Index)theEObject;
				Object result = caseIndex(index);
				if (result == null) result = caseSQLObject(index);
				if (result == null) result = caseENamedElement(index);
				if (result == null) result = caseEModelElement(index);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLConstraintsPackage.INDEX_MEMBER: {
				IndexMember indexMember = (IndexMember)theEObject;
				Object result = caseIndexMember(indexMember);
				if (result == null) result = caseSQLObject(indexMember);
				if (result == null) result = caseENamedElement(indexMember);
				if (result == null) result = caseEModelElement(indexMember);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SQLConstraintsPackage.INDEX_EXPRESSION: {
				IndexExpression indexExpression = (IndexExpression)theEObject;
				Object result = caseIndexExpression(indexExpression);
				if (result == null) result = caseSQLObject(indexExpression);
				if (result == null) result = caseENamedElement(indexExpression);
				if (result == null) result = caseEModelElement(indexExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assertion</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assertion</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAssertion(Assertion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConstraint(Constraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTableConstraint(TableConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseReferenceConstraint(ReferenceConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Check Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Check Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCheckConstraint(CheckConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Foreign Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Foreign Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseForeignKey(ForeignKey object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unique Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unique Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseUniqueConstraint(UniqueConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primary Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primary Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePrimaryKey(PrimaryKey object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Index Member</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Index Member</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseIndexMember(IndexMember object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Index Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Index Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseIndexExpression(IndexExpression object) {
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

} //SQLConstraintsSwitch
