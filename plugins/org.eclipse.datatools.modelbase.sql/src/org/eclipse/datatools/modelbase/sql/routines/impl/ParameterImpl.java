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

import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
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
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl#getStringTypeOption <em>String Type Option</em>}</li>
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
	 * The cached value of the '{@link #getStringTypeOption() <em>String Type Option</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringTypeOption()
	 * @generated
	 * @ordered
	 */
	protected CharacterStringDataType stringTypeOption;

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
		return SQLRoutinesPackage.Literals.PARAMETER;
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
		return (Routine)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoutine(Routine newRoutine, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRoutine, SQLRoutinesPackage.PARAMETER__ROUTINE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoutine(Routine newRoutine) {
		if (newRoutine != eInternalContainer() || (eContainerFeatureID != SQLRoutinesPackage.PARAMETER__ROUTINE && newRoutine != null)) {
			if (EcoreUtil.isAncestor(this, newRoutine))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRoutine != null)
				msgs = ((InternalEObject)newRoutine).eInverseAdd(this, SQLRoutinesPackage.ROUTINE__PARAMETERS, Routine.class, msgs);
			msgs = basicSetRoutine(newRoutine, msgs);
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
	public CharacterStringDataType getStringTypeOption() {
		return stringTypeOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStringTypeOption(CharacterStringDataType newStringTypeOption, NotificationChain msgs) {
		CharacterStringDataType oldStringTypeOption = stringTypeOption;
		stringTypeOption = newStringTypeOption;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION, oldStringTypeOption, newStringTypeOption);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStringTypeOption(CharacterStringDataType newStringTypeOption) {
		if (newStringTypeOption != stringTypeOption) {
			NotificationChain msgs = null;
			if (stringTypeOption != null)
				msgs = ((InternalEObject)stringTypeOption).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION, null, msgs);
			if (newStringTypeOption != null)
				msgs = ((InternalEObject)newStringTypeOption).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION, null, msgs);
			msgs = basicSetStringTypeOption(newStringTypeOption, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION, newStringTypeOption, newStringTypeOption));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLRoutinesPackage.PARAMETER__ROUTINE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRoutine((Routine)otherEnd, msgs);
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
			case SQLRoutinesPackage.PARAMETER__ROUTINE:
				return basicSetRoutine(null, msgs);
			case SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION:
				return basicSetStringTypeOption(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case SQLRoutinesPackage.PARAMETER__ROUTINE:
				return eInternalContainer().eInverseRemove(this, SQLRoutinesPackage.ROUTINE__PARAMETERS, Routine.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLRoutinesPackage.PARAMETER__MODE:
				return getMode();
			case SQLRoutinesPackage.PARAMETER__LOCATOR:
				return isLocator() ? Boolean.TRUE : Boolean.FALSE;
			case SQLRoutinesPackage.PARAMETER__ROUTINE:
				return getRoutine();
			case SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION:
				return getStringTypeOption();
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
			case SQLRoutinesPackage.PARAMETER__MODE:
				setMode((ParameterMode)newValue);
				return;
			case SQLRoutinesPackage.PARAMETER__LOCATOR:
				setLocator(((Boolean)newValue).booleanValue());
				return;
			case SQLRoutinesPackage.PARAMETER__ROUTINE:
				setRoutine((Routine)newValue);
				return;
			case SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION:
				setStringTypeOption((CharacterStringDataType)newValue);
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
			case SQLRoutinesPackage.PARAMETER__MODE:
				setMode(MODE_EDEFAULT);
				return;
			case SQLRoutinesPackage.PARAMETER__LOCATOR:
				setLocator(LOCATOR_EDEFAULT);
				return;
			case SQLRoutinesPackage.PARAMETER__ROUTINE:
				setRoutine((Routine)null);
				return;
			case SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION:
				setStringTypeOption((CharacterStringDataType)null);
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
			case SQLRoutinesPackage.PARAMETER__MODE:
				return mode != MODE_EDEFAULT;
			case SQLRoutinesPackage.PARAMETER__LOCATOR:
				return locator != LOCATOR_EDEFAULT;
			case SQLRoutinesPackage.PARAMETER__ROUTINE:
				return getRoutine() != null;
			case SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION:
				return stringTypeOption != null;
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
		result.append(" (mode: "); //$NON-NLS-1$
		result.append(mode);
		result.append(", locator: "); //$NON-NLS-1$
		result.append(locator);
		result.append(')');
		return result.toString();
	}

} //ParameterImpl
