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

import org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interval Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.IntervalDataTypeImpl#getLeadingQualifier <em>Leading Qualifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.IntervalDataTypeImpl#getTrailingQualifier <em>Trailing Qualifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.IntervalDataTypeImpl#getLeadingFieldPrecision <em>Leading Field Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.IntervalDataTypeImpl#getTrailingFieldPrecision <em>Trailing Field Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.IntervalDataTypeImpl#getFractionalSecondsPrecision <em>Fractional Seconds Precision</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntervalDataTypeImpl extends PredefinedDataTypeImpl implements IntervalDataType {
	/**
	 * The default value of the '{@link #getLeadingQualifier() <em>Leading Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeadingQualifier()
	 * @generated
	 * @ordered
	 */
	protected static final IntervalQualifierType LEADING_QUALIFIER_EDEFAULT = IntervalQualifierType.YEAR_LITERAL;

	/**
	 * The cached value of the '{@link #getLeadingQualifier() <em>Leading Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeadingQualifier()
	 * @generated
	 * @ordered
	 */
	protected IntervalQualifierType leadingQualifier = LEADING_QUALIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getTrailingQualifier() <em>Trailing Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrailingQualifier()
	 * @generated
	 * @ordered
	 */
	protected static final IntervalQualifierType TRAILING_QUALIFIER_EDEFAULT = IntervalQualifierType.YEAR_LITERAL;

	/**
	 * The cached value of the '{@link #getTrailingQualifier() <em>Trailing Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrailingQualifier()
	 * @generated
	 * @ordered
	 */
	protected IntervalQualifierType trailingQualifier = TRAILING_QUALIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getLeadingFieldPrecision() <em>Leading Field Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeadingFieldPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final int LEADING_FIELD_PRECISION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLeadingFieldPrecision() <em>Leading Field Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeadingFieldPrecision()
	 * @generated
	 * @ordered
	 */
	protected int leadingFieldPrecision = LEADING_FIELD_PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTrailingFieldPrecision() <em>Trailing Field Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrailingFieldPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final int TRAILING_FIELD_PRECISION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTrailingFieldPrecision() <em>Trailing Field Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrailingFieldPrecision()
	 * @generated
	 * @ordered
	 */
	protected int trailingFieldPrecision = TRAILING_FIELD_PRECISION_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntervalDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.Literals.INTERVAL_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntervalQualifierType getLeadingQualifier() {
		return leadingQualifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeadingQualifier(IntervalQualifierType newLeadingQualifier) {
		IntervalQualifierType oldLeadingQualifier = leadingQualifier;
		leadingQualifier = newLeadingQualifier == null ? LEADING_QUALIFIER_EDEFAULT : newLeadingQualifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.INTERVAL_DATA_TYPE__LEADING_QUALIFIER, oldLeadingQualifier, leadingQualifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntervalQualifierType getTrailingQualifier() {
		return trailingQualifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrailingQualifier(IntervalQualifierType newTrailingQualifier) {
		IntervalQualifierType oldTrailingQualifier = trailingQualifier;
		trailingQualifier = newTrailingQualifier == null ? TRAILING_QUALIFIER_EDEFAULT : newTrailingQualifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.INTERVAL_DATA_TYPE__TRAILING_QUALIFIER, oldTrailingQualifier, trailingQualifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLeadingFieldPrecision() {
		return leadingFieldPrecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeadingFieldPrecision(int newLeadingFieldPrecision) {
		int oldLeadingFieldPrecision = leadingFieldPrecision;
		leadingFieldPrecision = newLeadingFieldPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.INTERVAL_DATA_TYPE__LEADING_FIELD_PRECISION, oldLeadingFieldPrecision, leadingFieldPrecision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTrailingFieldPrecision() {
		return trailingFieldPrecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrailingFieldPrecision(int newTrailingFieldPrecision) {
		int oldTrailingFieldPrecision = trailingFieldPrecision;
		trailingFieldPrecision = newTrailingFieldPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.INTERVAL_DATA_TYPE__TRAILING_FIELD_PRECISION, oldTrailingFieldPrecision, trailingFieldPrecision));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.INTERVAL_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION, oldFractionalSecondsPrecision, fractionalSecondsPrecision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__LEADING_QUALIFIER:
				return getLeadingQualifier();
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__TRAILING_QUALIFIER:
				return getTrailingQualifier();
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__LEADING_FIELD_PRECISION:
				return new Integer(getLeadingFieldPrecision());
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__TRAILING_FIELD_PRECISION:
				return new Integer(getTrailingFieldPrecision());
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION:
				return new Integer(getFractionalSecondsPrecision());
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
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__LEADING_QUALIFIER:
				setLeadingQualifier((IntervalQualifierType)newValue);
				return;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__TRAILING_QUALIFIER:
				setTrailingQualifier((IntervalQualifierType)newValue);
				return;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__LEADING_FIELD_PRECISION:
				setLeadingFieldPrecision(((Integer)newValue).intValue());
				return;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__TRAILING_FIELD_PRECISION:
				setTrailingFieldPrecision(((Integer)newValue).intValue());
				return;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION:
				setFractionalSecondsPrecision(((Integer)newValue).intValue());
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
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__LEADING_QUALIFIER:
				setLeadingQualifier(LEADING_QUALIFIER_EDEFAULT);
				return;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__TRAILING_QUALIFIER:
				setTrailingQualifier(TRAILING_QUALIFIER_EDEFAULT);
				return;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__LEADING_FIELD_PRECISION:
				setLeadingFieldPrecision(LEADING_FIELD_PRECISION_EDEFAULT);
				return;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__TRAILING_FIELD_PRECISION:
				setTrailingFieldPrecision(TRAILING_FIELD_PRECISION_EDEFAULT);
				return;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION:
				setFractionalSecondsPrecision(FRACTIONAL_SECONDS_PRECISION_EDEFAULT);
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
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__LEADING_QUALIFIER:
				return leadingQualifier != LEADING_QUALIFIER_EDEFAULT;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__TRAILING_QUALIFIER:
				return trailingQualifier != TRAILING_QUALIFIER_EDEFAULT;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__LEADING_FIELD_PRECISION:
				return leadingFieldPrecision != LEADING_FIELD_PRECISION_EDEFAULT;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__TRAILING_FIELD_PRECISION:
				return trailingFieldPrecision != TRAILING_FIELD_PRECISION_EDEFAULT;
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION:
				return fractionalSecondsPrecision != FRACTIONAL_SECONDS_PRECISION_EDEFAULT;
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
		result.append(" (leadingQualifier: "); //$NON-NLS-1$
		result.append(leadingQualifier);
		result.append(", trailingQualifier: "); //$NON-NLS-1$
		result.append(trailingQualifier);
		result.append(", leadingFieldPrecision: "); //$NON-NLS-1$
		result.append(leadingFieldPrecision);
		result.append(", trailingFieldPrecision: "); //$NON-NLS-1$
		result.append(trailingFieldPrecision);
		result.append(", fractionalSecondsPrecision: "); //$NON-NLS-1$
		result.append(fractionalSecondsPrecision);
		result.append(')');
		return result.toString();
	}

} //IntervalDataTypeImpl
