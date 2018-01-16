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
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering;
import org.eclipse.datatools.modelbase.sql.routines.Method;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structured User Defined Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl#isInstantiable <em>Instantiable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl#getSuper <em>Super</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl#getSub <em>Sub</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl#getMethods <em>Methods</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StructuredUserDefinedTypeImpl extends UserDefinedTypeImpl implements StructuredUserDefinedType {
/**
	 * The default value of the '{@link #isInstantiable() <em>Instantiable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstantiable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INSTANTIABLE_EDEFAULT = true;

/**
	 * The cached value of the '{@link #isInstantiable() <em>Instantiable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstantiable()
	 * @generated
	 * @ordered
	 */
	protected boolean instantiable = INSTANTIABLE_EDEFAULT;

/**
	 * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINAL_EDEFAULT = false;

/**
	 * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected boolean final_ = FINAL_EDEFAULT;

/**
	 * The cached value of the '{@link #getSuper() <em>Super</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuper()
	 * @generated
	 * @ordered
	 */
	protected StructuredUserDefinedType super_;

/**
	 * The cached value of the '{@link #getSub() <em>Sub</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSub()
	 * @generated
	 * @ordered
	 */
	protected EList sub;

/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList attributes;

/**
	 * The cached value of the '{@link #getMethods() <em>Methods</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethods()
	 * @generated
	 * @ordered
	 */
	protected EList methods;

/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StructuredUserDefinedTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.Literals.STRUCTURED_USER_DEFINED_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInstantiable() {
		return instantiable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstantiable(boolean newInstantiable) {
		boolean oldInstantiable = instantiable;
		instantiable = newInstantiable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__INSTANTIABLE, oldInstantiable, instantiable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinal() {
		return final_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(boolean newFinal) {
		boolean oldFinal = final_;
		final_ = newFinal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__FINAL, oldFinal, final_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredUserDefinedType getSuper() {
		if (super_ != null && super_.eIsProxy()) {
			InternalEObject oldSuper = (InternalEObject)super_;
			super_ = (StructuredUserDefinedType)eResolveProxy(oldSuper);
			if (super_ != oldSuper) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER, oldSuper, super_));
			}
		}
		return super_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredUserDefinedType basicGetSuper() {
		return super_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuper(StructuredUserDefinedType newSuper, NotificationChain msgs) {
		StructuredUserDefinedType oldSuper = super_;
		super_ = newSuper;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER, oldSuper, newSuper);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuper(StructuredUserDefinedType newSuper) {
		if (newSuper != super_) {
			NotificationChain msgs = null;
			if (super_ != null)
				msgs = ((InternalEObject)super_).eInverseRemove(this, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUB, StructuredUserDefinedType.class, msgs);
			if (newSuper != null)
				msgs = ((InternalEObject)newSuper).eInverseAdd(this, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUB, StructuredUserDefinedType.class, msgs);
			msgs = basicSetSuper(newSuper, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER, newSuper, newSuper));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSub() {
		if (sub == null) {
			sub = new EObjectWithInverseResolvingEList(StructuredUserDefinedType.class, this, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUB, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER);
		}
		return sub;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList(AttributeDefinition.class, this, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMethods() {
		if (methods == null) {
			methods = new EObjectContainmentEList(Method.class, this, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__METHODS);
		}
		return methods;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER:
				if (super_ != null)
					msgs = ((InternalEObject)super_).eInverseRemove(this, SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUB, StructuredUserDefinedType.class, msgs);
				return basicSetSuper((StructuredUserDefinedType)otherEnd, msgs);
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUB:
				return ((InternalEList)getSub()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER:
				return basicSetSuper(null, msgs);
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUB:
				return ((InternalEList)getSub()).basicRemove(otherEnd, msgs);
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__ATTRIBUTES:
				return ((InternalEList)getAttributes()).basicRemove(otherEnd, msgs);
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__METHODS:
				return ((InternalEList)getMethods()).basicRemove(otherEnd, msgs);
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
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__INSTANTIABLE:
				return isInstantiable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__FINAL:
				return isFinal() ? Boolean.TRUE : Boolean.FALSE;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER:
				if (resolve) return getSuper();
				return basicGetSuper();
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUB:
				return getSub();
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__ATTRIBUTES:
				return getAttributes();
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__METHODS:
				return getMethods();
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
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__INSTANTIABLE:
				setInstantiable(((Boolean)newValue).booleanValue());
				return;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__FINAL:
				setFinal(((Boolean)newValue).booleanValue());
				return;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER:
				setSuper((StructuredUserDefinedType)newValue);
				return;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUB:
				getSub().clear();
				getSub().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__METHODS:
				getMethods().clear();
				getMethods().addAll((Collection)newValue);
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
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__INSTANTIABLE:
				setInstantiable(INSTANTIABLE_EDEFAULT);
				return;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__FINAL:
				setFinal(FINAL_EDEFAULT);
				return;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER:
				setSuper((StructuredUserDefinedType)null);
				return;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUB:
				getSub().clear();
				return;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__ATTRIBUTES:
				getAttributes().clear();
				return;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__METHODS:
				getMethods().clear();
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
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__INSTANTIABLE:
				return instantiable != INSTANTIABLE_EDEFAULT;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__FINAL:
				return final_ != FINAL_EDEFAULT;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUPER:
				return super_ != null;
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__SUB:
				return sub != null && !sub.isEmpty();
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE__METHODS:
				return methods != null && !methods.isEmpty();
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
		result.append(" (instantiable: "); //$NON-NLS-1$
		result.append(instantiable);
		result.append(", final: "); //$NON-NLS-1$
		result.append(final_);
		result.append(')');
		return result.toString();
	}

} //StructuredUserDefinedTypeImpl
