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

import org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ReferenceDataTypeImpl#getScopeTable <em>Scope Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ReferenceDataTypeImpl#getReferencedType <em>Referenced Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ReferenceDataTypeImpl extends ConstructedDataTypeImpl implements ReferenceDataType {
	/**
	 * The cached value of the '{@link #getScopeTable() <em>Scope Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeTable()
	 * @generated
	 * @ordered
	 */
	protected Table scopeTable;

	/**
	 * The cached value of the '{@link #getReferencedType() <em>Referenced Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedType()
	 * @generated
	 * @ordered
	 */
	protected StructuredUserDefinedType referencedType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.Literals.REFERENCE_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getScopeTable() {
		if (scopeTable != null && scopeTable.eIsProxy()) {
			InternalEObject oldScopeTable = (InternalEObject)scopeTable;
			scopeTable = (Table)eResolveProxy(oldScopeTable);
			if (scopeTable != oldScopeTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLDataTypesPackage.REFERENCE_DATA_TYPE__SCOPE_TABLE, oldScopeTable, scopeTable));
			}
		}
		return scopeTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table basicGetScopeTable() {
		return scopeTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScopeTable(Table newScopeTable) {
		Table oldScopeTable = scopeTable;
		scopeTable = newScopeTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.REFERENCE_DATA_TYPE__SCOPE_TABLE, oldScopeTable, scopeTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredUserDefinedType getReferencedType() {
		if (referencedType != null && referencedType.eIsProxy()) {
			InternalEObject oldReferencedType = (InternalEObject)referencedType;
			referencedType = (StructuredUserDefinedType)eResolveProxy(oldReferencedType);
			if (referencedType != oldReferencedType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLDataTypesPackage.REFERENCE_DATA_TYPE__REFERENCED_TYPE, oldReferencedType, referencedType));
			}
		}
		return referencedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredUserDefinedType basicGetReferencedType() {
		return referencedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedType(StructuredUserDefinedType newReferencedType) {
		StructuredUserDefinedType oldReferencedType = referencedType;
		referencedType = newReferencedType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.REFERENCE_DATA_TYPE__REFERENCED_TYPE, oldReferencedType, referencedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLDataTypesPackage.REFERENCE_DATA_TYPE__SCOPE_TABLE:
				if (resolve) return getScopeTable();
				return basicGetScopeTable();
			case SQLDataTypesPackage.REFERENCE_DATA_TYPE__REFERENCED_TYPE:
				if (resolve) return getReferencedType();
				return basicGetReferencedType();
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
			case SQLDataTypesPackage.REFERENCE_DATA_TYPE__SCOPE_TABLE:
				setScopeTable((Table)newValue);
				return;
			case SQLDataTypesPackage.REFERENCE_DATA_TYPE__REFERENCED_TYPE:
				setReferencedType((StructuredUserDefinedType)newValue);
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
			case SQLDataTypesPackage.REFERENCE_DATA_TYPE__SCOPE_TABLE:
				setScopeTable((Table)null);
				return;
			case SQLDataTypesPackage.REFERENCE_DATA_TYPE__REFERENCED_TYPE:
				setReferencedType((StructuredUserDefinedType)null);
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
			case SQLDataTypesPackage.REFERENCE_DATA_TYPE__SCOPE_TABLE:
				return scopeTable != null;
			case SQLDataTypesPackage.REFERENCE_DATA_TYPE__REFERENCED_TYPE:
				return referencedType != null;
		}
		return super.eIsSet(featureID);
	}

} //ReferenceDataTypeImpl
