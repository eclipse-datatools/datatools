/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateBetweenImpl.java,v 1.6 2005/10/22 01:35:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Predicate Between</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl#isNotBetween <em>Not Between</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl#getLeftValueExpr <em>Left Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl#getRightValueExpr1 <em>Right Value Expr1</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl#getRightValueExpr2 <em>Right Value Expr2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredicateBetweenImpl extends PredicateImpl implements PredicateBetween {
	/**
	 * The default value of the '{@link #isNotBetween() <em>Not Between</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNotBetween()
	 * @generated
	 * @ordered
	 */
    protected static final boolean NOT_BETWEEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNotBetween() <em>Not Between</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNotBetween()
	 * @generated
	 * @ordered
	 */
    protected boolean notBetween = NOT_BETWEEN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLeftValueExpr() <em>Left Value Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLeftValueExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryValueExpression leftValueExpr = null;

	/**
	 * The cached value of the '{@link #getRightValueExpr1() <em>Right Value Expr1</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRightValueExpr1()
	 * @generated
	 * @ordered
	 */
    protected QueryValueExpression rightValueExpr1 = null;

	/**
	 * The cached value of the '{@link #getRightValueExpr2() <em>Right Value Expr2</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRightValueExpr2()
	 * @generated
	 * @ordered
	 */
    protected QueryValueExpression rightValueExpr2 = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected PredicateBetweenImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getPredicateBetween();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isNotBetween() {
		return notBetween;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNotBetween(boolean newNotBetween) {
		boolean oldNotBetween = notBetween;
		notBetween = newNotBetween;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_BETWEEN__NOT_BETWEEN, oldNotBetween, notBetween));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryValueExpression getLeftValueExpr() {
		return leftValueExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetLeftValueExpr(QueryValueExpression newLeftValueExpr, NotificationChain msgs) {
		QueryValueExpression oldLeftValueExpr = leftValueExpr;
		leftValueExpr = newLeftValueExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR, oldLeftValueExpr, newLeftValueExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLeftValueExpr(QueryValueExpression newLeftValueExpr) {
		if (newLeftValueExpr != leftValueExpr) {
			NotificationChain msgs = null;
			if (leftValueExpr != null)
				msgs = ((InternalEObject)leftValueExpr).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT, QueryValueExpression.class, msgs);
			if (newLeftValueExpr != null)
				msgs = ((InternalEObject)newLeftValueExpr).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_LEFT, QueryValueExpression.class, msgs);
			msgs = basicSetLeftValueExpr(newLeftValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR, newLeftValueExpr, newLeftValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryValueExpression getRightValueExpr1() {
		return rightValueExpr1;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetRightValueExpr1(QueryValueExpression newRightValueExpr1, NotificationChain msgs) {
		QueryValueExpression oldRightValueExpr1 = rightValueExpr1;
		rightValueExpr1 = newRightValueExpr1;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1, oldRightValueExpr1, newRightValueExpr1);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRightValueExpr1(QueryValueExpression newRightValueExpr1) {
		if (newRightValueExpr1 != rightValueExpr1) {
			NotificationChain msgs = null;
			if (rightValueExpr1 != null)
				msgs = ((InternalEObject)rightValueExpr1).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1, QueryValueExpression.class, msgs);
			if (newRightValueExpr1 != null)
				msgs = ((InternalEObject)newRightValueExpr1).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1, QueryValueExpression.class, msgs);
			msgs = basicSetRightValueExpr1(newRightValueExpr1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1, newRightValueExpr1, newRightValueExpr1));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryValueExpression getRightValueExpr2() {
		return rightValueExpr2;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetRightValueExpr2(QueryValueExpression newRightValueExpr2, NotificationChain msgs) {
		QueryValueExpression oldRightValueExpr2 = rightValueExpr2;
		rightValueExpr2 = newRightValueExpr2;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2, oldRightValueExpr2, newRightValueExpr2);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setRightValueExpr2(QueryValueExpression newRightValueExpr2) {
		if (newRightValueExpr2 != rightValueExpr2) {
			NotificationChain msgs = null;
			if (rightValueExpr2 != null)
				msgs = ((InternalEObject)rightValueExpr2).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2, QueryValueExpression.class, msgs);
			if (newRightValueExpr2 != null)
				msgs = ((InternalEObject)newRightValueExpr2).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2, QueryValueExpression.class, msgs);
			msgs = basicSetRightValueExpr2(newRightValueExpr2, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2, newRightValueExpr2, newRightValueExpr2));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.PREDICATE_BETWEEN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_BETWEEN__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_BETWEEN__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__TABLE_JOINED:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_BETWEEN__TABLE_JOINED, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_LEFT, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_HAVING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_WHERE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_BETWEEN__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_BETWEEN__NEST, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
					if (leftValueExpr != null)
						msgs = ((InternalEObject)leftValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR, null, msgs);
					return basicSetLeftValueExpr((QueryValueExpression)otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
					if (rightValueExpr1 != null)
						msgs = ((InternalEObject)rightValueExpr1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1, null, msgs);
					return basicSetRightValueExpr1((QueryValueExpression)otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
					if (rightValueExpr2 != null)
						msgs = ((InternalEObject)rightValueExpr2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2, null, msgs);
					return basicSetRightValueExpr2((QueryValueExpression)otherEnd, msgs);
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
				case SQLQueryPackage.PREDICATE_BETWEEN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_BETWEEN__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_BETWEEN__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__TABLE_JOINED:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_BETWEEN__TABLE_JOINED, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_LEFT, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_HAVING:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_WHERE:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_BETWEEN__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_BETWEEN__NEST, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
					return basicSetLeftValueExpr(null, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
					return basicSetRightValueExpr1(null, msgs);
				case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
					return basicSetRightValueExpr2(null, msgs);
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
			case SQLQueryPackage.PREDICATE_BETWEEN__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.PREDICATE_BETWEEN__NAME:
				return getName();
			case SQLQueryPackage.PREDICATE_BETWEEN__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.PREDICATE_BETWEEN__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.PREDICATE_BETWEEN__LABEL:
				return getLabel();
			case SQLQueryPackage.PREDICATE_BETWEEN__NEGATED_CONDITION:
				return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_BETWEEN__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryPackage.PREDICATE_BETWEEN__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLQueryPackage.PREDICATE_BETWEEN__TABLE_JOINED:
				return getTableJoined();
			case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_HAVING:
				return getQuerySelectHaving();
			case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_WHERE:
				return getQuerySelectWhere();
			case SQLQueryPackage.PREDICATE_BETWEEN__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryPackage.PREDICATE_BETWEEN__NEST:
				return getNest();
			case SQLQueryPackage.PREDICATE_BETWEEN__NEGATED_PREDICATE:
				return isNegatedPredicate() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_BETWEEN__HAS_SELECTIVITY:
				return isHasSelectivity() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_BETWEEN__SELECTIVITY_VALUE:
				return getSelectivityValue();
			case SQLQueryPackage.PREDICATE_BETWEEN__NOT_BETWEEN:
				return isNotBetween() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
				return getLeftValueExpr();
			case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
				return getRightValueExpr1();
			case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
				return getRightValueExpr2();
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
			case SQLQueryPackage.PREDICATE_BETWEEN__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__NEGATED_CONDITION:
				setNegatedCondition(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__TABLE_JOINED:
				setTableJoined((TableJoined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__NEST:
				setNest((SearchConditionNested)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__NEGATED_PREDICATE:
				setNegatedPredicate(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__HAS_SELECTIVITY:
				setHasSelectivity(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__SELECTIVITY_VALUE:
				setSelectivityValue((Integer)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__NOT_BETWEEN:
				setNotBetween(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
				setLeftValueExpr((QueryValueExpression)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
				setRightValueExpr1((QueryValueExpression)newValue);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
				setRightValueExpr2((QueryValueExpression)newValue);
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
			case SQLQueryPackage.PREDICATE_BETWEEN__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__NEGATED_CONDITION:
				setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__TABLE_JOINED:
				setTableJoined((TableJoined)null);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)null);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)null);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__NEST:
				setNest((SearchConditionNested)null);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__NEGATED_PREDICATE:
				setNegatedPredicate(NEGATED_PREDICATE_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__HAS_SELECTIVITY:
				setHasSelectivity(HAS_SELECTIVITY_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__SELECTIVITY_VALUE:
				setSelectivityValue(SELECTIVITY_VALUE_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__NOT_BETWEEN:
				setNotBetween(NOT_BETWEEN_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
				setLeftValueExpr((QueryValueExpression)null);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
				setRightValueExpr1((QueryValueExpression)null);
				return;
			case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
				setRightValueExpr2((QueryValueExpression)null);
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
			case SQLQueryPackage.PREDICATE_BETWEEN__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.PREDICATE_BETWEEN__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.PREDICATE_BETWEEN__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.PREDICATE_BETWEEN__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.PREDICATE_BETWEEN__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.PREDICATE_BETWEEN__NEGATED_CONDITION:
				return negatedCondition != NEGATED_CONDITION_EDEFAULT;
			case SQLQueryPackage.PREDICATE_BETWEEN__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryPackage.PREDICATE_BETWEEN__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLQueryPackage.PREDICATE_BETWEEN__TABLE_JOINED:
				return getTableJoined() != null;
			case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryPackage.PREDICATE_BETWEEN__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_HAVING:
				return getQuerySelectHaving() != null;
			case SQLQueryPackage.PREDICATE_BETWEEN__QUERY_SELECT_WHERE:
				return getQuerySelectWhere() != null;
			case SQLQueryPackage.PREDICATE_BETWEEN__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryPackage.PREDICATE_BETWEEN__NEST:
				return getNest() != null;
			case SQLQueryPackage.PREDICATE_BETWEEN__NEGATED_PREDICATE:
				return negatedPredicate != NEGATED_PREDICATE_EDEFAULT;
			case SQLQueryPackage.PREDICATE_BETWEEN__HAS_SELECTIVITY:
				return hasSelectivity != HAS_SELECTIVITY_EDEFAULT;
			case SQLQueryPackage.PREDICATE_BETWEEN__SELECTIVITY_VALUE:
				return SELECTIVITY_VALUE_EDEFAULT == null ? selectivityValue != null : !SELECTIVITY_VALUE_EDEFAULT.equals(selectivityValue);
			case SQLQueryPackage.PREDICATE_BETWEEN__NOT_BETWEEN:
				return notBetween != NOT_BETWEEN_EDEFAULT;
			case SQLQueryPackage.PREDICATE_BETWEEN__LEFT_VALUE_EXPR:
				return leftValueExpr != null;
			case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1:
				return rightValueExpr1 != null;
			case SQLQueryPackage.PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2:
				return rightValueExpr2 != null;
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
		result.append(" (notBetween: ");
		result.append(notBetween);
		result.append(')');
		return result.toString();
	}

} //SQLPredicateBetweenImpl
