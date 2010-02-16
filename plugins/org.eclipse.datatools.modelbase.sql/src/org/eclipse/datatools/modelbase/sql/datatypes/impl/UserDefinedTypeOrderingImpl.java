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

import org.eclipse.datatools.modelbase.sql.datatypes.OrderingCategoryType;
import org.eclipse.datatools.modelbase.sql.datatypes.OrderingType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Defined Type Ordering</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeOrderingImpl#getOrderingForm <em>Ordering Form</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeOrderingImpl#getOrderingCategory <em>Ordering Category</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeOrderingImpl#getOrderingRoutine <em>Ordering Routine</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserDefinedTypeOrderingImpl extends SQLObjectImpl implements UserDefinedTypeOrdering {
	/**
	 * The default value of the '{@link #getOrderingForm() <em>Ordering Form</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderingForm()
	 * @generated
	 * @ordered
	 */
	protected static final OrderingType ORDERING_FORM_EDEFAULT = OrderingType.EQUALS_LITERAL;

	/**
	 * The cached value of the '{@link #getOrderingForm() <em>Ordering Form</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderingForm()
	 * @generated
	 * @ordered
	 */
	protected OrderingType orderingForm = ORDERING_FORM_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrderingCategory() <em>Ordering Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderingCategory()
	 * @generated
	 * @ordered
	 */
	protected static final OrderingCategoryType ORDERING_CATEGORY_EDEFAULT = OrderingCategoryType.RELATIVE_LITERAL;

	/**
	 * The cached value of the '{@link #getOrderingCategory() <em>Ordering Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderingCategory()
	 * @generated
	 * @ordered
	 */
	protected OrderingCategoryType orderingCategory = ORDERING_CATEGORY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOrderingRoutine() <em>Ordering Routine</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderingRoutine()
	 * @generated
	 * @ordered
	 */
	protected Routine orderingRoutine;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserDefinedTypeOrderingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.Literals.USER_DEFINED_TYPE_ORDERING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderingType getOrderingForm() {
		return orderingForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderingForm(OrderingType newOrderingForm) {
		OrderingType oldOrderingForm = orderingForm;
		orderingForm = newOrderingForm == null ? ORDERING_FORM_EDEFAULT : newOrderingForm;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_FORM, oldOrderingForm, orderingForm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderingCategoryType getOrderingCategory() {
		return orderingCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderingCategory(OrderingCategoryType newOrderingCategory) {
		OrderingCategoryType oldOrderingCategory = orderingCategory;
		orderingCategory = newOrderingCategory == null ? ORDERING_CATEGORY_EDEFAULT : newOrderingCategory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_CATEGORY, oldOrderingCategory, orderingCategory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Routine getOrderingRoutine() {
		if (orderingRoutine != null && orderingRoutine.eIsProxy()) {
			InternalEObject oldOrderingRoutine = (InternalEObject)orderingRoutine;
			orderingRoutine = (Routine)eResolveProxy(oldOrderingRoutine);
			if (orderingRoutine != oldOrderingRoutine) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_ROUTINE, oldOrderingRoutine, orderingRoutine));
			}
		}
		return orderingRoutine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Routine basicGetOrderingRoutine() {
		return orderingRoutine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderingRoutine(Routine newOrderingRoutine) {
		Routine oldOrderingRoutine = orderingRoutine;
		orderingRoutine = newOrderingRoutine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_ROUTINE, oldOrderingRoutine, orderingRoutine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_FORM:
				return getOrderingForm();
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_CATEGORY:
				return getOrderingCategory();
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_ROUTINE:
				if (resolve) return getOrderingRoutine();
				return basicGetOrderingRoutine();
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
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_FORM:
				setOrderingForm((OrderingType)newValue);
				return;
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_CATEGORY:
				setOrderingCategory((OrderingCategoryType)newValue);
				return;
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_ROUTINE:
				setOrderingRoutine((Routine)newValue);
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
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_FORM:
				setOrderingForm(ORDERING_FORM_EDEFAULT);
				return;
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_CATEGORY:
				setOrderingCategory(ORDERING_CATEGORY_EDEFAULT);
				return;
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_ROUTINE:
				setOrderingRoutine((Routine)null);
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
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_FORM:
				return orderingForm != ORDERING_FORM_EDEFAULT;
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_CATEGORY:
				return orderingCategory != ORDERING_CATEGORY_EDEFAULT;
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING__ORDERING_ROUTINE:
				return orderingRoutine != null;
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
		result.append(" (orderingForm: "); //$NON-NLS-1$
		result.append(orderingForm);
		result.append(", orderingCategory: "); //$NON-NLS-1$
		result.append(orderingCategory);
		result.append(')');
		return result.toString();
	}

} //UserDefinedTypeOrderingImpl
