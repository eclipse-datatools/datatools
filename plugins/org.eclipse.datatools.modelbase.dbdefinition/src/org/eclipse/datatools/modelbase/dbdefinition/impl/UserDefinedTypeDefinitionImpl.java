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
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Defined Type Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.UserDefinedTypeDefinitionImpl#isDefaultValueSupported <em>Default Value Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.UserDefinedTypeDefinitionImpl#isDistinctTypeSupported <em>Distinct Type Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.UserDefinedTypeDefinitionImpl#isStructuredTypeSupported <em>Structured Type Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserDefinedTypeDefinitionImpl extends EObjectImpl implements UserDefinedTypeDefinition {
	/**
	 * The default value of the '{@link #isDefaultValueSupported() <em>Default Value Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultValueSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEFAULT_VALUE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDefaultValueSupported() <em>Default Value Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultValueSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean defaultValueSupported = DEFAULT_VALUE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isDistinctTypeSupported() <em>Distinct Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDistinctTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISTINCT_TYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDistinctTypeSupported() <em>Distinct Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDistinctTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean distinctTypeSupported = DISTINCT_TYPE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isStructuredTypeSupported() <em>Structured Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStructuredTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STRUCTURED_TYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStructuredTypeSupported() <em>Structured Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStructuredTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean structuredTypeSupported = STRUCTURED_TYPE_SUPPORTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserDefinedTypeDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.eINSTANCE.getUserDefinedTypeDefinition();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDefaultValueSupported() {
		return defaultValueSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValueSupported(boolean newDefaultValueSupported) {
		boolean oldDefaultValueSupported = defaultValueSupported;
		defaultValueSupported = newDefaultValueSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__DEFAULT_VALUE_SUPPORTED, oldDefaultValueSupported, defaultValueSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDistinctTypeSupported() {
		return distinctTypeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDistinctTypeSupported(boolean newDistinctTypeSupported) {
		boolean oldDistinctTypeSupported = distinctTypeSupported;
		distinctTypeSupported = newDistinctTypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__DISTINCT_TYPE_SUPPORTED, oldDistinctTypeSupported, distinctTypeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStructuredTypeSupported() {
		return structuredTypeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStructuredTypeSupported(boolean newStructuredTypeSupported) {
		boolean oldStructuredTypeSupported = structuredTypeSupported;
		structuredTypeSupported = newStructuredTypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__STRUCTURED_TYPE_SUPPORTED, oldStructuredTypeSupported, structuredTypeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__DEFAULT_VALUE_SUPPORTED:
				return isDefaultValueSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__DISTINCT_TYPE_SUPPORTED:
				return isDistinctTypeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__STRUCTURED_TYPE_SUPPORTED:
				return isStructuredTypeSupported() ? Boolean.TRUE : Boolean.FALSE;
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
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__DEFAULT_VALUE_SUPPORTED:
				setDefaultValueSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__DISTINCT_TYPE_SUPPORTED:
				setDistinctTypeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__STRUCTURED_TYPE_SUPPORTED:
				setStructuredTypeSupported(((Boolean)newValue).booleanValue());
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
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__DEFAULT_VALUE_SUPPORTED:
				setDefaultValueSupported(DEFAULT_VALUE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__DISTINCT_TYPE_SUPPORTED:
				setDistinctTypeSupported(DISTINCT_TYPE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__STRUCTURED_TYPE_SUPPORTED:
				setStructuredTypeSupported(STRUCTURED_TYPE_SUPPORTED_EDEFAULT);
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
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__DEFAULT_VALUE_SUPPORTED:
				return defaultValueSupported != DEFAULT_VALUE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__DISTINCT_TYPE_SUPPORTED:
				return distinctTypeSupported != DISTINCT_TYPE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION__STRUCTURED_TYPE_SUPPORTED:
				return structuredTypeSupported != STRUCTURED_TYPE_SUPPORTED_EDEFAULT;
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
		result.append(" (defaultValueSupported: "); //$NON-NLS-1$
		result.append(defaultValueSupported);
		result.append(", distinctTypeSupported: "); //$NON-NLS-1$
		result.append(distinctTypeSupported);
		result.append(", structuredTypeSupported: "); //$NON-NLS-1$
		result.append(structuredTypeSupported);
		result.append(')');
		return result.toString();
	}

} //UserDefinedTypeDefinitionImpl
