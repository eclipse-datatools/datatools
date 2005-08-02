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
package org.eclipse.datatools.modelbase.sql.datatypes.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.impl.TypedElementImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.AttributeDefinitionImpl#getScopeCheck <em>Scope Check</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.AttributeDefinitionImpl#isScopeChecked <em>Scope Checked</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.AttributeDefinitionImpl#getDefaultValue <em>Default Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeDefinitionImpl extends TypedElementImpl implements AttributeDefinition {
	/**
	 * The default value of the '{@link #getScopeCheck() <em>Scope Check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeCheck()
	 * @generated
	 * @ordered
	 */
	protected static final ReferentialActionType SCOPE_CHECK_EDEFAULT = ReferentialActionType.NO_ACTION_LITERAL;

	/**
	 * The cached value of the '{@link #getScopeCheck() <em>Scope Check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeCheck()
	 * @generated
	 * @ordered
	 */
	protected ReferentialActionType scopeCheck = SCOPE_CHECK_EDEFAULT;

	/**
	 * The default value of the '{@link #isScopeChecked() <em>Scope Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScopeChecked()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SCOPE_CHECKED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isScopeChecked() <em>Scope Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScopeChecked()
	 * @generated
	 * @ordered
	 */
	protected boolean scopeChecked = SCOPE_CHECKED_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected String defaultValue = DEFAULT_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.eINSTANCE.getAttributeDefinition();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferentialActionType getScopeCheck() {
		return scopeCheck;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScopeCheck(ReferentialActionType newScopeCheck) {
		ReferentialActionType oldScopeCheck = scopeCheck;
		scopeCheck = newScopeCheck == null ? SCOPE_CHECK_EDEFAULT : newScopeCheck;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECK, oldScopeCheck, scopeCheck));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isScopeChecked() {
		return scopeChecked;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScopeChecked(boolean newScopeChecked) {
		boolean oldScopeChecked = scopeChecked;
		scopeChecked = newScopeChecked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECKED, oldScopeChecked, scopeChecked));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValue(String newDefaultValue) {
		String oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEFAULT_VALUE, oldDefaultValue, defaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__CONTAINED_TYPE:
					return basicSetContainedType(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__EANNOTATIONS:
				return getEAnnotations();
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__NAME:
				return getName();
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEPENDENCIES:
				return getDependencies();
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DESCRIPTION:
				return getDescription();
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__LABEL:
				return getLabel();
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__CONTAINED_TYPE:
				return getContainedType();
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__REFERENCED_TYPE:
				if (resolve) return getReferencedType();
				return basicGetReferencedType();
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECK:
				return getScopeCheck();
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECKED:
				return isScopeChecked() ? Boolean.TRUE : Boolean.FALSE;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEFAULT_VALUE:
				return getDefaultValue();
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
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__NAME:
				setName((String)newValue);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__CONTAINED_TYPE:
				setContainedType((SQLDataType)newValue);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)newValue);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECK:
				setScopeCheck((ReferentialActionType)newValue);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECKED:
				setScopeChecked(((Boolean)newValue).booleanValue());
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEFAULT_VALUE:
				setDefaultValue((String)newValue);
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
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__CONTAINED_TYPE:
				setContainedType((SQLDataType)null);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)null);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECK:
				setScopeCheck(SCOPE_CHECK_EDEFAULT);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECKED:
				setScopeChecked(SCOPE_CHECKED_EDEFAULT);
				return;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEFAULT_VALUE:
				setDefaultValue(DEFAULT_VALUE_EDEFAULT);
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
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__CONTAINED_TYPE:
				return containedType != null;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__REFERENCED_TYPE:
				return referencedType != null;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECK:
				return scopeCheck != SCOPE_CHECK_EDEFAULT;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECKED:
				return scopeChecked != SCOPE_CHECKED_EDEFAULT;
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEFAULT_VALUE:
				return DEFAULT_VALUE_EDEFAULT == null ? defaultValue != null : !DEFAULT_VALUE_EDEFAULT.equals(defaultValue);
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
		result.append(" (scopeCheck: "); //$NON-NLS-1$
		result.append(scopeCheck);
		result.append(", scopeChecked: "); //$NON-NLS-1$
		result.append(scopeChecked);
		result.append(", defaultValue: "); //$NON-NLS-1$
		result.append(defaultValue);
		result.append(')');
		return result.toString();
	}

} //AttributeDefinitionImpl
