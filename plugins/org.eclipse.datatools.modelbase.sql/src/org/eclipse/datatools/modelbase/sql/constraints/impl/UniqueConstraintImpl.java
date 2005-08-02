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

import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unique Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.UniqueConstraintImpl#getForeignKey <em>Foreign Key</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UniqueConstraintImpl extends ReferenceConstraintImpl implements UniqueConstraint {
	/**
	 * The cached value of the '{@link #getForeignKey() <em>Foreign Key</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeignKey()
	 * @generated
	 * @ordered
	 */
	protected EList foreignKey = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UniqueConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLConstraintsPackage.eINSTANCE.getUniqueConstraint();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getForeignKey() {
		if (foreignKey == null) {
			foreignKey = new EObjectWithInverseResolvingEList(ForeignKey.class, this, SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY, SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT);
		}
		return foreignKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLConstraintsPackage.UNIQUE_CONSTRAINT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLConstraintsPackage.UNIQUE_CONSTRAINT__BASE_TABLE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLConstraintsPackage.UNIQUE_CONSTRAINT__BASE_TABLE, msgs);
				case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
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
				case SQLConstraintsPackage.UNIQUE_CONSTRAINT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLConstraintsPackage.UNIQUE_CONSTRAINT__BASE_TABLE:
					return eBasicSetContainer(null, SQLConstraintsPackage.UNIQUE_CONSTRAINT__BASE_TABLE, msgs);
				case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
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
				case SQLConstraintsPackage.UNIQUE_CONSTRAINT__BASE_TABLE:
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
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__EANNOTATIONS:
				return getEAnnotations();
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__NAME:
				return getName();
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEPENDENCIES:
				return getDependencies();
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DESCRIPTION:
				return getDescription();
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__LABEL:
				return getLabel();
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEFERRABLE:
				return isDeferrable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__INITIALLY_DEFERRED:
				return isInitiallyDeferred() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__ENFORCED:
				return isEnforced() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__BASE_TABLE:
				return getBaseTable();
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__MEMBERS:
				return getMembers();
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
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
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__NAME:
				setName((String)newValue);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEFERRABLE:
				setDeferrable(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__INITIALLY_DEFERRED:
				setInitiallyDeferred(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__ENFORCED:
				setEnforced(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__BASE_TABLE:
				setBaseTable((BaseTable)newValue);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__MEMBERS:
				getMembers().clear();
				getMembers().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
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
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEFERRABLE:
				setDeferrable(DEFERRABLE_EDEFAULT);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__INITIALLY_DEFERRED:
				setInitiallyDeferred(INITIALLY_DEFERRED_EDEFAULT);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__ENFORCED:
				setEnforced(ENFORCED_EDEFAULT);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__BASE_TABLE:
				setBaseTable((BaseTable)null);
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__MEMBERS:
				getMembers().clear();
				return;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
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
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEFERRABLE:
				return deferrable != DEFERRABLE_EDEFAULT;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__INITIALLY_DEFERRED:
				return initiallyDeferred != INITIALLY_DEFERRED_EDEFAULT;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__ENFORCED:
				return enforced != ENFORCED_EDEFAULT;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__BASE_TABLE:
				return getBaseTable() != null;
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__MEMBERS:
				return members != null && !members.isEmpty();
			case SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY:
				return foreignKey != null && !foreignKey.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //UniqueConstraintImpl
