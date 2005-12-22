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
package org.eclipse.datatools.modelbase.sql.routines.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.impl.TypedElementImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl#isLocator <em>Locator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl#getRoutine <em>Routine</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterImpl extends TypedElementImpl implements Parameter {
	/**
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final ParameterMode MODE_EDEFAULT = ParameterMode.IN_LITERAL;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected ParameterMode mode = MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #isLocator() <em>Locator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocator()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOCATOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLocator() <em>Locator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocator()
	 * @generated
	 * @ordered
	 */
	protected boolean locator = LOCATOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLRoutinesPackage.eINSTANCE.getParameter();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterMode getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMode(ParameterMode newMode) {
		ParameterMode oldMode = mode;
		mode = newMode == null ? MODE_EDEFAULT : newMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.PARAMETER__MODE, oldMode, mode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLocator() {
		return locator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocator(boolean newLocator) {
		boolean oldLocator = locator;
		locator = newLocator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.PARAMETER__LOCATOR, oldLocator, locator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Routine getRoutine() {
		if (eContainerFeatureID != SQLRoutinesPackage.PARAMETER__ROUTINE) return null;
		return (Routine)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoutine(Routine newRoutine) {
		if (newRoutine != eContainer || (eContainerFeatureID != SQLRoutinesPackage.PARAMETER__ROUTINE && newRoutine != null)) {
			if (EcoreUtil.isAncestor(this, newRoutine))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRoutine != null)
				msgs = ((InternalEObject)newRoutine).eInverseAdd(this, SQLRoutinesPackage.ROUTINE__PARAMETERS, Routine.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newRoutine, SQLRoutinesPackage.PARAMETER__ROUTINE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.PARAMETER__ROUTINE, newRoutine, newRoutine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLRoutinesPackage.PARAMETER__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLRoutinesPackage.PARAMETER__ROUTINE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLRoutinesPackage.PARAMETER__ROUTINE, msgs);
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
				case SQLRoutinesPackage.PARAMETER__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.PARAMETER__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLRoutinesPackage.PARAMETER__CONTAINED_TYPE:
					return basicSetContainedType(null, msgs);
				case SQLRoutinesPackage.PARAMETER__ROUTINE:
					return eBasicSetContainer(null, SQLRoutinesPackage.PARAMETER__ROUTINE, msgs);
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
				case SQLRoutinesPackage.PARAMETER__ROUTINE:
					return eContainer.eInverseRemove(this, SQLRoutinesPackage.ROUTINE__PARAMETERS, Routine.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLRoutinesPackage.PARAMETER__EANNOTATIONS:
				return getEAnnotations();
			case SQLRoutinesPackage.PARAMETER__NAME:
				return getName();
			case SQLRoutinesPackage.PARAMETER__DEPENDENCIES:
				return getDependencies();
			case SQLRoutinesPackage.PARAMETER__DESCRIPTION:
				return getDescription();
			case SQLRoutinesPackage.PARAMETER__LABEL:
				return getLabel();
			case SQLRoutinesPackage.PARAMETER__CONTAINED_TYPE:
				return getContainedType();
			case SQLRoutinesPackage.PARAMETER__REFERENCED_TYPE:
				if (resolve) return getReferencedType();
				return basicGetReferencedType();
			case SQLRoutinesPackage.PARAMETER__MODE:
				return getMode();
			case SQLRoutinesPackage.PARAMETER__LOCATOR:
				return isLocator() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.PARAMETER__ROUTINE:
				return getRoutine();
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
			case SQLRoutinesPackage.PARAMETER__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.PARAMETER__NAME:
				setName((String)newValue);
				return;
			case SQLRoutinesPackage.PARAMETER__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLRoutinesPackage.PARAMETER__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLRoutinesPackage.PARAMETER__LABEL:
				setLabel((String)newValue);
				return;
			case SQLRoutinesPackage.PARAMETER__CONTAINED_TYPE:
				setContainedType((SQLDataType)newValue);
				return;
			case SQLRoutinesPackage.PARAMETER__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)newValue);
				return;
			case SQLRoutinesPackage.PARAMETER__MODE:
				setMode((ParameterMode)newValue);
				return;
			case SQLRoutinesPackage.PARAMETER__LOCATOR:
				setLocator(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.PARAMETER__ROUTINE:
				setRoutine((Routine)newValue);
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
			case SQLRoutinesPackage.PARAMETER__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLRoutinesPackage.PARAMETER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLRoutinesPackage.PARAMETER__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLRoutinesPackage.PARAMETER__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLRoutinesPackage.PARAMETER__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLRoutinesPackage.PARAMETER__CONTAINED_TYPE:
				setContainedType((SQLDataType)null);
				return;
			case SQLRoutinesPackage.PARAMETER__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)null);
				return;
			case SQLRoutinesPackage.PARAMETER__MODE:
				setMode(MODE_EDEFAULT);
				return;
			case SQLRoutinesPackage.PARAMETER__LOCATOR:
				setLocator(LOCATOR_EDEFAULT);
				return;
			case SQLRoutinesPackage.PARAMETER__ROUTINE:
				setRoutine((Routine)null);
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
			case SQLRoutinesPackage.PARAMETER__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLRoutinesPackage.PARAMETER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLRoutinesPackage.PARAMETER__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLRoutinesPackage.PARAMETER__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLRoutinesPackage.PARAMETER__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLRoutinesPackage.PARAMETER__CONTAINED_TYPE:
				return containedType != null;
			case SQLRoutinesPackage.PARAMETER__REFERENCED_TYPE:
				return referencedType != null;
			case SQLRoutinesPackage.PARAMETER__MODE:
				return mode != MODE_EDEFAULT;
			case SQLRoutinesPackage.PARAMETER__LOCATOR:
				return locator != LOCATOR_EDEFAULT;
			case SQLRoutinesPackage.PARAMETER__ROUTINE:
				return getRoutine() != null;
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
		result.append(" (mode: "); //$NON-NLS-1$
		result.append(mode);
		result.append(", locator: "); //$NON-NLS-1$
		result.append(locator);
		result.append(')');
		return result.toString();
	}

} //ParameterImpl
