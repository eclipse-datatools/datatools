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
package org.eclipse.datatools.modelbase.sql.schema.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Typed Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.TypedElementImpl#getContainedType <em>Contained Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.TypedElementImpl#getReferencedType <em>Referenced Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TypedElementImpl extends SQLObjectImpl implements TypedElement {
	/**
	 * The cached value of the '{@link #getContainedType() <em>Contained Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainedType()
	 * @generated
	 * @ordered
	 */
	protected SQLDataType containedType;

	/**
	 * The cached value of the '{@link #getReferencedType() <em>Referenced Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedType()
	 * @generated
	 * @ordered
	 */
	protected UserDefinedType referencedType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypedElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLSchemaPackage.Literals.TYPED_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLDataType getContainedType() {
		return containedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainedType(SQLDataType newContainedType, NotificationChain msgs) {
		SQLDataType oldContainedType = containedType;
		containedType = newContainedType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE, oldContainedType, newContainedType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainedType(SQLDataType newContainedType) {
		if (newContainedType != containedType) {
			NotificationChain msgs = null;
			if (containedType != null)
				msgs = ((InternalEObject)containedType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE, null, msgs);
			if (newContainedType != null)
				msgs = ((InternalEObject)newContainedType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE, null, msgs);
			msgs = basicSetContainedType(newContainedType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE, newContainedType, newContainedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserDefinedType getReferencedType() {
		if (referencedType != null && referencedType.eIsProxy()) {
			InternalEObject oldReferencedType = (InternalEObject)referencedType;
			referencedType = (UserDefinedType)eResolveProxy(oldReferencedType);
			if (referencedType != oldReferencedType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE, oldReferencedType, referencedType));
			}
		}
		return referencedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserDefinedType basicGetReferencedType() {
		return referencedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedType(UserDefinedType newReferencedType) {
		UserDefinedType oldReferencedType = referencedType;
		referencedType = newReferencedType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE, oldReferencedType, referencedType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void setDataType(DataType newType) {
		if (newType == null) {
			this.setReferencedType(null);
			this.setContainedType(null);
		}
		else if (newType instanceof SQLDataType) {
			if (this.referencedType != null) {
				this.setReferencedType(null);
			}
			this.setContainedType((SQLDataType)newType);
		}
		else if (newType instanceof UserDefinedType) {
			if (this.containedType != null) {
				this.setContainedType(null);
			}
			this.setReferencedType((UserDefinedType)newType);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public DataType getDataType() {
		if (this.containedType != null)
			return this.getContainedType();
		else if (this.referencedType != null)
			return this.getReferencedType();
		else
			return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE:
				return basicSetContainedType(null, msgs);
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
			case SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE:
				return getContainedType();
			case SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE:
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
			case SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE:
				setContainedType((SQLDataType)newValue);
				return;
			case SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)newValue);
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
			case SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE:
				setContainedType((SQLDataType)null);
				return;
			case SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)null);
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
			case SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE:
				return containedType != null;
			case SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE:
				return referencedType != null;
		}
		return super.eIsSet(featureID);
	}

} //TypedElementImpl
