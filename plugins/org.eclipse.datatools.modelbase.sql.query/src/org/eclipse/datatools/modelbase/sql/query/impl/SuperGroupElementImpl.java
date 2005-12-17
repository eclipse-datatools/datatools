/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroupElementImpl.java,v 1.1 2005/12/16 13:11:12 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElement;
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
 * An implementation of the model object '<em><b>SQL Super Group Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementImpl#getSuperGroup <em>Super Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class SuperGroupElementImpl extends SQLQueryObjectImpl implements SuperGroupElement {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected SuperGroupElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getSuperGroupElement();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SuperGroup getSuperGroup() {
		if (eContainerFeatureID != SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP) return null;
		return (SuperGroup)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSuperGroup(SuperGroup newSuperGroup) {
		if (newSuperGroup != eContainer || (eContainerFeatureID != SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP && newSuperGroup != null)) {
			if (EcoreUtil.isAncestor(this, newSuperGroup))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSuperGroup != null)
				msgs = ((InternalEObject)newSuperGroup).eInverseAdd(this, SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST, SuperGroup.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newSuperGroup, SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP, newSuperGroup, newSuperGroup));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.SUPER_GROUP_ELEMENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP, msgs);
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
				case SQLQueryPackage.SUPER_GROUP_ELEMENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
					return eBasicSetContainer(null, SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP, msgs);
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
				case SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST, SuperGroup.class, msgs);
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__NAME:
				return getName();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__LABEL:
				return getLabel();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
				return getSuperGroup();
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
				setSuperGroup((SuperGroup)newValue);
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
				setSuperGroup((SuperGroup)null);
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.SUPER_GROUP_ELEMENT__SUPER_GROUP:
				return getSuperGroup() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLSuperGroupElementImpl
