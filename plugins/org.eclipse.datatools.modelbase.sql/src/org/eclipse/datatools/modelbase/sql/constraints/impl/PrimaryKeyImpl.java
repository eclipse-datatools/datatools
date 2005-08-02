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
package org.eclipse.datatools.modelbase.sql.constraints.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Primary Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class PrimaryKeyImpl extends UniqueConstraintImpl implements PrimaryKey {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrimaryKeyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLConstraintsPackage.eINSTANCE.getPrimaryKey();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLConstraintsPackage.PRIMARY_KEY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLConstraintsPackage.PRIMARY_KEY__BASE_TABLE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLConstraintsPackage.PRIMARY_KEY__BASE_TABLE, msgs);
				case SQLConstraintsPackage.PRIMARY_KEY__FOREIGN_KEY:
					return ((InternalEList)getForeignKey()).basicAdd(otherEnd, msgs);
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
				case SQLConstraintsPackage.PRIMARY_KEY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLConstraintsPackage.PRIMARY_KEY__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLConstraintsPackage.PRIMARY_KEY__BASE_TABLE:
					return eBasicSetContainer(null, SQLConstraintsPackage.PRIMARY_KEY__BASE_TABLE, msgs);
				case SQLConstraintsPackage.PRIMARY_KEY__FOREIGN_KEY:
					return ((InternalEList)getForeignKey()).basicRemove(otherEnd, msgs);
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
				case SQLConstraintsPackage.PRIMARY_KEY__BASE_TABLE:
					return ((InternalEObject)eContainer).eInverseRemove(this, SQLTablesPackage.BASE_TABLE__CONSTRAINTS, BaseTable.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLConstraintsPackage.PRIMARY_KEY__EANNOTATIONS:
				return getEAnnotations();
			case SQLConstraintsPackage.PRIMARY_KEY__NAME:
				return getName();
			case SQLConstraintsPackage.PRIMARY_KEY__DEPENDENCIES:
				return getDependencies();
			case SQLConstraintsPackage.PRIMARY_KEY__DESCRIPTION:
				return getDescription();
			case SQLConstraintsPackage.PRIMARY_KEY__LABEL:
				return getLabel();
			case SQLConstraintsPackage.PRIMARY_KEY__DEFERRABLE:
				return isDeferrable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.PRIMARY_KEY__INITIALLY_DEFERRED:
				return isInitiallyDeferred() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.PRIMARY_KEY__ENFORCED:
				return isEnforced() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.PRIMARY_KEY__BASE_TABLE:
				return getBaseTable();
			case SQLConstraintsPackage.PRIMARY_KEY__MEMBERS:
				return getMembers();
			case SQLConstraintsPackage.PRIMARY_KEY__FOREIGN_KEY:
				return getForeignKey();
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
			case SQLConstraintsPackage.PRIMARY_KEY__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__NAME:
				setName((String)newValue);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__LABEL:
				setLabel((String)newValue);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__DEFERRABLE:
				setDeferrable(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__INITIALLY_DEFERRED:
				setInitiallyDeferred(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__ENFORCED:
				setEnforced(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__BASE_TABLE:
				setBaseTable((BaseTable)newValue);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__MEMBERS:
				getMembers().clear();
				getMembers().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__FOREIGN_KEY:
				getForeignKey().clear();
				getForeignKey().addAll((Collection)newValue);
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
			case SQLConstraintsPackage.PRIMARY_KEY__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__DEFERRABLE:
				setDeferrable(DEFERRABLE_EDEFAULT);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__INITIALLY_DEFERRED:
				setInitiallyDeferred(INITIALLY_DEFERRED_EDEFAULT);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__ENFORCED:
				setEnforced(ENFORCED_EDEFAULT);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__BASE_TABLE:
				setBaseTable((BaseTable)null);
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__MEMBERS:
				getMembers().clear();
				return;
			case SQLConstraintsPackage.PRIMARY_KEY__FOREIGN_KEY:
				getForeignKey().clear();
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
			case SQLConstraintsPackage.PRIMARY_KEY__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLConstraintsPackage.PRIMARY_KEY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLConstraintsPackage.PRIMARY_KEY__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLConstraintsPackage.PRIMARY_KEY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLConstraintsPackage.PRIMARY_KEY__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLConstraintsPackage.PRIMARY_KEY__DEFERRABLE:
				return deferrable != DEFERRABLE_EDEFAULT;
			case SQLConstraintsPackage.PRIMARY_KEY__INITIALLY_DEFERRED:
				return initiallyDeferred != INITIALLY_DEFERRED_EDEFAULT;
			case SQLConstraintsPackage.PRIMARY_KEY__ENFORCED:
				return enforced != ENFORCED_EDEFAULT;
			case SQLConstraintsPackage.PRIMARY_KEY__BASE_TABLE:
				return getBaseTable() != null;
			case SQLConstraintsPackage.PRIMARY_KEY__MEMBERS:
				return members != null && !members.isEmpty();
			case SQLConstraintsPackage.PRIMARY_KEY__FOREIGN_KEY:
				return foreignKey != null && !foreignKey.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //PrimaryKeyImpl
