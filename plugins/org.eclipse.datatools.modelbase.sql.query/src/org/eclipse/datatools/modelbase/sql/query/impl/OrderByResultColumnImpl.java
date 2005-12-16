/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderByResultColumnImpl.java,v 1.6 2005/10/22 01:35:23 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.NullOrderingType;
import org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn;
import org.eclipse.datatools.modelbase.sql.query.OrderingSpecType;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Order By Result Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderByResultColumnImpl#getResultCol <em>Result Col</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderByResultColumnImpl extends OrderBySpecificationImpl implements OrderByResultColumn {
	/**
	 * The cached value of the '{@link #getResultCol() <em>Result Col</em>}' reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getResultCol()
	 * @generated
	 * @ordered
	 */
  protected ResultColumn resultCol = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected OrderByResultColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getOrderByResultColumn();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ResultColumn getResultCol() {
		if (resultCol != null && resultCol.eIsProxy()) {
			ResultColumn oldResultCol = resultCol;
			resultCol = (ResultColumn)eResolveProxy((InternalEObject)resultCol);
			if (resultCol != oldResultCol) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLQueryPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL, oldResultCol, resultCol));
			}
		}
		return resultCol;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ResultColumn basicGetResultCol() {
		return resultCol;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetResultCol(ResultColumn newResultCol, NotificationChain msgs) {
		ResultColumn oldResultCol = resultCol;
		resultCol = newResultCol;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL, oldResultCol, newResultCol);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setResultCol(ResultColumn newResultCol) {
		if (newResultCol != resultCol) {
			NotificationChain msgs = null;
			if (resultCol != null)
				msgs = ((InternalEObject)resultCol).eInverseRemove(this, SQLQueryPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL, ResultColumn.class, msgs);
			if (newResultCol != null)
				msgs = ((InternalEObject)newResultCol).eInverseAdd(this, SQLQueryPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL, ResultColumn.class, msgs);
			msgs = basicSetResultCol(newResultCol, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL, newResultCol, newResultCol));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__SELECT_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.ORDER_BY_RESULT_COLUMN__SELECT_STATEMENT, msgs);
				case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
					if (resultCol != null)
						msgs = ((InternalEObject)resultCol).eInverseRemove(this, SQLQueryPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL, ResultColumn.class, msgs);
					return basicSetResultCol((ResultColumn)otherEnd, msgs);
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
				case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__SELECT_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.ORDER_BY_RESULT_COLUMN__SELECT_STATEMENT, msgs);
				case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
					return basicSetResultCol(null, msgs);
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
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__NAME:
				return getName();
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__LABEL:
				return getLabel();
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DESCENDING:
				return isDescending() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__ORDERING_SPEC_OPTION:
				return getOrderingSpecOption();
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__NULL_ORDERING_OPTION:
				return getNullOrderingOption();
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__SELECT_STATEMENT:
				return getSelectStatement();
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
				if (resolve) return getResultCol();
				return basicGetResultCol();
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
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DESCENDING:
				setDescending(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__ORDERING_SPEC_OPTION:
				setOrderingSpecOption((OrderingSpecType)newValue);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__NULL_ORDERING_OPTION:
				setNullOrderingOption((NullOrderingType)newValue);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__SELECT_STATEMENT:
				setSelectStatement((QuerySelectStatement)newValue);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
				setResultCol((ResultColumn)newValue);
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
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DESCENDING:
				setDescending(DESCENDING_EDEFAULT);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__ORDERING_SPEC_OPTION:
				setOrderingSpecOption(ORDERING_SPEC_OPTION_EDEFAULT);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__NULL_ORDERING_OPTION:
				setNullOrderingOption(NULL_ORDERING_OPTION_EDEFAULT);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__SELECT_STATEMENT:
				setSelectStatement((QuerySelectStatement)null);
				return;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
				setResultCol((ResultColumn)null);
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
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__DESCENDING:
				return descending != DESCENDING_EDEFAULT;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__ORDERING_SPEC_OPTION:
				return orderingSpecOption != ORDERING_SPEC_OPTION_EDEFAULT;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__NULL_ORDERING_OPTION:
				return nullOrderingOption != NULL_ORDERING_OPTION_EDEFAULT;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__SELECT_STATEMENT:
				return getSelectStatement() != null;
			case SQLQueryPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL:
				return resultCol != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //OrderByResultColumnImpl
