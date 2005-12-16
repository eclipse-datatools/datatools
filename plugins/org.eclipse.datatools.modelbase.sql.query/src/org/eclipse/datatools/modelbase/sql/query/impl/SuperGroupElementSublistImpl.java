/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroupElementSublistImpl.java,v 1.6 2005/10/22 01:35:22 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SuperGroup;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression;
import org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Super Group Element Sublist</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementSublistImpl#getSuperGroupElementExprList <em>Super Group Element Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SuperGroupElementSublistImpl extends SuperGroupElementImpl implements SuperGroupElementSublist {
	/**
	 * The cached value of the '{@link #getSuperGroupElementExprList() <em>Super Group Element Expr List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSuperGroupElementExprList()
	 * @generated
	 * @ordered
	 */
    protected EList superGroupElementExprList = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected SuperGroupElementSublistImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getSuperGroupElementSublist();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getSuperGroupElementExprList() {
		if (superGroupElementExprList == null) {
			superGroupElementExprList = new EObjectContainmentWithInverseEList(SuperGroupElementExpression.class, this, SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST, SQLQueryPackage.SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST);
		}
		return superGroupElementExprList;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST:
					return ((InternalEList)getSuperGroupElementExprList()).basicAdd(otherEnd, msgs);
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
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP:
					return eBasicSetContainer(null, SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP, msgs);
				case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST:
					return ((InternalEList)getSuperGroupElementExprList()).basicRemove(otherEnd, msgs);
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__NAME:
				return getName();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__LABEL:
				return getLabel();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP:
				return getSuperGroup();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST:
				return getSuperGroupElementExprList();
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP:
				setSuperGroup((SuperGroup)newValue);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST:
				getSuperGroupElementExprList().clear();
				getSuperGroupElementExprList().addAll((Collection)newValue);
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP:
				setSuperGroup((SuperGroup)null);
				return;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST:
				getSuperGroupElementExprList().clear();
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
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP:
				return getSuperGroup() != null;
			case SQLQueryPackage.SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST:
				return superGroupElementExprList != null && !superGroupElementExprList.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLSuperGroupElementSublistImpl
