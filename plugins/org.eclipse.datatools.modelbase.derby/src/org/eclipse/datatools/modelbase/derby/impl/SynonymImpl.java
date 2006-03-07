/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.derby.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.derby.DerbyModelPackage;
import org.eclipse.datatools.modelbase.derby.Synonym;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.datatools.modelbase.sql.tables.Table;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Synonym</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.derby.impl.SynonymImpl#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SynonymImpl extends SQLObjectImpl implements Synonym {
	/**
	 * The cached value of the '{@link #getTable() <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTable()
	 * @generated
	 * @ordered
	 */
	protected Table table = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SynonymImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DerbyModelPackage.eINSTANCE.getSynonym();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getTable() {
		if (table != null && table.eIsProxy()) {
			Table oldTable = table;
			table = (Table)eResolveProxy((InternalEObject)table);
			if (table != oldTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DerbyModelPackage.SYNONYM__TABLE, oldTable, table));
			}
		}
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table basicGetTable() {
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(Table newTable) {
		Table oldTable = table;
		table = newTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DerbyModelPackage.SYNONYM__TABLE, oldTable, table));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case DerbyModelPackage.SYNONYM__EANNOTATIONS:
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
				case DerbyModelPackage.SYNONYM__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__DEPENDENCIES:
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
			case DerbyModelPackage.SYNONYM__EANNOTATIONS:
				return getEAnnotations();
			case DerbyModelPackage.SYNONYM__NAME:
				return getName();
			case DerbyModelPackage.SYNONYM__DEPENDENCIES:
				return getDependencies();
			case DerbyModelPackage.SYNONYM__DESCRIPTION:
				return getDescription();
			case DerbyModelPackage.SYNONYM__LABEL:
				return getLabel();
			case DerbyModelPackage.SYNONYM__TABLE:
				if (resolve) return getTable();
				return basicGetTable();
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
			case DerbyModelPackage.SYNONYM__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case DerbyModelPackage.SYNONYM__NAME:
				setName((String)newValue);
				return;
			case DerbyModelPackage.SYNONYM__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case DerbyModelPackage.SYNONYM__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case DerbyModelPackage.SYNONYM__LABEL:
				setLabel((String)newValue);
				return;
			case DerbyModelPackage.SYNONYM__TABLE:
				setTable((Table)newValue);
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
			case DerbyModelPackage.SYNONYM__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case DerbyModelPackage.SYNONYM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DerbyModelPackage.SYNONYM__DEPENDENCIES:
				getDependencies().clear();
				return;
			case DerbyModelPackage.SYNONYM__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case DerbyModelPackage.SYNONYM__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case DerbyModelPackage.SYNONYM__TABLE:
				setTable((Table)null);
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
			case DerbyModelPackage.SYNONYM__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case DerbyModelPackage.SYNONYM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DerbyModelPackage.SYNONYM__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case DerbyModelPackage.SYNONYM__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case DerbyModelPackage.SYNONYM__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case DerbyModelPackage.SYNONYM__TABLE:
				return table != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SynonymImpl
