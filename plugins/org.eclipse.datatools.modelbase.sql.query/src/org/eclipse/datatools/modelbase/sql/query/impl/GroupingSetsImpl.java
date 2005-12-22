/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsImpl.java,v 1.3 2005/12/19 20:56:37 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Grouping Sets</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsImpl#getGroupingSetsElementList <em>Grouping Sets Element List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupingSetsImpl extends GroupingSpecificationImpl implements GroupingSets {
	/**
	 * The cached value of the '{@link #getGroupingSetsElementList() <em>Grouping Sets Element List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGroupingSetsElementList()
	 * @generated
	 * @ordered
	 */
    protected EList groupingSetsElementList = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected GroupingSetsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getGroupingSets();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getGroupingSetsElementList() {
		if (groupingSetsElementList == null) {
			groupingSetsElementList = new EObjectContainmentWithInverseEList(GroupingSetsElement.class, this, SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST, SQLQueryModelPackage.GROUPING_SETS_ELEMENT__GROUPING_SETS);
		}
		return groupingSetsElementList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.GROUPING_SETS__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING_SETS__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.GROUPING_SETS__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
					return ((InternalEList)getGroupingSetsElementList()).basicAdd(otherEnd, msgs);
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
				case SQLQueryModelPackage.GROUPING_SETS__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING_SETS__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.GROUPING_SETS__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryModelPackage.GROUPING_SETS__QUERY_SELECT, msgs);
				case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
					return ((InternalEList)getGroupingSetsElementList()).basicRemove(otherEnd, msgs);
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
				case SQLQueryModelPackage.GROUPING_SETS__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__GROUP_BY_CLAUSE, QuerySelect.class, msgs);
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
			case SQLQueryModelPackage.GROUPING_SETS__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.GROUPING_SETS__NAME:
				return getName();
			case SQLQueryModelPackage.GROUPING_SETS__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.GROUPING_SETS__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.GROUPING_SETS__LABEL:
				return getLabel();
			case SQLQueryModelPackage.GROUPING_SETS__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
				return getGroupingSetsElementList();
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
			case SQLQueryModelPackage.GROUPING_SETS__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
				getGroupingSetsElementList().clear();
				getGroupingSetsElementList().addAll((Collection)newValue);
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
			case SQLQueryModelPackage.GROUPING_SETS__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.GROUPING_SETS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING_SETS__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.GROUPING_SETS__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING_SETS__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.GROUPING_SETS__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
				getGroupingSetsElementList().clear();
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
			case SQLQueryModelPackage.GROUPING_SETS__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.GROUPING_SETS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.GROUPING_SETS__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.GROUPING_SETS__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.GROUPING_SETS__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.GROUPING_SETS__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryModelPackage.GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST:
				return groupingSetsElementList != null && !groupingSetsElementList.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLGroupingSetsImpl
