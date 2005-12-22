/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateQuantifiedValueSelectImpl.java,v 1.3 2005/12/19 20:56:37 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
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
 * An implementation of the model object '<em><b>SQL Predicate Quantified Value Select</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl#getQuantifiedType <em>Quantified Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl#getComparisonOperator <em>Comparison Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl#getQueryExpr <em>Query Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredicateQuantifiedValueSelectImpl extends PredicateQuantifiedImpl implements PredicateQuantifiedValueSelect {
	/**
	 * The default value of the '{@link #getQuantifiedType() <em>Quantified Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getQuantifiedType()
	 * @generated
	 * @ordered
	 */
    protected static final PredicateQuantifiedType QUANTIFIED_TYPE_EDEFAULT = PredicateQuantifiedType.SOME_LITERAL;

	/**
	 * The cached value of the '{@link #getQuantifiedType() <em>Quantified Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getQuantifiedType()
	 * @generated
	 * @ordered
	 */
    protected PredicateQuantifiedType quantifiedType = QUANTIFIED_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getComparisonOperator() <em>Comparison Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getComparisonOperator()
	 * @generated
	 * @ordered
	 */
    protected static final PredicateComparisonOperator COMPARISON_OPERATOR_EDEFAULT = PredicateComparisonOperator.EQUAL_LITERAL;

	/**
	 * The cached value of the '{@link #getComparisonOperator() <em>Comparison Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getComparisonOperator()
	 * @generated
	 * @ordered
	 */
    protected PredicateComparisonOperator comparisonOperator = COMPARISON_OPERATOR_EDEFAULT;

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
	 * The cached value of the '{@link #getValueExpr() <em>Value Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValueExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryValueExpression valueExpr = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected PredicateQuantifiedValueSelectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryModelPackage.eINSTANCE.getPredicateQuantifiedValueSelect();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateQuantifiedType getQuantifiedType() {
		return quantifiedType;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setQuantifiedType(PredicateQuantifiedType newQuantifiedType) {
		PredicateQuantifiedType oldQuantifiedType = quantifiedType;
		quantifiedType = newQuantifiedType == null ? QUANTIFIED_TYPE_EDEFAULT : newQuantifiedType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE, oldQuantifiedType, quantifiedType));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PredicateComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setComparisonOperator(PredicateComparisonOperator newComparisonOperator) {
		PredicateComparisonOperator oldComparisonOperator = comparisonOperator;
		comparisonOperator = newComparisonOperator == null ? COMPARISON_OPERATOR_EDEFAULT : newComparisonOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR, oldComparisonOperator, comparisonOperator));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR, oldQueryExpr, newQueryExpr);
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
				msgs = ((InternalEObject)queryExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT, QueryExpressionRoot.class, msgs);
			if (newQueryExpr != null)
				msgs = ((InternalEObject)newQueryExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT, QueryExpressionRoot.class, msgs);
			msgs = basicSetQueryExpr(newQueryExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR, newQueryExpr, newQueryExpr));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR, oldValueExpr, newValueExpr);
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
				msgs = ((InternalEObject)valueExpr).eInverseRemove(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT, QueryValueExpression.class, msgs);
			if (newValueExpr != null)
				msgs = ((InternalEObject)newValueExpr).eInverseAdd(this, SQLQueryModelPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT, QueryValueExpression.class, msgs);
			msgs = basicSetValueExpr(newValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR, newValueExpr, newValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__UPDATE_STATEMENT, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DELETE_STATEMENT, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__TABLE_JOINED:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__TABLE_JOINED, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_LEFT, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_RIGHT, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_HAVING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_HAVING, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_WHERE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_WHERE, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEST, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
					if (queryExpr != null)
						msgs = ((InternalEObject)queryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR, null, msgs);
					return basicSetQueryExpr((QueryExpressionRoot)otherEnd, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
					if (valueExpr != null)
						msgs = ((InternalEObject)valueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR, null, msgs);
					return basicSetValueExpr((QueryValueExpression)otherEnd, msgs);
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
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__UPDATE_STATEMENT, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DELETE_STATEMENT, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__TABLE_JOINED:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__TABLE_JOINED, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_LEFT, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_RIGHT, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_HAVING:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_HAVING, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_WHERE:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_WHERE, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEST:
					return eBasicSetContainer(null, SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEST, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
					return basicSetQueryExpr(null, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
					return basicSetValueExpr(null, msgs);
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
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__UPDATE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_UPDATE_STATEMENT__WHERE_CLAUSE, QueryUpdateStatement.class, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DELETE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, QueryDeleteStatement.class, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__TABLE_JOINED:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.TABLE_JOINED__JOIN_CONDITION, TableJoined.class, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_HAVING:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__HAVING_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_WHERE:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.QUERY_SELECT__WHERE_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, ValueExpressionCaseSearchContent.class, msgs);
				case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEST:
					return eContainer.eInverseRemove(this, SQLQueryModelPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, SearchConditionNested.class, msgs);
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
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NAME:
				return getName();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DEPENDENCIES:
				return getDependencies();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DESCRIPTION:
				return getDescription();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__LABEL:
				return getLabel();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEGATED_CONDITION:
				return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__TABLE_JOINED:
				return getTableJoined();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_HAVING:
				return getQuerySelectHaving();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_WHERE:
				return getQuerySelectWhere();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEST:
				return getNest();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEGATED_PREDICATE:
				return isNegatedPredicate() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__HAS_SELECTIVITY:
				return isHasSelectivity() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__SELECTIVITY_VALUE:
				return getSelectivityValue();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE:
				return getQuantifiedType();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR:
				return getComparisonOperator();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
				return getQueryExpr();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
				return getValueExpr();
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
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NAME:
				setName((String)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEGATED_CONDITION:
				setNegatedCondition(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__TABLE_JOINED:
				setTableJoined((TableJoined)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEST:
				setNest((SearchConditionNested)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEGATED_PREDICATE:
				setNegatedPredicate(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__HAS_SELECTIVITY:
				setHasSelectivity(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__SELECTIVITY_VALUE:
				setSelectivityValue((Integer)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE:
				setQuantifiedType((PredicateQuantifiedType)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR:
				setComparisonOperator((PredicateComparisonOperator)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
				setQueryExpr((QueryExpressionRoot)newValue);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
				setValueExpr((QueryValueExpression)newValue);
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
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEGATED_CONDITION:
				setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__TABLE_JOINED:
				setTableJoined((TableJoined)null);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)null);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)null);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)null);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)null);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEST:
				setNest((SearchConditionNested)null);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEGATED_PREDICATE:
				setNegatedPredicate(NEGATED_PREDICATE_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__HAS_SELECTIVITY:
				setHasSelectivity(HAS_SELECTIVITY_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__SELECTIVITY_VALUE:
				setSelectivityValue(SELECTIVITY_VALUE_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE:
				setQuantifiedType(QUANTIFIED_TYPE_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR:
				setComparisonOperator(COMPARISON_OPERATOR_EDEFAULT);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
				setQueryExpr((QueryExpressionRoot)null);
				return;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
				setValueExpr((QueryValueExpression)null);
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
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEGATED_CONDITION:
				return negatedCondition != NEGATED_CONDITION_EDEFAULT;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__TABLE_JOINED:
				return getTableJoined() != null;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_HAVING:
				return getQuerySelectHaving() != null;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_WHERE:
				return getQuerySelectWhere() != null;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEST:
				return getNest() != null;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__NEGATED_PREDICATE:
				return negatedPredicate != NEGATED_PREDICATE_EDEFAULT;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__HAS_SELECTIVITY:
				return hasSelectivity != HAS_SELECTIVITY_EDEFAULT;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__SELECTIVITY_VALUE:
				return SELECTIVITY_VALUE_EDEFAULT == null ? selectivityValue != null : !SELECTIVITY_VALUE_EDEFAULT.equals(selectivityValue);
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE:
				return quantifiedType != QUANTIFIED_TYPE_EDEFAULT;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR:
				return comparisonOperator != COMPARISON_OPERATOR_EDEFAULT;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR:
				return queryExpr != null;
			case SQLQueryModelPackage.PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR:
				return valueExpr != null;
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
		result.append(" (quantifiedType: ");
		result.append(quantifiedType);
		result.append(", comparisonOperator: ");
		result.append(comparisonOperator);
		result.append(')');
		return result.toString();
	}

} //SQLPredicateQuantifiedValueSelectImpl
