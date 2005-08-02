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

import org.eclipse.datatools.modelbase.sql.datatypes.ExactNumericDataType;
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
 * An implementation of the model object '<em><b>Exact Numeric Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ExactNumericDataTypeImpl#getScale <em>Scale</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ExactNumericDataTypeImpl extends NumericalDataTypeImpl implements ExactNumericDataType {
	/**
	 * The default value of the '{@link #getScale() <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScale()
	 * @generated
	 * @ordered
	 */
	protected static final int SCALE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getScale() <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScale()
	 * @generated
	 * @ordered
	 */
	protected int scale = SCALE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExactNumericDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.eINSTANCE.getExactNumericDataType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getScale() {
		return scale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScale(int newScale) {
		int oldScale = scale;
		scale = newScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__SCALE, oldScale, scale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__EANNOTATIONS:
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
				case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__DEPENDENCIES:
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
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__EANNOTATIONS:
				return getEAnnotations();
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__NAME:
				return getName();
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__DEPENDENCIES:
				return getDependencies();
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__DESCRIPTION:
				return getDescription();
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__LABEL:
				return getLabel();
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__PRIMITIVE_TYPE:
				return getPrimitiveType();
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__PRECISION:
				return new Integer(getPrecision());
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__SCALE:
				return new Integer(getScale());
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
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__NAME:
				setName((String)newValue);
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__PRIMITIVE_TYPE:
				setPrimitiveType((PrimitiveType)newValue);
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__PRECISION:
				setPrecision(((Integer)newValue).intValue());
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__SCALE:
				setScale(((Integer)newValue).intValue());
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
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__PRIMITIVE_TYPE:
				setPrimitiveType(PRIMITIVE_TYPE_EDEFAULT);
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__PRECISION:
				setPrecision(PRECISION_EDEFAULT);
				return;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__SCALE:
				setScale(SCALE_EDEFAULT);
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
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__PRIMITIVE_TYPE:
				return primitiveType != PRIMITIVE_TYPE_EDEFAULT;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__PRECISION:
				return precision != PRECISION_EDEFAULT;
			case SQLDataTypesPackage.EXACT_NUMERIC_DATA_TYPE__SCALE:
				return scale != SCALE_EDEFAULT;
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
		result.append(" (scale: "); //$NON-NLS-1$
		result.append(scale);
		result.append(')');
		return result.toString();
	}

} //ExactNumericDataTypeImpl
