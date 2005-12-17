/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCaseSearchContentImpl.java,v 1.1 2005/12/16 13:11:12 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
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
 * An implementation of the model object '<em><b>SQL Value Expression Case Search Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSearchContentImpl#getValueExpr <em>Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSearchContentImpl#getSearchCondition <em>Search Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSearchContentImpl#getValueExprCaseSearch <em>Value Expr Case Search</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueExpressionCaseSearchContentImpl extends SQLQueryObjectImpl implements ValueExpressionCaseSearchContent {
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
	 * The cached value of the '{@link #getSearchCondition() <em>Search Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSearchCondition()
	 * @generated
	 * @ordered
	 */
    protected QuerySearchCondition searchCondition = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ValueExpressionCaseSearchContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getValueExpressionCaseSearchContent();
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR, oldValueExpr, newValueExpr);
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
				msgs = ((InternalEObject)valueExpr).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT, QueryValueExpression.class, msgs);
			if (newValueExpr != null)
				msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT, QueryValueExpression.class, msgs);
			msgs = basicSetValueExpr(newValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR, newValueExpr, newValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QuerySearchCondition getSearchCondition() {
		return searchCondition;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetSearchCondition(QuerySearchCondition newSearchCondition, NotificationChain msgs) {
		QuerySearchCondition oldSearchCondition = searchCondition;
		searchCondition = newSearchCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, oldSearchCondition, newSearchCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSearchCondition(QuerySearchCondition newSearchCondition) {
		if (newSearchCondition != searchCondition) {
			NotificationChain msgs = null;
			if (searchCondition != null)
				msgs = ((InternalEObject)searchCondition).eInverseRemove(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT, QuerySearchCondition.class, msgs);
			if (newSearchCondition != null)
				msgs = ((InternalEObject)newSearchCondition).eInverseAdd(this, SQLQueryPackage.QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT, QuerySearchCondition.class, msgs);
			msgs = basicSetSearchCondition(newSearchCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, newSearchCondition, newSearchCondition));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ValueExpressionCaseSearch getValueExprCaseSearch() {
		if (eContainerFeatureID != SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH) return null;
		return (ValueExpressionCaseSearch)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueExprCaseSearch(ValueExpressionCaseSearch newValueExprCaseSearch) {
		if (newValueExprCaseSearch != eContainer || (eContainerFeatureID != SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH && newValueExprCaseSearch != null)) {
			if (EcoreUtil.isAncestor(this, newValueExprCaseSearch))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueExprCaseSearch != null)
				msgs = ((InternalEObject)newValueExprCaseSearch).eInverseAdd(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH__SEARCH_CONTENT_LIST, ValueExpressionCaseSearch.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueExprCaseSearch, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH, newValueExprCaseSearch, newValueExprCaseSearch));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR:
					if (valueExpr != null)
						msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR, null, msgs);
					return basicSetValueExpr((QueryValueExpression)otherEnd, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION:
					if (searchCondition != null)
						msgs = ((InternalEObject)searchCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, null, msgs);
					return basicSetSearchCondition((QuerySearchCondition)otherEnd, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH, msgs);
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
				case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR:
					return basicSetValueExpr(null, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION:
					return basicSetSearchCondition(null, msgs);
				case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH:
					return eBasicSetContainer(null, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH, msgs);
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
				case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH__SEARCH_CONTENT_LIST, ValueExpressionCaseSearch.class, msgs);
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
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__NAME:
				return getName();
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__LABEL:
				return getLabel();
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR:
				return getValueExpr();
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION:
				return getSearchCondition();
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH:
				return getValueExprCaseSearch();
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
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR:
				setValueExpr((QueryValueExpression)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION:
				setSearchCondition((QuerySearchCondition)newValue);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH:
				setValueExprCaseSearch((ValueExpressionCaseSearch)newValue);
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
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR:
				setValueExpr((QueryValueExpression)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION:
				setSearchCondition((QuerySearchCondition)null);
				return;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH:
				setValueExprCaseSearch((ValueExpressionCaseSearch)null);
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
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR:
				return valueExpr != null;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION:
				return searchCondition != null;
			case SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH:
				return getValueExprCaseSearch() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SQLValueExpressionCaseSearchContentImpl
