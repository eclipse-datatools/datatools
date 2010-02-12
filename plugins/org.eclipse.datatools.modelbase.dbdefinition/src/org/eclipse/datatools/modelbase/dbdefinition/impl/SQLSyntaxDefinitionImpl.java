/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLSyntaxDefinitionImpl.java,v 1.3 2006/10/11 20:34:54 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Syntax Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SQLSyntaxDefinitionImpl#getKeywords <em>Keywords</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SQLSyntaxDefinitionImpl#getOperators <em>Operators</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SQLSyntaxDefinitionImpl#getTerminationCharacter <em>Termination Character</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SQLSyntaxDefinitionImpl extends EObjectImpl implements SQLSyntaxDefinition {
	/**
	 * The cached value of the '{@link #getKeywords() <em>Keywords</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeywords()
	 * @generated
	 * @ordered
	 */
	protected EList keywords;

	/**
	 * The cached value of the '{@link #getOperators() <em>Operators</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperators()
	 * @generated
	 * @ordered
	 */
	protected EList operators;

	/**
	 * The default value of the '{@link #getTerminationCharacter() <em>Termination Character</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminationCharacter()
	 * @generated
	 * @ordered
	 */
	protected static final String TERMINATION_CHARACTER_EDEFAULT = ";"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getTerminationCharacter() <em>Termination Character</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminationCharacter()
	 * @generated
	 * @ordered
	 */
	protected String terminationCharacter = TERMINATION_CHARACTER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SQLSyntaxDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.SQL_SYNTAX_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getKeywords() {
		if (keywords == null) {
			keywords = new EDataTypeUniqueEList(String.class, this, DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__KEYWORDS);
		}
		return keywords;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOperators() {
		if (operators == null) {
			operators = new EDataTypeUniqueEList(String.class, this, DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__OPERATORS);
		}
		return operators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTerminationCharacter() {
		return terminationCharacter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTerminationCharacter(String newTerminationCharacter) {
		String oldTerminationCharacter = terminationCharacter;
		terminationCharacter = newTerminationCharacter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__TERMINATION_CHARACTER, oldTerminationCharacter, terminationCharacter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__KEYWORDS:
				return getKeywords();
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__OPERATORS:
				return getOperators();
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__TERMINATION_CHARACTER:
				return getTerminationCharacter();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__KEYWORDS:
				getKeywords().clear();
				getKeywords().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__OPERATORS:
				getOperators().clear();
				getOperators().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__TERMINATION_CHARACTER:
				setTerminationCharacter((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__KEYWORDS:
				getKeywords().clear();
				return;
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__OPERATORS:
				getOperators().clear();
				return;
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__TERMINATION_CHARACTER:
				setTerminationCharacter(TERMINATION_CHARACTER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__KEYWORDS:
				return keywords != null && !keywords.isEmpty();
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__OPERATORS:
				return operators != null && !operators.isEmpty();
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__TERMINATION_CHARACTER:
				return TERMINATION_CHARACTER_EDEFAULT == null ? terminationCharacter != null : !TERMINATION_CHARACTER_EDEFAULT.equals(terminationCharacter);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (keywords: "); //$NON-NLS-1$
		result.append(keywords);
		result.append(", operators: "); //$NON-NLS-1$
		result.append(operators);
		result.append(", terminationCharacter: "); //$NON-NLS-1$
		result.append(terminationCharacter);
		result.append(')');
		return result.toString();
	}

} //SQLSyntaxDefinitionImpl
