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
	protected SearchCondition searchCondition;

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
		return SQLConstraintsPackage.Literals.CHECK_CONSTRAINT;
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION:
				return basicSetSearchCondition(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION:
				return getSearchCondition();
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
			case SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION:
				setSearchCondition((SearchCondition)newValue);
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
			case SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION:
				setSearchCondition((SearchCondition)null);
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
			case SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION:
				return searchCondition != null;
		}
		return super.eIsSet(featureID);
	}

} //CheckConstraintImpl
