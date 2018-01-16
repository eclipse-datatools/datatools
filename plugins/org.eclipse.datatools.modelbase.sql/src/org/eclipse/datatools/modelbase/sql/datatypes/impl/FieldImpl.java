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
		return SQLDataTypesPackage.Literals.FIELD;
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLDataTypesPackage.FIELD__SCOPE_CHECK:
				return getScopeCheck();
			case SQLDataTypesPackage.FIELD__SCOPE_CHECKED:
				return isScopeChecked() ? Boolean.TRUE : Boolean.FALSE;
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
			case SQLDataTypesPackage.FIELD__SCOPE_CHECK:
				setScopeCheck((ReferentialActionType)newValue);
				return;
			case SQLDataTypesPackage.FIELD__SCOPE_CHECKED:
				setScopeChecked(((Boolean)newValue).booleanValue());
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
			case SQLDataTypesPackage.FIELD__SCOPE_CHECK:
				setScopeCheck(SCOPE_CHECK_EDEFAULT);
				return;
			case SQLDataTypesPackage.FIELD__SCOPE_CHECKED:
				setScopeChecked(SCOPE_CHECKED_EDEFAULT);
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
			case SQLDataTypesPackage.FIELD__SCOPE_CHECK:
				return scopeCheck != SCOPE_CHECK_EDEFAULT;
			case SQLDataTypesPackage.FIELD__SCOPE_CHECKED:
				return scopeChecked != SCOPE_CHECKED_EDEFAULT;
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
		result.append(" (scopeCheck: "); //$NON-NLS-1$
		result.append(scopeCheck);
		result.append(", scopeChecked: "); //$NON-NLS-1$
		result.append(scopeChecked);
		result.append(')');
		return result.toString();
	}

} //FieldImpl
