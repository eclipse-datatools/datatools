/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLSyntaxDefinitionImpl.java,v 1.3 2005/06/15 18:16:00 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

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
	protected EList keywords = null;

	/**
	 * The cached value of the '{@link #getOperators() <em>Operators</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperators()
	 * @generated
	 * @ordered
	 */
	protected EList operators = null;

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
		return DatabaseDefinitionPackage.eINSTANCE.getSQLSyntaxDefinition();
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__KEYWORDS:
				return getKeywords();
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__OPERATORS:
				return getOperators();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__KEYWORDS:
				getKeywords().clear();
				getKeywords().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__OPERATORS:
				getOperators().clear();
				getOperators().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__KEYWORDS:
				getKeywords().clear();
				return;
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__OPERATORS:
				getOperators().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__KEYWORDS:
				return keywords != null && !keywords.isEmpty();
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION__OPERATORS:
				return operators != null && !operators.isEmpty();
		}
		return eDynamicIsSet(eFeature);
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
		result.append(')');
		return result.toString();
	}

} //SQLSyntaxDefinitionImpl
