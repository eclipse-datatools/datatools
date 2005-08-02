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

import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.TableConstraintImpl#getBaseTable <em>Base Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TableConstraintImpl extends ConstraintImpl implements TableConstraint {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLConstraintsPackage.eINSTANCE.getTableConstraint();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseTable getBaseTable() {
		if (eContainerFeatureID != SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE) return null;
		return (BaseTable)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseTable(BaseTable newBaseTable) {
		if (newBaseTable != eContainer || (eContainerFeatureID != SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE && newBaseTable != null)) {
			if (EcoreUtil.isAncestor(this, newBaseTable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBaseTable != null)
				msgs = ((InternalEObject)newBaseTable).eInverseAdd(this, SQLTablesPackage.BASE_TABLE__CONSTRAINTS, BaseTable.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newBaseTable, SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE, newBaseTable, newBaseTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLConstraintsPackage.TABLE_CONSTRAINT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE, msgs);
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
				case SQLConstraintsPackage.TABLE_CONSTRAINT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLConstraintsPackage.TABLE_CONSTRAINT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE:
					return eBasicSetContainer(null, SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE, msgs);
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
				case SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE:
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
			case SQLConstraintsPackage.TABLE_CONSTRAINT__EANNOTATIONS:
				return getEAnnotations();
			case SQLConstraintsPackage.TABLE_CONSTRAINT__NAME:
				return getName();
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DEPENDENCIES:
				return getDependencies();
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DESCRIPTION:
				return getDescription();
			case SQLConstraintsPackage.TABLE_CONSTRAINT__LABEL:
				return getLabel();
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DEFERRABLE:
				return isDeferrable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__INITIALLY_DEFERRED:
				return isInitiallyDeferred() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__ENFORCED:
				return isEnforced() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE:
				return getBaseTable();
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
			case SQLConstraintsPackage.TABLE_CONSTRAINT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__NAME:
				setName((String)newValue);
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DEFERRABLE:
				setDeferrable(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__INITIALLY_DEFERRED:
				setInitiallyDeferred(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__ENFORCED:
				setEnforced(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE:
				setBaseTable((BaseTable)newValue);
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
			case SQLConstraintsPackage.TABLE_CONSTRAINT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DEFERRABLE:
				setDeferrable(DEFERRABLE_EDEFAULT);
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__INITIALLY_DEFERRED:
				setInitiallyDeferred(INITIALLY_DEFERRED_EDEFAULT);
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__ENFORCED:
				setEnforced(ENFORCED_EDEFAULT);
				return;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE:
				setBaseTable((BaseTable)null);
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
			case SQLConstraintsPackage.TABLE_CONSTRAINT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLConstraintsPackage.TABLE_CONSTRAINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLConstraintsPackage.TABLE_CONSTRAINT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLConstraintsPackage.TABLE_CONSTRAINT__DEFERRABLE:
				return deferrable != DEFERRABLE_EDEFAULT;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__INITIALLY_DEFERRED:
				return initiallyDeferred != INITIALLY_DEFERRED_EDEFAULT;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__ENFORCED:
				return enforced != ENFORCED_EDEFAULT;
			case SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE:
				return getBaseTable() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //TableConstraintImpl
