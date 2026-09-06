/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *
 *************************************************************************
 *
 * $Id: DataSetParametersImpl.java,v 1.2 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import java.util.Collection;

import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Set Parameters</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetParametersImpl#getParameterDefinitions <em>Parameter Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.DataSetParametersImpl#isDerivedMetaData <em>Derived Meta Data</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DataSetParametersImpl extends EObjectImpl implements DataSetParameters {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getParameterDefinitions() <em>Parameter Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<ParameterDefinition> parameterDefinitions;

	/**
	 * The default value of the '{@link #isDerivedMetaData() <em>Derived Meta Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDerivedMetaData()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DERIVED_META_DATA_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isDerivedMetaData() <em>Derived Meta Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDerivedMetaData()
	 * @generated
	 * @ordered
	 */
	protected boolean derivedMetaData = DERIVED_META_DATA_EDEFAULT;

	/**
	 * This is true if the Derived Meta Data attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean derivedMetaDataESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataSetParametersImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.DATA_SET_PARAMETERS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ParameterDefinition> getParameterDefinitions() {
		if (parameterDefinitions == null) {
			parameterDefinitions = new EObjectContainmentEList<>(ParameterDefinition.class, this,
					DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS);
		}
		return parameterDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDerivedMetaData() {
		return derivedMetaData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDerivedMetaData(boolean newDerivedMetaData) {
		boolean oldDerivedMetaData = derivedMetaData;
		derivedMetaData = newDerivedMetaData;
		boolean oldDerivedMetaDataESet = derivedMetaDataESet;
		derivedMetaDataESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA,
					oldDerivedMetaData, derivedMetaData, !oldDerivedMetaDataESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDerivedMetaData() {
		boolean oldDerivedMetaData = derivedMetaData;
		boolean oldDerivedMetaDataESet = derivedMetaDataESet;
		derivedMetaData = DERIVED_META_DATA_EDEFAULT;
		derivedMetaDataESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET,
					DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA, oldDerivedMetaData,
					DERIVED_META_DATA_EDEFAULT, oldDerivedMetaDataESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDerivedMetaData() {
		return derivedMetaDataESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS:
			return ((InternalEList<?>) getParameterDefinitions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS:
			return getParameterDefinitions();
		case DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA:
			return isDerivedMetaData();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS:
			getParameterDefinitions().clear();
			getParameterDefinitions().addAll((Collection<? extends ParameterDefinition>) newValue);
			return;
		case DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA:
			setDerivedMetaData((Boolean) newValue);
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
		case DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS:
			getParameterDefinitions().clear();
			return;
		case DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA:
			unsetDerivedMetaData();
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
		case DesignPackage.DATA_SET_PARAMETERS__PARAMETER_DEFINITIONS:
			return parameterDefinitions != null && !parameterDefinitions.isEmpty();
		case DesignPackage.DATA_SET_PARAMETERS__DERIVED_META_DATA:
			return isSetDerivedMetaData();
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
		result.append(" (derivedMetaData: "); //$NON-NLS-1$
		if (derivedMetaDataESet) {
			result.append(derivedMetaData);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(')');
		return result.toString();
	}

} //DataSetParametersImpl
