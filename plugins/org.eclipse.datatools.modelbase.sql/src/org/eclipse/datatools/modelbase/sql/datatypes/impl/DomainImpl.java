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

import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.Domain;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Domain</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DomainImpl#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DomainImpl#getDefaultValue <em>Default Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DomainImpl extends DistinctUserDefinedTypeImpl implements Domain {
	/**
	 * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraint()
	 * @generated
	 * @ordered
	 */
	protected EList constraint = null;

	/**
	 * The default value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected String defaultValue = DEFAULT_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DomainImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.eINSTANCE.getDomain();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getConstraint() {
		if (constraint == null) {
			constraint = new EObjectContainmentEList(CheckConstraint.class, this, SQLDataTypesPackage.DOMAIN__CONSTRAINT);
		}
		return constraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValue(String newDefaultValue) {
		String oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.DOMAIN__DEFAULT_VALUE, oldDefaultValue, defaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLDataTypesPackage.DOMAIN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLDataTypesPackage.DOMAIN__SCHEMA:
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
				case SQLDataTypesPackage.DOMAIN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLDataTypesPackage.DOMAIN__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLDataTypesPackage.DOMAIN__SCHEMA:
					return basicSetSchema(null, msgs);
				case SQLDataTypesPackage.DOMAIN__ORDERING:
					return basicSetOrdering(null, msgs);
				case SQLDataTypesPackage.DOMAIN__PREDEFINED_REPRESENTATION:
					return basicSetPredefinedRepresentation(null, msgs);
				case SQLDataTypesPackage.DOMAIN__CONSTRAINT:
					return ((InternalEList)getConstraint()).basicRemove(otherEnd, msgs);
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
			case SQLDataTypesPackage.DOMAIN__EANNOTATIONS:
				return getEAnnotations();
			case SQLDataTypesPackage.DOMAIN__NAME:
				return getName();
			case SQLDataTypesPackage.DOMAIN__DEPENDENCIES:
				return getDependencies();
			case SQLDataTypesPackage.DOMAIN__DESCRIPTION:
				return getDescription();
			case SQLDataTypesPackage.DOMAIN__LABEL:
				return getLabel();
			case SQLDataTypesPackage.DOMAIN__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLDataTypesPackage.DOMAIN__ORDERING:
				return getOrdering();
			case SQLDataTypesPackage.DOMAIN__PREDEFINED_REPRESENTATION:
				return getPredefinedRepresentation();
			case SQLDataTypesPackage.DOMAIN__CONSTRAINT:
				return getConstraint();
			case SQLDataTypesPackage.DOMAIN__DEFAULT_VALUE:
				return getDefaultValue();
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
			case SQLDataTypesPackage.DOMAIN__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.DOMAIN__NAME:
				setName((String)newValue);
				return;
			case SQLDataTypesPackage.DOMAIN__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.DOMAIN__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLDataTypesPackage.DOMAIN__LABEL:
				setLabel((String)newValue);
				return;
			case SQLDataTypesPackage.DOMAIN__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case SQLDataTypesPackage.DOMAIN__ORDERING:
				setOrdering((UserDefinedTypeOrdering)newValue);
				return;
			case SQLDataTypesPackage.DOMAIN__PREDEFINED_REPRESENTATION:
				setPredefinedRepresentation((PredefinedDataType)newValue);
				return;
			case SQLDataTypesPackage.DOMAIN__CONSTRAINT:
				getConstraint().clear();
				getConstraint().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.DOMAIN__DEFAULT_VALUE:
				setDefaultValue((String)newValue);
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
			case SQLDataTypesPackage.DOMAIN__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLDataTypesPackage.DOMAIN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLDataTypesPackage.DOMAIN__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLDataTypesPackage.DOMAIN__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLDataTypesPackage.DOMAIN__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLDataTypesPackage.DOMAIN__SCHEMA:
				setSchema((Schema)null);
				return;
			case SQLDataTypesPackage.DOMAIN__ORDERING:
				setOrdering((UserDefinedTypeOrdering)null);
				return;
			case SQLDataTypesPackage.DOMAIN__PREDEFINED_REPRESENTATION:
				setPredefinedRepresentation((PredefinedDataType)null);
				return;
			case SQLDataTypesPackage.DOMAIN__CONSTRAINT:
				getConstraint().clear();
				return;
			case SQLDataTypesPackage.DOMAIN__DEFAULT_VALUE:
				setDefaultValue(DEFAULT_VALUE_EDEFAULT);
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
			case SQLDataTypesPackage.DOMAIN__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLDataTypesPackage.DOMAIN__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLDataTypesPackage.DOMAIN__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLDataTypesPackage.DOMAIN__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLDataTypesPackage.DOMAIN__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLDataTypesPackage.DOMAIN__SCHEMA:
				return schema != null;
			case SQLDataTypesPackage.DOMAIN__ORDERING:
				return ordering != null;
			case SQLDataTypesPackage.DOMAIN__PREDEFINED_REPRESENTATION:
				return predefinedRepresentation != null;
			case SQLDataTypesPackage.DOMAIN__CONSTRAINT:
				return constraint != null && !constraint.isEmpty();
			case SQLDataTypesPackage.DOMAIN__DEFAULT_VALUE:
				return DEFAULT_VALUE_EDEFAULT == null ? defaultValue != null : !DEFAULT_VALUE_EDEFAULT.equals(defaultValue);
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
		result.append(" (defaultValue: "); //$NON-NLS-1$
		result.append(defaultValue);
		result.append(')');
		return result.toString();
	}

} //DomainImpl
