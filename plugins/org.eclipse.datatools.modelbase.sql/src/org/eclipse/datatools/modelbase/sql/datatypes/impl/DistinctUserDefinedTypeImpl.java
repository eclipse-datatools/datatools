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

import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Distinct User Defined Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DistinctUserDefinedTypeImpl#getPredefinedRepresentation <em>Predefined Representation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DistinctUserDefinedTypeImpl extends UserDefinedTypeImpl implements DistinctUserDefinedType {
	/**
	 * The cached value of the '{@link #getPredefinedRepresentation() <em>Predefined Representation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredefinedRepresentation()
	 * @generated
	 * @ordered
	 */
	protected PredefinedDataType predefinedRepresentation = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DistinctUserDefinedTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.eINSTANCE.getDistinctUserDefinedType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredefinedDataType getPredefinedRepresentation() {
		return predefinedRepresentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPredefinedRepresentation(PredefinedDataType newPredefinedRepresentation, NotificationChain msgs) {
		PredefinedDataType oldPredefinedRepresentation = predefinedRepresentation;
		predefinedRepresentation = newPredefinedRepresentation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION, oldPredefinedRepresentation, newPredefinedRepresentation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredefinedRepresentation(PredefinedDataType newPredefinedRepresentation) {
		if (newPredefinedRepresentation != predefinedRepresentation) {
			NotificationChain msgs = null;
			if (predefinedRepresentation != null)
				msgs = ((InternalEObject)predefinedRepresentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION, null, msgs);
			if (newPredefinedRepresentation != null)
				msgs = ((InternalEObject)newPredefinedRepresentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION, null, msgs);
			msgs = basicSetPredefinedRepresentation(newPredefinedRepresentation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION, newPredefinedRepresentation, newPredefinedRepresentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__SCHEMA:
					if (schema != null)
						msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES, Schema.class, msgs);
					return basicSetSchema((Schema)otherEnd, msgs);
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
				case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__SCHEMA:
					return basicSetSchema(null, msgs);
				case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__ORDERING:
					return basicSetOrdering(null, msgs);
				case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION:
					return basicSetPredefinedRepresentation(null, msgs);
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
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS:
				return getEAnnotations();
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__NAME:
				return getName();
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DEPENDENCIES:
				return getDependencies();
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DESCRIPTION:
				return getDescription();
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__LABEL:
				return getLabel();
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__ORDERING:
				return getOrdering();
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION:
				return getPredefinedRepresentation();
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
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__NAME:
				setName((String)newValue);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__ORDERING:
				setOrdering((UserDefinedTypeOrdering)newValue);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION:
				setPredefinedRepresentation((PredefinedDataType)newValue);
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
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__SCHEMA:
				setSchema((Schema)null);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__ORDERING:
				setOrdering((UserDefinedTypeOrdering)null);
				return;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION:
				setPredefinedRepresentation((PredefinedDataType)null);
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
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__SCHEMA:
				return schema != null;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__ORDERING:
				return ordering != null;
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION:
				return predefinedRepresentation != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //DistinctUserDefinedTypeImpl
