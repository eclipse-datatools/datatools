/**
 * <copyright>
 * </copyright>
 *
 * $Id: UpdateSourceQueryImpl.java,v 1.6 2005/10/22 01:35:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Update Source Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceQueryImpl#getQueryExpr <em>Query Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UpdateSourceQueryImpl extends UpdateSourceImpl implements UpdateSourceQuery {
	/**
	 * The cached value of the '{@link #getQueryExpr() <em>Query Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getQueryExpr()
	 * @generated
	 * @ordered
	 */
  protected QueryExpressionBody queryExpr = null;

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected UpdateSourceQueryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getUpdateSourceQuery();
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public QueryExpressionBody getQueryExpr() {
		return queryExpr;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetQueryExpr(QueryExpressionBody newQueryExpr, NotificationChain msgs) {
		QueryExpressionBody oldQueryExpr = queryExpr;
		queryExpr = newQueryExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR, oldQueryExpr, newQueryExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setQueryExpr(QueryExpressionBody newQueryExpr) {
		if (newQueryExpr != queryExpr) {
			NotificationChain msgs = null;
			if (queryExpr != null)
				msgs = ((InternalEObject)queryExpr).eInverseRemove(this, SQLQueryPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY, QueryExpressionBody.class, msgs);
			if (newQueryExpr != null)
				msgs = ((InternalEObject)newQueryExpr).eInverseAdd(this, SQLQueryPackage.QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY, QueryExpressionBody.class, msgs);
			msgs = basicSetQueryExpr(newQueryExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR, newQueryExpr, newQueryExpr));
	}

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.UPDATE_SOURCE_QUERY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_SOURCE_QUERY__UPDATE_ASSIGNMENT_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.UPDATE_SOURCE_QUERY__UPDATE_ASSIGNMENT_EXPR, msgs);
				case SQLQueryPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR:
					if (queryExpr != null)
						msgs = ((InternalEObject)queryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR, null, msgs);
					return basicSetQueryExpr((QueryExpressionBody)otherEnd, msgs);
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
				case SQLQueryPackage.UPDATE_SOURCE_QUERY__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_SOURCE_QUERY__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.UPDATE_SOURCE_QUERY__UPDATE_ASSIGNMENT_EXPR:
					return eBasicSetContainer(null, SQLQueryPackage.UPDATE_SOURCE_QUERY__UPDATE_ASSIGNMENT_EXPR, msgs);
				case SQLQueryPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR:
					return basicSetQueryExpr(null, msgs);
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
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__NAME:
				return getName();
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__LABEL:
				return getLabel();
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__UPDATE_ASSIGNMENT_EXPR:
				return getUpdateAssignmentExpr();
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR:
				return getQueryExpr();
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
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__UPDATE_ASSIGNMENT_EXPR:
				setUpdateAssignmentExpr((UpdateAssignmentExpression)newValue);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR:
				setQueryExpr((QueryExpressionBody)newValue);
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
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__UPDATE_ASSIGNMENT_EXPR:
				setUpdateAssignmentExpr((UpdateAssignmentExpression)null);
				return;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR:
				setQueryExpr((QueryExpressionBody)null);
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
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__UPDATE_ASSIGNMENT_EXPR:
				return getUpdateAssignmentExpr() != null;
			case SQLQueryPackage.UPDATE_SOURCE_QUERY__QUERY_EXPR:
				return queryExpr != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //UpdateSourceQueryImpl
