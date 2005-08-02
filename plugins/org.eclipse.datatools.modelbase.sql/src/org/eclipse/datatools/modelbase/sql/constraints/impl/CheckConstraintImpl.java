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
package org.eclipse.datatools.modelbase.sql.constraints.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Check Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.CheckConstraintImpl#getSearchCondition <em>Search Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CheckConstraintImpl extends TableConstraintImpl implements CheckConstraint {
	/**
	 * The cached value of the '{@link #getSearchCondition() <em>Search Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSearchCondition()
	 * @generated
	 * @ordered
	 */
	protected SearchCondition searchCondition = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CheckConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLConstraintsPackage.eINSTANCE.getCheckConstraint();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchCondition getSearchCondition() {
		return searchCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSearchCondition(SearchCondition newSearchCondition, NotificationChain msgs) {
		SearchCondition oldSearchCondition = searchCondition;
		searchCondition = newSearchCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION, oldSearchCondition, newSearchCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSearchCondition(SearchCondition newSearchCondition) {
		if (newSearchCondition != searchCondition) {
			NotificationChain msgs = null;
			if (searchCondition != null)
				msgs = ((InternalEObject)searchCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION, null, msgs);
			if (newSearchCondition != null)
				msgs = ((InternalEObject)newSearchCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION, null, msgs);
			msgs = basicSetSearchCondition(newSearchCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION, newSearchCondition, newSearchCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLConstraintsPackage.CHECK_CONSTRAINT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLConstraintsPackage.CHECK_CONSTRAINT__BASE_TABLE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLConstraintsPackage.CHECK_CONSTRAINT__BASE_TABLE, msgs);
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
				case SQLConstraintsPackage.CHECK_CONSTRAINT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLConstraintsPackage.CHECK_CONSTRAINT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLConstraintsPackage.CHECK_CONSTRAINT__BASE_TABLE:
					return eBasicSetContainer(null, SQLConstraintsPackage.CHECK_CONSTRAINT__BASE_TABLE, msgs);
				case SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION:
					return basicSetSearchCondition(null, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case SQLConstraintsPackage.CHECK_CONSTRAINT__BASE_TABLE:
					return ((InternalEObject)eContainer).eInverseRemove(this, SQLTablesPackage.BASE_TABLE__CONSTRAINTS, BaseTable.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLConstraintsPackage.CHECK_CONSTRAINT__EANNOTATIONS:
				return getEAnnotations();
			case SQLConstraintsPackage.CHECK_CONSTRAINT__NAME:
				return getName();
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DEPENDENCIES:
				return getDependencies();
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DESCRIPTION:
				return getDescription();
			case SQLConstraintsPackage.CHECK_CONSTRAINT__LABEL:
				return getLabel();
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DEFERRABLE:
				return isDeferrable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__INITIALLY_DEFERRED:
				return isInitiallyDeferred() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__ENFORCED:
				return isEnforced() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__BASE_TABLE:
				return getBaseTable();
			case SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION:
				return getSearchCondition();
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
			case SQLConstraintsPackage.CHECK_CONSTRAINT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__NAME:
				setName((String)newValue);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DEFERRABLE:
				setDeferrable(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__INITIALLY_DEFERRED:
				setInitiallyDeferred(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__ENFORCED:
				setEnforced(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__BASE_TABLE:
				setBaseTable((BaseTable)newValue);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION:
				setSearchCondition((SearchCondition)newValue);
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
			case SQLConstraintsPackage.CHECK_CONSTRAINT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DEFERRABLE:
				setDeferrable(DEFERRABLE_EDEFAULT);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__INITIALLY_DEFERRED:
				setInitiallyDeferred(INITIALLY_DEFERRED_EDEFAULT);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__ENFORCED:
				setEnforced(ENFORCED_EDEFAULT);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__BASE_TABLE:
				setBaseTable((BaseTable)null);
				return;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION:
				setSearchCondition((SearchCondition)null);
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
			case SQLConstraintsPackage.CHECK_CONSTRAINT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLConstraintsPackage.CHECK_CONSTRAINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLConstraintsPackage.CHECK_CONSTRAINT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLConstraintsPackage.CHECK_CONSTRAINT__DEFERRABLE:
				return deferrable != DEFERRABLE_EDEFAULT;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__INITIALLY_DEFERRED:
				return initiallyDeferred != INITIALLY_DEFERRED_EDEFAULT;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__ENFORCED:
				return enforced != ENFORCED_EDEFAULT;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__BASE_TABLE:
				return getBaseTable() != null;
			case SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION:
				return searchCondition != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //CheckConstraintImpl
