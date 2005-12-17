/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateQuantifiedRowSelectImpl.java,v 1.1 2005/12/16 13:11:11 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Predicate Quantified Row Select</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedRowSelectImpl#getQuantifiedType <em>Quantified Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedRowSelectImpl#getQueryExpr <em>Query Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedRowSelectImpl#getValueExprList <em>Value Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredicateQuantifiedRowSelectImpl extends PredicateQuantifiedImpl implements PredicateQuantifiedRowSelect {
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
	 * The cached value of the '{@link #getQueryExpr() <em>Query Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getQueryExpr()
	 * @generated
	 * @ordered
	 */
    protected QueryExpressionRoot queryExpr = null;

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
    protected PredicateQuantifiedRowSelectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getPredicateQuantifiedRowSelect();
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
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUANTIFIED_TYPE, oldQuantifiedType, quantifiedType));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR, oldQueryExpr, newQueryExpr);
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
				msgs = ((InternalEObject)queryExpr).eInverseRemove(this, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT, QueryExpressionRoot.class, msgs);
			if (newQueryExpr != null)
				msgs = ((InternalEObject)newQueryExpr).eInverseAdd(this, SQLQueryPackage.QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT, QueryExpressionRoot.class, msgs);
			msgs = basicSetQueryExpr(newQueryExpr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR, newQueryExpr, newQueryExpr));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getValueExprList() {
		if (valueExprList == null) {
			valueExprList = new EObjectContainmentWithInverseEList(QueryValueExpression.class, this, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST, SQLQueryPackage.QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT);
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
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__UPDATE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DELETE_STATEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__TABLE_JOINED:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__TABLE_JOINED, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_LEFT, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_HAVING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_WHERE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEST, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR:
					if (queryExpr != null)
						msgs = ((InternalEObject)queryExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR, null, msgs);
					return basicSetQueryExpr((QueryExpressionRoot)otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST:
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
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__UPDATE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__UPDATE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DELETE_STATEMENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DELETE_STATEMENT, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__TABLE_JOINED:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__TABLE_JOINED, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_LEFT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_LEFT, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_RIGHT, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_HAVING:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_HAVING, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_WHERE:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_WHERE, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEST:
					return eBasicSetContainer(null, SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEST, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR:
					return basicSetQueryExpr(null, msgs);
				case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST:
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
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NAME:
				return getName();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__LABEL:
				return getLabel();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEGATED_CONDITION:
				return isNegatedCondition() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__UPDATE_STATEMENT:
				return getUpdateStatement();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DELETE_STATEMENT:
				return getDeleteStatement();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__TABLE_JOINED:
				return getTableJoined();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_LEFT:
				return getCombinedLeft();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_RIGHT:
				return getCombinedRight();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_HAVING:
				return getQuerySelectHaving();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_WHERE:
				return getQuerySelectWhere();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEST:
				return getNest();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEGATED_PREDICATE:
				return isNegatedPredicate() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__HAS_SELECTIVITY:
				return isHasSelectivity() ? Boolean.TRUE : Boolean.FALSE;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__SELECTIVITY_VALUE:
				return getSelectivityValue();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUANTIFIED_TYPE:
				return getQuantifiedType();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR:
				return getQueryExpr();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST:
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
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__LABEL:
				setLabel((String)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEGATED_CONDITION:
				setNegatedCondition(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__TABLE_JOINED:
				setTableJoined((TableJoined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEST:
				setNest((SearchConditionNested)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEGATED_PREDICATE:
				setNegatedPredicate(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__HAS_SELECTIVITY:
				setHasSelectivity(((Boolean)newValue).booleanValue());
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__SELECTIVITY_VALUE:
				setSelectivityValue((Integer)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUANTIFIED_TYPE:
				setQuantifiedType((PredicateQuantifiedType)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR:
				setQueryExpr((QueryExpressionRoot)newValue);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST:
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
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEGATED_CONDITION:
				setNegatedCondition(NEGATED_CONDITION_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__UPDATE_STATEMENT:
				setUpdateStatement((QueryUpdateStatement)null);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DELETE_STATEMENT:
				setDeleteStatement((QueryDeleteStatement)null);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__TABLE_JOINED:
				setTableJoined((TableJoined)null);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_LEFT:
				setCombinedLeft((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_RIGHT:
				setCombinedRight((SearchConditionCombined)null);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_HAVING:
				setQuerySelectHaving((QuerySelect)null);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_WHERE:
				setQuerySelectWhere((QuerySelect)null);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEST:
				setNest((SearchConditionNested)null);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEGATED_PREDICATE:
				setNegatedPredicate(NEGATED_PREDICATE_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__HAS_SELECTIVITY:
				setHasSelectivity(HAS_SELECTIVITY_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__SELECTIVITY_VALUE:
				setSelectivityValue(SELECTIVITY_VALUE_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUANTIFIED_TYPE:
				setQuantifiedType(QUANTIFIED_TYPE_EDEFAULT);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR:
				setQueryExpr((QueryExpressionRoot)null);
				return;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST:
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
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEGATED_CONDITION:
				return negatedCondition != NEGATED_CONDITION_EDEFAULT;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__UPDATE_STATEMENT:
				return getUpdateStatement() != null;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__DELETE_STATEMENT:
				return getDeleteStatement() != null;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__TABLE_JOINED:
				return getTableJoined() != null;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_LEFT:
				return getCombinedLeft() != null;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_RIGHT:
				return getCombinedRight() != null;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_HAVING:
				return getQuerySelectHaving() != null;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_WHERE:
				return getQuerySelectWhere() != null;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEST:
				return getNest() != null;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__NEGATED_PREDICATE:
				return negatedPredicate != NEGATED_PREDICATE_EDEFAULT;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__HAS_SELECTIVITY:
				return hasSelectivity != HAS_SELECTIVITY_EDEFAULT;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__SELECTIVITY_VALUE:
				return SELECTIVITY_VALUE_EDEFAULT == null ? selectivityValue != null : !SELECTIVITY_VALUE_EDEFAULT.equals(selectivityValue);
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUANTIFIED_TYPE:
				return quantifiedType != QUANTIFIED_TYPE_EDEFAULT;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR:
				return queryExpr != null;
			case SQLQueryPackage.PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST:
				return valueExprList != null && !valueExprList.isEmpty();
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
		result.append(')');
		return result.toString();
	}

} //SQLPredicateQuantifiedRowSelectImpl
