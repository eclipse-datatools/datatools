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

import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Time Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.TimeDataTypeImpl#getFractionalSecondsPrecision <em>Fractional Seconds Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.TimeDataTypeImpl#isTimeZone <em>Time Zone</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimeDataTypeImpl extends PredefinedDataTypeImpl implements TimeDataType {
	/**
	 * The default value of the '{@link #getFractionalSecondsPrecision() <em>Fractional Seconds Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFractionalSecondsPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final int FRACTIONAL_SECONDS_PRECISION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFractionalSecondsPrecision() <em>Fractional Seconds Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFractionalSecondsPrecision()
	 * @generated
	 * @ordered
	 */
	protected int fractionalSecondsPrecision = FRACTIONAL_SECONDS_PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #isTimeZone() <em>Time Zone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTimeZone()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TIME_ZONE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTimeZone() <em>Time Zone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTimeZone()
	 * @generated
	 * @ordered
	 */
	protected boolean timeZone = TIME_ZONE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimeDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.Literals.TIME_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFractionalSecondsPrecision() {
		return fractionalSecondsPrecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFractionalSecondsPrecision(int newFractionalSecondsPrecision) {
		int oldFractionalSecondsPrecision = fractionalSecondsPrecision;
		fractionalSecondsPrecision = newFractionalSecondsPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.TIME_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION, oldFractionalSecondsPrecision, fractionalSecondsPrecision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTimeZone() {
		return timeZone;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeZone(boolean newTimeZone) {
		boolean oldTimeZone = timeZone;
		timeZone = newTimeZone;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.TIME_DATA_TYPE__TIME_ZONE, oldTimeZone, timeZone));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLDataTypesPackage.TIME_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION:
				return new Integer(getFractionalSecondsPrecision());
			case SQLDataTypesPackage.TIME_DATA_TYPE__TIME_ZONE:
				return isTimeZone() ? Boolean.TRUE : Boolean.FALSE;
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
			case SQLDataTypesPackage.TIME_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION:
				setFractionalSecondsPrecision(((Integer)newValue).intValue());
				return;
			case SQLDataTypesPackage.TIME_DATA_TYPE__TIME_ZONE:
				setTimeZone(((Boolean)newValue).booleanValue());
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
			case SQLDataTypesPackage.TIME_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION:
				setFractionalSecondsPrecision(FRACTIONAL_SECONDS_PRECISION_EDEFAULT);
				return;
			case SQLDataTypesPackage.TIME_DATA_TYPE__TIME_ZONE:
				setTimeZone(TIME_ZONE_EDEFAULT);
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
			case SQLDataTypesPackage.TIME_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION:
				return fractionalSecondsPrecision != FRACTIONAL_SECONDS_PRECISION_EDEFAULT;
			case SQLDataTypesPackage.TIME_DATA_TYPE__TIME_ZONE:
				return timeZone != TIME_ZONE_EDEFAULT;
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
		result.append(" (fractionalSecondsPrecision: "); //$NON-NLS-1$
		result.append(fractionalSecondsPrecision);
		result.append(", timeZone: "); //$NON-NLS-1$
		result.append(timeZone);
		result.append(')');
		return result.toString();
	}

} //TimeDataTypeImpl
