/**
 * <copyright>
 * </copyright>
 *
 * $Id: QuerySelectStatementImpl.java,v 1.5 2005/02/03 22:58:56 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.OrderBySpecification;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Select Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectStatementImpl#getQueryExpr <em>Query Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectStatementImpl#getOrderByClause <em>Order By Clause</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QuerySelectStatementImpl extends QueryStatementImpl implements QuerySelectStatement {
	/**
	 * The cached value of the '{@link #getQueryExpr() <em>Query Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getQueryExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryExpressionRoot queryExpr = null;

	/**
	 * The cached value of the '{@link #getOrderByClause() <em>Order By Clause</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOrderByClause()
	 * @generated
	 * @ordered
	 */
    protected EList orderByClause = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected QuerySelectStatementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getQuerySelectStatement();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryExpressionRoot getQueryExpr() {
		return queryExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetQueryExpr(QueryExpressionRoot newQueryExpr, NotificationChain msgs) {
		QueryExpressionRoot oldQueryExpr = queryExpr;
		queryExpr = newQueryExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR, oldQueryExpr, newQueryExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQueryExpr(QueryExpressionRoot newQueryExpr) {
		if (newQueryExpr != queryExpr) {
			NotificationChain msgs = null;
			if (queryExpr != null)
				msgs = ((InternalEObject)queryExpr).eInverseRemove(this, SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT, QueryExpressionRoot.class, msgs);
			if (newQueryExpr != null)
				msgs = ((InternalEObject)newQueryExpr).eInverseAdd(this, SQLQueryPackage.QUERY_EXPRESSION_ROOT__SELECT_STATEMENT, QueryExpressionRoot.class, msgs);
			msgs = basicSetQueryExpr(newQueryExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR, newQueryExpr, newQueryExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getOrderByClause() {
		if (orderByClause == null) {
			orderByClause = new EObjectContainmentWithInverseEList(OrderBySpecification.class, this, SQLQueryPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE, SQLQueryPackage.ORDER_BY_SPECIFICATION__SELECT_STATEMENT);
		}
		return orderByClause;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.QUERY_SELECT_STATEMENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
					if (queryExpr != null)
						msgs = ((InternalEObject)queryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR, null, msgs);
					return basicSetQueryExpr((QueryExpressionRoot)otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
					return ((InternalEList)getOrderByClause()).basicAdd(otherEnd, msgs);
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
				case SQLQueryPackage.QUERY_SELECT_STATEMENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT_STATEMENT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
					return basicSetQueryExpr(null, msgs);
				case SQLQueryPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
					return ((InternalEList)getOrderByClause()).basicRemove(otherEnd, msgs);
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
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__NAME:
				return getName();
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__LABEL:
				return getLabel();
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
				return getQueryExpr();
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
				return getOrderByClause();
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
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
				setQueryExpr((QueryExpressionRoot)newValue);
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
				getOrderByClause().clear();
				getOrderByClause().addAll((Collection)newValue);
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
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
				setQueryExpr((QueryExpressionRoot)null);
				return;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
				getOrderByClause().clear();
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
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__QUERY_EXPR:
				return queryExpr != null;
			case SQLQueryPackage.QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE:
				return orderByClause != null && !orderByClause.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLSelectStatementImpl
