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

import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
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
 * An implementation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ConstraintImpl#isDeferrable <em>Deferrable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ConstraintImpl#isInitiallyDeferred <em>Initially Deferred</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ConstraintImpl#isEnforced <em>Enforced</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ConstraintImpl extends SQLObjectImpl implements Constraint {
	/**
	 * The default value of the '{@link #isDeferrable() <em>Deferrable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeferrable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEFERRABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDeferrable() <em>Deferrable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeferrable()
	 * @generated
	 * @ordered
	 */
	protected boolean deferrable = DEFERRABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isInitiallyDeferred() <em>Initially Deferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInitiallyDeferred()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INITIALLY_DEFERRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInitiallyDeferred() <em>Initially Deferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInitiallyDeferred()
	 * @generated
	 * @ordered
	 */
	protected boolean initiallyDeferred = INITIALLY_DEFERRED_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnforced() <em>Enforced</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnforced()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENFORCED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnforced() <em>Enforced</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnforced()
	 * @generated
	 * @ordered
	 */
	protected boolean enforced = ENFORCED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLConstraintsPackage.eINSTANCE.getConstraint();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeferrable() {
		return deferrable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeferrable(boolean newDeferrable) {
		boolean oldDeferrable = deferrable;
		deferrable = newDeferrable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.CONSTRAINT__DEFERRABLE, oldDeferrable, deferrable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInitiallyDeferred() {
		return initiallyDeferred;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitiallyDeferred(boolean newInitiallyDeferred) {
		boolean oldInitiallyDeferred = initiallyDeferred;
		initiallyDeferred = newInitiallyDeferred;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.CONSTRAINT__INITIALLY_DEFERRED, oldInitiallyDeferred, initiallyDeferred));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnforced() {
		return enforced;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnforced(boolean newEnforced) {
		boolean oldEnforced = enforced;
		enforced = newEnforced;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.CONSTRAINT__ENFORCED, oldEnforced, enforced));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLConstraintsPackage.CONSTRAINT__EANNOTATIONS:
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
				case SQLConstraintsPackage.CONSTRAINT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLConstraintsPackage.CONSTRAINT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
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
			case SQLConstraintsPackage.CONSTRAINT__EANNOTATIONS:
				return getEAnnotations();
			case SQLConstraintsPackage.CONSTRAINT__NAME:
				return getName();
			case SQLConstraintsPackage.CONSTRAINT__DEPENDENCIES:
				return getDependencies();
			case SQLConstraintsPackage.CONSTRAINT__DESCRIPTION:
				return getDescription();
			case SQLConstraintsPackage.CONSTRAINT__LABEL:
				return getLabel();
			case SQLConstraintsPackage.CONSTRAINT__DEFERRABLE:
				return isDeferrable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.CONSTRAINT__INITIALLY_DEFERRED:
				return isInitiallyDeferred() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.CONSTRAINT__ENFORCED:
				return isEnforced() ? Boolean.TRUE : Boolean.FALSE;
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
			case SQLConstraintsPackage.CONSTRAINT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.CONSTRAINT__NAME:
				setName((String)newValue);
				return;
			case SQLConstraintsPackage.CONSTRAINT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.CONSTRAINT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLConstraintsPackage.CONSTRAINT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLConstraintsPackage.CONSTRAINT__DEFERRABLE:
				setDeferrable(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.CONSTRAINT__INITIALLY_DEFERRED:
				setInitiallyDeferred(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.CONSTRAINT__ENFORCED:
				setEnforced(((Boolean)newValue).booleanValue());
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
			case SQLConstraintsPackage.CONSTRAINT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLConstraintsPackage.CONSTRAINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLConstraintsPackage.CONSTRAINT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLConstraintsPackage.CONSTRAINT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLConstraintsPackage.CONSTRAINT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLConstraintsPackage.CONSTRAINT__DEFERRABLE:
				setDeferrable(DEFERRABLE_EDEFAULT);
				return;
			case SQLConstraintsPackage.CONSTRAINT__INITIALLY_DEFERRED:
				setInitiallyDeferred(INITIALLY_DEFERRED_EDEFAULT);
				return;
			case SQLConstraintsPackage.CONSTRAINT__ENFORCED:
				setEnforced(ENFORCED_EDEFAULT);
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
			case SQLConstraintsPackage.CONSTRAINT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLConstraintsPackage.CONSTRAINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLConstraintsPackage.CONSTRAINT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLConstraintsPackage.CONSTRAINT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLConstraintsPackage.CONSTRAINT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLConstraintsPackage.CONSTRAINT__DEFERRABLE:
				return deferrable != DEFERRABLE_EDEFAULT;
			case SQLConstraintsPackage.CONSTRAINT__INITIALLY_DEFERRED:
				return initiallyDeferred != INITIALLY_DEFERRED_EDEFAULT;
			case SQLConstraintsPackage.CONSTRAINT__ENFORCED:
				return enforced != ENFORCED_EDEFAULT;
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
		result.append(" (deferrable: "); //$NON-NLS-1$
		result.append(deferrable);
		result.append(", initiallyDeferred: "); //$NON-NLS-1$
		result.append(initiallyDeferred);
		result.append(", enforced: "); //$NON-NLS-1$
		result.append(enforced);
		result.append(')');
		return result.toString();
	}

} //ConstraintImpl
