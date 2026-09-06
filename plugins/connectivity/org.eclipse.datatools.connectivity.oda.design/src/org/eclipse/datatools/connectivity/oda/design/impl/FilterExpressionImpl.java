/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *
 *************************************************************************
 *
 * $Id: FilterExpressionImpl.java,v 1.1 2009/01/30 00:23:57 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.FilterExpression;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filter Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.FilterExpressionImpl#isNegatable <em>Negatable</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class FilterExpressionImpl extends EObjectImpl implements FilterExpression {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #isNegatable() <em>Negatable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNegatable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEGATABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNegatable() <em>Negatable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNegatable()
	 * @generated
	 * @ordered
	 */
	protected boolean negatable = NEGATABLE_EDEFAULT;

	/**
	 * This is true if the Negatable attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean negatableESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FilterExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.FILTER_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isNegatable() {
		return negatable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNegatable(boolean newNegatable) {
		boolean oldNegatable = negatable;
		negatable = newNegatable;
		boolean oldNegatableESet = negatableESet;
		negatableESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.FILTER_EXPRESSION__NEGATABLE,
					oldNegatable, negatable, !oldNegatableESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetNegatable() {
		boolean oldNegatable = negatable;
		boolean oldNegatableESet = negatableESet;
		negatable = NEGATABLE_EDEFAULT;
		negatableESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.FILTER_EXPRESSION__NEGATABLE,
					oldNegatable, NEGATABLE_EDEFAULT, oldNegatableESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetNegatable() {
		return negatableESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DesignPackage.FILTER_EXPRESSION__NEGATABLE:
			return isNegatable();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case DesignPackage.FILTER_EXPRESSION__NEGATABLE:
			setNegatable((Boolean) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case DesignPackage.FILTER_EXPRESSION__NEGATABLE:
			unsetNegatable();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case DesignPackage.FILTER_EXPRESSION__NEGATABLE:
			return isSetNegatable();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (negatable: "); //$NON-NLS-1$
		if (negatableESet) {
			result.append(negatable);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(')');
		return result.toString();
	}

} //FilterExpressionImpl
