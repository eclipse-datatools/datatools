/**
 * <copyright>
 * </copyright>
 *
 * $Id: UpdateSourceExprListImpl.java,v 1.6 2005/10/22 01:35:23 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Update Source Expr List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceExprListImpl#getValueExprList <em>Value Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UpdateSourceExprListImpl extends UpdateSourceImpl implements UpdateSourceExprList {
	/**
	 * The cached value of the '{@link #getValueExprList() <em>Value Expr List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getValueExprList()
	 * @generated
	 * @ordered
	 */
  protected EList valueExprList = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected UpdateSourceExprListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getUpdateSourceExprList();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList getValueExprList() {
		if (valueExprList == null) {
			valueExprList = new EObjectContainmentWithInverseEList(QueryValueExpression.class, this, SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST, SQLQueryPackage.QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST);
		}
		return valueExprList;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__UPDATE_ASSIGNMENT_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__UPDATE_ASSIGNMENT_EXPR, msgs);
				case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST:
					return ((InternalEList)getValueExprList()).basicAdd(otherEnd, msgs);
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
				case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__UPDATE_ASSIGNMENT_EXPR:
					return eBasicSetContainer(null, SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__UPDATE_ASSIGNMENT_EXPR, msgs);
				case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST:
					return ((InternalEList)getValueExprList()).basicRemove(otherEnd, msgs);
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
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__NAME:
				return getName();
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__LABEL:
				return getLabel();
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__UPDATE_ASSIGNMENT_EXPR:
				return getUpdateAssignmentExpr();
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST:
				return getValueExprList();
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
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__UPDATE_ASSIGNMENT_EXPR:
				setUpdateAssignmentExpr((UpdateAssignmentExpression)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST:
				getValueExprList().clear();
				getValueExprList().addAll((Collection)newValue);
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
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__UPDATE_ASSIGNMENT_EXPR:
				setUpdateAssignmentExpr((UpdateAssignmentExpression)null);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST:
				getValueExprList().clear();
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
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__UPDATE_ASSIGNMENT_EXPR:
				return getUpdateAssignmentExpr() != null;
			case SQLQueryPackage.UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST:
				return valueExprList != null && !valueExprList.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //UpdateSourceExprListImpl
