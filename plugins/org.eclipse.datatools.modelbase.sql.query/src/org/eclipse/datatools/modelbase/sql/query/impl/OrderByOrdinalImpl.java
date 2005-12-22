/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderByOrdinalImpl.java,v 1.3 2005/12/19 20:56:36 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.NullOrderingType;
import org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal;
import org.eclipse.datatools.modelbase.sql.query.OrderingSpecType;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Order By Ordinal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderByOrdinalImpl#getOrdinalValue <em>Ordinal Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderByOrdinalImpl extends OrderBySpecificationImpl implements OrderByOrdinal {
	/**
	 * The default value of the '{@link #getOrdinalValue() <em>Ordinal Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOrdinalValue()
	 * @generated
	 * @ordered
	 */
    protected static final int ORDINAL_VALUE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getOrdinalValue() <em>Ordinal Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOrdinalValue()
	 * @generated
	 * @ordered
	 */
    protected int ordinalValue = ORDINAL_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected OrderByOrdinalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getOrderByOrdinal();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getOrdinalValue() {
		return ordinalValue;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setOrdinalValue(int newOrdinalValue) {
		int oldOrdinalValue = ordinalValue;
		ordinalValue = newOrdinalValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDINAL_VALUE, oldOrdinalValue, ordinalValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.ORDER_BY_ORDINAL__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.ORDER_BY_ORDINAL__SELECT_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.ORDER_BY_ORDINAL__SELECT_STATEMENT, msgs);
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
				case SQLQueryModelPackage.ORDER_BY_ORDINAL__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.ORDER_BY_ORDINAL__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.ORDER_BY_ORDINAL__SELECT_STATEMENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.ORDER_BY_ORDINAL__SELECT_STATEMENT, msgs);
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
				case SQLQueryModelPackage.ORDER_BY_ORDINAL__SELECT_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE, QuerySelectStatement.class, msgs);
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
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__NAME:
				return getName();
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__LABEL:
				return getLabel();
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DESCENDING:
				return isDescending() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDERING_SPEC_OPTION:
				return getOrderingSpecOption();
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__NULL_ORDERING_OPTION:
				return getNullOrderingOption();
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__SELECT_STATEMENT:
				return getSelectStatement();
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDINAL_VALUE:
				return new Integer(getOrdinalValue());
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
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DESCENDING:
				setDescending(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDERING_SPEC_OPTION:
				setOrderingSpecOption((OrderingSpecType)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__NULL_ORDERING_OPTION:
				setNullOrderingOption((NullOrderingType)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__SELECT_STATEMENT:
				setSelectStatement((QuerySelectStatement)newValue);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDINAL_VALUE:
				setOrdinalValue(((Integer)newValue).intValue());
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
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DESCENDING:
				setDescending(DESCENDING_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDERING_SPEC_OPTION:
				setOrderingSpecOption(ORDERING_SPEC_OPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__NULL_ORDERING_OPTION:
				setNullOrderingOption(NULL_ORDERING_OPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__SELECT_STATEMENT:
				setSelectStatement((QuerySelectStatement)null);
				return;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDINAL_VALUE:
				setOrdinalValue(ORDINAL_VALUE_EDEFAULT);
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
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__DESCENDING:
				return descending != DESCENDING_EDEFAULT;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDERING_SPEC_OPTION:
				return orderingSpecOption != ORDERING_SPEC_OPTION_EDEFAULT;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__NULL_ORDERING_OPTION:
				return nullOrderingOption != NULL_ORDERING_OPTION_EDEFAULT;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__SELECT_STATEMENT:
				return getSelectStatement() != null;
			case SQLQueryModelPackage.ORDER_BY_ORDINAL__ORDINAL_VALUE:
				return ordinalValue != ORDINAL_VALUE_EDEFAULT;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (ordinalValue: ");
		result.append(ordinalValue);
		result.append(')');
		return result.toString();
	}

} //SQLOrderByOrdinalImpl
