/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResultColumnImpl.java,v 1.2 2005/12/17 01:46:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Result Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ResultColumnImpl#getValueExpr <em>Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ResultColumnImpl#getOrderByResultCol <em>Order By Result Col</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultColumnImpl extends QueryResultSpecificationImpl implements ResultColumn {
	/**
	 * The cached value of the '{@link #getValueExpr() <em>Value Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValueExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryValueExpression valueExpr = null;

	/**
	 * The cached value of the '{@link #getOrderByResultCol() <em>Order By Result Col</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOrderByResultCol()
	 * @generated
	 * @ordered
	 */
    protected EList orderByResultCol = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ResultColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getResultColumn();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryValueExpression getValueExpr() {
		return valueExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetValueExpr(QueryValueExpression newValueExpr, NotificationChain msgs) {
		QueryValueExpression oldValueExpr = valueExpr;
		valueExpr = newValueExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.RESULT_COLUMN__VALUE_EXPR, oldValueExpr, newValueExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExpr(QueryValueExpression newValueExpr) {
		if (newValueExpr != valueExpr) {
			NotificationChain msgs = null;
			if (valueExpr != null)
				msgs = ((InternalEObject)valueExpr).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN, QueryValueExpression.class, msgs);
			if (newValueExpr != null)
				msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__RESULT_COLUMN, QueryValueExpression.class, msgs);
			msgs = basicSetValueExpr(newValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.RESULT_COLUMN__VALUE_EXPR, newValueExpr, newValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getOrderByResultCol() {
		if (orderByResultCol == null) {
			orderByResultCol = new EObjectWithInverseResolvingEList(OrderByResultColumn.class, this, SQLQueryPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL, SQLQueryPackage.ORDER_BY_RESULT_COLUMN__RESULT_COL);
		}
		return orderByResultCol;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.RESULT_COLUMN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.RESULT_COLUMN__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.RESULT_COLUMN__QUERY_SELECT, msgs);
				case SQLQueryPackage.RESULT_COLUMN__VALUE_EXPR:
					if (valueExpr != null)
						msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.RESULT_COLUMN__VALUE_EXPR, null, msgs);
					return basicSetValueExpr((QueryValueExpression)otherEnd, msgs);
				case SQLQueryPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
					return ((InternalEList)getOrderByResultCol()).basicAdd(otherEnd, msgs);
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
				case SQLQueryPackage.RESULT_COLUMN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.RESULT_COLUMN__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.RESULT_COLUMN__QUERY_SELECT:
					return eBasicSetContainer(null, SQLQueryPackage.RESULT_COLUMN__QUERY_SELECT, msgs);
				case SQLQueryPackage.RESULT_COLUMN__VALUE_EXPR:
					return basicSetValueExpr(null, msgs);
				case SQLQueryPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
					return ((InternalEList)getOrderByResultCol()).basicRemove(otherEnd, msgs);
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
				case SQLQueryPackage.RESULT_COLUMN__QUERY_SELECT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__SELECT_CLAUSE, QuerySelect.class, msgs);
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
			case SQLQueryPackage.RESULT_COLUMN__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.RESULT_COLUMN__NAME:
				return getName();
			case SQLQueryPackage.RESULT_COLUMN__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.RESULT_COLUMN__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.RESULT_COLUMN__LABEL:
				return getLabel();
			case SQLQueryPackage.RESULT_COLUMN__QUERY_SELECT:
				return getQuerySelect();
			case SQLQueryPackage.RESULT_COLUMN__VALUE_EXPR:
				return getValueExpr();
			case SQLQueryPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
				return getOrderByResultCol();
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
			case SQLQueryPackage.RESULT_COLUMN__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.RESULT_COLUMN__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.RESULT_COLUMN__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.RESULT_COLUMN__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.RESULT_COLUMN__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.RESULT_COLUMN__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLQueryPackage.RESULT_COLUMN__VALUE_EXPR:
				setValueExpr((QueryValueExpression)newValue);
				return;
			case SQLQueryPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
				getOrderByResultCol().clear();
				getOrderByResultCol().addAll((Collection)newValue);
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
			case SQLQueryPackage.RESULT_COLUMN__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.RESULT_COLUMN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.RESULT_COLUMN__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.RESULT_COLUMN__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.RESULT_COLUMN__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.RESULT_COLUMN__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLQueryPackage.RESULT_COLUMN__VALUE_EXPR:
				setValueExpr((QueryValueExpression)null);
				return;
			case SQLQueryPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
				getOrderByResultCol().clear();
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
			case SQLQueryPackage.RESULT_COLUMN__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.RESULT_COLUMN__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.RESULT_COLUMN__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.RESULT_COLUMN__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.RESULT_COLUMN__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.RESULT_COLUMN__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLQueryPackage.RESULT_COLUMN__VALUE_EXPR:
				return valueExpr != null;
			case SQLQueryPackage.RESULT_COLUMN__ORDER_BY_RESULT_COL:
				return orderByResultCol != null && !orderByResultCol.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLResultColumnImpl
