/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsElementSublistImpl.java,v 1.1 2005/12/16 13:11:12 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingSets;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression;
import org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Grouping Sets Element Sublist</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementSublistImpl#getGroupingSetsElementExprList <em>Grouping Sets Element Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupingSetsElementSublistImpl extends GroupingSetsElementImpl implements GroupingSetsElementSublist {
	/**
	 * The cached value of the '{@link #getGroupingSetsElementExprList() <em>Grouping Sets Element Expr List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGroupingSetsElementExprList()
	 * @generated
	 * @ordered
	 */
    protected EList groupingSetsElementExprList = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected GroupingSetsElementSublistImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getGroupingSetsElementSublist();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getGroupingSetsElementExprList() {
		if (groupingSetsElementExprList == null) {
			groupingSetsElementExprList = new EObjectContainmentWithInverseEList(GroupingSetsElementExpression.class, this, SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST, SQLQueryPackage.GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST);
		}
		return groupingSetsElementExprList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
					return ((InternalEList)getGroupingSetsElementExprList()).basicAdd(otherEnd, msgs);
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
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS:
					return eBasicSetContainer(null, SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS, msgs);
				case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
					return ((InternalEList)getGroupingSetsElementExprList()).basicRemove(otherEnd, msgs);
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
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__NAME:
				return getName();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__LABEL:
				return getLabel();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS:
				return getGroupingSets();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
				return getGroupingSetsElementExprList();
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
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS:
				setGroupingSets((GroupingSets)newValue);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
				getGroupingSetsElementExprList().clear();
				getGroupingSetsElementExprList().addAll((Collection)newValue);
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
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS:
				setGroupingSets((GroupingSets)null);
				return;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
				getGroupingSetsElementExprList().clear();
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
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS:
				return getGroupingSets() != null;
			case SQLQueryPackage.GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST:
				return groupingSetsElementExprList != null && !groupingSetsElementExprList.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLGroupingSetsElementSublistImpl
