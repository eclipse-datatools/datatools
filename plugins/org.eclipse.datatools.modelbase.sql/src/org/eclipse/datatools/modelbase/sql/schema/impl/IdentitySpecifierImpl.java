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

import java.math.BigInteger;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.GenerateType;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Identity Specifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.IdentitySpecifierImpl#getGenerationType <em>Generation Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.IdentitySpecifierImpl#getStartValue <em>Start Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.IdentitySpecifierImpl#getIncrement <em>Increment</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.IdentitySpecifierImpl#getMinimum <em>Minimum</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.IdentitySpecifierImpl#getMaximum <em>Maximum</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.IdentitySpecifierImpl#isCycleOption <em>Cycle Option</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IdentitySpecifierImpl extends SQLObjectImpl implements IdentitySpecifier {
	/**
	 * The default value of the '{@link #getGenerationType() <em>Generation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenerationType()
	 * @generated
	 * @ordered
	 */
	protected static final GenerateType GENERATION_TYPE_EDEFAULT = GenerateType.DEFAULT_GENERATED_LITERAL;

	/**
	 * The cached value of the '{@link #getGenerationType() <em>Generation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenerationType()
	 * @generated
	 * @ordered
	 */
	protected GenerateType generationType = GENERATION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartValue() <em>Start Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartValue()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger START_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartValue() <em>Start Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartValue()
	 * @generated
	 * @ordered
	 */
	protected BigInteger startValue = START_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIncrement() <em>Increment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncrement()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger INCREMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIncrement() <em>Increment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncrement()
	 * @generated
	 * @ordered
	 */
	protected BigInteger increment = INCREMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinimum() <em>Minimum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimum()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MINIMUM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinimum() <em>Minimum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimum()
	 * @generated
	 * @ordered
	 */
	protected BigInteger minimum = MINIMUM_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximum() <em>Maximum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximum()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MAXIMUM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaximum() <em>Maximum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximum()
	 * @generated
	 * @ordered
	 */
	protected BigInteger maximum = MAXIMUM_EDEFAULT;

	/**
	 * The default value of the '{@link #isCycleOption() <em>Cycle Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCycleOption()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CYCLE_OPTION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCycleOption() <em>Cycle Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCycleOption()
	 * @generated
	 * @ordered
	 */
	protected boolean cycleOption = CYCLE_OPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdentitySpecifierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLSchemaPackage.Literals.IDENTITY_SPECIFIER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenerateType getGenerationType() {
		return generationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenerationType(GenerateType newGenerationType) {
		GenerateType oldGenerationType = generationType;
		generationType = newGenerationType == null ? GENERATION_TYPE_EDEFAULT : newGenerationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.IDENTITY_SPECIFIER__GENERATION_TYPE, oldGenerationType, generationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getStartValue() {
		return startValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartValue(BigInteger newStartValue) {
		BigInteger oldStartValue = startValue;
		startValue = newStartValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.IDENTITY_SPECIFIER__START_VALUE, oldStartValue, startValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getIncrement() {
		return increment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncrement(BigInteger newIncrement) {
		BigInteger oldIncrement = increment;
		increment = newIncrement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.IDENTITY_SPECIFIER__INCREMENT, oldIncrement, increment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getMinimum() {
		return minimum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinimum(BigInteger newMinimum) {
		BigInteger oldMinimum = minimum;
		minimum = newMinimum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.IDENTITY_SPECIFIER__MINIMUM, oldMinimum, minimum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getMaximum() {
		return maximum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximum(BigInteger newMaximum) {
		BigInteger oldMaximum = maximum;
		maximum = newMaximum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.IDENTITY_SPECIFIER__MAXIMUM, oldMaximum, maximum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCycleOption() {
		return cycleOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCycleOption(boolean newCycleOption) {
		boolean oldCycleOption = cycleOption;
		cycleOption = newCycleOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.IDENTITY_SPECIFIER__CYCLE_OPTION, oldCycleOption, cycleOption));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLSchemaPackage.IDENTITY_SPECIFIER__GENERATION_TYPE:
				return getGenerationType();
			case SQLSchemaPackage.IDENTITY_SPECIFIER__START_VALUE:
				return getStartValue();
			case SQLSchemaPackage.IDENTITY_SPECIFIER__INCREMENT:
				return getIncrement();
			case SQLSchemaPackage.IDENTITY_SPECIFIER__MINIMUM:
				return getMinimum();
			case SQLSchemaPackage.IDENTITY_SPECIFIER__MAXIMUM:
				return getMaximum();
			case SQLSchemaPackage.IDENTITY_SPECIFIER__CYCLE_OPTION:
				return isCycleOption() ? Boolean.TRUE : Boolean.FALSE;
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
			case SQLSchemaPackage.IDENTITY_SPECIFIER__GENERATION_TYPE:
				setGenerationType((GenerateType)newValue);
				return;
			case SQLSchemaPackage.IDENTITY_SPECIFIER__START_VALUE:
				setStartValue((BigInteger)newValue);
				return;
			case SQLSchemaPackage.IDENTITY_SPECIFIER__INCREMENT:
				setIncrement((BigInteger)newValue);
				return;
			case SQLSchemaPackage.IDENTITY_SPECIFIER__MINIMUM:
				setMinimum((BigInteger)newValue);
				return;
			case SQLSchemaPackage.IDENTITY_SPECIFIER__MAXIMUM:
				setMaximum((BigInteger)newValue);
				return;
			case SQLSchemaPackage.IDENTITY_SPECIFIER__CYCLE_OPTION:
				setCycleOption(((Boolean)newValue).booleanValue());
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
			case SQLSchemaPackage.IDENTITY_SPECIFIER__GENERATION_TYPE:
				setGenerationType(GENERATION_TYPE_EDEFAULT);
				return;
			case SQLSchemaPackage.IDENTITY_SPECIFIER__START_VALUE:
				setStartValue(START_VALUE_EDEFAULT);
				return;
			case SQLSchemaPackage.IDENTITY_SPECIFIER__INCREMENT:
				setIncrement(INCREMENT_EDEFAULT);
				return;
			case SQLSchemaPackage.IDENTITY_SPECIFIER__MINIMUM:
				setMinimum(MINIMUM_EDEFAULT);
				return;
			case SQLSchemaPackage.IDENTITY_SPECIFIER__MAXIMUM:
				setMaximum(MAXIMUM_EDEFAULT);
				return;
			case SQLSchemaPackage.IDENTITY_SPECIFIER__CYCLE_OPTION:
				setCycleOption(CYCLE_OPTION_EDEFAULT);
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
			case SQLSchemaPackage.IDENTITY_SPECIFIER__GENERATION_TYPE:
				return generationType != GENERATION_TYPE_EDEFAULT;
			case SQLSchemaPackage.IDENTITY_SPECIFIER__START_VALUE:
				return START_VALUE_EDEFAULT == null ? startValue != null : !START_VALUE_EDEFAULT.equals(startValue);
			case SQLSchemaPackage.IDENTITY_SPECIFIER__INCREMENT:
				return INCREMENT_EDEFAULT == null ? increment != null : !INCREMENT_EDEFAULT.equals(increment);
			case SQLSchemaPackage.IDENTITY_SPECIFIER__MINIMUM:
				return MINIMUM_EDEFAULT == null ? minimum != null : !MINIMUM_EDEFAULT.equals(minimum);
			case SQLSchemaPackage.IDENTITY_SPECIFIER__MAXIMUM:
				return MAXIMUM_EDEFAULT == null ? maximum != null : !MAXIMUM_EDEFAULT.equals(maximum);
			case SQLSchemaPackage.IDENTITY_SPECIFIER__CYCLE_OPTION:
				return cycleOption != CYCLE_OPTION_EDEFAULT;
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
		result.append(" (generationType: "); //$NON-NLS-1$
		result.append(generationType);
		result.append(", startValue: "); //$NON-NLS-1$
		result.append(startValue);
		result.append(", increment: "); //$NON-NLS-1$
		result.append(increment);
		result.append(", minimum: "); //$NON-NLS-1$
		result.append(minimum);
		result.append(", maximum: "); //$NON-NLS-1$
		result.append(maximum);
		result.append(", cycleOption: "); //$NON-NLS-1$
		result.append(cycleOption);
		result.append(')');
		return result.toString();
	}

} //IdentitySpecifierImpl
