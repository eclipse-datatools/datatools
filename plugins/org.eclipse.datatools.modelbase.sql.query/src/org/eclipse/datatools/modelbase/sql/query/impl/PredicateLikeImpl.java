/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateLikeImpl.java,v 1.2 2005/12/17 01:46:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
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
 * An implementation of the model object '<em><b>SQL Predicate Like</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl#isNotLike <em>Not Like</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl#getPatternValueExpr <em>Pattern Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl#getMatchingValueExpr <em>Matching Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl#getEscapeValueExpr <em>Escape Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredicateLikeImpl extends PredicateImpl implements PredicateLike {
	/**
	 * The default value of the '{@link #isNotLike() <em>Not Like</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNotLike()
	 * @generated
	 * @ordered
	 */
    protected static final boolean NOT_LIKE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNotLike() <em>Not Like</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isNotLike()
	 * @generated
	 * @ordered
	 */
    protected boolean notLike = NOT_LIKE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPatternValueExpr() <em>Pattern Value Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPatternValueExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryValueExpression patternValueExpr = null;

	/**
	 * The cached value of the '{@link #getMatchingValueExpr() <em>Matching Value Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMatchingValueExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryValueExpression matchingValueExpr = null;

	/**
	 * The cached value of the '{@link #getEscapeValueExpr() <em>Escape Value Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getEscapeValueExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryValueExpression escapeValueExpr = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected PredicateLikeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getPredicateLike();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isNotLike() {
		return notLike;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNotLike(boolean newNotLike) {
		boolean oldNotLike = notLike;
		notLike = newNotLike;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_LIKE__NOT_LIKE, oldNotLike, notLike));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryValueExpression getPatternValueExpr() {
		return patternValueExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetPatternValueExpr(QueryValueExpression newPatternValueExpr, NotificationChain msgs) {
		QueryValueExpression oldPatternValueExpr = patternValueExpr;
		patternValueExpr = newPatternValueExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR, oldPatternValueExpr, newPatternValueExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPatternValueExpr(QueryValueExpression newPatternValueExpr) {
		if (newPatternValueExpr != patternValueExpr) {
			NotificationChain msgs = null;
			if (patternValueExpr != null)
				msgs = ((InternalEObject)patternValueExpr).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN, QueryValueExpression.class, msgs);
			if (newPatternValueExpr != null)
				msgs = ((InternalEObject)newPatternValueExpr).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_PATTERN, QueryValueExpression.class, msgs);
			msgs = basicSetPatternValueExpr(newPatternValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR, newPatternValueExpr, newPatternValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryValueExpression getMatchingValueExpr() {
		return matchingValueExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetMatchingValueExpr(QueryValueExpression newMatchingValueExpr, NotificationChain msgs) {
		QueryValueExpression oldMatchingValueExpr = matchingValueExpr;
		matchingValueExpr = newMatchingValueExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR, oldMatchingValueExpr, newMatchingValueExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMatchingValueExpr(QueryValueExpression newMatchingValueExpr) {
		if (newMatchingValueExpr != matchingValueExpr) {
			NotificationChain msgs = null;
			if (matchingValueExpr != null)
				msgs = ((InternalEObject)matchingValueExpr).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING, QueryValueExpression.class, msgs);
			if (newMatchingValueExpr != null)
				msgs = ((InternalEObject)newMatchingValueExpr).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_MATCHING, QueryValueExpression.class, msgs);
			msgs = basicSetMatchingValueExpr(newMatchingValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR, newMatchingValueExpr, newMatchingValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public QueryValueExpression getEscapeValueExpr() {
		return escapeValueExpr;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetEscapeValueExpr(QueryValueExpression newEscapeValueExpr, NotificationChain msgs) {
		QueryValueExpression oldEscapeValueExpr = escapeValueExpr;
		escapeValueExpr = newEscapeValueExpr;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR, oldEscapeValueExpr, newEscapeValueExpr);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setEscapeValueExpr(QueryValueExpression newEscapeValueExpr) {
		if (newEscapeValueExpr != escapeValueExpr) {
			NotificationChain msgs = null;
			if (escapeValueExpr != null)
				msgs = ((InternalEObject)escapeValueExpr).eInverseRemove(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE, QueryValueExpression.class, msgs);
			if (newEscapeValueExpr != null)
				msgs = ((InternalEObject)newEscapeValueExpr).eInverseAdd(this, SQLQueryPackage.QUERY_VALUE_EXPRESSION__LIKE_ESCAPE, QueryValueExpression.class, msgs);
			msgs = basicSetEscapeValueExpr(newEscapeValueExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR, newEscapeValueExpr, newEscapeValueExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.PREDICATE_LIKE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_LIKE__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_LIKE__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__TABLE_JOINED:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_LIKE__TABLE_JOINED, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_LIKE__COMBINED_LEFT, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_LIKE__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_HAVING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_WHERE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_LIKE__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_LIKE__NEST, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
					if (patternValueExpr != null)
						msgs = ((InternalEObject)patternValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR, null, msgs);
					return basicSetPatternValueExpr((QueryValueExpression)otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
					if (matchingValueExpr != null)
						msgs = ((InternalEObject)matchingValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR, null, msgs);
					return basicSetMatchingValueExpr((QueryValueExpression)otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
					if (escapeValueExpr != null)
						msgs = ((InternalEObject)escapeValueExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR, null, msgs);
					return basicSetEscapeValueExpr((QueryValueExpression)otherEnd, msgs);
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
				case SQLQueryPackage.PREDICATE_LIKE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_LIKE__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_LIKE__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__TABLE_JOINED:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_LIKE__TABLE_JOINED, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_LIKE__COMBINED_LEFT, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_LIKE__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_HAVING:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_WHERE:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_LIKE__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_LIKE__NEST, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
					return basicSetPatternValueExpr(null, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
					return basicSetMatchingValueExpr(null, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
					return basicSetEscapeValueExpr(null, msgs);
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
				case SQLQueryPackage.PREDICATE_LIKE__UPDATE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_UPDATE_STATEMENT__WHERE_CLAUSE, QueryUpdateStatement.class, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__DELETE_STATEMENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_DELETE_STATEMENT__WHERE_CLAUSE, QueryDeleteStatement.class, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__TABLE_JOINED:
					return eContainer.eInverseRemove(this, SQLQueryPackage.TABLE_JOINED__JOIN_CONDITION, TableJoined.class, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__COMBINED_LEFT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SEARCH_CONDITION_COMBINED__LEFT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__COMBINED_RIGHT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SEARCH_CONDITION_COMBINED__RIGHT_CONDITION, SearchConditionCombined.class, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_HAVING:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__HAVING_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_WHERE:
					return eContainer.eInverseRemove(this, SQLQueryPackage.QUERY_SELECT__WHERE_CLAUSE, QuerySelect.class, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eContainer.eInverseRemove(this, SQLQueryPackage.VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION, ValueExpressionCaseSearchContent.class, msgs);
				case SQLQueryPackage.PREDICATE_LIKE__NEST:
					return eContainer.eInverseRemove(this, SQLQueryPackage.SEARCH_CONDITION_NESTED__NESTED_CONDITION, SearchConditionNested.class, msgs);
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
			case SQLQueryPackage.PREDICATE_LIKE__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.PREDICATE_LIKE__NAME:
				return getName();
			case SQLQueryPackage.PREDICATE_LIKE__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.PREDICATE_LIKE__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.PREDICATE_LIKE__LABEL:
				return getLabel();
			case SQLQueryPackage.PREDICATE_LIKE__NEGATED_CONDITION:
				return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_LIKE__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryPackage.PREDICATE_LIKE__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLQueryPackage.PREDICATE_LIKE__TABLE_JOINED:
				return getTableJoined();
			case SQLQueryPackage.PREDICATE_LIKE__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryPackage.PREDICATE_LIKE__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_HAVING:
				return getQuerySelectHaving();
			case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_WHERE:
				return getQuerySelectWhere();
			case SQLQueryPackage.PREDICATE_LIKE__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryPackage.PREDICATE_LIKE__NEST:
				return getNest();
			case SQLQueryPackage.PREDICATE_LIKE__NEGATED_PREDICATE:
				return isNegatedPredicate() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_LIKE__HAS_SELECTIVITY:
				return isHasSelectivity() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_LIKE__SELECTIVITY_VALUE:
				return getSelectivityValue();
			case SQLQueryPackage.PREDICATE_LIKE__NOT_LIKE:
				return isNotLike() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
				return getPatternValueExpr();
			case SQLQueryPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
				return getMatchingValueExpr();
			case SQLQueryPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
				return getEscapeValueExpr();
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
			case SQLQueryPackage.PREDICATE_LIKE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__NEGATED_CONDITION:
				setNegatedCondition(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_LIKE__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__TABLE_JOINED:
				setTableJoined((TableJoined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__NEST:
				setNest((SearchConditionNested)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__NEGATED_PREDICATE:
				setNegatedPredicate(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_LIKE__HAS_SELECTIVITY:
				setHasSelectivity(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_LIKE__SELECTIVITY_VALUE:
				setSelectivityValue((Integer)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__NOT_LIKE:
				setNotLike(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
				setPatternValueExpr((QueryValueExpression)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
				setMatchingValueExpr((QueryValueExpression)newValue);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
				setEscapeValueExpr((QueryValueExpression)newValue);
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
			case SQLQueryPackage.PREDICATE_LIKE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.PREDICATE_LIKE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.PREDICATE_LIKE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__NEGATED_CONDITION:
				setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__TABLE_JOINED:
				setTableJoined((TableJoined)null);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)null);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)null);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__NEST:
				setNest((SearchConditionNested)null);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__NEGATED_PREDICATE:
				setNegatedPredicate(NEGATED_PREDICATE_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__HAS_SELECTIVITY:
				setHasSelectivity(HAS_SELECTIVITY_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__SELECTIVITY_VALUE:
				setSelectivityValue(SELECTIVITY_VALUE_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__NOT_LIKE:
				setNotLike(NOT_LIKE_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
				setPatternValueExpr((QueryValueExpression)null);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
				setMatchingValueExpr((QueryValueExpression)null);
				return;
			case SQLQueryPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
				setEscapeValueExpr((QueryValueExpression)null);
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
			case SQLQueryPackage.PREDICATE_LIKE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.PREDICATE_LIKE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.PREDICATE_LIKE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.PREDICATE_LIKE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.PREDICATE_LIKE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.PREDICATE_LIKE__NEGATED_CONDITION:
				return negatedCondition != NEGATED_CONDITION_EDEFAULT;
			case SQLQueryPackage.PREDICATE_LIKE__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryPackage.PREDICATE_LIKE__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLQueryPackage.PREDICATE_LIKE__TABLE_JOINED:
				return getTableJoined() != null;
			case SQLQueryPackage.PREDICATE_LIKE__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryPackage.PREDICATE_LIKE__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_HAVING:
				return getQuerySelectHaving() != null;
			case SQLQueryPackage.PREDICATE_LIKE__QUERY_SELECT_WHERE:
				return getQuerySelectWhere() != null;
			case SQLQueryPackage.PREDICATE_LIKE__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryPackage.PREDICATE_LIKE__NEST:
				return getNest() != null;
			case SQLQueryPackage.PREDICATE_LIKE__NEGATED_PREDICATE:
				return negatedPredicate != NEGATED_PREDICATE_EDEFAULT;
			case SQLQueryPackage.PREDICATE_LIKE__HAS_SELECTIVITY:
				return hasSelectivity != HAS_SELECTIVITY_EDEFAULT;
			case SQLQueryPackage.PREDICATE_LIKE__SELECTIVITY_VALUE:
				return SELECTIVITY_VALUE_EDEFAULT == null ? selectivityValue != null : !SELECTIVITY_VALUE_EDEFAULT.equals(selectivityValue);
			case SQLQueryPackage.PREDICATE_LIKE__NOT_LIKE:
				return notLike != NOT_LIKE_EDEFAULT;
			case SQLQueryPackage.PREDICATE_LIKE__PATTERN_VALUE_EXPR:
				return patternValueExpr != null;
			case SQLQueryPackage.PREDICATE_LIKE__MATCHING_VALUE_EXPR:
				return matchingValueExpr != null;
			case SQLQueryPackage.PREDICATE_LIKE__ESCAPE_VALUE_EXPR:
				return escapeValueExpr != null;
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
		result.append(" (notLike: ");
		result.append(notLike);
		result.append(')');
		return result.toString();
	}

} //SQLPredicateLikeImpl
