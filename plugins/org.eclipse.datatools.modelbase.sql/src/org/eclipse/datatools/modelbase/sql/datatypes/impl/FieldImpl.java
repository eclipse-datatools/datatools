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

import org.eclipse.datatools.modelbase.sql.datatypes.Field;
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
 * An implementation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.FieldImpl#getScopeCheck <em>Scope Check</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.FieldImpl#isScopeChecked <em>Scope Checked</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FieldImpl extends TypedElementImpl implements Field {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.eINSTANCE.getField();
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
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.FIELD__SCOPE_CHECK, oldScopeCheck, scopeCheck));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.FIELD__SCOPE_CHECKED, oldScopeChecked, scopeChecked));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLDataTypesPackage.FIELD__EANNOTATIONS:
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
				case SQLDataTypesPackage.FIELD__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLDataTypesPackage.FIELD__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLDataTypesPackage.FIELD__CONTAINED_TYPE:
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
			case SQLDataTypesPackage.FIELD__EANNOTATIONS:
				return getEAnnotations();
			case SQLDataTypesPackage.FIELD__NAME:
				return getName();
			case SQLDataTypesPackage.FIELD__DEPENDENCIES:
				return getDependencies();
			case SQLDataTypesPackage.FIELD__DESCRIPTION:
				return getDescription();
			case SQLDataTypesPackage.FIELD__LABEL:
				return getLabel();
			case SQLDataTypesPackage.FIELD__CONTAINED_TYPE:
				return getContainedType();
			case SQLDataTypesPackage.FIELD__REFERENCED_TYPE:
				if (resolve) return getReferencedType();
				return basicGetReferencedType();
			case SQLDataTypesPackage.FIELD__SCOPE_CHECK:
				return getScopeCheck();
			case SQLDataTypesPackage.FIELD__SCOPE_CHECKED:
				return isScopeChecked() ? Boolean.TRUE : Boolean.FALSE;
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
			case SQLDataTypesPackage.FIELD__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.FIELD__NAME:
				setName((String)newValue);
				return;
			case SQLDataTypesPackage.FIELD__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.FIELD__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLDataTypesPackage.FIELD__LABEL:
				setLabel((String)newValue);
				return;
			case SQLDataTypesPackage.FIELD__CONTAINED_TYPE:
				setContainedType((SQLDataType)newValue);
				return;
			case SQLDataTypesPackage.FIELD__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)newValue);
				return;
			case SQLDataTypesPackage.FIELD__SCOPE_CHECK:
				setScopeCheck((ReferentialActionType)newValue);
				return;
			case SQLDataTypesPackage.FIELD__SCOPE_CHECKED:
				setScopeChecked(((Boolean)newValue).booleanValue());
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
			case SQLDataTypesPackage.FIELD__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLDataTypesPackage.FIELD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLDataTypesPackage.FIELD__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLDataTypesPackage.FIELD__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLDataTypesPackage.FIELD__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLDataTypesPackage.FIELD__CONTAINED_TYPE:
				setContainedType((SQLDataType)null);
				return;
			case SQLDataTypesPackage.FIELD__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)null);
				return;
			case SQLDataTypesPackage.FIELD__SCOPE_CHECK:
				setScopeCheck(SCOPE_CHECK_EDEFAULT);
				return;
			case SQLDataTypesPackage.FIELD__SCOPE_CHECKED:
				setScopeChecked(SCOPE_CHECKED_EDEFAULT);
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
			case SQLDataTypesPackage.FIELD__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLDataTypesPackage.FIELD__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLDataTypesPackage.FIELD__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLDataTypesPackage.FIELD__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLDataTypesPackage.FIELD__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLDataTypesPackage.FIELD__CONTAINED_TYPE:
				return containedType != null;
			case SQLDataTypesPackage.FIELD__REFERENCED_TYPE:
				return referencedType != null;
			case SQLDataTypesPackage.FIELD__SCOPE_CHECK:
				return scopeCheck != SCOPE_CHECK_EDEFAULT;
			case SQLDataTypesPackage.FIELD__SCOPE_CHECKED:
				return scopeChecked != SCOPE_CHECKED_EDEFAULT;
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
		result.append(')');
		return result.toString();
	}

} //FieldImpl
