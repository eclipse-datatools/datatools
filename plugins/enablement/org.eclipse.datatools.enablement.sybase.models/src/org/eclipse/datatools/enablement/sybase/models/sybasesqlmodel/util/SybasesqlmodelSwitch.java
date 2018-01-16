/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybasesqlmodelSwitch.java,v 1.1 2008/04/28 17:10:56 bfitzpatrick Exp $
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.util;

import java.util.List;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.*;

import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;

import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;

import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.Routine;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;

import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.DerivedTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
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
 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage
 * @generated
 */
public class SybasesqlmodelSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SybasesqlmodelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybasesqlmodelSwitch() {
		if (modelPackage == null) {
			modelPackage = SybasesqlmodelPackage.eINSTANCE;
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
			case SybasesqlmodelPackage.SYBASE_PARAMETER: {
				SybaseParameter sybaseParameter = (SybaseParameter)theEObject;
				Object result = caseSybaseParameter(sybaseParameter);
				if (result == null) result = caseParameter(sybaseParameter);
				if (result == null) result = caseTypedElement(sybaseParameter);
				if (result == null) result = caseSQLObject(sybaseParameter);
				if (result == null) result = caseENamedElement(sybaseParameter);
				if (result == null) result = caseEModelElement(sybaseParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybasesqlmodelPackage.SYBASE_ROUTINE: {
				SybaseRoutine sybaseRoutine = (SybaseRoutine)theEObject;
				Object result = caseSybaseRoutine(sybaseRoutine);
				if (result == null) result = caseRoutine(sybaseRoutine);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseRoutine);
				if (result == null) result = caseSQLObject(sybaseRoutine);
				if (result == null) result = caseENamedElement(sybaseRoutine);
				if (result == null) result = caseEModelElement(sybaseRoutine);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybasesqlmodelPackage.SYBASE_BASE_TABLE: {
				SybaseBaseTable sybaseBaseTable = (SybaseBaseTable)theEObject;
				Object result = caseSybaseBaseTable(sybaseBaseTable);
				if (result == null) result = caseBaseTable(sybaseBaseTable);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseBaseTable);
				if (result == null) result = caseTable(sybaseBaseTable);
				if (result == null) result = caseSQLObject(sybaseBaseTable);
				if (result == null) result = caseENamedElement(sybaseBaseTable);
				if (result == null) result = caseEModelElement(sybaseBaseTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybasesqlmodelPackage.SYBASE_VIEW_TABLE: {
				SybaseViewTable sybaseViewTable = (SybaseViewTable)theEObject;
				Object result = caseSybaseViewTable(sybaseViewTable);
				if (result == null) result = caseViewTable(sybaseViewTable);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseViewTable);
				if (result == null) result = caseDerivedTable(sybaseViewTable);
				if (result == null) result = caseSQLObject(sybaseViewTable);
				if (result == null) result = caseTable(sybaseViewTable);
				if (result == null) result = caseENamedElement(sybaseViewTable);
				if (result == null) result = caseEModelElement(sybaseViewTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER: {
				SybaseAuthorizationIdentifier sybaseAuthorizationIdentifier = (SybaseAuthorizationIdentifier)theEObject;
				Object result = caseSybaseAuthorizationIdentifier(sybaseAuthorizationIdentifier);
				if (result == null) result = caseAuthorizationIdentifier(sybaseAuthorizationIdentifier);
				if (result == null) result = caseSQLObject(sybaseAuthorizationIdentifier);
				if (result == null) result = caseENamedElement(sybaseAuthorizationIdentifier);
				if (result == null) result = caseEModelElement(sybaseAuthorizationIdentifier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybasesqlmodelPackage.SYBASE_INDEX_MEMBER: {
				SybaseIndexMember sybaseIndexMember = (SybaseIndexMember)theEObject;
				Object result = caseSybaseIndexMember(sybaseIndexMember);
				if (result == null) result = caseIndexMember(sybaseIndexMember);
				if (result == null) result = caseSQLObject(sybaseIndexMember);
				if (result == null) result = caseENamedElement(sybaseIndexMember);
				if (result == null) result = caseEModelElement(sybaseIndexMember);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybasesqlmodelPackage.SYBASE_AUTHORIZED_OBJECT: {
				SybaseAuthorizedObject sybaseAuthorizedObject = (SybaseAuthorizedObject)theEObject;
				Object result = caseSybaseAuthorizedObject(sybaseAuthorizedObject);
				if (result == null) result = caseSQLObject(sybaseAuthorizedObject);
				if (result == null) result = caseENamedElement(sybaseAuthorizedObject);
				if (result == null) result = caseEModelElement(sybaseAuthorizedObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybasesqlmodelPackage.SYBASE_PRIVILEGE: {
				SybasePrivilege sybasePrivilege = (SybasePrivilege)theEObject;
				Object result = caseSybasePrivilege(sybasePrivilege);
				if (result == null) result = casePrivilege(sybasePrivilege);
				if (result == null) result = caseSQLObject(sybasePrivilege);
				if (result == null) result = caseENamedElement(sybasePrivilege);
				if (result == null) result = caseEModelElement(sybasePrivilege);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseParameter(SybaseParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase Routine</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase Routine</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseRoutine(SybaseRoutine object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase Base Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase Base Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseBaseTable(SybaseBaseTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase View Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase View Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseViewTable(SybaseViewTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase Authorization Identifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase Authorization Identifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseAuthorizationIdentifier(SybaseAuthorizationIdentifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase Index Member</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase Index Member</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseIndexMember(SybaseIndexMember object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase Authorized Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase Authorized Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseAuthorizedObject(SybaseAuthorizedObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase Privilege</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase Privilege</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybasePrivilege(SybasePrivilege object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Authorization Identifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Authorization Identifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAuthorizationIdentifier(AuthorizationIdentifier object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Privilege</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Privilege</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePrivilege(Privilege object) {
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

} //SybasesqlmodelSwitch
