/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsElementImpl.java,v 1.2 2005/12/17 01:46:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
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
 * An implementation of the model object '<em><b>SQL Grouping Sets Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementImpl#getGroupingSets <em>Grouping Sets</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GroupingSetsElementImpl extends SQLQueryObjectImpl implements GroupingSetsElement {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected GroupingSetsElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getGroupingSetsElement();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GroupingSets getGroupingSets() {
		if (eContainerFeatureID != SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS) return null;
		return (GroupingSets)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGroupingSets(GroupingSets newGroupingSets) {
		if (newGroupingSets != eContainer || (eContainerFeatureID != SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS && newGroupingSets != null)) {
			if (EcoreUtil.isAncestor(this, newGroupingSets))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroupingSets != null)
				msgs = ((InternalEObject)newGroupingSets).eInverseAdd(this, SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST, GroupingSets.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newGroupingSets, SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS, newGroupingSets, newGroupingSets));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS, msgs);
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
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
					return eBasicSetContainer(null, SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS, msgs);
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
				case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST, GroupingSets.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__NAME:
				return getName();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__LABEL:
				return getLabel();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
				return getGroupingSets();
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
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
				setGroupingSets((GroupingSets)newValue);
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
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
				setGroupingSets((GroupingSets)null);
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
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS:
				return getGroupingSets() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLGroupingSetsElementImpl
